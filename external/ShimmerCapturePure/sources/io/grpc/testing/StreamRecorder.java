package io.grpc.testing;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Deprecated
/* loaded from: classes3.dex */
public class StreamRecorder<T> implements StreamObserver<T> {
    private final SettableFuture<T> firstValue = SettableFuture.create();
    private final CountDownLatch latch = new CountDownLatch(1);
    private final List<T> results = Collections.synchronizedList(new ArrayList());
    private Throwable error;

    private StreamRecorder() {
    }

    public static <T> StreamRecorder<T> create() {
        return new StreamRecorder<>();
    }

    public ListenableFuture<T> firstValue() {
        return this.firstValue;
    }

    @Nullable
    public Throwable getError() {
        return this.error;
    }

    @Override // io.grpc.stub.StreamObserver
    public void onNext(T t) {
        if (!this.firstValue.isDone()) {
            this.firstValue.set(t);
        }
        this.results.add(t);
    }

    @Override // io.grpc.stub.StreamObserver
    public void onError(Throwable th) {
        if (!this.firstValue.isDone()) {
            this.firstValue.setException(th);
        }
        this.error = th;
        this.latch.countDown();
    }

    @Override // io.grpc.stub.StreamObserver
    public void onCompleted() {
        if (!this.firstValue.isDone()) {
            this.firstValue.setException(new IllegalStateException("No first value provided"));
        }
        this.latch.countDown();
    }

    public void awaitCompletion() throws Exception {
        this.latch.await();
    }

    public boolean awaitCompletion(int i, TimeUnit timeUnit) throws Exception {
        return this.latch.await(i, timeUnit);
    }

    public List<T> getValues() {
        return Collections.unmodifiableList(this.results);
    }
}
