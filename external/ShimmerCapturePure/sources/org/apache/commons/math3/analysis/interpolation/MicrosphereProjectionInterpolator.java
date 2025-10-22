package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;

/* loaded from: classes5.dex */
public class MicrosphereProjectionInterpolator implements MultivariateInterpolator {
    private final double exponent;
    private final InterpolatingMicrosphere microsphere;
    private final double noInterpolationTolerance;
    private final boolean sharedSphere;

    public MicrosphereProjectionInterpolator(int i, int i2, double d, double d2, double d3, double d4, boolean z, double d5) {
        this(new InterpolatingMicrosphere(i, i2, d, d2, d3, new UnitSphereRandomVectorGenerator(i)), d4, z, d5);
    }

    public MicrosphereProjectionInterpolator(InterpolatingMicrosphere interpolatingMicrosphere, double d, boolean z, double d2) throws NotPositiveException {
        if (d < 0.0d) {
            throw new NotPositiveException(Double.valueOf(d));
        }
        this.microsphere = interpolatingMicrosphere;
        this.exponent = d;
        this.sharedSphere = z;
        this.noInterpolationTolerance = d2;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.MultivariateInterpolator
    public MultivariateFunction interpolate(final double[][] dArr, final double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        }
        if (dArr.length == 0) {
            throw new NoDataException();
        }
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        if (dArr[0] == null) {
            throw new NullArgumentException();
        }
        int dimension = this.microsphere.getDimension();
        if (dimension != dArr[0].length) {
            throw new DimensionMismatchException(dArr[0].length, dimension);
        }
        final InterpolatingMicrosphere interpolatingMicrosphereCopy = this.sharedSphere ? this.microsphere : this.microsphere.copy();
        return new MultivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.1
            @Override // org.apache.commons.math3.analysis.MultivariateFunction
            public double value(double[] dArr3) {
                return interpolatingMicrosphereCopy.value(dArr3, dArr, dArr2, MicrosphereProjectionInterpolator.this.exponent, MicrosphereProjectionInterpolator.this.noInterpolationTolerance);
            }
        };
    }
}
