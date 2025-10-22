package com.google.api.client.testing.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class TestableByteArrayOutputStream extends ByteArrayOutputStream {
    private boolean closed;

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }

    public final boolean isClosed() {
        return this.closed;
    }

    public final byte[] getBuffer() {
        return this.buf;
    }
}
