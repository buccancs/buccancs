package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;

/* loaded from: classes3.dex */
final class SucceededChannelFuture extends CompleteChannelFuture {
    SucceededChannelFuture(Channel channel, EventExecutor eventExecutor) {
        super(channel, eventExecutor);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        return true;
    }
}
