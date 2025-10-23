package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.analysis.TrivariateRealFunction;
import org.apache.commons.math.exception.OutOfRangeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: TricubicSplineInterpolatingFunction.java */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/TricubicSplineFunction.class */
class TricubicSplineFunction implements TrivariateRealFunction {
    private static final short N = 4;
    private final double[][][] a = new double[4][4][4];

    public TricubicSplineFunction(double[] aV) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.a[i][j][k] = aV[i + (4 * (j + (4 * k)))];
                }
            }
        }
    }

    @Override // org.apache.commons.math.analysis.TrivariateRealFunction
    public double value(double x, double y, double z) {
        if (x < 0.0d || x > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(x), 0, 1);
        }
        if (y < 0.0d || y > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(y), 0, 1);
        }
        if (z < 0.0d || z > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(z), 0, 1);
        }
        double x2 = x * x;
        double x3 = x2 * x;
        double[] pX = {1.0d, x, x2, x3};
        double y2 = y * y;
        double y3 = y2 * y;
        double[] pY = {1.0d, y, y2, y3};
        double z2 = z * z;
        double z3 = z2 * z;
        double[] pZ = {1.0d, z, z2, z3};
        double result = 0.0d;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result += this.a[i][j][k] * pX[i] * pY[j] * pZ[k];
                }
            }
        }
        return result;
    }
}
