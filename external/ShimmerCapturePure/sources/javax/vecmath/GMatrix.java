package javax.vecmath;

import java.io.Serializable;
import java.lang.reflect.Array;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class GMatrix implements Serializable, Cloneable {
    static final long serialVersionUID = 2777097312029690941L;
    private static final double EPS = 1.0E-10d;
    private static final boolean debug = false;
    int nCol;
    int nRow;
    double[][] values;

    public GMatrix(int i, int i2) {
        this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        this.nRow = i;
        this.nCol = i2;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                this.values[i3][i4] = 0.0d;
            }
        }
        int i5 = i < i2 ? i : i2;
        for (int i6 = 0; i6 < i5; i6++) {
            this.values[i6][i6] = 1.0d;
        }
    }

    public GMatrix(int i, int i2, double[] dArr) {
        this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        this.nRow = i;
        this.nCol = i2;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                this.values[i3][i4] = dArr[(i3 * i2) + i4];
            }
        }
    }

    public GMatrix(GMatrix gMatrix) {
        int i = gMatrix.nRow;
        this.nRow = i;
        int i2 = gMatrix.nCol;
        this.nCol = i2;
        this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i3 = 0; i3 < this.nRow; i3++) {
            for (int i4 = 0; i4 < this.nCol; i4++) {
                this.values[i3][i4] = gMatrix.values[i3][i4];
            }
        }
    }

    static double d_sign(double d, double d2) {
        if (d < 0.0d) {
            d = -d;
        }
        return d2 >= 0.0d ? d : -d;
    }

    static double max(double d, double d2) {
        return d > d2 ? d : d2;
    }

    static double min(double d, double d2) {
        return d < d2 ? d : d2;
    }

    private static void checkMatrix(GMatrix gMatrix) {
        for (int i = 0; i < gMatrix.nRow; i++) {
            for (int i2 = 0; i2 < gMatrix.nCol; i2++) {
                if (Math.abs(gMatrix.values[i][i2]) < 1.0E-10d) {
                    System.out.print(" 0.0     ");
                } else {
                    System.out.print(new StringBuffer(StringUtils.SPACE).append(gMatrix.values[i][i2]).toString());
                }
            }
            System.out.print(StringUtils.LF);
        }
    }

    static boolean luDecomposition(int i, double[] dArr, int[] iArr, int[] iArr2) {
        double[] dArr2 = new double[i];
        iArr2[0] = 1;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 == 0) {
                for (int i6 = 0; i6 < i; i6++) {
                    for (int i7 = 0; i7 < i6; i7++) {
                        int i8 = i * i7;
                        int i9 = i8 + i6;
                        double d = dArr[i9];
                        int i10 = i6;
                        int i11 = i7;
                        while (true) {
                            int i12 = i11 - 1;
                            if (i11 == 0) {
                                break;
                            }
                            d -= dArr[i8] * dArr[i10];
                            i8++;
                            i10 += i;
                            i11 = i12;
                        }
                        dArr[i9] = d;
                    }
                    int i13 = -1;
                    double d2 = 0.0d;
                    for (int i14 = i6; i14 < i; i14++) {
                        int i15 = i * i14;
                        int i16 = i15 + i6;
                        double d3 = dArr[i16];
                        int i17 = i6;
                        int i18 = i17;
                        while (true) {
                            int i19 = i17 - 1;
                            if (i17 == 0) {
                                break;
                            }
                            d3 -= dArr[i15] * dArr[i18];
                            i15++;
                            i18 += i;
                            i17 = i19;
                        }
                        dArr[i16] = d3;
                        double dAbs = dArr2[i14] * Math.abs(d3);
                        if (dAbs >= d2) {
                            i13 = i14;
                            d2 = dAbs;
                        }
                    }
                    if (i13 < 0) {
                        throw new RuntimeException(VecMathI18N.getString("GMatrix24"));
                    }
                    if (i6 != i13) {
                        int i20 = i * i13;
                        int i21 = i * i6;
                        int i22 = i;
                        while (true) {
                            int i23 = i22 - 1;
                            if (i22 == 0) {
                                break;
                            }
                            double d4 = dArr[i20];
                            dArr[i20] = dArr[i21];
                            dArr[i21] = d4;
                            i21++;
                            i20++;
                            i22 = i23;
                        }
                        dArr2[i13] = dArr2[i6];
                        iArr2[0] = -iArr2[0];
                    }
                    iArr[i6] = i13;
                    double d5 = dArr[(i * i6) + i6];
                    if (d5 == 0.0d) {
                        return false;
                    }
                    int i24 = i - 1;
                    if (i6 != i24) {
                        double d6 = 1.0d / d5;
                        int i25 = ((i6 + 1) * i) + i6;
                        int i26 = i24 - i6;
                        while (true) {
                            int i27 = i26 - 1;
                            if (i26 == 0) {
                                break;
                            }
                            dArr[i25] = dArr[i25] * d6;
                            i25 += i;
                            i26 = i27;
                        }
                    }
                }
                return true;
            }
            int i28 = i;
            double d7 = 0.0d;
            while (true) {
                int i29 = i28 - 1;
                if (i28 == 0) {
                    break;
                }
                int i30 = i3 + 1;
                double dAbs2 = Math.abs(dArr[i3]);
                if (dAbs2 > d7) {
                    i3 = i30;
                    i28 = i29;
                    d7 = dAbs2;
                } else {
                    i3 = i30;
                    i28 = i29;
                }
            }
            if (d7 == 0.0d) {
                return false;
            }
            dArr2[i4] = 1.0d / d7;
            i4++;
            i2 = i5;
        }
    }

    static void luBacksubstitution(int i, double[] dArr, int[] iArr, double[] dArr2) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = -1;
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = (iArr[i4] * i) + i2;
                double d = dArr2[i5];
                int i6 = i * i4;
                int i7 = i2 + i6;
                dArr2[i5] = dArr2[i7];
                if (i3 >= 0) {
                    for (int i8 = i3; i8 <= i4 - 1; i8++) {
                        d -= dArr[i6 + i8] * dArr2[(i * i8) + i2];
                    }
                } else if (d != 0.0d) {
                    i3 = i4;
                }
                dArr2[i7] = d;
            }
            for (int i9 = 0; i9 < i; i9++) {
                int i10 = (i - 1) - i9;
                int i11 = i * i10;
                double d2 = 0.0d;
                for (int i12 = 1; i12 <= i9; i12++) {
                    d2 += dArr[(i11 + i) - i12] * dArr2[((i - i12) * i) + i2];
                }
                int i13 = i2 + i11;
                dArr2[i13] = (dArr2[i13] - d2) / dArr[i11 + i10];
            }
        }
    }

    static int computeSVD(GMatrix gMatrix, GMatrix gMatrix2, GMatrix gMatrix3, GMatrix gMatrix4) {
        int i;
        int i2;
        GMatrix gMatrix5;
        GMatrix gMatrix6;
        double[] dArr;
        int i3;
        int i4;
        double[] dArr2;
        int i5;
        GMatrix gMatrix7 = gMatrix2;
        GMatrix gMatrix8 = gMatrix4;
        GMatrix gMatrix9 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        GMatrix gMatrix10 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        GMatrix gMatrix11 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        GMatrix gMatrix12 = new GMatrix(gMatrix);
        int i6 = gMatrix12.nRow;
        int i7 = gMatrix12.nCol;
        if (i6 >= i7) {
            i = i7 - 1;
            i2 = i7;
        } else {
            i = i6;
            i2 = i;
        }
        if (i6 <= i7) {
            i6 = i7;
        }
        double[] dArr3 = new double[i6];
        double[] dArr4 = new double[i2];
        double[] dArr5 = new double[i];
        gMatrix2.setIdentity();
        gMatrix4.setIdentity();
        int i8 = gMatrix12.nRow;
        int i9 = gMatrix12.nCol;
        int i10 = 0;
        while (i10 < i2) {
            if (i8 > 1) {
                double d = 0.0d;
                int i11 = 0;
                while (i11 < i8) {
                    double[] dArr6 = dArr4;
                    double d2 = gMatrix12.values[i11 + i10][i10];
                    d += d2 * d2;
                    i11++;
                    dArr4 = dArr6;
                    i = i;
                    gMatrix10 = gMatrix10;
                    gMatrix11 = gMatrix11;
                }
                dArr = dArr4;
                i3 = i;
                double dSqrt = Math.sqrt(d);
                i4 = i2;
                dArr2 = dArr5;
                double d3 = gMatrix12.values[i10][i10];
                if (d3 == 0.0d) {
                    dArr3[0] = dSqrt;
                } else {
                    dArr3[0] = d3 + d_sign(dSqrt, d3);
                }
                int i12 = 1;
                while (i12 < i8) {
                    dArr3[i12] = gMatrix12.values[i10 + i12][i10];
                    i12++;
                    gMatrix10 = gMatrix10;
                    gMatrix11 = gMatrix11;
                }
                double d4 = 0.0d;
                int i13 = 0;
                while (i13 < i8) {
                    GMatrix gMatrix13 = gMatrix10;
                    double d5 = dArr3[i13];
                    d4 += d5 * d5;
                    i13++;
                    gMatrix10 = gMatrix13;
                    gMatrix11 = gMatrix11;
                }
                double d6 = 2.0d / d4;
                for (int i14 = i10; i14 < gMatrix12.nRow; i14++) {
                    int i15 = i10;
                    while (i15 < gMatrix12.nRow) {
                        gMatrix10.values[i14][i15] = (-d6) * dArr3[i14 - i10] * dArr3[i15 - i10];
                        i15++;
                        gMatrix10 = gMatrix10;
                        gMatrix11 = gMatrix11;
                    }
                }
                for (int i16 = i10; i16 < gMatrix12.nRow; i16++) {
                    double[] dArr7 = gMatrix10.values[i16];
                    dArr7[i16] = dArr7[i16] + 1.0d;
                }
                double d7 = 0.0d;
                for (int i17 = i10; i17 < gMatrix12.nRow; i17++) {
                    d7 += gMatrix10.values[i10][i17] * gMatrix12.values[i17][i10];
                }
                gMatrix12.values[i10][i10] = d7;
                for (int i18 = i10; i18 < gMatrix12.nRow; i18++) {
                    for (int i19 = i10 + 1; i19 < gMatrix12.nCol; i19++) {
                        gMatrix9.values[i18][i19] = 0.0d;
                        for (int i20 = i10; i20 < gMatrix12.nCol; i20++) {
                            double[] dArr8 = gMatrix9.values[i18];
                            dArr8[i19] = dArr8[i19] + (gMatrix10.values[i18][i20] * gMatrix12.values[i20][i19]);
                        }
                    }
                }
                for (int i21 = i10; i21 < gMatrix12.nRow; i21++) {
                    for (int i22 = i10 + 1; i22 < gMatrix12.nCol; i22++) {
                        gMatrix12.values[i21][i22] = gMatrix9.values[i21][i22];
                    }
                }
                for (int i23 = i10; i23 < gMatrix12.nRow; i23++) {
                    for (int i24 = 0; i24 < gMatrix12.nCol; i24++) {
                        gMatrix9.values[i23][i24] = 0.0d;
                        for (int i25 = i10; i25 < gMatrix12.nCol; i25++) {
                            double[] dArr9 = gMatrix9.values[i23];
                            dArr9[i24] = dArr9[i24] + (gMatrix10.values[i23][i25] * gMatrix7.values[i25][i24]);
                        }
                    }
                }
                for (int i26 = i10; i26 < gMatrix12.nRow; i26++) {
                    for (int i27 = 0; i27 < gMatrix12.nCol; i27++) {
                        gMatrix7.values[i26][i27] = gMatrix9.values[i26][i27];
                    }
                }
                i8--;
                gMatrix5 = gMatrix10;
                gMatrix6 = gMatrix11;
            } else {
                gMatrix5 = gMatrix10;
                gMatrix6 = gMatrix11;
                dArr = dArr4;
                i3 = i;
                i4 = i2;
                dArr2 = dArr5;
            }
            if (i9 > 2) {
                double d8 = 0.0d;
                int i28 = 1;
                while (i28 < i9) {
                    double d9 = gMatrix12.values[i10][i10 + i28];
                    d8 += d9 * d9;
                    i28++;
                    gMatrix8 = gMatrix4;
                }
                double dSqrt2 = Math.sqrt(d8);
                int i29 = i10 + 1;
                double d10 = gMatrix12.values[i10][i29];
                if (d10 == 0.0d) {
                    dArr3[0] = dSqrt2;
                } else {
                    dArr3[0] = d10 + d_sign(dSqrt2, d10);
                }
                int i30 = 1;
                while (true) {
                    i5 = i9 - 1;
                    if (i30 >= i5) {
                        break;
                    }
                    dArr3[i30] = gMatrix12.values[i10][i10 + i30 + 1];
                    i30++;
                    gMatrix8 = gMatrix4;
                }
                double d11 = 0.0d;
                int i31 = 0;
                while (i31 < i5) {
                    double d12 = dArr3[i31];
                    d11 += d12 * d12;
                    i31++;
                    gMatrix8 = gMatrix4;
                }
                double d13 = 2.0d / d11;
                int i32 = i29;
                while (i32 < i9) {
                    GMatrix gMatrix14 = gMatrix6;
                    int i33 = i29;
                    while (i33 < gMatrix12.nCol) {
                        gMatrix14.values[i32][i33] = (-d13) * dArr3[(i32 - i10) - 1] * dArr3[(i33 - i10) - 1];
                        i33++;
                        gMatrix8 = gMatrix4;
                    }
                    i32++;
                    gMatrix6 = gMatrix14;
                }
                for (int i34 = i29; i34 < gMatrix12.nCol; i34++) {
                    double[] dArr10 = gMatrix6.values[i34];
                    dArr10[i34] = dArr10[i34] + 1.0d;
                }
                double d14 = 0.0d;
                for (int i35 = i10; i35 < gMatrix12.nCol; i35++) {
                    d14 += gMatrix6.values[i35][i29] * gMatrix12.values[i10][i35];
                }
                gMatrix12.values[i10][i29] = d14;
                int i36 = i29;
                while (i36 < gMatrix12.nRow) {
                    GMatrix gMatrix15 = gMatrix6;
                    for (int i37 = i29; i37 < gMatrix12.nCol; i37++) {
                        gMatrix9.values[i36][i37] = 0.0d;
                        for (int i38 = i29; i38 < gMatrix12.nCol; i38++) {
                            double[] dArr11 = gMatrix9.values[i36];
                            dArr11[i37] = dArr11[i37] + (gMatrix15.values[i38][i37] * gMatrix12.values[i36][i38]);
                        }
                    }
                    i36++;
                    gMatrix6 = gMatrix15;
                }
                int i39 = i29;
                while (i39 < gMatrix12.nRow) {
                    GMatrix gMatrix16 = gMatrix6;
                    for (int i40 = i29; i40 < gMatrix12.nCol; i40++) {
                        gMatrix12.values[i39][i40] = gMatrix9.values[i39][i40];
                    }
                    i39++;
                    gMatrix6 = gMatrix16;
                }
                for (int i41 = 0; i41 < gMatrix12.nRow; i41++) {
                    for (int i42 = i29; i42 < gMatrix12.nCol; i42++) {
                        gMatrix9.values[i41][i42] = 0.0d;
                        for (int i43 = i29; i43 < gMatrix12.nCol; i43++) {
                            double[] dArr12 = gMatrix9.values[i41];
                            dArr12[i42] = dArr12[i42] + (gMatrix6.values[i43][i42] * gMatrix8.values[i41][i43]);
                        }
                    }
                }
                for (int i44 = 0; i44 < gMatrix12.nRow; i44++) {
                    for (int i45 = i29; i45 < gMatrix12.nCol; i45++) {
                        gMatrix8.values[i44][i45] = gMatrix9.values[i44][i45];
                    }
                }
                i9--;
            }
            i10++;
            gMatrix7 = gMatrix2;
            dArr4 = dArr;
            gMatrix8 = gMatrix4;
            gMatrix11 = gMatrix6;
            i = i3;
            i2 = i4;
            dArr5 = dArr2;
            gMatrix10 = gMatrix5;
        }
        for (int i46 = 0; i46 < i2; i46++) {
            dArr4[i46] = gMatrix12.values[i46][i46];
        }
        int i47 = 0;
        while (i47 < i) {
            int i48 = i47 + 1;
            dArr5[i47] = gMatrix12.values[i47][i48];
            i47 = i48;
        }
        if (gMatrix12.nRow == 2 && gMatrix12.nCol == 2) {
            double[] dArr13 = new double[1];
            double[] dArr14 = new double[1];
            double[] dArr15 = new double[1];
            double[] dArr16 = new double[1];
            compute_2X2(dArr4[0], dArr5[0], dArr4[1], dArr4, dArr15, dArr13, dArr16, dArr14, 0);
            update_u(0, gMatrix7, dArr13, dArr15);
            update_v(0, gMatrix8, dArr14, dArr16);
            return 2;
        }
        compute_qr(0, i - 1, dArr4, dArr5, gMatrix2, gMatrix4);
        return i2;
    }

    static void compute_qr(int i, int i2, double[] dArr, double[] dArr2, GMatrix gMatrix, GMatrix gMatrix2) {
        GMatrix gMatrix3;
        double[] dArr3;
        double[] dArr4;
        double[] dArr5;
        double[] dArr6;
        int i3;
        double d;
        int i4;
        double d2;
        GMatrix gMatrix4 = gMatrix;
        GMatrix gMatrix5 = gMatrix2;
        double[] dArr7 = new double[1];
        double[] dArr8 = new double[1];
        double[] dArr9 = new double[1];
        double[] dArr10 = new double[1];
        new GMatrix(gMatrix4.nCol, gMatrix5.nRow);
        int i5 = i2;
        double dAbs = 0.0d;
        double d3 = 0.0d;
        int i6 = 0;
        boolean z = false;
        while (i6 < 2 && !z) {
            int i7 = i;
            double d4 = d3;
            while (i7 <= i5) {
                int i8 = i6;
                int i9 = i5;
                if (i7 == i) {
                    int i10 = dArr2.length == dArr.length ? i9 : i9 + 1;
                    double dCompute_shift = compute_shift(dArr[i10 - 1], dArr2[i9], dArr[i10]);
                    i4 = i9;
                    dAbs = (Math.abs(dArr[i7]) - dCompute_shift) * (d_sign(1.0d, dArr[i7]) + (dCompute_shift / dArr[i7]));
                    d2 = dArr2[i7];
                } else {
                    i4 = i9;
                    d2 = d4;
                }
                double dCompute_rot = compute_rot(dAbs, d2, dArr10, dArr8);
                if (i7 != i) {
                    dArr2[i7 - 1] = dCompute_rot;
                }
                double d5 = dArr8[0];
                double d6 = dArr[i7];
                double d7 = dArr10[0];
                double d8 = dArr2[i7];
                double d9 = (d5 * d6) + (d7 * d8);
                dArr2[i7] = (d5 * d8) - (d7 * d6);
                double d10 = dArr10[0];
                int i11 = i7 + 1;
                double d11 = dArr[i11];
                d4 = d10 * d11;
                dArr[i11] = dArr8[0] * d11;
                gMatrix5 = gMatrix2;
                update_v(i7, gMatrix5, dArr8, dArr10);
                dArr[i7] = compute_rot(d9, d4, dArr9, dArr7);
                double d12 = dArr7[0];
                double d13 = dArr2[i7];
                double d14 = dArr9[0];
                double d15 = dArr[i11];
                double d16 = (d12 * d13) + (d14 * d15);
                dArr[i11] = (d12 * d15) - (d14 * d13);
                int i12 = i4;
                if (i7 < i12) {
                    double d17 = dArr9[0];
                    double d18 = dArr2[i11];
                    dArr2[i11] = dArr7[0] * d18;
                    d4 = d17 * d18;
                }
                update_u(i7, gMatrix, dArr7, dArr9);
                i7 = i11;
                i5 = i12;
                dAbs = d16;
                i6 = i8;
            }
            if (dArr.length == dArr2.length) {
                i3 = i6;
                compute_rot(dAbs, d4, dArr10, dArr8);
                double d19 = dArr8[0];
                double d20 = dArr[i7];
                double d21 = dArr10[0];
                double d22 = dArr2[i7];
                double d23 = (d19 * d20) + (d21 * d22);
                dArr2[i7] = (d19 * d22) - (d21 * d20);
                int i13 = i7 + 1;
                dArr[i13] = dArr8[0] * dArr[i13];
                update_v(i7, gMatrix5, dArr8, dArr10);
                d = d23;
                i5 = i5;
            } else {
                i3 = i6;
                d = dAbs;
            }
            while (i5 - i > 1 && Math.abs(dArr2[i5]) < 4.89E-15d) {
                i5--;
            }
            for (int i14 = i5 - 2; i14 > i; i14--) {
                if (Math.abs(dArr2[i14]) < 4.89E-15d) {
                    compute_qr(i14 + 1, i5, dArr, dArr2, gMatrix, gMatrix2);
                    i5 = i14 - 1;
                    while (i5 - i > 1 && Math.abs(dArr2[i5]) < 4.89E-15d) {
                        i5--;
                    }
                }
            }
            if (i5 - i <= 1 && Math.abs(dArr2[i + 1]) < 4.89E-15d) {
                z = true;
            }
            i6 = i3 + 1;
            gMatrix4 = gMatrix;
            d3 = d4;
            dAbs = d;
        }
        GMatrix gMatrix6 = gMatrix4;
        if (Math.abs(dArr2[1]) < 4.89E-15d) {
            double d24 = dArr[i];
            double d25 = dArr2[i];
            int i15 = i + 1;
            double d26 = dArr[i15];
            gMatrix3 = gMatrix6;
            dArr3 = dArr10;
            dArr4 = dArr8;
            compute_2X2(d24, d25, d26, dArr, dArr9, dArr7, dArr3, dArr8, 0);
            dArr2[i] = 0.0d;
            dArr2[i15] = 0.0d;
            dArr5 = dArr9;
            dArr6 = dArr7;
        } else {
            gMatrix3 = gMatrix6;
            dArr3 = dArr10;
            dArr4 = dArr8;
            dArr5 = dArr9;
            dArr6 = dArr7;
        }
        update_u(i, gMatrix3, dArr6, dArr5);
        update_v(i, gMatrix2, dArr4, dArr3);
    }

    private static void print_se(double[] dArr, double[] dArr2) {
        System.out.println(new StringBuffer("\ns =").append(dArr[0]).append(StringUtils.SPACE).append(dArr[1]).append(StringUtils.SPACE).append(dArr[2]).toString());
        System.out.println(new StringBuffer("e =").append(dArr2[0]).append(StringUtils.SPACE).append(dArr2[1]).toString());
    }

    private static void update_v(int i, GMatrix gMatrix, double[] dArr, double[] dArr2) {
        for (int i2 = 0; i2 < gMatrix.nRow; i2++) {
            double[] dArr3 = gMatrix.values[i2];
            double d = dArr3[i];
            int i3 = i + 1;
            dArr3[i] = (dArr[0] * d) + (dArr2[0] * dArr3[i3]);
            dArr3[i3] = ((-dArr2[0]) * d) + (dArr[0] * dArr3[i3]);
        }
    }

    private static void chase_up(double[] dArr, double[] dArr2, int i, GMatrix gMatrix) {
        double[] dArr3 = new double[1];
        double[] dArr4 = new double[1];
        GMatrix gMatrix2 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        GMatrix gMatrix3 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        double d = dArr2[i];
        double d2 = dArr[i];
        int i2 = i;
        while (i2 > 0) {
            double dCompute_rot = compute_rot(d, d2, dArr4, dArr3);
            int i3 = i2 - 1;
            double d3 = dArr4[0] * (-dArr2[i3]);
            double d4 = dArr[i3];
            dArr[i2] = dCompute_rot;
            dArr2[i3] = dArr2[i3] * dArr3[0];
            update_v_split(i2, i + 1, gMatrix, dArr3, dArr4, gMatrix2, gMatrix3);
            i2--;
            d = d3;
            d2 = d4;
        }
        dArr[i2 + 1] = compute_rot(d, d2, dArr4, dArr3);
        update_v_split(i2, i + 1, gMatrix, dArr3, dArr4, gMatrix2, gMatrix3);
    }

    private static void chase_across(double[] dArr, double[] dArr2, int i, GMatrix gMatrix) {
        double[] dArr3 = new double[1];
        double[] dArr4 = new double[1];
        GMatrix gMatrix2 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        GMatrix gMatrix3 = new GMatrix(gMatrix.nRow, gMatrix.nCol);
        double d = dArr2[i];
        int i2 = i;
        double d2 = dArr[i + 1];
        double d3 = d;
        while (i2 < gMatrix.nCol - 2) {
            double dCompute_rot = compute_rot(d2, d3, dArr4, dArr3);
            int i3 = i2 + 1;
            double d4 = dArr4[0] * (-dArr2[i3]);
            double d5 = dArr[i2 + 2];
            dArr[i3] = dCompute_rot;
            dArr2[i3] = dArr2[i3] * dArr3[0];
            update_u_split(i, i3, gMatrix, dArr3, dArr4, gMatrix2, gMatrix3);
            i2 = i3;
            d3 = d4;
            d2 = d5;
        }
        int i4 = i2 + 1;
        dArr[i4] = compute_rot(d2, d3, dArr4, dArr3);
        update_u_split(i, i4, gMatrix, dArr3, dArr4, gMatrix2, gMatrix3);
    }

    private static void update_v_split(int i, int i2, GMatrix gMatrix, double[] dArr, double[] dArr2, GMatrix gMatrix2, GMatrix gMatrix3) {
        for (int i3 = 0; i3 < gMatrix.nRow; i3++) {
            double[] dArr3 = gMatrix.values[i3];
            double d = dArr3[i];
            dArr3[i] = (dArr[0] * d) - (dArr2[0] * dArr3[i2]);
            dArr3[i2] = (dArr2[0] * d) + (dArr[0] * dArr3[i2]);
        }
        System.out.println(new StringBuffer("topr    =").append(i).toString());
        System.out.println(new StringBuffer("bottomr =").append(i2).toString());
        System.out.println(new StringBuffer("cosr =").append(dArr[0]).toString());
        System.out.println(new StringBuffer("sinr =").append(dArr2[0]).toString());
        System.out.println("\nm =");
        checkMatrix(gMatrix3);
        System.out.println("\nv =");
        checkMatrix(gMatrix2);
        gMatrix3.mul(gMatrix3, gMatrix2);
        System.out.println("\nt*m =");
        checkMatrix(gMatrix3);
    }

    private static void update_u_split(int i, int i2, GMatrix gMatrix, double[] dArr, double[] dArr2, GMatrix gMatrix2, GMatrix gMatrix3) {
        for (int i3 = 0; i3 < gMatrix.nCol; i3++) {
            double[][] dArr3 = gMatrix.values;
            double[] dArr4 = dArr3[i];
            double d = dArr4[i3];
            double d2 = dArr[0] * d;
            double d3 = dArr2[0];
            double[] dArr5 = dArr3[i2];
            dArr4[i3] = d2 - (d3 * dArr5[i3]);
            dArr5[i3] = (dArr2[0] * d) + (dArr[0] * dArr5[i3]);
        }
        System.out.println("\nm=");
        checkMatrix(gMatrix3);
        System.out.println("\nu=");
        checkMatrix(gMatrix2);
        gMatrix3.mul(gMatrix2, gMatrix3);
        System.out.println("\nt*m=");
        checkMatrix(gMatrix3);
    }

    private static void update_u(int i, GMatrix gMatrix, double[] dArr, double[] dArr2) {
        for (int i2 = 0; i2 < gMatrix.nCol; i2++) {
            double[][] dArr3 = gMatrix.values;
            double[] dArr4 = dArr3[i];
            double d = dArr4[i2];
            double d2 = dArr[0] * d;
            double d3 = dArr2[0];
            double[] dArr5 = dArr3[i + 1];
            dArr4[i2] = d2 + (d3 * dArr5[i2]);
            dArr5[i2] = ((-dArr2[0]) * d) + (dArr[0] * dArr5[i2]);
        }
    }

    private static void print_m(GMatrix gMatrix, GMatrix gMatrix2, GMatrix gMatrix3) {
        GMatrix gMatrix4 = new GMatrix(gMatrix.nCol, gMatrix.nRow);
        gMatrix4.mul(gMatrix2, gMatrix4);
        gMatrix4.mul(gMatrix4, gMatrix3);
        System.out.println(new StringBuffer("\n m = \n").append(toString(gMatrix4)).toString());
    }

    private static String toString(GMatrix gMatrix) {
        StringBuffer stringBuffer = new StringBuffer(gMatrix.nRow * gMatrix.nCol * 8);
        for (int i = 0; i < gMatrix.nRow; i++) {
            for (int i2 = 0; i2 < gMatrix.nCol; i2++) {
                if (Math.abs(gMatrix.values[i][i2]) < 1.0E-9d) {
                    stringBuffer.append("0.0000 ");
                } else {
                    stringBuffer.append(gMatrix.values[i][i2]).append(StringUtils.SPACE);
                }
            }
            stringBuffer.append(StringUtils.LF);
        }
        return stringBuffer.toString();
    }

    private static void print_svd(double[] dArr, double[] dArr2, GMatrix gMatrix, GMatrix gMatrix2) {
        GMatrix gMatrix3 = new GMatrix(gMatrix.nCol, gMatrix2.nRow);
        System.out.println(" \ns = ");
        int i = 0;
        for (double d : dArr) {
            System.out.println(new StringBuffer(StringUtils.SPACE).append(d).toString());
        }
        System.out.println(" \ne = ");
        for (double d2 : dArr2) {
            System.out.println(new StringBuffer(StringUtils.SPACE).append(d2).toString());
        }
        System.out.println(new StringBuffer(" \nu  = \n").append(gMatrix.toString()).toString());
        System.out.println(new StringBuffer(" \nv  = \n").append(gMatrix2.toString()).toString());
        gMatrix3.setIdentity();
        for (int i2 = 0; i2 < dArr.length; i2++) {
            gMatrix3.values[i2][i2] = dArr[i2];
        }
        while (i < dArr2.length) {
            int i3 = i + 1;
            gMatrix3.values[i][i3] = dArr2[i];
            i = i3;
        }
        System.out.println(new StringBuffer(" \nm  = \n").append(gMatrix3.toString()).toString());
        gMatrix3.mulTransposeLeft(gMatrix, gMatrix3);
        gMatrix3.mulTransposeRight(gMatrix3, gMatrix2);
        System.out.println(new StringBuffer(" \n u.transpose*m*v.transpose  = \n").append(gMatrix3.toString()).toString());
    }

    static double compute_shift(double d, double d2, double d3) {
        double dAbs = Math.abs(d);
        double dAbs2 = Math.abs(d2);
        double dAbs3 = Math.abs(d3);
        double dMin = min(dAbs, dAbs3);
        double dMax = max(dAbs, dAbs3);
        if (dMin == 0.0d) {
            if (dMax == 0.0d) {
                return 0.0d;
            }
            min(dMax, dAbs2);
            max(dMax, dAbs2);
            return 0.0d;
        }
        if (dAbs2 < dMax) {
            double d4 = (dMin / dMax) + 1.0d;
            double d5 = (dMax - dMin) / dMax;
            double d6 = dAbs2 / dMax;
            double d7 = d6 * d6;
            return dMin * (2.0d / (Math.sqrt((d4 * d4) + d7) + Math.sqrt((d5 * d5) + d7)));
        }
        double d8 = dMax / dAbs2;
        if (d8 == 0.0d) {
            return (dMin * dMax) / dAbs2;
        }
        double d9 = ((dMin / dMax) + 1.0d) * d8;
        double d10 = ((dMax - dMin) / dMax) * d8;
        double dSqrt = dMin * (1.0d / (Math.sqrt((d9 * d9) + 1.0d) + Math.sqrt((d10 * d10) + 1.0d))) * d8;
        return dSqrt + dSqrt;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int compute_2X2(double r51, double r53, double r55, double[] r57, double[] r58, double[] r59, double[] r60, double[] r61, int r62) {
        /*
            Method dump skipped, instructions count: 497
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.vecmath.GMatrix.compute_2X2(double, double, double, double[], double[], double[], double[], double[], int):int");
    }

    static double compute_rot(double d, double d2, double[] dArr, double[] dArr2) {
        double d3;
        double d4;
        double d5;
        double d6 = 1.0d;
        double d7 = 0.0d;
        if (d2 != 0.0d) {
            if (d == 0.0d) {
                d5 = d2;
            } else {
                double dMax = max(Math.abs(d), Math.abs(d2));
                double d8 = 2.002083095183101E-146d;
                if (dMax >= 4.994797680505588E145d) {
                    double d9 = d;
                    double d10 = d2;
                    int i = 0;
                    while (dMax >= 4.994797680505588E145d) {
                        d9 *= 2.002083095183101E-146d;
                        d10 *= 2.002083095183101E-146d;
                        dMax = max(Math.abs(d9), Math.abs(d10));
                        i++;
                    }
                    double dSqrt = Math.sqrt((d9 * d9) + (d10 * d10));
                    double d11 = d9 / dSqrt;
                    double d12 = d10 / dSqrt;
                    for (int i2 = 1; i2 <= i; i2++) {
                        dSqrt *= 4.994797680505588E145d;
                    }
                    d5 = dSqrt;
                    d4 = d11;
                    d3 = d12;
                } else if (dMax <= 2.002083095183101E-146d) {
                    double d13 = d;
                    double d14 = d2;
                    int i3 = 0;
                    while (dMax <= d8) {
                        i3++;
                        d13 *= 4.994797680505588E145d;
                        d14 *= 4.994797680505588E145d;
                        dMax = max(Math.abs(d13), Math.abs(d14));
                        d8 = 2.002083095183101E-146d;
                    }
                    double dSqrt2 = Math.sqrt((d13 * d13) + (d14 * d14));
                    double d15 = d13 / dSqrt2;
                    double d16 = d14 / dSqrt2;
                    for (int i4 = 1; i4 <= i3; i4++) {
                        dSqrt2 *= d8;
                    }
                    d5 = dSqrt2;
                    d4 = d15;
                    d3 = d16;
                } else {
                    double dSqrt3 = Math.sqrt((d * d) + (d2 * d2));
                    d3 = d2 / dSqrt3;
                    d4 = d / dSqrt3;
                    d5 = dSqrt3;
                }
                if (Math.abs(d) <= Math.abs(d2) || d4 >= 0.0d) {
                    d7 = d4;
                    d6 = d3;
                } else {
                    d6 = -d4;
                    d7 = -d3;
                    d5 = -d5;
                }
            }
            dArr[0] = d6;
            dArr2[0] = d7;
            return d5;
        }
        d5 = d;
        double d17 = d6;
        d6 = d7;
        d7 = d17;
        dArr[0] = d6;
        dArr2[0] = d7;
        return d5;
    }

    public final int getNumCol() {
        return this.nCol;
    }

    public final int getNumRow() {
        return this.nRow;
    }

    public final void mul(GMatrix gMatrix) {
        int i = this.nCol;
        if (i != gMatrix.nRow || i != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix0"));
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.nRow, i);
        for (int i2 = 0; i2 < this.nRow; i2++) {
            for (int i3 = 0; i3 < this.nCol; i3++) {
                dArr[i2][i3] = 0.0d;
                for (int i4 = 0; i4 < this.nCol; i4++) {
                    double[] dArr2 = dArr[i2];
                    dArr2[i3] = dArr2[i3] + (this.values[i2][i4] * gMatrix.values[i4][i3]);
                }
            }
        }
        this.values = dArr;
    }

    public final void mul(GMatrix gMatrix, GMatrix gMatrix2) {
        int i;
        int i2;
        if (gMatrix.nCol != gMatrix2.nRow || (i = this.nRow) != gMatrix.nRow || (i2 = this.nCol) != gMatrix2.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix1"));
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i3 = 0; i3 < gMatrix.nRow; i3++) {
            for (int i4 = 0; i4 < gMatrix2.nCol; i4++) {
                dArr[i3][i4] = 0.0d;
                for (int i5 = 0; i5 < gMatrix.nCol; i5++) {
                    double[] dArr2 = dArr[i3];
                    dArr2[i4] = dArr2[i4] + (gMatrix.values[i3][i5] * gMatrix2.values[i5][i4]);
                }
            }
        }
        this.values = dArr;
    }

    public final void mul(GVector gVector, GVector gVector2) {
        if (this.nRow < gVector.getSize()) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix2"));
        }
        if (this.nCol < gVector2.getSize()) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix3"));
        }
        for (int i = 0; i < gVector.getSize(); i++) {
            for (int i2 = 0; i2 < gVector2.getSize(); i2++) {
                this.values[i][i2] = gVector.values[i] * gVector2.values[i2];
            }
        }
    }

    public final void add(GMatrix gMatrix) {
        if (this.nRow != gMatrix.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix4"));
        }
        if (this.nCol != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix5"));
        }
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                double[] dArr = this.values[i];
                dArr[i2] = dArr[i2] + gMatrix.values[i][i2];
            }
        }
    }

    public final void add(GMatrix gMatrix, GMatrix gMatrix2) {
        int i = gMatrix2.nRow;
        int i2 = gMatrix.nRow;
        if (i != i2) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix6"));
        }
        int i3 = gMatrix2.nCol;
        int i4 = gMatrix.nCol;
        if (i3 != i4) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix7"));
        }
        if (this.nCol != i4 || this.nRow != i2) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix8"));
        }
        for (int i5 = 0; i5 < this.nRow; i5++) {
            for (int i6 = 0; i6 < this.nCol; i6++) {
                this.values[i5][i6] = gMatrix.values[i5][i6] + gMatrix2.values[i5][i6];
            }
        }
    }

    public final void sub(GMatrix gMatrix) {
        if (this.nRow != gMatrix.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix9"));
        }
        if (this.nCol != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix28"));
        }
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                double[] dArr = this.values[i];
                dArr[i2] = dArr[i2] - gMatrix.values[i][i2];
            }
        }
    }

    public final void sub(GMatrix gMatrix, GMatrix gMatrix2) {
        int i = gMatrix2.nRow;
        int i2 = gMatrix.nRow;
        if (i != i2) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix10"));
        }
        int i3 = gMatrix2.nCol;
        int i4 = gMatrix.nCol;
        if (i3 != i4) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix11"));
        }
        if (this.nRow != i2 || this.nCol != i4) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix12"));
        }
        for (int i5 = 0; i5 < this.nRow; i5++) {
            for (int i6 = 0; i6 < this.nCol; i6++) {
                this.values[i5][i6] = gMatrix.values[i5][i6] - gMatrix2.values[i5][i6];
            }
        }
    }

    public final void negate() {
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                double[] dArr = this.values[i];
                dArr[i2] = -dArr[i2];
            }
        }
    }

    public final void negate(GMatrix gMatrix) {
        if (this.nRow != gMatrix.nRow || this.nCol != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix13"));
        }
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                this.values[i][i2] = -gMatrix.values[i][i2];
            }
        }
    }

    public final void setIdentity() {
        int i;
        int i2 = 0;
        while (true) {
            i = this.nRow;
            if (i2 >= i) {
                break;
            }
            for (int i3 = 0; i3 < this.nCol; i3++) {
                this.values[i2][i3] = 0.0d;
            }
            i2++;
        }
        int i4 = this.nCol;
        if (i >= i4) {
            i = i4;
        }
        for (int i5 = 0; i5 < i; i5++) {
            this.values[i5][i5] = 1.0d;
        }
    }

    public final void setZero() {
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                this.values[i][i2] = 0.0d;
            }
        }
    }

    public final void identityMinus() {
        int i;
        int i2 = 0;
        while (true) {
            i = this.nRow;
            if (i2 >= i) {
                break;
            }
            for (int i3 = 0; i3 < this.nCol; i3++) {
                double[] dArr = this.values[i2];
                dArr[i3] = -dArr[i3];
            }
            i2++;
        }
        int i4 = this.nCol;
        if (i >= i4) {
            i = i4;
        }
        for (int i5 = 0; i5 < i; i5++) {
            double[] dArr2 = this.values[i5];
            dArr2[i5] = dArr2[i5] + 1.0d;
        }
    }

    public final void invert() {
        invertGeneral(this);
    }

    public final void invert(GMatrix gMatrix) {
        invertGeneral(gMatrix);
    }

    public final void copySubMatrix(int i, int i2, int i3, int i4, int i5, int i6, GMatrix gMatrix) {
        if (this != gMatrix) {
            for (int i7 = 0; i7 < i3; i7++) {
                for (int i8 = 0; i8 < i4; i8++) {
                    gMatrix.values[i5 + i7][i6 + i8] = this.values[i + i7][i2 + i8];
                }
            }
            return;
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i3, i4);
        for (int i9 = 0; i9 < i3; i9++) {
            for (int i10 = 0; i10 < i4; i10++) {
                dArr[i9][i10] = this.values[i + i9][i2 + i10];
            }
        }
        for (int i11 = 0; i11 < i3; i11++) {
            for (int i12 = 0; i12 < i4; i12++) {
                gMatrix.values[i5 + i11][i6 + i12] = dArr[i11][i12];
            }
        }
    }

    public final void setSize(int i, int i2) {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        int i3 = this.nRow;
        if (i3 >= i) {
            i3 = i;
        }
        int i4 = this.nCol;
        if (i4 >= i2) {
            i4 = i2;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                dArr[i5][i6] = this.values[i5][i6];
            }
        }
        this.nRow = i;
        this.nCol = i2;
        this.values = dArr;
    }

    public final void set(double[] dArr) {
        for (int i = 0; i < this.nRow; i++) {
            int i2 = 0;
            while (true) {
                int i3 = this.nCol;
                if (i2 >= i3) {
                    break;
                }
                this.values[i][i2] = dArr[(i3 * i) + i2];
                i2++;
            }
        }
    }

    public final void set(Matrix3f matrix3f) {
        if (this.nCol < 3 || this.nRow < 3) {
            this.nCol = 3;
            this.nRow = 3;
            this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 3, 3);
        }
        this.values[0][0] = matrix3f.m00;
        this.values[0][1] = matrix3f.m01;
        this.values[0][2] = matrix3f.m02;
        this.values[1][0] = matrix3f.m10;
        this.values[1][1] = matrix3f.m11;
        this.values[1][2] = matrix3f.m12;
        this.values[2][0] = matrix3f.m20;
        this.values[2][1] = matrix3f.m21;
        this.values[2][2] = matrix3f.m22;
        for (int i = 3; i < this.nRow; i++) {
            for (int i2 = 3; i2 < this.nCol; i2++) {
                this.values[i][i2] = 0.0d;
            }
        }
    }

    public final void set(Matrix3d matrix3d) {
        if (this.nRow < 3 || this.nCol < 3) {
            this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 3, 3);
            this.nRow = 3;
            this.nCol = 3;
        }
        this.values[0][0] = matrix3d.m00;
        this.values[0][1] = matrix3d.m01;
        this.values[0][2] = matrix3d.m02;
        this.values[1][0] = matrix3d.m10;
        this.values[1][1] = matrix3d.m11;
        this.values[1][2] = matrix3d.m12;
        this.values[2][0] = matrix3d.m20;
        this.values[2][1] = matrix3d.m21;
        this.values[2][2] = matrix3d.m22;
        for (int i = 3; i < this.nRow; i++) {
            for (int i2 = 3; i2 < this.nCol; i2++) {
                this.values[i][i2] = 0.0d;
            }
        }
    }

    public final void set(Matrix4f matrix4f) {
        if (this.nRow < 4 || this.nCol < 4) {
            this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
            this.nRow = 4;
            this.nCol = 4;
        }
        this.values[0][0] = matrix4f.m00;
        this.values[0][1] = matrix4f.m01;
        this.values[0][2] = matrix4f.m02;
        this.values[0][3] = matrix4f.m03;
        this.values[1][0] = matrix4f.m10;
        this.values[1][1] = matrix4f.m11;
        this.values[1][2] = matrix4f.m12;
        this.values[1][3] = matrix4f.m13;
        this.values[2][0] = matrix4f.m20;
        this.values[2][1] = matrix4f.m21;
        this.values[2][2] = matrix4f.m22;
        this.values[2][3] = matrix4f.m23;
        this.values[3][0] = matrix4f.m30;
        this.values[3][1] = matrix4f.m31;
        this.values[3][2] = matrix4f.m32;
        this.values[3][3] = matrix4f.m33;
        for (int i = 4; i < this.nRow; i++) {
            for (int i2 = 4; i2 < this.nCol; i2++) {
                this.values[i][i2] = 0.0d;
            }
        }
    }

    public final void set(Matrix4d matrix4d) {
        if (this.nRow < 4 || this.nCol < 4) {
            this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
            this.nRow = 4;
            this.nCol = 4;
        }
        this.values[0][0] = matrix4d.m00;
        this.values[0][1] = matrix4d.m01;
        this.values[0][2] = matrix4d.m02;
        this.values[0][3] = matrix4d.m03;
        this.values[1][0] = matrix4d.m10;
        this.values[1][1] = matrix4d.m11;
        this.values[1][2] = matrix4d.m12;
        this.values[1][3] = matrix4d.m13;
        this.values[2][0] = matrix4d.m20;
        this.values[2][1] = matrix4d.m21;
        this.values[2][2] = matrix4d.m22;
        this.values[2][3] = matrix4d.m23;
        this.values[3][0] = matrix4d.m30;
        this.values[3][1] = matrix4d.m31;
        this.values[3][2] = matrix4d.m32;
        this.values[3][3] = matrix4d.m33;
        for (int i = 4; i < this.nRow; i++) {
            for (int i2 = 4; i2 < this.nCol; i2++) {
                this.values[i][i2] = 0.0d;
            }
        }
    }

    public final void set(GMatrix gMatrix) {
        int i = this.nRow;
        int i2 = gMatrix.nRow;
        if (i < i2 || this.nCol < gMatrix.nCol) {
            this.nRow = i2;
            int i3 = gMatrix.nCol;
            this.nCol = i3;
            this.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i2, i3);
        }
        for (int i4 = 0; i4 < Math.min(this.nRow, gMatrix.nRow); i4++) {
            for (int i5 = 0; i5 < Math.min(this.nCol, gMatrix.nCol); i5++) {
                this.values[i4][i5] = gMatrix.values[i4][i5];
            }
        }
        for (int i6 = gMatrix.nRow; i6 < this.nRow; i6++) {
            for (int i7 = gMatrix.nCol; i7 < this.nCol; i7++) {
                this.values[i6][i7] = 0.0d;
            }
        }
    }

    public final double getElement(int i, int i2) {
        return this.values[i][i2];
    }

    public final void setElement(int i, int i2, double d) {
        this.values[i][i2] = d;
    }

    public final void getRow(int i, double[] dArr) {
        for (int i2 = 0; i2 < this.nCol; i2++) {
            dArr[i2] = this.values[i][i2];
        }
    }

    public final void getRow(int i, GVector gVector) {
        int size = gVector.getSize();
        int i2 = this.nCol;
        if (size < i2) {
            gVector.setSize(i2);
        }
        for (int i3 = 0; i3 < this.nCol; i3++) {
            gVector.values[i3] = this.values[i][i3];
        }
    }

    public final void getColumn(int i, double[] dArr) {
        for (int i2 = 0; i2 < this.nRow; i2++) {
            dArr[i2] = this.values[i2][i];
        }
    }

    public final void getColumn(int i, GVector gVector) {
        int size = gVector.getSize();
        int i2 = this.nRow;
        if (size < i2) {
            gVector.setSize(i2);
        }
        for (int i3 = 0; i3 < this.nRow; i3++) {
            gVector.values[i3] = this.values[i3][i];
        }
    }

    public final void get(Matrix3d matrix3d) {
        if (this.nRow < 3 || this.nCol < 3) {
            matrix3d.setZero();
            if (this.nCol > 0) {
                if (this.nRow > 0) {
                    matrix3d.m00 = this.values[0][0];
                    if (this.nRow > 1) {
                        matrix3d.m10 = this.values[1][0];
                        if (this.nRow > 2) {
                            matrix3d.m20 = this.values[2][0];
                        }
                    }
                }
                if (this.nCol > 1) {
                    if (this.nRow > 0) {
                        matrix3d.m01 = this.values[0][1];
                        if (this.nRow > 1) {
                            matrix3d.m11 = this.values[1][1];
                            if (this.nRow > 2) {
                                matrix3d.m21 = this.values[2][1];
                            }
                        }
                    }
                    if (this.nCol <= 2 || this.nRow <= 0) {
                        return;
                    }
                    matrix3d.m02 = this.values[0][2];
                    if (this.nRow > 1) {
                        matrix3d.m12 = this.values[1][2];
                        if (this.nRow > 2) {
                            matrix3d.m22 = this.values[2][2];
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        matrix3d.m00 = this.values[0][0];
        matrix3d.m01 = this.values[0][1];
        matrix3d.m02 = this.values[0][2];
        matrix3d.m10 = this.values[1][0];
        matrix3d.m11 = this.values[1][1];
        matrix3d.m12 = this.values[1][2];
        matrix3d.m20 = this.values[2][0];
        matrix3d.m21 = this.values[2][1];
        matrix3d.m22 = this.values[2][2];
    }

    public final void get(Matrix3f matrix3f) {
        if (this.nRow < 3 || this.nCol < 3) {
            matrix3f.setZero();
            if (this.nCol > 0) {
                if (this.nRow > 0) {
                    matrix3f.m00 = (float) this.values[0][0];
                    if (this.nRow > 1) {
                        matrix3f.m10 = (float) this.values[1][0];
                        if (this.nRow > 2) {
                            matrix3f.m20 = (float) this.values[2][0];
                        }
                    }
                }
                if (this.nCol > 1) {
                    if (this.nRow > 0) {
                        matrix3f.m01 = (float) this.values[0][1];
                        if (this.nRow > 1) {
                            matrix3f.m11 = (float) this.values[1][1];
                            if (this.nRow > 2) {
                                matrix3f.m21 = (float) this.values[2][1];
                            }
                        }
                    }
                    if (this.nCol <= 2 || this.nRow <= 0) {
                        return;
                    }
                    matrix3f.m02 = (float) this.values[0][2];
                    if (this.nRow > 1) {
                        matrix3f.m12 = (float) this.values[1][2];
                        if (this.nRow > 2) {
                            matrix3f.m22 = (float) this.values[2][2];
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        matrix3f.m00 = (float) this.values[0][0];
        matrix3f.m01 = (float) this.values[0][1];
        matrix3f.m02 = (float) this.values[0][2];
        matrix3f.m10 = (float) this.values[1][0];
        matrix3f.m11 = (float) this.values[1][1];
        matrix3f.m12 = (float) this.values[1][2];
        matrix3f.m20 = (float) this.values[2][0];
        matrix3f.m21 = (float) this.values[2][1];
        matrix3f.m22 = (float) this.values[2][2];
    }

    public final void get(Matrix4d matrix4d) {
        if (this.nRow < 4 || this.nCol < 4) {
            matrix4d.setZero();
            if (this.nCol > 0) {
                if (this.nRow > 0) {
                    matrix4d.m00 = this.values[0][0];
                    if (this.nRow > 1) {
                        matrix4d.m10 = this.values[1][0];
                        if (this.nRow > 2) {
                            matrix4d.m20 = this.values[2][0];
                            if (this.nRow > 3) {
                                matrix4d.m30 = this.values[3][0];
                            }
                        }
                    }
                }
                if (this.nCol > 1) {
                    if (this.nRow > 0) {
                        matrix4d.m01 = this.values[0][1];
                        if (this.nRow > 1) {
                            matrix4d.m11 = this.values[1][1];
                            if (this.nRow > 2) {
                                matrix4d.m21 = this.values[2][1];
                                if (this.nRow > 3) {
                                    matrix4d.m31 = this.values[3][1];
                                }
                            }
                        }
                    }
                    if (this.nCol > 2) {
                        if (this.nRow > 0) {
                            matrix4d.m02 = this.values[0][2];
                            if (this.nRow > 1) {
                                matrix4d.m12 = this.values[1][2];
                                if (this.nRow > 2) {
                                    matrix4d.m22 = this.values[2][2];
                                    if (this.nRow > 3) {
                                        matrix4d.m32 = this.values[3][2];
                                    }
                                }
                            }
                        }
                        if (this.nCol <= 3 || this.nRow <= 0) {
                            return;
                        }
                        matrix4d.m03 = this.values[0][3];
                        if (this.nRow > 1) {
                            matrix4d.m13 = this.values[1][3];
                            if (this.nRow > 2) {
                                matrix4d.m23 = this.values[2][3];
                                if (this.nRow > 3) {
                                    matrix4d.m33 = this.values[3][3];
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        matrix4d.m00 = this.values[0][0];
        matrix4d.m01 = this.values[0][1];
        matrix4d.m02 = this.values[0][2];
        matrix4d.m03 = this.values[0][3];
        matrix4d.m10 = this.values[1][0];
        matrix4d.m11 = this.values[1][1];
        matrix4d.m12 = this.values[1][2];
        matrix4d.m13 = this.values[1][3];
        matrix4d.m20 = this.values[2][0];
        matrix4d.m21 = this.values[2][1];
        matrix4d.m22 = this.values[2][2];
        matrix4d.m23 = this.values[2][3];
        matrix4d.m30 = this.values[3][0];
        matrix4d.m31 = this.values[3][1];
        matrix4d.m32 = this.values[3][2];
        matrix4d.m33 = this.values[3][3];
    }

    public final void get(Matrix4f matrix4f) {
        if (this.nRow < 4 || this.nCol < 4) {
            matrix4f.setZero();
            if (this.nCol > 0) {
                if (this.nRow > 0) {
                    matrix4f.m00 = (float) this.values[0][0];
                    if (this.nRow > 1) {
                        matrix4f.m10 = (float) this.values[1][0];
                        if (this.nRow > 2) {
                            matrix4f.m20 = (float) this.values[2][0];
                            if (this.nRow > 3) {
                                matrix4f.m30 = (float) this.values[3][0];
                            }
                        }
                    }
                }
                if (this.nCol > 1) {
                    if (this.nRow > 0) {
                        matrix4f.m01 = (float) this.values[0][1];
                        if (this.nRow > 1) {
                            matrix4f.m11 = (float) this.values[1][1];
                            if (this.nRow > 2) {
                                matrix4f.m21 = (float) this.values[2][1];
                                if (this.nRow > 3) {
                                    matrix4f.m31 = (float) this.values[3][1];
                                }
                            }
                        }
                    }
                    if (this.nCol > 2) {
                        if (this.nRow > 0) {
                            matrix4f.m02 = (float) this.values[0][2];
                            if (this.nRow > 1) {
                                matrix4f.m12 = (float) this.values[1][2];
                                if (this.nRow > 2) {
                                    matrix4f.m22 = (float) this.values[2][2];
                                    if (this.nRow > 3) {
                                        matrix4f.m32 = (float) this.values[3][2];
                                    }
                                }
                            }
                        }
                        if (this.nCol <= 3 || this.nRow <= 0) {
                            return;
                        }
                        matrix4f.m03 = (float) this.values[0][3];
                        if (this.nRow > 1) {
                            matrix4f.m13 = (float) this.values[1][3];
                            if (this.nRow > 2) {
                                matrix4f.m23 = (float) this.values[2][3];
                                if (this.nRow > 3) {
                                    matrix4f.m33 = (float) this.values[3][3];
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        matrix4f.m00 = (float) this.values[0][0];
        matrix4f.m01 = (float) this.values[0][1];
        matrix4f.m02 = (float) this.values[0][2];
        matrix4f.m03 = (float) this.values[0][3];
        matrix4f.m10 = (float) this.values[1][0];
        matrix4f.m11 = (float) this.values[1][1];
        matrix4f.m12 = (float) this.values[1][2];
        matrix4f.m13 = (float) this.values[1][3];
        matrix4f.m20 = (float) this.values[2][0];
        matrix4f.m21 = (float) this.values[2][1];
        matrix4f.m22 = (float) this.values[2][2];
        matrix4f.m23 = (float) this.values[2][3];
        matrix4f.m30 = (float) this.values[3][0];
        matrix4f.m31 = (float) this.values[3][1];
        matrix4f.m32 = (float) this.values[3][2];
        matrix4f.m33 = (float) this.values[3][3];
    }

    public final void get(GMatrix gMatrix) {
        int i = this.nCol;
        int i2 = gMatrix.nCol;
        if (i >= i2) {
            i = i2;
        }
        int i3 = this.nRow;
        int i4 = gMatrix.nRow;
        if (i3 >= i4) {
            i3 = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                gMatrix.values[i5][i6] = this.values[i5][i6];
            }
        }
        for (int i7 = i3; i7 < gMatrix.nRow; i7++) {
            for (int i8 = 0; i8 < gMatrix.nCol; i8++) {
                gMatrix.values[i7][i8] = 0.0d;
            }
        }
        for (int i9 = i; i9 < gMatrix.nCol; i9++) {
            for (int i10 = 0; i10 < i3; i10++) {
                gMatrix.values[i10][i9] = 0.0d;
            }
        }
    }

    public final void setRow(int i, double[] dArr) {
        for (int i2 = 0; i2 < this.nCol; i2++) {
            this.values[i][i2] = dArr[i2];
        }
    }

    public final void setRow(int i, GVector gVector) {
        for (int i2 = 0; i2 < this.nCol; i2++) {
            this.values[i][i2] = gVector.values[i2];
        }
    }

    public final void setColumn(int i, double[] dArr) {
        for (int i2 = 0; i2 < this.nRow; i2++) {
            this.values[i2][i] = dArr[i2];
        }
    }

    public final void setColumn(int i, GVector gVector) {
        for (int i2 = 0; i2 < this.nRow; i2++) {
            this.values[i2][i] = gVector.values[i2];
        }
    }

    public final void mulTransposeBoth(GMatrix gMatrix, GMatrix gMatrix2) {
        int i;
        int i2;
        if (gMatrix.nRow != gMatrix2.nCol || (i = this.nRow) != gMatrix.nCol || (i2 = this.nCol) != gMatrix2.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix14"));
        }
        if (gMatrix != this && gMatrix2 != this) {
            for (int i3 = 0; i3 < this.nRow; i3++) {
                for (int i4 = 0; i4 < this.nCol; i4++) {
                    this.values[i3][i4] = 0.0d;
                    for (int i5 = 0; i5 < gMatrix.nRow; i5++) {
                        double[] dArr = this.values[i3];
                        dArr[i4] = dArr[i4] + (gMatrix.values[i5][i3] * gMatrix2.values[i4][i5]);
                    }
                }
            }
            return;
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i2);
        for (int i6 = 0; i6 < this.nRow; i6++) {
            for (int i7 = 0; i7 < this.nCol; i7++) {
                dArr2[i6][i7] = 0.0d;
                for (int i8 = 0; i8 < gMatrix.nRow; i8++) {
                    double[] dArr3 = dArr2[i6];
                    dArr3[i7] = dArr3[i7] + (gMatrix.values[i8][i6] * gMatrix2.values[i7][i8]);
                }
            }
        }
        this.values = dArr2;
    }

    public final void mulTransposeRight(GMatrix gMatrix, GMatrix gMatrix2) {
        int i;
        int i2;
        if (gMatrix.nCol != gMatrix2.nCol || (i = this.nCol) != gMatrix2.nRow || (i2 = this.nRow) != gMatrix.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix15"));
        }
        if (gMatrix != this && gMatrix2 != this) {
            for (int i3 = 0; i3 < this.nRow; i3++) {
                for (int i4 = 0; i4 < this.nCol; i4++) {
                    this.values[i3][i4] = 0.0d;
                    for (int i5 = 0; i5 < gMatrix.nCol; i5++) {
                        double[] dArr = this.values[i3];
                        dArr[i4] = dArr[i4] + (gMatrix.values[i3][i5] * gMatrix2.values[i4][i5]);
                    }
                }
            }
            return;
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i2, i);
        for (int i6 = 0; i6 < this.nRow; i6++) {
            for (int i7 = 0; i7 < this.nCol; i7++) {
                dArr2[i6][i7] = 0.0d;
                for (int i8 = 0; i8 < gMatrix.nCol; i8++) {
                    double[] dArr3 = dArr2[i6];
                    dArr3[i7] = dArr3[i7] + (gMatrix.values[i6][i8] * gMatrix2.values[i7][i8]);
                }
            }
        }
        this.values = dArr2;
    }

    public final void mulTransposeLeft(GMatrix gMatrix, GMatrix gMatrix2) {
        int i;
        int i2;
        if (gMatrix.nRow != gMatrix2.nRow || (i = this.nCol) != gMatrix2.nCol || (i2 = this.nRow) != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix16"));
        }
        if (gMatrix != this && gMatrix2 != this) {
            for (int i3 = 0; i3 < this.nRow; i3++) {
                for (int i4 = 0; i4 < this.nCol; i4++) {
                    this.values[i3][i4] = 0.0d;
                    for (int i5 = 0; i5 < gMatrix.nRow; i5++) {
                        double[] dArr = this.values[i3];
                        dArr[i4] = dArr[i4] + (gMatrix.values[i5][i3] * gMatrix2.values[i5][i4]);
                    }
                }
            }
            return;
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i2, i);
        for (int i6 = 0; i6 < this.nRow; i6++) {
            for (int i7 = 0; i7 < this.nCol; i7++) {
                dArr2[i6][i7] = 0.0d;
                for (int i8 = 0; i8 < gMatrix.nRow; i8++) {
                    double[] dArr3 = dArr2[i6];
                    dArr3[i7] = dArr3[i7] + (gMatrix.values[i8][i6] * gMatrix2.values[i8][i7]);
                }
            }
        }
        this.values = dArr2;
    }

    public final void transpose() {
        int i = this.nRow;
        int i2 = this.nCol;
        if (i != i2) {
            this.nRow = i2;
            this.nCol = i;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i2, i);
            for (int i3 = 0; i3 < this.nRow; i3++) {
                for (int i4 = 0; i4 < this.nCol; i4++) {
                    dArr[i3][i4] = this.values[i4][i3];
                }
            }
            this.values = dArr;
            return;
        }
        for (int i5 = 0; i5 < this.nRow; i5++) {
            for (int i6 = 0; i6 < i5; i6++) {
                double[][] dArr2 = this.values;
                double[] dArr3 = dArr2[i5];
                double d = dArr3[i6];
                double[] dArr4 = dArr2[i6];
                dArr3[i6] = dArr4[i5];
                dArr4[i5] = d;
            }
        }
    }

    public final void transpose(GMatrix gMatrix) {
        if (this.nRow != gMatrix.nCol || this.nCol != gMatrix.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix17"));
        }
        if (gMatrix == this) {
            transpose();
            return;
        }
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                this.values[i][i2] = gMatrix.values[i2][i];
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(this.nRow * this.nCol * 8);
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                stringBuffer.append(this.values[i][i2]).append(StringUtils.SPACE);
            }
            stringBuffer.append(StringUtils.LF);
        }
        return stringBuffer.toString();
    }

    public int hashCode() {
        long jDoubleToLongBits = ((this.nRow + 31) * 31) + this.nCol;
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                jDoubleToLongBits = (jDoubleToLongBits * 31) + Double.doubleToLongBits(this.values[i][i2]);
            }
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >> 32));
    }

    public boolean equals(GMatrix gMatrix) {
        try {
            if (this.nRow == gMatrix.nRow && this.nCol == gMatrix.nCol) {
                for (int i = 0; i < this.nRow; i++) {
                    for (int i2 = 0; i2 < this.nCol; i2++) {
                        if (this.values[i][i2] != gMatrix.values[i][i2]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public boolean equals(Object obj) {
        try {
            GMatrix gMatrix = (GMatrix) obj;
            if (this.nRow == gMatrix.nRow && this.nCol == gMatrix.nCol) {
                for (int i = 0; i < this.nRow; i++) {
                    for (int i2 = 0; i2 < this.nCol; i2++) {
                        if (this.values[i][i2] != gMatrix.values[i][i2]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public boolean epsilonEquals(GMatrix gMatrix, float f) {
        return epsilonEquals(gMatrix, f);
    }

    public boolean epsilonEquals(GMatrix gMatrix, double d) {
        if (this.nRow != gMatrix.nRow || this.nCol != gMatrix.nCol) {
            return false;
        }
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                double d2 = this.values[i][i2] - gMatrix.values[i][i2];
                if (d2 < 0.0d) {
                    d2 = -d2;
                }
                if (d2 > d) {
                    return false;
                }
            }
        }
        return true;
    }

    public final double trace() {
        int i = this.nRow;
        int i2 = this.nCol;
        if (i >= i2) {
            i = i2;
        }
        double d = 0.0d;
        for (int i3 = 0; i3 < i; i3++) {
            d += this.values[i3][i3];
        }
        return d;
    }

    public final int SVD(GMatrix gMatrix, GMatrix gMatrix2, GMatrix gMatrix3) {
        int i = this.nCol;
        if (i != gMatrix3.nCol || i != gMatrix3.nRow) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix18"));
        }
        int i2 = this.nRow;
        if (i2 != gMatrix.nRow || i2 != gMatrix.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix25"));
        }
        if (i2 != gMatrix2.nRow || i != gMatrix2.nCol) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix26"));
        }
        if (i2 == 2 && i == 2 && this.values[1][0] == 0.0d) {
            gMatrix.setIdentity();
            gMatrix3.setIdentity();
            double[][] dArr = this.values;
            double[] dArr2 = dArr[0];
            if (dArr2[1] == 0.0d) {
                return 2;
            }
            double[] dArr3 = new double[1];
            double[] dArr4 = new double[1];
            double[] dArr5 = new double[1];
            double[] dArr6 = new double[1];
            double[] dArr7 = dArr[1];
            compute_2X2(dArr2[0], dArr2[1], dArr7[1], new double[]{dArr2[0], dArr7[1]}, dArr3, dArr5, dArr4, dArr6, 0);
            update_u(0, gMatrix, dArr5, dArr3);
            update_v(0, gMatrix3, dArr6, dArr4);
            return 2;
        }
        return computeSVD(this, gMatrix, gMatrix2, gMatrix3);
    }

    public final int LUD(GMatrix gMatrix, GVector gVector) {
        int i = gMatrix.nRow;
        int i2 = gMatrix.nCol;
        double[] dArr = new double[i * i2];
        int[] iArr = new int[1];
        int[] iArr2 = new int[i];
        int i3 = this.nRow;
        int i4 = this.nCol;
        if (i3 != i4) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix19"));
        }
        if (i3 != i) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
        }
        if (i4 != i2) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
        }
        if (i != gVector.getSize()) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix20"));
        }
        for (int i5 = 0; i5 < this.nRow; i5++) {
            int i6 = 0;
            while (true) {
                int i7 = this.nCol;
                if (i6 >= i7) {
                    break;
                }
                dArr[(i7 * i5) + i6] = this.values[i5][i6];
                i6++;
            }
        }
        if (!luDecomposition(gMatrix.nRow, dArr, iArr2, iArr)) {
            throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
        }
        for (int i8 = 0; i8 < this.nRow; i8++) {
            int i9 = 0;
            while (true) {
                int i10 = this.nCol;
                if (i9 >= i10) {
                    break;
                }
                gMatrix.values[i8][i9] = dArr[(i10 * i8) + i9];
                i9++;
            }
        }
        for (int i11 = 0; i11 < gMatrix.nRow; i11++) {
            gVector.values[i11] = iArr2[i11];
        }
        return iArr[0];
    }

    public final void setScale(double d) {
        int i = this.nRow;
        int i2 = this.nCol;
        if (i >= i2) {
            i = i2;
        }
        for (int i3 = 0; i3 < this.nRow; i3++) {
            for (int i4 = 0; i4 < this.nCol; i4++) {
                this.values[i3][i4] = 0.0d;
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            this.values[i5][i5] = d;
        }
    }

    final void invertGeneral(GMatrix gMatrix) {
        int i = gMatrix.nRow;
        int i2 = gMatrix.nCol;
        int i3 = i * i2;
        double[] dArr = new double[i3];
        double[] dArr2 = new double[i3];
        int[] iArr = new int[i];
        int[] iArr2 = new int[1];
        if (i != i2) {
            throw new MismatchedSizeException(VecMathI18N.getString("GMatrix22"));
        }
        for (int i4 = 0; i4 < this.nRow; i4++) {
            int i5 = 0;
            while (true) {
                int i6 = this.nCol;
                if (i5 >= i6) {
                    break;
                }
                dArr[(i6 * i4) + i5] = gMatrix.values[i4][i5];
                i5++;
            }
        }
        if (!luDecomposition(gMatrix.nRow, dArr, iArr, iArr2)) {
            throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
        }
        for (int i7 = 0; i7 < i3; i7++) {
            dArr2[i7] = 0.0d;
        }
        int i8 = 0;
        while (true) {
            int i9 = this.nCol;
            if (i8 >= i9) {
                break;
            }
            dArr2[(i9 * i8) + i8] = 1.0d;
            i8++;
        }
        luBacksubstitution(gMatrix.nRow, dArr, iArr, dArr2);
        for (int i10 = 0; i10 < this.nRow; i10++) {
            int i11 = 0;
            while (true) {
                int i12 = this.nCol;
                if (i11 >= i12) {
                    break;
                }
                this.values[i10][i11] = dArr2[(i12 * i10) + i11];
                i11++;
            }
        }
    }

    public Object clone() {
        try {
            GMatrix gMatrix = (GMatrix) super.clone();
            gMatrix.values = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.nRow, this.nCol);
            for (int i = 0; i < this.nRow; i++) {
                for (int i2 = 0; i2 < this.nCol; i2++) {
                    gMatrix.values[i][i2] = this.values[i][i2];
                }
            }
            return gMatrix;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
