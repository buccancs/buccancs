package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.univariate.BracketFinder;
import org.apache.commons.math3.optim.univariate.BrentOptimizer;
import org.apache.commons.math3.optim.univariate.SearchInterval;
import org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optim.univariate.UnivariateObjectiveFunction;
import org.apache.commons.math3.optim.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;

/* loaded from: classes5.dex */
public class LineSearch {
    private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
    private static final double REL_TOL_UNUSED = 1.0E-15d;
    private final BracketFinder bracket = new BracketFinder();
    private final double initialBracketingRange;
    private final UnivariateOptimizer lineOptimizer;
    private final MultivariateOptimizer mainOptimizer;

    public LineSearch(MultivariateOptimizer multivariateOptimizer, double d, double d2, double d3) {
        this.mainOptimizer = multivariateOptimizer;
        this.lineOptimizer = new BrentOptimizer(1.0E-15d, Double.MIN_VALUE, new SimpleUnivariateValueChecker(d, d2));
        this.initialBracketingRange = d3;
    }

    public UnivariatePointValuePair search(final double[] dArr, final double[] dArr2) {
        final int length = dArr.length;
        UnivariateFunction univariateFunction = new UnivariateFunction() { // from class: org.apache.commons.math3.optim.nonlinear.scalar.LineSearch.1
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double d) {
                double[] dArr3 = new double[length];
                for (int i = 0; i < length; i++) {
                    dArr3[i] = dArr[i] + (dArr2[i] * d);
                }
                return LineSearch.this.mainOptimizer.computeObjectiveValue(dArr3);
            }
        };
        GoalType goalType = this.mainOptimizer.getGoalType();
        this.bracket.search(univariateFunction, goalType, 0.0d, this.initialBracketingRange);
        return this.lineOptimizer.optimize(new MaxEval(Integer.MAX_VALUE), new UnivariateObjectiveFunction(univariateFunction), goalType, new SearchInterval(this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid()));
    }
}
