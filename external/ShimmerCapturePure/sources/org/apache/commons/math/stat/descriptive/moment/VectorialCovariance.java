package org.apache.commons.math.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/descriptive/moment/VectorialCovariance.class */
public class VectorialCovariance implements Serializable {
    private static final long serialVersionUID = 4118372414238930270L;
    private final double[] sums;
    private final double[] productsSums;
    private final boolean isBiasCorrected;
    private long n = 0;

    public VectorialCovariance(int dimension, boolean isBiasCorrected) {
        this.sums = new double[dimension];
        this.productsSums = new double[(dimension * (dimension + 1)) / 2];
        this.isBiasCorrected = isBiasCorrected;
    }

    public void increment(double[] v) throws DimensionMismatchException {
        if (v.length != this.sums.length) {
            throw new DimensionMismatchException(v.length, this.sums.length);
        }
        int k = 0;
        for (int i = 0; i < v.length; i++) {
            double[] dArr = this.sums;
            int i2 = i;
            dArr[i2] = dArr[i2] + v[i];
            for (int j = 0; j <= i; j++) {
                double[] dArr2 = this.productsSums;
                int i3 = k;
                k++;
                dArr2[i3] = dArr2[i3] + (v[i] * v[j]);
            }
        }
        this.n++;
    }

    public RealMatrix getResult() throws MatrixIndexException {
        int dimension = this.sums.length;
        RealMatrix result = MatrixUtils.createRealMatrix(dimension, dimension);
        if (this.n > 1) {
            double c = 1.0d / (this.n * (this.isBiasCorrected ? this.n - 1 : this.n));
            int k = 0;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j <= i; j++) {
                    int i2 = k;
                    k++;
                    double e = c * ((this.n * this.productsSums[i2]) - (this.sums[i] * this.sums[j]));
                    result.setEntry(i, j, e);
                    result.setEntry(j, i, e);
                }
            }
        }
        return result;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.n = 0L;
        Arrays.fill(this.sums, 0.0d);
        Arrays.fill(this.productsSums, 0.0d);
    }

    public int hashCode() {
        int result = (31 * 1) + (this.isBiasCorrected ? 1231 : 1237);
        return (31 * ((31 * ((31 * result) + ((int) (this.n ^ (this.n >>> 32))))) + Arrays.hashCode(this.productsSums))) + Arrays.hashCode(this.sums);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance other = (VectorialCovariance) obj;
        if (this.isBiasCorrected != other.isBiasCorrected || this.n != other.n || !Arrays.equals(this.productsSums, other.productsSums) || !Arrays.equals(this.sums, other.sums)) {
            return false;
        }
        return true;
    }
}
