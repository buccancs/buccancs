package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.IntegerSequence;

/* loaded from: classes5.dex */
public class FieldBracketingNthOrderBrentSolver<T extends RealFieldElement<T>> implements BracketedRealFieldUnivariateSolver<T> {
    private static final int MAXIMAL_AGING = 2;
    private final T absoluteAccuracy;
    private final Field<T> field;
    private final T functionValueAccuracy;
    private final int maximalOrder;
    private final T relativeAccuracy;
    private IntegerSequence.Incrementor evaluations;

    public FieldBracketingNthOrderBrentSolver(T t, T t2, T t3, int i) throws NumberIsTooSmallException {
        if (i < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        this.field = t.getField();
        this.maximalOrder = i;
        this.absoluteAccuracy = t2;
        this.relativeAccuracy = t;
        this.functionValueAccuracy = t3;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T solve(int i, RealFieldUnivariateFunction<T> realFieldUnivariateFunction, T t, T t2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return (T) solve(i, realFieldUnivariateFunction, t, t2, (RealFieldElement) ((RealFieldElement) t.add(t2)).divide(2.0d), allowedSolution);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0324, code lost:
    
        if (r5.getReal() > 0.0d) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0328, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0329, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x032a, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x033b, code lost:
    
        if (((org.apache.commons.math3.RealFieldElement) r25.subtract(r19)).getReal() >= 0.0d) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x033f, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:?, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:?, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:?, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x02ee, code lost:
    
        r25 = r14;
        r5 = r0;
        r0 = org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution[r39.ordinal()];
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02ff, code lost:
    
        if (r0 == 1) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0302, code lost:
    
        if (r0 == 2) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0305, code lost:
    
        if (r0 == 3) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0308, code lost:
    
        if (r0 == 4) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x030b, code lost:
    
        if (r0 != 5) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0313, code lost:
    
        if (r5.getReal() >= 0.0d) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0316, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x031d, code lost:
    
        throw new org.apache.commons.math3.exception.MathInternalError(null);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02bf  */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [T extends org.apache.commons.math3.RealFieldElement<T>, java.lang.Object, org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r1v23, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v39, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T extends org.apache.commons.math3.RealFieldElement<T>, org.apache.commons.math3.RealFieldElement] */
    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public T solve(int r34, org.apache.commons.math3.analysis.RealFieldUnivariateFunction<T> r35, T r36, T r37, T r38, org.apache.commons.math3.analysis.solvers.AllowedSolution r39) throws org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 868
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve(int, org.apache.commons.math3.analysis.RealFieldUnivariateFunction, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.analysis.solvers.AllowedSolution):org.apache.commons.math3.RealFieldElement");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T guessX(T t, T[] tArr, T[] tArr2, int i, int i2) {
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
                tArr[i3] = (RealFieldElement) ((RealFieldElement) tArr[i3].subtract((Decimal64) tArr[i3 - 1])).divide((RealFieldElement) tArr2[i3].subtract(tArr2[i3 - i6]));
                i3--;
            }
            i4 = i5;
        }
        T zero = this.field.getZero();
        while (i3 >= i) {
            zero = (T) tArr[i3].add((Decimal64) zero.multiply(t.subtract(tArr2[i3])));
            i3--;
        }
        return zero;
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver$1, reason: invalid class name */
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
