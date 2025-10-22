package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.analysis.BivariateRealFunction;
import org.apache.commons.math.exception.OutOfRangeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: BicubicSplineInterpolatingFunction.java */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/BicubicSplineFunction.class */
class BicubicSplineFunction implements BivariateRealFunction {
    private static final short N = 4;
    private final double[][] a = new double[4][4];
    private BivariateRealFunction partialDerivativeX;
    private BivariateRealFunction partialDerivativeY;
    private BivariateRealFunction partialDerivativeXX;
    private BivariateRealFunction partialDerivativeYY;
    private BivariateRealFunction partialDerivativeXY;

    public BicubicSplineFunction(double[] a) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.a[i][j] = a[i + (4 * j)];
            }
        }
    }

    @Override // org.apache.commons.math.analysis.BivariateRealFunction
    public double value(double x, double y) {
        if (x < 0.0d || x > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(x), 0, 1);
        }
        if (y < 0.0d || y > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(y), 0, 1);
        }
        double x2 = x * x;
        double x3 = x2 * x;
        double[] pX = {1.0d, x, x2, x3};
        double y2 = y * y;
        double y3 = y2 * y;
        double[] pY = {1.0d, y, y2, y3};
        return apply(pX, pY, this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double apply(double[] pX, double[] pY, double[][] coeff) {
        double result = 0.0d;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += coeff[i][j] * pX[i] * pY[j];
            }
        }
        return result;
    }

    public BivariateRealFunction partialDerivativeX() {
        if (this.partialDerivativeX == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeX;
    }

    public BivariateRealFunction partialDerivativeY() {
        if (this.partialDerivativeY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeY;
    }

    public BivariateRealFunction partialDerivativeXX() {
        if (this.partialDerivativeXX == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeXX;
    }

    public BivariateRealFunction partialDerivativeYY() {
        if (this.partialDerivativeYY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeYY;
    }

    public BivariateRealFunction partialDerivativeXY() {
        if (this.partialDerivativeXY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeXY;
    }

    private void computePartialDerivatives() {
        final double[][] aX = new double[4][4];
        final double[][] aY = new double[4][4];
        final double[][] aXX = new double[4][4];
        final double[][] aYY = new double[4][4];
        final double[][] aXY = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double c = this.a[i][j];
                aX[i][j] = i * c;
                aY[i][j] = j * c;
                aXX[i][j] = (i - 1) * aX[i][j];
                aYY[i][j] = (j - 1) * aY[i][j];
                aXY[i][j] = j * aX[i][j];
            }
        }
        this.partialDerivativeX = new BivariateRealFunction() { // from class: org.apache.commons.math.analysis.interpolation.BicubicSplineFunction.1
            @Override // org.apache.commons.math.analysis.BivariateRealFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double[] pX = {0.0d, 1.0d, x, x2};
                double y2 = y * y;
                double y3 = y2 * y;
                double[] pY = {1.0d, y, y2, y3};
                return BicubicSplineFunction.this.apply(pX, pY, aX);
            }
        };
        this.partialDerivativeY = new BivariateRealFunction() { // from class: org.apache.commons.math.analysis.interpolation.BicubicSplineFunction.2
            @Override // org.apache.commons.math.analysis.BivariateRealFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double x3 = x2 * x;
                double[] pX = {1.0d, x, x2, x3};
                double y2 = y * y;
                double[] pY = {0.0d, 1.0d, y, y2};
                return BicubicSplineFunction.this.apply(pX, pY, aY);
            }
        };
        this.partialDerivativeXX = new BivariateRealFunction() { // from class: org.apache.commons.math.analysis.interpolation.BicubicSplineFunction.3
            @Override // org.apache.commons.math.analysis.BivariateRealFunction
            public double value(double x, double y) {
                double[] pX = {0.0d, 0.0d, 1.0d, x};
                double y2 = y * y;
                double y3 = y2 * y;
                double[] pY = {1.0d, y, y2, y3};
                return BicubicSplineFunction.this.apply(pX, pY, aXX);
            }
        };
        this.partialDerivativeYY = new BivariateRealFunction() { // from class: org.apache.commons.math.analysis.interpolation.BicubicSplineFunction.4
            @Override // org.apache.commons.math.analysis.BivariateRealFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double x3 = x2 * x;
                double[] pX = {1.0d, x, x2, x3};
                double[] pY = {0.0d, 0.0d, 1.0d, y};
                return BicubicSplineFunction.this.apply(pX, pY, aYY);
            }
        };
        this.partialDerivativeXY = new BivariateRealFunction() { // from class: org.apache.commons.math.analysis.interpolation.BicubicSplineFunction.5
            @Override // org.apache.commons.math.analysis.BivariateRealFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double[] pX = {0.0d, 1.0d, x, x2};
                double y2 = y * y;
                double[] pY = {0.0d, 1.0d, y, y2};
                return BicubicSplineFunction.this.apply(pX, pY, aXY);
            }
        };
    }
}
