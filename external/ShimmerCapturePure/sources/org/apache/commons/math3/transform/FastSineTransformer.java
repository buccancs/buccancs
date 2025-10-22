package org.apache.commons.math3.transform;

import java.io.Serializable;

import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class FastSineTransformer implements RealTransformer, Serializable {
    static final long serialVersionUID = 20120211;
    private final DstNormalization normalization;

    public FastSineTransformer(DstNormalization dstNormalization) {
        this.normalization = dstNormalization;
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(double[] dArr, TransformType transformType) {
        if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
            return TransformUtils.scaleArray(fst(dArr), FastMath.sqrt(2.0d / dArr.length));
        }
        if (transformType == TransformType.FORWARD) {
            return fst(dArr);
        }
        return TransformUtils.scaleArray(fst(dArr), 2.0d / dArr.length);
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        double[] dArrSample = FunctionUtils.sample(univariateFunction, d, d2, i);
        dArrSample[0] = 0.0d;
        return transform(dArrSample, transformType);
    }

    protected double[] fst(double[] dArr) throws MathIllegalArgumentException {
        double[] dArr2 = new double[dArr.length];
        if (!ArithmeticUtils.isPowerOfTwo(dArr.length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(dArr.length));
        }
        if (dArr[0] != 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, Double.valueOf(dArr[0]));
        }
        int length = dArr.length;
        if (length == 1) {
            dArr2[0] = 0.0d;
            return dArr2;
        }
        double[] dArr3 = new double[length];
        dArr3[0] = 0.0d;
        int i = length >> 1;
        dArr3[i] = dArr[i] * 2.0d;
        for (int i2 = 1; i2 < i; i2++) {
            double dSin = FastMath.sin((i2 * 3.141592653589793d) / length);
            double d = dArr[i2];
            int i3 = length - i2;
            double d2 = dArr[i3];
            double d3 = dSin * (d + d2);
            double d4 = (d - d2) * 0.5d;
            dArr3[i2] = d3 + d4;
            dArr3[i3] = d3 - d4;
        }
        Complex[] complexArrTransform = new FastFourierTransformer(DftNormalization.STANDARD).transform(dArr3, TransformType.FORWARD);
        dArr2[0] = 0.0d;
        dArr2[1] = complexArrTransform[0].getReal() * 0.5d;
        for (int i4 = 1; i4 < i; i4++) {
            int i5 = i4 * 2;
            dArr2[i5] = -complexArrTransform[i4].getImaginary();
            dArr2[i5 + 1] = complexArrTransform[i4].getReal() + dArr2[i5 - 1];
        }
        return dArr2;
    }
}
