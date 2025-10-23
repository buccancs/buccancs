package io.opencensus.proto.stats.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import io.opencensus.proto.stats.v1.Measurement;

import java.util.List;

/* loaded from: classes4.dex */
public interface MeasurementOrBuilder extends MessageOrBuilder {
    double getDoubleValue();

    long getIntValue();

    String getMeasureName();

    ByteString getMeasureNameBytes();

    Tag getTags(int i);

    int getTagsCount();

    List<Tag> getTagsList();

    TagOrBuilder getTagsOrBuilder(int i);

    List<? extends TagOrBuilder> getTagsOrBuilderList();

    Timestamp getTime();

    TimestampOrBuilder getTimeOrBuilder();

    Measurement.ValueCase getValueCase();

    boolean hasTime();
}
