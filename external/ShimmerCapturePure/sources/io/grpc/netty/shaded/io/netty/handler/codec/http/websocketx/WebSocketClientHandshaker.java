package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import com.shimmerresearch.verisense.communication.VerisenseMessage;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContentDecompressor;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequestEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpScheme;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* loaded from: classes3.dex */
public abstract class WebSocketClientHandshaker {
    protected static final int DEFAULT_FORCE_CLOSE_TIMEOUT_MILLIS = 10000;
    private static final String HTTP_SCHEME_PREFIX = HttpScheme.HTTP + "://";
    private static final String HTTPS_SCHEME_PREFIX = HttpScheme.HTTPS + "://";
    private static final AtomicIntegerFieldUpdater<WebSocketClientHandshaker> FORCE_CLOSE_INIT_UPDATER = AtomicIntegerFieldUpdater.newUpdater(WebSocketClientHandshaker.class, "forceCloseInit");
    protected final HttpHeaders customHeaders;
    private final boolean absoluteUpgradeUrl;
    private final String expectedSubprotocol;
    private final int maxFramePayloadLength;
    private final URI uri;
    private final WebSocketVersion version;
    private volatile String actualSubprotocol;
    private volatile boolean forceCloseComplete;
    private volatile int forceCloseInit;
    private volatile long forceCloseTimeoutMillis;
    private volatile boolean handshakeComplete;

