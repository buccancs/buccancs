package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration;

/* loaded from: classes3.dex */
public interface ScopedRouteConfigurationOrBuilder extends MessageOrBuilder {
    ScopedRouteConfiguration.Key getKey();

    ScopedRouteConfiguration.KeyOrBuilder getKeyOrBuilder();

    String getName();

    ByteString getNameBytes();

    String getRouteConfigurationName();

    ByteString getRouteConfigurationNameBytes();

    boolean hasKey();
}
