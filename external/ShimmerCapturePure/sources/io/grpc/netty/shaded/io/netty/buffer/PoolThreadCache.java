package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.buffer.PoolArena;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
final class PoolThreadCache {
    private static final int INTEGER_SIZE_MINUS_ONE = 31;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PoolThreadCache.class);
    final PoolArena<ByteBuffer> directArena;
    final PoolArena<byte[]> heapArena;
    private final int freeSweepAllocationThreshold;
    private final AtomicBoolean freed = new AtomicBoolean();
    private final MemoryRegionCache<ByteBuffer>[] normalDirectCaches;
    private final MemoryRegionCache<byte[]>[] normalHeapCaches;
    private final int numShiftsNormalDirect;
    private final int numShiftsNormalHeap;
    private final MemoryRegionCache<ByteBuffer>[] smallSubPageDirectCaches;
    private final MemoryRegionCache<byte[]>[] smallSubPageHeapCaches;
    private final MemoryRegionCache<ByteBuffer>[] tinySubPageDirectCaches;
    private final MemoryRegionCache<byte[]>[] tinySubPageHeapCaches;
    private int allocations;

    PoolThreadCache(PoolArena<byte[]> poolArena, PoolArena<ByteBuffer> poolArena2, int i, int i2, int i3, int i4, int i5) {
        ObjectUtil.checkPositiveOrZero(i4, "maxCachedBufferCapacity");
        this.freeSweepAllocationThreshold = i5;
        this.heapArena = poolArena;
        this.directArena = poolArena2;
        if (poolArena2 != null) {
            this.tinySubPageDirectCaches = createSubPageCaches(i, 32, PoolArena.SizeClass.Tiny);
            this.smallSubPageDirectCaches = createSubPageCaches(i2, poolArena2.numSmallSubpagePools, PoolArena.SizeClass.Small);
            this.numShiftsNormalDirect = log2(poolArena2.pageSize);
            this.normalDirectCaches = createNormalCaches(i3, i4, poolArena2);
            poolArena2.numThreadCaches.getAndIncrement();
        } else {
            this.tinySubPageDirectCaches = null;
            this.smallSubPageDirectCaches = null;
            this.normalDirectCaches = null;
            this.numShiftsNormalDirect = -1;
        }
        if (poolArena != null) {
            this.tinySubPageHeapCaches = createSubPageCaches(i, 32, PoolArena.SizeClass.Tiny);
            this.smallSubPageHeapCaches = createSubPageCaches(i2, poolArena.numSmallSubpagePools, PoolArena.SizeClass.Small);
            this.numShiftsNormalHeap = log2(poolArena.pageSize);
            this.normalHeapCaches = createNormalCaches(i3, i4, poolArena);
            poolArena.numThreadCaches.getAndIncrement();
        } else {
            this.tinySubPageHeapCaches = null;
            this.smallSubPageHeapCaches = null;
            this.normalHeapCaches = null;
            this.numShiftsNormalHeap = -1;
        }
        if (!(this.tinySubPageDirectCaches == null && this.smallSubPageDirectCaches == null && this.normalDirectCaches == null && this.tinySubPageHeapCaches == null && this.smallSubPageHeapCaches == null && this.normalHeapCaches == null) && i5 < 1) {
            throw new IllegalArgumentException("freeSweepAllocationThreshold: " + i5 + " (expected: > 0)");
        }
    }

    private static <T> MemoryRegionCache<T>[] createSubPageCaches(int i, int i2, PoolArena.SizeClass sizeClass) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        MemoryRegionCache<T>[] memoryRegionCacheArr = new MemoryRegionCache[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            memoryRegionCacheArr[i3] = new SubPageMemoryRegionCache(i, sizeClass);
        }
        return memoryRegionCacheArr;
    }

    private static <T> MemoryRegionCache<T>[] createNormalCaches(int i, int i2, PoolArena<T> poolArena) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        int iMax = Math.max(1, log2(Math.min(poolArena.chunkSize, i2) / poolArena.pageSize) + 1);
        MemoryRegionCache<T>[] memoryRegionCacheArr = new MemoryRegionCache[iMax];
        for (int i3 = 0; i3 < iMax; i3++) {
            memoryRegionCacheArr[i3] = new NormalMemoryRegionCache(i);
        }
        return memoryRegionCacheArr;
    }

    private static int log2(int i) {
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    private static int free(MemoryRegionCache<?>[] memoryRegionCacheArr, boolean z) {
        if (memoryRegionCacheArr == null) {
            return 0;
        }
        int iFree = 0;
        for (MemoryRegionCache<?> memoryRegionCache : memoryRegionCacheArr) {
            iFree += free(memoryRegionCache, z);
        }
        return iFree;
    }

    private static int free(MemoryRegionCache<?> memoryRegionCache, boolean z) {
        if (memoryRegionCache == null) {
            return 0;
        }
        return memoryRegionCache.free(z);
    }

    private static void trim(MemoryRegionCache<?>[] memoryRegionCacheArr) {
        if (memoryRegionCacheArr == null) {
            return;
        }
        for (MemoryRegionCache<?> memoryRegionCache : memoryRegionCacheArr) {
            trim(memoryRegionCache);
        }
    }

    private static void trim(MemoryRegionCache<?> memoryRegionCache) {
        if (memoryRegionCache == null) {
            return;
        }
        memoryRegionCache.trim();
    }

    private static <T> MemoryRegionCache<T> cache(MemoryRegionCache<T>[] memoryRegionCacheArr, int i) {
        if (memoryRegionCacheArr == null || i > memoryRegionCacheArr.length - 1) {
            return null;
        }
        return memoryRegionCacheArr[i];
    }

    boolean allocateTiny(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForTiny(poolArena, i2), pooledByteBuf, i);
    }

    boolean allocateSmall(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForSmall(poolArena, i2), pooledByteBuf, i);
    }

    boolean allocateNormal(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForNormal(poolArena, i2), pooledByteBuf, i);
    }

    private boolean allocate(MemoryRegionCache<?> memoryRegionCache, PooledByteBuf pooledByteBuf, int i) {
        if (memoryRegionCache == null) {
            return false;
        }
        boolean zAllocate = memoryRegionCache.allocate(pooledByteBuf, i, this);
        int i2 = this.allocations + 1;
        this.allocations = i2;
        if (i2 >= this.freeSweepAllocationThreshold) {
            this.allocations = 0;
            trim();
        }
        return zAllocate;
    }

    boolean add(PoolArena<?> poolArena, PoolChunk poolChunk, ByteBuffer byteBuffer, long j, int i, PoolArena.SizeClass sizeClass) {
        MemoryRegionCache<?> memoryRegionCacheCache = cache(poolArena, i, sizeClass);
        if (memoryRegionCacheCache == null) {
            return false;
        }
        return memoryRegionCacheCache.add(poolChunk, byteBuffer, j);
    }

    private MemoryRegionCache<?> cache(PoolArena<?> poolArena, int i, PoolArena.SizeClass sizeClass) {
        int i2 = AnonymousClass1.$SwitchMap$io$netty$buffer$PoolArena$SizeClass[sizeClass.ordinal()];
        if (i2 == 1) {
            return cacheForNormal(poolArena, i);
        }
        if (i2 == 2) {
            return cacheForSmall(poolArena, i);
        }
        if (i2 == 3) {
            return cacheForTiny(poolArena, i);
        }
        throw new Error();
    }

    protected void finalize() throws Throwable {
        try {
            super.finalize();
        } finally {
            free(true);
        }
    }

    void free(boolean z) {
        if (this.freed.compareAndSet(false, true)) {
            int iFree = free(this.tinySubPageDirectCaches, z) + free(this.smallSubPageDirectCaches, z) + free(this.normalDirectCaches, z) + free((MemoryRegionCache<?>[]) this.tinySubPageHeapCaches, z) + free((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches, z) + free((MemoryRegionCache<?>[]) this.normalHeapCaches, z);
            if (iFree > 0) {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Freed {} thread-local buffer(s) from thread: {}", Integer.valueOf(iFree), Thread.currentThread().getName());
                }
            }
            PoolArena<ByteBuffer> poolArena = this.directArena;
            if (poolArena != null) {
                poolArena.numThreadCaches.getAndDecrement();
            }
            PoolArena<byte[]> poolArena2 = this.heapArena;
            if (poolArena2 != null) {
                poolArena2.numThreadCaches.getAndDecrement();
            }
        }
    }

    void trim() {
        trim(this.tinySubPageDirectCaches);
        trim(this.smallSubPageDirectCaches);
        trim(this.normalDirectCaches);
        trim((MemoryRegionCache<?>[]) this.tinySubPageHeapCaches);
        trim((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches);
        trim((MemoryRegionCache<?>[]) this.normalHeapCaches);
    }

    private MemoryRegionCache<?> cacheForTiny(PoolArena<?> poolArena, int i) {
        int iTinyIdx = PoolArena.tinyIdx(i);
        if (poolArena.isDirect()) {
            return cache(this.tinySubPageDirectCaches, iTinyIdx);
        }
        return cache(this.tinySubPageHeapCaches, iTinyIdx);
    }

    private MemoryRegionCache<?> cacheForSmall(PoolArena<?> poolArena, int i) {
        int iSmallIdx = PoolArena.smallIdx(i);
        if (poolArena.isDirect()) {
            return cache(this.smallSubPageDirectCaches, iSmallIdx);
        }
        return cache(this.smallSubPageHeapCaches, iSmallIdx);
    }

    private MemoryRegionCache<?> cacheForNormal(PoolArena<?> poolArena, int i) {
        if (poolArena.isDirect()) {
            return cache(this.normalDirectCaches, log2(i >> this.numShiftsNormalDirect));
        }
        return cache(this.normalHeapCaches, log2(i >> this.numShiftsNormalHeap));
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$buffer$PoolArena$SizeClass;

        static {
            int[] iArr = new int[PoolArena.SizeClass.values().length];
            $SwitchMap$io$netty$buffer$PoolArena$SizeClass = iArr;
            try {
                iArr[PoolArena.SizeClass.Normal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[PoolArena.SizeClass.Small.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass[PoolArena.SizeClass.Tiny.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static final class SubPageMemoryRegionCache<T> extends MemoryRegionCache<T> {
        SubPageMemoryRegionCache(int i, PoolArena.SizeClass sizeClass) {
            super(i, sizeClass);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache
        protected void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBufWithSubpage(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    private static final class NormalMemoryRegionCache<T> extends MemoryRegionCache<T> {
        NormalMemoryRegionCache(int i) {
            super(i, PoolArena.SizeClass.Normal);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache
        protected void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBuf(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    private static abstract class MemoryRegionCache<T> {
        private static final ObjectPool<Entry> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<Entry>() { // from class: io.grpc.netty.shaded.io.netty.buffer.PoolThreadCache.MemoryRegionCache.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
            public Entry newObject(ObjectPool.Handle<Entry> handle) {
                return new Entry(handle);
            }
        });
        private final Queue<Entry<T>> queue;
        private final int size;
        private final PoolArena.SizeClass sizeClass;
        private int allocations;

        MemoryRegionCache(int i, PoolArena.SizeClass sizeClass) {
            int iSafeFindNextPositivePowerOfTwo = MathUtil.safeFindNextPositivePowerOfTwo(i);
            this.size = iSafeFindNextPositivePowerOfTwo;
            this.queue = PlatformDependent.newFixedMpscQueue(iSafeFindNextPositivePowerOfTwo);
            this.sizeClass = sizeClass;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static Entry newEntry(PoolChunk<?> poolChunk, ByteBuffer byteBuffer, long j) {
            Entry entry = RECYCLER.get();
            entry.chunk = poolChunk;
            entry.nioBuffer = byteBuffer;
            entry.handle = j;
            return entry;
        }

        protected abstract void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache);

        public final boolean add(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j) {
            Entry<T> entryNewEntry = newEntry(poolChunk, byteBuffer, j);
            boolean zOffer = this.queue.offer(entryNewEntry);
            if (!zOffer) {
                entryNewEntry.recycle();
            }
            return zOffer;
        }

        public final boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            Entry<T> entryPoll = this.queue.poll();
            if (entryPoll == null) {
                return false;
            }
            initBuf(entryPoll.chunk, entryPoll.nioBuffer, entryPoll.handle, pooledByteBuf, i, poolThreadCache);
            entryPoll.recycle();
            this.allocations++;
            return true;
        }

        public final int free(boolean z) {
            return free(Integer.MAX_VALUE, z);
        }

        private int free(int i, boolean z) {
            int i2 = 0;
            while (i2 < i) {
                Entry<T> entryPoll = this.queue.poll();
                if (entryPoll == null) {
                    break;
                }
                freeEntry(entryPoll, z);
                i2++;
            }
            return i2;
        }

        public final void trim() {
            int i = this.size - this.allocations;
            this.allocations = 0;
            if (i > 0) {
                free(i, false);
            }
        }

        private void freeEntry(Entry entry, boolean z) {
            PoolChunk<T> poolChunk = entry.chunk;
            long j = entry.handle;
            ByteBuffer byteBuffer = entry.nioBuffer;
            if (!z) {
                entry.recycle();
            }
            poolChunk.arena.freeChunk(poolChunk, j, this.sizeClass, byteBuffer, z);
        }

        static final class Entry<T> {
            final ObjectPool.Handle<Entry<?>> recyclerHandle;
            PoolChunk<T> chunk;
            long handle = -1;
            ByteBuffer nioBuffer;

            Entry(ObjectPool.Handle<Entry<?>> handle) {
                this.recyclerHandle = handle;
            }

            void recycle() {
                this.chunk = null;
                this.nioBuffer = null;
                this.handle = -1L;
                this.recyclerHandle.recycle(this);
            }
        }
    }
}
