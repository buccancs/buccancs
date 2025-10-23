package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import com.shimmerresearch.verisense.communication.VerisenseMessage;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.net.SocketAddress;
import java.util.List;

/* loaded from: classes3.dex */
public class WebSocketServerProtocolHandler extends WebSocketProtocolHandler {
    private static final AttributeKey<WebSocketServerHandshaker> HANDSHAKER_ATTR_KEY = AttributeKey.valueOf(WebSocketServerHandshaker.class, "HANDSHAKER");
    private final WebSocketServerProtocolConfig serverConfig;

    public WebSocketServerProtocolHandler(WebSocketServerProtocolConfig webSocketServerProtocolConfig) {
        super(((WebSocketServerProtocolConfig) ObjectUtil.checkNotNull(webSocketServerProtocolConfig, "serverConfig")).dropPongFrames(), webSocketServerProtocolConfig.sendCloseFrame(), webSocketServerProtocolConfig.forceCloseTimeoutMillis());
        this.serverConfig = webSocketServerProtocolConfig;
    }

    public WebSocketServerProtocolHandler(String str) {
        this(str, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, long j) {
        this(str, false, j);
    }

    public WebSocketServerProtocolHandler(String str, boolean z) {
        this(str, z, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, boolean z, long j) {
        this(str, (String) null, false, 65536, false, z, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2) {
        this(str, str2, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, long j) {
        this(str, str2, false, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z) {
        this(str, str2, z, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, long j) {
        this(str, str2, z, 65536, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i) {
        this(str, str2, z, i, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, long j) {
        this(str, str2, z, i, false, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2) {
        this(str, str2, z, i, z2, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, long j) {
        this(str, str2, z, i, z2, false, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3) {
        this(str, str2, z, i, z2, z3, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3, long j) {
        this(str, str2, z, i, z2, z3, true, j);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3, boolean z4) {
        this(str, str2, z, i, z2, z3, z4, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3, boolean z4, long j) {
        this(str, str2, z3, z4, j, WebSocketDecoderConfig.newBuilder().maxFramePayloadLength(i).allowMaskMismatch(z2).allowExtensions(z).build());
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, boolean z2, long j, WebSocketDecoderConfig webSocketDecoderConfig) {
        this(WebSocketServerProtocolConfig.newBuilder().websocketPath(str).subprotocols(str2).checkStartsWith(z).handshakeTimeoutMillis(j).dropPongFrames(z2).decoderConfig(webSocketDecoderConfig).build());
    }

    static WebSocketServerHandshaker getHandshaker(Channel channel) {
        return (WebSocketServerHandshaker) channel.attr(HANDSHAKER_ATTR_KEY).get();
    }

    static void setHandshaker(Channel channel, WebSocketServerHandshaker webSocketServerHandshaker) {
        channel.attr(HANDSHAKER_ATTR_KEY).set(webSocketServerHandshaker);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        super.bind(channelHandlerContext, socketAddress, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.close(channelHandlerContext, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        super.connect(channelHandlerContext, socketAddress, socketAddress2, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        decode(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.deregister(channelHandlerContext, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.disconnect(channelHandlerContext, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.flush(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.read(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        super.write(channelHandlerContext, obj, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        ChannelPipeline channelPipelinePipeline = channelHandlerContext.pipeline();
        if (channelPipelinePipeline.get(WebSocketServerProtocolHandshakeHandler.class) == null) {
            channelPipelinePipeline.addBefore(channelHandlerContext.name(), WebSocketServerProtocolHandshakeHandler.class.getName(), new WebSocketServerProtocolHandshakeHandler(this.serverConfig));
        }
        if (this.serverConfig.decoderConfig().withUTF8Validator() && channelPipelinePipeline.get(Utf8FrameValidator.class) == null) {
            channelPipelinePipeline.addBefore(channelHandlerContext.name(), Utf8FrameValidator.class.getName(), new Utf8FrameValidator());
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        if (this.serverConfig.handleCloseFrames() && (webSocketFrame instanceof CloseWebSocketFrame)) {
            WebSocketServerHandshaker handshaker = getHandshaker(channelHandlerContext.channel());
            if (handshaker != null) {
                webSocketFrame.retain();
                handshaker.close(channelHandlerContext.channel(), (CloseWebSocketFrame) webSocketFrame);
                return;
            } else {
                channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
                return;
            }
        }
        super.decode(channelHandlerContext, webSocketFrame, list);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (th instanceof WebSocketHandshakeException) {
            channelHandlerContext.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.wrappedBuffer(th.getMessage().getBytes()))).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
        } else {
            channelHandlerContext.fireExceptionCaught(th);
            channelHandlerContext.close();
        }
    }

    public enum ServerHandshakeStateEvent {
        HANDSHAKE_COMPLETE,
        HANDSHAKE_TIMEOUT
    }

    public static final class HandshakeComplete {
        private final HttpHeaders requestHeaders;
        private final String requestUri;
        private final String selectedSubprotocol;

        HandshakeComplete(String str, HttpHeaders httpHeaders, String str2) {
            this.requestUri = str;
            this.requestHeaders = httpHeaders;
            this.selectedSubprotocol = str2;
        }

        public HttpHeaders requestHeaders() {
            return this.requestHeaders;
        }

        public String requestUri() {
            return this.requestUri;
        }

        public String selectedSubprotocol() {
            return this.selectedSubprotocol;
        }
    }
}
