package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.xds.internal.sds.FileBasedPluginCredential;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.math3.geometry.VectorFormat;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* loaded from: classes3.dex */
public class MemoryFileUpload extends AbstractMemoryHttpData implements FileUpload {
    private String contentTransferEncoding;
    private String contentType;
    private String filename;

    public MemoryFileUpload(String str, String str2, String str3, String str4, Charset charset, long j) {
        super(str, charset, j);
        setFilename(str2);
        setContentType(str3);
        setContentTransferEncoding(str4);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public String getContentTransferEncoding() {
        return this.contentTransferEncoding;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public void setContentTransferEncoding(String str) {
        this.contentTransferEncoding = str;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public String getContentType() {
        return this.contentType;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public void setContentType(String str) {
        this.contentType = (String) ObjectUtil.checkNotNull(str, CMSAttributeTableGenerator.CONTENT_TYPE);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.filename;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String str) {
        this.filename = (String) ObjectUtil.checkNotNull(str, FileBasedPluginCredential.FILENAME);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.FileUpload;
    }

    public int hashCode() {
        return FileUploadUtil.hashCode(this);
    }

    public boolean equals(Object obj) {
        return (obj instanceof FileUpload) && FileUploadUtil.equals(this, (FileUpload) obj);
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof FileUpload)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((FileUpload) interfaceHttpData);
    }

    public int compareTo(FileUpload fileUpload) {
        return FileUploadUtil.compareTo(this, fileUpload);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) HttpHeaderNames.CONTENT_DISPOSITION);
        sb.append(": ");
        sb.append((Object) HttpHeaderValues.FORM_DATA);
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append((Object) HttpHeaderValues.NAME);
        sb.append("=\"");
        sb.append(getName());
        sb.append("\"; ");
        sb.append((Object) HttpHeaderValues.FILENAME);
        sb.append("=\"");
        sb.append(this.filename);
        sb.append("\"\r\n");
        sb.append((Object) HttpHeaderNames.CONTENT_TYPE);
        sb.append(": ");
        sb.append(this.contentType);
        String str = "\r\n";
        if (getCharset() != null) {
            str = VectorFormat.DEFAULT_SEPARATOR + ((Object) HttpHeaderValues.CHARSET) + '=' + getCharset().name() + "\r\n";
        }
        sb.append(str);
        sb.append((Object) HttpHeaderNames.CONTENT_LENGTH);
        sb.append(": ");
        sb.append(length());
        sb.append("\r\nCompleted: ");
        sb.append(isCompleted());
        sb.append("\r\nIsInMemory: ");
        sb.append(isInMemory());
        return sb.toString();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload copy() {
        ByteBuf byteBufContent = content();
        if (byteBufContent != null) {
            byteBufContent = byteBufContent.copy();
        }
        return replace(byteBufContent);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload duplicate() {
        ByteBuf byteBufContent = content();
        if (byteBufContent != null) {
            byteBufContent = byteBufContent.duplicate();
        }
        return replace(byteBufContent);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload retainedDuplicate() {
        ByteBuf byteBufContent = content();
        if (byteBufContent != null) {
            ByteBuf byteBufRetainedDuplicate = byteBufContent.retainedDuplicate();
            try {
                return replace(byteBufRetainedDuplicate);
            } catch (Throwable th) {
                byteBufRetainedDuplicate.release();
                throw th;
            }
        }
        return replace((ByteBuf) null);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload replace(ByteBuf byteBuf) {
        MemoryFileUpload memoryFileUpload = new MemoryFileUpload(getName(), getFilename(), getContentType(), getContentTransferEncoding(), getCharset(), this.size);
        if (byteBuf == null) {
            return memoryFileUpload;
        }
        try {
            memoryFileUpload.setContent(byteBuf);
            return memoryFileUpload;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload retain() {
        super.retain();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload retain(int i) {
        super.retain(i);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractMemoryHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload touch() {
        super.touch();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractMemoryHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
