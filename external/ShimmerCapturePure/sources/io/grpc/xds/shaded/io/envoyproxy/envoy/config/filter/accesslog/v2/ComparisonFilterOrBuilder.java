package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeUInt32;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RuntimeUInt32OrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2.ComparisonFilter;

/* loaded from: classes6.dex */
public interface ComparisonFilterOrBuilder extends MessageOrBuilder {
    ComparisonFilter.Op getOp();

    int getOpValue();

    RuntimeUInt32 getValue();

    RuntimeUInt32OrBuilder getValueOrBuilder();

    boolean hasValue();
}
