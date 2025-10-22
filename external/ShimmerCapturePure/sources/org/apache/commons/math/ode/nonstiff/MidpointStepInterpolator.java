package org.apache.commons.math.ode.nonstiff;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.sampling.StepInterpolator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/MidpointStepInterpolator.class */
class MidpointStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = -865524111506042509L;

    public MidpointStepInterpolator() {
    }

    public MidpointStepInterpolator(MidpointStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new MidpointStepInterpolator(this);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) throws DerivativeException {
        double coeff1 = oneMinusThetaH * theta;
        double coeff2 = oneMinusThetaH * (1.0d + theta);
        double coeffDot2 = 2.0d * theta;
        double coeffDot1 = 1.0d - coeffDot2;
        for (int i = 0; i < this.interpolatedState.length; i++) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            this.interpolatedState[i] = (this.currentState[i] + (coeff1 * yDot1)) - (coeff2 * yDot2);
            this.interpolatedDerivatives[i] = (coeffDot1 * yDot1) + (coeffDot2 * yDot2);
        }
    }
}
