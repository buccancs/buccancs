package io.opencensus.proto.stats.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface TagOrBuilder extends MessageOrBuilder {
    String getKey();

    ByteString getKeyBytes();

    String getValue();

    ByteString getValueBytes();
}
