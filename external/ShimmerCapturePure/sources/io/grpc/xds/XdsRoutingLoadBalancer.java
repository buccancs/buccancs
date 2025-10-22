package io.grpc.xds;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import io.grpc.ConnectivityState;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.util.ForwardingLoadBalancerHelper;
import io.grpc.util.GracefulSwitchLoadBalancer;
import io.grpc.xds.XdsLogger;
import io.grpc.xds.XdsRoutingLoadBalancerProvider;
import io.grpc.xds.XdsSubchannelPickers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
final class XdsRoutingLoadBalancer extends LoadBalancer {
    static final int DELAYED_ACTION_DELETION_TIME_MINUTES = 15;
    private final LoadBalancer.Helper helper;
    private final XdsLogger logger;
    private final SynchronizationContext syncContext;
    private final ScheduledExecutorService timeService;
    private final Map<String, ChildLbState> childLbStates = new HashMap();
    private List<XdsRoutingLoadBalancerProvider.Route> routes = ImmutableList.of();

    XdsRoutingLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        this.timeService = (ScheduledExecutorService) Preconditions.checkNotNull(helper.getScheduledExecutorService(), "timeService");
        XdsLogger xdsLoggerWithLogId = XdsLogger.withLogId(InternalLogId.allocate("xds-routing-lb", helper.getAuthority()));
        this.logger = xdsLoggerWithLogId;
        xdsLoggerWithLogId.log(XdsLogger.XdsLogLevel.INFO, "Created");
    }

    @Nullable
    static ConnectivityState aggregateState(@Nullable ConnectivityState connectivityState, ConnectivityState connectivityState2) {
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
    public void handleResolvedAddresses(final LoadBalancer.ResolvedAddresses resolvedAddresses) {
        this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Received resolution result: {0}", resolvedAddresses);
        XdsRoutingLoadBalancerProvider.XdsRoutingConfig xdsRoutingConfig = (XdsRoutingLoadBalancerProvider.XdsRoutingConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
        Map<String, ServiceConfigUtil.PolicySelection> map = xdsRoutingConfig.actions;
        for (String str : map.keySet()) {
            final ServiceConfigUtil.PolicySelection policySelection = map.get(str);
            if (!this.childLbStates.containsKey(str)) {
                this.childLbStates.put(str, new ChildLbState(str, policySelection.getProvider()));
            } else {
                this.childLbStates.get(str).reactivate(policySelection.getProvider());
            }
            final GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = this.childLbStates.get(str).lb;
            this.syncContext.execute(new Runnable() { // from class: io.grpc.xds.XdsRoutingLoadBalancer.1
                @Override // java.lang.Runnable
                public void run() {
                    gracefulSwitchLoadBalancer.handleResolvedAddresses(resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(policySelection.getConfig()).build());
                }
            });
        }
        this.routes = xdsRoutingConfig.routes;
        Iterator<E> it2 = Sets.difference(this.childLbStates.keySet(), map.keySet()).iterator();
        while (it2.hasNext()) {
            this.childLbStates.get((String) it2.next()).deactivate();
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
            it2.next().tearDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOverallBalancingState() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ConnectivityState connectivityStateAggregateState = null;
        for (XdsRoutingLoadBalancerProvider.Route route : this.routes) {
            ChildLbState childLbState = this.childLbStates.get(route.getActionName());
            linkedHashMap.put(route.getRouteMatch(), childLbState.currentPicker);
            connectivityStateAggregateState = aggregateState(connectivityStateAggregateState, childLbState.currentState);
        }
        if (connectivityStateAggregateState != null) {
            this.helper.updateBalancingState(connectivityStateAggregateState, new RouteMatchingSubchannelPicker(linkedHashMap));
        }
    }

    static final class RouteMatchingSubchannelPicker extends LoadBalancer.SubchannelPicker {
        final Map<RouteMatch, LoadBalancer.SubchannelPicker> routePickers;

        RouteMatchingSubchannelPicker(Map<RouteMatch, LoadBalancer.SubchannelPicker> map) {
            this.routePickers = map;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            HashMap map = new HashMap();
            Metadata headers = pickSubchannelArgs.getHeaders();
            for (String str : headers.keys()) {
                if (!str.endsWith(Metadata.BINARY_HEADER_SUFFIX)) {
                    map.put(str, headers.getAll(Metadata.Key.of(str, Metadata.ASCII_STRING_MARSHALLER)));
                }
            }
            for (Map.Entry<RouteMatch, LoadBalancer.SubchannelPicker> entry : this.routePickers.entrySet()) {
                if (entry.getKey().matches("/" + pickSubchannelArgs.getMethodDescriptor().getFullMethodName(), map)) {
                    return entry.getValue().pickSubchannel(pickSubchannelArgs);
                }
            }
            return LoadBalancer.PickResult.withError(Status.UNAVAILABLE.withDescription("no matching route found"));
        }
    }

    private final class ChildLbState {
        private final GracefulSwitchLoadBalancer lb;
        private final String name;
        @Nullable
        SynchronizationContext.ScheduledHandle deletionTimer;
        private LoadBalancer.SubchannelPicker currentPicker;
        private ConnectivityState currentState;
        private boolean deactivated;
        private LoadBalancerProvider policyProvider;

        private ChildLbState(String str, LoadBalancerProvider loadBalancerProvider) {
            this.currentState = ConnectivityState.CONNECTING;
            this.currentPicker = XdsSubchannelPickers.BUFFER_PICKER;
            this.name = str;
            this.policyProvider = loadBalancerProvider;
            GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = new GracefulSwitchLoadBalancer(new RouteHelper());
            this.lb = gracefulSwitchLoadBalancer;
            gracefulSwitchLoadBalancer.switchTo(loadBalancerProvider);
        }

        void deactivate() {
            if (this.deactivated) {
                return;
            }
            this.deletionTimer = XdsRoutingLoadBalancer.this.syncContext.schedule(new Runnable() { // from class: io.grpc.xds.XdsRoutingLoadBalancer.ChildLbState.1DeletionTask
                @Override // java.lang.Runnable
                public void run() {
                    ChildLbState.this.tearDown();
                    XdsRoutingLoadBalancer.this.childLbStates.remove(ChildLbState.this.name);
                }
            }, 15L, TimeUnit.MINUTES, XdsRoutingLoadBalancer.this.timeService);
            this.deactivated = true;
            XdsRoutingLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Route action {0} deactivated", this.name);
        }

        void reactivate(LoadBalancerProvider loadBalancerProvider) {
            SynchronizationContext.ScheduledHandle scheduledHandle = this.deletionTimer;
            if (scheduledHandle != null && scheduledHandle.isPending()) {
                this.deletionTimer.cancel();
                this.deactivated = false;
                XdsRoutingLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Route action {0} reactivated", this.name);
            }
            if (this.policyProvider.getPolicyName().equals(loadBalancerProvider.getPolicyName())) {
                return;
            }
            XdsRoutingLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Action {0} switching policy from {1} to {2}", this.name, this.policyProvider.getPolicyName(), loadBalancerProvider.getPolicyName());
            this.lb.switchTo(loadBalancerProvider);
            this.policyProvider = loadBalancerProvider;
        }

        void tearDown() {
            this.deactivated = true;
            SynchronizationContext.ScheduledHandle scheduledHandle = this.deletionTimer;
            if (scheduledHandle != null && scheduledHandle.isPending()) {
                this.deletionTimer.cancel();
            }
            this.lb.shutdown();
            XdsRoutingLoadBalancer.this.logger.log(XdsLogger.XdsLogLevel.DEBUG, "Route action {0} deleted", this.name);
        }

        private final class RouteHelper extends ForwardingLoadBalancerHelper {
            private RouteHelper() {
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
            public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                ChildLbState.this.currentState = connectivityState;
                ChildLbState.this.currentPicker = subchannelPicker;
                if (ChildLbState.this.deactivated) {
                    return;
                }
                XdsRoutingLoadBalancer.this.updateOverallBalancingState();
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper
            protected LoadBalancer.Helper delegate() {
                return XdsRoutingLoadBalancer.this.helper;
            }
        }
    }
}
