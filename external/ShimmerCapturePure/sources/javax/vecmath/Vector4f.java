package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Vector4f extends Tuple4f implements Serializable {
    static final long serialVersionUID = 8749319902347760659L;

    public Vector4f(float f, float f2, float f3, float f4) {
        super(f, f2, f3, f4);
    }

    public Vector4f(float[] fArr) {
        super(fArr);
    }

    public Vector4f(Vector4f vector4f) {
        super(vector4f);
    }

    public Vector4f(Vector4d vector4d) {
        super(vector4d);
    }

    public Vector4f(Tuple4f tuple4f) {
        super(tuple4f);
    }

    public Vector4f(Tuple4d tuple4d) {
        super(tuple4d);
    }

    public Vector4f(Tuple3f tuple3f) {
        super(tuple3f.x, tuple3f.y, tuple3f.z, 0.0f);
    }

    public Vector4f() {
    }

    public final void set(Tuple3f tuple3f) {
        this.x = tuple3f.x;
        this.y = tuple3f.y;
        this.z = tuple3f.z;
        this.w = 0.0f;
    }

    public final float length() {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
    }

    public final float lengthSquared() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
    }

    public final float dot(Vector4f vector4f) {
        return (this.x * vector4f.x) + (this.y * vector4f.y) + (this.z * vector4f.z) + (this.w * vector4f.w);
    }

    public final void normalize(Vector4f vector4f) {
        float fSqrt = (float) (1.0d / Math.sqrt((((vector4f.x * vector4f.x) + (vector4f.y * vector4f.y)) + (vector4f.z * vector4f.z)) + (vector4f.w * vector4f.w)));
        this.x = vector4f.x * fSqrt;
        this.y = vector4f.y * fSqrt;
        this.z = vector4f.z * fSqrt;
        this.w = vector4f.w * fSqrt;
    }

    public final void normalize() {
        float fSqrt = (float) (1.0d / Math.sqrt((((this.x * this.x) + (this.y * this.y)) + (this.z * this.z)) + (this.w * this.w)));
        this.x *= fSqrt;
        this.y *= fSqrt;
        this.z *= fSqrt;
        this.w *= fSqrt;
    }

    public final float angle(Vector4f vector4f) {
        double dDot = dot(vector4f) / (length() * vector4f.length());
        if (dDot < -1.0d) {
            dDot = -1.0d;
        }
        if (dDot > 1.0d) {
            dDot = 1.0d;
        }
        return (float) Math.acos(dDot);
    }
}
