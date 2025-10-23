package io.grpc.internal;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
