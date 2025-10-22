package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
final class ByteCountingOutputStream extends OutputStream {
    long count;

    ByteCountingOutputStream() {
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.count++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.count += i2;
    }
}
