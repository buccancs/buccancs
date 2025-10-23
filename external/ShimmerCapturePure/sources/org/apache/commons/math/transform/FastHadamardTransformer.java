package org.apache.commons.math.transform;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/transform/FastHadamardTransformer.class */
public class FastHadamardTransformer implements RealTransformer {
    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] transform(double[] f) throws IllegalArgumentException {
        return fht(f);
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] transform(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        return fht(FastFourierTransformer.sample(f, min, max, n));
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] inversetransform(double[] f) throws IllegalArgumentException {
        return FastFourierTransformer.scaleArray(fht(f), 1.0d / f.length);
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] inversetransform(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        double[] unscaled = fht(FastFourierTransformer.sample(f, min, max, n));
        return FastFourierTransformer.scaleArray(unscaled, 1.0d / n);
    }

    public int[] transform(int[] f) throws IllegalArgumentException {
        return fht(f);
    }

    protected double[] fht(double[] x) throws IllegalArgumentException {
        int n = x.length;
        int halfN = n / 2;
        if (!FastFourierTransformer.isPowerOf2(n)) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(n));
        }
        double[] yPrevious = new double[n];
        double[] yCurrent = (double[]) x.clone();
        int i = 1;
        while (true) {
            int j = i;
            if (j < n) {
                double[] yTmp = yCurrent;
                yCurrent = yPrevious;
                yPrevious = yTmp;
                for (int i2 = 0; i2 < halfN; i2++) {
                    int twoI = 2 * i2;
                    yCurrent[i2] = yPrevious[twoI] + yPrevious[twoI + 1];
                }
                for (int i3 = halfN; i3 < n; i3++) {
                    int twoI2 = 2 * i3;
                    yCurrent[i3] = yPrevious[twoI2 - n] - yPrevious[(twoI2 - n) + 1];
                }
                i = j << 1;
            } else {
                return yCurrent;
            }
        }
    }

    protected int[] fht(int[] x) throws IllegalArgumentException {
        int n = x.length;
        int halfN = n / 2;
        if (!FastFourierTransformer.isPowerOf2(n)) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(n));
        }
        int[] yPrevious = new int[n];
        int[] yCurrent = (int[]) x.clone();
        int i = 1;
        while (true) {
            int j = i;
            if (j < n) {
                int[] yTmp = yCurrent;
                yCurrent = yPrevious;
                yPrevious = yTmp;
                for (int i2 = 0; i2 < halfN; i2++) {
                    int twoI = 2 * i2;
                    yCurrent[i2] = yPrevious[twoI] + yPrevious[twoI + 1];
                }
                for (int i3 = halfN; i3 < n; i3++) {
                    int twoI2 = 2 * i3;
                    yCurrent[i3] = yPrevious[twoI2 - n] - yPrevious[(twoI2 - n) + 1];
                }
                i = j << 1;
            } else {
                return yCurrent;
            }
        }
    }
}
