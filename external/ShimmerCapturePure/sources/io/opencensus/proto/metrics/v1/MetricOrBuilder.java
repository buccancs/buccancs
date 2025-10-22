package io.opencensus.proto.metrics.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.resource.v1.Resource;
import io.opencensus.proto.resource.v1.ResourceOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface MetricOrBuilder extends MessageOrBuilder {
    MetricDescriptor getMetricDescriptor();

    MetricDescriptorOrBuilder getMetricDescriptorOrBuilder();

    Resource getResource();

    ResourceOrBuilder getResourceOrBuilder();

    TimeSeries getTimeseries(int i);

    int getTimeseriesCount();

    List<TimeSeries> getTimeseriesList();

    TimeSeriesOrBuilder getTimeseriesOrBuilder(int i);

    List<? extends TimeSeriesOrBuilder> getTimeseriesOrBuilderList();

    boolean hasMetricDescriptor();

    boolean hasResource();
}
