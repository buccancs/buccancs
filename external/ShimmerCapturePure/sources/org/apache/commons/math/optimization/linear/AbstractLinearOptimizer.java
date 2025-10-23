package org.apache.commons.math.optimization.linear;

import java.util.Collection;

import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/linear/AbstractLinearOptimizer.class */
public abstract class AbstractLinearOptimizer implements LinearOptimizer {
    public static final int DEFAULT_MAX_ITERATIONS = 100;
    protected LinearObjectiveFunction function;
    protected Collection<LinearConstraint> linearConstraints;
    protected GoalType goal;
    protected boolean nonNegative;
    private int maxIterations;
    private int iterations;

    protected AbstractLinearOptimizer() {
        setMaxIterations(100);
    }

    protected abstract RealPointValuePair doOptimize() throws OptimizationException;

    @Override // org.apache.commons.math.optimization.linear.LinearOptimizer
    public int getMaxIterations() {
        return this.maxIterations;
    }

    @Override // org.apache.commons.math.optimization.linear.LinearOptimizer
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Override // org.apache.commons.math.optimization.linear.LinearOptimizer
    public int getIterations() {
        return this.iterations;
    }

    protected void incrementIterationsCounter() throws OptimizationException {
        int i = this.iterations + 1;
        this.iterations = i;
        if (i > this.maxIterations) {
            throw new OptimizationException(new MaxIterationsExceededException(this.maxIterations));
        }
    }

    @Override // org.apache.commons.math.optimization.linear.LinearOptimizer
    public RealPointValuePair optimize(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative) throws OptimizationException {
        this.function = f;
        this.linearConstraints = constraints;
        this.goal = goalType;
        this.nonNegative = restrictToNonNegative;
        this.iterations = 0;
        return doOptimize();
    }
}
