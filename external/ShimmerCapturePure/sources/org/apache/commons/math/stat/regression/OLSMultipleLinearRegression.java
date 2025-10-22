package org.apache.commons.math.stat.regression;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.QRDecomposition;
import org.apache.commons.math.linear.QRDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.stat.StatUtils;
import org.apache.commons.math.stat.descriptive.moment.SecondMoment;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/regression/OLSMultipleLinearRegression.class */
public class OLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
    private QRDecomposition qr = null;

    public void newSampleData(double[] y, double[][] x) {
        validateSampleData(x, y);
        newYSampleData(y);
        newXSampleData(x);
    }

    @Override // org.apache.commons.math.stat.regression.AbstractMultipleLinearRegression
    public void newSampleData(double[] data, int nobs, int nvars) {
        super.newSampleData(data, nobs, nvars);
        this.qr = new QRDecompositionImpl(this.X);
    }

    public RealMatrix calculateHat() {
        RealMatrix Q = this.qr.getQ();
        int p = this.qr.getR().getColumnDimension();
        int n = Q.getColumnDimension();
        Array2DRowRealMatrix augI = new Array2DRowRealMatrix(n, n);
        double[][] augIData = augI.getDataRef();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && i < p) {
                    augIData[i][j] = 1.0d;
                } else {
                    augIData[i][j] = 0.0d;
                }
            }
        }
        return Q.multiply(augI).multiply(Q.transpose());
    }

    public double calculateTotalSumOfSquares() {
        if (isNoIntercept()) {
            return StatUtils.sumSq(this.Y.getData());
        }
        return new SecondMoment().evaluate(this.Y.getData());
    }

    public double calculateResidualSumOfSquares() {
        RealVector residuals = calculateResiduals();
        return residuals.dotProduct(residuals);
    }

    public double calculateRSquared() {
        return 1.0d - (calculateResidualSumOfSquares() / calculateTotalSumOfSquares());
    }

    public double calculateAdjustedRSquared() {
        double n = this.X.getRowDimension();
        if (isNoIntercept()) {
            return 1.0d - ((1.0d - calculateRSquared()) * (n / (n - this.X.getColumnDimension())));
        }
        return 1.0d - ((calculateResidualSumOfSquares() * (n - 1.0d)) / (calculateTotalSumOfSquares() * (n - this.X.getColumnDimension())));
    }

    @Override // org.apache.commons.math.stat.regression.AbstractMultipleLinearRegression
    protected void newXSampleData(double[][] x) {
        super.newXSampleData(x);
        this.qr = new QRDecompositionImpl(this.X);
    }

    @Override // org.apache.commons.math.stat.regression.AbstractMultipleLinearRegression
    protected RealVector calculateBeta() {
        return this.qr.getSolver().solve(this.Y);
    }

    @Override // org.apache.commons.math.stat.regression.AbstractMultipleLinearRegression
    protected RealMatrix calculateBetaVariance() throws MatrixIndexException, InvalidMatrixException {
        int p = this.X.getColumnDimension();
        RealMatrix Raug = this.qr.getR().getSubMatrix(0, p - 1, 0, p - 1);
        RealMatrix Rinv = new LUDecompositionImpl(Raug).getSolver().getInverse();
        return Rinv.multiply(Rinv.transpose());
    }
}
