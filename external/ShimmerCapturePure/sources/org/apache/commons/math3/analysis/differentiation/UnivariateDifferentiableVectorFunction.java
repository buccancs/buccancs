package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface UnivariateDifferentiableVectorFunction extends UnivariateVectorFunction {
    DerivativeStructure[] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException;
}
