package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class Signum implements UnivariateFunction {
    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double d) {
        return FastMath.signum(d);
    }
}
