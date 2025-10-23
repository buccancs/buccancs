package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/* loaded from: classes3.dex */
public final class SelfSignedCertificate {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SelfSignedCertificate.class);
    private static final Date DEFAULT_NOT_BEFORE = new Date(SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.selfSignedCertificate.defaultNotBefore", System.currentTimeMillis() - 31536000000L));
    private static final Date DEFAULT_NOT_AFTER = new Date(SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.selfSignedCertificate.defaultNotAfter", 253402300799000L));
    private static final int DEFAULT_KEY_LENGTH_BITS = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.handler.ssl.util.selfSignedKeyStrength", 2048);
    private final X509Certificate cert;
    private final File certificate;
    private final PrivateKey key;
    private final File privateKey;

    public SelfSignedCertificate() throws CertificateException {
        this(DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2) throws CertificateException {
        this("localhost", date, date2, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(Date date, Date date2, String str, int i) throws CertificateException {
        this("localhost", date, date2, str, i);
    }

    public SelfSignedCertificate(String str) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA", DEFAULT_KEY_LENGTH_BITS);
    }

    public SelfSignedCertificate(String str, String str2, int i) throws CertificateException {
        this(str, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2, i);
    }

    public SelfSignedCertificate(String str, Date date, Date date2) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), DEFAULT_KEY_LENGTH_BITS, date, date2, "RSA");
    }

    public SelfSignedCertificate(String str, Date date, Date date2, String str2, int i) throws CertificateException {
        this(str, ThreadLocalInsecureRandom.current(), i, date, date2, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, "RSA");
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, String str2, int i) throws CertificateException {
        this(str, secureRandom, i, DEFAULT_NOT_BEFORE, DEFAULT_NOT_AFTER, str2);
    }

    public SelfSignedCertificate(String str, SecureRandom secureRandom, int i, Date date, Date date2) throws CertificateException {
        this(str, secureRandom, i, date, date2, "RSA");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.security.KeyPair] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SelfSignedCertificate(java.lang.String r9, java.security.SecureRandom r10, int r11, java.util.Date r12, java.util.Date r13, java.lang.String r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate.<init>(java.lang.String, java.security.SecureRandom, int, java.util.Date, java.util.Date, java.lang.String):void");
    }

    static String[] newSelfSignedCertificate(String str, PrivateKey privateKey, X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        ByteBuf byteBufWrappedBuffer = Unpooled.wrappedBuffer(privateKey.getEncoded());
        try {
            try {
                String str2 = "-----BEGIN PRIVATE KEY-----\n" + Base64.encode(byteBufWrappedBuffer, true).toString(CharsetUtil.US_ASCII) + "\n-----END PRIVATE KEY-----\n";
                byteBufWrappedBuffer.release();
                File fileCreateTempFile = File.createTempFile("keyutil_" + str + '_', ".key");
                fileCreateTempFile.deleteOnExit();
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                try {
                    fileOutputStream.write(str2.getBytes(CharsetUtil.US_ASCII));
                    fileOutputStream.close();
                    byteBufWrappedBuffer = Unpooled.wrappedBuffer(x509Certificate.getEncoded());
                    try {
                        try {
                            String str3 = "-----BEGIN CERTIFICATE-----\n" + Base64.encode(byteBufWrappedBuffer, true).toString(CharsetUtil.US_ASCII) + "\n-----END CERTIFICATE-----\n";
                            byteBufWrappedBuffer.release();
                            File fileCreateTempFile2 = File.createTempFile("keyutil_" + str + '_', ".crt");
                            fileCreateTempFile2.deleteOnExit();
                            FileOutputStream fileOutputStream2 = new FileOutputStream(fileCreateTempFile2);
                            try {
                                fileOutputStream2.write(str3.getBytes(CharsetUtil.US_ASCII));
                                fileOutputStream2.close();
                                return new String[]{fileCreateTempFile2.getPath(), fileCreateTempFile.getPath()};
                            } catch (Throwable th) {
                                safeClose(fileCreateTempFile2, fileOutputStream2);
                                safeDelete(fileCreateTempFile2);
                                safeDelete(fileCreateTempFile);
                                throw th;
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th2) {
                    safeClose(fileCreateTempFile, fileOutputStream);
                    safeDelete(fileCreateTempFile);
                    throw th2;
                }
            } finally {
            }
        } finally {
        }
    }

    private static void safeDelete(File file) {
        if (file.delete()) {
            return;
        }
        InternalLogger internalLogger = logger;
        if (internalLogger.isWarnEnabled()) {
            internalLogger.warn("Failed to delete a file: " + file);
        }
    }

    private static void safeClose(File file, OutputStream outputStream) throws IOException {
        try {
            outputStream.close();
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a file: " + file, (Throwable) e);
            }
        }
    }

    public X509Certificate cert() {
        return this.cert;
    }

    public File certificate() {
        return this.certificate;
    }

    public PrivateKey key() {
        return this.key;
    }

    public File privateKey() {
        return this.privateKey;
    }

    public void delete() {
        safeDelete(this.certificate);
        safeDelete(this.privateKey);
    }
}
