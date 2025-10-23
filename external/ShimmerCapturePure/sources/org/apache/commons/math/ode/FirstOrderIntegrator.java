package org.apache.commons.math.ode;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/FirstOrderIntegrator.class */
public interface FirstOrderIntegrator extends ODEIntegrator {
    double integrate(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2, double[] dArr2) throws DerivativeException, IntegratorException;
}
