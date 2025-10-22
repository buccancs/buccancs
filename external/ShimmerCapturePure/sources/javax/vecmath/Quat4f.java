package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Quat4f extends Tuple4f implements Serializable {
    static final double EPS = 1.0E-6d;
    static final double EPS2 = 1.0E-30d;
    static final double PIO2 = 1.57079632679d;
    static final long serialVersionUID = 2675933778405442383L;

    public Quat4f(float f, float f2, float f3, float f4) {
        float fSqrt = (float) (1.0d / Math.sqrt((((f * f) + (f2 * f2)) + (f3 * f3)) + (f4 * f4)));
        this.x = f * fSqrt;
        this.y = f2 * fSqrt;
        this.z = f3 * fSqrt;
        this.w = f4 * fSqrt;
    }

    public Quat4f(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float fSqrt = (float) (1.0d / Math.sqrt((((f * f) + (f2 * f2)) + (f3 * f3)) + (f4 * f4)));
        this.x = fArr[0] * fSqrt;
        this.y = fArr[1] * fSqrt;
        this.z = fArr[2] * fSqrt;
        this.w = fArr[3] * fSqrt;
    }

    public Quat4f(Quat4f quat4f) {
        super(quat4f);
    }

    public Quat4f(Quat4d quat4d) {
        super(quat4d);
    }

    public Quat4f(Tuple4f tuple4f) {
        float fSqrt = (float) (1.0d / Math.sqrt((((tuple4f.x * tuple4f.x) + (tuple4f.y * tuple4f.y)) + (tuple4f.z * tuple4f.z)) + (tuple4f.w * tuple4f.w)));
        this.x = tuple4f.x * fSqrt;
        this.y = tuple4f.y * fSqrt;
        this.z = tuple4f.z * fSqrt;
        this.w = tuple4f.w * fSqrt;
    }

    public Quat4f(Tuple4d tuple4d) {
        double dSqrt = 1.0d / Math.sqrt((((tuple4d.x * tuple4d.x) + (tuple4d.y * tuple4d.y)) + (tuple4d.z * tuple4d.z)) + (tuple4d.w * tuple4d.w));
        this.x = (float) (tuple4d.x * dSqrt);
        this.y = (float) (tuple4d.y * dSqrt);
        this.z = (float) (tuple4d.z * dSqrt);
        this.w = (float) (tuple4d.w * dSqrt);
    }

    public Quat4f() {
    }

    public final void conjugate(Quat4f quat4f) {
        this.x = -quat4f.x;
        this.y = -quat4f.y;
        this.z = -quat4f.z;
        this.w = quat4f.w;
    }

    public final void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public final void mul(Quat4f quat4f, Quat4f quat4f2) {
        if (this != quat4f && this != quat4f2) {
            this.w = (((quat4f.w * quat4f2.w) - (quat4f.x * quat4f2.x)) - (quat4f.y * quat4f2.y)) - (quat4f.z * quat4f2.z);
            this.x = (((quat4f.w * quat4f2.x) + (quat4f2.w * quat4f.x)) + (quat4f.y * quat4f2.z)) - (quat4f.z * quat4f2.y);
            this.y = (((quat4f.w * quat4f2.y) + (quat4f2.w * quat4f.y)) - (quat4f.x * quat4f2.z)) + (quat4f.z * quat4f2.x);
            this.z = (((quat4f.w * quat4f2.z) + (quat4f2.w * quat4f.z)) + (quat4f.x * quat4f2.y)) - (quat4f.y * quat4f2.x);
            return;
        }
        float f = (((quat4f.w * quat4f2.w) - (quat4f.x * quat4f2.x)) - (quat4f.y * quat4f2.y)) - (quat4f.z * quat4f2.z);
        float f2 = (((quat4f.w * quat4f2.x) + (quat4f2.w * quat4f.x)) + (quat4f.y * quat4f2.z)) - (quat4f.z * quat4f2.y);
        float f3 = (((quat4f.w * quat4f2.y) + (quat4f2.w * quat4f.y)) - (quat4f.x * quat4f2.z)) + (quat4f.z * quat4f2.x);
        this.z = (((quat4f.w * quat4f2.z) + (quat4f2.w * quat4f.z)) + (quat4f.x * quat4f2.y)) - (quat4f.y * quat4f2.x);
        this.w = f;
        this.x = f2;
        this.y = f3;
    }

    public final void mul(Quat4f quat4f) {
        float f = (((this.w * quat4f.w) - (this.x * quat4f.x)) - (this.y * quat4f.y)) - (this.z * quat4f.z);
        float f2 = (((this.w * quat4f.x) + (quat4f.w * this.x)) + (this.y * quat4f.z)) - (this.z * quat4f.y);
        float f3 = (((this.w * quat4f.y) + (quat4f.w * this.y)) - (this.x * quat4f.z)) + (this.z * quat4f.x);
        this.z = (((this.w * quat4f.z) + (quat4f.w * this.z)) + (this.x * quat4f.y)) - (this.y * quat4f.x);
        this.w = f;
        this.x = f2;
        this.y = f3;
    }

    public final void mulInverse(Quat4f quat4f, Quat4f quat4f2) {
        Quat4f quat4f3 = new Quat4f(quat4f2);
        quat4f3.inverse();
        mul(quat4f, quat4f3);
    }

    public final void mulInverse(Quat4f quat4f) {
        Quat4f quat4f2 = new Quat4f(quat4f);
        quat4f2.inverse();
        mul(quat4f2);
    }

    public final void inverse(Quat4f quat4f) {
        float f = 1.0f / ((((quat4f.w * quat4f.w) + (quat4f.x * quat4f.x)) + (quat4f.y * quat4f.y)) + (quat4f.z * quat4f.z));
        this.w = quat4f.w * f;
        float f2 = -f;
        this.x = quat4f.x * f2;
        this.y = quat4f.y * f2;
        this.z = f2 * quat4f.z;
    }

    public final void inverse() {
        float f = 1.0f / ((((this.w * this.w) + (this.x * this.x)) + (this.y * this.y)) + (this.z * this.z));
        this.w *= f;
        float f2 = -f;
        this.x *= f2;
        this.y *= f2;
        this.z *= f2;
    }

    public final void normalize(Quat4f quat4f) {
        float f = (quat4f.x * quat4f.x) + (quat4f.y * quat4f.y) + (quat4f.z * quat4f.z) + (quat4f.w * quat4f.w);
        if (f > 0.0f) {
            float fSqrt = 1.0f / ((float) Math.sqrt(f));
            this.x = quat4f.x * fSqrt;
            this.y = quat4f.y * fSqrt;
            this.z = quat4f.z * fSqrt;
            this.w = fSqrt * quat4f.w;
            return;
        }
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public final void normalize() {
        float f = (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
        if (f > 0.0f) {
            float fSqrt = 1.0f / ((float) Math.sqrt(f));
            this.x *= fSqrt;
            this.y *= fSqrt;
            this.z *= fSqrt;
            this.w *= fSqrt;
            return;
        }
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public final void set(Matrix4f matrix4f) {
        float f = (matrix4f.m00 + matrix4f.m11 + matrix4f.m22 + matrix4f.m33) * 0.25f;
        if (f < 0.0f) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        double d = f;
        if (d >= EPS2) {
            this.w = (float) Math.sqrt(d);
            float f2 = 0.25f / this.w;
            this.x = (matrix4f.m21 - matrix4f.m12) * f2;
            this.y = (matrix4f.m02 - matrix4f.m20) * f2;
            this.z = (matrix4f.m10 - matrix4f.m01) * f2;
            return;
        }
        this.w = 0.0f;
        float f3 = (matrix4f.m11 + matrix4f.m22) * (-0.5f);
        if (f3 < 0.0f) {
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        double d2 = f3;
        if (d2 >= EPS2) {
            this.x = (float) Math.sqrt(d2);
            float f4 = 1.0f / (this.x * 2.0f);
            this.y = matrix4f.m10 * f4;
            this.z = matrix4f.m20 * f4;
            return;
        }
        this.x = 0.0f;
        double d3 = (1.0f - matrix4f.m22) * 0.5f;
        if (d3 >= EPS2) {
            this.y = (float) Math.sqrt(d3);
            this.z = matrix4f.m21 / (this.y * 2.0f);
        } else {
            this.y = 0.0f;
            this.z = 1.0f;
        }
    }

    public final void set(Matrix4d matrix4d) {
        double d = (matrix4d.m00 + matrix4d.m11 + matrix4d.m22 + matrix4d.m33) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        if (d >= EPS2) {
            this.w = (float) Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (float) ((matrix4d.m21 - matrix4d.m12) * d2);
            this.y = (float) ((matrix4d.m02 - matrix4d.m20) * d2);
            this.z = (float) ((matrix4d.m10 - matrix4d.m01) * d2);
            return;
        }
        this.w = 0.0f;
        double d3 = (matrix4d.m11 + matrix4d.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
        } else {
            if (d3 >= EPS2) {
                this.x = (float) Math.sqrt(d3);
                double d4 = 0.5d / this.x;
                this.y = (float) (matrix4d.m10 * d4);
                this.z = (float) (matrix4d.m20 * d4);
                return;
            }
            this.x = 0.0f;
            double d5 = (1.0d - matrix4d.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = (float) Math.sqrt(d5);
                this.z = (float) (matrix4d.m21 / (this.y * 2.0d));
            } else {
                this.y = 0.0f;
                this.z = 1.0f;
            }
        }
    }

    public final void set(Matrix3f matrix3f) {
        float f = (matrix3f.m00 + matrix3f.m11 + matrix3f.m22 + 1.0f) * 0.25f;
        if (f < 0.0f) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        double d = f;
        if (d >= EPS2) {
            this.w = (float) Math.sqrt(d);
            float f2 = 0.25f / this.w;
            this.x = (matrix3f.m21 - matrix3f.m12) * f2;
            this.y = (matrix3f.m02 - matrix3f.m20) * f2;
            this.z = (matrix3f.m10 - matrix3f.m01) * f2;
            return;
        }
        this.w = 0.0f;
        float f3 = (matrix3f.m11 + matrix3f.m22) * (-0.5f);
        if (f3 < 0.0f) {
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        double d2 = f3;
        if (d2 >= EPS2) {
            this.x = (float) Math.sqrt(d2);
            float f4 = 0.5f / this.x;
            this.y = matrix3f.m10 * f4;
            this.z = matrix3f.m20 * f4;
            return;
        }
        this.x = 0.0f;
        double d3 = (1.0f - matrix3f.m22) * 0.5f;
        if (d3 >= EPS2) {
            this.y = (float) Math.sqrt(d3);
            this.z = matrix3f.m21 / (this.y * 2.0f);
        } else {
            this.y = 0.0f;
            this.z = 1.0f;
        }
    }

    public final void set(Matrix3d matrix3d) {
        double d = (matrix3d.m00 + matrix3d.m11 + matrix3d.m22 + 1.0d) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
            return;
        }
        if (d >= EPS2) {
            this.w = (float) Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (float) ((matrix3d.m21 - matrix3d.m12) * d2);
            this.y = (float) ((matrix3d.m02 - matrix3d.m20) * d2);
            this.z = (float) ((matrix3d.m10 - matrix3d.m01) * d2);
            return;
        }
        this.w = 0.0f;
        double d3 = (matrix3d.m11 + matrix3d.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 1.0f;
        } else {
            if (d3 >= EPS2) {
                this.x = (float) Math.sqrt(d3);
                double d4 = 0.5d / this.x;
                this.y = (float) (matrix3d.m10 * d4);
                this.z = (float) (matrix3d.m20 * d4);
                return;
            }
            this.x = 0.0f;
            double d5 = (1.0d - matrix3d.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = (float) Math.sqrt(d5);
                this.z = (float) (matrix3d.m21 / (this.y * 2.0d));
            } else {
                this.y = 0.0f;
                this.z = 1.0f;
            }
        }
    }

    public final void set(AxisAngle4f axisAngle4f) {
        float fSqrt = (float) Math.sqrt((axisAngle4f.x * axisAngle4f.x) + (axisAngle4f.y * axisAngle4f.y) + (axisAngle4f.z * axisAngle4f.z));
        if (fSqrt < 1.0E-6d) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 0.0f;
            return;
        }
        float f = 1.0f / fSqrt;
        float fSin = (float) Math.sin(axisAngle4f.angle / 2.0d);
        this.w = (float) Math.cos(axisAngle4f.angle / 2.0d);
        this.x = axisAngle4f.x * f * fSin;
        this.y = axisAngle4f.y * f * fSin;
        this.z = axisAngle4f.z * f * fSin;
    }

    public final void set(AxisAngle4d axisAngle4d) {
        float fSqrt = (float) (1.0d / Math.sqrt(((axisAngle4d.x * axisAngle4d.x) + (axisAngle4d.y * axisAngle4d.y)) + (axisAngle4d.z * axisAngle4d.z)));
        if (fSqrt < 1.0E-6d) {
            this.w = 0.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 0.0f;
            return;
        }
        float f = 1.0f / fSqrt;
        float fSin = (float) Math.sin(axisAngle4d.angle / 2.0d);
        this.w = (float) Math.cos(axisAngle4d.angle / 2.0d);
        this.x = ((float) axisAngle4d.x) * f * fSin;
        this.y = ((float) axisAngle4d.y) * f * fSin;
        this.z = ((float) axisAngle4d.z) * f * fSin;
    }

    public final void interpolate(Quat4f quat4f, float f) {
        double dSin;
        double dSin2;
        double d = (this.x * quat4f.x) + (this.y * quat4f.y) + (this.z * quat4f.z) + (this.w * quat4f.w);
        if (d < 0.0d) {
            quat4f.x = -quat4f.x;
            quat4f.y = -quat4f.y;
            quat4f.z = -quat4f.z;
            quat4f.w = -quat4f.w;
        }
        if (1.0d - Math.abs(d) > 1.0E-6d) {
            double dAcos = Math.acos(d);
            double dSin3 = Math.sin(dAcos);
            double d2 = f;
            dSin2 = Math.sin((1.0d - d2) * dAcos) / dSin3;
            dSin = Math.sin(d2 * dAcos) / dSin3;
        } else {
            dSin = f;
            dSin2 = 1.0d - dSin;
        }
        this.w = (float) ((this.w * dSin2) + (quat4f.w * dSin));
        this.x = (float) ((this.x * dSin2) + (quat4f.x * dSin));
        this.y = (float) ((this.y * dSin2) + (quat4f.y * dSin));
        this.z = (float) ((dSin2 * this.z) + (dSin * quat4f.z));
    }

    public final void interpolate(Quat4f quat4f, Quat4f quat4f2, float f) {
        double dSin;
        double dSin2;
        double d = (quat4f2.x * quat4f.x) + (quat4f2.y * quat4f.y) + (quat4f2.z * quat4f.z) + (quat4f2.w * quat4f.w);
        if (d < 0.0d) {
            quat4f.x = -quat4f.x;
            quat4f.y = -quat4f.y;
            quat4f.z = -quat4f.z;
            quat4f.w = -quat4f.w;
        }
        if (1.0d - Math.abs(d) > 1.0E-6d) {
            double dAcos = Math.acos(d);
            double dSin3 = Math.sin(dAcos);
            double d2 = f;
            dSin2 = Math.sin((1.0d - d2) * dAcos) / dSin3;
            dSin = Math.sin(d2 * dAcos) / dSin3;
        } else {
            dSin = f;
            dSin2 = 1.0d - dSin;
        }
        this.w = (float) ((quat4f.w * dSin2) + (quat4f2.w * dSin));
        this.x = (float) ((quat4f.x * dSin2) + (quat4f2.x * dSin));
        this.y = (float) ((quat4f.y * dSin2) + (quat4f2.y * dSin));
        this.z = (float) ((dSin2 * quat4f.z) + (dSin * quat4f2.z));
    }
}
