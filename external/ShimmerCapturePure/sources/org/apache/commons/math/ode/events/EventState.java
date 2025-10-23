package org.apache.commons.math.ode.events;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.solvers.BrentSolver;
import org.apache.commons.math.exception.MathInternalError;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.sampling.StepInterpolator;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/events/EventState.class */
public class EventState {
    private final EventHandler handler;
    private final double maxCheckInterval;
    private final double convergence;
    private final int maxIterationCount;
    private boolean forward;
    private double t0 = Double.NaN;
    private double g0 = Double.NaN;
    private boolean g0Positive = true;
    private boolean pendingEvent = false;
    private double pendingEventTime = Double.NaN;
    private double previousEventTime = Double.NaN;
    private boolean increasing = true;
    private int nextAction = 3;

    public EventState(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = FastMath.abs(convergence);
        this.maxIterationCount = maxIterationCount;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(StepInterpolator interpolator) throws EventException {
        try {
            double ignoreZone = interpolator.isForward() ? getConvergence() : -getConvergence();
            this.t0 = interpolator.getPreviousTime() + ignoreZone;
            interpolator.setInterpolatedTime(this.t0);
            this.g0 = this.handler.g(this.t0, interpolator.getInterpolatedState());
            if (this.g0 == 0.0d) {
                double tStart = interpolator.getPreviousTime();
                interpolator.setInterpolatedTime(tStart);
                this.g0Positive = this.handler.g(tStart, interpolator.getInterpolatedState()) <= 0.0d;
            } else {
                this.g0Positive = this.g0 >= 0.0d;
            }
        } catch (DerivativeException mue) {
            throw new EventException(mue);
        }
    }

    public boolean evaluateStep(final StepInterpolator interpolator) throws DerivativeException, EventException, ConvergenceException {
        double dSolve;
        try {
            this.forward = interpolator.isForward();
            double t1 = interpolator.getCurrentTime();
            if (FastMath.abs(t1 - this.t0) < this.convergence) {
                return false;
            }
            double start = this.forward ? this.t0 + this.convergence : this.t0 - this.convergence;
            double dt = t1 - start;
            int n = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(dt) / this.maxCheckInterval));
            double h = dt / n;
            double ta = this.t0;
            double ga = this.g0;
            for (int i = 0; i < n; i++) {
                double tb = start + ((i + 1) * h);
                interpolator.setInterpolatedTime(tb);
                double gb = this.handler.g(tb, interpolator.getInterpolatedState());
                if (this.g0Positive ^ (gb >= 0.0d)) {
                    this.increasing = gb >= ga;
                    UnivariateRealFunction f = new UnivariateRealFunction() { // from class: org.apache.commons.math.ode.events.EventState.1
                        @Override // org.apache.commons.math.analysis.UnivariateRealFunction
                        public double value(double t) {
                            try {
                                interpolator.setInterpolatedTime(t);
                                return EventState.this.handler.g(t, interpolator.getInterpolatedState());
                            } catch (DerivativeException e) {
                                throw new EmbeddedDerivativeException(e);
                            } catch (EventException e2) {
                                throw new EmbeddedEventException(e2);
                            }
                        }
                    };
                    BrentSolver solver = new BrentSolver(this.convergence);
                    if (ga * gb >= 0.0d) {
                        double epsilon = (this.forward ? 0.25d : -0.25d) * this.convergence;
                        for (int k = 0; k < 4 && ga * gb > 0.0d; k++) {
                            ta += epsilon;
                            try {
                                ga = f.value(ta);
                            } catch (FunctionEvaluationException ex) {
                                throw new DerivativeException(ex);
                            }
                        }
                        if (ga * gb > 0.0d) {
                            throw new MathInternalError();
                        }
                    }
                    if (ta <= tb) {
                        try {
                            dSolve = solver.solve(this.maxIterationCount, f, ta, tb);
                        } catch (FunctionEvaluationException ex2) {
                            throw new DerivativeException(ex2);
                        }
                    } else {
                        dSolve = solver.solve(this.maxIterationCount, f, tb, ta);
                    }
                    double root = dSolve;
                    if (!Double.isNaN(this.previousEventTime) && FastMath.abs(root - ta) <= this.convergence && FastMath.abs(root - this.previousEventTime) <= this.convergence) {
                        ta = tb;
                        ga = gb;
                    } else {
                        if (Double.isNaN(this.previousEventTime) || FastMath.abs(this.previousEventTime - root) > this.convergence) {
                            this.pendingEventTime = root;
                            this.pendingEvent = true;
                            return true;
                        }
                        ta = tb;
                        ga = gb;
                    }
                } else {
                    ta = tb;
                    ga = gb;
                }
            }
            this.pendingEvent = false;
            this.pendingEventTime = Double.NaN;
            return false;
        } catch (EmbeddedDerivativeException ede) {
            throw ede.getDerivativeException();
        } catch (EmbeddedEventException eee) {
            throw eee.getEventException();
        }
    }

    public double getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return Double.POSITIVE_INFINITY;
    }

    public void stepAccepted(double t, double[] y) throws EventException {
        this.t0 = t;
        this.g0 = this.handler.g(t, y);
        if (this.pendingEvent && FastMath.abs(this.pendingEventTime - t) <= this.convergence) {
            this.previousEventTime = t;
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(t, y, !(this.increasing ^ this.forward));
        } else {
            this.g0Positive = this.g0 >= 0.0d;
            this.nextAction = 3;
        }
    }

    public boolean stop() {
        return this.nextAction == 0;
    }

    public boolean reset(double t, double[] y) throws EventException {
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - t) > this.convergence) {
            return false;
        }
        if (this.nextAction == 1) {
            this.handler.resetState(t, y);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        return this.nextAction == 1 || this.nextAction == 2;
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/events/EventState$EmbeddedDerivativeException.class */
    private static class EmbeddedDerivativeException extends RuntimeException {
        private static final long serialVersionUID = 3574188382434584610L;
        private final DerivativeException derivativeException;

        public EmbeddedDerivativeException(DerivativeException derivativeException) {
            this.derivativeException = derivativeException;
        }

        public DerivativeException getDerivativeException() {
            return this.derivativeException;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/events/EventState$EmbeddedEventException.class */
    private static class EmbeddedEventException extends RuntimeException {
        private static final long serialVersionUID = -1337749250090455474L;
        private final EventException eventException;

        public EmbeddedEventException(EventException eventException) {
            this.eventException = eventException;
        }

        public EventException getEventException() {
            return this.eventException;
        }
    }
}
