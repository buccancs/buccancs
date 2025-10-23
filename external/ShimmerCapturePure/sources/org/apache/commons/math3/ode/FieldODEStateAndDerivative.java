package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;

/* loaded from: classes5.dex */
public class FieldODEStateAndDerivative<T extends RealFieldElement<T>> extends FieldODEState<T> {
    private final T[] derivative;
    private final T[][] secondaryDerivative;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FieldODEStateAndDerivative(T t, T[] tArr, T[] tArr2) {
        this(t, tArr, tArr2, null, null);
    }

    public FieldODEStateAndDerivative(T t, T[] tArr, T[] tArr2, T[][] tArr3, T[][] tArr4) {
        super(t, tArr, tArr3);
        this.derivative = (T[]) ((RealFieldElement[]) tArr2.clone());
        this.secondaryDerivative = copy(t.getField(), tArr4);
    }

    public T[] getDerivative() {
        return (T[]) ((RealFieldElement[]) this.derivative.clone());
    }

    public T[] getSecondaryDerivative(int i) {
        return (T[]) ((RealFieldElement[]) (i == 0 ? this.derivative.clone() : this.secondaryDerivative[i - 1].clone()));
    }
}
