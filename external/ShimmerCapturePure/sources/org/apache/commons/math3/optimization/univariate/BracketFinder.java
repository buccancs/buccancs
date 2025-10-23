package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
/* loaded from: classes5.dex */
public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private final Incrementor evaluations;
    private final double growLimit;
    private double fHi;
    private double fLo;
    private double fMid;
    private double hi;
    private double lo;
    private double mid;

    public BracketFinder() {
        this(100.0d, 50);
    }

    public BracketFinder(double d, int i) {
        Incrementor incrementor = new Incrementor();
        this.evaluations = incrementor;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.growLimit = d;
        incrementor.setMaximalCount(i);
    }

    public double getFHi() {
        return this.fHi;
    }

    public double getFLo() {
        return this.fLo;
    }

    public double getFMid() {
        return this.fMid;
    }

    public double getHi() {
        return this.hi;
    }

    public double getLo() {
        return this.lo;
    }

    public double getMid() {
        return this.mid;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a0, code lost:
    
        r1 = r15;
        r5 = r9;
        r9 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0130, code lost:
    
        r5 = r5;
        r13 = r3;
        r3 = r7;
        r1 = r15;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void search(org.apache.commons.math3.analysis.UnivariateFunction r35, org.apache.commons.math3.optimization.GoalType r36, double r37, double r39) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.univariate.BracketFinder.search(org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.optimization.GoalType, double, double):void");
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    private double eval(UnivariateFunction univariateFunction, double d) {
        try {
            this.evaluations.incrementCount();
            return univariateFunction.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
