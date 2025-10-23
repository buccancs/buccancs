package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes5.dex */
public abstract class BaseAbstractUnivariateIntegrator implements UnivariateIntegrator {
    public static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-15d;
    public static final int DEFAULT_MAX_ITERATIONS_COUNT = Integer.MAX_VALUE;
    public static final int DEFAULT_MIN_ITERATIONS_COUNT = 3;
    public static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-6d;
    private final double absoluteAccuracy;
    private final int minimalIterationCount;
    private final double relativeAccuracy;
    @Deprecated
    protected Incrementor iterations;
    private IntegerSequence.Incrementor count;
    private IntegerSequence.Incrementor evaluations;
    private UnivariateFunction function;
    private double max;
    private double min;

    protected BaseAbstractUnivariateIntegrator(double d, double d2, int i, int i2) throws NumberIsTooSmallException {
        this.relativeAccuracy = d;
        this.absoluteAccuracy = d2;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        if (i2 <= i) {
            throw new NumberIsTooSmallException(Integer.valueOf(i2), Integer.valueOf(i), false);
        }
        this.minimalIterationCount = i;
        IntegerSequence.Incrementor incrementorWithMaximalCount = IntegerSequence.Incrementor.create().withMaximalCount(i2);
        this.count = incrementorWithMaximalCount;
        this.iterations = Incrementor.wrap(incrementorWithMaximalCount);
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    protected BaseAbstractUnivariateIntegrator(double d, double d2) {
        this(d, d2, 3, Integer.MAX_VALUE);
    }

    protected BaseAbstractUnivariateIntegrator(int i, int i2) throws NumberIsTooSmallException {
        this(1.0E-6d, 1.0E-15d, i, i2);
    }

    protected abstract double doIntegrate() throws MaxCountExceededException;

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public double getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    protected double getMax() {
        return this.max;
    }

    protected double getMin() {
        return this.min;
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public int getMinimalIterationCount() {
        return this.minimalIterationCount;
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public double getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public int getMaximalIterationCount() {
        return this.count.getMaximalCount();
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public int getIterations() {
        return this.count.getCount();
    }

    protected void incrementCount() throws MaxCountExceededException {
        this.count.increment();
    }

    protected double computeObjectiveValue(double d) throws TooManyEvaluationsException {
        try {
            this.evaluations.increment();
            return this.function.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    protected void setup(int i, UnivariateFunction univariateFunction, double d, double d2) throws MathIllegalArgumentException {
        MathUtils.checkNotNull(univariateFunction);
        UnivariateSolverUtils.verifyInterval(d, d2);
        this.min = d;
        this.max = d2;
        this.function = univariateFunction;
        this.evaluations = this.evaluations.withMaximalCount(i).withStart(0);
        this.count = this.count.withStart(0);
    }

    @Override // org.apache.commons.math3.analysis.integration.UnivariateIntegrator
    public double integrate(int i, UnivariateFunction univariateFunction, double d, double d2) throws MaxCountExceededException, MathIllegalArgumentException {
        setup(i, univariateFunction, d, d2);
        return doIntegrate();
    }
}
