package org.apache.commons.math3.optimization;

import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes5.dex */
public abstract class AbstractConvergenceChecker<PAIR> implements ConvergenceChecker<PAIR> {
    @Deprecated
    private static final double DEFAULT_RELATIVE_THRESHOLD = Precision.EPSILON * 100.0d;
    @Deprecated
    private static final double DEFAULT_ABSOLUTE_THRESHOLD = Precision.SAFE_MIN * 100.0d;
    private final double absoluteThreshold;
    private final double relativeThreshold;

    @Deprecated
    public AbstractConvergenceChecker() {
        this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
        this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
    }

    public AbstractConvergenceChecker(double d, double d2) {
        this.relativeThreshold = d;
        this.absoluteThreshold = d2;
    }

    @Override // org.apache.commons.math3.optimization.ConvergenceChecker
    public abstract boolean converged(int i, PAIR pair, PAIR pair2);

    public double getAbsoluteThreshold() {
        return this.absoluteThreshold;
    }

    public double getRelativeThreshold() {
        return this.relativeThreshold;
    }
}
