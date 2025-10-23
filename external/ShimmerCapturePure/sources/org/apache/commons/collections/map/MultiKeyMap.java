package org.apache.commons.collections.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.AbstractHashedMap;

/* loaded from: classes5.dex */
public class MultiKeyMap implements IterableMap, Serializable {
    private static final long serialVersionUID = -1788199231038721040L;
    protected final AbstractHashedMap map;

    public MultiKeyMap() {
        this.map = new HashedMap();
    }

    protected MultiKeyMap(AbstractHashedMap abstractHashedMap) {
        this.map = abstractHashedMap;
    }

    public static MultiKeyMap decorate(AbstractHashedMap abstractHashedMap) {
        if (abstractHashedMap == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        if (abstractHashedMap.size() > 0) {
            throw new IllegalArgumentException("Map must be empty");
        }
        return new MultiKeyMap(abstractHashedMap);
    }

    public Object get(Object obj, Object obj2) {
        int iHash = hash(obj, obj2);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2) {
        int iHash = hash(obj, obj2);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2)) {
                return true;
            }
        }
        return false;
    }

    public Object put(Object obj, Object obj2, Object obj3) {
        int iHash = hash(obj, obj2);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        for (AbstractHashedMap.HashEntry hashEntry = this.map.data[iHashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2)) {
                Object value = hashEntry.getValue();
                this.map.updateEntry(hashEntry, obj3);
                return value;
            }
        }
        this.map.addMapping(iHashIndex, iHash, new MultiKey(obj, obj2), obj3);
        return null;
    }

    @Override // java.util.Map
    public Object remove(Object obj, Object obj2) {
        int iHash = hash(obj, obj2);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        AbstractHashedMap.HashEntry hashEntry = null;
        for (AbstractHashedMap.HashEntry hashEntry2 = this.map.data[iHashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == iHash && isEqualKey(hashEntry2, obj, obj2)) {
                Object value = hashEntry2.getValue();
                this.map.removeMapping(hashEntry2, iHashIndex, hashEntry);
                return value;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    protected int hash(Object obj, Object obj2) {
        int iHashCode = obj != null ? obj.hashCode() : 0;
        if (obj2 != null) {
            iHashCode ^= obj2.hashCode();
        }
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry hashEntry, Object obj, Object obj2) {
        MultiKey multiKey = (MultiKey) hashEntry.getKey();
        if (multiKey.size() != 2) {
            return false;
        }
        if (obj == null) {
            if (multiKey.getKey(0) != null) {
                return false;
            }
        } else if (!obj.equals(multiKey.getKey(0))) {
            return false;
        }
        Object key = multiKey.getKey(1);
        if (obj2 == null) {
            if (key != null) {
                return false;
            }
        } else if (!obj2.equals(key)) {
            return false;
        }
        return true;
    }

    public Object get(Object obj, Object obj2, Object obj3) {
        int iHash = hash(obj, obj2, obj3);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3) {
        int iHash = hash(obj, obj2, obj3);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                return true;
            }
        }
        return false;
    }

    public Object put(Object obj, Object obj2, Object obj3, Object obj4) {
        int iHash = hash(obj, obj2, obj3);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        for (AbstractHashedMap.HashEntry hashEntry = this.map.data[iHashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3)) {
                Object value = hashEntry.getValue();
                this.map.updateEntry(hashEntry, obj4);
                return value;
            }
        }
        this.map.addMapping(iHashIndex, iHash, new MultiKey(obj, obj2, obj3), obj4);
        return null;
    }

    public Object remove(Object obj, Object obj2, Object obj3) {
        int iHash = hash(obj, obj2, obj3);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        AbstractHashedMap.HashEntry hashEntry = null;
        for (AbstractHashedMap.HashEntry hashEntry2 = this.map.data[iHashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == iHash && isEqualKey(hashEntry2, obj, obj2, obj3)) {
                Object value = hashEntry2.getValue();
                this.map.removeMapping(hashEntry2, iHashIndex, hashEntry);
                return value;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    protected int hash(Object obj, Object obj2, Object obj3) {
        int iHashCode = obj != null ? obj.hashCode() : 0;
        if (obj2 != null) {
            iHashCode ^= obj2.hashCode();
        }
        if (obj3 != null) {
            iHashCode ^= obj3.hashCode();
        }
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry hashEntry, Object obj, Object obj2, Object obj3) {
        MultiKey multiKey = (MultiKey) hashEntry.getKey();
        if (multiKey.size() != 3) {
            return false;
        }
        if (obj == null) {
            if (multiKey.getKey(0) != null) {
                return false;
            }
        } else if (!obj.equals(multiKey.getKey(0))) {
            return false;
        }
        if (obj2 == null) {
            if (multiKey.getKey(1) != null) {
                return false;
            }
        } else if (!obj2.equals(multiKey.getKey(1))) {
            return false;
        }
        Object key = multiKey.getKey(2);
        if (obj3 == null) {
            if (key != null) {
                return false;
            }
        } else if (!obj3.equals(key)) {
            return false;
        }
        return true;
    }

    public Object get(Object obj, Object obj2, Object obj3, Object obj4) {
        int iHash = hash(obj, obj2, obj3, obj4);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4) {
        int iHash = hash(obj, obj2, obj3, obj4);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                return true;
            }
        }
        return false;
    }

    public Object put(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int iHash = hash(obj, obj2, obj3, obj4);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        for (AbstractHashedMap.HashEntry hashEntry = this.map.data[iHashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4)) {
                Object value = hashEntry.getValue();
                this.map.updateEntry(hashEntry, obj5);
                return value;
            }
        }
        this.map.addMapping(iHashIndex, iHash, new MultiKey(obj, obj2, obj3, obj4), obj5);
        return null;
    }

    public Object remove(Object obj, Object obj2, Object obj3, Object obj4) {
        int iHash = hash(obj, obj2, obj3, obj4);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        AbstractHashedMap.HashEntry hashEntry = null;
        for (AbstractHashedMap.HashEntry hashEntry2 = this.map.data[iHashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == iHash && isEqualKey(hashEntry2, obj, obj2, obj3, obj4)) {
                Object value = hashEntry2.getValue();
                this.map.removeMapping(hashEntry2, iHashIndex, hashEntry);
                return value;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    protected int hash(Object obj, Object obj2, Object obj3, Object obj4) {
        int iHashCode = obj != null ? obj.hashCode() : 0;
        if (obj2 != null) {
            iHashCode ^= obj2.hashCode();
        }
        if (obj3 != null) {
            iHashCode ^= obj3.hashCode();
        }
        if (obj4 != null) {
            iHashCode ^= obj4.hashCode();
        }
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry hashEntry, Object obj, Object obj2, Object obj3, Object obj4) {
        MultiKey multiKey = (MultiKey) hashEntry.getKey();
        if (multiKey.size() != 4) {
            return false;
        }
        if (obj == null) {
            if (multiKey.getKey(0) != null) {
                return false;
            }
        } else if (!obj.equals(multiKey.getKey(0))) {
            return false;
        }
        if (obj2 == null) {
            if (multiKey.getKey(1) != null) {
                return false;
            }
        } else if (!obj2.equals(multiKey.getKey(1))) {
            return false;
        }
        Object key = multiKey.getKey(2);
        if (obj3 == null) {
            if (key != null) {
                return false;
            }
        } else if (!obj3.equals(key)) {
            return false;
        }
        Object key2 = multiKey.getKey(3);
        if (obj4 == null) {
            if (key2 != null) {
                return false;
            }
        } else if (!obj4.equals(key2)) {
            return false;
        }
        return true;
    }

    public Object get(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int iHash = hash(obj, obj2, obj3, obj4, obj5);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return hashEntry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int iHash = hash(obj, obj2, obj3, obj4, obj5);
        AbstractHashedMap.HashEntry[] hashEntryArr = this.map.data;
        AbstractHashedMap abstractHashedMap = this.map;
        for (AbstractHashedMap.HashEntry hashEntry = hashEntryArr[abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                return true;
            }
        }
        return false;
    }

    public Object put(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        int iHash = hash(obj, obj2, obj3, obj4, obj5);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        for (AbstractHashedMap.HashEntry hashEntry = this.map.data[iHashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == iHash && isEqualKey(hashEntry, obj, obj2, obj3, obj4, obj5)) {
                Object value = hashEntry.getValue();
                this.map.updateEntry(hashEntry, obj6);
                return value;
            }
        }
        this.map.addMapping(iHashIndex, iHash, new MultiKey(obj, obj2, obj3, obj4, obj5), obj6);
        return null;
    }

    public Object remove(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int iHash = hash(obj, obj2, obj3, obj4, obj5);
        AbstractHashedMap abstractHashedMap = this.map;
        int iHashIndex = abstractHashedMap.hashIndex(iHash, abstractHashedMap.data.length);
        AbstractHashedMap.HashEntry hashEntry = null;
        for (AbstractHashedMap.HashEntry hashEntry2 = this.map.data[iHashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == iHash && isEqualKey(hashEntry2, obj, obj2, obj3, obj4, obj5)) {
                Object value = hashEntry2.getValue();
                this.map.removeMapping(hashEntry2, iHashIndex, hashEntry);
                return value;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    protected int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int iHashCode = obj != null ? obj.hashCode() : 0;
        if (obj2 != null) {
            iHashCode ^= obj2.hashCode();
        }
        if (obj3 != null) {
            iHashCode ^= obj3.hashCode();
        }
        if (obj4 != null) {
            iHashCode ^= obj4.hashCode();
        }
        if (obj5 != null) {
            iHashCode ^= obj5.hashCode();
        }
        int i = iHashCode + (~(iHashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry hashEntry, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        MultiKey multiKey = (MultiKey) hashEntry.getKey();
        if (multiKey.size() != 5) {
            return false;
        }
        if (obj == null) {
            if (multiKey.getKey(0) != null) {
                return false;
            }
        } else if (!obj.equals(multiKey.getKey(0))) {
            return false;
        }
        if (obj2 == null) {
            if (multiKey.getKey(1) != null) {
                return false;
            }
        } else if (!obj2.equals(multiKey.getKey(1))) {
            return false;
        }
        Object key = multiKey.getKey(2);
        if (obj3 == null) {
            if (key != null) {
                return false;
            }
        } else if (!obj3.equals(key)) {
            return false;
        }
        Object key2 = multiKey.getKey(3);
        if (obj4 == null) {
            if (key2 != null) {
                return false;
            }
        } else if (!obj4.equals(key2)) {
            return false;
        }
        Object key3 = multiKey.getKey(4);
        if (obj5 == null) {
            if (key3 != null) {
                return false;
            }
        } else if (!obj5.equals(key3)) {
            return false;
        }
        return true;
    }

    public boolean removeAll(Object obj) {
        MapIterator mapIterator = mapIterator();
        boolean z = false;
        while (mapIterator.hasNext()) {
            MultiKey multiKey = (MultiKey) mapIterator.next();
            if (multiKey.size() >= 1) {
                Object key = multiKey.getKey(0);
                if (obj == null) {
                    if (key == null) {
                        mapIterator.remove();
                        z = true;
                    }
                } else if (obj.equals(key)) {
                    mapIterator.remove();
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            org.apache.commons.collections.MapIterator r0 = r6.mapIterator()
            r1 = 0
            r2 = 0
        L6:
            boolean r3 = r0.hasNext()
            if (r3 != 0) goto Ld
            return r2
        Ld:
            java.lang.Object r3 = r0.next()
            org.apache.commons.collections.keyvalue.MultiKey r3 = (org.apache.commons.collections.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 2
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r7 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r7.equals(r4)
            if (r4 == 0) goto L6
        L29:
            r4 = 1
            java.lang.Object r3 = r3.getKey(r4)
            if (r8 != 0) goto L33
            if (r3 != 0) goto L6
            goto L39
        L33:
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L6
        L39:
            r0.remove()
            r2 = 1
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0040 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r7, java.lang.Object r8, java.lang.Object r9) {
        /*
            r6 = this;
            org.apache.commons.collections.MapIterator r0 = r6.mapIterator()
            r1 = 0
            r2 = 0
        L6:
            boolean r3 = r0.hasNext()
            if (r3 != 0) goto Ld
            return r2
        Ld:
            java.lang.Object r3 = r0.next()
            org.apache.commons.collections.keyvalue.MultiKey r3 = (org.apache.commons.collections.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 3
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r7 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r7.equals(r4)
            if (r4 == 0) goto L6
        L29:
            r4 = 1
            java.lang.Object r5 = r3.getKey(r4)
            if (r8 != 0) goto L33
            if (r5 != 0) goto L6
            goto L39
        L33:
            boolean r5 = r8.equals(r5)
            if (r5 == 0) goto L6
        L39:
            r5 = 2
            java.lang.Object r3 = r3.getKey(r5)
            if (r9 != 0) goto L43
            if (r3 != 0) goto L6
            goto L49
        L43:
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L6
        L49:
            r0.remove()
            r2 = 1
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0053 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0050 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0040 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAll(java.lang.Object r7, java.lang.Object r8, java.lang.Object r9, java.lang.Object r10) {
        /*
            r6 = this;
            org.apache.commons.collections.MapIterator r0 = r6.mapIterator()
            r1 = 0
            r2 = 0
        L6:
            boolean r3 = r0.hasNext()
            if (r3 != 0) goto Ld
            return r2
        Ld:
            java.lang.Object r3 = r0.next()
            org.apache.commons.collections.keyvalue.MultiKey r3 = (org.apache.commons.collections.keyvalue.MultiKey) r3
            int r4 = r3.size()
            r5 = 4
            if (r4 < r5) goto L6
            java.lang.Object r4 = r3.getKey(r1)
            if (r7 != 0) goto L23
            if (r4 != 0) goto L6
            goto L29
        L23:
            boolean r4 = r7.equals(r4)
            if (r4 == 0) goto L6
        L29:
            r4 = 1
            java.lang.Object r5 = r3.getKey(r4)
            if (r8 != 0) goto L33
            if (r5 != 0) goto L6
            goto L39
        L33:
            boolean r5 = r8.equals(r5)
            if (r5 == 0) goto L6
        L39:
            r5 = 2
            java.lang.Object r5 = r3.getKey(r5)
            if (r9 != 0) goto L43
            if (r5 != 0) goto L6
            goto L49
        L43:
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L6
        L49:
            r5 = 3
            java.lang.Object r3 = r3.getKey(r5)
            if (r10 != 0) goto L53
            if (r3 != 0) goto L6
            goto L59
        L53:
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L6
        L59:
            r0.remove()
            r2 = 1
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections.map.MultiKeyMap.removeAll(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):boolean");
    }

    protected void checkKey(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Key must not be null");
        }
        if (!(obj instanceof MultiKey)) {
            throw new ClassCastException("Key must be a MultiKey");
        }
    }

    public Object clone() {
        return new MultiKeyMap((AbstractHashedMap) this.map.clone());
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        checkKey(obj);
        return this.map.put(obj, obj2);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        Iterator it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            checkKey(it2.next());
        }
        this.map.putAll(map);
    }

    @Override // org.apache.commons.collections.IterableMap
    public MapIterator mapIterator() {
        return this.map.mapIterator();
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.map.get(obj);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public Set keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public Set entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    public String toString() {
        return this.map.toString();
    }
}
