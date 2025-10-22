package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class Beta {
    private static final double DEFAULT_EPSILON = 1.0E-14d;
    private static final double[] DELTA = {0.08333333333333333d, -2.777777777777778E-5d, 7.936507936507937E-8d, -5.952380952380953E-10d, 8.417508417508329E-12d, -1.917526917518546E-13d, 6.410256405103255E-15d, -2.955065141253382E-16d, 1.7964371635940225E-17d, -1.3922896466162779E-18d, 1.338028550140209E-19d, -1.542460098679661E-20d, 1.9770199298095743E-21d, -2.3406566479399704E-22d, 1.713480149663986E-23d};
    private static final double HALF_LOG_TWO_PI = 0.9189385332046727d;

    private Beta() {
    }

    public static double regularizedBeta(double d, double d2, double d3) {
        return regularizedBeta(d, d2, d3, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double d, double d2, double d3, double d4) {
        return regularizedBeta(d, d2, d3, d4, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double d, double d2, double d3, int i) {
        return regularizedBeta(d, d2, d3, DEFAULT_EPSILON, i);
    }

    public static double regularizedBeta(double d, final double d2, final double d3, double d4, int i) {
        if (Double.isNaN(d) || Double.isNaN(d2) || Double.isNaN(d3) || d < 0.0d || d > 1.0d || d2 <= 0.0d || d3 <= 0.0d) {
            return Double.NaN;
        }
        double d5 = 2.0d + d3 + d2;
        if (d > (d2 + 1.0d) / d5) {
            double d6 = 1.0d - d;
            if (d6 <= (d3 + 1.0d) / d5) {
                return 1.0d - regularizedBeta(d6, d3, d2, d4, i);
            }
        }
        return (FastMath.exp((((FastMath.log(d) * d2) + (FastMath.log1p(-d) * d3)) - FastMath.log(d2)) - logBeta(d2, d3)) * 1.0d) / new ContinuedFraction() { // from class: org.apache.commons.math3.special.Beta.1
            @Override // org.apache.commons.math3.util.ContinuedFraction
            protected double getA(int i2, double d7) {
                return 1.0d;
            }

            @Override // org.apache.commons.math3.util.ContinuedFraction
            protected double getB(int i2, double d7) {
                if (i2 % 2 == 0) {
                    double d8 = i2 / 2.0d;
                    double d9 = (d3 - d8) * d8 * d7;
                    double d10 = d2;
                    double d11 = d8 * 2.0d;
                    return d9 / (((d10 + d11) - 1.0d) * (d10 + d11));
                }
                double d12 = (i2 - 1.0d) / 2.0d;
                double d13 = d2;
                double d14 = -((d13 + d12) * (d3 + d13 + d12) * d7);
                double d15 = d12 * 2.0d;
                return d14 / ((d13 + d15) * ((d13 + d15) + 1.0d));
            }
        }.evaluate(d, d4, i);
    }

    @Deprecated
    public static double logBeta(double d, double d2, double d3, int i) {
        return logBeta(d, d2);
    }

    private static double logGammaSum(double d, double d2) throws NumberIsTooSmallException, OutOfRangeException, NumberIsTooLargeException {
        double dLogGamma1p;
        double dLog;
        Double dValueOf = Double.valueOf(2.0d);
        Double dValueOf2 = Double.valueOf(1.0d);
        if (d < 1.0d || d > 2.0d) {
            throw new OutOfRangeException(Double.valueOf(d), dValueOf2, dValueOf);
        }
        if (d2 < 1.0d || d2 > 2.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), dValueOf2, dValueOf);
        }
        double d3 = (d - 1.0d) + (d2 - 1.0d);
        if (d3 <= 0.5d) {
            return Gamma.logGamma1p(d3 + 1.0d);
        }
        if (d3 <= 1.5d) {
            dLogGamma1p = Gamma.logGamma1p(d3);
            dLog = FastMath.log1p(d3);
        } else {
            dLogGamma1p = Gamma.logGamma1p(d3 - 1.0d);
            dLog = FastMath.log(d3 * (1.0d + d3));
        }
        return dLogGamma1p + dLog;
    }

    private static double logGammaMinusLogGammaSum(double d, double d2) throws NumberIsTooSmallException, OutOfRangeException {
        double d3;
        double dDeltaMinusDeltaSum;
        if (d < 0.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(0.0d), true);
        }
        if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), Double.valueOf(10.0d), true);
        }
        if (d <= d2) {
            d3 = (d - 0.5d) + d2;
            dDeltaMinusDeltaSum = deltaMinusDeltaSum(d, d2);
        } else {
            d3 = (d2 - 0.5d) + d;
            dDeltaMinusDeltaSum = deltaMinusDeltaSum(d2, d);
        }
        double dLog1p = d3 * FastMath.log1p(d / d2);
        double dLog = d * (FastMath.log(d2) - 1.0d);
        return dLog1p <= dLog ? (dDeltaMinusDeltaSum - dLog1p) - dLog : (dDeltaMinusDeltaSum - dLog) - dLog1p;
    }

    private static double deltaMinusDeltaSum(double d, double d2) throws NumberIsTooSmallException, OutOfRangeException {
        if (d < 0.0d || d > d2) {
            throw new OutOfRangeException(Double.valueOf(d), 0, Double.valueOf(d2));
        }
        if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), 10, true);
        }
        double d3 = d / d2;
        double d4 = d3 + 1.0d;
        double d5 = d3 / d4;
        double d6 = 1.0d / d4;
        double d7 = d6 * d6;
        int length = DELTA.length;
        double[] dArr = new double[length];
        dArr[0] = 1.0d;
        for (int i = 1; i < length; i++) {
            dArr[i] = (dArr[i - 1] * d7) + d6 + 1.0d;
        }
        double d8 = 10.0d / d2;
        double d9 = d8 * d8;
        double[] dArr2 = DELTA;
        double d10 = dArr2[dArr2.length - 1] * dArr[length - 1];
        for (int length2 = dArr2.length - 2; length2 >= 0; length2--) {
            d10 = (d10 * d9) + (DELTA[length2] * dArr[length2]);
        }
        return (d10 * d5) / d2;
    }

    private static double sumDeltaMinusDeltaSum(double d, double d2) {
        Double dValueOf = Double.valueOf(10.0d);
        if (d < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), dValueOf, true);
        }
        if (d2 < 10.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(d2), dValueOf, true);
        }
        double dMin = FastMath.min(d, d2);
        double dMax = FastMath.max(d, d2);
        double d3 = 10.0d / dMin;
        double d4 = d3 * d3;
        double[] dArr = DELTA;
        double d5 = dArr[dArr.length - 1];
        for (int length = dArr.length - 2; length >= 0; length--) {
            d5 = (d5 * d4) + DELTA[length];
        }
        return (d5 / dMin) + deltaMinusDeltaSum(dMin, dMax);
    }

    public static double logBeta(double d, double d2) {
        double dLog;
        double dLogGamma;
        if (Double.isNaN(d) || Double.isNaN(d2) || d <= 0.0d || d2 <= 0.0d) {
            return Double.NaN;
        }
        double dMin = FastMath.min(d, d2);
        double dMax = FastMath.max(d, d2);
        if (dMin >= 10.0d) {
            double dSumDeltaMinusDeltaSum = sumDeltaMinusDeltaSum(dMin, dMax);
            double d3 = dMin / dMax;
            double dLog2 = (-(dMin - 0.5d)) * FastMath.log(d3 / (1.0d + d3));
            double dLog1p = FastMath.log1p(d3) * dMax;
            if (dLog2 <= dLog1p) {
                return ((((FastMath.log(dMax) * (-0.5d)) + HALF_LOG_TWO_PI) + dSumDeltaMinusDeltaSum) - dLog2) - dLog1p;
            }
            return ((((FastMath.log(dMax) * (-0.5d)) + HALF_LOG_TWO_PI) + dSumDeltaMinusDeltaSum) - dLog1p) - dLog2;
        }
        if (dMin <= 2.0d) {
            if (dMin < 1.0d) {
                if (dMax >= 10.0d) {
                    return Gamma.logGamma(dMin) + logGammaMinusLogGammaSum(dMin, dMax);
                }
                return FastMath.log((Gamma.gamma(dMin) * Gamma.gamma(dMax)) / Gamma.gamma(dMin + dMax));
            }
            if (dMax <= 2.0d) {
                return (Gamma.logGamma(dMin) + Gamma.logGamma(dMax)) - logGammaSum(dMin, dMax);
            }
            if (dMax < 10.0d) {
                double d4 = 1.0d;
                while (dMax > 2.0d) {
                    dMax -= 1.0d;
                    d4 *= dMax / (dMin + dMax);
                }
                return FastMath.log(d4) + Gamma.logGamma(dMin) + (Gamma.logGamma(dMax) - logGammaSum(dMin, dMax));
            }
            return Gamma.logGamma(dMin) + logGammaMinusLogGammaSum(dMin, dMax);
        }
        if (dMax > 1000.0d) {
            int iFloor = (int) FastMath.floor(dMin - 1.0d);
            double d5 = 1.0d;
            for (int i = 0; i < iFloor; i++) {
                dMin -= 1.0d;
                d5 *= dMin / ((dMin / dMax) + 1.0d);
            }
            dLog = FastMath.log(d5) - (iFloor * FastMath.log(dMax));
            dLogGamma = Gamma.logGamma(dMin) + logGammaMinusLogGammaSum(dMin, dMax);
        } else {
            double d6 = 1.0d;
            while (dMin > 2.0d) {
                dMin -= 1.0d;
                double d7 = dMin / dMax;
                d6 *= d7 / (d7 + 1.0d);
            }
            if (dMax < 10.0d) {
                double d8 = 1.0d;
                while (dMax > 2.0d) {
                    dMax -= 1.0d;
                    d8 *= dMax / (dMin + dMax);
                }
                dLog = FastMath.log(d6) + FastMath.log(d8);
                dLogGamma = Gamma.logGamma(dMin) + (Gamma.logGamma(dMax) - logGammaSum(dMin, dMax));
            } else {
                return FastMath.log(d6) + Gamma.logGamma(dMin) + logGammaMinusLogGammaSum(dMin, dMax);
            }
        }
        return dLog + dLogGamma;
    }
}
