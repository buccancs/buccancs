package org.apache.commons.math.ode.events;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/events/EventHandler.class */
public interface EventHandler {
    public static final int STOP = 0;
    public static final int RESET_STATE = 1;
    public static final int RESET_DERIVATIVES = 2;
    public static final int CONTINUE = 3;

    double g(double d, double[] dArr) throws EventException;

    int eventOccurred(double d, double[] dArr, boolean z) throws EventException;

    void resetState(double d, double[] dArr) throws EventException;
}
