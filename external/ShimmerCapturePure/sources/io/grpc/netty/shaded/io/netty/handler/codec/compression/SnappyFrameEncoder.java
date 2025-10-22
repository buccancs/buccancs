package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;

/* loaded from: classes3.dex */
public class SnappyFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    private static final int MIN_COMPRESSIBLE_LENGTH = 18;
    private static final byte[] STREAM_START = {-1, 6, 0, 0, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 78, ShimmerObject.SET_EXG_REGS_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, ShimmerObject.START_SDBT_COMMAND, 89};
    private final Snappy snappy = new Snappy();
    private boolean started;

    private static void writeUnencodedChunk(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        byteBuf2.writeByte(1);
        writeChunkLength(byteBuf2, i + 4);
        calculateAndWriteChecksum(byteBuf, byteBuf2);
        byteBuf2.writeBytes(byteBuf, i);
    }

    private static void setChunkLength(ByteBuf byteBuf, int i) {
        int iWriterIndex = (byteBuf.writerIndex() - i) - 3;
        if ((iWriterIndex >>> 24) != 0) {
            throw new CompressionException("compressed data too large: " + iWriterIndex);
        }
        byteBuf.setMediumLE(i, iWriterIndex);
    }

    private static void writeChunkLength(ByteBuf byteBuf, int i) {
        byteBuf.writeMediumLE(i);
    }

    private static void calculateAndWriteChecksum(ByteBuf byteBuf, ByteBuf byteBuf2) {
        byteBuf2.writeIntLE(Snappy.calculateChecksum(byteBuf));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        if (!byteBuf.isReadable()) {
            return;
        }
        if (!this.started) {
            this.started = true;
            byteBuf2.writeBytes(STREAM_START);
        }
        int i = byteBuf.readableBytes();
        if (i <= 18) {
            writeUnencodedChunk(byteBuf, byteBuf2, i);
            return;
        }
        while (true) {
            int iWriterIndex = byteBuf2.writerIndex() + 1;
            if (i < 18) {
                writeUnencodedChunk(byteBuf.readSlice(i), byteBuf2, i);
                return;
            }
            byteBuf2.writeInt(0);
            if (i > 32767) {
                ByteBuf slice = byteBuf.readSlice(32767);
                calculateAndWriteChecksum(slice, byteBuf2);
                this.snappy.encode(slice, byteBuf2, 32767);
                setChunkLength(byteBuf2, iWriterIndex);
                i -= 32767;
            } else {
                ByteBuf slice2 = byteBuf.readSlice(i);
                calculateAndWriteChecksum(slice2, byteBuf2);
                this.snappy.encode(slice2, byteBuf2, i);
                setChunkLength(byteBuf2, iWriterIndex);
                return;
            }
        }
    }
}
