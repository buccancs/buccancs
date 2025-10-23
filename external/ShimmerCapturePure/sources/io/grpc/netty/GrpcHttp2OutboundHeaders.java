package io.grpc.netty;

import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class GrpcHttp2OutboundHeaders extends AbstractHttp2Headers {
    private static final AsciiString[] EMPTY = new AsciiString[0];
    private final AsciiString[] normalHeaders;
    private final AsciiString[] preHeaders;

    private GrpcHttp2OutboundHeaders(AsciiString[] asciiStringArr, byte[][] bArr) {
        this.normalHeaders = new AsciiString[bArr.length];
        int i = 0;
        while (true) {
            AsciiString[] asciiStringArr2 = this.normalHeaders;
            if (i >= asciiStringArr2.length) {
                this.preHeaders = asciiStringArr;
                return;
            } else {
                asciiStringArr2[i] = new AsciiString(bArr[i], false);
                i++;
            }
        }
    }

    static GrpcHttp2OutboundHeaders clientRequestHeaders(byte[][] bArr, AsciiString asciiString, AsciiString asciiString2, AsciiString asciiString3, AsciiString asciiString4, AsciiString asciiString5) {
        return new GrpcHttp2OutboundHeaders(new AsciiString[]{Http2Headers.PseudoHeaderName.AUTHORITY.value(), asciiString, Http2Headers.PseudoHeaderName.PATH.value(), asciiString2, Http2Headers.PseudoHeaderName.METHOD.value(), asciiString3, Http2Headers.PseudoHeaderName.SCHEME.value(), asciiString4, Utils.CONTENT_TYPE_HEADER, Utils.CONTENT_TYPE_GRPC, Utils.TE_HEADER, Utils.TE_TRAILERS, Utils.USER_AGENT, asciiString5}, bArr);
    }

    static GrpcHttp2OutboundHeaders serverResponseHeaders(byte[][] bArr) {
        return new GrpcHttp2OutboundHeaders(new AsciiString[]{Http2Headers.PseudoHeaderName.STATUS.value(), Utils.STATUS_OK, Utils.CONTENT_TYPE_HEADER, Utils.CONTENT_TYPE_GRPC}, bArr);
    }

    static GrpcHttp2OutboundHeaders serverResponseTrailers(byte[][] bArr) {
        return new GrpcHttp2OutboundHeaders(EMPTY, bArr);
    }

    @Override // io.grpc.netty.AbstractHttp2Headers
    public CharSequence status() {
        AsciiString[] asciiStringArr = this.preHeaders;
        if (asciiStringArr.length < 2 || asciiStringArr[0] != Http2Headers.PseudoHeaderName.STATUS.value()) {
            return null;
        }
        return this.preHeaders[1];
    }

    @Override // io.grpc.netty.AbstractHttp2Headers
    public Iterator<Map.Entry<CharSequence, CharSequence>> iterator() {
        return new Itr();
    }

    @Override // io.grpc.netty.AbstractHttp2Headers
    public int size() {
        return (this.normalHeaders.length + this.preHeaders.length) / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append('[');
        Iterator<Map.Entry<CharSequence, CharSequence>> it2 = iterator();
        String str = "";
        while (it2.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = it2.next();
            CharSequence key = next.getKey();
            CharSequence value = next.getValue();
            sb.append(str);
            sb.append(key);
            sb.append(": ");
            sb.append(value);
            str = ", ";
        }
        sb.append(']');
        return sb.toString();
    }

    private class Itr implements Map.Entry<CharSequence, CharSequence>, Iterator<Map.Entry<CharSequence, CharSequence>> {
        private AsciiString[] current;
        private int idx;
        private AsciiString key;
        private AsciiString value;

        private Itr() {
            this.current = GrpcHttp2OutboundHeaders.this.preHeaders.length != 0 ? GrpcHttp2OutboundHeaders.this.preHeaders : GrpcHttp2OutboundHeaders.this.normalHeaders;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public CharSequence getKey() {
            return this.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public CharSequence getValue() {
            return this.value;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.idx < this.current.length;
        }

        @Override // java.util.Iterator
        public Map.Entry<CharSequence, CharSequence> next() {
            if (hasNext()) {
                AsciiString[] asciiStringArr = this.current;
                int i = this.idx;
                this.key = asciiStringArr[i];
                this.value = asciiStringArr[i + 1];
                int i2 = i + 2;
                this.idx = i2;
                if (i2 >= asciiStringArr.length && asciiStringArr == GrpcHttp2OutboundHeaders.this.preHeaders) {
                    this.current = GrpcHttp2OutboundHeaders.this.normalHeaders;
                    this.idx = 0;
                }
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Map.Entry
        public CharSequence setValue(CharSequence charSequence) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
