package io.grpc.netty.shaded.io.netty.util.collection;

import java.util.Map;

/* loaded from: classes3.dex */
public interface ByteObjectMap<V> extends Map<Byte, V> {

    boolean containsKey(byte b);

    Iterable<PrimitiveEntry<V>> entries();

    V get(byte b);

    V put(byte b, V v);

    V remove(byte b);

    public interface PrimitiveEntry<V> {
        byte key();

        void setValue(V v);

        V value();
    }
}
