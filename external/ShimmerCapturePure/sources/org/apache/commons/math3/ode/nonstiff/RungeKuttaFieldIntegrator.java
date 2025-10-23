package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public abstract class RungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a;
    private final T[] b;
    private final T[] c;
    private final T step;

    protected RungeKuttaFieldIntegrator(Field<T> field, String str, T t) {
        super(field, str);
        this.c = getC();
        this.a = getA();
        this.b = getB();
        this.step = (T) t.abs();
    }

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    protected T fraction(int i, int i2) {
        return (T) ((RealFieldElement) getField().getZero().add(i)).divide(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x020e A[PHI: r2
  0x020e: PHI (r2v11 long) = (r2v10 long), (r2v12 long) binds: [B:42:0x020c, B:39:0x01fb] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r20, org.apache.commons.math3.ode.FieldODEState<T> r21, T r22) throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.MathIllegalArgumentException {
        /*
            Method dump skipped, instructions count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] singleStep(FirstOrderFieldDifferentialEquations<T> firstOrderFieldDifferentialEquations, T t, T[] tArr, T t2) {
        T[] tArr2 = (T[]) ((RealFieldElement[]) tArr.clone());
        int length = this.c.length + 1;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), length, -1);
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) tArr.clone();
        RealFieldElement realFieldElement = (RealFieldElement) t2.subtract(t);
        char c = 0;
        realFieldElementArr[0] = firstOrderFieldDifferentialEquations.computeDerivatives(t, tArr2);
        int i = 1;
        while (i < length) {
            int i2 = 0;
            while (i2 < tArr.length) {
                int i3 = i - 1;
                RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElementArr[c][i2].multiply(this.a[i3][c]);
                for (int i4 = 1; i4 < i; i4++) {
                    realFieldElement2 = (RealFieldElement) realFieldElement2.add((RealFieldElement) realFieldElementArr[i4][i2].multiply(this.a[i3][i4]));
                }
                realFieldElementArr2[i2] = (RealFieldElement) tArr2[i2].add((Decimal64) realFieldElement.multiply(realFieldElement2));
                i2++;
                c = 0;
            }
            realFieldElementArr[i] = firstOrderFieldDifferentialEquations.computeDerivatives((RealFieldElement) t.add(realFieldElement.multiply(this.c[i - 1])), realFieldElementArr2);
            i++;
            c = 0;
        }
        for (int i5 = 0; i5 < tArr.length; i5++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElementArr[0][i5].multiply(this.b[0]);
            for (int i6 = 1; i6 < length; i6++) {
                realFieldElement3 = (RealFieldElement) realFieldElement3.add((RealFieldElement) realFieldElementArr[i6][i5].multiply(this.b[i6]));
            }
            tArr2[i5] = (RealFieldElement) tArr2[i5].add((Decimal64) realFieldElement.multiply(realFieldElement3));
        }
        return tArr2;
    }
}
