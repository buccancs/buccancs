package org.apache.commons.math.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.commons.math.ode.DerivativeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/sampling/AbstractStepInterpolator.class */
public abstract class AbstractStepInterpolator implements StepInterpolator {
    protected double h;
    protected double[] currentState;
    protected double interpolatedTime;
    protected double[] interpolatedState;
    protected double[] interpolatedDerivatives;
    private double globalPreviousTime;
    private double globalCurrentTime;
    private double softPreviousTime;
    private double softCurrentTime;
    private boolean finalized;
    private boolean forward;
    private boolean dirtyState;

    protected AbstractStepInterpolator() {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = null;
        this.interpolatedState = null;
        this.interpolatedDerivatives = null;
        this.finalized = false;
        this.forward = true;
        this.dirtyState = true;
    }

    protected AbstractStepInterpolator(double[] y, boolean forward) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = y;
        this.interpolatedState = new double[y.length];
        this.interpolatedDerivatives = new double[y.length];
        this.finalized = false;
        this.forward = forward;
        this.dirtyState = true;
    }

    protected AbstractStepInterpolator(AbstractStepInterpolator interpolator) {
        this.globalPreviousTime = interpolator.globalPreviousTime;
        this.globalCurrentTime = interpolator.globalCurrentTime;
        this.softPreviousTime = interpolator.softPreviousTime;
        this.softCurrentTime = interpolator.softCurrentTime;
        this.h = interpolator.h;
        this.interpolatedTime = interpolator.interpolatedTime;
        if (interpolator.currentState != null) {
            this.currentState = (double[]) interpolator.currentState.clone();
            this.interpolatedState = (double[]) interpolator.interpolatedState.clone();
            this.interpolatedDerivatives = (double[]) interpolator.interpolatedDerivatives.clone();
        } else {
            this.currentState = null;
            this.interpolatedState = null;
            this.interpolatedDerivatives = null;
        }
        this.finalized = interpolator.finalized;
        this.forward = interpolator.forward;
        this.dirtyState = interpolator.dirtyState;
    }

    protected abstract StepInterpolator doCopy();

    protected abstract void computeInterpolatedStateAndDerivatives(double d, double d2) throws DerivativeException;

    @Override // java.io.Externalizable
    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

    @Override // java.io.Externalizable
    public abstract void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException;

    protected void reinitialize(double[] y, boolean isForward) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = y;
        this.interpolatedState = new double[y.length];
        this.interpolatedDerivatives = new double[y.length];
        this.finalized = false;
        this.forward = isForward;
        this.dirtyState = true;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public StepInterpolator copy() throws DerivativeException {
        finalizeStep();
        return doCopy();
    }

    public void shift() {
        this.globalPreviousTime = this.globalCurrentTime;
        this.softPreviousTime = this.globalPreviousTime;
        this.softCurrentTime = this.globalCurrentTime;
    }

    public void storeTime(double t) {
        this.globalCurrentTime = t;
        this.softCurrentTime = this.globalCurrentTime;
        this.h = this.globalCurrentTime - this.globalPreviousTime;
        setInterpolatedTime(t);
        this.finalized = false;
    }

    public void setSoftPreviousTime(double softPreviousTime) {
        this.softPreviousTime = softPreviousTime;
    }

    public void setSoftCurrentTime(double softCurrentTime) {
        this.softCurrentTime = softCurrentTime;
    }

    public double getGlobalPreviousTime() {
        return this.globalPreviousTime;
    }

    public double getGlobalCurrentTime() {
        return this.globalCurrentTime;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public double getPreviousTime() {
        return this.softPreviousTime;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public double getCurrentTime() {
        return this.softCurrentTime;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public double getInterpolatedTime() {
        return this.interpolatedTime;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public void setInterpolatedTime(double time) {
        this.interpolatedTime = time;
        this.dirtyState = true;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public boolean isForward() {
        return this.forward;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public double[] getInterpolatedState() throws DerivativeException {
        if (this.dirtyState) {
            double oneMinusThetaH = this.globalCurrentTime - this.interpolatedTime;
            double theta = this.h == 0.0d ? 0.0d : (this.h - oneMinusThetaH) / this.h;
            computeInterpolatedStateAndDerivatives(theta, oneMinusThetaH);
            this.dirtyState = false;
        }
        return this.interpolatedState;
    }

    @Override // org.apache.commons.math.ode.sampling.StepInterpolator
    public double[] getInterpolatedDerivatives() throws DerivativeException {
        if (this.dirtyState) {
            double oneMinusThetaH = this.globalCurrentTime - this.interpolatedTime;
            double theta = this.h == 0.0d ? 0.0d : (this.h - oneMinusThetaH) / this.h;
            computeInterpolatedStateAndDerivatives(theta, oneMinusThetaH);
            this.dirtyState = false;
        }
        return this.interpolatedDerivatives;
    }

    public final void finalizeStep() throws DerivativeException {
        if (!this.finalized) {
            doFinalize();
            this.finalized = true;
        }
    }

    protected void doFinalize() throws DerivativeException {
    }

    protected void writeBaseExternal(ObjectOutput out) throws IOException {
        if (this.currentState == null) {
            out.writeInt(-1);
        } else {
            out.writeInt(this.currentState.length);
        }
        out.writeDouble(this.globalPreviousTime);
        out.writeDouble(this.globalCurrentTime);
        out.writeDouble(this.softPreviousTime);
        out.writeDouble(this.softCurrentTime);
        out.writeDouble(this.h);
        out.writeBoolean(this.forward);
        if (this.currentState != null) {
            for (int i = 0; i < this.currentState.length; i++) {
                out.writeDouble(this.currentState[i]);
            }
        }
        out.writeDouble(this.interpolatedTime);
        try {
            finalizeStep();
        } catch (DerivativeException e) {
            IOException ioe = new IOException(e.getLocalizedMessage());
            ioe.initCause(e);
            throw ioe;
        }
    }

    protected double readBaseExternal(ObjectInput in) throws IOException {
        int dimension = in.readInt();
        this.globalPreviousTime = in.readDouble();
        this.globalCurrentTime = in.readDouble();
        this.softPreviousTime = in.readDouble();
        this.softCurrentTime = in.readDouble();
        this.h = in.readDouble();
        this.forward = in.readBoolean();
        this.dirtyState = true;
        if (dimension < 0) {
            this.currentState = null;
        } else {
            this.currentState = new double[dimension];
            for (int i = 0; i < this.currentState.length; i++) {
                this.currentState[i] = in.readDouble();
            }
        }
        this.interpolatedTime = Double.NaN;
        this.interpolatedState = dimension < 0 ? null : new double[dimension];
        this.interpolatedDerivatives = dimension < 0 ? null : new double[dimension];
        this.finalized = true;
        return in.readDouble();
    }
}
