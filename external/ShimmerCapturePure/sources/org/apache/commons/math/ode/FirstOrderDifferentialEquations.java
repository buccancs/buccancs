package org.apache.commons.math.ode;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/FirstOrderDifferentialEquations.class */
public interface FirstOrderDifferentialEquations {
    int getDimension();

    void computeDerivatives(double d, double[] dArr, double[] dArr2) throws DerivativeException;
}
