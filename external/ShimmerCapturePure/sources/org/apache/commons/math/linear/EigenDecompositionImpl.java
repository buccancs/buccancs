package org.apache.commons.math.linear;

import com.google.common.base.Ascii;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/EigenDecompositionImpl.class */
public class EigenDecompositionImpl implements EigenDecomposition {
    private byte maxIter;
    private double[] main;
    private double[] secondary;
    private TriDiagonalTransformer transformer;
    private double[] realEigenvalues;
    private double[] imagEigenvalues;
    private ArrayRealVector[] eigenvectors;
    private RealMatrix cachedV;
    private RealMatrix cachedD;
    private RealMatrix cachedVt;

    public EigenDecompositionImpl(RealMatrix matrix, double splitTolerance) throws InvalidMatrixException {
        this.maxIter = Ascii.RS;
        if (isSymmetric(matrix)) {
            transformToTridiagonal(matrix);
            findEigenVectors(this.transformer.getQ().getData());
            return;
        }
        throw new InvalidMatrixException(LocalizedFormats.ASSYMETRIC_EIGEN_NOT_SUPPORTED, new Object[0]);
    }

    public EigenDecompositionImpl(double[] main, double[] secondary, double splitTolerance) throws InvalidMatrixException {
        this.maxIter = Ascii.RS;
        this.main = (double[]) main.clone();
        this.secondary = (double[]) secondary.clone();
        this.transformer = null;
        int size = main.length;
        double[][] z = new double[size][size];
        for (int i = 0; i < size; i++) {
            z[i][i] = 1.0d;
        }
        findEigenVectors(z);
    }

