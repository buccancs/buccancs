package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class Power implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double p;

    public Power(double d) {
        this.p = d;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        return FastMath.pow(d, this.p);
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return derivativeStructure.pow(this.p);
    }
}
