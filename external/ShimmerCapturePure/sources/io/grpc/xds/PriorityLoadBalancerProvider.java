package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.internal.ServiceConfigUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
final class PriorityLoadBalancerProvider extends LoadBalancerProvider {
    PriorityLoadBalancerProvider() {
    }

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return "priority_experimental";
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
        return new PriorityLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        throw new UnsupportedOperationException();
    }

    static final class PriorityLbConfig {
        final Map<String, ServiceConfigUtil.PolicySelection> childConfigs;
        final List<String> priorities;

        PriorityLbConfig(Map<String, ServiceConfigUtil.PolicySelection> map, List<String> list) {
            this.childConfigs = Collections.unmodifiableMap((Map) Preconditions.checkNotNull(map, "childConfigs"));
            this.priorities = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "priorities"));
            Preconditions.checkArgument(!list.isEmpty(), "priority list is empty");
            Preconditions.checkArgument(map.keySet().containsAll(list), "missing child config for at lease one of the priorities");
            Preconditions.checkArgument(list.size() == new HashSet(list).size(), "duplicate names in priorities");
            Preconditions.checkArgument(list.size() == map.keySet().size(), "some names in childConfigs are not referenced by priorities");
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("childConfigs", this.childConfigs).add("priorities", this.priorities).toString();
        }
    }
}
