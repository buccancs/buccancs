package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AddressOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.Endpoint;

/* loaded from: classes6.dex */
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
