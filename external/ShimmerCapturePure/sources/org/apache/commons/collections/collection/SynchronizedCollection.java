package org.apache.commons.collections.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class SynchronizedCollection implements Collection, Serializable {
    private static final long serialVersionUID = 2412805092710877986L;
    protected final Collection collection;
    protected final Object lock;

    protected SynchronizedCollection(Collection collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        this.collection = collection;
        this.lock = this;
    }

    protected SynchronizedCollection(Collection collection, Object obj) {
        if (collection == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        this.collection = collection;
        this.lock = obj;
    }

    public static Collection decorate(Collection collection) {
        return new SynchronizedCollection(collection);
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        boolean zAdd;
        synchronized (this.lock) {
            zAdd = this.collection.add(obj);
        }
        return zAdd;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        boolean zAddAll;
        synchronized (this.lock) {
            zAddAll = this.collection.addAll(collection);
        }
        return zAddAll;
    }

    @Override // java.util.Collection
    public void clear() {
        synchronized (this.lock) {
            this.collection.clear();
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        boolean zContains;
        synchronized (this.lock) {
            zContains = this.collection.contains(obj);
        }
        return zContains;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        boolean zContainsAll;
        synchronized (this.lock) {
            zContainsAll = this.collection.containsAll(collection);
        }
        return zContainsAll;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.lock) {
            zIsEmpty = this.collection.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return this.collection.iterator();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] array;
        synchronized (this.lock) {
            array = this.collection.toArray();
        }
        return array;
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        Object[] array;
        synchronized (this.lock) {
            array = this.collection.toArray(objArr);
        }
        return array;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        boolean zRemove;
        synchronized (this.lock) {
            zRemove = this.collection.remove(obj);
        }
        return zRemove;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        boolean zRemoveAll;
        synchronized (this.lock) {
            zRemoveAll = this.collection.removeAll(collection);
        }
        return zRemoveAll;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        boolean zRetainAll;
        synchronized (this.lock) {
            zRetainAll = this.collection.retainAll(collection);
        }
        return zRetainAll;
    }

    @Override // java.util.Collection
    public int size() {
        int size;
        synchronized (this.lock) {
            size = this.collection.size();
        }
        return size;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        synchronized (this.lock) {
            if (obj == this) {
                return true;
            }
            return this.collection.equals(obj);
        }
    }

    @Override // java.util.Collection
    public int hashCode() {
        int iHashCode;
        synchronized (this.lock) {
            iHashCode = this.collection.hashCode();
        }
        return iHashCode;
    }

    public String toString() {
        String string;
        synchronized (this.lock) {
            string = this.collection.toString();
        }
        return string;
    }
}
