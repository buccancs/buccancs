package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.nio.ByteOrder;

import kotlin.UShort;

/* loaded from: classes3.dex */
abstract class AbstractUnsafeSwappedByteBuf extends SwappedByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean nativeByteOrder;
    private final AbstractByteBuf wrapped;

    AbstractUnsafeSwappedByteBuf(AbstractByteBuf abstractByteBuf) {
        super(abstractByteBuf);
        this.wrapped = abstractByteBuf;
        this.nativeByteOrder = PlatformDependent.BIG_ENDIAN_NATIVE_ORDER == (order() == ByteOrder.BIG_ENDIAN);
    }

    protected abstract int _getInt(AbstractByteBuf abstractByteBuf, int i);

    protected abstract long _getLong(AbstractByteBuf abstractByteBuf, int i);

    protected abstract short _getShort(AbstractByteBuf abstractByteBuf, int i);

    protected abstract void _setInt(AbstractByteBuf abstractByteBuf, int i, int i2);

    protected abstract void _setLong(AbstractByteBuf abstractByteBuf, int i, long j);

    protected abstract void _setShort(AbstractByteBuf abstractByteBuf, int i, short s);

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final long getLong(int i) {
        this.wrapped.checkIndex(i, 8);
        long j_getLong = _getLong(this.wrapped, i);
        return this.nativeByteOrder ? j_getLong : Long.reverseBytes(j_getLong);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final char getChar(int i) {
        return (char) getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final long getUnsignedInt(int i) {
        return getInt(i) & 4294967295L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int getInt(int i) {
        this.wrapped.checkIndex(i, 4);
        int i_getInt = _getInt(this.wrapped, i);
        return this.nativeByteOrder ? i_getInt : Integer.reverseBytes(i_getInt);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int getUnsignedShort(int i) {
        return getShort(i) & UShort.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final short getShort(int i) {
        this.wrapped.checkIndex(i, 2);
        short s_getShort = _getShort(this.wrapped, i);
        return this.nativeByteOrder ? s_getShort : Short.reverseBytes(s_getShort);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setShort(int i, int i2) {
        this.wrapped.checkIndex(i, 2);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        short sReverseBytes = (short) i2;
        if (!this.nativeByteOrder) {
            sReverseBytes = Short.reverseBytes(sReverseBytes);
        }
        _setShort(abstractByteBuf, i, sReverseBytes);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setInt(int i, int i2) {
        this.wrapped.checkIndex(i, 4);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        if (!this.nativeByteOrder) {
            i2 = Integer.reverseBytes(i2);
        }
        _setInt(abstractByteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setLong(int i, long j) {
        this.wrapped.checkIndex(i, 8);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        if (!this.nativeByteOrder) {
            j = Long.reverseBytes(j);
        }
        _setLong(abstractByteBuf, i, j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setChar(int i, int i2) {
        setShort(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setFloat(int i, float f) {
        setInt(i, Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setDouble(int i, double d) {
        setLong(i, Double.doubleToRawLongBits(d));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeShort(int i) {
        this.wrapped.ensureWritable0(2);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        int i2 = abstractByteBuf.writerIndex;
        short sReverseBytes = (short) i;
        if (!this.nativeByteOrder) {
            sReverseBytes = Short.reverseBytes(sReverseBytes);
        }
        _setShort(abstractByteBuf, i2, sReverseBytes);
        this.wrapped.writerIndex += 2;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeInt(int i) {
        this.wrapped.ensureWritable0(4);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        int i2 = abstractByteBuf.writerIndex;
        if (!this.nativeByteOrder) {
            i = Integer.reverseBytes(i);
        }
        _setInt(abstractByteBuf, i2, i);
        this.wrapped.writerIndex += 4;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeLong(long j) {
        this.wrapped.ensureWritable0(8);
        AbstractByteBuf abstractByteBuf = this.wrapped;
        int i = abstractByteBuf.writerIndex;
        if (!this.nativeByteOrder) {
            j = Long.reverseBytes(j);
        }
        _setLong(abstractByteBuf, i, j);
        this.wrapped.writerIndex += 8;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeChar(int i) {
        writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeFloat(float f) {
        writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf writeDouble(double d) {
        writeLong(Double.doubleToRawLongBits(d));
        return this;
    }
}
