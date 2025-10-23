package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
final class InternalAttribute extends AbstractReferenceCounted implements InterfaceHttpData {
    private final Charset charset;
    private final List<ByteBuf> value = new ArrayList();
    private int size;

    InternalAttribute(Charset charset) {
        this.charset = charset;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return "InternalAttribute";
    }

    public int size() {
        return this.size;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.InternalAttribute;
    }

    public void addValue(String str) {
        ObjectUtil.checkNotNull(str, "value");
        ByteBuf byteBufCopiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        this.value.add(byteBufCopiedBuffer);
        this.size += byteBufCopiedBuffer.readableBytes();
    }

    public void addValue(String str, int i) {
        ObjectUtil.checkNotNull(str, "value");
        ByteBuf byteBufCopiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        this.value.add(i, byteBufCopiedBuffer);
        this.size += byteBufCopiedBuffer.readableBytes();
    }

    public void setValue(String str, int i) {
        ObjectUtil.checkNotNull(str, "value");
        ByteBuf byteBufCopiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        ByteBuf byteBuf = this.value.set(i, byteBufCopiedBuffer);
        if (byteBuf != null) {
            this.size -= byteBuf.readableBytes();
            byteBuf.release();
        }
        this.size += byteBufCopiedBuffer.readableBytes();
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof InternalAttribute) {
            return getName().equalsIgnoreCase(((InternalAttribute) obj).getName());
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof InternalAttribute)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((InternalAttribute) interfaceHttpData);
    }

    public int compareTo(InternalAttribute internalAttribute) {
        return getName().compareToIgnoreCase(internalAttribute.getName());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<ByteBuf> it2 = this.value.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString(this.charset));
        }
        return sb.toString();
    }

    public ByteBuf toByteBuf() {
        return Unpooled.compositeBuffer().addComponents(this.value).writerIndex(size()).readerIndex(0);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public InterfaceHttpData retain() {
        Iterator<ByteBuf> it2 = this.value.iterator();
        while (it2.hasNext()) {
            it2.next().retain();
        }
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public InterfaceHttpData retain(int i) {
        Iterator<ByteBuf> it2 = this.value.iterator();
        while (it2.hasNext()) {
            it2.next().retain(i);
        }
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public InterfaceHttpData touch() {
        Iterator<ByteBuf> it2 = this.value.iterator();
        while (it2.hasNext()) {
            it2.next().touch();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public InterfaceHttpData touch(Object obj) {
        Iterator<ByteBuf> it2 = this.value.iterator();
        while (it2.hasNext()) {
            it2.next().touch(obj);
        }
        return this;
    }
}
