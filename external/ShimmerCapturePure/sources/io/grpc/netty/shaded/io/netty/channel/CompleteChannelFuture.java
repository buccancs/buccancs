package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
abstract class CompleteChannelFuture extends CompleteFuture<Void> implements ChannelFuture {
    private final Channel channel;

    protected CompleteChannelFuture(Channel channel, EventExecutor eventExecutor) {
        super(eventExecutor);
        this.channel = (Channel) ObjectUtil.checkNotNull(channel, "channel");
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> await() throws InterruptedException {
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> awaitUninterruptibly() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public Channel channel() {
        return this.channel;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Void getNow() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public boolean isVoid() {
        return false;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> sync() throws InterruptedException {
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> syncUninterruptibly() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture
    protected EventExecutor executor() {
        EventExecutor eventExecutorExecutor = super.executor();
        return eventExecutorExecutor == null ? channel().eventLoop() : eventExecutorExecutor;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.addListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.addListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.removeListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.removeListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }
}
