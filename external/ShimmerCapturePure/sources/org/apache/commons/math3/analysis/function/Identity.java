package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

/* loaded from: classes5.dex */
public class Identity implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        return d;
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return derivativeStructure;
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Constant(1.0d);
    }
}
