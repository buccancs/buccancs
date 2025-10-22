package io.grpc.stub;

import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class ClientCallStreamObserver<V> extends CallStreamObserver<V> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);

    public void disableAutoRequestWithInitial(int i) {
        throw new UnsupportedOperationException();
    }
}
