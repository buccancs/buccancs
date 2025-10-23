package io.opencensus.proto.stats.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.stats.v1.View;

import java.util.List;

/* loaded from: classes4.dex */
public interface ViewOrBuilder extends MessageOrBuilder {
    View.AggregationCase getAggregationCase();

    String getColumns(int i);

    ByteString getColumnsBytes(int i);

    int getColumnsCount();

    /* renamed from: getColumnsList */
    List<String> mo37787getColumnsList();

    CountAggregation getCountAggregation();

    CountAggregationOrBuilder getCountAggregationOrBuilder();

    String getDescription();

    ByteString getDescriptionBytes();

    DistributionAggregation getDistributionAggregation();

    DistributionAggregationOrBuilder getDistributionAggregationOrBuilder();

    LastValueAggregation getLastValueAggregation();

    LastValueAggregationOrBuilder getLastValueAggregationOrBuilder();

    Measure getMeasure();

    MeasureOrBuilder getMeasureOrBuilder();

    String getName();

    ByteString getNameBytes();

    SumAggregation getSumAggregation();

    SumAggregationOrBuilder getSumAggregationOrBuilder();

    boolean hasCountAggregation();

    boolean hasDistributionAggregation();

    boolean hasLastValueAggregation();

    boolean hasMeasure();

    boolean hasSumAggregation();
}
