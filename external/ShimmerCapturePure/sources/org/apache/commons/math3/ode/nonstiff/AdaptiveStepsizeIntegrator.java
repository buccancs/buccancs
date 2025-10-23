package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {
    protected int mainSetDimension;
    protected double scalAbsoluteTolerance;
    protected double scalRelativeTolerance;
    protected double[] vecAbsoluteTolerance;
    protected double[] vecRelativeTolerance;
    private double initialStep;
    private double maxStep;
    private double minStep;

    public AdaptiveStepsizeIntegrator(String str, double d, double d2, double d3, double d4) {
        super(str);
        setStepSizeControl(d, d2, d3, d4);
        resetInternalState();
    }

    public AdaptiveStepsizeIntegrator(String str, double d, double d2, double[] dArr, double[] dArr2) {
        super(str);
        setStepSizeControl(d, d2, dArr, dArr2);
        resetInternalState();
    }

    public double getMaxStep() {
        return this.maxStep;
    }

    public double getMinStep() {
        return this.minStep;
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator
    public abstract void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    public void setInitialStepSize(double d) {
        if (d < this.minStep || d > this.maxStep) {
            this.initialStep = -1.0d;
        } else {
            this.initialStep = d;
        }
    }

    public void setStepSizeControl(double d, double d2, double d3, double d4) {
        this.minStep = FastMath.abs(d);
        this.maxStep = FastMath.abs(d2);
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = d3;
        this.scalRelativeTolerance = d4;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
    }

    public void setStepSizeControl(double d, double d2, double[] dArr, double[] dArr2) {
        this.minStep = FastMath.abs(d);
        this.maxStep = FastMath.abs(d2);
        this.initialStep = -1.0d;
        this.scalAbsoluteTolerance = 0.0d;
        this.scalRelativeTolerance = 0.0d;
        this.vecAbsoluteTolerance = (double[]) dArr.clone();
        this.vecRelativeTolerance = (double[]) dArr2.clone();
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator
    protected void sanityChecks(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException {
        super.sanityChecks(expandableStatefulODE, d);
        int dimension = expandableStatefulODE.getPrimaryMapper().getDimension();
        this.mainSetDimension = dimension;
        double[] dArr = this.vecAbsoluteTolerance;
        if (dArr != null && dArr.length != dimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
        }
        double[] dArr2 = this.vecRelativeTolerance;
        if (dArr2 != null && dArr2.length != dimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
        }
    }

    public double initializeStep(boolean z, int i, double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) throws MaxCountExceededException, DimensionMismatchException {
        double d2 = this.initialStep;
        if (d2 > 0.0d) {
            return z ? d2 : -d2;
        }
        double d3 = 0.0d;
        double d4 = 0.0d;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            double d5 = dArr2[i2];
            double d6 = dArr[i2];
            double d7 = d5 / d6;
            d3 += d7 * d7;
            double d8 = dArr3[i2] / d6;
            d4 += d8 * d8;
        }
        double dSqrt = (d3 < 1.0E-10d || d4 < 1.0E-10d) ? 1.0E-6d : FastMath.sqrt(d3 / d4) * 0.01d;
        if (!z) {
            dSqrt = -dSqrt;
        }
        for (int i3 = 0; i3 < dArr2.length; i3++) {
            dArr4[i3] = dArr2[i3] + (dArr3[i3] * dSqrt);
        }
        computeDerivatives(d + dSqrt, dArr4, dArr5);
        double d9 = 0.0d;
        for (int i4 = 0; i4 < dArr.length; i4++) {
            double d10 = (dArr5[i4] - dArr3[i4]) / dArr[i4];
            d9 += d10 * d10;
        }
        double dMax = FastMath.max(FastMath.sqrt(d4), FastMath.sqrt(d9) / dSqrt);
        double dMax2 = FastMath.max(FastMath.min(FastMath.abs(dSqrt) * 100.0d, dMax < 1.0E-15d ? FastMath.max(1.0E-6d, FastMath.abs(dSqrt) * 0.001d) : FastMath.pow(0.01d / dMax, 1.0d / i)), FastMath.abs(d) * 1.0E-12d);
        if (dMax2 < getMinStep()) {
            dMax2 = getMinStep();
        }
        if (dMax2 > getMaxStep()) {
            dMax2 = getMaxStep();
        }
        return !z ? -dMax2 : dMax2;
    }

    protected double filterStep(double d, boolean z, boolean z2) throws NumberIsTooSmallException {
        double dAbs = FastMath.abs(d);
        double d2 = this.minStep;
        if (dAbs < d2) {
            if (!z2) {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(FastMath.abs(d)), Double.valueOf(this.minStep), true);
            }
            d = z ? d2 : -d2;
        }
        double d3 = this.maxStep;
        return d > d3 ? d3 : d < (-d3) ? -d3 : d;
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator, org.apache.commons.math3.ode.ODEIntegrator
    public double getCurrentStepStart() {
        return this.stepStart;
    }

    protected void resetInternalState() {
        this.stepStart = Double.NaN;
        this.stepSize = FastMath.sqrt(this.minStep * this.maxStep);
    }
}
