package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HealthStatus;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LbEndpoint;

/* loaded from: classes3.dex */
public interface LbEndpointOrBuilder extends MessageOrBuilder {
    Endpoint getEndpoint();

    String getEndpointName();

    ByteString getEndpointNameBytes();

    EndpointOrBuilder getEndpointOrBuilder();

    HealthStatus getHealthStatus();

    int getHealthStatusValue();

    LbEndpoint.HostIdentifierCase getHostIdentifierCase();

    UInt32Value getLoadBalancingWeight();

    UInt32ValueOrBuilder getLoadBalancingWeightOrBuilder();

    Metadata getMetadata();

    MetadataOrBuilder getMetadataOrBuilder();

    boolean hasEndpoint();

    boolean hasLoadBalancingWeight();

    boolean hasMetadata();
}
