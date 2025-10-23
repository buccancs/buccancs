package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthStatus;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint;

/* loaded from: classes6.dex */
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
