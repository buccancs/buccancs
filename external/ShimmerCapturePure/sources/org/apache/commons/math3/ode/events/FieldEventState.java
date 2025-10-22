package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class FieldEventState<T extends RealFieldElement<T>> {
    private final T convergence;
    private final FieldEventHandler<T> handler;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private final BracketedRealFieldUnivariateSolver<T> solver;
    private boolean forward;
    private T t0 = null;
    private T g0 = null;
    private boolean g0Positive = true;
    private boolean pendingEvent = false;
    private T pendingEventTime = null;
    private T previousEventTime = null;
    private boolean increasing = true;
    private Action nextAction = Action.CONTINUE;

    public FieldEventState(FieldEventHandler<T> fieldEventHandler, double d, T t, int i, BracketedRealFieldUnivariateSolver<T> bracketedRealFieldUnivariateSolver) {
        this.handler = fieldEventHandler;
        this.maxCheckInterval = d;
        this.convergence = (T) t.abs();
        this.maxIterationCount = i;
        this.solver = bracketedRealFieldUnivariateSolver;
    }

    public T getConvergence() {
        return this.convergence;
    }

    public FieldEventHandler<T> getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(FieldStepInterpolator<T> fieldStepInterpolator) throws MaxCountExceededException {
        FieldODEStateAndDerivative<T> previousState = fieldStepInterpolator.getPreviousState();
        this.t0 = previousState.getTime();
        T t = (T) this.handler.g(previousState);
        this.g0 = t;
        if (t.getReal() == 0.0d) {
            this.g0 = (T) this.handler.g(fieldStepInterpolator.getInterpolatedState((RealFieldElement) this.t0.add(FastMath.max(this.solver.getAbsoluteAccuracy().getReal(), FastMath.abs(((RealFieldElement) this.solver.getRelativeAccuracy().multiply(this.t0)).getReal())) * 0.5d)));
        }
        this.g0Positive = this.g0.getReal() >= 0.0d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0176  */
    /* JADX WARN: Type inference failed for: r10v21, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r11v24, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r11v3, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r9v7, types: [org.apache.commons.math3.RealFieldElement] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean evaluateStep(final org.apache.commons.math3.ode.sampling.FieldStepInterpolator<T> r22) throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.events.FieldEventState.evaluateStep(org.apache.commons.math3.ode.sampling.FieldStepInterpolator):boolean");
    }

    public T getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return (T) ((RealFieldElement) this.t0.getField().getZero()).add(this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    public void stepAccepted(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        this.t0 = fieldODEStateAndDerivative.getTime();
        this.g0 = (T) this.handler.g(fieldODEStateAndDerivative);
        if (this.pendingEvent && ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(fieldODEStateAndDerivative.getTime())).abs()).subtract(this.convergence)).getReal() <= 0.0d) {
            this.previousEventTime = fieldODEStateAndDerivative.getTime();
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(fieldODEStateAndDerivative, !(r0 ^ this.forward));
            return;
        }
        this.g0Positive = this.g0.getReal() >= 0.0d;
        this.nextAction = Action.CONTINUE;
    }

    public boolean stop() {
        return this.nextAction == Action.STOP;
    }

    public FieldODEState<T> reset(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        if (!this.pendingEvent || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(fieldODEStateAndDerivative.getTime())).abs()).subtract(this.convergence)).getReal() > 0.0d) {
            return null;
        }
        if (this.nextAction == Action.RESET_STATE) {
            fieldODEStateAndDerivative = this.handler.resetState(fieldODEStateAndDerivative);
        } else if (this.nextAction != Action.RESET_DERIVATIVES) {
            fieldODEStateAndDerivative = null;
        }
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return fieldODEStateAndDerivative;
    }
}
