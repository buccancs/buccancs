package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class ClassicalRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {
    public ClassicalRungeKuttaFieldIntegrator(Field<T> field, T t) {
        super(field, "classical Runge-Kutta", t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[] getC() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), 3));
        RealFieldElement realFieldElement = (RealFieldElement) getField().getOne().multiply(0.5d);
        tArr[0] = realFieldElement;
        tArr[1] = realFieldElement;
        tArr[2] = getField().getOne();
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[][] getA() {
        T[][] tArr = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(getField(), 3, -1));
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = fraction(1, 2);
        tArr[1][0] = getField().getZero();
        tArr[1][1] = tArr[0][0];
        tArr[2][0] = getField().getZero();
        tArr[2][1] = getField().getZero();
        tArr[2][2] = getField().getOne();
        return tArr;
    }

    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[] getB() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), 4));
        tArr[0] = fraction(1, 6);
        T tFraction = fraction(1, 3);
        tArr[1] = tFraction;
        tArr[2] = tFraction;
        tArr[3] = tArr[0];
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator
    public ClassicalRungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator<>(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }
}
