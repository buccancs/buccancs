package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;

/* loaded from: classes3.dex */
final class DefaultChannelHandlerContext extends AbstractChannelHandlerContext {
    private final ChannelHandler handler;

    DefaultChannelHandlerContext(DefaultChannelPipeline defaultChannelPipeline, EventExecutor eventExecutor, String str, ChannelHandler channelHandler) {
        super(defaultChannelPipeline, eventExecutor, str, channelHandler.getClass());
        this.handler = channelHandler;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext
    public ChannelHandler handler() {
        return this.handler;
    }
}
