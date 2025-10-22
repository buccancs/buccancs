package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes5.dex */
public class GaussNewtonOptimizer implements LeastSquaresOptimizer {
    private static final double SINGULARITY_THRESHOLD = 1.0E-11d;
    private final Decomposition decomposition;

    public GaussNewtonOptimizer() {
        this(Decomposition.QR);
    }

    public GaussNewtonOptimizer(Decomposition decomposition) {
        this.decomposition = decomposition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Pair<RealMatrix, RealVector> computeNormalMatrix(RealMatrix realMatrix, RealVector realVector) throws OutOfRangeException {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        RealMatrix realMatrixCreateRealMatrix = MatrixUtils.createRealMatrix(columnDimension, columnDimension);
        ArrayRealVector arrayRealVector = new ArrayRealVector(columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                arrayRealVector.setEntry(i2, arrayRealVector.getEntry(i2) + (realVector.getEntry(i) * realMatrix.getEntry(i, i2)));
            }
            for (int i3 = 0; i3 < columnDimension; i3++) {
                for (int i4 = i3; i4 < columnDimension; i4++) {
                    realMatrixCreateRealMatrix.setEntry(i3, i4, realMatrixCreateRealMatrix.getEntry(i3, i4) + (realMatrix.getEntry(i, i3) * realMatrix.getEntry(i, i4)));
                }
            }
        }
        for (int i5 = 0; i5 < columnDimension; i5++) {
            for (int i6 = 0; i6 < i5; i6++) {
                realMatrixCreateRealMatrix.setEntry(i5, i6, realMatrixCreateRealMatrix.getEntry(i6, i5));
            }
        }
        return new Pair<>(realMatrixCreateRealMatrix, arrayRealVector);
    }

    public Decomposition getDecomposition() {
        return this.decomposition;
    }

    public GaussNewtonOptimizer withDecomposition(Decomposition decomposition) {
        return new GaussNewtonOptimizer(decomposition);
    }

    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer
    public LeastSquaresOptimizer.Optimum optimize(LeastSquaresProblem leastSquaresProblem) throws OutOfRangeException, MaxCountExceededException, DimensionMismatchException {
        Incrementor evaluationCounter = leastSquaresProblem.getEvaluationCounter();
        Incrementor iterationCounter = leastSquaresProblem.getIterationCounter();
        ConvergenceChecker<LeastSquaresProblem.Evaluation> convergenceChecker = leastSquaresProblem.getConvergenceChecker();
        if (convergenceChecker == null) {
            throw new NullArgumentException();
        }
        RealVector start = leastSquaresProblem.getStart();
        LeastSquaresProblem.Evaluation evaluation = null;
        while (true) {
            iterationCounter.incrementCount();
            evaluationCounter.incrementCount();
            LeastSquaresProblem.Evaluation evaluationEvaluate = leastSquaresProblem.evaluate(start);
            RealVector residuals = evaluationEvaluate.getResiduals();
            RealMatrix jacobian = evaluationEvaluate.getJacobian();
            RealVector point = evaluationEvaluate.getPoint();
            if (evaluation != null && convergenceChecker.converged(iterationCounter.getCount(), evaluation, evaluationEvaluate)) {
                return new OptimumImpl(evaluationEvaluate, evaluationCounter.getCount(), iterationCounter.getCount());
            }
            evaluation = evaluationEvaluate;
            start = point.add(this.decomposition.solve(jacobian, residuals));
        }
    }

    public String toString() {
        return "GaussNewtonOptimizer{decomposition=" + this.decomposition + '}';
    }

    public enum Decomposition {
        LU { // from class: org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition.1

            @Override // org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition
            protected RealVector solve(RealMatrix realMatrix, RealVector realVector) throws OutOfRangeException {
                try {
                    Pair pairComputeNormalMatrix = GaussNewtonOptimizer.computeNormalMatrix(realMatrix, realVector);
                    return new LUDecomposition((RealMatrix) pairComputeNormalMatrix.getFirst(), 1.0E-11d).getSolver().solve((RealVector) pairComputeNormalMatrix.getSecond());
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        QR { // from class: org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition.2

            @Override // org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition
            protected RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                try {
                    return new QRDecomposition(realMatrix, 1.0E-11d).getSolver().solve(realVector);
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        CHOLESKY { // from class: org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition.3

            @Override // org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition
            protected RealVector solve(RealMatrix realMatrix, RealVector realVector) throws OutOfRangeException {
                try {
                    Pair pairComputeNormalMatrix = GaussNewtonOptimizer.computeNormalMatrix(realMatrix, realVector);
                    return new CholeskyDecomposition((RealMatrix) pairComputeNormalMatrix.getFirst(), 1.0E-11d, 1.0E-11d).getSolver().solve((RealVector) pairComputeNormalMatrix.getSecond());
                } catch (NonPositiveDefiniteMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        SVD { // from class: org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition.4

            @Override // org.apache.commons.math3.fitting.leastsquares.GaussNewtonOptimizer.Decomposition
            protected RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                return new SingularValueDecomposition(realMatrix).getSolver().solve(realVector);
            }
        };

        protected abstract RealVector solve(RealMatrix realMatrix, RealVector realVector);
    }
}
