package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Vector4d extends Tuple4d implements Serializable {
    static final long serialVersionUID = 3938123424117448700L;

    public Vector4d(double d, double d2, double d3, double d4) {
        super(d, d2, d3, d4);
    }

    public Vector4d(double[] dArr) {
        super(dArr);
    }

    public Vector4d(Vector4d vector4d) {
        super(vector4d);
    }

    public Vector4d(Vector4f vector4f) {
        super(vector4f);
    }

    public Vector4d(Tuple4f tuple4f) {
        super(tuple4f);
    }

    public Vector4d(Tuple4d tuple4d) {
        super(tuple4d);
    }

    public Vector4d(Tuple3d tuple3d) {
        super(tuple3d.x, tuple3d.y, tuple3d.z, 0.0d);
    }

    public Vector4d() {
    }

    public final void set(Tuple3d tuple3d) {
        this.x = tuple3d.x;
        this.y = tuple3d.y;
        this.z = tuple3d.z;
        this.w = 0.0d;
    }

    public final double length() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
    }

    public final double lengthSquared() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
    }

    public final double dot(Vector4d vector4d) {
        return (this.x * vector4d.x) + (this.y * vector4d.y) + (this.z * vector4d.z) + (this.w * vector4d.w);
    }

    public final void normalize(Vector4d vector4d) {
        double dSqrt = 1.0d / Math.sqrt((((vector4d.x * vector4d.x) + (vector4d.y * vector4d.y)) + (vector4d.z * vector4d.z)) + (vector4d.w * vector4d.w));
        this.x = vector4d.x * dSqrt;
        this.y = vector4d.y * dSqrt;
        this.z = vector4d.z * dSqrt;
        this.w = vector4d.w * dSqrt;
    }

    public final void normalize() {
        double dSqrt = 1.0d / Math.sqrt((((this.x * this.x) + (this.y * this.y)) + (this.z * this.z)) + (this.w * this.w));
        this.x *= dSqrt;
        this.y *= dSqrt;
        this.z *= dSqrt;
        this.w *= dSqrt;
    }

    public final double angle(Vector4d vector4d) {
        double dDot = dot(vector4d) / (length() * vector4d.length());
        if (dDot < -1.0d) {
            dDot = -1.0d;
        }
        if (dDot > 1.0d) {
            dDot = 1.0d;
        }
        return Math.acos(dDot);
    }
}
