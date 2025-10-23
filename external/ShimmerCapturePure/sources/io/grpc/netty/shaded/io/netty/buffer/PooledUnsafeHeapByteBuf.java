package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* loaded from: classes3.dex */
final class PooledUnsafeHeapByteBuf extends PooledHeapByteBuf {
    private static final ObjectPool<PooledUnsafeHeapByteBuf> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<PooledUnsafeHeapByteBuf>() { // from class: io.grpc.netty.shaded.io.netty.buffer.PooledUnsafeHeapByteBuf.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
        public PooledUnsafeHeapByteBuf newObject(ObjectPool.Handle<PooledUnsafeHeapByteBuf> handle) {
            return new PooledUnsafeHeapByteBuf(handle, 0);
        }
    });

    private PooledUnsafeHeapByteBuf(ObjectPool.Handle<PooledUnsafeHeapByteBuf> handle, int i) {
        super(handle, i);
    }

    static PooledUnsafeHeapByteBuf newUnsafeInstance(int i) {
        PooledUnsafeHeapByteBuf pooledUnsafeHeapByteBuf = RECYCLER.get();
        pooledUnsafeHeapByteBuf.reuse(i);
        return pooledUnsafeHeapByteBuf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected byte _getByte(int i) {
        return UnsafeByteBufUtil.getByte((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShort(int i) {
        return UnsafeByteBufUtil.getShort((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShortLE(int i) {
        return UnsafeByteBufUtil.getShortLE((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMedium(int i) {
        return UnsafeByteBufUtil.getUnsignedMedium((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMediumLE(int i) {
        return UnsafeByteBufUtil.getUnsignedMediumLE((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getInt(int i) {
        return UnsafeByteBufUtil.getInt((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getIntLE(int i) {
        return UnsafeByteBufUtil.getIntLE((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLong(int i) {
        return UnsafeByteBufUtil.getLong((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLongLE(int i) {
        return UnsafeByteBufUtil.getLongLE((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setByte(int i, int i2) {
        UnsafeByteBufUtil.setByte((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShort(int i, int i2) {
        UnsafeByteBufUtil.setShort((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShortLE(int i, int i2) {
        UnsafeByteBufUtil.setShortLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMedium(int i, int i2) {
        UnsafeByteBufUtil.setMedium((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMediumLE(int i, int i2) {
        UnsafeByteBufUtil.setMediumLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setInt(int i, int i2) {
        UnsafeByteBufUtil.setInt((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setIntLE(int i, int i2) {
        UnsafeByteBufUtil.setIntLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLong(int i, long j) {
        UnsafeByteBufUtil.setLong((byte[]) this.memory, idx(i), j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override
    // io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLongLE(int i, long j) {
        UnsafeByteBufUtil.setLongLE((byte[]) this.memory, idx(i), j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setZero(int i, int i2) {
        if (PlatformDependent.javaVersion() >= 7) {
            checkIndex(i, i2);
            UnsafeByteBufUtil.setZero((byte[]) this.memory, idx(i), i2);
            return this;
        }
        return super.setZero(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeZero(int i) {
        if (PlatformDependent.javaVersion() >= 7) {
            ensureWritable(i);
            int i2 = this.writerIndex;
            UnsafeByteBufUtil.setZero((byte[]) this.memory, idx(i2), i);
            this.writerIndex = i2 + i;
            return this;
        }
        return super.writeZero(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    @Deprecated
    protected SwappedByteBuf newSwappedByteBuf() {
        if (PlatformDependent.isUnaligned()) {
            return new UnsafeHeapSwappedByteBuf(this);
        }
        return super.newSwappedByteBuf();
    }
}
