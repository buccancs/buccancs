package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig;

/* loaded from: classes6.dex */
public interface EventServiceConfigOrBuilder extends MessageOrBuilder {
    EventServiceConfig.ConfigSourceSpecifierCase getConfigSourceSpecifierCase();

    GrpcService getGrpcService();

    GrpcServiceOrBuilder getGrpcServiceOrBuilder();

    boolean hasGrpcService();
}
