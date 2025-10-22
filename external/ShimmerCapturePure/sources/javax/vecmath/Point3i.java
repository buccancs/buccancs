package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Point3i extends Tuple3i implements Serializable {
    static final long serialVersionUID = 6149289077348153921L;

    public Point3i(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    public Point3i(int[] iArr) {
        super(iArr);
    }

    public Point3i(Tuple3i tuple3i) {
        super(tuple3i);
    }

    public Point3i() {
    }
}
