package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface UnivariateInterpolator {
    UnivariateFunction interpolate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException;
}
