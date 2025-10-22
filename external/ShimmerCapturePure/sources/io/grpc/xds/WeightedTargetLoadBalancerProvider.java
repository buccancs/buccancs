package io.grpc.xds;

import com.google.common.base.MoreObjects;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.JsonUtil;
import io.grpc.internal.ServiceConfigUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class WeightedTargetLoadBalancerProvider extends LoadBalancerProvider {

    @Nullable
    private final LoadBalancerRegistry lbRegistry;

    public WeightedTargetLoadBalancerProvider() {
        this(null);
    }

    WeightedTargetLoadBalancerProvider(@Nullable LoadBalancerRegistry loadBalancerRegistry) {
        this.lbRegistry = loadBalancerRegistry;
    }

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return "weighted_target_experimental";
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
        return new WeightedTargetLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        try {
            Map<String, ?> object = JsonUtil.getObject(map, "targets");
            if (object != null && !object.isEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (String str : object.keySet()) {
                    Map<String, ?> object2 = JsonUtil.getObject(object, str);
                    if (object2 != null && !object2.isEmpty()) {
                        Integer numberAsInteger = JsonUtil.getNumberAsInteger(object2, "weight");
                        if (numberAsInteger != null && numberAsInteger.intValue() >= 1) {
                            List<ServiceConfigUtil.LbConfig> listUnwrapLoadBalancingConfigList = ServiceConfigUtil.unwrapLoadBalancingConfigList(JsonUtil.getListOfObjects(object2, "childPolicy"));
                            if (listUnwrapLoadBalancingConfigList != null && !listUnwrapLoadBalancingConfigList.isEmpty()) {
                                LoadBalancerRegistry defaultRegistry = this.lbRegistry;
                                if (defaultRegistry == null) {
                                    defaultRegistry = LoadBalancerRegistry.getDefaultRegistry();
                                }
                                NameResolver.ConfigOrError configOrErrorSelectLbPolicyFromList = ServiceConfigUtil.selectLbPolicyFromList(listUnwrapLoadBalancingConfigList, defaultRegistry);
                                if (configOrErrorSelectLbPolicyFromList.getError() != null) {
                                    return configOrErrorSelectLbPolicyFromList;
                                }
                                linkedHashMap.put(str, new WeightedPolicySelection(numberAsInteger.intValue(), (ServiceConfigUtil.PolicySelection) configOrErrorSelectLbPolicyFromList.getConfig()));
                            }
                            return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No child policy for target " + str + " in weighted_target LB policy:\n " + map));
                        }
                        return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("Wrong weight for target " + str + " in weighted_target LB policy:\n " + map));
                    }
                    return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No config for target " + str + " in weighted_target LB policy:\n " + map));
                }
                return NameResolver.ConfigOrError.fromConfig(new WeightedTargetConfig(linkedHashMap));
            }
            return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No targets provided for weighted_target LB policy:\n " + map));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.fromThrowable(e).withDescription("Failed to parse weighted_target LB config: " + map));
        }
    }

    static final class WeightedPolicySelection {
        final ServiceConfigUtil.PolicySelection policySelection;
        final int weight;

        WeightedPolicySelection(int i, ServiceConfigUtil.PolicySelection policySelection) {
            this.weight = i;
            this.policySelection = policySelection;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WeightedPolicySelection weightedPolicySelection = (WeightedPolicySelection) obj;
            return this.weight == weightedPolicySelection.weight && Objects.equals(this.policySelection, weightedPolicySelection.policySelection);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.weight), this.policySelection);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("weight", this.weight).add("policySelection", this.policySelection).toString();
        }
    }

    static final class WeightedTargetConfig {
        final Map<String, WeightedPolicySelection> targets;

        WeightedTargetConfig(Map<String, WeightedPolicySelection> map) {
            this.targets = map;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.targets, ((WeightedTargetConfig) obj).targets);
        }

        public int hashCode() {
            return Objects.hashCode(this.targets);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("targets", this.targets).toString();
        }
    }
}
