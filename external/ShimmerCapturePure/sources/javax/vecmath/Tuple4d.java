package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class Tuple4d implements Serializable, Cloneable {
    static final long serialVersionUID = -4748953690425311052L;
    public double w;
    public double x;
    public double y;
    public double z;

    public Tuple4d(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.w = d4;
    }

    public Tuple4d(double[] dArr) {
        this.x = dArr[0];
        this.y = dArr[1];
        this.z = dArr[2];
        this.w = dArr[3];
    }

    public Tuple4d(Tuple4d tuple4d) {
        this.x = tuple4d.x;
        this.y = tuple4d.y;
        this.z = tuple4d.z;
        this.w = tuple4d.w;
    }

    public Tuple4d(Tuple4f tuple4f) {
        this.x = tuple4f.x;
        this.y = tuple4f.y;
        this.z = tuple4f.z;
        this.w = tuple4f.w;
    }

    public Tuple4d() {
        this.x = 0.0d;
        this.y = 0.0d;
        this.z = 0.0d;
        this.w = 0.0d;
    }

    public final void clamp(double d, double d2) {
        double d3 = this.x;
        if (d3 > d2) {
            this.x = d2;
        } else if (d3 < d) {
            this.x = d;
        }
        double d4 = this.y;
        if (d4 > d2) {
            this.y = d2;
        } else if (d4 < d) {
            this.y = d;
        }
        double d5 = this.z;
        if (d5 > d2) {
            this.z = d2;
        } else if (d5 < d) {
            this.z = d;
        }
        double d6 = this.w;
        if (d6 > d2) {
            this.w = d2;
        } else if (d6 < d) {
            this.w = d;
        }
    }

    public final void clampMax(double d) {
        if (this.x > d) {
            this.x = d;
        }
        if (this.y > d) {
            this.y = d;
        }
        if (this.z > d) {
            this.z = d;
        }
        if (this.w > d) {
            this.w = d;
        }
    }

    public final void clampMin(double d) {
        if (this.x < d) {
            this.x = d;
        }
        if (this.y < d) {
            this.y = d;
        }
        if (this.z < d) {
            this.z = d;
        }
        if (this.w < d) {
            this.w = d;
        }
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    public final void scale(double d) {
        this.x *= d;
        this.y *= d;
        this.z *= d;
        this.w *= d;
    }

    public final void set(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.w = d4;
    }

    public final void set(double[] dArr) {
        this.x = dArr[0];
        this.y = dArr[1];
        this.z = dArr[2];
        this.w = dArr[3];
    }

    public final void set(Tuple4d tuple4d) {
        this.x = tuple4d.x;
        this.y = tuple4d.y;
        this.z = tuple4d.z;
        this.w = tuple4d.w;
    }

    public final void set(Tuple4f tuple4f) {
        this.x = tuple4f.x;
        this.y = tuple4f.y;
        this.z = tuple4f.z;
        this.w = tuple4f.w;
    }

    public final void get(double[] dArr) {
        dArr[0] = this.x;
        dArr[1] = this.y;
        dArr[2] = this.z;
        dArr[3] = this.w;
    }

    public final void get(Tuple4d tuple4d) {
        tuple4d.x = this.x;
        tuple4d.y = this.y;
        tuple4d.z = this.z;
        tuple4d.w = this.w;
    }

    public final void add(Tuple4d tuple4d, Tuple4d tuple4d2) {
        this.x = tuple4d.x + tuple4d2.x;
        this.y = tuple4d.y + tuple4d2.y;
        this.z = tuple4d.z + tuple4d2.z;
        this.w = tuple4d.w + tuple4d2.w;
    }

    public final void add(Tuple4d tuple4d) {
        this.x += tuple4d.x;
        this.y += tuple4d.y;
        this.z += tuple4d.z;
        this.w += tuple4d.w;
    }

    public final void sub(Tuple4d tuple4d, Tuple4d tuple4d2) {
        this.x = tuple4d.x - tuple4d2.x;
        this.y = tuple4d.y - tuple4d2.y;
        this.z = tuple4d.z - tuple4d2.z;
        this.w = tuple4d.w - tuple4d2.w;
    }

    public final void sub(Tuple4d tuple4d) {
        this.x -= tuple4d.x;
        this.y -= tuple4d.y;
        this.z -= tuple4d.z;
        this.w -= tuple4d.w;
    }

    public final void negate(Tuple4d tuple4d) {
        this.x = -tuple4d.x;
        this.y = -tuple4d.y;
        this.z = -tuple4d.z;
        this.w = -tuple4d.w;
    }

    public final void scale(double d, Tuple4d tuple4d) {
        this.x = tuple4d.x * d;
        this.y = tuple4d.y * d;
        this.z = tuple4d.z * d;
        this.w = d * tuple4d.w;
    }

    public final void scaleAdd(double d, Tuple4d tuple4d, Tuple4d tuple4d2) {
        this.x = (tuple4d.x * d) + tuple4d2.x;
        this.y = (tuple4d.y * d) + tuple4d2.y;
        this.z = (tuple4d.z * d) + tuple4d2.z;
        this.w = (d * tuple4d.w) + tuple4d2.w;
    }

    public final void scaleAdd(float f, Tuple4d tuple4d) {
        scaleAdd(f, tuple4d);
    }

    public final void scaleAdd(double d, Tuple4d tuple4d) {
        this.x = (this.x * d) + tuple4d.x;
        this.y = (this.y * d) + tuple4d.y;
        this.z = (this.z * d) + tuple4d.z;
        this.w = (d * this.w) + tuple4d.w;
    }

    public String toString() {
        return new StringBuffer("(").append(this.x).append(", ").append(this.y).append(", ").append(this.z).append(", ").append(this.w).append(")").toString();
    }

    public boolean equals(Tuple4d tuple4d) {
        try {
            if (this.x == tuple4d.x && this.y == tuple4d.y && this.z == tuple4d.z) {
                return this.w == tuple4d.w;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Tuple4d tuple4d = (Tuple4d) obj;
            if (this.x == tuple4d.x && this.y == tuple4d.y && this.z == tuple4d.z) {
                return this.w == tuple4d.w;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(Tuple4d tuple4d, double d) {
        double d2 = this.x - tuple4d.x;
        if (d2 < 0.0d) {
            d2 = -d2;
        }
        if (d2 > d) {
            return false;
        }
        double d3 = this.y - tuple4d.y;
        if (d3 < 0.0d) {
            d3 = -d3;
        }
        if (d3 > d) {
            return false;
        }
        double d4 = this.z - tuple4d.z;
        if (d4 < 0.0d) {
            d4 = -d4;
        }
        if (d4 > d) {
            return false;
        }
        double d5 = this.w - tuple4d.w;
        if (d5 < 0.0d) {
            d5 = -d5;
        }
        return d5 <= d;
    }

    public int hashCode() {
        long jDoubleToLongBits = ((((((Double.doubleToLongBits(this.x) + 31) * 31) + Double.doubleToLongBits(this.y)) * 31) + Double.doubleToLongBits(this.z)) * 31) + Double.doubleToLongBits(this.w);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >> 32));
    }

    public final void clamp(float f, float f2, Tuple4d tuple4d) {
        clamp(f, f2, tuple4d);
    }

    public final void clamp(double d, double d2, Tuple4d tuple4d) {
        double d3 = tuple4d.x;
        if (d3 > d2) {
            this.x = d2;
        } else if (d3 < d) {
            this.x = d;
        } else {
            this.x = d3;
        }
        double d4 = tuple4d.y;
        if (d4 > d2) {
            this.y = d2;
        } else if (d4 < d) {
            this.y = d;
        } else {
            this.y = d4;
        }
        double d5 = tuple4d.z;
        if (d5 > d2) {
            this.z = d2;
        } else if (d5 < d) {
            this.z = d;
        } else {
            this.z = d5;
        }
        double d6 = tuple4d.w;
        if (d6 > d2) {
            this.w = d2;
        } else if (d6 < d) {
            this.w = d;
        } else {
            this.w = d6;
        }
    }

    public final void clampMin(float f, Tuple4d tuple4d) {
        clampMin(f, tuple4d);
    }

    public final void clampMin(double d, Tuple4d tuple4d) {
        double d2 = tuple4d.x;
        if (d2 < d) {
            this.x = d;
        } else {
            this.x = d2;
        }
        double d3 = tuple4d.y;
        if (d3 < d) {
            this.y = d;
        } else {
            this.y = d3;
        }
        double d4 = tuple4d.z;
        if (d4 < d) {
            this.z = d;
        } else {
            this.z = d4;
        }
        double d5 = tuple4d.w;
        if (d5 < d) {
            this.w = d;
        } else {
            this.w = d5;
        }
    }

    public final void clampMax(float f, Tuple4d tuple4d) {
        clampMax(f, tuple4d);
    }

    public final void clampMax(double d, Tuple4d tuple4d) {
        double d2 = tuple4d.x;
        if (d2 > d) {
            this.x = d;
        } else {
            this.x = d2;
        }
        double d3 = tuple4d.y;
        if (d3 > d) {
            this.y = d;
        } else {
            this.y = d3;
        }
        double d4 = tuple4d.z;
        if (d4 > d) {
            this.z = d;
        } else {
            this.z = d4;
        }
        if (tuple4d.w > d) {
            this.w = d;
        } else {
            this.w = tuple4d.z;
        }
    }

    public final void absolute(Tuple4d tuple4d) {
        this.x = Math.abs(tuple4d.x);
        this.y = Math.abs(tuple4d.y);
        this.z = Math.abs(tuple4d.z);
        this.w = Math.abs(tuple4d.w);
    }

    public final void clamp(float f, float f2) {
        clamp(f, f2);
    }

    public final void clampMin(float f) {
        clampMin(f);
    }

    public final void clampMax(float f) {
        clampMax(f);
    }

    public final void absolute() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        this.z = Math.abs(this.z);
        this.w = Math.abs(this.w);
    }

    public void interpolate(Tuple4d tuple4d, Tuple4d tuple4d2, float f) {
        interpolate(tuple4d, tuple4d2, f);
    }

    public void interpolate(Tuple4d tuple4d, Tuple4d tuple4d2, double d) {
        double d2 = 1.0d - d;
        this.x = (tuple4d.x * d2) + (tuple4d2.x * d);
        this.y = (tuple4d.y * d2) + (tuple4d2.y * d);
        this.z = (tuple4d.z * d2) + (tuple4d2.z * d);
        this.w = (d2 * tuple4d.w) + (d * tuple4d2.w);
    }

    public void interpolate(Tuple4d tuple4d, float f) {
        interpolate(tuple4d, f);
    }

    public void interpolate(Tuple4d tuple4d, double d) {
        double d2 = 1.0d - d;
        this.x = (this.x * d2) + (tuple4d.x * d);
        this.y = (this.y * d2) + (tuple4d.y * d);
        this.z = (this.z * d2) + (tuple4d.z * d);
        this.w = (d2 * this.w) + (d * tuple4d.w);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
