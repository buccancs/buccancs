package org.conscrypt;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.MacSpi;
import javax.crypto.SecretKey;

import org.conscrypt.EvpMdRef;
import org.conscrypt.NativeRef;

/* loaded from: classes5.dex */
public abstract class OpenSSLMac extends MacSpi {
    private final long evp_md;
    private final byte[] singleByte;
    private final int size;
    private NativeRef.HMAC_CTX ctx;
    private byte[] keyBytes;

    private OpenSSLMac(long j, int i) {
        this.singleByte = new byte[1];
        this.evp_md = j;
        this.size = i;
    }

    @Override // javax.crypto.MacSpi
    protected int engineGetMacLength() {
        return this.size;
    }

    @Override // javax.crypto.MacSpi
    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("key must be a SecretKey");
        }
        if (algorithmParameterSpec != null) {
            throw new InvalidAlgorithmParameterException("unknown parameter type");
        }
        byte[] encoded = key.getEncoded();
        this.keyBytes = encoded;
        if (encoded == null) {
            throw new InvalidKeyException("key cannot be encoded");
        }
        resetContext();
    }

    private final void resetContext() {
        NativeRef.HMAC_CTX hmac_ctx = new NativeRef.HMAC_CTX(NativeCrypto.HMAC_CTX_new());
        byte[] bArr = this.keyBytes;
        if (bArr != null) {
            NativeCrypto.HMAC_Init_ex(hmac_ctx, bArr, this.evp_md);
        }
        this.ctx = hmac_ctx;
    }

    @Override // javax.crypto.MacSpi
    protected void engineUpdate(byte b) {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        engineUpdate(bArr, 0, 1);
    }

    @Override // javax.crypto.MacSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) {
        NativeCrypto.HMAC_Update(this.ctx, bArr, i, i2);
    }

    @Override // javax.crypto.MacSpi
    protected void engineUpdate(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            if (!byteBuffer.isDirect()) {
                super.engineUpdate(byteBuffer);
                return;
            }
            long directBufferAddress = NativeCrypto.getDirectBufferAddress(byteBuffer);
            if (directBufferAddress == 0) {
                super.engineUpdate(byteBuffer);
                return;
            }
            int iPosition = byteBuffer.position();
            if (iPosition < 0) {
                throw new RuntimeException("Negative position");
            }
            long j = directBufferAddress + iPosition;
            int iRemaining = byteBuffer.remaining();
            if (iRemaining < 0) {
                throw new RuntimeException("Negative remaining amount");
            }
            NativeCrypto.HMAC_UpdateDirect(this.ctx, j, iRemaining);
            byteBuffer.position(iPosition + iRemaining);
        }
    }

    @Override // javax.crypto.MacSpi
    protected byte[] engineDoFinal() {
        byte[] bArrHMAC_Final = NativeCrypto.HMAC_Final(this.ctx);
        resetContext();
        return bArrHMAC_Final;
    }

    @Override // javax.crypto.MacSpi
    protected void engineReset() {
        resetContext();
    }

    public static final class HmacMD5 extends OpenSSLMac {
        public HmacMD5() {
            super(EvpMdRef.MD5.EVP_MD, EvpMdRef.MD5.SIZE_BYTES);
        }
    }

    public static final class HmacSHA1 extends OpenSSLMac {
        public HmacSHA1() {
            super(EvpMdRef.SHA1.EVP_MD, EvpMdRef.SHA1.SIZE_BYTES);
        }
    }

    public static final class HmacSHA224 extends OpenSSLMac {
        public HmacSHA224() throws NoSuchAlgorithmException {
            super(EvpMdRef.SHA224.EVP_MD, EvpMdRef.SHA224.SIZE_BYTES);
        }
    }

    public static final class HmacSHA256 extends OpenSSLMac {
        public HmacSHA256() throws NoSuchAlgorithmException {
            super(EvpMdRef.SHA256.EVP_MD, EvpMdRef.SHA256.SIZE_BYTES);
        }
    }

    public static final class HmacSHA384 extends OpenSSLMac {
        public HmacSHA384() throws NoSuchAlgorithmException {
            super(EvpMdRef.SHA384.EVP_MD, EvpMdRef.SHA384.SIZE_BYTES);
        }
    }

    public static final class HmacSHA512 extends OpenSSLMac {
        public HmacSHA512() {
            super(EvpMdRef.SHA512.EVP_MD, EvpMdRef.SHA512.SIZE_BYTES);
        }
    }
}
