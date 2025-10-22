package com.shimmerresearch.biophysicalprocessing;

import com.shimmerresearch.algorithms.Filter;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GSRMetrics {
    private static double mDefaultLPFCutoff = 0.5d;
    private static double mDefaultSamplingRate = 256.0d;
    private static int mNumTapsAtDefaultRate = 2560;
    private double mLPFCutOff;
    private int mLPFHalfTaps;
    private int mLPFTaps;
    private Filter mLPFilter;
    private double mPrevGSRValue;
    private double mSamplingRate;
    private boolean mClimbing = false;
    private boolean mStimulusUpdate = false;
    private double mMinTime = 0.0d;
    private double mMaxTime = 0.0d;
    private double mMinAmpl = 0.0d;
    private double mMaxAmpl = 0.0d;
    private double mRiseTime = 0.0d;
    private double mPeakAmpl = 0.0d;
    private List<Double> mTimestampBuffer = new ArrayList();
    private double mGSRWindowMilliSec = 60000.0d;
    private List<Double> mStimulusTimeBuffer = new ArrayList();
    private int mNumResponsesInWindow = 0;
    private double mNumResponsesPerMin = 0.0d;

    public GSRMetrics(double d) throws Exception {
        this.mLPFilter = null;
        setParameters(d);
        this.mLPFilter = new Filter(Filter.LOW_PASS, this.mSamplingRate, new double[]{this.mLPFCutOff}, this.mLPFTaps);
    }

    public double getMaxAmpl() {
        return this.mMaxAmpl;
    }

    public double getMaxTime() {
        return this.mMaxTime;
    }

    public double getMinAmpl() {
        return this.mMinAmpl;
    }

    public double getMinTime() {
        return this.mMinTime;
    }

    public int getNumRespInWindow() {
        return this.mNumResponsesInWindow;
    }

    public double getNumRespPerMin() {
        return this.mNumResponsesPerMin;
    }

    public double getPeakAmpl() {
        return this.mPeakAmpl;
    }

    public double getRiseTime() {
        return this.mRiseTime;
    }

    public void setParameters(double d) {
        reset();
        this.mSamplingRate = d;
        int iCeil = (int) Math.ceil(((mNumTapsAtDefaultRate * d) / mDefaultSamplingRate) / 2.0d);
        this.mLPFHalfTaps = iCeil;
        this.mLPFTaps = iCeil * 2;
        this.mLPFCutOff = mDefaultLPFCutoff;
    }

    public void reset() {
        this.mLPFilter = null;
        this.mTimestampBuffer.clear();
        this.mStimulusTimeBuffer.clear();
        this.mStimulusUpdate = false;
        this.mClimbing = false;
        this.mPrevGSRValue = 0.0d;
    }

    public void findGSRPeaks(double[] dArr, double[] dArr2) throws Exception {
        for (int i = 0; i < dArr.length; i++) {
            findGSRPeaks(dArr[i], dArr2[i]);
        }
    }

    public void findGSRPeaks(double d, double d2) throws Exception {
        this.mTimestampBuffer.add(Double.valueOf(d2));
        double dFilterData = this.mLPFilter.filterData(d);
        this.mStimulusUpdate = false;
        if (this.mTimestampBuffer.size() >= this.mLPFHalfTaps + 2) {
            while (this.mTimestampBuffer.size() > this.mLPFHalfTaps + 2) {
                this.mTimestampBuffer.remove(0);
            }
            boolean z = this.mClimbing;
            if (z && dFilterData < this.mPrevGSRValue) {
                double dDoubleValue = this.mTimestampBuffer.get(0).doubleValue();
                this.mMaxTime = dDoubleValue;
                double d3 = this.mPrevGSRValue;
                this.mMaxAmpl = d3;
                this.mClimbing = false;
                double d4 = this.mMinTime;
                if (dDoubleValue > d4) {
                    this.mRiseTime = dDoubleValue - d4;
                    this.mPeakAmpl = d3 - this.mMinAmpl;
                    this.mStimulusTimeBuffer.add(Double.valueOf(d4));
                    this.mStimulusUpdate = true;
                }
            } else if (!z && dFilterData > this.mPrevGSRValue) {
                this.mMinTime = this.mTimestampBuffer.get(0).doubleValue();
                this.mMinAmpl = this.mPrevGSRValue;
                this.mClimbing = true;
            }
            if (this.mStimulusTimeBuffer.size() > 0 && this.mStimulusTimeBuffer.get(0).doubleValue() < this.mTimestampBuffer.get(0).doubleValue() - this.mGSRWindowMilliSec) {
                this.mStimulusTimeBuffer.remove(0);
                this.mStimulusUpdate = true;
            }
            if (this.mStimulusUpdate) {
                int size = this.mStimulusTimeBuffer.size();
                this.mNumResponsesInWindow = size;
                this.mNumResponsesPerMin = (size / this.mGSRWindowMilliSec) * 60.0d * 1000.0d;
            }
        }
        this.mPrevGSRValue = dFilterData;
    }
}
