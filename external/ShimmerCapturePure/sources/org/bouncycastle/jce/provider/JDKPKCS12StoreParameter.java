package org.bouncycastle.jce.provider;

import java.io.OutputStream;
import java.security.KeyStore;

/* loaded from: classes5.dex */
public class JDKPKCS12StoreParameter implements KeyStore.LoadStoreParameter {
    private OutputStream outputStream;
    private KeyStore.ProtectionParameter protectionParameter;
    private boolean useDEREncoding;

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.protectionParameter;
    }

    public void setProtectionParameter(KeyStore.ProtectionParameter protectionParameter) {
        this.protectionParameter = protectionParameter;
    }

    public boolean isUseDEREncoding() {
        return this.useDEREncoding;
    }

    public void setUseDEREncoding(boolean z) {
        this.useDEREncoding = z;
    }

    public void setPassword(char[] cArr) {
        this.protectionParameter = new KeyStore.PasswordProtection(cArr);
    }
}
