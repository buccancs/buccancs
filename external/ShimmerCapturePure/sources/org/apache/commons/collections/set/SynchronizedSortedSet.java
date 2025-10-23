package org.apache.commons.collections.set;

import java.util.Comparator;
import java.util.SortedSet;

import org.apache.commons.collections.collection.SynchronizedCollection;

/* loaded from: classes5.dex */
public class SynchronizedSortedSet extends SynchronizedCollection implements SortedSet {
    private static final long serialVersionUID = 2775582861954500111L;

    protected SynchronizedSortedSet(SortedSet sortedSet) {
        super(sortedSet);
    }

    protected SynchronizedSortedSet(SortedSet sortedSet, Object obj) {
        super(sortedSet, obj);
    }

    public static SortedSet decorate(SortedSet sortedSet) {
        return new SynchronizedSortedSet(sortedSet);
    }

    protected SortedSet getSortedSet() {
        return (SortedSet) this.collection;
    }

    @Override // java.util.SortedSet
    public SortedSet subSet(Object obj, Object obj2) {
        SynchronizedSortedSet synchronizedSortedSet;
        synchronized (this.lock) {
            synchronizedSortedSet = new SynchronizedSortedSet(getSortedSet().subSet(obj, obj2), this.lock);
        }
        return synchronizedSortedSet;
    }

    @Override // java.util.SortedSet
    public SortedSet headSet(Object obj) {
        SynchronizedSortedSet synchronizedSortedSet;
        synchronized (this.lock) {
            synchronizedSortedSet = new SynchronizedSortedSet(getSortedSet().headSet(obj), this.lock);
        }
        return synchronizedSortedSet;
    }

    @Override // java.util.SortedSet
    public SortedSet tailSet(Object obj) {
        SynchronizedSortedSet synchronizedSortedSet;
        synchronized (this.lock) {
            synchronizedSortedSet = new SynchronizedSortedSet(getSortedSet().tailSet(obj), this.lock);
        }
        return synchronizedSortedSet;
    }

    @Override // java.util.SortedSet
    public Object first() {
        Object objFirst;
        synchronized (this.lock) {
            objFirst = getSortedSet().first();
        }
        return objFirst;
    }

    @Override // java.util.SortedSet
    public Object last() {
        Object objLast;
        synchronized (this.lock) {
            objLast = getSortedSet().last();
        }
        return objLast;
    }

    @Override // java.util.SortedSet
    public Comparator comparator() {
        Comparator comparator;
        synchronized (this.lock) {
            comparator = getSortedSet().comparator();
        }
        return comparator;
    }
}