    private boolean isSymmetric(RealMatrix matrix) throws MatrixIndexException {
        int rows = matrix.getRowDimension();
        int columns = matrix.getColumnDimension();
        double eps = 10 * rows * columns * 1.1102230246251565E-16d;
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                double mij = matrix.getEntry(i, j);
                double mji = matrix.getEntry(j, i);
                if (FastMath.abs(mij - mji) > FastMath.max(FastMath.abs(mij), FastMath.abs(mji)) * eps) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public RealMatrix getV() throws MatrixIndexException, InvalidMatrixException {
        if (this.cachedV == null) {
            int m = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; k++) {
                this.cachedV.setColumnVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedV;
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public RealMatrix getD() throws InvalidMatrixException {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
        }
        return this.cachedD;
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public RealMatrix getVT() throws MatrixIndexException, InvalidMatrixException {
        if (this.cachedVt == null) {
            int m = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; k++) {
                this.cachedVt.setRowVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedVt;
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public double[] getRealEigenvalues() throws InvalidMatrixException {
        return (double[]) this.realEigenvalues.clone();
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public double getRealEigenvalue(int i) throws InvalidMatrixException, ArrayIndexOutOfBoundsException {
        return this.realEigenvalues[i];
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public double[] getImagEigenvalues() throws InvalidMatrixException {
        return (double[]) this.imagEigenvalues.clone();
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public double getImagEigenvalue(int i) throws InvalidMatrixException, ArrayIndexOutOfBoundsException {
        return this.imagEigenvalues[i];
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public RealVector getEigenvector(int i) throws InvalidMatrixException, ArrayIndexOutOfBoundsException {
        return this.eigenvectors[i].copy();
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public double getDeterminant() {
        double determinant = 1.0d;
        double[] arr$ = this.realEigenvalues;
        for (double lambda : arr$) {
            determinant *= lambda;
        }
        return determinant;
    }

    @Override // org.apache.commons.math.linear.EigenDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
    }

    private void transformToTridiagonal(RealMatrix matrix) {
        this.transformer = new TriDiagonalTransformer(matrix);
        this.main = this.transformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    private void findEigenVectors(double[][] householderMatrix) {
        int m;
        double q;
        double[][] z = (double[][]) householderMatrix.clone();
        int n = this.main.length;
        this.realEigenvalues = new double[n];
        this.imagEigenvalues = new double[n];
        double[] e = new double[n];
        for (int i = 0; i < n - 1; i++) {
            this.realEigenvalues[i] = this.main[i];
            e[i] = this.secondary[i];
        }
        this.realEigenvalues[n - 1] = this.main[n - 1];
        e[n - 1] = 0.0d;
        double maxAbsoluteValue = 0.0d;
        for (int i2 = 0; i2 < n; i2++) {
            if (FastMath.abs(this.realEigenvalues[i2]) > maxAbsoluteValue) {
                maxAbsoluteValue = FastMath.abs(this.realEigenvalues[i2]);
            }
            if (FastMath.abs(e[i2]) > maxAbsoluteValue) {
                maxAbsoluteValue = FastMath.abs(e[i2]);
            }
        }
        if (maxAbsoluteValue != 0.0d) {
            for (int i3 = 0; i3 < n; i3++) {
                if (FastMath.abs(this.realEigenvalues[i3]) <= 1.1102230246251565E-16d * maxAbsoluteValue) {
                    this.realEigenvalues[i3] = 0.0d;
                }
                if (FastMath.abs(e[i3]) <= 1.1102230246251565E-16d * maxAbsoluteValue) {
                    e[i3] = 0.0d;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int its = 0;
            do {
                m = j;
                while (m < n - 1) {
                    double delta = FastMath.abs(this.realEigenvalues[m]) + FastMath.abs(this.realEigenvalues[m + 1]);
                    if (FastMath.abs(e[m]) + delta == delta) {
                        break;
                    } else {
                        m++;
                    }
                }
                if (m != j) {
                    if (its == this.maxIter) {
                        throw new InvalidMatrixException(new MaxIterationsExceededException(this.maxIter));
                    }
                    its++;
                    double q2 = (this.realEigenvalues[j + 1] - this.realEigenvalues[j]) / (2.0d * e[j]);
                    double t = FastMath.sqrt(1.0d + (q2 * q2));
                    if (q2 < 0.0d) {
                        q = (this.realEigenvalues[m] - this.realEigenvalues[j]) + (e[j] / (q2 - t));
                    } else {
                        q = (this.realEigenvalues[m] - this.realEigenvalues[j]) + (e[j] / (q2 + t));
                    }
                    double u = 0.0d;
                    double s = 1.0d;
                    double c = 1.0d;
                    int i4 = m - 1;
                    while (true) {
                        if (i4 < j) {
                            break;
                        }
                        double p = s * e[i4];
                        double h = c * e[i4];
                        if (FastMath.abs(p) >= FastMath.abs(q)) {
                            double c2 = q / p;
                            t = FastMath.sqrt((c2 * c2) + 1.0d);
                            e[i4 + 1] = p * t;
                            s = 1.0d / t;
                            c = c2 * s;
                        } else {
                            double s2 = p / q;
                            t = FastMath.sqrt((s2 * s2) + 1.0d);
                            e[i4 + 1] = q * t;
                            c = 1.0d / t;
                            s = s2 * c;
                        }
                        if (e[i4 + 1] == 0.0d) {
                            double[] dArr = this.realEigenvalues;
                            int i5 = i4 + 1;
                            dArr[i5] = dArr[i5] - u;
                            e[m] = 0.0d;
                            break;
                        }
                        double q3 = this.realEigenvalues[i4 + 1] - u;
                        t = ((this.realEigenvalues[i4] - q3) * s) + (2.0d * c * h);
                        u = s * t;
                        this.realEigenvalues[i4 + 1] = q3 + u;
                        q = (c * t) - h;
                        for (int ia = 0; ia < n; ia++) {
                            double p2 = z[ia][i4 + 1];
                            z[ia][i4 + 1] = (s * z[ia][i4]) + (c * p2);
                            z[ia][i4] = (c * z[ia][i4]) - (s * p2);
                        }
                        i4--;
                    }
                    if (t != 0.0d || i4 < j) {
                        double[] dArr2 = this.realEigenvalues;
                        int i6 = j;
                        dArr2[i6] = dArr2[i6] - u;
                        e[j] = q;
                        e[m] = 0.0d;
                    }
                }
            } while (m != j);
        }
        for (int i7 = 0; i7 < n; i7++) {
            int k = i7;
            double p3 = this.realEigenvalues[i7];
            for (int j2 = i7 + 1; j2 < n; j2++) {
                if (this.realEigenvalues[j2] > p3) {
                    k = j2;
                    p3 = this.realEigenvalues[j2];
                }
            }
            if (k != i7) {
                this.realEigenvalues[k] = this.realEigenvalues[i7];
                this.realEigenvalues[i7] = p3;
                for (int j3 = 0; j3 < n; j3++) {
                    double p4 = z[j3][i7];
                    z[j3][i7] = z[j3][k];
                    z[j3][k] = p4;
                }
            }
        }
        double maxAbsoluteValue2 = 0.0d;
        for (int i8 = 0; i8 < n; i8++) {
            if (FastMath.abs(this.realEigenvalues[i8]) > maxAbsoluteValue2) {
                maxAbsoluteValue2 = FastMath.abs(this.realEigenvalues[i8]);
            }
        }
        if (maxAbsoluteValue2 != 0.0d) {
            for (int i9 = 0; i9 < n; i9++) {
                if (FastMath.abs(this.realEigenvalues[i9]) < 1.1102230246251565E-16d * maxAbsoluteValue2) {
                    this.realEigenvalues[i9] = 0.0d;
                }
            }
        }
        this.eigenvectors = new ArrayRealVector[n];
        double[] tmp = new double[n];
        for (int i10 = 0; i10 < n; i10++) {
            for (int j4 = 0; j4 < n; j4++) {
                tmp[j4] = z[j4][i10];
            }
            this.eigenvectors[i10] = new ArrayRealVector(tmp);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/EigenDecompositionImpl$Solver.class */
    private static class Solver implements DecompositionSolver {
        private final ArrayRealVector[] eigenvectors;
        private double[] realEigenvalues;
        private double[] imagEigenvalues;

        private Solver(double[] realEigenvalues, double[] imagEigenvalues, ArrayRealVector[] eigenvectors) {
            this.realEigenvalues = realEigenvalues;
            this.imagEigenvalues = imagEigenvalues;
            this.eigenvectors = eigenvectors;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public double[] solve(double[] b) throws InvalidMatrixException, IllegalArgumentException {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.length != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.length), Integer.valueOf(m));
            }
            double[] bp = new double[m];
            for (int i = 0; i < m; i++) {
                ArrayRealVector v = this.eigenvectors[i];
                double[] vData = v.getDataRef();
                double s = v.dotProduct(b) / this.realEigenvalues[i];
                for (int j = 0; j < m; j++) {
                    int i2 = j;
                    bp[i2] = bp[i2] + (s * vData[j]);
                }
            }
            return bp;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealVector solve(RealVector b) throws InvalidMatrixException, IllegalArgumentException {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getDimension() != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.VECTOR_LENGTH_MISMATCH, Integer.valueOf(b.getDimension()), Integer.valueOf(m));
            }
            double[] bp = new double[m];
            for (int i = 0; i < m; i++) {
                ArrayRealVector v = this.eigenvectors[i];
                double[] vData = v.getDataRef();
                double s = v.dotProduct(b) / this.realEigenvalues[i];
                for (int j = 0; j < m; j++) {
                    int i2 = j;
                    bp[i2] = bp[i2] + (s * vData[j]);
                }
            }
            return new ArrayRealVector(bp, false);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) throws InvalidMatrixException, IllegalArgumentException {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getRowDimension() != m) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_2x2, Integer.valueOf(b.getRowDimension()), Integer.valueOf(b.getColumnDimension()), Integer.valueOf(m), "n");
            }
            int nColB = b.getColumnDimension();
            double[][] bp = new double[m][nColB];
            for (int k = 0; k < nColB; k++) {
                for (int i = 0; i < m; i++) {
                    ArrayRealVector v = this.eigenvectors[i];
                    double[] vData = v.getDataRef();
                    double s = 0.0d;
                    for (int j = 0; j < m; j++) {
                        s += v.getEntry(j) * b.getEntry(j, k);
                    }
                    double s2 = s / this.realEigenvalues[i];
                    for (int j2 = 0; j2 < m; j2++) {
                        double[] dArr = bp[j2];
                        int i2 = k;
                        dArr[i2] = dArr[i2] + (s2 * vData[j2]);
                    }
                }
            }
            return MatrixUtils.createRealMatrix(bp);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public boolean isNonSingular() {
            for (int i = 0; i < this.realEigenvalues.length; i++) {
                if (this.realEigenvalues[i] == 0.0d && this.imagEigenvalues[i] == 0.0d) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix getInverse() throws InvalidMatrixException {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            double[][] invData = new double[m][m];
            for (int i = 0; i < m; i++) {
                double[] invI = invData[i];
                for (int j = 0; j < m; j++) {
                    double invIJ = 0.0d;
                    for (int k = 0; k < m; k++) {
                        double[] vK = this.eigenvectors[k].getDataRef();
                        invIJ += (vK[i] * vK[j]) / this.realEigenvalues[k];
                    }
                    invI[j] = invIJ;
                }
            }
            return MatrixUtils.createRealMatrix(invData);
        }
    }
}
