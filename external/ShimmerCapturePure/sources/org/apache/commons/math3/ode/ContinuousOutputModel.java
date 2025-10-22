package org.apache.commons.math3.ode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class ContinuousOutputModel implements StepHandler, Serializable {
    private static final long serialVersionUID = -1417964919405031606L;
    private List<StepInterpolator> steps = new ArrayList();
    private double initialTime = Double.NaN;
    private double finalTime = Double.NaN;
    private boolean forward = true;
    private int index = 0;

    public double getFinalTime() {
        return this.finalTime;
    }

    public double getInitialTime() {
        return this.initialTime;
    }

    public void append(ContinuousOutputModel continuousOutputModel) throws MaxCountExceededException, MathIllegalArgumentException {
        if (continuousOutputModel.steps.size() == 0) {
            return;
        }
        if (this.steps.size() == 0) {
            this.initialTime = continuousOutputModel.initialTime;
            this.forward = continuousOutputModel.forward;
        } else {
            if (getInterpolatedState().length != continuousOutputModel.getInterpolatedState().length) {
                throw new DimensionMismatchException(continuousOutputModel.getInterpolatedState().length, getInterpolatedState().length);
            }
            if (this.forward ^ continuousOutputModel.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            StepInterpolator stepInterpolator = this.steps.get(this.index);
            double currentTime = stepInterpolator.getCurrentTime();
            double previousTime = currentTime - stepInterpolator.getPreviousTime();
            double initialTime = continuousOutputModel.getInitialTime() - currentTime;
            if (FastMath.abs(initialTime) > FastMath.abs(previousTime) * 0.001d) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, Double.valueOf(FastMath.abs(initialTime)));
            }
        }
        Iterator<StepInterpolator> it2 = continuousOutputModel.steps.iterator();
        while (it2.hasNext()) {
            this.steps.add(it2.next().copy());
        }
        int size = this.steps.size() - 1;
        this.index = size;
        this.finalTime = this.steps.get(size).getCurrentTime();
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void init(double d, double[] dArr, double d2) {
        this.initialTime = Double.NaN;
        this.finalTime = Double.NaN;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = stepInterpolator.getPreviousTime();
            this.forward = stepInterpolator.isForward();
        }
        this.steps.add(stepInterpolator.copy());
        if (z) {
            this.finalTime = stepInterpolator.getCurrentTime();
            this.index = this.steps.size() - 1;
        }
    }

    public double getInterpolatedTime() {
        return this.steps.get(this.index).getInterpolatedTime();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setInterpolatedTime(double r28) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime(double):void");
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedState();
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedDerivatives();
    }

    public double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryState(i);
    }

    public double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryDerivatives(i);
    }

    private int locatePoint(double d, StepInterpolator stepInterpolator) {
        if (this.forward) {
            if (d < stepInterpolator.getPreviousTime()) {
                return -1;
            }
            return d > stepInterpolator.getCurrentTime() ? 1 : 0;
        }
        if (d > stepInterpolator.getPreviousTime()) {
            return -1;
        }
        return d < stepInterpolator.getCurrentTime() ? 1 : 0;
    }
}
