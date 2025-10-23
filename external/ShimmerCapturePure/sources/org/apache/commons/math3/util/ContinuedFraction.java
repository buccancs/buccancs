package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes5.dex */
public abstract class ContinuedFraction {
    private static final double DEFAULT_EPSILON = 1.0E-8d;

    protected ContinuedFraction() {
    }

    protected abstract double getA(int i, double d);

    protected abstract double getB(int i, double d);

    public double evaluate(double d) throws ConvergenceException {
        return evaluate(d, 1.0E-8d, Integer.MAX_VALUE);
    }

    public double evaluate(double d, double d2) throws ConvergenceException {
        return evaluate(d, d2, Integer.MAX_VALUE);
    }

    public double evaluate(double d, int i) throws ConvergenceException, MaxCountExceededException {
        return evaluate(d, 1.0E-8d, i);
    }

    public double evaluate(double d, double d2, int i) throws ConvergenceException, MaxCountExceededException {
        double a = getA(0, d);
        if (Precision.equals(a, 0.0d, 1.0E-50d)) {
            a = 1.0E-50d;
        }
        double d3 = 0.0d;
        double d4 = a;
        int i2 = 1;
        while (i2 < i) {
            double a2 = getA(i2, d);
            double b = getB(i2, d);
            double d5 = (d3 * b) + a2;
            if (Precision.equals(d5, 0.0d, 1.0E-50d)) {
                d5 = 1.0E-50d;
            }
            double d6 = a2 + (b / a);
            a = Precision.equals(d6, 0.0d, 1.0E-50d) ? 1.0E-50d : d6;
            d3 = 1.0d / d5;
            double d7 = a * d3;
            d4 *= d7;
            if (Double.isInfinite(d4)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, Double.valueOf(d));
            }
            if (Double.isNaN(d4)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, Double.valueOf(d));
            }
            if (FastMath.abs(d7 - 1.0d) < d2) {
                break;
            }
            i2++;
        }
        if (i2 < i) {
            return d4;
        }
        throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, Integer.valueOf(i), Double.valueOf(d));
    }
}
