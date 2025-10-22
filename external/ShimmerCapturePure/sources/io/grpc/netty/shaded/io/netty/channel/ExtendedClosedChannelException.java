package io.grpc.netty.shaded.io.netty.channel;

import java.nio.channels.ClosedChannelException;

/* loaded from: classes3.dex */
final class ExtendedClosedChannelException extends ClosedChannelException {
    ExtendedClosedChannelException(Throwable th) {
        if (th != null) {
            initCause(th);
        }
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }
}
