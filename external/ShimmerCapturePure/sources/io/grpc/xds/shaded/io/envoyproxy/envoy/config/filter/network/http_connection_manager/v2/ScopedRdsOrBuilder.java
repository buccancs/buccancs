package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder;

/* loaded from: classes6.dex */
public interface ScopedRdsOrBuilder extends MessageOrBuilder {
    ConfigSource getScopedRdsConfigSource();

    ConfigSourceOrBuilder getScopedRdsConfigSourceOrBuilder();

    boolean hasScopedRdsConfigSource();
}
