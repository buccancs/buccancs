package io.grpc.netty.shaded.io.netty.handler.codec.xml;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.CorruptedFrameException;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;

/* loaded from: classes3.dex */
public class XmlFrameDecoder extends ByteToMessageDecoder {
    private final int maxFrameLength;

    public XmlFrameDecoder(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("maxFrameLength must be a positive int");
        }
        this.maxFrameLength = i;
    }

    private static boolean isValidStartCharForXmlElement(byte b) {
        return (b >= 97 && b <= 122) || (b >= 65 && b <= 90) || b == 58 || b == 95;
    }

    private static void fail(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.fireExceptionCaught((Throwable) new CorruptedFrameException("frame contains content before the xml starts"));
    }

    private static ByteBuf extractFrame(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.copy(i, i2);
    }

    private static boolean isCommentBlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + (-3) && byteBuf.getByte(i + 2) == 45 && byteBuf.getByte(i + 3) == 45;
    }

    private static boolean isCDATABlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + (-8) && byteBuf.getByte(i + 2) == 91 && byteBuf.getByte(i + 3) == 67 && byteBuf.getByte(i + 4) == 68 && byteBuf.getByte(i + 5) == 65 && byteBuf.getByte(i + 6) == 84 && byteBuf.getByte(i + 7) == 65 && byteBuf.getByte(i + 8) == 91;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0090  */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r19, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r20, java.util.List<java.lang.Object> r21) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.xml.XmlFrameDecoder.decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.util.List):void");
    }

    private void fail(long j) {
        if (j > 0) {
            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded");
        }
        throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
    }
}
