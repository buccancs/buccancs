package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface HeaderMapOrBuilder extends MessageOrBuilder {
    HeaderValue getHeaders(int i);

    int getHeadersCount();

    List<HeaderValue> getHeadersList();

    HeaderValueOrBuilder getHeadersOrBuilder(int i);

    List<? extends HeaderValueOrBuilder> getHeadersOrBuilderList();
}
