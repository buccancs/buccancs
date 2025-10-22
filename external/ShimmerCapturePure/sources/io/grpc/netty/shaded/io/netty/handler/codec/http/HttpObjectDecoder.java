package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.PrematureChannelClosureException;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.AppendableCharSequence;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public abstract class HttpObjectDecoder extends ByteToMessageDecoder {
    public static final boolean DEFAULT_ALLOW_DUPLICATE_CONTENT_LENGTHS = false;
    public static final boolean DEFAULT_CHUNKED_SUPPORTED = true;
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 128;
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    public static final int DEFAULT_MAX_HEADER_SIZE = 8192;
    public static final int DEFAULT_MAX_INITIAL_LINE_LENGTH = 4096;
    public static final boolean DEFAULT_VALIDATE_HEADERS = true;
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Pattern COMMA_PATTERN = Pattern.compile(UtilVerisenseDriver.CSV_DELIMITER);
    private static final String EMPTY_VALUE = "";
    protected final boolean validateHeaders;
    private final boolean allowDuplicateContentLengths;
    private final boolean chunkedSupported;
    private final HeaderParser headerParser;
    private final LineParser lineParser;
    private final int maxChunkSize;
    private long chunkSize;
    private long contentLength;
    private State currentState;
    private HttpMessage message;
    private CharSequence name;
    private volatile boolean resetRequested;
    private LastHttpContent trailer;
    private CharSequence value;

    protected HttpObjectDecoder() {
        this(4096, 8192, 8192, true);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z) {
        this(i, i2, i3, z, true);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2) {
        this(i, i2, i3, z, z2, 128);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4) {
        this(i, i2, i3, z, z2, i4, false);
    }

    protected HttpObjectDecoder(int i, int i2, int i3, boolean z, boolean z2, int i4, boolean z3) {
        this.contentLength = Long.MIN_VALUE;
        this.currentState = State.SKIP_CONTROL_CHARS;
        ObjectUtil.checkPositive(i, "maxInitialLineLength");
        ObjectUtil.checkPositive(i2, "maxHeaderSize");
        ObjectUtil.checkPositive(i3, "maxChunkSize");
        AppendableCharSequence appendableCharSequence = new AppendableCharSequence(i4);
        this.lineParser = new LineParser(appendableCharSequence, i);
        this.headerParser = new HeaderParser(appendableCharSequence, i2);
        this.maxChunkSize = i3;
        this.chunkedSupported = z;
        this.validateHeaders = z2;
        this.allowDuplicateContentLengths = z3;
    }

    private static boolean isOWS(char c) {
        return c == ' ' || c == '\t';
    }

    private static boolean isSPLenient(char c) {
        return c == ' ' || c == '\t' || c == 11 || c == '\f' || c == '\r';
    }

    private static int getChunkSize(String str) {
        String strTrim = str.trim();
        for (int i = 0; i < strTrim.length(); i++) {
            char cCharAt = strTrim.charAt(i);
            if (cCharAt == ';' || Character.isWhitespace(cCharAt) || Character.isISOControl(cCharAt)) {
                strTrim = strTrim.substring(0, i);
                break;
            }
        }
        return Integer.parseInt(strTrim, 16);
    }

    private static String[] splitInitialLine(AppendableCharSequence appendableCharSequence) {
        int iFindNonSPLenient = findNonSPLenient(appendableCharSequence, 0);
        int iFindSPLenient = findSPLenient(appendableCharSequence, iFindNonSPLenient);
        int iFindNonSPLenient2 = findNonSPLenient(appendableCharSequence, iFindSPLenient);
        int iFindSPLenient2 = findSPLenient(appendableCharSequence, iFindNonSPLenient2);
        int iFindNonSPLenient3 = findNonSPLenient(appendableCharSequence, iFindSPLenient2);
        int iFindEndOfString = findEndOfString(appendableCharSequence);
        String[] strArr = new String[3];
        strArr[0] = appendableCharSequence.subStringUnsafe(iFindNonSPLenient, iFindSPLenient);
        strArr[1] = appendableCharSequence.subStringUnsafe(iFindNonSPLenient2, iFindSPLenient2);
        strArr[2] = iFindNonSPLenient3 < iFindEndOfString ? appendableCharSequence.subStringUnsafe(iFindNonSPLenient3, iFindEndOfString) : "";
        return strArr;
    }

    private static int findNonSPLenient(AppendableCharSequence appendableCharSequence, int i) {
        while (i < appendableCharSequence.length()) {
            char cCharAtUnsafe = appendableCharSequence.charAtUnsafe(i);
            if (!isSPLenient(cCharAtUnsafe)) {
                if (Character.isWhitespace(cCharAtUnsafe)) {
                    throw new IllegalArgumentException("Invalid separator");
                }
                return i;
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findSPLenient(AppendableCharSequence appendableCharSequence, int i) {
        while (i < appendableCharSequence.length()) {
            if (isSPLenient(appendableCharSequence.charAtUnsafe(i))) {
                return i;
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findNonWhitespace(AppendableCharSequence appendableCharSequence, int i, boolean z) {
        while (i < appendableCharSequence.length()) {
            char cCharAtUnsafe = appendableCharSequence.charAtUnsafe(i);
            if (!Character.isWhitespace(cCharAtUnsafe)) {
                return i;
            }
            if (z && !isOWS(cCharAtUnsafe)) {
                throw new IllegalArgumentException("Invalid separator, only a single space or horizontal tab allowed, but received a '" + cCharAtUnsafe + "'");
            }
            i++;
        }
        return appendableCharSequence.length();
    }

    private static int findEndOfString(AppendableCharSequence appendableCharSequence) {
        for (int length = appendableCharSequence.length() - 1; length > 0; length--) {
            if (!Character.isWhitespace(appendableCharSequence.charAtUnsafe(length))) {
                return length + 1;
            }
        }
        return 0;
    }

    protected abstract HttpMessage createInvalidMessage();

    protected abstract HttpMessage createMessage(String[] strArr) throws Exception;

    protected abstract boolean isDecodingRequest();

    public void reset() {
        this.resetRequested = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0139 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x013a A[Catch: Exception -> 0x0199, TryCatch #2 {Exception -> 0x0199, blocks: (B:69:0x0133, B:72:0x013a, B:76:0x014a, B:80:0x0158, B:83:0x015f, B:85:0x0168, B:87:0x016b, B:89:0x0179, B:91:0x017d, B:93:0x0183, B:94:0x018a, B:95:0x018b), top: B:106:0x0133 }] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext r8, io.grpc.netty.shaded.io.netty.buffer.ByteBuf r9, java.util.List<java.lang.Object> r10) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.decode(io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.util.List):void");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decodeLast(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        super.decodeLast(channelHandlerContext, byteBuf, list);
        if (this.resetRequested) {
            resetNow();
        }
        HttpMessage httpMessage = this.message;
        if (httpMessage != null) {
            boolean zIsTransferEncodingChunked = HttpUtil.isTransferEncodingChunked(httpMessage);
            if (this.currentState == State.READ_VARIABLE_LENGTH_CONTENT && !byteBuf.isReadable() && !zIsTransferEncodingChunked) {
                list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                resetNow();
            } else {
                if (this.currentState == State.READ_HEADER) {
                    list.add(invalidMessage(Unpooled.EMPTY_BUFFER, new PrematureChannelClosureException("Connection closed before received headers")));
                    resetNow();
                    return;
                }
                if (!isDecodingRequest() && !zIsTransferEncodingChunked && contentLength() <= 0) {
                    list.add(LastHttpContent.EMPTY_LAST_CONTENT);
                }
                resetNow();
            }
        }
    }

    @Override
    // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        int i;
        if ((obj instanceof HttpExpectationFailedEvent) && ((i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[this.currentState.ordinal()]) == 2 || i == 5 || i == 6)) {
            reset();
        }
        super.userEventTriggered(channelHandlerContext, obj);
    }

    protected boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        if (httpMessage instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) httpMessage;
            int iCode = httpResponse.status().code();
            if (iCode >= 100 && iCode < 200) {
                return (iCode == 101 && !httpResponse.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT) && httpResponse.headers().contains((CharSequence) HttpHeaderNames.UPGRADE, (CharSequence) HttpHeaderValues.WEBSOCKET, true)) ? false : true;
            }
            if (iCode == 204 || iCode == 304) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSwitchingToNonHttp1Protocol(HttpResponse httpResponse) {
        if (httpResponse.status().code() != HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
            return false;
        }
        String str = httpResponse.headers().get(HttpHeaderNames.UPGRADE);
        return str == null || !(str.contains(HttpVersion.HTTP_1_0.text()) || str.contains(HttpVersion.HTTP_1_1.text()));
    }

    private void resetNow() {
        HttpResponse httpResponse;
        HttpMessage httpMessage = this.message;
        this.message = null;
        this.name = null;
        this.value = null;
        this.contentLength = Long.MIN_VALUE;
        this.lineParser.reset();
        this.headerParser.reset();
        this.trailer = null;
        if (!isDecodingRequest() && (httpResponse = (HttpResponse) httpMessage) != null && isSwitchingToNonHttp1Protocol(httpResponse)) {
            this.currentState = State.UPGRADED;
        } else {
            this.resetRequested = false;
            this.currentState = State.SKIP_CONTROL_CHARS;
        }
    }

    private HttpMessage invalidMessage(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        if (this.message == null) {
            this.message = createInvalidMessage();
        }
        this.message.setDecoderResult(DecoderResult.failure(exc));
        HttpMessage httpMessage = this.message;
        this.message = null;
        return httpMessage;
    }

    private HttpContent invalidChunk(ByteBuf byteBuf, Exception exc) {
        this.currentState = State.BAD_MESSAGE;
        byteBuf.skipBytes(byteBuf.readableBytes());
        DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        defaultLastHttpContent.setDecoderResult(DecoderResult.failure(exc));
        this.message = null;
        this.trailer = null;
        return defaultLastHttpContent;
    }

    private State readHeaders(ByteBuf byteBuf) {
        HttpMessage httpMessage = this.message;
        HttpHeaders httpHeadersHeaders = httpMessage.headers();
        AppendableCharSequence appendableCharSequence = this.headerParser.parse(byteBuf);
        String str = null;
        if (appendableCharSequence == null) {
            return null;
        }
        if (appendableCharSequence.length() > 0) {
            do {
                char cCharAtUnsafe = appendableCharSequence.charAtUnsafe(0);
                CharSequence charSequence = this.name;
                if (charSequence != null && (cCharAtUnsafe == ' ' || cCharAtUnsafe == '\t')) {
                    this.value = String.valueOf(this.value) + ' ' + appendableCharSequence.toString().trim();
                } else {
                    if (charSequence != null) {
                        httpHeadersHeaders.add(charSequence, this.value);
                    }
                    splitHeader(appendableCharSequence);
                }
                appendableCharSequence = this.headerParser.parse(byteBuf);
                if (appendableCharSequence == null) {
                    return null;
                }
            } while (appendableCharSequence.length() > 0);
        }
        CharSequence charSequence2 = this.name;
        if (charSequence2 != null) {
            httpHeadersHeaders.add(charSequence2, this.value);
        }
        this.name = null;
        this.value = null;
        List<String> all = httpHeadersHeaders.getAll(HttpHeaderNames.CONTENT_LENGTH);
        if (!all.isEmpty()) {
            if ((all.size() <= 1 && all.get(0).indexOf(44) < 0) || httpMessage.protocolVersion() != HttpVersion.HTTP_1_1) {
                this.contentLength = Long.parseLong(all.get(0));
            } else if (this.allowDuplicateContentLengths) {
                Iterator<String> it2 = all.iterator();
                while (it2.hasNext()) {
                    for (String str2 : COMMA_PATTERN.split(it2.next(), -1)) {
                        String strTrim = str2.trim();
                        if (str == null) {
                            str = strTrim;
                        } else if (!strTrim.equals(str)) {
                            throw new IllegalArgumentException("Multiple Content-Length values found: " + all);
                        }
                    }
                }
                httpHeadersHeaders.set(HttpHeaderNames.CONTENT_LENGTH, str);
                this.contentLength = Long.parseLong(str);
            } else {
                throw new IllegalArgumentException("Multiple Content-Length values found: " + all);
            }
        }
        if (isContentAlwaysEmpty(httpMessage)) {
            HttpUtil.setTransferEncodingChunked(httpMessage, false);
            return State.SKIP_CONTROL_CHARS;
        }
        if (!HttpUtil.isTransferEncodingChunked(httpMessage)) {
            if (contentLength() >= 0) {
                return State.READ_FIXED_LENGTH_CONTENT;
            }
            return State.READ_VARIABLE_LENGTH_CONTENT;
        }
        if (!all.isEmpty() && httpMessage.protocolVersion() == HttpVersion.HTTP_1_1) {
            handleTransferEncodingChunkedWithContentLength(httpMessage);
        }
        return State.READ_CHUNK_SIZE;
    }

    protected void handleTransferEncodingChunkedWithContentLength(HttpMessage httpMessage) {
        httpMessage.headers().remove(HttpHeaderNames.CONTENT_LENGTH);
        this.contentLength = Long.MIN_VALUE;
    }

    private long contentLength() {
        if (this.contentLength == Long.MIN_VALUE) {
            this.contentLength = HttpUtil.getContentLength(this.message, -1L);
        }
        return this.contentLength;
    }

    private LastHttpContent readTrailingHeaders(ByteBuf byteBuf) {
        AppendableCharSequence appendableCharSequence = this.headerParser.parse(byteBuf);
        if (appendableCharSequence == null) {
            return null;
        }
        LastHttpContent defaultLastHttpContent = this.trailer;
        if (appendableCharSequence.length() == 0 && defaultLastHttpContent == null) {
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        if (defaultLastHttpContent == null) {
            defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
            this.trailer = defaultLastHttpContent;
        }
        CharSequence charSequence = null;
        while (appendableCharSequence.length() > 0) {
            char cCharAtUnsafe = appendableCharSequence.charAtUnsafe(0);
            if (charSequence != null && (cCharAtUnsafe == ' ' || cCharAtUnsafe == '\t')) {
                List<String> all = defaultLastHttpContent.trailingHeaders().getAll(charSequence);
                if (!all.isEmpty()) {
                    int size = all.size() - 1;
                    String strTrim = appendableCharSequence.toString().trim();
                    all.set(size, all.get(size) + strTrim);
                }
            } else {
                splitHeader(appendableCharSequence);
                CharSequence charSequence2 = this.name;
                if (!HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(charSequence2) && !HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(charSequence2) && !HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(charSequence2)) {
                    defaultLastHttpContent.trailingHeaders().add(charSequence2, this.value);
                }
                charSequence = this.name;
                this.name = null;
                this.value = null;
            }
            appendableCharSequence = this.headerParser.parse(byteBuf);
            if (appendableCharSequence == null) {
                return null;
            }
        }
        this.trailer = null;
        return defaultLastHttpContent;
    }

    private void splitHeader(AppendableCharSequence appendableCharSequence) {
        char cCharAtUnsafe;
        int length = appendableCharSequence.length();
        int iFindNonWhitespace = findNonWhitespace(appendableCharSequence, 0, false);
        int i = iFindNonWhitespace;
        while (i < length && (cCharAtUnsafe = appendableCharSequence.charAtUnsafe(i)) != ':' && (isDecodingRequest() || !isOWS(cCharAtUnsafe))) {
            i++;
        }
        if (i == length) {
            throw new IllegalArgumentException("No colon found");
        }
        int i2 = i;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (appendableCharSequence.charAtUnsafe(i2) == ':') {
                i2++;
                break;
            }
            i2++;
        }
        this.name = appendableCharSequence.subStringUnsafe(iFindNonWhitespace, i);
        int iFindNonWhitespace2 = findNonWhitespace(appendableCharSequence, i2, true);
        if (iFindNonWhitespace2 == length) {
            this.value = "";
        } else {
            this.value = appendableCharSequence.subStringUnsafe(iFindNonWhitespace2, findEndOfString(appendableCharSequence));
        }
    }

    private enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER,
        BAD_MESSAGE,
        UPGRADED
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State = iArr;
            try {
                iArr[State.SKIP_CONTROL_CHARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_CHUNK_SIZE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_INITIAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_HEADER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_VARIABLE_LENGTH_CONTENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_FIXED_LENGTH_CONTENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_CHUNKED_CONTENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_CHUNK_DELIMITER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.READ_CHUNK_FOOTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.BAD_MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[State.UPGRADED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private static class HeaderParser implements ByteProcessor {
        private final int maxLength;
        private final AppendableCharSequence seq;
        private int size;

        HeaderParser(AppendableCharSequence appendableCharSequence, int i) {
            this.seq = appendableCharSequence;
            this.maxLength = i;
        }

        public void reset() {
            this.size = 0;
        }

        public AppendableCharSequence parse(ByteBuf byteBuf) {
            int i = this.size;
            this.seq.reset();
            int iForEachByte = byteBuf.forEachByte(this);
            if (iForEachByte == -1) {
                this.size = i;
                return null;
            }
            byteBuf.readerIndex(iForEachByte + 1);
            return this.seq;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            char c = (char) (b & 255);
            if (c == '\n') {
                int length = this.seq.length();
                if (length < 1) {
                    return false;
                }
                int i = length - 1;
                if (this.seq.charAtUnsafe(i) != '\r') {
                    return false;
                }
                this.size--;
                this.seq.setLength(i);
                return false;
            }
            increaseCount();
            this.seq.append(c);
            return true;
        }

        protected final void increaseCount() {
            int i = this.size + 1;
            this.size = i;
            int i2 = this.maxLength;
            if (i > i2) {
                throw newException(i2);
            }
        }

        protected TooLongFrameException newException(int i) {
            return new TooLongFrameException("HTTP header is larger than " + i + " bytes.");
        }
    }

    private final class LineParser extends HeaderParser {
        LineParser(AppendableCharSequence appendableCharSequence, int i) {
            super(appendableCharSequence, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        public AppendableCharSequence parse(ByteBuf byteBuf) {
            reset();
            return super.parse(byteBuf);
        }

        @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser, io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            if (HttpObjectDecoder.this.currentState == State.SKIP_CONTROL_CHARS) {
                char c = (char) (b & 255);
                if (!Character.isISOControl(c) && !Character.isWhitespace(c)) {
                    HttpObjectDecoder.this.currentState = State.READ_INITIAL;
                } else {
                    increaseCount();
                    return true;
                }
            }
            return super.process(b);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        protected TooLongFrameException newException(int i) {
            return new TooLongFrameException("An HTTP line is larger than " + i + " bytes.");
        }
    }
}
