package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

/* loaded from: classes5.dex */
public abstract class GradientMultivariateOptimizer extends MultivariateOptimizer {
    private MultivariateVectorFunction gradient;

    protected GradientMultivariateOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    protected double[] computeObjectiveGradient(double[] dArr) {
        return this.gradient.value(dArr);
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        return super.optimize(optimizationDataArr);
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    protected void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (OptimizationData optimizationData : optimizationDataArr) {
            if (optimizationData instanceof ObjectiveFunctionGradient) {
                this.gradient = ((ObjectiveFunctionGradient) optimizationData).getObjectiveFunctionGradient();
                return;
            }
        }
    }
}
