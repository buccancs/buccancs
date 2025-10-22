package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedMessageTypeException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.HttpConversionUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.collection.IntObjectHashMap;
import io.grpc.netty.shaded.io.netty.util.collection.IntObjectMap;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogLevel;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* loaded from: classes3.dex */
public class Http2FrameCodec extends Http2ConnectionHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger LOG = InternalLoggerFactory.getInstance((Class<?>) Http2FrameCodec.class);
    protected final Http2Connection.PropertyKey streamKey;
    private final IntObjectMap<DefaultHttp2FrameStream> frameStreamToInitializeMap;
    private final Integer initialFlowControlWindowSize;
    private final Http2Connection.PropertyKey upgradeKey;
    ChannelHandlerContext ctx;
    private int numBufferedStreams;

    Http2FrameCodec(Http2ConnectionEncoder http2ConnectionEncoder, Http2ConnectionDecoder http2ConnectionDecoder, Http2Settings http2Settings, boolean z) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z);
        this.frameStreamToInitializeMap = new IntObjectHashMap(8);
        http2ConnectionDecoder.frameListener(new FrameListener());
        connection().addListener(new ConnectionListener());
        ((Http2RemoteFlowController) connection().remote().flowController()).listener(new Http2RemoteFlowControllerListener());
        this.streamKey = connection().newKey();
        this.upgradeKey = connection().newKey();
        this.initialFlowControlWindowSize = http2Settings.initialWindowSize();
    }

    static /* synthetic */ int access$410(Http2FrameCodec http2FrameCodec) {
        int i = http2FrameCodec.numBufferedStreams;
        http2FrameCodec.numBufferedStreams = i - 1;
        return i;
    }

    void handlerAdded0(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    DefaultHttp2FrameStream newStream() {
        return new DefaultHttp2FrameStream();
    }

    final void forEachActiveStream(final Http2FrameStreamVisitor http2FrameStreamVisitor) throws Http2Exception {
        if (connection().numActiveStreams() > 0) {
            connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec.1
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
                public boolean visit(Http2Stream http2Stream) {
                    try {
                        return http2FrameStreamVisitor.visit((Http2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey));
                    } catch (Throwable th) {
                        Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
                        http2FrameCodec.onError(http2FrameCodec.ctx, false, th);
                        return false;
                    }
                }
            });
        }
    }

    int numInitializingStreams() {
        return this.frameStreamToInitializeMap.size();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public final void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
        handlerAdded0(channelHandlerContext);
        Http2Connection http2ConnectionConnection = connection();
        if (http2ConnectionConnection.isServer()) {
            tryExpandConnectionFlowControlWindow(http2ConnectionConnection);
        }
    }

    private void tryExpandConnectionFlowControlWindow(Http2Connection http2Connection) throws Http2Exception {
        if (this.initialFlowControlWindowSize != null) {
            Http2Stream http2StreamConnectionStream = http2Connection.connectionStream();
            Http2LocalFlowController http2LocalFlowController = (Http2LocalFlowController) http2Connection.local().flowController();
            int iIntValue = this.initialFlowControlWindowSize.intValue() - http2LocalFlowController.initialWindowSize(http2StreamConnectionStream);
            if (iIntValue > 0) {
                http2LocalFlowController.incrementWindowSize(http2StreamConnectionStream, Math.max(iIntValue << 1, iIntValue));
                flush(this.ctx);
            }
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public final void userEventTriggered(final ChannelHandlerContext channelHandlerContext, final Object obj) throws Exception {
        if (obj == Http2ConnectionPrefaceAndSettingsFrameWrittenEvent.INSTANCE) {
            tryExpandConnectionFlowControlWindow(connection());
            channelHandlerContext.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec.2
                @Override // java.lang.Runnable
                public void run() {
                    channelHandlerContext.fireUserEventTriggered(obj);
                }
            });
            return;
        }
        if (obj instanceof HttpServerUpgradeHandler.UpgradeEvent) {
            HttpServerUpgradeHandler.UpgradeEvent upgradeEvent = (HttpServerUpgradeHandler.UpgradeEvent) obj;
            try {
                onUpgradeEvent(channelHandlerContext, upgradeEvent.retain());
                Http2Stream http2StreamStream = connection().stream(1);
                if (http2StreamStream.getProperty(this.streamKey) == null) {
                    onStreamActive0(http2StreamStream);
                }
                upgradeEvent.upgradeRequest().headers().setInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), 1);
                http2StreamStream.setProperty(this.upgradeKey, true);
                InboundHttpToHttp2Adapter.handle(channelHandlerContext, connection(), decoder().frameListener(), upgradeEvent.upgradeRequest().retain());
                return;
            } finally {
                upgradeEvent.release();
            }
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        if (obj instanceof Http2DataFrame) {
            Http2DataFrame http2DataFrame = (Http2DataFrame) obj;
            encoder().writeData(channelHandlerContext, http2DataFrame.stream().id(), http2DataFrame.content(), http2DataFrame.padding(), http2DataFrame.isEndStream(), channelPromise);
            return;
        }
        if (obj instanceof Http2HeadersFrame) {
            writeHeadersFrame(channelHandlerContext, (Http2HeadersFrame) obj, channelPromise);
            return;
        }
        if (obj instanceof Http2WindowUpdateFrame) {
            Http2WindowUpdateFrame http2WindowUpdateFrame = (Http2WindowUpdateFrame) obj;
            Http2FrameStream http2FrameStreamStream = http2WindowUpdateFrame.stream();
            try {
                if (http2FrameStreamStream == null) {
                    increaseInitialConnectionWindow(http2WindowUpdateFrame.windowSizeIncrement());
                } else {
                    consumeBytes(http2FrameStreamStream.id(), http2WindowUpdateFrame.windowSizeIncrement());
                }
                channelPromise.setSuccess();
                return;
            } catch (Throwable th) {
                channelPromise.setFailure(th);
                return;
            }
        }
        if (obj instanceof Http2ResetFrame) {
            Http2ResetFrame http2ResetFrame = (Http2ResetFrame) obj;
            if (connection().streamMayHaveExisted(http2ResetFrame.stream().id())) {
                encoder().writeRstStream(channelHandlerContext, http2ResetFrame.stream().id(), http2ResetFrame.errorCode(), channelPromise);
                return;
            } else {
                ReferenceCountUtil.release(http2ResetFrame);
                channelPromise.setFailure((Throwable) Http2Exception.streamError(http2ResetFrame.stream().id(), Http2Error.PROTOCOL_ERROR, "Stream never existed", new Object[0]));
                return;
            }
        }
        if (obj instanceof Http2PingFrame) {
            Http2PingFrame http2PingFrame = (Http2PingFrame) obj;
            encoder().writePing(channelHandlerContext, http2PingFrame.ack(), http2PingFrame.content(), channelPromise);
            return;
        }
        if (obj instanceof Http2SettingsFrame) {
            encoder().writeSettings(channelHandlerContext, ((Http2SettingsFrame) obj).settings(), channelPromise);
            return;
        }
        if (obj instanceof Http2SettingsAckFrame) {
            encoder().writeSettingsAck(channelHandlerContext, channelPromise);
            return;
        }
        if (obj instanceof Http2GoAwayFrame) {
            writeGoAwayFrame(channelHandlerContext, (Http2GoAwayFrame) obj, channelPromise);
            return;
        }
        if (obj instanceof Http2UnknownFrame) {
            Http2UnknownFrame http2UnknownFrame = (Http2UnknownFrame) obj;
            encoder().writeFrame(channelHandlerContext, http2UnknownFrame.frameType(), http2UnknownFrame.stream().id(), http2UnknownFrame.flags(), http2UnknownFrame.content(), channelPromise);
        } else if (!(obj instanceof Http2Frame)) {
            channelHandlerContext.write(obj, channelPromise);
        } else {
            ReferenceCountUtil.release(obj);
            throw new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[0]);
        }
    }

    private void increaseInitialConnectionWindow(int i) throws Http2Exception {
        ((Http2LocalFlowController) connection().local().flowController()).incrementWindowSize(connection().connectionStream(), i);
    }

    final boolean consumeBytes(int i, int i2) throws Http2Exception {
        Http2Stream http2StreamStream = connection().stream(i);
        if (http2StreamStream != null && i == 1) {
            if (Boolean.TRUE.equals((Boolean) http2StreamStream.getProperty(this.upgradeKey))) {
                return false;
            }
        }
        return ((Http2LocalFlowController) connection().local().flowController()).consumeBytes(http2StreamStream, i2);
    }

    private void writeGoAwayFrame(ChannelHandlerContext channelHandlerContext, Http2GoAwayFrame http2GoAwayFrame, ChannelPromise channelPromise) {
        if (http2GoAwayFrame.lastStreamId() > -1) {
            http2GoAwayFrame.release();
            throw new IllegalArgumentException("Last stream id must not be set on GOAWAY frame");
        }
        long jLastStreamCreated = connection().remote().lastStreamCreated() + (http2GoAwayFrame.extraStreamIds() * 2);
        if (jLastStreamCreated > 2147483647L) {
            jLastStreamCreated = 2147483647L;
        }
        goAway(channelHandlerContext, (int) jLastStreamCreated, http2GoAwayFrame.errorCode(), http2GoAwayFrame.content(), channelPromise);
    }

    private void writeHeadersFrame(ChannelHandlerContext channelHandlerContext, Http2HeadersFrame http2HeadersFrame, ChannelPromise channelPromise) {
        if (Http2CodecUtil.isStreamIdValid(http2HeadersFrame.stream().id())) {
            encoder().writeHeaders(channelHandlerContext, http2HeadersFrame.stream().id(), http2HeadersFrame.headers(), http2HeadersFrame.padding(), http2HeadersFrame.isEndStream(), channelPromise);
            return;
        }
        DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2HeadersFrame.stream();
        Http2Connection http2ConnectionConnection = connection();
        final int iIncrementAndGetNextStreamId = http2ConnectionConnection.local().incrementAndGetNextStreamId();
        if (iIncrementAndGetNextStreamId < 0) {
            channelPromise.setFailure((Throwable) new Http2NoMoreStreamIdsException());
            onHttp2Frame(channelHandlerContext, new DefaultHttp2GoAwayFrame(http2ConnectionConnection.isServer() ? Integer.MAX_VALUE : 2147483646, Http2Error.NO_ERROR.code(), ByteBufUtil.writeAscii(channelHandlerContext.alloc(), "Stream IDs exhausted on local stream creation")));
            return;
        }
        defaultHttp2FrameStream.id = iIncrementAndGetNextStreamId;
        this.frameStreamToInitializeMap.put(iIncrementAndGetNextStreamId, (int) defaultHttp2FrameStream);
        encoder().writeHeaders(channelHandlerContext, iIncrementAndGetNextStreamId, http2HeadersFrame.headers(), http2HeadersFrame.padding(), http2HeadersFrame.isEndStream(), channelPromise);
        if (!channelPromise.isDone()) {
            this.numBufferedStreams++;
            channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec.3
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) {
                    Http2FrameCodec.access$410(Http2FrameCodec.this);
                    Http2FrameCodec.this.handleHeaderFuture(channelFuture, iIncrementAndGetNextStreamId);
                }
            });
        } else {
            handleHeaderFuture(channelPromise, iIncrementAndGetNextStreamId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHeaderFuture(ChannelFuture channelFuture, int i) {
        if (channelFuture.isSuccess()) {
            return;
        }
        this.frameStreamToInitializeMap.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStreamActive0(Http2Stream http2Stream) {
        if (http2Stream.id() == 1 || !connection().local().isValidStreamId(http2Stream.id())) {
            onHttp2StreamStateChanged(this.ctx, newStream().setStreamAndProperty(this.streamKey, http2Stream));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    protected void onConnectionError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception http2Exception) {
        if (!z) {
            channelHandlerContext.fireExceptionCaught(th);
        }
        super.onConnectionError(channelHandlerContext, z, th, http2Exception);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    protected final void onStreamError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception.StreamException streamException) {
        Http2Stream http2StreamStream = connection().stream(streamException.streamId());
        if (http2StreamStream == null) {
            onHttp2UnknownStreamError(channelHandlerContext, th, streamException);
            super.onStreamError(channelHandlerContext, z, th, streamException);
            return;
        }
        Http2FrameStream http2FrameStream = (Http2FrameStream) http2StreamStream.getProperty(this.streamKey);
        if (http2FrameStream == null) {
            LOG.warn("Stream exception thrown without stream object attached.", th);
            super.onStreamError(channelHandlerContext, z, th, streamException);
        } else {
            if (z) {
                return;
            }
            onHttp2FrameStreamException(channelHandlerContext, new Http2FrameStreamException(http2FrameStream, streamException.error(), th));
        }
    }

    private void onHttp2UnknownStreamError(ChannelHandlerContext channelHandlerContext, Throwable th, Http2Exception.StreamException streamException) {
        LOG.log(streamException.error() == Http2Error.STREAM_CLOSED ? InternalLogLevel.DEBUG : InternalLogLevel.WARN, "Stream exception thrown for unknown stream {}.", Integer.valueOf(streamException.streamId()), th);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    protected final boolean isGracefulShutdownComplete() {
        return super.isGracefulShutdownComplete() && this.numBufferedStreams == 0;
    }

    private void onUpgradeEvent(ChannelHandlerContext channelHandlerContext, HttpServerUpgradeHandler.UpgradeEvent upgradeEvent) {
        channelHandlerContext.fireUserEventTriggered((Object) upgradeEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHttp2StreamWritabilityChanged(ChannelHandlerContext channelHandlerContext, DefaultHttp2FrameStream defaultHttp2FrameStream, boolean z) {
        channelHandlerContext.fireUserEventTriggered((Object) defaultHttp2FrameStream.writabilityChanged);
    }

    void onHttp2StreamStateChanged(ChannelHandlerContext channelHandlerContext, DefaultHttp2FrameStream defaultHttp2FrameStream) {
        channelHandlerContext.fireUserEventTriggered((Object) defaultHttp2FrameStream.stateChanged);
    }

    void onHttp2Frame(ChannelHandlerContext channelHandlerContext, Http2Frame http2Frame) {
        channelHandlerContext.fireChannelRead((Object) http2Frame);
    }

    void onHttp2FrameStreamException(ChannelHandlerContext channelHandlerContext, Http2FrameStreamException http2FrameStreamException) {
        channelHandlerContext.fireExceptionCaught((Throwable) http2FrameStreamException);
    }

    static class DefaultHttp2FrameStream implements Http2FrameStream {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final Http2FrameStreamEvent stateChanged = Http2FrameStreamEvent.stateChanged(this);
        final Http2FrameStreamEvent writabilityChanged = Http2FrameStreamEvent.writabilityChanged(this);
        Channel attachment;
        volatile Http2Stream stream;
        private volatile int id = -1;

        DefaultHttp2FrameStream() {
        }

        DefaultHttp2FrameStream setStreamAndProperty(Http2Connection.PropertyKey propertyKey, Http2Stream http2Stream) {
            this.stream = http2Stream;
            http2Stream.setProperty(propertyKey, this);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameStream
        public int id() {
            Http2Stream http2Stream = this.stream;
            return http2Stream == null ? this.id : http2Stream.id();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameStream
        public Http2Stream.State state() {
            Http2Stream http2Stream = this.stream;
            return http2Stream == null ? Http2Stream.State.IDLE : http2Stream.state();
        }

        public String toString() {
            return String.valueOf(id());
        }
    }

    private final class ConnectionListener extends Http2ConnectionAdapter {
        private ConnectionListener() {
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamAdded(Http2Stream http2Stream) {
            DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) Http2FrameCodec.this.frameStreamToInitializeMap.remove(http2Stream.id());
            if (defaultHttp2FrameStream != null) {
                defaultHttp2FrameStream.setStreamAndProperty(Http2FrameCodec.this.streamKey, http2Stream);
            }
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamActive(Http2Stream http2Stream) {
            Http2FrameCodec.this.onStreamActive0(http2Stream);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamClosed(Http2Stream http2Stream) {
            onHttp2StreamStateChanged0(http2Stream);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
        public void onStreamHalfClosed(Http2Stream http2Stream) {
            onHttp2StreamStateChanged0(http2Stream);
        }

        private void onHttp2StreamStateChanged0(Http2Stream http2Stream) {
            DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey);
            if (defaultHttp2FrameStream != null) {
                Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
                http2FrameCodec.onHttp2StreamStateChanged(http2FrameCodec.ctx, defaultHttp2FrameStream);
            }
        }
    }

    private final class FrameListener implements Http2FrameListener {
        private FrameListener() {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2UnknownFrame(b, http2Flags, byteBuf).stream(requireStream(i)).retain());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2SettingsFrame(http2Settings));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2PingFrame(j, false));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2PingFrame(j, true));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2ResetFrame(j).stream(requireStream(i)));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) {
            if (i == 0) {
                return;
            }
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2WindowUpdateFrame(i2).stream(requireStream(i)));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) {
            onHeadersRead(channelHandlerContext, i, http2Headers, i3, z2);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2HeadersFrame(http2Headers, z, i2).stream(requireStream(i)));
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2DataFrame(byteBuf, z, i2).stream(requireStream(i)).retain());
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, new DefaultHttp2GoAwayFrame(i, j, byteBuf).retain());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) {
            Http2FrameCodec.this.onHttp2Frame(channelHandlerContext, Http2SettingsAckFrame.INSTANCE);
        }

        private Http2FrameStream requireStream(int i) {
            Http2FrameStream http2FrameStream = (Http2FrameStream) Http2FrameCodec.this.connection().stream(i).getProperty(Http2FrameCodec.this.streamKey);
            if (http2FrameStream != null) {
                return http2FrameStream;
            }
            throw new IllegalStateException("Stream object required for identifier: " + i);
        }
    }

    private final class Http2RemoteFlowControllerListener implements Http2RemoteFlowController.Listener {
        private Http2RemoteFlowControllerListener() {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.Listener
        public void writabilityChanged(Http2Stream http2Stream) {
            DefaultHttp2FrameStream defaultHttp2FrameStream = (DefaultHttp2FrameStream) http2Stream.getProperty(Http2FrameCodec.this.streamKey);
            if (defaultHttp2FrameStream == null) {
                return;
            }
            Http2FrameCodec http2FrameCodec = Http2FrameCodec.this;
            http2FrameCodec.onHttp2StreamWritabilityChanged(http2FrameCodec.ctx, defaultHttp2FrameStream, ((Http2RemoteFlowController) Http2FrameCodec.this.connection().remote().flowController()).isWritable(http2Stream));
        }
    }
}
