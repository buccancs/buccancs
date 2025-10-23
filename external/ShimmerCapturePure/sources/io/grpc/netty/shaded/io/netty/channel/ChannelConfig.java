package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;

import java.util.Map;

/* loaded from: classes3.dex */
public interface ChannelConfig {
    ByteBufAllocator getAllocator();

    ChannelConfig setAllocator(ByteBufAllocator byteBufAllocator);

    int getConnectTimeoutMillis();

    ChannelConfig setConnectTimeoutMillis(int i);

    @Deprecated
    int getMaxMessagesPerRead();

    @Deprecated
    ChannelConfig setMaxMessagesPerRead(int i);

    MessageSizeEstimator getMessageSizeEstimator();

    ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator);

    <T> T getOption(ChannelOption<T> channelOption);

    Map<ChannelOption<?>, Object> getOptions();

    <T extends RecvByteBufAllocator> T getRecvByteBufAllocator();

    ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator);

    int getWriteBufferHighWaterMark();

    ChannelConfig setWriteBufferHighWaterMark(int i);

    int getWriteBufferLowWaterMark();

    ChannelConfig setWriteBufferLowWaterMark(int i);

    WriteBufferWaterMark getWriteBufferWaterMark();

    ChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark);

    int getWriteSpinCount();

    ChannelConfig setWriteSpinCount(int i);

    boolean isAutoClose();

    ChannelConfig setAutoClose(boolean z);

    boolean isAutoRead();

    ChannelConfig setAutoRead(boolean z);

    <T> boolean setOption(ChannelOption<T> channelOption, T t);

    boolean setOptions(Map<ChannelOption<?>, ?> map);
}
