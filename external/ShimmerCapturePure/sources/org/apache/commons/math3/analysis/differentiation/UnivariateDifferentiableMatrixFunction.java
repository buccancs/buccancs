package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateMatrixFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface UnivariateDifferentiableMatrixFunction extends UnivariateMatrixFunction {
    DerivativeStructure[][] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException;
}
