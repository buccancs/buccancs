package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
final class ReadOnlyUnsafeDirectByteBuf extends ReadOnlyByteBufferBuf {
    private final long memoryAddress;

    ReadOnlyUnsafeDirectByteBuf(ByteBufAllocator byteBufAllocator, ByteBuffer byteBuffer) {
        super(byteBufAllocator, byteBuffer);
        this.memoryAddress = PlatformDependent.directBufferAddress(this.buffer);
    }

    private long addr(int i) {
        return this.memoryAddress + i;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return true;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        return this.memoryAddress;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected byte _getByte(int i) {
        return UnsafeByteBufUtil.getByte(addr(i));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShort(int i) {
        return UnsafeByteBufUtil.getShort(addr(i));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMedium(int i) {
        return UnsafeByteBufUtil.getUnsignedMedium(addr(i));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getInt(int i) {
        return UnsafeByteBufUtil.getInt(addr(i));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLong(int i) {
        return UnsafeByteBufUtil.getLong(addr(i));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkIndex(i, i3);
        ObjectUtil.checkNotNull(byteBuf, "dst");
        if (i2 < 0 || i2 > byteBuf.capacity() - i3) {
            throw new IndexOutOfBoundsException("dstIndex: " + i2);
        }
        if (byteBuf.hasMemoryAddress()) {
            PlatformDependent.copyMemory(addr(i), i2 + byteBuf.memoryAddress(), i3);
        } else if (byteBuf.hasArray()) {
            PlatformDependent.copyMemory(addr(i), byteBuf.array(), byteBuf.arrayOffset() + i2, i3);
        } else {
            byteBuf.setBytes(i2, this, i, i3);
        }
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkIndex(i, i3);
        ObjectUtil.checkNotNull(bArr, "dst");
        if (i2 < 0 || i2 > bArr.length - i3) {
            throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
        }
        if (i3 != 0) {
            PlatformDependent.copyMemory(addr(i), bArr, i2, i3);
        }
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.ReadOnlyByteBufferBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf byteBufDirectBuffer = alloc().directBuffer(i2, maxCapacity());
        if (i2 != 0) {
            if (byteBufDirectBuffer.hasMemoryAddress()) {
                PlatformDependent.copyMemory(addr(i), byteBufDirectBuffer.memoryAddress(), i2);
                byteBufDirectBuffer.setIndex(0, i2);
            } else {
                byteBufDirectBuffer.writeBytes(this, i, i2);
            }
        }
        return byteBufDirectBuffer;
    }
}
