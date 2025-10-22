package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.google.api.client.http.HttpMethods;
import com.shimmerresearch.verisense.UtilVerisenseDriver;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/* loaded from: classes3.dex */
public abstract class HttpContentEncoder extends MessageToMessageCodec<HttpRequest, HttpObject> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final CharSequence ZERO_LENGTH_HEAD = HttpMethods.HEAD;
    private static final CharSequence ZERO_LENGTH_CONNECT = HttpMethods.CONNECT;
    private static final int CONTINUE_CODE = HttpResponseStatus.CONTINUE.code();
    private final Queue<CharSequence> acceptEncodingQueue = new ArrayDeque();
    private EmbeddedChannel encoder;
    private State state = State.AWAIT_HEADERS;

    private static boolean isPassthru(HttpVersion httpVersion, int i, CharSequence charSequence) {
        return i < 200 || i == 204 || i == 304 || charSequence == ZERO_LENGTH_HEAD || (charSequence == ZERO_LENGTH_CONNECT && i == 200) || httpVersion == HttpVersion.HTTP_1_0;
    }

    private static void ensureHeaders(HttpObject httpObject) {
        if (httpObject instanceof HttpResponse) {
            return;
        }
        throw new IllegalStateException("unexpected message type: " + httpObject.getClass().getName() + " (expected: HttpResponse)");
    }

    private static void ensureContent(HttpObject httpObject) {
        if (httpObject instanceof HttpContent) {
            return;
        }
        throw new IllegalStateException("unexpected message type: " + httpObject.getClass().getName() + " (expected: HttpContent)");
    }

    protected abstract Result beginEncode(HttpResponse httpResponse, String str) throws Exception;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, List list) throws Exception {
        decode2(channelHandlerContext, httpRequest, (List<Object>) list);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List list) throws Exception {
        encode2(channelHandlerContext, httpObject, (List<Object>) list);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return (obj instanceof HttpContent) || (obj instanceof HttpResponse);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, List<Object> list) throws Exception {
        String strJoin;
        List<String> all = httpRequest.headers().getAll(HttpHeaderNames.ACCEPT_ENCODING);
        int size = all.size();
        if (size == 0) {
            strJoin = HttpContentDecoder.IDENTITY;
        } else if (size == 1) {
            strJoin = all.get(0);
        } else {
            strJoin = StringUtil.join(UtilVerisenseDriver.CSV_DELIMITER, all);
        }
        HttpMethod httpMethodMethod = httpRequest.method();
        if (HttpMethod.HEAD.equals(httpMethodMethod)) {
            strJoin = ZERO_LENGTH_HEAD;
        } else if (HttpMethod.CONNECT.equals(httpMethodMethod)) {
            strJoin = ZERO_LENGTH_CONNECT;
        }
        this.acceptEncodingQueue.add(strJoin);
        list.add(ReferenceCountUtil.retain(httpRequest));
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        CharSequence charSequencePoll;
        boolean z = (httpObject instanceof HttpResponse) && (httpObject instanceof LastHttpContent);
        int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[this.state.ordinal()];
        if (i == 1) {
            ensureHeaders(httpObject);
            HttpResponse httpResponse = (HttpResponse) httpObject;
            int iCode = httpResponse.status().code();
            if (iCode == CONTINUE_CODE) {
                charSequencePoll = null;
            } else {
                charSequencePoll = this.acceptEncodingQueue.poll();
                if (charSequencePoll == null) {
                    throw new IllegalStateException("cannot send more responses than requests");
                }
            }
            if (isPassthru(httpResponse.protocolVersion(), iCode, charSequencePoll)) {
                if (z) {
                    list.add(ReferenceCountUtil.retain(httpResponse));
                    return;
                } else {
                    list.add(httpResponse);
                    this.state = State.PASS_THROUGH;
                    return;
                }
            }
            if (z && !((ByteBufHolder) httpResponse).content().isReadable()) {
                list.add(ReferenceCountUtil.retain(httpResponse));
                return;
            }
            Result resultBeginEncode = beginEncode(httpResponse, charSequencePoll.toString());
            if (resultBeginEncode == null) {
                if (z) {
                    list.add(ReferenceCountUtil.retain(httpResponse));
                    return;
                } else {
                    list.add(httpResponse);
                    this.state = State.PASS_THROUGH;
                    return;
                }
            }
            this.encoder = resultBeginEncode.contentEncoder();
            httpResponse.headers().set(HttpHeaderNames.CONTENT_ENCODING, resultBeginEncode.targetContentEncoding());
            if (z) {
                DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(httpResponse.protocolVersion(), httpResponse.status());
                defaultHttpResponse.headers().set(httpResponse.headers());
                list.add(defaultHttpResponse);
                ensureContent(httpResponse);
                encodeFullResponse(defaultHttpResponse, (HttpContent) httpResponse, list);
                return;
            }
            httpResponse.headers().remove(HttpHeaderNames.CONTENT_LENGTH);
            httpResponse.headers().set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
            list.add(httpResponse);
            this.state = State.AWAIT_CONTENT;
            if (!(httpObject instanceof HttpContent)) {
                return;
            }
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            ensureContent(httpObject);
            list.add(ReferenceCountUtil.retain(httpObject));
            if (httpObject instanceof LastHttpContent) {
                this.state = State.AWAIT_HEADERS;
                return;
            }
            return;
        }
        ensureContent(httpObject);
        if (encodeContent((HttpContent) httpObject, list)) {
            this.state = State.AWAIT_HEADERS;
        }
    }

    private void encodeFullResponse(HttpResponse httpResponse, HttpContent httpContent, List<Object> list) {
        encodeContent(httpContent, list);
        if (HttpUtil.isContentLengthSet(httpResponse)) {
            int i = 0;
            for (int size = list.size(); size < list.size(); size++) {
                Object obj = list.get(size);
                if (obj instanceof HttpContent) {
                    i += ((HttpContent) obj).content().readableBytes();
                }
            }
            HttpUtil.setContentLength(httpResponse, i);
            return;
        }
        httpResponse.headers().set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
    }

    private boolean encodeContent(HttpContent httpContent, List<Object> list) {
        encode(httpContent.content(), list);
        if (!(httpContent instanceof LastHttpContent)) {
            return false;
        }
        finishEncode(list);
        HttpHeaders httpHeadersTrailingHeaders = ((LastHttpContent) httpContent).trailingHeaders();
        if (httpHeadersTrailingHeaders.isEmpty()) {
            list.add(LastHttpContent.EMPTY_LAST_CONTENT);
            return true;
        }
        list.add(new ComposedLastHttpContent(httpHeadersTrailingHeaders, DecoderResult.SUCCESS));
        return true;
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanupSafely(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    @Override
    // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanupSafely(channelHandlerContext);
        super.channelInactive(channelHandlerContext);
    }

    private void cleanup() {
        EmbeddedChannel embeddedChannel = this.encoder;
        if (embeddedChannel != null) {
            embeddedChannel.finishAndReleaseAll();
            this.encoder = null;
        }
    }

    private void cleanupSafely(ChannelHandlerContext channelHandlerContext) {
        try {
            cleanup();
        } catch (Throwable th) {
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    private void encode(ByteBuf byteBuf, List<Object> list) {
        this.encoder.writeOutbound(byteBuf.retain());
        fetchEncoderOutput(list);
    }

    private void finishEncode(List<Object> list) {
        if (this.encoder.finish()) {
            fetchEncoderOutput(list);
        }
        this.encoder = null;
    }

    private void fetchEncoderOutput(List<Object> list) {
        while (true) {
            ByteBuf byteBuf = (ByteBuf) this.encoder.readOutbound();
            if (byteBuf == null) {
                return;
            }
            if (!byteBuf.isReadable()) {
                byteBuf.release();
            } else {
                list.add(new DefaultHttpContent(byteBuf));
            }
        }
    }

    private enum State {
        PASS_THROUGH,
        AWAIT_HEADERS,
        AWAIT_CONTENT
    }

    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContentEncoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State = iArr;
            try {
                iArr[State.AWAIT_HEADERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[State.AWAIT_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[State.PASS_THROUGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static final class Result {
        private final EmbeddedChannel contentEncoder;
        private final String targetContentEncoding;

        public Result(String str, EmbeddedChannel embeddedChannel) {
            this.targetContentEncoding = (String) ObjectUtil.checkNotNull(str, "targetContentEncoding");
            this.contentEncoder = (EmbeddedChannel) ObjectUtil.checkNotNull(embeddedChannel, "contentEncoder");
        }

        public EmbeddedChannel contentEncoder() {
            return this.contentEncoder;
        }

        public String targetContentEncoding() {
            return this.targetContentEncoding;
        }
    }
}
