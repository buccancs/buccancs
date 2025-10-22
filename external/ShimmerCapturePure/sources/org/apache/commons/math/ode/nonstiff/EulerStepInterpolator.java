package org.apache.commons.math.ode.nonstiff;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.sampling.StepInterpolator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/EulerStepInterpolator.class */
class EulerStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = -7179861704951334960L;

    public EulerStepInterpolator() {
    }

    public EulerStepInterpolator(EulerStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new EulerStepInterpolator(this);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) throws DerivativeException {
        for (int i = 0; i < this.interpolatedState.length; i++) {
            this.interpolatedState[i] = this.currentState[i] - (oneMinusThetaH * this.yDotK[0][i]);
        }
        System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
    }
}
