package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedMessageTypeException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.protocol.HTTP;

/* loaded from: classes3.dex */
public class SpdyHttpEncoder extends MessageToMessageEncoder<HttpObject> {
    private final boolean headersToLowerCase;
    private final boolean validateHeaders;
    private int currentStreamId;

    public SpdyHttpEncoder(SpdyVersion spdyVersion) {
        this(spdyVersion, true, true);
    }

    public SpdyHttpEncoder(SpdyVersion spdyVersion, boolean z, boolean z2) {
        ObjectUtil.checkNotNull(spdyVersion, "version");
        this.headersToLowerCase = z;
        this.validateHeaders = z2;
    }

    private static boolean isLast(HttpMessage httpMessage) {
        if (!(httpMessage instanceof FullHttpMessage)) {
            return false;
        }
        FullHttpMessage fullHttpMessage = (FullHttpMessage) httpMessage;
        return fullHttpMessage.trailingHeaders().isEmpty() && !fullHttpMessage.content().isReadable();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List list) throws Exception {
        encode2(channelHandlerContext, httpObject, (List<Object>) list);
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        boolean zIsLast;
        boolean z;
        if (httpObject instanceof HttpRequest) {
            SpdySynStreamFrame spdySynStreamFrameCreateSynStreamFrame = createSynStreamFrame((HttpRequest) httpObject);
            list.add(spdySynStreamFrameCreateSynStreamFrame);
            zIsLast = spdySynStreamFrameCreateSynStreamFrame.isLast() || spdySynStreamFrameCreateSynStreamFrame.isUnidirectional();
            z = true;
        } else {
            zIsLast = false;
            z = false;
        }
        if (httpObject instanceof HttpResponse) {
            SpdyHeadersFrame spdyHeadersFrameCreateHeadersFrame = createHeadersFrame((HttpResponse) httpObject);
            list.add(spdyHeadersFrameCreateHeadersFrame);
            zIsLast = spdyHeadersFrameCreateHeadersFrame.isLast();
            z = true;
        }
        if (!(httpObject instanceof HttpContent) || zIsLast) {
            if (!z) {
                throw new UnsupportedMessageTypeException(httpObject, (Class<?>[]) new Class[0]);
            }
            return;
        }
        HttpContent httpContent = (HttpContent) httpObject;
        httpContent.content().retain();
        DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(this.currentStreamId, httpContent.content());
        if (httpContent instanceof LastHttpContent) {
            HttpHeaders httpHeadersTrailingHeaders = ((LastHttpContent) httpContent).trailingHeaders();
            if (httpHeadersTrailingHeaders.isEmpty()) {
                defaultSpdyDataFrame.setLast(true);
                list.add(defaultSpdyDataFrame);
                return;
            }
            DefaultSpdyHeadersFrame defaultSpdyHeadersFrame = new DefaultSpdyHeadersFrame(this.currentStreamId, this.validateHeaders);
            defaultSpdyHeadersFrame.setLast(true);
            Iterator<Map.Entry<CharSequence, CharSequence>> itIteratorCharSequence = httpHeadersTrailingHeaders.iteratorCharSequence();
            while (itIteratorCharSequence.hasNext()) {
                Map.Entry<CharSequence, CharSequence> next = itIteratorCharSequence.next();
                defaultSpdyHeadersFrame.headers().add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
            }
            list.add(defaultSpdyDataFrame);
            list.add(defaultSpdyHeadersFrame);
            return;
        }
        list.add(defaultSpdyDataFrame);
    }

