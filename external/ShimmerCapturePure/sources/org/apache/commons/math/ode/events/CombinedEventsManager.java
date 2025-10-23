package org.apache.commons.math.ode.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.sampling.StepInterpolator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/events/CombinedEventsManager.class */
public class CombinedEventsManager {
    private final List<EventState> states = new ArrayList();
    private EventState first = null;
    private boolean initialized = false;

    public void addEventHandler(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        this.states.add(new EventState(handler, maxCheckInterval, convergence, maxIterationCount));
    }

    public Collection<EventHandler> getEventsHandlers() {
        List<EventHandler> list = new ArrayList<>();
        for (EventState state : this.states) {
            list.add(state.getEventHandler());
        }
        return Collections.unmodifiableCollection(list);
    }

    public void clearEventsHandlers() {
        this.states.clear();
    }

    public Collection<EventState> getEventsStates() {
        return this.states;
    }

    public boolean isEmpty() {
        return this.states.isEmpty();
    }

    public boolean evaluateStep(StepInterpolator interpolator) throws DerivativeException, IntegratorException {
        try {
            this.first = null;
            if (this.states.isEmpty()) {
                return false;
            }
            if (!this.initialized) {
                Iterator i$ = this.states.iterator();
                while (i$.hasNext()) {
                    i$.next().reinitializeBegin(interpolator);
                }
                this.initialized = true;
            }
            for (EventState state : this.states) {
                if (state.evaluateStep(interpolator)) {
                    if (this.first == null) {
                        this.first = state;
                    } else if (interpolator.isForward()) {
                        if (state.getEventTime() < this.first.getEventTime()) {
                            this.first = state;
                        }
                    } else if (state.getEventTime() > this.first.getEventTime()) {
                        this.first = state;
                    }
                }
            }
            return this.first != null;
        } catch (ConvergenceException ce) {
            throw new IntegratorException(ce);
        } catch (EventException se) {
            Throwable cause = se.getCause();
            if (cause != null && (cause instanceof DerivativeException)) {
                throw ((DerivativeException) cause);
            }
            throw new IntegratorException(se);
        }
    }

    public double getEventTime() {
        if (this.first == null) {
            return Double.NaN;
        }
        return this.first.getEventTime();
    }

    public void stepAccepted(double t, double[] y) throws IntegratorException {
        try {
            for (EventState state : this.states) {
                state.stepAccepted(t, y);
            }
        } catch (EventException se) {
            throw new IntegratorException(se);
        }
    }

    public boolean stop() {
        for (EventState state : this.states) {
            if (state.stop()) {
                return true;
            }
        }
        return false;
    }

    public boolean reset(double t, double[] y) throws IntegratorException {
        try {
            boolean resetDerivatives = false;
            for (EventState state : this.states) {
                if (state.reset(t, y)) {
                    resetDerivatives = true;
                }
            }
            return resetDerivatives;
        } catch (EventException se) {
            throw new IntegratorException(se);
        }
    }
}
