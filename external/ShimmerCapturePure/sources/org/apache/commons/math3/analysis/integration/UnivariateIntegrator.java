package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public interface UnivariateIntegrator {
    double getAbsoluteAccuracy();

    int getEvaluations();

    int getIterations();

    int getMaximalIterationCount();

    int getMinimalIterationCount();

    double getRelativeAccuracy();

    double integrate(int i, UnivariateFunction univariateFunction, double d, double d2) throws MaxCountExceededException, MathIllegalArgumentException;
}
