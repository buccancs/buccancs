package org.apache.commons.math.linear;

import java.util.Arrays;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/QRDecompositionImpl.class */
public class QRDecompositionImpl implements QRDecomposition {
    private double[][] qrt;
    private double[] rDiag;
    private RealMatrix cachedQ;
    private RealMatrix cachedQT;
    private RealMatrix cachedR;
    private RealMatrix cachedH;

    public QRDecompositionImpl(RealMatrix matrix) {
        int m = matrix.getRowDimension();
        int n = matrix.getColumnDimension();
        this.qrt = matrix.transpose().getData();
        this.rDiag = new double[FastMath.min(m, n)];
        this.cachedQ = null;
        this.cachedQT = null;
        this.cachedR = null;
        this.cachedH = null;
        for (int minor = 0; minor < FastMath.min(m, n); minor++) {
            double[] qrtMinor = this.qrt[minor];
            double xNormSqr = 0.0d;
            for (int row = minor; row < m; row++) {
                double c = qrtMinor[row];
                xNormSqr += c * c;
            }
            double a = qrtMinor[minor] > 0.0d ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.rDiag[minor] = a;
            if (a != 0.0d) {
                int i = minor;
                qrtMinor[i] = qrtMinor[i] - a;
                for (int col = minor + 1; col < n; col++) {
                    double[] qrtCol = this.qrt[col];
                    double alpha = 0.0d;
                    for (int row2 = minor; row2 < m; row2++) {
                        alpha -= qrtCol[row2] * qrtMinor[row2];
                    }
                    double alpha2 = alpha / (a * qrtMinor[minor]);
                    for (int row3 = minor; row3 < m; row3++) {
                        int i2 = row3;
                        qrtCol[i2] = qrtCol[i2] - (alpha2 * qrtMinor[row3]);
                    }
                }
            }
        }
    }

    @Override // org.apache.commons.math.linear.QRDecomposition
    public RealMatrix getR() throws MatrixIndexException {
        if (this.cachedR == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            this.cachedR = MatrixUtils.createRealMatrix(m, n);
            for (int row = FastMath.min(m, n) - 1; row >= 0; row--) {
                this.cachedR.setEntry(row, row, this.rDiag[row]);
                for (int col = row + 1; col < n; col++) {
                    this.cachedR.setEntry(row, col, this.qrt[col][row]);
                }
            }
        }
        return this.cachedR;
    }

    @Override // org.apache.commons.math.linear.QRDecomposition
    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    @Override // org.apache.commons.math.linear.QRDecomposition
    public RealMatrix getQT() throws MatrixIndexException {
        if (this.cachedQT == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            this.cachedQT = MatrixUtils.createRealMatrix(m, m);
            for (int minor = m - 1; minor >= FastMath.min(m, n); minor--) {
                this.cachedQT.setEntry(minor, minor, 1.0d);
            }
            for (int minor2 = FastMath.min(m, n) - 1; minor2 >= 0; minor2--) {
                double[] qrtMinor = this.qrt[minor2];
                this.cachedQT.setEntry(minor2, minor2, 1.0d);
                if (qrtMinor[minor2] != 0.0d) {
                    for (int col = minor2; col < m; col++) {
                        double alpha = 0.0d;
                        for (int row = minor2; row < m; row++) {
                            alpha -= this.cachedQT.getEntry(col, row) * qrtMinor[row];
                        }
                        double alpha2 = alpha / (this.rDiag[minor2] * qrtMinor[minor2]);
                        for (int row2 = minor2; row2 < m; row2++) {
                            this.cachedQT.addToEntry(col, row2, (-alpha2) * qrtMinor[row2]);
                        }
                    }
                }
            }
        }
        return this.cachedQT;
    }

