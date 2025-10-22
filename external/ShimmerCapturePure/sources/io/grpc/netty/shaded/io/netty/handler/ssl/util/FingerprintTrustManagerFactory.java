package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public final class FingerprintTrustManagerFactory extends SimpleTrustManagerFactory {
    private static final int SHA1_BYTE_LEN = 20;
    private static final int SHA1_HEX_LEN = 40;
    private static final Pattern FINGERPRINT_PATTERN = Pattern.compile("^[0-9a-fA-F:]+$");
    private static final Pattern FINGERPRINT_STRIP_PATTERN = Pattern.compile(":");
    private static final FastThreadLocal<MessageDigest> tlmd = new FastThreadLocal<MessageDigest>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.util.FingerprintTrustManagerFactory.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException e) {
                throw new Error(e);
            }
        }
    };
    private final byte[][] fingerprints;
    private final TrustManager tm;

    public FingerprintTrustManagerFactory(Iterable<String> iterable) {
        this(toFingerprintArray(iterable));
    }

    public FingerprintTrustManagerFactory(String... strArr) {
        this(toFingerprintArray(Arrays.asList(strArr)));
    }

    public FingerprintTrustManagerFactory(byte[]... bArr) {
        this.tm = new X509TrustManager() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.util.FingerprintTrustManagerFactory.2
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                checkTrusted("client", x509CertificateArr);
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                checkTrusted("server", x509CertificateArr);
            }

            private void checkTrusted(String str, X509Certificate[] x509CertificateArr) throws CertificateException {
                X509Certificate x509Certificate = x509CertificateArr[0];
                byte[] bArrFingerprint = fingerprint(x509Certificate);
                for (byte[] bArr2 : FingerprintTrustManagerFactory.this.fingerprints) {
                    if (Arrays.equals(bArrFingerprint, bArr2)) {
                        return;
                    }
                }
                throw new CertificateException(str + " certificate with unknown fingerprint: " + x509Certificate.getSubjectDN());
            }

            private byte[] fingerprint(X509Certificate x509Certificate) throws CertificateEncodingException {
                MessageDigest messageDigest = (MessageDigest) FingerprintTrustManagerFactory.tlmd.get();
                messageDigest.reset();
                return messageDigest.digest(x509Certificate.getEncoded());
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return EmptyArrays.EMPTY_X509_CERTIFICATES;
            }
        };
        ObjectUtil.checkNotNull(bArr, "fingerprints");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            if (bArr2 == null) {
                break;
            }
            if (bArr2.length != 20) {
                throw new IllegalArgumentException("malformed fingerprint: " + ByteBufUtil.hexDump(Unpooled.wrappedBuffer(bArr2)) + " (expected: SHA1)");
            }
            arrayList.add(bArr2.clone());
        }
        this.fingerprints = (byte[][]) arrayList.toArray(new byte[0][]);
    }

    private static byte[][] toFingerprintArray(Iterable<String> iterable) {
        String next;
        ObjectUtil.checkNotNull(iterable, "fingerprints");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it2 = iterable.iterator();
        while (it2.hasNext() && (next = it2.next()) != null) {
            if (!FINGERPRINT_PATTERN.matcher(next).matches()) {
                throw new IllegalArgumentException("malformed fingerprint: " + next);
            }
            String strReplaceAll = FINGERPRINT_STRIP_PATTERN.matcher(next).replaceAll("");
            if (strReplaceAll.length() != 40) {
                throw new IllegalArgumentException("malformed fingerprint: " + strReplaceAll + " (expected: SHA1)");
            }
            arrayList.add(StringUtil.decodeHexDump(strReplaceAll));
        }
        return (byte[][]) arrayList.toArray(new byte[0][]);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected TrustManager[] engineGetTrustManagers() {
        return new TrustManager[]{this.tm};
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(KeyStore keyStore) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }
}
