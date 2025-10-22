package io.grpc.stub;

/* loaded from: classes3.dex */
public interface StreamObserver<V> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(V v);
}
