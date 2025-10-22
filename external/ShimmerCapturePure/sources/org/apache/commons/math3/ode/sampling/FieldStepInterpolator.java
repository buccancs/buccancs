package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
public interface FieldStepInterpolator<T extends RealFieldElement<T>> {
    FieldODEStateAndDerivative<T> getCurrentState();

    FieldODEStateAndDerivative<T> getInterpolatedState(T t);

    FieldODEStateAndDerivative<T> getPreviousState();

    boolean isForward();
}
