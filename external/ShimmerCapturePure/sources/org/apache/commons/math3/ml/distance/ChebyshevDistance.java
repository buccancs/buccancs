package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class ChebyshevDistance implements DistanceMeasure {
    private static final long serialVersionUID = -4694868171115238296L;

    @Override // org.apache.commons.math3.ml.distance.DistanceMeasure
    public double compute(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        return MathArrays.distanceInf(dArr, dArr2);
    }
}
