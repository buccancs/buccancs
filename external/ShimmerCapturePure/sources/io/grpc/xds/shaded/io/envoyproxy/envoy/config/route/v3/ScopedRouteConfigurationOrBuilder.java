package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.ScopedRouteConfiguration;

/* loaded from: classes4.dex */
public interface ScopedRouteConfigurationOrBuilder extends MessageOrBuilder {
    ScopedRouteConfiguration.Key getKey();

    ScopedRouteConfiguration.KeyOrBuilder getKeyOrBuilder();

    String getName();

    ByteString getNameBytes();

    String getRouteConfigurationName();

    ByteString getRouteConfigurationNameBytes();

    boolean hasKey();
}
