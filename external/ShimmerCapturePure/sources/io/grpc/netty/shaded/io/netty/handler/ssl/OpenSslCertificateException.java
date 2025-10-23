package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateVerifier;

import java.security.cert.CertificateException;

/* loaded from: classes3.dex */
public final class OpenSslCertificateException extends CertificateException {
    private static final long serialVersionUID = 5542675253797129798L;
    private final int errorCode;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OpenSslCertificateException(int i) {
        this((String) null, i);
    }

    public OpenSslCertificateException(String str, int i) {
        super(str);
        this.errorCode = checkErrorCode(i);
    }

    public OpenSslCertificateException(String str, Throwable th, int i) {
        super(str, th);
        this.errorCode = checkErrorCode(i);
    }

    public OpenSslCertificateException(Throwable th, int i) {
        this(null, th, i);
    }

    private static int checkErrorCode(int i) {
        if (!OpenSsl.isAvailable() || CertificateVerifier.isValid(i)) {
            return i;
        }
        throw new IllegalArgumentException("errorCode '" + i + "' invalid, see https://www.openssl.org/docs/man1.0.2/apps/verify.html.");
    }

    public int errorCode() {
        return this.errorCode;
    }
}
