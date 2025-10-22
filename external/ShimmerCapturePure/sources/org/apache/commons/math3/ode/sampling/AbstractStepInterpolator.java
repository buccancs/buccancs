package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.EquationsMapper;

/* loaded from: classes5.dex */
public abstract class AbstractStepInterpolator implements StepInterpolator {
    protected double[] currentState;
    protected double h;
    protected double[] interpolatedDerivatives;
    protected double[] interpolatedPrimaryDerivatives;
    protected double[] interpolatedPrimaryState;
    protected double[][] interpolatedSecondaryDerivatives;
    protected double[][] interpolatedSecondaryState;
    protected double[] interpolatedState;
    protected double interpolatedTime;
    private boolean dirtyState;
    private boolean finalized;
    private boolean forward;
    private double globalCurrentTime;
    private double globalPreviousTime;
    private EquationsMapper primaryMapper;
    private EquationsMapper[] secondaryMappers;
    private double softCurrentTime;
    private double softPreviousTime;

    protected AbstractStepInterpolator() {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = null;
        this.finalized = false;
        this.forward = true;
        this.dirtyState = true;
        this.primaryMapper = null;
        this.secondaryMappers = null;
        allocateInterpolatedArrays(-1);
    }

    protected AbstractStepInterpolator(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        this.secondaryMappers = equationsMapperArr == null ? null : (EquationsMapper[]) equationsMapperArr.clone();
        allocateInterpolatedArrays(dArr.length);
    }

    protected AbstractStepInterpolator(AbstractStepInterpolator abstractStepInterpolator) {
        this.globalPreviousTime = abstractStepInterpolator.globalPreviousTime;
        this.globalCurrentTime = abstractStepInterpolator.globalCurrentTime;
        this.softPreviousTime = abstractStepInterpolator.softPreviousTime;
        this.softCurrentTime = abstractStepInterpolator.softCurrentTime;
        this.h = abstractStepInterpolator.h;
        this.interpolatedTime = abstractStepInterpolator.interpolatedTime;
        double[] dArr = abstractStepInterpolator.currentState;
        if (dArr == null) {
            this.currentState = null;
            this.primaryMapper = null;
            this.secondaryMappers = null;
            allocateInterpolatedArrays(-1);
        } else {
            this.currentState = (double[]) dArr.clone();
            this.interpolatedState = (double[]) abstractStepInterpolator.interpolatedState.clone();
            this.interpolatedDerivatives = (double[]) abstractStepInterpolator.interpolatedDerivatives.clone();
            this.interpolatedPrimaryState = (double[]) abstractStepInterpolator.interpolatedPrimaryState.clone();
            this.interpolatedPrimaryDerivatives = (double[]) abstractStepInterpolator.interpolatedPrimaryDerivatives.clone();
            this.interpolatedSecondaryState = new double[abstractStepInterpolator.interpolatedSecondaryState.length][];
            this.interpolatedSecondaryDerivatives = new double[abstractStepInterpolator.interpolatedSecondaryDerivatives.length][];
            int i = 0;
            while (true) {
                double[][] dArr2 = this.interpolatedSecondaryState;
                if (i >= dArr2.length) {
                    break;
                }
                dArr2[i] = (double[]) abstractStepInterpolator.interpolatedSecondaryState[i].clone();
                this.interpolatedSecondaryDerivatives[i] = (double[]) abstractStepInterpolator.interpolatedSecondaryDerivatives[i].clone();
                i++;
            }
        }
        this.finalized = abstractStepInterpolator.finalized;
        this.forward = abstractStepInterpolator.forward;
        this.dirtyState = abstractStepInterpolator.dirtyState;
        this.primaryMapper = abstractStepInterpolator.primaryMapper;
        EquationsMapper[] equationsMapperArr = abstractStepInterpolator.secondaryMappers;
        this.secondaryMappers = equationsMapperArr != null ? (EquationsMapper[]) equationsMapperArr.clone() : null;
    }

    protected abstract void computeInterpolatedStateAndDerivatives(double d, double d2) throws MaxCountExceededException;

    protected abstract StepInterpolator doCopy();

