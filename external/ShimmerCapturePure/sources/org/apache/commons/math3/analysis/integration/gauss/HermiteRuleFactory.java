package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public class HermiteRuleFactory extends BaseRuleFactory<Double> {
    private static final double H0 = 0.7511255444649425d;
    private static final double H1 = 1.0622519320271968d;
    private static final double SQRT_PI = 1.772453850905516d;

    @Override // org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory
    protected Pair<Double[], Double[]> computeRule(int i) throws DimensionMismatchException {
        double d;
        Double dValueOf = Double.valueOf(0.0d);
        int i2 = 0;
        int i3 = 1;
        if (i == 1) {
            return new Pair<>(new Double[]{dValueOf}, new Double[]{Double.valueOf(SQRT_PI)});
        }
        int i4 = i - 1;
        Double[] first = getRuleInternal(i4).getFirst();
        Double[] dArr = new Double[i];
        Double[] dArr2 = new Double[i];
        double dSqrt = FastMath.sqrt(i4 * 2);
        double dSqrt2 = FastMath.sqrt(i * 2);
        int i5 = i / 2;
        while (true) {
            d = H0;
            if (i2 >= i5) {
                break;
            }
            double dDoubleValue = i2 == 0 ? -dSqrt : first[i2 - 1].doubleValue();
            double dDoubleValue2 = i5 == i3 ? -0.5d : first[i2].doubleValue();
            double d2 = dDoubleValue * H1;
            double d3 = 0.7511255444649425d;
            while (i3 < i) {
                int i6 = i3 + 1;
                double d4 = dSqrt;
                double d5 = i6;
                double dSqrt3 = ((FastMath.sqrt(2.0d / d5) * dDoubleValue) * d2) - (FastMath.sqrt(i3 / d5) * d3);
                d3 = d2;
                dSqrt = d4;
                i3 = i6;
                d2 = dSqrt3;
                first = first;
            }
            Double[] dArr3 = first;
            double d6 = dSqrt;
            double d7 = (dDoubleValue + dDoubleValue2) * 0.5d;
            double d8 = 0.7511255444649425d;
            boolean z = false;
            while (!z) {
                z = dDoubleValue2 - dDoubleValue <= Math.ulp(d7);
                double d9 = d7 * H1;
                double d10 = 0.7511255444649425d;
                int i7 = 1;
                while (i7 < i) {
                    int i8 = i7 + 1;
                    double d11 = dDoubleValue;
                    double d12 = i8;
                    double dSqrt4 = ((FastMath.sqrt(2.0d / d12) * d7) * d9) - (FastMath.sqrt(i7 / d12) * d10);
                    d10 = d9;
                    i7 = i8;
                    d9 = dSqrt4;
                    dDoubleValue = d11;
                }
                double d13 = dDoubleValue;
                if (z) {
                    d8 = d10;
                    dDoubleValue = d13;
                } else {
                    if (d2 * d9 < 0.0d) {
                        dDoubleValue2 = d7;
                        dDoubleValue = d13;
                    } else {
                        dDoubleValue = d7;
                        d2 = d9;
                    }
                    d7 = (dDoubleValue + dDoubleValue2) * 0.5d;
                    d8 = d10;
                }
            }
            double d14 = d8 * dSqrt2;
            double d15 = 2.0d / (d14 * d14);
            dArr[i2] = Double.valueOf(d7);
            dArr2[i2] = Double.valueOf(d15);
            int i9 = i4 - i2;
            dArr[i9] = Double.valueOf(-d7);
            dArr2[i9] = Double.valueOf(d15);
            i2++;
            dSqrt = d6;
            first = dArr3;
            i3 = 1;
        }
        if (i % 2 != 0) {
            for (int i10 = 1; i10 < i; i10 += 2) {
                d *= -FastMath.sqrt(i10 / (i10 + 1));
            }
            double d16 = dSqrt2 * d;
            dArr[i5] = dValueOf;
            dArr2[i5] = Double.valueOf(2.0d / (d16 * d16));
        }
        return new Pair<>(dArr, dArr2);
    }
}
