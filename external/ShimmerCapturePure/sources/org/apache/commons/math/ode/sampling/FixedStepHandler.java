package org.apache.commons.math.ode.sampling;

import org.apache.commons.math.ode.DerivativeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/FixedStepHandler.class */
public interface FixedStepHandler {
    void handleStep(double d, double[] dArr, double[] dArr2, boolean z) throws DerivativeException;
}
