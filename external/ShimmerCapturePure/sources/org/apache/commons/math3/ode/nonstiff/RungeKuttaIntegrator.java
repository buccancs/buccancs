package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    protected RungeKuttaIntegrator(String str, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d) {
        super(str);
        this.c = dArr;
        this.a = dArr2;
        this.b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        this.step = FastMath.abs(d);
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        char c = 0;
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        int length = this.c.length;
        int i = length + 1;
        double[][] dArr2 = new double[i][];
        for (int i2 = 0; i2 < i; i2++) {
            dArr2[i2] = new double[completeState.length];
        }
        double[] dArr3 = (double[]) completeState.clone();
        double[] dArr4 = new double[completeState.length];
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator;
        double[] dArr5 = dArr4;
        double[] dArr6 = dArr3;
        rungeKuttaStepInterpolator.reinitialize(this, dArr3, dArr2, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        rungeKuttaStepInterpolator2.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        if (z) {
            double d2 = this.stepStart;
            double d3 = this.step;
            if (d2 + d3 >= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = d3;
            }
        } else {
            double d4 = this.stepStart;
            double d5 = this.step;
            if (d4 - d5 <= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = -d5;
            }
        }
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        this.isLastStep = false;
        while (true) {
            rungeKuttaStepInterpolator2.shift();
            computeDerivatives(this.stepStart, dArr, dArr2[c]);
            int i3 = 1;
            while (i3 < i) {
                int i4 = 0;
                while (i4 < completeState.length) {
                    int i5 = i3 - 1;
                    double d6 = this.a[i5][c] * dArr2[c][i4];
                    for (int i6 = 1; i6 < i3; i6++) {
                        d6 += this.a[i5][i6] * dArr2[i6][i4];
                    }
                    dArr6[i4] = dArr[i4] + (this.stepSize * d6);
                    i4++;
                    z = z;
                    c = 0;
                }
                computeDerivatives(this.stepStart + (this.c[i3 - 1] * this.stepSize), dArr6, dArr2[i3]);
                i3++;
                rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator2;
                z = z;
                c = 0;
            }
            boolean z2 = z;
            double[] dArr7 = dArr6;
            RungeKuttaStepInterpolator rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator2;
            for (int i7 = 0; i7 < completeState.length; i7++) {
                double d7 = this.b[0] * dArr2[0][i7];
                for (int i8 = 1; i8 < i; i8++) {
                    d7 += this.b[i8] * dArr2[i8][i7];
                }
                dArr7[i7] = dArr[i7] + (this.stepSize * d7);
            }
            rungeKuttaStepInterpolator3.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr7, 0, dArr, 0, completeState.length);
            double[] dArr8 = dArr5;
            System.arraycopy(dArr2[length], 0, dArr8, 0, completeState.length);
            this.stepStart = acceptStep(rungeKuttaStepInterpolator3, dArr, dArr8, d);
            if (!this.isLastStep) {
                rungeKuttaStepInterpolator3.storeTime(this.stepStart);
                double d8 = this.stepStart + this.stepSize;
                if (!z2 ? d8 <= d : d8 >= d) {
                    this.stepSize = d - this.stepStart;
                }
            }
            if (this.isLastStep) {
                expandableStatefulODE.setTime(this.stepStart);
                expandableStatefulODE.setCompleteState(dArr);
                this.stepStart = Double.NaN;
                this.stepSize = Double.NaN;
                return;
            }
            dArr5 = dArr8;
            dArr6 = dArr7;
            rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator3;
            z = z2;
            c = 0;
        }
    }

    public double[] singleStep(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2) throws MaxCountExceededException, DimensionMismatchException {
        double[] dArr2 = (double[]) dArr.clone();
        int length = this.c.length + 1;
        double[][] dArr3 = new double[length][];
        for (int i = 0; i < length; i++) {
            dArr3[i] = new double[dArr.length];
        }
        double[] dArr4 = (double[]) dArr.clone();
        double d3 = d2 - d;
        firstOrderDifferentialEquations.computeDerivatives(d, dArr2, dArr3[0]);
        for (int i2 = 1; i2 < length; i2++) {
            for (int i3 = 0; i3 < dArr.length; i3++) {
                int i4 = i2 - 1;
                double d4 = this.a[i4][0] * dArr3[0][i3];
                for (int i5 = 1; i5 < i2; i5++) {
                    d4 += this.a[i4][i5] * dArr3[i5][i3];
                }
                dArr4[i3] = dArr2[i3] + (d4 * d3);
            }
            firstOrderDifferentialEquations.computeDerivatives((this.c[i2 - 1] * d3) + d, dArr4, dArr3[i2]);
        }
        for (int i6 = 0; i6 < dArr.length; i6++) {
            double d5 = this.b[0] * dArr3[0][i6];
            for (int i7 = 1; i7 < length; i7++) {
                d5 += this.b[i7] * dArr3[i7][i6];
            }
            dArr2[i6] = dArr2[i6] + (d5 * d3);
        }
        return dArr2;
    }
}
