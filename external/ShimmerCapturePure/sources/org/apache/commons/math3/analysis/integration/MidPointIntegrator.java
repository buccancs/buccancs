package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class MidPointIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

    public MidPointIntegrator(double d, double d2, int i, int i2) throws NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public MidPointIntegrator(int i, int i2) throws NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public MidPointIntegrator() {
        super(3, 64);
    }

    private double stage(int i, double d, double d2, double d3) throws TooManyEvaluationsException {
        long j = 1 << (i - 1);
        double d4 = d3 / j;
        double d5 = d2 + (d4 * 0.5d);
        double dComputeObjectiveValue = 0.0d;
        for (long j2 = 0; j2 < j; j2++) {
            dComputeObjectiveValue += computeObjectiveValue(d5);
            d5 += d4;
        }
        return (d + (dComputeObjectiveValue * d4)) * 0.5d;
    }

    @Override // org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator
    protected double doIntegrate() throws MaxCountExceededException, MathIllegalArgumentException {
        double dStage;
        long j;
        double min = getMin();
        double max = getMax() - min;
        double dComputeObjectiveValue = computeObjectiveValue((max * 0.5d) + min) * max;
        while (true) {
            incrementCount();
            int iterations = getIterations();
            dStage = stage(iterations, dComputeObjectiveValue, min, max);
            if (iterations >= getMinimalIterationCount()) {
                double dAbs = FastMath.abs(dStage - dComputeObjectiveValue);
                j = 4602678819172646912L;
                if (dAbs <= getRelativeAccuracy() * (FastMath.abs(dComputeObjectiveValue) + FastMath.abs(dStage)) * 0.5d || dAbs <= getAbsoluteAccuracy()) {
                    break;
                }
            } else {
                j = 4602678819172646912L;
            }
            dComputeObjectiveValue = dStage;
        }
        return dStage;
    }
}
