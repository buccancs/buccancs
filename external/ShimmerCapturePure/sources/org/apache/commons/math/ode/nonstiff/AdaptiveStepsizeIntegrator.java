package org.apache.commons.math.ode.nonstiff;

import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.ode.AbstractIntegrator;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.ExtendedFirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/AdaptiveStepsizeIntegrator.class */
public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {
    protected final double scalAbsoluteTolerance;
    protected final double scalRelativeTolerance;
    protected final double[] vecAbsoluteTolerance;
    protected final double[] vecRelativeTolerance;
    private final double minStep;
    private final double maxStep;
    protected int mainSetDimension;
    private double initialStep;

    public AdaptiveStepsizeIntegrator(String name, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(name);
        this.minStep = FastMath.abs(minStep);
        this.maxStep = FastMath.abs(maxStep);
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = scalAbsoluteTolerance;
        this.scalRelativeTolerance = scalRelativeTolerance;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
        resetInternalState();
    }

    public AdaptiveStepsizeIntegrator(String name, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(name);
        this.minStep = minStep;
        this.maxStep = maxStep;
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = 0.0d;
        this.scalRelativeTolerance = 0.0d;
        this.vecAbsoluteTolerance = (double[]) vecAbsoluteTolerance.clone();
        this.vecRelativeTolerance = (double[]) vecRelativeTolerance.clone();
        resetInternalState();
    }

    @Override // org.apache.commons.math.ode.FirstOrderIntegrator
    public abstract double integrate(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2, double[] dArr2) throws DerivativeException, IntegratorException;

    public void setInitialStepSize(double initialStepSize) {
        if (initialStepSize < this.minStep || initialStepSize > this.maxStep) {
            this.initialStep = -1.0d;
        } else {
            this.initialStep = initialStepSize;
        }
    }

    @Override // org.apache.commons.math.ode.AbstractIntegrator
    protected void sanityChecks(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y) throws IntegratorException {
        super.sanityChecks(equations, t0, y0, t, y);
        if (equations instanceof ExtendedFirstOrderDifferentialEquations) {
            this.mainSetDimension = ((ExtendedFirstOrderDifferentialEquations) equations).getMainSetDimension();
        } else {
            this.mainSetDimension = equations.getDimension();
        }
        if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension) {
            throw new IntegratorException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(this.mainSetDimension), Integer.valueOf(this.vecAbsoluteTolerance.length));
        }
        if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension) {
            throw new IntegratorException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(this.mainSetDimension), Integer.valueOf(this.vecRelativeTolerance.length));
        }
    }

    public double initializeStep(FirstOrderDifferentialEquations equations, boolean forward, int order, double[] scale, double t0, double[] y0, double[] yDot0, double[] y1, double[] yDot1) throws DerivativeException {
        if (this.initialStep > 0.0d) {
            return forward ? this.initialStep : -this.initialStep;
        }
        double yOnScale2 = 0.0d;
        double yDotOnScale2 = 0.0d;
        for (int j = 0; j < scale.length; j++) {
            double ratio = y0[j] / scale[j];
            yOnScale2 += ratio * ratio;
            double ratio2 = yDot0[j] / scale[j];
            yDotOnScale2 += ratio2 * ratio2;
        }
        double h = (yOnScale2 < 1.0E-10d || yDotOnScale2 < 1.0E-10d) ? 1.0E-6d : 0.01d * FastMath.sqrt(yOnScale2 / yDotOnScale2);
        if (!forward) {
            h = -h;
        }
        for (int j2 = 0; j2 < y0.length; j2++) {
            y1[j2] = y0[j2] + (h * yDot0[j2]);
        }
        computeDerivatives(t0 + h, y1, yDot1);
        double yDDotOnScale = 0.0d;
        for (int j3 = 0; j3 < scale.length; j3++) {
            double ratio3 = (yDot1[j3] - yDot0[j3]) / scale[j3];
            yDDotOnScale += ratio3 * ratio3;
        }
        double maxInv2 = FastMath.max(FastMath.sqrt(yDotOnScale2), FastMath.sqrt(yDDotOnScale) / h);
        double h1 = maxInv2 < 1.0E-15d ? FastMath.max(1.0E-6d, 0.001d * FastMath.abs(h)) : FastMath.pow(0.01d / maxInv2, 1.0d / order);
        double h2 = FastMath.max(FastMath.min(100.0d * FastMath.abs(h), h1), 1.0E-12d * FastMath.abs(t0));
        if (h2 < getMinStep()) {
            h2 = getMinStep();
        }
        if (h2 > getMaxStep()) {
            h2 = getMaxStep();
        }
        if (!forward) {
            h2 = -h2;
        }
        return h2;
    }

    protected double filterStep(double h, boolean forward, boolean acceptSmall) throws IntegratorException {
        double filteredH = h;
        if (FastMath.abs(h) < this.minStep) {
            if (acceptSmall) {
                filteredH = forward ? this.minStep : -this.minStep;
            } else {
                throw new IntegratorException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(this.minStep), Double.valueOf(FastMath.abs(h)));
            }
        }
        if (filteredH > this.maxStep) {
            filteredH = this.maxStep;
        } else if (filteredH < (-this.maxStep)) {
            filteredH = -this.maxStep;
        }
        return filteredH;
    }

    @Override // org.apache.commons.math.ode.AbstractIntegrator, org.apache.commons.math.ode.ODEIntegrator
    public double getCurrentStepStart() {
        return this.stepStart;
    }

    protected void resetInternalState() {
        this.stepStart = Double.NaN;
        this.stepSize = FastMath.sqrt(this.minStep * this.maxStep);
    }

    public double getMinStep() {
        return this.minStep;
    }

    public double getMaxStep() {
        return this.maxStep;
    }
}
