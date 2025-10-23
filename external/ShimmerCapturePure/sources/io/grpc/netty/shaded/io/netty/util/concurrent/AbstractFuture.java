package io.grpc.netty.shaded.io.netty.util.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
public abstract class AbstractFuture<V> implements Future<V> {
    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        await();
        Throwable thCause = cause();
        if (thCause == null) {
            return getNow();
        }
        if (thCause instanceof CancellationException) {
            throw ((CancellationException) thCause);
        }
        throw new ExecutionException(thCause);
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (await(j, timeUnit)) {
            Throwable thCause = cause();
            if (thCause == null) {
                return getNow();
            }
            if (thCause instanceof CancellationException) {
                throw ((CancellationException) thCause);
            }
            throw new ExecutionException(thCause);
        }
        throw new TimeoutException();
    }
}
