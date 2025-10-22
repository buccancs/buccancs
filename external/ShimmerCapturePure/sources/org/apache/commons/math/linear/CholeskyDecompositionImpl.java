package org.apache.commons.math.linear;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/CholeskyDecompositionImpl.class */
public class CholeskyDecompositionImpl implements CholeskyDecomposition {
    public static final double DEFAULT_RELATIVE_SYMMETRY_THRESHOLD = 1.0E-15d;
    public static final double DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD = 1.0E-10d;
    private double[][] lTData;
    private RealMatrix cachedL;
    private RealMatrix cachedLT;

    public CholeskyDecompositionImpl(RealMatrix matrix) throws NonSquareMatrixException, NotSymmetricMatrixException, NotPositiveDefiniteMatrixException {
        this(matrix, 1.0E-15d, 1.0E-10d);
    }

    public CholeskyDecompositionImpl(RealMatrix matrix, double relativeSymmetryThreshold, double absolutePositivityThreshold) throws NonSquareMatrixException, NotSymmetricMatrixException, NotPositiveDefiniteMatrixException {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int order = matrix.getRowDimension();
        this.lTData = matrix.getData();
        this.cachedL = null;
        this.cachedLT = null;
        for (int i = 0; i < order; i++) {
            double[] lI = this.lTData[i];
            for (int j = i + 1; j < order; j++) {
                double[] lJ = this.lTData[j];
                double lIJ = lI[j];
                double lJI = lJ[i];
                double maxDelta = relativeSymmetryThreshold * FastMath.max(FastMath.abs(lIJ), FastMath.abs(lJI));
                if (FastMath.abs(lIJ - lJI) > maxDelta) {
                    throw new NotSymmetricMatrixException();
                }
                lJ[i] = 0.0d;
            }
        }
        for (int i2 = 0; i2 < order; i2++) {
            double[] ltI = this.lTData[i2];
            if (ltI[i2] < absolutePositivityThreshold) {
                throw new NotPositiveDefiniteMatrixException();
            }
            ltI[i2] = FastMath.sqrt(ltI[i2]);
            double inverse = 1.0d / ltI[i2];
            for (int q = order - 1; q > i2; q--) {
                int i3 = q;
                ltI[i3] = ltI[i3] * inverse;
                double[] ltQ = this.lTData[q];
                for (int p = q; p < order; p++) {
                    int i4 = p;
                    ltQ[i4] = ltQ[i4] - (ltI[q] * ltI[p]);
                }
            }
        }
    }

    @Override // org.apache.commons.math.linear.CholeskyDecomposition
    public RealMatrix getL() {
        if (this.cachedL == null) {
            this.cachedL = getLT().transpose();
        }
        return this.cachedL;
    }

    @Override // org.apache.commons.math.linear.CholeskyDecomposition
    public RealMatrix getLT() {
        if (this.cachedLT == null) {
            this.cachedLT = MatrixUtils.createRealMatrix(this.lTData);
        }
        return this.cachedLT;
    }

    @Override // org.apache.commons.math.linear.CholeskyDecomposition
    public double getDeterminant() {
        double determinant = 1.0d;
        for (int i = 0; i < this.lTData.length; i++) {
            double lTii = this.lTData[i][i];
            determinant *= lTii * lTii;
        }
        return determinant;
    }

    @Override // org.apache.commons.math.linear.CholeskyDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(this.lTData);
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/CholeskyDecompositionImpl$Solver.class */
    private static class Solver implements DecompositionSolver {
        private final double[][] lTData;

