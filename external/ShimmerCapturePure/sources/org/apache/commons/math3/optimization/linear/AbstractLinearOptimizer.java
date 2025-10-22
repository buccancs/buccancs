package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
/* loaded from: classes5.dex */
public abstract class AbstractLinearOptimizer implements LinearOptimizer {
    public static final int DEFAULT_MAX_ITERATIONS = 100;
    private LinearObjectiveFunction function;
    private GoalType goal;
    private int iterations;
    private Collection<LinearConstraint> linearConstraints;
    private int maxIterations;
    private boolean nonNegative;

    protected AbstractLinearOptimizer() {
        setMaxIterations(100);
    }

    protected abstract PointValuePair doOptimize() throws MathIllegalStateException;

    protected LinearObjectiveFunction getFunction() {
        return this.function;
    }

    protected GoalType getGoalType() {
        return this.goal;
    }

    @Override // org.apache.commons.math3.optimization.linear.LinearOptimizer
    public int getIterations() {
        return this.iterations;
    }

    @Override // org.apache.commons.math3.optimization.linear.LinearOptimizer
    public int getMaxIterations() {
        return this.maxIterations;
    }

    @Override // org.apache.commons.math3.optimization.linear.LinearOptimizer
    public void setMaxIterations(int i) {
        this.maxIterations = i;
    }

    protected boolean restrictToNonNegative() {
        return this.nonNegative;
    }

    protected Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    protected void incrementIterationsCounter() throws MaxCountExceededException {
        int i = this.iterations + 1;
        this.iterations = i;
        if (i > this.maxIterations) {
            throw new MaxCountExceededException(Integer.valueOf(this.maxIterations));
        }
    }

    @Override // org.apache.commons.math3.optimization.linear.LinearOptimizer
    public PointValuePair optimize(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z) throws MathIllegalStateException {
        this.function = linearObjectiveFunction;
        this.linearConstraints = collection;
        this.goal = goalType;
        this.nonNegative = z;
        this.iterations = 0;
        return doOptimize();
    }
}
