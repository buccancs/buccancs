package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;

import java.util.List;

/* loaded from: classes3.dex */
public class LineBasedFrameDecoder extends ByteToMessageDecoder {
    private final boolean failFast;
    private final int maxLength;
    private final boolean stripDelimiter;
    private int discardedBytes;
    private boolean discarding;
    private int offset;

    public LineBasedFrameDecoder(int i) {
        this(i, true, false);
    }

    public LineBasedFrameDecoder(int i, boolean z, boolean z2) {
        this.maxLength = i;
        this.failFast = z2;
        this.stripDelimiter = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object objDecode = decode(channelHandlerContext, byteBuf);
        if (objDecode != null) {
            list.add(objDecode);
        }
    }

    protected Object decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        int iFindEndOfLine = findEndOfLine(byteBuf);
        if (this.discarding) {
            if (iFindEndOfLine >= 0) {
                int i = (this.discardedBytes + iFindEndOfLine) - byteBuf.readerIndex();
                byteBuf.readerIndex(iFindEndOfLine + (byteBuf.getByte(iFindEndOfLine) != 13 ? 1 : 2));
                this.discardedBytes = 0;
                this.discarding = false;
                if (!this.failFast) {
                    fail(channelHandlerContext, i);
                }
            } else {
                this.discardedBytes += byteBuf.readableBytes();
                byteBuf.readerIndex(byteBuf.writerIndex());
                this.offset = 0;
            }
            return null;
        }
        if (iFindEndOfLine >= 0) {
            int i2 = iFindEndOfLine - byteBuf.readerIndex();
            int i3 = byteBuf.getByte(iFindEndOfLine) != 13 ? 1 : 2;
            if (i2 > this.maxLength) {
                byteBuf.readerIndex(iFindEndOfLine + i3);
                fail(channelHandlerContext, i2);
                return null;
            }
            if (this.stripDelimiter) {
                ByteBuf retainedSlice = byteBuf.readRetainedSlice(i2);
                byteBuf.skipBytes(i3);
                return retainedSlice;
            }
            return byteBuf.readRetainedSlice(i2 + i3);
        }
        int i4 = byteBuf.readableBytes();
        if (i4 > this.maxLength) {
            this.discardedBytes = i4;
            byteBuf.readerIndex(byteBuf.writerIndex());
            this.discarding = true;
            this.offset = 0;
            if (this.failFast) {
                fail(channelHandlerContext, "over " + this.discardedBytes);
            }
        }
        return null;
    }

    private void fail(ChannelHandlerContext channelHandlerContext, int i) {
        fail(channelHandlerContext, String.valueOf(i));
    }

    private void fail(ChannelHandlerContext channelHandlerContext, String str) {
        channelHandlerContext.fireExceptionCaught((Throwable) new TooLongFrameException("frame length (" + str + ") exceeds the allowed maximum (" + this.maxLength + ')'));
    }

    private int findEndOfLine(ByteBuf byteBuf) {
        int i = byteBuf.readableBytes();
        int i2 = byteBuf.readerIndex();
        int i3 = this.offset;
        int iForEachByte = byteBuf.forEachByte(i2 + i3, i - i3, ByteProcessor.FIND_LF);
        if (iForEachByte >= 0) {
            this.offset = 0;
            return (iForEachByte <= 0 || byteBuf.getByte(iForEachByte + (-1)) != 13) ? iForEachByte : iForEachByte - 1;
        }
        this.offset = i;
        return iForEachByte;
    }
}
