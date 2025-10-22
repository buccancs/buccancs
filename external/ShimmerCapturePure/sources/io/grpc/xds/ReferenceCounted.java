package io.grpc.xds;

import com.google.common.base.Preconditions;

/* loaded from: classes3.dex */
final class ReferenceCounted<T> {
    private final T instance;
    private int refs;

    private ReferenceCounted(T t) {
        this.instance = t;
    }

    static <T> ReferenceCounted<T> wrap(T t) {
        Preconditions.checkNotNull(t, "instance");
        return new ReferenceCounted<>(t);
    }

    T get() {
        return this.instance;
    }

    int getReferenceCount() {
        return this.refs;
    }

    void retain() {
        this.refs++;
    }

    void release() {
        Preconditions.checkState(this.refs > 0, "reference reached 0");
        this.refs--;
    }
}
