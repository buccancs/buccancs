package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;

import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
class FailingClientTransport implements ClientTransport {
    final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;

    FailingClientTransport(Status status, ClientStreamListener.RpcProgress rpcProgress) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress;
    }

    @Override // io.grpc.internal.ClientTransport
    public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        return new FailingClientStream(this.error, this.rpcProgress);
    }

    @Override // io.grpc.internal.ClientTransport
    public void ping(final ClientTransport.PingCallback pingCallback, Executor executor) {
        executor.execute(new Runnable() { // from class: io.grpc.internal.FailingClientTransport.1
            @Override // java.lang.Runnable
            public void run() {
                pingCallback.onFailure(FailingClientTransport.this.error.asException());
            }
        });
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture settableFutureCreate = SettableFuture.create();
        settableFutureCreate.set(null);
        return settableFutureCreate;
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}