    protected void doFinalize() throws MaxCountExceededException {
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double getCurrentTime() {
        return this.softCurrentTime;
    }

    public double getGlobalCurrentTime() {
        return this.globalCurrentTime;
    }

    public double getGlobalPreviousTime() {
        return this.globalPreviousTime;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double getInterpolatedTime() {
        return this.interpolatedTime;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public void setInterpolatedTime(double d) {
        this.interpolatedTime = d;
        this.dirtyState = true;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double getPreviousTime() {
        return this.softPreviousTime;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public boolean isForward() {
        return this.forward;
    }

    @Override // java.io.Externalizable
    public abstract void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException;

    public void setSoftCurrentTime(double d) {
        this.softCurrentTime = d;
    }

    public void setSoftPreviousTime(double d) {
        this.softPreviousTime = d;
    }

    public void shift() {
        double d = this.globalCurrentTime;
        this.globalPreviousTime = d;
        this.softPreviousTime = d;
        this.softCurrentTime = d;
    }

    @Override // java.io.Externalizable
    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

    private void allocateInterpolatedArrays(int i) {
        if (i < 0) {
            this.interpolatedState = null;
            this.interpolatedDerivatives = null;
            this.interpolatedPrimaryState = null;
            this.interpolatedPrimaryDerivatives = null;
            this.interpolatedSecondaryState = null;
            this.interpolatedSecondaryDerivatives = null;
            return;
        }
        this.interpolatedState = new double[i];
        this.interpolatedDerivatives = new double[i];
        this.interpolatedPrimaryState = new double[this.primaryMapper.getDimension()];
        this.interpolatedPrimaryDerivatives = new double[this.primaryMapper.getDimension()];
        EquationsMapper[] equationsMapperArr = this.secondaryMappers;
        if (equationsMapperArr == null) {
            this.interpolatedSecondaryState = null;
            this.interpolatedSecondaryDerivatives = null;
            return;
        }
        this.interpolatedSecondaryState = new double[equationsMapperArr.length][];
        this.interpolatedSecondaryDerivatives = new double[equationsMapperArr.length][];
        int i2 = 0;
        while (true) {
            EquationsMapper[] equationsMapperArr2 = this.secondaryMappers;
            if (i2 >= equationsMapperArr2.length) {
                return;
            }
            this.interpolatedSecondaryState[i2] = new double[equationsMapperArr2[i2].getDimension()];
            this.interpolatedSecondaryDerivatives[i2] = new double[this.secondaryMappers[i2].getDimension()];
            i2++;
        }
    }

    protected void reinitialize(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        this.secondaryMappers = (EquationsMapper[]) equationsMapperArr.clone();
        allocateInterpolatedArrays(dArr.length);
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public StepInterpolator copy() throws MaxCountExceededException {
        finalizeStep();
        return doCopy();
    }

    public void storeTime(double d) {
        this.globalCurrentTime = d;
        this.softCurrentTime = d;
        this.h = d - this.globalPreviousTime;
        setInterpolatedTime(d);
        this.finalized = false;
    }

    private void evaluateCompleteInterpolatedState() throws MaxCountExceededException {
        if (this.dirtyState) {
            double d = this.globalCurrentTime - this.interpolatedTime;
            double d2 = this.h;
            computeInterpolatedStateAndDerivatives(d2 != 0.0d ? (d2 - d) / d2 : 0.0d, d);
            this.dirtyState = false;
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double[] getInterpolatedState() throws MaxCountExceededException, DimensionMismatchException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedState, this.interpolatedPrimaryState);
        return this.interpolatedPrimaryState;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double[] getInterpolatedDerivatives() throws MaxCountExceededException, DimensionMismatchException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedDerivatives, this.interpolatedPrimaryDerivatives);
        return this.interpolatedPrimaryDerivatives;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException, DimensionMismatchException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedState, this.interpolatedSecondaryState[i]);
        return this.interpolatedSecondaryState[i];
    }

    @Override // org.apache.commons.math3.ode.sampling.StepInterpolator
    public double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException, DimensionMismatchException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedDerivatives, this.interpolatedSecondaryDerivatives[i]);
        return this.interpolatedSecondaryDerivatives[i];
    }

    public final void finalizeStep() throws MaxCountExceededException {
        if (this.finalized) {
            return;
        }
        doFinalize();
        this.finalized = true;
    }

    protected void writeBaseExternal(ObjectOutput objectOutput) throws IOException {
        double[] dArr = this.currentState;
        if (dArr == null) {
            objectOutput.writeInt(-1);
        } else {
            objectOutput.writeInt(dArr.length);
        }
        objectOutput.writeDouble(this.globalPreviousTime);
        objectOutput.writeDouble(this.globalCurrentTime);
        objectOutput.writeDouble(this.softPreviousTime);
        objectOutput.writeDouble(this.softCurrentTime);
        objectOutput.writeDouble(this.h);
        objectOutput.writeBoolean(this.forward);
        objectOutput.writeObject(this.primaryMapper);
        objectOutput.write(this.secondaryMappers.length);
        int i = 0;
        for (EquationsMapper equationsMapper : this.secondaryMappers) {
            objectOutput.writeObject(equationsMapper);
        }
        if (this.currentState != null) {
            while (true) {
                double[] dArr2 = this.currentState;
                if (i >= dArr2.length) {
                    break;
                }
                objectOutput.writeDouble(dArr2[i]);
                i++;
            }
        }
        objectOutput.writeDouble(this.interpolatedTime);
        try {
            finalizeStep();
        } catch (MaxCountExceededException e) {
            IOException iOException = new IOException(e.getLocalizedMessage());
            iOException.initCause(e);
            throw iOException;
        }
    }

    protected double readBaseExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int i = objectInput.readInt();
        this.globalPreviousTime = objectInput.readDouble();
        this.globalCurrentTime = objectInput.readDouble();
        this.softPreviousTime = objectInput.readDouble();
        this.softCurrentTime = objectInput.readDouble();
        this.h = objectInput.readDouble();
        this.forward = objectInput.readBoolean();
        this.primaryMapper = (EquationsMapper) objectInput.readObject();
        this.secondaryMappers = new EquationsMapper[objectInput.read()];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            EquationsMapper[] equationsMapperArr = this.secondaryMappers;
            if (i3 >= equationsMapperArr.length) {
                break;
            }
            equationsMapperArr[i3] = (EquationsMapper) objectInput.readObject();
            i3++;
        }
        this.dirtyState = true;
        if (i >= 0) {
            this.currentState = new double[i];
            while (true) {
                double[] dArr = this.currentState;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = objectInput.readDouble();
                i2++;
            }
        } else {
            this.currentState = null;
        }
        this.interpolatedTime = Double.NaN;
        allocateInterpolatedArrays(i);
        this.finalized = true;
        return objectInput.readDouble();
    }
}
