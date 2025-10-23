package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.HpackUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;

/* loaded from: classes3.dex */
final class HpackDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte READ_HEADER_REPRESENTATION = 0;
    private static final byte READ_INDEXED_HEADER = 2;
    private static final byte READ_INDEXED_HEADER_NAME = 3;
    private static final byte READ_LITERAL_HEADER_NAME = 6;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH = 5;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH_PREFIX = 4;
    private static final byte READ_LITERAL_HEADER_VALUE = 9;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH = 8;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH_PREFIX = 7;
    private static final byte READ_MAX_DYNAMIC_TABLE_SIZE = 1;
    private static final Http2Exception DECODE_ULE_128_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - decompression failure", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "decodeULE128(..)");
    private static final Http2Exception DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - long overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "decodeULE128(..)");
    private static final Http2Exception DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - int overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "decodeULE128ToInt(..)");
    private static final Http2Exception DECODE_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "decode(..)");
    private static final Http2Exception INDEX_HEADER_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "indexHeader(..)");
    private static final Http2Exception READ_NAME_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "readName(..)");
    private static final Http2Exception INVALID_MAX_DYNAMIC_TABLE_SIZE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - invalid max dynamic table size", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "setDynamicTableSize(..)");
    private static final Http2Exception MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - max dynamic table size change required", Http2Exception.ShutdownHint.HARD_SHUTDOWN), HpackDecoder.class, "decode(..)");
    private final HpackDynamicTable hpackDynamicTable;
    private final HpackHuffmanDecoder huffmanDecoder;
    private long encoderMaxDynamicTableSize;
    private long maxDynamicTableSize;
    private boolean maxDynamicTableSizeChangeRequired;
    private long maxHeaderListSize;

    HpackDecoder(long j) {
        this(j, 4096);
    }

    HpackDecoder(long j, int i) {
        this.huffmanDecoder = new HpackHuffmanDecoder();
        this.maxHeaderListSize = ObjectUtil.checkPositive(j, "maxHeaderListSize");
        long j2 = i;
        this.encoderMaxDynamicTableSize = j2;
        this.maxDynamicTableSize = j2;
        this.maxDynamicTableSizeChangeRequired = false;
        this.hpackDynamicTable = new HpackDynamicTable(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HeaderType validate(int i, CharSequence charSequence, HeaderType headerType) throws Http2Exception {
        if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence)) {
            if (headerType == HeaderType.REGULAR_HEADER) {
                throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Pseudo-header field '%s' found after regular header.", charSequence);
            }
            Http2Headers.PseudoHeaderName pseudoHeader = Http2Headers.PseudoHeaderName.getPseudoHeader(charSequence);
            if (pseudoHeader == null) {
                throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 pseudo-header '%s' encountered.", charSequence);
            }
            HeaderType headerType2 = pseudoHeader.isRequestOnly() ? HeaderType.REQUEST_PSEUDO_HEADER : HeaderType.RESPONSE_PSEUDO_HEADER;
            if (headerType == null || headerType2 == headerType) {
                return headerType2;
            }
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, "Mix of request and response pseudo-headers.", new Object[0]);
        }
        return HeaderType.REGULAR_HEADER;
    }

    private static IllegalArgumentException notEnoughDataException(ByteBuf byteBuf) {
        return new IllegalArgumentException("decode only works with an entire header block! " + byteBuf);
    }

    static int decodeULE128(ByteBuf byteBuf, int i) throws Http2Exception {
        int i2 = byteBuf.readerIndex();
        long jDecodeULE128 = decodeULE128(byteBuf, i);
        if (jDecodeULE128 <= 2147483647L) {
            return (int) jDecodeULE128;
        }
        byteBuf.readerIndex(i2);
        throw DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION;
    }

    static long decodeULE128(ByteBuf byteBuf, long j) throws Http2Exception {
        int i = 0;
        boolean z = j == 0;
        int iWriterIndex = byteBuf.writerIndex();
        int i2 = byteBuf.readerIndex();
        while (i2 < iWriterIndex) {
            byte b = byteBuf.getByte(i2);
            if (i == 56 && ((b & 128) != 0 || (b == 127 && !z))) {
                throw DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION;
            }
            if ((b & 128) == 0) {
                byteBuf.readerIndex(i2 + 1);
                return j + ((b & 127) << i);
            }
            j += (b & 127) << i;
            i2++;
            i += 7;
        }
        throw DECODE_ULE_128_DECOMPRESSION_EXCEPTION;
    }

    public long getMaxHeaderListSize() {
        return this.maxHeaderListSize;
    }

    public void setMaxHeaderListSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxHeaderListSize = j;
    }

    public void decode(int i, ByteBuf byteBuf, Http2Headers http2Headers, boolean z) throws Http2Exception {
        Http2HeadersSink http2HeadersSink = new Http2HeadersSink(i, http2Headers, this.maxHeaderListSize, z);
        decode(byteBuf, http2HeadersSink);
        http2HeadersSink.finish();
    }

    private void decode(ByteBuf byteBuf, Sink sink) throws Http2Exception {
        HpackUtil.IndexType indexType = HpackUtil.IndexType.NONE;
        CharSequence name = null;
        int i = 0;
        int i2 = 0;
        int length = 0;
        int iDecodeULE128 = 0;
        boolean z = false;
        while (byteBuf.isReadable()) {
            switch (i) {
                case 0:
                    byte b = byteBuf.readByte();
                    if (this.maxDynamicTableSizeChangeRequired && (b & ShimmerObject.ROUTINE_COMMUNICATION) != 32) {
                        throw MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED;
                    }
                    if (b < 0) {
                        i2 = b & 127;
                        if (i2 == 0) {
                            throw DECODE_ILLEGAL_INDEX_VALUE;
                        }
                        if (i2 != 127) {
                            HpackHeaderField indexedHeader = getIndexedHeader(i2);
                            sink.appendToHeaderList(indexedHeader.name, indexedHeader.value);
                        } else {
                            i = 2;
                        }
                    } else if ((b & 64) == 64) {
                        indexType = HpackUtil.IndexType.INCREMENTAL;
                        i2 = b & ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW;
                        if (i2 == 0) {
                            i = 4;
                        } else if (i2 != 63) {
                            name = readName(i2);
                            length = name.length();
                            i = 7;
                        } else {
                            i = 3;
                        }
                    } else if ((b & 32) == 32) {
                        i2 = b & Ascii.US;
                        if (i2 == 31) {
                            i = 1;
                        } else {
                            setDynamicTableSize(i2);
                            i = 0;
                        }
                    } else {
                        indexType = (b & 16) == 16 ? HpackUtil.IndexType.NEVER : HpackUtil.IndexType.NONE;
                        i2 = b & 15;
                        if (i2 == 0) {
                            i = 4;
                        } else if (i2 != 15) {
                            name = readName(i2);
                            length = name.length();
                            i = 7;
                        } else {
                            i = 3;
                        }
                    }
                    break;
                case 1:
                    setDynamicTableSize(decodeULE128(byteBuf, i2));
                    i = 0;
                case 2:
                    HpackHeaderField indexedHeader2 = getIndexedHeader(decodeULE128(byteBuf, i2));
                    sink.appendToHeaderList(indexedHeader2.name, indexedHeader2.value);
                    i = 0;
                case 3:
                    name = readName(decodeULE128(byteBuf, i2));
                    length = name.length();
                    i = 7;
                case 4:
                    byte b2 = byteBuf.readByte();
                    z = (b2 & 128) == 128;
                    i2 = b2 & 127;
                    if (i2 == 127) {
                        i = 5;
                    } else {
                        length = i2;
                        i = 6;
                    }
                case 5:
                    length = decodeULE128(byteBuf, i2);
                    i = 6;
                case 6:
                    if (byteBuf.readableBytes() < length) {
                        throw notEnoughDataException(byteBuf);
                    }
                    name = readStringLiteral(byteBuf, length, z);
                    i = 7;
                case 7:
                    byte b3 = byteBuf.readByte();
                    z = (b3 & 128) == 128;
                    i2 = b3 & 127;
                    if (i2 == 0) {
                        insertHeader(sink, name, AsciiString.EMPTY_STRING, indexType);
                        i = 0;
                    } else if (i2 != 127) {
                        iDecodeULE128 = i2;
                        i = 9;
                    } else {
                        i = 8;
                    }
                case 8:
                    iDecodeULE128 = decodeULE128(byteBuf, i2);
                    i = 9;
                case 9:
                    if (byteBuf.readableBytes() < iDecodeULE128) {
                        throw notEnoughDataException(byteBuf);
                    }
                    insertHeader(sink, name, readStringLiteral(byteBuf, iDecodeULE128, z), indexType);
                    i = 0;
                default:
                    throw new Error("should not reach here state: " + i);
            }
        }
        if (i != 0) {
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "Incomplete header block fragment.", new Object[0]);
        }
    }

    @Deprecated
    public void setMaxHeaderListSize(long j, long j2) throws Http2Exception {
        setMaxHeaderListSize(j);
    }

    public long getMaxHeaderTableSize() {
        return this.hpackDynamicTable.capacity();
    }

    public void setMaxHeaderTableSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxDynamicTableSize = j;
        if (j < this.encoderMaxDynamicTableSize) {
            this.maxDynamicTableSizeChangeRequired = true;
            this.hpackDynamicTable.setCapacity(j);
        }
    }

    int length() {
        return this.hpackDynamicTable.length();
    }

    long size() {
        return this.hpackDynamicTable.size();
    }

    HpackHeaderField getHeaderField(int i) {
        return this.hpackDynamicTable.getEntry(i + 1);
    }

    private void setDynamicTableSize(long j) throws Http2Exception {
        if (j > this.maxDynamicTableSize) {
            throw INVALID_MAX_DYNAMIC_TABLE_SIZE;
        }
        this.encoderMaxDynamicTableSize = j;
        this.maxDynamicTableSizeChangeRequired = false;
        this.hpackDynamicTable.setCapacity(j);
    }

    private CharSequence readName(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return HpackStaticTable.getEntry(i).name;
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return this.hpackDynamicTable.getEntry(i - HpackStaticTable.length).name;
        }
        throw READ_NAME_ILLEGAL_INDEX_VALUE;
    }

    private HpackHeaderField getIndexedHeader(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return HpackStaticTable.getEntry(i);
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return this.hpackDynamicTable.getEntry(i - HpackStaticTable.length);
        }
        throw INDEX_HEADER_ILLEGAL_INDEX_VALUE;
    }

    private void insertHeader(Sink sink, CharSequence charSequence, CharSequence charSequence2, HpackUtil.IndexType indexType) {
        sink.appendToHeaderList(charSequence, charSequence2);
        int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[indexType.ordinal()];
        if (i == 1 || i == 2) {
            return;
        }
        if (i == 3) {
            this.hpackDynamicTable.add(new HpackHeaderField(charSequence, charSequence2));
            return;
        }
        throw new Error("should not reach here");
    }

    private CharSequence readStringLiteral(ByteBuf byteBuf, int i, boolean z) throws Http2Exception {
        if (z) {
            return this.huffmanDecoder.decode(byteBuf, i);
        }
        byte[] bArr = new byte[i];
        byteBuf.readBytes(bArr);
        return new AsciiString(bArr, false);
    }

    private enum HeaderType {
        REGULAR_HEADER,
        REQUEST_PSEUDO_HEADER,
        RESPONSE_PSEUDO_HEADER
    }

    private interface Sink {
        void appendToHeaderList(CharSequence charSequence, CharSequence charSequence2);

        void finish() throws Http2Exception;
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http2.HpackDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType;

        static {
            int[] iArr = new int[HpackUtil.IndexType.values().length];
            $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType = iArr;
            try {
                iArr[HpackUtil.IndexType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[HpackUtil.IndexType.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[HpackUtil.IndexType.INCREMENTAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static final class Http2HeadersSink implements Sink {
        private final Http2Headers headers;
        private final long maxHeaderListSize;
        private final int streamId;
        private final boolean validate;
        private boolean exceededMaxLength;
        private long headersLength;
        private HeaderType previousType;
        private Http2Exception validationException;

        Http2HeadersSink(int i, Http2Headers http2Headers, long j, boolean z) {
            this.headers = http2Headers;
            this.maxHeaderListSize = j;
            this.streamId = i;
            this.validate = z;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.HpackDecoder.Sink
        public void finish() throws Http2Exception {
            if (this.exceededMaxLength) {
                Http2CodecUtil.headerListSizeExceeded(this.streamId, this.maxHeaderListSize, true);
                return;
            }
            Http2Exception http2Exception = this.validationException;
            if (http2Exception != null) {
                throw http2Exception;
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.HpackDecoder.Sink
        public void appendToHeaderList(CharSequence charSequence, CharSequence charSequence2) {
            long jSizeOf = this.headersLength + HpackHeaderField.sizeOf(charSequence, charSequence2);
            this.headersLength = jSizeOf;
            boolean z = (jSizeOf > this.maxHeaderListSize) | this.exceededMaxLength;
            this.exceededMaxLength = z;
            if (z || this.validationException != null) {
                return;
            }
            if (this.validate) {
                try {
                    this.previousType = HpackDecoder.validate(this.streamId, charSequence, this.previousType);
                } catch (Http2Exception e) {
                    this.validationException = e;
                    return;
                }
            }
            this.headers.add((Http2Headers) charSequence, charSequence2);
        }
    }
}
