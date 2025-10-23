package org.apache.commons.collections.iterators;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class EnumerationIterator implements Iterator {
    private Collection collection;
    private Enumeration enumeration;
    private Object last;

    public EnumerationIterator() {
        this(null, null);
    }

    public EnumerationIterator(Enumeration enumeration) {
        this(enumeration, null);
    }

    public EnumerationIterator(Enumeration enumeration, Collection collection) {
        this.enumeration = enumeration;
        this.collection = collection;
        this.last = null;
    }

    public Enumeration getEnumeration() {
        return this.enumeration;
    }

    public void setEnumeration(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    @Override // java.util.Iterator
    public Object next() {
        Object objNextElement = this.enumeration.nextElement();
        this.last = objNextElement;
        return objNextElement;
    }

    @Override // java.util.Iterator
    public void remove() {
        Collection collection = this.collection;
        if (collection == null) {
            throw new UnsupportedOperationException("No Collection associated with this Iterator");
        }
        Object obj = this.last;
        if (obj != null) {
            collection.remove(obj);
            return;
        }
        throw new IllegalStateException("next() must have been called for remove() to function");
    }
}
