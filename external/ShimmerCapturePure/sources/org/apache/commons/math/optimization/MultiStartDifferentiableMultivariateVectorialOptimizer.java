package org.apache.commons.math.optimization;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.random.RandomVectorGenerator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/MultiStartDifferentiableMultivariateVectorialOptimizer.class */
public class MultiStartDifferentiableMultivariateVectorialOptimizer implements DifferentiableMultivariateVectorialOptimizer {
    private static final long serialVersionUID = 9206382258980561530L;
    private final DifferentiableMultivariateVectorialOptimizer optimizer;
    private int maxIterations;
    private int maxEvaluations;
    private int starts;
    private RandomVectorGenerator generator;
    private int totalIterations = 0;
    private int totalEvaluations = 0;
    private int totalJacobianEvaluations = 0;
    private VectorialPointValuePair[] optima = null;

    public MultiStartDifferentiableMultivariateVectorialOptimizer(DifferentiableMultivariateVectorialOptimizer optimizer, int starts, RandomVectorGenerator generator) {
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
        setMaxIterations(Integer.MAX_VALUE);
        setMaxEvaluations(Integer.MAX_VALUE);
    }

    public VectorialPointValuePair[] getOptima() throws IllegalStateException {
        if (this.optima == null) {
            throw MathRuntimeException.createIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (VectorialPointValuePair[]) this.optima.clone();
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getMaxIterations() {
        return this.maxIterations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getIterations() {
        return this.totalIterations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public void setMaxEvaluations(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getJacobianEvaluations() {
        return this.totalJacobianEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public VectorialConvergenceChecker getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public void setConvergenceChecker(VectorialConvergenceChecker checker) {
        this.optimizer.setConvergenceChecker(checker);
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public VectorialPointValuePair optimize(DifferentiableMultivariateVectorialFunction f, final double[] target, final double[] weights, double[] startPoint) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException {
        this.optima = new VectorialPointValuePair[this.starts];
        this.totalIterations = 0;
        this.totalEvaluations = 0;
        this.totalJacobianEvaluations = 0;
        int i = 0;
        while (i < this.starts) {
            try {
                this.optimizer.setMaxIterations(this.maxIterations - this.totalIterations);
                this.optimizer.setMaxEvaluations(this.maxEvaluations - this.totalEvaluations);
                this.optima[i] = this.optimizer.optimize(f, target, weights, i == 0 ? startPoint : this.generator.nextVector());
            } catch (FunctionEvaluationException e) {
                this.optima[i] = null;
            } catch (OptimizationException e2) {
                this.optima[i] = null;
            }
            this.totalIterations += this.optimizer.getIterations();
            this.totalEvaluations += this.optimizer.getEvaluations();
            this.totalJacobianEvaluations += this.optimizer.getJacobianEvaluations();
            i++;
        }
        Arrays.sort(this.optima, new Comparator<VectorialPointValuePair>() { // from class: org.apache.commons.math.optimization.MultiStartDifferentiableMultivariateVectorialOptimizer.1
            @Override // java.util.Comparator
            public int compare(VectorialPointValuePair o1, VectorialPointValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return Double.compare(weightedResidual(o1), weightedResidual(o2));
            }

            private double weightedResidual(VectorialPointValuePair pv) {
                double[] value = pv.getValueRef();
                double sum = 0.0d;
                for (int i2 = 0; i2 < value.length; i2++) {
                    double ri = value[i2] - target[i2];
                    sum += weights[i2] * ri * ri;
                }
                return sum;
            }
        });
        if (this.optima[0] == null) {
            throw new OptimizationException(LocalizedFormats.NO_CONVERGENCE_WITH_ANY_START_POINT, Integer.valueOf(this.starts));
        }
        return this.optima[0];
    }
}
