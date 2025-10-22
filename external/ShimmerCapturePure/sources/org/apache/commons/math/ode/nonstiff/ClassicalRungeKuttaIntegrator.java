package org.apache.commons.math.ode.nonstiff;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/ClassicalRungeKuttaIntegrator.class */
public class ClassicalRungeKuttaIntegrator extends RungeKuttaIntegrator {
    private static final double[] STATIC_C = {0.5d, 0.5d, 1.0d};
    private static final double[][] STATIC_A = {new double[]{0.5d}, new double[]{0.0d, 0.5d}, new double[]{0.0d, 0.0d, 1.0d}};
    private static final double[] STATIC_B = {0.16666666666666666d, 0.3333333333333333d, 0.3333333333333333d, 0.16666666666666666d};

    public ClassicalRungeKuttaIntegrator(double step) {
        super("classical Runge-Kutta", STATIC_C, STATIC_A, STATIC_B, new ClassicalRungeKuttaStepInterpolator(), step);
    }
}
