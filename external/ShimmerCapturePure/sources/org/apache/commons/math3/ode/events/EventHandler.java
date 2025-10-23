package org.apache.commons.math3.ode.events;

/* loaded from: classes5.dex */
public interface EventHandler {

    Action eventOccurred(double d, double[] dArr, boolean z);

    double g(double d, double[] dArr);

    void init(double d, double[] dArr, double d2);

    void resetState(double d, double[] dArr);

    public enum Action {
        STOP,
        RESET_STATE,
        RESET_DERIVATIVES,
        CONTINUE
    }
}
