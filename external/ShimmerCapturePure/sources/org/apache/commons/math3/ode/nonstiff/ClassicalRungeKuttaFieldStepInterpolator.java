package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
class ClassicalRungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    ClassicalRungeKuttaFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator
    public ClassicalRungeKuttaFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator<>(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        T[] tArrCurrentStateLinearCombination;
        T[] tArrDerivativeLinearCombination;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.subtract(t2);
        RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement.subtract((RealFieldElement) t2.multiply(2));
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement2.multiply(realFieldElement3);
        RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement2)).multiply(2);
        RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement3)).negate();
        if (getGlobalPreviousState() != null && t2.getReal() <= 0.5d) {
            RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) t2.multiply(t2)).multiply(4);
            RealFieldElement realFieldElement8 = (RealFieldElement) t3.divide(6.0d);
            RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement8.multiply((RealFieldElement) ((RealFieldElement) realFieldElement7.subtract((RealFieldElement) t2.multiply(9))).add(6.0d));
            RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement8.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(6)).subtract(realFieldElement7));
            tArrCurrentStateLinearCombination = previousStateLinearCombination(realFieldElement9, realFieldElement10, realFieldElement10, (RealFieldElement) realFieldElement8.multiply((RealFieldElement) realFieldElement7.subtract((RealFieldElement) t2.multiply(3))));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement4, realFieldElement5, realFieldElement5, realFieldElement6);
        } else {
            RealFieldElement realFieldElement11 = (RealFieldElement) t2.multiply(4);
            RealFieldElement realFieldElement12 = (RealFieldElement) t4.divide(6.0d);
            RealFieldElement realFieldElement13 = (RealFieldElement) realFieldElement12.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) realFieldElement11.negate()).add(5.0d))).subtract(1.0d));
            RealFieldElement realFieldElement14 = (RealFieldElement) realFieldElement12.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement11.subtract(2.0d))).subtract(2.0d));
            tArrCurrentStateLinearCombination = currentStateLinearCombination(realFieldElement13, realFieldElement14, realFieldElement14, (RealFieldElement) realFieldElement12.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(((RealFieldElement) realFieldElement11.negate()).subtract(1.0d))).subtract(1.0d)));
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement4, realFieldElement5, realFieldElement5, realFieldElement6);
        }
        return new FieldODEStateAndDerivative<>(t, tArrCurrentStateLinearCombination, tArrDerivativeLinearCombination);
    }
}
