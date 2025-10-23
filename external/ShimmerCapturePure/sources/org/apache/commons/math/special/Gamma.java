package org.apache.commons.math.special;

import org.apache.commons.math.MathException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.util.ContinuedFraction;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/special/Gamma.class */
public class Gamma {
    public static final double GAMMA = 0.5772156649015329d;
    private static final double DEFAULT_EPSILON = 1.0E-14d;
    private static final double[] LANCZOS = {0.9999999999999971d, 57.15623566586292d, -59.59796035547549d, 14.136097974741746d, -0.4919138160976202d, 3.399464998481189E-5d, 4.652362892704858E-5d, -9.837447530487956E-5d, 1.580887032249125E-4d, -2.1026444172410488E-4d, 2.1743961811521265E-4d, -1.643181065367639E-4d, 8.441822398385275E-5d, -2.6190838401581408E-5d, 3.6899182659531625E-6d};
    private static final double HALF_LOG_2_PI = 0.5d * FastMath.log(6.283185307179586d);
    private static final double C_LIMIT = 49.0d;
    private static final double S_LIMIT = 1.0E-5d;

    private Gamma() {
    }

    public static double logGamma(double x) {
        double ret;
        if (Double.isNaN(x) || x <= 0.0d) {
            ret = Double.NaN;
        } else {
            double sum = 0.0d;
            for (int i = LANCZOS.length - 1; i > 0; i--) {
                sum += LANCZOS[i] / (x + i);
            }
            double tmp = x + 4.7421875d + 0.5d;
            ret = (((x + 0.5d) * FastMath.log(tmp)) - tmp) + HALF_LOG_2_PI + FastMath.log((sum + LANCZOS[0]) / x);
        }
        return ret;
    }

    public static double regularizedGammaP(double a, double x) throws MathException {
        return regularizedGammaP(a, x, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) throws MathException {
        double ret;
        double sum;
        if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0d || x < 0.0d) {
            ret = Double.NaN;
        } else if (x == 0.0d) {
            ret = 0.0d;
        } else if (x >= a + 1.0d) {
            ret = 1.0d - regularizedGammaQ(a, x, epsilon, maxIterations);
        } else {
            double n = 0.0d;
            double an = 1.0d / a;
            double d = an;
            while (true) {
                sum = d;
                if (FastMath.abs(an / sum) <= epsilon || n >= maxIterations || sum >= Double.POSITIVE_INFINITY) {
                    break;
                }
                n += 1.0d;
                an *= x / (a + n);
                d = sum + an;
            }
            if (n >= maxIterations) {
                throw new MaxIterationsExceededException(maxIterations);
            }
            if (Double.isInfinite(sum)) {
                ret = 1.0d;
            } else {
                ret = FastMath.exp(((-x) + (a * FastMath.log(x))) - logGamma(a)) * sum;
            }
        }
        return ret;
    }

    public static double regularizedGammaQ(double a, double x) throws MathException {
        return regularizedGammaQ(a, x, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedGammaQ(final double a, double x, double epsilon, int maxIterations) throws MathException {
        double ret;
        if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0d || x < 0.0d) {
            ret = Double.NaN;
        } else if (x == 0.0d) {
            ret = 1.0d;
        } else if (x < a + 1.0d) {
            ret = 1.0d - regularizedGammaP(a, x, epsilon, maxIterations);
        } else {
            ContinuedFraction cf = new ContinuedFraction() { // from class: org.apache.commons.math.special.Gamma.1
                @Override // org.apache.commons.math.util.ContinuedFraction
                protected double getA(int n, double x2) {
                    return (((2.0d * n) + 1.0d) - a) + x2;
                }

                @Override // org.apache.commons.math.util.ContinuedFraction
                protected double getB(int n, double x2) {
                    return n * (a - n);
                }
            };
            double ret2 = 1.0d / cf.evaluate(x, epsilon, maxIterations);
            ret = FastMath.exp(((-x) + (a * FastMath.log(x))) - logGamma(a)) * ret2;
        }
        return ret;
    }

    public static double digamma(double x) {
        if (x > 0.0d && x <= 1.0E-5d) {
            return (-0.5772156649015329d) - (1.0d / x);
        }
        if (x >= C_LIMIT) {
            double inv = 1.0d / (x * x);
            return (FastMath.log(x) - (0.5d / x)) - (inv * (0.08333333333333333d + (inv * (0.008333333333333333d - (inv / 252.0d)))));
        }
        return digamma(x + 1.0d) - (1.0d / x);
    }

    public static double trigamma(double x) {
        if (x > 0.0d && x <= 1.0E-5d) {
            return 1.0d / (x * x);
        }
        if (x >= C_LIMIT) {
            double inv = 1.0d / (x * x);
            return (1.0d / x) + (inv / 2.0d) + ((inv / x) * (0.16666666666666666d - (inv * (0.03333333333333333d + (inv / 42.0d)))));
        }
        return trigamma(x + 1.0d) + (1.0d / (x * x));
    }
}
