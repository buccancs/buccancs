package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;

/* loaded from: classes4.dex */
public interface ScopedRdsOrBuilder extends MessageOrBuilder {
    ConfigSource getScopedRdsConfigSource();

    ConfigSourceOrBuilder getScopedRdsConfigSourceOrBuilder();

    boolean hasScopedRdsConfigSource();
}
