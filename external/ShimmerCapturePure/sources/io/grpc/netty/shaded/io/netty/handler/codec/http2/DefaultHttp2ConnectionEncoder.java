package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.CoalescingBufferQueue;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpStatusClass;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes3.dex */
public class DefaultHttp2ConnectionEncoder implements Http2ConnectionEncoder, Http2SettingsReceivedConsumer {
    private final Http2Connection connection;
    private final Http2FrameWriter frameWriter;
    private final Queue<Http2Settings> outstandingLocalSettingsQueue = new ArrayDeque(4);
    private Http2LifecycleManager lifecycleManager;
    private Queue<Http2Settings> outstandingRemoteSettingsQueue;

    public DefaultHttp2ConnectionEncoder(Http2Connection http2Connection, Http2FrameWriter http2FrameWriter) {
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        if (http2Connection.remote().flowController() == null) {
            http2Connection.remote().flowController(new DefaultHttp2RemoteFlowController(http2Connection));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateHeadersSentState(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, boolean z2) {
        boolean z3 = z && HttpStatusClass.valueOf(http2Headers.status()) == HttpStatusClass.INFORMATIONAL;
        if (((!z3 && z2) || !http2Stream.isHeadersSent()) && !http2Stream.isTrailersSent()) {
            return z3;
        }
        throw new IllegalStateException("Stream " + http2Stream.id() + " sent too many headers EOS: " + z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChannelFuture sendHeaders(Http2FrameWriter http2FrameWriter, ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, boolean z, int i2, short s, boolean z2, int i3, boolean z3, ChannelPromise channelPromise) {
        if (z) {
            return http2FrameWriter.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z2, i3, z3, channelPromise);
        }
        return http2FrameWriter.writeHeaders(channelHandlerContext, i, http2Headers, i3, z3, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Connection connection() {
        return this.connection;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2FrameWriter frameWriter() {
        return this.frameWriter;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void lifecycleManager(Http2LifecycleManager http2LifecycleManager) {
        this.lifecycleManager = (Http2LifecycleManager) ObjectUtil.checkNotNull(http2LifecycleManager, "lifecycleManager");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public final Http2RemoteFlowController flowController() {
        return (Http2RemoteFlowController) connection().remote().flowController();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public void remoteSettings(Http2Settings http2Settings) throws Http2Exception {
        Boolean boolPushEnabled = http2Settings.pushEnabled();
        Http2FrameWriter.Configuration configuration = configuration();
        Http2HeadersEncoder.Configuration configurationHeadersConfiguration = configuration.headersConfiguration();
        Http2FrameSizePolicy http2FrameSizePolicyFrameSizePolicy = configuration.frameSizePolicy();
        if (boolPushEnabled != null) {
            if (!this.connection.isServer() && boolPushEnabled.booleanValue()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Client received a value of ENABLE_PUSH specified to other than 0", new Object[0]);
            }
            this.connection.remote().allowPushTo(boolPushEnabled.booleanValue());
        }
        Long lMaxConcurrentStreams = http2Settings.maxConcurrentStreams();
        if (lMaxConcurrentStreams != null) {
            this.connection.local().maxActiveStreams((int) Math.min(lMaxConcurrentStreams.longValue(), 2147483647L));
        }
        if (http2Settings.headerTableSize() != null) {
            configurationHeadersConfiguration.maxHeaderTableSize((int) Math.min(r0.longValue(), 2147483647L));
        }
        Long lMaxHeaderListSize = http2Settings.maxHeaderListSize();
        if (lMaxHeaderListSize != null) {
            configurationHeadersConfiguration.maxHeaderListSize(lMaxHeaderListSize.longValue());
        }
        Integer numMaxFrameSize = http2Settings.maxFrameSize();
        if (numMaxFrameSize != null) {
            http2FrameSizePolicyFrameSizePolicy.maxFrameSize(numMaxFrameSize.intValue());
        }
        Integer numInitialWindowSize = http2Settings.initialWindowSize();
        if (numInitialWindowSize != null) {
            flowController().initialWindowSize(numInitialWindowSize.intValue());
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2DataWriter
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        try {
            Http2Stream http2StreamRequireStream = requireStream(i);
            int i3 = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[http2StreamRequireStream.state().ordinal()];
            if (i3 != 1 && i3 != 2) {
                throw new IllegalStateException("Stream " + http2StreamRequireStream.id() + " in unexpected state " + http2StreamRequireStream.state());
            }
            flowController().addFlowControlled(http2StreamRequireStream, new FlowControlledData(http2StreamRequireStream, byteBuf, i2, z, channelPromise));
            return channelPromise;
        } catch (Throwable th) {
            byteBuf.release();
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        return writeHeaders0(channelHandlerContext, i, http2Headers, false, 0, (short) 0, false, i2, z, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        return writeHeaders0(channelHandlerContext, i, http2Headers, true, i2, s, z, i3, z2, channelPromise);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00df A[Catch: all -> 0x0106, TRY_LEAVE, TryCatch #1 {all -> 0x0106, blocks: (B:24:0x0087, B:27:0x008e, B:29:0x00af, B:31:0x00df), top: B:50:0x0087 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f3 A[Catch: all -> 0x0104, TryCatch #0 {all -> 0x0104, blocks: (B:34:0x00eb, B:38:0x00fe, B:36:0x00f3), top: B:48:0x00dd }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fe A[Catch: all -> 0x0104, TRY_LEAVE, TryCatch #0 {all -> 0x0104, blocks: (B:34:0x00eb, B:38:0x00fe, B:36:0x00f3), top: B:48:0x00dd }] */
    /* JADX WARN: Type inference failed for: r1v1, types: [io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LifecycleManager] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.grpc.netty.shaded.io.netty.channel.ChannelFuture writeHeaders0(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r18, int r19, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers r20, boolean r21, int r22, short r23, boolean r24, int r25, boolean r26, io.grpc.netty.shaded.io.netty.channel.ChannelPromise r27) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder.writeHeaders0(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, int, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers, boolean, int, short, boolean, int, boolean, io.grpc.netty.shaded.io.netty.channel.ChannelPromise):io.grpc.netty.shaded.io.netty.channel.ChannelFuture");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePriority(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z, ChannelPromise channelPromise) {
        return this.frameWriter.writePriority(channelHandlerContext, i, i2, s, z, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeRstStream(ChannelHandlerContext channelHandlerContext, int i, long j, ChannelPromise channelPromise) {
        return this.lifecycleManager.resetStream(channelHandlerContext, i, j, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettings(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings, ChannelPromise channelPromise) {
        this.outstandingLocalSettingsQueue.add(http2Settings);
        try {
            if (http2Settings.pushEnabled() != null && this.connection.isServer()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server sending SETTINGS frame with ENABLE_PUSH specified", new Object[0]);
            }
            return this.frameWriter.writeSettings(channelHandlerContext, http2Settings, channelPromise);
        } catch (Throwable th) {
            return channelPromise.setFailure(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeSettingsAck(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        Queue<Http2Settings> queue = this.outstandingRemoteSettingsQueue;
        if (queue == null) {
            return this.frameWriter.writeSettingsAck(channelHandlerContext, channelPromise);
        }
        Http2Settings http2SettingsPoll = queue.poll();
        if (http2SettingsPoll == null) {
            return channelPromise.setFailure((Throwable) new Http2Exception(Http2Error.INTERNAL_ERROR, "attempted to write a SETTINGS ACK with no  pending SETTINGS"));
        }
        Http2CodecUtil.SimpleChannelPromiseAggregator simpleChannelPromiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(channelPromise, channelHandlerContext.channel(), channelHandlerContext.executor());
        this.frameWriter.writeSettingsAck(channelHandlerContext, simpleChannelPromiseAggregator.newPromise());
        ChannelPromise channelPromiseNewPromise = simpleChannelPromiseAggregator.newPromise();
        try {
            remoteSettings(http2SettingsPoll);
            channelPromiseNewPromise.setSuccess();
        } catch (Throwable th) {
            channelPromiseNewPromise.setFailure(th);
            this.lifecycleManager.onError(channelHandlerContext, true, th);
        }
        return simpleChannelPromiseAggregator.doneAllocatingPromises();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePing(ChannelHandlerContext channelHandlerContext, boolean z, long j, ChannelPromise channelPromise) {
        return this.frameWriter.writePing(channelHandlerContext, z, j, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writePushPromise(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3, ChannelPromise channelPromise) {
        ChannelPromise channelPromiseUnvoid;
        try {
            if (this.connection.goAwayReceived()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Sending PUSH_PROMISE after GO_AWAY received.", new Object[0]);
            }
            Http2Stream http2StreamRequireStream = requireStream(i);
            this.connection.local().reservePushStream(i2, http2StreamRequireStream);
            channelPromiseUnvoid = channelPromise.unvoid();
            try {
                ChannelFuture channelFutureWritePushPromise = this.frameWriter.writePushPromise(channelHandlerContext, i, i2, http2Headers, i3, channelPromiseUnvoid);
                Throwable thCause = channelFutureWritePushPromise.cause();
                if (thCause == null) {
                    http2StreamRequireStream.pushPromiseSent();
                    if (!channelFutureWritePushPromise.isSuccess()) {
                        notifyLifecycleManagerOnError(channelFutureWritePushPromise, channelHandlerContext);
                    }
                } else {
                    this.lifecycleManager.onError(channelHandlerContext, true, thCause);
                }
                return channelFutureWritePushPromise;
            } catch (Throwable th) {
                th = th;
                this.lifecycleManager.onError(channelHandlerContext, true, th);
                channelPromiseUnvoid.tryFailure(th);
                return channelPromiseUnvoid;
            }
        } catch (Throwable th2) {
            th = th2;
            channelPromiseUnvoid = channelPromise;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeGoAway(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.lifecycleManager.goAway(channelHandlerContext, i, j, byteBuf, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeWindowUpdate(ChannelHandlerContext channelHandlerContext, int i, int i2, ChannelPromise channelPromise) {
        return channelPromise.setFailure((Throwable) new UnsupportedOperationException("Use the Http2[Inbound|Outbound]FlowController objects to control window sizes"));
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf, ChannelPromise channelPromise) {
        return this.frameWriter.writeFrame(channelHandlerContext, b, i, http2Flags, byteBuf, channelPromise);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.frameWriter.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder
    public Http2Settings pollSentSettings() {
        return this.outstandingLocalSettingsQueue.poll();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter
    public Http2FrameWriter.Configuration configuration() {
        return this.frameWriter.configuration();
    }

    private Http2Stream requireStream(int i) {
        String str;
        Http2Stream http2StreamStream = this.connection.stream(i);
        if (http2StreamStream != null) {
            return http2StreamStream;
        }
        if (this.connection.streamMayHaveExisted(i)) {
            str = "Stream no longer exists: " + i;
        } else {
            str = "Stream does not exist: " + i;
        }
        throw new IllegalArgumentException(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SettingsReceivedConsumer
    public void consumeReceivedSettings(Http2Settings http2Settings) {
        if (this.outstandingRemoteSettingsQueue == null) {
            this.outstandingRemoteSettingsQueue = new ArrayDeque(2);
        }
        this.outstandingRemoteSettingsQueue.add(http2Settings);
    }

    private void notifyLifecycleManagerOnError(ChannelFuture channelFuture, final ChannelHandlerContext channelHandlerContext) {
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                Throwable thCause = channelFuture2.cause();
                if (thCause != null) {
                    DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, thCause);
                }
            }
        });
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        static {
            int[] iArr = new int[Http2Stream.State.values().length];
            $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = iArr;
            try {
                iArr[Http2Stream.State.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.HALF_CLOSED_REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[Http2Stream.State.RESERVED_LOCAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private final class FlowControlledData extends FlowControlledBase {
        private final CoalescingBufferQueue queue;
        private int dataSize;

        FlowControlledData(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z, ChannelPromise channelPromise) {
            super(http2Stream, i, z, channelPromise);
            CoalescingBufferQueue coalescingBufferQueue = new CoalescingBufferQueue(channelPromise.channel());
            this.queue = coalescingBufferQueue;
            coalescingBufferQueue.add(byteBuf, channelPromise);
            this.dataSize = coalescingBufferQueue.readableBytes();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return this.dataSize + this.padding;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            this.queue.releaseAndFailAll(th);
            DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r12v11, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        /* JADX WARN: Type inference failed for: r9v0, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            int i2 = this.queue.readableBytes();
            if (!this.endOfStream) {
                if (i2 == 0) {
                    if (this.queue.isEmpty()) {
                        this.dataSize = 0;
                        this.padding = 0;
                        return;
                    } else {
                        ??
                        AddListener = channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
                        channelHandlerContext.write(this.queue.remove(0, AddListener), AddListener);
                        return;
                    }
                }
                if (i == 0) {
                    return;
                }
            }
            int iMin = Math.min(i2, i);
            ??
            AddListener2 = channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
            ByteBuf byteBufRemove = this.queue.remove(iMin, AddListener2);
            this.dataSize = this.queue.readableBytes();
            int iMin2 = Math.min(i - iMin, this.padding);
            this.padding -= iMin2;
            DefaultHttp2ConnectionEncoder.this.frameWriter().writeData(channelHandlerContext, this.stream.id(), byteBufRemove, iMin2, this.endOfStream && size() == 0, AddListener2);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public boolean merge(ChannelHandlerContext channelHandlerContext, Http2RemoteFlowController.FlowControlled flowControlled) {
            if (FlowControlledData.class != flowControlled.getClass()) {
                return false;
            }
            FlowControlledData flowControlledData = (FlowControlledData) flowControlled;
            if (Integer.MAX_VALUE - flowControlledData.size() < size()) {
                return false;
            }
            flowControlledData.queue.copyTo(this.queue);
            this.dataSize = this.queue.readableBytes();
            this.padding = Math.max(this.padding, flowControlledData.padding);
            this.endOfStream = flowControlledData.endOfStream;
            return true;
        }
    }

    private final class FlowControlledHeaders extends FlowControlledBase {
        private final boolean exclusive;
        private final boolean hasPriorty;
        private final Http2Headers headers;
        private final int streamDependency;
        private final short weight;

        FlowControlledHeaders(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, int i, short s, boolean z2, int i2, boolean z3, ChannelPromise channelPromise) {
            super(http2Stream, i2, z3, channelPromise.unvoid());
            this.headers = http2Headers;
            this.hasPriorty = z;
            this.streamDependency = i;
            this.weight = s;
            this.exclusive = z2;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public boolean merge(ChannelHandlerContext channelHandlerContext, Http2RemoteFlowController.FlowControlled flowControlled) {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public int size() {
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void error(ChannelHandlerContext channelHandlerContext, Throwable th) {
            if (channelHandlerContext != null) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.onError(channelHandlerContext, true, th);
            }
            this.promise.tryFailure(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void write(ChannelHandlerContext channelHandlerContext, int i) {
            boolean zValidateHeadersSentState = DefaultHttp2ConnectionEncoder.validateHeadersSentState(this.stream, this.headers, DefaultHttp2ConnectionEncoder.this.connection.isServer(), this.endOfStream);
            this.promise.addListener((GenericFutureListener<? extends Future<? super Void>>) this);
            if (DefaultHttp2ConnectionEncoder.sendHeaders(DefaultHttp2ConnectionEncoder.this.frameWriter, channelHandlerContext, this.stream.id(), this.headers, this.hasPriorty, this.streamDependency, this.weight, this.exclusive, this.padding, this.endOfStream, this.promise).cause() == null) {
                this.stream.headersSent(zValidateHeadersSentState);
            }
        }
    }

    public abstract class FlowControlledBase implements Http2RemoteFlowController.FlowControlled, ChannelFutureListener {
        protected final Http2Stream stream;
        protected boolean endOfStream;
        protected int padding;
        protected ChannelPromise promise;

        FlowControlledBase(Http2Stream http2Stream, int i, boolean z, ChannelPromise channelPromise) {
            ObjectUtil.checkPositiveOrZero(i, "padding");
            this.padding = i;
            this.endOfStream = z;
            this.stream = http2Stream;
            this.promise = channelPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController.FlowControlled
        public void writeComplete() {
            if (this.endOfStream) {
                DefaultHttp2ConnectionEncoder.this.lifecycleManager.closeStreamLocal(this.stream, this.promise);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            error(DefaultHttp2ConnectionEncoder.this.flowController().channelHandlerContext(), channelFuture.cause());
        }
    }
}
