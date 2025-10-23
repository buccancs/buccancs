package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.nio.ByteOrder;
import java.util.Set;

/* loaded from: classes3.dex */
public class SpdyFrameEncoder {
    private final int version;

    public SpdyFrameEncoder(SpdyVersion spdyVersion) {
        this.version = ((SpdyVersion) ObjectUtil.checkNotNull(spdyVersion, "spdyVersion")).getVersion();
    }

    private void writeControlFrameHeader(ByteBuf byteBuf, int i, byte b, int i2) {
        byteBuf.writeShort(this.version | 32768);
        byteBuf.writeShort(i);
        byteBuf.writeByte(b);
        byteBuf.writeMedium(i2);
    }

    public ByteBuf encodeDataFrame(ByteBufAllocator byteBufAllocator, int i, boolean z, ByteBuf byteBuf) {
        int i2 = byteBuf.readableBytes();
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(i2 + 8).order(ByteOrder.BIG_ENDIAN);
        byteBufOrder.writeInt(i & Integer.MAX_VALUE);
        byteBufOrder.writeByte(z ? 1 : 0);
        byteBufOrder.writeMedium(i2);
        byteBufOrder.writeBytes(byteBuf, byteBuf.readerIndex(), i2);
        return byteBufOrder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ByteBuf encodeSynStreamFrame(ByteBufAllocator byteBufAllocator, int i, int i2, byte b, boolean z, boolean z2, ByteBuf byteBuf) {
        int i3 = byteBuf.readableBytes();
        byte b2 = z;
        if (z2) {
            b2 = (byte) (z | 2);
        }
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(i3 + 18).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 1, b2, i3 + 10);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeInt(i2);
        byteBufOrder.writeShort((b & 255) << 13);
        byteBufOrder.writeBytes(byteBuf, byteBuf.readerIndex(), i3);
        return byteBufOrder;
    }

    public ByteBuf encodeSynReplyFrame(ByteBufAllocator byteBufAllocator, int i, boolean z, ByteBuf byteBuf) {
        int i2 = byteBuf.readableBytes();
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(i2 + 12).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 2, z ? (byte) 1 : (byte) 0, i2 + 4);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeBytes(byteBuf, byteBuf.readerIndex(), i2);
        return byteBufOrder;
    }

    public ByteBuf encodeRstStreamFrame(ByteBufAllocator byteBufAllocator, int i, int i2) {
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(16).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 3, (byte) 0, 8);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeInt(i2);
        return byteBufOrder;
    }

    public ByteBuf encodeSettingsFrame(ByteBufAllocator byteBufAllocator, SpdySettingsFrame spdySettingsFrame) {
        Set<Integer> setIds = spdySettingsFrame.ids();
        int size = setIds.size();
        boolean zClearPreviouslyPersistedSettings = spdySettingsFrame.clearPreviouslyPersistedSettings();
        int i = size * 8;
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(i + 12).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 4, zClearPreviouslyPersistedSettings ? (byte) 1 : (byte) 0, i + 4);
        byteBufOrder.writeInt(size);
        for (Integer num : setIds) {
            byte b = spdySettingsFrame.isPersistValue(num.intValue()) ? (byte) 1 : (byte) 0;
            if (spdySettingsFrame.isPersisted(num.intValue())) {
                b = (byte) (b | 2);
            }
            byteBufOrder.writeByte(b);
            byteBufOrder.writeMedium(num.intValue());
            byteBufOrder.writeInt(spdySettingsFrame.getValue(num.intValue()));
        }
        return byteBufOrder;
    }

    public ByteBuf encodePingFrame(ByteBufAllocator byteBufAllocator, int i) {
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(12).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 6, (byte) 0, 4);
        byteBufOrder.writeInt(i);
        return byteBufOrder;
    }

    public ByteBuf encodeGoAwayFrame(ByteBufAllocator byteBufAllocator, int i, int i2) {
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(16).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 7, (byte) 0, 8);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeInt(i2);
        return byteBufOrder;
    }

    public ByteBuf encodeHeadersFrame(ByteBufAllocator byteBufAllocator, int i, boolean z, ByteBuf byteBuf) {
        int i2 = byteBuf.readableBytes();
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(i2 + 12).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 8, z ? (byte) 1 : (byte) 0, i2 + 4);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeBytes(byteBuf, byteBuf.readerIndex(), i2);
        return byteBufOrder;
    }

    public ByteBuf encodeWindowUpdateFrame(ByteBufAllocator byteBufAllocator, int i, int i2) {
        ByteBuf byteBufOrder = byteBufAllocator.ioBuffer(16).order(ByteOrder.BIG_ENDIAN);
        writeControlFrameHeader(byteBufOrder, 9, (byte) 0, 8);
        byteBufOrder.writeInt(i);
        byteBufOrder.writeInt(i2);
        return byteBufOrder;
    }
}
