package org.apache.commons.math.optimization.fitting;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer;
import org.apache.commons.math.optimization.OptimizationException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/PolynomialFitter.class */
public class PolynomialFitter {
    private final CurveFitter fitter;
    private final int degree;

    public PolynomialFitter(int degree, DifferentiableMultivariateVectorialOptimizer optimizer) {
        this.fitter = new CurveFitter(optimizer);
        this.degree = degree;
    }

    public void addObservedPoint(double weight, double x, double y) {
        this.fitter.addObservedPoint(weight, x, y);
    }

    public void clearObservations() {
        this.fitter.clearObservations();
    }

    public PolynomialFunction fit() throws OptimizationException {
        try {
            return new PolynomialFunction(this.fitter.fit(new ParametricPolynomial(), new double[this.degree + 1]));
        } catch (FunctionEvaluationException fee) {
            throw new RuntimeException(fee);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/fitting/PolynomialFitter$ParametricPolynomial.class */
    private static class ParametricPolynomial implements ParametricRealFunction {
        private ParametricPolynomial() {
        }

        @Override // org.apache.commons.math.optimization.fitting.ParametricRealFunction
        public double[] gradient(double x, double[] parameters) {
            double[] gradient = new double[parameters.length];
            double xn = 1.0d;
            for (int i = 0; i < parameters.length; i++) {
                gradient[i] = xn;
                xn *= x;
            }
            return gradient;
        }

        @Override // org.apache.commons.math.optimization.fitting.ParametricRealFunction
        public double value(double x, double[] parameters) {
            double y = 0.0d;
            for (int i = parameters.length - 1; i >= 0; i--) {
                y = (y * x) + parameters[i];
            }
            return y;
        }
    }
}
