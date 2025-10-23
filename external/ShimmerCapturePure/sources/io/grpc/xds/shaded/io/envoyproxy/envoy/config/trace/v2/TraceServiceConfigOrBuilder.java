package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder;

/* loaded from: classes4.dex */
public interface TraceServiceConfigOrBuilder extends MessageOrBuilder {
    GrpcService getGrpcService();

    GrpcServiceOrBuilder getGrpcServiceOrBuilder();

    boolean hasGrpcService();
}
