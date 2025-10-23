package org.apache.commons.math.ode.nonstiff;

import java.util.Iterator;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.events.EventHandler;
import org.apache.commons.math.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math.ode.sampling.DummyStepInterpolator;
import org.apache.commons.math.ode.sampling.StepHandler;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/nonstiff/GraggBulirschStoerIntegrator.class */
public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private int maxOrder;
    private int[] sequence;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private double[] optimalStep;
    private double[][] coeff;
    private boolean performTest;
    private int maxChecks;
    private int maxIter;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private double orderControl1;
    private double orderControl2;
    private boolean useInterpolationError;
    private int mudif;

    public GraggBulirschStoerIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1.0d);
        setStepsizeControl(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1.0d);
        setStepsizeControl(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setStabilityCheck(boolean performStabilityCheck, int maxNumIter, int maxNumChecks, double stepsizeReductionFactor) {
        this.performTest = performStabilityCheck;
        this.maxIter = maxNumIter <= 0 ? 2 : maxNumIter;
        this.maxChecks = maxNumChecks <= 0 ? 1 : maxNumChecks;
        if (stepsizeReductionFactor < 1.0E-4d || stepsizeReductionFactor > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = stepsizeReductionFactor;
        }
    }

    public void setStepsizeControl(double control1, double control2, double control3, double control4) {
        if (control1 < 1.0E-4d || control1 > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = control1;
        }
        if (control2 < 1.0E-4d || control2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = control2;
        }
        if (control3 < 1.0E-4d || control3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = control3;
        }
        if (control4 < 1.0001d || control4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = control4;
        }
    }

    public void setOrderControl(int maximalOrder, double control1, double control2) {
        if (maximalOrder <= 6 || maximalOrder % 2 != 0) {
            this.maxOrder = 18;
        }
        if (control1 < 1.0E-4d || control1 > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = control1;
        }
        if (control2 < 1.0E-4d || control2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = control2;
        }
        initializeArrays();
    }

    @Override // org.apache.commons.math.ode.AbstractIntegrator, org.apache.commons.math.ode.ODEIntegrator
    public void addStepHandler(StepHandler handler) {
        super.addStepHandler(handler);
        initializeArrays();
    }

    @Override // org.apache.commons.math.ode.AbstractIntegrator, org.apache.commons.math.ode.ODEIntegrator
    public void addEventHandler(EventHandler function, double maxCheckInterval, double convergence, int maxIterationCount) {
        super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount);
        initializeArrays();
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [double[], double[][]] */
    private void initializeArrays() {
        int size = this.maxOrder / 2;
        if (this.sequence == null || this.sequence.length != size) {
            this.sequence = new int[size];
            this.costPerStep = new int[size];
            this.coeff = new double[size];
            this.costPerTimeUnit = new double[size];
            this.optimalStep = new double[size];
        }
        if (requiresDenseOutput()) {
            for (int k = 0; k < size; k++) {
                this.sequence[k] = (4 * k) + 2;
            }
        } else {
            for (int k2 = 0; k2 < size; k2++) {
                this.sequence[k2] = 2 * (k2 + 1);
            }
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int k3 = 1; k3 < size; k3++) {
            this.costPerStep[k3] = this.costPerStep[k3 - 1] + this.sequence[k3];
        }
        int k4 = 0;
        while (k4 < size) {
            this.coeff[k4] = k4 > 0 ? new double[k4] : null;
            for (int l = 0; l < k4; l++) {
                double ratio = this.sequence[k4] / this.sequence[(k4 - l) - 1];
                this.coeff[k4][l] = 1.0d / ((ratio * ratio) - 1.0d);
            }
            k4++;
        }
    }

    public void setInterpolationControl(boolean useInterpolationErrorForControl, int mudifControlParameter) {
        this.useInterpolationError = useInterpolationErrorForControl;
        if (mudifControlParameter <= 0 || mudifControlParameter >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = mudifControlParameter;
        }
    }

    private void rescale(double[] y1, double[] y2, double[] scale) {
        if (this.vecAbsoluteTolerance == null) {
            for (int i = 0; i < scale.length; i++) {
                double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
                scale[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * yi);
            }
            return;
        }
        for (int i2 = 0; i2 < scale.length; i2++) {
            double yi2 = FastMath.max(FastMath.abs(y1[i2]), FastMath.abs(y2[i2]));
            scale[i2] = this.vecAbsoluteTolerance[i2] + (this.vecRelativeTolerance[i2] * yi2);
        }
    }

    private boolean tryStep(double t0, double[] y0, double step, int k, double[] scale, double[][] f, double[] yMiddle, double[] yEnd, double[] yTmp) throws DerivativeException {
        int n = this.sequence[k];
        double subStep = step / n;
        double subStep2 = 2.0d * subStep;
        double t = t0 + subStep;
        for (int i = 0; i < y0.length; i++) {
            yTmp[i] = y0[i];
            yEnd[i] = y0[i] + (subStep * f[0][i]);
        }
        computeDerivatives(t, yEnd, f[1]);
        for (int j = 1; j < n; j++) {
            if (2 * j == n) {
                System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
            }
            t += subStep;
            for (int i2 = 0; i2 < y0.length; i2++) {
                double middle = yEnd[i2];
                yEnd[i2] = yTmp[i2] + (subStep2 * f[j][i2]);
                yTmp[i2] = middle;
            }
            computeDerivatives(t, yEnd, f[j + 1]);
            if (this.performTest && j <= this.maxChecks && k < this.maxIter) {
                double initialNorm = 0.0d;
                for (int l = 0; l < scale.length; l++) {
                    double ratio = f[0][l] / scale[l];
                    initialNorm += ratio * ratio;
                }
                double deltaNorm = 0.0d;
                for (int l2 = 0; l2 < scale.length; l2++) {
                    double ratio2 = (f[j + 1][l2] - f[0][l2]) / scale[l2];
                    deltaNorm += ratio2 * ratio2;
                }
                if (deltaNorm > 4.0d * FastMath.max(1.0E-15d, initialNorm)) {
                    return false;
                }
            }
        }
        for (int i3 = 0; i3 < y0.length; i3++) {
            yEnd[i3] = 0.5d * (yTmp[i3] + yEnd[i3] + (subStep * f[n][i3]));
        }
        return true;
    }

    private void extrapolate(int offset, int k, double[][] diag, double[] last) {
        for (int j = 1; j < k; j++) {
            for (int i = 0; i < last.length; i++) {
                diag[(k - j) - 1][i] = diag[k - j][i] + (this.coeff[k + offset][j - 1] * (diag[k - j][i] - diag[(k - j) - 1][i]));
            }
        }
        for (int i2 = 0; i2 < last.length; i2++) {
            last[i2] = diag[0][i2] + (this.coeff[k + offset][k - 1] * (diag[0][i2] - last[i2]));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v214 */
    /* JADX WARN: Type inference failed for: r0v228 */
    /* JADX WARN: Type inference failed for: r0v23, types: [double[], double[][]] */
    /* JADX WARN: Type inference failed for: r0v235 */
    /* JADX WARN: Type inference failed for: r0v236 */
    /* JADX WARN: Type inference failed for: r0v243 */
    /* JADX WARN: Type inference failed for: r0v250 */
    /* JADX WARN: Type inference failed for: r0v251 */
    /* JADX WARN: Type inference failed for: r0v252 */
    /* JADX WARN: Type inference failed for: r0v28, types: [double[], double[][]] */
    /* JADX WARN: Type inference failed for: r0v34, types: [double[][]] */
    /* JADX WARN: Type inference failed for: r0v398 */
    /* JADX WARN: Type inference failed for: r0v402 */
    /* JADX WARN: Type inference failed for: r14v0, types: [org.apache.commons.math.ode.nonstiff.GraggBulirschStoerIntegrator] */
    /* JADX WARN: Type inference failed for: r1v253 */
    /* JADX WARN: Type inference failed for: r1v254, types: [double] */
    /* JADX WARN: Type inference failed for: r2v69, types: [double] */
    /* JADX WARN: Type inference failed for: r31v1 */
    /* JADX WARN: Type inference failed for: r31v2 */
    /* JADX WARN: Type inference failed for: r31v3 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31, types: [double] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40, types: [double] */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44, types: [double] */
    /* JADX WARN: Type inference failed for: r6v2, types: [double[][]] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    @Override
    // org.apache.commons.math.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math.ode.FirstOrderIntegrator
    public double integrate(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2, double[] dArr2) throws DerivativeException, IntegratorException {
        double[][] dArr3;
        AbstractStepInterpolator dummyStepInterpolator;
        int iMin;
        sanityChecks(firstOrderDifferentialEquations, d, dArr, d2, dArr2);
        setEquations(firstOrderDifferentialEquations);
        resetEvaluations();
        boolean z = d2 > d;
        double[] dArr4 = new double[dArr.length];
        double[] dArr5 = new double[dArr.length];
        double[] dArr6 = new double[dArr.length];
        double[] dArr7 = new double[dArr.length];
        ??r0 = new double[this.sequence.length - 1];
        ??r02 = new double[this.sequence.length - 1];
        for (int i = 0; i < this.sequence.length - 1; i++) {
            r0[i] = new double[dArr.length];
            r02[i] = new double[dArr.length];
        }
        ??r03 = new double[this.sequence.length][];
        for (int i2 = 0; i2 < this.sequence.length; i2++) {
            r03[i2] = new double[this.sequence[i2] + 1];
            r03[i2][0] = dArr4;
            for (int i3 = 0; i3 < this.sequence[i2]; i3++) {
                r03[i2][i3 + 1] = new double[dArr.length];
            }
        }
        if (dArr2 != dArr) {
            System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        }
        double[] dArr8 = new double[dArr.length];
        boolean zRequiresDenseOutput = requiresDenseOutput();
        if (zRequiresDenseOutput) {
            dArr3 = new double[1 + (2 * this.sequence.length)];
            for (int i4 = 0; i4 < dArr3.length; i4++) {
                dArr3[i4] = new double[dArr.length];
            }
        } else {
            dArr3 = new double[]{new double[dArr.length]};
        }
        double[] dArr9 = new double[this.mainSetDimension];
        rescale(dArr2, dArr2, dArr9);
        int iMax = FastMath.max(1, FastMath.min(this.sequence.length - 2, (int) FastMath.floor(0.5d - (0.6d * FastMath.log10(FastMath.max(1.0E-10d, this.vecRelativeTolerance == null ? this.scalRelativeTolerance : this.vecRelativeTolerance[0]))))));
        if (zRequiresDenseOutput) {
            dummyStepInterpolator = new GraggBulirschStoerStepInterpolator(dArr2, dArr4, dArr5, dArr8, dArr3, z);
        } else {
            dummyStepInterpolator = new DummyStepInterpolator(dArr2, dArr8, z);
        }
        dummyStepInterpolator.storeTime(d);
        this.stepStart = d;
        double dMin = 0.0d;
        double dMax = Double.MAX_VALUE;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = true;
        boolean z5 = false;
        Iterator<StepHandler> it2 = this.stepHandlers.iterator();
        while (it2.hasNext()) {
            it2.next().reset();
        }
        setStateInitialized(false);
        this.costPerTimeUnit[0] = 0.0d;
        this.isLastStep = false;
        do {
            boolean z6 = false;
            if (z4) {
                dummyStepInterpolator.shift();
                if (!z5) {
                    computeDerivatives(this.stepStart, dArr2, dArr4);
                }
                if (z3) {
                    dMin = initializeStep(firstOrderDifferentialEquations, z, (2 * iMax) + 1, dArr9, this.stepStart, dArr2, dArr4, dArr6, dArr7);
                }
                z4 = false;
            }
            this.stepSize = dMin;
            if ((z && this.stepStart + this.stepSize > d2) || (!z && this.stepStart + this.stepSize < d2)) {
                this.stepSize = d2 - this.stepStart;
            }
            double d3 = this.stepStart + this.stepSize;
            this.isLastStep = z ? d3 >= d2 : d3 <= d2;
            int i5 = -1;
            boolean z7 = true;
            while (z7) {
                i5++;
                if (!tryStep(this.stepStart, dArr2, this.stepSize, i5, dArr9, r03[i5], i5 == 0 ? dArr3[0] : r0[i5 - 1], i5 == 0 ? dArr5 : r02[i5 - 1], dArr6)) {
                    dMin = FastMath.abs(filterStep(this.stepSize * this.stabilityReduction, z, false));
                    z6 = true;
                    z7 = false;
                } else if (i5 > 0) {
                    extrapolate(0, i5, r02, dArr5);
                    rescale(dArr2, dArr5, dArr9);
                    double d4 = 0.0d;
                    for (int i6 = 0; i6 < this.mainSetDimension; i6++) {
                        double dAbs = FastMath.abs(dArr5[i6] - r02[0][i6]) / dArr9[i6];
                        d4 += dAbs * dAbs;
                    }
                    double dSqrt = FastMath.sqrt(d4 / this.mainSetDimension);
                    if (dSqrt > 1.0E15d || (i5 > 1 && dSqrt > dMax)) {
                        dMin = FastMath.abs(filterStep(this.stepSize * this.stabilityReduction, z, false));
                        z6 = true;
                        z7 = false;
                    } else {
                        dMax = FastMath.max(4.0d * dSqrt, 1.0d);
                        double d5 = 1.0d / ((2 * i5) + 1);
                        double dPow = this.stepControl2 / FastMath.pow(dSqrt / this.stepControl1, d5);
                        double dPow2 = FastMath.pow(this.stepControl3, d5);
                        this.optimalStep[i5] = FastMath.abs(filterStep(this.stepSize * FastMath.max(dPow2 / this.stepControl4, FastMath.min(1.0d / dPow2, dPow)), z, true));
                        this.costPerTimeUnit[i5] = this.costPerStep[i5] / this.optimalStep[i5];
                        switch (i5 - iMax) {
                            case -1:
                                if (iMax <= 1 || z2) {
                                    break;
                                } else if (dSqrt <= 1.0d) {
                                    z7 = false;
                                    break;
                                } else {
                                    double d6 = (this.sequence[iMax] * this.sequence[iMax + 1]) / (this.sequence[0] * this.sequence[0]);
                                    if (dSqrt > d6 * d6) {
                                        z6 = true;
                                        z7 = false;
                                        iMax = i5;
                                        if (iMax > 1 && this.costPerTimeUnit[iMax - 1] < this.orderControl1 * this.costPerTimeUnit[iMax]) {
                                            iMax--;
                                        }
                                        dMin = this.optimalStep[iMax];
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                                break;
                            case 0:
                                if (dSqrt <= 1.0d) {
                                    z7 = false;
                                    break;
                                } else {
                                    double d7 = this.sequence[i5 + 1] / this.sequence[0];
                                    if (dSqrt > d7 * d7) {
                                        z6 = true;
                                        z7 = false;
                                        if (iMax > 1 && this.costPerTimeUnit[iMax - 1] < this.orderControl1 * this.costPerTimeUnit[iMax]) {
                                            iMax--;
                                        }
                                        dMin = this.optimalStep[iMax];
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                            case 1:
                                if (dSqrt > 1.0d) {
                                    z6 = true;
                                    if (iMax > 1 && this.costPerTimeUnit[iMax - 1] < this.orderControl1 * this.costPerTimeUnit[iMax]) {
                                        iMax--;
                                    }
                                    dMin = this.optimalStep[iMax];
                                }
                                z7 = false;
                                break;
                            default:
                                if (!z3 && !this.isLastStep) {
                                    break;
                                } else if (dSqrt <= 1.0d) {
                                    z7 = false;
                                    break;
                                } else {
                                    break;
                                }
                                break;
                        }
                    }
                }
            }
            if (!z6) {
                computeDerivatives(this.stepStart + this.stepSize, dArr5, dArr8);
            }
            double maxStep = getMaxStep();
            if (zRequiresDenseOutput && !z6) {
                for (int i7 = 1; i7 <= i5; i7++) {
                    extrapolate(0, i7, r0, dArr3[0]);
                }
                int i8 = ((2 * i5) - this.mudif) + 3;
                for (int i9 = 0; i9 < i8; i9++) {
                    int i10 = i9 / 2;
                    double dPow3 = FastMath.pow(0.5d * this.sequence[i10], i9);
                    int length = r03[i10].length / 2;
                    for (int i11 = 0; i11 < dArr.length; i11++) {
                        dArr3[i9 + 1][i11] = dPow3 * r03[i10][length + i9][i11];
                    }
                    for (int i12 = 1; i12 <= i5 - i10; i12++) {
                        double dPow4 = FastMath.pow(0.5d * this.sequence[i12 + i10], i9);
                        int length2 = r03[i10 + i12].length / 2;
                        for (int i13 = 0; i13 < dArr.length; i13++) {
                            r0[i12 - 1][i13] = dPow4 * r03[i10 + i12][length2 + i9][i13];
                        }
                        extrapolate(i10, i12, r0, dArr3[i9 + 1]);
                    }
                    for (int i14 = 0; i14 < dArr.length; i14++) {
                        double[] dArr10 = dArr3[i9 + 1];
                        int i15 = i14;
                        dArr10[i15] = dArr10[i15] * this.stepSize;
                    }
                    for (int i16 = (i9 + 1) / 2; i16 <= i5; i16++) {
                        for (int length3 = r03[i16].length - 1; length3 >= 2 * (i9 + 1); length3--) {
                            for (int i17 = 0; i17 < dArr.length; i17++) {
                                ??r04 = r03[i16][length3];
                                int i18 = i17;
                                r04[i18] = r04[i18] - r03[i16][length3 - 2][i17];
                            }
                        }
                    }
                }
                if (i8 >= 0) {
                    GraggBulirschStoerStepInterpolator graggBulirschStoerStepInterpolator = (GraggBulirschStoerStepInterpolator) dummyStepInterpolator;
                    graggBulirschStoerStepInterpolator.computeCoefficients(i8, this.stepSize);
                    if (this.useInterpolationError) {
                        double dEstimateError = graggBulirschStoerStepInterpolator.estimateError(dArr9);
                        maxStep = FastMath.abs(this.stepSize / FastMath.max(FastMath.pow(dEstimateError, 1.0d / (i8 + 4)), 0.01d));
                        if (dEstimateError > 10.0d) {
                            dMin = maxStep;
                            z6 = true;
                        }
                    }
                }
            }
            if (!z6) {
                dummyStepInterpolator.storeTime(this.stepStart + this.stepSize);
                this.stepStart = acceptStep(dummyStepInterpolator, dArr5, dArr8, d2);
                dummyStepInterpolator.storeTime(this.stepStart);
                System.arraycopy(dArr5, 0, dArr2, 0, dArr.length);
                System.arraycopy(dArr8, 0, dArr4, 0, dArr.length);
                z5 = true;
                if (i5 == 1) {
                    iMin = 2;
                    if (z2) {
                        iMin = 1;
                    }
                } else if (i5 <= iMax) {
                    iMin = i5;
                    if (this.costPerTimeUnit[i5 - 1] < this.orderControl1 * this.costPerTimeUnit[i5]) {
                        iMin = i5 - 1;
                    } else if (this.costPerTimeUnit[i5] < this.orderControl2 * this.costPerTimeUnit[i5 - 1]) {
                        iMin = FastMath.min(i5 + 1, this.sequence.length - 2);
                    }
                } else {
                    iMin = i5 - 1;
                    if (i5 > 2 && this.costPerTimeUnit[i5 - 2] < this.orderControl1 * this.costPerTimeUnit[i5 - 1]) {
                        iMin = i5 - 2;
                    }
                    if (this.costPerTimeUnit[i5] < this.orderControl2 * this.costPerTimeUnit[iMin]) {
                        iMin = FastMath.min(i5, this.sequence.length - 2);
                    }
                }
                if (z2) {
                    iMax = FastMath.min(iMin, i5);
                    dMin = FastMath.min(FastMath.abs(this.stepSize), this.optimalStep[iMax]);
                } else {
                    if (iMin <= i5) {
                        dMin = this.optimalStep[iMin];
                    } else if (i5 < iMax && this.costPerTimeUnit[i5] < this.orderControl2 * this.costPerTimeUnit[i5 - 1]) {
                        dMin = filterStep((this.optimalStep[i5] * this.costPerStep[iMin + 1]) / this.costPerStep[i5], z, false);
                    } else {
                        dMin = filterStep((this.optimalStep[i5] * this.costPerStep[iMin]) / this.costPerStep[i5], z, false);
                    }
                    iMax = iMin;
                }
                z4 = true;
            }
            dMin = FastMath.min(dMin, maxStep);
            if (!z) {
                dMin = -dMin;
            }
            z3 = false;
            if (z6) {
                this.isLastStep = false;
                z2 = true;
            } else {
                z2 = false;
            }
        } while (!this.isLastStep);
        double d8 = this.stepStart;
        resetInternalState();
        return d8;
    }
}
