package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/* loaded from: classes5.dex */
public abstract class EmbeddedRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a;
    private final T[] b;
    private final T[] c;
    private final T exp;
    private final int fsal;
    private T maxGrowth;
    private T minReduction;
    private T safety;

    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double d3, double d4) {
        super(field, str, d, d2, d3, d4);
        this.fsal = i;
        this.c = getC();
        this.a = getA();
        this.b = getB();
        this.exp = (T) field.getOne().divide(-getOrder());
        setSafety((RealFieldElement) field.getZero().add(0.9d));
        setMinReduction((RealFieldElement) field.getZero().add(0.2d));
        setMaxGrowth((RealFieldElement) field.getZero().add(10.0d));
    }

    protected EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, str, d, d2, dArr, dArr2);
        this.fsal = i;
        this.c = getC();
        this.a = getA();
        this.b = getB();
        this.exp = (T) field.getOne().divide(-getOrder());
        setSafety((RealFieldElement) field.getZero().add(0.9d));
        setMinReduction((RealFieldElement) field.getZero().add(0.2d));
        setMaxGrowth((RealFieldElement) field.getZero().add(10.0d));
    }

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    protected abstract T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t);

    public T getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(T t) {
        this.maxGrowth = t;
    }

    public T getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(T t) {
        this.minReduction = t;
    }

    public abstract int getOrder();

    public T getSafety() {
        return this.safety;
    }

    public void setSafety(T t) {
        this.safety = t;
    }

    protected T fraction(int i, int i2) {
        return (T) ((RealFieldElement) getField().getOne().multiply(i)).divide(i2);
    }

    protected T fraction(double d, double d2) {
        return (T) ((RealFieldElement) getField().getOne().multiply(d)).divide(d2);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0361 A[PHI: r3
  0x0361: PHI (r3v15 double) = (r3v14 double), (r3v16 double) binds: [B:74:0x035f, B:71:0x034e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> integrate(org.apache.commons.math3.ode.FieldExpandableODE<T> r23, org.apache.commons.math3.ode.FieldODEState<T> r24, T r25) throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.MathIllegalArgumentException {
        /*
            Method dump skipped, instructions count: 912
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate(org.apache.commons.math3.ode.FieldExpandableODE, org.apache.commons.math3.ode.FieldODEState, org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }
}
