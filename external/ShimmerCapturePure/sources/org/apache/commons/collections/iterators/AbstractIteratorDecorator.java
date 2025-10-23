package org.apache.commons.collections.iterators;

import java.util.Iterator;

/* loaded from: classes5.dex */
public class AbstractIteratorDecorator implements Iterator {
    protected final Iterator iterator;

    public AbstractIteratorDecorator(Iterator it2) {
        if (it2 == null) {
            throw new IllegalArgumentException("Iterator must not be null");
        }
        this.iterator = it2;
    }

    protected Iterator getIterator() {
        return this.iterator;
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
        this.iterator.remove();
    }
}
