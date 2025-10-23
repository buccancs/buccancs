package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.xds.internal.sds.FileBasedPluginCredential;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.math3.geometry.VectorFormat;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

/* loaded from: classes3.dex */
public class DiskFileUpload extends AbstractDiskHttpData implements FileUpload {
    public static final String postfix = ".tmp";
    public static final String prefix = "FUp_";
    public static String baseDirectory = null;
    public static boolean deleteOnExitTemporaryFile = true;
    private final String baseDir;
    private final boolean deleteOnExit;
    private String contentTransferEncoding;
    private String contentType;
    private String filename;

    public DiskFileUpload(String str, String str2, String str3, String str4, Charset charset, long j, String str5, boolean z) {
        super(str, charset, j);
        setFilename(str2);
        setContentType(str3);
        setContentTransferEncoding(str4);
        this.baseDir = str5 == null ? baseDirectory : str5;
        this.deleteOnExit = z;
    }

    public DiskFileUpload(String str, String str2, String str3, String str4, Charset charset, long j) {
        this(str, str2, str3, str4, charset, j, baseDirectory, deleteOnExitTemporaryFile);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected boolean deleteOnExit() {
        return this.deleteOnExit;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getBaseDirectory() {
        return this.baseDir;
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getDiskFilename() {
        return "upload";
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public String getFilename() {
        return this.filename;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload
    public void setFilename(String str) {
        this.filename = (String) ObjectUtil.checkNotNull(str, FileBasedPluginCredential.FILENAME);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPostfix() {
        return postfix;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData
    protected String getPrefix() {
        return prefix;
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
        File file;
        try {
            file = getFile();
        } catch (IOException unused) {
            file = null;
        }
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
        sb.append("\r\nRealFile: ");
        sb.append(file != null ? file.getAbsolutePath() : "null");
        sb.append(" DefaultDeleteAfter: ");
        sb.append(deleteOnExitTemporaryFile);
        return sb.toString();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload copy() {
        ByteBuf byteBufContent = content();
        return replace(byteBufContent != null ? byteBufContent.copy() : null);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public FileUpload duplicate() {
        ByteBuf byteBufContent = content();
        return replace(byteBufContent != null ? byteBufContent.duplicate() : null);
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
        DiskFileUpload diskFileUpload = new DiskFileUpload(getName(), getFilename(), getContentType(), getContentTransferEncoding(), getCharset(), this.size, this.baseDir, this.deleteOnExit);
        if (byteBuf != null) {
            try {
                diskFileUpload.setContent(byteBuf);
            } catch (IOException e) {
                throw new ChannelException(e);
            }
        }
        return diskFileUpload;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload retain(int i) {
        super.retain(i);
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload retain() {
        super.retain();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload touch() {
        super.touch();
        return this;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractDiskHttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public FileUpload touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
