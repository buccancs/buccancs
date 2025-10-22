package org.apache.commons.math3.ode.sampling;

/* loaded from: classes5.dex */
public interface FixedStepHandler {
    void handleStep(double d, double[] dArr, double[] dArr2, boolean z);

    void init(double d, double[] dArr, double d2);
}
