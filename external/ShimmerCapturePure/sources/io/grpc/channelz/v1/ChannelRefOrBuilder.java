package io.grpc.channelz.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface ChannelRefOrBuilder extends MessageOrBuilder {
    long getChannelId();

    String getName();

    ByteString getNameBytes();
}
