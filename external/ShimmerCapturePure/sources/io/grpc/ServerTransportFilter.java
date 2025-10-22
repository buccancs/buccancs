package io.grpc;

/* loaded from: classes2.dex */
public abstract class ServerTransportFilter {
    public Attributes transportReady(Attributes attributes) {
        return attributes;
    }

    public void transportTerminated(Attributes attributes) {
    }
}
