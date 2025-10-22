package org.apache.commons.math.ode;

import java.util.Collection;

import org.apache.commons.math.ode.events.EventHandler;
import org.apache.commons.math.ode.sampling.StepHandler;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/ODEIntegrator.class */
public interface ODEIntegrator {
    String getName();

    void addStepHandler(StepHandler stepHandler);

    Collection<StepHandler> getStepHandlers();

    void clearStepHandlers();

    void addEventHandler(EventHandler eventHandler, double d, double d2, int i);

    Collection<EventHandler> getEventHandlers();

    void clearEventHandlers();

    double getCurrentStepStart();

    double getCurrentSignedStepsize();

    int getMaxEvaluations();

    void setMaxEvaluations(int i);

    int getEvaluations();
}
