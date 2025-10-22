package org.conscrypt;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class OpenSSLX509CertificateFactory extends CertificateFactorySpi {
    private static final byte[] PKCS7_MARKER = {ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, 66, 69, 71, 73, 78, 32, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 75, 67, 83, 55};
    private static final int PUSHBACK_SIZE = 64;
    private Parser<OpenSSLX509Certificate> certificateParser = new Parser<OpenSSLX509Certificate>() { // from class: org.conscrypt.OpenSSLX509CertificateFactory.1
        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509Certificate fromX509PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromX509PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509Certificate fromX509DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromX509DerInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509Certificate> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromPkcs7PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509Certificate> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromPkcs7DerInputStream(inputStream);
        }
    };
    private Parser<OpenSSLX509CRL> crlParser = new Parser<OpenSSLX509CRL>() { // from class: org.conscrypt.OpenSSLX509CertificateFactory.2
        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509CRL fromX509PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromX509PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509CRL fromX509DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromX509DerInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509CRL> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromPkcs7PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509CRL> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromPkcs7DerInputStream(inputStream);
        }
    };

    @Override // java.security.cert.CertificateFactorySpi
    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        try {
            return this.certificateParser.generateItem(inputStream);
        } catch (ParsingException e) {
            throw new CertificateException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends Certificate> engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        try {
            return this.certificateParser.generateItems(inputStream);
        } catch (ParsingException e) {
            throw new CertificateException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        try {
            return this.crlParser.generateItem(inputStream);
        } catch (ParsingException e) {
            throw new CRLException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends CRL> engineGenerateCRLs(InputStream inputStream) throws CRLException {
        if (inputStream == null) {
            return Collections.emptyList();
        }
        try {
            return this.crlParser.generateItems(inputStream);
        } catch (ParsingException e) {
            throw new CRLException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Iterator<String> engineGetCertPathEncodings() {
        return OpenSSLX509CertPath.getEncodingsIterator();
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream) throws CertificateException {
        return OpenSSLX509CertPath.fromEncoding(inputStream);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream, String str) throws CertificateException {
        return OpenSSLX509CertPath.fromEncoding(inputStream, str);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(List<? extends Certificate> list) throws CertificateException {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            Certificate certificate = list.get(i);
            if (!(certificate instanceof X509Certificate)) {
                throw new CertificateException("Certificate not X.509 type at index " + i);
            }
            arrayList.add((X509Certificate) certificate);
        }
        return new OpenSSLX509CertPath(arrayList);
    }

    static class ParsingException extends Exception {
        private static final long serialVersionUID = 8390802697728301325L;

        ParsingException(String str) {
            super(str);
        }

        ParsingException(Exception exc) {
            super(exc);
        }

        ParsingException(String str, Exception exc) {
            super(str, exc);
        }
    }

    private static abstract class Parser<T> {
        private Parser() {
        }

        protected abstract List<? extends T> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException;

        protected abstract List<? extends T> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException;

        protected abstract T fromX509DerInputStream(InputStream inputStream) throws ParsingException;

        protected abstract T fromX509PemInputStream(InputStream inputStream) throws ParsingException;

        T generateItem(InputStream inputStream) throws ParsingException, IOException {
            if (inputStream == null) {
                throw new ParsingException("inStream == null");
            }
            boolean zMarkSupported = inputStream.markSupported();
            if (zMarkSupported) {
                inputStream.mark(OpenSSLX509CertificateFactory.PKCS7_MARKER.length);
            }
            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 64);
            try {
                byte[] bArr = new byte[OpenSSLX509CertificateFactory.PKCS7_MARKER.length];
                int i = pushbackInputStream.read(bArr);
                if (i < 0) {
                    throw new ParsingException("inStream is empty");
                }
                pushbackInputStream.unread(bArr, 0, i);
                if (bArr[0] == 45) {
                    if (i == OpenSSLX509CertificateFactory.PKCS7_MARKER.length && Arrays.equals(OpenSSLX509CertificateFactory.PKCS7_MARKER, bArr)) {
                        List<? extends T> listFromPkcs7PemInputStream = fromPkcs7PemInputStream(pushbackInputStream);
                        if (listFromPkcs7PemInputStream.size() == 0) {
                            return null;
                        }
                        listFromPkcs7PemInputStream.get(0);
                    } else {
                        return fromX509PemInputStream(pushbackInputStream);
                    }
                }
                if (bArr[4] == 6) {
                    List<? extends T> listFromPkcs7DerInputStream = fromPkcs7DerInputStream(pushbackInputStream);
                    if (listFromPkcs7DerInputStream.size() == 0) {
                        return null;
                    }
                    return listFromPkcs7DerInputStream.get(0);
                }
                return fromX509DerInputStream(pushbackInputStream);
            } catch (Exception e) {
                if (zMarkSupported) {
                    try {
                        inputStream.reset();
                    } catch (IOException unused) {
                    }
                }
                throw new ParsingException(e);
            }
        }

        Collection<? extends T> generateItems(InputStream inputStream) throws ParsingException, IOException {
            T tGenerateItem;
            if (inputStream == null) {
                throw new ParsingException("inStream == null");
            }
            try {
                if (inputStream.available() == 0) {
                    return Collections.emptyList();
                }
                boolean zMarkSupported = inputStream.markSupported();
                if (zMarkSupported) {
                    inputStream.mark(64);
                }
                PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 64);
                try {
                    byte[] bArr = new byte[OpenSSLX509CertificateFactory.PKCS7_MARKER.length];
                    int i = pushbackInputStream.read(bArr);
                    if (i < 0) {
                        throw new ParsingException("inStream is empty");
                    }
                    pushbackInputStream.unread(bArr, 0, i);
                    if (i == OpenSSLX509CertificateFactory.PKCS7_MARKER.length && Arrays.equals(OpenSSLX509CertificateFactory.PKCS7_MARKER, bArr)) {
                        return fromPkcs7PemInputStream(pushbackInputStream);
                    }
                    if (bArr[4] == 6) {
                        return fromPkcs7DerInputStream(pushbackInputStream);
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        if (zMarkSupported) {
                            inputStream.mark(64);
                        }
                        try {
                            tGenerateItem = generateItem(pushbackInputStream);
                            arrayList.add(tGenerateItem);
                        } catch (ParsingException unused) {
                            if (zMarkSupported) {
                                try {
                                    inputStream.reset();
                                } catch (IOException unused2) {
                                }
                            }
                            tGenerateItem = null;
                        }
                    } while (tGenerateItem != null);
                    return arrayList;
                } catch (Exception e) {
                    if (zMarkSupported) {
                        try {
                            inputStream.reset();
                        } catch (IOException unused3) {
                        }
                    }
                    throw new ParsingException(e);
                }
            } catch (IOException e2) {
                throw new ParsingException("Problem reading input stream", e2);
            }
        }
    }
}
