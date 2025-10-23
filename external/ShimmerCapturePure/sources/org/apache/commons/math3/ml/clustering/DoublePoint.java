package org.apache.commons.math3.ml.clustering;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DoublePoint implements Clusterable, Serializable {
    private static final long serialVersionUID = 3946024775784901369L;
    private final double[] point;

    public DoublePoint(double[] dArr) {
        this.point = dArr;
    }

    public DoublePoint(int[] iArr) {
        this.point = new double[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            this.point[i] = iArr[i];
        }
    }

    @Override // org.apache.commons.math3.ml.clustering.Clusterable
    public double[] getPoint() {
        return this.point;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DoublePoint) {
            return Arrays.equals(this.point, ((DoublePoint) obj).point);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}
