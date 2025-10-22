package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;

import java.net.InetAddress;
import java.net.NetworkInterface;

/* loaded from: classes3.dex */
public interface DatagramChannelConfig extends ChannelConfig {
    InetAddress getInterface();

    DatagramChannelConfig setInterface(InetAddress inetAddress);

    NetworkInterface getNetworkInterface();

    DatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface);

    int getReceiveBufferSize();

    DatagramChannelConfig setReceiveBufferSize(int i);

    int getSendBufferSize();

    DatagramChannelConfig setSendBufferSize(int i);

    int getTimeToLive();

    DatagramChannelConfig setTimeToLive(int i);

    int getTrafficClass();

    DatagramChannelConfig setTrafficClass(int i);

    boolean isBroadcast();

    DatagramChannelConfig setBroadcast(boolean z);

    boolean isLoopbackModeDisabled();

    DatagramChannelConfig setLoopbackModeDisabled(boolean z);

    boolean isReuseAddress();

    DatagramChannelConfig setReuseAddress(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAutoClose(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setAutoRead(boolean z);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setConnectTimeoutMillis(int i);

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    DatagramChannelConfig setMaxMessagesPerRead(int i);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    @Override
        // io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    DatagramChannelConfig setWriteSpinCount(int i);
}
