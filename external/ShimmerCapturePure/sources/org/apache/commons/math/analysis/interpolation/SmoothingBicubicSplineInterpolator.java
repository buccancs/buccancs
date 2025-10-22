package org.apache.commons.math.analysis.interpolation;

import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.MathException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.BivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.MathUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/SmoothingBicubicSplineInterpolator.class */
public class SmoothingBicubicSplineInterpolator implements BivariateRealGridInterpolator {
    @Override // org.apache.commons.math.analysis.interpolation.BivariateRealGridInterpolator
    public BivariateRealFunction interpolate(double[] xval, double[] yval, double[][] zval) throws MathException, IllegalArgumentException {
        if (xval.length == 0 || yval.length == 0 || zval.length == 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NO_DATA, new Object[0]);
        }
        if (xval.length != zval.length) {
            throw new DimensionMismatchException(xval.length, zval.length);
        }
        MathUtils.checkOrder(xval, MathUtils.OrderDirection.INCREASING, true);
        MathUtils.checkOrder(yval, MathUtils.OrderDirection.INCREASING, true);
        int xLen = xval.length;
        int yLen = yval.length;
        double[][] zX = new double[yLen][xLen];
        for (int i = 0; i < xLen; i++) {
            if (zval[i].length != yLen) {
                throw new DimensionMismatchException(zval[i].length, yLen);
            }
            for (int j = 0; j < yLen; j++) {
                zX[j][i] = zval[i][j];
            }
        }
        SplineInterpolator spInterpolator = new SplineInterpolator();
        PolynomialSplineFunction[] ySplineX = new PolynomialSplineFunction[yLen];
        for (int j2 = 0; j2 < yLen; j2++) {
            ySplineX[j2] = spInterpolator.interpolate(xval, zX[j2]);
        }
        double[][] zY_1 = new double[xLen][yLen];
        for (int j3 = 0; j3 < yLen; j3++) {
            PolynomialSplineFunction f = ySplineX[j3];
            for (int i2 = 0; i2 < xLen; i2++) {
                zY_1[i2][j3] = f.value(xval[i2]);
            }
        }
        PolynomialSplineFunction[] xSplineY = new PolynomialSplineFunction[xLen];
        for (int i3 = 0; i3 < xLen; i3++) {
            xSplineY[i3] = spInterpolator.interpolate(yval, zY_1[i3]);
        }
        double[][] zY_2 = new double[xLen][yLen];
        for (int i4 = 0; i4 < xLen; i4++) {
            PolynomialSplineFunction f2 = xSplineY[i4];
            for (int j4 = 0; j4 < yLen; j4++) {
                zY_2[i4][j4] = f2.value(yval[j4]);
            }
        }
        double[][] dZdX = new double[xLen][yLen];
        for (int j5 = 0; j5 < yLen; j5++) {
            UnivariateRealFunction f3 = ySplineX[j5].derivative();
            for (int i5 = 0; i5 < xLen; i5++) {
                dZdX[i5][j5] = f3.value(xval[i5]);
            }
        }
        double[][] dZdY = new double[xLen][yLen];
        for (int i6 = 0; i6 < xLen; i6++) {
            UnivariateRealFunction f4 = xSplineY[i6].derivative();
            for (int j6 = 0; j6 < yLen; j6++) {
                dZdY[i6][j6] = f4.value(yval[j6]);
            }
        }
        double[][] dZdXdY = new double[xLen][yLen];
        for (int i7 = 0; i7 < xLen; i7++) {
            int nI = nextIndex(i7, xLen);
            int pI = previousIndex(i7);
            for (int j7 = 0; j7 < yLen; j7++) {
                int nJ = nextIndex(j7, yLen);
                int pJ = previousIndex(j7);
                dZdXdY[i7][j7] = (((zY_2[nI][nJ] - zY_2[nI][pJ]) - zY_2[pI][nJ]) + zY_2[pI][pJ]) / ((xval[nI] - xval[pI]) * (yval[nJ] - yval[pJ]));
            }
        }
        return new BicubicSplineInterpolatingFunction(xval, yval, zY_2, dZdX, dZdY, dZdXdY);
    }

    private int nextIndex(int i, int max) {
        int index = i + 1;
        return index < max ? index : index - 1;
    }

    private int previousIndex(int i) {
        int index = i - 1;
        if (index >= 0) {
            return index;
        }
        return 0;
    }
}
