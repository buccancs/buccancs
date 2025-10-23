package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class UnivariateSolverUtils {
    private UnivariateSolverUtils() {
    }

    public static boolean isSequence(double d, double d2, double d3) {
        return d < d2 && d2 < d3;
    }

    public static double midpoint(double d, double d2) {
        return (d + d2) * 0.5d;
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        return new BrentSolver().solve(Integer.MAX_VALUE, univariateFunction, d, d2);
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NullArgumentException, NoBracketingException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        return new BrentSolver(d3).solve(Integer.MAX_VALUE, univariateFunction, d, d2);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static double forceSide(int r28, org.apache.commons.math3.analysis.UnivariateFunction r29, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver<org.apache.commons.math3.analysis.UnivariateFunction> r30, double r31, double r33, double r35, org.apache.commons.math3.analysis.solvers.AllowedSolution r37) throws org.apache.commons.math3.exception.NoBracketingException {
        /*
            r2 = r29
            r0 = r33
            r3 = r35
            org.apache.commons.math3.analysis.solvers.AllowedSolution r5 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE
            r9 = r37
            if (r9 != r5) goto Ld
            return r31
        Ld:
            double r5 = r30.getAbsoluteAccuracy()
            double r7 = r30.getRelativeAccuracy()
            double r7 = r7 * r31
            double r7 = org.apache.commons.math3.util.FastMath.abs(r7)
            double r5 = org.apache.commons.math3.util.FastMath.max(r5, r7)
            double r7 = r31 - r5
            double r7 = org.apache.commons.math3.util.FastMath.max(r0, r7)
            double r10 = r2.value(r7)
            double r12 = r31 + r5
            double r12 = org.apache.commons.math3.util.FastMath.min(r3, r12)
            double r14 = r2.value(r12)
            int r16 = r28 + (-2)
            r19 = r7
            r23 = r10
            r21 = r12
            r25 = r14
        L3d:
            r7 = 0
            r8 = 1
            if (r16 <= 0) goto L9c
            r10 = 0
            int r12 = (r23 > r10 ? 1 : (r23 == r10 ? 0 : -1))
            if (r12 < 0) goto L4b
            int r13 = (r25 > r10 ? 1 : (r25 == r10 ? 0 : -1))
            if (r13 <= 0) goto L53
        L4b:
            int r13 = (r23 > r10 ? 1 : (r23 == r10 ? 0 : -1))
            if (r13 > 0) goto L66
            int r14 = (r25 > r10 ? 1 : (r25 == r10 ? 0 : -1))
            if (r14 < 0) goto L66
        L53:
            r0 = r30
            r1 = r16
            r2 = r29
            r3 = r19
            r5 = r21
            r7 = r31
            r9 = r37
            double r0 = r0.solve(r1, r2, r3, r5, r7, r9)
            return r0
        L66:
            int r10 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
            if (r10 >= 0) goto L6f
            if (r12 < 0) goto L77
        L6c:
            r7 = 1
            r8 = 0
            goto L77
        L6f:
            int r10 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
            if (r10 <= 0) goto L76
            if (r13 > 0) goto L77
            goto L6c
        L76:
            r7 = 1
        L77:
            if (r7 == 0) goto L89
            double r10 = r19 - r5
            double r10 = org.apache.commons.math3.util.FastMath.max(r0, r10)
            double r12 = r2.value(r10)
            int r16 = r16 + (-1)
            r19 = r10
            r23 = r12
        L89:
            if (r8 == 0) goto L3d
            double r7 = r21 + r5
            double r7 = org.apache.commons.math3.util.FastMath.min(r3, r7)
            double r10 = r2.value(r7)
            int r16 = r16 + (-1)
            r21 = r7
            r25 = r10
            goto L3d
        L9c:
            org.apache.commons.math3.exception.NoBracketingException r2 = new org.apache.commons.math3.exception.NoBracketingException
            org.apache.commons.math3.exception.util.LocalizedFormats r18 = org.apache.commons.math3.exception.util.LocalizedFormats.FAILED_BRACKETING
            r5 = 5
            java.lang.Object[] r5 = new java.lang.Object[r5]
            int r6 = r28 - r16
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r7] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r28)
            r5[r8] = r6
            java.lang.Double r6 = java.lang.Double.valueOf(r31)
            r7 = 2
            r5[r7] = r6
            r6 = 3
            java.lang.Double r0 = java.lang.Double.valueOf(r33)
            r5[r6] = r0
            r0 = 4
            java.lang.Double r1 = java.lang.Double.valueOf(r35)
            r5[r0] = r1
            r17 = r2
            r27 = r5
            r17.<init>(r18, r19, r21, r23, r25, r27)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide(int, org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver, double, double, double, org.apache.commons.math3.analysis.solvers.AllowedSolution):double");
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NotStrictlyPositiveException, NullArgumentException, NoBracketingException {
        return bracket(univariateFunction, d, d2, d3, 1.0d, 1.0d, Integer.MAX_VALUE);
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3, int i) throws NotStrictlyPositiveException, NullArgumentException, NoBracketingException {
        return bracket(univariateFunction, d, d2, d3, 1.0d, 1.0d, i);
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3, double d4, double d5, int i) throws NumberIsTooLargeException, NoBracketingException {
        long j;
        UnivariateFunction univariateFunction2 = univariateFunction;
        double d6 = d2;
        if (univariateFunction2 == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        if (d4 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d4));
        }
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, Integer.valueOf(i));
        }
        double d7 = d;
        verifySequence(d2, d7, d3);
        double d8 = Double.NaN;
        double d9 = d7;
        double d10 = Double.NaN;
        double d11 = 0.0d;
        int i2 = 0;
        while (i2 < i && (d7 > d6 || d9 < d3)) {
            d11 = (d11 * d5) + d4;
            double dMax = FastMath.max(d - d11, d6);
            double dMin = FastMath.min(d + d11, d3);
            double dValue = univariateFunction2.value(dMax);
            double dValue2 = univariateFunction2.value(dMin);
            if (i2 == 0) {
                j = 0;
                if (dValue * dValue2 <= 0.0d) {
                    return new double[]{dMax, dMin};
                }
            } else {
                j = 0;
                if (d8 * dValue <= 0.0d) {
                    return new double[]{dMax, d7};
                }
                if (d10 * dValue2 <= 0.0d) {
                    return new double[]{d9, dMin};
                }
            }
            i2++;
            univariateFunction2 = univariateFunction;
            d9 = dMin;
            d7 = dMax;
            d8 = dValue;
            d10 = dValue2;
            d6 = d2;
        }
        throw new NoBracketingException(d7, d9, d8, d10);
    }

    public static boolean isBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        double dValue = univariateFunction.value(d);
        double dValue2 = univariateFunction.value(d2);
        return (dValue >= 0.0d && dValue2 <= 0.0d) || (dValue <= 0.0d && dValue2 >= 0.0d);
    }

    public static void verifyInterval(double d, double d2) throws NumberIsTooLargeException {
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), false);
        }
    }

    public static void verifySequence(double d, double d2, double d3) throws NumberIsTooLargeException {
        verifyInterval(d, d2);
        verifyInterval(d2, d3);
    }

    public static void verifyBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        verifyInterval(d, d2);
        if (!isBracketing(univariateFunction, d, d2)) {
            throw new NoBracketingException(d, d2, univariateFunction.value(d), univariateFunction.value(d2));
        }
    }
}
