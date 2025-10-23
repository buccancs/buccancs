package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address;

/* loaded from: classes3.dex */
public interface AddressOrBuilder extends MessageOrBuilder {
    Address.AddressCase getAddressCase();

    Pipe getPipe();

    PipeOrBuilder getPipeOrBuilder();

    SocketAddress getSocketAddress();

    SocketAddressOrBuilder getSocketAddressOrBuilder();

    boolean hasPipe();

    boolean hasSocketAddress();
}
