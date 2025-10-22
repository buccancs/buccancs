package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface HeaderMapOrBuilder extends MessageOrBuilder {
    HeaderValue getHeaders(int i);

    int getHeadersCount();

    List<HeaderValue> getHeadersList();

    HeaderValueOrBuilder getHeadersOrBuilder(int i);

    List<? extends HeaderValueOrBuilder> getHeadersOrBuilderList();
}
