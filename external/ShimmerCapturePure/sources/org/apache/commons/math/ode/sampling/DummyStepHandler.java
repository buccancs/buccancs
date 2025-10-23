package org.apache.commons.math.ode.sampling;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/DummyStepHandler.class */
public class DummyStepHandler implements StepHandler {
    private DummyStepHandler() {
    }

    public static DummyStepHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public boolean requiresDenseOutput() {
        return false;
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public void reset() {
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public void handleStep(StepInterpolator interpolator, boolean isLast) {
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/DummyStepHandler$LazyHolder.class */
    private static class LazyHolder {
        private static final DummyStepHandler INSTANCE = new DummyStepHandler();

        private LazyHolder() {
        }
    }
}
