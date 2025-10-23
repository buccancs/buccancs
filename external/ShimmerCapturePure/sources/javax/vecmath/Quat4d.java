package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Quat4d extends Tuple4d implements Serializable {
    static final double EPS = 1.0E-6d;
    static final double EPS2 = 1.0E-30d;
    static final double PIO2 = 1.57079632679d;
    static final long serialVersionUID = 7577479888820201099L;

    public Quat4d(double d, double d2, double d3, double d4) {
        double dSqrt = 1.0d / Math.sqrt((((d * d) + (d2 * d2)) + (d3 * d3)) + (d4 * d4));
        this.x = d * dSqrt;
        this.y = d2 * dSqrt;
        this.z = d3 * dSqrt;
        this.w = d4 * dSqrt;
    }

    public Quat4d(double[] dArr) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = dArr[3];
        double dSqrt = 1.0d / Math.sqrt((((d * d) + (d2 * d2)) + (d3 * d3)) + (d4 * d4));
        this.x = dArr[0] * dSqrt;
        this.y = dArr[1] * dSqrt;
        this.z = dArr[2] * dSqrt;
        this.w = dArr[3] * dSqrt;
    }

    public Quat4d(Quat4d quat4d) {
        super(quat4d);
    }

    public Quat4d(Quat4f quat4f) {
        super(quat4f);
    }

    public Quat4d(Tuple4f tuple4f) {
        double dSqrt = 1.0d / Math.sqrt((((tuple4f.x * tuple4f.x) + (tuple4f.y * tuple4f.y)) + (tuple4f.z * tuple4f.z)) + (tuple4f.w * tuple4f.w));
        this.x = tuple4f.x * dSqrt;
        this.y = tuple4f.y * dSqrt;
        this.z = tuple4f.z * dSqrt;
        this.w = tuple4f.w * dSqrt;
    }

    public Quat4d(Tuple4d tuple4d) {
        double dSqrt = 1.0d / Math.sqrt((((tuple4d.x * tuple4d.x) + (tuple4d.y * tuple4d.y)) + (tuple4d.z * tuple4d.z)) + (tuple4d.w * tuple4d.w));
        this.x = tuple4d.x * dSqrt;
        this.y = tuple4d.y * dSqrt;
        this.z = tuple4d.z * dSqrt;
        this.w = tuple4d.w * dSqrt;
    }

    public Quat4d() {
    }

    public final void conjugate(Quat4d quat4d) {
        this.x = -quat4d.x;
        this.y = -quat4d.y;
        this.z = -quat4d.z;
        this.w = quat4d.w;
    }

    public final void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public final void mul(Quat4d quat4d, Quat4d quat4d2) {
        if (this != quat4d && this != quat4d2) {
            this.w = (((quat4d.w * quat4d2.w) - (quat4d.x * quat4d2.x)) - (quat4d.y * quat4d2.y)) - (quat4d.z * quat4d2.z);
            this.x = (((quat4d.w * quat4d2.x) + (quat4d2.w * quat4d.x)) + (quat4d.y * quat4d2.z)) - (quat4d.z * quat4d2.y);
            this.y = (((quat4d.w * quat4d2.y) + (quat4d2.w * quat4d.y)) - (quat4d.x * quat4d2.z)) + (quat4d.z * quat4d2.x);
            this.z = (((quat4d.w * quat4d2.z) + (quat4d2.w * quat4d.z)) + (quat4d.x * quat4d2.y)) - (quat4d.y * quat4d2.x);
            return;
        }
        double d = (((quat4d.w * quat4d2.w) - (quat4d.x * quat4d2.x)) - (quat4d.y * quat4d2.y)) - (quat4d.z * quat4d2.z);
        double d2 = (((quat4d.w * quat4d2.x) + (quat4d2.w * quat4d.x)) + (quat4d.y * quat4d2.z)) - (quat4d.z * quat4d2.y);
        double d3 = (((quat4d.w * quat4d2.y) + (quat4d2.w * quat4d.y)) - (quat4d.x * quat4d2.z)) + (quat4d.z * quat4d2.x);
        this.z = (((quat4d.w * quat4d2.z) + (quat4d2.w * quat4d.z)) + (quat4d.x * quat4d2.y)) - (quat4d.y * quat4d2.x);
        this.w = d;
        this.x = d2;
        this.y = d3;
    }

    public final void mul(Quat4d quat4d) {
        double d = (((this.w * quat4d.w) - (this.x * quat4d.x)) - (this.y * quat4d.y)) - (this.z * quat4d.z);
        double d2 = (((this.w * quat4d.x) + (quat4d.w * this.x)) + (this.y * quat4d.z)) - (this.z * quat4d.y);
        double d3 = (((this.w * quat4d.y) + (quat4d.w * this.y)) - (this.x * quat4d.z)) + (this.z * quat4d.x);
        this.z = (((this.w * quat4d.z) + (quat4d.w * this.z)) + (this.x * quat4d.y)) - (this.y * quat4d.x);
        this.w = d;
        this.x = d2;
        this.y = d3;
    }

    public final void mulInverse(Quat4d quat4d, Quat4d quat4d2) {
        Quat4d quat4d3 = new Quat4d(quat4d2);
        quat4d3.inverse();
        mul(quat4d, quat4d3);
    }

    public final void mulInverse(Quat4d quat4d) {
        Quat4d quat4d2 = new Quat4d(quat4d);
        quat4d2.inverse();
        mul(quat4d2);
    }

    public final void inverse(Quat4d quat4d) {
        double d = 1.0d / ((((quat4d.w * quat4d.w) + (quat4d.x * quat4d.x)) + (quat4d.y * quat4d.y)) + (quat4d.z * quat4d.z));
        this.w = quat4d.w * d;
        double d2 = -d;
        this.x = quat4d.x * d2;
        this.y = quat4d.y * d2;
        this.z = d2 * quat4d.z;
    }

    public final void inverse() {
        double d = 1.0d / ((((this.w * this.w) + (this.x * this.x)) + (this.y * this.y)) + (this.z * this.z));
        this.w *= d;
        double d2 = -d;
        this.x *= d2;
        this.y *= d2;
        this.z *= d2;
    }

    public final void normalize(Quat4d quat4d) {
        double d = (quat4d.x * quat4d.x) + (quat4d.y * quat4d.y) + (quat4d.z * quat4d.z) + (quat4d.w * quat4d.w);
        if (d > 0.0d) {
            double dSqrt = 1.0d / Math.sqrt(d);
            this.x = quat4d.x * dSqrt;
            this.y = quat4d.y * dSqrt;
            this.z = quat4d.z * dSqrt;
            this.w = dSqrt * quat4d.w;
            return;
        }
        this.x = 0.0d;
        this.y = 0.0d;
        this.z = 0.0d;
        this.w = 0.0d;
    }

    public final void normalize() {
        double d = (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
        if (d > 0.0d) {
            double dSqrt = 1.0d / Math.sqrt(d);
            this.x *= dSqrt;
            this.y *= dSqrt;
            this.z *= dSqrt;
            this.w *= dSqrt;
            return;
        }
        this.x = 0.0d;
        this.y = 0.0d;
        this.z = 0.0d;
        this.w = 0.0d;
    }

    public final void set(Matrix4f matrix4f) {
        double d = (matrix4f.m00 + matrix4f.m11 + matrix4f.m22 + matrix4f.m33) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
            return;
        }
        if (d >= EPS2) {
            this.w = Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (matrix4f.m21 - matrix4f.m12) * d2;
            this.y = (matrix4f.m02 - matrix4f.m20) * d2;
            this.z = (matrix4f.m10 - matrix4f.m01) * d2;
            return;
        }
        this.w = 0.0d;
        double d3 = (matrix4f.m11 + matrix4f.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
        } else {
            if (d3 >= EPS2) {
                this.x = Math.sqrt(d3);
                double d4 = 1.0d / (this.x * 2.0d);
                this.y = matrix4f.m10 * d4;
                this.z = matrix4f.m20 * d4;
                return;
            }
            this.x = 0.0d;
            double d5 = (1.0d - matrix4f.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = Math.sqrt(d5);
                this.z = matrix4f.m21 / (this.y * 2.0d);
            } else {
                this.y = 0.0d;
                this.z = 1.0d;
            }
        }
    }

    public final void set(Matrix4d matrix4d) {
        double d = (matrix4d.m00 + matrix4d.m11 + matrix4d.m22 + matrix4d.m33) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
            return;
        }
        if (d >= EPS2) {
            this.w = Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (matrix4d.m21 - matrix4d.m12) * d2;
            this.y = (matrix4d.m02 - matrix4d.m20) * d2;
            this.z = (matrix4d.m10 - matrix4d.m01) * d2;
            return;
        }
        this.w = 0.0d;
        double d3 = (matrix4d.m11 + matrix4d.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
        } else {
            if (d3 >= EPS2) {
                this.x = Math.sqrt(d3);
                double d4 = 0.5d / this.x;
                this.y = matrix4d.m10 * d4;
                this.z = matrix4d.m20 * d4;
                return;
            }
            this.x = 0.0d;
            double d5 = (1.0d - matrix4d.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = Math.sqrt(d5);
                this.z = matrix4d.m21 / (this.y * 2.0d);
            } else {
                this.y = 0.0d;
                this.z = 1.0d;
            }
        }
    }

    public final void set(Matrix3f matrix3f) {
        double d = (matrix3f.m00 + matrix3f.m11 + matrix3f.m22 + 1.0d) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
            return;
        }
        if (d >= EPS2) {
            this.w = Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (matrix3f.m21 - matrix3f.m12) * d2;
            this.y = (matrix3f.m02 - matrix3f.m20) * d2;
            this.z = (matrix3f.m10 - matrix3f.m01) * d2;
            return;
        }
        this.w = 0.0d;
        double d3 = (matrix3f.m11 + matrix3f.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
        } else {
            if (d3 >= EPS2) {
                this.x = Math.sqrt(d3);
                double d4 = 0.5d / this.x;
                this.y = matrix3f.m10 * d4;
                this.z = matrix3f.m20 * d4;
                return;
            }
            this.x = 0.0d;
            double d5 = (1.0d - matrix3f.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = Math.sqrt(d5);
                this.z = matrix3f.m21 / (this.y * 2.0d);
            }
            this.y = 0.0d;
            this.z = 1.0d;
        }
    }

    public final void set(Matrix3d matrix3d) {
        double d = (matrix3d.m00 + matrix3d.m11 + matrix3d.m22 + 1.0d) * 0.25d;
        if (d < 0.0d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
            return;
        }
        if (d >= EPS2) {
            this.w = Math.sqrt(d);
            double d2 = 0.25d / this.w;
            this.x = (matrix3d.m21 - matrix3d.m12) * d2;
            this.y = (matrix3d.m02 - matrix3d.m20) * d2;
            this.z = (matrix3d.m10 - matrix3d.m01) * d2;
            return;
        }
        this.w = 0.0d;
        double d3 = (matrix3d.m11 + matrix3d.m22) * (-0.5d);
        if (d3 < 0.0d) {
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 1.0d;
        } else {
            if (d3 >= EPS2) {
                this.x = Math.sqrt(d3);
                double d4 = 0.5d / this.x;
                this.y = matrix3d.m10 * d4;
                this.z = matrix3d.m20 * d4;
                return;
            }
            this.x = 0.0d;
            double d5 = (1.0d - matrix3d.m22) * 0.5d;
            if (d5 >= EPS2) {
                this.y = Math.sqrt(d5);
                this.z = matrix3d.m21 / (this.y * 2.0d);
            } else {
                this.y = 0.0d;
                this.z = 1.0d;
            }
        }
    }

    public final void set(AxisAngle4f axisAngle4f) {
        double dSqrt = Math.sqrt((axisAngle4f.x * axisAngle4f.x) + (axisAngle4f.y * axisAngle4f.y) + (axisAngle4f.z * axisAngle4f.z));
        if (dSqrt < 1.0E-6d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 0.0d;
            return;
        }
        double dSin = Math.sin(axisAngle4f.angle / 2.0d);
        double d = 1.0d / dSqrt;
        this.w = Math.cos(axisAngle4f.angle / 2.0d);
        this.x = axisAngle4f.x * d * dSin;
        this.y = axisAngle4f.y * d * dSin;
        this.z = axisAngle4f.z * d * dSin;
    }

    public final void set(AxisAngle4d axisAngle4d) {
        double dSqrt = Math.sqrt((axisAngle4d.x * axisAngle4d.x) + (axisAngle4d.y * axisAngle4d.y) + (axisAngle4d.z * axisAngle4d.z));
        if (dSqrt < 1.0E-6d) {
            this.w = 0.0d;
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 0.0d;
            return;
        }
        double d = 1.0d / dSqrt;
        double dSin = Math.sin(axisAngle4d.angle / 2.0d);
        this.w = Math.cos(axisAngle4d.angle / 2.0d);
        this.x = axisAngle4d.x * d * dSin;
        this.y = axisAngle4d.y * d * dSin;
        this.z = axisAngle4d.z * d * dSin;
    }

    public final void interpolate(Quat4d quat4d, double d) {
        double dSin;
        double d2 = (this.x * quat4d.x) + (this.y * quat4d.y) + (this.z * quat4d.z) + (this.w * quat4d.w);
        if (d2 < 0.0d) {
            quat4d.x = -quat4d.x;
            quat4d.y = -quat4d.y;
            quat4d.z = -quat4d.z;
            quat4d.w = -quat4d.w;
        }
        if (1.0d - Math.abs(d2) > 1.0E-6d) {
            double dAcos = Math.acos(d2);
            double dSin2 = Math.sin(dAcos);
            dSin = Math.sin((1.0d - d) * dAcos) / dSin2;
            d = Math.sin(d * dAcos) / dSin2;
        } else {
            dSin = 1.0d - d;
        }
        this.w = (this.w * dSin) + (quat4d.w * d);
        this.x = (this.x * dSin) + (quat4d.x * d);
        this.y = (this.y * dSin) + (quat4d.y * d);
        this.z = (dSin * this.z) + (d * quat4d.z);
    }

    public final void interpolate(Quat4d quat4d, Quat4d quat4d2, double d) {
        double dSin;
        double d2 = (quat4d2.x * quat4d.x) + (quat4d2.y * quat4d.y) + (quat4d2.z * quat4d.z) + (quat4d2.w * quat4d.w);
        if (d2 < 0.0d) {
            quat4d.x = -quat4d.x;
            quat4d.y = -quat4d.y;
            quat4d.z = -quat4d.z;
            quat4d.w = -quat4d.w;
        }
        if (1.0d - Math.abs(d2) > 1.0E-6d) {
            double dAcos = Math.acos(d2);
            double dSin2 = Math.sin(dAcos);
            dSin = Math.sin((1.0d - d) * dAcos) / dSin2;
            d = Math.sin(d * dAcos) / dSin2;
        } else {
            dSin = 1.0d - d;
        }
        this.w = (quat4d.w * dSin) + (quat4d2.w * d);
        this.x = (quat4d.x * dSin) + (quat4d2.x * d);
        this.y = (quat4d.y * dSin) + (quat4d2.y * d);
        this.z = (dSin * quat4d.z) + (d * quat4d2.z);
    }
}
