package io.grpc.netty.shaded.io.netty.channel.group;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
final class CombinedIterator<E> implements Iterator<E> {
    private final Iterator<E> i1;
    private final Iterator<E> i2;
    private Iterator<E> currentIterator;

    CombinedIterator(Iterator<E> it2, Iterator<E> it3) {
        this.i1 = (Iterator) ObjectUtil.checkNotNull(it2, "i1");
        this.i2 = (Iterator) ObjectUtil.checkNotNull(it3, "i2");
        this.currentIterator = it2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (!this.currentIterator.hasNext()) {
            if (this.currentIterator != this.i1) {
                return false;
            }
            this.currentIterator = this.i2;
        }
        return true;
    }

    @Override // java.util.Iterator
    public E next() {
        while (true) {
            try {
                return this.currentIterator.next();
            } catch (NoSuchElementException e) {
                if (this.currentIterator != this.i1) {
                    throw e;
                }
                this.currentIterator = this.i2;
            }
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        this.currentIterator.remove();
    }
}
