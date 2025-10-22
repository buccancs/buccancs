package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public interface ParameterJacobianProvider extends Parameterizable {
    void computeParameterJacobian(double d, double[] dArr, double[] dArr2, String str, double[] dArr3) throws UnknownParameterException, DimensionMismatchException, MaxCountExceededException;
}
