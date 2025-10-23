package org.bouncycastle.pqc.crypto.gmss.util;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes5.dex */
public class GMSSUtil {
    public int bytesToIntLittleEndian(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public int bytesToIntLittleEndian(byte[] bArr, int i) {
        int i2 = ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
        return ((bArr[i + 3] & 255) << 24) | i2 | ((bArr[i + 2] & 255) << 16);
    }

    public byte[] concatenateArray(byte[][] bArr) {
        byte[] bArr2 = new byte[bArr.length * bArr[0].length];
        int length = 0;
        for (int i = 0; i < bArr.length; i++) {
            byte[] bArr3 = bArr[i];
            System.arraycopy(bArr3, 0, bArr2, length, bArr3.length);
            length += bArr[i].length;
        }
        return bArr2;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public byte[] intToBytesLittleEndian(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public void printArray(String str, byte[] bArr) {
        System.out.println(str);
        int i = 0;
        for (byte b : bArr) {
            System.out.println(i + VectorFormat.DEFAULT_SEPARATOR + ((int) b));
            i++;
        }
    }

    public void printArray(String str, byte[][] bArr) {
        System.out.println(str);
        int i = 0;
        for (byte[] bArr2 : bArr) {
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                System.out.println(i + VectorFormat.DEFAULT_SEPARATOR + ((int) bArr2[i2]));
                i++;
            }
        }
    }

    public boolean testPowerOfTwo(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        return i == i2;
    }
}
