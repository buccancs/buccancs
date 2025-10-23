package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.RequestMethod;

import java.util.List;

/* loaded from: classes2.dex */
public interface VirtualClusterOrBuilder extends MessageOrBuilder {
    HeaderMatcher getHeaders(int i);

    int getHeadersCount();

    List<HeaderMatcher> getHeadersList();

    HeaderMatcherOrBuilder getHeadersOrBuilder(int i);

    List<? extends HeaderMatcherOrBuilder> getHeadersOrBuilderList();

    @Deprecated
    RequestMethod getMethod();

    @Deprecated
    int getMethodValue();

    String getName();

    ByteString getNameBytes();

    @Deprecated
    String getPattern();

    @Deprecated
    ByteString getPatternBytes();
}
