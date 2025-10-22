package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/* loaded from: classes5.dex */
class OptimumImpl implements LeastSquaresOptimizer.Optimum {
    private final int evaluations;
    private final int iterations;
    private final LeastSquaresProblem.Evaluation value;

    OptimumImpl(LeastSquaresProblem.Evaluation evaluation, int i, int i2) {
        this.value = evaluation;
        this.evaluations = i;
        this.iterations = i2;
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum
    public int getEvaluations() {
        return this.evaluations;
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum
    public int getIterations() {
        return this.iterations;
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public RealMatrix getCovariances(double d) {
        return this.value.getCovariances(d);
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public RealVector getSigma(double d) {
        return this.value.getSigma(d);
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public double getRMS() {
        return this.value.getRMS();
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public RealMatrix getJacobian() {
        return this.value.getJacobian();
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public double getCost() {
        return this.value.getCost();
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public RealVector getResiduals() {
        return this.value.getResiduals();
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation
    public RealVector getPoint() {
        return this.value.getPoint();
    }
}
