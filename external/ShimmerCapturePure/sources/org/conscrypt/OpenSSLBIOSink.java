package org.conscrypt;

import java.io.ByteArrayOutputStream;

/* loaded from: classes5.dex */
final class OpenSSLBIOSink {
    private final ByteArrayOutputStream buffer;
    private final long ctx;
    private int position;

    private OpenSSLBIOSink(ByteArrayOutputStream byteArrayOutputStream) {
        this.ctx = NativeCrypto.create_BIO_OutputStream(byteArrayOutputStream);
        this.buffer = byteArrayOutputStream;
    }

    static OpenSSLBIOSink create() {
        return new OpenSSLBIOSink(new ByteArrayOutputStream());
    }

    long getContext() {
        return this.ctx;
    }

    int position() {
        return this.position;
    }

    int available() {
        return this.buffer.size() - this.position;
    }

    void reset() {
        this.buffer.reset();
        this.position = 0;
    }

    long skip(long j) {
        int iMin = Math.min(available(), (int) j);
        int i = this.position + iMin;
        this.position = i;
        if (i == this.buffer.size()) {
            reset();
        }
        return iMin;
    }

    byte[] toByteArray() {
        return this.buffer.toByteArray();
    }

    protected void finalize() throws Throwable {
        try {
            NativeCrypto.BIO_free_all(this.ctx);
        } finally {
            super.finalize();
        }
    }
}
