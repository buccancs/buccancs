package org.apache.commons.math.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math.DimensionMismatchException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/moment/VectorialMean.class */
public class VectorialMean implements Serializable {
    private static final long serialVersionUID = 8223009086481006892L;
    private final Mean[] means;

    public VectorialMean(int dimension) {
        this.means = new Mean[dimension];
        for (int i = 0; i < dimension; i++) {
            this.means[i] = new Mean();
        }
    }

    public void increment(double[] v) throws DimensionMismatchException {
        if (v.length != this.means.length) {
            throw new DimensionMismatchException(v.length, this.means.length);
        }
        for (int i = 0; i < v.length; i++) {
            this.means[i].increment(v[i]);
        }
    }

    public double[] getResult() {
        double[] result = new double[this.means.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.means[i].getResult();
        }
        return result;
    }

    public long getN() {
        if (this.means.length == 0) {
            return 0L;
        }
        return this.means[0].getN();
    }

    public int hashCode() {
        int result = (31 * 1) + Arrays.hashCode(this.means);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialMean)) {
            return false;
        }
        VectorialMean other = (VectorialMean) obj;
        if (!Arrays.equals(this.means, other.means)) {
            return false;
        }
        return true;
    }
}
