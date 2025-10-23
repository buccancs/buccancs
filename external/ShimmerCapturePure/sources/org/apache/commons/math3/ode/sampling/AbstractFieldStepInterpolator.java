package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
public abstract class AbstractFieldStepInterpolator<T extends RealFieldElement<T>> implements FieldStepInterpolator<T> {
    private final boolean forward;
    private final FieldODEStateAndDerivative<T> globalCurrentState;
    private final FieldODEStateAndDerivative<T> globalPreviousState;
    private final FieldODEStateAndDerivative<T> softCurrentState;
    private final FieldODEStateAndDerivative<T> softPreviousState;
    private FieldEquationsMapper<T> mapper;

    protected AbstractFieldStepInterpolator(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        this.forward = z;
        this.globalPreviousState = fieldODEStateAndDerivative;
        this.globalCurrentState = fieldODEStateAndDerivative2;
        this.softPreviousState = fieldODEStateAndDerivative3;
        this.softCurrentState = fieldODEStateAndDerivative4;
        this.mapper = fieldEquationsMapper;
    }

    protected abstract FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) throws MaxCountExceededException;

    protected abstract AbstractFieldStepInterpolator<T> create(boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper);

    @Override // org.apache.commons.math3.ode.sampling.FieldStepInterpolator
    public FieldODEStateAndDerivative<T> getCurrentState() {
        return this.softCurrentState;
    }

    public FieldODEStateAndDerivative<T> getGlobalCurrentState() {
        return this.globalCurrentState;
    }

    public FieldODEStateAndDerivative<T> getGlobalPreviousState() {
        return this.globalPreviousState;
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepInterpolator
    public FieldODEStateAndDerivative<T> getPreviousState() {
        return this.softPreviousState;
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepInterpolator
    public boolean isForward() {
        return this.forward;
    }

    public AbstractFieldStepInterpolator<T> restrictStep(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2) {
        return create(this.forward, this.globalPreviousState, this.globalCurrentState, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, this.mapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepInterpolator
    public FieldODEStateAndDerivative<T> getInterpolatedState(T t) {
        RealFieldElement realFieldElement = (RealFieldElement) t.subtract(this.globalPreviousState.getTime());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.globalCurrentState.getTime().subtract(t);
        return computeInterpolatedStateAndDerivatives(this.mapper, t, (RealFieldElement) realFieldElement.divide((RealFieldElement) this.globalCurrentState.getTime().subtract(this.globalPreviousState.getTime())), realFieldElement, realFieldElement2);
    }
}
