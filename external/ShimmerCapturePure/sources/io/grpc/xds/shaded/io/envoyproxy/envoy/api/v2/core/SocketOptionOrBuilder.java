package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.SocketOption;

/* loaded from: classes3.dex */
public interface SocketOptionOrBuilder extends MessageOrBuilder {
    ByteString getBufValue();

    String getDescription();

    ByteString getDescriptionBytes();

    long getIntValue();

    long getLevel();

    long getName();

    SocketOption.SocketState getState();

    int getStateValue();

    SocketOption.ValueCase getValueCase();
}
