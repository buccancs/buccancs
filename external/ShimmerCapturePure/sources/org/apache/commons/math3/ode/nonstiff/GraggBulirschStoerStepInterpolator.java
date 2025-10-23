package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
    private static final long serialVersionUID = 20110928;
    private int currentDegree;
    private double[] errfac;
    private double[][] polynomials;
    private double[] y0Dot;
    private double[] y1;
    private double[] y1Dot;
    private double[][] yMidDots;

    public GraggBulirschStoerStepInterpolator() {
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        this.yMidDots = null;
        resetTables(-1);
    }

    GraggBulirschStoerStepInterpolator(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[][] dArr5, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super(dArr, z, equationsMapper, equationsMapperArr);
        this.y0Dot = dArr2;
        this.y1 = dArr3;
        this.y1Dot = dArr4;
        this.yMidDots = dArr5;
        resetTables(dArr5.length + 4);
    }

    GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator graggBulirschStoerStepInterpolator) {
        super(graggBulirschStoerStepInterpolator);
        int length = this.currentState.length;
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        this.yMidDots = null;
        if (graggBulirschStoerStepInterpolator.polynomials == null) {
            this.polynomials = null;
            this.currentDegree = -1;
            return;
        }
        resetTables(graggBulirschStoerStepInterpolator.currentDegree);
        int i = 0;
        while (true) {
            double[][] dArr = this.polynomials;
            if (i < dArr.length) {
                double[] dArr2 = new double[length];
                dArr[i] = dArr2;
                System.arraycopy(graggBulirschStoerStepInterpolator.polynomials[i], 0, dArr2, 0, length);
                i++;
            } else {
                this.currentDegree = graggBulirschStoerStepInterpolator.currentDegree;
                return;
            }
        }
    }

    private void resetTables(int i) {
        if (i < 0) {
            this.polynomials = null;
            this.errfac = null;
            this.currentDegree = -1;
            return;
        }
        int i2 = i + 1;
        double[][] dArr = new double[i2][];
        double[][] dArr2 = this.polynomials;
        if (dArr2 != null) {
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
            for (int length = this.polynomials.length; length < i2; length++) {
                dArr[length] = new double[this.currentState.length];
            }
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                dArr[i3] = new double[this.currentState.length];
            }
        }
        this.polynomials = dArr;
        if (i > 4) {
            this.errfac = new double[i - 4];
            int i4 = 0;
            while (true) {
                double[] dArr3 = this.errfac;
                if (i4 >= dArr3.length) {
                    break;
                }
                dArr3[i4] = 1.0d / (r1 * r1);
                int i5 = i4 + 1;
                double dSqrt = FastMath.sqrt(i5 / (i4 + 5)) * 0.5d;
                int i6 = 0;
                while (i6 <= i4) {
                    double[] dArr4 = this.errfac;
                    i6++;
                    dArr4[i4] = dArr4[i4] * (dSqrt / i6);
                }
                i4 = i5;
            }
        } else {
            this.errfac = null;
        }
        this.currentDegree = 0;
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new GraggBulirschStoerStepInterpolator(this);
    }

    public void computeCoefficients(int i, double d) {
        double[][] dArr = this.polynomials;
        if (dArr == null || dArr.length <= i + 4) {
            resetTables(i + 4);
        }
        this.currentDegree = i + 4;
        char c = 0;
        int i2 = 0;
        while (i2 < this.currentState.length) {
            double d2 = d * this.y0Dot[i2];
            double d3 = d * this.y1Dot[i2];
            double d4 = this.y1[i2] - this.currentState[i2];
            double d5 = d4 - d3;
            double d6 = d2 - d4;
            this.polynomials[c][i2] = this.currentState[i2];
            double[][] dArr2 = this.polynomials;
            dArr2[1][i2] = d4;
            dArr2[2][i2] = d5;
            dArr2[3][i2] = d6;
            if (i < 0) {
                return;
            }
            double d7 = ((this.currentState[i2] + this.y1[i2]) * 0.5d) + ((d5 + d6) * 0.125d);
            double[][] dArr3 = this.polynomials;
            double[] dArr4 = dArr3[4];
            double[][] dArr5 = this.yMidDots;
            dArr4[i2] = (dArr5[c][i2] - d7) * 16.0d;
            if (i > 0) {
                double[] dArr6 = dArr3[5];
                dArr6[i2] = (dArr5[1][i2] - (d4 + ((d5 - d6) * 0.25d))) * 16.0d;
                if (i > 1) {
                    dArr3[6][i2] = ((dArr5[2][i2] - (d3 - d2)) + dArr4[i2]) * 16.0d;
                    if (i > 2) {
                        dArr3[7][i2] = ((dArr5[3][i2] - ((d6 - d5) * 6.0d)) + (dArr6[i2] * 3.0d)) * 16.0d;
                        for (int i3 = 4; i3 <= i; i3++) {
                            double d8 = i3 * 0.5d * (i3 - 1);
                            double[][] dArr7 = this.polynomials;
                            dArr7[i3 + 4][i2] = ((this.yMidDots[i3][i2] + (d8 * dArr7[i3 + 2][i2])) - ((((2.0d * d8) * (i3 - 2)) * (i3 - 3)) * dArr7[i3][i2])) * 16.0d;
                        }
                    }
                }
            }
            i2++;
            c = 0;
        }
    }

    public double estimateError(double[] dArr) {
        double d = 0.0d;
        if (this.currentDegree < 5) {
            return 0.0d;
        }
        for (int i = 0; i < dArr.length; i++) {
            double d2 = this.polynomials[this.currentDegree][i] / dArr[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d / dArr.length) * this.errfac[this.currentDegree - 5];
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3;
        double d4;
        int length = this.currentState.length;
        double d5 = 1.0d - d;
        double d6 = d - 0.5d;
        double d7 = d * d5;
        double d8 = d7 * d7;
        double d9 = d7 * 2.0d * (1.0d - (d * 2.0d));
        double d10 = 1.0d / this.h;
        double d11 = 3.0d * d;
        double d12 = ((2.0d - d11) * d) / this.h;
        double d13 = (((d11 - 4.0d) * d) + 1.0d) / this.h;
        char c = 0;
        int i = 0;
        while (true) {
            double d14 = 0.0d;
            if (i >= length) {
                break;
            }
            int i2 = length;
            double[][] dArr = this.polynomials;
            double d15 = dArr[c][i];
            double d16 = dArr[1][i];
            double d17 = dArr[2][i];
            double d18 = dArr[3][i];
            this.interpolatedState[i] = d15 + ((d16 + (((d17 * d) + (d18 * d5)) * d5)) * d);
            this.interpolatedDerivatives[i] = (d16 * d10) + (d17 * d12) + (d18 * d13);
            int i3 = this.currentDegree;
            if (i3 > 3) {
                double d19 = this.polynomials[i3][i];
                int i4 = i3 - 1;
                while (i4 > 3) {
                    double d20 = d5;
                    double d21 = 1.0d / (i4 - 3);
                    d14 = ((d14 * d6) + d19) * d21;
                    d19 = this.polynomials[i4][i] + (d19 * d21 * d6);
                    i4--;
                    d5 = d20;
                }
                d3 = d5;
                double[] dArr2 = this.interpolatedState;
                dArr2[i] = dArr2[i] + (d8 * d19);
                double[] dArr3 = this.interpolatedDerivatives;
                d4 = d6;
                dArr3[i] = dArr3[i] + (((d14 * d8) + (d19 * d9)) / this.h);
            } else {
                d3 = d5;
                d4 = d6;
            }
            i++;
            d6 = d4;
            length = i2;
            d5 = d3;
            c = 0;
        }
        int i5 = length;
        if (this.h == 0.0d) {
            System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, i5);
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int length = this.currentState == null ? -1 : this.currentState.length;
        writeBaseExternal(objectOutput);
        objectOutput.writeInt(this.currentDegree);
        for (int i = 0; i <= this.currentDegree; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                objectOutput.writeDouble(this.polynomials[i][i2]);
            }
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double baseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        int i = objectInput.readInt();
        resetTables(i);
        this.currentDegree = i;
        for (int i2 = 0; i2 <= this.currentDegree; i2++) {
            for (int i3 = 0; i3 < length; i3++) {
                this.polynomials[i2][i3] = objectInput.readDouble();
            }
        }
        setInterpolatedTime(baseExternal);
    }
}
