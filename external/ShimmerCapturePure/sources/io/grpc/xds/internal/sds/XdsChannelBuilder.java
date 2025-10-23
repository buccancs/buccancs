package io.grpc.xds.internal.sds;

import io.grpc.ForwardingChannelBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.net.SocketAddress;
import javax.annotation.CheckReturnValue;

/* loaded from: classes3.dex */
public final class XdsChannelBuilder extends ForwardingChannelBuilder<XdsChannelBuilder> {
    private final NettyChannelBuilder delegate;

    private XdsChannelBuilder(NettyChannelBuilder nettyChannelBuilder) {
        this.delegate = nettyChannelBuilder;
    }

    @CheckReturnValue
    public static XdsChannelBuilder forAddress(SocketAddress socketAddress) {
        return new XdsChannelBuilder(NettyChannelBuilder.forAddress(socketAddress));
    }

    @CheckReturnValue
    public static XdsChannelBuilder forAddress(String str, int i) {
        return new XdsChannelBuilder(NettyChannelBuilder.forAddress(str, i));
    }

    @CheckReturnValue
    public static XdsChannelBuilder forTarget(String str) {
        return new XdsChannelBuilder(NettyChannelBuilder.forTarget(str));
    }

    @Override // io.grpc.ForwardingChannelBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.delegate;
    }

    @Override // io.grpc.ForwardingChannelBuilder, io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        InternalNettyChannelBuilder.setProtocolNegotiatorFactory(this.delegate, SdsProtocolNegotiators.clientProtocolNegotiatorFactory());
        return this.delegate.build();
    }
}
