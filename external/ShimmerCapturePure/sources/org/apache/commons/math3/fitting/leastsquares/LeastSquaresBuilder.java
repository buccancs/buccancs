package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;

/* loaded from: classes5.dex */
public class LeastSquaresBuilder {
    private ConvergenceChecker<LeastSquaresProblem.Evaluation> checker;
    private boolean lazyEvaluation;
    private int maxEvaluations;
    private int maxIterations;
    private MultivariateJacobianFunction model;
    private ParameterValidator paramValidator;
    private RealVector start;
    private RealVector target;
    private RealMatrix weight;

    public LeastSquaresBuilder checker(ConvergenceChecker<LeastSquaresProblem.Evaluation> convergenceChecker) {
        this.checker = convergenceChecker;
        return this;
    }

    public LeastSquaresBuilder lazyEvaluation(boolean z) {
        this.lazyEvaluation = z;
        return this;
    }

    public LeastSquaresBuilder maxEvaluations(int i) {
        this.maxEvaluations = i;
        return this;
    }

    public LeastSquaresBuilder maxIterations(int i) {
        this.maxIterations = i;
        return this;
    }

    public LeastSquaresBuilder model(MultivariateJacobianFunction multivariateJacobianFunction) {
        this.model = multivariateJacobianFunction;
        return this;
    }

    public LeastSquaresBuilder parameterValidator(ParameterValidator parameterValidator) {
        this.paramValidator = parameterValidator;
        return this;
    }

    public LeastSquaresBuilder start(RealVector realVector) {
        this.start = realVector;
        return this;
    }

    public LeastSquaresBuilder target(RealVector realVector) {
        this.target = realVector;
        return this;
    }

    public LeastSquaresBuilder weight(RealMatrix realMatrix) {
        this.weight = realMatrix;
        return this;
    }

    public LeastSquaresProblem build() {
        return LeastSquaresFactory.create(this.model, this.target, this.start, this.weight, this.checker, this.maxEvaluations, this.maxIterations, this.lazyEvaluation, this.paramValidator);
    }

    public LeastSquaresBuilder checkerPair(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        return checker(LeastSquaresFactory.evaluationChecker(convergenceChecker));
    }

    public LeastSquaresBuilder model(MultivariateVectorFunction multivariateVectorFunction, MultivariateMatrixFunction multivariateMatrixFunction) {
        return model(LeastSquaresFactory.model(multivariateVectorFunction, multivariateMatrixFunction));
    }

    public LeastSquaresBuilder target(double[] dArr) {
        return target(new ArrayRealVector(dArr, false));
    }

    public LeastSquaresBuilder start(double[] dArr) {
        return start(new ArrayRealVector(dArr, false));
    }
}
