package io.grpc.xds;

import com.google.common.base.Preconditions;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.xds.EnvoyProtoData;

import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class LrsLoadBalancerProvider extends LoadBalancerProvider {
    private static final String LRS_POLICY_NAME = "lrs_experimental";

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return LRS_POLICY_NAME;
    }

    @Override // io.grpc.LoadBalancerProvider
    public int getPriority() {
        return 5;
    }

    @Override // io.grpc.LoadBalancerProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new LrsLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        throw new UnsupportedOperationException();
    }

    static final class LrsConfig {
        final ServiceConfigUtil.PolicySelection childPolicy;
        final String clusterName;

        @Nullable
        final String edsServiceName;
        final EnvoyProtoData.Locality locality;
        final String lrsServerName;

        LrsConfig(String str, @Nullable String str2, String str3, EnvoyProtoData.Locality locality, ServiceConfigUtil.PolicySelection policySelection) {
            this.clusterName = (String) Preconditions.checkNotNull(str, "clusterName");
            this.edsServiceName = str2;
            this.lrsServerName = (String) Preconditions.checkNotNull(str3, "lrsServerName");
            this.locality = (EnvoyProtoData.Locality) Preconditions.checkNotNull(locality, "locality");
            this.childPolicy = (ServiceConfigUtil.PolicySelection) Preconditions.checkNotNull(policySelection, "childPolicy");
        }
    }
}
