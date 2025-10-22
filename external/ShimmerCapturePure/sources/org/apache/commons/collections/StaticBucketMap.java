package org.apache.commons.collections;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes5.dex */
public final class StaticBucketMap implements Map {
    private static final int DEFAULT_BUCKETS = 255;
    private Node[] m_buckets;
    private Lock[] m_locks;

    public StaticBucketMap() {
        this(255);
    }

    public StaticBucketMap(int i) {
        int iMax = Math.max(17, i);
        iMax = iMax % 2 == 0 ? iMax - 1 : iMax;
        this.m_buckets = new Node[iMax];
        this.m_locks = new Lock[iMax];
        for (int i2 = 0; i2 < iMax; i2++) {
            this.m_locks[i2] = new Lock(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getHash(Object obj) {
        if (obj == null) {
            return 0;
        }
        int iHashCode = obj.hashCode();
        int i = iHashCode + (~(iHashCode << 15));
        int i2 = i ^ (i >>> 10);
        int i3 = i2 + (i2 << 3);
        int i4 = i3 ^ (i3 >>> 6);
        int i5 = i4 + (~(i4 << 11));
        int length = (i5 ^ (i5 >>> 16)) % this.m_buckets.length;
        return length < 0 ? length * (-1) : length;
    }

    @Override // java.util.Map
    public Set keySet() {
        return new KeySet(this, null);
    }

    @Override // java.util.Map
    public int size() {
        int i = 0;
        for (int i2 = 0; i2 < this.m_buckets.length; i2++) {
            i += this.m_locks[i2].size;
        }
        return i;
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        int hash = getHash(obj);
        synchronized (this.m_locks[hash]) {
            Node node = this.m_buckets[hash];
            AnonymousClass1 anonymousClass1 = null;
            if (node == null) {
                Node node2 = new Node(anonymousClass1);
                node2.key = obj;
                node2.value = obj2;
                this.m_buckets[hash] = node2;
                this.m_locks[hash].size++;
                return null;
            }
            Node node3 = node;
            while (node != null) {
                if (node.key != obj && (node.key == null || !node.key.equals(obj))) {
                    node3 = node;
                    node = node.next;
                }
                Object obj3 = node.value;
                node.value = obj2;
                return obj3;
            }
            Node node4 = new Node(anonymousClass1);
            node4.key = obj;
            node4.value = obj2;
            node3.next = node4;
            this.m_locks[hash].size++;
            return null;
        }
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        int hash = getHash(obj);
        synchronized (this.m_locks[hash]) {
            for (Node node = this.m_buckets[hash]; node != null; node = node.next) {
                if (node.key != obj && (node.key == null || !node.key.equals(obj))) {
                }
                return node.value;
            }
            return null;
        }
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        int hash = getHash(obj);
        synchronized (this.m_locks[hash]) {
            for (Node node = this.m_buckets[hash]; node != null; node = node.next) {
                if (node.key != obj && (node.key == null || !node.key.equals(obj))) {
                }
                return true;
            }
            return false;
        }
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        for (int i = 0; i < this.m_buckets.length; i++) {
            synchronized (this.m_locks[i]) {
                for (Node node = this.m_buckets[i]; node != null; node = node.next) {
                    if (node.value != obj && (node.value == null || !node.value.equals(obj))) {
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Collection values() {
        return new Values(this, null);
    }

    @Override // java.util.Map
    public Set entrySet() {
        return new EntrySet(this, null);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        for (Object obj : map.keySet()) {
            put(obj, map.get(obj));
        }
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        int hash = getHash(obj);
        synchronized (this.m_locks[hash]) {
            Node node = null;
            for (Node node2 = this.m_buckets[hash]; node2 != null; node2 = node2.next) {
                if (node2.key != obj && (node2.key == null || !node2.key.equals(obj))) {
                    node = node2;
                }
                if (node == null) {
                    this.m_buckets[hash] = node2.next;
                } else {
                    node.next = node2.next;
                }
                Lock lock = this.m_locks[hash];
                lock.size--;
                return node2.value;
            }
            return null;
        }
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final void clear() {
        for (int i = 0; i < this.m_buckets.length; i++) {
            Lock lock = this.m_locks[i];
            synchronized (lock) {
                this.m_buckets[i] = null;
                lock.size = 0;
            }
        }
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public final int hashCode() {
        int iHashCode = 0;
        for (int i = 0; i < this.m_buckets.length; i++) {
            synchronized (this.m_locks[i]) {
                for (Node node = this.m_buckets[i]; node != null; node = node.next) {
                    iHashCode += node.hashCode();
                }
            }
        }
        return iHashCode;
    }

    public void atomic(Runnable runnable) {
        runnable.getClass();
        atomic(runnable, 0);
    }

    private void atomic(Runnable runnable, int i) {
        if (i >= this.m_buckets.length) {
            runnable.run();
            return;
        }
        synchronized (this.m_locks[i]) {
            atomic(runnable, i + 1);
        }
    }

    private static final class Node implements Map.Entry, KeyValue {
        protected Object key;
        protected Node next;
        protected Object value;

        private Node() {
        }

        /* synthetic */ Node(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections.KeyValue
        public Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections.KeyValue
        public Object getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.key;
            int iHashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.value;
            return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.key;
            if (obj2 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!obj2.equals(entry.getKey())) {
                return false;
            }
            Object obj3 = this.value;
            Object value = entry.getValue();
            if (obj3 == null) {
                if (value != null) {
                    return false;
                }
            } else if (!obj3.equals(value)) {
                return false;
            }
            return true;
        }
    }

    private static final class Lock {
        public int size;

        private Lock() {
        }

        /* synthetic */ Lock(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* renamed from: org.apache.commons.collections.StaticBucketMap$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private class EntryIterator implements Iterator {
        private int bucket;
        private ArrayList current;
        private Map.Entry last;

        private EntryIterator() {
            this.current = new ArrayList();
        }

        /* synthetic */ EntryIterator(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.current.size() > 0) {
                return true;
            }
            while (this.bucket < StaticBucketMap.this.m_buckets.length) {
                synchronized (StaticBucketMap.this.m_locks[this.bucket]) {
                    for (Node node = StaticBucketMap.this.m_buckets[this.bucket]; node != null; node = node.next) {
                        this.current.add(node);
                    }
                    this.bucket++;
                    if (this.current.size() > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        protected Map.Entry nextEntry() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Map.Entry entry = (Map.Entry) this.current.remove(r0.size() - 1);
            this.last = entry;
            return entry;
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextEntry();
        }

        @Override // java.util.Iterator
        public void remove() {
            Map.Entry entry = this.last;
            if (entry == null) {
                throw new IllegalStateException();
            }
            StaticBucketMap.this.remove(entry.getKey());
            this.last = null;
        }
    }

    private class ValueIterator extends EntryIterator {
        private final /* synthetic */ StaticBucketMap this$0;

        private ValueIterator(StaticBucketMap staticBucketMap) {
            super(staticBucketMap, null);
            this.this$0 = staticBucketMap;
        }

        /* synthetic */ ValueIterator(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this(staticBucketMap);
        }

        @Override // org.apache.commons.collections.StaticBucketMap.EntryIterator, java.util.Iterator
        public Object next() {
            return nextEntry().getValue();
        }
    }

    private class KeyIterator extends EntryIterator {
        private final /* synthetic */ StaticBucketMap this$0;

        private KeyIterator(StaticBucketMap staticBucketMap) {
            super(staticBucketMap, null);
            this.this$0 = staticBucketMap;
        }

        /* synthetic */ KeyIterator(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this(staticBucketMap);
        }

        @Override // org.apache.commons.collections.StaticBucketMap.EntryIterator, java.util.Iterator
        public Object next() {
            return nextEntry().getKey();
        }
    }

    private class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        /* synthetic */ EntrySet(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new EntryIterator(StaticBucketMap.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            int hash = StaticBucketMap.this.getHash(entry.getKey());
            synchronized (StaticBucketMap.this.m_locks[hash]) {
                for (Node node = StaticBucketMap.this.m_buckets[hash]; node != null; node = node.next) {
                    if (node.equals(entry)) {
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int hash = StaticBucketMap.this.getHash(entry.getKey());
            synchronized (StaticBucketMap.this.m_locks[hash]) {
                for (Node node = StaticBucketMap.this.m_buckets[hash]; node != null; node = node.next) {
                    if (node.equals(entry)) {
                        StaticBucketMap.this.remove(node.getKey());
                        return true;
                    }
                }
                return false;
            }
        }
    }

    private class KeySet extends AbstractSet {
        private KeySet() {
        }

        /* synthetic */ KeySet(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new KeyIterator(StaticBucketMap.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return StaticBucketMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int hash = StaticBucketMap.this.getHash(obj);
            synchronized (StaticBucketMap.this.m_locks[hash]) {
                for (Node node = StaticBucketMap.this.m_buckets[hash]; node != null; node = node.next) {
                    Object key = node.getKey();
                    if (key != obj && (key == null || !key.equals(obj))) {
                    }
                    StaticBucketMap.this.remove(key);
                    return true;
                }
                return false;
            }
        }
    }

    private class Values extends AbstractCollection {
        private Values() {
        }

        /* synthetic */ Values(StaticBucketMap staticBucketMap, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return StaticBucketMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            StaticBucketMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new ValueIterator(StaticBucketMap.this, null);
        }
    }
}
