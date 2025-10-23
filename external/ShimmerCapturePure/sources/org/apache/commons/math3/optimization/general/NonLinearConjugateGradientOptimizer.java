package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes5.dex */
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private final ConjugateGradientFormula updateFormula;
    private double initialStep;
    private double[] point;

    @Deprecated
    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula) {
        this(conjugateGradientFormula, new SimpleValueChecker());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(conjugateGradientFormula, convergenceChecker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(conjugateGradientFormula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner) {
        super(convergenceChecker);
        this.updateFormula = conjugateGradientFormula;
        this.solver = univariateSolver;
        this.preconditioner = preconditioner;
        this.initialStep = 1.0d;
    }

    public void setInitialStep(double d) {
        if (d <= 0.0d) {
            this.initialStep = 1.0d;
        } else {
            this.initialStep = d;
        }
    }

    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    protected PointValuePair doOptimize() {
        double[] dArr;
        double d;
        ConvergenceChecker<PointValuePair> convergenceChecker = getConvergenceChecker();
        this.point = getStartPoint();
        GoalType goalType = getGoalType();
        double[] dArr2 = this.point;
        int length = dArr2.length;
        double[] dArrComputeObjectiveGradient = computeObjectiveGradient(dArr2);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                dArrComputeObjectiveGradient[i] = -dArrComputeObjectiveGradient[i];
            }
        }
        double[] dArrPrecondition = this.preconditioner.precondition(this.point, dArrComputeObjectiveGradient);
        double[] dArr3 = (double[]) dArrPrecondition.clone();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            d2 += dArrComputeObjectiveGradient[i2] * dArr3[i2];
        }
        PointValuePair pointValuePair = null;
        int maxEvaluations = getMaxEvaluations();
        double[] dArr4 = dArrPrecondition;
        double[] dArr5 = dArr3;
        double d3 = d2;
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            PointValuePair pointValuePair2 = new PointValuePair(this.point, computeObjectiveValue(this.point));
            if (pointValuePair != null && convergenceChecker.converged(i4, pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            LineSearchFunction lineSearchFunction = new LineSearchFunction(dArr5);
            double dFindUpperBound = findUpperBound(lineSearchFunction, 0.0d, this.initialStep);
            pointValuePair = pointValuePair2;
            double[] dArr6 = dArr5;
            double dSolve = this.solver.solve(maxEvaluations, lineSearchFunction, 0.0d, dFindUpperBound, 1.0E-15d);
            maxEvaluations -= this.solver.getEvaluations();
            int i5 = 0;
            while (true) {
                dArr = this.point;
                if (i5 >= dArr.length) {
                    break;
                }
                dArr[i5] = dArr[i5] + (dArr6[i5] * dSolve);
                i5++;
            }
            double[] dArrComputeObjectiveGradient2 = computeObjectiveGradient(dArr);
            if (goalType == GoalType.MINIMIZE) {
                for (int i6 = 0; i6 < length; i6++) {
                    dArrComputeObjectiveGradient2[i6] = -dArrComputeObjectiveGradient2[i6];
                }
            }
            double[] dArrPrecondition2 = this.preconditioner.precondition(this.point, dArrComputeObjectiveGradient2);
            double d4 = 0.0d;
            for (int i7 = 0; i7 < length; i7++) {
                d4 += dArrComputeObjectiveGradient2[i7] * dArrPrecondition2[i7];
            }
            if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                d = d4 / d3;
            } else {
                double d5 = 0.0d;
                for (int i8 = 0; i8 < dArrComputeObjectiveGradient2.length; i8++) {
                    d5 += dArrComputeObjectiveGradient2[i8] * dArr4[i8];
                }
                d = (d4 - d5) / d3;
            }
            if (i4 % length == 0 || d < 0.0d) {
                dArr5 = (double[]) dArrPrecondition2.clone();
            } else {
                for (int i9 = 0; i9 < length; i9++) {
                    dArr6[i9] = dArrPrecondition2[i9] + (dArr6[i9] * d);
                }
                dArr5 = dArr6;
            }
            dArr4 = dArrPrecondition2;
            i3 = i4;
            d3 = d4;
        }
    }

    private double findUpperBound(UnivariateFunction univariateFunction, double d, double d2) {
        double dValue = univariateFunction.value(d);
        double dMax = d2;
        while (dMax < Double.MAX_VALUE) {
            double d3 = d + dMax;
            double dValue2 = univariateFunction.value(d3);
            if (dValue * dValue2 <= 0.0d) {
                return d3;
            }
            dMax *= FastMath.max(2.0d, dValue / dValue2);
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
    }

    public static class IdentityPreconditioner implements Preconditioner {
        @Override // org.apache.commons.math3.optimization.general.Preconditioner
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }

    private class LineSearchFunction implements UnivariateFunction {
        private final double[] searchDirection;

        LineSearchFunction(double[] dArr) {
            this.searchDirection = dArr;
        }

        @Override // org.apache.commons.math3.analysis.UnivariateFunction
        public double value(double d) {
            double[] dArr = (double[]) NonLinearConjugateGradientOptimizer.this.point.clone();
            for (int i = 0; i < dArr.length; i++) {
                dArr[i] = dArr[i] + (this.searchDirection[i] * d);
            }
            double[] dArrComputeObjectiveGradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(dArr);
            double d2 = 0.0d;
            for (int i2 = 0; i2 < dArrComputeObjectiveGradient.length; i2++) {
                d2 += dArrComputeObjectiveGradient[i2] * this.searchDirection[i2];
            }
            return d2;
        }
    }
}
