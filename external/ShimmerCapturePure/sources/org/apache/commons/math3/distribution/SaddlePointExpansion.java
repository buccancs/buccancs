package org.apache.commons.math3.distribution;

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
final class SaddlePointExpansion {
    private static final double HALF_LOG_2_PI = FastMath.log(6.283185307179586d) * 0.5d;
    private static final double[] EXACT_STIRLING_ERRORS = {0.0d, 0.15342640972002736d, 0.08106146679532726d, 0.05481412105191765d, 0.0413406959554093d, 0.03316287351993629d, 0.02767792568499834d, 0.023746163656297496d, 0.020790672103765093d, 0.018488450532673187d, 0.016644691189821193d, 0.015134973221917378d, 0.013876128823070748d, 0.012810465242920227d, 0.01189670994589177d, 0.011104559758206917d, 0.010411265261972096d, 0.009799416126158804d, 0.009255462182712733d, 0.008768700134139386d, 0.00833056343336287d, 0.00793411456431402d, 0.007573675487951841d, 0.007244554301320383d, 0.00694284010720953d, 0.006665247032707682d, 0.006408994188004207d, 0.006171712263039458d, 0.0059513701127588475d, 0.0057462165130101155d, 0.005554733551962801d};

    private SaddlePointExpansion() {
    }

    static double getStirlingError(double d) {
        if (d >= 15.0d) {
            double d2 = d * d;
            return (0.08333333333333333d - ((0.002777777777777778d - ((7.936507936507937E-4d - ((5.952380952380953E-4d - (8.417508417508417E-4d / d2)) / d2)) / d2)) / d2)) / d;
        }
        double d3 = 2.0d * d;
        if (FastMath.floor(d3) == d3) {
            return EXACT_STIRLING_ERRORS[(int) d3];
        }
        return ((Gamma.logGamma(1.0d + d) - ((0.5d + d) * FastMath.log(d))) + d) - HALF_LOG_2_PI;
    }

    static double getDeviancePart(double d, double d2) {
        double d3 = d - d2;
        double d4 = d + d2;
        if (FastMath.abs(d3) >= 0.1d * d4) {
            return ((FastMath.log(d / d2) * d) + d2) - d;
        }
        double d5 = d3 / d4;
        double d6 = d3 * d5;
        double d7 = d * 2.0d * d5;
        double d8 = d5 * d5;
        double d9 = Double.NaN;
        int i = 1;
        while (d6 != d9) {
            d7 *= d8;
            double d10 = (d7 / ((i * 2) + 1)) + d6;
            i++;
            double d11 = d6;
            d6 = d10;
            d9 = d11;
        }
        return d6;
    }

    static double logBinomialProbability(int i, int i2, double d, double d2) {
        double d3;
        double dLog;
        if (i == 0) {
            if (d < 0.1d) {
                double d4 = i2;
                return (-getDeviancePart(d4, d2 * d4)) - (d4 * d);
            }
            d3 = i2;
            dLog = FastMath.log(d2);
        } else {
            if (i != i2) {
                double d5 = i2;
                double d6 = i;
                double d7 = i2 - i;
                return (FastMath.log(((d6 * 6.283185307179586d) * d7) / d5) * (-0.5d)) + ((((getStirlingError(d5) - getStirlingError(d6)) - getStirlingError(d7)) - getDeviancePart(d6, d * d5)) - getDeviancePart(d7, d2 * d5));
            }
            if (d2 < 0.1d) {
                double d8 = i2;
                return (-getDeviancePart(d8, d * d8)) - (d8 * d2);
            }
            d3 = i2;
            dLog = FastMath.log(d);
        }
        return d3 * dLog;
    }
}
