package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import com.shimmerresearch.verisense.UtilVerisenseDriver;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.QueryStringDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class HttpPostMultipartRequestDecoder implements InterfaceHttpPostRequestDecoder {
    private static final String FILENAME_ENCODED = HttpHeaderValues.FILENAME.toString() + '*';
    private final List<InterfaceHttpData> bodyListHttpData;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private final HttpDataFactory factory;
    private final HttpRequest request;
    private int bodyListHttpDataRank;
    private Charset charset;
    private Attribute currentAttribute;
    private Map<CharSequence, Attribute> currentFieldAttributes;
    private FileUpload currentFileUpload;
    private HttpPostRequestDecoder.MultiPartStatus currentStatus;
    private boolean destroyed;
    private int discardThreshold;
    private boolean isLastChunk;
    private String multipartDataBoundary;
    private String multipartMixedBoundary;
    private ByteBuf undecodedChunk;

    public HttpPostMultipartRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) throws NumberFormatException {
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
        this.discardThreshold = 10485760;
        HttpRequest httpRequest2 = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.request = httpRequest2;
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        setMultipart(httpRequest2.headers().get(HttpHeaderNames.CONTENT_TYPE));
        if (httpRequest instanceof HttpContent) {
            offer((HttpContent) httpRequest);
        } else {
            parseBody();
        }
    }

    private static void skipControlCharacters(ByteBuf byteBuf) {
        if (!byteBuf.hasArray()) {
            try {
                skipControlCharactersStandard(byteBuf);
                return;
            } catch (IndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
            }
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
            byte[] bArr = seekAheadOptimize.bytes;
            int i = seekAheadOptimize.pos;
            seekAheadOptimize.pos = i + 1;
            char c = (char) (bArr[i] & 255);
            if (!Character.isISOControl(c) && !Character.isWhitespace(c)) {
                seekAheadOptimize.setReadPosition(1);
                return;
            }
        }
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException("Access out of bounds");
    }

    private static void skipControlCharactersStandard(ByteBuf byteBuf) {
        while (true) {
            char unsignedByte = (char) byteBuf.readUnsignedByte();
            if (!Character.isISOControl(unsignedByte) && !Character.isWhitespace(unsignedByte)) {
                byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                return;
            }
        }
    }

    private static String readLineStandard(ByteBuf byteBuf, Charset charset) {
        int i = byteBuf.readerIndex();
        ByteBuf byteBufHeapBuffer = byteBuf.alloc().heapBuffer(64);
        while (byteBuf.isReadable()) {
            try {
                try {
                    byte b = byteBuf.readByte();
                    if (b == 13) {
                        if (byteBuf.getByte(byteBuf.readerIndex()) == 10) {
                            byteBuf.readByte();
                            return byteBufHeapBuffer.toString(charset);
                        }
                        byteBufHeapBuffer.writeByte(13);
                    } else {
                        if (b == 10) {
                            return byteBufHeapBuffer.toString(charset);
                        }
                        byteBufHeapBuffer.writeByte(b);
                    }
                } catch (IndexOutOfBoundsException e) {
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
                }
            } finally {
                byteBufHeapBuffer.release();
            }
        }
        byteBufHeapBuffer.release();
        byteBuf.readerIndex(i);
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    private static String readLine(ByteBuf byteBuf, Charset charset) {
        if (!byteBuf.hasArray()) {
            return readLineStandard(byteBuf, charset);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int i = byteBuf.readerIndex();
        ByteBuf byteBufHeapBuffer = byteBuf.alloc().heapBuffer(64);
        while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
            try {
                try {
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i2 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i2 + 1;
                    byte b = bArr[i2];
                    if (b == 13) {
                        if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                            byte[] bArr2 = seekAheadOptimize.bytes;
                            int i3 = seekAheadOptimize.pos;
                            seekAheadOptimize.pos = i3 + 1;
                            if (bArr2[i3] == 10) {
                                seekAheadOptimize.setReadPosition(0);
                                return byteBufHeapBuffer.toString(charset);
                            }
                            seekAheadOptimize.pos--;
                            byteBufHeapBuffer.writeByte(13);
                        } else {
                            byteBufHeapBuffer.writeByte(b);
                        }
                    } else {
                        if (b == 10) {
                            seekAheadOptimize.setReadPosition(0);
                            return byteBufHeapBuffer.toString(charset);
                        }
                        byteBufHeapBuffer.writeByte(b);
                    }
                } catch (IndexOutOfBoundsException e) {
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
                }
            } finally {
                byteBufHeapBuffer.release();
            }
        }
        byteBufHeapBuffer.release();
        byteBuf.readerIndex(i);
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    private static String readDelimiterStandard(ByteBuf byteBuf, String str) {
        int i = byteBuf.readerIndex();
        try {
            StringBuilder sb = new StringBuilder(64);
            int length = str.length();
            int i2 = 0;
            while (byteBuf.isReadable() && i2 < length) {
                char c = byteBuf.readByte();
                if (c == str.charAt(i2)) {
                    i2++;
                    sb.append(c);
                } else {
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
            }
            if (byteBuf.isReadable()) {
                byte b = byteBuf.readByte();
                if (b == 13) {
                    if (byteBuf.readByte() == 10) {
                        return sb.toString();
                    }
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
                if (b == 10) {
                    return sb.toString();
                }
                if (b == 45) {
                    sb.append('-');
                    if (byteBuf.readByte() == 45) {
                        sb.append('-');
                        if (byteBuf.isReadable()) {
                            byte b2 = byteBuf.readByte();
                            if (b2 == 13) {
                                if (byteBuf.readByte() == 10) {
                                    return sb.toString();
                                }
                                byteBuf.readerIndex(i);
                                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                            }
                            if (b2 == 10) {
                                return sb.toString();
                            }
                            byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                            return sb.toString();
                        }
                        return sb.toString();
                    }
                }
            }
            byteBuf.readerIndex(i);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(i);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
        }
    }

    private static String readDelimiter(ByteBuf byteBuf, String str) {
        if (!byteBuf.hasArray()) {
            return readDelimiterStandard(byteBuf, str);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int i = byteBuf.readerIndex();
        int length = str.length();
        try {
            StringBuilder sb = new StringBuilder(64);
            int i2 = 0;
            while (seekAheadOptimize.pos < seekAheadOptimize.limit && i2 < length) {
                byte[] bArr = seekAheadOptimize.bytes;
                int i3 = seekAheadOptimize.pos;
                seekAheadOptimize.pos = i3 + 1;
                byte b = bArr[i3];
                if (b == str.charAt(i2)) {
                    i2++;
                    sb.append((char) b);
                } else {
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
            }
            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                byte[] bArr2 = seekAheadOptimize.bytes;
                int i4 = seekAheadOptimize.pos;
                seekAheadOptimize.pos = i4 + 1;
                byte b2 = bArr2[i4];
                if (b2 == 13) {
                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                        byte[] bArr3 = seekAheadOptimize.bytes;
                        int i5 = seekAheadOptimize.pos;
                        seekAheadOptimize.pos = i5 + 1;
                        if (bArr3[i5] == 10) {
                            seekAheadOptimize.setReadPosition(0);
                            return sb.toString();
                        }
                        byteBuf.readerIndex(i);
                        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                    }
                    byteBuf.readerIndex(i);
                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                }
                if (b2 == 10) {
                    seekAheadOptimize.setReadPosition(0);
                    return sb.toString();
                }
                if (b2 == 45) {
                    sb.append('-');
                    if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                        byte[] bArr4 = seekAheadOptimize.bytes;
                        int i6 = seekAheadOptimize.pos;
                        seekAheadOptimize.pos = i6 + 1;
                        if (bArr4[i6] == 45) {
                            sb.append('-');
                            if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                byte[] bArr5 = seekAheadOptimize.bytes;
                                int i7 = seekAheadOptimize.pos;
                                seekAheadOptimize.pos = i7 + 1;
                                byte b3 = bArr5[i7];
                                if (b3 != 13) {
                                    if (b3 == 10) {
                                        seekAheadOptimize.setReadPosition(0);
                                        return sb.toString();
                                    }
                                    seekAheadOptimize.setReadPosition(1);
                                    return sb.toString();
                                }
                                if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                                    byte[] bArr6 = seekAheadOptimize.bytes;
                                    int i8 = seekAheadOptimize.pos;
                                    seekAheadOptimize.pos = i8 + 1;
                                    if (bArr6[i8] == 10) {
                                        seekAheadOptimize.setReadPosition(0);
                                        return sb.toString();
                                    }
                                    byteBuf.readerIndex(i);
                                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                                }
                                byteBuf.readerIndex(i);
                                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                            }
                            seekAheadOptimize.setReadPosition(0);
                            return sb.toString();
                        }
                    }
                }
            }
            byteBuf.readerIndex(i);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(i);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(e);
        }
    }

    private static boolean loadDataMultipartStandard(ByteBuf byteBuf, String str, HttpData httpData) {
        int i = byteBuf.readerIndex();
        int length = str.length();
        boolean z = false;
        int i2 = i;
        byte b = 10;
        int i3 = 0;
        while (true) {
            if (!byteBuf.isReadable()) {
                break;
            }
            byte b2 = byteBuf.readByte();
            if (b == 10 && b2 == str.codePointAt(i3)) {
                i3++;
                if (length == i3) {
                    z = true;
                    break;
                }
            } else {
                i2 = byteBuf.readerIndex();
                if (b2 == 10) {
                    i2 -= b == 13 ? 2 : 1;
                    i3 = 0;
                }
                b = b2;
            }
        }
        if (b == 13) {
            i2--;
        }
        try {
            httpData.addContent(byteBuf.retainedSlice(i, i2 - i), z);
            byteBuf.readerIndex(i2);
            return z;
        } catch (IOException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        }
    }

    private static boolean loadDataMultipart(ByteBuf byteBuf, String str, HttpData httpData) {
        if (!byteBuf.hasArray()) {
            return loadDataMultipartStandard(byteBuf, str, httpData);
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
        int i = byteBuf.readerIndex();
        int length = str.length();
        int i2 = seekAheadOptimize.pos;
        boolean z = false;
        byte b = 10;
        int i3 = 0;
        while (true) {
            if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                break;
            }
            byte[] bArr = seekAheadOptimize.bytes;
            int i4 = seekAheadOptimize.pos;
            seekAheadOptimize.pos = i4 + 1;
            byte b2 = bArr[i4];
            if (b == 10 && b2 == str.codePointAt(i3)) {
                i3++;
                if (length == i3) {
                    z = true;
                    break;
                }
            } else {
                i2 = seekAheadOptimize.pos;
                if (b2 == 10) {
                    i2 -= b == 13 ? 2 : 1;
                    i3 = 0;
                }
                b = b2;
            }
        }
        if (b == 13) {
            i2--;
        }
        int readPosition = seekAheadOptimize.getReadPosition(i2);
        try {
            httpData.addContent(byteBuf.retainedSlice(i, readPosition - i), z);
            byteBuf.readerIndex(readPosition);
            return z;
        } catch (IOException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String cleanString(java.lang.String r5) {
        /*
            int r0 = r5.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r2 = 0
        La:
            if (r2 >= r0) goto L34
            char r3 = r5.charAt(r2)
            r4 = 9
            if (r3 == r4) goto L2c
            r4 = 34
            if (r3 == r4) goto L31
            r4 = 44
            if (r3 == r4) goto L2c
            r4 = 61
            if (r3 == r4) goto L2c
            r4 = 58
            if (r3 == r4) goto L2c
            r4 = 59
            if (r3 == r4) goto L2c
            r1.append(r3)
            goto L31
        L2c:
            r3 = 32
            r1.append(r3)
        L31:
            int r2 = r2 + 1
            goto La
        L34:
            java.lang.String r5 = r1.toString()
            java.lang.String r5 = r5.trim()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.cleanString(java.lang.String):java.lang.String");
    }

    private static String[] splitMultipartHeader(String str) {
        String[] strArrSplit;
        char cCharAt;
        ArrayList arrayList = new ArrayList(1);
        int iFindNonWhitespace = HttpPostBodyUtil.findNonWhitespace(str, 0);
        int i = iFindNonWhitespace;
        while (i < str.length() && (cCharAt = str.charAt(i)) != ':' && !Character.isWhitespace(cCharAt)) {
            i++;
        }
        int i2 = i;
        while (true) {
            if (i2 >= str.length()) {
                break;
            }
            if (str.charAt(i2) == ':') {
                i2++;
                break;
            }
            i2++;
        }
        int iFindNonWhitespace2 = HttpPostBodyUtil.findNonWhitespace(str, i2);
        int iFindEndOfString = HttpPostBodyUtil.findEndOfString(str);
        arrayList.add(str.substring(iFindNonWhitespace, i));
        String strSubstring = iFindNonWhitespace2 >= iFindEndOfString ? "" : str.substring(iFindNonWhitespace2, iFindEndOfString);
        if (strSubstring.indexOf(59) >= 0) {
            strArrSplit = splitMultipartHeaderValues(strSubstring);
        } else {
            strArrSplit = strSubstring.split(UtilVerisenseDriver.CSV_DELIMITER);
        }
        for (String str2 : strArrSplit) {
            arrayList.add(str2.trim());
        }
        String[] strArr = new String[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            strArr[i3] = (String) arrayList.get(i3);
        }
        return strArr;
    }

    private static String[] splitMultipartHeaderValues(String str) {
        ArrayList arrayList = InternalThreadLocalMap.get().arrayList(1);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (z) {
                if (z2) {
                    z2 = false;
                } else if (cCharAt == '\\') {
                    z2 = true;
                } else if (cCharAt == '\"') {
                    z = false;
                }
            } else if (cCharAt == '\"') {
                z = true;
            } else if (cCharAt == ';') {
                arrayList.add(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        arrayList.add(str.substring(i));
        return (String[]) arrayList.toArray(new String[0]);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData currentPartialHttpData() {
        FileUpload fileUpload = this.currentFileUpload;
        return fileUpload != null ? fileUpload : this.currentAttribute;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public int getDiscardThreshold() {
        return this.discardThreshold;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void setDiscardThreshold(int i) {
        this.discardThreshold = ObjectUtil.checkPositiveOrZero(i, "discardThreshold");
    }

    private void checkDestroyed() {
        if (this.destroyed) {
            throw new IllegalStateException("HttpPostMultipartRequestDecoder was destroyed already");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        checkDestroyed();
        return true;
    }

    private void setMultipart(String str) {
        String str2;
        String[] multipartDataBoundary = HttpPostRequestDecoder.getMultipartDataBoundary(str);
        if (multipartDataBoundary != null) {
            this.multipartDataBoundary = multipartDataBoundary[0];
            if (multipartDataBoundary.length > 1 && (str2 = multipartDataBoundary[1]) != null) {
                this.charset = Charset.forName(str2);
            }
        } else {
            this.multipartDataBoundary = null;
        }
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas() {
        checkDestroyed();
        if (this.isLastChunk) {
            return this.bodyListHttpData;
        }
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        return this.bodyMapHttpData.get(str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData getBodyHttpData(String str) {
        checkDestroyed();
        if (!this.isLastChunk) {
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        }
        List<InterfaceHttpData> list = this.bodyMapHttpData.get(str);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public HttpPostMultipartRequestDecoder offer(HttpContent httpContent) throws NumberFormatException {
        ByteBuf byteBufWriteBytes;
        checkDestroyed();
        if (httpContent instanceof LastHttpContent) {
            this.isLastChunk = true;
        }
        ByteBuf byteBufContent = httpContent.content();
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            if (this.isLastChunk) {
                byteBufWriteBytes = byteBufContent.retainedSlice();
            } else {
                byteBufWriteBytes = byteBufContent.alloc().buffer(byteBufContent.readableBytes()).writeBytes(byteBufContent);
            }
            this.undecodedChunk = byteBufWriteBytes;
        } else {
            byteBuf.writeBytes(byteBufContent);
        }
        parseBody();
        ByteBuf byteBuf2 = this.undecodedChunk;
        if (byteBuf2 != null && byteBuf2.writerIndex() > this.discardThreshold) {
            this.undecodedChunk.discardReadBytes();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean hasNext() {
        checkDestroyed();
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return !this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData next() {
        checkDestroyed();
        if (!hasNext()) {
            return null;
        }
        List<InterfaceHttpData> list = this.bodyListHttpData;
        int i = this.bodyListHttpDataRank;
        this.bodyListHttpDataRank = i + 1;
        return list.get(i);
    }

    private void parseBody() throws NumberFormatException {
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE && this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
            parseBodyMultipart();
        } else if (this.isLastChunk) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
        }
    }

    protected void addHttpData(InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData == null) {
            return;
        }
        List<InterfaceHttpData> arrayList = this.bodyMapHttpData.get(interfaceHttpData.getName());
        if (arrayList == null) {
            arrayList = new ArrayList<>(1);
            this.bodyMapHttpData.put(interfaceHttpData.getName(), arrayList);
        }
        arrayList.add(interfaceHttpData);
        this.bodyListHttpData.add(interfaceHttpData);
    }

    private void parseBodyMultipart() throws NumberFormatException {
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null || byteBuf.readableBytes() == 0) {
            return;
        }
        InterfaceHttpData interfaceHttpDataDecodeMultipart = decodeMultipart(this.currentStatus);
        while (interfaceHttpDataDecodeMultipart != null) {
            addHttpData(interfaceHttpDataDecodeMultipart);
            if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE || this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
                return;
            } else {
                interfaceHttpDataDecodeMultipart = decodeMultipart(this.currentStatus);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008c A[Catch: IOException -> 0x00b9, IllegalArgumentException -> 0x00c0, NullPointerException -> 0x00c7, TRY_ENTER, TryCatch #4 {IOException -> 0x00b9, IllegalArgumentException -> 0x00c0, NullPointerException -> 0x00c7, blocks: (B:39:0x008c, B:40:0x009f), top: B:69:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009f A[Catch: IOException -> 0x00b9, IllegalArgumentException -> 0x00c0, NullPointerException -> 0x00c7, TRY_LEAVE, TryCatch #4 {IOException -> 0x00b9, IllegalArgumentException -> 0x00c0, NullPointerException -> 0x00c7, blocks: (B:39:0x008c, B:40:0x009f), top: B:69:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData decodeMultipart(io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus r8) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.decodeMultipart(io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus):io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData");
    }

    private InterfaceHttpData findMultipartDelimiter(String str, HttpPostRequestDecoder.MultiPartStatus multiPartStatus, HttpPostRequestDecoder.MultiPartStatus multiPartStatus2) {
        int i = this.undecodedChunk.readerIndex();
        try {
            skipControlCharacters(this.undecodedChunk);
            skipOneLine();
            try {
                String delimiter = readDelimiter(this.undecodedChunk, str);
                if (delimiter.equals(str)) {
                    this.currentStatus = multiPartStatus;
                    return decodeMultipart(multiPartStatus);
                }
                if (delimiter.equals(str + "--")) {
                    this.currentStatus = multiPartStatus2;
                    if (multiPartStatus2 != HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER) {
                        return null;
                    }
                    this.currentFieldAttributes = null;
                    return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER);
                }
                this.undecodedChunk.readerIndex(i);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("No Multipart delimiter found");
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(i);
                return null;
            }
        } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused2) {
            this.undecodedChunk.readerIndex(i);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData findMultipartDisposition() {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.findMultipartDisposition():io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData");
    }

    private Attribute getContentDispositionAttribute(String... strArr) {
        String strCleanString = cleanString(strArr[0]);
        String strDecodeComponent = strArr[1];
        if (HttpHeaderValues.FILENAME.contentEquals(strCleanString)) {
            int length = strDecodeComponent.length() - 1;
            if (length > 0 && strDecodeComponent.charAt(0) == '\"' && strDecodeComponent.charAt(length) == '\"') {
                strDecodeComponent = strDecodeComponent.substring(1, length);
            }
        } else if (FILENAME_ENCODED.equals(strCleanString)) {
            try {
                strCleanString = HttpHeaderValues.FILENAME.toString();
                String[] strArrSplit = cleanString(strDecodeComponent).split("'", 3);
                strDecodeComponent = QueryStringDecoder.decodeComponent(strArrSplit[2], Charset.forName(strArrSplit[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            } catch (UnsupportedCharsetException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
            }
        } else {
            strDecodeComponent = cleanString(strDecodeComponent);
        }
        return this.factory.createAttribute(this.request, strCleanString, strDecodeComponent);
    }

    protected InterfaceHttpData getFileUpload(String str) throws NumberFormatException {
        String value;
        Attribute attribute = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        Charset charsetForName = this.charset;
        HttpPostBodyUtil.TransferEncodingMechanism transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT7;
        if (attribute != null) {
            try {
                String lowerCase = attribute.getValue().toLowerCase();
                if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT7.value())) {
                    charsetForName = CharsetUtil.US_ASCII;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT8.value())) {
                    charsetForName = CharsetUtil.ISO_8859_1;
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT8;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value())) {
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BINARY;
                } else {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException("TransferEncoding Unknown: " + lowerCase);
                }
            } catch (IOException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            }
        }
        Attribute attribute2 = this.currentFieldAttributes.get(HttpHeaderValues.CHARSET);
        if (attribute2 != null) {
            try {
                charsetForName = Charset.forName(attribute2.getValue());
            } catch (IOException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e2);
            } catch (UnsupportedCharsetException e3) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e3);
            }
        }
        Charset charset = charsetForName;
        if (this.currentFileUpload == null) {
            Attribute attribute3 = this.currentFieldAttributes.get(HttpHeaderValues.FILENAME);
            Attribute attribute4 = this.currentFieldAttributes.get(HttpHeaderValues.NAME);
            Attribute attribute5 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TYPE);
            Attribute attribute6 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_LENGTH);
            long j = 0;
            if (attribute6 != null) {
                try {
                    j = Long.parseLong(attribute6.getValue());
                } catch (IOException e4) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e4);
                } catch (NumberFormatException unused) {
                }
            }
            long j2 = j;
            if (attribute5 != null) {
                try {
                    value = attribute5.getValue();
                } catch (IOException e5) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e5);
                } catch (IllegalArgumentException e6) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e6);
                } catch (NullPointerException e7) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException(e7);
                }
            } else {
                value = "application/octet-stream";
            }
            this.currentFileUpload = this.factory.createFileUpload(this.request, cleanString(attribute4.getValue()), cleanString(attribute3.getValue()), value, transferEncodingMechanism.value(), charset, j2);
        }
        if (!loadDataMultipart(this.undecodedChunk, str, this.currentFileUpload) || !this.currentFileUpload.isCompleted()) {
            return null;
        }
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
            this.currentFieldAttributes = null;
        } else {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER;
            cleanMixedAttributes();
        }
        FileUpload fileUpload = this.currentFileUpload;
        this.currentFileUpload = null;
        return fileUpload;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void destroy() {
        cleanFiles();
        this.destroyed = true;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null || byteBuf.refCnt() <= 0) {
            return;
        }
        this.undecodedChunk.release();
        this.undecodedChunk = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void cleanFiles() {
        checkDestroyed();
        this.factory.cleanRequestHttpData(this.request);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        checkDestroyed();
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }

    private void cleanMixedAttributes() {
        this.currentFieldAttributes.remove(HttpHeaderValues.CHARSET);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_LENGTH);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TYPE);
        this.currentFieldAttributes.remove(HttpHeaderValues.FILENAME);
    }

    private boolean skipOneLine() {
        if (!this.undecodedChunk.isReadable()) {
            return false;
        }
        byte b = this.undecodedChunk.readByte();
        if (b != 13) {
            if (b == 10) {
                return true;
            }
            ByteBuf byteBuf = this.undecodedChunk;
            byteBuf.readerIndex(byteBuf.readerIndex() - 1);
            return false;
        }
        if (!this.undecodedChunk.isReadable()) {
            ByteBuf byteBuf2 = this.undecodedChunk;
            byteBuf2.readerIndex(byteBuf2.readerIndex() - 1);
            return false;
        }
        if (this.undecodedChunk.readByte() == 10) {
            return true;
        }
        this.undecodedChunk.readerIndex(r0.readerIndex() - 2);
        return false;
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus;

        static {
            int[] iArr = new int[HttpPostRequestDecoder.MultiPartStatus.values().length];
            $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus = iArr;
            try {
                iArr[HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.PREAMBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.DISPOSITION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.FIELD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.MIXEDDISPOSITION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.EPILOGUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }
}
