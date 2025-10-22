package org.apache.commons.collections.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.iterators.EmptyIterator;
import org.apache.commons.collections.iterators.EmptyMapIterator;

/* loaded from: classes5.dex */
public class AbstractHashedMap extends AbstractMap implements IterableMap {
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int DEFAULT_THRESHOLD = 12;
    protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
    protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
    protected static final int MAXIMUM_CAPACITY = 1073741824;
    protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
    protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
    protected static final Object NULL = new Object();
    protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
    protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
    protected transient HashEntry[] data;
    protected transient EntrySet entrySet;
    protected transient KeySet keySet;
    protected transient float loadFactor;
    protected transient int modCount;
    protected transient int size;
    protected transient int threshold;
    protected transient Values values;

    protected AbstractHashedMap() {
    }

    protected AbstractHashedMap(int i, float f, int i2) {
        this.loadFactor = f;
        this.data = new HashEntry[i];
        this.threshold = i2;
        init();
    }

    protected AbstractHashedMap(int i) {
        this(i, DEFAULT_LOAD_FACTOR);
    }

    protected AbstractHashedMap(int i, float f) {
        if (i < 1) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Load factor must be greater than 0");
        }
        this.loadFactor = f;
        int iCalculateNewCapacity = calculateNewCapacity(i);
        this.threshold = calculateThreshold(iCalculateNewCapacity, f);
        this.data = new HashEntry[iCalculateNewCapacity];
        init();
    }

    protected AbstractHashedMap(Map map) {
        this(Math.max(map.size() * 2, 16), DEFAULT_LOAD_FACTOR);
        putAll(map);
    }

    protected int calculateNewCapacity(int i) {
        if (i > 1073741824) {
            return 1073741824;
        }
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        if (i2 > 1073741824) {
            return 1073741824;
        }
        return i2;
    }

    protected int calculateThreshold(int i, float f) {
        return (int) (i * f);
    }

    protected Object convertKey(Object obj) {
        return obj == null ? NULL : obj;
    }

    protected int hashIndex(int i, int i2) {
        return i & (i2 - 1);
    }

    protected void init() {
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Object objConvertKey = convertKey(obj);
        int iHash = hash(objConvertKey);
        HashEntry[] hashEntryArr = this.data;
        for (HashEntry hashEntry = hashEntryArr[hashIndex(iHash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(objConvertKey, hashEntry.key)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Object objConvertKey = convertKey(obj);
        int iHash = hash(objConvertKey);
        HashEntry[] hashEntryArr = this.data;
        for (HashEntry hashEntry = hashEntryArr[hashIndex(iHash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(objConvertKey, hashEntry.key)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        if (obj == null) {
            int length = this.data.length;
            for (int i = 0; i < length; i++) {
                for (HashEntry hashEntry = this.data[i]; hashEntry != null; hashEntry = hashEntry.next) {
                    if (hashEntry.getValue() == null) {
                        return true;
                    }
                }
            }
        } else {
            int length2 = this.data.length;
            for (int i2 = 0; i2 < length2; i2++) {
                for (HashEntry hashEntry2 = this.data[i2]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
                    if (isEqualValue(obj, hashEntry2.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        Object objConvertKey = convertKey(obj);
        int iHash = hash(objConvertKey);
        int iHashIndex = hashIndex(iHash, this.data.length);
        for (HashEntry hashEntry = this.data[iHashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(objConvertKey, hashEntry.key)) {
                Object value = hashEntry.getValue();
                updateEntry(hashEntry, obj2);
                return value;
            }
        }
        addMapping(iHashIndex, iHash, objConvertKey, obj2);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        if (map.size() == 0) {
            return;
        }
        ensureCapacity(calculateNewCapacity((int) (((this.size + r0) / this.loadFactor) + 1.0f)));
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object objConvertKey = convertKey(obj);
        int iHash = hash(objConvertKey);
        int iHashIndex = hashIndex(iHash, this.data.length);
        HashEntry hashEntry = null;
        for (HashEntry hashEntry2 = this.data[iHashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == iHash && isEqualKey(objConvertKey, hashEntry2.key)) {
                Object value = hashEntry2.getValue();
                removeMapping(hashEntry2, iHashIndex, hashEntry);
                return value;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        HashEntry[] hashEntryArr = this.data;
        for (int length = hashEntryArr.length - 1; length >= 0; length--) {
            hashEntryArr[length] = null;
        }
        this.size = 0;
    }

    protected int hash(Object obj) {
        int iHashCode = obj.hashCode();
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    protected boolean isEqualKey(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    protected boolean isEqualValue(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    protected HashEntry getEntry(Object obj) {
        Object objConvertKey = convertKey(obj);
        int iHash = hash(objConvertKey);
        HashEntry[] hashEntryArr = this.data;
        for (HashEntry hashEntry = hashEntryArr[hashIndex(iHash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(objConvertKey, hashEntry.key)) {
                return hashEntry;
            }
        }
        return null;
    }

    protected void updateEntry(HashEntry hashEntry, Object obj) {
        hashEntry.setValue(obj);
    }

    protected void reuseEntry(HashEntry hashEntry, int i, int i2, Object obj, Object obj2) {
        hashEntry.next = this.data[i];
        hashEntry.hashCode = i2;
        hashEntry.key = obj;
        hashEntry.value = obj2;
    }

    protected void addMapping(int i, int i2, Object obj, Object obj2) {
        this.modCount++;
        addEntry(createEntry(this.data[i], i2, obj, obj2), i);
        this.size++;
        checkCapacity();
    }

    protected HashEntry createEntry(HashEntry hashEntry, int i, Object obj, Object obj2) {
        return new HashEntry(hashEntry, i, obj, obj2);
    }

    protected void addEntry(HashEntry hashEntry, int i) {
        this.data[i] = hashEntry;
    }

    protected void removeMapping(HashEntry hashEntry, int i, HashEntry hashEntry2) {
        this.modCount++;
        removeEntry(hashEntry, i, hashEntry2);
        this.size--;
        destroyEntry(hashEntry);
    }

    protected void removeEntry(HashEntry hashEntry, int i, HashEntry hashEntry2) {
        if (hashEntry2 == null) {
            this.data[i] = hashEntry.next;
        } else {
            hashEntry2.next = hashEntry.next;
        }
    }

    protected void destroyEntry(HashEntry hashEntry) {
        hashEntry.next = null;
        hashEntry.key = null;
        hashEntry.value = null;
    }

    protected void checkCapacity() {
        int length;
        if (this.size < this.threshold || (length = this.data.length * 2) > 1073741824) {
            return;
        }
        ensureCapacity(length);
    }

    protected void ensureCapacity(int i) {
        HashEntry[] hashEntryArr = this.data;
        int length = hashEntryArr.length;
        if (i <= length) {
            return;
        }
        if (this.size == 0) {
            this.threshold = calculateThreshold(i, this.loadFactor);
            this.data = new HashEntry[i];
            return;
        }
        HashEntry[] hashEntryArr2 = new HashEntry[i];
        this.modCount++;
        for (int i2 = length - 1; i2 >= 0; i2--) {
            HashEntry hashEntry = hashEntryArr[i2];
            if (hashEntry != null) {
                hashEntryArr[i2] = null;
                while (true) {
                    HashEntry hashEntry2 = hashEntry.next;
                    int iHashIndex = hashIndex(hashEntry.hashCode, i);
                    hashEntry.next = hashEntryArr2[iHashIndex];
                    hashEntryArr2[iHashIndex] = hashEntry;
                    if (hashEntry2 == null) {
                        break;
                    } else {
                        hashEntry = hashEntry2;
                    }
                }
            }
        }
        this.threshold = calculateThreshold(i, this.loadFactor);
        this.data = hashEntryArr2;
    }

    protected HashEntry entryNext(HashEntry hashEntry) {
        return hashEntry.next;
    }

    protected int entryHashCode(HashEntry hashEntry) {
        return hashEntry.hashCode;
    }

    protected Object entryKey(HashEntry hashEntry) {
        return hashEntry.key;
    }

    protected Object entryValue(HashEntry hashEntry) {
        return hashEntry.value;
    }

    @Override // org.apache.commons.collections.IterableMap
    public MapIterator mapIterator() {
        if (this.size == 0) {
            return EmptyMapIterator.INSTANCE;
        }
        return new HashMapIterator(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    protected Iterator createEntrySetIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new EntrySetIterator(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    protected Iterator createKeySetIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new KeySetIterator(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }

    protected Iterator createValuesIterator() {
        if (size() == 0) {
            return EmptyIterator.INSTANCE;
        }
        return new ValuesIterator(this);
    }

    protected void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.writeInt(this.size);
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
    }

    protected void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.loadFactor = objectInputStream.readFloat();
        int i = objectInputStream.readInt();
        int i2 = objectInputStream.readInt();
        init();
        this.threshold = calculateThreshold(i, this.loadFactor);
        this.data = new HashEntry[i];
        for (int i3 = 0; i3 < i2; i3++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    @Override // java.util.AbstractMap
    protected Object clone() {
        try {
            AbstractHashedMap abstractHashedMap = (AbstractHashedMap) super.clone();
            abstractHashedMap.data = new HashEntry[this.data.length];
            abstractHashedMap.entrySet = null;
            abstractHashedMap.keySet = null;
            abstractHashedMap.values = null;
            abstractHashedMap.modCount = 0;
            abstractHashedMap.size = 0;
            abstractHashedMap.init();
            abstractHashedMap.putAll(this);
            return abstractHashedMap;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        Object next;
        Object value;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            try {
                next = mapIterator.next();
                value = mapIterator.getValue();
            } catch (ClassCastException | NullPointerException unused) {
            }
            if (value == null) {
                if (map.get(next) != null || !map.containsKey(next)) {
                    return false;
                }
            } else if (!value.equals(map.get(next))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        Iterator itCreateEntrySetIterator = createEntrySetIterator();
        int iHashCode = 0;
        while (itCreateEntrySetIterator.hasNext()) {
            iHashCode += itCreateEntrySetIterator.next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        if (size() == 0) {
            return "{}";
        }
        StringBuffer stringBuffer = new StringBuffer(size() * 32);
        stringBuffer.append('{');
        MapIterator mapIterator = mapIterator();
        boolean zHasNext = mapIterator.hasNext();
        while (zHasNext) {
            Object next = mapIterator.next();
            Object value = mapIterator.getValue();
            if (next == this) {
                next = "(this Map)";
            }
            StringBuffer stringBufferAppend = stringBuffer.append(next).append('=');
            if (value == this) {
                value = "(this Map)";
            }
            stringBufferAppend.append(value);
            zHasNext = mapIterator.hasNext();
            if (zHasNext) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    protected static class HashMapIterator extends HashIterator implements MapIterator {
        protected HashMapIterator(AbstractHashedMap abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // org.apache.commons.collections.map.AbstractHashedMap.HashIterator, java.util.Iterator
        public Object next() {
            return super.nextEntry().getKey();
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object getKey() {
            HashEntry hashEntryCurrentEntry = currentEntry();
            if (hashEntryCurrentEntry == null) {
                throw new IllegalStateException(AbstractHashedMap.GETKEY_INVALID);
            }
            return hashEntryCurrentEntry.getKey();
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object getValue() {
            HashEntry hashEntryCurrentEntry = currentEntry();
            if (hashEntryCurrentEntry == null) {
                throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
            }
            return hashEntryCurrentEntry.getValue();
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object setValue(Object obj) {
            HashEntry hashEntryCurrentEntry = currentEntry();
            if (hashEntryCurrentEntry == null) {
                throw new IllegalStateException(AbstractHashedMap.SETVALUE_INVALID);
            }
            return hashEntryCurrentEntry.setValue(obj);
        }
    }

    protected static class EntrySet extends AbstractSet {
        protected final AbstractHashedMap parent;

        protected EntrySet(AbstractHashedMap abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            HashEntry entry2 = this.parent.getEntry(entry.getKey());
            return entry2 != null && entry2.equals(entry);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry) || !contains(obj)) {
                return false;
            }
            this.parent.remove(((Map.Entry) obj).getKey());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return this.parent.createEntrySetIterator();
        }
    }

    protected static class EntrySetIterator extends HashIterator {
        protected EntrySetIterator(AbstractHashedMap abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // org.apache.commons.collections.map.AbstractHashedMap.HashIterator, java.util.Iterator
        public Object next() {
            return super.nextEntry();
        }
    }

    protected static class KeySet extends AbstractSet {
        protected final AbstractHashedMap parent;

        protected KeySet(AbstractHashedMap abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean zContainsKey = this.parent.containsKey(obj);
            this.parent.remove(obj);
            return zContainsKey;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return this.parent.createKeySetIterator();
        }
    }

    protected static class KeySetIterator extends EntrySetIterator {
        protected KeySetIterator(AbstractHashedMap abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override
        // org.apache.commons.collections.map.AbstractHashedMap.EntrySetIterator, org.apache.commons.collections.map.AbstractHashedMap.HashIterator, java.util.Iterator
        public Object next() {
            return super.nextEntry().getKey();
        }
    }

    protected static class Values extends AbstractCollection {
        protected final AbstractHashedMap parent;

        protected Values(AbstractHashedMap abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.parent.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return this.parent.createValuesIterator();
        }
    }

    protected static class ValuesIterator extends HashIterator {
        protected ValuesIterator(AbstractHashedMap abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // org.apache.commons.collections.map.AbstractHashedMap.HashIterator, java.util.Iterator
        public Object next() {
            return super.nextEntry().getValue();
        }
    }

    protected static class HashEntry implements Map.Entry, KeyValue {
        protected int hashCode;
        protected Object key;
        protected HashEntry next;
        protected Object value;

        protected HashEntry(HashEntry hashEntry, int i, Object obj, Object obj2) {
            this.next = hashEntry;
            this.hashCode = i;
            this.key = obj;
            this.value = obj2;
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

        @Override // java.util.Map.Entry, org.apache.commons.collections.KeyValue
        public Object getKey() {
            if (this.key == AbstractHashedMap.NULL) {
                return null;
            }
            return this.key;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
                if (getValue() == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (getValue().equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() != null ? getValue().hashCode() : 0);
        }

        public String toString() {
            return new StringBuffer().append(getKey()).append('=').append(getValue()).toString();
        }
    }

    protected static abstract class HashIterator implements Iterator {
        protected final AbstractHashedMap parent;
        protected int expectedModCount;
        protected int hashIndex;
        protected HashEntry last;
        protected HashEntry next;

        protected HashIterator(AbstractHashedMap abstractHashedMap) {
            this.parent = abstractHashedMap;
            HashEntry[] hashEntryArr = abstractHashedMap.data;
            int length = hashEntryArr.length;
            HashEntry hashEntry = null;
            while (length > 0 && hashEntry == null) {
                length--;
                hashEntry = hashEntryArr[length];
            }
            this.next = hashEntry;
            this.hashIndex = length;
            this.expectedModCount = abstractHashedMap.modCount;
        }

        protected HashEntry currentEntry() {
            return this.last;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public abstract Object next();

        protected HashEntry nextEntry() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            HashEntry hashEntry = this.next;
            if (hashEntry == null) {
                throw new NoSuchElementException(AbstractHashedMap.NO_NEXT_ENTRY);
            }
            HashEntry[] hashEntryArr = this.parent.data;
            int i = this.hashIndex;
            HashEntry hashEntry2 = hashEntry.next;
            while (hashEntry2 == null && i > 0) {
                i--;
                hashEntry2 = hashEntryArr[i];
            }
            this.next = hashEntry2;
            this.hashIndex = i;
            this.last = hashEntry;
            return hashEntry;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException(AbstractHashedMap.REMOVE_INVALID);
            }
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.parent.remove(this.last.getKey());
            this.last = null;
            this.expectedModCount = this.parent.modCount;
        }

        public String toString() {
            return this.last != null ? new StringBuffer("Iterator[").append(this.last.getKey()).append("=").append(this.last.getValue()).append("]").toString() : "Iterator[]";
        }
    }
}
