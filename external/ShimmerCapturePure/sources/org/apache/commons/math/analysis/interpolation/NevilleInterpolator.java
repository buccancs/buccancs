package org.apache.commons.math.analysis.interpolation;

import java.io.Serializable;

import org.apache.commons.math.MathException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/analysis/interpolation/NevilleInterpolator.class */
public class NevilleInterpolator implements UnivariateRealInterpolator, Serializable {
    static final long serialVersionUID = 3003707660147873733L;

    @Override // org.apache.commons.math.analysis.interpolation.UnivariateRealInterpolator
    public PolynomialFunctionLagrangeForm interpolate(double[] x, double[] y) throws MathException {
        return new PolynomialFunctionLagrangeForm(x, y);
    }
}
