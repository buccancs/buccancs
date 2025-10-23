package io.grpc.xds;

import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.util.ForwardingLoadBalancerHelper;
import io.grpc.util.GracefulSwitchLoadBalancer;
import io.grpc.xds.ClusterManagerLoadBalancerProvider;
import io.grpc.xds.XdsLogger;
import io.grpc.xds.XdsSubchannelPickers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
class ClusterManagerLoadBalancer extends LoadBalancer {
    static final int DELAYED_CHILD_DELETION_TIME_MINUTES = 15;
    private final Map<String, ChildLbState> childLbStates = new HashMap();
    private final LoadBalancer.Helper helper;
    private final XdsLogger logger;
    private final SynchronizationContext syncContext;
    private final ScheduledExecutorService timeService;

    ClusterManagerLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        this.timeService = (ScheduledExecutorService) Preconditions.checkNotNull(helper.getScheduledExecutorService(), "timeService");
        XdsLogger xdsLoggerWithLogId = XdsLogger.withLogId(InternalLogId.allocate("cluster_manager-lb", helper.getAuthority()));
        this.logger = xdsLoggerWithLogId;
        xdsLoggerWithLogId.log(XdsLogger.XdsLogLevel.INFO, "Created");
    }

    @Nullable
    private static ConnectivityState aggregateState(@Nullable ConnectivityState connectivityState, ConnectivityState connectivityState2) {
        if (connectivityState == null) {
            return connectivityState2;
        }
        if (connectivityState == ConnectivityState.READY || connectivityState2 == ConnectivityState.READY) {
            return ConnectivityState.READY;
        }
        if (connectivityState == ConnectivityState.CONNECTING || connectivityState2 == ConnectivityState.CONNECTING) {
            return ConnectivityState.CONNECTING;
        }
        return (connectivityState == ConnectivityState.IDLE || connectivityState2 == ConnectivityState.IDLE) ? ConnectivityState.IDLE : connectivityState;
    }

    @Override // io.grpc.LoadBalancer
    public boolean canHandleEmptyAddressListFromNameResolution() {
        return true;
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Received resolution result: {0}", resolvedAddresses);
        Map<String, ServiceConfigUtil.PolicySelection> map = ((ClusterManagerLoadBalancerProvider.ClusterManagerConfig) resolvedAddresses.getLoadBalancingPolicyConfig()).childPolicies;
        this.logger.log(XdsLogger.XdsLogLevel.INFO, "Received cluster_manager lb config: child names={0}", map.keySet());
        for (Map.Entry<String, ServiceConfigUtil.PolicySelection> entry : map.entrySet()) {
            String key = entry.getKey();
            LoadBalancerProvider provider = entry.getValue().getProvider();
            Object config = entry.getValue().getConfig();
            if (!this.childLbStates.containsKey(key)) {
                this.childLbStates.put(key, new ChildLbState(key, provider));
            } else {
                this.childLbStates.get(key).reactivate(provider);
            }
            final GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = this.childLbStates.get(key).lb;
            final LoadBalancer.ResolvedAddresses resolvedAddressesBuild = resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(config).build();
            this.syncContext.execute(new Runnable() { // from class: io.grpc.xds.ClusterManagerLoadBalancer.1
                @Override // java.lang.Runnable
                public void run() {
                    gracefulSwitchLoadBalancer.handleResolvedAddresses(resolvedAddressesBuild);
                }
            });
        }
        for (String str : this.childLbStates.keySet()) {
            if (!map.containsKey(str)) {
                this.childLbStates.get(str).deactivate();
            }
        }
        updateOverallBalancingState();
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        boolean z = true;
        this.logger.log(XdsLogger.XdsLogLevel.WARNING, "Received name resolution error: {0}", status);
        for (ChildLbState childLbState : this.childLbStates.values()) {
            if (!childLbState.deactivated) {
                childLbState.lb.handleNameResolutionError(status);
                z = false;
            }
        }
        if (z) {
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new XdsSubchannelPickers.ErrorPicker(status));
        }
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        this.logger.log(XdsLogger.XdsLogLevel.INFO, "Shutdown");
        Iterator<ChildLbState> it2 = this.childLbStates.values().iterator();
        while (it2.hasNext()) {
            it2.next().shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOverallBalancingState() {
        final HashMap map = new HashMap();
        ConnectivityState connectivityStateAggregateState = null;
        for (ChildLbState childLbState : this.childLbStates.values()) {
            if (!childLbState.deactivated) {
                map.put(childLbState.name, childLbState.currentPicker);
                connectivityStateAggregateState = aggregateState(connectivityStateAggregateState, childLbState.currentState);
            }
        }
        if (connectivityStateAggregateState != null) {
            this.helper.updateBalancingState(connectivityStateAggregateState, new LoadBalancer.SubchannelPicker() { // from class: io.grpc.xds.ClusterManagerLoadBalancer.2
                @Override // io.grpc.LoadBalancer.SubchannelPicker
                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    String str = (String) pickSubchannelArgs.getCallOptions().getOption(XdsNameResolver2.CLUSTER_SELECTION_KEY);
                    LoadBalancer.SubchannelPicker subchannelPicker = (LoadBalancer.SubchannelPicker) map.get(str);
                    if (subchannelPicker == null) {
                        return LoadBalancer.PickResult.withError(Status.UNAVAILABLE.withDescription("Unable to find cluster " + str));
                    }
                    return subchannelPicker.pickSubchannel(pickSubchannelArgs);
                }
            });
        }
    }

    private final class ChildLbState {
        private final GracefulSwitchLoadBalancer lb;
        private final String name;
        @Nullable
        SynchronizationContext.ScheduledHandle deletionTimer;
        private boolean deactivated;
        private LoadBalancerProvider policyProvider;
        private ConnectivityState currentState = ConnectivityState.CONNECTING;
        private LoadBalancer.SubchannelPicker currentPicker = XdsSubchannelPickers.BUFFER_PICKER;

        ChildLbState(String str, LoadBalancerProvider loadBalancerProvider) {
            this.name = str;
            this.policyProvider = loadBalancerProvider;
            GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = new GracefulSwitchLoadBalancer(new ChildLbStateHelper());
            this.lb = gracefulSwitchLoadBalancer;
            gracefulSwitchLoadBalancer.switchTo(loadBalancerProvider);
        }

        void deactivate() {
            if (this.deactivated) {
                return;
            }
            this.deletionTimer = ClusterManagerLoadBalancer.this.syncContext.schedule(new Runnable() { // from class: io.grpc.xds.ClusterManagerLoadBalancer.ChildLbState.1DeletionTask
                @Override // java.lang.Runnable
                public void run() {
                    ChildLbState.this.shutdown();
                    ClusterManagerLoadBalancer.this.childLbStates.remove(ChildLbState.this.name);
                }
            }, 15L, TimeUnit.MINUTES, ClusterManagerLoadBalancer.this.timeService);
            this.deactivated = true;
            ClusterManagerLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Child balancer {0} deactivated", this.name);
        }

        void reactivate(LoadBalancerProvider loadBalancerProvider) {
            SynchronizationContext.ScheduledHandle scheduledHandle = this.deletionTimer;
            if (scheduledHandle != null && scheduledHandle.isPending()) {
                this.deletionTimer.cancel();
                this.deactivated = false;
                ClusterManagerLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Child balancer {0} reactivated", this.name);
            }
            if (this.policyProvider.getPolicyName().equals(loadBalancerProvider.getPolicyName())) {
                return;
            }
            ClusterManagerLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Child balancer {0} switching policy from {1} to {2}", this.name, this.policyProvider.getPolicyName(), loadBalancerProvider.getPolicyName());
            this.lb.switchTo(loadBalancerProvider);
            this.policyProvider = loadBalancerProvider;
        }

        void shutdown() {
            this.deactivated = true;
            SynchronizationContext.ScheduledHandle scheduledHandle = this.deletionTimer;
            if (scheduledHandle != null && scheduledHandle.isPending()) {
                this.deletionTimer.cancel();
            }
            this.lb.shutdown();
            ClusterManagerLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Child balancer {0} deleted", this.name);
        }

        private final class ChildLbStateHelper extends ForwardingLoadBalancerHelper {
            private ChildLbStateHelper() {
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
            public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                ChildLbState.this.currentState = connectivityState;
                ChildLbState.this.currentPicker = subchannelPicker;
                if (ChildLbState.this.deactivated) {
                    return;
                }
                ClusterManagerLoadBalancer.this.updateOverallBalancingState();
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper
            protected LoadBalancer.Helper delegate() {
                return ClusterManagerLoadBalancer.this.helper;
            }
        }
    }
}
