package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import io.grpc.InternalMetadata;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.shaded.io.grpc.netty.ListeningEncoder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerStream;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Error;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2InboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2OutboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.WeightedFairQueueByteDistributor;
import io.grpc.netty.shaded.io.netty.handler.logging.LogLevel;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import org.apache.http.HttpStatus;

/* loaded from: classes2.dex */
class NettyServerHandler extends AbstractNettyHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long GRACEFUL_SHUTDOWN_PING = 40715087873L;
    private static final long KEEPALIVE_PING = 57005;
    private static final Logger logger = Logger.getLogger(NettyServerHandler.class.getName());
    private static final long GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(10);
    private final KeepAliveEnforcer keepAliveEnforcer;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private final long maxConnectionAgeGraceInNanos;
    private final long maxConnectionAgeInNanos;
    private final int maxMessageSize;
    private final Http2Connection.PropertyKey streamKey;
    private final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
    private final ServerTransportListener transportListener;
    private final TransportTracer transportTracer;
    private Attributes attributes;
    private Throwable connectionError;
    @CheckForNull
    private GracefulShutdown gracefulShutdown;
    @CheckForNull
    private KeepAliveManager keepAliveManager;
    private AsciiString lastKnownAuthority;
    @CheckForNull
    private ScheduledFuture<?> maxConnectionAgeMonitor;
    @CheckForNull
    private MaxConnectionIdleManager maxConnectionIdleManager;
    private Attributes negotiationAttributes;
    private InternalChannelz.Security securityInfo;
    private WriteQueue serverWriteQueue;
    private boolean teWarningLogged;

    private NettyServerHandler(ChannelPromise channelPromise, final Http2Connection http2Connection, ServerTransportListener serverTransportListener, List<? extends ServerStreamTracer.Factory> list, TransportTracer transportTracer, Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, int i, long j, long j2, long j3, long j4, long j5, final KeepAliveEnforcer keepAliveEnforcer, boolean z) {
        super(channelPromise, http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, z);
        final MaxConnectionIdleManager maxConnectionIdleManager = j3 == Long.MAX_VALUE ? null : new MaxConnectionIdleManager(j3) { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.1
            @Override
                // io.grpc.netty.shaded.io.grpc.netty.MaxConnectionIdleManager
            void close(ChannelHandlerContext channelHandlerContext) {
                if (NettyServerHandler.this.gracefulShutdown == null) {
                    NettyServerHandler nettyServerHandler = NettyServerHandler.this;
                    nettyServerHandler.gracefulShutdown = nettyServerHandler.new GracefulShutdown("max_idle", null);
                    NettyServerHandler.this.gracefulShutdown.start(channelHandlerContext);
                    channelHandlerContext.flush();
                }
            }
        };
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.2
            @Override
            // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamActive(Http2Stream http2Stream) {
                if (http2Connection.numActiveStreams() == 1) {
                    keepAliveEnforcer.onTransportActive();
                    MaxConnectionIdleManager maxConnectionIdleManager2 = maxConnectionIdleManager;
                    if (maxConnectionIdleManager2 != null) {
                        maxConnectionIdleManager2.onTransportActive();
                    }
                }
            }

            @Override
            // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                if (http2Connection.numActiveStreams() == 0) {
                    keepAliveEnforcer.onTransportIdle();
                    MaxConnectionIdleManager maxConnectionIdleManager2 = maxConnectionIdleManager;
                    if (maxConnectionIdleManager2 != null) {
                        maxConnectionIdleManager2.onTransportIdle();
                    }
                }
            }
        });
        Preconditions.checkArgument(i >= 0, "maxMessageSize must be non-negative: %s", i);
        this.maxMessageSize = i;
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.maxConnectionIdleManager = maxConnectionIdleManager;
        this.maxConnectionAgeInNanos = j4;
        this.maxConnectionAgeGraceInNanos = j5;
        this.keepAliveEnforcer = (KeepAliveEnforcer) Preconditions.checkNotNull(keepAliveEnforcer, "keepAliveEnforcer");
        this.streamKey = http2ConnectionEncoder.connection().newKey();
        this.transportListener = (ServerTransportListener) Preconditions.checkNotNull(serverTransportListener, "transportListener");
        this.streamTracerFactories = (List) Preconditions.checkNotNull(list, "streamTracerFactories");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        decoder().frameListener(new FrameListener());
    }

    static NettyServerHandler newHandler(ServerTransportListener serverTransportListener, ChannelPromise channelPromise, List<? extends ServerStreamTracer.Factory> list, TransportTracer transportTracer, int i, boolean z, int i2, int i3, int i4, long j, long j2, long j3, long j4, long j5, boolean z2, long j6) {
        Preconditions.checkArgument(i3 > 0, "maxHeaderListSize must be positive: %s", i3);
        Http2FrameLogger http2FrameLogger = new Http2FrameLogger(LogLevel.DEBUG, (Class<?>) NettyServerHandler.class);
        return newHandler(channelPromise, new Http2InboundFrameLogger(new DefaultHttp2FrameReader(new GrpcHttp2HeadersUtils.GrpcHttp2ServerHeadersDecoder(i3)), http2FrameLogger), new Http2OutboundFrameLogger(new DefaultHttp2FrameWriter(), http2FrameLogger), serverTransportListener, list, transportTracer, i, z, i2, i3, i4, j, j2, j3, j4, j5, z2, j6);
    }

    static NettyServerHandler newHandler(ChannelPromise channelPromise, Http2FrameReader http2FrameReader, Http2FrameWriter http2FrameWriter, ServerTransportListener serverTransportListener, List<? extends ServerStreamTracer.Factory> list, TransportTracer transportTracer, int i, boolean z, int i2, int i3, int i4, long j, long j2, long j3, long j4, long j5, boolean z2, long j6) {
        Preconditions.checkArgument(i > 0, "maxStreams must be positive: %s", i);
        Preconditions.checkArgument(i2 > 0, "flowControlWindow must be positive: %s", i2);
        Preconditions.checkArgument(i3 > 0, "maxHeaderListSize must be positive: %s", i3);
        Preconditions.checkArgument(i4 > 0, "maxMessageSize must be positive: %s", i4);
        DefaultHttp2Connection defaultHttp2Connection = new DefaultHttp2Connection(true);
        WeightedFairQueueByteDistributor weightedFairQueueByteDistributor = new WeightedFairQueueByteDistributor(defaultHttp2Connection);
        weightedFairQueueByteDistributor.allocationQuantum(16384);
        defaultHttp2Connection.remote().flowController(new DefaultHttp2RemoteFlowController(defaultHttp2Connection, weightedFairQueueByteDistributor));
        KeepAliveEnforcer keepAliveEnforcer = new KeepAliveEnforcer(z2, j6, TimeUnit.NANOSECONDS);
        defaultHttp2Connection.local().flowController(new DefaultHttp2LocalFlowController(defaultHttp2Connection, 0.5f, true));
        Http2ControlFrameLimitEncoder http2ControlFrameLimitEncoder = new Http2ControlFrameLimitEncoder(new ListeningEncoder.ListeningDefaultHttp2ConnectionEncoder(defaultHttp2Connection, new WriteMonitoringFrameWriter(http2FrameWriter, keepAliveEnforcer)), 10000);
        DefaultHttp2ConnectionDecoder defaultHttp2ConnectionDecoder = new DefaultHttp2ConnectionDecoder(defaultHttp2Connection, http2ControlFrameLimitEncoder, http2FrameReader);
        Http2Settings http2Settings = new Http2Settings();
        http2Settings.initialWindowSize(i2);
        http2Settings.maxConcurrentStreams(i);
        http2Settings.maxHeaderListSize(i3);
        return new NettyServerHandler(channelPromise, defaultHttp2Connection, serverTransportListener, list, transportTracer, defaultHttp2ConnectionDecoder, http2ControlFrameLimitEncoder, http2Settings, i4, j, j2, j3, j4, j5, keepAliveEnforcer, z);
    }

    @Nullable
    Throwable connectionError() {
        return this.connectionError;
    }

    KeepAliveManager getKeepAliveManagerForTest() {
        return this.keepAliveManager;
    }

    void setKeepAliveManagerForTest(KeepAliveManager keepAliveManager) {
        this.keepAliveManager = keepAliveManager;
    }

    InternalChannelz.Security getSecurityInfo() {
        return this.securityInfo;
    }

    WriteQueue getWriteQueue() {
        return this.serverWriteQueue;
    }

    @Override
    // io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(final ChannelHandlerContext channelHandlerContext) throws Exception {
        this.serverWriteQueue = new WriteQueue(channelHandlerContext.channel());
        if (this.maxConnectionAgeInNanos != Long.MAX_VALUE) {
            this.maxConnectionAgeMonitor = channelHandlerContext.executor().schedule((Runnable) new LogExceptionRunnable(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    if (NettyServerHandler.this.gracefulShutdown == null) {
                        NettyServerHandler nettyServerHandler = NettyServerHandler.this;
                        nettyServerHandler.gracefulShutdown = nettyServerHandler.new GracefulShutdown("max_age", Long.valueOf(nettyServerHandler.maxConnectionAgeGraceInNanos));
                        NettyServerHandler.this.gracefulShutdown.start(channelHandlerContext);
                        channelHandlerContext.flush();
                    }
                }
            }), this.maxConnectionAgeInNanos, TimeUnit.NANOSECONDS);
        }
        MaxConnectionIdleManager maxConnectionIdleManager = this.maxConnectionIdleManager;
        if (maxConnectionIdleManager != null) {
            maxConnectionIdleManager.start(channelHandlerContext);
        }
        if (this.keepAliveTimeInNanos != Long.MAX_VALUE) {
            KeepAliveManager keepAliveManager = new KeepAliveManager(new KeepAlivePinger(channelHandlerContext), channelHandlerContext.executor(), this.keepAliveTimeInNanos, this.keepAliveTimeoutInNanos, true);
            this.keepAliveManager = keepAliveManager;
            keepAliveManager.onTransportStarted();
        }
        if (this.transportTracer != null) {
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader(encoder().connection(), channelHandlerContext) { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.4
                static final /* synthetic */ boolean $assertionsDisabled = false;
                final /* synthetic */ Http2Connection val$connection;
                final /* synthetic */ ChannelHandlerContext val$ctx;
                private final Http2FlowController local;
                private final Http2FlowController remote;

                {
                    this.val$connection = http2Connection;
                    this.val$ctx = channelHandlerContext;
                    this.local = http2Connection.local().flowController();
                    this.remote = http2Connection.remote().flowController();
                }

                @Override // io.grpc.internal.TransportTracer.FlowControlReader
                public TransportTracer.FlowControlWindows read() {
                    return new TransportTracer.FlowControlWindows(this.local.windowSize(this.val$connection.connectionStream()), this.remote.windowSize(this.val$connection.connectionStream()));
                }
            });
        }
        super.handlerAdded(channelHandlerContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers) throws Throwable {
        NettyServerStream.TransportState transportState;
        try {
            CharSequence charSequencePath = http2Headers.path();
            if (charSequencePath == null) {
                respondWithHttpError(channelHandlerContext, i, 404, Status.Code.UNIMPLEMENTED, "Expected path but is missing");
                return;
            }
            if (charSequencePath.charAt(0) != '/') {
                respondWithHttpError(channelHandlerContext, i, 404, Status.Code.UNIMPLEMENTED, String.format("Expected path to start with /: %s", charSequencePath));
                return;
            }
            String string = charSequencePath.subSequence(1, charSequencePath.length()).toString();
            CharSequence charSequence = http2Headers.get(Utils.CONTENT_TYPE_HEADER);
            if (charSequence == null) {
                respondWithHttpError(channelHandlerContext, i, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, Status.Code.INTERNAL, "Content-Type is missing from the request");
                return;
            }
            String string2 = charSequence.toString();
            if (!GrpcUtil.isGrpcContentType(string2)) {
                respondWithHttpError(channelHandlerContext, i, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, Status.Code.INTERNAL, String.format("Content-Type '%s' is not supported", string2));
                return;
            }
            if (!Utils.HTTP_METHOD.contentEquals(http2Headers.method())) {
                respondWithHttpError(channelHandlerContext, i, 405, Status.Code.INTERNAL, String.format("Method '%s' is not supported", http2Headers.method()));
                return;
            }
            if (!this.teWarningLogged && !Utils.TE_TRAILERS.contentEquals(http2Headers.get(Utils.TE_HEADER))) {
                logger.warning(String.format("Expected header TE: %s, but %s is received. This means some intermediate proxy may not support trailers", Utils.TE_TRAILERS, http2Headers.get(Utils.TE_HEADER)));
                this.teWarningLogged = true;
            }
            Http2Stream http2StreamRequireHttp2Stream = requireHttp2Stream(i);
            Metadata metadataConvertHeaders = Utils.convertHeaders(http2Headers);
            StatsTraceContext statsTraceContextNewServerContext = StatsTraceContext.newServerContext(this.streamTracerFactories, string, metadataConvertHeaders);
            NettyServerStream.TransportState transportState2 = new NettyServerStream.TransportState(this, channelHandlerContext.channel().eventLoop(), http2StreamRequireHttp2Stream, this.maxMessageSize, statsTraceContextNewServerContext, this.transportTracer, string);
            PerfMark.startTask("NettyServerHandler.onHeadersRead", transportState2.tag());
            try {
                this.transportListener.streamCreated(new NettyServerStream(channelHandlerContext.channel(), transportState2, this.attributes, getOrUpdateAuthority((AsciiString) http2Headers.authority()), statsTraceContextNewServerContext, this.transportTracer), string, metadataConvertHeaders);
                transportState2.onStreamAllocated();
                transportState = transportState2;
                try {
                    http2StreamRequireHttp2Stream.setProperty(this.streamKey, transportState);
                    PerfMark.stopTask("NettyServerHandler.onHeadersRead", transportState.tag());
                } catch (Throwable th) {
                    th = th;
                    PerfMark.stopTask("NettyServerHandler.onHeadersRead", transportState.tag());
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                transportState = transportState2;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in onHeadersRead()", (Throwable) e);
            throw newStreamException(i, e);
        }
    }

    private String getOrUpdateAuthority(AsciiString asciiString) {
        if (asciiString == null) {
            return null;
        }
        if (!asciiString.equals(this.lastKnownAuthority)) {
            this.lastKnownAuthority = asciiString;
        }
        return this.lastKnownAuthority.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataRead(int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
        flowControlPing().onDataRead(byteBuf.readableBytes(), i2);
        try {
            NettyServerStream.TransportState transportStateServerStream = serverStream(requireHttp2Stream(i));
            PerfMark.startTask("NettyServerHandler.onDataRead", transportStateServerStream.tag());
            try {
                transportStateServerStream.inboundDataReceived(byteBuf, z);
            } finally {
                PerfMark.stopTask("NettyServerHandler.onDataRead", transportStateServerStream.tag());
            }
        } catch (Throwable th) {
            logger.log(Level.WARNING, "Exception in onDataRead()", th);
            throw newStreamException(i, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRstStreamRead(int i, long j) throws Http2Exception {
        try {
            NettyServerStream.TransportState transportStateServerStream = serverStream(connection().stream(i));
            if (transportStateServerStream != null) {
                PerfMark.startTask("NettyServerHandler.onRstStreamRead", transportStateServerStream.tag());
                try {
                    transportStateServerStream.transportReportStatus(Status.CANCELLED.withDescription("RST_STREAM received for code " + j));
                    PerfMark.stopTask("NettyServerHandler.onRstStreamRead", transportStateServerStream.tag());
                } catch (Throwable th) {
                    PerfMark.stopTask("NettyServerHandler.onRstStreamRead", transportStateServerStream.tag());
                    throw th;
                }
            }
        } catch (Throwable th2) {
            logger.log(Level.WARNING, "Exception in onRstStreamRead()", th2);
            throw newStreamException(i, th2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    protected void onConnectionError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception http2Exception) {
        logger.log(Level.FINE, "Connection Error", th);
        this.connectionError = th;
        super.onConnectionError(channelHandlerContext, z, th, http2Exception);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler
    protected void onStreamError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th, Http2Exception.StreamException streamException) {
        logger.log(Level.WARNING, "Stream Error", th);
        NettyServerStream.TransportState transportStateServerStream = serverStream(connection().stream(Http2Exception.streamId(streamException)));
        Tag tag = transportStateServerStream != null ? transportStateServerStream.tag() : PerfMark.createTag();
        PerfMark.startTask("NettyServerHandler.onStreamError", tag);
        if (transportStateServerStream != null) {
            try {
                transportStateServerStream.transportReportStatus(Utils.statusFromThrowable(th));
            } finally {
                PerfMark.stopTask("NettyServerHandler.onStreamError", tag);
            }
        }
        super.onStreamError(channelHandlerContext, z, th, streamException);
    }

    @Override // io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler
    public void handleProtocolNegotiationCompleted(Attributes attributes, InternalChannelz.Security security) {
        this.negotiationAttributes = attributes;
        this.securityInfo = security;
        super.handleProtocolNegotiationCompleted(attributes, security);
        NettyClientHandler.writeBufferingAndRemove(ctx().channel());
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportTermination();
            }
            MaxConnectionIdleManager maxConnectionIdleManager = this.maxConnectionIdleManager;
            if (maxConnectionIdleManager != null) {
                maxConnectionIdleManager.onTransportTermination();
            }
            ScheduledFuture<?> scheduledFuture = this.maxConnectionAgeMonitor;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            final Status statusWithDescription = Status.UNAVAILABLE.withDescription("connection terminated for unknown reason");
            connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.5
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
                public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                    NettyServerStream.TransportState transportStateServerStream = NettyServerHandler.this.serverStream(http2Stream);
                    if (transportStateServerStream == null) {
                        return true;
                    }
                    transportStateServerStream.transportReportStatus(statusWithDescription);
                    return true;
                }
            });
        } finally {
            super.channelInactive(channelHandlerContext);
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (obj instanceof SendGrpcFrameCommand) {
            sendGrpcFrame(channelHandlerContext, (SendGrpcFrameCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof SendResponseHeadersCommand) {
            sendResponseHeaders(channelHandlerContext, (SendResponseHeadersCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof CancelServerStreamCommand) {
            cancelStream(channelHandlerContext, (CancelServerStreamCommand) obj, channelPromise);
            return;
        }
        if (obj instanceof ForcefulCloseCommand) {
            forcefulClose(channelHandlerContext, (ForcefulCloseCommand) obj, channelPromise);
            return;
        }
        AssertionError assertionError = new AssertionError("Write called for unexpected type: " + obj.getClass().getName());
        ReferenceCountUtil.release(obj);
        channelPromise.setFailure((Throwable) assertionError);
        throw assertionError;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (this.gracefulShutdown == null) {
            GracefulShutdown gracefulShutdown = new GracefulShutdown("app_requested", null);
            this.gracefulShutdown = gracefulShutdown;
            gracefulShutdown.start(channelHandlerContext);
            channelHandlerContext.flush();
        }
    }

    void returnProcessedBytes(Http2Stream http2Stream, int i) {
        try {
            decoder().flowController().consumeBytes(http2Stream, i);
        } catch (Http2Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void closeStreamWhenDone(ChannelPromise channelPromise, int i) throws Http2Exception {
        final NettyServerStream.TransportState transportStateServerStream = serverStream(requireHttp2Stream(i));
        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.6
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                transportStateServerStream.complete();
            }
        });
    }

    private void sendGrpcFrame(ChannelHandlerContext channelHandlerContext, SendGrpcFrameCommand sendGrpcFrameCommand, ChannelPromise channelPromise) throws Http2Exception {
        PerfMark.startTask("NettyServerHandler.sendGrpcFrame", sendGrpcFrameCommand.stream().tag());
        PerfMark.linkIn(sendGrpcFrameCommand.getLink());
        try {
            if (sendGrpcFrameCommand.endStream()) {
                closeStreamWhenDone(channelPromise, sendGrpcFrameCommand.stream().id());
            }
            encoder().writeData(channelHandlerContext, sendGrpcFrameCommand.stream().id(), sendGrpcFrameCommand.content(), 0, sendGrpcFrameCommand.endStream(), channelPromise);
        } finally {
            PerfMark.stopTask("NettyServerHandler.sendGrpcFrame", sendGrpcFrameCommand.stream().tag());
        }
    }

    private void sendResponseHeaders(ChannelHandlerContext channelHandlerContext, SendResponseHeadersCommand sendResponseHeadersCommand, ChannelPromise channelPromise) throws Http2Exception {
        PerfMark.startTask("NettyServerHandler.sendResponseHeaders", sendResponseHeadersCommand.stream().tag());
        PerfMark.linkIn(sendResponseHeadersCommand.getLink());
        try {
            int iId = sendResponseHeadersCommand.stream().id();
            if (connection().stream(iId) == null) {
                resetStream(channelHandlerContext, iId, Http2Error.CANCEL.code(), channelPromise);
                return;
            }
            if (sendResponseHeadersCommand.endOfStream()) {
                closeStreamWhenDone(channelPromise, iId);
            }
            encoder().writeHeaders(channelHandlerContext, iId, sendResponseHeadersCommand.headers(), 0, sendResponseHeadersCommand.endOfStream(), channelPromise);
        } finally {
            PerfMark.stopTask("NettyServerHandler.sendResponseHeaders", sendResponseHeadersCommand.stream().tag());
        }
    }

    private void cancelStream(ChannelHandlerContext channelHandlerContext, CancelServerStreamCommand cancelServerStreamCommand, ChannelPromise channelPromise) {
        PerfMark.startTask("NettyServerHandler.cancelStream", cancelServerStreamCommand.stream().tag());
        PerfMark.linkIn(cancelServerStreamCommand.getLink());
        try {
            cancelServerStreamCommand.stream().transportReportStatus(cancelServerStreamCommand.reason());
            encoder().writeRstStream(channelHandlerContext, cancelServerStreamCommand.stream().id(), Http2Error.CANCEL.code(), channelPromise);
        } finally {
            PerfMark.stopTask("NettyServerHandler.cancelStream", cancelServerStreamCommand.stream().tag());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forcefulClose(final ChannelHandlerContext channelHandlerContext, final ForcefulCloseCommand forcefulCloseCommand, ChannelPromise channelPromise) throws Exception {
        super.close(channelHandlerContext, channelPromise);
        connection().forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.7
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
            public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                NettyServerStream.TransportState transportStateServerStream = NettyServerHandler.this.serverStream(http2Stream);
                if (transportStateServerStream != null) {
                    PerfMark.startTask("NettyServerHandler.forcefulClose", transportStateServerStream.tag());
                    PerfMark.linkIn(forcefulCloseCommand.getLink());
                    try {
                        transportStateServerStream.transportReportStatus(forcefulCloseCommand.getStatus());
                        NettyServerHandler.this.resetStream(channelHandlerContext, http2Stream.id(), Http2Error.CANCEL.code(), channelHandlerContext.newPromise());
                    } finally {
                        PerfMark.stopTask("NettyServerHandler.forcefulClose", transportStateServerStream.tag());
                    }
                }
                http2Stream.close();
                return true;
            }
        });
    }

    private void respondWithHttpError(ChannelHandlerContext channelHandlerContext, int i, int i2, Status.Code code, String str) {
        Metadata metadata = new Metadata();
        metadata.put(InternalStatus.CODE_KEY, code.toStatus());
        metadata.put(InternalStatus.MESSAGE_KEY, str);
        byte[][] bArrSerialize = InternalMetadata.serialize(metadata);
        Http2Headers http2Headers = new DefaultHttp2Headers(true, bArrSerialize.length / 2).status("" + i2).set((Http2Headers) Utils.CONTENT_TYPE_HEADER, (AsciiString) "text/plain; encoding=utf-8");
        for (int i3 = 0; i3 < bArrSerialize.length; i3 += 2) {
            http2Headers.add((Http2Headers) new AsciiString(bArrSerialize[i3], false), new AsciiString(bArrSerialize[i3 + 1], false));
        }
        encoder().writeHeaders(channelHandlerContext, i, http2Headers, 0, false, channelHandlerContext.newPromise());
        encoder().writeData(channelHandlerContext, i, ByteBufUtil.writeUtf8(channelHandlerContext.alloc(), str), 0, true, channelHandlerContext.newPromise());
    }

    private Http2Stream requireHttp2Stream(int i) {
        Http2Stream http2StreamStream = connection().stream(i);
        if (http2StreamStream != null) {
            return http2StreamStream;
        }
        throw new AssertionError("Stream does not exist: " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NettyServerStream.TransportState serverStream(Http2Stream http2Stream) {
        if (http2Stream == null) {
            return null;
        }
        return (NettyServerStream.TransportState) http2Stream.getProperty(this.streamKey);
    }

    private Http2Exception newStreamException(int i, Throwable th) {
        return Http2Exception.streamError(i, Http2Error.INTERNAL_ERROR, th, Strings.nullToEmpty(th.getMessage()), new Object[0]);
    }

    private static class WriteMonitoringFrameWriter extends DecoratingHttp2FrameWriter {
        private final KeepAliveEnforcer keepAliveEnforcer;

        public WriteMonitoringFrameWriter(Http2FrameWriter http2FrameWriter, KeepAliveEnforcer keepAliveEnforcer) {
            super(http2FrameWriter);
            this.keepAliveEnforcer = keepAliveEnforcer;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2DataWriter
        public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeHeaders(channelHandlerContext, i, http2Headers, i2, z, channelPromise);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
        public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise);
        }
    }

    private class FrameListener extends Http2FrameAdapter {
        private boolean firstSettings;

        private FrameListener() {
            this.firstSettings = true;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
            if (this.firstSettings) {
                this.firstSettings = false;
                NettyServerHandler nettyServerHandler = NettyServerHandler.this;
                nettyServerHandler.attributes = nettyServerHandler.transportListener.transportReady(NettyServerHandler.this.negotiationAttributes);
            }
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onDataRead(i, byteBuf, i2, z);
            return i2;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Throwable {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onHeadersRead(channelHandlerContext, i, http2Headers);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onRstStreamRead(i, j);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            if (NettyServerHandler.this.keepAliveEnforcer.pingAcceptable()) {
                return;
            }
            ByteBuf byteBufWriteAscii = ByteBufUtil.writeAscii(channelHandlerContext.alloc(), "too_many_pings");
            NettyServerHandler nettyServerHandler = NettyServerHandler.this;
            nettyServerHandler.goAway(channelHandlerContext, nettyServerHandler.connection().remote().lastStreamCreated(), Http2Error.ENHANCE_YOUR_CALM.code(), byteBufWriteAscii, channelHandlerContext.newPromise());
            try {
                NettyServerHandler.this.forcefulClose(channelHandlerContext, new ForcefulCloseCommand(Status.RESOURCE_EXHAUSTED.withDescription("Too many pings from client")), channelHandlerContext.newPromise());
            } catch (Exception e) {
                NettyServerHandler.this.onError(channelHandlerContext, true, e);
            }
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
        public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            if (j == NettyServerHandler.this.flowControlPing().payload()) {
                NettyServerHandler.this.flowControlPing().updateWindow();
                if (NettyServerHandler.logger.isLoggable(Level.FINE)) {
                    NettyServerHandler.logger.log(Level.FINE, String.format("Window: %d", Integer.valueOf(NettyServerHandler.this.decoder().flowController().initialWindowSize(NettyServerHandler.this.connection().connectionStream()))));
                    return;
                }
                return;
            }
            if (j != NettyServerHandler.GRACEFUL_SHUTDOWN_PING) {
                if (j != NettyServerHandler.KEEPALIVE_PING) {
                    NettyServerHandler.logger.warning("Received unexpected ping ack. No ping outstanding");
                }
            } else if (NettyServerHandler.this.gracefulShutdown != null) {
                NettyServerHandler.this.gracefulShutdown.secondGoAwayAndClose(channelHandlerContext);
            } else {
                NettyServerHandler.logger.warning("Received GRACEFUL_SHUTDOWN_PING Ack but gracefulShutdown is null");
            }
        }
    }

    private final class KeepAlivePinger implements KeepAliveManager.KeepAlivePinger {
        final ChannelHandlerContext ctx;

        KeepAlivePinger(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void ping() {
            Http2ConnectionEncoder http2ConnectionEncoderEncoder = NettyServerHandler.this.encoder();
            ChannelHandlerContext channelHandlerContext = this.ctx;
            ChannelFuture channelFutureWritePing = http2ConnectionEncoderEncoder.writePing(channelHandlerContext, false, NettyServerHandler.KEEPALIVE_PING, channelHandlerContext.newPromise());
            this.ctx.flush();
            if (NettyServerHandler.this.transportTracer != null) {
                channelFutureWritePing.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.KeepAlivePinger.1
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            NettyServerHandler.this.transportTracer.reportKeepAliveSent();
                        }
                    }
                });
            }
        }

        @Override // io.grpc.internal.KeepAliveManager.KeepAlivePinger
        public void onPingTimeout() {
            try {
                NettyServerHandler.this.forcefulClose(this.ctx, new ForcefulCloseCommand(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone")), this.ctx.newPromise());
            } catch (Exception e) {
                try {
                    NettyServerHandler.this.exceptionCaught(this.ctx, e);
                } catch (Exception e2) {
                    NettyServerHandler.logger.log(Level.WARNING, "Exception while propagating exception", (Throwable) e2);
                    NettyServerHandler.logger.log(Level.WARNING, "Original failure", (Throwable) e);
                }
            }
        }
    }

    private final class GracefulShutdown {
        String goAwayMessage;

        @CheckForNull
        Long graceTimeInNanos;
        boolean pingAckedOrTimeout;
        java.util.concurrent.Future<?> pingFuture;

        GracefulShutdown(String str, @Nullable Long l) {
            this.goAwayMessage = str;
            this.graceTimeInNanos = l;
        }

        void start(final ChannelHandlerContext channelHandlerContext) {
            NettyServerHandler.this.goAway(channelHandlerContext, Integer.MAX_VALUE, Http2Error.NO_ERROR.code(), ByteBufUtil.writeAscii(channelHandlerContext.alloc(), this.goAwayMessage), channelHandlerContext.newPromise());
            this.pingFuture = channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler.GracefulShutdown.1
                @Override // java.lang.Runnable
                public void run() {
                    GracefulShutdown.this.secondGoAwayAndClose(channelHandlerContext);
                }
            }, NettyServerHandler.GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS, TimeUnit.NANOSECONDS);
            NettyServerHandler.this.encoder().writePing(channelHandlerContext, false, NettyServerHandler.GRACEFUL_SHUTDOWN_PING, channelHandlerContext.newPromise());
        }

        void secondGoAwayAndClose(ChannelHandlerContext channelHandlerContext) {
            if (this.pingAckedOrTimeout) {
                return;
            }
            this.pingAckedOrTimeout = true;
            Preconditions.checkNotNull(this.pingFuture, "pingFuture");
            this.pingFuture.cancel(false);
            NettyServerHandler nettyServerHandler = NettyServerHandler.this;
            nettyServerHandler.goAway(channelHandlerContext, nettyServerHandler.connection().remote().lastStreamCreated(), Http2Error.NO_ERROR.code(), ByteBufUtil.writeAscii(channelHandlerContext.alloc(), this.goAwayMessage), channelHandlerContext.newPromise());
            long jGracefulShutdownTimeoutMillis = NettyServerHandler.this.gracefulShutdownTimeoutMillis();
            try {
                try {
                    NettyServerHandler.this.gracefulShutdownTimeoutMillis(graceTimeOverrideMillis(jGracefulShutdownTimeoutMillis));
                    NettyServerHandler.super.close(channelHandlerContext, channelHandlerContext.newPromise());
                } catch (Exception e) {
                    NettyServerHandler.this.onError(channelHandlerContext, true, e);
                }
            } finally {
                NettyServerHandler.this.gracefulShutdownTimeoutMillis(jGracefulShutdownTimeoutMillis);
            }
        }

        private long graceTimeOverrideMillis(long j) {
            Long l = this.graceTimeInNanos;
            if (l == null) {
                return j;
            }
            if (l.longValue() == Long.MAX_VALUE) {
                return -1L;
            }
            return TimeUnit.NANOSECONDS.toMillis(this.graceTimeInNanos.longValue());
        }
    }
}
