package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.ConfigSourceOrBuilder;

/* loaded from: classes3.dex */
public interface SdsSecretConfigOrBuilder extends MessageOrBuilder {
    String getName();

    ByteString getNameBytes();

    ConfigSource getSdsConfig();

    ConfigSourceOrBuilder getSdsConfigOrBuilder();

    boolean hasSdsConfig();
}
