package io.grpc.services;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.health.v1.HealthCheckRequest;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.health.v1.HealthGrpc;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.util.ForwardingLoadBalancer;
import io.grpc.util.ForwardingLoadBalancerHelper;
import io.grpc.util.ForwardingSubchannel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
final class HealthCheckingLoadBalancerFactory extends LoadBalancer.Factory {
    private static final Logger logger = Logger.getLogger(HealthCheckingLoadBalancerFactory.class.getName());
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final LoadBalancer.Factory delegateFactory;
    private final Supplier<Stopwatch> stopwatchSupplier;

    public HealthCheckingLoadBalancerFactory(LoadBalancer.Factory factory, BackoffPolicy.Provider provider, Supplier<Stopwatch> supplier) {
        this.delegateFactory = (LoadBalancer.Factory) Preconditions.checkNotNull(factory, "delegateFactory");
        this.backoffPolicyProvider = (BackoffPolicy.Provider) Preconditions.checkNotNull(provider, "backoffPolicyProvider");
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        HelperImpl helperImpl = new HelperImpl(helper);
        return new HealthCheckingLoadBalancer(helperImpl, this.delegateFactory.newLoadBalancer(helperImpl));
    }

    static final class SubchannelImpl extends ForwardingSubchannel {
        final LoadBalancer.Subchannel delegate;
        final HealthCheckState hcState;

        SubchannelImpl(LoadBalancer.Subchannel subchannel, HealthCheckState healthCheckState) {
            this.delegate = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "delegate");
            this.hcState = (HealthCheckState) Preconditions.checkNotNull(healthCheckState, "hcState");
        }

