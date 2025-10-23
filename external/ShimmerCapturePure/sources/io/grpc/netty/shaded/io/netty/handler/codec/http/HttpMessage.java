package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* loaded from: classes3.dex */
public interface HttpMessage extends HttpObject {
    @Deprecated
    HttpVersion getProtocolVersion();

    HttpMessage setProtocolVersion(HttpVersion httpVersion);

    HttpHeaders headers();

    HttpVersion protocolVersion();
}
