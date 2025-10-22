package io.opencensus.proto.metrics.v1;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import io.opencensus.proto.metrics.v1.Point;

/* loaded from: classes4.dex */
public interface PointOrBuilder extends MessageOrBuilder {
    DistributionValue getDistributionValue();

    DistributionValueOrBuilder getDistributionValueOrBuilder();

    double getDoubleValue();

    long getInt64Value();

    SummaryValue getSummaryValue();

    SummaryValueOrBuilder getSummaryValueOrBuilder();

    Timestamp getTimestamp();

    TimestampOrBuilder getTimestampOrBuilder();

    Point.ValueCase getValueCase();

    boolean hasDistributionValue();

    boolean hasSummaryValue();

    boolean hasTimestamp();
}
