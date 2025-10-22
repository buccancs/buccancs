package org.apache.commons.math.ode;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/SecondOrderIntegrator.class */
public interface SecondOrderIntegrator extends ODEIntegrator {
    void integrate(SecondOrderDifferentialEquations secondOrderDifferentialEquations, double d, double[] dArr, double[] dArr2, double d2, double[] dArr3, double[] dArr4) throws DerivativeException, IntegratorException;
}
