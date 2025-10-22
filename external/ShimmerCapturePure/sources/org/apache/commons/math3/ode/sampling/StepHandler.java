package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.exception.MaxCountExceededException;

/* loaded from: classes5.dex */
public interface StepHandler {
    void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException;

    void init(double d, double[] dArr, double d2);
}
