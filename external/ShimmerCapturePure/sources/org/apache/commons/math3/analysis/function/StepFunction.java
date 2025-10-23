package org.apache.commons.math3.analysis.function;

import java.util.Arrays;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class StepFunction implements UnivariateFunction {
    private final double[] abscissa;
    private final double[] ordinate;

    public StepFunction(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, NonMonotonicSequenceException, DimensionMismatchException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        }
        if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException();
        }
        if (dArr2.length != dArr.length) {
            throw new DimensionMismatchException(dArr2.length, dArr.length);
        }
        MathArrays.checkOrder(dArr);
        this.abscissa = MathArrays.copyOf(dArr);
        this.ordinate = MathArrays.copyOf(dArr2);
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        int iBinarySearch = Arrays.binarySearch(this.abscissa, d);
        if (iBinarySearch < -1) {
            return this.ordinate[(-iBinarySearch) - 2];
        }
        if (iBinarySearch >= 0) {
            return this.ordinate[iBinarySearch];
        }
        return this.ordinate[0];
    }
}
