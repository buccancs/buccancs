package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;

/* loaded from: classes5.dex */
abstract class RungeKuttaStepInterpolator extends AbstractStepInterpolator {
    protected AbstractIntegrator integrator;
    protected double[] previousState;
    protected double[][] yDotK;

    protected RungeKuttaStepInterpolator() {
        this.previousState = null;
        this.yDotK = null;
        this.integrator = null;
    }

    RungeKuttaStepInterpolator(RungeKuttaStepInterpolator rungeKuttaStepInterpolator) {
        super(rungeKuttaStepInterpolator);
        if (rungeKuttaStepInterpolator.currentState != null) {
            this.previousState = (double[]) rungeKuttaStepInterpolator.previousState.clone();
            this.yDotK = new double[rungeKuttaStepInterpolator.yDotK.length][];
            int i = 0;
            while (true) {
                double[][] dArr = rungeKuttaStepInterpolator.yDotK;
                if (i >= dArr.length) {
                    break;
                }
                this.yDotK[i] = (double[]) dArr[i].clone();
                i++;
            }
        } else {
            this.previousState = null;
            this.yDotK = null;
        }
        this.integrator = null;
    }

    public void reinitialize(AbstractIntegrator abstractIntegrator, double[] dArr, double[][] dArr2, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        reinitialize(dArr, z, equationsMapper, equationsMapperArr);
        this.previousState = null;
        this.yDotK = dArr2;
        this.integrator = abstractIntegrator;
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    public void shift() {
        this.previousState = (double[]) this.currentState.clone();
        super.shift();
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        writeBaseExternal(objectOutput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        for (int i = 0; i < length; i++) {
            objectOutput.writeDouble(this.previousState[i]);
        }
        double[][] dArr = this.yDotK;
        int length2 = dArr != null ? dArr.length : -1;
        objectOutput.writeInt(length2);
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length; i3++) {
                objectOutput.writeDouble(this.yDotK[i2][i3]);
            }
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double[][] dArr;
        double baseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        if (length < 0) {
            this.previousState = null;
        } else {
            this.previousState = new double[length];
            for (int i = 0; i < length; i++) {
                this.previousState[i] = objectInput.readDouble();
            }
        }
        int i2 = objectInput.readInt();
        if (i2 < 0) {
            dArr = null;
        } else {
            dArr = new double[i2][];
        }
        this.yDotK = dArr;
        for (int i3 = 0; i3 < i2; i3++) {
            this.yDotK[i3] = length < 0 ? null : new double[length];
            for (int i4 = 0; i4 < length; i4++) {
                this.yDotK[i3][i4] = objectInput.readDouble();
            }
        }
        this.integrator = null;
        if (this.currentState != null) {
            setInterpolatedTime(baseExternal);
        } else {
            this.interpolatedTime = baseExternal;
        }
    }
}
