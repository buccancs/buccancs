package org.apache.commons.math3.stat.correlation;

import java.lang.reflect.Array;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes5.dex */
public class StorelessCovariance extends Covariance {
    private StorelessBivariateCovariance[] covMatrix;
    private int dimension;

    public StorelessCovariance(int i) {
        this(i, true);
    }

    public StorelessCovariance(int i, boolean z) {
        this.dimension = i;
        this.covMatrix = new StorelessBivariateCovariance[(i * (i + 1)) / 2];
        initializeMatrix(z);
    }

    private void initializeMatrix(boolean z) {
        for (int i = 0; i < this.dimension; i++) {
            for (int i2 = 0; i2 < this.dimension; i2++) {
                setElement(i, i2, new StorelessBivariateCovariance(z));
            }
        }
    }

    private int indexOf(int i, int i2) {
        return i2 < i ? ((i * (i + 1)) / 2) + i2 : i + ((i2 * (i2 + 1)) / 2);
    }

    private StorelessBivariateCovariance getElement(int i, int i2) {
        return this.covMatrix[indexOf(i, i2)];
    }

    private void setElement(int i, int i2, StorelessBivariateCovariance storelessBivariateCovariance) {
        this.covMatrix[indexOf(i, i2)] = storelessBivariateCovariance;
    }

    public double getCovariance(int i, int i2) throws NumberIsTooSmallException {
        return getElement(i, i2).getResult();
    }

    public void increment(double[] dArr) throws DimensionMismatchException {
        int length = dArr.length;
        if (length != this.dimension) {
            throw new DimensionMismatchException(length, this.dimension);
        }
        for (int i = 0; i < length; i++) {
            for (int i2 = i; i2 < length; i2++) {
                getElement(i, i2).increment(dArr[i], dArr[i2]);
            }
        }
    }

    public void append(StorelessCovariance storelessCovariance) throws DimensionMismatchException {
        if (storelessCovariance.dimension != this.dimension) {
            throw new DimensionMismatchException(storelessCovariance.dimension, this.dimension);
        }
        for (int i = 0; i < this.dimension; i++) {
            for (int i2 = i; i2 < this.dimension; i2++) {
                getElement(i, i2).append(storelessCovariance.getElement(i, i2));
            }
        }
    }

    @Override // org.apache.commons.math3.stat.correlation.Covariance
    public RealMatrix getCovarianceMatrix() throws NumberIsTooSmallException {
        return MatrixUtils.createRealMatrix(getData());
    }

    public double[][] getData() throws NumberIsTooSmallException {
        int i = this.dimension;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i);
        for (int i2 = 0; i2 < this.dimension; i2++) {
            for (int i3 = 0; i3 < this.dimension; i3++) {
                dArr[i2][i3] = getElement(i2, i3).getResult();
            }
        }
        return dArr;
    }

    @Override // org.apache.commons.math3.stat.correlation.Covariance
    public int getN() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }
}
