package org.bouncycastle.pqc.crypto.qtesla;

import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
class Polynomial {
    public static final int HASH = 32;
    public static final int MESSAGE = 64;
    public static final int PRIVATE_KEY_I = 1344;
    public static final int PRIVATE_KEY_III_P = 12352;
    public static final int PRIVATE_KEY_III_SIZE = 2112;
    public static final int PRIVATE_KEY_III_SPEED = 2368;
    public static final int PRIVATE_KEY_I_P = 5184;
    public static final int PUBLIC_KEY_I = 1504;
    public static final int PUBLIC_KEY_III_P = 39712;
    public static final int PUBLIC_KEY_III_SIZE = 2976;
    public static final int PUBLIC_KEY_III_SPEED = 3104;
    public static final int PUBLIC_KEY_I_P = 14880;
    public static final int RANDOM = 32;
    public static final int SEED = 32;
    public static final int SIGNATURE_I = 1376;
    public static final int SIGNATURE_III_P = 6176;
    public static final int SIGNATURE_III_SIZE = 2720;
    public static final int SIGNATURE_III_SPEED = 2848;
    public static final int SIGNATURE_I_P = 2848;

    Polynomial() {
    }

    public static int barrett(int i, int i2, int i3, int i4) {
        return i - (((int) ((i * i3) >> i4)) * i2);
    }

    public static long barrett(long j, int i, int i2, int i3) {
        return j - (((i2 * j) >> i3) * i);
    }

