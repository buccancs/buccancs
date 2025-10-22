package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress;

/* loaded from: classes6.dex */
public interface SocketAddressOrBuilder extends MessageOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    boolean getIpv4Compat();

    String getNamedPort();

    ByteString getNamedPortBytes();

    SocketAddress.PortSpecifierCase getPortSpecifierCase();

    int getPortValue();

    SocketAddress.Protocol getProtocol();

    int getProtocolValue();

    String getResolverName();

    ByteString getResolverNameBytes();
}
