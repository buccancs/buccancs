package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.util.AsciiString;

/* loaded from: classes3.dex */
public enum WebSocketVersion {
    UNKNOWN(AsciiString.cached("")),
    V00(AsciiString.cached("0")),
    V07(AsciiString.cached("7")),
    V08(AsciiString.cached("8")),
    V13(AsciiString.cached("13"));

    private final AsciiString headerValue;

    WebSocketVersion(AsciiString asciiString) {
        this.headerValue = asciiString;
    }

    public String toHttpHeaderValue() {
        return toAsciiString().toString();
    }

    AsciiString toAsciiString() {
        if (this != UNKNOWN) {
            return this.headerValue;
        }
        throw new IllegalStateException("Unknown web socket version: " + this);
    }
}
