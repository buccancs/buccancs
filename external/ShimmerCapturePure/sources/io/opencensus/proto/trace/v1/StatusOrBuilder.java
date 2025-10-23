package io.opencensus.proto.trace.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface StatusOrBuilder extends MessageOrBuilder {
    int getCode();

    String getMessage();

    ByteString getMessageBytes();
}
