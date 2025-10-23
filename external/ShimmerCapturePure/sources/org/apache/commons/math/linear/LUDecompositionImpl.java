package org.apache.commons.math.linear;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/LUDecompositionImpl.class */
public class LUDecompositionImpl implements LUDecomposition {
    private static final double DEFAULT_TOO_SMALL = 1.0E-11d;
    private double[][] lu;
    private int[] pivot;
    private boolean even;
    private boolean singular;
    private RealMatrix cachedL;
    private RealMatrix cachedU;
    private RealMatrix cachedP;

    public LUDecompositionImpl(RealMatrix matrix) throws InvalidMatrixException {
        this(matrix, 1.0E-11d);
    }

    public LUDecompositionImpl(RealMatrix matrix, double singularityThreshold) throws NonSquareMatrixException {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int m = matrix.getColumnDimension();
        this.lu = matrix.getData();
        this.pivot = new int[m];
        this.cachedL = null;
        this.cachedU = null;
        this.cachedP = null;
        for (int row = 0; row < m; row++) {
            this.pivot[row] = row;
        }
        this.even = true;
        this.singular = false;
        for (int col = 0; col < m; col++) {
            for (int row2 = 0; row2 < col; row2++) {
                double[] luRow = this.lu[row2];
                double sum = luRow[col];
                for (int i = 0; i < row2; i++) {
                    sum -= luRow[i] * this.lu[i][col];
                }
                luRow[col] = sum;
            }
            int max = col;
            double largest = Double.NEGATIVE_INFINITY;
            for (int row3 = col; row3 < m; row3++) {
                double[] luRow2 = this.lu[row3];
                double sum2 = luRow2[col];
                for (int i2 = 0; i2 < col; i2++) {
                    sum2 -= luRow2[i2] * this.lu[i2][col];
                }
                luRow2[col] = sum2;
                if (FastMath.abs(sum2) > largest) {
                    largest = FastMath.abs(sum2);
                    max = row3;
                }
            }
            if (FastMath.abs(this.lu[max][col]) < singularityThreshold) {
                this.singular = true;
                return;
            }
            if (max != col) {
                double[] luMax = this.lu[max];
                double[] luCol = this.lu[col];
                for (int i3 = 0; i3 < m; i3++) {
                    double tmp = luMax[i3];
                    luMax[i3] = luCol[i3];
                    luCol[i3] = tmp;
                }
                int temp = this.pivot[max];
                this.pivot[max] = this.pivot[col];
                this.pivot[col] = temp;
                this.even = !this.even;
            }
            double luDiag = this.lu[col][col];
            for (int row4 = col + 1; row4 < m; row4++) {
                double[] dArr = this.lu[row4];
                int i4 = col;
                dArr[i4] = dArr[i4] / luDiag;
            }
        }
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public RealMatrix getL() throws MatrixIndexException {
        if (this.cachedL == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedL = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; i < m; i++) {
                double[] luI = this.lu[i];
                for (int j = 0; j < i; j++) {
                    this.cachedL.setEntry(i, j, luI[j]);
                }
                this.cachedL.setEntry(i, i, 1.0d);
            }
        }
        return this.cachedL;
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public RealMatrix getU() throws MatrixIndexException {
        if (this.cachedU == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedU = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; i < m; i++) {
                double[] luI = this.lu[i];
                for (int j = i; j < m; j++) {
                    this.cachedU.setEntry(i, j, luI[j]);
                }
            }
        }
        return this.cachedU;
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public RealMatrix getP() throws MatrixIndexException {
        if (this.cachedP == null && !this.singular) {
            int m = this.pivot.length;
            this.cachedP = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; i < m; i++) {
                this.cachedP.setEntry(i, this.pivot[i], 1.0d);
            }
        }
        return this.cachedP;
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public int[] getPivot() {
        return (int[]) this.pivot.clone();
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public double getDeterminant() {
        if (this.singular) {
            return 0.0d;
        }
        int m = this.pivot.length;
        double determinant = this.even ? 1.0d : -1.0d;
        for (int i = 0; i < m; i++) {
            determinant *= this.lu[i][i];
        }
        return determinant;
    }

    @Override // org.apache.commons.math.linear.LUDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(this.lu, this.pivot, this.singular);
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/LUDecompositionImpl$Solver.class */
    private static class Solver implements DecompositionSolver {
        private final double[][] lu;
        private final int[] pivot;
        private final boolean singular;

        private Solver(double[][] lu, int[] pivot, boolean singular) {
            this.lu = lu;
            this.pivot = pivot;
            this.singular = singular;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public boolean isNonSingular() {
            return !this.singular;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public double[] solve(double[] b) throws InvalidMatrixException, IllegalArgumentException {
            int m = this.pivot.length;
            if (b.length != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.length), Integer.valueOf(m));
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            double[] bp = new double[m];
            for (int row = 0; row < m; row++) {
                bp[row] = b[this.pivot[row]];
            }
            for (int col = 0; col < m; col++) {
                double bpCol = bp[col];
                for (int i = col + 1; i < m; i++) {
                    int i2 = i;
                    bp[i2] = bp[i2] - (bpCol * this.lu[i][col]);
                }
            }
            for (int col2 = m - 1; col2 >= 0; col2--) {
                int i3 = col2;
                bp[i3] = bp[i3] / this.lu[col2][col2];
                double bpCol2 = bp[col2];
                for (int i4 = 0; i4 < col2; i4++) {
                    int i5 = i4;
                    bp[i5] = bp[i5] - (bpCol2 * this.lu[i4][col2]);
                }
            }
            return bp;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealVector solve(RealVector b) throws InvalidMatrixException, IllegalArgumentException {
            try {
                return solve((ArrayRealVector) b);
            } catch (ClassCastException e) {
                int m = this.pivot.length;
                if (b.getDimension() != m) {
                    throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.getDimension()), Integer.valueOf(m));
                }
                if (this.singular) {
                    throw new SingularMatrixException();
                }
                double[] bp = new double[m];
                for (int row = 0; row < m; row++) {
                    bp[row] = b.getEntry(this.pivot[row]);
                }
                for (int col = 0; col < m; col++) {
                    double bpCol = bp[col];
                    for (int i = col + 1; i < m; i++) {
                        int i2 = i;
                        bp[i2] = bp[i2] - (bpCol * this.lu[i][col]);
                    }
                }
                for (int col2 = m - 1; col2 >= 0; col2--) {
                    int i3 = col2;
                    bp[i3] = bp[i3] / this.lu[col2][col2];
                    double bpCol2 = bp[col2];
                    for (int i4 = 0; i4 < col2; i4++) {
                        int i5 = i4;
                        bp[i5] = bp[i5] - (bpCol2 * this.lu[i4][col2]);
                    }
                }
                return new ArrayRealVector(bp, false);
            }
        }

        public ArrayRealVector solve(ArrayRealVector b) throws InvalidMatrixException, IllegalArgumentException {
            return new ArrayRealVector(solve(b.getDataRef()), false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) throws InvalidMatrixException, IllegalArgumentException {
            int m = this.pivot.length;
            if (b.getRowDimension() != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(b.getRowDimension()), Integer.valueOf(b.getColumnDimension()), Integer.valueOf(m), "n");
            }
            if (this.singular) {
                throw new SingularMatrixException();
            }
            int nColB = b.getColumnDimension();
            double[][] bp = new double[m][nColB];
            for (int row = 0; row < m; row++) {
                double[] bpRow = bp[row];
                int pRow = this.pivot[row];
                for (int col = 0; col < nColB; col++) {
                    bpRow[col] = b.getEntry(pRow, col);
                }
            }
            for (int col2 = 0; col2 < m; col2++) {
                double[] bpCol = bp[col2];
                for (int i = col2 + 1; i < m; i++) {
                    double[] bpI = bp[i];
                    double luICol = this.lu[i][col2];
                    for (int j = 0; j < nColB; j++) {
                        int i2 = j;
                        bpI[i2] = bpI[i2] - (bpCol[j] * luICol);
                    }
                }
            }
            for (int col3 = m - 1; col3 >= 0; col3--) {
                double[] bpCol2 = bp[col3];
                double luDiag = this.lu[col3][col3];
                for (int j2 = 0; j2 < nColB; j2++) {
                    int i3 = j2;
                    bpCol2[i3] = bpCol2[i3] / luDiag;
                }
                for (int i4 = 0; i4 < col3; i4++) {
                    double[] bpI2 = bp[i4];
                    double luICol2 = this.lu[i4][col3];
                    for (int j3 = 0; j3 < nColB; j3++) {
                        int i5 = j3;
                        bpI2[i5] = bpI2[i5] - (bpCol2[j3] * luICol2);
                    }
                }
            }
            return new Array2DRowRealMatrix(bp, false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix getInverse() throws InvalidMatrixException {
            return solve(MatrixUtils.createRealIdentityMatrix(this.pivot.length));
        }
    }
}
