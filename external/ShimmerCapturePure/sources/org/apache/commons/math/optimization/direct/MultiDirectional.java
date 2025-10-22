package org.apache.commons.math.optimization.direct;

import java.util.Comparator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealConvergenceChecker;
import org.apache.commons.math.optimization.RealPointValuePair;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/direct/MultiDirectional.class */
public class MultiDirectional extends DirectSearchOptimizer {
    private final double khi;
    private final double gamma;

    public MultiDirectional() {
        this.khi = 2.0d;
        this.gamma = 0.5d;
    }

    public MultiDirectional(double khi, double gamma) {
        this.khi = khi;
        this.gamma = gamma;
    }

    @Override // org.apache.commons.math.optimization.direct.DirectSearchOptimizer
    protected void iterateSimplex(Comparator<RealPointValuePair> comparator) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException {
        boolean converged;
        RealConvergenceChecker checker = getConvergenceChecker();
        do {
            incrementIterationsCounter();
            RealPointValuePair[] original = this.simplex;
            RealPointValuePair best = original[0];
            RealPointValuePair reflected = evaluateNewSimplex(original, 1.0d, comparator);
            if (comparator.compare(reflected, best) < 0) {
                RealPointValuePair[] reflectedSimplex = this.simplex;
                RealPointValuePair expanded = evaluateNewSimplex(original, this.khi, comparator);
                if (comparator.compare(reflected, expanded) <= 0) {
                    this.simplex = reflectedSimplex;
                    return;
                }
                return;
            }
            RealPointValuePair contracted = evaluateNewSimplex(original, this.gamma, comparator);
            if (comparator.compare(contracted, best) < 0) {
                return;
            }
            int iter = getIterations();
            converged = true;
            for (int i = 0; i < this.simplex.length; i++) {
                converged &= checker.converged(iter, original[i], this.simplex[i]);
            }
        } while (!converged);
    }

    private RealPointValuePair evaluateNewSimplex(RealPointValuePair[] original, double coeff, Comparator<RealPointValuePair> comparator) throws FunctionEvaluationException, OptimizationException {
        double[] xSmallest = original[0].getPointRef();
        int n = xSmallest.length;
        this.simplex = new RealPointValuePair[n + 1];
        this.simplex[0] = original[0];
        for (int i = 1; i <= n; i++) {
            double[] xOriginal = original[i].getPointRef();
            double[] xTransformed = new double[n];
            for (int j = 0; j < n; j++) {
                xTransformed[j] = xSmallest[j] + (coeff * (xSmallest[j] - xOriginal[j]));
            }
            this.simplex[i] = new RealPointValuePair(xTransformed, Double.NaN, false);
        }
        evaluateSimplex(comparator);
        return this.simplex[0];
    }
}
