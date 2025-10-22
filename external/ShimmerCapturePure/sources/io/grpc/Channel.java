package io.grpc;

/* loaded from: classes2.dex */
public abstract class Channel {
    public abstract String authority();

    public abstract <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions);
}
