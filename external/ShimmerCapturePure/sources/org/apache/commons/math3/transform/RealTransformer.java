package org.apache.commons.math3.transform;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public interface RealTransformer {
    double[] transform(UnivariateFunction univariateFunction, double d, double d2, int i, TransformType transformType) throws MathIllegalArgumentException;

    double[] transform(double[] dArr, TransformType transformType) throws MathIllegalArgumentException;
}
