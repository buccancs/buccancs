package com.google.api.client.json.webtoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public class JsonWebSignature extends JsonWebToken {
    private final byte[] signatureBytes;
    private final byte[] signedContentBytes;

    public JsonWebSignature(Header header, JsonWebToken.Payload payload, byte[] bArr, byte[] bArr2) {
        super(header, payload);
        this.signatureBytes = (byte[]) Preconditions.checkNotNull(bArr);
        this.signedContentBytes = (byte[]) Preconditions.checkNotNull(bArr2);
    }

    private static X509TrustManager getDefaultX509TrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
        } catch (KeyStoreException | NoSuchAlgorithmException unused) {
        }
        return null;
    }

    public static JsonWebSignature parse(JsonFactory jsonFactory, String str) throws IOException {
        return parser(jsonFactory).parse(str);
    }

    public static Parser parser(JsonFactory jsonFactory) {
        return new Parser(jsonFactory);
    }

    public static String signUsingRsaSha256(PrivateKey privateKey, JsonFactory jsonFactory, Header header, JsonWebToken.Payload payload) throws GeneralSecurityException, IOException {
        String str = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "." + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
        return str + "." + Base64.encodeBase64URLSafeString(SecurityUtils.sign(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, StringUtils.getBytesUtf8(str)));
    }

    @Override // com.google.api.client.json.webtoken.JsonWebToken
    public Header getHeader() {
        return (Header) super.getHeader();
    }

    public final boolean verifySignature(PublicKey publicKey) throws GeneralSecurityException {
        if ("RS256".equals(getHeader().getAlgorithm())) {
            return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), publicKey, this.signatureBytes, this.signedContentBytes);
        }
        return false;
    }

    public final X509Certificate verifySignature(X509TrustManager x509TrustManager) throws GeneralSecurityException {
        List<String> x509Certificates = getHeader().getX509Certificates();
        if (x509Certificates == null || x509Certificates.isEmpty() || !"RS256".equals(getHeader().getAlgorithm())) {
            return null;
        }
        return SecurityUtils.verify(SecurityUtils.getSha256WithRsaSignatureAlgorithm(), x509TrustManager, x509Certificates, this.signatureBytes, this.signedContentBytes);
    }

    public final X509Certificate verifySignature() throws GeneralSecurityException {
        X509TrustManager defaultX509TrustManager = getDefaultX509TrustManager();
        if (defaultX509TrustManager == null) {
            return null;
        }
        return verifySignature(defaultX509TrustManager);
    }

    public final byte[] getSignatureBytes() {
        byte[] bArr = this.signatureBytes;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public final byte[] getSignedContentBytes() {
        byte[] bArr = this.signedContentBytes;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public static class Header extends JsonWebToken.Header {

        @Key("alg")
        private String algorithm;

        @Key("crit")
        private List<String> critical;

        @Key("jwk")
        private String jwk;

        @Key("jku")
        private String jwkUrl;

        @Key("kid")
        private String keyId;

        @Key("x5c")
        private ArrayList<String> x509Certificates;

        @Key("x5t")
        private String x509Thumbprint;

        @Key("x5u")
        private String x509Url;

        public final String getAlgorithm() {
            return this.algorithm;
        }

        public Header setAlgorithm(String str) {
            this.algorithm = str;
            return this;
        }

        public final String getJwk() {
            return this.jwk;
        }

        public Header setJwk(String str) {
            this.jwk = str;
            return this;
        }

        public final String getJwkUrl() {
            return this.jwkUrl;
        }

        public Header setJwkUrl(String str) {
            this.jwkUrl = str;
            return this;
        }

        public final String getKeyId() {
            return this.keyId;
        }

        public Header setKeyId(String str) {
            this.keyId = str;
            return this;
        }

        public final String getX509Thumbprint() {
            return this.x509Thumbprint;
        }

        public Header setX509Thumbprint(String str) {
            this.x509Thumbprint = str;
            return this;
        }

        public final String getX509Url() {
            return this.x509Url;
        }

        public Header setX509Url(String str) {
            this.x509Url = str;
            return this;
        }

        @Override // com.google.api.client.json.webtoken.JsonWebToken.Header
        public Header setType(String str) {
            super.setType(str);
            return this;
        }

        public final List<String> getX509Certificates() {
            return new ArrayList(this.x509Certificates);
        }

        public Header setX509Certificates(List<String> list) {
            this.x509Certificates = new ArrayList<>(list);
            return this;
        }

        public final List<String> getCritical() {
            List<String> list = this.critical;
            if (list == null || list.isEmpty()) {
                return null;
            }
            return new ArrayList(this.critical);
        }

        public Header setCritical(List<String> list) {
            this.critical = new ArrayList(list);
            return this;
        }

        @Override
        // com.google.api.client.json.webtoken.JsonWebToken.Header, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
        public Header set(String str, Object obj) {
            return (Header) super.set(str, obj);
        }

        @Override
        // com.google.api.client.json.webtoken.JsonWebToken.Header, com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
        public Header clone() {
            return (Header) super.clone();
        }
    }

    public static final class Parser {
        private final JsonFactory jsonFactory;
        private Class<? extends Header> headerClass = Header.class;
        private Class<? extends JsonWebToken.Payload> payloadClass = JsonWebToken.Payload.class;

        public Parser(JsonFactory jsonFactory) {
            this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
        }

        public Class<? extends Header> getHeaderClass() {
            return this.headerClass;
        }

        public Parser setHeaderClass(Class<? extends Header> cls) {
            this.headerClass = cls;
            return this;
        }

        public JsonFactory getJsonFactory() {
            return this.jsonFactory;
        }

        public Class<? extends JsonWebToken.Payload> getPayloadClass() {
            return this.payloadClass;
        }

        public Parser setPayloadClass(Class<? extends JsonWebToken.Payload> cls) {
            this.payloadClass = cls;
            return this;
        }

        public JsonWebSignature parse(String str) throws IOException {
            int iIndexOf = str.indexOf(46);
            Preconditions.checkArgument(iIndexOf != -1);
            byte[] bArrDecodeBase64 = Base64.decodeBase64(str.substring(0, iIndexOf));
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(46, i);
            Preconditions.checkArgument(iIndexOf2 != -1);
            int i2 = iIndexOf2 + 1;
            Preconditions.checkArgument(str.indexOf(46, i2) == -1);
            byte[] bArrDecodeBase642 = Base64.decodeBase64(str.substring(i, iIndexOf2));
            byte[] bArrDecodeBase643 = Base64.decodeBase64(str.substring(i2));
            byte[] bytesUtf8 = StringUtils.getBytesUtf8(str.substring(0, iIndexOf2));
            Header header = (Header) this.jsonFactory.fromInputStream(new ByteArrayInputStream(bArrDecodeBase64), this.headerClass);
            Preconditions.checkArgument(header.getAlgorithm() != null);
            return new JsonWebSignature(header, (JsonWebToken.Payload) this.jsonFactory.fromInputStream(new ByteArrayInputStream(bArrDecodeBase642), this.payloadClass), bArrDecodeBase643, bytesUtf8);
        }
    }
}
