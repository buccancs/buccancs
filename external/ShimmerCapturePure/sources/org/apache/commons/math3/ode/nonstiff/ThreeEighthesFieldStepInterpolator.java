package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
class ThreeEighthesFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    ThreeEighthesFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator
    public ThreeEighthesFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ThreeEighthesFieldStepInterpolator<>(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        T[] tArrDerivativeLinearCombination;
        T[] tArrPreviousStateLinearCombination;
        RealFieldElement realFieldElement = (RealFieldElement) t2.multiply(0.75d);
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(4)).subtract(5.0d))).add(1.0d);
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(-6)).add(5.0d));
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(2)).subtract(1.0d));
        if (getGlobalPreviousState() != null && t2.getReal() <= 0.5d) {
            RealFieldElement realFieldElement5 = (RealFieldElement) t3.divide(8.0d);
            RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t2.multiply(t2)).multiply(4);
            tArrPreviousStateLinearCombination = previousStateLinearCombination((RealFieldElement) realFieldElement5.multiply((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(2)).subtract((RealFieldElement) t2.multiply(15))).add(8.0d)), (RealFieldElement) ((RealFieldElement) realFieldElement5.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(5)).subtract(realFieldElement6))).multiply(3), (RealFieldElement) ((RealFieldElement) realFieldElement5.multiply(t2)).multiply(3), (RealFieldElement) realFieldElement5.multiply((RealFieldElement) realFieldElement6.subtract((RealFieldElement) t2.multiply(3))));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement2, realFieldElement3, realFieldElement, realFieldElement4);
        } else {
            RealFieldElement realFieldElement7 = (RealFieldElement) t4.divide(-8.0d);
            RealFieldElement realFieldElement8 = (RealFieldElement) ((RealFieldElement) t2.multiply(t2)).multiply(4);
            RealFieldElement realFieldElement9 = (RealFieldElement) t2.add(1.0d);
            T[] tArrCurrentStateLinearCombination = currentStateLinearCombination((RealFieldElement) realFieldElement7.multiply((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement8.multiply(2)).subtract((RealFieldElement) t2.multiply(7))).add(1.0d)), (RealFieldElement) ((RealFieldElement) realFieldElement7.multiply((RealFieldElement) realFieldElement9.subtract(realFieldElement8))).multiply(3), (RealFieldElement) ((RealFieldElement) realFieldElement7.multiply(realFieldElement9)).multiply(3), (RealFieldElement) realFieldElement7.multiply((RealFieldElement) realFieldElement9.add(realFieldElement8)));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement2, realFieldElement3, realFieldElement, realFieldElement4);
            tArrPreviousStateLinearCombination = tArrCurrentStateLinearCombination;
        }
        return new FieldODEStateAndDerivative<>(t, tArrPreviousStateLinearCombination, tArrDerivativeLinearCombination);
    }
}
