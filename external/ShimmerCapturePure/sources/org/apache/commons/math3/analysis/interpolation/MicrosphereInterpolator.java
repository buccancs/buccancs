package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;

@Deprecated
/* loaded from: classes5.dex */
public class MicrosphereInterpolator implements MultivariateInterpolator {
    public static final int DEFAULT_BRIGHTNESS_EXPONENT = 2;
    public static final int DEFAULT_MICROSPHERE_ELEMENTS = 2000;
    private final int brightnessExponent;
    private final int microsphereElements;

    public MicrosphereInterpolator() {
        this(2000, 2);
    }

    public MicrosphereInterpolator(int i, int i2) throws NotStrictlyPositiveException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(Integer.valueOf(i2));
        }
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.microsphereElements = i;
        this.brightnessExponent = i2;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.MultivariateInterpolator
    public MultivariateFunction interpolate(double[][] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        return new MicrosphereInterpolatingFunction(dArr, dArr2, this.brightnessExponent, this.microsphereElements, new UnitSphereRandomVectorGenerator(dArr[0].length));
    }
}
