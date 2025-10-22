package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes5.dex */
class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ClassicalRungeKuttaStepInterpolator() {
    }

    ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator classicalRungeKuttaStepInterpolator) {
        super(classicalRungeKuttaStepInterpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new ClassicalRungeKuttaStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 1.0d - d;
        double d4 = d * 2.0d;
        double d5 = 1.0d - d4;
        double d6 = d3 * d5;
        double d7 = d4 * d3;
        double d8 = (-d) * d5;
        if (this.previousState != null && d <= 0.5d) {
            double d9 = d * 4.0d * d;
            double d10 = (this.h * d) / 6.0d;
            double d11 = ((6.0d - (9.0d * d)) + d9) * d10;
            double d12 = ((6.0d * d) - d9) * d10;
            double d13 = d10 * ((d * (-3.0d)) + d9);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d14 = this.yDotK[0][i];
                double d15 = this.yDotK[1][i] + this.yDotK[2][i];
                double d16 = this.yDotK[3][i];
                this.interpolatedState[i] = this.previousState[i] + (d11 * d14) + (d12 * d15) + (d13 * d16);
                this.interpolatedDerivatives[i] = (d14 * d6) + (d15 * d7) + (d16 * d8);
            }
            return;
        }
        double d17 = d * 4.0d;
        double d18 = d2 / 6.0d;
        double d19 = -d17;
        double d20 = (((d19 + 5.0d) * d) - 1.0d) * d18;
        double d21 = (((d17 - 2.0d) * d) - 2.0d) * d18;
        double d22 = d18 * (((d19 - 1.0d) * d) - 1.0d);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d23 = this.yDotK[0][i2];
            double d24 = this.yDotK[1][i2] + this.yDotK[2][i2];
            double d25 = this.yDotK[3][i2];
            this.interpolatedState[i2] = this.currentState[i2] + (d20 * d23) + (d21 * d24) + (d22 * d25);
            this.interpolatedDerivatives[i2] = (d23 * d6) + (d24 * d7) + (d25 * d8);
        }
    }
}
