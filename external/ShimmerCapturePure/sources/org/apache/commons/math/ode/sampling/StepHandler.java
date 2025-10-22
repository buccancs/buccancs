package org.apache.commons.math.ode.sampling;

import org.apache.commons.math.ode.DerivativeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/StepHandler.class */
public interface StepHandler {
    boolean requiresDenseOutput();

    void reset();

    void handleStep(StepInterpolator stepInterpolator, boolean z) throws DerivativeException;
}
