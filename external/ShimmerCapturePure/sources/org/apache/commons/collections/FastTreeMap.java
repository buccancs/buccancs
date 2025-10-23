package org.apache.commons.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public class FastTreeMap extends TreeMap {
    protected boolean fast = false;
    protected TreeMap map;

    public FastTreeMap() {
        this.map = null;
        this.map = new TreeMap();
    }

    public FastTreeMap(Comparator comparator) {
        this.map = null;
        this.map = new TreeMap(comparator);
    }

    public FastTreeMap(Map map) {
        this.map = null;
        this.map = new TreeMap(map);
    }

    public FastTreeMap(SortedMap sortedMap) {
        this.map = null;
        this.map = new TreeMap(sortedMap);
    }

    public boolean getFast() {
        return this.fast;
    }

    public void setFast(boolean z) {
        this.fast = z;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Object obj2;
        if (this.fast) {
            return this.map.get(obj);
        }
        synchronized (this.map) {
            obj2 = this.map.get(obj);
        }
        return obj2;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public int size() {
        int size;
        if (this.fast) {
            return this.map.size();
        }
        synchronized (this.map) {
            size = this.map.size();
        }
        return size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        boolean zIsEmpty;
        if (this.fast) {
            return this.map.isEmpty();
        }
        synchronized (this.map) {
            zIsEmpty = this.map.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        boolean zContainsKey;
        if (this.fast) {
            return this.map.containsKey(obj);
        }
        synchronized (this.map) {
            zContainsKey = this.map.containsKey(obj);
        }
        return zContainsKey;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        boolean zContainsValue;
        if (this.fast) {
            return this.map.containsValue(obj);
        }
        synchronized (this.map) {
            zContainsValue = this.map.containsValue(obj);
        }
        return zContainsValue;
    }

    @Override // java.util.TreeMap, java.util.SortedMap
    public Comparator comparator() {
        Comparator comparator;
        if (this.fast) {
            return this.map.comparator();
        }
        synchronized (this.map) {
            comparator = this.map.comparator();
        }
        return comparator;
    }

    @Override // java.util.TreeMap, java.util.SortedMap
    public Object firstKey() {
        Object objFirstKey;
        if (this.fast) {
            return this.map.firstKey();
        }
        synchronized (this.map) {
            objFirstKey = this.map.firstKey();
        }
        return objFirstKey;
    }

    @Override // java.util.TreeMap, java.util.SortedMap
    public Object lastKey() {
        Object objLastKey;
        if (this.fast) {
            return this.map.lastKey();
        }
        synchronized (this.map) {
            objLastKey = this.map.lastKey();
        }
        return objLastKey;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        Object objPut;
        Object objPut2;
        if (this.fast) {
            synchronized (this) {
                TreeMap treeMap = (TreeMap) this.map.clone();
                objPut2 = treeMap.put(obj, obj2);
                this.map = treeMap;
            }
            return objPut2;
        }
        synchronized (this.map) {
            objPut = this.map.put(obj, obj2);
        }
        return objPut;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        if (this.fast) {
            synchronized (this) {
                TreeMap treeMap = (TreeMap) this.map.clone();
                treeMap.putAll(map);
                this.map = treeMap;
            }
            return;
        }
        synchronized (this.map) {
            this.map.putAll(map);
        }
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object objRemove;
        Object objRemove2;
        if (this.fast) {
            synchronized (this) {
                TreeMap treeMap = (TreeMap) this.map.clone();
                objRemove2 = treeMap.remove(obj);
                this.map = treeMap;
            }
            return objRemove2;
        }
        synchronized (this.map) {
            objRemove = this.map.remove(obj);
        }
        return objRemove;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (this.fast) {
            synchronized (this) {
                this.map = new TreeMap();
            }
        } else {
            synchronized (this.map) {
                this.map.clear();
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.fast) {
            if (map.size() != this.map.size()) {
                return false;
            }
            for (Map.Entry entry : this.map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    if (map.get(key) != null || !map.containsKey(key)) {
                        return false;
                    }
                } else if (!value.equals(map.get(key))) {
                    return false;
                }
            }
            return true;
        }
        synchronized (this.map) {
            if (map.size() != this.map.size()) {
                return false;
            }
            for (Map.Entry entry2 : this.map.entrySet()) {
                Object key2 = entry2.getKey();
                Object value2 = entry2.getValue();
                if (value2 == null) {
                    if (map.get(key2) != null || !map.containsKey(key2)) {
                        return false;
                    }
                } else if (!value2.equals(map.get(key2))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iHashCode = 0;
        if (this.fast) {
            Iterator it2 = this.map.entrySet().iterator();
            while (it2.hasNext()) {
                iHashCode += it2.next().hashCode();
            }
            return iHashCode;
        }
        synchronized (this.map) {
            Iterator it3 = this.map.entrySet().iterator();
            while (it3.hasNext()) {
                iHashCode += it3.next().hashCode();
            }
        }
        return iHashCode;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap
    public Object clone() {
        FastTreeMap fastTreeMap;
        FastTreeMap fastTreeMap2;
        if (this.fast) {
            fastTreeMap2 = new FastTreeMap((SortedMap) this.map);
        } else {
            synchronized (this.map) {
                fastTreeMap = new FastTreeMap((SortedMap) this.map);
            }
            fastTreeMap2 = fastTreeMap;
        }
        fastTreeMap2.setFast(getFast());
        return fastTreeMap2;
    }

    @Override // java.util.TreeMap, java.util.NavigableMap, java.util.SortedMap
    public SortedMap headMap(Object obj) {
        SortedMap sortedMapHeadMap;
        if (this.fast) {
            return this.map.headMap(obj);
        }
        synchronized (this.map) {
            sortedMapHeadMap = this.map.headMap(obj);
        }
        return sortedMapHeadMap;
    }

    @Override // java.util.TreeMap, java.util.NavigableMap, java.util.SortedMap
    public SortedMap subMap(Object obj, Object obj2) {
        SortedMap sortedMapSubMap;
        if (this.fast) {
            return this.map.subMap(obj, obj2);
        }
        synchronized (this.map) {
            sortedMapSubMap = this.map.subMap(obj, obj2);
        }
        return sortedMapSubMap;
    }

    @Override // java.util.TreeMap, java.util.NavigableMap, java.util.SortedMap
    public SortedMap tailMap(Object obj) {
        SortedMap sortedMapTailMap;
        if (this.fast) {
            return this.map.tailMap(obj);
        }
        synchronized (this.map) {
            sortedMapTailMap = this.map.tailMap(obj);
        }
        return sortedMapTailMap;
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set entrySet() {
        return new EntrySet(this, null);
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set keySet() {
        return new KeySet(this, null);
    }

    @Override // java.util.TreeMap, java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Collection values() {
        return new Values(this, null);
    }

    /* renamed from: org.apache.commons.collections.FastTreeMap$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private abstract class CollectionView implements Collection {
        public CollectionView() {
        }

        protected abstract Collection get(Map map);

        protected abstract Object iteratorNext(Map.Entry entry);

        @Override // java.util.Collection
        public void clear() {
            if (FastTreeMap.this.fast) {
                synchronized (FastTreeMap.this) {
                    FastTreeMap.this.map = new TreeMap();
                }
                return;
            }
            synchronized (FastTreeMap.this.map) {
                get(FastTreeMap.this.map).clear();
            }
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            boolean zRemove;
            boolean zRemove2;
            if (FastTreeMap.this.fast) {
                synchronized (FastTreeMap.this) {
                    TreeMap treeMap = (TreeMap) FastTreeMap.this.map.clone();
                    zRemove2 = get(treeMap).remove(obj);
                    FastTreeMap.this.map = treeMap;
                }
                return zRemove2;
            }
            synchronized (FastTreeMap.this.map) {
                zRemove = get(FastTreeMap.this.map).remove(obj);
            }
            return zRemove;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            boolean zRemoveAll;
            boolean zRemoveAll2;
            if (FastTreeMap.this.fast) {
                synchronized (FastTreeMap.this) {
                    TreeMap treeMap = (TreeMap) FastTreeMap.this.map.clone();
                    zRemoveAll2 = get(treeMap).removeAll(collection);
                    FastTreeMap.this.map = treeMap;
                }
                return zRemoveAll2;
            }
            synchronized (FastTreeMap.this.map) {
                zRemoveAll = get(FastTreeMap.this.map).removeAll(collection);
            }
            return zRemoveAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            boolean zRetainAll;
            boolean zRetainAll2;
            if (FastTreeMap.this.fast) {
                synchronized (FastTreeMap.this) {
                    TreeMap treeMap = (TreeMap) FastTreeMap.this.map.clone();
                    zRetainAll2 = get(treeMap).retainAll(collection);
                    FastTreeMap.this.map = treeMap;
                }
                return zRetainAll2;
            }
            synchronized (FastTreeMap.this.map) {
                zRetainAll = get(FastTreeMap.this.map).retainAll(collection);
            }
            return zRetainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).size();
            }
            synchronized (FastTreeMap.this.map) {
                size = get(FastTreeMap.this.map).size();
            }
            return size;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean zIsEmpty;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).isEmpty();
            }
            synchronized (FastTreeMap.this.map) {
                zIsEmpty = get(FastTreeMap.this.map).isEmpty();
            }
            return zIsEmpty;
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            boolean zContains;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).contains(obj);
            }
            synchronized (FastTreeMap.this.map) {
                zContains = get(FastTreeMap.this.map).contains(obj);
            }
            return zContains;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            boolean zContainsAll;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).containsAll(collection);
            }
            synchronized (FastTreeMap.this.map) {
                zContainsAll = get(FastTreeMap.this.map).containsAll(collection);
            }
            return zContainsAll;
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            Object[] array;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).toArray(objArr);
            }
            synchronized (FastTreeMap.this.map) {
                array = get(FastTreeMap.this.map).toArray(objArr);
            }
            return array;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Object[] array;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).toArray();
            }
            synchronized (FastTreeMap.this.map) {
                array = get(FastTreeMap.this.map).toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            boolean zEquals;
            if (obj == this) {
                return true;
            }
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).equals(obj);
            }
            synchronized (FastTreeMap.this.map) {
                zEquals = get(FastTreeMap.this.map).equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Collection
        public int hashCode() {
            int iHashCode;
            if (FastTreeMap.this.fast) {
                return get(FastTreeMap.this.map).hashCode();
            }
            synchronized (FastTreeMap.this.map) {
                iHashCode = get(FastTreeMap.this.map).hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new CollectionViewIterator();
        }

        private class CollectionViewIterator implements Iterator {
            private Map expected;
            private Iterator iterator;
            private Map.Entry lastReturned = null;

            public CollectionViewIterator() {
                TreeMap treeMap = FastTreeMap.this.map;
                this.expected = treeMap;
                this.iterator = treeMap.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.expected != FastTreeMap.this.map) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.expected != FastTreeMap.this.map) {
                    throw new ConcurrentModificationException();
                }
                Map.Entry entry = (Map.Entry) this.iterator.next();
                this.lastReturned = entry;
                return CollectionView.this.iteratorNext(entry);
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.lastReturned != null) {
                    if (FastTreeMap.this.fast) {
                        synchronized (FastTreeMap.this) {
                            if (this.expected == FastTreeMap.this.map) {
                                FastTreeMap.this.remove(this.lastReturned.getKey());
                                this.lastReturned = null;
                                this.expected = FastTreeMap.this.map;
                            } else {
                                throw new ConcurrentModificationException();
                            }
                        }
                        return;
                    }
                    this.iterator.remove();
                    this.lastReturned = null;
                    return;
                }
                throw new IllegalStateException();
            }
        }
    }

    private class KeySet extends CollectionView implements Set {
        private final /* synthetic */ FastTreeMap this$0;

        private KeySet(FastTreeMap fastTreeMap) {
            super();
            this.this$0 = fastTreeMap;
        }

        /* synthetic */ KeySet(FastTreeMap fastTreeMap, AnonymousClass1 anonymousClass1) {
            this(fastTreeMap);
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Collection get(Map map) {
            return map.keySet();
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry.getKey();
        }
    }

    private class Values extends CollectionView {
        private final /* synthetic */ FastTreeMap this$0;

        private Values(FastTreeMap fastTreeMap) {
            super();
            this.this$0 = fastTreeMap;
        }

        /* synthetic */ Values(FastTreeMap fastTreeMap, AnonymousClass1 anonymousClass1) {
            this(fastTreeMap);
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Collection get(Map map) {
            return map.values();
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry.getValue();
        }
    }

    private class EntrySet extends CollectionView implements Set {
        private final /* synthetic */ FastTreeMap this$0;

        private EntrySet(FastTreeMap fastTreeMap) {
            super();
            this.this$0 = fastTreeMap;
        }

        /* synthetic */ EntrySet(FastTreeMap fastTreeMap, AnonymousClass1 anonymousClass1) {
            this(fastTreeMap);
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry;
        }

        @Override // org.apache.commons.collections.FastTreeMap.CollectionView
        protected Collection get(Map map) {
            return map.entrySet();
        }
    }
}
