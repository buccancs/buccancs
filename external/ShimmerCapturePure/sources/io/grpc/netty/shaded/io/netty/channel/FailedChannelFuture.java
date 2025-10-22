package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* loaded from: classes3.dex */
final class FailedChannelFuture extends CompleteChannelFuture {
    private final Throwable cause;

    FailedChannelFuture(Channel channel, EventExecutor eventExecutor, Throwable th) {
        super(channel, eventExecutor);
        this.cause = (Throwable) ObjectUtil.checkNotNull(th, "cause");
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return this.cause;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        return false;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.CompleteChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> sync() throws Throwable {
        PlatformDependent.throwException(this.cause);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.CompleteChannelFuture, io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> syncUninterruptibly() throws Throwable {
        PlatformDependent.throwException(this.cause);
        return this;
    }
}
