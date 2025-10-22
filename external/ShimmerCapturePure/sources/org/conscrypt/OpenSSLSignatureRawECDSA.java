package org.conscrypt;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;

/* loaded from: classes5.dex */
public class OpenSSLSignatureRawECDSA extends SignatureSpi {
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private OpenSSLKey key;

    private static OpenSSLKey verifyKey(OpenSSLKey openSSLKey) throws InvalidKeyException {
        if (NativeCrypto.EVP_PKEY_type(openSSLKey.getNativeRef()) == 408) {
            return openSSLKey;
        }
        throw new InvalidKeyException("Non-EC key used to initialize EC signature.");
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) throws InvalidParameterException {
        return null;
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) throws InvalidParameterException {
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) {
        this.buffer.write(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        this.key = verifyKey(OpenSSLKey.fromPrivateKey(privateKey));
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        this.key = verifyKey(OpenSSLKey.fromPublicKey(publicKey));
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        OpenSSLKey openSSLKey = this.key;
        if (openSSLKey == null) {
            throw new SignatureException("No key provided");
        }
        int iECDSA_size = NativeCrypto.ECDSA_size(openSSLKey.getNativeRef());
        byte[] bArr = new byte[iECDSA_size];
        try {
            try {
                int iECDSA_sign = NativeCrypto.ECDSA_sign(this.buffer.toByteArray(), bArr, this.key.getNativeRef());
                if (iECDSA_sign < 0) {
                    throw new SignatureException("Could not compute signature.");
                }
                if (iECDSA_sign != iECDSA_size) {
                    byte[] bArr2 = new byte[iECDSA_sign];
                    System.arraycopy(bArr, 0, bArr2, 0, iECDSA_sign);
                    bArr = bArr2;
                }
                return bArr;
            } catch (Exception e) {
                throw new SignatureException(e);
            }
        } finally {
            this.buffer.reset();
        }
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        if (this.key == null) {
            throw new SignatureException("No key provided");
        }
        try {
            try {
                int iECDSA_verify = NativeCrypto.ECDSA_verify(this.buffer.toByteArray(), bArr, this.key.getNativeRef());
                if (iECDSA_verify != -1) {
                    return iECDSA_verify == 1;
                }
                throw new SignatureException("Could not verify signature.");
            } catch (Exception e) {
                throw new SignatureException(e);
            }
        } finally {
            this.buffer.reset();
        }
    }
}
