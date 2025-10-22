package io.grpc;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public abstract class ServerCall<ReqT, RespT> {

    public abstract void close(Status status, Metadata metadata);

    @Nullable
    public String getAuthority() {
        return null;
    }

    public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();

    public abstract boolean isCancelled();

    public boolean isReady() {
        return true;
    }

    public abstract void request(int i);

    public abstract void sendHeaders(Metadata metadata);

    public abstract void sendMessage(RespT respt);

    public void setCompression(String str) {
    }

    public void setMessageCompression(boolean z) {
    }

    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    public static abstract class Listener<ReqT> {
        public void onCancel() {
        }

        public void onComplete() {
        }

        public void onHalfClose() {
        }

        public void onMessage(ReqT reqt) {
        }

        public void onReady() {
        }
    }
}
