package io.opencensus.proto.metrics.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.metrics.v1.DistributionValue;

import java.util.List;

/* loaded from: classes4.dex */
public interface DistributionValueOrBuilder extends MessageOrBuilder {
    DistributionValue.BucketOptions getBucketOptions();

    DistributionValue.BucketOptionsOrBuilder getBucketOptionsOrBuilder();

    DistributionValue.Bucket getBuckets(int i);

    int getBucketsCount();

    List<DistributionValue.Bucket> getBucketsList();

    DistributionValue.BucketOrBuilder getBucketsOrBuilder(int i);

    List<? extends DistributionValue.BucketOrBuilder> getBucketsOrBuilderList();

    long getCount();

    double getSum();

    double getSumOfSquaredDeviation();

    boolean hasBucketOptions();
}
