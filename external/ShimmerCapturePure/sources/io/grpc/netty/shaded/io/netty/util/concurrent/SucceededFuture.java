package io.grpc.netty.shaded.io.netty.util.concurrent;

/* loaded from: classes3.dex */
public final class SucceededFuture<V> extends CompleteFuture<V> {
    private final V result;

    public SucceededFuture(EventExecutor eventExecutor, V v) {
        super(eventExecutor);
        this.result = v;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public V getNow() {
        return this.result;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        return true;
    }
}
