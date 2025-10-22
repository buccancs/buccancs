package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.Tracing;

/* loaded from: classes4.dex */
public interface TracingOrBuilder extends MessageOrBuilder {
    Tracing.Http getHttp();

    Tracing.HttpOrBuilder getHttpOrBuilder();

    boolean hasHttp();
}
