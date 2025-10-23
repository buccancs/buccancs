package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;

/* loaded from: classes3.dex */
final class HttpHeadersEncoder {
    private static final int COLON_AND_SPACE_SHORT = 14880;

    private HttpHeadersEncoder() {
    }

    static void encoderHeader(CharSequence charSequence, CharSequence charSequence2, ByteBuf byteBuf) {
        int length = charSequence.length();
        int length2 = charSequence2.length();
        byteBuf.ensureWritable(length + length2 + 4);
        int iWriterIndex = byteBuf.writerIndex();
        writeAscii(byteBuf, iWriterIndex, charSequence);
        int i = iWriterIndex + length;
        ByteBufUtil.setShortBE(byteBuf, i, 14880);
        int i2 = i + 2;
        writeAscii(byteBuf, i2, charSequence2);
        int i3 = i2 + length2;
        ByteBufUtil.setShortBE(byteBuf, i3, 3338);
        byteBuf.writerIndex(i3 + 2);
    }

    private static void writeAscii(ByteBuf byteBuf, int i, CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            ByteBufUtil.copy((AsciiString) charSequence, 0, byteBuf, i, charSequence.length());
        } else {
            byteBuf.setCharSequence(i, charSequence, CharsetUtil.US_ASCII);
        }
    }
}
