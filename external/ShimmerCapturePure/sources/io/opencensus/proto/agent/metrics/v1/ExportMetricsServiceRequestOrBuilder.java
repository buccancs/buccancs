package io.opencensus.proto.agent.metrics.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.agent.common.v1.Node;
import io.opencensus.proto.agent.common.v1.NodeOrBuilder;
import io.opencensus.proto.metrics.v1.Metric;
import io.opencensus.proto.metrics.v1.MetricOrBuilder;
import io.opencensus.proto.resource.v1.Resource;
import io.opencensus.proto.resource.v1.ResourceOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface ExportMetricsServiceRequestOrBuilder extends MessageOrBuilder {
    Metric getMetrics(int i);

    int getMetricsCount();

    List<Metric> getMetricsList();

    MetricOrBuilder getMetricsOrBuilder(int i);

    List<? extends MetricOrBuilder> getMetricsOrBuilderList();

    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    Resource getResource();

    ResourceOrBuilder getResourceOrBuilder();

    boolean hasNode();

    boolean hasResource();
}
