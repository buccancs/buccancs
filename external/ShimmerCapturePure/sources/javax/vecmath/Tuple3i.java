package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class Tuple3i implements Serializable, Cloneable {
    static final long serialVersionUID = -732740491767276200L;
    public int x;
    public int y;
    public int z;

    public Tuple3i(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.z = i3;
    }

    public Tuple3i(int[] iArr) {
        this.x = iArr[0];
        this.y = iArr[1];
        this.z = iArr[2];
    }

    public Tuple3i(Tuple3i tuple3i) {
        this.x = tuple3i.x;
        this.y = tuple3i.y;
        this.z = tuple3i.z;
    }

    public Tuple3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public final void clamp(int i, int i2) {
        int i3 = this.x;
        if (i3 > i2) {
            this.x = i2;
        } else if (i3 < i) {
            this.x = i;
        }
        int i4 = this.y;
        if (i4 > i2) {
            this.y = i2;
        } else if (i4 < i) {
            this.y = i;
        }
        int i5 = this.z;
        if (i5 > i2) {
            this.z = i2;
        } else if (i5 < i) {
            this.z = i;
        }
    }

    public final void clampMax(int i) {
        if (this.x > i) {
            this.x = i;
        }
        if (this.y > i) {
            this.y = i;
        }
        if (this.z > i) {
            this.z = i;
        }
    }

    public final void clampMin(int i) {
        if (this.x < i) {
            this.x = i;
        }
        if (this.y < i) {
            this.y = i;
        }
        if (this.z < i) {
            this.z = i;
        }
    }

    public int hashCode() {
        long j = ((((this.x + 31) * 31) + this.y) * 31) + this.z;
        return (int) (j ^ (j >> 32));
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public final void scale(int i) {
        this.x *= i;
        this.y *= i;
        this.z *= i;
    }

    public final void set(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.z = i3;
    }

    public final void set(int[] iArr) {
        this.x = iArr[0];
        this.y = iArr[1];
        this.z = iArr[2];
    }

    public final void set(Tuple3i tuple3i) {
        this.x = tuple3i.x;
        this.y = tuple3i.y;
        this.z = tuple3i.z;
    }

    public final void get(int[] iArr) {
        iArr[0] = this.x;
        iArr[1] = this.y;
        iArr[2] = this.z;
    }

    public final void get(Tuple3i tuple3i) {
        tuple3i.x = this.x;
        tuple3i.y = this.y;
        tuple3i.z = this.z;
    }

    public final void add(Tuple3i tuple3i, Tuple3i tuple3i2) {
        this.x = tuple3i.x + tuple3i2.x;
        this.y = tuple3i.y + tuple3i2.y;
        this.z = tuple3i.z + tuple3i2.z;
    }

    public final void add(Tuple3i tuple3i) {
        this.x += tuple3i.x;
        this.y += tuple3i.y;
        this.z += tuple3i.z;
    }

    public final void sub(Tuple3i tuple3i, Tuple3i tuple3i2) {
        this.x = tuple3i.x - tuple3i2.x;
        this.y = tuple3i.y - tuple3i2.y;
        this.z = tuple3i.z - tuple3i2.z;
    }

    public final void sub(Tuple3i tuple3i) {
        this.x -= tuple3i.x;
        this.y -= tuple3i.y;
        this.z -= tuple3i.z;
    }

    public final void negate(Tuple3i tuple3i) {
        this.x = -tuple3i.x;
        this.y = -tuple3i.y;
        this.z = -tuple3i.z;
    }

    public final void scale(int i, Tuple3i tuple3i) {
        this.x = tuple3i.x * i;
        this.y = tuple3i.y * i;
        this.z = i * tuple3i.z;
    }

    public final void scaleAdd(int i, Tuple3i tuple3i, Tuple3i tuple3i2) {
        this.x = (tuple3i.x * i) + tuple3i2.x;
        this.y = (tuple3i.y * i) + tuple3i2.y;
        this.z = (i * tuple3i.z) + tuple3i2.z;
    }

    public final void scaleAdd(int i, Tuple3i tuple3i) {
        this.x = (this.x * i) + tuple3i.x;
        this.y = (this.y * i) + tuple3i.y;
        this.z = (i * this.z) + tuple3i.z;
    }

    public String toString() {
        return new StringBuffer("(").append(this.x).append(", ").append(this.y).append(", ").append(this.z).append(")").toString();
    }

    public boolean equals(Object obj) {
        try {
            Tuple3i tuple3i = (Tuple3i) obj;
            if (this.x == tuple3i.x && this.y == tuple3i.y) {
                return this.z == tuple3i.z;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final void clamp(int i, int i2, Tuple3i tuple3i) {
        int i3 = tuple3i.x;
        if (i3 > i2) {
            this.x = i2;
        } else if (i3 < i) {
            this.x = i;
        } else {
            this.x = i3;
        }
        int i4 = tuple3i.y;
        if (i4 > i2) {
            this.y = i2;
        } else if (i4 < i) {
            this.y = i;
        } else {
            this.y = i4;
        }
        int i5 = tuple3i.z;
        if (i5 > i2) {
            this.z = i2;
        } else if (i5 < i) {
            this.z = i;
        } else {
            this.z = i5;
        }
    }

    public final void clampMin(int i, Tuple3i tuple3i) {
        int i2 = tuple3i.x;
        if (i2 < i) {
            this.x = i;
        } else {
            this.x = i2;
        }
        int i3 = tuple3i.y;
        if (i3 < i) {
            this.y = i;
        } else {
            this.y = i3;
        }
        int i4 = tuple3i.z;
        if (i4 < i) {
            this.z = i;
        } else {
            this.z = i4;
        }
    }

    public final void clampMax(int i, Tuple3i tuple3i) {
        int i2 = tuple3i.x;
        if (i2 > i) {
            this.x = i;
        } else {
            this.x = i2;
        }
        int i3 = tuple3i.y;
        if (i3 > i) {
            this.y = i;
        } else {
            this.y = i3;
        }
        int i4 = tuple3i.z;
        if (i4 > i) {
            this.z = i;
        } else {
            this.z = i4;
        }
    }

    public final void absolute(Tuple3i tuple3i) {
        this.x = Math.abs(tuple3i.x);
        this.y = Math.abs(tuple3i.y);
        this.z = Math.abs(tuple3i.z);
    }

    public final void absolute() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        this.z = Math.abs(this.z);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
