package com.shimmerresearch.driverUtilities;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class MultiOutputStream extends OutputStream {
    OutputStream[] outputStreams;

    public MultiOutputStream(OutputStream... outputStreamArr) {
        this.outputStreams = outputStreamArr;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        for (OutputStream outputStream : this.outputStreams) {
            outputStream.write(i);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        for (OutputStream outputStream : this.outputStreams) {
            outputStream.write(bArr);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (OutputStream outputStream : this.outputStreams) {
            outputStream.write(bArr, i, i2);
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        for (OutputStream outputStream : this.outputStreams) {
            outputStream.flush();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (OutputStream outputStream : this.outputStreams) {
            outputStream.close();
        }
    }
}
