package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface HeaderValueOptionOrBuilder extends MessageOrBuilder {
    BoolValue getAppend();

    BoolValueOrBuilder getAppendOrBuilder();

    HeaderValue getHeader();

    HeaderValueOrBuilder getHeaderOrBuilder();

    boolean hasAppend();

    boolean hasHeader();
}
