package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AddressOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.Endpoint;

/* loaded from: classes3.dex */
public interface EndpointOrBuilder extends MessageOrBuilder {
    Address getAddress();

    AddressOrBuilder getAddressOrBuilder();

    Endpoint.HealthCheckConfig getHealthCheckConfig();

    Endpoint.HealthCheckConfigOrBuilder getHealthCheckConfigOrBuilder();

    String getHostname();

    ByteString getHostnameBytes();

    boolean hasAddress();

    boolean hasHealthCheckConfig();
}
