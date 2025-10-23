package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address;

/* loaded from: classes6.dex */
public interface AddressOrBuilder extends MessageOrBuilder {
    Address.AddressCase getAddressCase();

    Pipe getPipe();

    PipeOrBuilder getPipeOrBuilder();

    SocketAddress getSocketAddress();

    SocketAddressOrBuilder getSocketAddressOrBuilder();

    boolean hasPipe();

    boolean hasSocketAddress();
}
