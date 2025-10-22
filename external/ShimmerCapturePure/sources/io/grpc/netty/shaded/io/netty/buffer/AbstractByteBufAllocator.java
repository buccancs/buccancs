package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* loaded from: classes3.dex */
public abstract class AbstractByteBufAllocator implements ByteBufAllocator {
    static final int CALCULATE_THRESHOLD = 4194304;
    static final int DEFAULT_INITIAL_CAPACITY = 256;
    static final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;
    static final int DEFAULT_MAX_COMPONENTS = 16;

    static {
        ResourceLeakDetector.addExclusions(AbstractByteBufAllocator.class, "toLeakAwareBuffer");
    }

    private final boolean directByDefault;
    private final ByteBuf emptyBuf;

    protected AbstractByteBufAllocator() {
        this(false);
    }

    protected AbstractByteBufAllocator(boolean z) {
        this.directByDefault = z && PlatformDependent.hasUnsafe();
        this.emptyBuf = new EmptyByteBuf(this);
    }

    protected static ByteBuf toLeakAwareBuffer(ByteBuf byteBuf) {
        ByteBuf simpleLeakAwareByteBuf;
        ResourceLeakTracker<ByteBuf> resourceLeakTrackerTrack;
        int i = AnonymousClass1.$SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.getLevel().ordinal()];
        if (i == 1) {
            ResourceLeakTracker<ByteBuf> resourceLeakTrackerTrack2 = AbstractByteBuf.leakDetector.track(byteBuf);
            if (resourceLeakTrackerTrack2 == null) {
                return byteBuf;
            }
            simpleLeakAwareByteBuf = new SimpleLeakAwareByteBuf(byteBuf, resourceLeakTrackerTrack2);
        } else {
            if ((i != 2 && i != 3) || (resourceLeakTrackerTrack = AbstractByteBuf.leakDetector.track(byteBuf)) == null) {
                return byteBuf;
            }
            simpleLeakAwareByteBuf = new AdvancedLeakAwareByteBuf(byteBuf, resourceLeakTrackerTrack);
        }
        return simpleLeakAwareByteBuf;
    }

    protected static CompositeByteBuf toLeakAwareBuffer(CompositeByteBuf compositeByteBuf) {
        CompositeByteBuf simpleLeakAwareCompositeByteBuf;
        ResourceLeakTracker<ByteBuf> resourceLeakTrackerTrack;
        int i = AnonymousClass1.$SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.getLevel().ordinal()];
        if (i == 1) {
            ResourceLeakTracker<ByteBuf> resourceLeakTrackerTrack2 = AbstractByteBuf.leakDetector.track(compositeByteBuf);
            if (resourceLeakTrackerTrack2 == null) {
                return compositeByteBuf;
            }
            simpleLeakAwareCompositeByteBuf = new SimpleLeakAwareCompositeByteBuf(compositeByteBuf, resourceLeakTrackerTrack2);
        } else {
            if ((i != 2 && i != 3) || (resourceLeakTrackerTrack = AbstractByteBuf.leakDetector.track(compositeByteBuf)) == null) {
                return compositeByteBuf;
            }
            simpleLeakAwareCompositeByteBuf = new AdvancedLeakAwareCompositeByteBuf(compositeByteBuf, resourceLeakTrackerTrack);
        }
        return simpleLeakAwareCompositeByteBuf;
    }

    private static void validate(int i, int i2) {
        ObjectUtil.checkPositiveOrZero(i, "initialCapacity");
        if (i > i2) {
            throw new IllegalArgumentException(String.format("initialCapacity: %d (expected: not greater than maxCapacity(%d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    protected abstract ByteBuf newDirectBuffer(int i, int i2);

    protected abstract ByteBuf newHeapBuffer(int i, int i2);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer() {
        if (this.directByDefault) {
            return directBuffer();
        }
        return heapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i) {
        if (this.directByDefault) {
            return directBuffer(i);
        }
        return heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i, int i2) {
        if (this.directByDefault) {
            return directBuffer(i, i2);
        }
        return heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer() {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(256);
        }
        return heapBuffer(256);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i) {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(i);
        }
        return heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i, int i2) {
        if (PlatformDependent.hasUnsafe() || isDirectBufferPooled()) {
            return directBuffer(i, i2);
        }
        return heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer() {
        return heapBuffer(256, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i) {
        return heapBuffer(i, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this.emptyBuf;
        }
        validate(i, i2);
        return newHeapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer() {
        return directBuffer(256, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i) {
        return directBuffer(i, Integer.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this.emptyBuf;
        }
        validate(i, i2);
        return newDirectBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer() {
        if (this.directByDefault) {
            return compositeDirectBuffer();
        }
        return compositeHeapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer(int i) {
        if (this.directByDefault) {
            return compositeDirectBuffer(i);
        }
        return compositeHeapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer() {
        return compositeHeapBuffer(16);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer(int i) {
        return toLeakAwareBuffer(new CompositeByteBuf(this, false, i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer() {
        return compositeDirectBuffer(16);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer(int i) {
        return toLeakAwareBuffer(new CompositeByteBuf(this, true, i));
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "(directByDefault: " + this.directByDefault + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public int calculateNewCapacity(int i, int i2) {
        ObjectUtil.checkPositiveOrZero(i, "minNewCapacity");
        if (i > i2) {
            throw new IllegalArgumentException(String.format("minNewCapacity: %d (expected: not greater than maxCapacity(%d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i == 4194304) {
            return 4194304;
        }
        if (i > 4194304) {
            int i3 = (i / 4194304) * 4194304;
            return i3 > i2 - 4194304 ? i2 : i3 + 4194304;
        }
        int i4 = 64;
        while (i4 < i) {
            i4 <<= 1;
        }
        return Math.min(i4, i2);
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$util$ResourceLeakDetector$Level;

        static {
            int[] iArr = new int[ResourceLeakDetector.Level.values().length];
            $SwitchMap$io$netty$util$ResourceLeakDetector$Level = iArr;
            try {
                iArr[ResourceLeakDetector.Level.SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.Level.ADVANCED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector.Level.PARANOID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
