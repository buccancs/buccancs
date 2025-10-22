package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.accesslog.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder;

/* loaded from: classes6.dex */
public interface HeaderFilterOrBuilder extends MessageOrBuilder {
    HeaderMatcher getHeader();

    HeaderMatcherOrBuilder getHeaderOrBuilder();

    boolean hasHeader();
}
