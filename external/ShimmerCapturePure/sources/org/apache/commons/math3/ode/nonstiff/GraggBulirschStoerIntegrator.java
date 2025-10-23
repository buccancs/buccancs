package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private double[][] coeff;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private int maxChecks;
    private int maxIter;
    private int maxOrder;
    private int mudif;
    private double[] optimalStep;
    private double orderControl1;
    private double orderControl2;
    private boolean performTest;
    private int[] sequence;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private boolean useInterpolationError;

    public GraggBulirschStoerIntegrator(double d, double d2, double d3, double d4) {
        super(METHOD_NAME, d, d2, d3, d4);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double d, double d2, double[] dArr, double[] dArr2) {
        super(METHOD_NAME, d, d2, dArr, dArr2);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setControlFactors(double d, double d2, double d3, double d4) {
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = d2;
        }
        if (d3 < 1.0E-4d || d3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = d3;
        }
        if (d4 < 1.0001d || d4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = d4;
        }
    }

    public void setInterpolationControl(boolean z, int i) {
        this.useInterpolationError = z;
        if (i <= 0 || i >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = i;
        }
    }

    public void setStabilityCheck(boolean z, int i, int i2, double d) {
        this.performTest = z;
        if (i <= 0) {
            i = 2;
        }
        this.maxIter = i;
        if (i2 <= 0) {
            i2 = 1;
        }
        this.maxChecks = i2;
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = d;
        }
    }

    public void setOrderControl(int i, double d, double d2) {
        if (i <= 6 || i % 2 != 0) {
            this.maxOrder = 18;
        }
        if (d < 1.0E-4d || d > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = d2;
        }
        initializeArrays();
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator, org.apache.commons.math3.ode.ODEIntegrator
    public void addStepHandler(StepHandler stepHandler) {
        super.addStepHandler(stepHandler);
        initializeArrays();
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator, org.apache.commons.math3.ode.ODEIntegrator
    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        super.addEventHandler(eventHandler, d, d2, i, univariateSolver);
        initializeArrays();
    }

    private void initializeArrays() {
        int i = this.maxOrder / 2;
        int[] iArr = this.sequence;
        if (iArr == null || iArr.length != i) {
            this.sequence = new int[i];
            this.costPerStep = new int[i];
            this.coeff = new double[i][];
            this.costPerTimeUnit = new double[i];
            this.optimalStep = new double[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.sequence[i2] = (i2 * 4) + 2;
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int i3 = 1; i3 < i; i3++) {
            int[] iArr2 = this.costPerStep;
            iArr2[i3] = iArr2[i3 - 1] + this.sequence[i3];
        }
        int i4 = 0;
        while (i4 < i) {
            this.coeff[i4] = i4 > 0 ? new double[i4] : null;
            for (int i5 = 0; i5 < i4; i5++) {
                int[] iArr3 = this.sequence;
                double d = iArr3[i4] / iArr3[(i4 - i5) - 1];
                this.coeff[i4][i5] = 1.0d / ((d * d) - 1.0d);
            }
            i4++;
        }
    }

    private void rescale(double[] dArr, double[] dArr2, double[] dArr3) {
        int i = 0;
        if (this.vecAbsoluteTolerance == null) {
            while (i < dArr3.length) {
                dArr3[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
                i++;
            }
            return;
        }
        while (i < dArr3.length) {
            dArr3[i] = this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
            i++;
        }
    }

    private boolean tryStep(double d, double[] dArr, double d2, int i, double[] dArr2, double[][] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) throws MaxCountExceededException, DimensionMismatchException {
        double d3;
        GraggBulirschStoerIntegrator graggBulirschStoerIntegrator = this;
        int i2 = i;
        double[] dArr7 = dArr2;
        int i3 = graggBulirschStoerIntegrator.sequence[i2];
        double d4 = d2 / i3;
        double d5 = 2.0d * d4;
        double d6 = d + d4;
        int i4 = 0;
        for (int i5 = 0; i5 < dArr.length; i5++) {
            dArr6[i5] = dArr[i5];
            dArr5[i5] = dArr[i5] + (dArr3[0][i5] * d4);
        }
        graggBulirschStoerIntegrator.computeDerivatives(d6, dArr5, dArr3[1]);
        int i6 = 1;
        while (i6 < i3) {
            if (i6 * 2 == i3) {
                System.arraycopy(dArr5, i4, dArr4, i4, dArr.length);
            }
            d6 += d4;
            for (int i7 = 0; i7 < dArr.length; i7++) {
                double d7 = dArr5[i7];
                dArr5[i7] = dArr6[i7] + (dArr3[i6][i7] * d5);
                dArr6[i7] = d7;
            }
            int i8 = i6 + 1;
            graggBulirschStoerIntegrator.computeDerivatives(d6, dArr5, dArr3[i8]);
            if (!graggBulirschStoerIntegrator.performTest || i6 > graggBulirschStoerIntegrator.maxChecks || i2 >= graggBulirschStoerIntegrator.maxIter) {
                d3 = d5;
            } else {
                d3 = d5;
                double d8 = 0.0d;
                for (int i9 = 0; i9 < dArr7.length; i9++) {
                    double d9 = dArr3[0][i9] / dArr7[i9];
                    d8 += d9 * d9;
                }
                double d10 = 0.0d;
                for (int i10 = 0; i10 < dArr7.length; i10++) {
                    double d11 = (dArr3[i8][i10] - dArr3[0][i10]) / dArr7[i10];
                    d10 += d11 * d11;
                }
                if (d10 > FastMath.max(1.0E-15d, d8) * 4.0d) {
                    return false;
                }
            }
            graggBulirschStoerIntegrator = this;
            i2 = i;
            dArr7 = dArr2;
            i6 = i8;
            d5 = d3;
            i4 = 0;
        }
        for (int i11 = 0; i11 < dArr.length; i11++) {
            dArr5[i11] = (dArr6[i11] + dArr5[i11] + (dArr3[i3][i11] * d4)) * 0.5d;
        }
        return true;
    }

    private void extrapolate(int i, int i2, double[][] dArr, double[] dArr2) {
        int i3 = 1;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            for (int i4 = 0; i4 < dArr2.length; i4++) {
                int i5 = i2 - i3;
                double[] dArr3 = dArr[i5 - 1];
                double d = dArr[i5][i4];
                dArr3[i4] = d + (this.coeff[i2 + i][i3 - 1] * (d - dArr3[i4]));
            }
            i3++;
        }
        for (int i6 = 0; i6 < dArr2.length; i6++) {
            double d2 = dArr[0][i6];
            dArr2[i6] = d2 + (this.coeff[i2 + i][i2 - 1] * (d2 - dArr2[i6]));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:191:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0605 A[PHI: r4 r23 r27 r33 r39
  0x0605: PHI (r4v23 int) = (r4v20 int), (r4v24 int), (r4v26 int) binds: [B:214:0x05f9, B:205:0x05c1, B:201:0x059c] A[DONT_GENERATE, DONT_INLINE]
  0x0605: PHI (r23v8 double[]) = (r23v7 double[]), (r23v9 double[]), (r23v9 double[]) binds: [B:214:0x05f9, B:205:0x05c1, B:201:0x059c] A[DONT_GENERATE, DONT_INLINE]
  0x0605: PHI (r27v10 double[]) = (r27v8 double[]), (r27v11 double[]), (r27v13 double[]) binds: [B:214:0x05f9, B:205:0x05c1, B:201:0x059c] A[DONT_GENERATE, DONT_INLINE]
  0x0605: PHI (r33v7 double[]) = (r33v5 double[]), (r33v8 double[]), (r33v10 double[]) binds: [B:214:0x05f9, B:205:0x05c1, B:201:0x059c] A[DONT_GENERATE, DONT_INLINE]
  0x0605: PHI (r39v4 double[]) = (r39v3 double[]), (r39v5 double[]), (r39v7 double[]) binds: [B:214:0x05f9, B:205:0x05c1, B:201:0x059c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0690  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x06a6 A[LOOP:3: B:25:0x013a->B:239:0x06a6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0696 A[SYNTHETIC] */
    @Override
    // org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r50, double r51) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 1728
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }
}
