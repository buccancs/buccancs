package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.GrpcService;

import java.util.List;

/* loaded from: classes6.dex */
public interface GrpcServiceOrBuilder extends MessageOrBuilder {
    GrpcService.EnvoyGrpc getEnvoyGrpc();

    GrpcService.EnvoyGrpcOrBuilder getEnvoyGrpcOrBuilder();

    GrpcService.GoogleGrpc getGoogleGrpc();

    GrpcService.GoogleGrpcOrBuilder getGoogleGrpcOrBuilder();

    HeaderValue getInitialMetadata(int i);

    int getInitialMetadataCount();

    List<HeaderValue> getInitialMetadataList();

    HeaderValueOrBuilder getInitialMetadataOrBuilder(int i);

    List<? extends HeaderValueOrBuilder> getInitialMetadataOrBuilderList();

    GrpcService.TargetSpecifierCase getTargetSpecifierCase();

    Duration getTimeout();

    DurationOrBuilder getTimeoutOrBuilder();

    boolean hasEnvoyGrpc();

    boolean hasGoogleGrpc();

    boolean hasTimeout();
}