    private static void componentWisePolynomialMultiplication(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2, long j) {
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = montgomery(iArr2[i3] * iArr3[i3], i2, j);
        }
    }

    private static void componentWisePolynomialMultiplication(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4, int i5, long j) {
        for (int i6 = 0; i6 < i4; i6++) {
            jArr[i + i6] = montgomeryP(jArr2[i2 + i6] * jArr3[i3 + i6], i5, j);
        }
    }

    private static void inverseNumberTheoreticTransform(int[] iArr, int[] iArr2, int i, int i2, long j, int i3, int i4, int i5) {
        int i6 = 1;
        int i7 = 0;
        while (i6 < i) {
            int i8 = 0;
            while (i8 < i) {
                int i9 = i7 + 1;
                long j2 = iArr2[i7];
                int i10 = i8;
                while (i10 < i8 + i6) {
                    int i11 = iArr[i10];
                    if (i6 == 16) {
                        iArr[i10] = barrett(iArr[i10 + i6] + i11, i2, i4, i5);
                    } else {
                        iArr[i10] = iArr[i10 + i6] + i11;
                    }
                    iArr[i10 + i6] = montgomery((i11 - iArr[r12]) * j2, i2, j);
                    i10++;
                    i6 = i6;
                }
                i8 = i10 + i6;
                i7 = i9;
            }
            i6 *= 2;
        }
        for (int i12 = 0; i12 < i / 2; i12++) {
            iArr[i12] = montgomery(i3 * iArr[i12], i2, j);
        }
    }

    private static void inverseNumberTheoreticTransformI(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 1; i2 < 512; i2 *= 2) {
            int i3 = 0;
            while (i3 < 512) {
                int i4 = i + 1;
                long j = iArr2[i];
                int i5 = i3;
                while (i5 < i3 + i2) {
                    int i6 = i5 + i2;
                    iArr[i5] = iArr[i6] + iArr[i5];
                    iArr[i6] = montgomery((r11 - iArr[i6]) * j, Parameter.Q_I, Parameter.Q_INVERSE_I);
                    i5++;
                }
                i3 = i5 + i2;
                i = i4;
            }
        }
        for (int i7 = 0; i7 < 256; i7++) {
            iArr[i7] = montgomery(iArr[i7] * 1081347, Parameter.Q_I, Parameter.Q_INVERSE_I);
        }
    }

    private static void inverseNumberTheoreticTransformIIIP(long[] jArr, int i, long[] jArr2, int i2) {
        int i3 = 0;
        for (int i4 = 1; i4 < 2048; i4 *= 2) {
            int i5 = 0;
            while (i5 < 2048) {
                int i6 = i3 + 1;
                long j = jArr2[i2 + i3];
                int i7 = i5;
                while (i7 < i5 + i4) {
                    int i8 = i + i7;
                    long j2 = jArr[i8];
                    int i9 = i8 + i4;
                    jArr[i8] = barrett(jArr[i9] + j2, Parameter.Q_III_P, 15, 34);
                    jArr[i9] = barrett(montgomeryP((j2 + (2259451906L - jArr[i9])) * j, Parameter.Q_III_P, Parameter.Q_INVERSE_III_P), Parameter.Q_III_P, 15, 34);
                    i7++;
                }
                i5 = i7 + i4;
                i3 = i6;
            }
        }
    }

    private static void inverseNumberTheoreticTransformIP(long[] jArr, int i, long[] jArr2, int i2) {
        int i3 = 1;
        int i4 = 0;
        while (true) {
            if (i3 >= 1024) {
                return;
            }
            int i5 = 0;
            while (i5 < 1024) {
                int i6 = i4 + 1;
                long j = jArr2[i2 + i4];
                int i7 = i5;
                while (i7 < i5 + i3) {
                    int i8 = i + i7;
                    long j2 = jArr[i8];
                    int i9 = i8 + i3;
                    jArr[i8] = j2 + jArr[i9];
                    jArr[i9] = montgomeryP(j * (j2 + (971956226 - jArr[i9])), Parameter.Q_I_P, Parameter.Q_INVERSE_I_P);
                    i7++;
                }
                i5 = i7 + i3;
                i4 = i6;
            }
            int i10 = i3 * 2;
            int i11 = 0;
            for (int i12 = 1024; i11 < i12; i12 = 1024) {
                int i13 = i4 + 1;
                long j3 = jArr2[i2 + i4];
                int i14 = i11;
                while (i14 < i11 + i10) {
                    int i15 = i + i14;
                    long j4 = jArr[i15];
                    int i16 = i15 + i10;
                    jArr[i15] = barrett(j4 + jArr[i16], Parameter.Q_I_P, 1, 29);
                    jArr[i16] = montgomeryP(j3 * (j4 + (971956226 - jArr[i16])), Parameter.Q_I_P, Parameter.Q_INVERSE_I_P);
                    i14++;
                    i10 = i10;
                }
                i11 = i14 + i10;
                i4 = i13;
            }
            i3 *= 4;
        }
    }

    private static int montgomery(long j, int i, long j2) {
        return (int) ((j + (((j2 * j) & 4294967295L) * i)) >> 32);
    }

    private static long montgomeryP(long j, int i, long j2) {
        return (j + (((j2 * j) & 4294967295L) * i)) >> 32;
    }

    private static void numberTheoreticTransform(int[] iArr, int[] iArr2, int i, int i2, long j) {
        int i3 = 0;
        for (int i4 = i >> 1; i4 > 0; i4 >>= 1) {
            int i5 = 0;
            while (i5 < i) {
                int i6 = i3 + 1;
                long j2 = iArr2[i3];
                int i7 = i5;
                while (i7 < i5 + i4) {
                    int iMontgomery = montgomery(iArr[r7] * j2, i2, j);
                    iArr[i7 + i4] = iArr[i7] - iMontgomery;
                    iArr[i7] = iArr[i7] + iMontgomery;
                    i7++;
                }
                i5 = i7 + i4;
                i3 = i6;
            }
        }
    }

    private static void numberTheoreticTransformIIIP(long[] jArr, long[] jArr2) {
        int i = 0;
        for (int i2 = 1024; i2 > 0; i2 >>= 1) {
            int i3 = 0;
            while (i3 < 2048) {
                int i4 = i + 1;
                int i5 = (int) jArr2[i];
                int i6 = i3;
                while (i6 < i3 + i2) {
                    int i7 = i6 + i2;
                    long jBarrett = barrett(montgomeryP(i5 * jArr[i7], Parameter.Q_III_P, Parameter.Q_INVERSE_III_P), Parameter.Q_III_P, 15, 34);
                    jArr[i7] = barrett(jArr[i6] + (2259451906L - jBarrett), Parameter.Q_III_P, 15, 34);
                    jArr[i6] = barrett(jArr[i6] + jBarrett, Parameter.Q_III_P, 15, 34);
                    i6++;
                }
                i3 = i6 + i2;
                i = i4;
            }
        }
    }

    private static void numberTheoreticTransformIP(long[] jArr, long[] jArr2) {
        int i = 0;
        for (int i2 = 512; i2 > 0; i2 >>= 1) {
            int i3 = 0;
            while (i3 < 1024) {
                int i4 = i + 1;
                long j = jArr2[i];
                int i5 = i3;
                while (i5 < i3 + i2) {
                    int i6 = i5 + i2;
                    long jMontgomeryP = montgomeryP(jArr[i6] * j, Parameter.Q_I_P, Parameter.Q_INVERSE_I_P);
                    jArr[i6] = jArr[i5] + (485978113 - jMontgomeryP);
                    jArr[i5] = jArr[i5] + jMontgomeryP;
                    i5++;
                }
                i3 = i5 + i2;
                i = i4;
            }
        }
    }

    public static void polynomialAddition(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = iArr2[i2] + iArr3[i2];
        }
    }

    public static void polynomialAddition(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            jArr[i + i5] = jArr2[i2 + i5] + jArr3[i3 + i5];
        }
    }

    public static void polynomialAdditionCorrection(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr2[i3] + iArr3[i3];
            iArr[i3] = i4;
            int i5 = i4 + ((i4 >> 31) & i2);
            iArr[i3] = i5;
            int i6 = i5 - i2;
            iArr[i3] = i6;
            iArr[i3] = i6 + ((i6 >> 31) & i2);
        }
    }

    public static void polynomialMultiplication(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2, long j, int[] iArr4) {
        int[] iArr5 = new int[i];
        for (int i3 = 0; i3 < i; i3++) {
            iArr5[i3] = iArr3[i3];
        }
        numberTheoreticTransform(iArr5, iArr4, i, i2, j);
        componentWisePolynomialMultiplication(iArr, iArr2, iArr5, i, i2, j);
        if (i2 == 4205569) {
            inverseNumberTheoreticTransformI(iArr, PolynomialHeuristic.ZETA_INVERSE_I);
        }
        if (i2 == 4206593) {
            inverseNumberTheoreticTransform(iArr, PolynomialHeuristic.ZETA_INVERSE_III_SIZE, 1024, Parameter.Q_III_SIZE, Parameter.Q_INVERSE_III_SIZE, Parameter.R_III_SIZE, 1021, 32);
        }
        if (i2 == 8404993) {
            inverseNumberTheoreticTransform(iArr, PolynomialHeuristic.ZETA_INVERSE_III_SPEED, 1024, Parameter.Q_III_SPEED, Parameter.Q_INVERSE_III_SPEED, Parameter.R_III_SPEED, 511, 32);
        }
    }

    public static void polynomialMultiplication(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4, int i5, long j) {
        componentWisePolynomialMultiplication(jArr, i, jArr2, i2, jArr3, i3, i4, i5, j);
        if (i5 == 485978113) {
            inverseNumberTheoreticTransformIP(jArr, i, PolynomialProvablySecure.ZETA_INVERSE_I_P, 0);
        }
        if (i5 == 1129725953) {
            inverseNumberTheoreticTransformIIIP(jArr, i, PolynomialProvablySecure.ZETA_INVERSE_III_P, 0);
        }
    }

    public static void polynomialNumberTheoreticTransform(long[] jArr, long[] jArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = jArr2[i2];
        }
        if (i == 1024) {
            numberTheoreticTransformIP(jArr, PolynomialProvablySecure.ZETA_I_P);
        }
        if (i == 2048) {
            numberTheoreticTransformIIIP(jArr, PolynomialProvablySecure.ZETA_III_P);
        }
    }

    public static void polynomialSubtraction(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4, int i5, int i6, int i7) {
        for (int i8 = 0; i8 < i4; i8++) {
            jArr[i + i8] = barrett(jArr2[i2 + i8] - jArr3[i3 + i8], i5, i6, i7);
        }
    }

    public static void polynomialSubtractionCorrection(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr2[i3] - iArr3[i3];
            iArr[i3] = i4;
            iArr[i3] = i4 + ((i4 >> 31) & i2);
        }
    }

    public static void polynomialSubtractionMontgomery(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2, long j, int i3) {
        for (int i4 = 0; i4 < i; i4++) {
            iArr[i4] = montgomery(i3 * (iArr2[i4] - iArr3[i4]), i2, j);
        }
    }

    public static void polynomialUniform(int[] iArr, byte[] bArr, int i, int i2, int i3, long j, int i4, int i5, int i6) {
        byte[] bArr2;
        int i7;
        byte[] bArr3;
        int i8;
        int i9 = (i4 + 7) / 8;
        int i10 = (1 << i4) - 1;
        int i11 = i5;
        int i12 = i11 * 168;
        byte[] bArr4 = new byte[i12];
        byte[] bArr5 = bArr4;
        HashUtils.customizableSecureHashAlgorithmKECCAK128Simple(bArr4, 0, i12, (short) 0, bArr, i, 32);
        short s = (short) 1;
        int i13 = 0;
        int i14 = 0;
        while (i13 < i2) {
            if (i14 > (i11 * 168) - (i9 * 4)) {
                HashUtils.customizableSecureHashAlgorithmKECCAK128Simple(bArr5, 0, 168, s, bArr, i, 32);
                bArr2 = bArr5;
                s = (short) (s + 1);
                i14 = 0;
                i11 = 1;
            } else {
                bArr2 = bArr5;
            }
            int iLoad32 = CommonFunction.load32(bArr2, i14) & i10;
            int i15 = i14 + i9;
            int iLoad322 = CommonFunction.load32(bArr2, i15) & i10;
            int i16 = i15 + i9;
            int iLoad323 = CommonFunction.load32(bArr2, i16) & i10;
            int i17 = i16 + i9;
            int iLoad324 = CommonFunction.load32(bArr2, i17) & i10;
            int i18 = i17 + i9;
            if (iLoad32 >= i3 || i13 >= i2) {
                i7 = i18;
                bArr3 = bArr2;
                i8 = i10;
            } else {
                i8 = i10;
                long j2 = iLoad32;
                i7 = i18;
                bArr3 = bArr2;
                iArr[i13] = montgomery(j2 * i6, i3, j);
                i13++;
            }
            if (iLoad322 < i3 && i13 < i2) {
                iArr[i13] = montgomery(iLoad322 * i6, i3, j);
                i13++;
            }
            if (iLoad323 < i3 && i13 < i2) {
                iArr[i13] = montgomery(iLoad323 * i6, i3, j);
                i13++;
            }
            if (iLoad324 >= i3 || i13 >= i2) {
                i14 = i7;
                bArr5 = bArr3;
                i10 = i8;
            } else {
                iArr[i13] = montgomery(iLoad324 * i6, i3, j);
                i13++;
                bArr5 = bArr3;
                i10 = i8;
                i14 = i7;
            }
        }
    }

    public static void polynomialUniform(long[] jArr, byte[] bArr, int i, int i2, int i3, int i4, long j, int i5, int i6, int i7) {
        byte[] bArr2;
        byte[] bArr3;
        int i8;
        int i9 = (i5 + 7) / 8;
        int i10 = (1 << i5) - 1;
        int i11 = i6;
        int i12 = i11 * 168;
        byte[] bArr4 = new byte[i12];
        short s = (short) 1;
        HashUtils.customizableSecureHashAlgorithmKECCAK128Simple(bArr4, 0, i12, (short) 0, bArr, i, 32);
        int i13 = 0;
        int i14 = 0;
        while (true) {
            int i15 = i2 * i3;
            if (i13 >= i15) {
                return;
            }
            if (i14 > (i11 * 168) - (i9 * 4)) {
                bArr2 = bArr4;
                HashUtils.customizableSecureHashAlgorithmKECCAK128Simple(bArr4, 0, 168, s, bArr, i, 32);
                s = (short) (s + 1);
                i14 = 0;
                i11 = 1;
            } else {
                bArr2 = bArr4;
            }
            int iLoad32 = CommonFunction.load32(bArr2, i14) & i10;
            int i16 = i14 + i9;
            int iLoad322 = CommonFunction.load32(bArr2, i16) & i10;
            int i17 = i16 + i9;
            int iLoad323 = CommonFunction.load32(bArr2, i17) & i10;
            int i18 = i17 + i9;
            int iLoad324 = CommonFunction.load32(bArr2, i18) & i10;
            i14 = i18 + i9;
            if (iLoad32 >= i4 || i13 >= i15) {
                bArr3 = bArr2;
                i8 = i10;
            } else {
                i8 = i10;
                bArr3 = bArr2;
                jArr[i13] = montgomeryP(iLoad32 * i7, i4, j);
                i13++;
            }
            if (iLoad322 < i4 && i13 < i15) {
                jArr[i13] = montgomeryP(iLoad322 * i7, i4, j);
                i13++;
            }
            if (iLoad323 < i4 && i13 < i15) {
                jArr[i13] = montgomeryP(iLoad323 * i7, i4, j);
                i13++;
            }
            if (iLoad324 < i4 && i13 < i15) {
                jArr[i13] = montgomeryP(iLoad324 * i7, i4, j);
                i13++;
            }
            i10 = i8;
            bArr4 = bArr3;
        }
    }

    public static void sparsePolynomialMultiplication16(int[] iArr, short[] sArr, int[] iArr2, short[] sArr2, int i, int i2) {
        Arrays.fill(iArr, 0);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr2[i3];
            for (int i5 = 0; i5 < i4; i5++) {
                iArr[i5] = iArr[i5] - (sArr2[i3] * sArr[(i + i5) - i4]);
            }
            for (int i6 = i4; i6 < i; i6++) {
                iArr[i6] = iArr[i6] + (sArr2[i3] * sArr[i6 - i4]);
            }
        }
    }

    public static void sparsePolynomialMultiplication32(int[] iArr, int[] iArr2, int[] iArr3, short[] sArr, int i, int i2) {
        Arrays.fill(iArr, 0);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr3[i3];
            for (int i5 = 0; i5 < i4; i5++) {
                iArr[i5] = iArr[i5] - (sArr[i3] * iArr2[(i + i5) - i4]);
            }
            for (int i6 = i4; i6 < i; i6++) {
                iArr[i6] = iArr[i6] + (sArr[i3] * iArr2[i6 - i4]);
            }
        }
    }

    public static void sparsePolynomialMultiplication32(long[] jArr, int i, int[] iArr, int i2, int[] iArr2, short[] sArr, int i3, int i4, int i5, int i6, int i7) {
        Arrays.fill(jArr, 0L);
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = iArr2[i8];
            for (int i10 = 0; i10 < i9; i10++) {
                int i11 = i + i10;
                jArr[i11] = jArr[i11] - (sArr[i8] * iArr[((i2 + i3) + i10) - i9]);
            }
            for (int i12 = i9; i12 < i3; i12++) {
                int i13 = i + i12;
                jArr[i13] = jArr[i13] + (sArr[i8] * iArr[(i2 + i12) - i9]);
            }
        }
        for (int i14 = 0; i14 < i3; i14++) {
            int i15 = i + i14;
            jArr[i15] = barrett(jArr[i15], i5, i6, i7);
        }
    }

    public static void sparsePolynomialMultiplication8(long[] jArr, int i, byte[] bArr, int i2, int[] iArr, short[] sArr, int i3, int i4) {
        Arrays.fill(jArr, 0L);
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = iArr[i5];
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = i + i7;
                jArr[i8] = jArr[i8] - (sArr[i5] * bArr[((i2 + i3) + i7) - i6]);
            }
            for (int i9 = i6; i9 < i3; i9++) {
                int i10 = i + i9;
                jArr[i10] = jArr[i10] + (sArr[i5] * bArr[(i2 + i9) - i6]);
            }
        }
    }
}
