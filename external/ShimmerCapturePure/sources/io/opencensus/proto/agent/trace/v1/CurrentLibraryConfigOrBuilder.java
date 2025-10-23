package io.opencensus.proto.agent.trace.v1;

import com.google.protobuf.MessageOrBuilder;
import io.opencensus.proto.agent.common.v1.Node;
import io.opencensus.proto.agent.common.v1.NodeOrBuilder;
import io.opencensus.proto.trace.v1.TraceConfig;
import io.opencensus.proto.trace.v1.TraceConfigOrBuilder;

/* loaded from: classes4.dex */
public interface CurrentLibraryConfigOrBuilder extends MessageOrBuilder {
    TraceConfig getConfig();

    TraceConfigOrBuilder getConfigOrBuilder();

    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    boolean hasConfig();

    boolean hasNode();
}
