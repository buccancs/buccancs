package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.IntegerSequence;

/* loaded from: classes5.dex */
public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private final double growLimit;
    private IntegerSequence.Incrementor evaluations;
    private double fHi;
    private double fLo;
    private double fMid;
    private double hi;
    private double lo;
    private double mid;

    public BracketFinder() {
        this(100.0d, 500);
    }

    public BracketFinder(double d, int i) {
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.growLimit = d;
        this.evaluations = IntegerSequence.Incrementor.create().withMaximalCount(i);
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

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a2, code lost:
    
        r1 = r16;
        r6 = r10;
        r10 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0139, code lost:
    
        r6 = r6;
        r14 = r4;
        r4 = r8;
        r1 = r16;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void search(org.apache.commons.math3.analysis.UnivariateFunction r35, org.apache.commons.math3.optim.nonlinear.scalar.GoalType r36, double r37, double r39) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.univariate.BracketFinder.search(org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.optim.nonlinear.scalar.GoalType, double, double):void");
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    private double eval(UnivariateFunction univariateFunction, double d) {
        try {
            this.evaluations.increment();
            return univariateFunction.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
