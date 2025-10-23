package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunctionJacobian;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;

@Deprecated
/* loaded from: classes5.dex */
public class CurveFitter<T extends ParametricUnivariateFunction> {
    private final List<WeightedObservedPoint> observations = new ArrayList();
    private final MultivariateVectorOptimizer optimizer;

    public CurveFitter(MultivariateVectorOptimizer multivariateVectorOptimizer) {
        this.optimizer = multivariateVectorOptimizer;
    }

    public void addObservedPoint(double d, double d2) {
        addObservedPoint(1.0d, d, d2);
    }

    public void addObservedPoint(double d, double d2, double d3) {
        this.observations.add(new WeightedObservedPoint(d, d2, d3));
    }

    public void addObservedPoint(WeightedObservedPoint weightedObservedPoint) {
        this.observations.add(weightedObservedPoint);
    }

    public WeightedObservedPoint[] getObservations() {
        List<WeightedObservedPoint> list = this.observations;
        return (WeightedObservedPoint[]) list.toArray(new WeightedObservedPoint[list.size()]);
    }

    public void clearObservations() {
        this.observations.clear();
    }

    public double[] fit(T t, double[] dArr) {
        return fit(Integer.MAX_VALUE, t, dArr);
    }

    public double[] fit(int i, T t, double[] dArr) {
        double[] dArr2 = new double[this.observations.size()];
        double[] dArr3 = new double[this.observations.size()];
        int i2 = 0;
        for (WeightedObservedPoint weightedObservedPoint : this.observations) {
            dArr2[i2] = weightedObservedPoint.getY();
            dArr3[i2] = weightedObservedPoint.getWeight();
            i2++;
        }
        TheoreticalValuesFunction theoreticalValuesFunction = new TheoreticalValuesFunction(t);
        return this.optimizer.optimize(new MaxEval(i), theoreticalValuesFunction.getModelFunction(), theoreticalValuesFunction.getModelFunctionJacobian(), new Target(dArr2), new Weight(dArr3), new InitialGuess(dArr)).getPointRef();
    }

    private class TheoreticalValuesFunction {
        private final ParametricUnivariateFunction f;

        TheoreticalValuesFunction(ParametricUnivariateFunction parametricUnivariateFunction) {
            this.f = parametricUnivariateFunction;
        }

        public ModelFunction getModelFunction() {
            return new ModelFunction(new MultivariateVectorFunction() { // from class: org.apache.commons.math3.fitting.CurveFitter.TheoreticalValuesFunction.1
                @Override // org.apache.commons.math3.analysis.MultivariateVectorFunction
                public double[] value(double[] dArr) {
                    double[] dArr2 = new double[CurveFitter.this.observations.size()];
                    Iterator it2 = CurveFitter.this.observations.iterator();
                    int i = 0;
                    while (it2.hasNext()) {
                        dArr2[i] = TheoreticalValuesFunction.this.f.value(((WeightedObservedPoint) it2.next()).getX(), dArr);
                        i++;
                    }
                    return dArr2;
                }
            });
        }

        public ModelFunctionJacobian getModelFunctionJacobian() {
            return new ModelFunctionJacobian(new MultivariateMatrixFunction() { // from class: org.apache.commons.math3.fitting.CurveFitter.TheoreticalValuesFunction.2
                @Override // org.apache.commons.math3.analysis.MultivariateMatrixFunction
                public double[][] value(double[] dArr) {
                    double[][] dArr2 = new double[CurveFitter.this.observations.size()][];
                    Iterator it2 = CurveFitter.this.observations.iterator();
                    int i = 0;
                    while (it2.hasNext()) {
                        dArr2[i] = TheoreticalValuesFunction.this.f.gradient(((WeightedObservedPoint) it2.next()).getX(), dArr);
                        i++;
                    }
                    return dArr2;
                }
            });
        }
    }
}
