package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class AdamsMoultonIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i + 1, d, d2, d3, d4);
    }

    public AdamsMoultonIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i + 1, d, d2, dArr, dArr2);
    }

    @Override
    // org.apache.commons.math3.ode.nonstiff.AdamsIntegrator, org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        boolean z;
        boolean z2;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z3 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        double[] dArr2 = new double[dArr.length];
        double[] dArr3 = new double[dArr.length];
        double[] dArr4 = new double[dArr.length];
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(dArr, z3, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        start(expandableStatefulODE.getTime(), dArr, d);
        nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        NordsieckStepInterpolator nordsieckStepInterpolator2 = nordsieckStepInterpolator;
        nordsieckStepInterpolator2.storeTime(this.stepStart);
        double dFilterStep = this.stepSize;
        nordsieckStepInterpolator2.rescale(dFilterStep);
        this.isLastStep = false;
        Array2DRowRealMatrix array2DRowRealMatrix = null;
        while (true) {
            Array2DRowRealMatrix array2DRowRealMatrixUpdateHighOrderDerivativesPhase1 = array2DRowRealMatrix;
            double d2 = dFilterStep;
            double dWalkInOptimizedOrder = 10.0d;
            while (dWalkInOptimizedOrder >= 1.0d) {
                this.stepSize = d2;
                boolean z4 = z3;
                double d3 = this.stepStart + this.stepSize;
                nordsieckStepInterpolator2.setInterpolatedTime(d3);
                ExpandableStatefulODE expandable = getExpandable();
                expandable.getPrimaryMapper().insertEquationData(nordsieckStepInterpolator2.getInterpolatedState(), dArr3);
                EquationsMapper[] secondaryMappers = expandable.getSecondaryMappers();
                int length = secondaryMappers.length;
                int i = 0;
                int i2 = 0;
                while (i2 < length) {
                    secondaryMappers[i2].insertEquationData(nordsieckStepInterpolator2.getInterpolatedSecondaryState(i), dArr3);
                    i++;
                    i2++;
                    d2 = d2;
                }
                double d4 = d2;
                computeDerivatives(d3, dArr3, dArr2);
                for (int i3 = 0; i3 < completeState.length; i3++) {
                    dArr4[i3] = this.stepSize * dArr2[i3];
                }
                array2DRowRealMatrixUpdateHighOrderDerivativesPhase1 = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, dArr4, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
                dWalkInOptimizedOrder = array2DRowRealMatrixUpdateHighOrderDerivativesPhase1.walkInOptimizedOrder(new Corrector(dArr, dArr4, dArr3));
                if (dWalkInOptimizedOrder >= 1.0d) {
                    z3 = z4;
                    double dFilterStep2 = filterStep(this.stepSize * computeStepGrowShrinkFactor(dWalkInOptimizedOrder), z3, false);
                    nordsieckStepInterpolator2.rescale(dFilterStep2);
                    d2 = dFilterStep2;
                } else {
                    z3 = z4;
                    d2 = d4;
                }
            }
            double d5 = d2;
            double d6 = this.stepStart + this.stepSize;
            computeDerivatives(d6, dArr3, dArr2);
            double[] dArr5 = new double[completeState.length];
            double d7 = dWalkInOptimizedOrder;
            for (int i4 = 0; i4 < completeState.length; i4++) {
                dArr5[i4] = this.stepSize * dArr2[i4];
            }
            updateHighOrderDerivativesPhase2(dArr4, dArr5, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
            System.arraycopy(dArr3, 0, dArr, 0, dArr.length);
            nordsieckStepInterpolator2.reinitialize(d6, this.stepSize, dArr5, array2DRowRealMatrixUpdateHighOrderDerivativesPhase1);
            nordsieckStepInterpolator2.storeTime(this.stepStart);
            nordsieckStepInterpolator2.shift();
            nordsieckStepInterpolator2.storeTime(d6);
            boolean z5 = z3;
            double[] dArr6 = dArr2;
            double[] dArr7 = completeState;
            double[] dArr8 = dArr2;
            NordsieckStepInterpolator nordsieckStepInterpolator3 = nordsieckStepInterpolator2;
            Array2DRowRealMatrix array2DRowRealMatrix2 = array2DRowRealMatrixUpdateHighOrderDerivativesPhase1;
            this.stepStart = acceptStep(nordsieckStepInterpolator2, dArr, dArr6, d);
            this.scaled = dArr5;
            this.nordsieck = array2DRowRealMatrix2;
            if (this.isLastStep) {
                z = z5;
                dFilterStep = d5;
            } else {
                nordsieckStepInterpolator3.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, dArr, d);
                    nordsieckStepInterpolator3.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double dComputeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d7);
                double d8 = this.stepStart + dComputeStepGrowShrinkFactor;
                if (!z5 ? d8 > d : d8 < d) {
                    z = z5;
                    z2 = false;
                } else {
                    z = z5;
                    z2 = true;
                }
                dFilterStep = filterStep(dComputeStepGrowShrinkFactor, z, z2);
                double d9 = this.stepStart + dFilterStep;
                if (!z ? d9 <= d : d9 >= d) {
                    dFilterStep = d - this.stepStart;
                }
                nordsieckStepInterpolator3.rescale(dFilterStep);
            }
            if (this.isLastStep) {
                expandableStatefulODE.setTime(this.stepStart);
                expandableStatefulODE.setCompleteState(dArr);
                resetInternalState();
                return;
            } else {
                z3 = z;
                nordsieckStepInterpolator2 = nordsieckStepInterpolator3;
                array2DRowRealMatrix = array2DRowRealMatrix2;
                completeState = dArr7;
                dArr2 = dArr8;
            }
        }
    }

    private class Corrector implements RealMatrixPreservingVisitor {
        private final double[] after;
        private final double[] before;
        private final double[] previous;
        private final double[] scaled;

        Corrector(double[] dArr, double[] dArr2, double[] dArr3) {
            this.previous = dArr;
            this.scaled = dArr2;
            this.after = dArr3;
            this.before = (double[]) dArr3.clone();
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            Arrays.fill(this.after, 0.0d);
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public void visit(int i, int i2, double d) {
            if ((i & 1) == 0) {
                double[] dArr = this.after;
                dArr[i2] = dArr[i2] - d;
            } else {
                double[] dArr2 = this.after;
                dArr2[i2] = dArr2[i2] + d;
            }
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public double end() {
            double d = 0.0d;
            int i = 0;
            while (true) {
                double[] dArr = this.after;
                if (i < dArr.length) {
                    dArr[i] = dArr[i] + this.previous[i] + this.scaled[i];
                    if (i < AdamsMoultonIntegrator.this.mainSetDimension) {
                        double dMax = FastMath.max(FastMath.abs(this.previous[i]), FastMath.abs(this.after[i]));
                        double d2 = (this.after[i] - this.before[i]) / (AdamsMoultonIntegrator.this.vecAbsoluteTolerance == null ? AdamsMoultonIntegrator.this.scalAbsoluteTolerance + (AdamsMoultonIntegrator.this.scalRelativeTolerance * dMax) : AdamsMoultonIntegrator.this.vecAbsoluteTolerance[i] + (AdamsMoultonIntegrator.this.vecRelativeTolerance[i] * dMax));
                        d += d2 * d2;
                    }
                    i++;
                } else {
                    return FastMath.sqrt(d / AdamsMoultonIntegrator.this.mainSetDimension);
                }
            }
        }
    }
}
