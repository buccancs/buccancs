package org.apache.commons.math.estimation;

import java.util.Arrays;

import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/estimation/AbstractEstimator.class */
public abstract class AbstractEstimator implements Estimator {
    public static final int DEFAULT_MAX_COST_EVALUATIONS = 100;
    protected WeightedMeasurement[] measurements;
    protected EstimatedParameter[] parameters;
    protected double[] jacobian;
    protected int cols;
    protected int rows;
    protected double[] residuals;
    protected double cost;
    private int maxCostEval;
    private int costEvaluations;
    private int jacobianEvaluations;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEstimator() {
        setMaxCostEval(100);
    }

    @Override // org.apache.commons.math.estimation.Estimator
    public abstract void estimate(EstimationProblem estimationProblem) throws EstimationException;

    public final void setMaxCostEval(int maxCostEval) {
        this.maxCostEval = maxCostEval;
    }

    public final int getCostEvaluations() {
        return this.costEvaluations;
    }

    public final int getJacobianEvaluations() {
        return this.jacobianEvaluations;
    }

    protected void updateJacobian() {
        incrementJacobianEvaluationsCounter();
        Arrays.fill(this.jacobian, 0.0d);
        int index = 0;
        for (int i = 0; i < this.rows; i++) {
            WeightedMeasurement wm = this.measurements[i];
            double factor = -FastMath.sqrt(wm.getWeight());
            for (int j = 0; j < this.cols; j++) {
                int i2 = index;
                index++;
                this.jacobian[i2] = factor * wm.getPartial(this.parameters[j]);
            }
        }
    }

    protected final void incrementJacobianEvaluationsCounter() {
        this.jacobianEvaluations++;
    }

    protected void updateResidualsAndCost() throws EstimationException {
        int i = this.costEvaluations + 1;
        this.costEvaluations = i;
        if (i > this.maxCostEval) {
            throw new EstimationException(LocalizedFormats.MAX_EVALUATIONS_EXCEEDED, Integer.valueOf(this.maxCostEval));
        }
        this.cost = 0.0d;
        int index = 0;
        int i2 = 0;
        while (i2 < this.rows) {
            WeightedMeasurement wm = this.measurements[i2];
            double residual = wm.getResidual();
            this.residuals[i2] = FastMath.sqrt(wm.getWeight()) * residual;
            this.cost += wm.getWeight() * residual * residual;
            i2++;
            index += this.cols;
        }
        this.cost = FastMath.sqrt(this.cost);
    }

    @Override // org.apache.commons.math.estimation.Estimator
    public double getRMS(EstimationProblem problem) {
        WeightedMeasurement[] wm = problem.getMeasurements();
        double criterion = 0.0d;
        for (int i = 0; i < wm.length; i++) {
            double residual = wm[i].getResidual();
            criterion += wm[i].getWeight() * residual * residual;
        }
        return FastMath.sqrt(criterion / wm.length);
    }

    public double getChiSquare(EstimationProblem problem) {
        WeightedMeasurement[] wm = problem.getMeasurements();
        double chiSquare = 0.0d;
        for (int i = 0; i < wm.length; i++) {
            double residual = wm[i].getResidual();
            chiSquare += (residual * residual) / wm[i].getWeight();
        }
        return chiSquare;
    }

    @Override // org.apache.commons.math.estimation.Estimator
    public double[][] getCovariances(EstimationProblem problem) throws EstimationException {
        updateJacobian();
        int n = problem.getMeasurements().length;
        int m = problem.getUnboundParameters().length;
        int max = m * n;
        double[][] jTj = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                double sum = 0.0d;
                int i2 = 0;
                while (true) {
                    int k = i2;
                    if (k < max) {
                        sum += this.jacobian[k + i] * this.jacobian[k + j];
                        i2 = k + m;
                    }
                }
                jTj[i][j] = sum;
                jTj[j][i] = sum;
            }
        }
        try {
            RealMatrix inverse = new LUDecompositionImpl(MatrixUtils.createRealMatrix(jTj)).getSolver().getInverse();
            return inverse.getData();
        } catch (InvalidMatrixException e) {
            throw new EstimationException(LocalizedFormats.UNABLE_TO_COMPUTE_COVARIANCE_SINGULAR_PROBLEM, new Object[0]);
        }
    }

    @Override // org.apache.commons.math.estimation.Estimator
    public double[] guessParametersErrors(EstimationProblem problem) throws EstimationException {
        int m = problem.getMeasurements().length;
        int p = problem.getUnboundParameters().length;
        if (m <= p) {
            throw new EstimationException(LocalizedFormats.NO_DEGREES_OF_FREEDOM, Integer.valueOf(m), Integer.valueOf(p));
        }
        double[] errors = new double[problem.getUnboundParameters().length];
        double c = FastMath.sqrt(getChiSquare(problem) / (m - p));
        double[][] covar = getCovariances(problem);
        for (int i = 0; i < errors.length; i++) {
            errors[i] = FastMath.sqrt(covar[i][i]) * c;
        }
        return errors;
    }

    protected void initializeEstimate(EstimationProblem problem) {
        this.costEvaluations = 0;
        this.jacobianEvaluations = 0;
        this.measurements = problem.getMeasurements();
        this.parameters = problem.getUnboundParameters();
        this.rows = this.measurements.length;
        this.cols = this.parameters.length;
        this.jacobian = new double[this.rows * this.cols];
        this.residuals = new double[this.rows];
        this.cost = Double.POSITIVE_INFINITY;
    }
}
