package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

/* loaded from: classes3.dex */
public class DefaultChannelProgressivePromise extends DefaultProgressivePromise<Void> implements ChannelProgressivePromise, ChannelFlushPromiseNotifier.FlushCheckpoint {
    private final Channel channel;
    private long checkpoint;

    public DefaultChannelProgressivePromise(Channel channel) {
        this.channel = channel;
    }

    public DefaultChannelProgressivePromise(Channel channel, EventExecutor eventExecutor) {
        super(eventExecutor);
        this.channel = channel;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public Channel channel() {
        return this.channel;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
    public long flushCheckpoint() {
        return this.checkpoint;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
    public void flushCheckpoint(long j) {
        this.checkpoint = j;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFuture
    public boolean isVoid() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
    public ChannelProgressivePromise promise() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public ChannelProgressivePromise unvoid() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    protected EventExecutor executor() {
        EventExecutor eventExecutorExecutor = super.executor();
        return eventExecutorExecutor == null ? channel().eventLoop() : eventExecutorExecutor;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public ChannelProgressivePromise setSuccess() {
        return setSuccess((Void) null);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public ChannelProgressivePromise setSuccess(Void r1) {
        super.setSuccess((DefaultChannelProgressivePromise) r1);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public boolean trySuccess() {
        return trySuccess(null);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Promise, io.grpc.netty.shaded.io.netty.channel.ChannelPromise
    public ChannelProgressivePromise setFailure(Throwable th) {
        super.setFailure(th);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise
    public ChannelProgressivePromise setProgress(long j, long j2) {
        super.setProgress(j, j2);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.addListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.addListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.removeListener((GenericFutureListener) genericFutureListener);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.removeListeners((GenericFutureListener[]) genericFutureListenerArr);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> sync() throws Throwable {
        super.sync();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> syncUninterruptibly() throws Throwable {
        super.syncUninterruptibly();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> await() throws InterruptedException {
        super.await();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultProgressivePromise, io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise, io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Future<Void> awaitUninterruptibly() {
        super.awaitUninterruptibly();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
    protected void checkDeadLock() {
        if (channel().isRegistered()) {
            super.checkDeadLock();
        }
    }
}
