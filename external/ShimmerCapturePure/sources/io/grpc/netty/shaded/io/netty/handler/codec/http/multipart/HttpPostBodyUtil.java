package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;

/* loaded from: classes3.dex */
final class HttpPostBodyUtil {
    public static final String DEFAULT_BINARY_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_TEXT_CONTENT_TYPE = "text/plain";
    public static final int chunkSize = 8096;

    private HttpPostBodyUtil() {
    }

    static int findNonWhitespace(String str, int i) {
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return i;
    }

    static int findEndOfString(String str) {
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return length;
    }

    public enum TransferEncodingMechanism {
        BIT7("7bit"),
        BIT8("8bit"),
        BINARY(HttpHeaders.Values.BINARY);

        private final String value;

        TransferEncodingMechanism(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }
    }

    static class SeekAheadOptimize {
        ByteBuf buffer;
        byte[] bytes;
        int limit;
        int origPos;
        int pos;
        int readerIndex;

        SeekAheadOptimize(ByteBuf byteBuf) {
            if (!byteBuf.hasArray()) {
                throw new IllegalArgumentException("buffer hasn't backing byte array");
            }
            this.buffer = byteBuf;
            this.bytes = byteBuf.array();
            this.readerIndex = byteBuf.readerIndex();
            int iArrayOffset = byteBuf.arrayOffset() + this.readerIndex;
            this.pos = iArrayOffset;
            this.origPos = iArrayOffset;
            this.limit = byteBuf.arrayOffset() + byteBuf.writerIndex();
        }

        int getReadPosition(int i) {
            return (i - this.origPos) + this.readerIndex;
        }

        void setReadPosition(int i) {
            int i2 = this.pos - i;
            this.pos = i2;
            int readPosition = getReadPosition(i2);
            this.readerIndex = readPosition;
            this.buffer.readerIndex(readPosition);
        }
    }
}
