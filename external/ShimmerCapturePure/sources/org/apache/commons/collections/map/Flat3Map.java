package org.apache.commons.collections.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.ResettableIterator;
import org.apache.commons.collections.iterators.EmptyIterator;
import org.apache.commons.collections.iterators.EmptyMapIterator;

/* loaded from: classes5.dex */
public class Flat3Map implements IterableMap, Serializable, Cloneable {
    private static final long serialVersionUID = -6701087419741928296L;
    private transient AbstractHashedMap delegateMap;
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    private transient Object key1;
    private transient Object key2;
    private transient Object key3;
    private transient int size;
    private transient Object value1;
    private transient Object value2;
    private transient Object value3;

    public Flat3Map() {
    }

    public Flat3Map(Map map) {
        putAll(map);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.get(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    if (this.key3 == null) {
                        return this.value3;
                    }
                }
                if (this.key2 == null) {
                    return this.value2;
                }
            }
            if (this.key1 == null) {
                return this.value1;
            }
            return null;
        }
        if (this.size <= 0) {
            return null;
        }
        int iHashCode = obj.hashCode();
        int i2 = this.size;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return null;
                }
                if (this.hash3 == iHashCode && obj.equals(this.key3)) {
                    return this.value3;
                }
            }
            if (this.hash2 == iHashCode && obj.equals(this.key2)) {
                return this.value2;
            }
        }
        if (this.hash1 == iHashCode && obj.equals(this.key1)) {
            return this.value1;
        }
        return null;
    }

    @Override // java.util.Map
    public int size() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        return abstractHashedMap != null ? abstractHashedMap.size() : this.size;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsKey(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.key3 == null) {
                        return true;
                    }
                }
                if (this.key2 == null) {
                    return true;
                }
            }
            return this.key1 == null;
        }
        if (this.size <= 0) {
            return false;
        }
        int iHashCode = obj.hashCode();
        int i2 = this.size;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                if (this.hash3 == iHashCode && obj.equals(this.key3)) {
                    return true;
                }
            }
            if (this.hash2 == iHashCode && obj.equals(this.key2)) {
                return true;
            }
        }
        return this.hash1 == iHashCode && obj.equals(this.key1);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsValue(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.value3 == null) {
                        return true;
                    }
                }
                if (this.value2 == null) {
                    return true;
                }
            }
            return this.value1 == null;
        }
        int i2 = this.size;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                if (obj.equals(this.value3)) {
                    return true;
                }
            }
            if (obj.equals(this.value2)) {
                return true;
            }
        }
        return obj.equals(this.value1);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x002d  */
    @Override // java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object put(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            org.apache.commons.collections.map.AbstractHashedMap r0 = r5.delegateMap
            if (r0 == 0) goto L9
            java.lang.Object r6 = r0.put(r6, r7)
            return r6
        L9:
            r0 = 3
            r1 = 2
            r2 = 1
            if (r6 != 0) goto L32
            int r3 = r5.size
            if (r3 == r2) goto L29
            if (r3 == r1) goto L20
            if (r3 == r0) goto L17
            goto L76
        L17:
            java.lang.Object r0 = r5.key3
            if (r0 != 0) goto L20
            java.lang.Object r6 = r5.value3
            r5.value3 = r7
            return r6
        L20:
            java.lang.Object r0 = r5.key2
            if (r0 != 0) goto L29
            java.lang.Object r6 = r5.value2
            r5.value2 = r7
            return r6
        L29:
            java.lang.Object r0 = r5.key1
            if (r0 != 0) goto L76
            java.lang.Object r6 = r5.value1
            r5.value1 = r7
            return r6
        L32:
            int r3 = r5.size
            if (r3 <= 0) goto L76
            int r3 = r6.hashCode()
            int r4 = r5.size
            if (r4 == r2) goto L65
            if (r4 == r1) goto L54
            if (r4 == r0) goto L43
            goto L76
        L43:
            int r0 = r5.hash3
            if (r0 != r3) goto L54
            java.lang.Object r0 = r5.key3
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L54
            java.lang.Object r6 = r5.value3
            r5.value3 = r7
            return r6
        L54:
            int r0 = r5.hash2
            if (r0 != r3) goto L65
            java.lang.Object r0 = r5.key2
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L65
            java.lang.Object r6 = r5.value2
            r5.value2 = r7
            return r6
        L65:
            int r0 = r5.hash1
            if (r0 != r3) goto L76
            java.lang.Object r0 = r5.key1
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L76
            java.lang.Object r6 = r5.value1
            r5.value1 = r7
            return r6
        L76:
            int r0 = r5.size
            r3 = 0
            r4 = 0
            if (r0 == 0) goto La5
            if (r0 == r2) goto L97
            if (r0 == r1) goto L89
            r5.convertToMap()
            org.apache.commons.collections.map.AbstractHashedMap r0 = r5.delegateMap
            r0.put(r6, r7)
            return r3
        L89:
            if (r6 != 0) goto L8c
            goto L90
        L8c:
            int r4 = r6.hashCode()
        L90:
            r5.hash3 = r4
            r5.key3 = r6
            r5.value3 = r7
            goto Lb2
        L97:
            if (r6 != 0) goto L9a
            goto L9e
        L9a:
            int r4 = r6.hashCode()
        L9e:
            r5.hash2 = r4
            r5.key2 = r6
            r5.value2 = r7
            goto Lb2
        La5:
            if (r6 != 0) goto La8
            goto Lac
        La8:
            int r4 = r6.hashCode()
        Lac:
            r5.hash1 = r4
            r5.key1 = r6
            r5.value1 = r7
        Lb2:
            int r6 = r5.size
            int r6 = r6 + r2
            r5.size = r6
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.Flat3Map.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            abstractHashedMap.putAll(map);
            return;
        }
        if (size < 4) {
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        convertToMap();
        this.delegateMap.putAll(map);
    }

    private void convertToMap() {
        AbstractHashedMap abstractHashedMapCreateDelegateMap = createDelegateMap();
        this.delegateMap = abstractHashedMapCreateDelegateMap;
        int i = this.size;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    abstractHashedMapCreateDelegateMap.put(this.key3, this.value3);
                }
            }
            this.delegateMap.put(this.key2, this.value2);
            this.delegateMap.put(this.key1, this.value1);
        } else {
            this.delegateMap.put(this.key1, this.value1);
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    protected AbstractHashedMap createDelegateMap() {
        return new HashedMap();
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.remove(obj);
        }
        int i = this.size;
        if (i == 0) {
            return null;
        }
        if (obj == null) {
            if (i != 1) {
                if (i == 2) {
                    Object obj2 = this.key2;
                    if (obj2 == null) {
                        Object obj3 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return obj3;
                    }
                    if (this.key1 != null) {
                        return null;
                    }
                    Object obj4 = this.value2;
                    this.hash1 = this.hash2;
                    this.key1 = obj2;
                    this.value1 = obj4;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return obj4;
                }
                if (i == 3) {
                    Object obj5 = this.key3;
                    if (obj5 == null) {
                        Object obj6 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return obj6;
                    }
                    if (this.key2 == null) {
                        Object obj7 = this.value3;
                        this.hash2 = this.hash3;
                        this.key2 = obj5;
                        this.value2 = obj7;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return obj7;
                    }
                    if (this.key1 != null) {
                        return null;
                    }
                    Object obj8 = this.value3;
                    this.hash1 = this.hash3;
                    this.key1 = obj5;
                    this.value1 = obj8;
                    this.hash3 = 0;
                    this.key3 = null;
                    this.value3 = null;
                    this.size = 2;
                    return obj8;
                }
            } else if (this.key1 == null) {
                Object obj9 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return obj9;
            }
        } else if (i > 0) {
            int iHashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 == 2) {
                    if (this.hash2 == iHashCode && obj.equals(this.key2)) {
                        Object obj10 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return obj10;
                    }
                    if (this.hash1 != iHashCode || !obj.equals(this.key1)) {
                        return null;
                    }
                    Object obj11 = this.value2;
                    this.hash1 = this.hash2;
                    this.key1 = this.key2;
                    this.value1 = obj11;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return obj11;
                }
                if (i2 == 3) {
                    if (this.hash3 == iHashCode && obj.equals(this.key3)) {
                        Object obj12 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return obj12;
                    }
                    if (this.hash2 == iHashCode && obj.equals(this.key2)) {
                        Object obj13 = this.value3;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = obj13;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return obj13;
                    }
                    if (this.hash1 != iHashCode || !obj.equals(this.key1)) {
                        return null;
                    }
                    Object obj14 = this.value3;
                    this.hash1 = this.hash3;
                    this.key1 = this.key3;
                    this.value1 = obj14;
                    this.hash3 = 0;
                    this.key3 = null;
                    this.value3 = null;
                    this.size = 2;
                    return obj14;
                }
            } else if (this.hash1 == iHashCode && obj.equals(this.key1)) {
                Object obj15 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return obj15;
            }
        }
        return null;
    }

    @Override // java.util.Map
    public void clear() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            abstractHashedMap.clear();
            this.delegateMap = null;
            return;
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    @Override // org.apache.commons.collections.IterableMap
    public MapIterator mapIterator() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mapIterator();
        }
        if (this.size == 0) {
            return EmptyMapIterator.INSTANCE;
        }
        return new FlatMapIterator(this);
    }

    @Override // java.util.Map
    public Set entrySet() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.entrySet();
        }
        return new EntrySet(this);
    }

    @Override // java.util.Map
    public Set keySet() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.keySet();
        }
        return new KeySet(this);
    }

    @Override // java.util.Map
    public Collection values() {
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.values();
        }
        return new Values(this);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        if (i > 3) {
            this.delegateMap = createDelegateMap();
        }
        while (i > 0) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
            i--;
        }
    }

    public Object clone() {
        try {
            Flat3Map flat3Map = (Flat3Map) super.clone();
            AbstractHashedMap abstractHashedMap = flat3Map.delegateMap;
            if (abstractHashedMap != null) {
                flat3Map.delegateMap = (HashedMap) abstractHashedMap.clone();
            }
            return flat3Map;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x006d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006e  */
    @Override // java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L4
            return r0
        L4:
            org.apache.commons.collections.map.AbstractHashedMap r1 = r4.delegateMap
            if (r1 == 0) goto Ld
            boolean r5 = r1.equals(r5)
            return r5
        Ld:
            boolean r1 = r5 instanceof java.util.Map
            r2 = 0
            if (r1 != 0) goto L13
            return r2
        L13:
            java.util.Map r5 = (java.util.Map) r5
            int r1 = r4.size
            int r3 = r5.size()
            if (r1 == r3) goto L1e
            return r2
        L1e:
            int r1 = r4.size
            if (r1 <= 0) goto L82
            if (r1 == r0) goto L65
            r3 = 2
            if (r1 == r3) goto L48
            r3 = 3
            if (r1 == r3) goto L2b
            goto L82
        L2b:
            java.lang.Object r1 = r4.key3
            boolean r1 = r5.containsKey(r1)
            if (r1 != 0) goto L34
            return r2
        L34:
            java.lang.Object r1 = r4.key3
            java.lang.Object r1 = r5.get(r1)
            java.lang.Object r3 = r4.value3
            if (r3 != 0) goto L41
            if (r1 == 0) goto L48
            goto L47
        L41:
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L48
        L47:
            return r2
        L48:
            java.lang.Object r1 = r4.key2
            boolean r1 = r5.containsKey(r1)
            if (r1 != 0) goto L51
            return r2
        L51:
            java.lang.Object r1 = r4.key2
            java.lang.Object r1 = r5.get(r1)
            java.lang.Object r3 = r4.value2
            if (r3 != 0) goto L5e
            if (r1 == 0) goto L65
            goto L64
        L5e:
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L65
        L64:
            return r2
        L65:
            java.lang.Object r1 = r4.key1
            boolean r1 = r5.containsKey(r1)
            if (r1 != 0) goto L6e
            return r2
        L6e:
            java.lang.Object r1 = r4.key1
            java.lang.Object r5 = r5.get(r1)
            java.lang.Object r1 = r4.value1
            if (r1 != 0) goto L7b
            if (r5 == 0) goto L82
            goto L81
        L7b:
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L82
        L81:
            return r2
        L82:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.Flat3Map.equals(java.lang.Object):boolean");
    }

    @Override // java.util.Map
    public int hashCode() {
        int iHashCode;
        int iHashCode2;
        AbstractHashedMap abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.hashCode();
        }
        int i = this.size;
        if (i != 1) {
            if (i == 2) {
                iHashCode2 = 0;
            } else {
                if (i != 3) {
                    return 0;
                }
                int i2 = this.hash3;
                Object obj = this.value3;
                iHashCode2 = i2 ^ (obj == null ? 0 : obj.hashCode());
            }
            int i3 = this.hash2;
            Object obj2 = this.value2;
            iHashCode = iHashCode2 + (i3 ^ (obj2 == null ? 0 : obj2.hashCode()));
        } else {
            iHashCode = 0;
        }
        int i4 = this.hash1;
        Object obj3 = this.value1;
        return iHashCode + (i4 ^ (obj3 != null ? obj3.hashCode() : 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r6 = this;
            org.apache.commons.collections.map.AbstractHashedMap r0 = r6.delegateMap
            if (r0 == 0) goto L9
            java.lang.String r0 = r0.toString()
            return r0
        L9:
            int r0 = r6.size
            if (r0 != 0) goto L10
            java.lang.String r0 = "{}"
            return r0
        L10:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 128(0x80, float:1.8E-43)
            r0.<init>(r1)
            r1 = 123(0x7b, float:1.72E-43)
            r0.append(r1)
            int r1 = r6.size
            r2 = 1
            r3 = 61
            java.lang.String r4 = "(this Map)"
            if (r1 == r2) goto L5a
            r2 = 2
            r5 = 44
            if (r1 == r2) goto L44
            r2 = 3
            if (r1 == r2) goto L2e
            goto L6e
        L2e:
            java.lang.Object r1 = r6.key3
            if (r1 != r6) goto L33
            r1 = r4
        L33:
            r0.append(r1)
            r0.append(r3)
            java.lang.Object r1 = r6.value3
            if (r1 != r6) goto L3e
            r1 = r4
        L3e:
            r0.append(r1)
            r0.append(r5)
        L44:
            java.lang.Object r1 = r6.key2
            if (r1 != r6) goto L49
            r1 = r4
        L49:
            r0.append(r1)
            r0.append(r3)
            java.lang.Object r1 = r6.value2
            if (r1 != r6) goto L54
            r1 = r4
        L54:
            r0.append(r1)
            r0.append(r5)
        L5a:
            java.lang.Object r1 = r6.key1
            if (r1 != r6) goto L5f
            r1 = r4
        L5f:
            r0.append(r1)
            r0.append(r3)
            java.lang.Object r1 = r6.value1
            if (r1 != r6) goto L6a
            goto L6b
        L6a:
            r4 = r1
        L6b:
            r0.append(r4)
        L6e:
            r1 = 125(0x7d, float:1.75E-43)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.Flat3Map.toString():java.lang.String");
    }

    static class FlatMapIterator implements MapIterator, ResettableIterator {
        private final Flat3Map parent;
        private int nextIndex = 0;
        private boolean canRemove = false;

        FlatMapIterator(Flat3Map flat3Map) {
            this.parent = flat3Map;
        }

        @Override // org.apache.commons.collections.ResettableIterator
        public void reset() {
            this.nextIndex = 0;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.parent.size;
        }

        @Override // org.apache.commons.collections.MapIterator, java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.canRemove = true;
            this.nextIndex++;
            return getKey();
        }

        @Override // org.apache.commons.collections.MapIterator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.parent.remove(getKey());
            this.nextIndex--;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object getKey() {
            if (!this.canRemove) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            int i = this.nextIndex;
            if (i == 1) {
                return this.parent.key1;
            }
            if (i == 2) {
                return this.parent.key2;
            }
            if (i == 3) {
                return this.parent.key3;
            }
            throw new IllegalStateException("Invalid map index");
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object getValue() {
            if (!this.canRemove) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            int i = this.nextIndex;
            if (i == 1) {
                return this.parent.value1;
            }
            if (i == 2) {
                return this.parent.value2;
            }
            if (i == 3) {
                return this.parent.value3;
            }
            throw new IllegalStateException("Invalid map index");
        }

        @Override // org.apache.commons.collections.MapIterator
        public Object setValue(Object obj) {
            if (!this.canRemove) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            Object value = getValue();
            int i = this.nextIndex;
            if (i == 1) {
                this.parent.value1 = obj;
            } else {
                if (i != 2) {
                    if (i == 3) {
                        this.parent.value3 = obj;
                    }
                }
                this.parent.value2 = obj;
                this.parent.value1 = obj;
            }
            return value;
        }

        public String toString() {
            return this.canRemove ? new StringBuffer("Iterator[").append(getKey()).append("=").append(getValue()).append("]").toString() : "Iterator[]";
        }
    }

    static class EntrySet extends AbstractSet {
        private final Flat3Map parent;

        EntrySet(Flat3Map flat3Map) {
            this.parent = flat3Map;
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
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Object key = ((Map.Entry) obj).getKey();
            boolean zContainsKey = this.parent.containsKey(key);
            this.parent.remove(key);
            return zContainsKey;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.entrySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.INSTANCE;
            }
            return new EntrySetIterator(this.parent);
        }
    }

    static class EntrySetIterator implements Iterator, Map.Entry {
        private final Flat3Map parent;
        private int nextIndex = 0;
        private boolean canRemove = false;

        EntrySetIterator(Flat3Map flat3Map) {
            this.parent = flat3Map;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.parent.size;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.canRemove = true;
            this.nextIndex++;
            return this;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.parent.remove(getKey());
            this.nextIndex--;
            this.canRemove = false;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            if (!this.canRemove) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            int i = this.nextIndex;
            if (i == 1) {
                return this.parent.key1;
            }
            if (i == 2) {
                return this.parent.key2;
            }
            if (i == 3) {
                return this.parent.key3;
            }
            throw new IllegalStateException("Invalid map index");
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            if (!this.canRemove) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            int i = this.nextIndex;
            if (i == 1) {
                return this.parent.value1;
            }
            if (i == 2) {
                return this.parent.value2;
            }
            if (i == 3) {
                return this.parent.value3;
            }
            throw new IllegalStateException("Invalid map index");
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (!this.canRemove) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            Object value = getValue();
            int i = this.nextIndex;
            if (i == 1) {
                this.parent.value1 = obj;
            } else {
                if (i != 2) {
                    if (i == 3) {
                        this.parent.value3 = obj;
                    }
                }
                this.parent.value2 = obj;
                this.parent.value1 = obj;
            }
            return value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.canRemove || !(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = getKey();
            Object value = getValue();
            if (key == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!key.equals(entry.getKey())) {
                return false;
            }
            Object value2 = entry.getValue();
            if (value == null) {
                if (value2 != null) {
                    return false;
                }
            } else if (!value.equals(value2)) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.canRemove) {
                return 0;
            }
            Object key = getKey();
            Object value = getValue();
            return (key == null ? 0 : key.hashCode()) ^ (value != null ? value.hashCode() : 0);
        }

        public String toString() {
            return this.canRemove ? new StringBuffer().append(getKey()).append("=").append(getValue()).toString() : "";
        }
    }

    static class KeySet extends AbstractSet {
        private final Flat3Map parent;

        KeySet(Flat3Map flat3Map) {
            this.parent = flat3Map;
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
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.keySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.INSTANCE;
            }
            return new KeySetIterator(this.parent);
        }
    }

    static class KeySetIterator extends EntrySetIterator {
        KeySetIterator(Flat3Map flat3Map) {
            super(flat3Map);
        }

        @Override // org.apache.commons.collections.map.Flat3Map.EntrySetIterator, java.util.Iterator
        public Object next() {
            super.next();
            return getKey();
        }
    }

    static class Values extends AbstractCollection {
        private final Flat3Map parent;

        Values(Flat3Map flat3Map) {
            this.parent = flat3Map;
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
            if (this.parent.delegateMap != null) {
                return this.parent.delegateMap.values().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.INSTANCE;
            }
            return new ValuesIterator(this.parent);
        }
    }

    static class ValuesIterator extends EntrySetIterator {
        ValuesIterator(Flat3Map flat3Map) {
            super(flat3Map);
        }

        @Override // org.apache.commons.collections.map.Flat3Map.EntrySetIterator, java.util.Iterator
        public Object next() {
            super.next();
            return getValue();
        }
    }
}