    protected WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, httpHeaders, i, VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
    }

    protected WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j) {
        this(uri, webSocketVersion, str, httpHeaders, i, j, false);
    }

    protected WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j, boolean z) {
        this.forceCloseTimeoutMillis = VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER;
        this.uri = uri;
        this.version = webSocketVersion;
        this.expectedSubprotocol = str;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
        this.forceCloseTimeoutMillis = j;
        this.absoluteUpgradeUrl = z;
    }

    static CharSequence websocketHostValue(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            return uri.getHost();
        }
        String host = uri.getHost();
        String scheme = uri.getScheme();
        if (port == HttpScheme.HTTP.port()) {
            return (HttpScheme.HTTP.name().contentEquals(scheme) || WebSocketScheme.WS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
        }
        if (port == HttpScheme.HTTPS.port()) {
            return (HttpScheme.HTTPS.name().contentEquals(scheme) || WebSocketScheme.WSS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
        }
        return NetUtil.toSocketAddressString(host, port);
    }

    static CharSequence websocketOriginValue(URI uri) {
        String str;
        int iPort;
        String scheme = uri.getScheme();
        int port = uri.getPort();
        if (WebSocketScheme.WSS.name().contentEquals(scheme) || HttpScheme.HTTPS.name().contentEquals(scheme) || (scheme == null && port == WebSocketScheme.WSS.port())) {
            str = HTTPS_SCHEME_PREFIX;
            iPort = WebSocketScheme.WSS.port();
        } else {
            str = HTTP_SCHEME_PREFIX;
            iPort = WebSocketScheme.WS.port();
        }
        String lowerCase = uri.getHost().toLowerCase(Locale.US);
        if (port != iPort && port != -1) {
            return str + NetUtil.toSocketAddressString(lowerCase, port);
        }
        return str + lowerCase;
    }

    private void setActualSubprotocol(String str) {
        this.actualSubprotocol = str;
    }

    private void setHandshakeComplete() {
        this.handshakeComplete = true;
    }

    public String actualSubprotocol() {
        return this.actualSubprotocol;
    }

    public String expectedSubprotocol() {
        return this.expectedSubprotocol;
    }

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    protected boolean isForceCloseComplete() {
        return this.forceCloseComplete;
    }

    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    protected abstract FullHttpRequest newHandshakeRequest();

    protected abstract WebSocketFrameEncoder newWebSocketEncoder();

    protected abstract WebSocketFrameDecoder newWebsocketDecoder();

    public WebSocketClientHandshaker setForceCloseTimeoutMillis(long j) {
        this.forceCloseTimeoutMillis = j;
        return this;
    }

    public URI uri() {
        return this.uri;
    }

    protected abstract void verify(FullHttpResponse fullHttpResponse);

    public WebSocketVersion version() {
        return this.version;
    }

    public ChannelFuture handshake(Channel channel) {
        ObjectUtil.checkNotNull(channel, "channel");
        return handshake(channel, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, final ChannelPromise channelPromise) {
        ChannelPipeline channelPipelinePipeline = channel.pipeline();
        if (((HttpResponseDecoder) channelPipelinePipeline.get(HttpResponseDecoder.class)) == null && ((HttpClientCodec) channelPipelinePipeline.get(HttpClientCodec.class)) == null) {
            channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            return channelPromise;
        }
        channel.writeAndFlush(newHandshakeRequest()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    ChannelPipeline channelPipelinePipeline2 = channelFuture.channel().pipeline();
                    ChannelHandlerContext channelHandlerContextContext = channelPipelinePipeline2.context(HttpRequestEncoder.class);
                    if (channelHandlerContextContext == null) {
                        channelHandlerContextContext = channelPipelinePipeline2.context(HttpClientCodec.class);
                    }
                    if (channelHandlerContextContext == null) {
                        channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec"));
                        return;
                    } else {
                        channelPipelinePipeline2.addAfter(channelHandlerContextContext.name(), "ws-encoder", WebSocketClientHandshaker.this.newWebSocketEncoder());
                        channelPromise.setSuccess();
                        return;
                    }
                }
                channelPromise.setFailure(channelFuture.cause());
            }
        });
        return channelPromise;
    }

    public final void finishHandshake(Channel channel, FullHttpResponse fullHttpResponse) {
        verify(fullHttpResponse);
        String str = fullHttpResponse.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
        String strTrim = str != null ? str.trim() : null;
        String str2 = this.expectedSubprotocol;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.isEmpty() && strTrim == null) {
            setActualSubprotocol(this.expectedSubprotocol);
        } else {
            if (!str2.isEmpty() && strTrim != null && !strTrim.isEmpty()) {
                for (String str3 : str2.split(UtilVerisenseDriver.CSV_DELIMITER)) {
                    if (str3.trim().equals(strTrim)) {
                        setActualSubprotocol(strTrim);
                    }
                }
            }
            throw new WebSocketHandshakeException(String.format("Invalid subprotocol. Actual: %s. Expected one of: %s", strTrim, this.expectedSubprotocol));
        }
        setHandshakeComplete();
        final ChannelPipeline channelPipelinePipeline = channel.pipeline();
        HttpContentDecompressor httpContentDecompressor = (HttpContentDecompressor) channelPipelinePipeline.get(HttpContentDecompressor.class);
        if (httpContentDecompressor != null) {
            channelPipelinePipeline.remove(httpContentDecompressor);
        }
        HttpObjectAggregator httpObjectAggregator = (HttpObjectAggregator) channelPipelinePipeline.get(HttpObjectAggregator.class);
        if (httpObjectAggregator != null) {
            channelPipelinePipeline.remove(httpObjectAggregator);
        }
        final ChannelHandlerContext channelHandlerContextContext = channelPipelinePipeline.context(HttpResponseDecoder.class);
        if (channelHandlerContextContext == null) {
            ChannelHandlerContext channelHandlerContextContext2 = channelPipelinePipeline.context(HttpClientCodec.class);
            if (channelHandlerContextContext2 == null) {
                throw new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec");
            }
            final HttpClientCodec httpClientCodec = (HttpClientCodec) channelHandlerContextContext2.handler();
            httpClientCodec.removeOutboundHandler();
            channelPipelinePipeline.addAfter(channelHandlerContextContext2.name(), "ws-decoder", newWebsocketDecoder());
            channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.2
                @Override // java.lang.Runnable
                public void run() {
                    channelPipelinePipeline.remove(httpClientCodec);
                }
            });
            return;
        }
        if (channelPipelinePipeline.get(HttpRequestEncoder.class) != null) {
            channelPipelinePipeline.remove(HttpRequestEncoder.class);
        }
        channelPipelinePipeline.addAfter(channelHandlerContextContext.name(), "ws-decoder", newWebsocketDecoder());
        channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.3
            @Override // java.lang.Runnable
            public void run() {
                channelPipelinePipeline.remove(channelHandlerContextContext.handler());
            }
        });
    }

    public final ChannelFuture processHandshake(Channel channel, HttpResponse httpResponse) {
        return processHandshake(channel, httpResponse, channel.newPromise());
    }

    public final ChannelFuture processHandshake(final Channel channel, HttpResponse httpResponse, final ChannelPromise channelPromise) {
        if (httpResponse instanceof FullHttpResponse) {
            try {
                finishHandshake(channel, (FullHttpResponse) httpResponse);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            ChannelPipeline channelPipelinePipeline = channel.pipeline();
            ChannelHandlerContext channelHandlerContextContext = channelPipelinePipeline.context(HttpResponseDecoder.class);
            if (channelHandlerContextContext == null && (channelHandlerContextContext = channelPipelinePipeline.context(HttpClientCodec.class)) == null) {
                return channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            }
            channelPipelinePipeline.addAfter(channelHandlerContextContext.name(), "httpAggregator", new HttpObjectAggregator(8192));
            channelPipelinePipeline.addAfter("httpAggregator", "handshaker", new SimpleChannelInboundHandler<FullHttpResponse>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler
                public void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    try {
                        WebSocketClientHandshaker.this.finishHandshake(channel, fullHttpResponse);
                        channelPromise.setSuccess();
                    } catch (Throwable th2) {
                        channelPromise.setFailure(th2);
                    }
                }

                @Override
                // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
                public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th2) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    channelPromise.setFailure(th2);
                }

                @Override
                // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
                public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
                    if (!channelPromise.isDone()) {
                        channelPromise.tryFailure(new ClosedChannelException());
                    }
                    channelHandlerContext.fireChannelInactive();
                }
            });
            try {
                channelHandlerContextContext.fireChannelRead(ReferenceCountUtil.retain(httpResponse));
            } catch (Throwable th2) {
                channelPromise.setFailure(th2);
            }
        }
        return channelPromise;
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        ObjectUtil.checkNotNull(channel, "channel");
        return close(channel, closeWebSocketFrame, channel.newPromise());
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channel, "channel");
        channel.writeAndFlush(closeWebSocketFrame, channelPromise);
        applyForceCloseTimeout(channel, channelPromise);
        return channelPromise;
    }

    private void applyForceCloseTimeout(final Channel channel, ChannelFuture channelFuture) {
        final long j = this.forceCloseTimeoutMillis;
        if (j > 0 && channel.isActive() && this.forceCloseInit == 0) {
            channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                    if (channelFuture2.isSuccess() && channel.isActive() && WebSocketClientHandshaker.FORCE_CLOSE_INIT_UPDATER.compareAndSet(this, 0, 1)) {
                        final ScheduledFuture<?> scheduledFutureSchedule = channel.eventLoop().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (channel.isActive()) {
                                    channel.close();
                                    WebSocketClientHandshaker.this.forceCloseComplete = true;
                                }
                            }
                        }, j, TimeUnit.MILLISECONDS);
                        channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.2
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture3) throws Exception {
                                scheduledFutureSchedule.cancel(false);
                            }
                        });
                    }
                }
            });
        }
    }

    protected String upgradeUrl(URI uri) {
        if (this.absoluteUpgradeUrl) {
            return uri.toString();
        }
        String rawPath = uri.getRawPath();
        if (rawPath == null || rawPath.isEmpty()) {
            rawPath = "/";
        }
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null || rawQuery.isEmpty()) {
            return rawPath;
        }
        return rawPath + '?' + rawQuery;
    }
}
