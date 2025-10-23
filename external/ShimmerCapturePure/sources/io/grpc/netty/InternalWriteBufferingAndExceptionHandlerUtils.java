package io.grpc.netty;

import io.netty.channel.Channel;

/* loaded from: classes2.dex */
public final class InternalWriteBufferingAndExceptionHandlerUtils {
    public static void writeBufferingAndRemove(Channel channel) {
        NettyClientHandler.writeBufferingAndRemove(channel);
    }
}
