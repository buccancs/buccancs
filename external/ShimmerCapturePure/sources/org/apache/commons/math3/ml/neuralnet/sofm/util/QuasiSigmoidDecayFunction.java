package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.analysis.function.Logistic;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;

/* loaded from: classes5.dex */
public class QuasiSigmoidDecayFunction {
    private final double scale;
    private final Logistic sigmoid;

    public QuasiSigmoidDecayFunction(double d, double d2, long j) {
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
        if (d2 >= 0.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(d2), 0, false);
        }
        if (j <= 1) {
            throw new NotStrictlyPositiveException(Long.valueOf(j));
        }
        Logistic logistic = new Logistic(d, j, (4.0d * d2) / d, 1.0d, 0.0d, 1.0d);
        this.sigmoid = logistic;
        this.scale = d / logistic.value(0.0d);
    }

    public double value(long j) {
        return this.scale * this.sigmoid.value(j);
    }
}
