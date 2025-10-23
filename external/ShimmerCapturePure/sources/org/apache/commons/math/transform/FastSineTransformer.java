package org.apache.commons.math.transform;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/transform/FastSineTransformer.class */
public class FastSineTransformer implements RealTransformer {
    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] transform(double[] f) throws IllegalArgumentException {
        return fst(f);
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] transform(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        double[] data = FastFourierTransformer.sample(f, min, max, n);
        data[0] = 0.0d;
        return fst(data);
    }

    public double[] transform2(double[] f) throws IllegalArgumentException {
        double scaling_coefficient = FastMath.sqrt(2.0d / f.length);
        return FastFourierTransformer.scaleArray(fst(f), scaling_coefficient);
    }

    public double[] transform2(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        double[] data = FastFourierTransformer.sample(f, min, max, n);
        data[0] = 0.0d;
        double scaling_coefficient = FastMath.sqrt(2.0d / n);
        return FastFourierTransformer.scaleArray(fst(data), scaling_coefficient);
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] inversetransform(double[] f) throws IllegalArgumentException {
        double scaling_coefficient = 2.0d / f.length;
        return FastFourierTransformer.scaleArray(fst(f), scaling_coefficient);
    }

    @Override // org.apache.commons.math.transform.RealTransformer
    public double[] inversetransform(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        double[] data = FastFourierTransformer.sample(f, min, max, n);
        data[0] = 0.0d;
        double scaling_coefficient = 2.0d / n;
        return FastFourierTransformer.scaleArray(fst(data), scaling_coefficient);
    }

    public double[] inversetransform2(double[] f) throws IllegalArgumentException {
        return transform2(f);
    }

    public double[] inversetransform2(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException, IllegalArgumentException {
        return transform2(f, min, max, n);
    }

    protected double[] fst(double[] f) throws IllegalArgumentException {
        double[] transformed = new double[f.length];
        FastFourierTransformer.verifyDataSet(f);
        if (f[0] != 0.0d) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, Double.valueOf(f[0]));
        }
        int n = f.length;
        if (n == 1) {
            transformed[0] = 0.0d;
            return transformed;
        }
        double[] x = new double[n];
        x[0] = 0.0d;
        x[n >> 1] = 2.0d * f[n >> 1];
        for (int i = 1; i < (n >> 1); i++) {
            double a = FastMath.sin((i * 3.141592653589793d) / n) * (f[i] + f[n - i]);
            double b = 0.5d * (f[i] - f[n - i]);
            x[i] = a + b;
            x[n - i] = a - b;
        }
        FastFourierTransformer transformer = new FastFourierTransformer();
        Complex[] y = transformer.transform(x);
        transformed[0] = 0.0d;
        transformed[1] = 0.5d * y[0].getReal();
        for (int i2 = 1; i2 < (n >> 1); i2++) {
            transformed[2 * i2] = -y[i2].getImaginary();
            transformed[(2 * i2) + 1] = y[i2].getReal() + transformed[(2 * i2) - 1];
        }
        return transformed;
    }
}
