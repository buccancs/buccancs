package org.apache.commons.collections.bag;

import java.util.Comparator;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.SortedBag;

/* loaded from: classes5.dex */
public class SynchronizedSortedBag extends SynchronizedBag implements SortedBag {
    private static final long serialVersionUID = 722374056718497858L;

    protected SynchronizedSortedBag(SortedBag sortedBag) {
        super(sortedBag);
    }

    protected SynchronizedSortedBag(Bag bag, Object obj) {
        super(bag, obj);
    }

    public static SortedBag decorate(SortedBag sortedBag) {
        return new SynchronizedSortedBag(sortedBag);
    }

    protected SortedBag getSortedBag() {
        return (SortedBag) this.collection;
    }

    @Override // org.apache.commons.collections.SortedBag
    public synchronized Object first() {
        Object objFirst;
        synchronized (this.lock) {
            objFirst = getSortedBag().first();
        }
        return objFirst;
    }

    @Override // org.apache.commons.collections.SortedBag
    public synchronized Object last() {
        Object objLast;
        synchronized (this.lock) {
            objLast = getSortedBag().last();
        }
        return objLast;
    }

    @Override // org.apache.commons.collections.SortedBag
    public synchronized Comparator comparator() {
        Comparator comparator;
        synchronized (this.lock) {
            comparator = getSortedBag().comparator();
        }
        return comparator;
    }
}
