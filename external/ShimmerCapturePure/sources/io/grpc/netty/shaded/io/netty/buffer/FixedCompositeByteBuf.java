package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.Collections;

import kotlin.UShort;

/* loaded from: classes3.dex */
final class FixedCompositeByteBuf extends AbstractReferenceCountedByteBuf {
    private static final ByteBuf[] EMPTY = {Unpooled.EMPTY_BUFFER};
    private final ByteBufAllocator allocator;
    private final ByteBuf[] buffers;
    private final int capacity;
    private final boolean direct;
    private final int nioBufferCount;
    private final ByteOrder order;

    FixedCompositeByteBuf(ByteBufAllocator byteBufAllocator, ByteBuf... byteBufArr) {
        super(Integer.MAX_VALUE);
        if (byteBufArr.length == 0) {
            this.buffers = EMPTY;
            this.order = ByteOrder.BIG_ENDIAN;
            this.nioBufferCount = 1;
            this.capacity = 0;
            this.direct = Unpooled.EMPTY_BUFFER.isDirect();
        } else {
            ByteBuf byteBuf = byteBufArr[0];
            this.buffers = byteBufArr;
            int iNioBufferCount = byteBuf.nioBufferCount();
            int i = byteBuf.readableBytes();
            this.order = byteBuf.order();
            boolean z = true;
            for (int i2 = 1; i2 < byteBufArr.length; i2++) {
                ByteBuf byteBuf2 = byteBufArr[i2];
                if (byteBuf2.order() != this.order) {
                    throw new IllegalArgumentException("All ByteBufs need to have same ByteOrder");
                }
                iNioBufferCount += byteBuf2.nioBufferCount();
                i += byteBuf2.readableBytes();
                if (!byteBuf2.isDirect()) {
                    z = false;
                }
            }
            this.nioBufferCount = iNioBufferCount;
            this.capacity = i;
            this.direct = z;
        }
        setIndex(0, capacity());
        this.allocator = byteBufAllocator;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.allocator;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        return this.capacity;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return this.direct;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxCapacity() {
        return this.capacity;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return this.nioBufferCount;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return this.order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setByte(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShort(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShortLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMedium(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMediumLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setInt(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setIntLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLong(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLongLE(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        throw new ReadOnlyBufferException();
    }

    private Component findComponent(int i) {
        Component component;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            ByteBuf[] byteBufArr = this.buffers;
            if (i2 < byteBufArr.length) {
                ByteBuf byteBuf = byteBufArr[i2];
                if (byteBuf instanceof Component) {
                    Component component2 = (Component) byteBuf;
                    component = component2;
                    byteBuf = component2.buf;
                } else {
                    component = null;
                }
                i3 += byteBuf.readableBytes();
                if (i < i3) {
                    if (component != null) {
                        return component;
                    }
                    Component component3 = new Component(i2, i3 - byteBuf.readableBytes(), byteBuf);
                    this.buffers[i2] = component3;
                    return component3;
                }
                i2++;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private ByteBuf buffer(int i) {
        ByteBuf byteBuf = this.buffers[i];
        return byteBuf instanceof Component ? ((Component) byteBuf).buf : byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        return _getByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected byte _getByte(int i) {
        Component componentFindComponent = findComponent(i);
        return componentFindComponent.buf.getByte(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShort(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 2 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
            }
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return componentFindComponent.buf.getShort(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShortLE(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 2 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
            }
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return componentFindComponent.buf.getShortLE(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMedium(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 3 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getByte(i + 2) & 255) | ((_getShort(i) & UShort.MAX_VALUE) << 8);
            }
            return ((_getByte(i + 2) & 255) << 16) | (_getShort(i) & UShort.MAX_VALUE);
        }
        return componentFindComponent.buf.getUnsignedMedium(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMediumLE(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 3 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getByte(i + 2) & 255) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
            }
            return (_getByte(i + 2) & 255) | ((_getShortLE(i) & UShort.MAX_VALUE) << 8);
        }
        return componentFindComponent.buf.getUnsignedMediumLE(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getInt(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 4 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getShort(i + 2) & UShort.MAX_VALUE) | ((_getShort(i) & UShort.MAX_VALUE) << 16);
            }
            return ((_getShort(i + 2) & UShort.MAX_VALUE) << 16) | (_getShort(i) & UShort.MAX_VALUE);
        }
        return componentFindComponent.buf.getInt(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getIntLE(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 4 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getShortLE(i + 2) & UShort.MAX_VALUE) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
            }
            return (_getShortLE(i + 2) & UShort.MAX_VALUE) | ((_getShortLE(i) & UShort.MAX_VALUE) << 16);
        }
        return componentFindComponent.buf.getIntLE(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLong(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 8 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getInt(i) & 4294967295L) << 32) | (_getInt(i + 4) & 4294967295L);
            }
            return (_getInt(i) & 4294967295L) | ((4294967295L & _getInt(i + 4)) << 32);
        }
        return componentFindComponent.buf.getLong(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLongLE(int i) {
        Component componentFindComponent = findComponent(i);
        if (i + 8 > componentFindComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getIntLE(i) & 4294967295L) | ((4294967295L & _getIntLE(i + 4)) << 32);
            }
            return ((_getIntLE(i) & 4294967295L) << 32) | (_getIntLE(i + 4) & 4294967295L);
        }
        return componentFindComponent.buf.getLongLE(i - componentFindComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        Component componentFindComponent = findComponent(i);
        int i4 = componentFindComponent.index;
        int i5 = componentFindComponent.offset;
        ByteBuf byteBufBuffer = componentFindComponent.buf;
        while (true) {
            int i6 = i - i5;
            int iMin = Math.min(i3, byteBufBuffer.readableBytes() - i6);
            byteBufBuffer.getBytes(i6, bArr, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            i5 += byteBufBuffer.readableBytes();
            if (i3 <= 0) {
                return this;
            }
            i4++;
            byteBufBuffer = buffer(i4);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        int iRemaining = byteBuffer.remaining();
        checkIndex(i, iRemaining);
        if (iRemaining == 0) {
            return this;
        }
        try {
            Component componentFindComponent = findComponent(i);
            int i2 = componentFindComponent.index;
            int i3 = componentFindComponent.offset;
            ByteBuf byteBufBuffer = componentFindComponent.buf;
            while (true) {
                int i4 = i - i3;
                int iMin = Math.min(iRemaining, byteBufBuffer.readableBytes() - i4);
                byteBuffer.limit(byteBuffer.position() + iMin);
                byteBufBuffer.getBytes(i4, byteBuffer);
                i += iMin;
                iRemaining -= iMin;
                i3 += byteBufBuffer.readableBytes();
                if (iRemaining <= 0) {
                    return this;
                }
                i2++;
                byteBufBuffer = buffer(i2);
            }
        } finally {
            byteBuffer.limit(iLimit);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        Component componentFindComponent = findComponent(i);
        int i4 = componentFindComponent.index;
        int i5 = componentFindComponent.offset;
        ByteBuf byteBufBuffer = componentFindComponent.buf;
        while (true) {
            int i6 = i - i5;
            int iMin = Math.min(i3, byteBufBuffer.readableBytes() - i6);
            byteBufBuffer.getBytes(i6, byteBuf, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            i5 += byteBufBuffer.readableBytes();
            if (i3 <= 0) {
                return this;
            }
            i4++;
            byteBufBuffer = buffer(i4);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return gatheringByteChannel.write(internalNioBuffer(i, i2));
        }
        long jWrite = gatheringByteChannel.write(nioBuffers(i, i2));
        if (jWrite > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) jWrite;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return fileChannel.write(internalNioBuffer(i, i2), j);
        }
        long jWrite = 0;
        for (int i3 = 0; i3 < nioBuffers(i, i2).length; i3++) {
            jWrite += fileChannel.write(r7[i3], j + jWrite);
        }
        if (jWrite > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) jWrite;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        Component componentFindComponent = findComponent(i);
        int i3 = componentFindComponent.index;
        int i4 = componentFindComponent.offset;
        ByteBuf byteBufBuffer = componentFindComponent.buf;
        while (true) {
            int i5 = i - i4;
            int iMin = Math.min(i2, byteBufBuffer.readableBytes() - i5);
            byteBufBuffer.getBytes(i5, outputStream, iMin);
            i += iMin;
            i2 -= iMin;
            i4 += byteBufBuffer.readableBytes();
            if (i2 <= 0) {
                return this;
            }
            i3++;
            byteBufBuffer = buffer(i3);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf byteBufBuffer = alloc().buffer(i2);
        try {
            byteBufBuffer.writeBytes(this, i, i2);
            return byteBufBuffer;
        } catch (Throwable th) {
            byteBufBuffer.release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        if (this.buffers.length == 1) {
            ByteBuf byteBufBuffer = buffer(0);
            if (byteBufBuffer.nioBufferCount() == 1) {
                return byteBufBuffer.nioBuffer(i, i2);
            }
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : nioBuffers(i, i2)) {
            byteBufferOrder.put(byteBuffer);
        }
        byteBufferOrder.flip();
        return byteBufferOrder;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        if (this.buffers.length == 1) {
            return buffer(0).internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return EmptyArrays.EMPTY_BYTE_BUFFERS;
        }
        RecyclableArrayList recyclableArrayListNewInstance = RecyclableArrayList.newInstance(this.buffers.length);
        try {
            Component componentFindComponent = findComponent(i);
            int i3 = componentFindComponent.index;
            int i4 = componentFindComponent.offset;
            ByteBuf byteBufBuffer = componentFindComponent.buf;
            while (true) {
                int i5 = i - i4;
                int iMin = Math.min(i2, byteBufBuffer.readableBytes() - i5);
                int iNioBufferCount = byteBufBuffer.nioBufferCount();
                if (iNioBufferCount == 0) {
                    throw new UnsupportedOperationException();
                }
                if (iNioBufferCount == 1) {
                    recyclableArrayListNewInstance.add(byteBufBuffer.nioBuffer(i5, iMin));
                } else {
                    Collections.addAll(recyclableArrayListNewInstance, byteBufBuffer.nioBuffers(i5, iMin));
                }
                i += iMin;
                i2 -= iMin;
                i4 += byteBufBuffer.readableBytes();
                if (i2 > 0) {
                    i3++;
                    byteBufBuffer = buffer(i3);
                } else {
                    return (ByteBuffer[]) recyclableArrayListNewInstance.toArray(new ByteBuffer[0]);
                }
            }
        } finally {
            recyclableArrayListNewInstance.recycle();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        int length = this.buffers.length;
        if (length == 0) {
            return true;
        }
        if (length != 1) {
            return false;
        }
        return buffer(0).hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        int length = this.buffers.length;
        if (length == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (length == 1) {
            return buffer(0).array();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        int length = this.buffers.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return buffer(0).arrayOffset();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        int length = this.buffers.length;
        if (length == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (length != 1) {
            return false;
        }
        return buffer(0).hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        int length = this.buffers.length;
        if (length == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (length == 1) {
            return buffer(0).memoryAddress();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    protected void deallocate() {
        for (int i = 0; i < this.buffers.length; i++) {
            buffer(i).release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.buffers.length + ')';
    }

    private static final class Component extends WrappedByteBuf {
        private final int endOffset;
        private final int index;
        private final int offset;

        Component(int i, int i2, ByteBuf byteBuf) {
            super(byteBuf);
            this.index = i;
            this.offset = i2;
            this.endOffset = i2 + byteBuf.readableBytes();
        }
    }
}
