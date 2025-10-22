package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/* loaded from: classes5.dex */
public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final int DEFAULT_MAXIMAL_ORDER = 5;
    private static final int MAXIMAL_AGING = 2;
    private static final double REDUCTION_FACTOR = 0.0625d;
    private final int maximalOrder;
    private AllowedSolution allowed;

    public BracketingNthOrderBrentSolver() {
        this(1.0E-6d, 5);
    }

    public BracketingNthOrderBrentSolver(double d, int i) throws NumberIsTooSmallException {
        super(d);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double d, double d2, int i) throws NumberIsTooSmallException {
        super(d, d2);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double d, double d2, double d3, int i) throws NumberIsTooSmallException {
        super(d, d2, d3);
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        this.maximalOrder = i;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0227, code lost:
    
        if (r36 >= r30) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x022c, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
    
        return r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:?, code lost:
    
        return r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:?, code lost:
    
        return r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01ef, code lost:
    
        r36 = r12;
        r30 = r14;
        r0 = org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[r39.allowed.ordinal()];
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0200, code lost:
    
        if (r0 == 1) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0203, code lost:
    
        if (r0 == 2) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0206, code lost:
    
        if (r0 == 3) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0209, code lost:
    
        if (r0 == 4) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x020c, code lost:
    
        if (r0 != 5) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0210, code lost:
    
        if (r16 >= 0.0d) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0214, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x021a, code lost:
    
        throw new org.apache.commons.math3.exception.MathInternalError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x021d, code lost:
    
        if (r16 > 0.0d) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0222, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0223, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0224, code lost:
    
        return r18;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0180 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0181  */
    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected double doSolve() throws org.apache.commons.math3.exception.TooManyEvaluationsException, org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve():double");
    }

    private double guessX(double d, double[] dArr, double[] dArr2, int i, int i2) {
        int i3;
        int i4 = i;
        while (true) {
            i3 = i2 - 1;
            if (i4 >= i3) {
                break;
            }
            int i5 = i4 + 1;
            int i6 = i5 - i;
            while (i3 > i4) {
                dArr[i3] = (dArr[i3] - dArr[i3 - 1]) / (dArr2[i3] - dArr2[i3 - i6]);
                i3--;
            }
            i4 = i5;
        }
        double d2 = 0.0d;
        while (i3 >= i) {
            d2 = (d2 * (d - dArr2[i3])) + dArr[i3];
            i3--;
        }
        return d2;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, (int) univariateFunction, d, d2, d3);
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution;

        static {
            int[] iArr = new int[AllowedSolution.values().length];
            $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution = iArr;
            try {
                iArr[AllowedSolution.ANY_SIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[AllowedSolution.LEFT_SIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[AllowedSolution.RIGHT_SIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[AllowedSolution.BELOW_SIDE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[AllowedSolution.ABOVE_SIDE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
