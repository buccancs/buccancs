package org.apache.commons.math3.optimization;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes5.dex */
public class SimpleValueChecker extends AbstractConvergenceChecker<PointValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    @Deprecated
    public SimpleValueChecker() {
        this.maxIterationCount = -1;
    }

    public SimpleValueChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimpleValueChecker(double d, double d2, int i) {
        super(d, d2);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.maxIterationCount = i;
    }

    @Override
    // org.apache.commons.math3.optimization.AbstractConvergenceChecker, org.apache.commons.math3.optimization.ConvergenceChecker
    public boolean converged(int i, PointValuePair pointValuePair, PointValuePair pointValuePair2) {
        int i2 = this.maxIterationCount;
        if (i2 != -1 && i >= i2) {
            return true;
        }
        double dDoubleValue = pointValuePair.getValue().doubleValue();
        double dDoubleValue2 = pointValuePair2.getValue().doubleValue();
        double dAbs = FastMath.abs(dDoubleValue - dDoubleValue2);
        return dAbs <= FastMath.max(FastMath.abs(dDoubleValue), FastMath.abs(dDoubleValue2)) * getRelativeThreshold() || dAbs <= getAbsoluteThreshold();
    }
}
