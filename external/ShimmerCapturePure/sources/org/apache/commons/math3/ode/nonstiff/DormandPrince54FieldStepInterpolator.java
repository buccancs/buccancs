package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
class DormandPrince54FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    private final T a70;
    private final T a72;
    private final T a73;
    private final T a74;
    private final T a75;
    private final T d0;
    private final T d2;
    private final T d3;
    private final T d4;
    private final T d5;
    private final T d6;

    DormandPrince54FieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        T one = field.getOne();
        this.a70 = (T) ((RealFieldElement) one.multiply(35.0d)).divide(384.0d);
        this.a72 = (T) ((RealFieldElement) one.multiply(500.0d)).divide(1113.0d);
        this.a73 = (T) ((RealFieldElement) one.multiply(125.0d)).divide(192.0d);
        this.a74 = (T) ((RealFieldElement) one.multiply(-2187.0d)).divide(6784.0d);
        this.a75 = (T) ((RealFieldElement) one.multiply(11.0d)).divide(84.0d);
        this.d0 = (T) ((RealFieldElement) one.multiply(-1.2715105075E10d)).divide(1.1282082432E10d);
        this.d2 = (T) ((RealFieldElement) one.multiply(8.74874797E10d)).divide(3.2700410799E10d);
        this.d3 = (T) ((RealFieldElement) one.multiply(-1.0690763975E10d)).divide(1.880347072E9d);
        this.d4 = (T) ((RealFieldElement) one.multiply(7.01980252875E11d)).divide(1.99316789632E11d);
        this.d5 = (T) ((RealFieldElement) one.multiply(-1.453857185E9d)).divide(8.22651844E8d);
        this.d6 = (T) ((RealFieldElement) one.multiply(6.9997945E7d)).divide(2.9380423E7d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator
    public DormandPrince54FieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince54FieldStepInterpolator<>(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) {
        T[] tArrCurrentStateLinearCombination;
        T[] tArrDerivativeLinearCombination;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.subtract(t2);
        RealFieldElement realFieldElement3 = (RealFieldElement) t2.multiply(2);
        RealFieldElement realFieldElement4 = (RealFieldElement) realFieldElement.subtract(realFieldElement3);
        RealFieldElement realFieldElement5 = (RealFieldElement) t2.multiply(((RealFieldElement) t2.multiply(-3)).add(2.0d));
        RealFieldElement realFieldElement6 = (RealFieldElement) realFieldElement3.multiply((RealFieldElement) ((RealFieldElement) t2.multiply(realFieldElement3.subtract(3.0d))).add(1.0d));
        if (getGlobalPreviousState() != null && t2.getReal() <= 0.5d) {
            RealFieldElement realFieldElement7 = (RealFieldElement) t3.multiply(realFieldElement2);
            RealFieldElement realFieldElement8 = (RealFieldElement) realFieldElement7.multiply(t2);
            RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement8.multiply(realFieldElement2);
            RealFieldElement realFieldElement10 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(this.a70)).subtract((RealFieldElement) realFieldElement7.multiply((RealFieldElement) this.a70.subtract(1.0d)))).add((RealFieldElement) realFieldElement8.multiply((RealFieldElement) ((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add((RealFieldElement) realFieldElement9.multiply(this.d0));
            RealFieldElement realFieldElement11 = (RealFieldElement) t.getField().getZero();
            RealFieldElement realFieldElement12 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(this.a72)).subtract((RealFieldElement) realFieldElement7.multiply(this.a72))).add((RealFieldElement) realFieldElement8.multiply((RealFieldElement) this.a72.multiply(2)))).add((RealFieldElement) realFieldElement9.multiply(this.d2));
            RealFieldElement realFieldElement13 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(this.a73)).subtract((RealFieldElement) realFieldElement7.multiply(this.a73))).add((RealFieldElement) realFieldElement8.multiply((RealFieldElement) this.a73.multiply(2)))).add((RealFieldElement) realFieldElement9.multiply(this.d3));
            RealFieldElement realFieldElement14 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(this.a74)).subtract((RealFieldElement) realFieldElement7.multiply(this.a74))).add((RealFieldElement) realFieldElement8.multiply((RealFieldElement) this.a74.multiply(2)))).add((RealFieldElement) realFieldElement9.multiply(this.d4));
            RealFieldElement realFieldElement15 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t3.multiply(this.a75)).subtract((RealFieldElement) realFieldElement7.multiply(this.a75))).add((RealFieldElement) realFieldElement8.multiply((RealFieldElement) this.a75.multiply(2)))).add((RealFieldElement) realFieldElement9.multiply(this.d5));
            RealFieldElement realFieldElement16 = (RealFieldElement) ((RealFieldElement) realFieldElement9.multiply(this.d6)).subtract(realFieldElement8);
            T t5 = this.a70;
            RealFieldElement realFieldElement17 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t5.subtract(realFieldElement4.multiply((RealFieldElement) t5.subtract(1.0d)))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) ((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add((RealFieldElement) realFieldElement6.multiply(this.d0));
            RealFieldElement realFieldElement18 = (RealFieldElement) t.getField().getZero();
            T t6 = this.a72;
            RealFieldElement realFieldElement19 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.subtract(realFieldElement4.multiply(t6))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a72.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d2));
            T t7 = this.a73;
            RealFieldElement realFieldElement20 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t7.subtract(realFieldElement4.multiply(t7))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a73.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d3));
            T t8 = this.a74;
            RealFieldElement realFieldElement21 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t8.subtract(realFieldElement4.multiply(t8))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a74.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d4));
            T t9 = this.a75;
            RealFieldElement realFieldElement22 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t9.subtract(realFieldElement4.multiply(t9))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a75.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d5));
            RealFieldElement realFieldElement23 = (RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(this.d6)).subtract(realFieldElement5);
            tArrCurrentStateLinearCombination = previousStateLinearCombination(realFieldElement10, realFieldElement11, realFieldElement12, realFieldElement13, realFieldElement14, realFieldElement15, realFieldElement16);
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement17, realFieldElement18, realFieldElement19, realFieldElement20, realFieldElement21, realFieldElement22, realFieldElement23);
        } else {
            RealFieldElement realFieldElement24 = (RealFieldElement) t4.negate();
            RealFieldElement realFieldElement25 = (RealFieldElement) t4.multiply(t2);
            RealFieldElement realFieldElement26 = (RealFieldElement) realFieldElement25.multiply(t2);
            RealFieldElement realFieldElement27 = (RealFieldElement) realFieldElement26.multiply(realFieldElement2);
            RealFieldElement realFieldElement28 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement24.multiply(this.a70)).subtract((RealFieldElement) realFieldElement25.multiply((RealFieldElement) this.a70.subtract(1.0d)))).add((RealFieldElement) realFieldElement26.multiply((RealFieldElement) ((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add((RealFieldElement) realFieldElement27.multiply(this.d0));
            RealFieldElement realFieldElement29 = (RealFieldElement) t.getField().getZero();
            RealFieldElement realFieldElement30 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement24.multiply(this.a72)).subtract((RealFieldElement) realFieldElement25.multiply(this.a72))).add((RealFieldElement) realFieldElement26.multiply((RealFieldElement) this.a72.multiply(2)))).add((RealFieldElement) realFieldElement27.multiply(this.d2));
            RealFieldElement realFieldElement31 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement24.multiply(this.a73)).subtract((RealFieldElement) realFieldElement25.multiply(this.a73))).add((RealFieldElement) realFieldElement26.multiply((RealFieldElement) this.a73.multiply(2)))).add((RealFieldElement) realFieldElement27.multiply(this.d3));
            RealFieldElement realFieldElement32 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement24.multiply(this.a74)).subtract((RealFieldElement) realFieldElement25.multiply(this.a74))).add((RealFieldElement) realFieldElement26.multiply((RealFieldElement) this.a74.multiply(2)))).add((RealFieldElement) realFieldElement27.multiply(this.d4));
            RealFieldElement realFieldElement33 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement24.multiply(this.a75)).subtract((RealFieldElement) realFieldElement25.multiply(this.a75))).add((RealFieldElement) realFieldElement26.multiply((RealFieldElement) this.a75.multiply(2)))).add((RealFieldElement) realFieldElement27.multiply(this.d5));
            RealFieldElement realFieldElement34 = (RealFieldElement) ((RealFieldElement) realFieldElement27.multiply(this.d6)).subtract(realFieldElement26);
            T t10 = this.a70;
            RealFieldElement realFieldElement35 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t10.subtract(realFieldElement4.multiply((RealFieldElement) t10.subtract(1.0d)))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) ((RealFieldElement) this.a70.multiply(2)).subtract(1.0d)))).add((RealFieldElement) realFieldElement6.multiply(this.d0));
            RealFieldElement realFieldElement36 = (RealFieldElement) t.getField().getZero();
            T t11 = this.a72;
            RealFieldElement realFieldElement37 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t11.subtract(realFieldElement4.multiply(t11))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a72.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d2));
            T t12 = this.a73;
            RealFieldElement realFieldElement38 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t12.subtract(realFieldElement4.multiply(t12))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a73.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d3));
            T t13 = this.a74;
            RealFieldElement realFieldElement39 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t13.subtract(realFieldElement4.multiply(t13))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a74.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d4));
            T t14 = this.a75;
            RealFieldElement realFieldElement40 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) t14.subtract(realFieldElement4.multiply(t14))).add((RealFieldElement) realFieldElement5.multiply((RealFieldElement) this.a75.multiply(2)))).add((RealFieldElement) realFieldElement6.multiply(this.d5));
            RealFieldElement realFieldElement41 = (RealFieldElement) ((RealFieldElement) realFieldElement6.multiply(this.d6)).subtract(realFieldElement5);
            tArrCurrentStateLinearCombination = currentStateLinearCombination(realFieldElement28, realFieldElement29, realFieldElement30, realFieldElement31, realFieldElement32, realFieldElement33, realFieldElement34);
            tArrDerivativeLinearCombination = derivativeLinearCombination(realFieldElement35, realFieldElement36, realFieldElement37, realFieldElement38, realFieldElement39, realFieldElement40, realFieldElement41);
        }
        return new FieldODEStateAndDerivative<>(t, tArrCurrentStateLinearCombination, tArrDerivativeLinearCombination);
    }
}
