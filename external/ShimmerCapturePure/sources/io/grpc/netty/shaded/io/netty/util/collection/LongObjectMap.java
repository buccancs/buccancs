package io.grpc.netty.shaded.io.netty.util.collection;

import java.util.Map;

/* loaded from: classes3.dex */
public interface LongObjectMap<V> extends Map<Long, V> {

    boolean containsKey(long j);

    Iterable<PrimitiveEntry<V>> entries();

    V get(long j);

    V put(long j, V v);

    V remove(long j);

    public interface PrimitiveEntry<V> {
        long key();

        void setValue(V v);

        V value();
    }
}
