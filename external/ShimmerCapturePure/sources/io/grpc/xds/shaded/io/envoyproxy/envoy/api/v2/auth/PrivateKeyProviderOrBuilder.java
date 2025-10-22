package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.PrivateKeyProvider;

/* loaded from: classes3.dex */
public interface PrivateKeyProviderOrBuilder extends MessageOrBuilder {
    @Deprecated
    Struct getConfig();

    @Deprecated
    StructOrBuilder getConfigOrBuilder();

    PrivateKeyProvider.ConfigTypeCase getConfigTypeCase();

    String getProviderName();

    ByteString getProviderNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    @Deprecated
    boolean hasConfig();

    boolean hasTypedConfig();
}
