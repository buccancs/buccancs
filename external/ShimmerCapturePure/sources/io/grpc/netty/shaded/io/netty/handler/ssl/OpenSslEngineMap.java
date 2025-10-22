package io.grpc.netty.shaded.io.netty.handler.ssl;

/* loaded from: classes3.dex */
interface OpenSslEngineMap {
    void add(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine);

    ReferenceCountedOpenSslEngine get(long j);

    ReferenceCountedOpenSslEngine remove(long j);
}
