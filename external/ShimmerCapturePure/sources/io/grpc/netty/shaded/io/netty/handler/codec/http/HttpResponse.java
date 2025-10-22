package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* loaded from: classes3.dex */
public interface HttpResponse extends HttpMessage {
    @Deprecated
    HttpResponseStatus getStatus();

    HttpResponse setStatus(HttpResponseStatus httpResponseStatus);

    @Override
        // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
    HttpResponse setProtocolVersion(HttpVersion httpVersion);

    HttpResponseStatus status();
}
