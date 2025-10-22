package io.opencensus.proto.stats.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface DistributionAggregationOrBuilder extends MessageOrBuilder {
    double getBucketBounds(int i);

    int getBucketBoundsCount();

    List<Double> getBucketBoundsList();
}
