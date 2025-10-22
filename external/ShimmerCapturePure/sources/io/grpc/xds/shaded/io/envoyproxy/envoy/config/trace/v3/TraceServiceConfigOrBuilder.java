package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.GrpcService;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.GrpcServiceOrBuilder;

/* loaded from: classes4.dex */
public interface TraceServiceConfigOrBuilder extends MessageOrBuilder {
    GrpcService getGrpcService();

    GrpcServiceOrBuilder getGrpcServiceOrBuilder();

    boolean hasGrpcService();
}
