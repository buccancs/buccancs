package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface BindConfigOrBuilder extends MessageOrBuilder {
    BoolValue getFreebind();

    BoolValueOrBuilder getFreebindOrBuilder();

    SocketOption getSocketOptions(int i);

    int getSocketOptionsCount();

    List<SocketOption> getSocketOptionsList();

    SocketOptionOrBuilder getSocketOptionsOrBuilder(int i);

    List<? extends SocketOptionOrBuilder> getSocketOptionsOrBuilderList();

    SocketAddress getSourceAddress();

    SocketAddressOrBuilder getSourceAddressOrBuilder();

    boolean hasFreebind();

    boolean hasSourceAddress();
}
