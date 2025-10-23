package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;

/* loaded from: classes5.dex */
public abstract class RealLinearOperator {
    public abstract int getColumnDimension();

    public abstract int getRowDimension();

    public boolean isTransposable() {
        return false;
    }

    public abstract RealVector operate(RealVector realVector) throws DimensionMismatchException;

    public RealVector operateTranspose(RealVector realVector) throws UnsupportedOperationException, DimensionMismatchException {
        throw new UnsupportedOperationException();
    }
}
