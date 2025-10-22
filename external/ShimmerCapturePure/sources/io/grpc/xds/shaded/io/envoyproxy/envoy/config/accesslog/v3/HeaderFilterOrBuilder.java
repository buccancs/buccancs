package io.grpc.xds.shaded.io.envoyproxy.envoy.config.accesslog.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.HeaderMatcherOrBuilder;

/* loaded from: classes2.dex */
public interface HeaderFilterOrBuilder extends MessageOrBuilder {
    HeaderMatcher getHeader();

    HeaderMatcherOrBuilder getHeaderOrBuilder();

    boolean hasHeader();
}
