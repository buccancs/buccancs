package io.grpc.services;

import io.grpc.LoadBalancer;
import io.grpc.internal.ExponentialBackoffPolicy;
import io.grpc.internal.GrpcUtil;

/* loaded from: classes3.dex */
public final class HealthCheckingLoadBalancerUtil {
    private HealthCheckingLoadBalancerUtil() {
    }

    public static LoadBalancer newHealthCheckingLoadBalancer(LoadBalancer.Factory factory, LoadBalancer.Helper helper) {
        return new HealthCheckingLoadBalancerFactory(factory, new ExponentialBackoffPolicy.Provider(), GrpcUtil.STOPWATCH_SUPPLIER).newLoadBalancer(helper);
    }
}
