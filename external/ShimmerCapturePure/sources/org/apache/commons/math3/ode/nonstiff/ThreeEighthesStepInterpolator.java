package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes5.dex */
class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ThreeEighthesStepInterpolator() {
    }

    ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator threeEighthesStepInterpolator) {
        super(threeEighthesStepInterpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 0.75d * d;
        double d4 = 4.0d * d;
        double d5 = ((d4 - 5.0d) * d3) + 1.0d;
        double d6 = (5.0d - (6.0d * d)) * d3;
        double d7 = ((d * 2.0d) - 1.0d) * d3;
        if (this.previousState != null && d <= 0.5d) {
            double d8 = (this.h * d) / 8.0d;
            double d9 = d4 * d;
            double d10 = ((8.0d - (15.0d * d)) + (2.0d * d9)) * d8;
            double d11 = 3.0d * d8;
            double d12 = ((5.0d * d) - d9) * d11;
            double d13 = d11 * d;
            double d14 = d8 * (((-3.0d) * d) + d9);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d15 = this.yDotK[0][i];
                double d16 = this.yDotK[1][i];
                double d17 = this.yDotK[2][i];
                double d18 = this.yDotK[3][i];
                this.interpolatedState[i] = this.previousState[i] + (d10 * d15) + (d12 * d16) + (d13 * d17) + (d14 * d18);
                this.interpolatedDerivatives[i] = (d15 * d5) + (d16 * d6) + (d17 * d3) + (d18 * d7);
            }
            return;
        }
        double d19 = d2 / 8.0d;
        double d20 = d4 * d;
        double d21 = ((1.0d - (7.0d * d)) + (2.0d * d20)) * d19;
        double d22 = 3.0d * d19;
        double d23 = d + 1.0d;
        double d24 = (d23 - d20) * d22;
        double d25 = d22 * d23;
        double d26 = d19 * (d23 + d20);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d27 = this.yDotK[0][i2];
            double d28 = this.yDotK[1][i2];
            double d29 = this.yDotK[2][i2];
            double d30 = this.yDotK[3][i2];
            this.interpolatedState[i2] = (((this.currentState[i2] - (d21 * d27)) - (d24 * d28)) - (d25 * d29)) - (d26 * d30);
            this.interpolatedDerivatives[i2] = (d27 * d5) + (d28 * d6) + (d29 * d3) + (d30 * d7);
        }
    }
}
