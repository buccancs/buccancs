package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;

/* loaded from: classes3.dex */
public interface SocketChannelConfig extends ChannelConfig {
    int getReceiveBufferSize();

    SocketChannelConfig setReceiveBufferSize(int i);

    int getSendBufferSize();

    SocketChannelConfig setSendBufferSize(int i);

    int getSoLinger();

    SocketChannelConfig setSoLinger(int i);

    int getTrafficClass();

    SocketChannelConfig setTrafficClass(int i);

    boolean isAllowHalfClosure();

    SocketChannelConfig setAllowHalfClosure(boolean z);

    boolean isKeepAlive();

    SocketChannelConfig setKeepAlive(boolean z);

    boolean isReuseAddress();

    SocketChannelConfig setReuseAddress(boolean z);

    boolean isTcpNoDelay();

    SocketChannelConfig setTcpNoDelay(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAutoClose(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setAutoRead(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    SocketChannelConfig setMaxMessagesPerRead(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    SocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    SocketChannelConfig setWriteSpinCount(int i);
}
