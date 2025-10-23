package org.apache.commons.math3.optimization;

import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;

@Deprecated
/* loaded from: classes5.dex */
public class Weight implements OptimizationData {
    private final RealMatrix weightMatrix;

    public Weight(double[] dArr) {
        this.weightMatrix = new DiagonalMatrix(dArr);
    }

    public Weight(RealMatrix realMatrix) {
        if (realMatrix.getColumnDimension() != realMatrix.getRowDimension()) {
            throw new NonSquareMatrixException(realMatrix.getColumnDimension(), realMatrix.getRowDimension());
        }
        this.weightMatrix = realMatrix.copy();
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }
}
