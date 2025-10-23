package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public class UnivariatePeriodicInterpolator implements UnivariateInterpolator {
    public static final int DEFAULT_EXTEND = 5;
    private final int extend;
    private final UnivariateInterpolator interpolator;
    private final double period;

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d, int i) {
        this.interpolator = univariateInterpolator;
        this.period = d;
        this.extend = i;
    }

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d) {
        this(univariateInterpolator, d, 5);
    }

    @Override // org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator
    public UnivariateFunction interpolate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        if (dArr.length < this.extend) {
            throw new NumberIsTooSmallException(Integer.valueOf(dArr.length), Integer.valueOf(this.extend), true);
        }
        MathArrays.checkOrder(dArr);
        final double d = dArr[0];
        int length = dArr.length + (this.extend * 2);
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        for (int i = 0; i < dArr.length; i++) {
            int i2 = i + this.extend;
            dArr3[i2] = MathUtils.reduce(dArr[i], this.period, d);
            dArr4[i2] = dArr2[i];
        }
        int i3 = 0;
        while (true) {
            int i4 = this.extend;
            if (i3 < i4) {
                int length2 = (dArr.length - i4) + i3;
                double dReduce = MathUtils.reduce(dArr[length2], this.period, d);
                double d2 = this.period;
                dArr3[i3] = dReduce - d2;
                dArr4[i3] = dArr2[length2];
                int i5 = (length - this.extend) + i3;
                dArr3[i5] = MathUtils.reduce(dArr[i3], d2, d) + this.period;
                dArr4[i5] = dArr2[i3];
                i3++;
            } else {
                MathArrays.sortInPlace(dArr3, dArr4);
                final UnivariateFunction univariateFunctionInterpolate = this.interpolator.interpolate(dArr3, dArr4);
                return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double d3) throws MathIllegalArgumentException {
                        return univariateFunctionInterpolate.value(MathUtils.reduce(d3, UnivariatePeriodicInterpolator.this.period, d));
                    }
                };
            }
        }
    }
}
