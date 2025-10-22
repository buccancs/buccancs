package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class EventState {
    private final double convergence;
    private final EventHandler handler;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private final UnivariateSolver solver;
    private boolean forward;
    private ExpandableStatefulODE expandable = null;
    private double t0 = Double.NaN;
    private double g0 = Double.NaN;
    private boolean g0Positive = true;
    private boolean pendingEvent = false;
    private double pendingEventTime = Double.NaN;
    private double previousEventTime = Double.NaN;
    private boolean increasing = true;
    private EventHandler.Action nextAction = EventHandler.Action.CONTINUE;

    public EventState(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        this.handler = eventHandler;
        this.maxCheckInterval = d;
        this.convergence = FastMath.abs(d2);
        this.maxIterationCount = i;
        this.solver = univariateSolver;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public double getEventTime() {
        return this.pendingEvent ? this.pendingEventTime : this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void setExpandable(ExpandableStatefulODE expandableStatefulODE) {
        this.expandable = expandableStatefulODE;
    }

    public void reinitializeBegin(StepInterpolator stepInterpolator) throws MaxCountExceededException {
        double previousTime = stepInterpolator.getPreviousTime();
        this.t0 = previousTime;
        stepInterpolator.setInterpolatedTime(previousTime);
        double dG = this.handler.g(this.t0, getCompleteState(stepInterpolator));
        this.g0 = dG;
        if (dG == 0.0d) {
            double dMax = this.t0 + (FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0)) * 0.5d);
            stepInterpolator.setInterpolatedTime(dMax);
            this.g0 = this.handler.g(dMax, getCompleteState(stepInterpolator));
        }
        this.g0Positive = this.g0 >= 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double[] getCompleteState(StepInterpolator stepInterpolator) throws DimensionMismatchException {
        double[] dArr = new double[this.expandable.getTotalDimension()];
        this.expandable.getPrimaryMapper().insertEquationData(stepInterpolator.getInterpolatedState(), dArr);
        EquationsMapper[] secondaryMappers = this.expandable.getSecondaryMappers();
        int length = secondaryMappers.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            secondaryMappers[i].insertEquationData(stepInterpolator.getInterpolatedSecondaryState(i2), dArr);
            i++;
            i2++;
        }
        return dArr;
    }

    public boolean evaluateStep(final StepInterpolator stepInterpolator) throws MaxCountExceededException, NoBracketingException {
        UnivariateFunction univariateFunction;
        double d;
        double d2;
        UnivariateFunction univariateFunction2;
        double dForceSide;
        double d3;
        try {
            this.forward = stepInterpolator.isForward();
            double currentTime = stepInterpolator.getCurrentTime();
            double d4 = currentTime - this.t0;
            if (FastMath.abs(d4) < this.convergence) {
                return false;
            }
            int iMax = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(d4) / this.maxCheckInterval));
            double d5 = d4 / iMax;
            UnivariateFunction univariateFunction3 = new UnivariateFunction() { // from class: org.apache.commons.math3.ode.events.EventState.1
                @Override // org.apache.commons.math3.analysis.UnivariateFunction
                public double value(double d6) throws LocalMaxCountExceededException {
                    try {
                        stepInterpolator.setInterpolatedTime(d6);
                        return EventState.this.handler.g(d6, EventState.this.getCompleteState(stepInterpolator));
                    } catch (MaxCountExceededException e) {
                        throw new LocalMaxCountExceededException(e);
                    }
                }
            };
            double d6 = this.t0;
            double d7 = this.g0;
            double d8 = d6;
            int i = 0;
            while (i < iMax) {
                if (i == iMax - 1) {
                    univariateFunction = univariateFunction3;
                    d = currentTime;
                } else {
                    univariateFunction = univariateFunction3;
                    d = this.t0 + ((i + 1) * d5);
                }
                stepInterpolator.setInterpolatedTime(d);
                double dG = this.handler.g(d, getCompleteState(stepInterpolator));
                if (this.g0Positive ^ (dG >= 0.0d)) {
                    this.increasing = dG >= d7;
                    UnivariateSolver univariateSolver = this.solver;
                    if (univariateSolver instanceof BracketedUnivariateSolver) {
                        BracketedUnivariateSolver bracketedUnivariateSolver = (BracketedUnivariateSolver) univariateSolver;
                        dForceSide = this.forward ? bracketedUnivariateSolver.solve(this.maxIterationCount, (int) univariateFunction, d8, d, AllowedSolution.RIGHT_SIDE) : bracketedUnivariateSolver.solve(this.maxIterationCount, (int) univariateFunction, d, d8, AllowedSolution.LEFT_SIDE);
                        d2 = currentTime;
                    } else {
                        double dSolve = this.forward ? univariateSolver.solve(this.maxIterationCount, univariateFunction, d8, d) : univariateSolver.solve(this.maxIterationCount, univariateFunction, d, d8);
                        int evaluations = this.maxIterationCount - this.solver.getEvaluations();
                        d2 = currentTime;
                        PegasusSolver pegasusSolver = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
                        dForceSide = this.forward ? UnivariateSolverUtils.forceSide(evaluations, univariateFunction, pegasusSolver, dSolve, d8, d, AllowedSolution.RIGHT_SIDE) : UnivariateSolverUtils.forceSide(evaluations, univariateFunction, pegasusSolver, dSolve, d, d8, AllowedSolution.LEFT_SIDE);
                    }
                    if (Double.isNaN(this.previousEventTime) || FastMath.abs(dForceSide - d8) > this.convergence || FastMath.abs(dForceSide - this.previousEventTime) > this.convergence) {
                        univariateFunction2 = univariateFunction;
                        if (Double.isNaN(this.previousEventTime) || FastMath.abs(this.previousEventTime - dForceSide) > this.convergence) {
                            this.pendingEventTime = dForceSide;
                            this.pendingEvent = true;
                            return true;
                        }
                    } else {
                        while (true) {
                            d3 = this.forward ? d8 + this.convergence : d8 - this.convergence;
                            univariateFunction2 = univariateFunction;
                            dG = univariateFunction2.value(d3);
                            if (!(this.g0Positive ^ (dG >= 0.0d))) {
                                break;
                            }
                            if (!(this.forward ^ (d3 >= d))) {
                                break;
                            }
                            d8 = d3;
                            univariateFunction = univariateFunction2;
                        }
                        if (!((d3 >= d) ^ this.forward)) {
                            this.pendingEventTime = dForceSide;
                            this.pendingEvent = true;
                            return true;
                        }
                        i--;
                        d = d3;
                    }
                } else {
                    d2 = currentTime;
                    univariateFunction2 = univariateFunction;
                }
                d8 = d;
                d7 = dG;
                i++;
                univariateFunction3 = univariateFunction2;
                currentTime = d2;
            }
            this.pendingEvent = false;
            this.pendingEventTime = Double.NaN;
            return false;
        } catch (LocalMaxCountExceededException e) {
            throw e.getException();
        }
    }

    public void stepAccepted(double d, double[] dArr) {
        this.t0 = d;
        this.g0 = this.handler.g(d, dArr);
        if (this.pendingEvent && FastMath.abs(this.pendingEventTime - d) <= this.convergence) {
            this.previousEventTime = d;
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(d, dArr, !(r0 ^ this.forward));
            return;
        }
        this.g0Positive = this.g0 >= 0.0d;
        this.nextAction = EventHandler.Action.CONTINUE;
    }

    public boolean stop() {
        return this.nextAction == EventHandler.Action.STOP;
    }

    public boolean reset(double d, double[] dArr) {
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - d) > this.convergence) {
            return false;
        }
        if (this.nextAction == EventHandler.Action.RESET_STATE) {
            this.handler.resetState(d, dArr);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        return this.nextAction == EventHandler.Action.RESET_STATE || this.nextAction == EventHandler.Action.RESET_DERIVATIVES;
    }

    private static class LocalMaxCountExceededException extends RuntimeException {
        private static final long serialVersionUID = 20120901;
        private final MaxCountExceededException wrapped;

        LocalMaxCountExceededException(MaxCountExceededException maxCountExceededException) {
            this.wrapped = maxCountExceededException;
        }

        public MaxCountExceededException getException() {
            return this.wrapped;
        }
    }
}
