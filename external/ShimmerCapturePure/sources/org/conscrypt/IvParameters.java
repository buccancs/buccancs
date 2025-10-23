package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes5.dex */
public class IvParameters extends AlgorithmParametersSpi {
    private byte[] iv;

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "Conscrypt IV AlgorithmParameters";
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
            throw new InvalidParameterSpecException("Only IvParameterSpec is supported");
        }
        this.iv = (byte[]) ((IvParameterSpec) algorithmParameterSpec).getIV().clone();
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws Throwable {
        long jAsn1_read_init;
        try {
            jAsn1_read_init = NativeCrypto.asn1_read_init(bArr);
            try {
                byte[] bArrAsn1_read_octetstring = NativeCrypto.asn1_read_octetstring(jAsn1_read_init);
                if (!NativeCrypto.asn1_read_is_empty(jAsn1_read_init)) {
                    throw new IOException("Error reading ASN.1 encoding");
                }
                this.iv = bArrAsn1_read_octetstring;
                NativeCrypto.asn1_read_free(jAsn1_read_init);
            } catch (Throwable th) {
                th = th;
                NativeCrypto.asn1_read_free(jAsn1_read_init);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            jAsn1_read_init = 0;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws Throwable {
        if (str == null || str.equals("ASN.1")) {
            engineInit(bArr);
        } else if (str.equals("RAW")) {
            this.iv = (byte[]) bArr.clone();
        } else {
            throw new IOException("Unsupported format: " + str);
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (cls != IvParameterSpec.class) {
            throw new InvalidParameterSpecException("Incompatible AlgorithmParametersSpec class: " + cls);
        }
        return new IvParameterSpec(this.iv);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        long jAsn1_write_init = 0;
        try {
            try {
                jAsn1_write_init = NativeCrypto.asn1_write_init();
                NativeCrypto.asn1_write_octetstring(jAsn1_write_init, this.iv);
                return NativeCrypto.asn1_write_finish(jAsn1_write_init);
            } catch (IOException e) {
                NativeCrypto.asn1_write_cleanup(jAsn1_write_init);
                throw e;
            }
        } finally {
            NativeCrypto.asn1_write_free(jAsn1_write_init);
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (str == null || str.equals("ASN.1")) {
            return engineGetEncoded();
        }
        if (str.equals("RAW")) {
            return (byte[]) this.iv.clone();
        }
        throw new IOException("Unsupported format: " + str);
    }

    public static class AES extends IvParameters {
    }

    public static class ChaCha20 extends IvParameters {
    }

    public static class DESEDE extends IvParameters {
    }
}
