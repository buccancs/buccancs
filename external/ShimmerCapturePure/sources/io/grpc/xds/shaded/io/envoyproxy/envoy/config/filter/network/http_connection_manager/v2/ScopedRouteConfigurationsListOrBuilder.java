package io.grpc.xds.shaded.io.envoyproxy.envoy.config.filter.network.http_connection_manager.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface ScopedRouteConfigurationsListOrBuilder extends MessageOrBuilder {
    ScopedRouteConfiguration getScopedRouteConfigurations(int i);

    int getScopedRouteConfigurationsCount();

    List<ScopedRouteConfiguration> getScopedRouteConfigurationsList();

    ScopedRouteConfigurationOrBuilder getScopedRouteConfigurationsOrBuilder(int i);

    List<? extends ScopedRouteConfigurationOrBuilder> getScopedRouteConfigurationsOrBuilderList();
}
