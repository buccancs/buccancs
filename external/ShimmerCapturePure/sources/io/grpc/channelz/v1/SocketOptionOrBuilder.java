package io.grpc.channelz.v1;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes2.dex */
public interface SocketOptionOrBuilder extends MessageOrBuilder {
    Any getAdditional();

    AnyOrBuilder getAdditionalOrBuilder();

    String getName();

    ByteString getNameBytes();

    String getValue();

    ByteString getValueBytes();

    boolean hasAdditional();
}
