package io.opencensus.proto.metrics.v1;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface TimeSeriesOrBuilder extends MessageOrBuilder {
    LabelValue getLabelValues(int i);

    int getLabelValuesCount();

    List<LabelValue> getLabelValuesList();

    LabelValueOrBuilder getLabelValuesOrBuilder(int i);

    List<? extends LabelValueOrBuilder> getLabelValuesOrBuilderList();

    Point getPoints(int i);

    int getPointsCount();

    List<Point> getPointsList();

    PointOrBuilder getPointsOrBuilder(int i);

    List<? extends PointOrBuilder> getPointsOrBuilderList();

    Timestamp getStartTimestamp();

    TimestampOrBuilder getStartTimestampOrBuilder();

    boolean hasStartTimestamp();
}
