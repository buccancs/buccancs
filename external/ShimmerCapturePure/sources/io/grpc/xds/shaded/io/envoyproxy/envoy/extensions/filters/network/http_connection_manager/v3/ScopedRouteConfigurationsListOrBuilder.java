package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.filters.network.http_connection_manager.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteConfiguration;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteConfigurationOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface ScopedRouteConfigurationsListOrBuilder extends MessageOrBuilder {
    ScopedRouteConfiguration getScopedRouteConfigurations(int i);

    int getScopedRouteConfigurationsCount();

    List<ScopedRouteConfiguration> getScopedRouteConfigurationsList();

    ScopedRouteConfigurationOrBuilder getScopedRouteConfigurationsOrBuilder(int i);

    List<? extends ScopedRouteConfigurationOrBuilder> getScopedRouteConfigurationsOrBuilderList();
}
