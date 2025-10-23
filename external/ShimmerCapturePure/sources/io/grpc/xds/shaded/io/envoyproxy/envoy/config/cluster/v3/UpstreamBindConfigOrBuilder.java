package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AddressOrBuilder;

/* loaded from: classes6.dex */
public interface UpstreamBindConfigOrBuilder extends MessageOrBuilder {
    Address getSourceAddress();

    AddressOrBuilder getSourceAddressOrBuilder();

    boolean hasSourceAddress();
}
