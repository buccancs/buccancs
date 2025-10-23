package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SpdyHttpDecoder extends MessageToMessageDecoder<SpdyFrame> {
    private final int maxContentLength;
    private final Map<Integer, FullHttpMessage> messageMap;
    private final int spdyVersion;
    private final boolean validateHeaders;

    public SpdyHttpDecoder(SpdyVersion spdyVersion, int i) {
        this(spdyVersion, i, new HashMap(), true);
    }

    public SpdyHttpDecoder(SpdyVersion spdyVersion, int i, boolean z) {
        this(spdyVersion, i, new HashMap(), z);
    }

    protected SpdyHttpDecoder(SpdyVersion spdyVersion, int i, Map<Integer, FullHttpMessage> map) {
        this(spdyVersion, i, map, true);
    }

    protected SpdyHttpDecoder(SpdyVersion spdyVersion, int i, Map<Integer, FullHttpMessage> map, boolean z) {
        this.spdyVersion = ((SpdyVersion) ObjectUtil.checkNotNull(spdyVersion, "version")).getVersion();
        this.maxContentLength = ObjectUtil.checkPositive(i, "maxContentLength");
        this.messageMap = map;
        this.validateHeaders = z;
    }

    private static FullHttpRequest createHttpRequest(SpdyHeadersFrame spdyHeadersFrame, ByteBufAllocator byteBufAllocator) throws Exception {
        SpdyHeaders spdyHeadersHeaders = spdyHeadersFrame.headers();
        HttpMethod httpMethodValueOf = HttpMethod.valueOf(spdyHeadersHeaders.getAsString(SpdyHeaders.HttpNames.METHOD));
        String asString = spdyHeadersHeaders.getAsString(SpdyHeaders.HttpNames.PATH);
        HttpVersion httpVersionValueOf = HttpVersion.valueOf(spdyHeadersHeaders.getAsString(SpdyHeaders.HttpNames.VERSION));
        spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.METHOD);
        spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.PATH);
        spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.VERSION);
        ByteBuf byteBufBuffer = byteBufAllocator.buffer();
        try {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(httpVersionValueOf, httpMethodValueOf, asString, byteBufBuffer);
            spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.SCHEME);
            CharSequence charSequence = spdyHeadersHeaders.get(SpdyHeaders.HttpNames.HOST);
            spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.HOST);
            defaultFullHttpRequest.headers().set(HttpHeaderNames.HOST, charSequence);
            for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                defaultFullHttpRequest.headers().add(entry.getKey(), entry.getValue());
            }
            HttpUtil.setKeepAlive(defaultFullHttpRequest, true);
            defaultFullHttpRequest.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            return defaultFullHttpRequest;
        } catch (Throwable th) {
            byteBufBuffer.release();
            throw th;
        }
    }

    private static FullHttpResponse createHttpResponse(SpdyHeadersFrame spdyHeadersFrame, ByteBufAllocator byteBufAllocator, boolean z) throws Exception {
        SpdyHeaders spdyHeadersHeaders = spdyHeadersFrame.headers();
        HttpResponseStatus line = HttpResponseStatus.parseLine(spdyHeadersHeaders.get(SpdyHeaders.HttpNames.STATUS));
        HttpVersion httpVersionValueOf = HttpVersion.valueOf(spdyHeadersHeaders.getAsString(SpdyHeaders.HttpNames.VERSION));
        spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.STATUS);
        spdyHeadersHeaders.remove(SpdyHeaders.HttpNames.VERSION);
        ByteBuf byteBufBuffer = byteBufAllocator.buffer();
        try {
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpVersionValueOf, line, byteBufBuffer, z);
            for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                defaultFullHttpResponse.headers().add(entry.getKey(), entry.getValue());
            }
            HttpUtil.setKeepAlive(defaultFullHttpResponse, true);
            defaultFullHttpResponse.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            defaultFullHttpResponse.headers().remove(HttpHeaderNames.TRAILER);
            return defaultFullHttpResponse;
        } catch (Throwable th) {
            byteBufBuffer.release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, SpdyFrame spdyFrame, List list) throws Exception {
        decode2(channelHandlerContext, spdyFrame, (List<Object>) list);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        Iterator<Map.Entry<Integer, FullHttpMessage>> it2 = this.messageMap.entrySet().iterator();
        while (it2.hasNext()) {
            ReferenceCountUtil.safeRelease(it2.next().getValue());
        }
        this.messageMap.clear();
        super.channelInactive(channelHandlerContext);
    }

    protected FullHttpMessage putMessage(int i, FullHttpMessage fullHttpMessage) {
        return this.messageMap.put(Integer.valueOf(i), fullHttpMessage);
    }

    protected FullHttpMessage getMessage(int i) {
        return this.messageMap.get(Integer.valueOf(i));
    }

    protected FullHttpMessage removeMessage(int i) {
        return this.messageMap.remove(Integer.valueOf(i));
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, SpdyFrame spdyFrame, List<Object> list) throws Exception {
        if (spdyFrame instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) spdyFrame;
            int iStreamId = spdySynStreamFrame.streamId();
            if (SpdyCodecUtil.isServerId(iStreamId)) {
                int iAssociatedStreamId = spdySynStreamFrame.associatedStreamId();
                if (iAssociatedStreamId == 0) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId, SpdyStreamStatus.INVALID_STREAM));
                    return;
                }
                if (spdySynStreamFrame.isLast()) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId, SpdyStreamStatus.PROTOCOL_ERROR));
                    return;
                }
                if (spdySynStreamFrame.isTruncated()) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId, SpdyStreamStatus.INTERNAL_ERROR));
                    return;
                }
                try {
                    FullHttpRequest fullHttpRequestCreateHttpRequest = createHttpRequest(spdySynStreamFrame, channelHandlerContext.alloc());
                    fullHttpRequestCreateHttpRequest.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, iStreamId);
                    fullHttpRequestCreateHttpRequest.headers().setInt(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID, iAssociatedStreamId);
                    fullHttpRequestCreateHttpRequest.headers().setInt(SpdyHttpHeaders.Names.PRIORITY, spdySynStreamFrame.priority());
                    list.add(fullHttpRequestCreateHttpRequest);
                    return;
                } catch (Throwable unused) {
                    channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId, SpdyStreamStatus.PROTOCOL_ERROR));
                    return;
                }
            }
            if (spdySynStreamFrame.isTruncated()) {
                DefaultSpdySynReplyFrame defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(iStreamId);
                defaultSpdySynReplyFrame.setLast(true);
                SpdyHeaders spdyHeadersHeaders = defaultSpdySynReplyFrame.headers();
                spdyHeadersHeaders.setInt(SpdyHeaders.HttpNames.STATUS, HttpResponseStatus.REQUEST_HEADER_FIELDS_TOO_LARGE.code());
                spdyHeadersHeaders.setObject((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (Object) HttpVersion.HTTP_1_0);
                channelHandlerContext.writeAndFlush(defaultSpdySynReplyFrame);
                return;
            }
            try {
                FullHttpRequest fullHttpRequestCreateHttpRequest2 = createHttpRequest(spdySynStreamFrame, channelHandlerContext.alloc());
                fullHttpRequestCreateHttpRequest2.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, iStreamId);
                if (spdySynStreamFrame.isLast()) {
                    list.add(fullHttpRequestCreateHttpRequest2);
                } else {
                    putMessage(iStreamId, fullHttpRequestCreateHttpRequest2);
                }
                return;
            } catch (Throwable unused2) {
                DefaultSpdySynReplyFrame defaultSpdySynReplyFrame2 = new DefaultSpdySynReplyFrame(iStreamId);
                defaultSpdySynReplyFrame2.setLast(true);
                SpdyHeaders spdyHeadersHeaders2 = defaultSpdySynReplyFrame2.headers();
                spdyHeadersHeaders2.setInt(SpdyHeaders.HttpNames.STATUS, HttpResponseStatus.BAD_REQUEST.code());
                spdyHeadersHeaders2.setObject((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (Object) HttpVersion.HTTP_1_0);
                channelHandlerContext.writeAndFlush(defaultSpdySynReplyFrame2);
                return;
            }
        }
        if (spdyFrame instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) spdyFrame;
            int iStreamId2 = spdySynReplyFrame.streamId();
            if (spdySynReplyFrame.isTruncated()) {
                channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId2, SpdyStreamStatus.INTERNAL_ERROR));
                return;
            }
            try {
                FullHttpResponse fullHttpResponseCreateHttpResponse = createHttpResponse(spdySynReplyFrame, channelHandlerContext.alloc(), this.validateHeaders);
                fullHttpResponseCreateHttpResponse.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, iStreamId2);
                if (spdySynReplyFrame.isLast()) {
                    HttpUtil.setContentLength(fullHttpResponseCreateHttpResponse, 0L);
                    list.add(fullHttpResponseCreateHttpResponse);
                } else {
                    putMessage(iStreamId2, fullHttpResponseCreateHttpResponse);
                }
                return;
            } catch (Throwable unused3) {
                channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId2, SpdyStreamStatus.PROTOCOL_ERROR));
                return;
            }
        }
        if (spdyFrame instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) spdyFrame;
            int iStreamId3 = spdyHeadersFrame.streamId();
            FullHttpMessage message = getMessage(iStreamId3);
            if (message == null) {
                if (SpdyCodecUtil.isServerId(iStreamId3)) {
                    if (spdyHeadersFrame.isTruncated()) {
                        channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId3, SpdyStreamStatus.INTERNAL_ERROR));
                        return;
                    }
                    try {
                        FullHttpResponse fullHttpResponseCreateHttpResponse2 = createHttpResponse(spdyHeadersFrame, channelHandlerContext.alloc(), this.validateHeaders);
                        fullHttpResponseCreateHttpResponse2.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, iStreamId3);
                        if (spdyHeadersFrame.isLast()) {
                            HttpUtil.setContentLength(fullHttpResponseCreateHttpResponse2, 0L);
                            list.add(fullHttpResponseCreateHttpResponse2);
                        } else {
                            putMessage(iStreamId3, fullHttpResponseCreateHttpResponse2);
                        }
                        return;
                    } catch (Throwable unused4) {
                        channelHandlerContext.writeAndFlush(new DefaultSpdyRstStreamFrame(iStreamId3, SpdyStreamStatus.PROTOCOL_ERROR));
                        return;
                    }
                }
                return;
            }
            if (!spdyHeadersFrame.isTruncated()) {
                for (Map.Entry<CharSequence, CharSequence> entry : spdyHeadersFrame.headers()) {
                    message.headers().add(entry.getKey(), entry.getValue());
                }
            }
            if (spdyHeadersFrame.isLast()) {
                HttpUtil.setContentLength(message, message.content().readableBytes());
                removeMessage(iStreamId3);
                list.add(message);
                return;
            }
            return;
        }
        if (spdyFrame instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) spdyFrame;
            int iStreamId4 = spdyDataFrame.streamId();
            FullHttpMessage message2 = getMessage(iStreamId4);
            if (message2 == null) {
                return;
            }
            ByteBuf byteBufContent = message2.content();
            if (byteBufContent.readableBytes() > this.maxContentLength - spdyDataFrame.content().readableBytes()) {
                removeMessage(iStreamId4);
                throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
            }
            ByteBuf byteBufContent2 = spdyDataFrame.content();
            byteBufContent.writeBytes(byteBufContent2, byteBufContent2.readerIndex(), byteBufContent2.readableBytes());
            if (spdyDataFrame.isLast()) {
                HttpUtil.setContentLength(message2, byteBufContent.readableBytes());
                removeMessage(iStreamId4);
                list.add(message2);
                return;
            }
            return;
        }
        if (spdyFrame instanceof SpdyRstStreamFrame) {
            removeMessage(((SpdyRstStreamFrame) spdyFrame).streamId());
        }
    }
}
