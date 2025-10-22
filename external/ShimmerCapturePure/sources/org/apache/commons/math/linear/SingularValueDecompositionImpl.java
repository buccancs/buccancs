package org.apache.commons.math.linear;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/SingularValueDecompositionImpl.class */
public class SingularValueDecompositionImpl implements SingularValueDecomposition {
    private int m;
    private int n;
    private EigenDecomposition eigenDecomposition;
    private double[] singularValues;
    private RealMatrix cachedU;
    private RealMatrix cachedUt;
    private RealMatrix cachedV;
    private RealMatrix cachedS = null;
    private RealMatrix cachedVt = null;

    public SingularValueDecompositionImpl(RealMatrix matrix) throws MatrixIndexException, InvalidMatrixException {
        int p;
        this.m = matrix.getRowDimension();
        this.n = matrix.getColumnDimension();
        this.cachedU = null;
        this.cachedV = null;
        double[][] localcopy = matrix.getData();
        double[][] matATA = new double[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = i; j < this.n; j++) {
                matATA[i][j] = 0.0d;
                for (int k = 0; k < this.m; k++) {
                    double[] dArr = matATA[i];
                    int i2 = j;
                    dArr[i2] = dArr[i2] + (localcopy[k][i] * localcopy[k][j]);
                }
                matATA[j][i] = matATA[i][j];
            }
        }
        double[][] matAAT = new double[this.m][this.m];
        for (int i3 = 0; i3 < this.m; i3++) {
            for (int j2 = i3; j2 < this.m; j2++) {
                matAAT[i3][j2] = 0.0d;
                for (int k2 = 0; k2 < this.n; k2++) {
                    double[] dArr2 = matAAT[i3];
                    int i4 = j2;
                    dArr2[i4] = dArr2[i4] + (localcopy[i3][k2] * localcopy[j2][k2]);
                }
                matAAT[j2][i3] = matAAT[i3][j2];
            }
        }
        if (this.m >= this.n) {
            p = this.n;
            this.eigenDecomposition = new EigenDecompositionImpl(new Array2DRowRealMatrix(matATA), 1.0d);
            this.singularValues = this.eigenDecomposition.getRealEigenvalues();
            this.cachedV = this.eigenDecomposition.getV();
            this.eigenDecomposition = new EigenDecompositionImpl(new Array2DRowRealMatrix(matAAT), 1.0d);
            this.cachedU = this.eigenDecomposition.getV().getSubMatrix(0, this.m - 1, 0, p - 1);
        } else {
            p = this.m;
            this.eigenDecomposition = new EigenDecompositionImpl(new Array2DRowRealMatrix(matAAT), 1.0d);
            this.singularValues = this.eigenDecomposition.getRealEigenvalues();
            this.cachedU = this.eigenDecomposition.getV();
            this.eigenDecomposition = new EigenDecompositionImpl(new Array2DRowRealMatrix(matATA), 1.0d);
            this.cachedV = this.eigenDecomposition.getV().getSubMatrix(0, this.n - 1, 0, p - 1);
        }
        for (int i5 = 0; i5 < p; i5++) {
            this.singularValues[i5] = FastMath.sqrt(FastMath.abs(this.singularValues[i5]));
        }
        for (int i6 = 0; i6 < p; i6++) {
            RealVector tmp = this.cachedU.getColumnVector(i6);
            double product = matrix.operate(this.cachedV.getColumnVector(i6)).dotProduct(tmp);
            if (product < 0.0d) {
                this.cachedU.setColumnVector(i6, tmp.mapMultiply(-1.0d));
            }
        }
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getU() throws InvalidMatrixException {
        return this.cachedU;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getUT() throws InvalidMatrixException {
        if (this.cachedUt == null) {
            this.cachedUt = getU().transpose();
        }
        return this.cachedUt;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getS() throws InvalidMatrixException {
        if (this.cachedS == null) {
            this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues);
        }
        return this.cachedS;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public double[] getSingularValues() throws InvalidMatrixException {
        return (double[]) this.singularValues.clone();
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getV() throws InvalidMatrixException {
        return this.cachedV;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getVT() throws InvalidMatrixException {
        if (this.cachedVt == null) {
            this.cachedVt = getV().transpose();
        }
        return this.cachedVt;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public RealMatrix getCovariance(double minSingularValue) throws MatrixVisitorException, MatrixIndexException {
        int p = this.singularValues.length;
        int dimension = 0;
        while (dimension < p && this.singularValues[dimension] >= minSingularValue) {
            dimension++;
        }
        if (dimension == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, Double.valueOf(minSingularValue), Double.valueOf(this.singularValues[0]));
        }
        final double[][] data = new double[dimension][p];
        getVT().walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() { // from class: org.apache.commons.math.linear.SingularValueDecompositionImpl.1
            @Override
            // org.apache.commons.math.linear.DefaultRealMatrixPreservingVisitor, org.apache.commons.math.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) {
                data[row][column] = value / SingularValueDecompositionImpl.this.singularValues[row];
            }
        }, 0, dimension - 1, 0, p - 1);
        RealMatrix jv = new Array2DRowRealMatrix(data, false);
        return jv.transpose().multiply(jv);
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public double getNorm() throws InvalidMatrixException {
        return this.singularValues[0];
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public double getConditionNumber() throws InvalidMatrixException {
        return this.singularValues[0] / this.singularValues[this.singularValues.length - 1];
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public int getRank() throws IllegalStateException {
        double threshold = FastMath.max(this.m, this.n) * FastMath.ulp(this.singularValues[0]);
        for (int i = this.singularValues.length - 1; i >= 0; i--) {
            if (this.singularValues[i] > threshold) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override // org.apache.commons.math.linear.SingularValueDecomposition
    public DecompositionSolver getSolver() {
        return new Solver(this.singularValues, getUT(), getV(), getRank() == Math.max(this.m, this.n));
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/SingularValueDecompositionImpl$Solver.class */
    private static class Solver implements DecompositionSolver {
        private final RealMatrix pseudoInverse;
        private boolean nonSingular;

        private Solver(double[] singularValues, RealMatrix uT, RealMatrix v, boolean nonSingular) {
            double a;
            double[][] suT = uT.getData();
            for (int i = 0; i < singularValues.length; i++) {
                if (singularValues[i] > 0.0d) {
                    a = 1.0d / singularValues[i];
                } else {
                    a = 0.0d;
                }
                double[] suTi = suT[i];
                for (int j = 0; j < suTi.length; j++) {
                    int i2 = j;
                    suTi[i2] = suTi[i2] * a;
                }
            }
            this.pseudoInverse = v.multiply(new Array2DRowRealMatrix(suT, false));
            this.nonSingular = nonSingular;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public double[] solve(double[] b) throws IllegalArgumentException {
            return this.pseudoInverse.operate(b);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealVector solve(RealVector b) throws IllegalArgumentException {
            return this.pseudoInverse.operate(b);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) throws IllegalArgumentException {
            return this.pseudoInverse.multiply(b);
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public boolean isNonSingular() {
            return this.nonSingular;
        }

        @Override // org.apache.commons.math.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return this.pseudoInverse;
        }
    }
}
