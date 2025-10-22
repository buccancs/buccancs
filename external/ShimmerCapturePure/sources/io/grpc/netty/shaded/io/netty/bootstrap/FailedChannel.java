package io.grpc.netty.shaded.io.netty.bootstrap;

import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;

import java.net.SocketAddress;

/* loaded from: classes3.dex */
final class FailedChannel extends AbstractChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private final ChannelConfig config;

    FailedChannel() {
        super(null);
        this.config = new DefaultChannelConfig(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new FailedChannelUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBeginRead() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) {
        throw new UnsupportedOperationException();
    }

    private final class FailedChannelUnsafe extends AbstractChannel.AbstractUnsafe {
        private FailedChannelUnsafe() {
            super();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        }
    }
}
