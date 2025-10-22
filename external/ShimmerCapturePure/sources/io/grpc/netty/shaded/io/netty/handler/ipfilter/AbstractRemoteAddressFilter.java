package io.grpc.netty.shaded.io.netty.handler.ipfilter;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

import java.net.SocketAddress;

/* loaded from: classes3.dex */
public abstract class AbstractRemoteAddressFilter<T extends SocketAddress> extends ChannelInboundHandlerAdapter {
    protected abstract boolean accept(ChannelHandlerContext channelHandlerContext, T t) throws Exception;

    protected void channelAccepted(ChannelHandlerContext channelHandlerContext, T t) {
    }

    protected ChannelFuture channelRejected(ChannelHandlerContext channelHandlerContext, T t) {
        return null;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        handleNewChannel(channelHandlerContext);
        channelHandlerContext.fireChannelRegistered();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!handleNewChannel(channelHandlerContext)) {
            throw new IllegalStateException("cannot determine to accept or reject a channel: " + channelHandlerContext.channel());
        }
        channelHandlerContext.fireChannelActive();
    }

    private boolean handleNewChannel(ChannelHandlerContext channelHandlerContext) throws Exception {
        SocketAddress socketAddressRemoteAddress = channelHandlerContext.channel().remoteAddress();
        if (socketAddressRemoteAddress == null) {
            return false;
        }
        channelHandlerContext.pipeline().remove(this);
        if (accept(channelHandlerContext, socketAddressRemoteAddress)) {
            channelAccepted(channelHandlerContext, socketAddressRemoteAddress);
            return true;
        }
        ChannelFuture channelFutureChannelRejected = channelRejected(channelHandlerContext, socketAddressRemoteAddress);
        if (channelFutureChannelRejected != null) {
            channelFutureChannelRejected.addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
            return true;
        }
        channelHandlerContext.close();
        return true;
    }
}
