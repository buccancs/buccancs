package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
public interface FieldEventHandler<T extends RealFieldElement<T>> {
    Action eventOccurred(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, boolean z);

    T g(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative);

    void init(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T t);

    FieldODEState<T> resetState(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative);
}
