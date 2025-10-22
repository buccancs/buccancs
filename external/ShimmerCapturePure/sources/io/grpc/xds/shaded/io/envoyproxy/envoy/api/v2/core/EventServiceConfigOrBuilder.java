package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.EventServiceConfig;

/* loaded from: classes3.dex */
public interface EventServiceConfigOrBuilder extends MessageOrBuilder {
    EventServiceConfig.ConfigSourceSpecifierCase getConfigSourceSpecifierCase();

    GrpcService getGrpcService();

    GrpcServiceOrBuilder getGrpcServiceOrBuilder();

    boolean hasGrpcService();
}
