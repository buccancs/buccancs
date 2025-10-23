package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class AxisAngle4f implements Serializable, Cloneable {
    static final double EPS = 1.0E-6d;
    static final long serialVersionUID = -163246355858070601L;
    public float angle;
    public float x;
    public float y;
    public float z;

    public AxisAngle4f(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.angle = f4;
    }

    public AxisAngle4f(float[] fArr) {
        this.x = fArr[0];
        this.y = fArr[1];
        this.z = fArr[2];
        this.angle = fArr[3];
    }

    public AxisAngle4f(AxisAngle4f axisAngle4f) {
        this.x = axisAngle4f.x;
        this.y = axisAngle4f.y;
        this.z = axisAngle4f.z;
        this.angle = axisAngle4f.angle;
    }

    public AxisAngle4f(AxisAngle4d axisAngle4d) {
        this.x = (float) axisAngle4d.x;
        this.y = (float) axisAngle4d.y;
        this.z = (float) axisAngle4d.z;
        this.angle = (float) axisAngle4d.angle;
    }

    public AxisAngle4f(Vector3f vector3f, float f) {
        this.x = vector3f.x;
        this.y = vector3f.y;
        this.z = vector3f.z;
        this.angle = f;
    }

    public AxisAngle4f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 1.0f;
        this.angle = 0.0f;
    }

    public final void set(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.angle = f4;
    }

    public final void set(float[] fArr) {
        this.x = fArr[0];
        this.y = fArr[1];
        this.z = fArr[2];
        this.angle = fArr[3];
    }

    public final void set(AxisAngle4f axisAngle4f) {
        this.x = axisAngle4f.x;
        this.y = axisAngle4f.y;
        this.z = axisAngle4f.z;
        this.angle = axisAngle4f.angle;
    }

    public final void set(AxisAngle4d axisAngle4d) {
        this.x = (float) axisAngle4d.x;
        this.y = (float) axisAngle4d.y;
        this.z = (float) axisAngle4d.z;
        this.angle = (float) axisAngle4d.angle;
    }

    public final void set(Vector3f vector3f, float f) {
        this.x = vector3f.x;
        this.y = vector3f.y;
        this.z = vector3f.z;
        this.angle = f;
    }

    public final void get(float[] fArr) {
        fArr[0] = this.x;
        fArr[1] = this.y;
        fArr[2] = this.z;
        fArr[3] = this.angle;
    }

    public final void set(Quat4f quat4f) {
        double d = (quat4f.x * quat4f.x) + (quat4f.y * quat4f.y) + (quat4f.z * quat4f.z);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (quat4f.x * d2);
        this.y = (float) (quat4f.y * d2);
        this.z = (float) (quat4f.z * d2);
        this.angle = (float) (Math.atan2(dSqrt, quat4f.w) * 2.0d);
    }

    public final void set(Quat4d quat4d) {
        double d = (quat4d.x * quat4d.x) + (quat4d.y * quat4d.y) + (quat4d.z * quat4d.z);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (quat4d.x * d2);
        this.y = (float) (quat4d.y * d2);
        this.z = (float) (quat4d.z * d2);
        this.angle = (float) (Math.atan2(dSqrt, quat4d.w) * 2.0d);
    }

    public final void set(Matrix4f matrix4f) {
        Matrix3f matrix3f = new Matrix3f();
        matrix4f.get(matrix3f);
        this.x = matrix3f.m21 - matrix3f.m12;
        this.y = matrix3f.m02 - matrix3f.m20;
        float f = matrix3f.m10 - matrix3f.m01;
        this.z = f;
        float f2 = this.x;
        float f3 = this.y;
        double d = (f2 * f2) + (f3 * f3) + (f * f);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        this.angle = (float) Math.atan2(dSqrt * 0.5d, (((matrix3f.m00 + matrix3f.m11) + matrix3f.m22) - 1.0d) * 0.5d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (this.x * d2);
        this.y = (float) (this.y * d2);
        this.z = (float) (this.z * d2);
    }

    public final void set(Matrix4d matrix4d) {
        Matrix3d matrix3d = new Matrix3d();
        matrix4d.get(matrix3d);
        this.x = (float) (matrix3d.m21 - matrix3d.m12);
        this.y = (float) (matrix3d.m02 - matrix3d.m20);
        float f = (float) (matrix3d.m10 - matrix3d.m01);
        this.z = f;
        float f2 = this.x;
        float f3 = this.y;
        double d = (f2 * f2) + (f3 * f3) + (f * f);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        this.angle = (float) Math.atan2(dSqrt * 0.5d, (((matrix3d.m00 + matrix3d.m11) + matrix3d.m22) - 1.0d) * 0.5d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (this.x * d2);
        this.y = (float) (this.y * d2);
        this.z = (float) (this.z * d2);
    }

    public final void set(Matrix3f matrix3f) {
        this.x = matrix3f.m21 - matrix3f.m12;
        this.y = matrix3f.m02 - matrix3f.m20;
        float f = matrix3f.m10 - matrix3f.m01;
        this.z = f;
        float f2 = this.x;
        float f3 = this.y;
        double d = (f2 * f2) + (f3 * f3) + (f * f);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        this.angle = (float) Math.atan2(dSqrt * 0.5d, (((matrix3f.m00 + matrix3f.m11) + matrix3f.m22) - 1.0d) * 0.5d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (this.x * d2);
        this.y = (float) (this.y * d2);
        this.z = (float) (this.z * d2);
    }

    public final void set(Matrix3d matrix3d) {
        this.x = (float) (matrix3d.m21 - matrix3d.m12);
        this.y = (float) (matrix3d.m02 - matrix3d.m20);
        float f = (float) (matrix3d.m10 - matrix3d.m01);
        this.z = f;
        float f2 = this.x;
        float f3 = this.y;
        double d = (f2 * f2) + (f3 * f3) + (f * f);
        if (d <= 1.0E-6d) {
            this.x = 0.0f;
            this.y = 1.0f;
            this.z = 0.0f;
            this.angle = 0.0f;
            return;
        }
        double dSqrt = Math.sqrt(d);
        this.angle = (float) Math.atan2(dSqrt * 0.5d, (((matrix3d.m00 + matrix3d.m11) + matrix3d.m22) - 1.0d) * 0.5d);
        double d2 = 1.0d / dSqrt;
        this.x = (float) (this.x * d2);
        this.y = (float) (this.y * d2);
        this.z = (float) (this.z * d2);
    }

    public String toString() {
        return new StringBuffer("(").append(this.x).append(", ").append(this.y).append(", ").append(this.z).append(", ").append(this.angle).append(")").toString();
    }

    public boolean equals(AxisAngle4f axisAngle4f) {
        try {
            if (this.x == axisAngle4f.x && this.y == axisAngle4f.y && this.z == axisAngle4f.z) {
                return this.angle == axisAngle4f.angle;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            AxisAngle4f axisAngle4f = (AxisAngle4f) obj;
            if (this.x == axisAngle4f.x && this.y == axisAngle4f.y && this.z == axisAngle4f.z) {
                return this.angle == axisAngle4f.angle;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(AxisAngle4f axisAngle4f, float f) {
        float f2 = this.x - axisAngle4f.x;
        if (f2 < 0.0f) {
            f2 = -f2;
        }
        if (f2 > f) {
            return false;
        }
        float f3 = this.y - axisAngle4f.y;
        if (f3 < 0.0f) {
            f3 = -f3;
        }
        if (f3 > f) {
            return false;
        }
        float f4 = this.z - axisAngle4f.z;
        if (f4 < 0.0f) {
            f4 = -f4;
        }
        if (f4 > f) {
            return false;
        }
        float f5 = this.angle - axisAngle4f.angle;
        if (f5 < 0.0f) {
            f5 = -f5;
        }
        return f5 <= f;
    }

    public int hashCode() {
        long jFloatToIntBits = ((((((Float.floatToIntBits(this.x) + 31) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.z)) * 31) + Float.floatToIntBits(this.angle);
        return (int) (jFloatToIntBits ^ (jFloatToIntBits >> 32));
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
