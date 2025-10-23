package io.opencensus.proto.stats.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.stats.v1.Measure;

/* loaded from: classes4.dex */
public interface MeasureOrBuilder extends MessageOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getName();

    ByteString getNameBytes();

    Measure.Type getType();

    int getTypeValue();

    String getUnit();

    ByteString getUnitBytes();
}
