package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;

/* loaded from: classes3.dex */
public class OptionalSslHandler extends ByteToMessageDecoder {
    private final SslContext sslContext;

    public OptionalSslHandler(SslContext sslContext) {
        this.sslContext = (SslContext) ObjectUtil.checkNotNull(sslContext, "sslContext");
    }

    protected ChannelHandler newNonSslHandler(ChannelHandlerContext channelHandlerContext) {
        return null;
    }

    protected String newNonSslHandlerName() {
        return null;
    }

    protected String newSslHandlerName() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 5) {
            return;
        }
        if (SslHandler.isEncrypted(byteBuf)) {
            handleSsl(channelHandlerContext);
        } else {
            handleNonSsl(channelHandlerContext);
        }
    }

    private void handleSsl(ChannelHandlerContext channelHandlerContext) {
        SslHandler sslHandlerNewSslHandler = null;
        try {
            sslHandlerNewSslHandler = newSslHandler(channelHandlerContext, this.sslContext);
            channelHandlerContext.pipeline().replace(this, newSslHandlerName(), sslHandlerNewSslHandler);
        } catch (Throwable th) {
            if (sslHandlerNewSslHandler != null) {
                ReferenceCountUtil.safeRelease(sslHandlerNewSslHandler.engine());
            }
            throw th;
        }
    }

    private void handleNonSsl(ChannelHandlerContext channelHandlerContext) {
        ChannelHandler channelHandlerNewNonSslHandler = newNonSslHandler(channelHandlerContext);
        if (channelHandlerNewNonSslHandler != null) {
            channelHandlerContext.pipeline().replace(this, newNonSslHandlerName(), channelHandlerNewNonSslHandler);
        } else {
            channelHandlerContext.pipeline().remove(this);
        }
    }

    protected SslHandler newSslHandler(ChannelHandlerContext channelHandlerContext, SslContext sslContext) {
        return sslContext.newHandler(channelHandlerContext.alloc());
    }
}
