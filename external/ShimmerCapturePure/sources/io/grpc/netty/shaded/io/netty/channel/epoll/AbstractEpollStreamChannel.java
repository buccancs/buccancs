package io.grpc.netty.shaded.io.netty.channel.epoll;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultFileRegion;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.FileRegion;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor;
import io.grpc.netty.shaded.io.netty.channel.unix.IovArray;
import io.grpc.netty.shaded.io.netty.channel.unix.SocketWritableByteChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.UnixChannelUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.Queue;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class AbstractEpollStreamChannel extends AbstractEpollChannel implements DuplexChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) DefaultFileRegion.class) + ')';
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractEpollStreamChannel.class);
    private final Runnable flushTask;
    private WritableByteChannel byteChannel;
    private FileDescriptor pipeIn;
    private FileDescriptor pipeOut;
    private volatile Queue<SpliceInTask> spliceQueue;

    protected AbstractEpollStreamChannel(Channel channel, int i) {
        this(channel, new LinuxSocket(i));
    }

    protected AbstractEpollStreamChannel(int i) {
        this(new LinuxSocket(i));
    }

    AbstractEpollStreamChannel(LinuxSocket linuxSocket) {
        this(linuxSocket, isSoErrorZero(linuxSocket));
    }

    AbstractEpollStreamChannel(Channel channel, LinuxSocket linuxSocket) {
        super(channel, linuxSocket, true);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    AbstractEpollStreamChannel(Channel channel, LinuxSocket linuxSocket, SocketAddress socketAddress) {
        super(channel, linuxSocket, socketAddress);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    protected AbstractEpollStreamChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void shutdownDone(ChannelFuture channelFuture, ChannelFuture channelFuture2, ChannelPromise channelPromise) {
        Throwable thCause = channelFuture.cause();
        Throwable thCause2 = channelFuture2.cause();
        if (thCause != null) {
            if (thCause2 != null) {
                logger.debug("Exception suppressed because a previous exception occurred.", thCause2);
            }
            channelPromise.setFailure(thCause);
        } else if (thCause2 != null) {
            channelPromise.setFailure(thCause2);
        } else {
            channelPromise.setSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void safeClosePipe(FileDescriptor fileDescriptor) {
        if (fileDescriptor != null) {
            try {
                fileDescriptor.close();
            } catch (IOException e) {
                logger.warn("Error while closing a pipe", (Throwable) e);
            }
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isActive() {
        return super.isActive();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollStreamUnsafe();
    }

    public final ChannelFuture spliceTo(AbstractEpollStreamChannel abstractEpollStreamChannel, int i) {
        return spliceTo(abstractEpollStreamChannel, i, newPromise());
    }

    public final ChannelFuture spliceTo(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, ChannelPromise channelPromise) {
        if (abstractEpollStreamChannel.eventLoop() != eventLoop()) {
            throw new IllegalArgumentException("EventLoops are not the same.");
        }
        ObjectUtil.checkPositiveOrZero(i, "len");
        if (abstractEpollStreamChannel.config().getEpollMode() != EpollMode.LEVEL_TRIGGERED || config().getEpollMode() != EpollMode.LEVEL_TRIGGERED) {
            throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
        }
        ObjectUtil.checkNotNull(channelPromise, "promise");
        if (!isOpen()) {
            channelPromise.tryFailure(new ClosedChannelException());
        } else {
            addToSpliceQueue(new SpliceInChannelTask(abstractEpollStreamChannel, i, channelPromise));
            failSpliceIfClosed(channelPromise);
        }
        return channelPromise;
    }

    public final ChannelFuture spliceTo(FileDescriptor fileDescriptor, int i, int i2) {
        return spliceTo(fileDescriptor, i, i2, newPromise());
    }

    public final ChannelFuture spliceTo(FileDescriptor fileDescriptor, int i, int i2, ChannelPromise channelPromise) {
        ObjectUtil.checkPositiveOrZero(i2, "len");
        ObjectUtil.checkPositiveOrZero(i, TypedValues.CycleType.S_WAVE_OFFSET);
        if (config().getEpollMode() != EpollMode.LEVEL_TRIGGERED) {
            throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
        }
        ObjectUtil.checkNotNull(channelPromise, "promise");
        if (!isOpen()) {
            channelPromise.tryFailure(new ClosedChannelException());
        } else {
            addToSpliceQueue(new SpliceFdTask(fileDescriptor, i, i2, channelPromise));
            failSpliceIfClosed(channelPromise);
        }
        return channelPromise;
    }

    private void failSpliceIfClosed(ChannelPromise channelPromise) {
        if (isOpen() || !channelPromise.tryFailure(new ClosedChannelException())) {
            return;
        }
        eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.2
            @Override // java.lang.Runnable
            public void run() {
                AbstractEpollStreamChannel.this.clearSpliceQueue();
            }
        });
    }

    private int writeBytes(ChannelOutboundBuffer channelOutboundBuffer, ByteBuf byteBuf) throws Exception {
        int i = byteBuf.readableBytes();
        if (i == 0) {
            channelOutboundBuffer.remove();
            return 0;
        }
        if (byteBuf.hasMemoryAddress() || byteBuf.nioBufferCount() == 1) {
            return doWriteBytes(channelOutboundBuffer, byteBuf);
        }
        ByteBuffer[] byteBufferArrNioBuffers = byteBuf.nioBuffers();
        return writeBytesMultiple(channelOutboundBuffer, byteBufferArrNioBuffers, byteBufferArrNioBuffers.length, i, config().getMaxBytesPerGatheringWrite());
    }

    private void adjustMaxBytesPerGatheringWrite(long j, long j2, long j3) {
        if (j == j2) {
            long j4 = j << 1;
            if (j4 > j3) {
                config().setMaxBytesPerGatheringWrite(j4);
                return;
            }
            return;
        }
        if (j > 4096) {
            long j5 = j >>> 1;
            if (j2 < j5) {
                config().setMaxBytesPerGatheringWrite(j5);
            }
        }
    }

    private int writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, IovArray iovArray) throws IOException {
        long size = iovArray.size();
        long jWritevAddresses = this.socket.writevAddresses(iovArray.memoryAddress(0), iovArray.count());
        if (jWritevAddresses <= 0) {
            return Integer.MAX_VALUE;
        }
        adjustMaxBytesPerGatheringWrite(size, jWritevAddresses, iovArray.maxBytes());
        channelOutboundBuffer.removeBytes(jWritevAddresses);
        return 1;
    }

    private int writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, ByteBuffer[] byteBufferArr, int i, long j, long j2) throws IOException {
        if (j > j2) {
            j = j2;
        }
        long jWritev = this.socket.writev(byteBufferArr, 0, i, j);
        if (jWritev <= 0) {
            return Integer.MAX_VALUE;
        }
        adjustMaxBytesPerGatheringWrite(j, jWritev, j2);
        channelOutboundBuffer.removeBytes(jWritev);
        return 1;
    }

    private int writeDefaultFileRegion(ChannelOutboundBuffer channelOutboundBuffer, DefaultFileRegion defaultFileRegion) throws Exception {
        long jTransferred = defaultFileRegion.transferred();
        long jCount = defaultFileRegion.count();
        if (jTransferred >= jCount) {
            channelOutboundBuffer.remove();
            return 0;
        }
        long jSendFile = this.socket.sendFile(defaultFileRegion, defaultFileRegion.position(), jTransferred, jCount - jTransferred);
        if (jSendFile <= 0) {
            if (jSendFile != 0) {
                return Integer.MAX_VALUE;
            }
            validateFileRegion(defaultFileRegion, jTransferred);
            return Integer.MAX_VALUE;
        }
        channelOutboundBuffer.progress(jSendFile);
        if (defaultFileRegion.transferred() < jCount) {
            return 1;
        }
        channelOutboundBuffer.remove();
        return 1;
    }

    private int writeFileRegion(ChannelOutboundBuffer channelOutboundBuffer, FileRegion fileRegion) throws Exception {
        if (fileRegion.transferred() >= fileRegion.count()) {
            channelOutboundBuffer.remove();
            return 0;
        }
        if (this.byteChannel == null) {
            this.byteChannel = new EpollSocketWritableByteChannel();
        }
        long jTransferTo = fileRegion.transferTo(this.byteChannel, fileRegion.transferred());
        if (jTransferTo <= 0) {
            return Integer.MAX_VALUE;
        }
        channelOutboundBuffer.progress(jTransferTo);
        if (fileRegion.transferred() < fileRegion.count()) {
            return 1;
        }
        channelOutboundBuffer.remove();
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int iDoWriteSingle;
        int writeSpinCount = config().getWriteSpinCount();
        do {
            int size = channelOutboundBuffer.size();
            if (size > 1 && (channelOutboundBuffer.current() instanceof ByteBuf)) {
                iDoWriteSingle = doWriteMultiple(channelOutboundBuffer);
            } else {
                if (size == 0) {
                    clearFlag(Native.EPOLLOUT);
                    return;
                }
                iDoWriteSingle = doWriteSingle(channelOutboundBuffer);
            }
            writeSpinCount -= iDoWriteSingle;
        } while (writeSpinCount > 0);
        if (writeSpinCount == 0) {
            clearFlag(Native.EPOLLOUT);
            eventLoop().execute(this.flushTask);
        } else {
            setFlag(Native.EPOLLOUT);
        }
    }

    protected int doWriteSingle(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        Object objCurrent = channelOutboundBuffer.current();
        if (objCurrent instanceof ByteBuf) {
            return writeBytes(channelOutboundBuffer, (ByteBuf) objCurrent);
        }
        if (objCurrent instanceof DefaultFileRegion) {
            return writeDefaultFileRegion(channelOutboundBuffer, (DefaultFileRegion) objCurrent);
        }
        if (objCurrent instanceof FileRegion) {
            return writeFileRegion(channelOutboundBuffer, (FileRegion) objCurrent);
        }
        if (objCurrent instanceof SpliceOutTask) {
            if (!((SpliceOutTask) objCurrent).spliceOut()) {
                return Integer.MAX_VALUE;
            }
            channelOutboundBuffer.remove();
            return 1;
        }
        throw new Error();
    }

    private int doWriteMultiple(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        long maxBytesPerGatheringWrite = config().getMaxBytesPerGatheringWrite();
        IovArray iovArrayCleanIovArray = ((EpollEventLoop) eventLoop()).cleanIovArray();
        iovArrayCleanIovArray.maxBytes(maxBytesPerGatheringWrite);
        channelOutboundBuffer.forEachFlushedMessage(iovArrayCleanIovArray);
        if (iovArrayCleanIovArray.count() >= 1) {
            return writeBytesMultiple(channelOutboundBuffer, iovArrayCleanIovArray);
        }
        channelOutboundBuffer.removeBytes(0L);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) {
        if (obj instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? newDirectBuffer(byteBuf) : byteBuf;
        }
        if ((obj instanceof FileRegion) || (obj instanceof SpliceOutTask)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected final void doShutdownOutput() throws Exception {
        this.socket.shutdown(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownInput0(ChannelPromise channelPromise) {
        try {
            this.socket.shutdown(true, false);
            channelPromise.setSuccess();
        } catch (Throwable th) {
            channelPromise.setFailure(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isOutputShutdown() {
        return this.socket.isOutputShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isInputShutdown() {
        return this.socket.isInputShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isShutdown() {
        return this.socket.isShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownOutput() {
        return shutdownOutput(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownOutput(final ChannelPromise channelPromise) {
        EventLoop eventLoop = eventLoop();
        if (eventLoop.inEventLoop()) {
            ((AbstractChannel.AbstractUnsafe) unsafe()).shutdownOutput(channelPromise);
        } else {
            eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.3
                @Override // java.lang.Runnable
                public void run() {
                    ((AbstractChannel.AbstractUnsafe) AbstractEpollStreamChannel.this.unsafe()).shutdownOutput(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownInput() {
        return shutdownInput(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownInput(final ChannelPromise channelPromise) {
        Executor executorPrepareToClose = ((EpollStreamUnsafe) unsafe()).prepareToClose();
        if (executorPrepareToClose != null) {
            executorPrepareToClose.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.4
                @Override // java.lang.Runnable
                public void run() {
                    AbstractEpollStreamChannel.this.shutdownInput0(channelPromise);
                }
            });
        } else {
            EventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                shutdownInput0(channelPromise);
            } else {
                eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.5
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractEpollStreamChannel.this.shutdownInput0(channelPromise);
                    }
                });
            }
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdown() {
        return shutdown(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdown(final ChannelPromise channelPromise) {
        ChannelFuture channelFutureShutdownOutput = shutdownOutput();
        if (channelFutureShutdownOutput.isDone()) {
            shutdownOutputDone(channelFutureShutdownOutput, channelPromise);
        } else {
            channelFutureShutdownOutput.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.6
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    AbstractEpollStreamChannel.this.shutdownOutputDone(channelFuture, channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownOutputDone(final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        ChannelFuture channelFutureShutdownInput = shutdownInput();
        if (channelFutureShutdownInput.isDone()) {
            shutdownDone(channelFuture, channelFutureShutdownInput, channelPromise);
        } else {
            channelFutureShutdownInput.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.7
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                    AbstractEpollStreamChannel.shutdownDone(channelFuture, channelFuture2, channelPromise);
                }
            });
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        try {
            super.doClose();
        } finally {
            safeClosePipe(this.pipeIn);
            safeClosePipe(this.pipeOut);
            clearSpliceQueue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSpliceQueue() {
        Queue<SpliceInTask> queue = this.spliceQueue;
        if (queue == null) {
            return;
        }
        ClosedChannelException closedChannelException = null;
        while (true) {
            SpliceInTask spliceInTaskPoll = queue.poll();
            if (spliceInTaskPoll == null) {
                return;
            }
            if (closedChannelException == null) {
                closedChannelException = new ClosedChannelException();
            }
            spliceInTaskPoll.promise.tryFailure(closedChannelException);
        }
    }

    private void addToSpliceQueue(SpliceInTask spliceInTask) {
        Queue<SpliceInTask> queueNewMpscQueue = this.spliceQueue;
        if (queueNewMpscQueue == null) {
            synchronized (this) {
                queueNewMpscQueue = this.spliceQueue;
                if (queueNewMpscQueue == null) {
                    queueNewMpscQueue = PlatformDependent.newMpscQueue();
                    this.spliceQueue = queueNewMpscQueue;
                }
            }
        }
        queueNewMpscQueue.add(spliceInTask);
    }

    class EpollStreamUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        EpollStreamUnsafe() {
            super();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe
        protected Executor prepareToClose() {
            return super.prepareToClose();
        }

        private void handleReadException(ChannelPipeline channelPipeline, ByteBuf byteBuf, Throwable th, boolean z, EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle) {
            if (byteBuf != null) {
                if (byteBuf.isReadable()) {
                    this.readPending = false;
                    channelPipeline.fireChannelRead((Object) byteBuf);
                } else {
                    byteBuf.release();
                }
            }
            epollRecvByteAllocatorHandle.readComplete();
            channelPipeline.fireChannelReadComplete();
            channelPipeline.fireExceptionCaught(th);
            if (z || (th instanceof IOException)) {
                shutdownInput(false);
            }
        }

        @Override
            // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        EpollRecvByteAllocatorHandle newEpollHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
            return new EpollRecvByteAllocatorStreamingHandle(extendedHandle);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:11:0x003e A[Catch: all -> 0x00ab, PHI: r4
  0x003e: PHI (r4v11 java.util.Queue) = (r4v1 java.util.Queue), (r4v3 java.util.Queue) binds: [B:8:0x0034, B:10:0x003c] A[DONT_GENERATE, DONT_INLINE], TryCatch #3 {all -> 0x00ab, blocks: (B:9:0x0036, B:19:0x005a, B:23:0x0071, B:31:0x0086, B:34:0x008f, B:11:0x003e, B:13:0x0046, B:15:0x004c, B:17:0x0054), top: B:61:0x0036 }] */
        /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[Catch: all -> 0x00ab, PHI: r4
  0x005a: PHI (r4v6 java.util.Queue) = (r4v3 java.util.Queue), (r4v11 java.util.Queue) binds: [B:10:0x003c, B:12:0x0044] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #3 {all -> 0x00ab, blocks: (B:9:0x0036, B:19:0x005a, B:23:0x0071, B:31:0x0086, B:34:0x008f, B:11:0x003e, B:13:0x0046, B:15:0x004c, B:17:0x0054), top: B:61:0x0036 }] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x009e A[Catch: all -> 0x00a2, TRY_LEAVE, TryCatch #0 {all -> 0x00a2, blocks: (B:28:0x007b, B:37:0x0096, B:39:0x009e), top: B:55:0x007b }] */
        @Override
        // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void epollInReady() {
            /*
                r10 = this;
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r0 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this
                io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig r0 = r0.config()
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r1 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this
                boolean r1 = r1.shouldBreakEpollInReady(r0)
                if (r1 == 0) goto L12
                r10.clearEpollIn0()
                return
            L12:
                io.grpc.netty.shaded.io.netty.channel.epoll.EpollRecvByteAllocatorHandle r7 = r10.recvBufAllocHandle()
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r1 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this
                int r2 = io.grpc.netty.shaded.io.netty.channel.epoll.Native.EPOLLET
                boolean r1 = r1.isFlagSet(r2)
                r7.edgeTriggered(r1)
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r1 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this
                io.grpc.netty.shaded.io.netty.channel.ChannelPipeline r3 = r1.pipeline()
                io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator r1 = r0.getAllocator()
                r7.reset(r0)
                r10.epollInBefore()
                r2 = 0
                r4 = r2
            L33:
                r5 = 0
                if (r4 != 0) goto L3e
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r4 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this     // Catch: java.lang.Throwable -> Lab
                java.util.Queue r4 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.access$400(r4)     // Catch: java.lang.Throwable -> Lab
                if (r4 == 0) goto L5a
            L3e:
                java.lang.Object r6 = r4.peek()     // Catch: java.lang.Throwable -> Lab
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel$SpliceInTask r6 = (io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.SpliceInTask) r6     // Catch: java.lang.Throwable -> Lab
                if (r6 == 0) goto L5a
                boolean r6 = r6.spliceIn(r7)     // Catch: java.lang.Throwable -> Lab
                if (r6 == 0) goto L58
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r6 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this     // Catch: java.lang.Throwable -> Lab
                boolean r6 = r6.isActive()     // Catch: java.lang.Throwable -> Lab
                if (r6 == 0) goto L8f
                r4.remove()     // Catch: java.lang.Throwable -> Lab
                goto L8f
            L58:
                r9 = 0
                goto L96
            L5a:
                io.grpc.netty.shaded.io.netty.buffer.ByteBuf r6 = r7.allocate(r1)     // Catch: java.lang.Throwable -> Lab
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r8 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this     // Catch: java.lang.Throwable -> La7
                int r8 = r8.doReadBytes(r6)     // Catch: java.lang.Throwable -> La7
                r7.lastBytesRead(r8)     // Catch: java.lang.Throwable -> La7
                int r8 = r7.lastBytesRead()     // Catch: java.lang.Throwable -> La7
                r9 = 1
                if (r8 > 0) goto L7e
                r6.release()     // Catch: java.lang.Throwable -> La7
                int r1 = r7.lastBytesRead()     // Catch: java.lang.Throwable -> Lab
                if (r1 >= 0) goto L78
                goto L79
            L78:
                r9 = 0
            L79:
                if (r9 == 0) goto L96
                r10.readPending = r5     // Catch: java.lang.Throwable -> La2
                goto L96
            L7e:
                r7.incMessagesRead(r9)     // Catch: java.lang.Throwable -> La7
                r10.readPending = r5     // Catch: java.lang.Throwable -> La7
                r3.fireChannelRead(r6)     // Catch: java.lang.Throwable -> La7
                io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel r6 = io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.this     // Catch: java.lang.Throwable -> Lab
                boolean r6 = r6.shouldBreakEpollInReady(r0)     // Catch: java.lang.Throwable -> Lab
                if (r6 == 0) goto L8f
                goto L58
            L8f:
                boolean r6 = r7.continueReading()     // Catch: java.lang.Throwable -> Lab
                if (r6 != 0) goto L33
                goto L58
            L96:
                r7.readComplete()     // Catch: java.lang.Throwable -> La2
                r3.fireChannelReadComplete()     // Catch: java.lang.Throwable -> La2
                if (r9 == 0) goto Lb3
                r10.shutdownInput(r5)     // Catch: java.lang.Throwable -> La2
                goto Lb3
            La2:
                r1 = move-exception
                r5 = r1
                r4 = r2
                r6 = r9
                goto Laf
            La7:
                r1 = move-exception
                r5 = r1
                r4 = r6
                goto Lae
            Lab:
                r1 = move-exception
                r5 = r1
                r4 = r2
            Lae:
                r6 = 0
            Laf:
                r2 = r10
                r2.handleReadException(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> Lb7
            Lb3:
                r10.epollInFinally(r0)
                return
            Lb7:
                r1 = move-exception
                r10.epollInFinally(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.EpollStreamUnsafe.epollInReady():void");
        }
    }

    protected abstract class SpliceInTask {
        final ChannelPromise promise;
        int len;

        protected SpliceInTask(int i, ChannelPromise channelPromise) {
            this.promise = channelPromise;
            this.len = i;
        }

        abstract boolean spliceIn(RecvByteBufAllocator.Handle handle);

        protected final int spliceIn(FileDescriptor fileDescriptor, RecvByteBufAllocator.Handle handle) throws IOException {
            int iMin = Math.min(handle.guess(), this.len);
            int i = 0;
            while (true) {
                int iSplice = Native.splice(AbstractEpollStreamChannel.this.socket.intValue(), -1L, fileDescriptor.intValue(), -1L, iMin);
                if (iSplice == 0) {
                    return i;
                }
                i += iSplice;
                iMin -= iSplice;
            }
        }
    }

    private final class SpliceInChannelTask extends SpliceInTask implements ChannelFutureListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AbstractEpollStreamChannel ch;

        SpliceInChannelTask(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, ChannelPromise channelPromise) {
            super(i, channelPromise);
            this.ch = abstractEpollStreamChannel;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            this.promise.setFailure(channelFuture.cause());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r4v1, types: [io.grpc.netty.shaded.io.netty.channel.Channel$Unsafe] */
        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.SpliceInTask
        public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
            ??AddListener;
            if (this.len != 0) {
                try {
                    FileDescriptor fileDescriptor = this.ch.pipeOut;
                    if (fileDescriptor == null) {
                        FileDescriptor[] fileDescriptorArrPipe = FileDescriptor.pipe();
                        this.ch.pipeIn = fileDescriptorArrPipe[0];
                        fileDescriptor = this.ch.pipeOut = fileDescriptorArrPipe[1];
                    }
                    int iSpliceIn = spliceIn(fileDescriptor, handle);
                    if (iSpliceIn > 0) {
                        if (this.len != Integer.MAX_VALUE) {
                            this.len -= iSpliceIn;
                        }
                        if (this.len == 0) {
                            AddListener = this.promise;
                        } else {
                            AddListener = this.ch.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
                        }
                        boolean zIsAutoRead = AbstractEpollStreamChannel.this.config().isAutoRead();
                        this.ch.unsafe().write(AbstractEpollStreamChannel.this.new SpliceOutTask(this.ch, iSpliceIn, zIsAutoRead), AddListener);
                        this.ch.unsafe().flush();
                        if (zIsAutoRead && !AddListener.isDone()) {
                            AbstractEpollStreamChannel.this.config().setAutoRead(false);
                        }
                    }
                    return this.len == 0;
                } catch (Throwable th) {
                    this.promise.setFailure(th);
                    return true;
                }
            }
            this.promise.setSuccess();
            return true;
        }
    }

    private final class SpliceOutTask {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean autoRead;
        private final AbstractEpollStreamChannel ch;
        private int len;

        SpliceOutTask(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, boolean z) {
            this.ch = abstractEpollStreamChannel;
            this.len = i;
            this.autoRead = z;
        }

        public boolean spliceOut() throws Exception {
            try {
                int iSplice = this.len - Native.splice(this.ch.pipeIn.intValue(), -1L, this.ch.socket.intValue(), -1L, this.len);
                this.len = iSplice;
                if (iSplice != 0) {
                    return false;
                }
                if (this.autoRead) {
                    AbstractEpollStreamChannel.this.config().setAutoRead(true);
                }
                return true;
            } catch (IOException e) {
                if (this.autoRead) {
                    AbstractEpollStreamChannel.this.config().setAutoRead(true);
                }
                throw e;
            }
        }
    }

    private final class SpliceFdTask extends SpliceInTask {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final FileDescriptor fd;
        private final ChannelPromise promise;
        private int offset;

        SpliceFdTask(FileDescriptor fileDescriptor, int i, int i2, ChannelPromise channelPromise) {
            super(i2, channelPromise);
            this.fd = fileDescriptor;
            this.promise = channelPromise;
            this.offset = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.SpliceInTask
        public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
            if (this.len == 0) {
                this.promise.setSuccess();
                return true;
            }
            try {
                FileDescriptor[] fileDescriptorArrPipe = FileDescriptor.pipe();
                FileDescriptor fileDescriptor = fileDescriptorArrPipe[0];
                FileDescriptor fileDescriptor2 = fileDescriptorArrPipe[1];
                try {
                    int iSpliceIn = spliceIn(fileDescriptor2, handle);
                    if (iSpliceIn > 0) {
                        if (this.len != Integer.MAX_VALUE) {
                            this.len -= iSpliceIn;
                        }
                        do {
                            int iSplice = Native.splice(fileDescriptor.intValue(), -1L, this.fd.intValue(), this.offset, iSpliceIn);
                            this.offset += iSplice;
                            iSpliceIn -= iSplice;
                        } while (iSpliceIn > 0);
                        if (this.len == 0) {
                            this.promise.setSuccess();
                            return true;
                        }
                    }
                    return false;
                } finally {
                    AbstractEpollStreamChannel.safeClosePipe(fileDescriptor);
                    AbstractEpollStreamChannel.safeClosePipe(fileDescriptor2);
                }
            } catch (Throwable th) {
                this.promise.setFailure(th);
                return true;
            }
        }
    }

    private final class EpollSocketWritableByteChannel extends SocketWritableByteChannel {
        EpollSocketWritableByteChannel() {
            super(AbstractEpollStreamChannel.this.socket);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.unix.SocketWritableByteChannel
        protected ByteBufAllocator alloc() {
            return AbstractEpollStreamChannel.this.alloc();
        }
    }
}
