package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3.ComparisonFilter;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeUInt32;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RuntimeUInt32OrBuilder;

/* loaded from: classes2.dex */
public interface ComparisonFilterOrBuilder extends MessageOrBuilder {
    ComparisonFilter.Op getOp();

    int getOpValue();

    RuntimeUInt32 getValue();

    RuntimeUInt32OrBuilder getValueOrBuilder();

    boolean hasValue();
}
