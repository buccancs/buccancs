package org.apache.commons.math.ode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.ode.sampling.StepHandler;
import org.apache.commons.math.ode.sampling.StepInterpolator;
import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/ode/ContinuousOutputModel.class */
public class ContinuousOutputModel implements StepHandler, Serializable {
    private static final long serialVersionUID = -1417964919405031606L;
    private double initialTime;
    private double finalTime;
    private boolean forward;
    private int index;
    private List<StepInterpolator> steps = new ArrayList();

    public ContinuousOutputModel() {
        reset();
    }

    public void append(ContinuousOutputModel model) throws DerivativeException {
        if (model.steps.size() == 0) {
            return;
        }
        if (this.steps.size() == 0) {
            this.initialTime = model.initialTime;
            this.forward = model.forward;
        } else {
            if (getInterpolatedState().length != model.getInterpolatedState().length) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(getInterpolatedState().length), Integer.valueOf(model.getInterpolatedState().length));
            }
            if (this.forward ^ model.forward) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            StepInterpolator lastInterpolator = this.steps.get(this.index);
            double current = lastInterpolator.getCurrentTime();
            double previous = lastInterpolator.getPreviousTime();
            double step = current - previous;
            double gap = model.getInitialTime() - current;
            if (FastMath.abs(gap) > 0.001d * FastMath.abs(step)) {
                throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, Double.valueOf(FastMath.abs(gap)));
            }
        }
        for (StepInterpolator interpolator : model.steps) {
            this.steps.add(interpolator.copy());
        }
        this.index = this.steps.size() - 1;
        this.finalTime = this.steps.get(this.index).getCurrentTime();
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public boolean requiresDenseOutput() {
        return true;
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public void reset() {
        this.initialTime = Double.NaN;
        this.finalTime = Double.NaN;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    @Override // org.apache.commons.math.ode.sampling.StepHandler
    public void handleStep(StepInterpolator interpolator, boolean isLast) throws DerivativeException {
        if (this.steps.size() == 0) {
            this.initialTime = interpolator.getPreviousTime();
            this.forward = interpolator.isForward();
        }
        this.steps.add(interpolator.copy());
        if (isLast) {
            this.finalTime = interpolator.getCurrentTime();
            this.index = this.steps.size() - 1;
        }
    }

    public double getInitialTime() {
        return this.initialTime;
    }

    public double getFinalTime() {
        return this.finalTime;
    }

    public double getInterpolatedTime() {
        return this.steps.get(this.index).getInterpolatedTime();
    }

    public void setInterpolatedTime(double time) {
        int iMin = 0;
        StepInterpolator sMin = this.steps.get(0);
        double tMin = 0.5d * (sMin.getPreviousTime() + sMin.getCurrentTime());
        int iMax = this.steps.size() - 1;
        StepInterpolator sMax = this.steps.get(iMax);
        double tMax = 0.5d * (sMax.getPreviousTime() + sMax.getCurrentTime());
        if (locatePoint(time, sMin) <= 0) {
            this.index = 0;
            sMin.setInterpolatedTime(time);
            return;
        }
        if (locatePoint(time, sMax) >= 0) {
            this.index = iMax;
            sMax.setInterpolatedTime(time);
            return;
        }
        while (iMax - iMin > 5) {
            StepInterpolator si = this.steps.get(this.index);
            int location = locatePoint(time, si);
            if (location < 0) {
                iMax = this.index;
                tMax = 0.5d * (si.getPreviousTime() + si.getCurrentTime());
            } else if (location > 0) {
                iMin = this.index;
                tMin = 0.5d * (si.getPreviousTime() + si.getCurrentTime());
            } else {
                si.setInterpolatedTime(time);
                return;
            }
            int iMed = (iMin + iMax) / 2;
            StepInterpolator sMed = this.steps.get(iMed);
            double tMed = 0.5d * (sMed.getPreviousTime() + sMed.getCurrentTime());
            if (FastMath.abs(tMed - tMin) < 1.0E-6d || FastMath.abs(tMax - tMed) < 1.0E-6d) {
                this.index = iMed;
            } else {
                double d12 = tMax - tMed;
                double d23 = tMed - tMin;
                double d13 = tMax - tMin;
                double dt1 = time - tMax;
                double dt2 = time - tMed;
                double dt3 = time - tMin;
                double iLagrange = (((((dt2 * dt3) * d23) * iMax) - (((dt1 * dt3) * d13) * iMed)) + (((dt1 * dt2) * d12) * iMin)) / ((d12 * d23) * d13);
                this.index = (int) FastMath.rint(iLagrange);
            }
            int low = FastMath.max(iMin + 1, ((9 * iMin) + iMax) / 10);
            int high = FastMath.min(iMax - 1, (iMin + (9 * iMax)) / 10);
            if (this.index < low) {
                this.index = low;
            } else if (this.index > high) {
                this.index = high;
            }
        }
        this.index = iMin;
        while (this.index <= iMax && locatePoint(time, this.steps.get(this.index)) > 0) {
            this.index++;
        }
        this.steps.get(this.index).setInterpolatedTime(time);
    }

    public double[] getInterpolatedState() throws DerivativeException {
        return this.steps.get(this.index).getInterpolatedState();
    }

    private int locatePoint(double time, StepInterpolator interval) {
        if (this.forward) {
            if (time < interval.getPreviousTime()) {
                return -1;
            }
            if (time > interval.getCurrentTime()) {
                return 1;
            }
            return 0;
        }
        if (time > interval.getPreviousTime()) {
            return -1;
        }
        if (time < interval.getCurrentTime()) {
            return 1;
        }
        return 0;
    }
}
