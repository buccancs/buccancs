package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public abstract class SslCompletionEvent {
    private final Throwable cause;

    SslCompletionEvent() {
        this.cause = null;
    }

    SslCompletionEvent(Throwable th) {
        this.cause = (Throwable) ObjectUtil.checkNotNull(th, "cause");
    }

    public final Throwable cause() {
        return this.cause;
    }

    public final boolean isSuccess() {
        return this.cause == null;
    }

    public String toString() {
        Throwable thCause = cause();
        if (thCause == null) {
            return getClass().getSimpleName() + "(SUCCESS)";
        }
        return getClass().getSimpleName() + '(' + thCause + ')';
    }
}
