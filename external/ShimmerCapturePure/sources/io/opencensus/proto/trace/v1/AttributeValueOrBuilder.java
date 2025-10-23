package io.opencensus.proto.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.trace.v1.AttributeValue;

/* loaded from: classes4.dex */
public interface AttributeValueOrBuilder extends MessageOrBuilder {
    boolean getBoolValue();

    double getDoubleValue();

    long getIntValue();

    TruncatableString getStringValue();

    TruncatableStringOrBuilder getStringValueOrBuilder();

    AttributeValue.ValueCase getValueCase();

    boolean hasStringValue();
}
