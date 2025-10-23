package org.apache.commons.collections.iterators;

import java.util.Iterator;

import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class TransformIterator implements Iterator {
    private Iterator iterator;
    private Transformer transformer;

    public TransformIterator() {
    }

    public TransformIterator(Iterator it2) {
        this.iterator = it2;
    }

    public TransformIterator(Iterator it2, Transformer transformer) {
        this.iterator = it2;
        this.transformer = transformer;
    }

    public Iterator getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator it2) {
        this.iterator = it2;
    }

    public Transformer getTransformer() {
        return this.transformer;
    }

    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return transform(this.iterator.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }

    protected Object transform(Object obj) {
        Transformer transformer = this.transformer;
        return transformer != null ? transformer.transform(obj) : obj;
    }
}
