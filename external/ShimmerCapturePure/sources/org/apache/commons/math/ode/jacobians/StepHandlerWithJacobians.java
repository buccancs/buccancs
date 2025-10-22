package org.apache.commons.math.ode.jacobians;

import org.apache.commons.math.ode.DerivativeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/StepHandlerWithJacobians.class */
public interface StepHandlerWithJacobians {
    boolean requiresDenseOutput();

    void reset();

    void handleStep(StepInterpolatorWithJacobians stepInterpolatorWithJacobians, boolean z) throws DerivativeException;
}