    private SpdySynStreamFrame createSynStreamFrame(HttpRequest httpRequest) throws Exception {
        HttpHeaders httpHeadersHeaders = httpRequest.headers();
        int iIntValue = httpHeadersHeaders.getInt(SpdyHttpHeaders.Names.STREAM_ID).intValue();
        int i = httpHeadersHeaders.getInt(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID, 0);
        byte b = (byte) httpHeadersHeaders.getInt(SpdyHttpHeaders.Names.PRIORITY, 0);
        String str = httpHeadersHeaders.get(SpdyHttpHeaders.Names.SCHEME);
        httpHeadersHeaders.remove(SpdyHttpHeaders.Names.STREAM_ID);
        httpHeadersHeaders.remove(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID);
        httpHeadersHeaders.remove(SpdyHttpHeaders.Names.PRIORITY);
        httpHeadersHeaders.remove(SpdyHttpHeaders.Names.SCHEME);
        httpHeadersHeaders.remove(HttpHeaderNames.CONNECTION);
        httpHeadersHeaders.remove(HTTP.CONN_KEEP_ALIVE);
        httpHeadersHeaders.remove("Proxy-Connection");
        httpHeadersHeaders.remove(HttpHeaderNames.TRANSFER_ENCODING);
        DefaultSpdySynStreamFrame defaultSpdySynStreamFrame = new DefaultSpdySynStreamFrame(iIntValue, i, b, this.validateHeaders);
        SpdyHeaders spdyHeadersHeaders = defaultSpdySynStreamFrame.headers();
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.METHOD, (AsciiString) httpRequest.method().name());
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.PATH, (AsciiString) httpRequest.uri());
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (AsciiString) httpRequest.protocolVersion().text());
        String str2 = httpHeadersHeaders.get(HttpHeaderNames.HOST);
        httpHeadersHeaders.remove(HttpHeaderNames.HOST);
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.HOST, (AsciiString) str2);
        if (str == null) {
            str = "https";
        }
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.SCHEME, (AsciiString) str);
        Iterator<Map.Entry<CharSequence, CharSequence>> itIteratorCharSequence = httpHeadersHeaders.iteratorCharSequence();
        while (itIteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = itIteratorCharSequence.next();
            spdyHeadersHeaders.add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
        }
        this.currentStreamId = defaultSpdySynStreamFrame.streamId();
        if (i == 0) {
            defaultSpdySynStreamFrame.setLast(isLast(httpRequest));
        } else {
            defaultSpdySynStreamFrame.setUnidirectional(true);
        }
        return defaultSpdySynStreamFrame;
    }

    private SpdyHeadersFrame createHeadersFrame(HttpResponse httpResponse) throws Exception {
        SpdyHeadersFrame defaultSpdySynReplyFrame;
        HttpHeaders httpHeadersHeaders = httpResponse.headers();
        int iIntValue = httpHeadersHeaders.getInt(SpdyHttpHeaders.Names.STREAM_ID).intValue();
        httpHeadersHeaders.remove(SpdyHttpHeaders.Names.STREAM_ID);
        httpHeadersHeaders.remove(HttpHeaderNames.CONNECTION);
        httpHeadersHeaders.remove(HTTP.CONN_KEEP_ALIVE);
        httpHeadersHeaders.remove("Proxy-Connection");
        httpHeadersHeaders.remove(HttpHeaderNames.TRANSFER_ENCODING);
        if (SpdyCodecUtil.isServerId(iIntValue)) {
            defaultSpdySynReplyFrame = new DefaultSpdyHeadersFrame(iIntValue, this.validateHeaders);
        } else {
            defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(iIntValue, this.validateHeaders);
        }
        SpdyHeaders spdyHeadersHeaders = defaultSpdySynReplyFrame.headers();
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.STATUS, httpResponse.status().codeAsText());
        spdyHeadersHeaders.set((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (AsciiString) httpResponse.protocolVersion().text());
        Iterator<Map.Entry<CharSequence, CharSequence>> itIteratorCharSequence = httpHeadersHeaders.iteratorCharSequence();
        while (itIteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = itIteratorCharSequence.next();
            defaultSpdySynReplyFrame.headers().add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
        }
        this.currentStreamId = iIntValue;
        defaultSpdySynReplyFrame.setLast(isLast(httpResponse));
        return defaultSpdySynReplyFrame;
    }
}
