package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes5.dex */
public class DefaultMeasurementModel implements MeasurementModel {
    private RealMatrix measurementMatrix;
    private RealMatrix measurementNoise;

    public DefaultMeasurementModel(double[][] dArr, double[][] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        this(new Array2DRowRealMatrix(dArr), new Array2DRowRealMatrix(dArr2));
    }

    public DefaultMeasurementModel(RealMatrix realMatrix, RealMatrix realMatrix2) {
        this.measurementMatrix = realMatrix;
        this.measurementNoise = realMatrix2;
    }

    @Override // org.apache.commons.math3.filter.MeasurementModel
    public RealMatrix getMeasurementMatrix() {
        return this.measurementMatrix;
    }

    @Override // org.apache.commons.math3.filter.MeasurementModel
    public RealMatrix getMeasurementNoise() {
        return this.measurementNoise;
    }
}
