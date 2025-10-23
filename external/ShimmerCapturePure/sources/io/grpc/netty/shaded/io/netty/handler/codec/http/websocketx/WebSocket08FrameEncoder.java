package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* loaded from: classes3.dex */
public class WebSocket08FrameEncoder extends MessageToMessageEncoder<WebSocketFrame> implements WebSocketFrameEncoder {
    private static final int GATHERING_WRITE_THRESHOLD = 1024;
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameEncoder.class);
    private final boolean maskPayload;

    public WebSocket08FrameEncoder(boolean z) {
        this.maskPayload = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        encode2(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [io.grpc.netty.shaded.io.netty.buffer.ByteBuf] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        byte b;
        ??r4;
        boolean z;
        int i;
        ByteBuf byteBufBuffer;
        ByteBuf byteBufContent = webSocketFrame.content();
        int i2 = 0;
        if (webSocketFrame instanceof TextWebSocketFrame) {
            b = 1;
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            b = 9;
        } else if (webSocketFrame instanceof PongWebSocketFrame) {
            b = 10;
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            b = 8;
        } else if (webSocketFrame instanceof BinaryWebSocketFrame) {
            b = 2;
        } else {
            if (!(webSocketFrame instanceof ContinuationWebSocketFrame)) {
                throw new UnsupportedOperationException("Cannot encode frame of type: " + webSocketFrame.getClass().getName());
            }
            b = 0;
        }
        int i3 = byteBufContent.readableBytes();
        InternalLogger internalLogger = logger;
        if (internalLogger.isTraceEnabled()) {
            internalLogger.trace("Encoding WebSocket Frame opCode={} length={}", Byte.valueOf(b), Integer.valueOf(i3));
        }
        int iRsv = ((webSocketFrame.rsv() % 8) << 4) | (webSocketFrame.isFinalFragment() ? 128 : 0) | (b % 128);
        if (b == 9 && i3 > 125) {
            throw new TooLongFrameException("invalid payload for PING (payload length must be <= 125, was " + i3);
        }
        try {
            z = this.maskPayload;
            i = z ? 4 : 0;
            r4 = 1024;
        } catch (Throwable th) {
            th = th;
            r4 = 0;
        }
        try {
            if (i3 <= 125) {
                int i4 = i + 2;
                if (z || i3 <= 1024) {
                    i4 += i3;
                }
                byteBufBuffer = channelHandlerContext.alloc().buffer(i4);
                byteBufBuffer.writeByte(iRsv);
                byteBufBuffer.writeByte((byte) (this.maskPayload ? ((byte) i3) | 128 : (byte) i3));
            } else if (i3 <= 65535) {
                int i5 = i + 4;
                if (z || i3 <= 1024) {
                    i5 += i3;
                }
                byteBufBuffer = channelHandlerContext.alloc().buffer(i5);
                byteBufBuffer.writeByte(iRsv);
                byteBufBuffer.writeByte(this.maskPayload ? 254 : ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_EXPID_COMMAND_VALUE);
                byteBufBuffer.writeByte((i3 >>> 8) & 255);
                byteBufBuffer.writeByte(i3 & 255);
            } else {
                int i6 = i + 10;
                if (z || i3 <= 1024) {
                    i6 += i3;
                }
                byteBufBuffer = channelHandlerContext.alloc().buffer(i6);
                byteBufBuffer.writeByte(iRsv);
                byteBufBuffer.writeByte(this.maskPayload ? 255 : 127);
                byteBufBuffer.writeLong(i3);
            }
            if (this.maskPayload) {
                byte[] bArrArray = ByteBuffer.allocate(4).putInt((int) (Math.random() * 2.147483647E9d)).array();
                byteBufBuffer.writeBytes(bArrArray);
                ByteOrder byteOrderOrder = byteBufContent.order();
                ByteOrder byteOrderOrder2 = byteBufBuffer.order();
                int i7 = byteBufContent.readerIndex();
                int iWriterIndex = byteBufContent.writerIndex();
                if (byteOrderOrder == byteOrderOrder2) {
                    int iReverseBytes = ((bArrArray[0] & 255) << 24) | ((bArrArray[1] & 255) << 16) | ((bArrArray[2] & 255) << 8) | (bArrArray[3] & 255);
                    if (byteOrderOrder == ByteOrder.LITTLE_ENDIAN) {
                        iReverseBytes = Integer.reverseBytes(iReverseBytes);
                    }
                    while (i7 + 3 < iWriterIndex) {
                        byteBufBuffer.writeInt(byteBufContent.getInt(i7) ^ iReverseBytes);
                        i7 += 4;
                    }
                }
                while (i7 < iWriterIndex) {
                    byteBufBuffer.writeByte(byteBufContent.getByte(i7) ^ bArrArray[i2 % 4]);
                    i7++;
                    i2++;
                }
                list.add(byteBufBuffer);
                return;
            }
            if (byteBufBuffer.writableBytes() >= byteBufContent.readableBytes()) {
                byteBufBuffer.writeBytes(byteBufContent);
                list.add(byteBufBuffer);
            } else {
                list.add(byteBufBuffer);
                list.add(byteBufContent.retain());
            }
        } catch (Throwable th2) {
            th = th2;
            if (r4 != 0) {
                r4.release();
            }
            throw th;
        }
    }
}
