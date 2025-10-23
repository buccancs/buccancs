package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;

/* loaded from: classes5.dex */
public interface UnivariateDifferentiableFunction extends UnivariateFunction {
    DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException;
}
