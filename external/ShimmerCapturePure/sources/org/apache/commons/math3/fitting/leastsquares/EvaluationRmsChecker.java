package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes5.dex */
public class EvaluationRmsChecker implements ConvergenceChecker<LeastSquaresProblem.Evaluation> {
    private final double absTol;
    private final double relTol;

    public EvaluationRmsChecker(double d) {
        this(d, d);
    }

    public EvaluationRmsChecker(double d, double d2) {
        this.relTol = d;
        this.absTol = d2;
    }

    @Override // org.apache.commons.math3.optim.ConvergenceChecker
    public boolean converged(int i, LeastSquaresProblem.Evaluation evaluation, LeastSquaresProblem.Evaluation evaluation2) {
        double rms = evaluation.getRMS();
        double rms2 = evaluation2.getRMS();
        return Precision.equals(rms, rms2, this.absTol) || Precision.equalsWithRelativeTolerance(rms, rms2, this.relTol);
    }
}
