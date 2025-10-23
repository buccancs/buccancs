package org.apache.commons.math.optimization.univariate;

import org.apache.commons.math.ConvergingAlgorithmImpl;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxEvaluationsExceededException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.MathUnsupportedOperationException;
import org.apache.commons.math.exception.NoDataException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.UnivariateRealOptimizer;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/univariate/AbstractUnivariateRealOptimizer.class */
public abstract class AbstractUnivariateRealOptimizer extends ConvergingAlgorithmImpl implements UnivariateRealOptimizer {
    protected boolean resultComputed;
    protected double result;
    protected double functionValue;
    private int maxEvaluations;
    private int evaluations;
    private GoalType optimizationGoal;
    private double searchMin;
    private double searchMax;
    private double searchStart;
    private UnivariateRealFunction function;

    @Deprecated
    protected AbstractUnivariateRealOptimizer(int defaultMaximalIterationCount, double defaultAbsoluteAccuracy) {
        super(defaultMaximalIterationCount, defaultAbsoluteAccuracy);
        this.resultComputed = false;
        setMaxEvaluations(Integer.MAX_VALUE);
    }

    protected AbstractUnivariateRealOptimizer() {
    }

    @Deprecated
    protected void checkResultComputed() {
        if (!this.resultComputed) {
            throw new NoDataException();
        }
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public double getResult() {
        if (!this.resultComputed) {
            throw new NoDataException();
        }
        return this.result;
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public double getFunctionValue() throws FunctionEvaluationException {
        if (Double.isNaN(this.functionValue)) {
            double opt = getResult();
            this.functionValue = this.function.value(opt);
        }
        return this.functionValue;
    }

    protected void setFunctionValue(double functionValue) {
        this.functionValue = functionValue;
    }

    @Deprecated
    protected final void setResult(double x, double fx, int iterationCount) {
        this.result = x;
        this.functionValue = fx;
        this.iterationCount = iterationCount;
        this.resultComputed = true;
    }

    @Deprecated
    protected final void clearResult() {
        this.resultComputed = false;
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public void setMaxEvaluations(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public int getEvaluations() {
        return this.evaluations;
    }

    public GoalType getGoalType() {
        return this.optimizationGoal;
    }

    public double getMin() {
        return this.searchMin;
    }

    public double getMax() {
        return this.searchMax;
    }

    public double getStartValue() {
        return this.searchStart;
    }

    @Deprecated
    protected double computeObjectiveValue(UnivariateRealFunction f, double point) throws FunctionEvaluationException {
        int i = this.evaluations + 1;
        this.evaluations = i;
        if (i > this.maxEvaluations) {
            throw new FunctionEvaluationException(new MaxEvaluationsExceededException(this.maxEvaluations), point);
        }
        return f.value(point);
    }

    protected double computeObjectiveValue(double point) throws FunctionEvaluationException {
        int i = this.evaluations + 1;
        this.evaluations = i;
        if (i > this.maxEvaluations) {
            this.resultComputed = false;
            throw new FunctionEvaluationException(new MaxEvaluationsExceededException(this.maxEvaluations), point);
        }
        return this.function.value(point);
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public double optimize(UnivariateRealFunction f, GoalType goal, double min, double max, double startValue) throws FunctionEvaluationException, MaxIterationsExceededException {
        this.searchMin = min;
        this.searchMax = max;
        this.searchStart = startValue;
        this.optimizationGoal = goal;
        this.function = f;
        this.functionValue = Double.NaN;
        this.evaluations = 0;
        resetIterationsCounter();
        this.result = doOptimize();
        this.resultComputed = true;
        return this.result;
    }

    @Override // org.apache.commons.math.optimization.UnivariateRealOptimizer
    public double optimize(UnivariateRealFunction f, GoalType goal, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException {
        return optimize(f, goal, min, max, min + (0.5d * (max - min)));
    }

    protected double doOptimize() throws FunctionEvaluationException, MaxIterationsExceededException {
        throw new MathUnsupportedOperationException(LocalizedFormats.NOT_OVERRIDEN, new Object[0]);
    }
}
