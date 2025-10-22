package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Locality;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.LocalityOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface LocalityLbEndpointsOrBuilder extends MessageOrBuilder {
    LbEndpoint getLbEndpoints(int i);

    int getLbEndpointsCount();

    List<LbEndpoint> getLbEndpointsList();

    LbEndpointOrBuilder getLbEndpointsOrBuilder(int i);

    List<? extends LbEndpointOrBuilder> getLbEndpointsOrBuilderList();

    UInt32Value getLoadBalancingWeight();

    UInt32ValueOrBuilder getLoadBalancingWeightOrBuilder();

    Locality getLocality();

    LocalityOrBuilder getLocalityOrBuilder();

    int getPriority();

    UInt32Value getProximity();

    UInt32ValueOrBuilder getProximityOrBuilder();

    boolean hasLoadBalancingWeight();

    boolean hasLocality();

    boolean hasProximity();
}
