package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.JsonUtil;
import io.grpc.internal.ServiceConfigUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class EdsLoadBalancerProvider extends LoadBalancerProvider {
    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return "eds_experimental";
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
        return new EdsLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        LoadBalancerRegistry defaultRegistry = LoadBalancerRegistry.getDefaultRegistry();
        try {
            String string = JsonUtil.getString(map, "cluster");
            if (string == null) {
                return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("Cluster name required"));
            }
            String string2 = JsonUtil.getString(map, "edsServiceName");
            String string3 = JsonUtil.getString(map, "lrsLoadReportingServerName");
            ServiceConfigUtil.LbConfig lbConfig = new ServiceConfigUtil.LbConfig("round_robin", ImmutableMap.of());
            List<ServiceConfigUtil.LbConfig> listUnwrapLoadBalancingConfigList = ServiceConfigUtil.unwrapLoadBalancingConfigList(JsonUtil.getListOfObjects(map, "endpointPickingPolicy"));
            if (listUnwrapLoadBalancingConfigList == null || listUnwrapLoadBalancingConfigList.isEmpty()) {
                listUnwrapLoadBalancingConfigList = Collections.singletonList(lbConfig);
            }
            NameResolver.ConfigOrError configOrErrorSelectLbPolicyFromList = ServiceConfigUtil.selectLbPolicyFromList(listUnwrapLoadBalancingConfigList, defaultRegistry);
            return configOrErrorSelectLbPolicyFromList.getError() != null ? configOrErrorSelectLbPolicyFromList : NameResolver.ConfigOrError.fromConfig(new EdsConfig(string, string2, string3, (ServiceConfigUtil.PolicySelection) configOrErrorSelectLbPolicyFromList.getConfig()));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.fromThrowable(e).withDescription("Failed to parse EDS LB config: " + map));
        }
    }

    static final class EdsConfig {
        final String clusterName;

        @Nullable
        final String edsServiceName;
        final ServiceConfigUtil.PolicySelection endpointPickingPolicy;

        @Nullable
        final String lrsServerName;

        EdsConfig(String str, @Nullable String str2, @Nullable String str3, ServiceConfigUtil.PolicySelection policySelection) {
            this.clusterName = (String) Preconditions.checkNotNull(str, "clusterName");
            this.edsServiceName = str2;
            this.lrsServerName = str3;
            this.endpointPickingPolicy = (ServiceConfigUtil.PolicySelection) Preconditions.checkNotNull(policySelection, "endpointPickingPolicy");
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("clusterName", this.clusterName).add("edsServiceName", this.edsServiceName).add("lrsServerName", this.lrsServerName).add("endpointPickingPolicy", this.endpointPickingPolicy).toString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EdsConfig)) {
                return false;
            }
            EdsConfig edsConfig = (EdsConfig) obj;
            return Objects.equal(this.clusterName, edsConfig.clusterName) && Objects.equal(this.edsServiceName, edsConfig.edsServiceName) && Objects.equal(this.lrsServerName, edsConfig.lrsServerName) && Objects.equal(this.endpointPickingPolicy, edsConfig.endpointPickingPolicy);
        }

        public int hashCode() {
            return Objects.hashCode(this.clusterName, this.edsServiceName, this.lrsServerName, this.endpointPickingPolicy);
        }
    }
}
