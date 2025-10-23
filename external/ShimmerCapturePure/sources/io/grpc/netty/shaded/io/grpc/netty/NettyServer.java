package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalWithLogId;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFactory;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class NettyServer implements InternalServer, InternalWithLogId {
    private static final Logger log = Logger.getLogger(InternalServer.class.getName());
    private final SocketAddress address;
    private final boolean autoFlowControl;
    private final ObjectPool<? extends EventLoopGroup> bossGroupPool;
    private final ChannelFactory<? extends ServerChannel> channelFactory;
    private final Map<ChannelOption<?>, ?> channelOptions;
    private final InternalChannelz channelz;
    private final Map<ChannelOption<?>, ?> childChannelOptions;
    private final int flowControlWindow;
    private final boolean forceHeapBuffer;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private final InternalLogId logId;
    private final long maxConnectionAgeGraceInNanos;
    private final long maxConnectionAgeInNanos;
    private final long maxConnectionIdleInNanos;
    private final int maxHeaderListSize;
    private final int maxMessageSize;
    private final int maxStreamsPerConnection;
    private final long permitKeepAliveTimeInNanos;
    private final boolean permitKeepAliveWithoutCalls;
    private final ProtocolNegotiator protocolNegotiator;
    private final ReferenceCounted sharedResourceReferenceCounter = new SharedResourceReferenceCounter();
    private final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
    private final TransportTracer.Factory transportTracerFactory;
    private final ObjectPool<? extends EventLoopGroup> workerGroupPool;
    private EventLoopGroup bossGroup;
    private Channel channel;
    private volatile InternalInstrumented<InternalChannelz.SocketStats> listenSocketStats;
    private ServerListener listener;
    private EventLoopGroup workerGroup;

    NettyServer(SocketAddress socketAddress, ChannelFactory<? extends ServerChannel> channelFactory, Map<ChannelOption<?>, ?> map, Map<ChannelOption<?>, ?> map2, ObjectPool<? extends EventLoopGroup> objectPool, ObjectPool<? extends EventLoopGroup> objectPool2, boolean z, ProtocolNegotiator protocolNegotiator, List<? extends ServerStreamTracer.Factory> list, TransportTracer.Factory factory, int i, boolean z2, int i2, int i3, int i4, long j, long j2, long j3, long j4, long j5, boolean z3, long j6, InternalChannelz internalChannelz) {
        this.address = socketAddress;
        this.channelFactory = (ChannelFactory) Preconditions.checkNotNull(channelFactory, "channelFactory");
        Preconditions.checkNotNull(map, "channelOptions");
        this.channelOptions = new HashMap(map);
        Preconditions.checkNotNull(map2, "childChannelOptions");
        this.childChannelOptions = new HashMap(map2);
        this.bossGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool, "bossGroupPool");
        this.workerGroupPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "workerGroupPool");
        this.forceHeapBuffer = z;
        this.bossGroup = objectPool.getObject();
        this.workerGroup = objectPool2.getObject();
        this.protocolNegotiator = (ProtocolNegotiator) Preconditions.checkNotNull(protocolNegotiator, "protocolNegotiator");
        this.streamTracerFactories = (List) Preconditions.checkNotNull(list, "streamTracerFactories");
        this.transportTracerFactory = factory;
        this.maxStreamsPerConnection = i;
        this.autoFlowControl = z2;
        this.flowControlWindow = i2;
        this.maxMessageSize = i3;
        this.maxHeaderListSize = i4;
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        this.maxConnectionIdleInNanos = j3;
        this.maxConnectionAgeInNanos = j4;
        this.maxConnectionAgeGraceInNanos = j5;
        this.permitKeepAliveWithoutCalls = z3;
        this.permitKeepAliveTimeInNanos = j6;
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(internalChannelz);
        this.logId = InternalLogId.allocate(getClass(), socketAddress != null ? socketAddress.toString() : "No address");
    }

    @Override // io.grpc.internal.InternalServer
    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        return this.listenSocketStats;
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.InternalServer
    public SocketAddress getListenSocketAddress() {
        Channel channel = this.channel;
        return channel == null ? this.address : channel.localAddress();
    }

    @Override // io.grpc.internal.InternalServer
    public void start(ServerListener serverListener) throws IOException {
        this.listener = (ServerListener) Preconditions.checkNotNull(serverListener, "serverListener");
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.option(ChannelOption.ALLOCATOR, Utils.getByteBufAllocator(this.forceHeapBuffer));
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, Utils.getByteBufAllocator(this.forceHeapBuffer));
        serverBootstrap.group(this.bossGroup, this.workerGroup);
        serverBootstrap.channelFactory((ChannelFactory) this.channelFactory);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        Map<ChannelOption<?>, ?> map = this.channelOptions;
        if (map != null) {
            for (Map.Entry<ChannelOption<?>, ?> entry : map.entrySet()) {
                serverBootstrap.option(entry.getKey(), entry.getValue());
            }
        }
        Map<ChannelOption<?>, ?> map2 = this.childChannelOptions;
        if (map2 != null) {
            for (Map.Entry<ChannelOption<?>, ?> entry2 : map2.entrySet()) {
                serverBootstrap.childOption(entry2.getKey(), entry2.getValue());
            }
        }
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.1
            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInitializer
            public void initChannel(Channel channel) {
                ChannelPromise channelPromiseNewPromise = channel.newPromise();
                long jRandom = NettyServer.this.maxConnectionAgeInNanos;
                if (jRandom != Long.MAX_VALUE) {
                    jRandom = (long) (((Math.random() * 0.2d) + 0.9d) * jRandom);
                }
                NettyServerTransport nettyServerTransport = new NettyServerTransport(channel, channelPromiseNewPromise, NettyServer.this.protocolNegotiator, NettyServer.this.streamTracerFactories, NettyServer.this.transportTracerFactory.create(), NettyServer.this.maxStreamsPerConnection, NettyServer.this.autoFlowControl, NettyServer.this.flowControlWindow, NettyServer.this.maxMessageSize, NettyServer.this.maxHeaderListSize, NettyServer.this.keepAliveTimeInNanos, NettyServer.this.keepAliveTimeoutInNanos, NettyServer.this.maxConnectionIdleInNanos, jRandom, NettyServer.this.maxConnectionAgeGraceInNanos, NettyServer.this.permitKeepAliveWithoutCalls, NettyServer.this.permitKeepAliveTimeInNanos);
                synchronized (NettyServer.this) {
                    if (NettyServer.this.channel == null || NettyServer.this.channel.isOpen()) {
                        NettyServer.this.sharedResourceReferenceCounter.retain();
                        nettyServerTransport.start(NettyServer.this.listener.transportCreated(nettyServerTransport));
                        ChannelFutureListener channelFutureListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.1.1LoopReleaser
                            private boolean done;

                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (this.done) {
                                    return;
                                }
                                this.done = true;
                                NettyServer.this.sharedResourceReferenceCounter.release();
                            }
                        };
                        channelPromiseNewPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
                        channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
                        return;
                    }
                    channel.close();
                }
            }
        });
        ChannelFuture channelFutureBind = serverBootstrap.bind(this.address);
        channelFutureBind.awaitUninterruptibly();
        if (!channelFutureBind.isSuccess()) {
            throw new IOException("Failed to bind", channelFutureBind.cause());
        }
        Channel channel = channelFutureBind.channel();
        this.channel = channel;
        channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.2
            @Override // java.lang.Runnable
            public void run() {
                NettyServer nettyServer = NettyServer.this;
                nettyServer.listenSocketStats = new ListenSocket(nettyServer.channel);
                NettyServer.this.channelz.addListenSocket(NettyServer.this.listenSocketStats);
            }
        });
    }

    @Override // io.grpc.internal.InternalServer
    public void shutdown() {
        Channel channel = this.channel;
        if (channel == null || !channel.isOpen()) {
            return;
        }
        this.channel.close().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.3
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    NettyServer.log.log(Level.WARNING, "Error shutting down server", channelFuture.cause());
                }
                InternalInstrumented<InternalChannelz.SocketStats> internalInstrumented = NettyServer.this.listenSocketStats;
                NettyServer.this.listenSocketStats = null;
                if (internalInstrumented != null) {
                    NettyServer.this.channelz.removeListenSocket(internalInstrumented);
                }
                NettyServer.this.sharedResourceReferenceCounter.release();
                NettyServer.this.protocolNegotiator.close();
                synchronized (NettyServer.this) {
                    NettyServer.this.listener.serverShutdown();
                }
            }
        });
        try {
            this.channel.closeFuture().await();
        } catch (InterruptedException e) {
            log.log(Level.FINE, "Interrupted while shutting down", (Throwable) e);
            Thread.currentThread().interrupt();
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    private static final class ListenSocket implements InternalInstrumented<InternalChannelz.SocketStats> {
        private final Channel ch;
        private final InternalLogId id;

        ListenSocket(Channel channel) {
            this.ch = channel;
            this.id = InternalLogId.allocate(getClass(), String.valueOf(channel.localAddress()));
        }

        @Override // io.grpc.InternalWithLogId
        public InternalLogId getLogId() {
            return this.id;
        }

        @Override // io.grpc.InternalInstrumented
        public ListenableFuture<InternalChannelz.SocketStats> getStats() {
            final SettableFuture settableFutureCreate = SettableFuture.create();
            if (this.ch.eventLoop().inEventLoop()) {
                settableFutureCreate.set(new InternalChannelz.SocketStats(null, this.ch.localAddress(), null, Utils.getSocketOptions(this.ch), null));
                return settableFutureCreate;
            }
            this.ch.eventLoop().submit(new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.ListenSocket.2
                @Override // java.lang.Runnable
                public void run() {
                    settableFutureCreate.set(new InternalChannelz.SocketStats(null, ListenSocket.this.ch.localAddress(), null, Utils.getSocketOptions(ListenSocket.this.ch), null));
                }
            }).addListener(new GenericFutureListener<Future<Object>>() { // from class: io.grpc.netty.shaded.io.grpc.netty.NettyServer.ListenSocket.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<Object> future) throws Exception {
                    if (future.isSuccess()) {
                        return;
                    }
                    settableFutureCreate.setException(future.cause());
                }
            });
            return settableFutureCreate;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("logId", this.id.getId()).add("channel", this.ch).toString();
        }
    }

    class SharedResourceReferenceCounter extends AbstractReferenceCounted {
        SharedResourceReferenceCounter() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public ReferenceCounted touch(Object obj) {
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted
        protected void deallocate() {
            try {
                if (NettyServer.this.bossGroup != null) {
                    NettyServer.this.bossGroupPool.returnObject(NettyServer.this.bossGroup);
                }
                NettyServer.this.bossGroup = null;
                try {
                    if (NettyServer.this.workerGroup != null) {
                        NettyServer.this.workerGroupPool.returnObject(NettyServer.this.workerGroup);
                    }
                } finally {
                }
            } catch (Throwable th) {
                NettyServer.this.bossGroup = null;
                try {
                    if (NettyServer.this.workerGroup != null) {
                        NettyServer.this.workerGroupPool.returnObject(NettyServer.this.workerGroup);
                    }
                    throw th;
                } finally {
                }
            }
        }
    }
}
