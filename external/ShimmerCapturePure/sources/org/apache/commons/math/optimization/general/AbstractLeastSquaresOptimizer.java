package org.apache.commons.math.optimization.general;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxEvaluationsExceededException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.DifferentiableMultivariateVectorialFunction;
import org.apache.commons.math.analysis.MultivariateMatrixFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.SimpleVectorialValueChecker;
import org.apache.commons.math.optimization.VectorialConvergenceChecker;
import org.apache.commons.math.optimization.VectorialPointValuePair;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/general/AbstractLeastSquaresOptimizer.class */
public abstract class AbstractLeastSquaresOptimizer implements DifferentiableMultivariateVectorialOptimizer {
    public static final int DEFAULT_MAX_ITERATIONS = 100;
    protected VectorialConvergenceChecker checker;
    protected double[][] jacobian;
    protected int cols;
    protected int rows;
    protected double[] targetValues;
    protected double[] residualsWeights;
    protected double[] point;
    protected double[] objective;
    protected double[] residuals;
    protected double[][] wjacobian;
    protected double[] wresiduals;
    protected double cost;
    private int maxIterations;
    private int iterations;
    private int maxEvaluations;
    private int objectiveEvaluations;
    private int jacobianEvaluations;
    private DifferentiableMultivariateVectorialFunction function;
    private MultivariateMatrixFunction jF;

    protected AbstractLeastSquaresOptimizer() {
        setConvergenceChecker(new SimpleVectorialValueChecker());
        setMaxIterations(100);
        setMaxEvaluations(Integer.MAX_VALUE);
    }

    protected abstract VectorialPointValuePair doOptimize() throws FunctionEvaluationException, OptimizationException, IllegalArgumentException;

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
        return this.iterations;
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
        return this.objectiveEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public int getJacobianEvaluations() {
        return this.jacobianEvaluations;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public VectorialConvergenceChecker getConvergenceChecker() {
        return this.checker;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public void setConvergenceChecker(VectorialConvergenceChecker convergenceChecker) {
        this.checker = convergenceChecker;
    }

    protected void incrementIterationsCounter() throws OptimizationException {
        int i = this.iterations + 1;
        this.iterations = i;
        if (i > this.maxIterations) {
            throw new OptimizationException(new MaxIterationsExceededException(this.maxIterations));
        }
    }

    protected void updateJacobian() throws FunctionEvaluationException {
        this.jacobianEvaluations++;
        this.jacobian = this.jF.value(this.point);
        if (this.jacobian.length != this.rows) {
            throw new FunctionEvaluationException(this.point, LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(this.jacobian.length), Integer.valueOf(this.rows));
        }
        for (int i = 0; i < this.rows; i++) {
            double[] ji = this.jacobian[i];
            double wi = FastMath.sqrt(this.residualsWeights[i]);
            for (int j = 0; j < this.cols; j++) {
                int i2 = j;
                ji[i2] = ji[i2] * (-1.0d);
                this.wjacobian[i][j] = ji[j] * wi;
            }
        }
    }

    protected void updateResidualsAndCost() throws FunctionEvaluationException {
        int i = this.objectiveEvaluations + 1;
        this.objectiveEvaluations = i;
        if (i > this.maxEvaluations) {
            throw new FunctionEvaluationException(new MaxEvaluationsExceededException(this.maxEvaluations), this.point);
        }
        this.objective = this.function.value(this.point);
        if (this.objective.length != this.rows) {
            throw new FunctionEvaluationException(this.point, LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(this.objective.length), Integer.valueOf(this.rows));
        }
        this.cost = 0.0d;
        int index = 0;
        for (int i2 = 0; i2 < this.rows; i2++) {
            double residual = this.targetValues[i2] - this.objective[i2];
            this.residuals[i2] = residual;
            this.wresiduals[i2] = residual * FastMath.sqrt(this.residualsWeights[i2]);
            this.cost += this.residualsWeights[i2] * residual * residual;
            index += this.cols;
        }
        this.cost = FastMath.sqrt(this.cost);
    }

    public double getRMS() {
        return FastMath.sqrt(getChiSquare() / this.rows);
    }

    public double getChiSquare() {
        return this.cost * this.cost;
    }

    public double[][] getCovariances() throws FunctionEvaluationException, OptimizationException {
        updateJacobian();
        double[][] jTj = new double[this.cols][this.cols];
        for (int i = 0; i < this.cols; i++) {
            for (int j = i; j < this.cols; j++) {
                double sum = 0.0d;
                for (int k = 0; k < this.rows; k++) {
                    sum += this.wjacobian[k][i] * this.wjacobian[k][j];
                }
                jTj[i][j] = sum;
                jTj[j][i] = sum;
            }
        }
        try {
            RealMatrix inverse = new LUDecompositionImpl(MatrixUtils.createRealMatrix(jTj)).getSolver().getInverse();
            return inverse.getData();
        } catch (InvalidMatrixException e) {
            throw new OptimizationException(LocalizedFormats.UNABLE_TO_COMPUTE_COVARIANCE_SINGULAR_PROBLEM, new Object[0]);
        }
    }

    public double[] guessParametersErrors() throws FunctionEvaluationException, OptimizationException {
        if (this.rows <= this.cols) {
            throw new OptimizationException(LocalizedFormats.NO_DEGREES_OF_FREEDOM, Integer.valueOf(this.rows), Integer.valueOf(this.cols));
        }
        double[] errors = new double[this.cols];
        double c = FastMath.sqrt(getChiSquare() / (this.rows - this.cols));
        double[][] covar = getCovariances();
        for (int i = 0; i < errors.length; i++) {
            errors[i] = FastMath.sqrt(covar[i][i]) * c;
        }
        return errors;
    }

    @Override // org.apache.commons.math.optimization.DifferentiableMultivariateVectorialOptimizer
    public VectorialPointValuePair optimize(DifferentiableMultivariateVectorialFunction f, double[] target, double[] weights, double[] startPoint) throws FunctionEvaluationException, OptimizationException, IllegalArgumentException {
        if (target.length != weights.length) {
            throw new OptimizationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(target.length), Integer.valueOf(weights.length));
        }
        this.iterations = 0;
        this.objectiveEvaluations = 0;
        this.jacobianEvaluations = 0;
        this.function = f;
        this.jF = f.jacobian();
        this.targetValues = (double[]) target.clone();
        this.residualsWeights = (double[]) weights.clone();
        this.point = (double[]) startPoint.clone();
        this.residuals = new double[target.length];
        this.rows = target.length;
        this.cols = this.point.length;
        this.jacobian = new double[this.rows][this.cols];
        this.wjacobian = new double[this.rows][this.cols];
        this.wresiduals = new double[this.rows];
        this.cost = Double.POSITIVE_INFINITY;
        return doOptimize();
    }
}