        @Override // io.grpc.util.ForwardingSubchannel
        protected LoadBalancer.Subchannel delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public void start(LoadBalancer.SubchannelStateListener subchannelStateListener) {
            this.hcState.init(subchannelStateListener);
            delegate().start(this.hcState);
        }
    }

    private static final class HealthCheckingLoadBalancer extends ForwardingLoadBalancer {
        final LoadBalancer delegate;
        final HelperImpl helper;
        final SynchronizationContext syncContext;
        final ScheduledExecutorService timerService;

        HealthCheckingLoadBalancer(HelperImpl helperImpl, LoadBalancer loadBalancer) {
            this.helper = (HelperImpl) Preconditions.checkNotNull(helperImpl, "helper");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helperImpl.getSynchronizationContext(), "syncContext");
            this.timerService = (ScheduledExecutorService) Preconditions.checkNotNull(helperImpl.getScheduledExecutorService(), "timerService");
            this.delegate = (LoadBalancer) Preconditions.checkNotNull(loadBalancer, "delegate");
        }

        @Override // io.grpc.util.ForwardingLoadBalancer
        protected LoadBalancer delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingLoadBalancer, io.grpc.LoadBalancer
        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            this.helper.setHealthCheckedService(ServiceConfigUtil.getHealthCheckedServiceName((Map) resolvedAddresses.getAttributes().get(LoadBalancer.ATTR_HEALTH_CHECKING_CONFIG)));
            super.handleResolvedAddresses(resolvedAddresses);
        }

        @Override // io.grpc.util.ForwardingLoadBalancer
        public String toString() {
            return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
        }
    }

    private final class HelperImpl extends ForwardingLoadBalancerHelper {
        final HashSet<HealthCheckState> hcStates = new HashSet<>();
        private final LoadBalancer.Helper delegate;
        private final SynchronizationContext syncContext;
        @Nullable
        String healthCheckedService;

        HelperImpl(LoadBalancer.Helper helper) {
            this.delegate = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "delegate");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        protected LoadBalancer.Helper delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            this.syncContext.throwIfNotInThisSynchronizationContext();
            LoadBalancer.Subchannel subchannelCreateSubchannel = super.createSubchannel(createSubchannelArgs);
            HealthCheckState healthCheckState = HealthCheckingLoadBalancerFactory.this.new HealthCheckState(this, subchannelCreateSubchannel, this.syncContext, this.delegate.getScheduledExecutorService());
            this.hcStates.add(healthCheckState);
            SubchannelImpl subchannelImpl = new SubchannelImpl(subchannelCreateSubchannel, healthCheckState);
            String str = this.healthCheckedService;
            if (str != null) {
                healthCheckState.setServiceName(str);
            }
            return subchannelImpl;
        }

        void setHealthCheckedService(@Nullable String str) {
            this.healthCheckedService = str;
            Iterator<HealthCheckState> it2 = this.hcStates.iterator();
            while (it2.hasNext()) {
                it2.next().setServiceName(str);
            }
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        public String toString() {
            return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
        }
    }

    private final class HealthCheckState implements LoadBalancer.SubchannelStateListener {

        private final HelperImpl helperImpl;
        private final LoadBalancer.Subchannel subchannel;
        private final ChannelLogger subchannelLogger;
        private final SynchronizationContext syncContext;
        private final ScheduledExecutorService timerService;
        @Nullable
        private HcStream activeRpc;
        private BackoffPolicy backoffPolicy;
        private boolean disabled;
        private SynchronizationContext.ScheduledHandle retryTimer;
        private boolean running;
        private String serviceName;
        private LoadBalancer.SubchannelStateListener stateListener;
        private ConnectivityStateInfo rawState = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
        private ConnectivityStateInfo concludedState = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
        private final Runnable retryTask = new Runnable() { // from class: io.grpc.services.HealthCheckingLoadBalancerFactory.HealthCheckState.1
            @Override // java.lang.Runnable
            public void run() {
                HealthCheckState.this.startRpc();
            }
        };

        HealthCheckState(HelperImpl helperImpl, LoadBalancer.Subchannel subchannel, SynchronizationContext synchronizationContext, ScheduledExecutorService scheduledExecutorService) {
            this.helperImpl = (HelperImpl) Preconditions.checkNotNull(helperImpl, "helperImpl");
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "subchannel");
            this.subchannelLogger = (ChannelLogger) Preconditions.checkNotNull(subchannel.getChannelLogger(), "subchannelLogger");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext, "syncContext");
            this.timerService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "timerService");
        }

        void init(LoadBalancer.SubchannelStateListener subchannelStateListener) {
            Preconditions.checkState(this.stateListener == null, "init() already called");
            this.stateListener = (LoadBalancer.SubchannelStateListener) Preconditions.checkNotNull(subchannelStateListener, "listener");
        }

        void setServiceName(@Nullable String str) {
            String str2;
            if (Objects.equal(str, this.serviceName)) {
                return;
            }
            this.serviceName = str;
            if (str == null) {
                str2 = "Health check disabled by service config";
            } else {
                str2 = "Switching to new service name: " + str;
            }
            stopRpc(str2);
            adjustHealthCheck();
        }

        @Override // io.grpc.LoadBalancer.SubchannelStateListener
        public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            if (Objects.equal(this.rawState.getState(), ConnectivityState.READY) && !Objects.equal(connectivityStateInfo.getState(), ConnectivityState.READY)) {
                this.disabled = false;
            }
            if (Objects.equal(connectivityStateInfo.getState(), ConnectivityState.SHUTDOWN)) {
                this.helperImpl.hcStates.remove(this);
            }
            this.rawState = connectivityStateInfo;
            adjustHealthCheck();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isRetryTimerPending() {
            SynchronizationContext.ScheduledHandle scheduledHandle = this.retryTimer;
            return scheduledHandle != null && scheduledHandle.isPending();
        }

        private void adjustHealthCheck() {
            if (!this.disabled && this.serviceName != null && Objects.equal(this.rawState.getState(), ConnectivityState.READY)) {
                this.running = true;
                if (this.activeRpc != null || isRetryTimerPending()) {
                    return;
                }
                startRpc();
                return;
            }
            this.running = false;
            stopRpc("Client stops health check");
            this.backoffPolicy = null;
            gotoState(this.rawState);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startRpc() {
            Preconditions.checkState(this.serviceName != null, "serviceName is null");
            Preconditions.checkState(this.activeRpc == null, "previous health-checking RPC has not been cleaned up");
            Preconditions.checkState(this.subchannel != null, "init() not called");
            if (!Objects.equal(this.concludedState.getState(), ConnectivityState.READY)) {
                this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING: Starting health-check for \"{0}\"", this.serviceName);
                gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.CONNECTING));
            }
            HcStream hcStream = new HcStream();
            this.activeRpc = hcStream;
            hcStream.start();
        }

        private void stopRpc(String str) {
            HcStream hcStream = this.activeRpc;
            if (hcStream != null) {
                hcStream.cancel(str);
                this.activeRpc = null;
            }
            SynchronizationContext.ScheduledHandle scheduledHandle = this.retryTimer;
            if (scheduledHandle != null) {
                scheduledHandle.cancel();
                this.retryTimer = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void gotoState(ConnectivityStateInfo connectivityStateInfo) {
            Preconditions.checkState(this.subchannel != null, "init() not called");
            if (Objects.equal(this.concludedState, connectivityStateInfo)) {
                return;
            }
            this.concludedState = connectivityStateInfo;
            this.stateListener.onSubchannelState(connectivityStateInfo);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("running", this.running).add("disabled", this.disabled).add("activeRpc", this.activeRpc).add("serviceName", this.serviceName).add("rawState", this.rawState).add("concludedState", this.concludedState).toString();
        }

        private class HcStream extends ClientCall.Listener<HealthCheckResponse> {
            private final ClientCall<HealthCheckRequest, HealthCheckResponse> call;
            private final String callServiceName;
            private final Stopwatch stopwatch;
            private boolean callHasResponded;

            HcStream() {
                this.stopwatch = ((Stopwatch) HealthCheckingLoadBalancerFactory.this.stopwatchSupplier.get()).start();
                this.callServiceName = HealthCheckState.this.serviceName;
                this.call = HealthCheckState.this.subchannel.asChannel().newCall(HealthGrpc.getWatchMethod(), CallOptions.DEFAULT);
            }

            void start() {
                this.call.start(this, new Metadata());
                this.call.sendMessage(HealthCheckRequest.newBuilder().setService(HealthCheckState.this.serviceName).m9107build());
                this.call.halfClose();
                this.call.request(1);
            }

            void cancel(String str) {
                this.call.cancel(str, null);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onMessage(final HealthCheckResponse healthCheckResponse) {
                HealthCheckState.this.syncContext.execute(new Runnable() { // from class: io.grpc.services.HealthCheckingLoadBalancerFactory.HealthCheckState.HcStream.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HcStream hcStream = HealthCheckState.this.activeRpc;
                        HcStream hcStream2 = HcStream.this;
                        if (hcStream == hcStream2) {
                            hcStream2.handleResponse(healthCheckResponse);
                        }
                    }
                });
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(final Status status, Metadata metadata) {
                HealthCheckState.this.syncContext.execute(new Runnable() { // from class: io.grpc.services.HealthCheckingLoadBalancerFactory.HealthCheckState.HcStream.2
                    @Override // java.lang.Runnable
                    public void run() {
                        HcStream hcStream = HealthCheckState.this.activeRpc;
                        HcStream hcStream2 = HcStream.this;
                        if (hcStream == hcStream2) {
                            HealthCheckState.this.activeRpc = null;
                            HcStream.this.handleStreamClosed(status);
                        }
                    }
                });
            }

            void handleResponse(HealthCheckResponse healthCheckResponse) {
                this.callHasResponded = true;
                HealthCheckState.this.backoffPolicy = null;
                HealthCheckResponse.ServingStatus status = healthCheckResponse.getStatus();
                if (Objects.equal(status, HealthCheckResponse.ServingStatus.SERVING)) {
                    HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY: health-check responded SERVING");
                    HealthCheckState.this.gotoState(ConnectivityStateInfo.forNonError(ConnectivityState.READY));
                } else {
                    HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE: health-check responded {0}", status);
                    HealthCheckState.this.gotoState(ConnectivityStateInfo.forTransientFailure(Status.UNAVAILABLE.withDescription("Health-check service responded " + status + " for '" + this.callServiceName + "'")));
                }
                this.call.request(1);
            }

            void handleStreamClosed(Status status) {
                long jNextBackoffNanos;
                if (Objects.equal(status.getCode(), Status.Code.UNIMPLEMENTED)) {
                    HealthCheckState.this.disabled = true;
                    HealthCheckingLoadBalancerFactory.logger.log(Level.SEVERE, "Health-check with {0} is disabled. Server returned: {1}", new Object[]{HealthCheckState.this.subchannel.getAllAddresses(), status});
                    HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "Health-check disabled: {0}", status);
                    HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} (no health-check)", HealthCheckState.this.rawState);
                    HealthCheckState healthCheckState = HealthCheckState.this;
                    healthCheckState.gotoState(healthCheckState.rawState);
                    return;
                }
                HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE: health-check stream closed with {0}", status);
                HealthCheckState.this.gotoState(ConnectivityStateInfo.forTransientFailure(Status.UNAVAILABLE.withDescription("Health-check stream unexpectedly closed with " + status + " for '" + this.callServiceName + "'")));
                if (this.callHasResponded) {
                    jNextBackoffNanos = 0;
                } else {
                    if (HealthCheckState.this.backoffPolicy == null) {
                        HealthCheckState healthCheckState2 = HealthCheckState.this;
                        healthCheckState2.backoffPolicy = HealthCheckingLoadBalancerFactory.this.backoffPolicyProvider.get();
                    }
                    jNextBackoffNanos = HealthCheckState.this.backoffPolicy.nextBackoffNanos() - this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
                }
                if (jNextBackoffNanos <= 0) {
                    HealthCheckState.this.startRpc();
                    return;
                }
                Preconditions.checkState(!HealthCheckState.this.isRetryTimerPending(), "Retry double scheduled");
                HealthCheckState.this.subchannelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Will retry health-check after {0} ns", Long.valueOf(jNextBackoffNanos));
                HealthCheckState healthCheckState3 = HealthCheckState.this;
                healthCheckState3.retryTimer = healthCheckState3.syncContext.schedule(HealthCheckState.this.retryTask, jNextBackoffNanos, TimeUnit.NANOSECONDS, HealthCheckState.this.timerService);
            }

            public String toString() {
                return MoreObjects.toStringHelper(this).add("callStarted", this.call != null).add("serviceName", this.callServiceName).add("hasResponded", this.callHasResponded).toString();
            }
        }
    }
}
