package org.apache.commons.math.ode;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math.ode.sampling.StepHandler;
import org.apache.commons.math.ode.sampling.StepInterpolator;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/MultistepIntegrator.class */
public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {
    private final int nSteps;
    protected double[] scaled;
    protected Array2DRowRealMatrix nordsieck;
    private FirstOrderIntegrator starter;
    private double exp;
    private double safety;
    private double minReduction;
    private double maxGrowth;

    protected MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        if (nSteps <= 0) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_ONE_PREVIOUS_POINT, name);
        }
        this.starter = new DormandPrince853Integrator(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    protected MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.starter = new DormandPrince853Integrator(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    protected abstract Array2DRowRealMatrix initializeHighOrderDerivatives(double[] dArr, double[][] dArr2);

    public ODEIntegrator getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderIntegrator starterIntegrator) {
        this.starter = starterIntegrator;
    }

    protected void start(double t0, double[] y0, double t) throws DerivativeException, IntegratorException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new NordsieckInitializer(y0.length));
        try {
            this.starter.integrate(new CountingDifferentialEquations(y0.length), t0, y0, t, new double[y0.length]);
        } catch (DerivativeException mue) {
            if (!(mue instanceof InitializationCompletedMarkerException)) {
                throw mue;
            }
        }
        this.starter.clearStepHandlers();
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double minReduction) {
        this.minReduction = minReduction;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    protected double computeStepGrowShrinkFactor(double error) {
        return FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/MultistepIntegrator$NordsieckTransformer.class */
    public interface NordsieckTransformer {
        RealMatrix initializeHighOrderDerivatives(double[] dArr, double[][] dArr2);
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/MultistepIntegrator$InitializationCompletedMarkerException.class */
    private static class InitializationCompletedMarkerException extends DerivativeException {
        private static final long serialVersionUID = -4105805787353488365L;

        public InitializationCompletedMarkerException() {
            super((Throwable) null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/MultistepIntegrator$NordsieckInitializer.class */
    private class NordsieckInitializer implements StepHandler {
        private final int n;

        public NordsieckInitializer(int n) {
            this.n = n;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v16, types: [double[], double[][]] */
        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public void handleStep(StepInterpolator interpolator, boolean isLast) throws DerivativeException {
            double prev = interpolator.getPreviousTime();
            double curr = interpolator.getCurrentTime();
            MultistepIntegrator.this.stepStart = prev;
            MultistepIntegrator.this.stepSize = (curr - prev) / (MultistepIntegrator.this.nSteps + 1);
            interpolator.setInterpolatedTime(prev);
            MultistepIntegrator.this.scaled = (double[]) interpolator.getInterpolatedDerivatives().clone();
            for (int j = 0; j < this.n; j++) {
                double[] dArr = MultistepIntegrator.this.scaled;
                int i = j;
                dArr[i] = dArr[i] * MultistepIntegrator.this.stepSize;
            }
            ??r0 = new double[MultistepIntegrator.this.nSteps];
            for (int i2 = 1; i2 <= MultistepIntegrator.this.nSteps; i2++) {
                interpolator.setInterpolatedTime(prev + (MultistepIntegrator.this.stepSize * i2));
                double[] msI = (double[]) interpolator.getInterpolatedDerivatives().clone();
                for (int j2 = 0; j2 < this.n; j2++) {
                    int i3 = j2;
                    msI[i3] = msI[i3] * MultistepIntegrator.this.stepSize;
                }
                r0[i2 - 1] = msI;
            }
            MultistepIntegrator.this.nordsieck = MultistepIntegrator.this.initializeHighOrderDerivatives(MultistepIntegrator.this.scaled, r0);
            throw new InitializationCompletedMarkerException();
        }

        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public boolean requiresDenseOutput() {
            return true;
        }

        @Override // org.apache.commons.math.ode.sampling.StepHandler
        public void reset() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
    /* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/MultistepIntegrator$CountingDifferentialEquations.class */
    private class CountingDifferentialEquations implements ExtendedFirstOrderDifferentialEquations {
        private final int dimension;

        public CountingDifferentialEquations(int dimension) {
            this.dimension = dimension;
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public void computeDerivatives(double t, double[] y, double[] dot) throws DerivativeException {
            MultistepIntegrator.this.computeDerivatives(t, y, dot);
        }

        @Override // org.apache.commons.math.ode.FirstOrderDifferentialEquations
        public int getDimension() {
            return this.dimension;
        }

        @Override // org.apache.commons.math.ode.ExtendedFirstOrderDifferentialEquations
        public int getMainSetDimension() {
            return MultistepIntegrator.this.mainSetDimension;
        }
    }
}
