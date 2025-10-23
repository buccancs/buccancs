package io.grpc.netty.shaded.io.netty.util.collection;

import java.util.Map;

/* loaded from: classes3.dex */
public interface IntObjectMap<V> extends Map<Integer, V> {

    boolean containsKey(int i);

    Iterable<PrimitiveEntry<V>> entries();

    V get(int i);

    V put(int i, V v);

    V remove(int i);

    public interface PrimitiveEntry<V> {
        int key();

        void setValue(V v);

        V value();
    }
}
