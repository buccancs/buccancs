package org.apache.commons.math.ode.nonstiff;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.sampling.StepInterpolator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/ThreeEighthesStepInterpolator.class */
class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = -3345024435978721931L;

    public ThreeEighthesStepInterpolator() {
    }

    public ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    @Override // org.apache.commons.math.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) throws DerivativeException {
        double fourTheta2 = 4.0d * theta * theta;
        double s = oneMinusThetaH / 8.0d;
        double coeff1 = s * ((1.0d - (7.0d * theta)) + (2.0d * fourTheta2));
        double coeff2 = 3.0d * s * ((1.0d + theta) - fourTheta2);
        double coeff3 = 3.0d * s * (1.0d + theta);
        double coeff4 = s * (1.0d + theta + fourTheta2);
        double coeffDot3 = 0.75d * theta;
        double coeffDot1 = (coeffDot3 * ((4.0d * theta) - 5.0d)) + 1.0d;
        double coeffDot2 = coeffDot3 * (5.0d - (6.0d * theta));
        double coeffDot4 = coeffDot3 * ((2.0d * theta) - 1.0d);
        for (int i = 0; i < this.interpolatedState.length; i++) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            double yDot3 = this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            this.interpolatedState[i] = (((this.currentState[i] - (coeff1 * yDot1)) - (coeff2 * yDot2)) - (coeff3 * yDot3)) - (coeff4 * yDot4);
            this.interpolatedDerivatives[i] = (coeffDot1 * yDot1) + (coeffDot2 * yDot2) + (coeffDot3 * yDot3) + (coeffDot4 * yDot4);
        }
    }
}
