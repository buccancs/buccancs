package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2.ScopedRoutes;

/* loaded from: classes6.dex */
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
