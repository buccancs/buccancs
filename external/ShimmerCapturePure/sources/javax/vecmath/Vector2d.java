package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Vector2d extends Tuple2d implements Serializable {
    static final long serialVersionUID = 8572646365302599857L;

    public Vector2d(double d, double d2) {
        super(d, d2);
    }

    public Vector2d(double[] dArr) {
        super(dArr);
    }

    public Vector2d(Vector2d vector2d) {
        super(vector2d);
    }

    public Vector2d(Vector2f vector2f) {
        super(vector2f);
    }

    public Vector2d(Tuple2d tuple2d) {
        super(tuple2d);
    }

    public Vector2d(Tuple2f tuple2f) {
        super(tuple2f);
    }

    public Vector2d() {
    }

    public final double dot(Vector2d vector2d) {
        return (this.x * vector2d.x) + (this.y * vector2d.y);
    }

    public final double length() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public final double lengthSquared() {
        return (this.x * this.x) + (this.y * this.y);
    }

    public final void normalize(Vector2d vector2d) {
        double dSqrt = 1.0d / Math.sqrt((vector2d.x * vector2d.x) + (vector2d.y * vector2d.y));
        this.x = vector2d.x * dSqrt;
        this.y = vector2d.y * dSqrt;
    }

    public final void normalize() {
        double dSqrt = 1.0d / Math.sqrt((this.x * this.x) + (this.y * this.y));
        this.x *= dSqrt;
        this.y *= dSqrt;
    }

    public final double angle(Vector2d vector2d) {
        double dDot = dot(vector2d) / (length() * vector2d.length());
        if (dDot < -1.0d) {
            dDot = -1.0d;
        }
        if (dDot > 1.0d) {
            dDot = 1.0d;
        }
        return Math.acos(dDot);
    }
}
