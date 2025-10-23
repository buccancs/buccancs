package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelId;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelProgressivePromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.VoidChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameCodec;
import io.grpc.netty.shaded.io.netty.util.DefaultAttributeMap;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* loaded from: classes3.dex */
abstract class AbstractHttp2StreamChannel extends DefaultAttributeMap implements Http2StreamChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_HTTP2_FRAME_SIZE = 9;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractHttp2StreamChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final AtomicLongFieldUpdater<AbstractHttp2StreamChannel> TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "totalPendingSize");
    private static final AtomicIntegerFieldUpdater<AbstractHttp2StreamChannel> UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractHttp2StreamChannel.class, "unwritable");
    static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameStreamVisitor
        public boolean visit(Http2FrameStream http2FrameStream) {
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment).trySetWritable();
            return true;
        }
    };
    private final ChannelId channelId;
    private final ChannelPromise closePromise;
    private final ChannelPipeline pipeline;
    private final Http2FrameCodec.DefaultHttp2FrameStream stream;
    private final ChannelFutureListener windowUpdateFrameWriteListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.2
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(channelFuture, AbstractHttp2StreamChannel.this);
        }
    };
    private final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
    private final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
    private Runnable fireChannelWritabilityChangedTask;
    private boolean firstFrameWritten;
    private int flowControlledBytes;
    private Queue<Object> inboundBuffer;
    private boolean outboundClosed;
    private boolean readCompletePending;
    private volatile boolean registered;
    private volatile long totalPendingSize;
    private volatile int unwritable;
    private ReadStatus readStatus = ReadStatus.IDLE;

    AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, int i, ChannelHandler channelHandler) {
        this.stream = defaultHttp2FrameStream;
        defaultHttp2FrameStream.attachment = this;
        DefaultChannelPipeline defaultChannelPipeline = new DefaultChannelPipeline(this) { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.3
            @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
            protected void incrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(j, true);
            }

            @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
            protected void decrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(j, true);
            }
        };
        this.pipeline = defaultChannelPipeline;
        this.closePromise = defaultChannelPipeline.newPromise();
        this.channelId = new Http2StreamChannelId(parent().id(), i);
        if (channelHandler != null) {
            defaultChannelPipeline.addLast(channelHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void windowUpdateFrameWriteComplete(ChannelFuture channelFuture, Channel channel) {
        Throwable cause;
        Throwable thCause = channelFuture.cause();
        if (thCause != null) {
            if ((thCause instanceof Http2FrameStreamException) && (cause = thCause.getCause()) != null) {
                thCause = cause;
            }
            channel.pipeline().fireExceptionCaught(thCause);
            channel.unsafe().close(channel.unsafe().voidPromise());
        }
    }

    protected abstract void addChannelToReadCompletePendingQueue();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelFuture closeFuture() {
        return this.closePromise;
    }

    void closeOutbound() {
        this.outboundClosed = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelId id() {
        return this.channelId;
    }

    protected abstract boolean isParentReadInProgress();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isRegistered() {
        return this.registered;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isWritable() {
        return this.unwritable == 0;
    }

    private void setWritable(boolean z) {
        int i;
        int i2;
        do {
            i = this.unwritable;
            i2 = i & (-2);
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i2));
        if (i == 0 || i2 != 0) {
            return;
        }
        fireChannelWritabilityChanged(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    protected abstract ChannelHandlerContext parentContext();

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelPipeline pipeline() {
        return this.pipeline;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamChannel
    public Http2FrameStream stream() {
        return this.stream;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel.Unsafe unsafe() {
        return this.unsafe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, j) > config().getWriteBufferHighWaterMark()) {
            setUnwritable(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -j) < config().getWriteBufferLowWaterMark() && parent().isWritable()) {
            setWritable(z);
        }
    }

    final void trySetWritable() {
        if (this.totalPendingSize < config().getWriteBufferLowWaterMark()) {
            setWritable(false);
        }
    }

    private void setUnwritable(boolean z) {
        int i;
        int i2;
        do {
            i = this.unwritable;
            i2 = i | 1;
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i2));
        if (i != 0 || i2 == 0) {
            return;
        }
        fireChannelWritabilityChanged(z);
    }

    private void fireChannelWritabilityChanged(boolean z) {
        final ChannelPipeline channelPipelinePipeline = pipeline();
        if (z) {
            Runnable runnable = this.fireChannelWritabilityChangedTask;
            if (runnable == null) {
                runnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.4
                    @Override // java.lang.Runnable
                    public void run() {
                        channelPipelinePipeline.fireChannelWritabilityChanged();
                    }
                };
                this.fireChannelWritabilityChangedTask = runnable;
            }
            eventLoop().execute(runnable);
            return;
        }
        channelPipelinePipeline.fireChannelWritabilityChanged();
    }

    void streamClosed() {
        this.unsafe.readEOS();
        this.unsafe.doBeginRead();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return !this.closePromise.isDone();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return isOpen();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public EventLoop eventLoop() {
        return parent().eventLoop();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel parent() {
        return parentContext().channel();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress localAddress() {
        return parent().localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public SocketAddress remoteAddress() {
        return parent().remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeUnwritable() {
        long writeBufferHighWaterMark = config().getWriteBufferHighWaterMark() - this.totalPendingSize;
        if (writeBufferHighWaterMark <= 0 || !isWritable()) {
            return 0L;
        }
        return writeBufferHighWaterMark;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public long bytesBeforeWritable() {
        long writeBufferLowWaterMark = this.totalPendingSize - config().getWriteBufferLowWaterMark();
        if (writeBufferLowWaterMark <= 0 || isWritable()) {
            return 0L;
        }
        return writeBufferLowWaterMark;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ByteBufAllocator alloc() {
        return config().getAllocator();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel read() {
        pipeline().read();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public Channel flush() {
        pipeline().flush();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress) {
        return pipeline().bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress) {
        return pipeline().connect(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return pipeline().connect(socketAddress, socketAddress2);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect() {
        return pipeline().disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close() {
        return pipeline().close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister() {
        return pipeline().deregister();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture disconnect(ChannelPromise channelPromise) {
        return pipeline().disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture close(ChannelPromise channelPromise) {
        return pipeline().close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture deregister(ChannelPromise channelPromise) {
        return pipeline().deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj) {
        return pipeline().write(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        return pipeline().write(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        return pipeline().writeAndFlush(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture writeAndFlush(Object obj) {
        return pipeline().writeAndFlush(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise newPromise() {
        return pipeline().newPromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelProgressivePromise newProgressivePromise() {
        return pipeline().newProgressivePromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newSucceededFuture() {
        return pipeline().newSucceededFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelFuture newFailedFuture(Throwable th) {
        return pipeline().newFailedFuture(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public ChannelPromise voidPromise() {
        return pipeline().voidPromise();
    }

    public int hashCode() {
        return id().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(Channel channel) {
        if (this == channel) {
            return 0;
        }
        return id().compareTo(channel.id());
    }

    public String toString() {
        return parent().toString() + "(H2 - " + this.stream + ')';
    }

    void fireChildRead(Http2Frame http2Frame) {
        if (!isActive()) {
            ReferenceCountUtil.release(http2Frame);
            return;
        }
        if (this.readStatus != ReadStatus.IDLE) {
            RecvByteBufAllocator.Handle handleRecvBufAllocHandle = this.unsafe.recvBufAllocHandle();
            this.unsafe.doRead0(http2Frame, handleRecvBufAllocHandle);
            if (handleRecvBufAllocHandle.continueReading()) {
                maybeAddChannelToReadCompletePendingQueue();
                return;
            } else {
                this.unsafe.notifyReadComplete(handleRecvBufAllocHandle, true);
                return;
            }
        }
        if (this.inboundBuffer == null) {
            this.inboundBuffer = new ArrayDeque(4);
        }
        this.inboundBuffer.add(http2Frame);
    }

    void fireChildReadComplete() {
        Http2ChannelUnsafe http2ChannelUnsafe = this.unsafe;
        http2ChannelUnsafe.notifyReadComplete(http2ChannelUnsafe.recvBufAllocHandle(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAddChannelToReadCompletePendingQueue() {
        if (this.readCompletePending) {
            return;
        }
        this.readCompletePending = true;
        addChannelToReadCompletePendingQueue();
    }

    protected void flush0(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    protected ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
        ChannelPromise channelPromiseNewPromise = channelHandlerContext.newPromise();
        channelHandlerContext.write(obj, channelPromiseNewPromise);
        return channelPromiseNewPromise;
    }

    private enum ReadStatus {
        IDLE,
        IN_PROGRESS,
        REQUESTED
    }

    private static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
        static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();
        private static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.FlowControlledFrameSizeEstimator.1
            @Override // io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator.Handle
            public int size(Object obj) {
                if (obj instanceof Http2DataFrame) {
                    return (int) Math.min(2147483647L, ((Http2DataFrame) obj).initialFlowControlledBytes() + 9);
                }
                return 9;
            }
        };

        private FlowControlledFrameSizeEstimator() {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator
        public MessageSizeEstimator.Handle newHandle() {
            return HANDLE_INSTANCE;
        }
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus;

        static {
            int[] iArr = new int[ReadStatus.values().length];
            $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus = iArr;
            try {
                iArr[ReadStatus.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus[ReadStatus.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static final class Http2StreamChannelConfig extends DefaultChannelConfig {
        Http2StreamChannelConfig(Channel channel) {
            super(channel);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public MessageSizeEstimator getMessageSizeEstimator() {
            return FlowControlledFrameSizeEstimator.INSTANCE;
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
            throw new UnsupportedOperationException();
        }

        @Override
        // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
            if (!(recvByteBufAllocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle)) {
                throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
            }
            super.setRecvByteBufAllocator(recvByteBufAllocator);
            return this;
        }
    }

    private final class Http2ChannelUnsafe implements Channel.Unsafe {
        private final VoidChannelPromise unsafeVoidPromise;
        private boolean closeInitiated;
        private boolean readEOS;
        private RecvByteBufAllocator.Handle recvHandle;
        private boolean writeDoneAndNoFlush;

        private Http2ChannelUnsafe() {
            this.unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public ChannelOutboundBuffer outboundBuffer() {
            return null;
        }

        void readEOS() {
            this.readEOS = true;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public ChannelPromise voidPromise() {
            return this.unsafeVoidPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure((Throwable) new UnsupportedOperationException());
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public RecvByteBufAllocator.Handle recvBufAllocHandle() {
            if (this.recvHandle == null) {
                RecvByteBufAllocator.Handle handleNewHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
                this.recvHandle = handleNewHandle;
                handleNewHandle.reset(AbstractHttp2StreamChannel.this.config());
            }
            return this.recvHandle;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public SocketAddress localAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public SocketAddress remoteAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void register(EventLoop eventLoop, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    AbstractHttp2StreamChannel.this.registered = true;
                    channelPromise.setSuccess();
                    AbstractHttp2StreamChannel.this.pipeline().fireChannelRegistered();
                    if (AbstractHttp2StreamChannel.this.isActive()) {
                        AbstractHttp2StreamChannel.this.pipeline().fireChannelActive();
                        return;
                    }
                    return;
                }
                channelPromise.setFailure((Throwable) new UnsupportedOperationException("Re-register is not supported"));
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure((Throwable) new UnsupportedOperationException());
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void disconnect(ChannelPromise channelPromise) {
            close(channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void close(final ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                if (this.closeInitiated) {
                    if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
                        channelPromise.setSuccess();
                        return;
                    } else {
                        if (channelPromise instanceof VoidChannelPromise) {
                            return;
                        }
                        AbstractHttp2StreamChannel.this.closePromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.1
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) {
                                channelPromise.setSuccess();
                            }
                        });
                        return;
                    }
                }
                this.closeInitiated = true;
                AbstractHttp2StreamChannel.this.readCompletePending = false;
                boolean zIsActive = AbstractHttp2StreamChannel.this.isActive();
                if (AbstractHttp2StreamChannel.this.parent().isActive() && !this.readEOS && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.id())) {
                    write(new DefaultHttp2ResetFrame(Http2Error.CANCEL).stream(AbstractHttp2StreamChannel.this.stream()), AbstractHttp2StreamChannel.this.unsafe().voidPromise());
                    flush();
                }
                if (AbstractHttp2StreamChannel.this.inboundBuffer != null) {
                    while (true) {
                        Object objPoll = AbstractHttp2StreamChannel.this.inboundBuffer.poll();
                        if (objPoll == null) {
                            break;
                        } else {
                            ReferenceCountUtil.release(objPoll);
                        }
                    }
                    AbstractHttp2StreamChannel.this.inboundBuffer = null;
                }
                AbstractHttp2StreamChannel.this.outboundClosed = true;
                AbstractHttp2StreamChannel.this.closePromise.setSuccess();
                channelPromise.setSuccess();
                fireChannelInactiveAndDeregister(voidPromise(), zIsActive);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void closeForcibly() {
            close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void deregister(ChannelPromise channelPromise) {
            fireChannelInactiveAndDeregister(channelPromise, false);
        }

        private void fireChannelInactiveAndDeregister(final ChannelPromise channelPromise, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    channelPromise.setSuccess();
                } else {
                    invokeLater(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (z) {
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelInactive();
                            }
                            if (AbstractHttp2StreamChannel.this.registered) {
                                AbstractHttp2StreamChannel.this.registered = false;
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelUnregistered();
                            }
                            Http2ChannelUnsafe.this.safeSetSuccess(channelPromise);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void safeSetSuccess(ChannelPromise channelPromise) {
            if ((channelPromise instanceof VoidChannelPromise) || channelPromise.trySuccess()) {
                return;
            }
            AbstractHttp2StreamChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", channelPromise);
        }

        private void invokeLater(Runnable runnable) {
            try {
                AbstractHttp2StreamChannel.this.eventLoop().execute(runnable);
            } catch (RejectedExecutionException e) {
                AbstractHttp2StreamChannel.logger.warn("Can't invoke task later as EventLoop rejected it", (Throwable) e);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void beginRead() {
            if (AbstractHttp2StreamChannel.this.isActive()) {
                updateLocalWindowIfNeeded();
                int i = AnonymousClass5.$SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus[AbstractHttp2StreamChannel.this.readStatus.ordinal()];
                if (i == 1) {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                    doBeginRead();
                } else {
                    if (i != 2) {
                        return;
                    }
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.REQUESTED;
                }
            }
        }

        private Object pollQueuedMessage() {
            if (AbstractHttp2StreamChannel.this.inboundBuffer == null) {
                return null;
            }
            return AbstractHttp2StreamChannel.this.inboundBuffer.poll();
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x0045 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:38:? A[LOOP:1: B:11:0x002f->B:38:?, LOOP_END, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void doBeginRead() {
            /*
                r3 = this;
            L0:
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.this
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel$ReadStatus r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.access$1300(r0)
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel$ReadStatus r1 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.ReadStatus.IDLE
                if (r0 == r1) goto L5f
                java.lang.Object r0 = r3.pollQueuedMessage()
                if (r0 != 0) goto L21
                boolean r0 = r3.readEOS
                if (r0 == 0) goto L1d
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.this
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel$Http2ChannelUnsafe r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.access$1400(r0)
                r0.closeForcibly()
            L1d:
                r3.flush()
                goto L5f
            L21:
                io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator$Handle r1 = r3.recvBufAllocHandle()
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel r2 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.this
                io.grpc.netty.shaded.io.netty.channel.ChannelConfig r2 = r2.config()
                r1.reset(r2)
                r2 = 0
            L2f:
                io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame r0 = (io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame) r0
                r3.doRead0(r0, r1)
                boolean r0 = r3.readEOS
                if (r0 != 0) goto L3f
                boolean r0 = r1.continueReading()
                if (r0 == 0) goto L46
                r2 = r0
            L3f:
                java.lang.Object r0 = r3.pollQueuedMessage()
                if (r0 != 0) goto L2f
                r0 = r2
            L46:
                if (r0 == 0) goto L5a
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.this
                boolean r0 = r0.isParentReadInProgress()
                if (r0 == 0) goto L5a
                boolean r0 = r3.readEOS
                if (r0 != 0) goto L5a
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel r0 = io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.this
                io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.access$1500(r0)
                goto L0
            L5a:
                r0 = 1
                r3.notifyReadComplete(r1, r0)
                goto L0
            L5f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.doBeginRead():void");
        }

        private void updateLocalWindowIfNeeded() {
            if (AbstractHttp2StreamChannel.this.flowControlledBytes != 0) {
                int i = AbstractHttp2StreamChannel.this.flowControlledBytes;
                AbstractHttp2StreamChannel.this.flowControlledBytes = 0;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture channelFutureWrite0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), new DefaultHttp2WindowUpdateFrame(i).stream((Http2FrameStream) AbstractHttp2StreamChannel.this.stream));
                this.writeDoneAndNoFlush = true;
                if (channelFutureWrite0.isDone()) {
                    AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(channelFutureWrite0, AbstractHttp2StreamChannel.this);
                } else {
                    channelFutureWrite0.addListener((GenericFutureListener<? extends Future<? super Void>>) AbstractHttp2StreamChannel.this.windowUpdateFrameWriteListener);
                }
            }
        }

        void notifyReadComplete(RecvByteBufAllocator.Handle handle, boolean z) {
            if (AbstractHttp2StreamChannel.this.readCompletePending || z) {
                AbstractHttp2StreamChannel.this.readCompletePending = false;
                if (AbstractHttp2StreamChannel.this.readStatus == ReadStatus.REQUESTED) {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                } else {
                    AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IDLE;
                }
                handle.readComplete();
                AbstractHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
                flush();
                if (this.readEOS) {
                    AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                }
            }
        }

        void doRead0(Http2Frame http2Frame, RecvByteBufAllocator.Handle handle) {
            int iInitialFlowControlledBytes;
            if (http2Frame instanceof Http2DataFrame) {
                iInitialFlowControlledBytes = ((Http2DataFrame) http2Frame).initialFlowControlledBytes();
                AbstractHttp2StreamChannel.this.flowControlledBytes += iInitialFlowControlledBytes;
            } else {
                iInitialFlowControlledBytes = 9;
            }
            handle.attemptedBytesRead(iInitialFlowControlledBytes);
            handle.lastBytesRead(iInitialFlowControlledBytes);
            handle.incMessagesRead(1);
            AbstractHttp2StreamChannel.this.pipeline().fireChannelRead((Object) http2Frame);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void write(Object obj, ChannelPromise channelPromise) {
            if (!channelPromise.setUncancellable()) {
                ReferenceCountUtil.release(obj);
                return;
            }
            if (!AbstractHttp2StreamChannel.this.isActive() || (AbstractHttp2StreamChannel.this.outboundClosed && ((obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame)))) {
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure((Throwable) new ClosedChannelException());
                return;
            }
            try {
                if (obj instanceof Http2StreamFrame) {
                    writeHttp2StreamFrame(validateStreamFrame((Http2StreamFrame) obj).stream(AbstractHttp2StreamChannel.this.stream()), channelPromise);
                    return;
                }
                String string = obj.toString();
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure((Throwable) new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName((Class<?>) Http2StreamFrame.class) + ": " + string));
            } catch (Throwable th) {
                channelPromise.tryFailure(th);
            }
        }

        private void writeHttp2StreamFrame(Http2StreamFrame http2StreamFrame, final ChannelPromise channelPromise) {
            if (AbstractHttp2StreamChannel.this.firstFrameWritten || Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().id()) || (http2StreamFrame instanceof Http2HeadersFrame)) {
                final boolean z = AbstractHttp2StreamChannel.this.firstFrameWritten ? false : AbstractHttp2StreamChannel.this.firstFrameWritten = true;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture channelFutureWrite0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), http2StreamFrame);
                if (channelFutureWrite0.isDone()) {
                    if (z) {
                        firstWriteComplete(channelFutureWrite0, channelPromise);
                        return;
                    } else {
                        writeComplete(channelFutureWrite0, channelPromise);
                        return;
                    }
                }
                final long size = FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(http2StreamFrame);
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, false);
                channelFutureWrite0.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.AbstractHttp2StreamChannel.Http2ChannelUnsafe.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) {
                        if (z) {
                            Http2ChannelUnsafe.this.firstWriteComplete(channelFuture, channelPromise);
                        } else {
                            Http2ChannelUnsafe.this.writeComplete(channelFuture, channelPromise);
                        }
                        AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, false);
                    }
                });
                this.writeDoneAndNoFlush = true;
                return;
            }
            ReferenceCountUtil.release(http2StreamFrame);
            channelPromise.setFailure((Throwable) new IllegalArgumentException("The first frame must be a headers frame. Was: " + http2StreamFrame.name()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void firstWriteComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
            Throwable thCause = channelFuture.cause();
            if (thCause == null) {
                channelPromise.setSuccess();
            } else {
                closeForcibly();
                channelPromise.setFailure(wrapStreamClosedError(thCause));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
            Throwable thCause = channelFuture.cause();
            if (thCause == null) {
                channelPromise.setSuccess();
                return;
            }
            Throwable thWrapStreamClosedError = wrapStreamClosedError(thCause);
            if (thWrapStreamClosedError instanceof IOException) {
                if (!AbstractHttp2StreamChannel.this.config.isAutoClose()) {
                    AbstractHttp2StreamChannel.this.outboundClosed = true;
                } else {
                    closeForcibly();
                }
            }
            channelPromise.setFailure(thWrapStreamClosedError);
        }

        private Throwable wrapStreamClosedError(Throwable th) {
            return ((th instanceof Http2Exception) && ((Http2Exception) th).error() == Http2Error.STREAM_CLOSED) ? new ClosedChannelException().initCause(th) : th;
        }

        private Http2StreamFrame validateStreamFrame(Http2StreamFrame http2StreamFrame) {
            if (http2StreamFrame.stream() == null || http2StreamFrame.stream() == AbstractHttp2StreamChannel.this.stream) {
                return http2StreamFrame;
            }
            String string = http2StreamFrame.toString();
            ReferenceCountUtil.release(http2StreamFrame);
            throw new IllegalArgumentException("Stream " + http2StreamFrame.stream() + " must not be set on the frame: " + string);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void flush() {
            if (!this.writeDoneAndNoFlush || AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
                return;
            }
            this.writeDoneAndNoFlush = false;
            AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
            abstractHttp2StreamChannel.flush0(abstractHttp2StreamChannel.parentContext());
        }
    }
}
