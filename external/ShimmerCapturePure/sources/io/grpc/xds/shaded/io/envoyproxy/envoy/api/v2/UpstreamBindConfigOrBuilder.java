package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AddressOrBuilder;

/* loaded from: classes3.dex */
public interface UpstreamBindConfigOrBuilder extends MessageOrBuilder {
    Address getSourceAddress();

    AddressOrBuilder getSourceAddressOrBuilder();

    boolean hasSourceAddress();
}
