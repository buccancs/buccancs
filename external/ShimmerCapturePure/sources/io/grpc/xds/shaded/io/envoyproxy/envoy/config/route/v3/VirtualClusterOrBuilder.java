package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface VirtualClusterOrBuilder extends MessageOrBuilder {
    HeaderMatcher getHeaders(int i);

    int getHeadersCount();

    List<HeaderMatcher> getHeadersList();

    HeaderMatcherOrBuilder getHeadersOrBuilder(int i);

    List<? extends HeaderMatcherOrBuilder> getHeadersOrBuilderList();

    String getName();

    ByteString getNameBytes();
}
