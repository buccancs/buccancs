package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;
    private final ClientTransport transport;
    private final Object lock = new Object();
    private final Context ctx = Context.current();
    DelayedStream delayedStream;
    boolean finalized;
    @Nullable
    private ClientStream returnedStream;

    MetadataApplierImpl(ClientTransport clientTransport, MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        this.transport = clientTransport;
        this.method = methodDescriptor;
        this.origHeaders = metadata;
        this.callOptions = callOptions;
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void apply(Metadata metadata) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(metadata, "headers");
        this.origHeaders.merge(metadata);
        Context contextAttach = this.ctx.attach();
        try {
            ClientStream clientStreamNewStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions);
            this.ctx.detach(contextAttach);
            finalizeWith(clientStreamNewStream);
        } catch (Throwable th) {
            this.ctx.detach(contextAttach);
            throw th;
        }
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(status));
    }

    private void finalizeWith(ClientStream clientStream) {
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = clientStream;
            } else {
                Preconditions.checkState(this.delayedStream != null, "delayedStream is null");
                this.delayedStream.setStream(clientStream);
            }
        }
    }

    ClientStream returnStream() {
        synchronized (this.lock) {
            ClientStream clientStream = this.returnedStream;
            if (clientStream != null) {
                return clientStream;
            }
            DelayedStream delayedStream = new DelayedStream();
            this.delayedStream = delayedStream;
            this.returnedStream = delayedStream;
            return delayedStream;
        }
    }
}
