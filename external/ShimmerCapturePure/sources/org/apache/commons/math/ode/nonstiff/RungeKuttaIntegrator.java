package org.apache.commons.math.ode.nonstiff;

import org.apache.commons.math.ode.AbstractIntegrator;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math.ode.sampling.DummyStepInterpolator;
import org.apache.commons.math.ode.sampling.StepHandler;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/RungeKuttaIntegrator.class */
public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
    private final double[] c;
    private final double[][] a;
    private final double[] b;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    protected RungeKuttaIntegrator(String name, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double step) {
        super(name);
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.step = FastMath.abs(step);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [double[], double[][]] */
    @Override // org.apache.commons.math.ode.FirstOrderIntegrator
    public double integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y) throws DerivativeException, IntegratorException {
        AbstractStepInterpolator interpolator;
        sanityChecks(equations, t0, y0, t, y);
        setEquations(equations);
        resetEvaluations();
        boolean forward = t > t0;
        int stages = this.c.length + 1;
        if (y != y0) {
            System.arraycopy(y0, 0, y, 0, y0.length);
        }
        ??r0 = new double[stages];
        for (int i = 0; i < stages; i++) {
            r0[i] = new double[y0.length];
        }
        double[] yTmp = new double[y0.length];
        double[] yDotTmp = new double[y0.length];
        if (requiresDenseOutput()) {
            RungeKuttaStepInterpolator rungeKuttaStepInterpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
            rungeKuttaStepInterpolator.reinitialize(this, yTmp, r0, forward);
            interpolator = rungeKuttaStepInterpolator;
        } else {
            interpolator = new DummyStepInterpolator(yTmp, r0[stages - 1], forward);
        }
        interpolator.storeTime(t0);
        this.stepStart = t0;
        this.stepSize = forward ? this.step : -this.step;
        for (StepHandler handler : this.stepHandlers) {
            handler.reset();
        }
        setStateInitialized(false);
        this.isLastStep = false;
        do {
            interpolator.shift();
            computeDerivatives(this.stepStart, y, r0[0]);
            for (int k = 1; k < stages; k++) {
                for (int j = 0; j < y0.length; j++) {
                    double sum = this.a[k - 1][0] * r0[0][j];
                    for (int l = 1; l < k; l++) {
                        sum += this.a[k - 1][l] * r0[l][j];
                    }
                    yTmp[j] = y[j] + (this.stepSize * sum);
                }
                computeDerivatives(this.stepStart + (this.c[k - 1] * this.stepSize), yTmp, r0[k]);
            }
            for (int j2 = 0; j2 < y0.length; j2++) {
                double sum2 = this.b[0] * r0[0][j2];
                for (int l2 = 1; l2 < stages; l2++) {
                    sum2 += this.b[l2] * r0[l2][j2];
                }
                yTmp[j2] = y[j2] + (this.stepSize * sum2);
            }
            interpolator.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            System.arraycopy(r0[stages - 1], 0, yDotTmp, 0, y0.length);
            this.stepStart = acceptStep(interpolator, y, yDotTmp, t);
            if (!this.isLastStep) {
                interpolator.storeTime(this.stepStart);
                double nextT = this.stepStart + this.stepSize;
                boolean nextIsLast = forward ? nextT >= t : nextT <= t;
                if (nextIsLast) {
                    this.stepSize = t - this.stepStart;
                }
            }
        } while (!this.isLastStep);
        double stopTime = this.stepStart;
        this.stepStart = Double.NaN;
        this.stepSize = Double.NaN;
        return stopTime;
    }
}
