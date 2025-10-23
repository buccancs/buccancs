package io.opencensus.proto.metrics.v1;

import com.google.protobuf.DoubleValue;
import com.google.protobuf.DoubleValueOrBuilder;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Int64ValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.metrics.v1.SummaryValue;

/* loaded from: classes4.dex */
public interface SummaryValueOrBuilder extends MessageOrBuilder {
    Int64Value getCount();

    Int64ValueOrBuilder getCountOrBuilder();

    SummaryValue.Snapshot getSnapshot();

    SummaryValue.SnapshotOrBuilder getSnapshotOrBuilder();

    DoubleValue getSum();

    DoubleValueOrBuilder getSumOrBuilder();

    boolean hasCount();

    boolean hasSnapshot();

    boolean hasSum();
}
