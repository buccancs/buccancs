package org.bouncycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;

/* loaded from: classes5.dex */
public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings;
    private OperatorHelper helper;
    private PrivateKey privKey;
    private boolean unwrappedKeyMustBeEncodable;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.privKey = privateKey;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    @Override // org.bouncycastle.operator.KeyUnwrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.operator.GenericKey generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r6, byte[] r7) throws java.security.NoSuchAlgorithmException, java.io.IOException, java.security.InvalidKeyException, org.bouncycastle.operator.OperatorException, java.security.InvalidAlgorithmParameterException {
        /*
            r5 = this;
            org.bouncycastle.operator.jcajce.OperatorHelper r0 = r5.helper     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            java.util.Map r2 = r5.extraMappings     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            javax.crypto.Cipher r0 = r0.createAsymmetricWrapper(r1, r2)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.operator.jcajce.OperatorHelper r1 = r5.helper     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r5.getAlgorithmIdentifier()     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            java.security.AlgorithmParameters r1 = r1.createAlgorithmParameters(r2)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r2 = 4
            r3 = 0
            if (r1 == 0) goto L24
            java.security.PrivateKey r4 = r5.privKey     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r0.init(r2, r4, r1)     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            goto L29
        L24:
            java.security.PrivateKey r1 = r5.privKey     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r0.init(r2, r1)     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
        L29:
            org.bouncycastle.operator.jcajce.OperatorHelper r1 = r5.helper     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r6.getAlgorithm()     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            java.lang.String r1 = r1.getKeyAlgorithmName(r2)     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r2 = 3
            java.security.Key r1 = r0.unwrap(r7, r1, r2)     // Catch: java.lang.Throwable -> L48 javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            boolean r2 = r5.unwrappedKeyMustBeEncodable     // Catch: java.lang.Throwable -> L4a javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            if (r2 == 0) goto L46
            byte[] r2 = r1.getEncoded()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a java.lang.Throwable -> L4a java.lang.Throwable -> L4a javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            if (r2 == 0) goto L4c
            int r2 = r2.length     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a java.lang.Throwable -> L4a java.lang.Throwable -> L4a javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            if (r2 != 0) goto L46
            goto L4c
        L46:
            r3 = r1
            goto L4c
        L48:
            goto L4c
        L4a:
            goto L46
        L4c:
            if (r3 != 0) goto L65
            java.security.PrivateKey r1 = r5.privKey     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r2 = 2
            r0.init(r2, r1)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            byte[] r7 = r0.doFinal(r7)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r6.getAlgorithm()     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            java.lang.String r0 = r0.getId()     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r3.<init>(r7, r0)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
        L65:
            org.bouncycastle.operator.jcajce.JceGenericKey r7 = new org.bouncycastle.operator.jcajce.JceGenericKey     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            r7.<init>(r6, r3)     // Catch: javax.crypto.BadPaddingException -> L6b javax.crypto.IllegalBlockSizeException -> L84 java.security.InvalidKeyException -> L9d
            return r7
        L6b:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "bad padding: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L84:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "illegal blocksize: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L9d:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key invalid: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper.generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, byte[]):org.bouncycastle.operator.GenericKey");
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean z) {
        this.unwrappedKeyMustBeEncodable = z;
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
