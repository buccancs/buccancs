package io.opencensus.proto.trace.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface TruncatableStringOrBuilder extends MessageOrBuilder {
    int getTruncatedByteCount();

    String getValue();

    ByteString getValueBytes();
}
