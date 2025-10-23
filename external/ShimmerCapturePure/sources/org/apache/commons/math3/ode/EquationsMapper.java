package org.apache.commons.math3.ode;

import java.io.Serializable;

import org.apache.commons.math3.exception.DimensionMismatchException;

/* loaded from: classes5.dex */
public class EquationsMapper implements Serializable {
    private static final long serialVersionUID = 20110925;
    private final int dimension;
    private final int firstIndex;

    public EquationsMapper(int i, int i2) {
        this.firstIndex = i;
        this.dimension = i2;
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getFirstIndex() {
        return this.firstIndex;
    }

    public void extractEquationData(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        int length = dArr2.length;
        int i = this.dimension;
        if (length != i) {
            throw new DimensionMismatchException(dArr2.length, this.dimension);
        }
        System.arraycopy(dArr, this.firstIndex, dArr2, 0, i);
    }

    public void insertEquationData(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        int length = dArr.length;
        int i = this.dimension;
        if (length != i) {
            throw new DimensionMismatchException(dArr.length, this.dimension);
        }
        System.arraycopy(dArr, 0, dArr2, this.firstIndex, i);
    }
}
