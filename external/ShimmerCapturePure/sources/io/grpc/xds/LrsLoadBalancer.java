package io.grpc.xds;

import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import io.grpc.util.ForwardingLoadBalancerHelper;
import io.grpc.util.GracefulSwitchLoadBalancer;
import io.grpc.xds.ClientLoadCounter;
import io.grpc.xds.EnvoyProtoData;
import io.grpc.xds.LoadStatsManager;
import io.grpc.xds.LrsLoadBalancerProvider;
import io.grpc.xds.XdsSubchannelPickers;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* loaded from: classes3.dex */
final class LrsLoadBalancer extends LoadBalancer {
    private final LoadBalancer.Helper helper;
    private String childPolicyName;
    private String clusterName;
    private String edsServiceName;
    private LoadStatsManager.LoadStatsStore loadStatsStore;
    private EnvoyProtoData.Locality locality;

    @CheckForNull
    private GracefulSwitchLoadBalancer switchingLoadBalancer;

    LrsLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        LrsLoadBalancerProvider.LrsConfig lrsConfig = (LrsLoadBalancerProvider.LrsConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
        LoadStatsManager.LoadStatsStore loadStatsStore = (LoadStatsManager.LoadStatsStore) resolvedAddresses.getAttributes().get(XdsAttributes.ATTR_CLUSTER_SERVICE_LOAD_STATS_STORE);
        Preconditions.checkNotNull(lrsConfig, "missing LRS lb config");
        Preconditions.checkNotNull(loadStatsStore, "missing cluster service stats object");
        checkAndSetUp(lrsConfig, loadStatsStore);
        if (this.switchingLoadBalancer == null) {
            final ClientLoadCounter clientLoadCounterAddLocality = this.loadStatsStore.addLocality(lrsConfig.locality);
            this.switchingLoadBalancer = new GracefulSwitchLoadBalancer(new ForwardingLoadBalancerHelper() { // from class: io.grpc.xds.LrsLoadBalancer.1
                @Override // io.grpc.util.ForwardingLoadBalancerHelper
                protected LoadBalancer.Helper delegate() {
                    return LrsLoadBalancer.this.helper;
                }

                @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
                public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                    super.updateBalancingState(connectivityState, new ClientLoadCounter.LoadRecordingSubchannelPicker(clientLoadCounterAddLocality, subchannelPicker));
                }
            });
        }
        String policyName = lrsConfig.childPolicy.getProvider().getPolicyName();
        if (!Objects.equals(this.childPolicyName, policyName)) {
            this.switchingLoadBalancer.switchTo(lrsConfig.childPolicy.getProvider());
            this.childPolicyName = policyName;
        }
        this.switchingLoadBalancer.handleResolvedAddresses(resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(lrsConfig.childPolicy.getConfig()).build());
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = this.switchingLoadBalancer;
        if (gracefulSwitchLoadBalancer != null) {
            gracefulSwitchLoadBalancer.handleNameResolutionError(status);
        } else {
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new XdsSubchannelPickers.ErrorPicker(status));
        }
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        if (this.switchingLoadBalancer != null) {
            this.loadStatsStore.removeLocality(this.locality);
            this.switchingLoadBalancer.shutdown();
        }
    }

    private void checkAndSetUp(LrsLoadBalancerProvider.LrsConfig lrsConfig, LoadStatsManager.LoadStatsStore loadStatsStore) {
        String str = this.clusterName;
        Preconditions.checkState(str == null || str.equals(lrsConfig.clusterName), "cluster name should not change");
        String str2 = this.edsServiceName;
        Preconditions.checkState(str2 == null || str2.equals(lrsConfig.edsServiceName), "edsServiceName should not change");
        EnvoyProtoData.Locality locality = this.locality;
        Preconditions.checkState(locality == null || locality.equals(lrsConfig.locality), "locality should not change");
        LoadStatsManager.LoadStatsStore loadStatsStore2 = this.loadStatsStore;
        Preconditions.checkState(loadStatsStore2 == null || loadStatsStore2.equals(loadStatsStore), "loadStatsStore should not change");
        this.clusterName = lrsConfig.clusterName;
        this.edsServiceName = lrsConfig.edsServiceName;
        this.locality = lrsConfig.locality;
        this.loadStatsStore = loadStatsStore;
    }
}
