package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* loaded from: classes3.dex */
public interface FileUpload extends HttpData {
    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload copy();

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload duplicate();

    String getContentTransferEncoding();

    void setContentTransferEncoding(String str);

    String getContentType();

    void setContentType(String str);

    String getFilename();

    void setFilename(String str);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload replace(ByteBuf byteBuf);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload retain();

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload retain(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    FileUpload retainedDuplicate();

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload touch();

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData, io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    FileUpload touch(Object obj);
}
