package io.grpc.netty.shaded.io.netty.handler.timeout;

import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class IdleStateHandler extends ChannelDuplexHandler {
    private static final long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1);
    private final long allIdleTimeNanos;
    private final boolean observeOutput;
    private final long readerIdleTimeNanos;
    private final ChannelFutureListener writeListener;
    private final long writerIdleTimeNanos;
    private ScheduledFuture<?> allIdleTimeout;
    private boolean firstAllIdleEvent;
    private boolean firstReaderIdleEvent;
    private boolean firstWriterIdleEvent;
    private long lastChangeCheckTimeStamp;
    private long lastFlushProgress;
    private int lastMessageHashCode;
    private long lastPendingWriteBytes;
    private long lastReadTime;
    private long lastWriteTime;
    private ScheduledFuture<?> readerIdleTimeout;
    private boolean reading;
    private byte state;
    private ScheduledFuture<?> writerIdleTimeout;

    public IdleStateHandler(int i, int i2, int i3) {
        this(i, i2, i3, TimeUnit.SECONDS);
    }

    public IdleStateHandler(long j, long j2, long j3, TimeUnit timeUnit) {
        this(false, j, j2, j3, timeUnit);
    }

    public IdleStateHandler(boolean z, long j, long j2, long j3, TimeUnit timeUnit) {
        this.writeListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                IdleStateHandler idleStateHandler = IdleStateHandler.this;
                idleStateHandler.lastWriteTime = idleStateHandler.ticksInNanos();
                IdleStateHandler idleStateHandler2 = IdleStateHandler.this;
                idleStateHandler2.firstWriterIdleEvent = idleStateHandler2.firstAllIdleEvent = true;
            }
        };
        this.firstReaderIdleEvent = true;
        this.firstWriterIdleEvent = true;
        this.firstAllIdleEvent = true;
        ObjectUtil.checkNotNull(timeUnit, "unit");
        this.observeOutput = z;
        if (j <= 0) {
            this.readerIdleTimeNanos = 0L;
        } else {
            this.readerIdleTimeNanos = Math.max(timeUnit.toNanos(j), MIN_TIMEOUT_NANOS);
        }
        if (j2 <= 0) {
            this.writerIdleTimeNanos = 0L;
        } else {
            this.writerIdleTimeNanos = Math.max(timeUnit.toNanos(j2), MIN_TIMEOUT_NANOS);
        }
        if (j3 <= 0) {
            this.allIdleTimeNanos = 0L;
        } else {
            this.allIdleTimeNanos = Math.max(timeUnit.toNanos(j3), MIN_TIMEOUT_NANOS);
        }
    }

    public long getReaderIdleTimeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(this.readerIdleTimeNanos);
    }

    public long getWriterIdleTimeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(this.writerIdleTimeNanos);
    }

    public long getAllIdleTimeInMillis() {
        return TimeUnit.NANOSECONDS.toMillis(this.allIdleTimeNanos);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isActive() && channelHandlerContext.channel().isRegistered()) {
            initialize(channelHandlerContext);
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroy();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isActive()) {
            initialize(channelHandlerContext);
        }
        super.channelRegistered(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        initialize(channelHandlerContext);
        super.channelActive(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroy();
        super.channelInactive(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.readerIdleTimeNanos > 0 || this.allIdleTimeNanos > 0) {
            this.reading = true;
            this.firstAllIdleEvent = true;
            this.firstReaderIdleEvent = true;
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if ((this.readerIdleTimeNanos > 0 || this.allIdleTimeNanos > 0) && this.reading) {
            this.lastReadTime = ticksInNanos();
            this.reading = false;
        }
        channelHandlerContext.fireChannelReadComplete();
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (this.writerIdleTimeNanos > 0 || this.allIdleTimeNanos > 0) {
            channelHandlerContext.write(obj, channelPromise.unvoid()).addListener((GenericFutureListener<? extends Future<? super Void>>) this.writeListener);
        } else {
            channelHandlerContext.write(obj, channelPromise);
        }
    }

    private void initialize(ChannelHandlerContext channelHandlerContext) {
        byte b = this.state;
        if (b == 1 || b == 2) {
            return;
        }
        this.state = (byte) 1;
        initOutputChanged(channelHandlerContext);
        long jTicksInNanos = ticksInNanos();
        this.lastWriteTime = jTicksInNanos;
        this.lastReadTime = jTicksInNanos;
        if (this.readerIdleTimeNanos > 0) {
            this.readerIdleTimeout = schedule(channelHandlerContext, new ReaderIdleTimeoutTask(channelHandlerContext), this.readerIdleTimeNanos, TimeUnit.NANOSECONDS);
        }
        if (this.writerIdleTimeNanos > 0) {
            this.writerIdleTimeout = schedule(channelHandlerContext, new WriterIdleTimeoutTask(channelHandlerContext), this.writerIdleTimeNanos, TimeUnit.NANOSECONDS);
        }
        if (this.allIdleTimeNanos > 0) {
            this.allIdleTimeout = schedule(channelHandlerContext, new AllIdleTimeoutTask(channelHandlerContext), this.allIdleTimeNanos, TimeUnit.NANOSECONDS);
        }
    }

    long ticksInNanos() {
        return System.nanoTime();
    }

    ScheduledFuture<?> schedule(ChannelHandlerContext channelHandlerContext, Runnable runnable, long j, TimeUnit timeUnit) {
        return channelHandlerContext.executor().schedule(runnable, j, timeUnit);
    }

    private void destroy() {
        this.state = (byte) 2;
        ScheduledFuture<?> scheduledFuture = this.readerIdleTimeout;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.readerIdleTimeout = null;
        }
        ScheduledFuture<?> scheduledFuture2 = this.writerIdleTimeout;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
            this.writerIdleTimeout = null;
        }
        ScheduledFuture<?> scheduledFuture3 = this.allIdleTimeout;
        if (scheduledFuture3 != null) {
            scheduledFuture3.cancel(false);
            this.allIdleTimeout = null;
        }
    }

    protected void channelIdle(ChannelHandlerContext channelHandlerContext, IdleStateEvent idleStateEvent) throws Exception {
        channelHandlerContext.fireUserEventTriggered((Object) idleStateEvent);
    }

    protected IdleStateEvent newIdleStateEvent(IdleState idleState, boolean z) {
        int i = AnonymousClass2.$SwitchMap$io$netty$handler$timeout$IdleState[idleState.ordinal()];
        if (i == 1) {
            return z ? IdleStateEvent.FIRST_ALL_IDLE_STATE_EVENT : IdleStateEvent.ALL_IDLE_STATE_EVENT;
        }
        if (i == 2) {
            return z ? IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT : IdleStateEvent.READER_IDLE_STATE_EVENT;
        }
        if (i == 3) {
            return z ? IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT : IdleStateEvent.WRITER_IDLE_STATE_EVENT;
        }
        throw new IllegalArgumentException("Unhandled: state=" + idleState + ", first=" + z);
    }

    private void initOutputChanged(ChannelHandlerContext channelHandlerContext) {
        ChannelOutboundBuffer channelOutboundBufferOutboundBuffer;
        if (!this.observeOutput || (channelOutboundBufferOutboundBuffer = channelHandlerContext.channel().unsafe().outboundBuffer()) == null) {
            return;
        }
        this.lastMessageHashCode = System.identityHashCode(channelOutboundBufferOutboundBuffer.current());
        this.lastPendingWriteBytes = channelOutboundBufferOutboundBuffer.totalPendingWriteBytes();
        this.lastFlushProgress = channelOutboundBufferOutboundBuffer.currentProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasOutputChanged(ChannelHandlerContext channelHandlerContext, boolean z) {
        if (!this.observeOutput) {
            return false;
        }
        long j = this.lastChangeCheckTimeStamp;
        long j2 = this.lastWriteTime;
        if (j != j2) {
            this.lastChangeCheckTimeStamp = j2;
            if (!z) {
                return true;
            }
        }
        ChannelOutboundBuffer channelOutboundBufferOutboundBuffer = channelHandlerContext.channel().unsafe().outboundBuffer();
        if (channelOutboundBufferOutboundBuffer == null) {
            return false;
        }
        int iIdentityHashCode = System.identityHashCode(channelOutboundBufferOutboundBuffer.current());
        long j3 = channelOutboundBufferOutboundBuffer.totalPendingWriteBytes();
        if (iIdentityHashCode != this.lastMessageHashCode || j3 != this.lastPendingWriteBytes) {
            this.lastMessageHashCode = iIdentityHashCode;
            this.lastPendingWriteBytes = j3;
            if (!z) {
                return true;
            }
        }
        long jCurrentProgress = channelOutboundBufferOutboundBuffer.currentProgress();
        if (jCurrentProgress == this.lastFlushProgress) {
            return false;
        }
        this.lastFlushProgress = jCurrentProgress;
        return !z;
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$timeout$IdleState;

        static {
            int[] iArr = new int[IdleState.values().length];
            $SwitchMap$io$netty$handler$timeout$IdleState = iArr;
            try {
                iArr[IdleState.ALL_IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$timeout$IdleState[IdleState.READER_IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$timeout$IdleState[IdleState.WRITER_IDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static abstract class AbstractIdleTask implements Runnable {
        private final ChannelHandlerContext ctx;

        AbstractIdleTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        protected abstract void run(ChannelHandlerContext channelHandlerContext);

        @Override // java.lang.Runnable
        public void run() {
            if (this.ctx.channel().isOpen()) {
                run(this.ctx);
            }
        }
    }

    private final class ReaderIdleTimeoutTask extends AbstractIdleTask {
        ReaderIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            super(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler.AbstractIdleTask
        protected void run(ChannelHandlerContext channelHandlerContext) {
            long jTicksInNanos = IdleStateHandler.this.readerIdleTimeNanos;
            if (!IdleStateHandler.this.reading) {
                jTicksInNanos -= IdleStateHandler.this.ticksInNanos() - IdleStateHandler.this.lastReadTime;
            }
            long j = jTicksInNanos;
            if (j <= 0) {
                IdleStateHandler idleStateHandler = IdleStateHandler.this;
                idleStateHandler.readerIdleTimeout = idleStateHandler.schedule(channelHandlerContext, this, idleStateHandler.readerIdleTimeNanos, TimeUnit.NANOSECONDS);
                boolean z = IdleStateHandler.this.firstReaderIdleEvent;
                IdleStateHandler.this.firstReaderIdleEvent = false;
                try {
                    IdleStateHandler.this.channelIdle(channelHandlerContext, IdleStateHandler.this.newIdleStateEvent(IdleState.READER_IDLE, z));
                    return;
                } catch (Throwable th) {
                    channelHandlerContext.fireExceptionCaught(th);
                    return;
                }
            }
            IdleStateHandler idleStateHandler2 = IdleStateHandler.this;
            idleStateHandler2.readerIdleTimeout = idleStateHandler2.schedule(channelHandlerContext, this, j, TimeUnit.NANOSECONDS);
        }
    }

    private final class WriterIdleTimeoutTask extends AbstractIdleTask {
        WriterIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            super(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler.AbstractIdleTask
        protected void run(ChannelHandlerContext channelHandlerContext) {
            long jTicksInNanos = IdleStateHandler.this.writerIdleTimeNanos - (IdleStateHandler.this.ticksInNanos() - IdleStateHandler.this.lastWriteTime);
            if (jTicksInNanos <= 0) {
                IdleStateHandler idleStateHandler = IdleStateHandler.this;
                idleStateHandler.writerIdleTimeout = idleStateHandler.schedule(channelHandlerContext, this, idleStateHandler.writerIdleTimeNanos, TimeUnit.NANOSECONDS);
                boolean z = IdleStateHandler.this.firstWriterIdleEvent;
                IdleStateHandler.this.firstWriterIdleEvent = false;
                try {
                    if (IdleStateHandler.this.hasOutputChanged(channelHandlerContext, z)) {
                        return;
                    }
                    IdleStateHandler.this.channelIdle(channelHandlerContext, IdleStateHandler.this.newIdleStateEvent(IdleState.WRITER_IDLE, z));
                    return;
                } catch (Throwable th) {
                    channelHandlerContext.fireExceptionCaught(th);
                    return;
                }
            }
            IdleStateHandler idleStateHandler2 = IdleStateHandler.this;
            idleStateHandler2.writerIdleTimeout = idleStateHandler2.schedule(channelHandlerContext, this, jTicksInNanos, TimeUnit.NANOSECONDS);
        }
    }

    private final class AllIdleTimeoutTask extends AbstractIdleTask {
        AllIdleTimeoutTask(ChannelHandlerContext channelHandlerContext) {
            super(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler.AbstractIdleTask
        protected void run(ChannelHandlerContext channelHandlerContext) {
            long jTicksInNanos = IdleStateHandler.this.allIdleTimeNanos;
            if (!IdleStateHandler.this.reading) {
                jTicksInNanos -= IdleStateHandler.this.ticksInNanos() - Math.max(IdleStateHandler.this.lastReadTime, IdleStateHandler.this.lastWriteTime);
            }
            long j = jTicksInNanos;
            if (j <= 0) {
                IdleStateHandler idleStateHandler = IdleStateHandler.this;
                idleStateHandler.allIdleTimeout = idleStateHandler.schedule(channelHandlerContext, this, idleStateHandler.allIdleTimeNanos, TimeUnit.NANOSECONDS);
                boolean z = IdleStateHandler.this.firstAllIdleEvent;
                IdleStateHandler.this.firstAllIdleEvent = false;
                try {
                    if (IdleStateHandler.this.hasOutputChanged(channelHandlerContext, z)) {
                        return;
                    }
                    IdleStateHandler.this.channelIdle(channelHandlerContext, IdleStateHandler.this.newIdleStateEvent(IdleState.ALL_IDLE, z));
                    return;
                } catch (Throwable th) {
                    channelHandlerContext.fireExceptionCaught(th);
                    return;
                }
            }
            IdleStateHandler idleStateHandler2 = IdleStateHandler.this;
            idleStateHandler2.allIdleTimeout = idleStateHandler2.schedule(channelHandlerContext, this, j, TimeUnit.NANOSECONDS);
        }
    }
}
