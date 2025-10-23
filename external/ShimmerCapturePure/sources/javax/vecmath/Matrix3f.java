package javax.vecmath;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class Matrix3f implements Serializable, Cloneable {
    static final long serialVersionUID = 329697160112089834L;
    private static final double EPS = 1.0E-8d;
    public float m00;
    public float m01;
    public float m02;
    public float m10;
    public float m11;
    public float m12;
    public float m20;
    public float m21;
    public float m22;

    public Matrix3f(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.m00 = f;
        this.m01 = f2;
        this.m02 = f3;
        this.m10 = f4;
        this.m11 = f5;
        this.m12 = f6;
        this.m20 = f7;
        this.m21 = f8;
        this.m22 = f9;
    }

    public Matrix3f(float[] fArr) {
        this.m00 = fArr[0];
        this.m01 = fArr[1];
        this.m02 = fArr[2];
        this.m10 = fArr[3];
        this.m11 = fArr[4];
        this.m12 = fArr[5];
        this.m20 = fArr[6];
        this.m21 = fArr[7];
        this.m22 = fArr[8];
    }

    public Matrix3f(Matrix3d matrix3d) {
        this.m00 = (float) matrix3d.m00;
        this.m01 = (float) matrix3d.m01;
        this.m02 = (float) matrix3d.m02;
        this.m10 = (float) matrix3d.m10;
        this.m11 = (float) matrix3d.m11;
        this.m12 = (float) matrix3d.m12;
        this.m20 = (float) matrix3d.m20;
        this.m21 = (float) matrix3d.m21;
        this.m22 = (float) matrix3d.m22;
    }

    public Matrix3f(Matrix3f matrix3f) {
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

    public Matrix3f() {
        this.m00 = 0.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 0.0f;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 0.0f;
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
                        throw new RuntimeException(VecMathI18N.getString("Matrix3f13"));
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

    public final void add(float f) {
        this.m00 += f;
        this.m01 += f;
        this.m02 += f;
        this.m10 += f;
        this.m11 += f;
        this.m12 += f;
        this.m20 += f;
        this.m21 += f;
        this.m22 += f;
    }

    public final float determinant() {
        float f = this.m00;
        float f2 = this.m11;
        float f3 = this.m22;
        float f4 = this.m12;
        float f5 = this.m21;
        float f6 = f * ((f2 * f3) - (f4 * f5));
        float f7 = this.m01;
        float f8 = this.m20;
        float f9 = this.m10;
        return f6 + (f7 * ((f4 * f8) - (f3 * f9))) + (this.m02 * ((f9 * f5) - (f2 * f8)));
    }

    public final void mul(float f) {
        this.m00 *= f;
        this.m01 *= f;
        this.m02 *= f;
        this.m10 *= f;
        this.m11 *= f;
        this.m12 *= f;
        this.m20 *= f;
        this.m21 *= f;
        this.m22 *= f;
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

    public final void set(float f) {
        this.m00 = f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = f;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = f;
    }

    public final void setIdentity() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
    }

    public final void setZero() {
        this.m00 = 0.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 0.0f;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 0.0f;
    }

    public final void transpose() {
        float f = this.m10;
        this.m10 = this.m01;
        this.m01 = f;
        float f2 = this.m20;
        this.m20 = this.m02;
        this.m02 = f2;
        float f3 = this.m21;
        this.m21 = this.m12;
        this.m12 = f3;
    }

    public String toString() {
        return new StringBuffer().append(this.m00).append(", ").append(this.m01).append(", ").append(this.m02).append(StringUtils.LF).append(this.m10).append(", ").append(this.m11).append(", ").append(this.m12).append(StringUtils.LF).append(this.m20).append(", ").append(this.m21).append(", ").append(this.m22).append(StringUtils.LF).toString();
    }

    public final void setElement(int i, int i2, float f) {
        if (i == 0) {
            if (i2 == 0) {
                this.m00 = f;
                return;
            } else if (i2 == 1) {
                this.m01 = f;
                return;
            } else {
                if (i2 != 2) {
                    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
                }
                this.m02 = f;
                return;
            }
        }
        if (i == 1) {
            if (i2 == 0) {
                this.m10 = f;
                return;
            } else if (i2 == 1) {
                this.m11 = f;
                return;
            } else {
                if (i2 != 2) {
                    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
                }
                this.m12 = f;
                return;
            }
        }
        if (i != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
        }
        if (i2 == 0) {
            this.m20 = f;
        } else if (i2 == 1) {
            this.m21 = f;
        } else {
            if (i2 != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
            }
            this.m22 = f;
        }
    }

    public final void getRow(int i, Vector3f vector3f) {
        if (i == 0) {
            vector3f.x = this.m00;
            vector3f.y = this.m01;
            vector3f.z = this.m02;
        } else if (i == 1) {
            vector3f.x = this.m10;
            vector3f.y = this.m11;
            vector3f.z = this.m12;
        } else {
            if (i == 2) {
                vector3f.x = this.m20;
                vector3f.y = this.m21;
                vector3f.z = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
        }
    }

    public final void getRow(int i, float[] fArr) {
        if (i == 0) {
            fArr[0] = this.m00;
            fArr[1] = this.m01;
            fArr[2] = this.m02;
        } else if (i == 1) {
            fArr[0] = this.m10;
            fArr[1] = this.m11;
            fArr[2] = this.m12;
        } else {
            if (i == 2) {
                fArr[0] = this.m20;
                fArr[1] = this.m21;
                fArr[2] = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
        }
    }

    public final void getColumn(int i, Vector3f vector3f) {
        if (i == 0) {
            vector3f.x = this.m00;
            vector3f.y = this.m10;
            vector3f.z = this.m20;
        } else if (i == 1) {
            vector3f.x = this.m01;
            vector3f.y = this.m11;
            vector3f.z = this.m21;
        } else {
            if (i == 2) {
                vector3f.x = this.m02;
                vector3f.y = this.m12;
                vector3f.z = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
        }
    }

    public final void getColumn(int i, float[] fArr) {
        if (i == 0) {
            fArr[0] = this.m00;
            fArr[1] = this.m10;
            fArr[2] = this.m20;
        } else if (i == 1) {
            fArr[0] = this.m01;
            fArr[1] = this.m11;
            fArr[2] = this.m21;
        } else {
            if (i == 2) {
                fArr[0] = this.m02;
                fArr[1] = this.m12;
                fArr[2] = this.m22;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
        }
    }

    public final float getElement(int i, int i2) {
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
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f5"));
    }

    public final void setRow(int i, float f, float f2, float f3) {
        if (i == 0) {
            this.m00 = f;
            this.m01 = f2;
            this.m02 = f3;
        } else if (i == 1) {
            this.m10 = f;
            this.m11 = f2;
            this.m12 = f3;
        } else {
            if (i != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
            }
            this.m20 = f;
            this.m21 = f2;
            this.m22 = f3;
        }
    }

    public final void setRow(int i, Vector3f vector3f) {
        if (i == 0) {
            this.m00 = vector3f.x;
            this.m01 = vector3f.y;
            this.m02 = vector3f.z;
        } else if (i == 1) {
            this.m10 = vector3f.x;
            this.m11 = vector3f.y;
            this.m12 = vector3f.z;
        } else {
            if (i == 2) {
                this.m20 = vector3f.x;
                this.m21 = vector3f.y;
                this.m22 = vector3f.z;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
        }
    }

    public final void setRow(int i, float[] fArr) {
        if (i == 0) {
            this.m00 = fArr[0];
            this.m01 = fArr[1];
            this.m02 = fArr[2];
        } else if (i == 1) {
            this.m10 = fArr[0];
            this.m11 = fArr[1];
            this.m12 = fArr[2];
        } else {
            if (i == 2) {
                this.m20 = fArr[0];
                this.m21 = fArr[1];
                this.m22 = fArr[2];
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
        }
    }

    public final void setColumn(int i, float f, float f2, float f3) {
        if (i == 0) {
            this.m00 = f;
            this.m10 = f2;
            this.m20 = f3;
        } else if (i == 1) {
            this.m01 = f;
            this.m11 = f2;
            this.m21 = f3;
        } else {
            if (i != 2) {
                throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
            }
            this.m02 = f;
            this.m12 = f2;
            this.m22 = f3;
        }
    }

    public final void setColumn(int i, Vector3f vector3f) {
        if (i == 0) {
            this.m00 = vector3f.x;
            this.m10 = vector3f.y;
            this.m20 = vector3f.z;
        } else if (i == 1) {
            this.m01 = vector3f.x;
            this.m11 = vector3f.y;
            this.m21 = vector3f.z;
        } else {
            if (i == 2) {
                this.m02 = vector3f.x;
                this.m12 = vector3f.y;
                this.m22 = vector3f.z;
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
        }
    }

    public final void setColumn(int i, float[] fArr) {
        if (i == 0) {
            this.m00 = fArr[0];
            this.m10 = fArr[1];
            this.m20 = fArr[2];
        } else if (i == 1) {
            this.m01 = fArr[0];
            this.m11 = fArr[1];
            this.m21 = fArr[2];
        } else {
            if (i == 2) {
                this.m02 = fArr[0];
                this.m12 = fArr[1];
                this.m22 = fArr[2];
                return;
            }
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
        }
    }

    public final float getScale() {
        double[] dArr = new double[3];
        getScaleRotate(dArr, new double[9]);
        return (float) Matrix3d.max3(dArr);
    }

    public final void setScale(float f) {
        double[] dArr = new double[9];
        getScaleRotate(new double[3], dArr);
        double d = f;
        this.m00 = (float) (dArr[0] * d);
        this.m01 = (float) (dArr[1] * d);
        this.m02 = (float) (dArr[2] * d);
        this.m10 = (float) (dArr[3] * d);
        this.m11 = (float) (dArr[4] * d);
        this.m12 = (float) (dArr[5] * d);
        this.m20 = (float) (dArr[6] * d);
        this.m21 = (float) (dArr[7] * d);
        this.m22 = (float) (dArr[8] * d);
    }

    public final void add(float f, Matrix3f matrix3f) {
        this.m00 = matrix3f.m00 + f;
        this.m01 = matrix3f.m01 + f;
        this.m02 = matrix3f.m02 + f;
        this.m10 = matrix3f.m10 + f;
        this.m11 = matrix3f.m11 + f;
        this.m12 = matrix3f.m12 + f;
        this.m20 = matrix3f.m20 + f;
        this.m21 = matrix3f.m21 + f;
        this.m22 = matrix3f.m22 + f;
    }

    public final void add(Matrix3f matrix3f, Matrix3f matrix3f2) {
        this.m00 = matrix3f.m00 + matrix3f2.m00;
        this.m01 = matrix3f.m01 + matrix3f2.m01;
        this.m02 = matrix3f.m02 + matrix3f2.m02;
        this.m10 = matrix3f.m10 + matrix3f2.m10;
        this.m11 = matrix3f.m11 + matrix3f2.m11;
        this.m12 = matrix3f.m12 + matrix3f2.m12;
        this.m20 = matrix3f.m20 + matrix3f2.m20;
        this.m21 = matrix3f.m21 + matrix3f2.m21;
        this.m22 = matrix3f.m22 + matrix3f2.m22;
    }

    public final void add(Matrix3f matrix3f) {
        this.m00 += matrix3f.m00;
        this.m01 += matrix3f.m01;
        this.m02 += matrix3f.m02;
        this.m10 += matrix3f.m10;
        this.m11 += matrix3f.m11;
        this.m12 += matrix3f.m12;
        this.m20 += matrix3f.m20;
        this.m21 += matrix3f.m21;
        this.m22 += matrix3f.m22;
    }

    public final void sub(Matrix3f matrix3f, Matrix3f matrix3f2) {
        this.m00 = matrix3f.m00 - matrix3f2.m00;
        this.m01 = matrix3f.m01 - matrix3f2.m01;
        this.m02 = matrix3f.m02 - matrix3f2.m02;
        this.m10 = matrix3f.m10 - matrix3f2.m10;
        this.m11 = matrix3f.m11 - matrix3f2.m11;
        this.m12 = matrix3f.m12 - matrix3f2.m12;
        this.m20 = matrix3f.m20 - matrix3f2.m20;
        this.m21 = matrix3f.m21 - matrix3f2.m21;
        this.m22 = matrix3f.m22 - matrix3f2.m22;
    }

    public final void sub(Matrix3f matrix3f) {
        this.m00 -= matrix3f.m00;
        this.m01 -= matrix3f.m01;
        this.m02 -= matrix3f.m02;
        this.m10 -= matrix3f.m10;
        this.m11 -= matrix3f.m11;
        this.m12 -= matrix3f.m12;
        this.m20 -= matrix3f.m20;
        this.m21 -= matrix3f.m21;
        this.m22 -= matrix3f.m22;
    }

    public final void transpose(Matrix3f matrix3f) {
        if (this != matrix3f) {
            this.m00 = matrix3f.m00;
            this.m01 = matrix3f.m10;
            this.m02 = matrix3f.m20;
            this.m10 = matrix3f.m01;
            this.m11 = matrix3f.m11;
            this.m12 = matrix3f.m21;
            this.m20 = matrix3f.m02;
            this.m21 = matrix3f.m12;
            this.m22 = matrix3f.m22;
            return;
        }
        transpose();
    }

    public final void set(Quat4f quat4f) {
        this.m00 = (1.0f - ((quat4f.y * 2.0f) * quat4f.y)) - ((quat4f.z * 2.0f) * quat4f.z);
        this.m10 = ((quat4f.x * quat4f.y) + (quat4f.w * quat4f.z)) * 2.0f;
        this.m20 = ((quat4f.x * quat4f.z) - (quat4f.w * quat4f.y)) * 2.0f;
        this.m01 = ((quat4f.x * quat4f.y) - (quat4f.w * quat4f.z)) * 2.0f;
        this.m11 = (1.0f - ((quat4f.x * 2.0f) * quat4f.x)) - ((quat4f.z * 2.0f) * quat4f.z);
        this.m21 = ((quat4f.y * quat4f.z) + (quat4f.w * quat4f.x)) * 2.0f;
        this.m02 = ((quat4f.x * quat4f.z) + (quat4f.w * quat4f.y)) * 2.0f;
        this.m12 = ((quat4f.y * quat4f.z) - (quat4f.w * quat4f.x)) * 2.0f;
        this.m22 = (1.0f - ((quat4f.x * 2.0f) * quat4f.x)) - ((quat4f.y * 2.0f) * quat4f.y);
    }

    public final void set(AxisAngle4f axisAngle4f) {
        float fSqrt = (float) Math.sqrt((axisAngle4f.x * axisAngle4f.x) + (axisAngle4f.y * axisAngle4f.y) + (axisAngle4f.z * axisAngle4f.z));
        if (fSqrt < 1.0E-8d) {
            this.m00 = 1.0f;
            this.m01 = 0.0f;
            this.m02 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = 1.0f;
            this.m12 = 0.0f;
            this.m20 = 0.0f;
            this.m21 = 0.0f;
            this.m22 = 1.0f;
            return;
        }
        float f = 1.0f / fSqrt;
        float f2 = axisAngle4f.x * f;
        float f3 = axisAngle4f.y * f;
        float f4 = axisAngle4f.z * f;
        float fSin = (float) Math.sin(axisAngle4f.angle);
        float fCos = (float) Math.cos(axisAngle4f.angle);
        float f5 = 1.0f - fCos;
        this.m00 = (f5 * f2 * f2) + fCos;
        float f6 = f2 * f3 * f5;
        float f7 = fSin * f4;
        this.m01 = f6 - f7;
        float f8 = f2 * f4 * f5;
        float f9 = fSin * f3;
        this.m02 = f8 + f9;
        this.m10 = f6 + f7;
        this.m11 = (f5 * f3 * f3) + fCos;
        float f10 = f3 * f4 * f5;
        float f11 = fSin * f2;
        this.m12 = f10 - f11;
        this.m20 = f8 - f9;
        this.m21 = f10 + f11;
        this.m22 = (f5 * f4 * f4) + fCos;
    }

    public final void set(AxisAngle4d axisAngle4d) {
        double dSqrt = Math.sqrt((axisAngle4d.x * axisAngle4d.x) + (axisAngle4d.y * axisAngle4d.y) + (axisAngle4d.z * axisAngle4d.z));
        if (dSqrt < 1.0E-8d) {
            this.m00 = 1.0f;
            this.m01 = 0.0f;
            this.m02 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = 1.0f;
            this.m12 = 0.0f;
            this.m20 = 0.0f;
            this.m21 = 0.0f;
            this.m22 = 1.0f;
            return;
        }
        double d = 1.0d / dSqrt;
        double d2 = axisAngle4d.x * d;
        double d3 = axisAngle4d.y * d;
        double d4 = axisAngle4d.z * d;
        double dSin = Math.sin(axisAngle4d.angle);
        double dCos = Math.cos(axisAngle4d.angle);
        double d5 = 1.0d - dCos;
        double d6 = d2 * d4;
        double d7 = d3 * d4;
        this.m00 = (float) ((d5 * d2 * d2) + dCos);
        double d8 = d2 * d3 * d5;
        double d9 = dSin * d4;
        this.m01 = (float) (d8 - d9);
        double d10 = d6 * d5;
        double d11 = dSin * d3;
        this.m02 = (float) (d10 + d11);
        this.m10 = (float) (d8 + d9);
        this.m11 = (float) ((d5 * d3 * d3) + dCos);
        double d12 = d7 * d5;
        double d13 = dSin * d2;
        this.m12 = (float) (d12 - d13);
        this.m20 = (float) (d10 - d11);
        this.m21 = (float) (d12 + d13);
        this.m22 = (float) ((d5 * d4 * d4) + dCos);
    }

    public final void set(Quat4d quat4d) {
        this.m00 = (float) ((1.0d - ((quat4d.y * 2.0d) * quat4d.y)) - ((quat4d.z * 2.0d) * quat4d.z));
        this.m10 = (float) (((quat4d.x * quat4d.y) + (quat4d.w * quat4d.z)) * 2.0d);
        this.m20 = (float) (((quat4d.x * quat4d.z) - (quat4d.w * quat4d.y)) * 2.0d);
        this.m01 = (float) (((quat4d.x * quat4d.y) - (quat4d.w * quat4d.z)) * 2.0d);
        this.m11 = (float) ((1.0d - ((quat4d.x * 2.0d) * quat4d.x)) - ((quat4d.z * 2.0d) * quat4d.z));
        this.m21 = (float) (((quat4d.y * quat4d.z) + (quat4d.w * quat4d.x)) * 2.0d);
        this.m02 = (float) (((quat4d.x * quat4d.z) + (quat4d.w * quat4d.y)) * 2.0d);
        this.m12 = (float) (((quat4d.y * quat4d.z) - (quat4d.w * quat4d.x)) * 2.0d);
        this.m22 = (float) ((1.0d - ((quat4d.x * 2.0d) * quat4d.x)) - ((quat4d.y * 2.0d) * quat4d.y));
    }

    public final void set(float[] fArr) {
        this.m00 = fArr[0];
        this.m01 = fArr[1];
        this.m02 = fArr[2];
        this.m10 = fArr[3];
        this.m11 = fArr[4];
        this.m12 = fArr[5];
        this.m20 = fArr[6];
        this.m21 = fArr[7];
        this.m22 = fArr[8];
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
        this.m00 = (float) matrix3d.m00;
        this.m01 = (float) matrix3d.m01;
        this.m02 = (float) matrix3d.m02;
        this.m10 = (float) matrix3d.m10;
        this.m11 = (float) matrix3d.m11;
        this.m12 = (float) matrix3d.m12;
        this.m20 = (float) matrix3d.m20;
        this.m21 = (float) matrix3d.m21;
        this.m22 = (float) matrix3d.m22;
    }

    public final void invert(Matrix3f matrix3f) {
        invertGeneral(matrix3f);
    }

    public final void invert() {
        invertGeneral(this);
    }

    private final void invertGeneral(Matrix3f matrix3f) {
        double[] dArr = new double[9];
        int[] iArr = new int[3];
        double[] dArr2 = {matrix3f.m00, matrix3f.m01, matrix3f.m02, matrix3f.m10, matrix3f.m11, matrix3f.m12, matrix3f.m20, matrix3f.m21, matrix3f.m22};
        if (!luDecomposition(dArr2, iArr)) {
            throw new SingularMatrixException(VecMathI18N.getString("Matrix3f12"));
        }
        for (int i = 0; i < 9; i++) {
            dArr[i] = 0.0d;
        }
        dArr[0] = 1.0d;
        dArr[4] = 1.0d;
        dArr[8] = 1.0d;
        luBacksubstitution(dArr2, iArr, dArr);
        this.m00 = (float) dArr[0];
        this.m01 = (float) dArr[1];
        this.m02 = (float) dArr[2];
        this.m10 = (float) dArr[3];
        this.m11 = (float) dArr[4];
        this.m12 = (float) dArr[5];
        this.m20 = (float) dArr[6];
        this.m21 = (float) dArr[7];
        this.m22 = (float) dArr[8];
    }

    public final void rotX(float f) {
        double d = f;
        float fSin = (float) Math.sin(d);
        float fCos = (float) Math.cos(d);
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = fCos;
        this.m12 = -fSin;
        this.m20 = 0.0f;
        this.m21 = fSin;
        this.m22 = fCos;
    }

    public final void rotY(float f) {
        double d = f;
        float fSin = (float) Math.sin(d);
        float fCos = (float) Math.cos(d);
        this.m00 = fCos;
        this.m01 = 0.0f;
        this.m02 = fSin;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m20 = -fSin;
        this.m21 = 0.0f;
        this.m22 = fCos;
    }

    public final void rotZ(float f) {
        double d = f;
        float fSin = (float) Math.sin(d);
        float fCos = (float) Math.cos(d);
        this.m00 = fCos;
        this.m01 = -fSin;
        this.m02 = 0.0f;
        this.m10 = fSin;
        this.m11 = fCos;
        this.m12 = 0.0f;
        this.m20 = 0.0f;
        this.m21 = 0.0f;
        this.m22 = 1.0f;
    }

    public final void mul(float f, Matrix3f matrix3f) {
        this.m00 = matrix3f.m00 * f;
        this.m01 = matrix3f.m01 * f;
        this.m02 = matrix3f.m02 * f;
        this.m10 = matrix3f.m10 * f;
        this.m11 = matrix3f.m11 * f;
        this.m12 = matrix3f.m12 * f;
        this.m20 = matrix3f.m20 * f;
        this.m21 = matrix3f.m21 * f;
        this.m22 = f * matrix3f.m22;
    }

    public final void mul(Matrix3f matrix3f) {
        float f = this.m00;
        float f2 = matrix3f.m00;
        float f3 = this.m01;
        float f4 = matrix3f.m10;
        float f5 = this.m02;
        float f6 = matrix3f.m20;
        float f7 = (f * f2) + (f3 * f4) + (f5 * f6);
        float f8 = matrix3f.m01;
        float f9 = matrix3f.m11;
        float f10 = matrix3f.m21;
        float f11 = (f * f8) + (f3 * f9) + (f5 * f10);
        float f12 = matrix3f.m02;
        float f13 = matrix3f.m12;
        float f14 = matrix3f.m22;
        float f15 = (f * f12) + (f3 * f13) + (f5 * f14);
        float f16 = this.m10;
        float f17 = this.m11;
        float f18 = this.m12;
        float f19 = (f16 * f2) + (f17 * f4) + (f18 * f6);
        float f20 = (f16 * f8) + (f17 * f9) + (f18 * f10);
        float f21 = (f16 * f12) + (f17 * f13) + (f18 * f14);
        float f22 = this.m20;
        float f23 = this.m21;
        float f24 = (f2 * f22) + (f4 * f23);
        float f25 = this.m22;
        this.m00 = f7;
        this.m01 = f11;
        this.m02 = f15;
        this.m10 = f19;
        this.m11 = f20;
        this.m12 = f21;
        this.m20 = f24 + (f6 * f25);
        this.m21 = (f8 * f22) + (f9 * f23) + (f10 * f25);
        this.m22 = (f22 * f12) + (f23 * f13) + (f25 * f14);
    }

    public final void mul(Matrix3f matrix3f, Matrix3f matrix3f2) {
        if (this != matrix3f && this != matrix3f2) {
            float f = matrix3f.m00 * matrix3f2.m00;
            float f2 = matrix3f.m01;
            float f3 = matrix3f2.m10;
            float f4 = matrix3f.m02;
            float f5 = matrix3f2.m20;
            this.m00 = f + (f2 * f3) + (f4 * f5);
            float f6 = matrix3f.m00;
            float f7 = matrix3f2.m01 * f6;
            float f8 = matrix3f2.m11;
            float f9 = f7 + (f2 * f8);
            float f10 = matrix3f2.m21;
            this.m01 = f9 + (f4 * f10);
            float f11 = f6 * matrix3f2.m02;
            float f12 = matrix3f.m01;
            float f13 = matrix3f2.m12;
            float f14 = f11 + (f12 * f13);
            float f15 = matrix3f2.m22;
            this.m02 = f14 + (f4 * f15);
            float f16 = matrix3f.m10;
            float f17 = matrix3f2.m00;
            float f18 = matrix3f.m11;
            float f19 = (f16 * f17) + (f3 * f18);
            float f20 = matrix3f.m12;
            this.m10 = f19 + (f20 * f5);
            float f21 = matrix3f.m10;
            float f22 = matrix3f2.m01;
            this.m11 = (f21 * f22) + (f18 * f8) + (f20 * f10);
            float f23 = matrix3f2.m02;
            this.m12 = (f21 * f23) + (matrix3f.m11 * f13) + (f20 * f15);
            float f24 = matrix3f.m20 * f17;
            float f25 = matrix3f.m21;
            float f26 = f24 + (matrix3f2.m10 * f25);
            float f27 = matrix3f.m22;
            this.m20 = f26 + (f5 * f27);
            float f28 = matrix3f.m20;
            this.m21 = (f22 * f28) + (f25 * matrix3f2.m11) + (f10 * f27);
            this.m22 = (f28 * f23) + (matrix3f.m21 * matrix3f2.m12) + (f27 * f15);
            return;
        }
        float f29 = matrix3f.m00;
        float f30 = matrix3f2.m00;
        float f31 = matrix3f.m01;
        float f32 = matrix3f2.m10;
        float f33 = matrix3f.m02;
        float f34 = matrix3f2.m20;
        float f35 = (f29 * f30) + (f31 * f32) + (f33 * f34);
        float f36 = matrix3f2.m01;
        float f37 = matrix3f2.m11;
        float f38 = matrix3f2.m21;
        float f39 = (f29 * f36) + (f31 * f37) + (f33 * f38);
        float f40 = matrix3f2.m02;
        float f41 = matrix3f2.m12;
        float f42 = matrix3f2.m22;
        float f43 = (f29 * f40) + (f31 * f41) + (f33 * f42);
        float f44 = matrix3f.m10;
        float f45 = matrix3f.m11;
        float f46 = matrix3f.m12;
        float f47 = (f44 * f30) + (f45 * f32) + (f46 * f34);
        float f48 = (f44 * f36) + (f45 * f37) + (f46 * f38);
        float f49 = (f44 * f40) + (f45 * f41) + (f46 * f42);
        float f50 = matrix3f.m20;
        float f51 = matrix3f.m21;
        float f52 = matrix3f.m22;
        this.m00 = f35;
        this.m01 = f39;
        this.m02 = f43;
        this.m10 = f47;
        this.m11 = f48;
        this.m12 = f49;
        this.m20 = (f30 * f50) + (f32 * f51) + (f34 * f52);
        this.m21 = (f36 * f50) + (f37 * f51) + (f38 * f52);
        this.m22 = (f50 * f40) + (f51 * f41) + (f52 * f42);
    }

    public final void mulNormalize(Matrix3f matrix3f) {
        double[] dArr = new double[9];
        float f = this.m00;
        float f2 = matrix3f.m00;
        float f3 = this.m01;
        float f4 = matrix3f.m10;
        float f5 = (f * f2) + (f3 * f4);
        float f6 = this.m02;
        float f7 = matrix3f.m20;
        float f8 = matrix3f.m01;
        float f9 = matrix3f.m11;
        float f10 = matrix3f.m21;
        float f11 = matrix3f.m02;
        float f12 = matrix3f.m12;
        float f13 = matrix3f.m22;
        float f14 = this.m10;
        float f15 = this.m11;
        float f16 = this.m12;
        float f17 = (f2 * this.m20) + (f4 * this.m21);
        float f18 = this.m22;
        Matrix3d.compute_svd(new double[]{f5 + (f6 * f7), (f * f8) + (f3 * f9) + (f6 * f10), (f * f11) + (f3 * f12) + (f6 * f13), (f14 * f2) + (f15 * f4) + (f16 * f7), (f14 * f8) + (f15 * f9) + (f16 * f10), (f14 * f11) + (f15 * f12) + (f16 * f13), f17 + (f7 * f18), (f8 * r1) + (r11 * f9) + (f10 * f18), (r1 * f11) + (r11 * f12) + (f18 * f13)}, new double[3], dArr);
        this.m00 = (float) dArr[0];
        this.m01 = (float) dArr[1];
        this.m02 = (float) dArr[2];
        this.m10 = (float) dArr[3];
        this.m11 = (float) dArr[4];
        this.m12 = (float) dArr[5];
        this.m20 = (float) dArr[6];
        this.m21 = (float) dArr[7];
        this.m22 = (float) dArr[8];
    }

    public final void mulNormalize(Matrix3f matrix3f, Matrix3f matrix3f2) {
        double[] dArr = new double[9];
        float f = (matrix3f.m00 * matrix3f2.m00) + (matrix3f.m01 * matrix3f2.m10);
        float f2 = matrix3f.m02;
        float f3 = matrix3f2.m20;
        float f4 = matrix3f2.m01;
        float f5 = matrix3f2.m11;
        float f6 = matrix3f2.m21;
        float f7 = matrix3f2.m02;
        float f8 = matrix3f2.m12;
        float f9 = matrix3f2.m22;
        float f10 = matrix3f.m10;
        float f11 = matrix3f.m11;
        float f12 = matrix3f.m12;
        float f13 = matrix3f.m20;
        float f14 = matrix3f.m21;
        float f15 = matrix3f.m22;
        Matrix3d.compute_svd(new double[]{f + (f2 * f3), (r7 * f4) + (r10 * f5) + (f2 * f6), (r7 * f7) + (r10 * f8) + (f2 * f9), (f10 * r8) + (f11 * r11) + (f12 * f3), (f10 * f4) + (f11 * f5) + (f12 * f6), (f10 * f7) + (f11 * f8) + (f12 * f9), (r8 * f13) + (f14 * r11) + (f3 * f15), (f4 * f13) + (f5 * f14) + (f15 * f6), (f13 * f7) + (f14 * f8) + (f15 * f9)}, new double[3], dArr);
        this.m00 = (float) dArr[0];
        this.m01 = (float) dArr[1];
        this.m02 = (float) dArr[2];
        this.m10 = (float) dArr[3];
        this.m11 = (float) dArr[4];
        this.m12 = (float) dArr[5];
        this.m20 = (float) dArr[6];
        this.m21 = (float) dArr[7];
        this.m22 = (float) dArr[8];
    }

    public final void mulTransposeBoth(Matrix3f matrix3f, Matrix3f matrix3f2) {
        if (this != matrix3f && this != matrix3f2) {
            float f = matrix3f.m00 * matrix3f2.m00;
            float f2 = matrix3f.m10;
            float f3 = f + (matrix3f2.m01 * f2);
            float f4 = matrix3f.m20;
            this.m00 = f3 + (matrix3f2.m02 * f4);
            float f5 = matrix3f.m00;
            float f6 = matrix3f2.m10 * f5;
            float f7 = matrix3f2.m11;
            float f8 = matrix3f2.m12;
            this.m01 = f6 + (f2 * f7) + (f4 * f8);
            float f9 = matrix3f2.m20;
            float f10 = matrix3f2.m21;
            float f11 = (f5 * f9) + (f2 * f10);
            float f12 = matrix3f2.m22;
            this.m02 = f11 + (f4 * f12);
            float f13 = matrix3f.m01;
            float f14 = matrix3f2.m00;
            float f15 = matrix3f.m11;
            float f16 = matrix3f2.m01;
            float f17 = matrix3f.m21;
            float f18 = matrix3f2.m02;
            this.m10 = (f13 * f14) + (f15 * f16) + (f17 * f18);
            float f19 = matrix3f2.m10;
            this.m11 = (f13 * f19) + (f15 * f7) + (f8 * f17);
            this.m12 = (f13 * f9) + (matrix3f.m11 * f10) + (f17 * f12);
            float f20 = matrix3f.m02;
            float f21 = matrix3f.m12;
            float f22 = matrix3f.m22;
            this.m20 = (f14 * f20) + (f16 * f21) + (f18 * f22);
            this.m21 = (f19 * f20) + (matrix3f2.m11 * f21) + (matrix3f2.m12 * f22);
            this.m22 = (f20 * matrix3f2.m20) + (f21 * matrix3f2.m21) + (f22 * f12);
            return;
        }
        float f23 = matrix3f.m00;
        float f24 = matrix3f2.m00;
        float f25 = matrix3f.m10;
        float f26 = matrix3f2.m01;
        float f27 = matrix3f.m20;
        float f28 = matrix3f2.m02;
        float f29 = (f23 * f24) + (f25 * f26) + (f27 * f28);
        float f30 = matrix3f2.m10;
        float f31 = matrix3f2.m11;
        float f32 = matrix3f2.m12;
        float f33 = (f23 * f30) + (f25 * f31) + (f27 * f32);
        float f34 = matrix3f2.m20;
        float f35 = matrix3f2.m21;
        float f36 = matrix3f2.m22;
        float f37 = (f23 * f34) + (f25 * f35) + (f27 * f36);
        float f38 = matrix3f.m01;
        float f39 = matrix3f.m11;
        float f40 = matrix3f.m21;
        float f41 = (f38 * f24) + (f39 * f26) + (f40 * f28);
        float f42 = (f38 * f30) + (f39 * f31) + (f40 * f32);
        float f43 = (f38 * f34) + (f39 * f35) + (f40 * f36);
        float f44 = matrix3f.m02;
        float f45 = matrix3f.m12;
        float f46 = matrix3f.m22;
        this.m00 = f29;
        this.m01 = f33;
        this.m02 = f37;
        this.m10 = f41;
        this.m11 = f42;
        this.m12 = f43;
        this.m20 = (f24 * f44) + (f26 * f45) + (f28 * f46);
        this.m21 = (f30 * f44) + (f31 * f45) + (f32 * f46);
        this.m22 = (f44 * f34) + (f45 * f35) + (f46 * f36);
    }

    public final void mulTransposeRight(Matrix3f matrix3f, Matrix3f matrix3f2) {
        if (this != matrix3f && this != matrix3f2) {
            float f = matrix3f.m00 * matrix3f2.m00;
            float f2 = matrix3f.m01;
            float f3 = f + (matrix3f2.m01 * f2);
            float f4 = matrix3f.m02;
            this.m00 = f3 + (matrix3f2.m02 * f4);
            float f5 = matrix3f.m00;
            float f6 = matrix3f2.m10 * f5;
            float f7 = matrix3f2.m11;
            float f8 = f6 + (f2 * f7);
            float f9 = matrix3f2.m12;
            this.m01 = f8 + (f4 * f9);
            float f10 = matrix3f2.m20;
            float f11 = matrix3f.m01;
            float f12 = matrix3f2.m21;
            float f13 = (f5 * f10) + (f11 * f12);
            float f14 = matrix3f2.m22;
            this.m02 = f13 + (f4 * f14);
            float f15 = matrix3f.m10;
            float f16 = matrix3f2.m00;
            float f17 = matrix3f.m11;
            float f18 = matrix3f2.m01;
            float f19 = matrix3f.m12;
            float f20 = matrix3f2.m02;
            this.m10 = (f15 * f16) + (f17 * f18) + (f19 * f20);
            float f21 = matrix3f.m10;
            float f22 = matrix3f2.m10;
            this.m11 = (f21 * f22) + (f17 * f7) + (f9 * f19);
            this.m12 = (f21 * f10) + (matrix3f.m11 * f12) + (f19 * f14);
            float f23 = matrix3f.m20 * f16;
            float f24 = matrix3f.m21;
            float f25 = matrix3f.m22;
            this.m20 = f23 + (f18 * f24) + (f20 * f25);
            float f26 = matrix3f.m20;
            this.m21 = (f22 * f26) + (f24 * matrix3f2.m11) + (matrix3f2.m12 * f25);
            this.m22 = (f26 * matrix3f2.m20) + (matrix3f.m21 * matrix3f2.m21) + (f25 * f14);
            return;
        }
        float f27 = matrix3f.m00;
        float f28 = matrix3f2.m00;
        float f29 = matrix3f.m01;
        float f30 = matrix3f2.m01;
        float f31 = matrix3f.m02;
        float f32 = matrix3f2.m02;
        float f33 = (f27 * f28) + (f29 * f30) + (f31 * f32);
        float f34 = matrix3f2.m10;
        float f35 = matrix3f2.m11;
        float f36 = matrix3f2.m12;
        float f37 = (f27 * f34) + (f29 * f35) + (f31 * f36);
        float f38 = matrix3f2.m20;
        float f39 = matrix3f2.m21;
        float f40 = matrix3f2.m22;
        float f41 = (f27 * f38) + (f29 * f39) + (f31 * f40);
        float f42 = matrix3f.m10;
        float f43 = matrix3f.m11;
        float f44 = matrix3f.m12;
        float f45 = (f42 * f28) + (f43 * f30) + (f44 * f32);
        float f46 = (f42 * f34) + (f43 * f35) + (f44 * f36);
        float f47 = (f42 * f38) + (f43 * f39) + (f44 * f40);
        float f48 = matrix3f.m20;
        float f49 = matrix3f.m21;
        float f50 = matrix3f.m22;
        this.m00 = f33;
        this.m01 = f37;
        this.m02 = f41;
        this.m10 = f45;
        this.m11 = f46;
        this.m12 = f47;
        this.m20 = (f28 * f48) + (f30 * f49) + (f32 * f50);
        this.m21 = (f34 * f48) + (f35 * f49) + (f36 * f50);
        this.m22 = (f48 * f38) + (f49 * f39) + (f50 * f40);
    }

    public final void mulTransposeLeft(Matrix3f matrix3f, Matrix3f matrix3f2) {
        if (this != matrix3f && this != matrix3f2) {
            float f = matrix3f.m00 * matrix3f2.m00;
            float f2 = matrix3f.m10;
            float f3 = matrix3f2.m10;
            float f4 = matrix3f.m20;
            float f5 = matrix3f2.m20;
            this.m00 = f + (f2 * f3) + (f4 * f5);
            float f6 = matrix3f.m00;
            float f7 = matrix3f2.m01 * f6;
            float f8 = matrix3f2.m11;
            float f9 = matrix3f2.m21;
            this.m01 = f7 + (f2 * f8) + (f4 * f9);
            float f10 = f6 * matrix3f2.m02;
            float f11 = matrix3f2.m12;
            float f12 = f10 + (f2 * f11);
            float f13 = matrix3f2.m22;
            this.m02 = f12 + (f4 * f13);
            float f14 = matrix3f.m01;
            float f15 = matrix3f2.m00;
            float f16 = matrix3f.m11;
            float f17 = (f14 * f15) + (f3 * f16);
            float f18 = matrix3f.m21;
            this.m10 = f17 + (f18 * f5);
            float f19 = matrix3f2.m01;
            this.m11 = (f14 * f19) + (f16 * f8) + (f18 * f9);
            float f20 = matrix3f2.m02;
            this.m12 = (f14 * f20) + (matrix3f.m11 * f11) + (f18 * f13);
            float f21 = matrix3f.m02;
            float f22 = matrix3f.m12;
            float f23 = (f15 * f21) + (matrix3f2.m10 * f22);
            float f24 = matrix3f.m22;
            this.m20 = f23 + (f5 * f24);
            this.m21 = (f19 * f21) + (matrix3f2.m11 * f22) + (f9 * f24);
            this.m22 = (f21 * f20) + (f22 * matrix3f2.m12) + (f24 * f13);
            return;
        }
        float f25 = matrix3f.m00;
        float f26 = matrix3f2.m00;
        float f27 = matrix3f.m10;
        float f28 = matrix3f2.m10;
        float f29 = matrix3f.m20;
        float f30 = matrix3f2.m20;
        float f31 = (f25 * f26) + (f27 * f28) + (f29 * f30);
        float f32 = matrix3f2.m01;
        float f33 = matrix3f2.m11;
        float f34 = matrix3f2.m21;
        float f35 = (f25 * f32) + (f27 * f33) + (f29 * f34);
        float f36 = matrix3f2.m02;
        float f37 = matrix3f2.m12;
        float f38 = matrix3f2.m22;
        float f39 = (f25 * f36) + (f27 * f37) + (f29 * f38);
        float f40 = matrix3f.m01;
        float f41 = matrix3f.m11;
        float f42 = matrix3f.m21;
        float f43 = (f40 * f26) + (f41 * f28) + (f42 * f30);
        float f44 = (f40 * f32) + (f41 * f33) + (f42 * f34);
        float f45 = (f40 * f36) + (f41 * f37) + (f42 * f38);
        float f46 = matrix3f.m02;
        float f47 = matrix3f.m12;
        float f48 = matrix3f.m22;
        this.m00 = f31;
        this.m01 = f35;
        this.m02 = f39;
        this.m10 = f43;
        this.m11 = f44;
        this.m12 = f45;
        this.m20 = (f26 * f46) + (f28 * f47) + (f30 * f48);
        this.m21 = (f32 * f46) + (f33 * f47) + (f34 * f48);
        this.m22 = (f46 * f36) + (f47 * f37) + (f48 * f38);
    }

    public final void normalize() {
        double[] dArr = new double[9];
        getScaleRotate(new double[3], dArr);
        this.m00 = (float) dArr[0];
        this.m01 = (float) dArr[1];
        this.m02 = (float) dArr[2];
        this.m10 = (float) dArr[3];
        this.m11 = (float) dArr[4];
        this.m12 = (float) dArr[5];
        this.m20 = (float) dArr[6];
        this.m21 = (float) dArr[7];
        this.m22 = (float) dArr[8];
    }

    public final void normalize(Matrix3f matrix3f) {
        double[] dArr = new double[9];
        Matrix3d.compute_svd(new double[]{matrix3f.m00, matrix3f.m01, matrix3f.m02, matrix3f.m10, matrix3f.m11, matrix3f.m12, matrix3f.m20, matrix3f.m21, matrix3f.m22}, new double[3], dArr);
        this.m00 = (float) dArr[0];
        this.m01 = (float) dArr[1];
        this.m02 = (float) dArr[2];
        this.m10 = (float) dArr[3];
        this.m11 = (float) dArr[4];
        this.m12 = (float) dArr[5];
        this.m20 = (float) dArr[6];
        this.m21 = (float) dArr[7];
        this.m22 = (float) dArr[8];
    }

    public final void normalizeCP() {
        float f = this.m00;
        float f2 = this.m10;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.m20;
        float fSqrt = 1.0f / ((float) Math.sqrt(f3 + (f4 * f4)));
        this.m00 *= fSqrt;
        this.m10 *= fSqrt;
        this.m20 *= fSqrt;
        float f5 = this.m01;
        float f6 = this.m11;
        float f7 = (f5 * f5) + (f6 * f6);
        float f8 = this.m21;
        float fSqrt2 = 1.0f / ((float) Math.sqrt(f7 + (f8 * f8)));
        float f9 = this.m01 * fSqrt2;
        this.m01 = f9;
        float f10 = this.m11 * fSqrt2;
        this.m11 = f10;
        float f11 = this.m21 * fSqrt2;
        this.m21 = f11;
        float f12 = this.m10;
        float f13 = this.m20;
        this.m02 = (f12 * f11) - (f10 * f13);
        float f14 = this.m00;
        this.m12 = (f13 * f9) - (f11 * f14);
        this.m22 = (f14 * f10) - (f9 * f12);
    }

    public final void normalizeCP(Matrix3f matrix3f) {
        float f = matrix3f.m00;
        float f2 = matrix3f.m10;
        float f3 = (f * f) + (f2 * f2);
        float f4 = matrix3f.m20;
        float fSqrt = 1.0f / ((float) Math.sqrt(f3 + (f4 * f4)));
        this.m00 = matrix3f.m00 * fSqrt;
        this.m10 = matrix3f.m10 * fSqrt;
        this.m20 = matrix3f.m20 * fSqrt;
        float f5 = matrix3f.m01;
        float f6 = matrix3f.m11;
        float f7 = (f5 * f5) + (f6 * f6);
        float f8 = matrix3f.m21;
        float fSqrt2 = 1.0f / ((float) Math.sqrt(f7 + (f8 * f8)));
        float f9 = matrix3f.m01 * fSqrt2;
        this.m01 = f9;
        float f10 = matrix3f.m11 * fSqrt2;
        this.m11 = f10;
        float f11 = matrix3f.m21 * fSqrt2;
        this.m21 = f11;
        float f12 = this.m10;
        float f13 = this.m20;
        this.m02 = (f12 * f11) - (f10 * f13);
        float f14 = this.m00;
        this.m12 = (f13 * f9) - (f11 * f14);
        this.m22 = (f14 * f10) - (f9 * f12);
    }

    public boolean equals(Matrix3f matrix3f) {
        try {
            if (this.m00 == matrix3f.m00 && this.m01 == matrix3f.m01 && this.m02 == matrix3f.m02 && this.m10 == matrix3f.m10 && this.m11 == matrix3f.m11 && this.m12 == matrix3f.m12 && this.m20 == matrix3f.m20 && this.m21 == matrix3f.m21) {
                return this.m22 == matrix3f.m22;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Matrix3f matrix3f = (Matrix3f) obj;
            if (this.m00 == matrix3f.m00 && this.m01 == matrix3f.m01 && this.m02 == matrix3f.m02 && this.m10 == matrix3f.m10 && this.m11 == matrix3f.m11 && this.m12 == matrix3f.m12 && this.m20 == matrix3f.m20 && this.m21 == matrix3f.m21) {
                return this.m22 == matrix3f.m22;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(Matrix3f matrix3f, float f) {
        boolean z = Math.abs(this.m00 - matrix3f.m00) <= f;
        if (Math.abs(this.m01 - matrix3f.m01) > f) {
            z = false;
        }
        if (Math.abs(this.m02 - matrix3f.m02) > f) {
            z = false;
        }
        if (Math.abs(this.m10 - matrix3f.m10) > f) {
            z = false;
        }
        if (Math.abs(this.m11 - matrix3f.m11) > f) {
            z = false;
        }
        if (Math.abs(this.m12 - matrix3f.m12) > f) {
            z = false;
        }
        if (Math.abs(this.m20 - matrix3f.m20) > f) {
            z = false;
        }
        if (Math.abs(this.m21 - matrix3f.m21) > f) {
            z = false;
        }
        if (Math.abs(this.m22 - matrix3f.m22) > f) {
            return false;
        }
        return z;
    }

    public int hashCode() {
        long jFloatToIntBits = ((((((((((((((((Float.floatToIntBits(this.m00) + 31) * 31) + Float.floatToIntBits(this.m01)) * 31) + Float.floatToIntBits(this.m02)) * 31) + Float.floatToIntBits(this.m10)) * 31) + Float.floatToIntBits(this.m11)) * 31) + Float.floatToIntBits(this.m12)) * 31) + Float.floatToIntBits(this.m20)) * 31) + Float.floatToIntBits(this.m21)) * 31) + Float.floatToIntBits(this.m22);
        return (int) (jFloatToIntBits ^ (jFloatToIntBits >> 32));
    }

    public final void negate(Matrix3f matrix3f) {
        this.m00 = -matrix3f.m00;
        this.m01 = -matrix3f.m01;
        this.m02 = -matrix3f.m02;
        this.m10 = -matrix3f.m10;
        this.m11 = -matrix3f.m11;
        this.m12 = -matrix3f.m12;
        this.m20 = -matrix3f.m20;
        this.m21 = -matrix3f.m21;
        this.m22 = -matrix3f.m22;
    }

    public final void transform(Tuple3f tuple3f) {
        tuple3f.set((this.m00 * tuple3f.x) + (this.m01 * tuple3f.y) + (this.m02 * tuple3f.z), (this.m10 * tuple3f.x) + (this.m11 * tuple3f.y) + (this.m12 * tuple3f.z), (this.m20 * tuple3f.x) + (this.m21 * tuple3f.y) + (this.m22 * tuple3f.z));
    }

    public final void transform(Tuple3f tuple3f, Tuple3f tuple3f2) {
        float f = (this.m00 * tuple3f.x) + (this.m01 * tuple3f.y) + (this.m02 * tuple3f.z);
        float f2 = (this.m10 * tuple3f.x) + (this.m11 * tuple3f.y) + (this.m12 * tuple3f.z);
        tuple3f2.z = (this.m20 * tuple3f.x) + (this.m21 * tuple3f.y) + (this.m22 * tuple3f.z);
        tuple3f2.x = f;
        tuple3f2.y = f2;
    }

    void getScaleRotate(double[] dArr, double[] dArr2) {
        Matrix3d.compute_svd(new double[]{this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22}, dArr, dArr2);
    }

    public Object clone() {
        try {
            return (Matrix3f) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
