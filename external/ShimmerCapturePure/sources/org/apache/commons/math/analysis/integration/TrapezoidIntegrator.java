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
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/integration/TrapezoidIntegrator.class */
public class TrapezoidIntegrator extends UnivariateRealIntegratorImpl {
    private double s;

    @Deprecated
    public TrapezoidIntegrator(UnivariateRealFunction f) {
        super(f, 64);
    }

    public TrapezoidIntegrator() {
        super(64);
    }

    double stage(UnivariateRealFunction f, double min, double max, int n) throws FunctionEvaluationException {
        if (n == 0) {
            this.s = 0.5d * (max - min) * (f.value(min) + f.value(max));
            return this.s;
        }
        long np = 1 << (n - 1);
        double sum = 0.0d;
        double spacing = (max - min) / np;
        double x = min + (0.5d * spacing);
        long j = 0;
        while (true) {
            long i = j;
            if (i < np) {
                sum += f.value(x);
                x += spacing;
                j = i + 1;
            } else {
                this.s = 0.5d * (this.s + (sum * spacing));
                return this.s;
            }
        }
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
        double oldt = stage(f, min, max, 0);
        for (int i = 1; i <= this.maximalIterationCount; i++) {
            double t = stage(f, min, max, i);
            if (i >= this.minimalIterationCount) {
                double delta = FastMath.abs(t - oldt);
                double rLimit = this.relativeAccuracy * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5d;
                if (delta <= rLimit || delta <= this.absoluteAccuracy) {
                    setResult(t, i);
                    return this.result;
                }
            }
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
