package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.TypeParameterMatcher;

import java.util.List;

/* loaded from: classes3.dex */
public abstract class MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter {
    private final TypeParameterMatcher matcher;

    protected MessageToMessageDecoder() {
        this.matcher = TypeParameterMatcher.find(this, MessageToMessageDecoder.class, "I");
    }

    protected MessageToMessageDecoder(Class<? extends I> cls) {
        this.matcher = TypeParameterMatcher.get(cls);
    }

    protected abstract void decode(ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception;

    public boolean acceptInboundMessage(Object obj) throws Exception {
        return this.matcher.match(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        CodecOutputList codecOutputListNewInstance = CodecOutputList.newInstance();
        int i = 0;
        try {
            try {
                try {
                    if (acceptInboundMessage(obj)) {
                        try {
                            decode(channelHandlerContext, obj, codecOutputListNewInstance);
                            ReferenceCountUtil.release(obj);
                        } catch (Throwable th) {
                            ReferenceCountUtil.release(obj);
                            throw th;
                        }
                    } else {
                        codecOutputListNewInstance.add(obj);
                    }
                    try {
                        int size = codecOutputListNewInstance.size();
                        while (i < size) {
                            channelHandlerContext.fireChannelRead(codecOutputListNewInstance.getUnsafe(i));
                            i++;
                        }
                    } finally {
                    }
                } catch (DecoderException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new DecoderException(e2);
            }
        } catch (Throwable th2) {
            try {
                int size2 = codecOutputListNewInstance.size();
                while (i < size2) {
                    channelHandlerContext.fireChannelRead(codecOutputListNewInstance.getUnsafe(i));
                    i++;
                }
                throw th2;
            } finally {
            }
        }
    }
}
