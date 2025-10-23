package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
class GillFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    private final T one_minus_inv_sqrt_2;
    private final T one_plus_inv_sqrt_2;

    GillFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) field.getZero().add(0.5d)).sqrt();
        this.one_minus_inv_sqrt_2 = (T) field.getOne().subtract(realFieldElement);
        this.one_plus_inv_sqrt_2 = (T) field.getOne().add(realFieldElement);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator
    public GillFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new GillFieldStepInterpolator<>(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        T[] tArrCurrentStateLinearCombination;
        T[] tArrDerivativeLinearCombination;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) t2.multiply(2);
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement2.multiply(realFieldElement2);
        RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement2.subtract(3.0d))).add(1.0d);
        RealFieldElement realFieldElement5 = (RealFieldElement) realFieldElement2.multiply((RealFieldElement) realFieldElement.subtract(t2));
        RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement5.multiply(this.one_minus_inv_sqrt_2);
        RealFieldElement realFieldElement7 = (RealFieldElement) realFieldElement5.multiply(this.one_plus_inv_sqrt_2);
        RealFieldElement realFieldElement8 = (RealFieldElement) t2.multiply(realFieldElement2.subtract(1.0d));
        if (getGlobalPreviousState() != null && t2.getReal() <= 0.5d) {
            RealFieldElement realFieldElement9 = (RealFieldElement) t3.divide(6.0d);
            RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement9.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(6)).subtract(realFieldElement3));
            tArrCurrentStateLinearCombination = previousStateLinearCombination((RealFieldElement) realFieldElement9.multiply((RealFieldElement) ((RealFieldElement) realFieldElement3.subtract((RealFieldElement) t2.multiply(9))).add(6.0d)), (RealFieldElement) realFieldElement10.multiply(this.one_minus_inv_sqrt_2), (RealFieldElement) realFieldElement10.multiply(this.one_plus_inv_sqrt_2), (RealFieldElement) realFieldElement9.multiply((RealFieldElement) realFieldElement3.subtract((RealFieldElement) t2.multiply(3))));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement4, realFieldElement6, realFieldElement7, realFieldElement8);
        } else {
            RealFieldElement realFieldElement11 = (RealFieldElement) t4.divide(-6.0d);
            RealFieldElement realFieldElement12 = (RealFieldElement) realFieldElement11.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.add(2.0d)).subtract(realFieldElement3));
            tArrCurrentStateLinearCombination = currentStateLinearCombination((RealFieldElement) realFieldElement11.multiply((RealFieldElement) ((RealFieldElement) realFieldElement3.subtract((RealFieldElement) t2.multiply(5))).add(1.0d)), (RealFieldElement) realFieldElement12.multiply(this.one_minus_inv_sqrt_2), (RealFieldElement) realFieldElement12.multiply(this.one_plus_inv_sqrt_2), (RealFieldElement) realFieldElement11.multiply((RealFieldElement) ((RealFieldElement) realFieldElement3.add(t2)).add(1.0d)));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement4, realFieldElement6, realFieldElement7, realFieldElement8);
        }
        return new FieldODEStateAndDerivative<>(t, tArrCurrentStateLinearCombination, tArrDerivativeLinearCombination);
    }
}
