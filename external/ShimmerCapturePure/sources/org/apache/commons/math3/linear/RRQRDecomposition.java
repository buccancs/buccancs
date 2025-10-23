package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class RRQRDecomposition extends QRDecomposition {
    private RealMatrix cachedP;
    private int[] p;

    public RRQRDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 0.0d);
    }

    public RRQRDecomposition(RealMatrix realMatrix, double d) {
        super(realMatrix, d);
    }

    @Override // org.apache.commons.math3.linear.QRDecomposition
    protected void decompose(double[][] dArr) {
        this.p = new int[dArr.length];
        int i = 0;
        while (true) {
            int[] iArr = this.p;
            if (i < iArr.length) {
                iArr[i] = i;
                i++;
            } else {
                super.decompose(dArr);
                return;
            }
        }
    }

    @Override // org.apache.commons.math3.linear.QRDecomposition
    protected void performHouseholderReflection(int i, double[][] dArr) {
        int i2 = i;
        int i3 = i2;
        double d = 0.0d;
        while (i2 < dArr.length) {
            int i4 = 0;
            double d2 = 0.0d;
            while (true) {
                double[] dArr2 = dArr[i2];
                if (i4 >= dArr2.length) {
                    break;
                }
                double d3 = dArr2[i4];
                d2 += d3 * d3;
                i4++;
            }
            if (d2 > d) {
                i3 = i2;
                d = d2;
            }
            i2++;
        }
        if (i3 != i) {
            double[] dArr3 = dArr[i];
            dArr[i] = dArr[i3];
            dArr[i3] = dArr3;
            int[] iArr = this.p;
            int i5 = iArr[i];
            iArr[i] = iArr[i3];
            iArr[i3] = i5;
        }
        super.performHouseholderReflection(i, dArr);
    }

    public RealMatrix getP() throws OutOfRangeException {
        if (this.cachedP == null) {
            int length = this.p.length;
            this.cachedP = MatrixUtils.createRealMatrix(length, length);
            for (int i = 0; i < length; i++) {
                this.cachedP.setEntry(this.p[i], i, 1.0d);
            }
        }
        return this.cachedP;
    }

    public int getRank(double d) {
        RealMatrix r = getR();
        int rowDimension = r.getRowDimension();
        int columnDimension = r.getColumnDimension();
        double frobeniusNorm = r.getFrobeniusNorm();
        int i = 1;
        double d2 = frobeniusNorm;
        while (i < FastMath.min(rowDimension, columnDimension)) {
            double frobeniusNorm2 = r.getSubMatrix(i, rowDimension - 1, i, columnDimension - 1).getFrobeniusNorm();
            if (frobeniusNorm2 == 0.0d || (frobeniusNorm2 / d2) * frobeniusNorm < d) {
                break;
            }
            i++;
            d2 = frobeniusNorm2;
        }
        return i;
    }

    @Override // org.apache.commons.math3.linear.QRDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(super.getSolver(), getP());
    }

    private static class Solver implements DecompositionSolver {
        private final DecompositionSolver upper;
        private RealMatrix p;

        private Solver(DecompositionSolver decompositionSolver, RealMatrix realMatrix) {
            this.upper = decompositionSolver;
            this.p = realMatrix;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            return this.upper.isNonSingular();
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector realVector) {
            return this.p.operate(this.upper.solve(realVector));
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix realMatrix) {
            return this.p.multiply(this.upper.solve(realMatrix));
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.p.getRowDimension()));
        }
    }
}
