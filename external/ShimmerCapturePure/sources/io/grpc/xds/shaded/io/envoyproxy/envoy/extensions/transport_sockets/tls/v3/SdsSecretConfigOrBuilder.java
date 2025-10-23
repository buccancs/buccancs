package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocatorOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;

/* loaded from: classes4.dex */
public interface SdsSecretConfigOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    ConfigSource getSdsConfig();

    ConfigSourceOrBuilder getSdsConfigOrBuilder();

    ResourceLocator getSdsResourceLocator();

    ResourceLocatorOrBuilder getSdsResourceLocatorOrBuilder();

    boolean hasSdsConfig();

    boolean hasSdsResourceLocator();
}
