package org.apache.commons.math.optimization.fitting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction;
import org.apache.commons.math.analysis.MultivariateMatrixFunction;
import org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.VectorialPointValuePair;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/CurveFitter.class */
public class CurveFitter {
    private final DifferentiableMultivariateVectorialOptimizer optimizer;
    private final List<WeightedObservedPoint> observations = new ArrayList();

    public CurveFitter(DifferentiableMultivariateVectorialOptimizer optimizer) {
        this.optimizer = optimizer;
    }

    public void addObservedPoint(double x, double y) {
        addObservedPoint(1.0d, x, y);
    }

    public void addObservedPoint(double weight, double x, double y) {
        this.observations.add(new WeightedObservedPoint(weight, x, y));
    }

    public void addObservedPoint(WeightedObservedPoint observed) {
        this.observations.add(observed);
    }

    public WeightedObservedPoint[] getObservations() {
        return (WeightedObservedPoint[]) this.observations.toArray(new WeightedObservedPoint[this.observations.size()]);
    }

    public void clearObservations() {
        this.observations.clear();
    }

    public double[] fit(ParametricRealFunction f, double[] initialGuess) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException {
        double[] target = new double[this.observations.size()];
        double[] weights = new double[this.observations.size()];
        int i = 0;
        for (WeightedObservedPoint point : this.observations) {
            target[i] = point.getY();
            weights[i] = point.getWeight();
            i++;
        }
        VectorialPointValuePair optimum = this.optimizer.optimize(new TheoreticalValuesFunction(f), target, weights, initialGuess);
        return optimum.getPointRef();
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/CurveFitter$TheoreticalValuesFunction.class */
    private class TheoreticalValuesFunction implements DifferentiableMultivariateVectorialFunction {
        private final ParametricRealFunction f;

        public TheoreticalValuesFunction(ParametricRealFunction f) {
            this.f = f;
        }

        @Override // org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction
        public MultivariateMatrixFunction jacobian() {
            return new MultivariateMatrixFunction() { // from class: org.apache.commons.math.optimization.fitting.CurveFitter.TheoreticalValuesFunction.1
                /* JADX WARN: Type inference failed for: r0v5, types: [double[], double[][]] */
                @Override // org.apache.commons.math.analysis.MultivariateMatrixFunction
                public double[][] value(double[] point) throws FunctionEvaluationException, IllegalArgumentException {
                    ??r0 = new double[CurveFitter.this.observations.size()];
                    int i = 0;
                    for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                        int i2 = i;
                        i++;
                        r0[i2] = TheoreticalValuesFunction.this.f.gradient(observed.getX(), point);
                    }
                    return r0;
                }
            };
        }

        @Override // org.apache.commons.math.analysis.MultivariateVectorialFunction
        public double[] value(double[] point) throws FunctionEvaluationException, IllegalArgumentException {
            double[] values = new double[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                int i2 = i;
                i++;
                values[i2] = this.f.value(observed.getX(), point);
            }
            return values;
        }
    }
}
