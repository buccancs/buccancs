package io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3.UdpListenerConfig;

/* loaded from: classes6.dex */
public interface UdpListenerConfigOrBuilder extends MessageOrBuilder {
    UdpListenerConfig.ConfigTypeCase getConfigTypeCase();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    String getUdpListenerName();

    ByteString getUdpListenerNameBytes();

    boolean hasTypedConfig();
}
