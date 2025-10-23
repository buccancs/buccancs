package io.grpc.netty.shaded.io.netty.handler.codec.compression;

/* loaded from: classes3.dex */
final class Bzip2MTFAndRLE2StageEncoder {
    private final int[] bwtBlock;
    private final int bwtLength;
    private final boolean[] bwtValuesPresent;
    private final char[] mtfBlock;
    private final int[] mtfSymbolFrequencies = new int[258];
    private int alphabetSize;
    private int mtfLength;

    Bzip2MTFAndRLE2StageEncoder(int[] iArr, int i, boolean[] zArr) {
        this.bwtBlock = iArr;
        this.bwtLength = i;
        this.bwtValuesPresent = zArr;
        this.mtfBlock = new char[i + 1];
    }

    int mtfAlphabetSize() {
        return this.alphabetSize;
    }

    char[] mtfBlock() {
        return this.mtfBlock;
    }

    int mtfLength() {
        return this.mtfLength;
    }

    int[] mtfSymbolFrequencies() {
        return this.mtfSymbolFrequencies;
    }

    void encode() {
        int i;
        int i2;
        int i3 = this.bwtLength;
        boolean[] zArr = this.bwtValuesPresent;
        int[] iArr = this.bwtBlock;
        char[] cArr = this.mtfBlock;
        int[] iArr2 = this.mtfSymbolFrequencies;
        byte[] bArr = new byte[256];
        Bzip2MoveToFrontTable bzip2MoveToFrontTable = new Bzip2MoveToFrontTable();
        char c = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 256; i5++) {
            if (zArr[i5]) {
                bArr[i5] = (byte) i4;
                i4++;
            }
        }
        int i6 = i4 + 1;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i7 < i3) {
            int iValueToFront = bzip2MoveToFrontTable.valueToFront(bArr[iArr[i7] & 255]);
            if (iValueToFront == 0) {
                i8++;
            } else {
                if (i8 > 0) {
                    int i12 = i8 - 1;
                    while (true) {
                        if ((i12 & 1) == 0) {
                            cArr[i9] = c;
                            i10++;
                            i9++;
                            i2 = 1;
                        } else {
                            i2 = 1;
                            cArr[i9] = 1;
                            i11++;
                            i9++;
                        }
                        if (i12 <= i2) {
                            break;
                        }
                        i12 = (i12 - 2) >>> i2;
                        c = 0;
                    }
                    i8 = 0;
                }
                int i13 = iValueToFront + 1;
                cArr[i9] = (char) i13;
                iArr2[i13] = iArr2[i13] + 1;
                i9++;
            }
            i7++;
            c = 0;
        }
        if (i8 > 0) {
            int i14 = i8 - 1;
            while (true) {
                if ((i14 & 1) == 0) {
                    cArr[i9] = 0;
                    i10++;
                    i9++;
                    i = 1;
                } else {
                    i = 1;
                    cArr[i9] = 1;
                    i11++;
                    i9++;
                }
                if (i14 <= i) {
                    break;
                } else {
                    i14 = (i14 - 2) >>> i;
                }
            }
        } else {
            i = 1;
        }
        cArr[i9] = (char) i6;
        iArr2[i6] = iArr2[i6] + i;
        iArr2[0] = iArr2[0] + i10;
        iArr2[i] = iArr2[i] + i11;
        this.mtfLength = i9 + i;
        this.alphabetSize = i4 + 2;
    }
}
