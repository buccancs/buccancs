package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.PromiseCombiner;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.TypeParameterMatcher;

import java.util.List;

/* loaded from: classes3.dex */
public abstract class MessageToMessageEncoder<I> extends ChannelOutboundHandlerAdapter {
    private final TypeParameterMatcher matcher;

    protected MessageToMessageEncoder() {
        this.matcher = TypeParameterMatcher.find(this, MessageToMessageEncoder.class, "I");
    }

    protected MessageToMessageEncoder(Class<? extends I> cls) {
        this.matcher = TypeParameterMatcher.get(cls);
    }

    private static void writeVoidPromise(ChannelHandlerContext channelHandlerContext, CodecOutputList codecOutputList) {
        ChannelPromise channelPromiseVoidPromise = channelHandlerContext.voidPromise();
        for (int i = 0; i < codecOutputList.size(); i++) {
            channelHandlerContext.write(codecOutputList.getUnsafe(i), channelPromiseVoidPromise);
        }
    }

    private static void writePromiseCombiner(ChannelHandlerContext channelHandlerContext, CodecOutputList codecOutputList, ChannelPromise channelPromise) {
        PromiseCombiner promiseCombiner = new PromiseCombiner(channelHandlerContext.executor());
        for (int i = 0; i < codecOutputList.size(); i++) {
            promiseCombiner.add(channelHandlerContext.write(codecOutputList.getUnsafe(i)));
        }
        promiseCombiner.finish(channelPromise);
    }

    protected abstract void encode(ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception;

    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return this.matcher.match(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        CodecOutputList codecOutputListNewInstance = null;
        try {
            try {
                try {
                    if (acceptOutboundMessage(obj)) {
                        codecOutputListNewInstance = CodecOutputList.newInstance();
                        try {
                            encode(channelHandlerContext, obj, codecOutputListNewInstance);
                            ReferenceCountUtil.release(obj);
                            if (codecOutputListNewInstance.isEmpty()) {
                                throw new EncoderException(StringUtil.simpleClassName(this) + " must produce at least one message.");
                            }
                        } catch (Throwable th) {
                            ReferenceCountUtil.release(obj);
                            throw th;
                        }
                    } else {
                        channelHandlerContext.write(obj, channelPromise);
                    }
                    if (codecOutputListNewInstance != null) {
                        try {
                            int size = codecOutputListNewInstance.size() - 1;
                            if (size == 0) {
                                channelHandlerContext.write(codecOutputListNewInstance.getUnsafe(0), channelPromise);
                            } else if (size > 0) {
                                if (channelPromise == channelHandlerContext.voidPromise()) {
                                    writeVoidPromise(channelHandlerContext, codecOutputListNewInstance);
                                } else {
                                    writePromiseCombiner(channelHandlerContext, codecOutputListNewInstance, channelPromise);
                                }
                            }
                        } finally {
                        }
                    }
                } catch (Throwable th2) {
                    if (0 != 0) {
                        try {
                            int size2 = codecOutputListNewInstance.size() - 1;
                            if (size2 == 0) {
                                channelHandlerContext.write(codecOutputListNewInstance.getUnsafe(0), channelPromise);
                            } else if (size2 > 0) {
                                if (channelPromise == channelHandlerContext.voidPromise()) {
                                    writeVoidPromise(channelHandlerContext, null);
                                } else {
                                    writePromiseCombiner(channelHandlerContext, null, channelPromise);
                                }
                            }
                        } finally {
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                throw new EncoderException(th3);
            }
        } catch (EncoderException e) {
            throw e;
        }
    }
}
