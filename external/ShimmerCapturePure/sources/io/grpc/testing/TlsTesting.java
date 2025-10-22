package io.grpc.testing;

import java.io.InputStream;

/* loaded from: classes3.dex */
public final class TlsTesting {
    private TlsTesting() {
    }

    public static InputStream loadCert(String str) {
        return TestUtils.class.getResourceAsStream("/certs/" + str);
    }
}
