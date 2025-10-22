package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.verisense.communication.VerisenseMessage;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromiseNotifier;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedMessageTypeException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ImmediateExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.concurrent.PromiseNotifier;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

/* loaded from: classes3.dex */
public class SslHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_PLAINTEXT_LENGTH = 16384;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslHandler.class);
    private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);
    private final Executor delegatedTaskExecutor;
    private final SSLEngine engine;
    private final SslEngineType engineType;
    private final boolean jdkCompatibilityMode;
    private final ByteBuffer[] singleBuffer;
    private final LazyChannelPromise sslClosePromise;
    private final boolean startTls;
    volatile int wrapDataSize;
    private boolean closeNotify;
    private volatile long closeNotifyFlushTimeoutMillis;
    private volatile long closeNotifyReadTimeoutMillis;
    private volatile ChannelHandlerContext ctx;
    private boolean firedChannelRead;
    private boolean flushedBeforeHandshake;
    private Promise<Channel> handshakePromise;
    private boolean handshakeStarted;
    private volatile long handshakeTimeoutMillis;
    private boolean needsFlush;
    private boolean outboundClosed;
    private int packetLength;
    private SslHandlerCoalescingBufferQueue pendingUnencryptedWrites;
    private boolean processTask;
    private boolean readDuringHandshake;
    private boolean sentFirstMessage;

    public SslHandler(SSLEngine sSLEngine) {
        this(sSLEngine, false);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z) {
        this(sSLEngine, z, ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, Executor executor) {
        this(sSLEngine, false, executor);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z, Executor executor) {
        this.singleBuffer = new ByteBuffer[1];
        this.handshakePromise = new LazyChannelPromise();
        this.sslClosePromise = new LazyChannelPromise();
        this.handshakeTimeoutMillis = VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER;
        this.closeNotifyFlushTimeoutMillis = 3000L;
        this.wrapDataSize = 16384;
        this.engine = (SSLEngine) ObjectUtil.checkNotNull(sSLEngine, "engine");
        this.delegatedTaskExecutor = (Executor) ObjectUtil.checkNotNull(executor, "delegatedTaskExecutor");
        SslEngineType sslEngineTypeForEngine = SslEngineType.forEngine(sSLEngine);
        this.engineType = sslEngineTypeForEngine;
        this.startTls = z;
        this.jdkCompatibilityMode = sslEngineTypeForEngine.jdkCompatibilityMode(sSLEngine);
        setCumulator(sslEngineTypeForEngine.cumulator);
    }

    private static IllegalStateException newPendingWritesNullException() {
        return new IllegalStateException("pendingUnencryptedWrites is null, handlerRemoved0 called?");
    }

    public static boolean isEncrypted(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() >= 5) {
            return SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex()) != -2;
        }
        throw new IllegalArgumentException("buffer must have at least 5 readable bytes");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuffer toByteBuffer(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.nioBufferCount() == 1 ? byteBuf.internalNioBuffer(i, i2) : byteBuf.nioBuffer(i, i2);
    }

    private static boolean inEventLoop(Executor executor) {
        return (executor instanceof EventExecutor) && ((EventExecutor) executor).inEventLoop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void runAllDelegatedTasks(SSLEngine sSLEngine) {
        while (true) {
            Runnable delegatedTask = sSLEngine.getDelegatedTask();
            if (delegatedTask == null) {
                return;
            } else {
                delegatedTask.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addCloseListener(ChannelFuture channelFuture, ChannelPromise channelPromise) {
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean attemptCopyToCumulation(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        int i2 = byteBuf2.readableBytes();
        int iCapacity = byteBuf.capacity();
        if (i - byteBuf.readableBytes() < i2 || ((!byteBuf.isWritable(i2) || iCapacity < i) && (iCapacity >= i || !ByteBufUtil.ensureWritableSuccess(byteBuf.ensureWritable(i2, false))))) {
            return false;
        }
        byteBuf.writeBytes(byteBuf2);
        byteBuf2.release();
        return true;
    }

    public SSLEngine engine() {
        return this.engine;
    }

    public final long getCloseNotifyFlushTimeoutMillis() {
        return this.closeNotifyFlushTimeoutMillis;
    }

    public final void setCloseNotifyFlushTimeoutMillis(long j) {
        if (j >= 0) {
            this.closeNotifyFlushTimeoutMillis = j;
            return;
        }
        throw new IllegalArgumentException("closeNotifyFlushTimeoutMillis: " + j + " (expected: >= 0)");
    }

    public final long getCloseNotifyReadTimeoutMillis() {
        return this.closeNotifyReadTimeoutMillis;
    }

    public final void setCloseNotifyReadTimeoutMillis(long j) {
        if (j >= 0) {
            this.closeNotifyReadTimeoutMillis = j;
            return;
        }
        throw new IllegalArgumentException("closeNotifyReadTimeoutMillis: " + j + " (expected: >= 0)");
    }

    public long getHandshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public void setHandshakeTimeoutMillis(long j) {
        if (j >= 0) {
            this.handshakeTimeoutMillis = j;
            return;
        }
        throw new IllegalArgumentException("handshakeTimeoutMillis: " + j + " (expected: >= 0)");
    }

    public Future<Channel> handshakeFuture() {
        return this.handshakePromise;
    }

    public final void setWrapDataSize(int i) {
        this.wrapDataSize = i;
    }

    public Future<Channel> sslCloseFuture() {
        return this.sslClosePromise;
    }

    public void setHandshakeTimeout(long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        setHandshakeTimeoutMillis(timeUnit.toMillis(j));
    }

    @Deprecated
    public long getCloseNotifyTimeoutMillis() {
        return getCloseNotifyFlushTimeoutMillis();
    }

    @Deprecated
    public void setCloseNotifyTimeoutMillis(long j) {
        setCloseNotifyFlushTimeoutMillis(j);
    }

    @Deprecated
    public void setCloseNotifyTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeout(j, timeUnit);
    }

    public final void setCloseNotifyFlushTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeoutMillis(timeUnit.toMillis(j));
    }

    public final void setCloseNotifyReadTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyReadTimeoutMillis(timeUnit.toMillis(j));
    }

    public String applicationProtocol() {
        Object objEngine = engine();
        if (objEngine instanceof ApplicationProtocolAccessor) {
            return ((ApplicationProtocolAccessor) objEngine).getNegotiatedApplicationProtocol();
        }
        return null;
    }

    @Deprecated
    public ChannelFuture close() {
        return closeOutbound();
    }

    @Deprecated
    public ChannelFuture close(ChannelPromise channelPromise) {
        return closeOutbound(channelPromise);
    }

    public ChannelFuture closeOutbound() {
        return closeOutbound(this.ctx.newPromise());
    }

    public ChannelFuture closeOutbound(final ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext.executor().inEventLoop()) {
            closeOutbound0(channelPromise);
        } else {
            channelHandlerContext.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    SslHandler.this.closeOutbound0(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeOutbound0(ChannelPromise channelPromise) {
        this.outboundClosed = true;
        this.engine.closeOutbound();
        try {
            flush(this.ctx, channelPromise);
        } catch (Exception e) {
            if (channelPromise.tryFailure(e)) {
                return;
            }
            logger.warn("{} flush() raised a masked exception.", this.ctx.channel(), e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.releaseAndFailAll(channelHandlerContext, new ChannelException("Pending write on removal of SslHandler"));
        }
        SSLHandshakeException sSLHandshakeException = null;
        this.pendingUnencryptedWrites = null;
        if (!this.handshakePromise.isDone()) {
            sSLHandshakeException = new SSLHandshakeException("SslHandler removed before handshake completed");
            if (this.handshakePromise.tryFailure(sSLHandshakeException)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(sSLHandshakeException));
            }
        }
        if (!this.sslClosePromise.isDone()) {
            if (sSLHandshakeException == null) {
                sSLHandshakeException = new SSLHandshakeException("SslHandler removed before handshake completed");
            }
            notifyClosePromise(sSLHandshakeException);
        }
        Object obj = this.engine;
        if (obj instanceof ReferenceCounted) {
            ((ReferenceCounted) obj).release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, true);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.handshakePromise.isDone()) {
            this.readDuringHandshake = true;
        }
        channelHandlerContext.read();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (!(obj instanceof ByteBuf)) {
            UnsupportedMessageTypeException unsupportedMessageTypeException = new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[]{ByteBuf.class});
            ReferenceCountUtil.safeRelease(obj);
            channelPromise.setFailure((Throwable) unsupportedMessageTypeException);
        } else {
            SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
            if (sslHandlerCoalescingBufferQueue == null) {
                ReferenceCountUtil.safeRelease(obj);
                channelPromise.setFailure((Throwable) newPendingWritesNullException());
            } else {
                sslHandlerCoalescingBufferQueue.add((ByteBuf) obj, channelPromise);
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.startTls && !this.sentFirstMessage) {
            this.sentFirstMessage = true;
            this.pendingUnencryptedWrites.writeAndRemoveAll(channelHandlerContext);
            forceFlush(channelHandlerContext);
            startHandshakeProcessing();
            return;
        }
        if (this.processTask) {
            return;
        }
        try {
            wrapAndFlush(channelHandlerContext);
        } catch (Throwable th) {
            setHandshakeFailure(channelHandlerContext, th);
            PlatformDependent.throwException(th);
        }
    }

    private void wrapAndFlush(ChannelHandlerContext channelHandlerContext) throws SSLException {
        if (this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, channelHandlerContext.newPromise());
        }
        if (!this.handshakePromise.isDone()) {
            this.flushedBeforeHandshake = true;
        }
        try {
            wrap(channelHandlerContext, false);
        } finally {
            forceFlush(channelHandlerContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004e, code lost:

        r3.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:

        r0 = r11.handshakePromise.cause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:

        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0059, code lost:

        r0 = r11.sslClosePromise.cause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005f, code lost:

        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0061, code lost:

        r0 = new javax.net.ssl.SSLException("SSLEngine closed already");
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0068, code lost:

        r2.tryFailure(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006c, code lost:

        r11.pendingUnencryptedWrites.releaseAndFailAll(r12, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0071, code lost:

        r1 = r11;
        r2 = r12;
        r3 = r4;
        r4 = null;
        r5 = r13;
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007b, code lost:

        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0104, code lost:

        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0108, code lost:

        if (r8 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x010a, code lost:

        r8.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x010d, code lost:

        finishWrap(r12, r3, r4, r13, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0114, code lost:

        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0117, code lost:

        r4 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x004e A[EDGE_INSN: B:106:0x004e->B:21:0x004e BREAK  A[LOOP:0: B:91:0x000a->B:111:0x000a], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007e A[Catch: all -> 0x0102, TRY_ENTER, TryCatch #0 {all -> 0x0102, blocks: (B:19:0x0040, B:21:0x004e, B:35:0x007e, B:37:0x0084, B:38:0x008b), top: B:89:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void wrap(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r12, boolean r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.wrap(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, boolean):void");
    }

    private void finishWrap(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ChannelPromise channelPromise, boolean z, boolean z2) {
        if (byteBuf == null) {
            byteBuf = Unpooled.EMPTY_BUFFER;
        } else if (!byteBuf.isReadable()) {
            byteBuf.release();
            byteBuf = Unpooled.EMPTY_BUFFER;
        }
        if (channelPromise != null) {
            channelHandlerContext.write(byteBuf, channelPromise);
        } else {
            channelHandlerContext.write(byteBuf);
        }
        if (z) {
            this.needsFlush = true;
        }
        if (z2) {
            readIfNeeded(channelHandlerContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean wrapNonAppData(final ChannelHandlerContext channelHandlerContext, boolean z) throws SSLException {
        ByteBufAllocator byteBufAllocatorAlloc = channelHandlerContext.alloc();
        ByteBuf byteBufAllocateOutNetBuf = null;
        while (!channelHandlerContext.isRemoved()) {
            try {
                if (byteBufAllocateOutNetBuf == null) {
                    byteBufAllocateOutNetBuf = allocateOutNetBuf(channelHandlerContext, 2048, 1);
                }
                SSLEngineResult sSLEngineResultWrap = wrap(byteBufAllocatorAlloc, this.engine, Unpooled.EMPTY_BUFFER, byteBufAllocateOutNetBuf);
                if (sSLEngineResultWrap.bytesProduced() > 0) {
                    channelHandlerContext.write(byteBufAllocateOutNetBuf).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.2
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(ChannelFuture channelFuture) {
                            Throwable thCause = channelFuture.cause();
                            if (thCause != null) {
                                SslHandler.this.setHandshakeFailureTransportFailure(channelHandlerContext, thCause);
                            }
                        }
                    });
                    if (z) {
                        this.needsFlush = true;
                    }
                    byteBufAllocateOutNetBuf = null;
                }
                SSLEngineResult.HandshakeStatus handshakeStatus = sSLEngineResultWrap.getHandshakeStatus();
                int i = AnonymousClass9.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        setHandshakeSuccess();
                        if (byteBufAllocateOutNetBuf != null) {
                            byteBufAllocateOutNetBuf.release();
                        }
                        return false;
                    }
                    if (i == 3) {
                        setHandshakeSuccessIfStillHandshaking();
                        if (!z) {
                            unwrapNonAppData(channelHandlerContext);
                        }
                        if (byteBufAllocateOutNetBuf != null) {
                            byteBufAllocateOutNetBuf.release();
                        }
                        return true;
                    }
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalStateException("Unknown handshake status: " + sSLEngineResultWrap.getHandshakeStatus());
                        }
                        if (z) {
                            return false;
                        }
                        unwrapNonAppData(channelHandlerContext);
                    }
                } else if (!runDelegatedTasks(z)) {
                    break;
                }
                if ((sSLEngineResultWrap.bytesProduced() == 0 && handshakeStatus != SSLEngineResult.HandshakeStatus.NEED_TASK) || (sSLEngineResultWrap.bytesConsumed() == 0 && sSLEngineResultWrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING)) {
                    break;
                }
            } finally {
                if (byteBufAllocateOutNetBuf != null) {
                    byteBufAllocateOutNetBuf.release();
                }
            }
        }
        if (byteBufAllocateOutNetBuf != null) {
            byteBufAllocateOutNetBuf.release();
        }
        return false;
    }

    private SSLEngineResult wrap(ByteBufAllocator byteBufAllocator, SSLEngine sSLEngine, ByteBuf byteBuf, ByteBuf byteBuf2) throws Throwable {
        ByteBuf byteBufDirectBuffer;
        ByteBuffer[] byteBufferArrNioBuffers;
        SSLEngineResult sSLEngineResultWrap;
        try {
            int i = byteBuf.readerIndex();
            int i2 = byteBuf.readableBytes();
            if (byteBuf.isDirect() || !this.engineType.wantsDirectBuffer) {
                if (!(byteBuf instanceof CompositeByteBuf) && byteBuf.nioBufferCount() == 1) {
                    ByteBuffer[] byteBufferArr = this.singleBuffer;
                    byteBufferArr[0] = byteBuf.internalNioBuffer(i, i2);
                    byteBufferArrNioBuffers = byteBufferArr;
                } else {
                    byteBufferArrNioBuffers = byteBuf.nioBuffers();
                }
                byteBufDirectBuffer = null;
            } else {
                byteBufDirectBuffer = byteBufAllocator.directBuffer(i2);
                try {
                    byteBufDirectBuffer.writeBytes(byteBuf, i, i2);
                    byteBufferArrNioBuffers = this.singleBuffer;
                    byteBufferArrNioBuffers[0] = byteBufDirectBuffer.internalNioBuffer(byteBufDirectBuffer.readerIndex(), i2);
                } catch (Throwable th) {
                    th = th;
                    this.singleBuffer[0] = null;
                    if (byteBufDirectBuffer != null) {
                        byteBufDirectBuffer.release();
                    }
                    throw th;
                }
            }
            while (true) {
                sSLEngineResultWrap = sSLEngine.wrap(byteBufferArrNioBuffers, byteBuf2.nioBuffer(byteBuf2.writerIndex(), byteBuf2.writableBytes()));
                byteBuf.skipBytes(sSLEngineResultWrap.bytesConsumed());
                byteBuf2.writerIndex(byteBuf2.writerIndex() + sSLEngineResultWrap.bytesProduced());
                if (AnonymousClass9.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[sSLEngineResultWrap.getStatus().ordinal()] != 1) {
                    break;
                }
                byteBuf2.ensureWritable(sSLEngine.getSession().getPacketBufferSize());
            }
            this.singleBuffer[0] = null;
            if (byteBufDirectBuffer != null) {
                byteBufDirectBuffer.release();
            }
            return sSLEngineResultWrap;
        } catch (Throwable th2) {
            th = th2;
            byteBufDirectBuffer = null;
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        boolean z = this.handshakePromise.cause() != null;
        ClosedChannelException closedChannelException = new ClosedChannelException();
        setHandshakeFailure(channelHandlerContext, closedChannelException, !this.outboundClosed, this.handshakeStarted, false);
        notifyClosePromise(closedChannelException);
        try {
            super.channelInactive(channelHandlerContext);
        } catch (DecoderException e) {
            if (!z || !(e.getCause() instanceof SSLException)) {
                throw e;
            }
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (ignoreException(th)) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isDebugEnabled()) {
                internalLogger.debug("{} Swallowing a harmless 'connection reset by peer / broken pipe' error that occurred while writing close_notify in response to the peer's close_notify", channelHandlerContext.channel(), th);
            }
            if (channelHandlerContext.channel().isActive()) {
                channelHandlerContext.close();
                return;
            }
            return;
        }
        channelHandlerContext.fireExceptionCaught(th);
    }

    private boolean ignoreException(Throwable th) {
        if (!(th instanceof SSLException) && (th instanceof IOException) && this.sslClosePromise.isDone()) {
            String message = th.getMessage();
            if (message != null && IGNORABLE_ERROR_MESSAGE.matcher(message).matches()) {
                return true;
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                if (!className.startsWith("io.grpc.netty.shaded.io.netty.") && "read".equals(methodName)) {
                    if (IGNORABLE_CLASS_IN_STACK.matcher(className).matches()) {
                        return true;
                    }
                    try {
                        Class<?> clsLoadClass = PlatformDependent.getClassLoader(getClass()).loadClass(className);
                        if (!SocketChannel.class.isAssignableFrom(clsLoadClass) && !DatagramChannel.class.isAssignableFrom(clsLoadClass) && (PlatformDependent.javaVersion() < 7 || !"com.sun.nio.sctp.SctpChannel".equals(clsLoadClass.getSuperclass().getName()))) {
                        }
                        return true;
                    } catch (Throwable th2) {
                        InternalLogger internalLogger = logger;
                        if (internalLogger.isDebugEnabled()) {
                            internalLogger.debug("Unexpected exception while loading class {} classname {}", getClass(), className, th2);
                        }
                    }
                }
            }
        }
        return false;
    }

    private void decodeJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Throwable {
        int i = this.packetLength;
        if (i > 0) {
            if (byteBuf.readableBytes() < i) {
                return;
            }
        } else {
            int i2 = byteBuf.readableBytes();
            if (i2 < 5) {
                return;
            }
            int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex());
            if (encryptedPacketLength == -2) {
                NotSslRecordException notSslRecordException = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(byteBuf));
                byteBuf.skipBytes(byteBuf.readableBytes());
                setHandshakeFailure(channelHandlerContext, notSslRecordException);
                throw notSslRecordException;
            }
            if (encryptedPacketLength > i2) {
                this.packetLength = encryptedPacketLength;
                return;
            }
            i = encryptedPacketLength;
        }
        this.packetLength = 0;
        try {
            byteBuf.skipBytes(unwrap(channelHandlerContext, byteBuf, byteBuf.readerIndex(), i));
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    private void decodeNonJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Throwable {
        try {
            byteBuf.skipBytes(unwrap(channelHandlerContext, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes()));
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUnwrapThrowable(ChannelHandlerContext channelHandlerContext, Throwable th) throws Throwable {
        try {
            if (this.handshakePromise.tryFailure(th)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(th));
            }
            wrapAndFlush(channelHandlerContext);
        } catch (SSLException e) {
            logger.debug("SSLException during trying to call SSLEngine.wrap(...) because of an previous SSLException, ignoring...", (Throwable) e);
        } finally {
            setHandshakeFailure(channelHandlerContext, th, true, false, true);
        }
        PlatformDependent.throwException(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Throwable {
        if (this.processTask) {
            return;
        }
        if (this.jdkCompatibilityMode) {
            decodeJdkCompatible(channelHandlerContext, byteBuf);
        } else {
            decodeNonJdkCompatible(channelHandlerContext, byteBuf);
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelReadComplete0(channelHandlerContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void channelReadComplete0(ChannelHandlerContext channelHandlerContext) {
        discardSomeReadBytes();
        flushIfNeeded(channelHandlerContext);
        readIfNeeded(channelHandlerContext);
        this.firedChannelRead = false;
        channelHandlerContext.fireChannelReadComplete();
    }

    private void readIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.channel().config().isAutoRead()) {
            return;
        }
        if (this.firedChannelRead && this.handshakePromise.isDone()) {
            return;
        }
        channelHandlerContext.read();
    }

    private void flushIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (this.needsFlush) {
            forceFlush(channelHandlerContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unwrapNonAppData(ChannelHandlerContext channelHandlerContext) throws Throwable {
        unwrap(channelHandlerContext, Unpooled.EMPTY_BUFFER, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b0, code lost:

        if (r3 != javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b2, code lost:

        readIfNeeded(r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0126, code lost:

        if (r17.flushedBeforeHandshake == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012e, code lost:

        if (r17.handshakePromise.isDone() == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0130, code lost:

        r17.flushedBeforeHandshake = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0133, code lost:

        if (r15 == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0135, code lost:

        wrap(r18, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0138, code lost:

        if (r16 == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x013a, code lost:

        notifyClosePromise(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013e, code lost:

        if (r12 == null) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0144, code lost:

        if (r12.isReadable() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0146, code lost:

        r17.firedChannelRead = r10;
        r18.fireChannelRead((java.lang.Object) r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014c, code lost:

        r12.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0150, code lost:

        return r21 - r13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int unwrap(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r18, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r19, int r20, int r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.unwrap(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, int, int):int");
    }

    private boolean runDelegatedTasks(boolean z) {
        if (this.delegatedTaskExecutor == ImmediateExecutor.INSTANCE || inEventLoop(this.delegatedTaskExecutor)) {
            runAllDelegatedTasks(this.engine);
            return true;
        }
        executeDelegatedTasks(z);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDelegatedTasks(boolean z) {
        this.processTask = true;
        try {
            this.delegatedTaskExecutor.execute(new SslTasksRunner(z));
        } catch (RejectedExecutionException e) {
            this.processTask = false;
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setHandshakeSuccessIfStillHandshaking() {
        if (this.handshakePromise.isDone()) {
            return false;
        }
        setHandshakeSuccess();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeSuccess() {
        this.handshakePromise.trySuccess(this.ctx.channel());
        InternalLogger internalLogger = logger;
        if (internalLogger.isDebugEnabled()) {
            SSLSession session = this.engine.getSession();
            internalLogger.debug("{} HANDSHAKEN: protocol:{} cipher suite:{}", this.ctx.channel(), session.getProtocol(), session.getCipherSuite());
        }
        this.ctx.fireUserEventTriggered((Object) SslHandshakeCompletionEvent.SUCCESS);
        if (!this.readDuringHandshake || this.ctx.channel().config().isAutoRead()) {
            return;
        }
        this.readDuringHandshake = false;
        this.ctx.read();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        setHandshakeFailure(channelHandlerContext, th, true, true, false);
    }

    private void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th, boolean z, boolean z2, boolean z3) {
        String message;
        try {
            this.outboundClosed = true;
            this.engine.closeOutbound();
            if (z) {
                try {
                    this.engine.closeInbound();
                } catch (SSLException e) {
                    InternalLogger internalLogger = logger;
                    if (internalLogger.isDebugEnabled() && ((message = e.getMessage()) == null || (!message.contains("possible truncation attack") && !message.contains("closing inbound before receiving peer's close_notify")))) {
                        internalLogger.debug("{} SSLEngine.closeInbound() raised an exception.", channelHandlerContext.channel(), e);
                    }
                }
            }
            if (this.handshakePromise.tryFailure(th) || z3) {
                SslUtils.handleHandshakeFailure(channelHandlerContext, th, z2);
            }
        } finally {
            releaseAndFailAll(channelHandlerContext, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeFailureTransportFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        try {
            SSLException sSLException = new SSLException("failure when writing TLS control frames", th);
            releaseAndFailAll(channelHandlerContext, sSLException);
            if (this.handshakePromise.tryFailure(sSLException)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(sSLException));
            }
        } finally {
            channelHandlerContext.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAndFailAll(ChannelHandlerContext channelHandlerContext, Throwable th) {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.releaseAndFailAll(channelHandlerContext, th);
        }
    }

    private void notifyClosePromise(Throwable th) {
        if (th == null) {
            if (this.sslClosePromise.trySuccess(this.ctx.channel())) {
                this.ctx.fireUserEventTriggered((Object) SslCloseCompletionEvent.SUCCESS);
            }
        } else if (this.sslClosePromise.tryFailure(th)) {
            this.ctx.fireUserEventTriggered((Object) new SslCloseCompletionEvent(th));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
    /* JADX WARN: Type inference failed for: r7v2, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
    private void closeOutboundAndChannel(ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise, boolean z) throws Exception {
        this.outboundClosed = true;
        this.engine.closeOutbound();
        if (!channelHandlerContext.channel().isActive()) {
            if (z) {
                channelHandlerContext.disconnect(channelPromise);
                return;
            } else {
                channelHandlerContext.close(channelPromise);
                return;
            }
        }
        ChannelPromise channelPromiseNewPromise = channelHandlerContext.newPromise();
        try {
            flush(channelHandlerContext, channelPromiseNewPromise);
            if (this.closeNotify) {
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) {
                        channelPromise.setSuccess();
                    }
                });
            } else {
                this.closeNotify = true;
                safeClose(channelHandlerContext, channelPromiseNewPromise, channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise)));
            }
        } catch (Throwable th) {
            if (this.closeNotify) {
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) {
                        channelPromise.setSuccess();
                    }
                });
            } else {
                this.closeNotify = true;
                safeClose(channelHandlerContext, channelPromiseNewPromise, channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise)));
            }
            throw th;
        }
    }

    private void flush(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.add(Unpooled.EMPTY_BUFFER, channelPromise);
        } else {
            channelPromise.setFailure((Throwable) newPendingWritesNullException());
        }
        flush(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        this.pendingUnencryptedWrites = new SslHandlerCoalescingBufferQueue(channelHandlerContext.channel(), 16);
        if (channelHandlerContext.channel().isActive()) {
            startHandshakeProcessing();
        }
    }

    private void startHandshakeProcessing() {
        if (this.handshakeStarted) {
            return;
        }
        this.handshakeStarted = true;
        if (this.engine.getUseClientMode()) {
            handshake();
        }
        applyHandshakeTimeout();
    }

    public Future<Channel> renegotiate() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        return renegotiate(channelHandlerContext.executor().newPromise());
    }

    public Future<Channel> renegotiate(final Promise<Channel> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        EventExecutor eventExecutorExecutor = channelHandlerContext.executor();
        if (!eventExecutorExecutor.inEventLoop()) {
            eventExecutorExecutor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    SslHandler.this.renegotiateOnEventLoop(promise);
                }
            });
            return promise;
        }
        renegotiateOnEventLoop(promise);
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renegotiateOnEventLoop(Promise<Channel> promise) {
        Promise<Channel> promise2 = this.handshakePromise;
        if (!promise2.isDone()) {
            promise2.addListener((GenericFutureListener<? extends Future<? super Channel>>) new PromiseNotifier(promise));
            return;
        }
        this.handshakePromise = promise;
        handshake();
        applyHandshakeTimeout();
    }

    private void handshake() {
        if (this.engine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING && !this.handshakePromise.isDone()) {
            ChannelHandlerContext channelHandlerContext = this.ctx;
            try {
                this.engine.beginHandshake();
                wrapNonAppData(channelHandlerContext, false);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    private void applyHandshakeTimeout() {
        final Promise<Channel> promise = this.handshakePromise;
        final long j = this.handshakeTimeoutMillis;
        if (j <= 0 || promise.isDone()) {
            return;
        }
        final ScheduledFuture<?> scheduledFutureSchedule = this.ctx.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.5
            @Override // java.lang.Runnable
            public void run() {
                if (promise.isDone()) {
                    return;
                }
                SslHandshakeTimeoutException sslHandshakeTimeoutException = new SslHandshakeTimeoutException("handshake timed out after " + j + Configuration.CHANNEL_UNITS.MILLISECONDS);
                try {
                    if (promise.tryFailure(sslHandshakeTimeoutException)) {
                        SslUtils.handleHandshakeFailure(SslHandler.this.ctx, sslHandshakeTimeoutException, true);
                    }
                } finally {
                    SslHandler sslHandler = SslHandler.this;
                    sslHandler.releaseAndFailAll(sslHandler.ctx, sslHandshakeTimeoutException);
                }
            }
        }, j, TimeUnit.MILLISECONDS);
        promise.addListener((GenericFutureListener<? extends Future<? super Channel>>) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.6
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Channel> future) throws Exception {
                scheduledFutureSchedule.cancel(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceFlush(ChannelHandlerContext channelHandlerContext) {
        this.needsFlush = false;
        channelHandlerContext.flush();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.startTls) {
            startHandshakeProcessing();
        }
        channelHandlerContext.fireChannelActive();
    }

    private void safeClose(final ChannelHandlerContext channelHandlerContext, final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        if (!channelHandlerContext.channel().isActive()) {
            channelHandlerContext.close(channelPromise);
            return;
        }
        final ScheduledFuture<?> scheduledFutureSchedule = null;
        if (!channelFuture.isDone()) {
            long j = this.closeNotifyFlushTimeoutMillis;
            if (j > 0) {
                scheduledFutureSchedule = channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (channelFuture.isDone()) {
                            return;
                        }
                        SslHandler.logger.warn("{} Last write attempt timed out; force-closing the connection.", channelHandlerContext.channel());
                        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                        SslHandler.addCloseListener(channelHandlerContext2.close(channelHandlerContext2.newPromise()), channelPromise);
                    }
                }, j, TimeUnit.MILLISECONDS);
            }
        }
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                java.util.concurrent.ScheduledFuture scheduledFuture = scheduledFutureSchedule;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                final long j2 = SslHandler.this.closeNotifyReadTimeoutMillis;
                if (j2 <= 0) {
                    ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                    SslHandler.addCloseListener(channelHandlerContext2.close(channelHandlerContext2.newPromise()), channelPromise);
                } else {
                    final ScheduledFuture<?> scheduledFutureSchedule2 = !SslHandler.this.sslClosePromise.isDone() ? channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (SslHandler.this.sslClosePromise.isDone()) {
                                return;
                            }
                            SslHandler.logger.debug("{} did not receive close_notify in {}ms; force-closing the connection.", channelHandlerContext.channel(), Long.valueOf(j2));
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }, j2, TimeUnit.MILLISECONDS) : null;
                    SslHandler.this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8.2
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<Channel> future) throws Exception {
                            java.util.concurrent.ScheduledFuture scheduledFuture2 = scheduledFutureSchedule2;
                            if (scheduledFuture2 != null) {
                                scheduledFuture2.cancel(false);
                            }
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    });
                }
            }
        });
    }

    private ByteBuf allocate(ChannelHandlerContext channelHandlerContext, int i) {
        ByteBufAllocator byteBufAllocatorAlloc = channelHandlerContext.alloc();
        if (this.engineType.wantsDirectBuffer) {
            return byteBufAllocatorAlloc.directBuffer(i);
        }
        return byteBufAllocatorAlloc.buffer(i);
    }

    private ByteBuf allocateOutNetBuf(ChannelHandlerContext channelHandlerContext, int i, int i2) {
        return this.engineType.allocateWrapBuffer(this, channelHandlerContext.alloc(), i, i2);
    }

    private enum SslEngineType {
        TCNATIVE(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.1

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult sSLEngineResultUnwrap;
                int iNioBufferCount = byteBuf.nioBufferCount();
                int iWriterIndex = byteBuf2.writerIndex();
                if (iNioBufferCount > 1) {
                    ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = (ReferenceCountedOpenSslEngine) sslHandler.engine;
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, iWriterIndex, byteBuf2.writableBytes());
                        sSLEngineResultUnwrap = referenceCountedOpenSslEngine.unwrap(byteBuf.nioBuffers(i, i2), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    sSLEngineResultUnwrap = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, i, i2), SslHandler.toByteBuffer(byteBuf2, iWriterIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(iWriterIndex + sSLEngineResultUnwrap.bytesProduced());
                return sSLEngineResultUnwrap;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ReferenceCountedOpenSslEngine) sslHandler.engine).calculateMaxLengthForWrap(i, i2));
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                int iSslPending = ((ReferenceCountedOpenSslEngine) sslHandler.engine).sslPending();
                return iSslPending > 0 ? iSslPending : i;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return ((ReferenceCountedOpenSslEngine) sSLEngine).jdkCompatibilityMode;
            }
        },
        CONSCRYPT(1 == true ? 1 : 0, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.2

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult sSLEngineResultUnwrap;
                int iNioBufferCount = byteBuf.nioBufferCount();
                int iWriterIndex = byteBuf2.writerIndex();
                if (iNioBufferCount > 1) {
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, iWriterIndex, byteBuf2.writableBytes());
                        sSLEngineResultUnwrap = ((ConscryptAlpnSslEngine) sslHandler.engine).unwrap(byteBuf.nioBuffers(i, i2), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    sSLEngineResultUnwrap = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, i, i2), SslHandler.toByteBuffer(byteBuf2, iWriterIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(iWriterIndex + sSLEngineResultUnwrap.bytesProduced());
                return sSLEngineResultUnwrap;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ConscryptAlpnSslEngine) sslHandler.engine).calculateOutNetBufSize(i, i2));
            }
        },
        JDK(0 == true ? 1 : 0, ByteToMessageDecoder.MERGE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.3

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                int iPosition;
                int iWriterIndex = byteBuf2.writerIndex();
                ByteBuffer byteBuffer = SslHandler.toByteBuffer(byteBuf, i, i2);
                int iPosition2 = byteBuffer.position();
                SSLEngineResult sSLEngineResultUnwrap = sslHandler.engine.unwrap(byteBuffer, SslHandler.toByteBuffer(byteBuf2, iWriterIndex, byteBuf2.writableBytes()));
                byteBuf2.writerIndex(iWriterIndex + sSLEngineResultUnwrap.bytesProduced());
                return (sSLEngineResultUnwrap.bytesConsumed() != 0 || (iPosition = byteBuffer.position() - iPosition2) == sSLEngineResultUnwrap.bytesConsumed()) ? sSLEngineResultUnwrap : new SSLEngineResult(sSLEngineResultUnwrap.getStatus(), sSLEngineResultUnwrap.getHandshakeStatus(), iPosition, sSLEngineResultUnwrap.bytesProduced());
            }

            @Override
                // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.heapBuffer(sslHandler.engine.getSession().getPacketBufferSize());
            }
        };

        final ByteToMessageDecoder.Cumulator cumulator;
        final boolean wantsDirectBuffer;

        SslEngineType(boolean z, ByteToMessageDecoder.Cumulator cumulator) {
            this.wantsDirectBuffer = z;
            this.cumulator = cumulator;
        }

        static SslEngineType forEngine(SSLEngine sSLEngine) {
            return sSLEngine instanceof ReferenceCountedOpenSslEngine ? TCNATIVE : sSLEngine instanceof ConscryptAlpnSslEngine ? CONSCRYPT : JDK;
        }

        abstract ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2);

        abstract int calculatePendingData(SslHandler sslHandler, int i);

        abstract boolean jdkCompatibilityMode(SSLEngine sSLEngine);

        abstract SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException;
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        static {
            int[] iArr = new int[SSLEngineResult.Status.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$Status = iArr;
            try {
                iArr[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SSLEngineResult.HandshakeStatus.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = iArr2;
            try {
                iArr2[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private final class SslTasksRunner implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean inUnwrap;

        SslTasksRunner(boolean z) {
            this.inUnwrap = z;
        }

        private void taskError(Throwable th) {
            if (this.inUnwrap) {
                try {
                    SslHandler sslHandler = SslHandler.this;
                    sslHandler.handleUnwrapThrowable(sslHandler.ctx, th);
                    return;
                } catch (Throwable th2) {
                    safeExceptionCaught(th2);
                    return;
                }
            }
            SslHandler sslHandler2 = SslHandler.this;
            sslHandler2.setHandshakeFailure(sslHandler2.ctx, th);
            SslHandler sslHandler3 = SslHandler.this;
            sslHandler3.forceFlush(sslHandler3.ctx);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void safeExceptionCaught(Throwable th) {
            try {
                SslHandler sslHandler = SslHandler.this;
                sslHandler.exceptionCaught(sslHandler.ctx, wrapIfNeeded(th));
            } catch (Throwable th2) {
                SslHandler.this.ctx.fireExceptionCaught(th2);
            }
        }

        private Throwable wrapIfNeeded(Throwable th) {
            return (this.inUnwrap && !(th instanceof DecoderException)) ? new DecoderException(th) : th;
        }

        private void tryDecodeAgain() {
            try {
                SslHandler sslHandler = SslHandler.this;
                sslHandler.channelRead(sslHandler.ctx, Unpooled.EMPTY_BUFFER);
            } finally {
                try {
                    SslHandler sslHandler2 = SslHandler.this;
                    sslHandler2.channelReadComplete0(sslHandler2.ctx);
                } catch (Throwable th) {
                }
            }
            SslHandler sslHandler22 = SslHandler.this;
            sslHandler22.channelReadComplete0(sslHandler22.ctx);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void resumeOnEventExecutor() {
            int i;
            SslHandler.this.processTask = false;
            try {
                i = AnonymousClass9.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SslHandler.this.engine.getHandshakeStatus().ordinal()];
            } catch (Throwable th) {
                safeExceptionCaught(th);
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    SslHandler.this.setHandshakeSuccess();
                } else {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                try {
                                    SslHandler sslHandler = SslHandler.this;
                                    sslHandler.unwrapNonAppData(sslHandler.ctx);
                                    tryDecodeAgain();
                                    return;
                                } catch (SSLException e) {
                                    SslHandler sslHandler2 = SslHandler.this;
                                    sslHandler2.handleUnwrapThrowable(sslHandler2.ctx, e);
                                    return;
                                }
                            }
                            throw new AssertionError();
                        }
                        try {
                            SslHandler sslHandler3 = SslHandler.this;
                            if (!sslHandler3.wrapNonAppData(sslHandler3.ctx, false) && this.inUnwrap) {
                                SslHandler sslHandler4 = SslHandler.this;
                                sslHandler4.unwrapNonAppData(sslHandler4.ctx);
                            }
                            SslHandler sslHandler5 = SslHandler.this;
                            sslHandler5.forceFlush(sslHandler5.ctx);
                            tryDecodeAgain();
                            return;
                        } catch (Throwable th2) {
                            taskError(th2);
                            return;
                        }
                    }
                    safeExceptionCaught(th);
                    return;
                }
                SslHandler.this.setHandshakeSuccessIfStillHandshaking();
                try {
                    SslHandler sslHandler6 = SslHandler.this;
                    sslHandler6.wrap(sslHandler6.ctx, this.inUnwrap);
                    if (this.inUnwrap) {
                        SslHandler sslHandler7 = SslHandler.this;
                        sslHandler7.unwrapNonAppData(sslHandler7.ctx);
                    }
                    SslHandler sslHandler8 = SslHandler.this;
                    sslHandler8.forceFlush(sslHandler8.ctx);
                    tryDecodeAgain();
                    return;
                } catch (Throwable th3) {
                    taskError(th3);
                    return;
                }
            }
            SslHandler.this.executeDelegatedTasks(this.inUnwrap);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                SslHandler.runAllDelegatedTasks(SslHandler.this.engine);
                SslHandler.this.ctx.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslTasksRunner.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SslTasksRunner.this.resumeOnEventExecutor();
                    }
                });
            } catch (Throwable th) {
                handleException(th);
            }
        }

        private void handleException(final Throwable th) {
            if (SslHandler.this.ctx.executor().inEventLoop()) {
                SslHandler.this.processTask = false;
                safeExceptionCaught(th);
            } else {
                try {
                    SslHandler.this.ctx.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslTasksRunner.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SslHandler.this.processTask = false;
                            SslTasksRunner.this.safeExceptionCaught(th);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    SslHandler.this.processTask = false;
                    SslHandler.this.ctx.fireExceptionCaught(th);
                }
            }
        }
    }

    private final class SslHandlerCoalescingBufferQueue extends AbstractCoalescingBufferQueue {
        SslHandlerCoalescingBufferQueue(Channel channel, int i) {
            super(channel, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf removeEmptyValue() {
            return null;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
            int i = SslHandler.this.wrapDataSize;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return SslHandler.attemptCopyToCumulation(byteBuf, byteBuf2, i) ? byteBuf : copyAndCompose(byteBufAllocator, byteBuf, byteBuf2);
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            int iNumComponents = compositeByteBuf.numComponents();
            if (iNumComponents == 0 || !SslHandler.attemptCopyToCumulation(compositeByteBuf.internalComponent(iNumComponents - 1), byteBuf2, i)) {
                compositeByteBuf.addComponent(true, byteBuf2);
            }
            return compositeByteBuf;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) throws Throwable {
            ByteBuf byteBufHeapBuffer;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return byteBuf;
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            if (SslHandler.this.engineType.wantsDirectBuffer) {
                byteBufHeapBuffer = byteBufAllocator.directBuffer(compositeByteBuf.readableBytes());
            } else {
                byteBufHeapBuffer = byteBufAllocator.heapBuffer(compositeByteBuf.readableBytes());
            }
            try {
                byteBufHeapBuffer.writeBytes(compositeByteBuf);
            } catch (Throwable th) {
                byteBufHeapBuffer.release();
                PlatformDependent.throwException(th);
            }
            compositeByteBuf.release();
            return byteBufHeapBuffer;
        }
    }

    private final class LazyChannelPromise extends DefaultPromise<Channel> {
        private LazyChannelPromise() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
        protected EventExecutor executor() {
            if (SslHandler.this.ctx != null) {
                return SslHandler.this.ctx.executor();
            }
            throw new IllegalStateException();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
        protected void checkDeadLock() {
            if (SslHandler.this.ctx == null) {
                return;
            }
            super.checkDeadLock();
        }
    }
}
