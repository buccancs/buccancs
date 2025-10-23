package org.apache.commons.collections;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class FastHashMap extends HashMap {
    protected boolean fast = false;
    protected HashMap map;

    public FastHashMap() {
        this.map = null;
        this.map = new HashMap();
    }

    public FastHashMap(int i) {
        this.map = null;
        this.map = new HashMap(i);
    }

    public FastHashMap(int i, float f) {
        this.map = null;
        this.map = new HashMap(i, f);
    }

    public FastHashMap(Map map) {
        this.map = null;
        this.map = new HashMap(map);
    }

    public boolean getFast() {
        return this.fast;
    }

    public void setFast(boolean z) {
        this.fast = z;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
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

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
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

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
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

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
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

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
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

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        Object objPut;
        Object objPut2;
        if (this.fast) {
            synchronized (this) {
                HashMap map = (HashMap) this.map.clone();
                objPut2 = map.put(obj, obj2);
                this.map = map;
            }
            return objPut2;
        }
        synchronized (this.map) {
            objPut = this.map.put(obj, obj2);
        }
        return objPut;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        if (this.fast) {
            synchronized (this) {
                HashMap map2 = (HashMap) this.map.clone();
                map2.putAll(map);
                this.map = map2;
            }
            return;
        }
        synchronized (this.map) {
            this.map.putAll(map);
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object objRemove;
        Object objRemove2;
        if (this.fast) {
            synchronized (this) {
                HashMap map = (HashMap) this.map.clone();
                objRemove2 = map.remove(obj);
                this.map = map;
            }
            return objRemove2;
        }
        synchronized (this.map) {
            objRemove = this.map.remove(obj);
        }
        return objRemove;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (this.fast) {
            synchronized (this) {
                this.map = new HashMap();
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

    @Override // java.util.HashMap, java.util.AbstractMap
    public Object clone() {
        FastHashMap fastHashMap;
        FastHashMap fastHashMap2;
        if (this.fast) {
            fastHashMap2 = new FastHashMap(this.map);
        } else {
            synchronized (this.map) {
                fastHashMap = new FastHashMap(this.map);
            }
            fastHashMap2 = fastHashMap;
        }
        fastHashMap2.setFast(getFast());
        return fastHashMap2;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        return new EntrySet(this, null);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Set keySet() {
        return new KeySet(this, null);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Collection values() {
        return new Values(this, null);
    }

    /* renamed from: org.apache.commons.collections.FastHashMap$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private abstract class CollectionView implements Collection {
        public CollectionView() {
        }

        protected abstract Collection get(Map map);

        protected abstract Object iteratorNext(Map.Entry entry);

        @Override // java.util.Collection
        public void clear() {
            if (FastHashMap.this.fast) {
                synchronized (FastHashMap.this) {
                    FastHashMap.this.map = new HashMap();
                }
                return;
            }
            synchronized (FastHashMap.this.map) {
                get(FastHashMap.this.map).clear();
            }
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            boolean zRemove;
            boolean zRemove2;
            if (FastHashMap.this.fast) {
                synchronized (FastHashMap.this) {
                    HashMap map = (HashMap) FastHashMap.this.map.clone();
                    zRemove2 = get(map).remove(obj);
                    FastHashMap.this.map = map;
                }
                return zRemove2;
            }
            synchronized (FastHashMap.this.map) {
                zRemove = get(FastHashMap.this.map).remove(obj);
            }
            return zRemove;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            boolean zRemoveAll;
            boolean zRemoveAll2;
            if (FastHashMap.this.fast) {
                synchronized (FastHashMap.this) {
                    HashMap map = (HashMap) FastHashMap.this.map.clone();
                    zRemoveAll2 = get(map).removeAll(collection);
                    FastHashMap.this.map = map;
                }
                return zRemoveAll2;
            }
            synchronized (FastHashMap.this.map) {
                zRemoveAll = get(FastHashMap.this.map).removeAll(collection);
            }
            return zRemoveAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            boolean zRetainAll;
            boolean zRetainAll2;
            if (FastHashMap.this.fast) {
                synchronized (FastHashMap.this) {
                    HashMap map = (HashMap) FastHashMap.this.map.clone();
                    zRetainAll2 = get(map).retainAll(collection);
                    FastHashMap.this.map = map;
                }
                return zRetainAll2;
            }
            synchronized (FastHashMap.this.map) {
                zRetainAll = get(FastHashMap.this.map).retainAll(collection);
            }
            return zRetainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).size();
            }
            synchronized (FastHashMap.this.map) {
                size = get(FastHashMap.this.map).size();
            }
            return size;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean zIsEmpty;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).isEmpty();
            }
            synchronized (FastHashMap.this.map) {
                zIsEmpty = get(FastHashMap.this.map).isEmpty();
            }
            return zIsEmpty;
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            boolean zContains;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).contains(obj);
            }
            synchronized (FastHashMap.this.map) {
                zContains = get(FastHashMap.this.map).contains(obj);
            }
            return zContains;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            boolean zContainsAll;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).containsAll(collection);
            }
            synchronized (FastHashMap.this.map) {
                zContainsAll = get(FastHashMap.this.map).containsAll(collection);
            }
            return zContainsAll;
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            Object[] array;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).toArray(objArr);
            }
            synchronized (FastHashMap.this.map) {
                array = get(FastHashMap.this.map).toArray(objArr);
            }
            return array;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Object[] array;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).toArray();
            }
            synchronized (FastHashMap.this.map) {
                array = get(FastHashMap.this.map).toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            boolean zEquals;
            if (obj == this) {
                return true;
            }
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).equals(obj);
            }
            synchronized (FastHashMap.this.map) {
                zEquals = get(FastHashMap.this.map).equals(obj);
            }
            return zEquals;
        }

        @Override // java.util.Collection
        public int hashCode() {
            int iHashCode;
            if (FastHashMap.this.fast) {
                return get(FastHashMap.this.map).hashCode();
            }
            synchronized (FastHashMap.this.map) {
                iHashCode = get(FastHashMap.this.map).hashCode();
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
                HashMap map = FastHashMap.this.map;
                this.expected = map;
                this.iterator = map.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.expected != FastHashMap.this.map) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.expected != FastHashMap.this.map) {
                    throw new ConcurrentModificationException();
                }
                Map.Entry entry = (Map.Entry) this.iterator.next();
                this.lastReturned = entry;
                return CollectionView.this.iteratorNext(entry);
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.lastReturned != null) {
                    if (FastHashMap.this.fast) {
                        synchronized (FastHashMap.this) {
                            if (this.expected == FastHashMap.this.map) {
                                FastHashMap.this.remove(this.lastReturned.getKey());
                                this.lastReturned = null;
                                this.expected = FastHashMap.this.map;
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
        private final /* synthetic */ FastHashMap this$0;

        private KeySet(FastHashMap fastHashMap) {
            super();
            this.this$0 = fastHashMap;
        }

        /* synthetic */ KeySet(FastHashMap fastHashMap, AnonymousClass1 anonymousClass1) {
            this(fastHashMap);
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Collection get(Map map) {
            return map.keySet();
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry.getKey();
        }
    }

    private class Values extends CollectionView {
        private final /* synthetic */ FastHashMap this$0;

        private Values(FastHashMap fastHashMap) {
            super();
            this.this$0 = fastHashMap;
        }

        /* synthetic */ Values(FastHashMap fastHashMap, AnonymousClass1 anonymousClass1) {
            this(fastHashMap);
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Collection get(Map map) {
            return map.values();
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry.getValue();
        }
    }

    private class EntrySet extends CollectionView implements Set {
        private final /* synthetic */ FastHashMap this$0;

        private EntrySet(FastHashMap fastHashMap) {
            super();
            this.this$0 = fastHashMap;
        }

        /* synthetic */ EntrySet(FastHashMap fastHashMap, AnonymousClass1 anonymousClass1) {
            this(fastHashMap);
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Object iteratorNext(Map.Entry entry) {
            return entry;
        }

        @Override // org.apache.commons.collections.FastHashMap.CollectionView
        protected Collection get(Map map) {
            return map.entrySet();
        }
    }
}
