package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;

import java.util.List;

/* loaded from: classes3.dex */
class PerMessageDeflateDecoder extends DeflateDecoder {
    private boolean compressing;

    PerMessageDeflateDecoder(boolean z) {
        super(z, WebSocketExtensionFilter.NEVER_SKIP);
    }

    PerMessageDeflateDecoder(boolean z, WebSocketExtensionFilter webSocketExtensionFilter) {
        super(z, webSocketExtensionFilter);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression.DeflateDecoder, io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        decode(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (!super.acceptInboundMessage(obj)) {
            return false;
        }
        WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
        if (!extensionDecoderFilter().mustSkip(webSocketFrame)) {
            return (((webSocketFrame instanceof TextWebSocketFrame) || (webSocketFrame instanceof BinaryWebSocketFrame)) && (webSocketFrame.rsv() & 4) > 0) || ((webSocketFrame instanceof ContinuationWebSocketFrame) && this.compressing);
        }
        if (this.compressing) {
            throw new IllegalStateException("Cannot skip per message deflate decoder, compression in progress");
        }
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression.DeflateDecoder
    protected int newRsv(WebSocketFrame webSocketFrame) {
        return (webSocketFrame.rsv() & 4) > 0 ? webSocketFrame.rsv() ^ 4 : webSocketFrame.rsv();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression.DeflateDecoder
    protected boolean appendFrameTail(WebSocketFrame webSocketFrame) {
        return webSocketFrame.isFinalFragment();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression.DeflateDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        super.decode2(channelHandlerContext, webSocketFrame, list);
        if (webSocketFrame.isFinalFragment()) {
            this.compressing = false;
        } else if ((webSocketFrame instanceof TextWebSocketFrame) || (webSocketFrame instanceof BinaryWebSocketFrame)) {
            this.compressing = true;
        }
    }
}
