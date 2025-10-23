package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.BaseMultivariateSimpleBoundsOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleBounds;

@Deprecated
/* loaded from: classes5.dex */
public abstract class BaseAbstractMultivariateSimpleBoundsOptimizer<FUNC extends MultivariateFunction> extends BaseAbstractMultivariateOptimizer<FUNC> implements BaseMultivariateOptimizer<FUNC>, BaseMultivariateSimpleBoundsOptimizer<FUNC> {
    @Deprecated
    protected BaseAbstractMultivariateSimpleBoundsOptimizer() {
    }

    protected BaseAbstractMultivariateSimpleBoundsOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    @Override
    // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer, org.apache.commons.math3.optimization.BaseMultivariateOptimizer
    public PointValuePair optimize(int i, FUNC func, GoalType goalType, double[] dArr) {
        return super.optimizeInternal(i, (int) func, goalType, new InitialGuess(dArr));
    }

    @Override // org.apache.commons.math3.optimization.BaseMultivariateSimpleBoundsOptimizer
    public PointValuePair optimize(int i, FUNC func, GoalType goalType, double[] dArr, double[] dArr2, double[] dArr3) {
        return super.optimizeInternal(i, (int) func, goalType, new InitialGuess(dArr), new SimpleBounds(dArr2, dArr3));
    }
}
