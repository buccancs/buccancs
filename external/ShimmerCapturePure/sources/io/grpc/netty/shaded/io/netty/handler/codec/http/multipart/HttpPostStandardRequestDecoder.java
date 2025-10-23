package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.QueryStringDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class HttpPostStandardRequestDecoder implements InterfaceHttpPostRequestDecoder {
    private final List<InterfaceHttpData> bodyListHttpData;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private final Charset charset;
    private final HttpDataFactory factory;
    private final HttpRequest request;
    private int bodyListHttpDataRank;
    private Attribute currentAttribute;
    private HttpPostRequestDecoder.MultiPartStatus currentStatus;
    private boolean destroyed;
    private int discardThreshold;
    private boolean isLastChunk;
    private ByteBuf undecodedChunk;

    public HttpPostStandardRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostStandardRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostStandardRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset) throws Throwable {
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
        this.discardThreshold = 10485760;
        this.request = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        try {
            if (httpRequest instanceof HttpContent) {
                offer((HttpContent) httpRequest);
            } else {
                parseBody();
            }
        } catch (Throwable th) {
            destroy();
            PlatformDependent.throwException(th);
        }
    }

    private static String decodeAttribute(String str, Charset charset) {
        try {
            return QueryStringDecoder.decodeComponent(str, charset);
        } catch (IllegalArgumentException e) {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad string: '" + str + '\'', e);
        }
    }

    private static ByteBuf decodeAttribute(ByteBuf byteBuf, Charset charset) {
        if (byteBuf.forEachByte(new UrlEncodedDetector(null)) == -1) {
            return null;
        }
        ByteBuf byteBufBuffer = byteBuf.alloc().buffer(byteBuf.readableBytes());
        UrlDecoder urlDecoder = new UrlDecoder(byteBufBuffer);
        int iForEachByte = byteBuf.forEachByte(urlDecoder);
        if (urlDecoder.nextEscapedIdx == 0) {
            return byteBufBuffer;
        }
        if (iForEachByte == -1) {
            iForEachByte = byteBuf.readableBytes() - 1;
        }
        int i = iForEachByte - (urlDecoder.nextEscapedIdx - 1);
        byteBufBuffer.release();
        throw new HttpPostRequestDecoder.ErrorDataDecoderException(String.format("Invalid hex byte at index '%d' in string: '%s'", Integer.valueOf(i), byteBuf.toString(charset)));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public InterfaceHttpData currentPartialHttpData() {
        return this.currentAttribute;
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
            throw new IllegalStateException("HttpPostStandardRequestDecoder was destroyed already");
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder
    public boolean isMultipart() {
        checkDestroyed();
        return false;
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
    public HttpPostStandardRequestDecoder offer(HttpContent httpContent) {
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

    private void parseBody() {
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE && this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
            parseBodyAttributes();
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

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e9, code lost:

        if (r10.isLastChunk == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00eb, code lost:

        r1 = r10.currentAttribute;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ed, code lost:

        if (r1 == null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ef, code lost:

        if (r0 <= r2) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f1, code lost:

        setFinalBuffer(r10.undecodedChunk.retainedSlice(r2, r0 - r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0101, code lost:

        if (r1.isCompleted() != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0103, code lost:

        setFinalBuffer(io.grpc.netty.shaded.io.netty.buffer.Unpooled.EMPTY_BUFFER);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0108, code lost:

        r10.currentStatus = io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x010d, code lost:

        if (r3 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0111, code lost:

        if (r10.currentAttribute == null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0117, code lost:

        if (r10.currentStatus != io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.FIELD) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0119, code lost:

        r10.currentAttribute.addContent(r10.undecodedChunk.retainedSlice(r2, r0 - r2), false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0127, code lost:

        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0128, code lost:

        r10.undecodedChunk.readerIndex(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x012d, code lost:

        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x012e, code lost:

        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x012f, code lost:

        r2 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0132, code lost:

        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0133, code lost:

        r2 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0136, code lost:

        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0137, code lost:

        r2 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013b, code lost:

        r10.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0145, code lost:

        throw new io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0147, code lost:

        r10.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0151, code lost:

        throw new io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0153, code lost:

        r10.undecodedChunk.readerIndex(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0158, code lost:

        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseBodyAttributesStandard() {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostStandardRequestDecoder.parseBodyAttributesStandard():void");
    }

    private void parseBodyAttributes() {
        int i;
        boolean z;
        Attribute attribute;
        int i2;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            return;
        }
        if (!byteBuf.hasArray()) {
            parseBodyAttributesStandard();
            return;
        }
        HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(this.undecodedChunk);
        int i3 = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
        }
        loop0:
        while (true) {
            i = i3;
            while (true) {
                try {
                    z = true;
                    if (seekAheadOptimize.pos >= seekAheadOptimize.limit) {
                        break loop0;
                    }
                    byte[] bArr = seekAheadOptimize.bytes;
                    int i4 = seekAheadOptimize.pos;
                    seekAheadOptimize.pos = i4 + 1;
                    char c = (char) (bArr[i4] & 255);
                    i2 = i3 + 1;
                    int i5 = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[this.currentStatus.ordinal()];
                    if (i5 != 1) {
                        if (i5 != 2) {
                            seekAheadOptimize.setReadPosition(0);
                            i3 = i2;
                            break loop0;
                        }
                        if (c == '&') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                            setFinalBuffer(this.undecodedChunk.retainedSlice(i, i3 - i));
                            break;
                        }
                        if (c != '\r') {
                            if (c == '\n') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                seekAheadOptimize.setReadPosition(0);
                                setFinalBuffer(this.undecodedChunk.retainedSlice(i, i3 - i));
                                break loop0;
                            }
                            i3 = i2;
                        } else if (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                            byte[] bArr2 = seekAheadOptimize.bytes;
                            int i6 = seekAheadOptimize.pos;
                            seekAheadOptimize.pos = i6 + 1;
                            char c2 = (char) (bArr2[i6] & 255);
                            i2 = i3 + 2;
                            if (c2 == '\n') {
                                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                                seekAheadOptimize.setReadPosition(0);
                                setFinalBuffer(this.undecodedChunk.retainedSlice(i, i3 - i));
                            } else {
                                seekAheadOptimize.setReadPosition(0);
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad end of line");
                            }
                        } else if (seekAheadOptimize.limit <= 0) {
                            i3 = i2;
                        }
                    } else {
                        if (c == '=') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
                            this.currentAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, i3 - i, this.charset), this.charset));
                            break;
                        }
                        if (c == '&') {
                            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
                            Attribute attributeCreateAttribute = this.factory.createAttribute(this.request, decodeAttribute(this.undecodedChunk.toString(i, i3 - i, this.charset), this.charset));
                            this.currentAttribute = attributeCreateAttribute;
                            attributeCreateAttribute.setValue("");
                            addHttpData(this.currentAttribute);
                            this.currentAttribute = null;
                            break;
                        }
                        i3 = i2;
                    }
                } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
                    e = e;
                } catch (IOException e2) {
                    e = e2;
                } catch (IllegalArgumentException e3) {
                    e = e3;
                }
            }
            i3 = i2;
        }
        i3 = i2;
        i = i3;
        z = false;
        if (this.isLastChunk && (attribute = this.currentAttribute) != null) {
            if (i3 > i) {
                setFinalBuffer(this.undecodedChunk.retainedSlice(i, i3 - i));
            } else if (!attribute.isCompleted()) {
                setFinalBuffer(Unpooled.EMPTY_BUFFER);
            }
            try {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
            } catch (HttpPostRequestDecoder.ErrorDataDecoderException e4) {
                e = e4;
                i = i3;
                this.undecodedChunk.readerIndex(i);
                throw e;
            } catch (IOException e5) {
                e = e5;
                i = i3;
                this.undecodedChunk.readerIndex(i);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            } catch (IllegalArgumentException e6) {
                e = e6;
                i = i3;
                this.undecodedChunk.readerIndex(i);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
            }
        } else {
            if (z && this.currentAttribute != null && this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FIELD) {
                this.currentAttribute.addContent(this.undecodedChunk.retainedSlice(i, i3 - i), false);
            }
            this.undecodedChunk.readerIndex(i);
        }
        i = i3;
        this.undecodedChunk.readerIndex(i);
    }

    private void setFinalBuffer(ByteBuf byteBuf) throws IOException {
        this.currentAttribute.addContent(byteBuf, true);
        ByteBuf byteBufDecodeAttribute = decodeAttribute(this.currentAttribute.getByteBuf(), this.charset);
        if (byteBufDecodeAttribute != null) {
            this.currentAttribute.setContent(byteBufDecodeAttribute);
        }
        addHttpData(this.currentAttribute);
        this.currentAttribute = null;
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

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpPostStandardRequestDecoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus;

        static {
            int[] iArr = new int[HttpPostRequestDecoder.MultiPartStatus.values().length];
            $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus = iArr;
            try {
                iArr[HttpPostRequestDecoder.MultiPartStatus.DISPOSITION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus[HttpPostRequestDecoder.MultiPartStatus.FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static final class UrlEncodedDetector implements ByteProcessor {
        private UrlEncodedDetector() {
        }

        /* synthetic */ UrlEncodedDetector(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) throws Exception {
            return (b == 37 || b == 43) ? false : true;
        }
    }

    private static final class UrlDecoder implements ByteProcessor {
        private final ByteBuf output;
        private byte hiByte;
        private int nextEscapedIdx;

        UrlDecoder(ByteBuf byteBuf) {
            this.output = byteBuf;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) {
            int i = this.nextEscapedIdx;
            if (i != 0) {
                if (i == 1) {
                    this.hiByte = b;
                    this.nextEscapedIdx = i + 1;
                } else {
                    int iDecodeHexNibble = StringUtil.decodeHexNibble((char) this.hiByte);
                    int iDecodeHexNibble2 = StringUtil.decodeHexNibble((char) b);
                    if (iDecodeHexNibble == -1 || iDecodeHexNibble2 == -1) {
                        this.nextEscapedIdx++;
                        return false;
                    }
                    this.output.writeByte((iDecodeHexNibble << 4) + iDecodeHexNibble2);
                    this.nextEscapedIdx = 0;
                }
            } else if (b == 37) {
                this.nextEscapedIdx = 1;
            } else if (b == 43) {
                this.output.writeByte(32);
            } else {
                this.output.writeByte(b);
            }
            return true;
        }
    }
}
