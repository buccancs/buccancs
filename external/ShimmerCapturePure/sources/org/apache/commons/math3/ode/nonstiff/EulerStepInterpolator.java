package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes5.dex */
class EulerStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public EulerStepInterpolator() {
    }

    EulerStepInterpolator(EulerStepInterpolator eulerStepInterpolator) {
        super(eulerStepInterpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new EulerStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double d, double d2) {
        if (this.previousState != null && d <= 0.5d) {
            for (int i = 0; i < this.interpolatedState.length; i++) {
                this.interpolatedState[i] = this.previousState[i] + (this.h * d * this.yDotK[0][i]);
            }
            System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
            return;
        }
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            this.interpolatedState[i2] = this.currentState[i2] - (this.yDotK[0][i2] * d2);
        }
        System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
    }
}
