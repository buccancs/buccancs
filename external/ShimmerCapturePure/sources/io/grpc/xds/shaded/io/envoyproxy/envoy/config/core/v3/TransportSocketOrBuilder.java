package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket;

/* loaded from: classes6.dex */
public interface TransportSocketOrBuilder extends MessageOrBuilder {
    TransportSocket.ConfigTypeCase getConfigTypeCase();

    String getName();

    ByteString getNameBytes();

    Any getTypedConfig();

    AnyOrBuilder getTypedConfigOrBuilder();

    boolean hasTypedConfig();
}
