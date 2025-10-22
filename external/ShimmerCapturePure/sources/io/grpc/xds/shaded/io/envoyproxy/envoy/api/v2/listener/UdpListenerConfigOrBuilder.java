package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.UdpListenerConfig;

/* loaded from: classes5.dex */
public interface UdpListenerConfigOrBuilder extends MessageOrBuilder {
    @Deprecated
    Struct getConfig();

    @Deprecated
    StructOrBuilder getConfigOrBuilder();

    UdpListenerConfig.ConfigTypeCase getConfigTypeCase();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    String getUdpListenerName();

    ByteString getUdpListenerNameBytes();

    @Deprecated
    boolean hasConfig();

    boolean hasTypedConfig();
}
