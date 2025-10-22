package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes5.dex */
public class EuclideanDistance implements DistanceMeasure {
    private static final long serialVersionUID = 1717556319784040040L;

    @Override // org.apache.commons.math3.ml.distance.DistanceMeasure
    public double compute(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        return MathArrays.distance(dArr, dArr2);
    }
}
