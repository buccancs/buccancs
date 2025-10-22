package org.bouncycastle.crypto.util;

import java.math.BigInteger;

import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
class SSHBuffer {
    private final byte[] buffer;
    private int pos = 0;

    public SSHBuffer(byte[] bArr) {
        this.buffer = bArr;
    }

    public SSHBuffer(byte[] bArr, byte[] bArr2) {
        this.buffer = bArr2;
        for (int i = 0; i != bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                throw new IllegalArgumentException("magic-number incorrect");
            }
        }
        this.pos += bArr.length;
    }

    public byte[] getBuffer() {
        return Arrays.clone(this.buffer);
    }

    public boolean hasRemaining() {
        return this.pos < this.buffer.length;
    }

    public BigInteger positiveBigNum() {
        int u32 = readU32();
        int i = this.pos;
        int i2 = i + u32;
        byte[] bArr = this.buffer;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("not enough data for big num");
        }
        byte[] bArr2 = new byte[u32];
        System.arraycopy(bArr, i, bArr2, 0, u32);
        this.pos += u32;
        return new BigInteger(1, bArr2);
    }

    public byte[] readPaddedString() {
        int u32 = readU32();
        if (u32 == 0) {
            return new byte[0];
        }
        int i = this.pos;
        int i2 = i + u32;
        byte[] bArr = this.buffer;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("not enough data for string");
        }
        int i3 = (u32 - (bArr[(i + u32) - 1] & 255)) + i;
        this.pos = i3;
        return Arrays.copyOfRange(bArr, i, i3);
    }

    public byte[] readString() {
        int u32 = readU32();
        if (u32 == 0) {
            return new byte[0];
        }
        int i = this.pos;
        int i2 = i + u32;
        byte[] bArr = this.buffer;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("not enough data for string");
        }
        int i3 = u32 + i;
        this.pos = i3;
        return Arrays.copyOfRange(bArr, i, i3);
    }

    public int readU32() {
        int i = this.pos;
        int i2 = i + 4;
        byte[] bArr = this.buffer;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("4 bytes for U32 exceeds buffer.");
        }
        int i3 = ((bArr[i + 1] & 255) << 16) | ((bArr[i] & 255) << 24);
        int i4 = i + 3;
        int i5 = i3 | ((bArr[i + 2] & 255) << 8);
        this.pos = i + 4;
        return (bArr[i4] & 255) | i5;
    }
}
