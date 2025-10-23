package io.opencensus.proto.metrics.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface LabelValueOrBuilder extends MessageOrBuilder {
    boolean getHasValue();

    String getValue();

    ByteString getValueBytes();
}
