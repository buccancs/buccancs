package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class Tuple4f implements Serializable, Cloneable {
    static final long serialVersionUID = 7068460319248845763L;
    public float w;
    public float x;
    public float y;
    public float z;

    public Tuple4f(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
    }

    public Tuple4f(float[] fArr) {
        this.x = fArr[0];
        this.y = fArr[1];
        this.z = fArr[2];
        this.w = fArr[3];
    }

    public Tuple4f(Tuple4f tuple4f) {
        this.x = tuple4f.x;
        this.y = tuple4f.y;
        this.z = tuple4f.z;
        this.w = tuple4f.w;
    }

    public Tuple4f(Tuple4d tuple4d) {
        this.x = (float) tuple4d.x;
        this.y = (float) tuple4d.y;
        this.z = (float) tuple4d.z;
        this.w = (float) tuple4d.w;
    }

    public Tuple4f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public final void clamp(float f, float f2) {
        float f3 = this.x;
        if (f3 > f2) {
            this.x = f2;
        } else if (f3 < f) {
            this.x = f;
        }
        float f4 = this.y;
        if (f4 > f2) {
            this.y = f2;
        } else if (f4 < f) {
            this.y = f;
        }
        float f5 = this.z;
        if (f5 > f2) {
            this.z = f2;
        } else if (f5 < f) {
            this.z = f;
        }
        float f6 = this.w;
        if (f6 > f2) {
            this.w = f2;
        } else if (f6 < f) {
            this.w = f;
        }
    }

    public final void clampMax(float f) {
        if (this.x > f) {
            this.x = f;
        }
        if (this.y > f) {
            this.y = f;
        }
        if (this.z > f) {
            this.z = f;
        }
        if (this.w > f) {
            this.w = f;
        }
    }

    public final void clampMin(float f) {
        if (this.x < f) {
            this.x = f;
        }
        if (this.y < f) {
            this.y = f;
        }
        if (this.z < f) {
            this.z = f;
        }
        if (this.w < f) {
            this.w = f;
        }
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    public final void scale(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
        this.w *= f;
    }

    public final void set(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
    }

    public final void set(float[] fArr) {
        this.x = fArr[0];
        this.y = fArr[1];
        this.z = fArr[2];
        this.w = fArr[3];
    }

    public final void set(Tuple4f tuple4f) {
        this.x = tuple4f.x;
        this.y = tuple4f.y;
        this.z = tuple4f.z;
        this.w = tuple4f.w;
    }

    public final void set(Tuple4d tuple4d) {
        this.x = (float) tuple4d.x;
        this.y = (float) tuple4d.y;
        this.z = (float) tuple4d.z;
        this.w = (float) tuple4d.w;
    }

    public final void get(float[] fArr) {
        fArr[0] = this.x;
        fArr[1] = this.y;
        fArr[2] = this.z;
        fArr[3] = this.w;
    }

    public final void get(Tuple4f tuple4f) {
        tuple4f.x = this.x;
        tuple4f.y = this.y;
        tuple4f.z = this.z;
        tuple4f.w = this.w;
    }

    public final void add(Tuple4f tuple4f, Tuple4f tuple4f2) {
        this.x = tuple4f.x + tuple4f2.x;
        this.y = tuple4f.y + tuple4f2.y;
        this.z = tuple4f.z + tuple4f2.z;
        this.w = tuple4f.w + tuple4f2.w;
    }

    public final void add(Tuple4f tuple4f) {
        this.x += tuple4f.x;
        this.y += tuple4f.y;
        this.z += tuple4f.z;
        this.w += tuple4f.w;
    }

    public final void sub(Tuple4f tuple4f, Tuple4f tuple4f2) {
        this.x = tuple4f.x - tuple4f2.x;
        this.y = tuple4f.y - tuple4f2.y;
        this.z = tuple4f.z - tuple4f2.z;
        this.w = tuple4f.w - tuple4f2.w;
    }

    public final void sub(Tuple4f tuple4f) {
        this.x -= tuple4f.x;
        this.y -= tuple4f.y;
        this.z -= tuple4f.z;
        this.w -= tuple4f.w;
    }

    public final void negate(Tuple4f tuple4f) {
        this.x = -tuple4f.x;
        this.y = -tuple4f.y;
        this.z = -tuple4f.z;
        this.w = -tuple4f.w;
    }

    public final void scale(float f, Tuple4f tuple4f) {
        this.x = tuple4f.x * f;
        this.y = tuple4f.y * f;
        this.z = tuple4f.z * f;
        this.w = f * tuple4f.w;
    }

    public final void scaleAdd(float f, Tuple4f tuple4f, Tuple4f tuple4f2) {
        this.x = (tuple4f.x * f) + tuple4f2.x;
        this.y = (tuple4f.y * f) + tuple4f2.y;
        this.z = (tuple4f.z * f) + tuple4f2.z;
        this.w = (f * tuple4f.w) + tuple4f2.w;
    }

    public final void scaleAdd(float f, Tuple4f tuple4f) {
        this.x = (this.x * f) + tuple4f.x;
        this.y = (this.y * f) + tuple4f.y;
        this.z = (this.z * f) + tuple4f.z;
        this.w = (f * this.w) + tuple4f.w;
    }

    public String toString() {
        return new StringBuffer("(").append(this.x).append(", ").append(this.y).append(", ").append(this.z).append(", ").append(this.w).append(")").toString();
    }

    public boolean equals(Tuple4f tuple4f) {
        try {
            if (this.x == tuple4f.x && this.y == tuple4f.y && this.z == tuple4f.z) {
                return this.w == tuple4f.w;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Tuple4f tuple4f = (Tuple4f) obj;
            if (this.x == tuple4f.x && this.y == tuple4f.y && this.z == tuple4f.z) {
                return this.w == tuple4f.w;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public boolean epsilonEquals(Tuple4f tuple4f, float f) {
        float f2 = this.x - tuple4f.x;
        if (f2 < 0.0f) {
            f2 = -f2;
        }
        if (f2 > f) {
            return false;
        }
        float f3 = this.y - tuple4f.y;
        if (f3 < 0.0f) {
            f3 = -f3;
        }
        if (f3 > f) {
            return false;
        }
        float f4 = this.z - tuple4f.z;
        if (f4 < 0.0f) {
            f4 = -f4;
        }
        if (f4 > f) {
            return false;
        }
        float f5 = this.w - tuple4f.w;
        if (f5 < 0.0f) {
            f5 = -f5;
        }
        return f5 <= f;
    }

    public int hashCode() {
        long jFloatToIntBits = ((((((Float.floatToIntBits(this.x) + 31) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.z)) * 31) + Float.floatToIntBits(this.w);
        return (int) (jFloatToIntBits ^ (jFloatToIntBits >> 32));
    }

    public final void clamp(float f, float f2, Tuple4f tuple4f) {
        float f3 = tuple4f.x;
        if (f3 > f2) {
            this.x = f2;
        } else if (f3 < f) {
            this.x = f;
        } else {
            this.x = f3;
        }
        float f4 = tuple4f.y;
        if (f4 > f2) {
            this.y = f2;
        } else if (f4 < f) {
            this.y = f;
        } else {
            this.y = f4;
        }
        float f5 = tuple4f.z;
        if (f5 > f2) {
            this.z = f2;
        } else if (f5 < f) {
            this.z = f;
        } else {
            this.z = f5;
        }
        float f6 = tuple4f.w;
        if (f6 > f2) {
            this.w = f2;
        } else if (f6 < f) {
            this.w = f;
        } else {
            this.w = f6;
        }
    }

    public final void clampMin(float f, Tuple4f tuple4f) {
        float f2 = tuple4f.x;
        if (f2 < f) {
            this.x = f;
        } else {
            this.x = f2;
        }
        float f3 = tuple4f.y;
        if (f3 < f) {
            this.y = f;
        } else {
            this.y = f3;
        }
        float f4 = tuple4f.z;
        if (f4 < f) {
            this.z = f;
        } else {
            this.z = f4;
        }
        float f5 = tuple4f.w;
        if (f5 < f) {
            this.w = f;
        } else {
            this.w = f5;
        }
    }

    public final void clampMax(float f, Tuple4f tuple4f) {
        float f2 = tuple4f.x;
        if (f2 > f) {
            this.x = f;
        } else {
            this.x = f2;
        }
        float f3 = tuple4f.y;
        if (f3 > f) {
            this.y = f;
        } else {
            this.y = f3;
        }
        float f4 = tuple4f.z;
        if (f4 > f) {
            this.z = f;
        } else {
            this.z = f4;
        }
        if (tuple4f.w > f) {
            this.w = f;
        } else {
            this.w = tuple4f.z;
        }
    }

    public final void absolute(Tuple4f tuple4f) {
        this.x = Math.abs(tuple4f.x);
        this.y = Math.abs(tuple4f.y);
        this.z = Math.abs(tuple4f.z);
        this.w = Math.abs(tuple4f.w);
    }

    public final void absolute() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        this.z = Math.abs(this.z);
        this.w = Math.abs(this.w);
    }

    public void interpolate(Tuple4f tuple4f, Tuple4f tuple4f2, float f) {
        float f2 = 1.0f - f;
        this.x = (tuple4f.x * f2) + (tuple4f2.x * f);
        this.y = (tuple4f.y * f2) + (tuple4f2.y * f);
        this.z = (tuple4f.z * f2) + (tuple4f2.z * f);
        this.w = (f2 * tuple4f.w) + (f * tuple4f2.w);
    }

    public void interpolate(Tuple4f tuple4f, float f) {
        float f2 = 1.0f - f;
        this.x = (this.x * f2) + (tuple4f.x * f);
        this.y = (this.y * f2) + (tuple4f.y * f);
        this.z = (this.z * f2) + (tuple4f.z * f);
        this.w = (f2 * this.w) + (f * tuple4f.w);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
