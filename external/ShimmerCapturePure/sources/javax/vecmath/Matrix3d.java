package javax.vecmath;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class Matrix3d implements Serializable, Cloneable {
    static final long serialVersionUID = 6837536777072402710L;
    private static final double EPS = 1.110223024E-16d;
    private static final double ERR_EPS = 1.0E-8d;
    private static double xin;
    private static double xout;
    private static double yin;
    private static double yout;
    private static double zin;
    private static double zout;
    public double m00;
    public double m01;
    public double m02;
    public double m10;
    public double m11;
    public double m12;
    public double m20;
    public double m21;
    public double m22;

    public Matrix3d(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.m00 = d;
        this.m01 = d2;
        this.m02 = d3;
        this.m10 = d4;
        this.m11 = d5;
        this.m12 = d6;
        this.m20 = d7;
        this.m21 = d8;
        this.m22 = d9;
    }

    public Matrix3d(double[] dArr) {
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public Matrix3d(Matrix3d matrix3d) {
        this.m00 = matrix3d.m00;
        this.m01 = matrix3d.m01;
        this.m02 = matrix3d.m02;
        this.m10 = matrix3d.m10;
        this.m11 = matrix3d.m11;
        this.m12 = matrix3d.m12;
        this.m20 = matrix3d.m20;
        this.m21 = matrix3d.m21;
        this.m22 = matrix3d.m22;
    }

    public Matrix3d(Matrix3f matrix3f) {
        this.m00 = matrix3f.m00;
        this.m01 = matrix3f.m01;
        this.m02 = matrix3f.m02;
        this.m10 = matrix3f.m10;
        this.m11 = matrix3f.m11;
        this.m12 = matrix3f.m12;
        this.m20 = matrix3f.m20;
        this.m21 = matrix3f.m21;
        this.m22 = matrix3f.m22;
    }

    public Matrix3d() {
        this.m00 = 0.0d;
        this.m01 = 0.0d;
        this.m02 = 0.0d;
        this.m10 = 0.0d;
        this.m11 = 0.0d;
        this.m12 = 0.0d;
        this.m20 = 0.0d;
        this.m21 = 0.0d;
        this.m22 = 0.0d;
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

    static boolean luDecomposition(double[] dArr, int[] iArr) {
        double[] dArr2 = new double[3];
        int i = 3;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            if (i == 0) {
                for (int i5 = 0; i5 < 3; i5++) {
                    for (int i6 = 0; i6 < i5; i6++) {
                        int i7 = i6 * 3;
                        int i8 = i7 + i5;
                        double d = dArr[i8];
                        int i9 = i5;
                        int i10 = i6;
                        while (true) {
                            int i11 = i10 - 1;
                            if (i10 == 0) {
                                break;
                            }
                            d -= dArr[i7] * dArr[i9];
                            i7++;
                            i9 += 3;
                            i10 = i11;
                        }
                        dArr[i8] = d;
                    }
                    int i12 = -1;
                    double d2 = 0.0d;
                    for (int i13 = i5; i13 < 3; i13++) {
                        int i14 = i13 * 3;
                        int i15 = i14 + i5;
                        double d3 = dArr[i15];
                        int i16 = i5;
                        int i17 = i16;
                        while (true) {
                            int i18 = i16 - 1;
                            if (i16 == 0) {
                                break;
                            }
                            d3 -= dArr[i14] * dArr[i17];
                            i14++;
                            i17 += 3;
                            i16 = i18;
                        }
                        dArr[i15] = d3;
                        double dAbs = dArr2[i13] * Math.abs(d3);
                        if (dAbs >= d2) {
                            i12 = i13;
                            d2 = dAbs;
                        }
                    }
                    if (i12 < 0) {
                        throw new RuntimeException(VecMathI18N.getString("Matrix3d13"));
                    }
                    if (i5 != i12) {
                        int i19 = i12 * 3;
                        int i20 = i5 * 3;
                        int i21 = 3;
                        while (true) {
                            int i22 = i21 - 1;
                            if (i21 == 0) {
                                break;
                            }
                            double d4 = dArr[i19];
                            dArr[i19] = dArr[i20];
                            dArr[i20] = d4;
                            i20++;
                            i19++;
                            i21 = i22;
                        }
                        dArr2[i12] = dArr2[i5];
                    }
                    iArr[i5] = i12;
                    double d5 = dArr[(i5 * 3) + i5];
                    if (d5 == 0.0d) {
                        return false;
                    }
                    if (i5 != 2) {
                        double d6 = 1.0d / d5;
                        int i23 = ((i5 + 1) * 3) + i5;
                        int i24 = 2 - i5;
                        while (true) {
                            int i25 = i24 - 1;
                            if (i24 == 0) {
                                break;
                            }
                            dArr[i23] = dArr[i23] * d6;
                            i23 += 3;
                            i24 = i25;
                        }
                    }
                }
                return true;
            }
            double d7 = 0.0d;
            int i26 = 3;
            while (true) {
                int i27 = i26 - 1;
                if (i26 == 0) {
                    break;
                }
                int i28 = i2 + 1;
                double dAbs2 = Math.abs(dArr[i2]);
                if (dAbs2 > d7) {
                    i2 = i28;
                    i26 = i27;
                    d7 = dAbs2;
                } else {
                    i2 = i28;
                    i26 = i27;
                }
            }
            if (d7 == 0.0d) {
                return false;
            }
            dArr2[i3] = 1.0d / d7;
            i3++;
            i = i4;
        }
    }

    static void luBacksubstitution(double[] dArr, int[] iArr, double[] dArr2) {
        for (int i = 0; i < 3; i++) {
            int i2 = -1;
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = (iArr[i3] * 3) + i;
                double d = dArr2[i4];
                int i5 = i3 * 3;
                int i6 = i + i5;
                dArr2[i4] = dArr2[i6];
                if (i2 >= 0) {
                    for (int i7 = i2; i7 <= i3 - 1; i7++) {
                        d -= dArr[i5 + i7] * dArr2[(i7 * 3) + i];
                    }
                } else if (d != 0.0d) {
                    i2 = i3;
                }
                dArr2[i6] = d;
            }
            int i8 = i + 6;
            double d2 = dArr2[i8] / dArr[8];
            dArr2[i8] = d2;
            int i9 = i + 3;
            double d3 = (dArr2[i9] - (dArr[5] * d2)) / dArr[4];
            dArr2[i9] = d3;
            dArr2[i] = ((dArr2[i] - (dArr[1] * d3)) - (dArr[2] * dArr2[i8])) / dArr[0];
        }
    }

    static void compute_svd(double[] dArr, double[] dArr2, double[] dArr3) {
        double[] dArr4;
        double[] dArr5;
        double[] dArr6;
        char c;
        double[] dArr7;
        double[] dArr8;
        double[] dArr9;
        double[] dArr10 = new double[9];
        double[] dArr11 = new double[9];
        double[] dArr12 = new double[9];
        double[] dArr13 = new double[9];
        double[] dArr14 = new double[9];
        double[] dArr15 = new double[3];
        double[] dArr16 = new double[3];
        char c2 = 0;
        int i = 0;
        for (int i2 = 9; i < i2; i2 = 9) {
            dArr14[i] = dArr[i];
            i++;
            dArr15 = dArr15;
            c2 = 0;
            dArr10 = dArr10;
        }
        double d = dArr[3];
        if (d * d < EPS) {
            dArr10[c2] = 1.0d;
            dArr10[1] = 0.0d;
            dArr10[2] = 0.0d;
            dArr10[3] = 0.0d;
            dArr10[4] = 1.0d;
            dArr10[5] = 0.0d;
            dArr10[6] = 0.0d;
            dArr10[7] = 0.0d;
            dArr10[8] = 1.0d;
        } else {
            double d2 = dArr[c2];
            if (d2 * d2 < EPS) {
                dArr12[c2] = d2;
                dArr12[1] = dArr[1];
                dArr12[2] = dArr[2];
                dArr[c2] = d;
                dArr[1] = dArr[4];
                dArr[2] = dArr[5];
                dArr[3] = -dArr12[c2];
                dArr[4] = -dArr12[1];
                dArr[5] = -dArr12[2];
                dArr10[c2] = 0.0d;
                dArr10[1] = 1.0d;
                dArr10[2] = 0.0d;
                dArr10[3] = -1.0d;
                dArr10[4] = 0.0d;
                dArr10[5] = 0.0d;
                dArr10[6] = 0.0d;
                dArr10[7] = 0.0d;
                dArr10[8] = 1.0d;
            } else {
                double dSqrt = 1.0d / Math.sqrt((d2 * d2) + (d * d));
                double d3 = dArr[c2];
                double d4 = d3 * dSqrt;
                double d5 = dArr[3];
                double d6 = dSqrt * d5;
                double d7 = (d3 * d4) + (d6 * d5);
                dArr12[c2] = d7;
                double d8 = dArr[1] * d4;
                double d9 = dArr[4];
                double d10 = d8 + (d6 * d9);
                dArr12[1] = d10;
                double d11 = dArr[2] * d4;
                double d12 = dArr[5];
                double d13 = d11 + (d6 * d12);
                dArr12[2] = d13;
                double d14 = -d6;
                dArr[3] = (dArr[c2] * d14) + (d5 * d4);
                dArr[4] = (dArr[1] * d14) + (d9 * d4);
                dArr[5] = (dArr[2] * d14) + (d12 * d4);
                dArr[c2] = d7;
                dArr[1] = d10;
                dArr[2] = d13;
                dArr10[c2] = d4;
                dArr10[1] = d6;
                dArr10[2] = 0.0d;
                dArr10[3] = d14;
                dArr10[4] = d4;
                dArr10[5] = 0.0d;
                dArr10[6] = 0.0d;
                dArr10[7] = 0.0d;
                dArr10[8] = 1.0d;
            }
        }
        double d15 = dArr[6];
        if (d15 * d15 < EPS) {
            dArr5 = dArr13;
            dArr6 = dArr14;
            dArr4 = dArr15;
            c = 0;
        } else {
            double d16 = dArr[c2];
            if (d16 * d16 < EPS) {
                dArr12[c2] = d16;
                dArr12[1] = dArr[1];
                dArr12[2] = dArr[2];
                dArr[c2] = d15;
                dArr[1] = dArr[7];
                dArr[2] = dArr[8];
                dArr[6] = -dArr12[c2];
                dArr[7] = -dArr12[1];
                dArr[8] = -dArr12[2];
                dArr12[c2] = dArr10[c2];
                dArr12[1] = dArr10[1];
                dArr12[2] = dArr10[2];
                dArr10[c2] = dArr10[6];
                dArr10[1] = dArr10[7];
                dArr10[2] = dArr10[8];
                dArr10[6] = -dArr12[c2];
                dArr10[7] = -dArr12[1];
                dArr10[8] = -dArr12[2];
                dArr5 = dArr13;
                dArr6 = dArr14;
                dArr4 = dArr15;
                c = 0;
            } else {
                double dSqrt2 = 1.0d / Math.sqrt((d16 * d16) + (d15 * d15));
                double d17 = dArr[c2];
                double d18 = d17 * dSqrt2;
                double d19 = dArr[6];
                double d20 = dSqrt2 * d19;
                double d21 = (d17 * d18) + (d20 * d19);
                dArr12[c2] = d21;
                double d22 = dArr[1] * d18;
                double d23 = dArr[7];
                double d24 = d22 + (d20 * d23);
                dArr12[1] = d24;
                double d25 = dArr[2] * d18;
                double d26 = dArr[8];
                double d27 = d25 + (d20 * d26);
                dArr12[2] = d27;
                dArr4 = dArr15;
                double d28 = -d20;
                dArr[6] = (dArr[c2] * d28) + (d19 * d18);
                dArr[7] = (dArr[1] * d28) + (d23 * d18);
                dArr[8] = (d28 * dArr[2]) + (d26 * d18);
                dArr[c2] = d21;
                dArr[1] = d24;
                dArr[2] = d27;
                double d29 = dArr10[c2] * d18;
                dArr12[c2] = d29;
                double d30 = dArr10[1] * d18;
                dArr12[1] = d30;
                dArr10[2] = d20;
                dArr5 = dArr13;
                dArr6 = dArr14;
                double d31 = (-dArr10[c2]) * d20;
                dArr12[6] = d31;
                double d32 = (-dArr10[1]) * d20;
                dArr12[7] = d32;
                dArr10[8] = d18;
                c = 0;
                dArr10[0] = d29;
                dArr10[1] = d30;
                dArr10[6] = d31;
                dArr10[7] = d32;
            }
        }
        double d33 = dArr[2];
        if (d33 * d33 < EPS) {
            dArr11[c] = 1.0d;
            dArr11[1] = 0.0d;
            dArr11[2] = 0.0d;
            dArr11[3] = 0.0d;
            dArr11[4] = 1.0d;
            dArr11[5] = 0.0d;
            dArr11[6] = 0.0d;
            dArr11[7] = 0.0d;
            dArr11[8] = 1.0d;
        } else {
            double d34 = dArr[1];
            if (d34 * d34 < EPS) {
                dArr12[2] = d33;
                dArr12[5] = dArr[5];
                dArr12[8] = dArr[8];
                dArr[2] = -d34;
                dArr[5] = -dArr[4];
                dArr[8] = -dArr[7];
                dArr[1] = dArr12[2];
                dArr[4] = dArr12[5];
                dArr[7] = dArr12[8];
                dArr11[0] = 1.0d;
                dArr11[1] = 0.0d;
                dArr11[2] = 0.0d;
                dArr11[3] = 0.0d;
                dArr11[4] = 0.0d;
                dArr11[5] = -1.0d;
                dArr11[6] = 0.0d;
                dArr11[7] = 1.0d;
                dArr11[8] = 0.0d;
            } else {
                double dSqrt3 = 1.0d / Math.sqrt((d34 * d34) + (d33 * d33));
                double d35 = dArr[1];
                double d36 = d35 * dSqrt3;
                double d37 = dArr[2];
                double d38 = dSqrt3 * d37;
                double d39 = (d35 * d36) + (d38 * d37);
                dArr12[1] = d39;
                double d40 = -d38;
                dArr[2] = (dArr[1] * d40) + (d37 * d36);
                dArr[1] = d39;
                double d41 = dArr[4] * d36;
                double d42 = dArr[5];
                double d43 = d41 + (d38 * d42);
                dArr12[4] = d43;
                dArr[5] = (dArr[4] * d40) + (d42 * d36);
                dArr[4] = d43;
                double d44 = dArr[7] * d36;
                double d45 = dArr[8];
                double d46 = d44 + (d38 * d45);
                dArr12[7] = d46;
                dArr[8] = (dArr[7] * d40) + (d45 * d36);
                dArr[7] = d46;
                dArr11[0] = 1.0d;
                dArr11[1] = 0.0d;
                dArr11[2] = 0.0d;
                dArr11[3] = 0.0d;
                dArr11[4] = d36;
                dArr11[5] = d40;
                dArr11[6] = 0.0d;
                dArr11[7] = d38;
                dArr11[8] = d36;
            }
        }
        double d47 = dArr[7];
        if (d47 * d47 < EPS) {
            dArr7 = dArr10;
        } else {
            double d48 = dArr[4];
            if (d48 * d48 < EPS) {
                dArr12[3] = dArr[3];
                dArr12[4] = d48;
                dArr12[5] = dArr[5];
                dArr[3] = dArr[6];
                dArr[4] = d47;
                dArr[5] = dArr[8];
                dArr[6] = -dArr12[3];
                dArr[7] = -dArr12[4];
                dArr[8] = -dArr12[5];
                dArr12[3] = dArr10[3];
                dArr12[4] = dArr10[4];
                dArr12[5] = dArr10[5];
                dArr10[3] = dArr10[6];
                dArr10[4] = dArr10[7];
                dArr10[5] = dArr10[8];
                dArr10[6] = -dArr12[3];
                dArr10[7] = -dArr12[4];
                dArr10[8] = -dArr12[5];
                dArr7 = dArr10;
            } else {
                double dSqrt4 = 1.0d / Math.sqrt((d48 * d48) + (d47 * d47));
                double d49 = dArr[4];
                double d50 = d49 * dSqrt4;
                double d51 = dArr[7];
                double d52 = dSqrt4 * d51;
                double d53 = dArr[3] * d50;
                double d54 = dArr[6];
                double d55 = d53 + (d52 * d54);
                dArr12[3] = d55;
                dArr7 = dArr10;
                double d56 = -d52;
                dArr[6] = (dArr[3] * d56) + (d54 * d50);
                dArr[3] = d55;
                double d57 = (d49 * d50) + (d52 * d51);
                dArr12[4] = d57;
                dArr[7] = (dArr[4] * d56) + (d51 * d50);
                dArr[4] = d57;
                double d58 = dArr[5] * d50;
                double d59 = dArr[8];
                double d60 = d58 + (d52 * d59);
                dArr12[5] = d60;
                dArr[8] = (dArr[5] * d56) + (d59 * d50);
                dArr[5] = d60;
                double d61 = dArr7[3] * d50;
                double d62 = dArr7[6];
                double d63 = d61 + (d52 * d62);
                dArr12[3] = d63;
                dArr7[6] = (dArr7[3] * d56) + (d62 * d50);
                dArr7[3] = d63;
                double d64 = dArr7[4] * d50;
                double d65 = dArr7[7];
                double d66 = d64 + (d52 * d65);
                dArr12[4] = d66;
                dArr7[7] = (dArr7[4] * d56) + (d65 * d50);
                dArr7[4] = d66;
                double d67 = dArr7[5] * d50;
                double d68 = dArr7[8];
                double d69 = d67 + (d52 * d68);
                dArr12[5] = d69;
                dArr7[8] = (d56 * dArr7[5]) + (d50 * d68);
                dArr7[5] = d69;
            }
        }
        dArr5[0] = dArr[0];
        dArr5[1] = dArr[4];
        dArr5[2] = dArr[8];
        double d70 = dArr[1];
        dArr4[0] = d70;
        double d71 = dArr[5];
        dArr4[1] = d71;
        if (d70 * d70 >= EPS || d71 * d71 >= EPS) {
            dArr8 = dArr5;
            dArr9 = dArr7;
            compute_qr(dArr8, dArr4, dArr9, dArr11);
        } else {
            dArr8 = dArr5;
            dArr9 = dArr7;
        }
        double d72 = dArr8[0];
        dArr16[0] = d72;
        dArr16[1] = dArr8[1];
        dArr16[2] = dArr8[2];
        if (almostEqual(Math.abs(d72), 1.0d) && almostEqual(Math.abs(dArr16[1]), 1.0d) && almostEqual(Math.abs(dArr16[2]), 1.0d)) {
            int i3 = 0;
            for (int i4 = 0; i4 < 3; i4++) {
                if (dArr16[i4] < 0.0d) {
                    i3++;
                }
            }
            if (i3 == 0 || i3 == 2) {
                dArr2[2] = 1.0d;
                dArr2[1] = 1.0d;
                dArr2[0] = 1.0d;
                for (int i5 = 0; i5 < 9; i5++) {
                    dArr3[i5] = dArr6[i5];
                }
                return;
            }
        }
        transpose_mat(dArr9, dArr12);
        transpose_mat(dArr11, dArr8);
        svdReorder(dArr, dArr12, dArr8, dArr16, dArr3, dArr2);
    }

    static void svdReorder(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) {
        char c;
        char c2;
        char c3;
        int[] iArr = new int[3];
        double[] dArr7 = new double[9];
        double d = dArr4[0];
        if (d < 0.0d) {
            dArr4[0] = -d;
            dArr3[0] = -dArr3[0];
            dArr3[1] = -dArr3[1];
            dArr3[2] = -dArr3[2];
        }
        double d2 = dArr4[1];
        if (d2 < 0.0d) {
            dArr4[1] = -d2;
            dArr3[3] = -dArr3[3];
            dArr3[4] = -dArr3[4];
            dArr3[5] = -dArr3[5];
        }
        double d3 = dArr4[2];
        if (d3 < 0.0d) {
            dArr4[2] = -d3;
            dArr3[6] = -dArr3[6];
            dArr3[7] = -dArr3[7];
            dArr3[8] = -dArr3[8];
        }
        mat_mul(dArr2, dArr3, dArr7);
        if (almostEqual(Math.abs(dArr4[0]), Math.abs(dArr4[1])) && almostEqual(Math.abs(dArr4[1]), Math.abs(dArr4[2]))) {
            for (int i = 0; i < 9; i++) {
                dArr5[i] = dArr7[i];
            }
            for (int i2 = 0; i2 < 3; i2++) {
                dArr6[i2] = dArr4[i2];
            }
            return;
        }
        double d4 = dArr4[0];
        double d5 = dArr4[1];
        if (d4 > d5) {
            double d6 = dArr4[2];
            if (d4 <= d6) {
                iArr[0] = 2;
                iArr[1] = 0;
                iArr[2] = 1;
            } else if (d6 > d5) {
                iArr[0] = 0;
                iArr[1] = 2;
                iArr[2] = 1;
            } else {
                iArr[0] = 0;
                iArr[1] = 1;
                iArr[2] = 2;
            }
        } else {
            double d7 = dArr4[2];
            if (d5 <= d7) {
                iArr[0] = 2;
                iArr[1] = 1;
                iArr[2] = 0;
            } else if (d7 > d4) {
                iArr[0] = 1;
                iArr[1] = 2;
                iArr[2] = 0;
            } else {
                iArr[0] = 1;
                iArr[1] = 0;
                iArr[2] = 2;
            }
        }
        double d8 = dArr[0];
        double d9 = dArr[1];
        double d10 = (d8 * d8) + (d9 * d9);
        double d11 = dArr[2];
        double d12 = d10 + (d11 * d11);
        double d13 = dArr[3];
        double d14 = dArr[4];
        double d15 = (d13 * d13) + (d14 * d14);
        double d16 = dArr[5];
        double d17 = d15 + (d16 * d16);
        double d18 = dArr[6];
        double d19 = dArr[7];
        double d20 = (d18 * d18) + (d19 * d19);
        double d21 = dArr[8];
        double d22 = d20 + (d21 * d21);
        if (d12 > d17) {
            if (d12 <= d22) {
                c = 1;
                c2 = 2;
                c3 = 0;
            } else if (d22 > d17) {
                c = 0;
                c2 = 2;
                c3 = 1;
            } else {
                c = 0;
                c2 = 1;
                c3 = 2;
            }
        } else if (d17 <= d22) {
            c = 2;
            c2 = 1;
            c3 = 0;
        } else if (d22 > d12) {
            c = 2;
            c2 = 0;
            c3 = 1;
        } else {
            c = 1;
            c2 = 0;
            c3 = 2;
        }
        int i3 = iArr[c];
        dArr6[0] = dArr4[i3];
        int i4 = iArr[c2];
        dArr6[1] = dArr4[i4];
        int i5 = iArr[c3];
        dArr6[2] = dArr4[i5];
        dArr5[0] = dArr7[i3];
        dArr5[3] = dArr7[i3 + 3];
        dArr5[6] = dArr7[i3 + 6];
        dArr5[1] = dArr7[i4];
        dArr5[4] = dArr7[i4 + 3];
        dArr5[7] = dArr7[i4 + 6];
        dArr5[2] = dArr7[i5];
        dArr5[5] = dArr7[i5 + 3];
        dArr5[8] = dArr7[i5 + 6];
    }

    static int compute_qr(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) {
        char c = 2;
        double[] dArr5 = new double[2];
        double[] dArr6 = new double[2];
        double[] dArr7 = new double[2];
        double[] dArr8 = new double[2];
        boolean z = Math.abs(dArr2[1]) < 4.89E-15d || Math.abs(dArr2[0]) < 4.89E-15d;
        int i = 0;
        int i2 = 1;
        while (i < 10 && !z) {
            double dCompute_shift = compute_shift(dArr[1], dArr2[1], dArr[c]);
            int i3 = i;
            compute_rot((Math.abs(dArr[0]) - dCompute_shift) * (d_sign(1.0d, dArr[0]) + (dCompute_shift / dArr[0])), dArr2[0], dArr8, dArr6, 0, i2);
            double d = dArr6[0];
            double d2 = dArr[0];
            double d3 = dArr8[0];
            double d4 = dArr2[0];
            double d5 = (d * d2) + (d3 * d4);
            dArr2[0] = (d * d4) - (d3 * d2);
            double d6 = dArr8[0];
            double d7 = dArr[1];
            dArr[1] = dArr6[0] * d7;
            double dCompute_rot = compute_rot(d5, d6 * d7, dArr7, dArr5, 0, i2);
            i2 = 0;
            dArr[0] = dCompute_rot;
            double d8 = dArr5[0];
            double d9 = dArr2[0];
            double d10 = dArr7[0];
            double d11 = dArr[1];
            double d12 = (d8 * d9) + (d10 * d11);
            dArr[1] = (d11 * d8) - (d9 * d10);
            double d13 = dArr2[1];
            dArr2[1] = d8 * d13;
            dArr2[0] = compute_rot(d12, d10 * d13, dArr8, dArr6, 1, 0);
            double d14 = dArr6[1];
            double d15 = dArr[1];
            double d16 = dArr8[1];
            double d17 = dArr2[1];
            double d18 = (d14 * d15) + (d16 * d17);
            dArr2[1] = (d14 * d17) - (d16 * d15);
            double d19 = dArr8[1];
            double d20 = dArr[c];
            dArr[c] = dArr6[1] * d20;
            dArr[1] = compute_rot(d18, d19 * d20, dArr7, dArr5, 1, 0);
            double d21 = dArr5[1];
            double d22 = dArr2[1];
            double d23 = dArr7[1];
            double d24 = dArr[c];
            dArr[c] = (d21 * d24) - (d23 * d22);
            dArr2[1] = (d21 * d22) + (d23 * d24);
            double d25 = dArr3[0];
            double d26 = dArr5[0] * d25;
            double d27 = dArr7[0];
            double d28 = dArr3[3];
            dArr3[0] = d26 + (d27 * d28);
            double d29 = dArr7[0];
            double d30 = (-d29) * d25;
            double d31 = dArr5[0];
            double d32 = d30 + (d28 * d31);
            dArr3[3] = d32;
            double d33 = dArr3[1];
            double d34 = dArr3[4];
            dArr3[1] = (d31 * d33) + (d29 * d34);
            double[] dArr9 = dArr5;
            double d35 = ((-d29) * d33) + (d34 * d31);
            dArr3[4] = d35;
            double d36 = dArr3[2];
            double d37 = dArr3[5];
            dArr3[2] = (d31 * d36) + (d29 * d37);
            double d38 = ((-d29) * d36) + (d31 * d37);
            dArr3[5] = d38;
            double d39 = dArr9[1];
            boolean z2 = z;
            double d40 = dArr7[1];
            double d41 = dArr3[6];
            dArr3[3] = (d39 * d32) + (d40 * d41);
            dArr3[6] = ((-d40) * d32) + (d41 * d39);
            double d42 = dArr3[7];
            dArr3[4] = (d39 * d35) + (d40 * d42);
            dArr3[7] = ((-d40) * d35) + (d42 * d39);
            double d43 = dArr3[8];
            dArr3[5] = (d39 * d38) + (d40 * d43);
            dArr3[8] = ((-d40) * d38) + (d39 * d43);
            double d44 = dArr4[0];
            double d45 = dArr6[0] * d44;
            double d46 = dArr8[0];
            double d47 = dArr4[1];
            dArr4[0] = d45 + (d46 * d47);
            double d48 = dArr8[0];
            double d49 = (-d48) * d44;
            double d50 = dArr6[0];
            double d51 = d49 + (d47 * d50);
            dArr4[1] = d51;
            double d52 = dArr4[3];
            double d53 = dArr4[4];
            dArr4[3] = (d50 * d52) + (d48 * d53);
            double d54 = ((-d48) * d52) + (d53 * d50);
            dArr4[4] = d54;
            double d55 = dArr4[6];
            double d56 = dArr4[7];
            dArr4[6] = (d50 * d55) + (d48 * d56);
            double d57 = ((-d48) * d55) + (d50 * d56);
            dArr4[7] = d57;
            double d58 = dArr6[1] * d51;
            double d59 = dArr8[1];
            double d60 = dArr4[2];
            dArr4[1] = d58 + (d59 * d60);
            double d61 = dArr8[1];
            double d62 = (-d61) * d51;
            double d63 = dArr6[1];
            dArr4[2] = d62 + (d60 * d63);
            double d64 = dArr4[5];
            dArr4[4] = (d63 * d54) + (d61 * d64);
            dArr4[5] = ((-d61) * d54) + (d64 * d63);
            double d65 = dArr4[8];
            dArr4[7] = (d63 * d57) + (d61 * d65);
            dArr4[8] = ((-d61) * d57) + (d63 * d65);
            double d66 = dArr[0];
            double d67 = dArr2[0];
            double d68 = dArr[1];
            double d69 = dArr2[1];
            double d70 = dArr[2];
            z = (Math.abs(d69) < 4.89E-15d || Math.abs(dArr2[0]) < 4.89E-15d) ? true : z2;
            i = i3 + 1;
            dArr5 = dArr9;
            c = 2;
        }
        double[] dArr10 = dArr5;
        if (Math.abs(dArr2[1]) < 4.89E-15d) {
            compute_2X2(dArr[0], dArr2[0], dArr[1], dArr, dArr7, dArr10, dArr8, dArr6, 0);
            double d71 = dArr3[0];
            double d72 = dArr10[0] * d71;
            double d73 = dArr7[0];
            double d74 = dArr3[3];
            dArr3[0] = d72 + (d73 * d74);
            double d75 = dArr7[0];
            double d76 = (-d75) * d71;
            double d77 = dArr10[0];
            dArr3[3] = d76 + (d74 * d77);
            double d78 = dArr3[1];
            double d79 = dArr3[4];
            dArr3[1] = (d77 * d78) + (d75 * d79);
            dArr3[4] = ((-d75) * d78) + (d79 * d77);
            double d80 = dArr3[2];
            double d81 = dArr3[5];
            dArr3[2] = (d77 * d80) + (d75 * d81);
            dArr3[5] = ((-d75) * d80) + (d77 * d81);
            double d82 = dArr4[0];
            double d83 = dArr6[0] * d82;
            double d84 = dArr8[0];
            double d85 = dArr4[1];
            dArr4[0] = d83 + (d84 * d85);
            double d86 = dArr8[0];
            double d87 = (-d86) * d82;
            double d88 = dArr6[0];
            dArr4[1] = d87 + (d85 * d88);
            double d89 = dArr4[3];
            double d90 = dArr4[4];
            dArr4[3] = (d88 * d89) + (d86 * d90);
            dArr4[4] = ((-d86) * d89) + (d90 * d88);
            double d91 = dArr4[6];
            double d92 = dArr4[7];
            dArr4[6] = (d88 * d91) + (d86 * d92);
            dArr4[7] = ((-d86) * d91) + (d88 * d92);
        } else {
            compute_2X2(dArr[1], dArr2[1], dArr[2], dArr, dArr7, dArr10, dArr8, dArr6, 1);
            double d93 = dArr3[3];
            double d94 = dArr10[0];
            double d95 = dArr7[0];
            double d96 = dArr3[6];
            dArr3[3] = (d94 * d93) + (d95 * d96);
            dArr3[6] = ((-d95) * d93) + (d96 * d94);
            double d97 = dArr3[4];
            double d98 = dArr3[7];
            dArr3[4] = (d94 * d97) + (d95 * d98);
            dArr3[7] = ((-d95) * d97) + (d98 * d94);
            double d99 = dArr3[5];
            double d100 = dArr3[8];
            dArr3[5] = (d94 * d99) + (d95 * d100);
            dArr3[8] = ((-d95) * d99) + (d94 * d100);
            double d101 = dArr4[1];
            double d102 = dArr6[0];
            double d103 = dArr8[0];
            double d104 = dArr4[2];
            dArr4[1] = (d102 * d101) + (d103 * d104);
            dArr4[2] = ((-d103) * d101) + (d104 * d102);
            double d105 = dArr4[4];
            double d106 = dArr4[5];
            dArr4[4] = (d102 * d105) + (d103 * d106);
            dArr4[5] = ((-d103) * d105) + (d106 * d102);
            double d107 = dArr4[7];
            double d108 = dArr4[8];
            dArr4[7] = (d102 * d107) + (d103 * d108);
            dArr4[8] = ((-d103) * d107) + (d102 * d108);
        }
        return 0;
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
        throw new UnsupportedOperationException("Method not decompiled: javax.vecmath.Matrix3d.compute_2X2(double, double, double, double[], double[], double[], double[], double[], int):int");
    }

    static double compute_rot(double d, double d2, double[] dArr, double[] dArr2, int i, int i2) {
        double d3;
        double d4;
        double d5;
        double dSqrt;
        double d6;
        double d7;
        double d8 = 1.0d;
        double d9 = 0.0d;
        if (d2 != 0.0d) {
            if (d == 0.0d) {
                d5 = d2;
            } else {
                double dMax = max(Math.abs(d), Math.abs(d2));
                int i3 = 0;
                int i4 = 1;
                if (dMax >= 4.994797680505588E145d) {
                    double d10 = d;
                    double d11 = d2;
                    while (dMax >= 4.994797680505588E145d) {
                        i3++;
                        d10 *= 2.002083095183101E-146d;
                        d11 *= 2.002083095183101E-146d;
                        dMax = max(Math.abs(d10), Math.abs(d11));
                    }
                    dSqrt = Math.sqrt((d10 * d10) + (d11 * d11));
                    d6 = d10 / dSqrt;
                    d7 = d11 / dSqrt;
                    while (i4 <= i3) {
                        dSqrt *= 4.994797680505588E145d;
                        i4++;
                    }
                } else if (dMax <= 2.002083095183101E-146d) {
                    double d12 = d;
                    double d13 = d2;
                    while (dMax <= 2.002083095183101E-146d) {
                        i3++;
                        d12 *= 4.994797680505588E145d;
                        d13 *= 4.994797680505588E145d;
                        dMax = max(Math.abs(d12), Math.abs(d13));
                    }
                    dSqrt = Math.sqrt((d12 * d12) + (d13 * d13));
                    d6 = d12 / dSqrt;
                    d7 = d13 / dSqrt;
                    while (i4 <= i3) {
                        dSqrt *= 2.002083095183101E-146d;
                        i4++;
                    }
                } else {
                    double dSqrt2 = Math.sqrt((d * d) + (d2 * d2));
                    d3 = d2 / dSqrt2;
                    d4 = d / dSqrt2;
                    d5 = dSqrt2;
                    if (Math.abs(d) > Math.abs(d2) || d4 >= 0.0d) {
                        d9 = d4;
                        d8 = d3;
                    } else {
                        d8 = -d4;
                        d9 = -d3;
                        d5 = -d5;
                    }
                }
                d5 = dSqrt;
                d4 = d6;
                d3 = d7;
                if (Math.abs(d) > Math.abs(d2)) {
                }
                d9 = d4;
                d8 = d3;
            }
            dArr[i] = d8;
            dArr2[i] = d9;
            return d5;
        }
        d5 = d;
        double d14 = d8;
        d8 = d9;
        d9 = d14;
        dArr[i] = d8;
        dArr2[i] = d9;
        return d5;
    }

    static void print_mat(double[] dArr) {
        for (int i = 0; i < 3; i++) {
            int i2 = i * 3;
            System.out.println(new StringBuffer().append(dArr[i2]).append(StringUtils.SPACE).append(dArr[i2 + 1]).append(StringUtils.SPACE).append(dArr[i2 + 2]).append(StringUtils.LF).toString());
        }
    }

    static void print_det(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[4];
        double d3 = dArr[8];
        double d4 = dArr[1];
        double d5 = dArr[5];
        double d6 = dArr[6];
        double d7 = dArr[2];
        double d8 = dArr[3];
        double d9 = dArr[7];
        System.out.println(new StringBuffer("det= ").append(((((((d * d2) * d3) + ((d4 * d5) * d6)) + ((d7 * d8) * d9)) - ((d7 * d2) * d6)) - ((d * d5) * d9)) - ((d4 * d8) * d3)).toString());
    }

    static void mat_mul(double[] dArr, double[] dArr2, double[] dArr3) {
        double d = dArr[0] * dArr2[0];
        double d2 = dArr[1];
        double d3 = dArr2[3];
        double d4 = dArr[2];
        double d5 = dArr2[6];
        double d6 = dArr[0];
        double d7 = dArr2[1] * d6;
        double d8 = dArr2[4];
        double d9 = dArr2[7];
        double d10 = d6 * dArr2[2];
        double d11 = dArr[1];
        double d12 = dArr2[5];
        double d13 = dArr2[8];
        double d14 = dArr[3];
        double d15 = dArr2[0];
        double d16 = dArr[4];
        double d17 = (d14 * d15) + (d3 * d16);
        double d18 = dArr[5];
        double d19 = dArr[3];
        double d20 = dArr2[1];
        double d21 = dArr2[2];
        double d22 = dArr[6] * d15;
        double d23 = dArr[7];
        double d24 = d22 + (dArr2[3] * d23);
        double d25 = dArr[8];
        double d26 = dArr[6];
        double[] dArr4 = {d + (d2 * d3) + (d4 * d5), d7 + (d2 * d8) + (d4 * d9), d10 + (d11 * d12) + (d4 * d13), d17 + (d18 * d5), (d19 * d20) + (d16 * d8) + (d18 * d9), (d19 * d21) + (dArr[4] * d12) + (d18 * d13), d24 + (d5 * d25), (d20 * d26) + (d23 * dArr2[4]) + (d9 * d25), (d26 * d21) + (dArr[7] * dArr2[5]) + (d25 * d13)};
        for (int i = 0; i < 9; i++) {
            dArr3[i] = dArr4[i];
        }
    }

    static void transpose_mat(double[] dArr, double[] dArr2) {
        dArr2[0] = dArr[0];
        dArr2[1] = dArr[3];
        dArr2[2] = dArr[6];
        dArr2[3] = dArr[1];
        dArr2[4] = dArr[4];
        dArr2[5] = dArr[7];
        dArr2[6] = dArr[2];
        dArr2[7] = dArr[5];
        dArr2[8] = dArr[8];
    }

    static double max3(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        if (d > d2) {
            double d3 = dArr[2];
            return d > d3 ? d : d3;
        }
        double d4 = dArr[2];
        return d2 > d4 ? d2 : d4;
    }

    private static final boolean almostEqual(double d, double d2) {
        if (d == d2) {
            return true;
        }
        double dAbs = Math.abs(d - d2);
        double dAbs2 = Math.abs(d);
        double dAbs3 = Math.abs(d2);
        if (dAbs2 < dAbs3) {
            dAbs2 = dAbs3;
        }
        return dAbs < 1.0E-6d || dAbs / dAbs2 < 1.0E-4d;
    }

    public final void add(double d) {
        this.m00 += d;
        this.m01 += d;
        this.m02 += d;
        this.m10 += d;
        this.m11 += d;
        this.m12 += d;
        this.m20 += d;
        this.m21 += d;
        this.m22 += d;
    }

    public final double determinant() {
        double d = this.m00;
        double d2 = this.m11;
        double d3 = this.m22;
        double d4 = this.m12;
        double d5 = this.m21;
        double d6 = d * ((d2 * d3) - (d4 * d5));
        double d7 = this.m01;
        double d8 = this.m20;
        double d9 = this.m10;
        return d6 + (d7 * ((d4 * d8) - (d3 * d9))) + (this.m02 * ((d9 * d5) - (d2 * d8)));
    }

    public final void mul(double d) {
        this.m00 *= d;
        this.m01 *= d;
        this.m02 *= d;
        this.m10 *= d;
        this.m11 *= d;
        this.m12 *= d;
        this.m20 *= d;
        this.m21 *= d;
        this.m22 *= d;
    }

    public final void negate() {
        this.m00 = -this.m00;
        this.m01 = -this.m01;
        this.m02 = -this.m02;
        this.m10 = -this.m10;
        this.m11 = -this.m11;
        this.m12 = -this.m12;
        this.m20 = -this.m20;
        this.m21 = -this.m21;
        this.m22 = -this.m22;
    }

    public final void set(double d) {
        this.m00 = d;
        this.m01 = 0.0d;
        this.m02 = 0.0d;
        this.m10 = 0.0d;
        this.m11 = d;
        this.m12 = 0.0d;
        this.m20 = 0.0d;
        this.m21 = 0.0d;
        this.m22 = d;
    }

    public final void setIdentity() {
        this.m00 = 1.0d;
        this.m01 = 0.0d;
        this.m02 = 0.0d;
        this.m10 = 0.0d;
        this.m11 = 1.0d;
        this.m12 = 0.0d;
        this.m20 = 0.0d;
        this.m21 = 0.0d;
        this.m22 = 1.0d;
    }

    public final void setZero() {
        this.m00 = 0.0d;
        this.m01 = 0.0d;
        this.m02 = 0.0d;
        this.m10 = 0.0d;
        this.m11 = 0.0d;
        this.m12 = 0.0d;
        this.m20 = 0.0d;
        this.m21 = 0.0d;
        this.m22 = 0.0d;
    }

    public final void transpose() {
        double d = this.m10;
        this.m10 = this.m01;
        this.m01 = d;
        double d2 = this.m20;
        this.m20 = this.m02;
        this.m02 = d2;
        double d3 = this.m21;
        this.m21 = this.m12;
        this.m12 = d3;
    }

    public String toString() {
        return new StringBuffer().append(this.m00).append(", ").append(this.m01).append(", ").append(this.m02).append(StringUtils.LF).append(this.m10).append(", ").append(this.m11).append(", ").append(this.m12).append(StringUtils.LF).append(this.m20).append(", ").append(this.m21).append(", ").append(this.m22).append(StringUtils.LF).toString();
    }

    public final void setElement(int i, int i2, double d) {
        if (i == 0) {
            if (i2 == 0) {
                this.m00 = d;
                return;
            } else if (i2 == 1) {
                this.m01 = d;
                return;
            } else {
                if (i2 != 2) {
                    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
                }
                this.m02 = d;
                return;
            }
        }
        if (i == 1) {
            if (i2 == 0) {
                this.m10 = d;
                return;
            } else if (i2 == 1) {
                this.m11 = d;
                return;
            } else {
                if (i2 != 2) {
                    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
                }
                this.m12 = d;
                return;
            }
        }
        if (i != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
        }
        if (i2 == 0) {
            this.m20 = d;
        } else if (i2 == 1) {
            this.m21 = d;
        } else {
            if (i2 != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
            }
            this.m22 = d;
        }
    }

    public final double getElement(int i, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    if (i2 == 0) {
                        return this.m20;
                    }
                    if (i2 == 1) {
                        return this.m21;
                    }
                    if (i2 == 2) {
                        return this.m22;
                    }
                }
            } else {
                if (i2 == 0) {
                    return this.m10;
                }
                if (i2 == 1) {
                    return this.m11;
                }
                if (i2 == 2) {
                    return this.m12;
                }
            }
        } else {
            if (i2 == 0) {
                return this.m00;
            }
            if (i2 == 1) {
                return this.m01;
            }
            if (i2 == 2) {
                return this.m02;
            }
        }
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d1"));
    }

    public final void getRow(int i, Vector3d vector3d) {
        if (i == 0) {
            vector3d.x = this.m00;
            vector3d.y = this.m01;
            vector3d.z = this.m02;
        } else if (i == 1) {
            vector3d.x = this.m10;
            vector3d.y = this.m11;
            vector3d.z = this.m12;
        } else {
            if (i == 2) {
                vector3d.x = this.m20;
                vector3d.y = this.m21;
                vector3d.z = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
        }
    }

    public final void getRow(int i, double[] dArr) {
        if (i == 0) {
            dArr[0] = this.m00;
            dArr[1] = this.m01;
            dArr[2] = this.m02;
        } else if (i == 1) {
            dArr[0] = this.m10;
            dArr[1] = this.m11;
            dArr[2] = this.m12;
        } else {
            if (i == 2) {
                dArr[0] = this.m20;
                dArr[1] = this.m21;
                dArr[2] = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
        }
    }

    public final void getColumn(int i, Vector3d vector3d) {
        if (i == 0) {
            vector3d.x = this.m00;
            vector3d.y = this.m10;
            vector3d.z = this.m20;
        } else if (i == 1) {
            vector3d.x = this.m01;
            vector3d.y = this.m11;
            vector3d.z = this.m21;
        } else {
            if (i == 2) {
                vector3d.x = this.m02;
                vector3d.y = this.m12;
                vector3d.z = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
        }
    }

    public final void getColumn(int i, double[] dArr) {
        if (i == 0) {
            dArr[0] = this.m00;
            dArr[1] = this.m10;
            dArr[2] = this.m20;
        } else if (i == 1) {
            dArr[0] = this.m01;
            dArr[1] = this.m11;
            dArr[2] = this.m21;
        } else {
            if (i == 2) {
                dArr[0] = this.m02;
                dArr[1] = this.m12;
                dArr[2] = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
        }
    }

    public final void setRow(int i, double d, double d2, double d3) {
        if (i == 0) {
            this.m00 = d;
            this.m01 = d2;
            this.m02 = d3;
        } else if (i == 1) {
            this.m10 = d;
            this.m11 = d2;
            this.m12 = d3;
        } else {
            if (i != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
            }
            this.m20 = d;
            this.m21 = d2;
            this.m22 = d3;
        }
    }

    public final void setRow(int i, Vector3d vector3d) {
        if (i == 0) {
            this.m00 = vector3d.x;
            this.m01 = vector3d.y;
            this.m02 = vector3d.z;
        } else if (i == 1) {
            this.m10 = vector3d.x;
            this.m11 = vector3d.y;
            this.m12 = vector3d.z;
        } else {
            if (i == 2) {
                this.m20 = vector3d.x;
                this.m21 = vector3d.y;
                this.m22 = vector3d.z;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
        }
    }

    public final void setRow(int i, double[] dArr) {
        if (i == 0) {
            this.m00 = dArr[0];
            this.m01 = dArr[1];
            this.m02 = dArr[2];
        } else if (i == 1) {
            this.m10 = dArr[0];
            this.m11 = dArr[1];
            this.m12 = dArr[2];
        } else {
            if (i == 2) {
                this.m20 = dArr[0];
                this.m21 = dArr[1];
                this.m22 = dArr[2];
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
        }
    }

    public final void setColumn(int i, double d, double d2, double d3) {
        if (i == 0) {
            this.m00 = d;
            this.m10 = d2;
            this.m20 = d3;
        } else if (i == 1) {
            this.m01 = d;
            this.m11 = d2;
            this.m21 = d3;
        } else {
            if (i != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
            }
            this.m02 = d;
            this.m12 = d2;
            this.m22 = d3;
        }
    }

    public final void setColumn(int i, Vector3d vector3d) {
        if (i == 0) {
            this.m00 = vector3d.x;
            this.m10 = vector3d.y;
            this.m20 = vector3d.z;
        } else if (i == 1) {
            this.m01 = vector3d.x;
            this.m11 = vector3d.y;
            this.m21 = vector3d.z;
        } else {
            if (i == 2) {
                this.m02 = vector3d.x;
                this.m12 = vector3d.y;
                this.m22 = vector3d.z;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
        }
    }

    public final void setColumn(int i, double[] dArr) {
        if (i == 0) {
            this.m00 = dArr[0];
            this.m10 = dArr[1];
            this.m20 = dArr[2];
        } else if (i == 1) {
            this.m01 = dArr[0];
            this.m11 = dArr[1];
            this.m21 = dArr[2];
        } else {
            if (i == 2) {
                this.m02 = dArr[0];
                this.m12 = dArr[1];
                this.m22 = dArr[2];
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
        }
    }

    public final double getScale() {
        double[] dArr = new double[3];
        getScaleRotate(dArr, new double[9]);
        return max3(dArr);
    }

    public final void setScale(double d) {
        double[] dArr = new double[9];
        getScaleRotate(new double[3], dArr);
        this.m00 = dArr[0] * d;
        this.m01 = dArr[1] * d;
        this.m02 = dArr[2] * d;
        this.m10 = dArr[3] * d;
        this.m11 = dArr[4] * d;
        this.m12 = dArr[5] * d;
        this.m20 = dArr[6] * d;
        this.m21 = dArr[7] * d;
        this.m22 = dArr[8] * d;
    }

    public final void add(double d, Matrix3d matrix3d) {
        this.m00 = matrix3d.m00 + d;
        this.m01 = matrix3d.m01 + d;
        this.m02 = matrix3d.m02 + d;
        this.m10 = matrix3d.m10 + d;
        this.m11 = matrix3d.m11 + d;
        this.m12 = matrix3d.m12 + d;
        this.m20 = matrix3d.m20 + d;
        this.m21 = matrix3d.m21 + d;
        this.m22 = matrix3d.m22 + d;
    }

    public final void add(Matrix3d matrix3d, Matrix3d matrix3d2) {
        this.m00 = matrix3d.m00 + matrix3d2.m00;
        this.m01 = matrix3d.m01 + matrix3d2.m01;
        this.m02 = matrix3d.m02 + matrix3d2.m02;
        this.m10 = matrix3d.m10 + matrix3d2.m10;
        this.m11 = matrix3d.m11 + matrix3d2.m11;
        this.m12 = matrix3d.m12 + matrix3d2.m12;
        this.m20 = matrix3d.m20 + matrix3d2.m20;
        this.m21 = matrix3d.m21 + matrix3d2.m21;
        this.m22 = matrix3d.m22 + matrix3d2.m22;
    }

    public final void add(Matrix3d matrix3d) {
        this.m00 += matrix3d.m00;
        this.m01 += matrix3d.m01;
        this.m02 += matrix3d.m02;
        this.m10 += matrix3d.m10;
        this.m11 += matrix3d.m11;
        this.m12 += matrix3d.m12;
        this.m20 += matrix3d.m20;
        this.m21 += matrix3d.m21;
        this.m22 += matrix3d.m22;
    }

    public final void sub(Matrix3d matrix3d, Matrix3d matrix3d2) {
        this.m00 = matrix3d.m00 - matrix3d2.m00;
        this.m01 = matrix3d.m01 - matrix3d2.m01;
        this.m02 = matrix3d.m02 - matrix3d2.m02;
        this.m10 = matrix3d.m10 - matrix3d2.m10;
        this.m11 = matrix3d.m11 - matrix3d2.m11;
        this.m12 = matrix3d.m12 - matrix3d2.m12;
        this.m20 = matrix3d.m20 - matrix3d2.m20;
        this.m21 = matrix3d.m21 - matrix3d2.m21;
        this.m22 = matrix3d.m22 - matrix3d2.m22;
    }

    public final void sub(Matrix3d matrix3d) {
        this.m00 -= matrix3d.m00;
        this.m01 -= matrix3d.m01;
        this.m02 -= matrix3d.m02;
        this.m10 -= matrix3d.m10;
        this.m11 -= matrix3d.m11;
        this.m12 -= matrix3d.m12;
        this.m20 -= matrix3d.m20;
        this.m21 -= matrix3d.m21;
        this.m22 -= matrix3d.m22;
    }

    public final void transpose(Matrix3d matrix3d) {
        if (this != matrix3d) {
            this.m00 = matrix3d.m00;
            this.m01 = matrix3d.m10;
            this.m02 = matrix3d.m20;
            this.m10 = matrix3d.m01;
            this.m11 = matrix3d.m11;
            this.m12 = matrix3d.m21;
            this.m20 = matrix3d.m02;
            this.m21 = matrix3d.m12;
            this.m22 = matrix3d.m22;
            return;
        }
        transpose();
    }

    public final void set(Quat4d quat4d) {
        this.m00 = (1.0d - ((quat4d.y * 2.0d) * quat4d.y)) - ((quat4d.z * 2.0d) * quat4d.z);
        this.m10 = ((quat4d.x * quat4d.y) + (quat4d.w * quat4d.z)) * 2.0d;
        this.m20 = ((quat4d.x * quat4d.z) - (quat4d.w * quat4d.y)) * 2.0d;
        this.m01 = ((quat4d.x * quat4d.y) - (quat4d.w * quat4d.z)) * 2.0d;
        this.m11 = (1.0d - ((quat4d.x * 2.0d) * quat4d.x)) - ((quat4d.z * 2.0d) * quat4d.z);
        this.m21 = ((quat4d.y * quat4d.z) + (quat4d.w * quat4d.x)) * 2.0d;
        this.m02 = ((quat4d.x * quat4d.z) + (quat4d.w * quat4d.y)) * 2.0d;
        this.m12 = ((quat4d.y * quat4d.z) - (quat4d.w * quat4d.x)) * 2.0d;
        this.m22 = (1.0d - ((quat4d.x * 2.0d) * quat4d.x)) - ((quat4d.y * 2.0d) * quat4d.y);
    }

    public final void set(AxisAngle4d axisAngle4d) {
        double dSqrt = Math.sqrt((axisAngle4d.x * axisAngle4d.x) + (axisAngle4d.y * axisAngle4d.y) + (axisAngle4d.z * axisAngle4d.z));
        if (dSqrt < EPS) {
            this.m00 = 1.0d;
            this.m01 = 0.0d;
            this.m02 = 0.0d;
            this.m10 = 0.0d;
            this.m11 = 1.0d;
            this.m12 = 0.0d;
            this.m20 = 0.0d;
            this.m21 = 0.0d;
            this.m22 = 1.0d;
            return;
        }
        double d = 1.0d / dSqrt;
        double d2 = axisAngle4d.x * d;
        double d3 = axisAngle4d.y * d;
        double d4 = axisAngle4d.z * d;
        double dSin = Math.sin(axisAngle4d.angle);
        double dCos = Math.cos(axisAngle4d.angle);
        double d5 = 1.0d - dCos;
        double d6 = axisAngle4d.x * axisAngle4d.z;
        double d7 = axisAngle4d.x * axisAngle4d.y;
        double d8 = axisAngle4d.y * axisAngle4d.z;
        this.m00 = (d5 * d2 * d2) + dCos;
        double d9 = d7 * d5;
        double d10 = dSin * d4;
        this.m01 = d9 - d10;
        double d11 = d5 * d6;
        double d12 = dSin * d3;
        this.m02 = d11 + d12;
        this.m10 = d9 + d10;
        this.m11 = (d5 * d3 * d3) + dCos;
        double d13 = d8 * d5;
        double d14 = dSin * d2;
        this.m12 = d13 - d14;
        this.m20 = d11 - d12;
        this.m21 = d13 + d14;
        this.m22 = (d5 * d4 * d4) + dCos;
    }

    public final void set(Quat4f quat4f) {
        this.m00 = (1.0d - ((quat4f.y * 2.0d) * quat4f.y)) - ((quat4f.z * 2.0d) * quat4f.z);
        this.m10 = ((quat4f.x * quat4f.y) + (quat4f.w * quat4f.z)) * 2.0d;
        this.m20 = ((quat4f.x * quat4f.z) - (quat4f.w * quat4f.y)) * 2.0d;
        this.m01 = ((quat4f.x * quat4f.y) - (quat4f.w * quat4f.z)) * 2.0d;
        this.m11 = (1.0d - ((quat4f.x * 2.0d) * quat4f.x)) - ((quat4f.z * 2.0d) * quat4f.z);
        this.m21 = ((quat4f.y * quat4f.z) + (quat4f.w * quat4f.x)) * 2.0d;
        this.m02 = ((quat4f.x * quat4f.z) + (quat4f.w * quat4f.y)) * 2.0d;
        this.m12 = ((quat4f.y * quat4f.z) - (quat4f.w * quat4f.x)) * 2.0d;
        this.m22 = (1.0d - ((quat4f.x * 2.0d) * quat4f.x)) - ((quat4f.y * 2.0d) * quat4f.y);
    }

    public final void set(AxisAngle4f axisAngle4f) {
        double dSqrt = Math.sqrt((axisAngle4f.x * axisAngle4f.x) + (axisAngle4f.y * axisAngle4f.y) + (axisAngle4f.z * axisAngle4f.z));
        if (dSqrt < EPS) {
            this.m00 = 1.0d;
            this.m01 = 0.0d;
            this.m02 = 0.0d;
            this.m10 = 0.0d;
            this.m11 = 1.0d;
            this.m12 = 0.0d;
            this.m20 = 0.0d;
            this.m21 = 0.0d;
            this.m22 = 1.0d;
            return;
        }
        double d = 1.0d / dSqrt;
        double d2 = axisAngle4f.x * d;
        double d3 = axisAngle4f.y * d;
        double d4 = axisAngle4f.z * d;
        double dSin = Math.sin(axisAngle4f.angle);
        double dCos = Math.cos(axisAngle4f.angle);
        double d5 = 1.0d - dCos;
        double d6 = d2 * d4;
        double d7 = d3 * d4;
        this.m00 = (d5 * d2 * d2) + dCos;
        double d8 = d2 * d3 * d5;
        double d9 = dSin * d4;
        this.m01 = d8 - d9;
        double d10 = d6 * d5;
        double d11 = dSin * d3;
        this.m02 = d10 + d11;
        this.m10 = d8 + d9;
        this.m11 = (d5 * d3 * d3) + dCos;
        double d12 = d7 * d5;
        double d13 = dSin * d2;
        this.m12 = d12 - d13;
        this.m20 = d10 - d11;
        this.m21 = d12 + d13;
        this.m22 = (d5 * d4 * d4) + dCos;
    }

    public final void set(Matrix3f matrix3f) {
        this.m00 = matrix3f.m00;
        this.m01 = matrix3f.m01;
        this.m02 = matrix3f.m02;
        this.m10 = matrix3f.m10;
        this.m11 = matrix3f.m11;
        this.m12 = matrix3f.m12;
        this.m20 = matrix3f.m20;
        this.m21 = matrix3f.m21;
        this.m22 = matrix3f.m22;
    }

    public final void set(Matrix3d matrix3d) {
        this.m00 = matrix3d.m00;
        this.m01 = matrix3d.m01;
        this.m02 = matrix3d.m02;
        this.m10 = matrix3d.m10;
        this.m11 = matrix3d.m11;
        this.m12 = matrix3d.m12;
        this.m20 = matrix3d.m20;
        this.m21 = matrix3d.m21;
        this.m22 = matrix3d.m22;
    }

    public final void set(double[] dArr) {
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void invert(Matrix3d matrix3d) {
        invertGeneral(matrix3d);
    }

    public final void invert() {
        invertGeneral(this);
    }

    private final void invertGeneral(Matrix3d matrix3d) {
        double[] dArr = new double[9];
        int[] iArr = new int[3];
        double[] dArr2 = {matrix3d.m00, matrix3d.m01, matrix3d.m02, matrix3d.m10, matrix3d.m11, matrix3d.m12, matrix3d.m20, matrix3d.m21, matrix3d.m22};
        if (!luDecomposition(dArr2, iArr)) {
            throw new SingularMatrixException(VecMathI18N.getString("Matrix3d12"));
        }
        for (int i = 0; i < 9; i++) {
            dArr[i] = 0.0d;
        }
        dArr[0] = 1.0d;
        dArr[4] = 1.0d;
        dArr[8] = 1.0d;
        luBacksubstitution(dArr2, iArr, dArr);
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void rotX(double d) {
        double dSin = Math.sin(d);
        double dCos = Math.cos(d);
        this.m00 = 1.0d;
        this.m01 = 0.0d;
        this.m02 = 0.0d;
        this.m10 = 0.0d;
        this.m11 = dCos;
        this.m12 = -dSin;
        this.m20 = 0.0d;
        this.m21 = dSin;
        this.m22 = dCos;
    }

    public final void rotY(double d) {
        double dSin = Math.sin(d);
        double dCos = Math.cos(d);
        this.m00 = dCos;
        this.m01 = 0.0d;
        this.m02 = dSin;
        this.m10 = 0.0d;
        this.m11 = 1.0d;
        this.m12 = 0.0d;
        this.m20 = -dSin;
        this.m21 = 0.0d;
        this.m22 = dCos;
    }

    public final void rotZ(double d) {
        double dSin = Math.sin(d);
        double dCos = Math.cos(d);
        this.m00 = dCos;
        this.m01 = -dSin;
        this.m02 = 0.0d;
        this.m10 = dSin;
        this.m11 = dCos;
        this.m12 = 0.0d;
        this.m20 = 0.0d;
        this.m21 = 0.0d;
        this.m22 = 1.0d;
    }

    public final void mul(double d, Matrix3d matrix3d) {
        this.m00 = matrix3d.m00 * d;
        this.m01 = matrix3d.m01 * d;
        this.m02 = matrix3d.m02 * d;
        this.m10 = matrix3d.m10 * d;
        this.m11 = matrix3d.m11 * d;
        this.m12 = matrix3d.m12 * d;
        this.m20 = matrix3d.m20 * d;
        this.m21 = matrix3d.m21 * d;
        this.m22 = d * matrix3d.m22;
    }

    public final void mul(Matrix3d matrix3d) {
        double d = this.m00;
        double d2 = matrix3d.m00;
        double d3 = this.m01;
        double d4 = matrix3d.m10;
        double d5 = this.m02;
        double d6 = matrix3d.m20;
        double d7 = (d * d2) + (d3 * d4) + (d5 * d6);
        double d8 = matrix3d.m01;
        double d9 = d * d8;
        double d10 = matrix3d.m11;
        double d11 = d9 + (d3 * d10);
        double d12 = matrix3d.m21;
        double d13 = d11 + (d5 * d12);
        double d14 = matrix3d.m02;
        double d15 = d * d14;
        double d16 = matrix3d.m12;
        double d17 = d15 + (d3 * d16);
        double d18 = matrix3d.m22;
        double d19 = d17 + (d5 * d18);
        double d20 = this.m10;
        double d21 = this.m11;
        double d22 = (d20 * d2) + (d21 * d4);
        double d23 = this.m12;
        double d24 = d22 + (d23 * d6);
        double d25 = (d20 * d8) + (d21 * d10) + (d23 * d12);
        double d26 = (d20 * d14) + (d21 * d16) + (d23 * d18);
        double d27 = this.m20;
        double d28 = this.m21;
        double d29 = this.m22;
        this.m00 = d7;
        this.m01 = d13;
        this.m02 = d19;
        this.m10 = d24;
        this.m11 = d25;
        this.m12 = d26;
        this.m20 = (d2 * d27) + (d28 * d4) + (d29 * d6);
        this.m21 = (d8 * d27) + (d10 * d28) + (d29 * d12);
        this.m22 = (d27 * d14) + (d28 * d16) + (d29 * d18);
    }

    public final void mul(Matrix3d matrix3d, Matrix3d matrix3d2) {
        if (this != matrix3d && this != matrix3d2) {
            double d = matrix3d.m00 * matrix3d2.m00;
            double d2 = matrix3d.m01;
            double d3 = matrix3d2.m10;
            double d4 = matrix3d.m02;
            double d5 = matrix3d2.m20;
            this.m00 = d + (d2 * d3) + (d4 * d5);
            double d6 = matrix3d.m00;
            double d7 = matrix3d2.m01 * d6;
            double d8 = matrix3d2.m11;
            double d9 = d7 + (d2 * d8);
            double d10 = matrix3d2.m21;
            this.m01 = d9 + (d4 * d10);
            double d11 = d6 * matrix3d2.m02;
            double d12 = matrix3d.m01;
            double d13 = matrix3d2.m12;
            double d14 = d11 + (d12 * d13);
            double d15 = matrix3d2.m22;
            this.m02 = d14 + (d4 * d15);
            double d16 = matrix3d.m10;
            double d17 = matrix3d2.m00;
            double d18 = d16 * d17;
            double d19 = matrix3d.m11;
            double d20 = d18 + (d3 * d19);
            double d21 = matrix3d.m12;
            this.m10 = d20 + (d21 * d5);
            double d22 = matrix3d.m10;
            double d23 = matrix3d2.m01;
            this.m11 = (d22 * d23) + (d19 * d8) + (d21 * d10);
            double d24 = matrix3d2.m02;
            this.m12 = (d22 * d24) + (matrix3d.m11 * d13) + (d21 * d15);
            double d25 = matrix3d.m20 * d17;
            double d26 = matrix3d.m21;
            double d27 = d25 + (matrix3d2.m10 * d26);
            double d28 = matrix3d.m22;
            this.m20 = d27 + (d28 * d5);
            double d29 = matrix3d.m20;
            this.m21 = (d23 * d29) + (d26 * matrix3d2.m11) + (d28 * d10);
            this.m22 = (d29 * d24) + (matrix3d.m21 * matrix3d2.m12) + (d28 * d15);
            return;
        }
        double d30 = matrix3d.m00;
        double d31 = matrix3d2.m00;
        double d32 = matrix3d.m01;
        double d33 = matrix3d2.m10;
        double d34 = (d30 * d31) + (d32 * d33);
        double d35 = matrix3d.m02;
        double d36 = matrix3d2.m20;
        double d37 = d34 + (d35 * d36);
        double d38 = matrix3d2.m01;
        double d39 = d30 * d38;
        double d40 = matrix3d2.m11;
        double d41 = d39 + (d32 * d40);
        double d42 = matrix3d2.m21;
        double d43 = d41 + (d35 * d42);
        double d44 = matrix3d2.m02;
        double d45 = d30 * d44;
        double d46 = matrix3d2.m12;
        double d47 = d45 + (d32 * d46);
        double d48 = matrix3d2.m22;
        double d49 = d47 + (d35 * d48);
        double d50 = matrix3d.m10;
        double d51 = matrix3d.m11;
        double d52 = matrix3d.m12;
        double d53 = (d50 * d31) + (d51 * d33) + (d52 * d36);
        double d54 = (d50 * d38) + (d51 * d40) + (d52 * d42);
        double d55 = (d50 * d44) + (d51 * d46) + (d52 * d48);
        double d56 = matrix3d.m20;
        double d57 = matrix3d.m21;
        double d58 = (d56 * d31) + (d33 * d57);
        double d59 = matrix3d.m22;
        this.m00 = d37;
        this.m01 = d43;
        this.m02 = d49;
        this.m10 = d53;
        this.m11 = d54;
        this.m12 = d55;
        this.m20 = d58 + (d59 * d36);
        this.m21 = (d38 * d56) + (d40 * d57) + (d59 * d42);
        this.m22 = (d56 * d44) + (d57 * d46) + (d59 * d48);
    }

    public final void mulNormalize(Matrix3d matrix3d) {
        double[] dArr = new double[9];
        double d = this.m00;
        double d2 = matrix3d.m00;
        double d3 = this.m01;
        double d4 = matrix3d.m10;
        double d5 = (d * d2) + (d3 * d4);
        double d6 = this.m02;
        double d7 = matrix3d.m20;
        double d8 = matrix3d.m01;
        double d9 = d * d8;
        double d10 = matrix3d.m11;
        double d11 = d9 + (d3 * d10);
        double d12 = matrix3d.m21;
        double d13 = matrix3d.m02;
        double d14 = d * d13;
        double d15 = matrix3d.m12;
        double d16 = d14 + (d3 * d15);
        double d17 = matrix3d.m22;
        double d18 = this.m10;
        double d19 = this.m11;
        double d20 = (d18 * d2) + (d19 * d4);
        double d21 = this.m12;
        double d22 = this.m20;
        double d23 = this.m21;
        double d24 = this.m22;
        compute_svd(new double[]{d5 + (d6 * d7), d11 + (d6 * d12), d16 + (d6 * d17), d20 + (d21 * d7), (d18 * d8) + (d19 * d10) + (d21 * d12), (d18 * d13) + (d19 * d15) + (d21 * d17), (d22 * d2) + (d4 * d23) + (d7 * d24), (d22 * d8) + (d23 * d10) + (d12 * d24), (d22 * d13) + (d23 * d15) + (d24 * d17)}, new double[3], dArr);
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void mulNormalize(Matrix3d matrix3d, Matrix3d matrix3d2) {
        double[] dArr = new double[9];
        double d = matrix3d.m00;
        double d2 = matrix3d2.m00;
        double d3 = matrix3d.m01;
        double d4 = matrix3d2.m10;
        double d5 = (d * d2) + (d3 * d4);
        double d6 = matrix3d.m02;
        double d7 = matrix3d2.m20;
        double d8 = matrix3d2.m01;
        double d9 = d * d8;
        double d10 = matrix3d2.m11;
        double d11 = d9 + (d3 * d10);
        double d12 = matrix3d2.m21;
        double d13 = matrix3d2.m02;
        double d14 = d * d13;
        double d15 = matrix3d2.m12;
        double d16 = d14 + (d3 * d15);
        double d17 = matrix3d2.m22;
        double d18 = matrix3d.m10;
        double d19 = matrix3d.m11;
        double d20 = matrix3d.m12;
        double d21 = matrix3d.m20;
        double d22 = matrix3d.m21;
        double d23 = matrix3d.m22;
        compute_svd(new double[]{d5 + (d6 * d7), d11 + (d6 * d12), d16 + (d6 * d17), (d18 * d2) + (d19 * d4) + (d20 * d7), (d18 * d8) + (d19 * d10) + (d20 * d12), (d18 * d13) + (d19 * d15) + (d20 * d17), (d21 * d2) + (d4 * d22) + (d7 * d23), (d21 * d8) + (d22 * d10) + (d23 * d12), (d21 * d13) + (d22 * d15) + (d23 * d17)}, new double[3], dArr);
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void mulTransposeBoth(Matrix3d matrix3d, Matrix3d matrix3d2) {
        if (this != matrix3d && this != matrix3d2) {
            double d = matrix3d.m00 * matrix3d2.m00;
            double d2 = matrix3d.m10;
            double d3 = d + (matrix3d2.m01 * d2);
            double d4 = matrix3d.m20;
            this.m00 = d3 + (matrix3d2.m02 * d4);
            double d5 = matrix3d.m00;
            double d6 = matrix3d2.m10 * d5;
            double d7 = matrix3d2.m11;
            double d8 = matrix3d2.m12;
            this.m01 = d6 + (d2 * d7) + (d4 * d8);
            double d9 = matrix3d2.m20;
            double d10 = d5 * d9;
            double d11 = matrix3d2.m21;
            double d12 = d10 + (d2 * d11);
            double d13 = matrix3d2.m22;
            this.m02 = d12 + (d4 * d13);
            double d14 = matrix3d.m01;
            double d15 = matrix3d2.m00;
            double d16 = d14 * d15;
            double d17 = matrix3d.m11;
            double d18 = matrix3d2.m01;
            double d19 = d16 + (d17 * d18);
            double d20 = matrix3d.m21;
            double d21 = matrix3d2.m02;
            this.m10 = d19 + (d20 * d21);
            double d22 = matrix3d2.m10;
            this.m11 = (d14 * d22) + (d17 * d7) + (d8 * d20);
            this.m12 = (d14 * d9) + (matrix3d.m11 * d11) + (d20 * d13);
            double d23 = matrix3d.m02;
            double d24 = matrix3d.m12;
            double d25 = matrix3d.m22;
            this.m20 = (d23 * d15) + (d24 * d18) + (d25 * d21);
            this.m21 = (d22 * d23) + (matrix3d2.m11 * d24) + (matrix3d2.m12 * d25);
            this.m22 = (d23 * matrix3d2.m20) + (d24 * matrix3d2.m21) + (d25 * d13);
            return;
        }
        double d26 = matrix3d.m00;
        double d27 = matrix3d2.m00;
        double d28 = matrix3d.m10;
        double d29 = matrix3d2.m01;
        double d30 = (d26 * d27) + (d28 * d29);
        double d31 = matrix3d.m20;
        double d32 = matrix3d2.m02;
        double d33 = d30 + (d31 * d32);
        double d34 = matrix3d2.m10;
        double d35 = d26 * d34;
        double d36 = matrix3d2.m11;
        double d37 = d35 + (d28 * d36);
        double d38 = matrix3d2.m12;
        double d39 = d37 + (d31 * d38);
        double d40 = matrix3d2.m20;
        double d41 = d26 * d40;
        double d42 = matrix3d2.m21;
        double d43 = d41 + (d28 * d42);
        double d44 = matrix3d2.m22;
        double d45 = d43 + (d31 * d44);
        double d46 = matrix3d.m01;
        double d47 = matrix3d.m11;
        double d48 = matrix3d.m21;
        double d49 = (d46 * d27) + (d47 * d29) + (d48 * d32);
        double d50 = (d46 * d34) + (d47 * d36) + (d48 * d38);
        double d51 = (d46 * d40) + (d47 * d42) + (d48 * d44);
        double d52 = matrix3d.m02;
        double d53 = matrix3d.m12;
        double d54 = (d52 * d27) + (d29 * d53);
        double d55 = matrix3d.m22;
        this.m00 = d33;
        this.m01 = d39;
        this.m02 = d45;
        this.m10 = d49;
        this.m11 = d50;
        this.m12 = d51;
        this.m20 = d54 + (d55 * d32);
        this.m21 = (d34 * d52) + (d36 * d53) + (d55 * d38);
        this.m22 = (d52 * d40) + (d53 * d42) + (d55 * d44);
    }

    public final void mulTransposeRight(Matrix3d matrix3d, Matrix3d matrix3d2) {
        if (this != matrix3d && this != matrix3d2) {
            double d = matrix3d.m00 * matrix3d2.m00;
            double d2 = matrix3d.m01;
            double d3 = d + (matrix3d2.m01 * d2);
            double d4 = matrix3d.m02;
            this.m00 = d3 + (matrix3d2.m02 * d4);
            double d5 = matrix3d.m00;
            double d6 = matrix3d2.m10 * d5;
            double d7 = matrix3d2.m11;
            double d8 = d6 + (d2 * d7);
            double d9 = matrix3d2.m12;
            this.m01 = d8 + (d4 * d9);
            double d10 = matrix3d2.m20;
            double d11 = d5 * d10;
            double d12 = matrix3d.m01;
            double d13 = matrix3d2.m21;
            double d14 = d11 + (d12 * d13);
            double d15 = matrix3d2.m22;
            this.m02 = d14 + (d4 * d15);
            double d16 = matrix3d.m10;
            double d17 = matrix3d2.m00;
            double d18 = d16 * d17;
            double d19 = matrix3d.m11;
            double d20 = matrix3d2.m01;
            double d21 = d18 + (d19 * d20);
            double d22 = matrix3d.m12;
            double d23 = matrix3d2.m02;
            this.m10 = d21 + (d22 * d23);
            double d24 = matrix3d.m10;
            double d25 = matrix3d2.m10;
            this.m11 = (d24 * d25) + (d19 * d7) + (d9 * d22);
            this.m12 = (d24 * d10) + (matrix3d.m11 * d13) + (d22 * d15);
            double d26 = matrix3d.m20 * d17;
            double d27 = matrix3d.m21;
            double d28 = matrix3d.m22;
            this.m20 = d26 + (d27 * d20) + (d28 * d23);
            double d29 = matrix3d.m20;
            this.m21 = (d25 * d29) + (d27 * matrix3d2.m11) + (matrix3d2.m12 * d28);
            this.m22 = (d29 * matrix3d2.m20) + (matrix3d.m21 * matrix3d2.m21) + (d28 * d15);
            return;
        }
        double d30 = matrix3d.m00;
        double d31 = matrix3d2.m00;
        double d32 = matrix3d.m01;
        double d33 = matrix3d2.m01;
        double d34 = (d30 * d31) + (d32 * d33);
        double d35 = matrix3d.m02;
        double d36 = matrix3d2.m02;
        double d37 = d34 + (d35 * d36);
        double d38 = matrix3d2.m10;
        double d39 = d30 * d38;
        double d40 = matrix3d2.m11;
        double d41 = d39 + (d32 * d40);
        double d42 = matrix3d2.m12;
        double d43 = d41 + (d35 * d42);
        double d44 = matrix3d2.m20;
        double d45 = d30 * d44;
        double d46 = matrix3d2.m21;
        double d47 = d45 + (d32 * d46);
        double d48 = matrix3d2.m22;
        double d49 = d47 + (d35 * d48);
        double d50 = matrix3d.m10;
        double d51 = matrix3d.m11;
        double d52 = matrix3d.m12;
        double d53 = (d50 * d31) + (d51 * d33) + (d52 * d36);
        double d54 = (d50 * d38) + (d51 * d40) + (d52 * d42);
        double d55 = (d50 * d44) + (d51 * d46) + (d52 * d48);
        double d56 = matrix3d.m20;
        double d57 = matrix3d.m21;
        double d58 = (d56 * d31) + (d33 * d57);
        double d59 = matrix3d.m22;
        this.m00 = d37;
        this.m01 = d43;
        this.m02 = d49;
        this.m10 = d53;
        this.m11 = d54;
        this.m12 = d55;
        this.m20 = d58 + (d59 * d36);
        this.m21 = (d38 * d56) + (d40 * d57) + (d59 * d42);
        this.m22 = (d56 * d44) + (d57 * d46) + (d59 * d48);
    }

    public final void mulTransposeLeft(Matrix3d matrix3d, Matrix3d matrix3d2) {
        if (this != matrix3d && this != matrix3d2) {
            double d = matrix3d.m00 * matrix3d2.m00;
            double d2 = matrix3d.m10;
            double d3 = matrix3d2.m10;
            double d4 = matrix3d.m20;
            double d5 = matrix3d2.m20;
            this.m00 = d + (d2 * d3) + (d4 * d5);
            double d6 = matrix3d.m00;
            double d7 = matrix3d2.m01 * d6;
            double d8 = matrix3d2.m11;
            double d9 = d7 + (d2 * d8);
            double d10 = matrix3d2.m21;
            this.m01 = d9 + (d4 * d10);
            double d11 = d6 * matrix3d2.m02;
            double d12 = matrix3d2.m12;
            double d13 = d11 + (d2 * d12);
            double d14 = matrix3d2.m22;
            this.m02 = d13 + (d4 * d14);
            double d15 = matrix3d.m01;
            double d16 = matrix3d2.m00;
            double d17 = d15 * d16;
            double d18 = matrix3d.m11;
            double d19 = d17 + (d3 * d18);
            double d20 = matrix3d.m21;
            this.m10 = d19 + (d20 * d5);
            double d21 = matrix3d2.m01;
            this.m11 = (d15 * d21) + (d18 * d8) + (d20 * d10);
            double d22 = matrix3d2.m02;
            this.m12 = (d15 * d22) + (matrix3d.m11 * d12) + (d20 * d14);
            double d23 = matrix3d.m02;
            double d24 = matrix3d.m12;
            double d25 = (d23 * d16) + (matrix3d2.m10 * d24);
            double d26 = matrix3d.m22;
            this.m20 = d25 + (d5 * d26);
            this.m21 = (d21 * d23) + (matrix3d2.m11 * d24) + (d10 * d26);
            this.m22 = (d23 * d22) + (d24 * matrix3d2.m12) + (d26 * d14);
            return;
        }
        double d27 = matrix3d.m00;
        double d28 = matrix3d2.m00;
        double d29 = matrix3d.m10;
        double d30 = matrix3d2.m10;
        double d31 = (d27 * d28) + (d29 * d30);
        double d32 = matrix3d.m20;
        double d33 = matrix3d2.m20;
        double d34 = d31 + (d32 * d33);
        double d35 = matrix3d2.m01;
        double d36 = d27 * d35;
        double d37 = matrix3d2.m11;
        double d38 = d36 + (d29 * d37);
        double d39 = matrix3d2.m21;
        double d40 = d38 + (d32 * d39);
        double d41 = matrix3d2.m02;
        double d42 = d27 * d41;
        double d43 = matrix3d2.m12;
        double d44 = d42 + (d29 * d43);
        double d45 = matrix3d2.m22;
        double d46 = d44 + (d32 * d45);
        double d47 = matrix3d.m01;
        double d48 = matrix3d.m11;
        double d49 = matrix3d.m21;
        double d50 = (d47 * d28) + (d48 * d30) + (d49 * d33);
        double d51 = (d47 * d35) + (d48 * d37) + (d49 * d39);
        double d52 = (d47 * d41) + (d48 * d43) + (d49 * d45);
        double d53 = matrix3d.m02;
        double d54 = matrix3d.m12;
        double d55 = (d53 * d28) + (d30 * d54);
        double d56 = matrix3d.m22;
        this.m00 = d34;
        this.m01 = d40;
        this.m02 = d46;
        this.m10 = d50;
        this.m11 = d51;
        this.m12 = d52;
        this.m20 = d55 + (d56 * d33);
        this.m21 = (d35 * d53) + (d37 * d54) + (d56 * d39);
        this.m22 = (d53 * d41) + (d54 * d43) + (d56 * d45);
    }

    public final void normalize() {
        double[] dArr = new double[9];
        getScaleRotate(new double[3], dArr);
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void normalize(Matrix3d matrix3d) {
        double[] dArr = new double[9];
        compute_svd(new double[]{matrix3d.m00, matrix3d.m01, matrix3d.m02, matrix3d.m10, matrix3d.m11, matrix3d.m12, matrix3d.m20, matrix3d.m21, matrix3d.m22}, new double[3], dArr);
        this.m00 = dArr[0];
        this.m01 = dArr[1];
        this.m02 = dArr[2];
        this.m10 = dArr[3];
        this.m11 = dArr[4];
        this.m12 = dArr[5];
        this.m20 = dArr[6];
        this.m21 = dArr[7];
        this.m22 = dArr[8];
    }

    public final void normalizeCP() {
        double d = this.m00;
        double d2 = this.m10;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.m20;
        double dSqrt = 1.0d / Math.sqrt(d3 + (d4 * d4));
        this.m00 *= dSqrt;
        this.m10 *= dSqrt;
        this.m20 *= dSqrt;
        double d5 = this.m01;
        double d6 = this.m11;
        double d7 = (d5 * d5) + (d6 * d6);
        double d8 = this.m21;
        double dSqrt2 = 1.0d / Math.sqrt(d7 + (d8 * d8));
        double d9 = this.m01 * dSqrt2;
        this.m01 = d9;
        double d10 = this.m11 * dSqrt2;
        this.m11 = d10;
        double d11 = this.m21 * dSqrt2;
        this.m21 = d11;
        double d12 = this.m10;
        double d13 = this.m20;
        this.m02 = (d12 * d11) - (d10 * d13);
        double d14 = this.m00;
        this.m12 = (d13 * d9) - (d11 * d14);
        this.m22 = (d14 * d10) - (d9 * d12);
    }

    public final void normalizeCP(Matrix3d matrix3d) {
        double d = matrix3d.m00;
        double d2 = matrix3d.m10;
        double d3 = (d * d) + (d2 * d2);
        double d4 = matrix3d.m20;
        double dSqrt = 1.0d / Math.sqrt(d3 + (d4 * d4));
        this.m00 = matrix3d.m00 * dSqrt;
        this.m10 = matrix3d.m10 * dSqrt;
        this.m20 = matrix3d.m20 * dSqrt;
        double d5 = matrix3d.m01;
        double d6 = matrix3d.m11;
        double d7 = (d5 * d5) + (d6 * d6);
        double d8 = matrix3d.m21;
        double dSqrt2 = 1.0d / Math.sqrt(d7 + (d8 * d8));
        double d9 = matrix3d.m01 * dSqrt2;
        this.m01 = d9;
        double d10 = matrix3d.m11 * dSqrt2;
        this.m11 = d10;
        double d11 = matrix3d.m21 * dSqrt2;
        this.m21 = d11;
        double d12 = this.m10;
        double d13 = this.m20;
        this.m02 = (d12 * d11) - (d10 * d13);
        double d14 = this.m00;
        this.m12 = (d13 * d9) - (d11 * d14);
        this.m22 = (d14 * d10) - (d9 * d12);
    }

    public boolean equals(Matrix3d matrix3d) {
        try {
            if (this.m00 == matrix3d.m00 && this.m01 == matrix3d.m01 && this.m02 == matrix3d.m02 && this.m10 == matrix3d.m10 && this.m11 == matrix3d.m11 && this.m12 == matrix3d.m12 && this.m20 == matrix3d.m20 && this.m21 == matrix3d.m21) {
                return this.m22 == matrix3d.m22;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Matrix3d matrix3d = (Matrix3d) obj;
            if (this.m00 == matrix3d.m00 && this.m01 == matrix3d.m01 && this.m02 == matrix3d.m02 && this.m10 == matrix3d.m10 && this.m11 == matrix3d.m11 && this.m12 == matrix3d.m12 && this.m20 == matrix3d.m20 && this.m21 == matrix3d.m21) {
                return this.m22 == matrix3d.m22;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(Matrix3d matrix3d, double d) {
        double d2 = this.m00 - matrix3d.m00;
        if (d2 < 0.0d) {
            d2 = -d2;
        }
        if (d2 > d) {
            return false;
        }
        double d3 = this.m01 - matrix3d.m01;
        if (d3 < 0.0d) {
            d3 = -d3;
        }
        if (d3 > d) {
            return false;
        }
        double d4 = this.m02 - matrix3d.m02;
        if (d4 < 0.0d) {
            d4 = -d4;
        }
        if (d4 > d) {
            return false;
        }
        double d5 = this.m10 - matrix3d.m10;
        if (d5 < 0.0d) {
            d5 = -d5;
        }
        if (d5 > d) {
            return false;
        }
        double d6 = this.m11 - matrix3d.m11;
        if (d6 < 0.0d) {
            d6 = -d6;
        }
        if (d6 > d) {
            return false;
        }
        double d7 = this.m12 - matrix3d.m12;
        if (d7 < 0.0d) {
            d7 = -d7;
        }
        if (d7 > d) {
            return false;
        }
        double d8 = this.m20 - matrix3d.m20;
        if (d8 < 0.0d) {
            d8 = -d8;
        }
        if (d8 > d) {
            return false;
        }
        double d9 = this.m21 - matrix3d.m21;
        if (d9 < 0.0d) {
            d9 = -d9;
        }
        if (d9 > d) {
            return false;
        }
        double d10 = this.m22 - matrix3d.m22;
        if (d10 < 0.0d) {
            d10 = -d10;
        }
        return d10 <= d;
    }

    public int hashCode() {
        long jDoubleToLongBits = ((((((((((((((((Double.doubleToLongBits(this.m00) + 31) * 31) + Double.doubleToLongBits(this.m01)) * 31) + Double.doubleToLongBits(this.m02)) * 31) + Double.doubleToLongBits(this.m10)) * 31) + Double.doubleToLongBits(this.m11)) * 31) + Double.doubleToLongBits(this.m12)) * 31) + Double.doubleToLongBits(this.m20)) * 31) + Double.doubleToLongBits(this.m21)) * 31) + Double.doubleToLongBits(this.m22);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >> 32));
    }

    public final void negate(Matrix3d matrix3d) {
        this.m00 = -matrix3d.m00;
        this.m01 = -matrix3d.m01;
        this.m02 = -matrix3d.m02;
        this.m10 = -matrix3d.m10;
        this.m11 = -matrix3d.m11;
        this.m12 = -matrix3d.m12;
        this.m20 = -matrix3d.m20;
        this.m21 = -matrix3d.m21;
        this.m22 = -matrix3d.m22;
    }

    public final void transform(Tuple3d tuple3d) {
        tuple3d.set((this.m00 * tuple3d.x) + (this.m01 * tuple3d.y) + (this.m02 * tuple3d.z), (this.m10 * tuple3d.x) + (this.m11 * tuple3d.y) + (this.m12 * tuple3d.z), (this.m20 * tuple3d.x) + (this.m21 * tuple3d.y) + (this.m22 * tuple3d.z));
    }

    public final void transform(Tuple3d tuple3d, Tuple3d tuple3d2) {
        double d = (this.m00 * tuple3d.x) + (this.m01 * tuple3d.y) + (this.m02 * tuple3d.z);
        double d2 = (this.m10 * tuple3d.x) + (this.m11 * tuple3d.y) + (this.m12 * tuple3d.z);
        tuple3d2.z = (this.m20 * tuple3d.x) + (this.m21 * tuple3d.y) + (this.m22 * tuple3d.z);
        tuple3d2.x = d;
        tuple3d2.y = d2;
    }

    final void getScaleRotate(double[] dArr, double[] dArr2) {
        compute_svd(new double[]{this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22}, dArr, dArr2);
    }

    public Object clone() {
        try {
            return (Matrix3d) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
