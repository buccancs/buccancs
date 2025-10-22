package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class Tuple2d implements Serializable, Cloneable {
    static final long serialVersionUID = 6205762482756093838L;
    public double x;
    public double y;

    public Tuple2d(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public Tuple2d(double[] dArr) {
        this.x = dArr[0];
        this.y = dArr[1];
    }

    public Tuple2d(Tuple2d tuple2d) {
        this.x = tuple2d.x;
        this.y = tuple2d.y;
    }

    public Tuple2d(Tuple2f tuple2f) {
        this.x = tuple2f.x;
        this.y = tuple2f.y;
    }

    public Tuple2d() {
        this.x = 0.0d;
        this.y = 0.0d;
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
    }

    public final void clampMax(double d) {
        if (this.x > d) {
            this.x = d;
        }
        if (this.y > d) {
            this.y = d;
        }
    }

    public final void clampMin(double d) {
        if (this.x < d) {
            this.x = d;
        }
        if (this.y < d) {
            this.y = d;
        }
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public final void scale(double d) {
        this.x *= d;
        this.y *= d;
    }

    public final void set(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public final void set(double[] dArr) {
        this.x = dArr[0];
        this.y = dArr[1];
    }

    public final void set(Tuple2d tuple2d) {
        this.x = tuple2d.x;
        this.y = tuple2d.y;
    }

    public final void set(Tuple2f tuple2f) {
        this.x = tuple2f.x;
        this.y = tuple2f.y;
    }

    public final void get(double[] dArr) {
        dArr[0] = this.x;
        dArr[1] = this.y;
    }

    public final void add(Tuple2d tuple2d, Tuple2d tuple2d2) {
        this.x = tuple2d.x + tuple2d2.x;
        this.y = tuple2d.y + tuple2d2.y;
    }

    public final void add(Tuple2d tuple2d) {
        this.x += tuple2d.x;
        this.y += tuple2d.y;
    }

    public final void sub(Tuple2d tuple2d, Tuple2d tuple2d2) {
        this.x = tuple2d.x - tuple2d2.x;
        this.y = tuple2d.y - tuple2d2.y;
    }

    public final void sub(Tuple2d tuple2d) {
        this.x -= tuple2d.x;
        this.y -= tuple2d.y;
    }

    public final void negate(Tuple2d tuple2d) {
        this.x = -tuple2d.x;
        this.y = -tuple2d.y;
    }

    public final void scale(double d, Tuple2d tuple2d) {
        this.x = tuple2d.x * d;
        this.y = d * tuple2d.y;
    }

    public final void scaleAdd(double d, Tuple2d tuple2d, Tuple2d tuple2d2) {
        this.x = (tuple2d.x * d) + tuple2d2.x;
        this.y = (d * tuple2d.y) + tuple2d2.y;
    }

    public final void scaleAdd(double d, Tuple2d tuple2d) {
        this.x = (this.x * d) + tuple2d.x;
        this.y = (d * this.y) + tuple2d.y;
    }

    public int hashCode() {
        long jDoubleToLongBits = ((Double.doubleToLongBits(this.x) + 31) * 31) + Double.doubleToLongBits(this.y);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >> 32));
    }

    public boolean equals(Tuple2d tuple2d) {
        try {
            if (this.x == tuple2d.x) {
                return this.y == tuple2d.y;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Tuple2d tuple2d = (Tuple2d) obj;
            if (this.x == tuple2d.x) {
                return this.y == tuple2d.y;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(Tuple2d tuple2d, double d) {
        double d2 = this.x - tuple2d.x;
        if (d2 < 0.0d) {
            d2 = -d2;
        }
        if (d2 > d) {
            return false;
        }
        double d3 = this.y - tuple2d.y;
        if (d3 < 0.0d) {
            d3 = -d3;
        }
        return d3 <= d;
    }

    public String toString() {
        return new StringBuffer("(").append(this.x).append(", ").append(this.y).append(")").toString();
    }

    public final void clamp(double d, double d2, Tuple2d tuple2d) {
        double d3 = tuple2d.x;
        if (d3 > d2) {
            this.x = d2;
        } else if (d3 < d) {
            this.x = d;
        } else {
            this.x = d3;
        }
        double d4 = tuple2d.y;
        if (d4 > d2) {
            this.y = d2;
        } else if (d4 < d) {
            this.y = d;
        } else {
            this.y = d4;
        }
    }

    public final void clampMin(double d, Tuple2d tuple2d) {
        double d2 = tuple2d.x;
        if (d2 < d) {
            this.x = d;
        } else {
            this.x = d2;
        }
        double d3 = tuple2d.y;
        if (d3 < d) {
            this.y = d;
        } else {
            this.y = d3;
        }
    }

    public final void clampMax(double d, Tuple2d tuple2d) {
        double d2 = tuple2d.x;
        if (d2 > d) {
            this.x = d;
        } else {
            this.x = d2;
        }
        double d3 = tuple2d.y;
        if (d3 > d) {
            this.y = d;
        } else {
            this.y = d3;
        }
    }

    public final void absolute(Tuple2d tuple2d) {
        this.x = Math.abs(tuple2d.x);
        this.y = Math.abs(tuple2d.y);
    }

    public final void absolute() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
    }

    public final void interpolate(Tuple2d tuple2d, Tuple2d tuple2d2, double d) {
        double d2 = 1.0d - d;
        this.x = (tuple2d.x * d2) + (tuple2d2.x * d);
        this.y = (d2 * tuple2d.y) + (d * tuple2d2.y);
    }

    public final void interpolate(Tuple2d tuple2d, double d) {
        double d2 = 1.0d - d;
        this.x = (this.x * d2) + (tuple2d.x * d);
        this.y = (d2 * this.y) + (d * tuple2d.y);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
