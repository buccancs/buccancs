package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3.ScopedRoutes;

/* loaded from: classes4.dex */
public interface ScopedRoutesOrBuilder extends MessageOrBuilder {
    ScopedRoutes.ConfigSpecifierCase getConfigSpecifierCase();

    String getName();

    ByteString getNameBytes();

    ConfigSource getRdsConfigSource();

    ConfigSourceOrBuilder getRdsConfigSourceOrBuilder();

    ScopedRoutes.ScopeKeyBuilder getScopeKeyBuilder();

    ScopedRoutes.ScopeKeyBuilderOrBuilder getScopeKeyBuilderOrBuilder();

    ScopedRds getScopedRds();

    ScopedRdsOrBuilder getScopedRdsOrBuilder();

    ScopedRouteConfigurationsList getScopedRouteConfigurationsList();

    ScopedRouteConfigurationsListOrBuilder getScopedRouteConfigurationsListOrBuilder();

    boolean hasRdsConfigSource();

    boolean hasScopeKeyBuilder();

    boolean hasScopedRds();

    boolean hasScopedRouteConfigurationsList();
}
