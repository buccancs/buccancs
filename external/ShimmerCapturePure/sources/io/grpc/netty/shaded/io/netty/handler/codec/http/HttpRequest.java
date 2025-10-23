package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* loaded from: classes3.dex */
public interface HttpRequest extends HttpMessage {
    @Deprecated
    HttpMethod getMethod();

    HttpRequest setMethod(HttpMethod httpMethod);

    @Deprecated
    String getUri();

    HttpRequest setUri(String str);

    HttpMethod method();

    HttpRequest setProtocolVersion(HttpVersion httpVersion);

    String uri();
}
