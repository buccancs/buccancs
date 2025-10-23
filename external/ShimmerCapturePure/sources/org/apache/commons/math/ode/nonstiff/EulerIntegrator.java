package org.apache.commons.math.ode.nonstiff;

import com.shimmerresearch.algorithms.orientation.OrientationModule;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/EulerIntegrator.class */
public class EulerIntegrator extends RungeKuttaIntegrator {
    private static final double[] STATIC_C = new double[0];
    private static final double[][] STATIC_A = new double[0];
    private static final double[] STATIC_B = {1.0d};

    public EulerIntegrator(double step) {
        super(OrientationModule.GuiLabelConfig.EULER_OUTPUT, STATIC_C, STATIC_A, STATIC_B, new EulerStepInterpolator(), step);
    }
}
