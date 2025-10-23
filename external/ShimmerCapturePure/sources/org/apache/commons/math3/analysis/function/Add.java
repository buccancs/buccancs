package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.BivariateFunction;

/* loaded from: classes5.dex */
public class Add implements BivariateFunction {
    @Override // org.apache.commons.math3.analysis.BivariateFunction
    public double value(double d, double d2) {
        return d + d2;
    }
}
