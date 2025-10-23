package org.apache.commons.math3.ode.sampling;

/* loaded from: classes5.dex */
public class DummyStepHandler implements StepHandler {
    private DummyStepHandler() {
    }

    public static DummyStepHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void handleStep(StepInterpolator stepInterpolator, boolean z) {
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void init(double d, double[] dArr, double d2) {
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DummyStepHandler INSTANCE = new DummyStepHandler();

        private LazyHolder() {
        }
    }
}
