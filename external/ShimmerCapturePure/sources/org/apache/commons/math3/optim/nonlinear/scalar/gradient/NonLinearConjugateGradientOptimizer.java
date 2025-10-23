package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;

/* loaded from: classes5.dex */
public class NonLinearConjugateGradientOptimizer extends GradientMultivariateOptimizer {
    private final LineSearch line;
    private final Preconditioner preconditioner;
    private final Formula updateFormula;

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(formula, convergenceChecker, 1.0E-8d, 1.0E-8d, 1.0E-8d, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(formula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, double d, double d2, double d3) {
        this(formula, convergenceChecker, d, d2, d3, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner) {
        this(formula, convergenceChecker, univariateSolver.getRelativeAccuracy(), univariateSolver.getAbsoluteAccuracy(), univariateSolver.getAbsoluteAccuracy(), preconditioner);
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, double d, double d2, double d3, Preconditioner preconditioner) {
        super(convergenceChecker);
        this.updateFormula = formula;
        this.preconditioner = preconditioner;
        this.line = new LineSearch(this, d, d2, d3);
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer, org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        return super.optimize(optimizationDataArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair doOptimize() {
        double d;
        ConvergenceChecker<PointValuePair> convergenceChecker = getConvergenceChecker();
        double[] startPoint = getStartPoint();
        GoalType goalType = getGoalType();
        int length = startPoint.length;
        double[] dArrComputeObjectiveGradient = computeObjectiveGradient(startPoint);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                dArrComputeObjectiveGradient[i] = -dArrComputeObjectiveGradient[i];
            }
        }
        double[] dArrPrecondition = this.preconditioner.precondition(startPoint, dArrComputeObjectiveGradient);
        double[] dArr = (double[]) dArrPrecondition.clone();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            d2 += dArrComputeObjectiveGradient[i2] * dArr[i2];
        }
        PointValuePair pointValuePair = null;
        while (true) {
            incrementIterationCount();
            PointValuePair pointValuePair2 = new PointValuePair(startPoint, computeObjectiveValue(startPoint));
            if (pointValuePair != null && convergenceChecker.converged(getIterations(), pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            double point = this.line.search(startPoint, dArr).getPoint();
            for (int i3 = 0; i3 < startPoint.length; i3++) {
                startPoint[i3] = startPoint[i3] + (dArr[i3] * point);
            }
            double[] dArrComputeObjectiveGradient2 = computeObjectiveGradient(startPoint);
            if (goalType == GoalType.MINIMIZE) {
                for (int i4 = 0; i4 < length; i4++) {
                    dArrComputeObjectiveGradient2[i4] = -dArrComputeObjectiveGradient2[i4];
                }
            }
            double[] dArrPrecondition2 = this.preconditioner.precondition(startPoint, dArrComputeObjectiveGradient2);
            double d3 = 0.0d;
            for (int i5 = 0; i5 < length; i5++) {
                d3 += dArrComputeObjectiveGradient2[i5] * dArrPrecondition2[i5];
            }
            int i6 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[this.updateFormula.ordinal()];
            if (i6 == 1) {
                d = d3 / d2;
            } else if (i6 == 2) {
                double d4 = 0.0d;
                for (int i7 = 0; i7 < dArrComputeObjectiveGradient2.length; i7++) {
                    d4 += dArrComputeObjectiveGradient2[i7] * dArrPrecondition[i7];
                }
                d = (d3 - d4) / d2;
            } else {
                throw new MathInternalError();
            }
            if (getIterations() % length == 0 || d < 0.0d) {
                dArr = (double[]) dArrPrecondition2.clone();
            } else {
                for (int i8 = 0; i8 < length; i8++) {
                    dArr[i8] = dArrPrecondition2[i8] + (dArr[i8] * d);
                }
            }
            dArrPrecondition = dArrPrecondition2;
            pointValuePair = pointValuePair2;
            d2 = d3;
        }
    }

    @Override
    // org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer, org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    protected void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        checkParameters();
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }

    public enum Formula {
        FLETCHER_REEVES,
        POLAK_RIBIERE
    }

    @Deprecated
    public static class BracketingStep implements OptimizationData {
        private final double initialStep;

        public BracketingStep(double d) {
            this.initialStep = d;
        }

        public double getBracketingStep() {
            return this.initialStep;
        }
    }

    /* renamed from: org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula;

        static {
            int[] iArr = new int[Formula.values().length];
            $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula = iArr;
            try {
                iArr[Formula.FLETCHER_REEVES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[Formula.POLAK_RIBIERE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class IdentityPreconditioner implements Preconditioner {
        @Override // org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }
}
