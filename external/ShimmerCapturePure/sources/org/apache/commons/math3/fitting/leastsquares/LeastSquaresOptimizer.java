package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;

/* loaded from: classes5.dex */
public interface LeastSquaresOptimizer {

    Optimum optimize(LeastSquaresProblem leastSquaresProblem);

    public interface Optimum extends LeastSquaresProblem.Evaluation {
        int getEvaluations();

        int getIterations();
    }
}
