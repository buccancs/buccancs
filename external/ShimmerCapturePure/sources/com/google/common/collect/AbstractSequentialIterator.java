package com.google.common.collect;

import java.util.NoSuchElementException;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {

    @NullableDecl
    private T nextOrNull;

    protected AbstractSequentialIterator(@NullableDecl T t) {
        this.nextOrNull = t;
    }

    @NullableDecl
    protected abstract T computeNext(T t);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            T t = this.nextOrNull;
            this.nextOrNull = computeNext(t);
            return t;
        } catch (Throwable th) {
            this.nextOrNull = computeNext(this.nextOrNull);
            throw th;
        }
    }
}
