package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import io.grpc.xds.internal.sds.Closeable;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckReturnValue;

/* loaded from: classes3.dex */
public final class ReferenceCountingMap<K, V extends Closeable> {
    private final Map<K, Instance<V>> instances = new HashMap();
    private final ValueFactory<K, V> valueFactory;

    public ReferenceCountingMap(ValueFactory<K, V> valueFactory) {
        Preconditions.checkNotNull(valueFactory, "valueFactory");
        this.valueFactory = valueFactory;
    }

    @CheckReturnValue
    public V get(K k) {
        Preconditions.checkNotNull(k, "key");
        return (V) getInternal(k);
    }

    public V release(K k, V v) {
        Preconditions.checkNotNull(k, "key");
        Preconditions.checkNotNull(v, "value");
        return (V) releaseInternal(k, v);
    }

    private synchronized V getInternal(K k) {
        Instance<V> instance = this.instances.get(k);
        if (instance == null) {
            Instance<V> instance2 = new Instance<>(this.valueFactory.create(k));
            this.instances.put(k, instance2);
            return instance2.value;
        }
        return (V) instance.acquire();
    }

    private synchronized V releaseInternal(K k, V v) {
        Instance<V> instance = this.instances.get(k);
        boolean z = true;
        Preconditions.checkArgument(instance != null, "No cached instance found for %s", k);
        if (v != instance.value) {
            z = false;
        }
        Preconditions.checkArgument(z, "Releasing the wrong instance");
        if (instance.release()) {
            try {
                instance.value.close();
                this.instances.remove(k);
            } catch (Throwable th) {
                this.instances.remove(k);
                throw th;
            }
        }
        return null;
    }

    public interface ValueFactory<K, V extends Closeable> {
        V create(K k);
    }

    private static final class Instance<V extends Closeable> {
        final V value;
        private int refCount = 1;

        Instance(V v) {
            this.value = v;
        }

        V acquire() {
            this.refCount++;
            return this.value;
        }

        boolean release() {
            Preconditions.checkState(this.refCount > 0, "refCount has to be > 0");
            int i = this.refCount - 1;
            this.refCount = i;
            return i == 0;
        }
    }
}
