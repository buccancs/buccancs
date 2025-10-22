package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ReplayingDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.List;

/* loaded from: classes3.dex */
public class WebSocket00FrameDecoder extends ReplayingDecoder<Void> implements WebSocketFrameDecoder {
    static final int DEFAULT_MAX_FRAME_SIZE = 16384;
    private final long maxFrameSize;
    private boolean receivedClosingHandshake;

    public WebSocket00FrameDecoder() {
        this(16384);
    }

    public WebSocket00FrameDecoder(int i) {
        this.maxFrameSize = i;
    }

    public WebSocket00FrameDecoder(WebSocketDecoderConfig webSocketDecoderConfig) {
        this.maxFrameSize = ((WebSocketDecoderConfig) ObjectUtil.checkNotNull(webSocketDecoderConfig, "decoderConfig")).maxFramePayloadLength();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        WebSocketFrame webSocketFrameDecodeTextFrame;
        if (this.receivedClosingHandshake) {
            byteBuf.skipBytes(actualReadableBytes());
            return;
        }
        byte b = byteBuf.readByte();
        if ((b & 128) == 128) {
            webSocketFrameDecodeTextFrame = decodeBinaryFrame(channelHandlerContext, b, byteBuf);
        } else {
            webSocketFrameDecodeTextFrame = decodeTextFrame(channelHandlerContext, byteBuf);
        }
        if (webSocketFrameDecodeTextFrame != null) {
            list.add(webSocketFrameDecodeTextFrame);
        }
    }

    private WebSocketFrame decodeBinaryFrame(ChannelHandlerContext channelHandlerContext, byte b, ByteBuf byteBuf) {
        byte b2;
        long j = 0;
        int i = 0;
        do {
            b2 = byteBuf.readByte();
            j = (j << 7) | (b2 & 127);
            if (j > this.maxFrameSize) {
                throw new TooLongFrameException();
            }
            i++;
            if (i > 8) {
                throw new TooLongFrameException();
            }
        } while ((b2 & 128) == 128);
        if (b == -1 && j == 0) {
            this.receivedClosingHandshake = true;
            return new CloseWebSocketFrame(true, 0, channelHandlerContext.alloc().buffer(0));
        }
        return new BinaryWebSocketFrame(ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, (int) j));
    }

    private WebSocketFrame decodeTextFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        int i = byteBuf.readerIndex();
        int iActualReadableBytes = actualReadableBytes();
        int iIndexOf = byteBuf.indexOf(i, i + iActualReadableBytes, (byte) -1);
        if (iIndexOf == -1) {
            if (iActualReadableBytes <= this.maxFrameSize) {
                return null;
            }
            throw new TooLongFrameException();
        }
        int i2 = iIndexOf - i;
        if (i2 > this.maxFrameSize) {
            throw new TooLongFrameException();
        }
        ByteBuf bytes = ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, i2);
        byteBuf.skipBytes(1);
        if (bytes.indexOf(bytes.readerIndex(), bytes.writerIndex(), (byte) -1) >= 0) {
            bytes.release();
            throw new IllegalArgumentException("a text frame should not contain 0xFF.");
        }
        return new TextWebSocketFrame(bytes);
    }
}
