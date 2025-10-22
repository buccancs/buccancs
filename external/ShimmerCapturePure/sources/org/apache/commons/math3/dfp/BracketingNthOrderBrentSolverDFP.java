package org.apache.commons.math3.dfp;

import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
/* loaded from: classes5.dex */
public class BracketingNthOrderBrentSolverDFP extends FieldBracketingNthOrderBrentSolver<Dfp> {
    public BracketingNthOrderBrentSolverDFP(Dfp dfp, Dfp dfp2, Dfp dfp3, int i) throws NumberIsTooSmallException {
        super(dfp, dfp2, dfp3, i);
    }

    @Override
    // org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver, org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public Dfp getAbsoluteAccuracy() {
        return (Dfp) super.getAbsoluteAccuracy();
    }

    @Override
    // org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver, org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public Dfp getRelativeAccuracy() {
        return (Dfp) super.getRelativeAccuracy();
    }

    @Override
    // org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver, org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public Dfp getFunctionValueAccuracy() {
        return (Dfp) super.getFunctionValueAccuracy();
    }

    public Dfp solve(int i, UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(i, univariateDfpFunction, dfp, dfp2, dfp.add(dfp2).divide(2), allowedSolution);
    }

    public Dfp solve(int i, final UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, Dfp dfp3, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        MathUtils.checkNotNull(univariateDfpFunction);
        return solve(i, new RealFieldUnivariateFunction<Dfp>() { // from class: org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.1
            @Override // org.apache.commons.math3.analysis.RealFieldUnivariateFunction
            public Dfp value(Dfp dfp4) {
                return univariateDfpFunction.value(dfp4);
            }
        }, dfp, dfp2, dfp3, allowedSolution);
    }
}
