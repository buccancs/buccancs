package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.UnpooledByteBufAllocator;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509KeyManager;

/* loaded from: classes3.dex */
class OpenSslKeyMaterialProvider {
    private final X509KeyManager keyManager;
    private final String password;

    OpenSslKeyMaterialProvider(X509KeyManager x509KeyManager, String str) {
        this.keyManager = x509KeyManager;
        this.password = str;
    }

    static void validateKeyMaterialSupported(X509Certificate[] x509CertificateArr, PrivateKey privateKey, String str) throws Throwable {
        validateSupported(x509CertificateArr);
        validateSupported(privateKey, str);
    }

    private static void validateSupported(PrivateKey privateKey, String str) throws Throwable {
        long bio;
        if (privateKey == null) {
            return;
        }
        long j = 0;
        try {
            try {
                bio = ReferenceCountedOpenSslContext.toBIO(UnpooledByteBufAllocator.DEFAULT, privateKey);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            long privateKey2 = SSL.parsePrivateKey(bio, str);
            SSL.freeBIO(bio);
            if (privateKey2 != 0) {
                SSL.freePrivateKey(privateKey2);
            }
        } catch (Exception e2) {
            e = e2;
            throw new SSLException("PrivateKey type not supported " + privateKey.getFormat(), e);
        } catch (Throwable th2) {
            th = th2;
            j = bio;
            SSL.freeBIO(j);
            throw th;
        }
    }

    private static void validateSupported(X509Certificate[] x509CertificateArr) throws Throwable {
        long bio;
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            return;
        }
        long j = 0;
        PemEncoded pem = null;
        try {
            try {
                pem = PemX509Certificate.toPEM(UnpooledByteBufAllocator.DEFAULT, true, x509CertificateArr);
                bio = ReferenceCountedOpenSslContext.toBIO(UnpooledByteBufAllocator.DEFAULT, pem.retain());
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            long x509Chain = SSL.parseX509Chain(bio);
            SSL.freeBIO(bio);
            if (x509Chain != 0) {
                SSL.freeX509Chain(x509Chain);
            }
            if (pem != null) {
                pem.release();
            }
        } catch (Exception e2) {
            e = e2;
            throw new SSLException("Certificate type not supported", e);
        } catch (Throwable th2) {
            th = th2;
            j = bio;
            SSL.freeBIO(j);
            if (pem != null) {
                pem.release();
            }
            throw th;
        }
    }

    void destroy() {
    }

    X509KeyManager keyManager() {
        return this.keyManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterial chooseKeyMaterial(io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator r21, java.lang.String r22) throws java.lang.Exception {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            r2 = r22
            javax.net.ssl.X509KeyManager r3 = r1.keyManager
            java.security.cert.X509Certificate[] r9 = r3.getCertificateChain(r2)
            if (r9 == 0) goto L94
            int r3 = r9.length
            if (r3 != 0) goto L13
            goto L94
        L13:
            javax.net.ssl.X509KeyManager r3 = r1.keyManager
            java.security.PrivateKey r2 = r3.getPrivateKey(r2)
            r3 = 1
            io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded r3 = io.grpc.netty.shaded.io.netty.handler.ssl.PemX509Certificate.toPEM(r0, r3, r9)
            r10 = 0
            io.grpc.netty.shaded.io.netty.handler.ssl.PemEncoded r4 = r3.retain()     // Catch: java.lang.Throwable -> L76
            long r12 = io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO(r0, r4)     // Catch: java.lang.Throwable -> L76
            long r14 = io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.parseX509Chain(r12)     // Catch: java.lang.Throwable -> L72
            boolean r4 = r2 instanceof io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslPrivateKey     // Catch: java.lang.Throwable -> L6d
            if (r4 == 0) goto L37
            io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslPrivateKey r2 = (io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslPrivateKey) r2     // Catch: java.lang.Throwable -> L6d
            io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterial r0 = r2.newKeyMaterial(r14, r9)     // Catch: java.lang.Throwable -> L6d
            goto L55
        L37:
            long r7 = io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.toBIO(r0, r2)     // Catch: java.lang.Throwable -> L6d
            if (r2 != 0) goto L40
            r16 = r10
            goto L48
        L40:
            java.lang.String r0 = r1.password     // Catch: java.lang.Throwable -> L67
            long r4 = io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.parsePrivateKey(r7, r0)     // Catch: java.lang.Throwable -> L67
            r16 = r4
        L48:
            io.grpc.netty.shaded.io.netty.handler.ssl.DefaultOpenSslKeyMaterial r0 = new io.grpc.netty.shaded.io.netty.handler.ssl.DefaultOpenSslKeyMaterial     // Catch: java.lang.Throwable -> L63
            r4 = r0
            r5 = r14
            r18 = r7
            r7 = r16
            r4.<init>(r5, r7, r9)     // Catch: java.lang.Throwable -> L5f
            r10 = r18
        L55:
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freeBIO(r12)
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freeBIO(r10)
            r3.release()
            return r0
        L5f:
            r0 = move-exception
            r7 = r18
            goto L7c
        L63:
            r0 = move-exception
            r18 = r7
            goto L7c
        L67:
            r0 = move-exception
            r18 = r7
            r16 = r10
            goto L7c
        L6d:
            r0 = move-exception
            r7 = r10
            r16 = r7
            goto L7c
        L72:
            r0 = move-exception
            r7 = r10
            r14 = r7
            goto L7a
        L76:
            r0 = move-exception
            r7 = r10
            r12 = r7
            r14 = r12
        L7a:
            r16 = r14
        L7c:
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freeBIO(r12)
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freeBIO(r7)
            int r2 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r2 == 0) goto L89
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freeX509Chain(r14)
        L89:
            int r2 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r2 == 0) goto L90
            io.grpc.netty.shaded.io.netty.internal.tcnative.SSL.freePrivateKey(r16)
        L90:
            r3.release()
            throw r0
        L94:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterialProvider.chooseKeyMaterial(io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator, java.lang.String):io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterial");
    }
}
