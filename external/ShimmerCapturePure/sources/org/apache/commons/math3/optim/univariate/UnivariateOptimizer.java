package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

/* loaded from: classes5.dex */
public abstract class UnivariateOptimizer extends BaseOptimizer<UnivariatePointValuePair> {
    private UnivariateFunction function;
    private GoalType goal;
    private double max;
    private double min;
    private double start;

    protected UnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double getMax() {
        return this.max;
    }

    public double getMin() {
        return this.min;
    }

    public double getStartValue() {
        return this.start;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public UnivariatePointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        return (UnivariatePointValuePair) super.optimize(optimizationDataArr);
    }

    @Override // org.apache.commons.math3.optim.BaseOptimizer
    protected void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (OptimizationData optimizationData : optimizationDataArr) {
            if (optimizationData instanceof SearchInterval) {
                SearchInterval searchInterval = (SearchInterval) optimizationData;
                this.min = searchInterval.getMin();
                this.max = searchInterval.getMax();
                this.start = searchInterval.getStartValue();
            } else if (optimizationData instanceof UnivariateObjectiveFunction) {
                this.function = ((UnivariateObjectiveFunction) optimizationData).getObjectiveFunction();
            } else if (optimizationData instanceof GoalType) {
                this.goal = (GoalType) optimizationData;
            }
        }
    }

    protected double computeObjectiveValue(double d) throws MaxCountExceededException {
        super.incrementEvaluationCount();
        return this.function.value(d);
    }
}
