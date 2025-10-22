package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/* loaded from: classes5.dex */
public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected TlsContext context;
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;

    protected AbstractTlsKeyExchange(int i, Vector vector) {
        this.keyExchange = i;
        this.supportedSignatureAlgorithms = vector;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(org.bouncycastle.crypto.tls.TlsContext r4) {
        /*
            r3 = this;
            r3.context = r4
            org.bouncycastle.crypto.tls.ProtocolVersion r4 = r4.getClientVersion()
            boolean r0 = org.bouncycastle.crypto.tls.TlsUtils.isSignatureAlgorithmsExtensionAllowed(r4)
            if (r0 == 0) goto L41
            java.util.Vector r4 = r3.supportedSignatureAlgorithms
            if (r4 != 0) goto L45
            int r4 = r3.keyExchange
            r0 = 1
            if (r4 == r0) goto L3a
            r0 = 3
            if (r4 == r0) goto L35
            r0 = 5
            if (r4 == r0) goto L3a
            r0 = 7
            if (r4 == r0) goto L35
            r0 = 9
            if (r4 == r0) goto L3a
            switch(r4) {
                case 13: goto L45;
                case 14: goto L45;
                case 15: goto L3a;
                case 16: goto L30;
                case 17: goto L30;
                case 18: goto L3a;
                case 19: goto L3a;
                default: goto L25;
            }
        L25:
            switch(r4) {
                case 21: goto L45;
                case 22: goto L35;
                case 23: goto L3a;
                case 24: goto L45;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "unsupported key exchange algorithm"
            r4.<init>(r0)
            throw r4
        L30:
            java.util.Vector r4 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultECDSASignatureAlgorithms()
            goto L3e
        L35:
            java.util.Vector r4 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultDSSSignatureAlgorithms()
            goto L3e
        L3a:
            java.util.Vector r4 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultRSASignatureAlgorithms()
        L3e:
            r3.supportedSignatureAlgorithms = r4
            goto L45
        L41:
            java.util.Vector r0 = r3.supportedSignatureAlgorithms
            if (r0 != 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "supported_signature_algorithms not allowed for "
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.AbstractTlsKeyExchange.init(org.bouncycastle.crypto.tls.TlsContext):void");
    }

    protected DigitallySigned parseSignature(InputStream inputStream) throws IOException {
        DigitallySigned digitallySigned = DigitallySigned.parse(this.context, inputStream);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, algorithm);
        }
        return digitallySigned;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        processServerCertificate(tlsCredentials.getCertificate());
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return false;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipClientCredentials() throws IOException {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipServerKeyExchange() throws IOException {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }
}
