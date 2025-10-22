package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public abstract class Tuple4b implements Serializable, Cloneable {
    static final long serialVersionUID = -8226727741811898211L;
    public byte w;
    public byte x;
    public byte y;
    public byte z;

    public Tuple4b(byte b, byte b2, byte b3, byte b4) {
        this.x = b;
        this.y = b2;
        this.z = b3;
        this.w = b4;
    }

    public Tuple4b(byte[] bArr) {
        this.x = bArr[0];
        this.y = bArr[1];
        this.z = bArr[2];
        this.w = bArr[3];
    }

    public Tuple4b(Tuple4b tuple4b) {
        this.x = tuple4b.x;
        this.y = tuple4b.y;
        this.z = tuple4b.z;
        this.w = tuple4b.w;
    }

    public Tuple4b() {
        this.x = (byte) 0;
        this.y = (byte) 0;
        this.z = (byte) 0;
        this.w = (byte) 0;
    }

    public int hashCode() {
        return (this.x & 255) | ((this.y & 255) << 8) | ((this.z & 255) << 16) | ((this.w & 255) << 24);
    }

    public String toString() {
        return new StringBuffer("(").append(this.x & 255).append(", ").append(this.y & 255).append(", ").append(this.z & 255).append(", ").append(this.w & 255).append(")").toString();
    }

    public final void get(byte[] bArr) {
        bArr[0] = this.x;
        bArr[1] = this.y;
        bArr[2] = this.z;
        bArr[3] = this.w;
    }

    public final void get(Tuple4b tuple4b) {
        tuple4b.x = this.x;
        tuple4b.y = this.y;
        tuple4b.z = this.z;
        tuple4b.w = this.w;
    }

    public final void set(Tuple4b tuple4b) {
        this.x = tuple4b.x;
        this.y = tuple4b.y;
        this.z = tuple4b.z;
        this.w = tuple4b.w;
    }

    public final void set(byte[] bArr) {
        this.x = bArr[0];
        this.y = bArr[1];
        this.z = bArr[2];
        this.w = bArr[3];
    }

    public boolean equals(Tuple4b tuple4b) {
        try {
            if (this.x == tuple4b.x && this.y == tuple4b.y && this.z == tuple4b.z) {
                return this.w == tuple4b.w;
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            Tuple4b tuple4b = (Tuple4b) obj;
            if (this.x == tuple4b.x && this.y == tuple4b.y && this.z == tuple4b.z) {
                return this.w == tuple4b.w;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
