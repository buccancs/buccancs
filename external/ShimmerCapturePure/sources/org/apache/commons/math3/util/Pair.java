package org.apache.commons.math3.util;

/* loaded from: classes5.dex */
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public Pair(Pair<? extends K, ? extends V> pair) {
        this(pair.getKey(), pair.getValue());
    }

    public static <K, V> Pair<K, V> create(K k, V v) {
        return new Pair<>(k, v);
    }

    public K getFirst() {
        return this.key;
    }

    public K getKey() {
        return this.key;
    }

    public V getSecond() {
        return this.value;
    }

    public V getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        K k = this.key;
        if (k != null ? k.equals(pair.key) : pair.key == null) {
            V v = this.value;
            V v2 = pair.value;
            if (v == null) {
                if (v2 == null) {
                    return true;
                }
            } else if (v.equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        K k = this.key;
        int iHashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        int iHashCode2 = v != null ? v.hashCode() : 0;
        return ((iHashCode * 37) + iHashCode2) ^ (iHashCode2 >>> 16);
    }

    public String toString() {
        return "[" + getKey() + ", " + getValue() + "]";
    }
}
