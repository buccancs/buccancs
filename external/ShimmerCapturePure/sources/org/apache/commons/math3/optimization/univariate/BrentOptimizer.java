package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes5.dex */
public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {
    private static final double GOLDEN_SECTION = (3.0d - FastMath.sqrt(5.0d)) * 0.5d;
    private static final double MIN_RELATIVE_TOLERANCE = FastMath.ulp(1.0d) * 2.0d;
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double d, double d2, ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d3 = MIN_RELATIVE_TOLERANCE;
        if (d < d3) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d3), true);
        }
        if (d2 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
        this.relativeThreshold = d;
        this.absoluteThreshold = d2;
    }

    public BrentOptimizer(double d, double d2) {
        this(d, d2, null);
    }

    @Override // org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer
    protected UnivariatePointValuePair doOptimize() {
        double d;
        boolean z;
        double d2;
        double d3;
        double d4;
        BrentOptimizer brentOptimizer = this;
        int i = 0;
        boolean z2 = getGoalType() == GoalType.MINIMIZE;
        double min = getMin();
        double startValue = getStartValue();
        double max = getMax();
        ConvergenceChecker<UnivariatePointValuePair> convergenceChecker = getConvergenceChecker();
        if (min >= max) {
            min = max;
            max = min;
        }
        double dComputeObjectiveValue = brentOptimizer.computeObjectiveValue(startValue);
        if (!z2) {
            dComputeObjectiveValue = -dComputeObjectiveValue;
        }
        UnivariatePointValuePair univariatePointValuePair = new UnivariatePointValuePair(startValue, z2 ? dComputeObjectiveValue : -dComputeObjectiveValue);
        double d5 = dComputeObjectiveValue;
        double d6 = d5;
        double d7 = d6;
        UnivariatePointValuePair univariatePointValuePair2 = univariatePointValuePair;
        UnivariatePointValuePair univariatePointValuePair3 = null;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double d10 = startValue;
        double d11 = max;
        double d12 = d10;
        while (true) {
            double d13 = (min + d11) * 0.5d;
            ConvergenceChecker<UnivariatePointValuePair> convergenceChecker2 = convergenceChecker;
            int i2 = i;
            double d14 = d10;
            double dAbs = (brentOptimizer.relativeThreshold * FastMath.abs(startValue)) + brentOptimizer.absoluteThreshold;
            double d15 = dAbs * 2.0d;
            if (FastMath.abs(startValue - d13) > d15 - ((d11 - min) * 0.5d)) {
                if (FastMath.abs(d8) > dAbs) {
                    double d16 = startValue - d12;
                    double d17 = (d5 - d6) * d16;
                    double d18 = startValue - d14;
                    double d19 = (d5 - d7) * d18;
                    d = d12;
                    double d20 = (d18 * d19) - (d16 * d17);
                    double d21 = 2.0d * (d19 - d17);
                    if (d21 > 0.0d) {
                        d20 = -d20;
                    } else {
                        d21 = -d21;
                    }
                    double d22 = min - startValue;
                    if (d20 <= d21 * d22 || d20 >= (d11 - startValue) * d21 || FastMath.abs(d20) >= FastMath.abs(0.5d * d21 * d8)) {
                        if (startValue < d13) {
                            d22 = d11 - startValue;
                        }
                        d9 = GOLDEN_SECTION * d22;
                        d8 = d22;
                    } else {
                        double d23 = d20 / d21;
                        double d24 = startValue + d23;
                        if (d24 - min >= d15 && d11 - d24 >= d15) {
                            d8 = d9;
                            d9 = d23;
                        } else if (startValue <= d13) {
                            d8 = d9;
                            d9 = dAbs;
                        } else {
                            d23 = -dAbs;
                            d8 = d9;
                            d9 = d23;
                        }
                    }
                } else {
                    d = d12;
                    double d25 = startValue < d13 ? d11 - startValue : min - startValue;
                    d8 = d25;
                    d9 = GOLDEN_SECTION * d25;
                }
                double d26 = FastMath.abs(d9) < dAbs ? d9 >= 0.0d ? dAbs + startValue : startValue - dAbs : startValue + d9;
                double dComputeObjectiveValue2 = brentOptimizer.computeObjectiveValue(d26);
                if (!z2) {
                    dComputeObjectiveValue2 = -dComputeObjectiveValue2;
                }
                UnivariatePointValuePair univariatePointValuePair4 = new UnivariatePointValuePair(d26, z2 ? dComputeObjectiveValue2 : -dComputeObjectiveValue2);
                univariatePointValuePair = brentOptimizer.best(univariatePointValuePair, brentOptimizer.best(univariatePointValuePair2, univariatePointValuePair4, z2), z2);
                if (convergenceChecker2 != null && convergenceChecker2.converged(i2, univariatePointValuePair2, univariatePointValuePair4)) {
                    return univariatePointValuePair;
                }
                if (dComputeObjectiveValue2 <= d5) {
                    if (d26 < startValue) {
                        d11 = startValue;
                    } else {
                        min = startValue;
                    }
                    z = z2;
                    d3 = startValue;
                    d6 = d7;
                    startValue = d26;
                    d7 = d5;
                    d5 = dComputeObjectiveValue2;
                    d10 = d;
                } else {
                    if (d26 < startValue) {
                        min = d26;
                    } else {
                        d11 = d26;
                    }
                    if (dComputeObjectiveValue2 > d7) {
                        z = z2;
                        d3 = d;
                        if (Precision.equals(d3, startValue)) {
                            d2 = d26;
                        } else {
                            if (dComputeObjectiveValue2 > d6) {
                                d4 = d26;
                                if (!Precision.equals(d14, startValue) && !Precision.equals(d14, d3)) {
                                    d10 = d14;
                                }
                            } else {
                                d4 = d26;
                            }
                            d6 = dComputeObjectiveValue2;
                            d10 = d4;
                        }
                    } else {
                        z = z2;
                        d2 = d26;
                        d3 = d;
                    }
                    d6 = d7;
                    d7 = dComputeObjectiveValue2;
                    d10 = d3;
                    d3 = d2;
                }
                i = i2 + 1;
                convergenceChecker = convergenceChecker2;
                d12 = d3;
                z2 = z;
                brentOptimizer = this;
                univariatePointValuePair3 = univariatePointValuePair2;
                univariatePointValuePair2 = univariatePointValuePair4;
            } else {
                return brentOptimizer.best(univariatePointValuePair, brentOptimizer.best(univariatePointValuePair3, univariatePointValuePair2, z2), z2);
            }
        }
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2, boolean z) {
        return univariatePointValuePair == null ? univariatePointValuePair2 : univariatePointValuePair2 == null ? univariatePointValuePair : z ? univariatePointValuePair.getValue() <= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2 : univariatePointValuePair.getValue() >= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2;
    }
}
