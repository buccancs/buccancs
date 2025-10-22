package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;

/* loaded from: classes4.dex */
public interface RdsOrBuilder extends MessageOrBuilder {
    ConfigSource getConfigSource();

    ConfigSourceOrBuilder getConfigSourceOrBuilder();

    ResourceLocator getRdsResourceLocator();

    ResourceLocatorOrBuilder getRdsResourceLocatorOrBuilder();

    String getRouteConfigName();

    ByteString getRouteConfigNameBytes();

    boolean hasConfigSource();

    boolean hasRdsResourceLocator();
}
