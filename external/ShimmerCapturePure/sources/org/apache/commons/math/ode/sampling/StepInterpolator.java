package org.apache.commons.math.ode.sampling;

import java.io.Externalizable;

import org.apache.commons.math.ode.DerivativeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/StepInterpolator.class */
public interface StepInterpolator extends Externalizable {
    double getPreviousTime();

    double getCurrentTime();

    double getInterpolatedTime();

    void setInterpolatedTime(double d);

    double[] getInterpolatedState() throws DerivativeException;

    double[] getInterpolatedDerivatives() throws DerivativeException;

    boolean isForward();

    StepInterpolator copy() throws DerivativeException;
}
