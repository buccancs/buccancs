package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class AdamsBashforthIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i, d, d2, d3, d4);
    }

    public AdamsBashforthIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i, d, d2, dArr, dArr2);
    }

    private double errorEstimation(double[] dArr, double[] dArr2, double[] dArr3, RealMatrix realMatrix) {
        double d = 0.0d;
        for (int i = 0; i < this.mainSetDimension; i++) {
            double dAbs = FastMath.abs(dArr2[i]);
            double d2 = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + (this.scalRelativeTolerance * dAbs) : this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * dAbs);
            int i2 = realMatrix.getRowDimension() % 2 == 0 ? -1 : 1;
            double entry = 0.0d;
            for (int rowDimension = realMatrix.getRowDimension() - 1; rowDimension >= 0; rowDimension--) {
                entry += i2 * realMatrix.getEntry(rowDimension, i);
                i2 = -i2;
            }
            double d3 = ((dArr2[i] - dArr[i]) + (entry - dArr3[i])) / d2;
            d += d3 * d3;
        }
        return FastMath.sqrt(d / this.mainSetDimension);
    }

    @Override
    // org.apache.commons.math3.ode.nonstiff.AdamsIntegrator, org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = new double[completeState.length];
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(completeState, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        start(expandableStatefulODE.getTime(), completeState, d);
        nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        nordsieckStepInterpolator.storeTime(this.stepStart);
        double dFilterStep = this.stepSize;
        nordsieckStepInterpolator.rescale(dFilterStep);
        this.isLastStep = false;
        while (true) {
            nordsieckStepInterpolator.shift();
            double[] dArr2 = new double[completeState.length];
            int length = completeState.length;
            double[] dArr3 = new double[length];
            Array2DRowRealMatrix array2DRowRealMatrixUpdateHighOrderDerivativesPhase1 = null;
            double dErrorEstimation = 10.0d;
            while (dErrorEstimation >= 1.0d) {
                double d2 = this.stepStart + dFilterStep;
                nordsieckStepInterpolator.storeTime(d2);
                ExpandableStatefulODE expandable = getExpandable();
                expandable.getPrimaryMapper().insertEquationData(nordsieckStepInterpolator.getInterpolatedState(), dArr2);
                EquationsMapper[] secondaryMappers = expandable.getSecondaryMappers();
                int length2 = secondaryMappers.length;
                int i = 0;
                int i2 = 0;
                while (i2 < length2) {
                    secondaryMappers[i2].insertEquationData(nordsieckStepInterpolator.getInterpolatedSecondaryState(i), dArr2);
                    i++;
                    i2++;
                    length2 = length2;
                    secondaryMappers = secondaryMappers;
                }
                computeDerivatives(d2, dArr2, dArr);
                for (int i3 = 0; i3 < length; i3++) {
                    dArr3[i3] = dArr[i3] * dFilterStep;
                }
                array2DRowRealMatrixUpdateHighOrderDerivativesPhase1 = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, dArr3, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
                dErrorEstimation = errorEstimation(completeState, dArr2, dArr3, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
                if (dErrorEstimation >= 1.0d) {
                    dFilterStep = filterStep(dFilterStep * computeStepGrowShrinkFactor(dErrorEstimation), z, false);
                    nordsieckStepInterpolator.rescale(dFilterStep);
                }
            }
            this.stepSize = dFilterStep;
            boolean z2 = z;
            double d3 = dFilterStep;
            double d4 = this.stepStart + this.stepSize;
            double d5 = dErrorEstimation;
            nordsieckStepInterpolator.reinitialize(d4, this.stepSize, dArr3, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
            nordsieckStepInterpolator.storeTime(d4);
            System.arraycopy(dArr2, 0, completeState, 0, completeState.length);
            this.stepStart = acceptStep(nordsieckStepInterpolator, completeState, dArr, d);
            this.scaled = dArr3;
            this.nordsieck = array2DRowRealMatrixUpdateHighOrderDerivativesPhase1;
            nordsieckStepInterpolator.reinitialize(d4, this.stepSize, this.scaled, this.nordsieck);
            if (this.isLastStep) {
                dFilterStep = d3;
            } else {
                nordsieckStepInterpolator.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, completeState, d);
                    nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double dComputeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d5);
                double d6 = this.stepStart + dComputeStepGrowShrinkFactor;
                dFilterStep = filterStep(dComputeStepGrowShrinkFactor, z2, !z2 ? d6 > d : d6 < d);
                double d7 = this.stepStart + dFilterStep;
                if (!z2 ? d7 <= d : d7 >= d) {
                    dFilterStep = d - this.stepStart;
                }
                nordsieckStepInterpolator.rescale(dFilterStep);
            }
            if (this.isLastStep) {
                expandableStatefulODE.setTime(this.stepStart);
                expandableStatefulODE.setCompleteState(completeState);
                resetInternalState();
                return;
            }
            z = z2;
        }
    }
}
