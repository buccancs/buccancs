package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* loaded from: classes3.dex */
public class SpdyProtocolException extends Exception {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 7870000537743847264L;

    public SpdyProtocolException() {
    }

    public SpdyProtocolException(String str, Throwable th) {
        super(str, th);
    }

    public SpdyProtocolException(String str) {
        super(str);
    }

    public SpdyProtocolException(Throwable th) {
        super(th);
    }

    private SpdyProtocolException(String str, boolean z) {
        super(str, null, false, true);
    }

    static SpdyProtocolException newStatic(String str) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new SpdyProtocolException(str, true);
        }
        return new SpdyProtocolException(str);
    }
}
