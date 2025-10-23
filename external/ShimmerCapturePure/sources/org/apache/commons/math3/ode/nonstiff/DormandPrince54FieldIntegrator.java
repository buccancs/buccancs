package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class DormandPrince54FieldIntegrator<T extends RealFieldElement<T>> extends EmbeddedRungeKuttaFieldIntegrator<T> {
    private static final String METHOD_NAME = "Dormand-Prince 5(4)";
    private final T e1;
    private final T e3;
    private final T e4;
    private final T e5;
    private final T e6;
    private final T e7;

    public DormandPrince54FieldIntegrator(Field<T> field, double d, double d2, double d3, double d4) {
        super(field, METHOD_NAME, 6, d, d2, d3, d4);
        this.e1 = fraction(71, 57600);
        this.e3 = fraction(-71, 16695);
        this.e4 = fraction(71, 1920);
        this.e5 = fraction(-17253, 339200);
        this.e6 = fraction(22, 525);
        this.e7 = fraction(-1, 40);
    }

    public DormandPrince54FieldIntegrator(Field<T> field, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, METHOD_NAME, 6, d, d2, dArr, dArr2);
        this.e1 = fraction(71, 57600);
        this.e3 = fraction(-71, 16695);
        this.e4 = fraction(71, 1920);
        this.e5 = fraction(-17253, 339200);
        this.e6 = fraction(22, 525);
        this.e7 = fraction(-1, 40);
    }

    @Override // org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator
    public int getOrder() {
        return 5;
    }

    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[] getC() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), 6));
        tArr[0] = fraction(1, 5);
        tArr[1] = fraction(3, 10);
        tArr[2] = fraction(4, 5);
        tArr[3] = fraction(8, 9);
        tArr[4] = getField().getOne();
        tArr[5] = getField().getOne();
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[][] getA() {
        T[][] tArr = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(getField(), 6, -1));
        int i = 0;
        while (i < tArr.length) {
            int i2 = i + 1;
            tArr[i] = (RealFieldElement[]) MathArrays.buildArray(getField(), i2);
            i = i2;
        }
        tArr[0][0] = fraction(1, 5);
        tArr[1][0] = fraction(3, 40);
        tArr[1][1] = fraction(9, 40);
        tArr[2][0] = fraction(44, 45);
        tArr[2][1] = fraction(-56, 15);
        tArr[2][2] = fraction(32, 9);
        tArr[3][0] = fraction(19372, 6561);
        tArr[3][1] = fraction(-25360, 2187);
        tArr[3][2] = fraction(64448, 6561);
        tArr[3][3] = fraction(-212, 729);
        tArr[4][0] = fraction(9017, 3168);
        tArr[4][1] = fraction(-355, 33);
        tArr[4][2] = fraction(46732, 5247);
        tArr[4][3] = fraction(49, CipherSuite.TLS_PSK_WITH_NULL_SHA256);
        tArr[4][4] = fraction(-5103, 18656);
        tArr[5][0] = fraction(35, 384);
        tArr[5][1] = getField().getZero();
        tArr[5][2] = fraction(500, 1113);
        tArr[5][3] = fraction(125, 192);
        tArr[5][4] = fraction(-2187, 6784);
        tArr[5][5] = fraction(11, 84);
        return tArr;
    }

    @Override // org.apache.commons.math3.ode.nonstiff.FieldButcherArrayProvider
    public T[] getB() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), 7));
        tArr[0] = fraction(35, 384);
        tArr[1] = getField().getZero();
        tArr[2] = fraction(500, 1113);
        tArr[3] = fraction(125, 192);
        tArr[4] = fraction(-2187, 6784);
        tArr[5] = fraction(11, 84);
        tArr[6] = getField().getZero();
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator
    public DormandPrince54FieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince54FieldStepInterpolator<>(getField(), z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldEquationsMapper);
    }

    @Override // org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator
    protected T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t) {
        T zero = getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][i].multiply(this.e1)).add((RealFieldElement) tArr[2][i].multiply(this.e3))).add((RealFieldElement) tArr[3][i].multiply(this.e4))).add((RealFieldElement) tArr[4][i].multiply(this.e5))).add((RealFieldElement) tArr[5][i].multiply(this.e6))).add((RealFieldElement) tArr[6][i].multiply(this.e7));
            RealFieldElement realFieldElementMax = MathUtils.max((RealFieldElement) tArr2[i].abs(), (RealFieldElement) tArr3[i].abs());
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) t.multiply(realFieldElement)).divide((RealFieldElement) (this.vecAbsoluteTolerance == null ? ((RealFieldElement) realFieldElementMax.multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance) : ((RealFieldElement) realFieldElementMax.multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i])));
            zero = (T) zero.add(realFieldElement2.multiply(realFieldElement2));
        }
        return (T) ((RealFieldElement) zero.divide(this.mainSetDimension)).sqrt();
    }
}
