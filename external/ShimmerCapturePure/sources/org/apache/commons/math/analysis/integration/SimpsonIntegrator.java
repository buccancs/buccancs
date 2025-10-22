package org.apache.commons.math.analysis.integration;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/integration/SimpsonIntegrator.class */
public class SimpsonIntegrator extends UnivariateRealIntegratorImpl {
    @Deprecated
    public SimpsonIntegrator(UnivariateRealFunction f) {
        super(f, 64);
    }

    public SimpsonIntegrator() {
        super(64);
    }

    @Override // org.apache.commons.math.analysis.integration.UnivariateRealIntegrator
    @Deprecated
    public double integrate(double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException, IllegalArgumentException {
        return integrate(this.f, min, max);
    }

    @Override // org.apache.commons.math.analysis.integration.UnivariateRealIntegrator
    public double integrate(UnivariateRealFunction f, double min, double max) throws FunctionEvaluationException, MaxIterationsExceededException, IllegalArgumentException {
        clearResult();
        verifyInterval(min, max);
        verifyIterationCount();
        TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
        if (this.minimalIterationCount == 1) {
            setResult(((4.0d * qtrap.stage(f, min, max, 1)) - qtrap.stage(f, min, max, 0)) / 3.0d, 1);
            return this.result;
        }
        double olds = 0.0d;
        double oldt = qtrap.stage(f, min, max, 0);
        for (int i = 1; i <= this.maximalIterationCount; i++) {
            double t = qtrap.stage(f, min, max, i);
            double s = ((4.0d * t) - oldt) / 3.0d;
            if (i >= this.minimalIterationCount) {
                double delta = FastMath.abs(s - olds);
                double rLimit = this.relativeAccuracy * (FastMath.abs(olds) + FastMath.abs(s)) * 0.5d;
                if (delta <= rLimit || delta <= this.absoluteAccuracy) {
                    setResult(s, i);
                    return this.result;
                }
            }
            olds = s;
            oldt = t;
        }
        throw new MaxIterationsExceededException(this.maximalIterationCount);
    }

    @Override // org.apache.commons.math.analysis.integration.UnivariateRealIntegratorImpl
    protected void verifyIterationCount() throws IllegalArgumentException {
        super.verifyIterationCount();
        if (this.maximalIterationCount > 64) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INVALID_ITERATIONS_LIMITS, 0, 64);
        }
    }
}
