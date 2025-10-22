package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.buffer.PoolArena;
import io.grpc.netty.shaded.io.netty.util.NettyRuntime;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocalThread;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadExecutorMap;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class PooledByteBufAllocator extends AbstractByteBufAllocator implements ByteBufAllocatorMetricProvider {
    public static final PooledByteBufAllocator DEFAULT;
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK;
    private static final int DEFAULT_CACHE_TRIM_INTERVAL;
    private static final long DEFAULT_CACHE_TRIM_INTERVAL_MILLIS;
    private static final int DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT;
    private static final int DEFAULT_MAX_CACHED_BUFFER_CAPACITY;
    private static final int DEFAULT_MAX_ORDER;
    private static final int DEFAULT_NORMAL_CACHE_SIZE;
    private static final int DEFAULT_NUM_DIRECT_ARENA;
    private static final int DEFAULT_NUM_HEAP_ARENA;
    private static final int DEFAULT_PAGE_SIZE;
    private static final int DEFAULT_SMALL_CACHE_SIZE;
    private static final int DEFAULT_TINY_CACHE_SIZE;
    private static final boolean DEFAULT_USE_CACHE_FOR_ALL_THREADS;
    private static final int MAX_CHUNK_SIZE = 1073741824;
    private static final int MIN_PAGE_SIZE = 4096;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PooledByteBufAllocator.class);

    static {
        Object obj;
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.pageSize", 8192);
        Object obj2 = null;
        try {
            validateAndCalculatePageShifts(i);
            obj = null;
        } catch (Throwable th) {
            obj = th;
            i = 8192;
        }
        DEFAULT_PAGE_SIZE = i;
        int i2 = 11;
        int i3 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxOrder", 11);
        try {
            validateAndCalculateChunkSize(i, i3);
            i2 = i3;
        } catch (Throwable th2) {
            obj2 = th2;
        }
        DEFAULT_MAX_ORDER = i2;
        Runtime runtime = Runtime.getRuntime();
        int iAvailableProcessors = NettyRuntime.availableProcessors() * 2;
        int i4 = DEFAULT_PAGE_SIZE;
        long j = iAvailableProcessors;
        long j2 = i4 << i2;
        int iMax = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.numHeapArenas", (int) Math.min(j, ((runtime.maxMemory() / j2) / 2) / 3)));
        DEFAULT_NUM_HEAP_ARENA = iMax;
        int iMax2 = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.numDirectArenas", (int) Math.min(j, ((PlatformDependent.maxDirectMemory() / j2) / 2) / 3)));
        DEFAULT_NUM_DIRECT_ARENA = iMax2;
        int i5 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.tinyCacheSize", 512);
        DEFAULT_TINY_CACHE_SIZE = i5;
        int i6 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.smallCacheSize", 256);
        DEFAULT_SMALL_CACHE_SIZE = i6;
        int i7 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.normalCacheSize", 64);
        DEFAULT_NORMAL_CACHE_SIZE = i7;
        int i8 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxCachedBufferCapacity", 32768);
        DEFAULT_MAX_CACHED_BUFFER_CAPACITY = i8;
        int i9 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.cacheTrimInterval", 8192);
        DEFAULT_CACHE_TRIM_INTERVAL = i9;
        if (SystemPropertyUtil.contains("io.grpc.netty.shaded.io.netty.allocation.cacheTrimIntervalMillis")) {
            logger.warn("-Dio.netty.allocation.cacheTrimIntervalMillis is deprecated, use -Dio.netty.allocator.cacheTrimIntervalMillis");
            if (SystemPropertyUtil.contains("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis")) {
                DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis", 0L);
            } else {
                DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocation.cacheTrimIntervalMillis", 0L);
            }
        } else {
            DEFAULT_CACHE_TRIM_INTERVAL_MILLIS = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.allocator.cacheTrimIntervalMillis", 0L);
        }
        boolean z = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.allocator.useCacheForAllThreads", true);
        DEFAULT_USE_CACHE_FOR_ALL_THREADS = z;
        DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.directMemoryCacheAlignment", 0);
        int i10 = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.allocator.maxCachedByteBuffersPerChunk", 1023);
        DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK = i10;
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            internalLogger.debug("-Dio.netty.allocator.numHeapArenas: {}", Integer.valueOf(iMax));
            internalLogger.debug("-Dio.netty.allocator.numDirectArenas: {}", Integer.valueOf(iMax2));
            if (obj == null) {
                internalLogger.debug("-Dio.netty.allocator.pageSize: {}", Integer.valueOf(i4));
            } else {
                internalLogger.debug("-Dio.netty.allocator.pageSize: {}", Integer.valueOf(i4), obj);
            }
            if (obj2 == null) {
                internalLogger.debug("-Dio.netty.allocator.maxOrder: {}", Integer.valueOf(i2));
            } else {
                internalLogger.debug("-Dio.netty.allocator.maxOrder: {}", Integer.valueOf(i2), obj2);
            }
            internalLogger.debug("-Dio.netty.allocator.chunkSize: {}", Integer.valueOf(i4 << i2));
            internalLogger.debug("-Dio.netty.allocator.tinyCacheSize: {}", Integer.valueOf(i5));
            internalLogger.debug("-Dio.netty.allocator.smallCacheSize: {}", Integer.valueOf(i6));
            internalLogger.debug("-Dio.netty.allocator.normalCacheSize: {}", Integer.valueOf(i7));
            internalLogger.debug("-Dio.netty.allocator.maxCachedBufferCapacity: {}", Integer.valueOf(i8));
            internalLogger.debug("-Dio.netty.allocator.cacheTrimInterval: {}", Integer.valueOf(i9));
            internalLogger.debug("-Dio.netty.allocator.cacheTrimIntervalMillis: {}", Long.valueOf(DEFAULT_CACHE_TRIM_INTERVAL_MILLIS));
            internalLogger.debug("-Dio.netty.allocator.useCacheForAllThreads: {}", Boolean.valueOf(z));
            internalLogger.debug("-Dio.netty.allocator.maxCachedByteBuffersPerChunk: {}", Integer.valueOf(i10));
        }
        DEFAULT = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());
    }

    private final int chunkSize;
    private final List<PoolArenaMetric> directArenaMetrics;
    private final PoolArena<ByteBuffer>[] directArenas;
    private final List<PoolArenaMetric> heapArenaMetrics;
    private final PoolArena<byte[]>[] heapArenas;
    private final PooledByteBufAllocatorMetric metric;
    private final int normalCacheSize;
    private final int smallCacheSize;
    private final PoolThreadLocalCache threadCache;
    private final int tinyCacheSize;
    private final Runnable trimTask;

    public PooledByteBufAllocator() {
        this(false);
    }

    public PooledByteBufAllocator(boolean z) {
        this(z, DEFAULT_NUM_HEAP_ARENA, DEFAULT_NUM_DIRECT_ARENA, DEFAULT_PAGE_SIZE, DEFAULT_MAX_ORDER);
    }

    public PooledByteBufAllocator(int i, int i2, int i3, int i4) {
        this(false, i, i2, i3, i4);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4) {
        this(z, i, i2, i3, i4, DEFAULT_TINY_CACHE_SIZE, DEFAULT_SMALL_CACHE_SIZE, DEFAULT_NORMAL_CACHE_SIZE);
    }

    @Deprecated
    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(z, i, i2, i3, i4, i5, i6, i7, DEFAULT_USE_CACHE_FOR_ALL_THREADS, DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT);
    }

    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2) {
        this(z, i, i2, i3, i4, i5, i6, i7, z2, DEFAULT_DIRECT_MEMORY_CACHE_ALIGNMENT);
    }

    public PooledByteBufAllocator(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, int i8) {
        super(z);
        this.trimTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.buffer.PooledByteBufAllocator.1
            @Override // java.lang.Runnable
            public void run() {
                PooledByteBufAllocator.this.trimCurrentThreadCache();
            }
        };
        this.threadCache = new PoolThreadLocalCache(z2);
        this.tinyCacheSize = i5;
        this.smallCacheSize = i6;
        this.normalCacheSize = i7;
        this.chunkSize = validateAndCalculateChunkSize(i3, i4);
        ObjectUtil.checkPositiveOrZero(i, "nHeapArena");
        ObjectUtil.checkPositiveOrZero(i2, "nDirectArena");
        ObjectUtil.checkPositiveOrZero(i8, "directMemoryCacheAlignment");
        if (i8 > 0 && !isDirectMemoryCacheAlignmentSupported()) {
            throw new IllegalArgumentException("directMemoryCacheAlignment is not supported");
        }
        if (((-i8) & i8) != i8) {
            throw new IllegalArgumentException("directMemoryCacheAlignment: " + i8 + " (expected: power of two)");
        }
        int iValidateAndCalculatePageShifts = validateAndCalculatePageShifts(i3);
        if (i > 0) {
            PoolArena<byte[]>[] poolArenaArrNewArenaArray = newArenaArray(i);
            this.heapArenas = poolArenaArrNewArenaArray;
            ArrayList arrayList = new ArrayList(poolArenaArrNewArenaArray.length);
            for (int i9 = 0; i9 < this.heapArenas.length; i9++) {
                PoolArena.HeapArena heapArena = new PoolArena.HeapArena(this, i3, i4, iValidateAndCalculatePageShifts, this.chunkSize, i8);
                this.heapArenas[i9] = heapArena;
                arrayList.add(heapArena);
            }
            this.heapArenaMetrics = Collections.unmodifiableList(arrayList);
        } else {
            this.heapArenas = null;
            this.heapArenaMetrics = Collections.emptyList();
        }
        if (i2 > 0) {
            PoolArena<ByteBuffer>[] poolArenaArrNewArenaArray2 = newArenaArray(i2);
            this.directArenas = poolArenaArrNewArenaArray2;
            ArrayList arrayList2 = new ArrayList(poolArenaArrNewArenaArray2.length);
            for (int i10 = 0; i10 < this.directArenas.length; i10++) {
                PoolArena.DirectArena directArena = new PoolArena.DirectArena(this, i3, i4, iValidateAndCalculatePageShifts, this.chunkSize, i8);
                this.directArenas[i10] = directArena;
                arrayList2.add(directArena);
            }
            this.directArenaMetrics = Collections.unmodifiableList(arrayList2);
        } else {
            this.directArenas = null;
            this.directArenaMetrics = Collections.emptyList();
        }
        this.metric = new PooledByteBufAllocatorMetric(this);
    }

    public static int defaultMaxOrder() {
        return DEFAULT_MAX_ORDER;
    }

    public static int defaultNormalCacheSize() {
        return DEFAULT_NORMAL_CACHE_SIZE;
    }

    public static int defaultNumDirectArena() {
        return DEFAULT_NUM_DIRECT_ARENA;
    }

    public static int defaultNumHeapArena() {
        return DEFAULT_NUM_HEAP_ARENA;
    }

    public static int defaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    public static int defaultSmallCacheSize() {
        return DEFAULT_SMALL_CACHE_SIZE;
    }

    public static int defaultTinyCacheSize() {
        return DEFAULT_TINY_CACHE_SIZE;
    }

    public static boolean defaultUseCacheForAllThreads() {
        return DEFAULT_USE_CACHE_FOR_ALL_THREADS;
    }

    private static <T> PoolArena<T>[] newArenaArray(int i) {
        return new PoolArena[i];
    }

    private static int validateAndCalculatePageShifts(int i) {
        if (i < 4096) {
            throw new IllegalArgumentException("pageSize: " + i + " (expected: 4096)");
        }
        if (((i - 1) & i) != 0) {
            throw new IllegalArgumentException("pageSize: " + i + " (expected: power of 2)");
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    private static int validateAndCalculateChunkSize(int i, int i2) {
        if (i2 > 14) {
            throw new IllegalArgumentException("maxOrder: " + i2 + " (expected: 0-14)");
        }
        int i3 = i;
        for (int i4 = i2; i4 > 0; i4--) {
            if (i3 > 536870912) {
                throw new IllegalArgumentException(String.format("pageSize (%d) << maxOrder (%d) must not exceed %d", Integer.valueOf(i), Integer.valueOf(i2), 1073741824));
            }
            i3 <<= 1;
        }
        return i3;
    }

    public static boolean defaultPreferDirect() {
        return PlatformDependent.directBufferPreferred();
    }

    public static boolean isDirectMemoryCacheAlignmentSupported() {
        return PlatformDependent.hasUnsafe();
    }

    private static long usedMemory(PoolArena<?>[] poolArenaArr) {
        if (poolArenaArr == null) {
            return -1L;
        }
        long jNumActiveBytes = 0;
        for (PoolArena<?> poolArena : poolArenaArr) {
            jNumActiveBytes += poolArena.numActiveBytes();
            if (jNumActiveBytes < 0) {
                return Long.MAX_VALUE;
            }
        }
        return jNumActiveBytes;
    }

    @Deprecated
    public final int chunkSize() {
        return this.chunkSize;
    }

    @Deprecated
    public List<PoolArenaMetric> directArenas() {
        return this.directArenaMetrics;
    }

    @Deprecated
    public List<PoolArenaMetric> heapArenas() {
        return this.heapArenaMetrics;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public boolean isDirectBufferPooled() {
        return this.directArenas != null;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocatorMetricProvider
    public PooledByteBufAllocatorMetric metric() {
        return this.metric;
    }

    @Deprecated
    public int normalCacheSize() {
        return this.normalCacheSize;
    }

    @Deprecated
    public int smallCacheSize() {
        return this.smallCacheSize;
    }

    @Deprecated
    public int tinyCacheSize() {
        return this.tinyCacheSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator
    protected ByteBuf newHeapBuffer(int i, int i2) {
        ByteBuf unpooledUnsafeHeapByteBuf;
        PoolThreadCache poolThreadCache = this.threadCache.get();
        PoolArena<byte[]> poolArena = poolThreadCache.heapArena;
        if (poolArena != null) {
            unpooledUnsafeHeapByteBuf = poolArena.allocate(poolThreadCache, i, i2);
        } else {
            unpooledUnsafeHeapByteBuf = PlatformDependent.hasUnsafe() ? new UnpooledUnsafeHeapByteBuf(this, i, i2) : new UnpooledHeapByteBuf(this, i, i2);
        }
        return toLeakAwareBuffer(unpooledUnsafeHeapByteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator
    protected ByteBuf newDirectBuffer(int i, int i2) {
        ByteBuf byteBufNewUnsafeDirectByteBuf;
        PoolThreadCache poolThreadCache = this.threadCache.get();
        PoolArena<ByteBuffer> poolArena = poolThreadCache.directArena;
        if (poolArena != null) {
            byteBufNewUnsafeDirectByteBuf = poolArena.allocate(poolThreadCache, i, i2);
        } else {
            byteBufNewUnsafeDirectByteBuf = PlatformDependent.hasUnsafe() ? UnsafeByteBufUtil.newUnsafeDirectByteBuf(this, i, i2) : new UnpooledDirectByteBuf(this, i, i2);
        }
        return toLeakAwareBuffer(byteBufNewUnsafeDirectByteBuf);
    }

    @Deprecated
    public boolean hasThreadLocalCache() {
        return this.threadCache.isSet();
    }

    @Deprecated
    public void freeThreadLocalCache() {
        this.threadCache.remove();
    }

    @Deprecated
    public int numHeapArenas() {
        return this.heapArenaMetrics.size();
    }

    @Deprecated
    public int numDirectArenas() {
        return this.directArenaMetrics.size();
    }

    @Deprecated
    public int numThreadLocalCaches() {
        PoolArena[] poolArenaArr = this.heapArenas;
        if (poolArenaArr == null) {
            poolArenaArr = this.directArenas;
        }
        if (poolArenaArr == null) {
            return 0;
        }
        int i = 0;
        for (PoolArena poolArena : poolArenaArr) {
            i += poolArena.numThreadCaches.get();
        }
        return i;
    }

    final long usedHeapMemory() {
        return usedMemory(this.heapArenas);
    }

    final long usedDirectMemory() {
        return usedMemory(this.directArenas);
    }

    final PoolThreadCache threadCache() {
        return this.threadCache.get();
    }

    public boolean trimCurrentThreadCache() {
        PoolThreadCache ifExists = this.threadCache.getIfExists();
        if (ifExists == null) {
            return false;
        }
        ifExists.trim();
        return true;
    }

    public String dumpStats() {
        PoolArena<byte[]>[] poolArenaArr = this.heapArenas;
        int length = poolArenaArr == null ? 0 : poolArenaArr.length;
        StringBuilder sb = new StringBuilder(512);
        sb.append(length);
        sb.append(" heap arena(s):");
        sb.append(StringUtil.NEWLINE);
        if (length > 0) {
            for (PoolArena<byte[]> poolArena : this.heapArenas) {
                sb.append(poolArena);
            }
        }
        PoolArena<ByteBuffer>[] poolArenaArr2 = this.directArenas;
        int length2 = poolArenaArr2 == null ? 0 : poolArenaArr2.length;
        sb.append(length2);
        sb.append(" direct arena(s):");
        sb.append(StringUtil.NEWLINE);
        if (length2 > 0) {
            for (PoolArena<ByteBuffer> poolArena2 : this.directArenas) {
                sb.append(poolArena2);
            }
        }
        return sb.toString();
    }

    final class PoolThreadLocalCache extends FastThreadLocal<PoolThreadCache> {
        private final boolean useCacheForAllThreads;

        PoolThreadLocalCache(boolean z) {
            this.useCacheForAllThreads = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public synchronized PoolThreadCache initialValue() {
            EventExecutor eventExecutorCurrentExecutor;
            PoolArena poolArenaLeastUsedArena = leastUsedArena(PooledByteBufAllocator.this.heapArenas);
            PoolArena poolArenaLeastUsedArena2 = leastUsedArena(PooledByteBufAllocator.this.directArenas);
            Thread threadCurrentThread = Thread.currentThread();
            if (!this.useCacheForAllThreads && !(threadCurrentThread instanceof FastThreadLocalThread)) {
                return new PoolThreadCache(poolArenaLeastUsedArena, poolArenaLeastUsedArena2, 0, 0, 0, 0, 0);
            }
            PoolThreadCache poolThreadCache = new PoolThreadCache(poolArenaLeastUsedArena, poolArenaLeastUsedArena2, PooledByteBufAllocator.this.tinyCacheSize, PooledByteBufAllocator.this.smallCacheSize, PooledByteBufAllocator.this.normalCacheSize, PooledByteBufAllocator.DEFAULT_MAX_CACHED_BUFFER_CAPACITY, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL);
            if (PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS > 0 && (eventExecutorCurrentExecutor = ThreadExecutorMap.currentExecutor()) != null) {
                eventExecutorCurrentExecutor.scheduleAtFixedRate(PooledByteBufAllocator.this.trimTask, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS, PooledByteBufAllocator.DEFAULT_CACHE_TRIM_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
            }
            return poolThreadCache;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public void onRemoval(PoolThreadCache poolThreadCache) {
            poolThreadCache.free(false);
        }

        private <T> PoolArena<T> leastUsedArena(PoolArena<T>[] poolArenaArr) {
            if (poolArenaArr == null || poolArenaArr.length == 0) {
                return null;
            }
            PoolArena<T> poolArena = poolArenaArr[0];
            for (int i = 1; i < poolArenaArr.length; i++) {
                PoolArena<T> poolArena2 = poolArenaArr[i];
                if (poolArena2.numThreadCaches.get() < poolArena.numThreadCaches.get()) {
                    poolArena = poolArena2;
                }
            }
            return poolArena;
        }
    }
}
