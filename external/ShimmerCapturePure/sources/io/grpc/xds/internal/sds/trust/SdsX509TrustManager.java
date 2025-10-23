package io.grpc.xds.internal.sds.trust;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
final class SdsX509TrustManager extends X509ExtendedTrustManager implements X509TrustManager {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    private static final int ALT_URI_NAME = 6;
    private final CertificateValidationContext certContext;
    private final X509ExtendedTrustManager delegate;

    SdsX509TrustManager(@Nullable CertificateValidationContext certificateValidationContext, X509ExtendedTrustManager x509ExtendedTrustManager) {
        Preconditions.checkNotNull(x509ExtendedTrustManager, "delegate");
        this.certContext = certificateValidationContext;
        this.delegate = x509ExtendedTrustManager;
    }

    private static boolean verifyDnsNameInPattern(String str, StringMatcher stringMatcher) {
        String exact = stringMatcher.getExact();
        if (exact == null || exact.length() == 0 || exact.startsWith(".") || exact.endsWith("..") || str == null || str.length() == 0 || str.startsWith(".") || str.endsWith("..")) {
            return false;
        }
        if (!exact.endsWith(".")) {
            exact = exact + '.';
        }
        if (!str.endsWith(".")) {
            str = str + '.';
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (!lowerCase.contains("*")) {
            return exact.equals(lowerCase);
        }
        if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || exact.length() < lowerCase.length() || "*.".equals(lowerCase)) {
            return false;
        }
        String strSubstring = lowerCase.substring(1);
        if (!exact.endsWith(strSubstring)) {
            return false;
        }
        int length = exact.length() - strSubstring.length();
        return length <= 0 || exact.lastIndexOf(46, length - 1) == -1;
    }

    private static boolean verifyDnsNameInSanList(String str, List<StringMatcher> list) {
        Iterator<StringMatcher> it2 = list.iterator();
        while (it2.hasNext()) {
            if (verifyDnsNameInPattern(str, it2.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean verifyStringInSanList(String str, List<StringMatcher> list) {
        Iterator<StringMatcher> it2 = list.iterator();
        while (it2.hasNext()) {
            if (Ascii.equalsIgnoreCase(it2.next().getExact(), str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean verifyOneSanInList(List<?> list, List<StringMatcher> list2) throws CertificateParsingException {
        if (list == null || list.size() < 2) {
            throw new CertificateParsingException("Invalid SAN entry");
        }
        Integer num = (Integer) list.get(0);
        if (num == null) {
            throw new CertificateParsingException("Invalid SAN entry: null altNameType");
        }
        String str = (String) list.get(1);
        int iIntValue = num.intValue();
        if (iIntValue == 2) {
            return verifyDnsNameInSanList(str, list2);
        }
        if (iIntValue == 6 || iIntValue == 7) {
            return verifyStringInSanList(str, list2);
        }
        throw new CertificateParsingException("Unsupported altNameType: " + num);
    }

    private static void verifySubjectAltNameInLeaf(X509Certificate x509Certificate, List<StringMatcher> list) throws CertificateException {
        Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        if (subjectAlternativeNames == null || subjectAlternativeNames.isEmpty()) {
            throw new CertificateException("Peer certificate SAN check failed");
        }
        Iterator<List<?>> it2 = subjectAlternativeNames.iterator();
        while (it2.hasNext()) {
            if (verifyOneSanInList(it2.next(), list)) {
                return;
            }
        }
        throw new CertificateException("Peer certificate SAN check failed");
    }

    void verifySubjectAltNameInChain(X509Certificate[] x509CertificateArr) throws CertificateException {
        CertificateValidationContext certificateValidationContext = this.certContext;
        if (certificateValidationContext == null) {
            return;
        }
        List<StringMatcher> matchSubjectAltNamesList = certificateValidationContext.getMatchSubjectAltNamesList();
        if (matchSubjectAltNamesList.isEmpty()) {
            return;
        }
        if (x509CertificateArr == null || x509CertificateArr.length < 1) {
            throw new CertificateException("Peer certificate(s) missing");
        }
        verifySubjectAltNameInLeaf(x509CertificateArr[0], matchSubjectAltNamesList);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.delegate.checkClientTrusted(x509CertificateArr, str, socket);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.delegate.checkClientTrusted(x509CertificateArr, str, sSLEngine);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.delegate.checkClientTrusted(x509CertificateArr, str);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        SSLSocket sSLSocket;
        SSLParameters sSLParameters;
        if ((socket instanceof SSLSocket) && (sSLParameters = (sSLSocket = (SSLSocket) socket).getSSLParameters()) != null) {
            sSLParameters.setEndpointIdentificationAlgorithm(null);
            sSLSocket.setSSLParameters(sSLParameters);
        }
        this.delegate.checkServerTrusted(x509CertificateArr, str, socket);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        SSLParameters sSLParameters = sSLEngine.getSSLParameters();
        if (sSLParameters != null) {
            sSLParameters.setEndpointIdentificationAlgorithm(null);
            sSLEngine.setSSLParameters(sSLParameters);
        }
        this.delegate.checkServerTrusted(x509CertificateArr, str, sSLEngine);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.delegate.checkServerTrusted(x509CertificateArr, str);
        verifySubjectAltNameInChain(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.delegate.getAcceptedIssuers();
    }
}
