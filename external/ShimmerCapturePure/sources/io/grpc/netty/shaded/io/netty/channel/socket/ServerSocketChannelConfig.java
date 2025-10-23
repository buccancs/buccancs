package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;

/* loaded from: classes3.dex */
public interface ServerSocketChannelConfig extends ChannelConfig {
    int getBacklog();

    ServerSocketChannelConfig setBacklog(int i);

    int getReceiveBufferSize();

    ServerSocketChannelConfig setReceiveBufferSize(int i);

    boolean isReuseAddress();

    ServerSocketChannelConfig setReuseAddress(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setAutoRead(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    ServerSocketChannelConfig setMaxMessagesPerRead(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    ServerSocketChannelConfig setPerformancePreferences(int i, int i2, int i3);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferHighWaterMark(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferLowWaterMark(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    ServerSocketChannelConfig setWriteSpinCount(int i);
}
