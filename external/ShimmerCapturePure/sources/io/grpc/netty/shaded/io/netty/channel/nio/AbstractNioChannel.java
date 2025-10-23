package io.grpc.netty.shaded.io.netty.channel.nio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ConnectTimeoutException;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public abstract class AbstractNioChannel extends AbstractChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractNioChannel.class);
    protected final int readInterestOp;
    private final SelectableChannel ch;
    private final Runnable clearReadPendingRunnable;
    boolean readPending;
    volatile SelectionKey selectionKey;
    private ChannelPromise connectPromise;
    private ScheduledFuture<?> connectTimeoutFuture;
    private SocketAddress requestedRemoteAddress;

    protected AbstractNioChannel(Channel channel, SelectableChannel selectableChannel, int i) throws IOException {
        super(channel);
        this.clearReadPendingRunnable = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractNioChannel.this.clearReadPending0();
            }
        };
        this.ch = selectableChannel;
        this.readInterestOp = i;
        try {
            selectableChannel.configureBlocking(false);
        } catch (IOException e) {
            try {
                selectableChannel.close();
            } catch (IOException e2) {
                logger.warn("Failed to close a partially initialized socket.", (Throwable) e2);
            }
            throw new ChannelException("Failed to enter non-blocking mode.", e);
        }
    }

    protected abstract boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception;

    protected abstract void doFinishConnect() throws Exception;

    @Deprecated
    protected boolean isReadPending() {
        return this.readPending;
    }

    @Deprecated
    protected void setReadPending(final boolean z) {
        if (!isRegistered()) {
            this.readPending = z;
            return;
        }
        NioEventLoop nioEventLoopEventLoop = eventLoop();
        if (nioEventLoopEventLoop.inEventLoop()) {
            setReadPending0(z);
        } else {
            nioEventLoopEventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractNioChannel.this.setReadPending0(z);
                }
            });
        }
    }

    protected SelectableChannel javaChannel() {
        return this.ch;
    }

    protected SelectionKey selectionKey() {
        return this.selectionKey;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return this.ch.isOpen();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public NioUnsafe unsafe() {
        return (NioUnsafe) super.unsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public NioEventLoop eventLoop() {
        return (NioEventLoop) super.eventLoop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearReadPending() {
        if (!isRegistered()) {
            this.readPending = false;
            return;
        }
        NioEventLoop nioEventLoopEventLoop = eventLoop();
        if (nioEventLoopEventLoop.inEventLoop()) {
            clearReadPending0();
        } else {
            nioEventLoopEventLoop.execute(this.clearReadPendingRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadPending0(boolean z) {
        this.readPending = z;
        if (z) {
            return;
        }
        ((AbstractNioUnsafe) unsafe()).removeReadOp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadPending0() {
        this.readPending = false;
        ((AbstractNioUnsafe) unsafe()).removeReadOp();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof NioEventLoop;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        boolean z = false;
        while (true) {
            try {
                this.selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this);
                return;
            } catch (CancelledKeyException e) {
                if (!z) {
                    eventLoop().selectNow();
                    z = true;
                } else {
                    throw e;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doDeregister() throws Exception {
        eventLoop().cancel(selectionKey());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
        SelectionKey selectionKey = this.selectionKey;
        if (selectionKey.isValid()) {
            this.readPending = true;
            int iInterestOps = selectionKey.interestOps();
            int i = this.readInterestOp;
            if ((iInterestOps & i) == 0) {
                selectionKey.interestOps(iInterestOps | i);
            }
        }
    }

    protected final ByteBuf newDirectBuffer(ByteBuf byteBuf) {
        int i = byteBuf.readableBytes();
        if (i == 0) {
            ReferenceCountUtil.safeRelease(byteBuf);
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBufAllocator byteBufAllocatorAlloc = alloc();
        if (byteBufAllocatorAlloc.isDirectBufferPooled()) {
            ByteBuf byteBufDirectBuffer = byteBufAllocatorAlloc.directBuffer(i);
            byteBufDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), i);
            ReferenceCountUtil.safeRelease(byteBuf);
            return byteBufDirectBuffer;
        }
        ByteBuf byteBufThreadLocalDirectBuffer = ByteBufUtil.threadLocalDirectBuffer();
        if (byteBufThreadLocalDirectBuffer == null) {
            return byteBuf;
        }
        byteBufThreadLocalDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), i);
        ReferenceCountUtil.safeRelease(byteBuf);
        return byteBufThreadLocalDirectBuffer;
    }

    protected final ByteBuf newDirectBuffer(ReferenceCounted referenceCounted, ByteBuf byteBuf) {
        int i = byteBuf.readableBytes();
        if (i == 0) {
            ReferenceCountUtil.safeRelease(referenceCounted);
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBufAllocator byteBufAllocatorAlloc = alloc();
        if (byteBufAllocatorAlloc.isDirectBufferPooled()) {
            ByteBuf byteBufDirectBuffer = byteBufAllocatorAlloc.directBuffer(i);
            byteBufDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), i);
            ReferenceCountUtil.safeRelease(referenceCounted);
            return byteBufDirectBuffer;
        }
        ByteBuf byteBufThreadLocalDirectBuffer = ByteBufUtil.threadLocalDirectBuffer();
        if (byteBufThreadLocalDirectBuffer != null) {
            byteBufThreadLocalDirectBuffer.writeBytes(byteBuf, byteBuf.readerIndex(), i);
            ReferenceCountUtil.safeRelease(referenceCounted);
            return byteBufThreadLocalDirectBuffer;
        }
        if (referenceCounted != byteBuf) {
            byteBuf.retain();
            ReferenceCountUtil.safeRelease(referenceCounted);
        }
        return byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        ChannelPromise channelPromise = this.connectPromise;
        if (channelPromise != null) {
            channelPromise.tryFailure(new ClosedChannelException());
            this.connectPromise = null;
        }
        ScheduledFuture<?> scheduledFuture = this.connectTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.connectTimeoutFuture = null;
        }
    }

    public interface NioUnsafe extends Channel.Unsafe {
        SelectableChannel ch();

        void finishConnect();

        void forceFlush();

        void read();
    }

    protected abstract class AbstractNioUnsafe extends AbstractChannel.AbstractUnsafe implements NioUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        protected AbstractNioUnsafe() {
            super();
        }

        protected final void removeReadOp() {
            SelectionKey selectionKey = AbstractNioChannel.this.selectionKey();
            if (selectionKey.isValid()) {
                int iInterestOps = selectionKey.interestOps();
                if ((AbstractNioChannel.this.readInterestOp & iInterestOps) != 0) {
                    selectionKey.interestOps(iInterestOps & (~AbstractNioChannel.this.readInterestOp));
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        public final SelectableChannel ch() {
            return AbstractNioChannel.this.javaChannel();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public final void connect(final SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                try {
                    if (AbstractNioChannel.this.connectPromise != null) {
                        throw new ConnectionPendingException();
                    }
                    boolean zIsActive = AbstractNioChannel.this.isActive();
                    if (!AbstractNioChannel.this.doConnect(socketAddress, socketAddress2)) {
                        AbstractNioChannel.this.connectPromise = channelPromise;
                        AbstractNioChannel.this.requestedRemoteAddress = socketAddress;
                        int connectTimeoutMillis = AbstractNioChannel.this.config().getConnectTimeoutMillis();
                        if (connectTimeoutMillis > 0) {
                            AbstractNioChannel abstractNioChannel = AbstractNioChannel.this;
                            abstractNioChannel.connectTimeoutFuture = abstractNioChannel.eventLoop().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.AbstractNioUnsafe.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ChannelPromise channelPromise2 = AbstractNioChannel.this.connectPromise;
                                    ConnectTimeoutException connectTimeoutException = new ConnectTimeoutException("connection timed out: " + socketAddress);
                                    if (channelPromise2 == null || !channelPromise2.tryFailure(connectTimeoutException)) {
                                        return;
                                    }
                                    AbstractNioUnsafe abstractNioUnsafe = AbstractNioUnsafe.this;
                                    abstractNioUnsafe.close(abstractNioUnsafe.voidPromise());
                                }
                            }, connectTimeoutMillis, TimeUnit.MILLISECONDS);
                        }
                        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.AbstractNioUnsafe.2
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (channelFuture.isCancelled()) {
                                    if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                                        AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
                                    }
                                    AbstractNioChannel.this.connectPromise = null;
                                    AbstractNioUnsafe abstractNioUnsafe = AbstractNioUnsafe.this;
                                    abstractNioUnsafe.close(abstractNioUnsafe.voidPromise());
                                }
                            }
                        });
                        return;
                    }
                    fulfillConnectPromise(channelPromise, zIsActive);
                } catch (Throwable th) {
                    channelPromise.tryFailure(annotateConnectException(th, socketAddress));
                    closeIfClosed();
                }
            }
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, boolean z) {
            if (channelPromise == null) {
                return;
            }
            boolean zIsActive = AbstractNioChannel.this.isActive();
            boolean zTrySuccess = channelPromise.trySuccess();
            if (!z && zIsActive) {
                AbstractNioChannel.this.pipeline().fireChannelActive();
            }
            if (zTrySuccess) {
                return;
            }
            close(voidPromise());
        }

        private void fulfillConnectPromise(ChannelPromise channelPromise, Throwable th) {
            if (channelPromise == null) {
                return;
            }
            channelPromise.tryFailure(th);
            closeIfClosed();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        public final void finishConnect() {
            try {
                boolean zIsActive = AbstractNioChannel.this.isActive();
                AbstractNioChannel.this.doFinishConnect();
                fulfillConnectPromise(AbstractNioChannel.this.connectPromise, zIsActive);
            } catch (Throwable th) {
                try {
                    fulfillConnectPromise(AbstractNioChannel.this.connectPromise, annotateConnectException(th, AbstractNioChannel.this.requestedRemoteAddress));
                    if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                    }
                } finally {
                    if (AbstractNioChannel.this.connectTimeoutFuture != null) {
                        AbstractNioChannel.this.connectTimeoutFuture.cancel(false);
                    }
                    AbstractNioChannel.this.connectPromise = null;
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe
        protected final void flush0() {
            if (isFlushPending()) {
                return;
            }
            super.flush0();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        public final void forceFlush() {
            super.flush0();
        }

        private boolean isFlushPending() {
            SelectionKey selectionKey = AbstractNioChannel.this.selectionKey();
            return selectionKey.isValid() && (selectionKey.interestOps() & 4) != 0;
        }
    }
}