        private Solver(double[][] lTData) {
            this.lTData = lTData;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public boolean isNonSingular() {
            return true;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public double[] solve(double[] b) throws InvalidMatrixException, IllegalArgumentException {
            int m = this.lTData.length;
            if (b.length != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.length), Integer.valueOf(m));
            }
            double[] x = (double[]) b.clone();
            for (int j = 0; j < m; j++) {
                double[] lJ = this.lTData[j];
                int i = j;
                x[i] = x[i] / lJ[j];
                double xJ = x[j];
                for (int i2 = j + 1; i2 < m; i2++) {
                    int i3 = i2;
                    x[i3] = x[i3] - (xJ * lJ[i2]);
                }
            }
            for (int j2 = m - 1; j2 >= 0; j2--) {
                int i4 = j2;
                x[i4] = x[i4] / this.lTData[j2][j2];
                double xJ2 = x[j2];
                for (int i5 = 0; i5 < j2; i5++) {
                    int i6 = i5;
                    x[i6] = x[i6] - (xJ2 * this.lTData[i5][j2]);
                }
            }
            return x;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealVector solve(RealVector b) throws InvalidMatrixException, IllegalArgumentException {
            try {
                return solve((ArrayRealVector) b);
            } catch (ClassCastException e) {
                int m = this.lTData.length;
                if (b.getDimension() != m) {
                    throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.getDimension()), Integer.valueOf(m));
                }
                double[] x = b.getData();
                for (int j = 0; j < m; j++) {
                    double[] lJ = this.lTData[j];
                    int i = j;
                    x[i] = x[i] / lJ[j];
                    double xJ = x[j];
                    for (int i2 = j + 1; i2 < m; i2++) {
                        int i3 = i2;
                        x[i3] = x[i3] - (xJ * lJ[i2]);
                    }
                }
                for (int j2 = m - 1; j2 >= 0; j2--) {
                    int i4 = j2;
                    x[i4] = x[i4] / this.lTData[j2][j2];
                    double xJ2 = x[j2];
                    for (int i5 = 0; i5 < j2; i5++) {
                        int i6 = i5;
                        x[i6] = x[i6] - (xJ2 * this.lTData[i5][j2]);
                    }
                }
                return new ArrayRealVector(x, false);
            }
        }

        public ArrayRealVector solve(ArrayRealVector b) throws InvalidMatrixException, IllegalArgumentException {
            return new ArrayRealVector(solve(b.getDataRef()), false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) throws InvalidMatrixException, IllegalArgumentException {
            int m = this.lTData.length;
            if (b.getRowDimension() != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(b.getRowDimension()), Integer.valueOf(b.getColumnDimension()), Integer.valueOf(m), "n");
            }
            int nColB = b.getColumnDimension();
            double[][] x = b.getData();
            for (int j = 0; j < m; j++) {
                double[] lJ = this.lTData[j];
                double lJJ = lJ[j];
                double[] xJ = x[j];
                for (int k = 0; k < nColB; k++) {
                    int i = k;
                    xJ[i] = xJ[i] / lJJ;
                }
                for (int i2 = j + 1; i2 < m; i2++) {
                    double[] xI = x[i2];
                    double lJI = lJ[i2];
                    for (int k2 = 0; k2 < nColB; k2++) {
                        int i3 = k2;
                        xI[i3] = xI[i3] - (xJ[k2] * lJI);
                    }
                }
            }
            for (int j2 = m - 1; j2 >= 0; j2--) {
                double lJJ2 = this.lTData[j2][j2];
                double[] xJ2 = x[j2];
                for (int k3 = 0; k3 < nColB; k3++) {
                    int i4 = k3;
                    xJ2[i4] = xJ2[i4] / lJJ2;
                }
                for (int i5 = 0; i5 < j2; i5++) {
                    double[] xI2 = x[i5];
                    double lIJ = this.lTData[i5][j2];
                    for (int k4 = 0; k4 < nColB; k4++) {
                        int i6 = k4;
                        xI2[i6] = xI2[i6] - (xJ2[k4] * lIJ);
                    }
                }
            }
            return new Array2DRowRealMatrix(x, false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix getInverse() throws InvalidMatrixException {
            return solve(MatrixUtils.createRealIdentityMatrix(this.lTData.length));
        }
    }
}
