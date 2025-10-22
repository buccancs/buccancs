package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public interface SecondaryEquations {
    void computeDerivatives(double d, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) throws MaxCountExceededException, DimensionMismatchException;

    int getDimension();
}
