package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.Status;

/* loaded from: classes2.dex */
public class NoopServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {

    @Override // io.grpc.ServerCall
    public void close(Status status, Metadata metadata) {
    }

    @Override // io.grpc.ServerCall
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return null;
    }

    @Override // io.grpc.ServerCall
    public boolean isCancelled() {
        return false;
    }

    @Override // io.grpc.ServerCall
    public void request(int i) {
    }

    @Override // io.grpc.ServerCall
    public void sendHeaders(Metadata metadata) {
    }

    @Override // io.grpc.ServerCall
    public void sendMessage(RespT respt) {
    }

    public static class NoopServerCallListener<T> extends ServerCall.Listener<T> {
    }
}
