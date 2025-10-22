package org.apache.commons.math.ode.jacobians;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.MaxEvaluationsExceededException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.ExtendedFirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderIntegrator;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.events.EventException;
import org.apache.commons.math.ode.events.EventHandler;
import org.apache.commons.math.ode.sampling.StepHandler;
import org.apache.commons.math.ode.sampling.StepInterpolator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians.class */
public class FirstOrderIntegratorWithJacobians {
    private final FirstOrderIntegrator integrator;
    private final ODEWithJacobians ode;
    private int maxEvaluations;
    private int evaluations;

    public FirstOrderIntegratorWithJacobians(FirstOrderIntegrator integrator, ParameterizedODE ode, double[] p, double[] hY, double[] hP) throws IllegalArgumentException {
        checkDimension(ode.getDimension(), hY);
        checkDimension(ode.getParametersDimension(), p);
        checkDimension(ode.getParametersDimension(), hP);
        this.integrator = integrator;
        this.ode = new FiniteDifferencesWrapper(ode, p, hY, hP);
        setMaxEvaluations(-1);
    }

    public FirstOrderIntegratorWithJacobians(FirstOrderIntegrator integrator, ODEWithJacobians ode) {
        this.integrator = integrator;
        this.ode = ode;
        setMaxEvaluations(-1);
    }

    static /* synthetic */ int access$104(FirstOrderIntegratorWithJacobians x0) {
        int i = x0.evaluations + 1;
        x0.evaluations = i;
        return i;
    }

