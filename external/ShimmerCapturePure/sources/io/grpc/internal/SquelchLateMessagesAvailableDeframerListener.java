package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
final class SquelchLateMessagesAvailableDeframerListener extends ForwardingDeframerListener {
    private final MessageDeframer.Listener delegate;
    private boolean closed;

    public SquelchLateMessagesAvailableDeframerListener(MessageDeframer.Listener listener) {
        this.delegate = listener;
    }

    @Override // io.grpc.internal.ForwardingDeframerListener
    protected MessageDeframer.Listener delegate() {
        return this.delegate;
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void messagesAvailable(StreamListener.MessageProducer messageProducer) throws IOException {
        if (this.closed) {
            if (messageProducer instanceof Closeable) {
                GrpcUtil.closeQuietly((Closeable) messageProducer);
                return;
            }
            return;
        }
        super.messagesAvailable(messageProducer);
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void deframerClosed(boolean z) {
        this.closed = true;
        super.deframerClosed(z);
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void deframeFailed(Throwable th) {
        this.closed = true;
        super.deframeFailed(th);
    }
}
