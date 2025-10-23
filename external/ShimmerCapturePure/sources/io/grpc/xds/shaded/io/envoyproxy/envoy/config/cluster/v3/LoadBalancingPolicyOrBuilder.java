package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.LoadBalancingPolicy;

import java.util.List;

/* loaded from: classes6.dex */
public interface LoadBalancingPolicyOrBuilder extends MessageOrBuilder {
    LoadBalancingPolicy.Policy getPolicies(int i);

    int getPoliciesCount();

    List<LoadBalancingPolicy.Policy> getPoliciesList();

    LoadBalancingPolicy.PolicyOrBuilder getPoliciesOrBuilder(int i);

    List<? extends LoadBalancingPolicy.PolicyOrBuilder> getPoliciesOrBuilderList();
}
