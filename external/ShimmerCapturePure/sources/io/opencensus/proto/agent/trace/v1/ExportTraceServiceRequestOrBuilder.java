package io.opencensus.proto.agent.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.agent.common.v1.Node;
import io.opencensus.proto.agent.common.v1.NodeOrBuilder;
import io.opencensus.proto.resource.v1.Resource;
import io.opencensus.proto.resource.v1.ResourceOrBuilder;
import io.opencensus.proto.trace.v1.Span;
import io.opencensus.proto.trace.v1.SpanOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface ExportTraceServiceRequestOrBuilder extends MessageOrBuilder {
    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    Resource getResource();

    ResourceOrBuilder getResourceOrBuilder();

    Span getSpans(int i);

    int getSpansCount();

    List<Span> getSpansList();

    SpanOrBuilder getSpansOrBuilder(int i);

    List<? extends SpanOrBuilder> getSpansOrBuilderList();

    boolean hasNode();

    boolean hasResource();
}
