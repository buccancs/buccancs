package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;

/* loaded from: classes3.dex */
public interface ConfigSourceOrBuilder extends MessageOrBuilder {
    AggregatedConfigSource getAds();

    AggregatedConfigSourceOrBuilder getAdsOrBuilder();

    ApiConfigSource getApiConfigSource();

    ApiConfigSourceOrBuilder getApiConfigSourceOrBuilder();

    ConfigSource.ConfigSourceSpecifierCase getConfigSourceSpecifierCase();

    Duration getInitialFetchTimeout();

    DurationOrBuilder getInitialFetchTimeoutOrBuilder();

    String getPath();

    ByteString getPathBytes();

    ApiVersion getResourceApiVersion();

    int getResourceApiVersionValue();

    SelfConfigSource getSelf();

    SelfConfigSourceOrBuilder getSelfOrBuilder();

    boolean hasAds();

    boolean hasApiConfigSource();

    boolean hasInitialFetchTimeout();

    boolean hasSelf();
}
