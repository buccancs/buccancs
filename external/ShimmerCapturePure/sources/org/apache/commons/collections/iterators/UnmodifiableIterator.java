package org.apache.commons.collections.iterators;

import java.util.Iterator;

import org.apache.commons.collections.Unmodifiable;

/* loaded from: classes5.dex */
public final class UnmodifiableIterator implements Iterator, Unmodifiable {
    private Iterator iterator;

    private UnmodifiableIterator(Iterator it2) {
        this.iterator = it2;
    }

    public static Iterator decorate(Iterator it2) {
        if (it2 != null) {
            return it2 instanceof Unmodifiable ? it2 : new UnmodifiableIterator(it2);
        }
        throw new IllegalArgumentException("Iterator must not be null");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
