package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.PrivateKeyProvider;

/* loaded from: classes4.dex */
public interface PrivateKeyProviderOrBuilder extends MessageOrBuilder {
    PrivateKeyProvider.ConfigTypeCase getConfigTypeCase();

    String getProviderName();

    ByteString getProviderNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasTypedConfig();
}
