package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class BaseNCodecInputStream extends FilterInputStream {
    private final BaseNCodec baseNCodec;
    private final boolean doEncode;
    private final byte[] singleByte;

    protected BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean z) {
        super(inputStream);
        this.singleByte = new byte[1];
        this.doEncode = z;
        this.baseNCodec = baseNCodec;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = read(this.singleByte, 0, 1);
        while (i == 0) {
            i = read(this.singleByte, 0, 1);
        }
        if (i <= 0) {
            return -1;
        }
        byte b = this.singleByte[0];
        return b < 0 ? b + 256 : b;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        bArr.getClass();
        if (i < 0 || i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i > bArr.length || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        int results = 0;
        while (results == 0) {
            if (!this.baseNCodec.hasData()) {
                byte[] bArr2 = new byte[this.doEncode ? 4096 : 8192];
                int i3 = this.in.read(bArr2);
                if (this.doEncode) {
                    this.baseNCodec.encode(bArr2, 0, i3);
                } else {
                    this.baseNCodec.decode(bArr2, 0, i3);
                }
            }
            results = this.baseNCodec.readResults(bArr, i, i2);
        }
        return results;
    }
}
