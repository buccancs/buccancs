package org.apache.commons.collections.iterators;

import java.util.Enumeration;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class IteratorEnumeration implements Enumeration {
    private Iterator iterator;

    public IteratorEnumeration() {
    }

    public IteratorEnumeration(Iterator it2) {
        this.iterator = it2;
    }

    public Iterator getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator it2) {
        this.iterator = it2;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        return this.iterator.next();
    }
}
