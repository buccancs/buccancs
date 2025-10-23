package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.util.Signal;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* loaded from: classes3.dex */
public class DecoderResult {
    public static final DecoderResult SUCCESS;
    public static final DecoderResult UNFINISHED;
    protected static final Signal SIGNAL_SUCCESS;
    protected static final Signal SIGNAL_UNFINISHED;

    static {
        Signal signalValueOf = Signal.valueOf(DecoderResult.class, "UNFINISHED");
        SIGNAL_UNFINISHED = signalValueOf;
        Signal signalValueOf2 = Signal.valueOf(DecoderResult.class, "SUCCESS");
        SIGNAL_SUCCESS = signalValueOf2;
        UNFINISHED = new DecoderResult(signalValueOf);
        SUCCESS = new DecoderResult(signalValueOf2);
    }

    private final Throwable cause;

    protected DecoderResult(Throwable th) {
        this.cause = (Throwable) ObjectUtil.checkNotNull(th, "cause");
    }

    public static DecoderResult failure(Throwable th) {
        return new DecoderResult((Throwable) ObjectUtil.checkNotNull(th, "cause"));
    }

    public boolean isFailure() {
        Throwable th = this.cause;
        return (th == SIGNAL_SUCCESS || th == SIGNAL_UNFINISHED) ? false : true;
    }

    public boolean isFinished() {
        return this.cause != SIGNAL_UNFINISHED;
    }

    public boolean isSuccess() {
        return this.cause == SIGNAL_SUCCESS;
    }

    public Throwable cause() {
        if (isFailure()) {
            return this.cause;
        }
        return null;
    }

    public String toString() {
        if (!isFinished()) {
            return "unfinished";
        }
        if (isSuccess()) {
            return "success";
        }
        String string = cause().toString();
        StringBuilder sb = new StringBuilder(string.length() + 17);
        sb.append("failure(");
        sb.append(string);
        sb.append(')');
        return sb.toString();
    }
}
