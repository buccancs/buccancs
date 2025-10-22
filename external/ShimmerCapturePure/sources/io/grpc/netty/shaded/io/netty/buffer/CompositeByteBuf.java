package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.IllegalReferenceCountException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import kotlin.UShort;

/* loaded from: classes3.dex */
public class CompositeByteBuf extends AbstractReferenceCountedByteBuf implements Iterable<ByteBuf> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final ByteWrapper<byte[]> BYTE_ARRAY_WRAPPER = new ByteWrapper<byte[]>() { // from class: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.1
        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public ByteBuf wrap(byte[] bArr) {
            return Unpooled.wrappedBuffer(bArr);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public boolean isEmpty(byte[] bArr) {
            return bArr.length == 0;
        }
    };
    static final ByteWrapper<ByteBuffer> BYTE_BUFFER_WRAPPER = new ByteWrapper<ByteBuffer>() { // from class: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.2
        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public ByteBuf wrap(ByteBuffer byteBuffer) {
            return Unpooled.wrappedBuffer(byteBuffer);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public boolean isEmpty(ByteBuffer byteBuffer) {
            return !byteBuffer.hasRemaining();
        }
    };
    private static final ByteBuffer EMPTY_NIO_BUFFER = Unpooled.EMPTY_BUFFER.nioBuffer();
    private static final Iterator<ByteBuf> EMPTY_ITERATOR = Collections.emptyList().iterator();
    private final ByteBufAllocator alloc;
    private final boolean direct;
    private final int maxNumComponents;
    private int componentCount;
    private Component[] components;
    private boolean freed;
    private Component lastAccessed;

    private CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, int i2) {
        super(Integer.MAX_VALUE);
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        if (i < 1) {
            throw new IllegalArgumentException("maxNumComponents: " + i + " (expected: >= 1)");
        }
        this.direct = z;
        this.maxNumComponents = i;
        this.components = newCompArray(i2, i);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i) {
        this(byteBufAllocator, z, i, 0);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf... byteBufArr) {
        this(byteBufAllocator, z, i, byteBufArr, 0);
    }

    CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf[] byteBufArr, int i2) {
        this(byteBufAllocator, z, i, byteBufArr.length - i2);
        addComponents0(false, 0, byteBufArr, i2);
        consolidateIfNeeded();
        setIndex0(0, capacity());
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, Iterable<ByteBuf> iterable) {
        this(byteBufAllocator, z, i, iterable instanceof Collection ? ((Collection) iterable).size() : 0);
        addComponents(false, 0, iterable);
        setIndex(0, capacity());
    }

    <T> CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        this(byteBufAllocator, z, i, tArr.length - i2);
        addComponents0(false, 0, byteWrapper, tArr, i2);
        consolidateIfNeeded();
        setIndex(0, capacity());
    }

    CompositeByteBuf(ByteBufAllocator byteBufAllocator) {
        super(Integer.MAX_VALUE);
        this.alloc = byteBufAllocator;
        this.direct = false;
        this.maxNumComponents = 0;
        this.components = null;
    }

    private static Component[] newCompArray(int i, int i2) {
        return new Component[Math.max(i, Math.min(16, i2))];
    }

    private static void checkForOverflow(int i, int i2) {
        if (i + i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException("Can't increase by " + i2 + " as capacity(" + i + ") would overflow 2147483647");
    }

    private static ByteBuf ensureAccessible(ByteBuf byteBuf) {
        if (!checkAccessible || byteBuf.isAccessible()) {
            return byteBuf;
        }
        throw new IllegalReferenceCountException(0);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.alloc;
    }

    @Override
        // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    boolean isAccessible() {
        return !this.freed;
    }

    public int maxNumComponents() {
        return this.maxNumComponents;
    }

    public int numComponents() {
        return this.componentCount;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf touch() {
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf touch(Object obj) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    public CompositeByteBuf addComponent(ByteBuf byteBuf) {
        return addComponent(false, byteBuf);
    }

    public CompositeByteBuf addComponents(ByteBuf... byteBufArr) {
        return addComponents(false, byteBufArr);
    }

    public CompositeByteBuf addComponents(Iterable<ByteBuf> iterable) {
        return addComponents(false, iterable);
    }

    public CompositeByteBuf addComponent(int i, ByteBuf byteBuf) {
        return addComponent(false, i, byteBuf);
    }

    public CompositeByteBuf addComponent(boolean z, ByteBuf byteBuf) {
        return addComponent(z, this.componentCount, byteBuf);
    }

    public CompositeByteBuf addComponents(boolean z, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(z, this.componentCount, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponents(boolean z, Iterable<ByteBuf> iterable) {
        return addComponents(z, this.componentCount, iterable);
    }

    public CompositeByteBuf addComponent(boolean z, int i, ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, "buffer");
        addComponent0(z, i, byteBuf);
        consolidateIfNeeded();
        return this;
    }

    private int addComponent0(boolean z, int i, ByteBuf byteBuf) {
        boolean z2 = false;
        try {
            checkComponentIndex(i);
            Component componentNewComponent = newComponent(ensureAccessible(byteBuf), 0);
            int length = componentNewComponent.length();
            checkForOverflow(capacity(), length);
            addComp(i, componentNewComponent);
            z2 = true;
            if (length > 0 && i < this.componentCount - 1) {
                updateComponentOffsets(i);
            } else if (i > 0) {
                componentNewComponent.reposition(this.components[i - 1].endOffset);
            }
            if (z) {
                this.writerIndex += length;
            }
            return i;
        } catch (Throwable th) {
            if (!z2) {
                byteBuf.release();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.Component newComponent(io.grpc.netty.shaded.io.netty.buffer.ByteBuf r10, int r11) {
        /*
            r9 = this;
            int r2 = r10.readerIndex()
            int r6 = r10.readableBytes()
            r0 = r10
        L9:
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf
            if (r1 != 0) goto L5f
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf
            if (r1 == 0) goto L12
            goto L5f
        L12:
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.AbstractUnpooledSlicedByteBuf
            if (r1 == 0) goto L25
            r1 = r0
            io.grpc.netty.shaded.io.netty.buffer.AbstractUnpooledSlicedByteBuf r1 = (io.grpc.netty.shaded.io.netty.buffer.AbstractUnpooledSlicedByteBuf) r1
            r3 = 0
            int r1 = r1.idx(r3)
            int r1 = r1 + r2
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r0 = r0.unwrap()
        L23:
            r4 = r1
            goto L41
        L25:
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.PooledSlicedByteBuf
            if (r1 == 0) goto L34
            r1 = r0
            io.grpc.netty.shaded.io.netty.buffer.PooledSlicedByteBuf r1 = (io.grpc.netty.shaded.io.netty.buffer.PooledSlicedByteBuf) r1
            int r1 = r1.adjustment
            int r1 = r1 + r2
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r0 = r0.unwrap()
            goto L23
        L34:
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.DuplicatedByteBuf
            if (r1 != 0) goto L3c
            boolean r1 = r0 instanceof io.grpc.netty.shaded.io.netty.buffer.PooledDuplicatedByteBuf
            if (r1 == 0) goto L40
        L3c:
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r0 = r0.unwrap()
        L40:
            r4 = r2
        L41:
            int r1 = r10.capacity()
            if (r1 != r6) goto L49
            r7 = r10
            goto L4b
        L49:
            r1 = 0
            r7 = r1
        L4b:
            io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component r8 = new io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r1 = r10.order(r1)
            java.nio.ByteOrder r10 = java.nio.ByteOrder.BIG_ENDIAN
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r3 = r0.order(r10)
            r0 = r8
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r8
        L5f:
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r0 = r0.unwrap()
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.newComponent(io.grpc.netty.shaded.io.netty.buffer.ByteBuf, int):io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component");
    }

    public CompositeByteBuf addComponents(int i, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(false, i, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    private CompositeByteBuf addComponents0(boolean z, int i, ByteBuf[] byteBufArr, int i2) {
        int length = byteBufArr.length;
        int i3 = length - i2;
        int iCapacity = capacity();
        int i4 = 0;
        for (ByteBuf byteBuf : byteBufArr) {
            i4 += byteBuf.readableBytes();
            checkForOverflow(iCapacity, i4);
        }
        int i5 = Integer.MAX_VALUE;
        try {
            checkComponentIndex(i);
            shiftComps(i, i3);
            int i6 = i > 0 ? this.components[i - 1].endOffset : 0;
            i5 = i;
            while (i2 < length) {
                ByteBuf byteBuf2 = byteBufArr[i2];
                if (byteBuf2 == null) {
                    break;
                }
                Component componentNewComponent = newComponent(ensureAccessible(byteBuf2), i6);
                this.components[i5] = componentNewComponent;
                i6 = componentNewComponent.endOffset;
                i2++;
                i5++;
            }
            return this;
        } finally {
            if (i5 < this.componentCount) {
                int i7 = i3 + i;
                if (i5 < i7) {
                    removeCompRange(i5, i7);
                    while (i2 < length) {
                        ReferenceCountUtil.safeRelease(byteBufArr[i2]);
                        i2++;
                    }
                }
                updateComponentOffsets(i5);
            }
            if (z && i5 > i && i5 <= this.componentCount) {
                this.writerIndex += this.components[i5 - 1].endOffset - this.components[i].offset;
            }
        }
    }

    private <T> int addComponents0(boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        int i3;
        checkComponentIndex(i);
        int length = tArr.length;
        while (i2 < length) {
            T t = tArr[i2];
            if (t == null) {
                break;
            }
            if (!byteWrapper.isEmpty(t) && (i = addComponent0(z, i, byteWrapper.wrap(t)) + 1) > (i3 = this.componentCount)) {
                i = i3;
            }
            i2++;
        }
        return i;
    }

    public CompositeByteBuf addComponents(int i, Iterable<ByteBuf> iterable) {
        return addComponents(false, i, iterable);
    }

    public CompositeByteBuf addFlattenedComponents(boolean z, ByteBuf byteBuf) throws Throwable {
        CompositeByteBuf compositeByteBuf;
        int i;
        int i2;
        Component[] componentArr;
        int i3;
        ObjectUtil.checkNotNull(byteBuf, "buffer");
        int i4 = byteBuf.readerIndex();
        int iWriterIndex = byteBuf.writerIndex();
        if (i4 == iWriterIndex) {
            byteBuf.release();
            return this;
        }
        if (!(byteBuf instanceof CompositeByteBuf)) {
            addComponent0(z, this.componentCount, byteBuf);
            consolidateIfNeeded();
            return this;
        }
        if (byteBuf instanceof WrappedCompositeByteBuf) {
            compositeByteBuf = (CompositeByteBuf) byteBuf.unwrap();
        } else {
            compositeByteBuf = (CompositeByteBuf) byteBuf;
        }
        int i5 = iWriterIndex - i4;
        compositeByteBuf.checkIndex(i4, i5);
        Component[] componentArr2 = compositeByteBuf.components;
        int i6 = this.componentCount;
        int i7 = this.writerIndex;
        try {
            int componentIndex0 = compositeByteBuf.toComponentIndex0(i4);
            int iCapacity = capacity();
            while (true) {
                Component component = componentArr2[componentIndex0];
                int iMax = Math.max(i4, component.offset);
                int iMin = Math.min(iWriterIndex, component.endOffset);
                int i8 = iMin - iMax;
                if (i8 > 0) {
                    i2 = i4;
                    componentArr = componentArr2;
                    i = i6;
                    i3 = iMin;
                    try {
                        addComp(this.componentCount, new Component(component.srcBuf.retain(), component.srcIdx(iMax), component.buf, component.idx(iMax), iCapacity, i8, null));
                    } catch (Throwable th) {
                        th = th;
                        if (byteBuf != null) {
                            if (z) {
                                this.writerIndex = i7;
                            }
                            int i9 = i;
                            for (int i10 = this.componentCount - 1; i10 >= i9; i10--) {
                                this.components[i10].free();
                                removeComp(i10);
                            }
                        }
                        throw th;
                    }
                } else {
                    i2 = i4;
                    componentArr = componentArr2;
                    i = i6;
                    i3 = iMin;
                }
                if (iWriterIndex == i3) {
                    break;
                }
                iCapacity += i8;
                componentIndex0++;
                i4 = i2;
                componentArr2 = componentArr;
                i6 = i;
            }
            if (z) {
                this.writerIndex = i5 + i7;
            }
            consolidateIfNeeded();
            byteBuf.release();
            return this;
        } catch (Throwable th2) {
            th = th2;
            i = i6;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CompositeByteBuf addComponents(boolean z, int i, Iterable<ByteBuf> iterable) {
        ByteBuf byteBuf;
        if (iterable instanceof ByteBuf) {
            return addComponent(z, i, (ByteBuf) iterable);
        }
        ObjectUtil.checkNotNull(iterable, "buffers");
        Iterator it2 = iterable.iterator();
        try {
            checkComponentIndex(i);
            while (it2.hasNext() && (byteBuf = (ByteBuf) it2.next()) != null) {
                i = Math.min(addComponent0(z, i, byteBuf) + 1, this.componentCount);
            }
            consolidateIfNeeded();
            return this;
        } finally {
            while (it2.hasNext()) {
                ReferenceCountUtil.safeRelease(it2.next());
            }
        }
    }

    private void consolidateIfNeeded() {
        int i = this.componentCount;
        if (i > this.maxNumComponents) {
            consolidate0(0, i);
        }
    }

    private void checkComponentIndex(int i) {
        ensureAccessible();
        if (i < 0 || i > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d (expected: >= 0 && <= numComponents(%d))", Integer.valueOf(i), Integer.valueOf(this.componentCount)));
        }
    }

    private void checkComponentIndex(int i, int i2) {
        ensureAccessible();
        if (i < 0 || i + i2 > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d, numComponents: %d (expected: cIndex >= 0 && cIndex + numComponents <= totalNumComponents(%d))", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.componentCount)));
        }
    }

    private void updateComponentOffsets(int i) {
        int i2 = this.componentCount;
        if (i2 <= i) {
            return;
        }
        int i3 = i > 0 ? this.components[i - 1].endOffset : 0;
        while (i < i2) {
            Component component = this.components[i];
            component.reposition(i3);
            i3 = component.endOffset;
            i++;
        }
    }

    public CompositeByteBuf removeComponent(int i) {
        checkComponentIndex(i);
        Component component = this.components[i];
        if (this.lastAccessed == component) {
            this.lastAccessed = null;
        }
        component.free();
        removeComp(i);
        if (component.length() > 0) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public CompositeByteBuf removeComponents(int i, int i2) {
        checkComponentIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int i3 = i2 + i;
        boolean z = false;
        for (int i4 = i; i4 < i3; i4++) {
            Component component = this.components[i4];
            if (component.length() > 0) {
                z = true;
            }
            if (this.lastAccessed == component) {
                this.lastAccessed = null;
            }
            component.free();
        }
        removeCompRange(i, i3);
        if (z) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public Iterator<ByteBuf> iterator() {
        ensureAccessible();
        return this.componentCount == 0 ? EMPTY_ITERATOR : new CompositeByteBufIterator();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int forEachByteAsc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int iForEachByte;
        if (i2 <= i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = i2 - i;
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int iIdx = component.idx(i);
                int iMin = Math.min(i3, component.endOffset - i);
                if (byteBuf instanceof AbstractByteBuf) {
                    iForEachByte = ((AbstractByteBuf) byteBuf).forEachByteAsc0(iIdx, iIdx + iMin, byteProcessor);
                } else {
                    iForEachByte = byteBuf.forEachByte(iIdx, iMin, byteProcessor);
                }
                if (iForEachByte != -1) {
                    return iForEachByte - component.adjustment;
                }
                i += iMin;
                i3 -= iMin;
            }
            componentIndex0++;
        }
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int forEachByteDesc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int iForEachByteDesc;
        if (i2 > i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = (i + 1) - i2;
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int iIdx = component.idx(i3 + i2);
                int iMin = Math.min(i3, iIdx);
                int i4 = iIdx - iMin;
                if (byteBuf instanceof AbstractByteBuf) {
                    iForEachByteDesc = ((AbstractByteBuf) byteBuf).forEachByteDesc0(iIdx - 1, i4, byteProcessor);
                } else {
                    iForEachByteDesc = byteBuf.forEachByteDesc(i4, iMin, byteProcessor);
                }
                if (iForEachByteDesc != -1) {
                    return iForEachByteDesc - component.adjustment;
                }
                i3 -= iMin;
            }
            componentIndex0--;
        }
        return -1;
    }

    public List<ByteBuf> decompose(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return Collections.emptyList();
        }
        int componentIndex0 = toComponentIndex0(i);
        Component component = this.components[componentIndex0];
        ByteBuf byteBufSlice = component.buf.slice(component.idx(i), Math.min(component.endOffset - i, i2));
        int i3 = i2 - byteBufSlice.readableBytes();
        if (i3 == 0) {
            return Collections.singletonList(byteBufSlice);
        }
        ArrayList arrayList = new ArrayList(this.componentCount - componentIndex0);
        arrayList.add(byteBufSlice);
        do {
            componentIndex0++;
            Component component2 = this.components[componentIndex0];
            ByteBuf byteBufSlice2 = component2.buf.slice(component2.idx(component2.offset), Math.min(component2.length(), i3));
            i3 -= byteBufSlice2.readableBytes();
            arrayList.add(byteBufSlice2);
        } while (i3 > 0);
        return arrayList;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        int i = this.componentCount;
        if (i == 0) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!this.components[i2].buf.isDirect()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        int i = this.componentCount;
        if (i == 0) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        int i = this.componentCount;
        if (i == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (i == 1) {
            return this.components[0].buf.array();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        int i = this.componentCount;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            Component component = this.components[0];
            return component.idx(component.buf.arrayOffset());
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (i == 1) {
            return this.components[0].buf.memoryAddress() + r0.adjustment;
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        int i = this.componentCount;
        if (i > 0) {
            return this.components[i - 1].endOffset;
        }
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf capacity(int i) {
        checkNewCapacity(i);
        int i2 = this.componentCount;
        int iCapacity = capacity();
        if (i > iCapacity) {
            int i3 = i - iCapacity;
            addComponent0(false, i2, allocBuffer(i3).setIndex(0, i3));
            if (this.componentCount >= this.maxNumComponents) {
                consolidateIfNeeded();
            }
        } else if (i < iCapacity) {
            this.lastAccessed = null;
            int i4 = i2 - 1;
            int i5 = iCapacity - i;
            while (true) {
                if (i4 < 0) {
                    break;
                }
                Component component = this.components[i4];
                int length = component.length();
                if (i5 < length) {
                    component.endOffset -= i5;
                    ByteBuf byteBuf = component.slice;
                    if (byteBuf != null) {
                        component.slice = byteBuf.slice(0, component.length());
                    }
                } else {
                    component.free();
                    i5 -= length;
                    i4--;
                }
            }
            removeCompRange(i4 + 1, i2);
            if (readerIndex() > i) {
                setIndex0(i, i);
            } else if (this.writerIndex > i) {
                this.writerIndex = i;
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    public int toComponentIndex(int i) {
        checkIndex(i);
        return toComponentIndex0(i);
    }

    private int toComponentIndex0(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        if (i == 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.components[i4].endOffset > 0) {
                    return i4;
                }
            }
        }
        if (i2 <= 2) {
            return (i2 == 1 || i < this.components[0].endOffset) ? 0 : 1;
        }
        while (i3 <= i2) {
            int i5 = (i3 + i2) >>> 1;
            Component component = this.components[i5];
            if (i >= component.endOffset) {
                i3 = i5 + 1;
            } else {
                if (i >= component.offset) {
                    return i5;
                }
                i2 = i5 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    public int toByteIndex(int i) {
        checkComponentIndex(i);
        return this.components[i].offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        Component componentFindComponent = findComponent(i);
        return componentFindComponent.buf.getByte(componentFindComponent.idx(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected byte _getByte(int i) {
        Component componentFindComponent0 = findComponent0(i);
        return componentFindComponent0.buf.getByte(componentFindComponent0.idx(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShort(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 2 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getShort(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected short _getShortLE(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 2 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getShortLE(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMedium(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 3 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getUnsignedMedium(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getByte(i + 2) & 255) | ((_getShort(i) & UShort.MAX_VALUE) << 8);
        }
        return ((_getByte(i + 2) & 255) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getUnsignedMediumLE(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 3 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getUnsignedMediumLE(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getByte(i + 2) & 255) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getByte(i + 2) & 255) | ((_getShortLE(i) & UShort.MAX_VALUE) << 8);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getInt(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 4 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getInt(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getShort(i + 2) & UShort.MAX_VALUE) | ((_getShort(i) & UShort.MAX_VALUE) << 16);
        }
        return ((_getShort(i + 2) & UShort.MAX_VALUE) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int _getIntLE(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 4 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getIntLE(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getShortLE(i + 2) & UShort.MAX_VALUE) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getShortLE(i + 2) & UShort.MAX_VALUE) | ((_getShortLE(i) & UShort.MAX_VALUE) << 16);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLong(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 8 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getLong(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getInt(i) & 4294967295L) << 32) | (_getInt(i + 4) & 4294967295L);
        }
        return (_getInt(i) & 4294967295L) | ((4294967295L & _getInt(i + 4)) << 32);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected long _getLongLE(int i) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 8 <= componentFindComponent0.endOffset) {
            return componentFindComponent0.buf.getLongLE(componentFindComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getIntLE(i) & 4294967295L) | ((4294967295L & _getIntLE(i + 4)) << 32);
        }
        return ((_getIntLE(i) & 4294967295L) << 32) | (_getIntLE(i + 4) & 4294967295L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int iMin = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), bArr, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        int iRemaining = byteBuffer.remaining();
        checkIndex(i, iRemaining);
        if (iRemaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (iRemaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int iMin = Math.min(iRemaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + iMin);
                component.buf.getBytes(component.idx(i), byteBuffer);
                i += iMin;
                iRemaining -= iMin;
                componentIndex0++;
            } finally {
                byteBuffer.limit(iLimit);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int iMin = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            componentIndex0++;
        }
        return this;
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
    public CompositeByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i2 > 0) {
            Component component = this.components[componentIndex0];
            int iMin = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), outputStream, iMin);
            i += iMin;
            i2 -= iMin;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setByte(int i, int i2) {
        Component componentFindComponent = findComponent(i);
        componentFindComponent.buf.setByte(componentFindComponent.idx(i), i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setByte(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        componentFindComponent0.buf.setByte(componentFindComponent0.idx(i), i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setShort(int i, int i2) {
        checkIndex(i, 2);
        _setShort(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShort(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 2 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setShort(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        } else {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setShortLE(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 2 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setShortLE(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        } else {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setMedium(int i, int i2) {
        checkIndex(i, 3);
        _setMedium(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMedium(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 3 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setMedium(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        } else {
            _setShort(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setMediumLE(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 3 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setMediumLE(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setInt(int i, int i2) {
        checkIndex(i, 4);
        _setInt(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setInt(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 4 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setInt(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >>> 16));
            _setShort(i + 2, (short) i2);
        } else {
            _setShort(i, (short) i2);
            _setShort(i + 2, (short) (i2 >>> 16));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setIntLE(int i, int i2) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 4 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setIntLE(componentFindComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setShortLE(i + 2, (short) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >>> 16));
            _setShortLE(i + 2, (short) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setLong(int i, long j) {
        checkIndex(i, 8);
        _setLong(i, j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLong(int i, long j) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 8 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setLong(componentFindComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setInt(i, (int) (j >>> 32));
            _setInt(i + 4, (int) j);
        } else {
            _setInt(i, (int) j);
            _setInt(i + 4, (int) (j >>> 32));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected void _setLongLE(int i, long j) {
        Component componentFindComponent0 = findComponent0(i);
        if (i + 8 <= componentFindComponent0.endOffset) {
            componentFindComponent0.buf.setLongLE(componentFindComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setIntLE(i, (int) j);
            _setIntLE(i + 4, (int) (j >>> 32));
        } else {
            _setIntLE(i, (int) (j >>> 32));
            _setIntLE(i + 4, (int) j);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        checkSrcIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int iMin = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), bArr, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit();
        int iRemaining = byteBuffer.remaining();
        checkIndex(i, iRemaining);
        if (iRemaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (iRemaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int iMin = Math.min(iRemaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + iMin);
                component.buf.setBytes(component.idx(i), byteBuffer);
                i += iMin;
                iRemaining -= iMin;
                componentIndex0++;
            } finally {
                byteBuffer.limit(iLimit);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkSrcIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int iMin = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), byteBuf, i2, iMin);
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            componentIndex0++;
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e A[PHI: r1 r6 r8
  0x001e: PHI (r1v5 int) = (r1v1 int), (r1v2 int) binds: [B:8:0x001c, B:16:0x0034] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r6v5 int) = (r6v1 int), (r6v3 int) binds: [B:8:0x001c, B:16:0x0034] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r8v4 int) = (r8v1 int), (r8v2 int) binds: [B:8:0x001c, B:16:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setBytes(int r6, java.io.InputStream r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5.checkIndex(r6, r8)
            if (r8 != 0) goto Lc
            byte[] r6 = io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays.EMPTY_BYTES
            int r6 = r7.read(r6)
            return r6
        Lc:
            int r0 = r5.toComponentIndex0(r6)
            r1 = 0
        L11:
            io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component[] r2 = r5.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r6
            int r3 = java.lang.Math.min(r8, r3)
            if (r3 != 0) goto L21
        L1e:
            int r0 = r0 + 1
            goto L37
        L21:
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r4 = r2.buf
            int r2 = r2.idx(r6)
            int r2 = r4.setBytes(r2, r7, r3)
            if (r2 >= 0) goto L31
            if (r1 != 0) goto L39
            r6 = -1
            return r6
        L31:
            int r6 = r6 + r2
            int r8 = r8 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L37
            goto L1e
        L37:
            if (r8 > 0) goto L11
        L39:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.setBytes(int, java.io.InputStream, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e A[PHI: r1 r6 r8
  0x001e: PHI (r1v5 int) = (r1v1 int), (r1v2 int) binds: [B:8:0x001c, B:18:0x0037] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r6v5 int) = (r6v1 int), (r6v3 int) binds: [B:8:0x001c, B:18:0x0037] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r8v4 int) = (r8v1 int), (r8v2 int) binds: [B:8:0x001c, B:18:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setBytes(int r6, java.nio.channels.ScatteringByteChannel r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5.checkIndex(r6, r8)
            if (r8 != 0) goto Lc
            java.nio.ByteBuffer r6 = io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.EMPTY_NIO_BUFFER
            int r6 = r7.read(r6)
            return r6
        Lc:
            int r0 = r5.toComponentIndex0(r6)
            r1 = 0
        L11:
            io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component[] r2 = r5.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r6
            int r3 = java.lang.Math.min(r8, r3)
            if (r3 != 0) goto L21
        L1e:
            int r0 = r0 + 1
            goto L3a
        L21:
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r4 = r2.buf
            int r2 = r2.idx(r6)
            int r2 = r4.setBytes(r2, r7, r3)
            if (r2 != 0) goto L2e
            goto L3c
        L2e:
            if (r2 >= 0) goto L34
            if (r1 != 0) goto L3c
            r6 = -1
            return r6
        L34:
            int r6 = r6 + r2
            int r8 = r8 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L3a
            goto L1e
        L3a:
            if (r8 > 0) goto L11
        L3c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.setBytes(int, java.nio.channels.ScatteringByteChannel, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e A[PHI: r1 r11 r15
  0x001e: PHI (r1v5 int) = (r1v1 int), (r1v2 int) binds: [B:8:0x001c, B:18:0x003c] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r11v5 int) = (r11v1 int), (r11v3 int) binds: [B:8:0x001c, B:18:0x003c] A[DONT_GENERATE, DONT_INLINE]
  0x001e: PHI (r15v4 int) = (r15v1 int), (r15v2 int) binds: [B:8:0x001c, B:18:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setBytes(int r11, java.nio.channels.FileChannel r12, long r13, int r15) throws java.io.IOException {
        /*
            r10 = this;
            r10.checkIndex(r11, r15)
            if (r15 != 0) goto Lc
            java.nio.ByteBuffer r11 = io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.EMPTY_NIO_BUFFER
            int r11 = r12.read(r11, r13)
            return r11
        Lc:
            int r0 = r10.toComponentIndex0(r11)
            r1 = 0
        L11:
            io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf$Component[] r2 = r10.components
            r2 = r2[r0]
            int r3 = r2.endOffset
            int r3 = r3 - r11
            int r3 = java.lang.Math.min(r15, r3)
            if (r3 != 0) goto L21
        L1e:
            int r0 = r0 + 1
            goto L3f
        L21:
            io.grpc.netty.shaded.io.netty.buffer.ByteBuf r4 = r2.buf
            int r5 = r2.idx(r11)
            long r6 = (long) r1
            long r7 = r13 + r6
            r6 = r12
            r9 = r3
            int r2 = r4.setBytes(r5, r6, r7, r9)
            if (r2 != 0) goto L33
            goto L41
        L33:
            if (r2 >= 0) goto L39
            if (r1 != 0) goto L41
            r11 = -1
            return r11
        L39:
            int r11 = r11 + r2
            int r15 = r15 - r2
            int r1 = r1 + r2
            if (r2 != r3) goto L3f
            goto L1e
        L3f:
            if (r15 > 0) goto L11
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.setBytes(int, java.nio.channels.FileChannel, long, int):int");
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf byteBufAllocBuffer = allocBuffer(i2);
        if (i2 != 0) {
            copyTo(i, i2, toComponentIndex0(i), byteBufAllocBuffer);
        }
        return byteBufAllocBuffer;
    }

    private void copyTo(int i, int i2, int i3, ByteBuf byteBuf) {
        int i4 = 0;
        while (i2 > 0) {
            Component component = this.components[i3];
            int iMin = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i4, iMin);
            i += iMin;
            i4 += iMin;
            i2 -= iMin;
            i3++;
        }
        byteBuf.writerIndex(byteBuf.capacity());
    }

    public ByteBuf component(int i) {
        checkComponentIndex(i);
        return this.components[i].duplicate();
    }

    public ByteBuf componentAtOffset(int i) {
        return findComponent(i).duplicate();
    }

    public ByteBuf internalComponent(int i) {
        checkComponentIndex(i);
        return this.components[i].slice();
    }

    public ByteBuf internalComponentAtOffset(int i) {
        return findComponent(i).slice();
    }

    private Component findComponent(int i) {
        Component component = this.lastAccessed;
        if (component != null && i >= component.offset && i < component.endOffset) {
            ensureAccessible();
            return component;
        }
        checkIndex(i);
        return findIt(i);
    }

    private Component findComponent0(int i) {
        Component component = this.lastAccessed;
        return (component == null || i < component.offset || i >= component.endOffset) ? findIt(i) : component;
    }

    private Component findIt(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            Component component = this.components[i4];
            if (i >= component.endOffset) {
                i3 = i4 + 1;
            } else {
                if (i >= component.offset) {
                    this.lastAccessed = component;
                    return component;
                }
                i2 = i4 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        int i = this.componentCount;
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return this.components[0].buf.nioBufferCount();
        }
        int iNioBufferCount = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iNioBufferCount += this.components[i2].buf.nioBufferCount();
        }
        return iNioBufferCount;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            return this.components[0].internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            Component component = this.components[0];
            ByteBuf byteBuf = component.buf;
            if (byteBuf.nioBufferCount() == 1) {
                return byteBuf.nioBuffer(component.idx(i), i2);
            }
        }
        ByteBuffer[] byteBufferArrNioBuffers = nioBuffers(i, i2);
        if (byteBufferArrNioBuffers.length == 1) {
            return byteBufferArrNioBuffers[0];
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : byteBufferArrNioBuffers) {
            byteBufferOrder.put(byteBuffer);
        }
        byteBufferOrder.flip();
        return byteBufferOrder;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return new ByteBuffer[]{EMPTY_NIO_BUFFER};
        }
        RecyclableArrayList recyclableArrayListNewInstance = RecyclableArrayList.newInstance(this.componentCount);
        try {
            int componentIndex0 = toComponentIndex0(i);
            while (i2 > 0) {
                Component component = this.components[componentIndex0];
                ByteBuf byteBuf = component.buf;
                int iMin = Math.min(i2, component.endOffset - i);
                int iNioBufferCount = byteBuf.nioBufferCount();
                if (iNioBufferCount == 0) {
                    throw new UnsupportedOperationException();
                }
                if (iNioBufferCount == 1) {
                    recyclableArrayListNewInstance.add(byteBuf.nioBuffer(component.idx(i), iMin));
                } else {
                    Collections.addAll(recyclableArrayListNewInstance, byteBuf.nioBuffers(component.idx(i), iMin));
                }
                i += iMin;
                i2 -= iMin;
                componentIndex0++;
            }
            return (ByteBuffer[]) recyclableArrayListNewInstance.toArray(new ByteBuffer[0]);
        } finally {
            recyclableArrayListNewInstance.recycle();
        }
    }

    public CompositeByteBuf consolidate() {
        ensureAccessible();
        consolidate0(0, this.componentCount);
        return this;
    }

    public CompositeByteBuf consolidate(int i, int i2) {
        checkComponentIndex(i, i2);
        consolidate0(i, i2);
        return this;
    }

    private void consolidate0(int i, int i2) {
        if (i2 <= 1) {
            return;
        }
        int i3 = i + i2;
        ByteBuf byteBufAllocBuffer = allocBuffer(this.components[i3 - 1].endOffset - (i != 0 ? this.components[i].offset : 0));
        for (int i4 = i; i4 < i3; i4++) {
            this.components[i4].transferTo(byteBufAllocBuffer);
        }
        this.lastAccessed = null;
        removeCompRange(i + 1, i3);
        this.components[i] = newComponent(byteBufAllocBuffer, 0);
        if (i == 0 && i2 == this.componentCount) {
            return;
        }
        updateComponentOffsets(i);
    }

    public CompositeByteBuf discardReadComponents() {
        ensureAccessible();
        int i = readerIndex();
        if (i == 0) {
            return this;
        }
        int iWriterIndex = writerIndex();
        if (i == iWriterIndex && iWriterIndex == capacity()) {
            int i2 = this.componentCount;
            for (int i3 = 0; i3 < i2; i3++) {
                this.components[i3].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(i);
            return this;
        }
        int i4 = this.componentCount;
        Component component = null;
        int i5 = 0;
        while (i5 < i4) {
            component = this.components[i5];
            if (component.endOffset > i) {
                break;
            }
            component.free();
            i5++;
        }
        if (i5 == 0) {
            return this;
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= i) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i5);
        int i6 = component.offset;
        updateComponentOffsets(0);
        setIndex(i - i6, iWriterIndex - i6);
        adjustMarkers(i6);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf discardReadBytes() {
        ensureAccessible();
        int i = readerIndex();
        if (i == 0) {
            return this;
        }
        int iWriterIndex = writerIndex();
        if (i == iWriterIndex && iWriterIndex == capacity()) {
            int i2 = this.componentCount;
            for (int i3 = 0; i3 < i2; i3++) {
                this.components[i3].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(i);
            return this;
        }
        int i4 = this.componentCount;
        Component component = null;
        int i5 = 0;
        while (i5 < i4) {
            component = this.components[i5];
            if (component.endOffset > i) {
                break;
            }
            component.free();
            i5++;
        }
        int i6 = i - component.offset;
        component.offset = 0;
        component.endOffset -= i;
        component.srcAdjustment += i;
        component.adjustment += i;
        ByteBuf byteBuf = component.slice;
        if (byteBuf != null) {
            component.slice = byteBuf.slice(i6, component.length());
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= i) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i5);
        updateComponentOffsets(0);
        setIndex(0, iWriterIndex - i);
        adjustMarkers(i);
        return this;
    }

    private ByteBuf allocBuffer(int i) {
        return this.direct ? alloc().directBuffer(i) : alloc().heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.componentCount + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readerIndex(int i) {
        super.readerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writerIndex(int i) {
        super.writerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setIndex(int i, int i2) {
        super.setIndex(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf clear() {
        super.clear();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf markReaderIndex() {
        super.markReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf resetReaderIndex() {
        super.resetReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf markWriterIndex() {
        super.markWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf resetWriterIndex() {
        super.resetWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf ensureWritable(int i) {
        super.ensureWritable(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf) {
        return getBytes(i, byteBuf, byteBuf.writableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        getBytes(i, byteBuf, byteBuf.writerIndex(), i2);
        byteBuf.writerIndex(byteBuf.writerIndex() + i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr) {
        return getBytes(i, bArr, 0, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBoolean(int i, boolean z) {
        return setByte(i, z ? 1 : 0);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setChar(int i, int i2) {
        return setShort(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setFloat(int i, float f) {
        return setInt(i, Float.floatToRawIntBits(f));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setDouble(int i, double d) {
        return setLong(i, Double.doubleToRawLongBits(d));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf) {
        super.setBytes(i, byteBuf, byteBuf.readableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        super.setBytes(i, byteBuf, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr) {
        return setBytes(i, bArr, 0, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setZero(int i, int i2) {
        super.setZero(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf) {
        super.readBytes(byteBuf, byteBuf.writableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i) {
        super.readBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        super.readBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr) {
        super.readBytes(bArr, 0, bArr.length);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr, int i, int i2) {
        super.readBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuffer byteBuffer) {
        super.readBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        super.readBytes(outputStream, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf skipBytes(int i) {
        super.skipBytes(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBoolean(boolean z) {
        writeByte(z ? 1 : 0);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeByte(int i) {
        ensureWritable0(1);
        int i2 = this.writerIndex;
        this.writerIndex = i2 + 1;
        _setByte(i2, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeShort(int i) {
        super.writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeMedium(int i) {
        super.writeMedium(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeInt(int i) {
        super.writeInt(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeLong(long j) {
        super.writeLong(j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeChar(int i) {
        super.writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeFloat(float f) {
        super.writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeDouble(double d) {
        super.writeLong(Double.doubleToRawLongBits(d));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf) {
        super.writeBytes(byteBuf, byteBuf.readableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i) {
        super.writeBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        super.writeBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr) {
        super.writeBytes(bArr, 0, bArr.length);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr, int i, int i2) {
        super.writeBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuffer byteBuffer) {
        super.writeBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeZero(int i) {
        super.writeZero(i);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf retain(int i) {
        super.retain(i);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        return nioBuffers(readerIndex(), readableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf discardSomeReadBytes() {
        return discardReadComponents();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    protected void deallocate() {
        if (this.freed) {
            return;
        }
        this.freed = true;
        int i = this.componentCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.components[i2].free();
        }
    }

    private void clearComps() {
        removeCompRange(0, this.componentCount);
    }

    private void removeComp(int i) {
        removeCompRange(i, i + 1);
    }

    private void removeCompRange(int i, int i2) {
        if (i >= i2) {
            return;
        }
        int i3 = this.componentCount;
        if (i2 < i3) {
            Component[] componentArr = this.components;
            System.arraycopy(componentArr, i2, componentArr, i, i3 - i2);
        }
        int i4 = (i3 - i2) + i;
        for (int i5 = i4; i5 < i3; i5++) {
            this.components[i5] = null;
        }
        this.componentCount = i4;
    }

    private void addComp(int i, Component component) {
        shiftComps(i, 1);
        this.components[i] = component;
    }

    private void shiftComps(int i, int i2) {
        Component[] componentArr;
        int i3 = this.componentCount;
        int i4 = i3 + i2;
        Component[] componentArr2 = this.components;
        if (i4 > componentArr2.length) {
            int iMax = Math.max((i3 >> 1) + i3, i4);
            if (i == i3) {
                componentArr = (Component[]) Arrays.copyOf(this.components, iMax, Component[].class);
            } else {
                Component[] componentArr3 = new Component[iMax];
                if (i > 0) {
                    System.arraycopy(this.components, 0, componentArr3, 0, i);
                }
                if (i < i3) {
                    System.arraycopy(this.components, i, componentArr3, i2 + i, i3 - i);
                }
                componentArr = componentArr3;
            }
            this.components = componentArr;
        } else if (i < i3) {
            System.arraycopy(componentArr2, i, componentArr2, i2 + i, i3 - i);
        }
        this.componentCount = i4;
    }

    interface ByteWrapper<T> {
        boolean isEmpty(T t);

        ByteBuf wrap(T t);
    }

    private static final class Component {
        final ByteBuf buf;
        final ByteBuf srcBuf;
        int adjustment;
        int endOffset;
        int offset;
        int srcAdjustment;
        private ByteBuf slice;

        Component(ByteBuf byteBuf, int i, ByteBuf byteBuf2, int i2, int i3, int i4, ByteBuf byteBuf3) {
            this.srcBuf = byteBuf;
            this.srcAdjustment = i - i3;
            this.buf = byteBuf2;
            this.adjustment = i2 - i3;
            this.offset = i3;
            this.endOffset = i3 + i4;
            this.slice = byteBuf3;
        }

        int idx(int i) {
            return i + this.adjustment;
        }

        int length() {
            return this.endOffset - this.offset;
        }

        void reposition(int i) {
            int i2 = i - this.offset;
            this.endOffset += i2;
            this.srcAdjustment -= i2;
            this.adjustment -= i2;
            this.offset = i;
        }

        int srcIdx(int i) {
            return i + this.srcAdjustment;
        }

        void transferTo(ByteBuf byteBuf) {
            byteBuf.writeBytes(this.buf, idx(this.offset), length());
            free();
        }

        ByteBuf slice() {
            ByteBuf byteBuf = this.slice;
            if (byteBuf != null) {
                return byteBuf;
            }
            ByteBuf byteBufSlice = this.srcBuf.slice(srcIdx(this.offset), length());
            this.slice = byteBufSlice;
            return byteBufSlice;
        }

        ByteBuf duplicate() {
            return this.srcBuf.duplicate();
        }

        ByteBuffer internalNioBuffer(int i, int i2) {
            return this.srcBuf.internalNioBuffer(srcIdx(i), i2);
        }

        void free() {
            this.slice = null;
            this.srcBuf.release();
        }
    }

    private final class CompositeByteBufIterator implements Iterator<ByteBuf> {
        private final int size;
        private int index;

        private CompositeByteBufIterator() {
            this.size = CompositeByteBuf.this.numComponents();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.size > this.index;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ByteBuf next() {
            if (this.size != CompositeByteBuf.this.numComponents()) {
                throw new ConcurrentModificationException();
            }
            if (hasNext()) {
                try {
                    Component[] componentArr = CompositeByteBuf.this.components;
                    int i = this.index;
                    this.index = i + 1;
                    return componentArr[i].slice();
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-Only");
        }
    }
}
