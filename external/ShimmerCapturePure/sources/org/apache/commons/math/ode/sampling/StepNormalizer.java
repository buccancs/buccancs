package org.apache.commons.math.ode.sampling;

import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/StepNormalizer.class */
public class StepNormalizer implements StepHandler {
    private final FixedStepHandler handler;
    private double h;
    private double lastTime;
    private double[] lastState;
    private double[] lastDerivatives;
    private boolean forward;

    public StepNormalizer(double h, FixedStepHandler handler) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        reset();
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public boolean requiresDenseOutput() {
        return true;
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public void reset() {
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
    }

    /* JADX WARN: Incorrect condition in loop: B:17:0x0085 */
    @Override // org.apache.commons.math.ode.sampling.StepHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleStep(org.apache.commons.math.ode.sampling.StepInterpolator r8, boolean r9) throws org.apache.commons.math.ode.DerivativeException {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.ode.sampling.StepNormalizer.handleStep(org.apache.commons.math.ode.sampling.StepInterpolator, boolean):void");
    }
}
