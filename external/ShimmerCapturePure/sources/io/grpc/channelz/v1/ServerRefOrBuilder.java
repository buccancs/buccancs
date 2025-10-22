package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface ServerRefOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    long getServerId();
}
