package org.apache.commons.lang3.concurrent;

/* loaded from: classes5.dex */
public abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private volatile T object;

    protected abstract T initialize() throws ConcurrentException;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public T get() throws ConcurrentException {
        T tInitialize = this.object;
        if (tInitialize == null) {
            synchronized (this) {
                tInitialize = this.object;
                if (tInitialize == null) {
                    tInitialize = initialize();
                    this.object = tInitialize;
                }
            }
        }
        return tInitialize;
    }
}
