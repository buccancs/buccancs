package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerMask;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;

import java.util.Map;

/* loaded from: classes3.dex */
public abstract class ChannelHandlerAdapter implements ChannelHandler {
    boolean added;

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    protected void ensureNotSharable() {
        if (isSharable()) {
            throw new IllegalStateException("ChannelHandler " + getClass().getName() + " is not allowed to be shared");
        }
    }

    public boolean isSharable() {
        Class<?> cls = getClass();
        Map<Class<?>, Boolean> mapHandlerSharableCache = InternalThreadLocalMap.get().handlerSharableCache();
        Boolean boolValueOf = mapHandlerSharableCache.get(cls);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(cls.isAnnotationPresent(ChannelHandler.Sharable.class));
            mapHandlerSharableCache.put(cls, boolValueOf);
        }
        return boolValueOf.booleanValue();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    @ChannelHandlerMask.Skip
    @Deprecated
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        channelHandlerContext.fireExceptionCaught(th);
    }
}