    static /* synthetic */ int access$112(FirstOrderIntegratorWithJacobians x0, int x1) {
        int i = x0.evaluations + x1;
        x0.evaluations = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchCompoundState(double[] z, double[] y, double[][] dydy0, double[][] dydp) {
        int n = y.length;
        int k = dydp[0].length;
        System.arraycopy(z, 0, y, 0, n);
        for (int i = 0; i < n; i++) {
            System.arraycopy(z, n * (i + 1), dydy0[i], 0, n);
        }
        for (int i2 = 0; i2 < n; i2++) {
            System.arraycopy(z, (n * (n + 1)) + (i2 * k), dydp[i2], 0, k);
        }
    }

    public void addStepHandler(StepHandlerWithJacobians handler) {
        int n = this.ode.getDimension();
        int k = this.ode.getParametersDimension();
        this.integrator.addStepHandler(new StepHandlerWrapper(handler, n, k));
    }

    public Collection<StepHandlerWithJacobians> getStepHandlers() {
        Collection<StepHandlerWithJacobians> handlers = new ArrayList<>();
        for (StepHandler handler : this.integrator.getStepHandlers()) {
            if (handler instanceof StepHandlerWrapper) {
                handlers.add(((StepHandlerWrapper) handler).getHandler());
            }
        }
        return handlers;
    }

    public void clearStepHandlers() {
        this.integrator.clearStepHandlers();
    }

    public void addEventHandler(EventHandlerWithJacobians handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        int n = this.ode.getDimension();
        int k = this.ode.getParametersDimension();
        this.integrator.addEventHandler(new EventHandlerWrapper(handler, n, k), maxCheckInterval, convergence, maxIterationCount);
    }

    public Collection<EventHandlerWithJacobians> getEventHandlers() {
        Collection<EventHandlerWithJacobians> handlers = new ArrayList<>();
        for (EventHandler handler : this.integrator.getEventHandlers()) {
            if (handler instanceof EventHandlerWrapper) {
                handlers.add(((EventHandlerWrapper) handler).getHandler());
            }
        }
        return handlers;
    }

    public void clearEventHandlers() {
        this.integrator.clearEventHandlers();
    }

    public double integrate(double t0, double[] y0, double[][] dY0dP, double t, double[] y, double[][] dYdY0, double[][] dYdP) throws DerivativeException, IntegratorException, IllegalArgumentException {
        int n = this.ode.getDimension();
        int k = this.ode.getParametersDimension();
        checkDimension(n, y0);
        checkDimension(n, y);
        checkDimension(n, dYdY0);
        checkDimension(n, dYdY0[0]);
        if (k != 0) {
            checkDimension(n, dY0dP);
            checkDimension(k, dY0dP[0]);
            checkDimension(n, dYdP);
            checkDimension(k, dYdP[0]);
        }
        double[] z = new double[n * (1 + n + k)];
        System.arraycopy(y0, 0, z, 0, n);
        for (int i = 0; i < n; i++) {
            z[(i * (1 + n)) + n] = 1.0d;
            System.arraycopy(dY0dP[i], 0, z, (n * (n + 1)) + (i * k), k);
        }
        this.evaluations = 0;
        double stopTime = this.integrator.integrate(new MappingWrapper(), t0, z, t, z);
        dispatchCompoundState(z, y, dYdY0, dYdP);
        return stopTime;
    }

    public double getCurrentStepStart() {
        return this.integrator.getCurrentStepStart();
    }

    public double getCurrentSignedStepsize() {
        return this.integrator.getCurrentSignedStepsize();
    }

    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    public void setMaxEvaluations(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations < 0 ? Integer.MAX_VALUE : maxEvaluations;
    }

    public int getEvaluations() {
        return this.evaluations;
    }

    private void checkDimension(int expected, Object array) throws IllegalArgumentException {
        int arrayDimension = array == null ? 0 : Array.getLength(array);
        if (arrayDimension != expected) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(arrayDimension), Integer.valueOf(expected));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians$StepHandlerWrapper.class */
    private static class StepHandlerWrapper implements StepHandler {
        private final StepHandlerWithJacobians handler;
        private final int n;
        private final int k;

        public StepHandlerWrapper(StepHandlerWithJacobians handler, int n, int k) {
            this.handler = handler;
            this.n = n;
            this.k = k;
        }

        public StepHandlerWithJacobians getHandler() {
            return this.handler;
        }

        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public void handleStep(StepInterpolator interpolator, boolean isLast) throws DerivativeException {
            this.handler.handleStep(new StepInterpolatorWrapper(interpolator, this.n, this.k), isLast);
        }

        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public boolean requiresDenseOutput() {
            return this.handler.requiresDenseOutput();
        }

        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public void reset() {
            this.handler.reset();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians$StepInterpolatorWrapper.class */
    private static class StepInterpolatorWrapper implements StepInterpolatorWithJacobians {
        private StepInterpolator interpolator;
        private double[] y;
        private double[][] dydy0;
        private double[][] dydp;
        private double[] yDot;
        private double[][] dydy0Dot;
        private double[][] dydpDot;

        public StepInterpolatorWrapper() {
        }

        public StepInterpolatorWrapper(StepInterpolator interpolator, int n, int k) {
            this.interpolator = interpolator;
            this.y = new double[n];
            this.dydy0 = new double[n][n];
            this.dydp = new double[n][k];
            this.yDot = new double[n];
            this.dydy0Dot = new double[n][n];
            this.dydpDot = new double[n][k];
        }

        private static void copyArray(double[] src, double[] dest) {
            System.arraycopy(src, 0, dest, 0, src.length);
        }

        private static void copyArray(double[][] src, double[][] dest) {
            for (int i = 0; i < src.length; i++) {
                copyArray(src[i], dest[i]);
            }
        }

        private static void writeArray(ObjectOutput out, double[] array) throws IOException {
            for (double d : array) {
                out.writeDouble(d);
            }
        }

        private static void writeArray(ObjectOutput out, double[][] array) throws IOException {
            for (double[] dArr : array) {
                writeArray(out, dArr);
            }
        }

        private static void readArray(ObjectInput in, double[] array) throws IOException {
            for (int i = 0; i < array.length; i++) {
                array[i] = in.readDouble();
            }
        }

        private static void readArray(ObjectInput in, double[][] array) throws IOException {
            for (double[] dArr : array) {
                readArray(in, dArr);
            }
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public boolean isForward() {
            return this.interpolator.isForward();
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double getPreviousTime() {
            return this.interpolator.getPreviousTime();
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double getInterpolatedTime() {
            return this.interpolator.getInterpolatedTime();
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public void setInterpolatedTime(double time) {
            this.interpolator.setInterpolatedTime(time);
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[] getInterpolatedY() throws DerivativeException {
            double[] extendedState = this.interpolator.getInterpolatedState();
            System.arraycopy(extendedState, 0, this.y, 0, this.y.length);
            return this.y;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[][] getInterpolatedDyDy0() throws DerivativeException {
            double[] extendedState = this.interpolator.getInterpolatedState();
            int n = this.y.length;
            int start = n;
            for (int i = 0; i < n; i++) {
                System.arraycopy(extendedState, start, this.dydy0[i], 0, n);
                start += n;
            }
            return this.dydy0;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[][] getInterpolatedDyDp() throws DerivativeException {
            double[] extendedState = this.interpolator.getInterpolatedState();
            int n = this.y.length;
            int k = this.dydp[0].length;
            int start = n * (n + 1);
            for (int i = 0; i < n; i++) {
                System.arraycopy(extendedState, start, this.dydp[i], 0, k);
                start += k;
            }
            return this.dydp;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[] getInterpolatedYDot() throws DerivativeException {
            double[] extendedDerivatives = this.interpolator.getInterpolatedDerivatives();
            System.arraycopy(extendedDerivatives, 0, this.yDot, 0, this.yDot.length);
            return this.yDot;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[][] getInterpolatedDyDy0Dot() throws DerivativeException {
            double[] extendedDerivatives = this.interpolator.getInterpolatedDerivatives();
            int n = this.y.length;
            int start = n;
            for (int i = 0; i < n; i++) {
                System.arraycopy(extendedDerivatives, start, this.dydy0Dot[i], 0, n);
                start += n;
            }
            return this.dydy0Dot;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double[][] getInterpolatedDyDpDot() throws DerivativeException {
            double[] extendedDerivatives = this.interpolator.getInterpolatedDerivatives();
            int n = this.y.length;
            int k = this.dydpDot[0].length;
            int start = n * (n + 1);
            for (int i = 0; i < n; i++) {
                System.arraycopy(extendedDerivatives, start, this.dydpDot[i], 0, k);
                start += k;
            }
            return this.dydpDot;
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public double getCurrentTime() {
            return this.interpolator.getCurrentTime();
        }

        @Override // org.apache.commons.math.ode.jacobians.StepInterpolatorWithJacobians
        public StepInterpolatorWithJacobians copy() throws DerivativeException {
            int n = this.y.length;
            int k = this.dydp[0].length;
            StepInterpolatorWrapper copied = new StepInterpolatorWrapper(this.interpolator.copy(), n, k);
            copyArray(this.y, copied.y);
            copyArray(this.dydy0, copied.dydy0);
            copyArray(this.dydp, copied.dydp);
            copyArray(this.yDot, copied.yDot);
            copyArray(this.dydy0Dot, copied.dydy0Dot);
            copyArray(this.dydpDot, copied.dydpDot);
            return copied;
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(this.interpolator);
            out.writeInt(this.y.length);
            out.writeInt(this.dydp[0].length);
            writeArray(out, this.y);
            writeArray(out, this.dydy0);
            writeArray(out, this.dydp);
            writeArray(out, this.yDot);
            writeArray(out, this.dydy0Dot);
            writeArray(out, this.dydpDot);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.interpolator = (StepInterpolator) in.readObject();
            int n = in.readInt();
            int k = in.readInt();
            this.y = new double[n];
            this.dydy0 = new double[n][n];
            this.dydp = new double[n][k];
            this.yDot = new double[n];
            this.dydy0Dot = new double[n][n];
            this.dydpDot = new double[n][k];
            readArray(in, this.y);
            readArray(in, this.dydy0);
            readArray(in, this.dydp);
            readArray(in, this.yDot);
            readArray(in, this.dydy0Dot);
            readArray(in, this.dydpDot);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians$EventHandlerWrapper.class */
    private static class EventHandlerWrapper implements EventHandler {
        private final EventHandlerWithJacobians handler;
        private double[] y;
        private double[][] dydy0;
        private double[][] dydp;

        public EventHandlerWrapper(EventHandlerWithJacobians handler, int n, int k) {
            this.handler = handler;
            this.y = new double[n];
            this.dydy0 = new double[n][n];
            this.dydp = new double[n][k];
        }

        public EventHandlerWithJacobians getHandler() {
            return this.handler;
        }

        @Override // org.apache.commons.math.ode.events.EventHandler
        public int eventOccurred(double t, double[] z, boolean increasing) throws EventException {
            FirstOrderIntegratorWithJacobians.dispatchCompoundState(z, this.y, this.dydy0, this.dydp);
            return this.handler.eventOccurred(t, this.y, this.dydy0, this.dydp, increasing);
        }

        @Override // org.apache.commons.math.ode.events.EventHandler
        public double g(double t, double[] z) throws EventException {
            FirstOrderIntegratorWithJacobians.dispatchCompoundState(z, this.y, this.dydy0, this.dydp);
            return this.handler.g(t, this.y, this.dydy0, this.dydp);
        }

        @Override // org.apache.commons.math.ode.events.EventHandler
        public void resetState(double t, double[] z) throws EventException {
            FirstOrderIntegratorWithJacobians.dispatchCompoundState(z, this.y, this.dydy0, this.dydp);
            this.handler.resetState(t, this.y, this.dydy0, this.dydp);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians$MappingWrapper.class */
    private class MappingWrapper implements ExtendedFirstOrderDifferentialEquations {
        private final double[] y;
        private final double[] yDot;
        private final double[][] dFdY;
        private final double[][] dFdP;

        public MappingWrapper() {
            int n = FirstOrderIntegratorWithJacobians.this.ode.getDimension();
            int k = FirstOrderIntegratorWithJacobians.this.ode.getParametersDimension();
            this.y = new double[n];
            this.yDot = new double[n];
            this.dFdY = new double[n][n];
            this.dFdP = new double[n][k];
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public int getDimension() {
            int n = this.y.length;
            int k = this.dFdP[0].length;
            return n * (1 + n + k);
        }

        @Override // org.apache.commons.math.ode.ExtendedFirstOrderDifferentialEquations
        public int getMainSetDimension() {
            return FirstOrderIntegratorWithJacobians.this.ode.getDimension();
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public void computeDerivatives(double t, double[] z, double[] zDot) throws DerivativeException {
            int n = this.y.length;
            int k = this.dFdP[0].length;
            System.arraycopy(z, 0, this.y, 0, n);
            if (FirstOrderIntegratorWithJacobians.access$104(FirstOrderIntegratorWithJacobians.this) <= FirstOrderIntegratorWithJacobians.this.maxEvaluations) {
                FirstOrderIntegratorWithJacobians.this.ode.computeDerivatives(t, this.y, this.yDot);
                FirstOrderIntegratorWithJacobians.this.ode.computeJacobians(t, this.y, this.yDot, this.dFdY, this.dFdP);
                System.arraycopy(this.yDot, 0, zDot, 0, n);
                for (int i = 0; i < n; i++) {
                    double[] dFdYi = this.dFdY[i];
                    for (int j = 0; j < n; j++) {
                        double s = 0.0d;
                        int startIndex = n + j;
                        int zIndex = startIndex;
                        for (int l = 0; l < n; l++) {
                            s += dFdYi[l] * z[zIndex];
                            zIndex += n;
                        }
                        zDot[startIndex + (i * n)] = s;
                    }
                }
                for (int i2 = 0; i2 < n; i2++) {
                    double[] dFdYi2 = this.dFdY[i2];
                    double[] dFdPi = this.dFdP[i2];
                    for (int j2 = 0; j2 < k; j2++) {
                        double s2 = dFdPi[j2];
                        int startIndex2 = (n * (n + 1)) + j2;
                        int zIndex2 = startIndex2;
                        for (int l2 = 0; l2 < n; l2++) {
                            s2 += dFdYi2[l2] * z[zIndex2];
                            zIndex2 += k;
                        }
                        zDot[startIndex2 + (i2 * k)] = s2;
                    }
                }
                return;
            }
            throw new DerivativeException(new MaxEvaluationsExceededException(FirstOrderIntegratorWithJacobians.this.maxEvaluations));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/jacobians/FirstOrderIntegratorWithJacobians$FiniteDifferencesWrapper.class */
    private class FiniteDifferencesWrapper implements ODEWithJacobians {
        private final ParameterizedODE ode;
        private final double[] p;
        private final double[] hY;
        private final double[] hP;
        private final double[] tmpDot;

        public FiniteDifferencesWrapper(ParameterizedODE ode, double[] p, double[] hY, double[] hP) {
            this.ode = ode;
            this.p = (double[]) p.clone();
            this.hY = (double[]) hY.clone();
            this.hP = (double[]) hP.clone();
            this.tmpDot = new double[ode.getDimension()];
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public int getDimension() {
            return this.ode.getDimension();
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public void computeDerivatives(double t, double[] y, double[] yDot) throws DerivativeException {
            this.ode.computeDerivatives(t, y, yDot);
        }

        @Override // org.apache.commons.math.ode.jacobians.ODEWithJacobians
        public int getParametersDimension() {
            return this.ode.getParametersDimension();
        }

        @Override // org.apache.commons.math.ode.jacobians.ODEWithJacobians
        public void computeJacobians(double t, double[] y, double[] yDot, double[][] dFdY, double[][] dFdP) throws DerivativeException {
            int n = this.hY.length;
            int k = this.hP.length;
            FirstOrderIntegratorWithJacobians.access$112(FirstOrderIntegratorWithJacobians.this, n + k);
            if (FirstOrderIntegratorWithJacobians.this.evaluations > FirstOrderIntegratorWithJacobians.this.maxEvaluations) {
                throw new DerivativeException(new MaxEvaluationsExceededException(FirstOrderIntegratorWithJacobians.this.maxEvaluations));
            }
            for (int j = 0; j < n; j++) {
                double savedYj = y[j];
                int i = j;
                y[i] = y[i] + this.hY[j];
                this.ode.computeDerivatives(t, y, this.tmpDot);
                for (int i2 = 0; i2 < n; i2++) {
                    dFdY[i2][j] = (this.tmpDot[i2] - yDot[i2]) / this.hY[j];
                }
                y[j] = savedYj;
            }
            for (int j2 = 0; j2 < k; j2++) {
                this.ode.setParameter(j2, this.p[j2] + this.hP[j2]);
                this.ode.computeDerivatives(t, y, this.tmpDot);
                for (int i3 = 0; i3 < n; i3++) {
                    dFdP[i3][j2] = (this.tmpDot[i3] - yDot[i3]) / this.hP[j2];
                }
                this.ode.setParameter(j2, this.p[j2]);
            }
        }
    }
}
