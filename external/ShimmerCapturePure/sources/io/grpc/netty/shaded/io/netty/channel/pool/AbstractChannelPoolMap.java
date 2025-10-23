package io.grpc.netty.shaded.io.netty.channel.pool;

import io.grpc.netty.shaded.io.netty.channel.pool.ChannelPool;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GlobalEventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.ReadOnlyIterator;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes3.dex */
public abstract class AbstractChannelPoolMap<K, P extends ChannelPool> implements ChannelPoolMap<K, P>, Iterable<Map.Entry<K, P>>, Closeable {
    private final ConcurrentMap<K, P> map = PlatformDependent.newConcurrentHashMap();

    private static Future<Void> poolCloseAsyncIfSupported(ChannelPool channelPool) {
        if (channelPool instanceof SimpleChannelPool) {
            return ((SimpleChannelPool) channelPool).closeAsync();
        }
        try {
            channelPool.close();
            return GlobalEventExecutor.INSTANCE.newSucceededFuture(null);
        } catch (Exception e) {
            return GlobalEventExecutor.INSTANCE.newFailedFuture(e);
        }
    }

    protected abstract P newPool(K k);

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPoolMap
    public final P get(K k) {
        P p = this.map.get(ObjectUtil.checkNotNull(k, "key"));
        if (p != null) {
            return p;
        }
        P p2 = (P) newPool(k);
        P p3 = (P) this.map.putIfAbsent(k, p2);
        if (p3 == null) {
            return p2;
        }
        poolCloseAsyncIfSupported(p2);
        return p3;
    }

    public final boolean remove(K k) {
        P pRemove = this.map.remove(ObjectUtil.checkNotNull(k, "key"));
        if (pRemove == null) {
            return false;
        }
        poolCloseAsyncIfSupported(pRemove);
        return true;
    }

    private Future<Boolean> removeAsyncIfSupported(K k) {
        P pRemove = this.map.remove(ObjectUtil.checkNotNull(k, "key"));
        if (pRemove != null) {
            final Promise promiseNewPromise = GlobalEventExecutor.INSTANCE.newPromise();
            poolCloseAsyncIfSupported(pRemove).addListener(new GenericFutureListener<Future<? super Void>>() { // from class: io.grpc.netty.shaded.io.netty.channel.pool.AbstractChannelPoolMap.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        promiseNewPromise.setSuccess(Boolean.TRUE);
                    } else {
                        promiseNewPromise.setFailure(future.cause());
                    }
                }
            });
            return promiseNewPromise;
        }
        return GlobalEventExecutor.INSTANCE.newSucceededFuture(Boolean.FALSE);
    }

    @Override // java.lang.Iterable
    public final Iterator<Map.Entry<K, P>> iterator() {
        return new ReadOnlyIterator(this.map.entrySet().iterator());
    }

    public final int size() {
        return this.map.size();
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.pool.ChannelPoolMap
    public final boolean contains(K k) {
        return this.map.containsKey(ObjectUtil.checkNotNull(k, "key"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator<K> it2 = this.map.keySet().iterator();
        while (it2.hasNext()) {
            removeAsyncIfSupported(it2.next()).syncUninterruptibly();
        }
    }
}
