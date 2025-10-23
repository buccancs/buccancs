package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class Point4i extends Tuple4i implements Serializable {
    static final long serialVersionUID = 620124780244617983L;

    public Point4i(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public Point4i(int[] iArr) {
        super(iArr);
    }

    public Point4i(Tuple4i tuple4i) {
        super(tuple4i);
    }

    public Point4i() {
    }
}