    @Override // org.apache.commons.math.linear.QRDecomposition
    public RealMatrix getH() throws MatrixIndexException {
        if (this.cachedH == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            this.cachedH = MatrixUtils.createRealMatrix(m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < FastMath.min(i + 1, n); j++) {
                    this.cachedH.setEntry(i, j, this.qrt[j][i] / (-this.rDiag[j]));
                }
            }
        }
        return this.cachedH;
    }

    @Override // org.apache.commons.math.linear.QRDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(this.qrt, this.rDiag);
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/QRDecompositionImpl$Solver.class */
    private static class Solver implements DecompositionSolver {
        private final double[][] qrt;
        private final double[] rDiag;

        private Solver(double[][] qrt, double[] rDiag) {
            this.qrt = qrt;
            this.rDiag = rDiag;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public boolean isNonSingular() {
            double[] arr$ = this.rDiag;
            for (double diag : arr$) {
                if (diag == 0.0d) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public double[] solve(double[] b) throws InvalidMatrixException, IllegalArgumentException {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            if (b.length != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.length), Integer.valueOf(m));
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            double[] x = new double[n];
            double[] y = (double[]) b.clone();
            for (int minor = 0; minor < FastMath.min(m, n); minor++) {
                double[] qrtMinor = this.qrt[minor];
                double dotProduct = 0.0d;
                for (int row = minor; row < m; row++) {
                    dotProduct += y[row] * qrtMinor[row];
                }
                double dotProduct2 = dotProduct / (this.rDiag[minor] * qrtMinor[minor]);
                for (int row2 = minor; row2 < m; row2++) {
                    int i = row2;
                    y[i] = y[i] + (dotProduct2 * qrtMinor[row2]);
                }
            }
            for (int row3 = this.rDiag.length - 1; row3 >= 0; row3--) {
                int i2 = row3;
                y[i2] = y[i2] / this.rDiag[row3];
                double yRow = y[row3];
                double[] qrtRow = this.qrt[row3];
                x[row3] = yRow;
                for (int i3 = 0; i3 < row3; i3++) {
                    int i4 = i3;
                    y[i4] = y[i4] - (yRow * qrtRow[i3]);
                }
            }
            return x;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealVector solve(RealVector b) throws InvalidMatrixException, IllegalArgumentException {
            try {
                return solve((ArrayRealVector) b);
            } catch (ClassCastException e) {
                return new ArrayRealVector(solve(b.getData()), false);
            }
        }

        public ArrayRealVector solve(ArrayRealVector b) throws InvalidMatrixException, IllegalArgumentException {
            return new ArrayRealVector(solve(b.getDataRef()), false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) throws MatrixIndexException, InvalidMatrixException, IllegalArgumentException {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            if (b.getRowDimension() != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(b.getRowDimension()), Integer.valueOf(b.getColumnDimension()), Integer.valueOf(m), "n");
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int columns = b.getColumnDimension();
            int cBlocks = ((columns + 52) - 1) / 52;
            double[][] xBlocks = BlockRealMatrix.createBlocksLayout(n, columns);
            double[][] y = new double[b.getRowDimension()][52];
            double[] alpha = new double[52];
            for (int kBlock = 0; kBlock < cBlocks; kBlock++) {
                int kStart = kBlock * 52;
                int kEnd = FastMath.min(kStart + 52, columns);
                int kWidth = kEnd - kStart;
                b.copySubMatrix(0, m - 1, kStart, kEnd - 1, y);
                for (int minor = 0; minor < FastMath.min(m, n); minor++) {
                    double[] qrtMinor = this.qrt[minor];
                    double factor = 1.0d / (this.rDiag[minor] * qrtMinor[minor]);
                    Arrays.fill(alpha, 0, kWidth, 0.0d);
                    for (int row = minor; row < m; row++) {
                        double d = qrtMinor[row];
                        double[] yRow = y[row];
                        for (int k = 0; k < kWidth; k++) {
                            int i = k;
                            alpha[i] = alpha[i] + (d * yRow[k]);
                        }
                    }
                    for (int k2 = 0; k2 < kWidth; k2++) {
                        int i2 = k2;
                        alpha[i2] = alpha[i2] * factor;
                    }
                    for (int row2 = minor; row2 < m; row2++) {
                        double d2 = qrtMinor[row2];
                        double[] yRow2 = y[row2];
                        for (int k3 = 0; k3 < kWidth; k3++) {
                            int i3 = k3;
                            yRow2[i3] = yRow2[i3] + (alpha[k3] * d2);
                        }
                    }
                }
                for (int j = this.rDiag.length - 1; j >= 0; j--) {
                    int jBlock = j / 52;
                    int jStart = jBlock * 52;
                    double factor2 = 1.0d / this.rDiag[j];
                    double[] yJ = y[j];
                    double[] xBlock = xBlocks[(jBlock * cBlocks) + kBlock];
                    int index = (j - jStart) * kWidth;
                    for (int k4 = 0; k4 < kWidth; k4++) {
                        int i4 = k4;
                        yJ[i4] = yJ[i4] * factor2;
                        int i5 = index;
                        index++;
                        xBlock[i5] = yJ[k4];
                    }
                    double[] qrtJ = this.qrt[j];
                    for (int i6 = 0; i6 < j; i6++) {
                        double rIJ = qrtJ[i6];
                        double[] yI = y[i6];
                        for (int k5 = 0; k5 < kWidth; k5++) {
                            int i7 = k5;
                            yI[i7] = yI[i7] - (yJ[k5] * rIJ);
                        }
                    }
                }
            }
            return new BlockRealMatrix(n, columns, xBlocks, false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix getInverse() throws InvalidMatrixException {
            return solve(MatrixUtils.createRealIdentityMatrix(this.rDiag.length));
        }
    }
}
