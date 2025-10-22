package org.apache.commons.math.special;

import org.apache.commons.math.MathException;
import org.apache.commons.math.util.ContinuedFraction;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/special/Beta.class */
public class Beta {
    private static final double DEFAULT_EPSILON = 1.0E-14d;

    private Beta() {
    }

    public static double regularizedBeta(double x, double a, double b) throws MathException {
        return regularizedBeta(x, a, b, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double x, double a, double b, double epsilon) throws MathException {
        return regularizedBeta(x, a, b, epsilon, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double x, double a, double b, int maxIterations) throws MathException {
        return regularizedBeta(x, a, b, DEFAULT_EPSILON, maxIterations);
    }

    public static double regularizedBeta(double x, final double a, final double b, double epsilon, int maxIterations) throws MathException {
        double ret;
        if (Double.isNaN(x) || Double.isNaN(a) || Double.isNaN(b) || x < 0.0d || x > 1.0d || a <= 0.0d || b <= 0.0d) {
            ret = Double.NaN;
        } else if (x > (a + 1.0d) / ((a + b) + 2.0d)) {
            ret = 1.0d - regularizedBeta(1.0d - x, b, a, epsilon, maxIterations);
        } else {
            ContinuedFraction fraction = new ContinuedFraction() { // from class: org.apache.commons.math.special.Beta.1
                @Override // org.apache.commons.math.util.ContinuedFraction
                protected double getB(int n, double x2) {
                    double ret2;
                    if (n % 2 == 0) {
                        double m = n / 2.0d;
                        ret2 = ((m * (b - m)) * x2) / (((a + (2.0d * m)) - 1.0d) * (a + (2.0d * m)));
                    } else {
                        double m2 = (n - 1.0d) / 2.0d;
                        ret2 = (-(((a + m2) * ((a + b) + m2)) * x2)) / ((a + (2.0d * m2)) * ((a + (2.0d * m2)) + 1.0d));
                    }
                    return ret2;
                }

                @Override // org.apache.commons.math.util.ContinuedFraction
                protected double getA(int n, double x2) {
                    return 1.0d;
                }
            };
            ret = (FastMath.exp((((a * FastMath.log(x)) + (b * FastMath.log(1.0d - x))) - FastMath.log(a)) - logBeta(a, b, epsilon, maxIterations)) * 1.0d) / fraction.evaluate(x, epsilon, maxIterations);
        }
        return ret;
    }

    public static double logBeta(double a, double b) {
        return logBeta(a, b, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double logBeta(double a, double b, double epsilon, int maxIterations) {
        double ret;
        if (Double.isNaN(a) || Double.isNaN(b) || a <= 0.0d || b <= 0.0d) {
            ret = Double.NaN;
        } else {
            ret = (Gamma.logGamma(a) + Gamma.logGamma(b)) - Gamma.logGamma(a + b);
        }
        return ret;
    }
}
