package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes5.dex */
class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public HighamHall54StepInterpolator() {
    }

    HighamHall54StepInterpolator(HighamHall54StepInterpolator highamHall54StepInterpolator) {
        super(highamHall54StepInterpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new HighamHall54StepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = (((d * (16.0d - (10.0d * d))) - 7.5d) * d) + 1.0d;
        double d4 = ((((67.5d * d) - 91.125d) * d) + 28.6875d) * d;
        double d5 = ((d * (152.0d - (120.0d * d))) - 44.0d) * d;
        double d6 = ((((62.5d * d) - 78.125d) * d) + 23.4375d) * d;
        double d7 = ((5.0d * d) / 8.0d) * ((d * 2.0d) - 1.0d);
        if (this.previousState != null && d <= 0.5d) {
            double d8 = this.h * d;
            double d9 = ((((d * (5.333333333333333d - (2.5d * d))) - 3.75d) * d) + 1.0d) * d8;
            double d10 = (((((d * 135.0d) / 8.0d) - 30.375d) * d) + 14.34375d) * d * d8;
            double d11 = (((((-30.0d) * d) + 50.666666666666664d) * d) - 22.0d) * d * d8;
            double d12 = (((((125.0d * d) / 8.0d) - 26.041666666666668d) * d) + 11.71875d) * d * d8;
            double d13 = d8 * ((r11 / 12.0d) - 0.3125d) * d;
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d14 = this.yDotK[0][i];
                double d15 = this.yDotK[2][i];
                double d16 = this.yDotK[3][i];
                double d17 = this.yDotK[4][i];
                double d18 = this.yDotK[5][i];
                this.interpolatedState[i] = this.previousState[i] + (d9 * d14) + (d10 * d15) + (d11 * d16) + (d12 * d17) + (d13 * d18);
                this.interpolatedDerivatives[i] = (d14 * d3) + (d15 * d4) + (d16 * d5) + (d17 * d6) + (d7 * d18);
            }
            return;
        }
        double d19 = this.h * ((d * ((((d * ((((-5.0d) * d) / 2.0d) + 5.333333333333333d)) - 3.75d) * d) + 1.0d)) - 0.08333333333333333d);
        double d20 = d6;
        double d21 = this.h * (((((((d * 135.0d) / 8.0d) - 30.375d) * d) + 14.34375d) * r13) - 0.84375d);
        double d22 = this.h * (((((((-30.0d) * d) + 50.666666666666664d) * d) - 22.0d) * d * d) + 1.3333333333333333d);
        double d23 = this.h * (((((((125.0d * d) / 8.0d) - 26.041666666666668d) * d) + 11.71875d) * r13) - 1.3020833333333333d);
        double d24 = this.h * ((r13 * ((r11 / 12.0d) - 0.3125d)) - 0.10416666666666667d);
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double d25 = this.yDotK[0][i2];
            double d26 = this.yDotK[2][i2];
            double d27 = this.yDotK[3][i2];
            double d28 = this.yDotK[4][i2];
            double d29 = this.yDotK[5][i2];
            double d30 = d20;
            this.interpolatedState[i2] = this.currentState[i2] + (d19 * d25) + (d21 * d26) + (d22 * d27) + (d23 * d28) + (d24 * d29);
            this.interpolatedDerivatives[i2] = (d25 * d3) + (d26 * d4) + (d5 * d27) + (d30 * d28) + (d7 * d29);
            i2++;
            d20 = d30;
        }
    }
}
