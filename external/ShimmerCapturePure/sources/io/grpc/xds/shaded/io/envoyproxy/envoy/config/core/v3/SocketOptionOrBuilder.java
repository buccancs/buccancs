package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketOption;

/* loaded from: classes6.dex */
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
