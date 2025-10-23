package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface SocketRefOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    long getSocketId();
}
