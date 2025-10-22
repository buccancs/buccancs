package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.channels.ClosedChannelException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public final class Http2StreamChannelBootstrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2StreamChannelBootstrap.class);
    private static final Map.Entry<ChannelOption<?>, Object>[] EMPTY_OPTION_ARRAY = new Map.Entry[0];
    private static final Map.Entry<AttributeKey<?>, Object>[] EMPTY_ATTRIBUTE_ARRAY = new Map.Entry[0];
    private final Channel channel;
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap();
    private final Map<AttributeKey<?>, Object> attrs = new ConcurrentHashMap();
    private volatile ChannelHandler handler;
    private volatile ChannelHandlerContext multiplexCtx;

    public Http2StreamChannelBootstrap(Channel channel) {
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
    }

    private static void setChannelOptions(Channel channel, Map.Entry<ChannelOption<?>, Object>[] entryArr) {
        for (Map.Entry<ChannelOption<?>, Object> entry : entryArr) {
            setChannelOption(channel, entry.getKey(), entry.getValue());
        }
    }

    private static void setChannelOption(Channel channel, ChannelOption<?> channelOption, Object obj) {
        try {
            if (channel.config().setOption(channelOption, obj)) {
                return;
            }
            logger.warn("Unknown channel option '{}' for channel '{}'", channelOption, channel);
        } catch (Throwable th) {
            logger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", channelOption, obj, channel, th);
        }
    }

    private static void setAttributes(Channel channel, Map.Entry<AttributeKey<?>, Object>[] entryArr) {
        for (Map.Entry<AttributeKey<?>, Object> entry : entryArr) {
            channel.attr(entry.getKey()).set(entry.getValue());
        }
    }

    public <T> Http2StreamChannelBootstrap option(ChannelOption<T> channelOption, T t) {
        ObjectUtil.checkNotNull(channelOption, "option");
        synchronized (this.options) {
            if (t == null) {
                this.options.remove(channelOption);
            } else {
                this.options.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> Http2StreamChannelBootstrap attr(AttributeKey<T> attributeKey, T t) {
        ObjectUtil.checkNotNull(attributeKey, "key");
        if (t == null) {
            this.attrs.remove(attributeKey);
        } else {
            this.attrs.put(attributeKey, t);
        }
        return this;
    }

    public Http2StreamChannelBootstrap handler(ChannelHandler channelHandler) {
        this.handler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "handler");
        return this;
    }

    public Future<Http2StreamChannel> open() {
        return open(this.channel.eventLoop().newPromise());
    }

    public Future<Http2StreamChannel> open(final Promise<Http2StreamChannel> promise) {
        try {
            final ChannelHandlerContext channelHandlerContextFindCtx = findCtx();
            EventExecutor eventExecutorExecutor = channelHandlerContextFindCtx.executor();
            if (eventExecutorExecutor.inEventLoop()) {
                open0(channelHandlerContextFindCtx, promise);
            } else {
                eventExecutorExecutor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannelBootstrap.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Http2StreamChannelBootstrap.this.open0(channelHandlerContextFindCtx, promise);
                    }
                });
            }
        } catch (Throwable th) {
            promise.setFailure(th);
        }
        return promise;
    }

    private ChannelHandlerContext findCtx() throws ClosedChannelException {
        ChannelHandlerContext channelHandlerContext = this.multiplexCtx;
        if (channelHandlerContext != null && !channelHandlerContext.isRemoved()) {
            return channelHandlerContext;
        }
        ChannelPipeline channelPipelinePipeline = this.channel.pipeline();
        ChannelHandlerContext channelHandlerContextContext = channelPipelinePipeline.context(Http2MultiplexCodec.class);
        if (channelHandlerContextContext == null) {
            channelHandlerContextContext = channelPipelinePipeline.context(Http2MultiplexHandler.class);
        }
        if (channelHandlerContextContext != null) {
            this.multiplexCtx = channelHandlerContextContext;
            return channelHandlerContextContext;
        }
        if (this.channel.isActive()) {
            throw new IllegalStateException(StringUtil.simpleClassName((Class<?>) Http2MultiplexCodec.class) + " or " + StringUtil.simpleClassName((Class<?>) Http2MultiplexHandler.class) + " must be in the ChannelPipeline of Channel " + this.channel);
        }
        throw new ClosedChannelException();
    }

    @Deprecated
    public void open0(ChannelHandlerContext channelHandlerContext, final Promise<Http2StreamChannel> promise) {
        final Http2StreamChannel http2StreamChannelNewOutboundStream;
        if (promise.setUncancellable()) {
            if (channelHandlerContext.handler() instanceof Http2MultiplexCodec) {
                http2StreamChannelNewOutboundStream = ((Http2MultiplexCodec) channelHandlerContext.handler()).newOutboundStream();
            } else {
                http2StreamChannelNewOutboundStream = ((Http2MultiplexHandler) channelHandlerContext.handler()).newOutboundStream();
            }
            try {
                init(http2StreamChannelNewOutboundStream);
                channelHandlerContext.channel().eventLoop().register(http2StreamChannelNewOutboundStream).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannelBootstrap.2
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) {
                        if (channelFuture.isSuccess()) {
                            promise.setSuccess(http2StreamChannelNewOutboundStream);
                            return;
                        }
                        if (channelFuture.isCancelled()) {
                            promise.cancel(false);
                            return;
                        }
                        if (http2StreamChannelNewOutboundStream.isRegistered()) {
                            http2StreamChannelNewOutboundStream.close();
                        } else {
                            http2StreamChannelNewOutboundStream.unsafe().closeForcibly();
                        }
                        promise.setFailure(channelFuture.cause());
                    }
                });
            } catch (Exception e) {
                http2StreamChannelNewOutboundStream.unsafe().closeForcibly();
                promise.setFailure(e);
            }
        }
    }

    private void init(Channel channel) {
        Map.Entry[] entryArr;
        ChannelPipeline channelPipelinePipeline = channel.pipeline();
        ChannelHandler channelHandler = this.handler;
        if (channelHandler != null) {
            channelPipelinePipeline.addLast(channelHandler);
        }
        synchronized (this.options) {
            entryArr = (Map.Entry[]) this.options.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        setChannelOptions(channel, entryArr);
        setAttributes(channel, (Map.Entry[]) this.attrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));
    }
}
