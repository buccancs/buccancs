package io.grpc.internal;

import io.grpc.ClientCall;
import io.grpc.Metadata;

/* loaded from: classes2.dex */
public class NoopClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {

    @Override // io.grpc.ClientCall
    public void cancel(String str, Throwable th) {
    }

    @Override // io.grpc.ClientCall
    public void halfClose() {
    }

    @Override // io.grpc.ClientCall
    public void request(int i) {
    }

    @Override // io.grpc.ClientCall
    public void sendMessage(ReqT reqt) {
    }

    @Override // io.grpc.ClientCall
    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
    }

    public static class NoopClientCallListener<T> extends ClientCall.Listener<T> {
    }
}
