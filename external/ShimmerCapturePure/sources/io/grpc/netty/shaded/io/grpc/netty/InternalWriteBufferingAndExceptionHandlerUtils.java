package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.netty.shaded.io.netty.channel.Channel;

/* loaded from: classes2.dex */
public final class InternalWriteBufferingAndExceptionHandlerUtils {
    public static void writeBufferingAndRemove(Channel channel) {
        NettyClientHandler.writeBufferingAndRemove(channel);
    }
}
