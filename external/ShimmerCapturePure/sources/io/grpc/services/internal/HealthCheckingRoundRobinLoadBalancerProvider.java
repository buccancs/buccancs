package io.grpc.services.internal;

import com.google.common.base.Throwables;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.services.HealthCheckingLoadBalancerUtil;

import java.util.Map;

/* loaded from: classes3.dex */
public final class HealthCheckingRoundRobinLoadBalancerProvider extends LoadBalancerProvider {
    private final LoadBalancerProvider rrProvider = newRoundRobinProvider();

    static LoadBalancerProvider newRoundRobinProvider() {
        try {
            return (LoadBalancerProvider) Class.forName("io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider").asSubclass(LoadBalancerProvider.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    @Override // io.grpc.LoadBalancerProvider
    public boolean isAvailable() {
        return this.rrProvider.isAvailable();
    }

    @Override // io.grpc.LoadBalancerProvider
    public int getPriority() {
        return this.rrProvider.getPriority() + 1;
    }

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return this.rrProvider.getPolicyName();
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return HealthCheckingLoadBalancerUtil.newHealthCheckingLoadBalancer(this.rrProvider, helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        return this.rrProvider.parseLoadBalancingPolicyConfig(map);
    }
}
