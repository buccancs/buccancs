package org.apache.commons.math.optimization.univariate;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.NotStrictlyPositiveException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/optimization/univariate/BracketFinder.class */
public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private final double growLimit;
    private final int maxIterations;
    private int iterations;
    private int evaluations;
    private double lo;
    private double hi;
    private double mid;
    private double fLo;
    private double fHi;
    private double fMid;

    public BracketFinder() {
        this(100.0d, 50);
    }

    public BracketFinder(double growLimit, int maxIterations) {
        if (growLimit <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(growLimit));
        }
        if (maxIterations <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(maxIterations));
        }
        this.growLimit = growLimit;
        this.maxIterations = maxIterations;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x012f, code lost:
    
        r12 = r14;
        r14 = r33;
        r17 = r19;
        r19 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void search(org.apache.commons.math.analysis.UnivariateRealFunction r10, org.apache.commons.math.optimization.GoalType r11, double r12, double r14) throws org.apache.commons.math.FunctionEvaluationException, org.apache.commons.math.MaxIterationsExceededException {
        /*
            Method dump skipped, instructions count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.optimization.univariate.BracketFinder.search(org.apache.commons.math.analysis.UnivariateRealFunction, org.apache.commons.math.optimization.GoalType, double, double):void");
    }

    public int getIterations() {
        return this.iterations;
    }

    public int getEvaluations() {
        return this.evaluations;
    }

    public double getLo() {
        return this.lo;
    }

    public double getFLow() {
        return this.fLo;
    }

    public double getHi() {
        return this.hi;
    }

    public double getFHi() {
        return this.fHi;
    }

    public double getMid() {
        return this.mid;
    }

    public double getFMid() {
        return this.fMid;
    }

    private double eval(UnivariateRealFunction f, double x) throws FunctionEvaluationException {
        this.evaluations++;
        return f.value(x);
    }

    private void reset() {
        this.iterations = 0;
        this.evaluations = 0;
    }
}
