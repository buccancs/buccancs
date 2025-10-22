package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class RiddersSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public RiddersSolver() {
        this(1.0E-6d);
    }

    public RiddersSolver(double d) {
        super(d);
    }

    public RiddersSolver(double d, double d2) {
        super(d, d2);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double dComputeObjectiveValue = computeObjectiveValue(min);
        double dComputeObjectiveValue2 = computeObjectiveValue(max);
        if (dComputeObjectiveValue == 0.0d) {
            return min;
        }
        if (dComputeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        double d = Double.POSITIVE_INFINITY;
        while (true) {
            double d2 = (min + max) * 0.5d;
            double dComputeObjectiveValue3 = computeObjectiveValue(d2);
            if (FastMath.abs(dComputeObjectiveValue3) <= functionValueAccuracy) {
                return d2;
            }
            double dSignum = ((FastMath.signum(dComputeObjectiveValue2) * FastMath.signum(dComputeObjectiveValue3)) * (d2 - min)) / FastMath.sqrt(1.0d - ((dComputeObjectiveValue * dComputeObjectiveValue2) / (dComputeObjectiveValue3 * dComputeObjectiveValue3)));
            double d3 = min;
            double d4 = d2 - dSignum;
            double dComputeObjectiveValue4 = computeObjectiveValue(d4);
            double d5 = max;
            if (FastMath.abs(d4 - d) <= FastMath.max(relativeAccuracy * FastMath.abs(d4), absoluteAccuracy) || FastMath.abs(dComputeObjectiveValue4) <= functionValueAccuracy) {
                return d4;
            }
            if (dSignum > 0.0d) {
                if (FastMath.signum(dComputeObjectiveValue) + FastMath.signum(dComputeObjectiveValue4) == 0.0d) {
                    d5 = d4;
                    d2 = d3;
                    dComputeObjectiveValue2 = dComputeObjectiveValue4;
                } else {
                    d5 = d2;
                    dComputeObjectiveValue2 = dComputeObjectiveValue3;
                    dComputeObjectiveValue = dComputeObjectiveValue4;
                    d2 = d4;
                }
            } else if (FastMath.signum(dComputeObjectiveValue2) + FastMath.signum(dComputeObjectiveValue4) == 0.0d) {
                d2 = d4;
                dComputeObjectiveValue = dComputeObjectiveValue4;
            } else {
                d5 = d4;
                dComputeObjectiveValue = dComputeObjectiveValue3;
                dComputeObjectiveValue2 = dComputeObjectiveValue4;
            }
            d = d4;
            min = d2;
            max = d5;
        }
    }
}
