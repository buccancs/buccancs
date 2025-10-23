package com.shimmerresearch.biophysicalprocessing;

import com.shimmerresearch.utilityfunctions.ListStatistics;
import com.shimmerresearch.utilityfunctions.LombScarglePeriodogram;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ECGtoHRAdaptive {
    private static int DECTHRESH = 2;
    private static int FINDMAX = 0;
    private static double INVALID_RESULT = -1.0d;
    private static int WAIT = 1;
    private static int mNumPeaksMin = 3;
    private static double mQRSInitMilliSec = 60.0d;
    private static int mRRBufferSize = 4;
    private static double mRRStdThreshold = 60.0d;
    private static double mRRmaxMilliSec = 1000.0d;
    private static double mRRminMilliSec = 200.0d;
    private double mAVNN;
    private List<Double> mAVNNBuffer;
    private double mCurrentTime;
    private List<Double> mDataBuffer;
    private int mDiffSamples;
    private double mHFPower;
    private double mHRVWindowMilliSec;
    private double mHeartRate;
    private int mIntSamples;
    private double mLFHFRatio;
    private double mLFPower;
    private LombScarglePeriodogram mLSPeriodogram;
    private ListStatistics mListStats;
    private boolean mNNBufferFull;
    private boolean mNNBufferUpdate;
    private List<Double> mNNIntervalBuffer;
    private List<Double> mNPeakTimeBuffer;
    private int mNumPeaksFound;
    private double mPNN50;
    private double mPNNx;
    private double mPthresh;
    private int mQRSInitSamples;
    private double mRMSSD;
    private double mRPeakPos;
    private double mRPeakPosPrev;
    private double mRRInterval;
    private List<Double> mRRIntervalBuffer;
    private int mRRIntervalSamples;
    private int mRRmaxSamples;
    private int mRRminSamples;
    private double mSDANN;
    private double mSDNN;
    private List<Double> mSDNNBuffer;
    private double mSDNNIDX;
    private double mSDSD;
    private int mSamplesSincePeak;
    private int mSamplesSincePeakCandidate;
    private double mSamplingPeriodSec;
    private double mSamplingRate;
    private int mState;
    private double mThreshold;
    private double mTotalPower;
    private int mWaitPeriod;
    private double mYPeakAmp;
    private List<Double> mYZeroBuffer;

    public ECGtoHRAdaptive(double d) {
        this.mDataBuffer = new ArrayList();
        this.mYZeroBuffer = new ArrayList();
        this.mRRIntervalBuffer = new ArrayList();
        this.mNNIntervalBuffer = new ArrayList();
        this.mNPeakTimeBuffer = new ArrayList();
        this.mNNBufferUpdate = false;
        this.mNNBufferFull = false;
        this.mHRVWindowMilliSec = 300000.0d;
        this.mSDNNBuffer = new ArrayList();
        this.mAVNNBuffer = new ArrayList();
        this.mListStats = new ListStatistics();
        this.mLSPeriodogram = new LombScarglePeriodogram();
        double d2 = INVALID_RESULT;
        this.mHeartRate = d2;
        this.mRRInterval = d2;
        this.mRRIntervalSamples = (int) d2;
        setParameters(d);
    }

    public ECGtoHRAdaptive(double d, int i) {
        this.mDataBuffer = new ArrayList();
        this.mYZeroBuffer = new ArrayList();
        this.mRRIntervalBuffer = new ArrayList();
        this.mNNIntervalBuffer = new ArrayList();
        this.mNPeakTimeBuffer = new ArrayList();
        this.mNNBufferUpdate = false;
        this.mNNBufferFull = false;
        this.mHRVWindowMilliSec = 300000.0d;
        this.mSDNNBuffer = new ArrayList();
        this.mAVNNBuffer = new ArrayList();
        this.mListStats = new ListStatistics();
        this.mLSPeriodogram = new LombScarglePeriodogram();
        double d2 = INVALID_RESULT;
        this.mHeartRate = d2;
        this.mRRInterval = d2;
        this.mRRIntervalSamples = (int) d2;
        setParameters(d);
        this.mHRVWindowMilliSec = i;
    }

    public double getAVNN() {
        return this.mAVNN;
    }

    public double getHFPower() {
        return this.mHFPower;
    }

    public double getHeartRate() {
        return this.mHeartRate;
    }

    public double getLFHFRatio() {
        return this.mLFHFRatio;
    }

    public double getLFPower() {
        return this.mLFPower;
    }

    public double getRMSSD() {
        return this.mRMSSD;
    }

    public double getRPeakPos() {
        return this.mRPeakPos;
    }

    public double getRRInterval() {
        return this.mRRInterval;
    }

    public double getSDANN() {
        return this.mSDANN;
    }

    public double getSDNN() {
        return this.mSDNN;
    }

    public double getSDNNIDX() {
        return this.mSDNNIDX;
    }

    public double getSDSD() {
        return this.mSDSD;
    }

    public double getTotalPower() {
        return this.mTotalPower;
    }

    public double getpNN50() {
        return this.mPNN50;
    }

    public double getpNNx() {
        return this.mPNNx;
    }

    public void setHRVWindowSize(int i) {
        this.mHRVWindowMilliSec = i;
    }

    public void setParameters(double d) {
        reset();
        this.mSamplingRate = d;
        this.mSamplingPeriodSec = 1.0d / d;
        this.mRRminSamples = (int) ((mRRminMilliSec * d) / 1000.0d);
        int i = (int) ((mRRmaxMilliSec * d) / 1000.0d);
        this.mRRmaxSamples = i;
        this.mQRSInitSamples = (int) ((mQRSInitMilliSec * d) / 1000.0d);
        this.mWaitPeriod = i;
        this.mRRIntervalSamples = i;
        int iRound = (int) Math.round((d * 3.0d) / 128.0d);
        this.mIntSamples = iRound;
        this.mDiffSamples = iRound - 1;
        this.mPthresh = ((this.mSamplingRate * 0.7d) / 128.0d) + 4.7d;
        this.mDataBuffer = new ArrayList(this.mDiffSamples);
        this.mYZeroBuffer = new ArrayList(this.mIntSamples);
    }

    public void reset() {
        this.mHeartRate = INVALID_RESULT;
        this.mSamplesSincePeak = 0;
        this.mSamplesSincePeakCandidate = 0;
        this.mRPeakPos = 0.0d;
        this.mRPeakPosPrev = 0.0d;
        this.mYPeakAmp = 0.0d;
        this.mNumPeaksFound = 0;
        this.mDataBuffer.clear();
        this.mYZeroBuffer.clear();
        this.mState = FINDMAX;
        this.mRRIntervalBuffer.clear();
        this.mNNIntervalBuffer.clear();
        this.mNPeakTimeBuffer.clear();
        this.mAVNNBuffer.clear();
        this.mSDNNBuffer.clear();
        this.mNNBufferFull = false;
        this.mNNBufferUpdate = false;
    }

    public double[] ecgToHrConversion(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr3[i] = ecgToHrConversion(dArr[i], dArr2[i]);
        }
        return dArr3;
    }

    public double ecgToHrConversion(double d, double d2) {
        this.mCurrentTime = d2;
        this.mDataBuffer.add(Double.valueOf(d));
        this.mNNBufferUpdate = false;
        this.mSamplesSincePeak++;
        if (this.mDataBuffer.size() > this.mDiffSamples) {
            this.mDataBuffer.remove(0);
            this.mYZeroBuffer.add(Double.valueOf(d - this.mDataBuffer.get(0).doubleValue()));
            if (this.mYZeroBuffer.size() > this.mIntSamples) {
                this.mYZeroBuffer.remove(0);
                double dPow = Math.pow((1.0d / (this.mIntSamples - 1.0d)) * this.mListStats.ListSum(this.mYZeroBuffer), 2.0d);
                int i = this.mState;
                int i2 = WAIT;
                if (i == i2) {
                    if (this.mSamplesSincePeak >= this.mRRminSamples) {
                        this.mState = DECTHRESH;
                    }
                } else if (i == DECTHRESH) {
                    double dExp = this.mThreshold * Math.exp((-this.mPthresh) * this.mSamplingPeriodSec);
                    this.mThreshold = dExp;
                    if (dPow > dExp) {
                        this.mState = FINDMAX;
                        this.mSamplesSincePeak = 0;
                        this.mSamplesSincePeakCandidate = 0;
                        this.mYPeakAmp = dPow;
                        this.mRPeakPos = this.mCurrentTime;
                    }
                } else if (i == FINDMAX) {
                    int i3 = this.mSamplesSincePeak;
                    if (i3 < this.mRRIntervalSamples * 0.5d) {
                        if (dPow > this.mYPeakAmp) {
                            this.mYPeakAmp = dPow;
                            this.mRPeakPos = this.mCurrentTime;
                            this.mSamplesSincePeakCandidate = 0;
                        } else {
                            this.mSamplesSincePeakCandidate++;
                        }
                    } else if (i3 >= this.mWaitPeriod + this.mQRSInitSamples) {
                        this.mState = i2;
                        this.mThreshold = this.mYPeakAmp;
                        this.mSamplesSincePeak = this.mSamplesSincePeakCandidate;
                        int i4 = this.mNumPeaksFound;
                        if (i4 < mNumPeaksMin) {
                            this.mNumPeaksFound = i4 + 1;
                        }
                        double dListStdDev = this.mListStats.ListStdDev(this.mRRIntervalBuffer);
                        if (this.mRRIntervalBuffer.size() < mRRBufferSize) {
                            int iExp = (int) (this.mWaitPeriod * Math.exp((-this.mSamplesSincePeak) / 2));
                            this.mWaitPeriod = iExp;
                            int i5 = this.mRRminSamples;
                            if (iExp < i5 / 3) {
                                this.mWaitPeriod = i5 / 3;
                            }
                        } else if (dListStdDev > 200.0d) {
                            this.mWaitPeriod = (int) ((this.mSamplingRate * (this.mListStats.ListSum(this.mRRIntervalBuffer) / (this.mRRIntervalBuffer.size() * 1000.0d))) / 3.0d);
                        }
                        if (this.mNumPeaksFound >= mNumPeaksMin) {
                            this.mRRInterval = this.mRPeakPos - this.mRPeakPosPrev;
                            if (this.mRRIntervalBuffer.size() < mRRBufferSize) {
                                this.mRRIntervalBuffer.add(Double.valueOf(this.mRRInterval));
                                this.mRRIntervalSamples = (int) ((this.mRRInterval * this.mSamplingRate) / 1000.0d);
                            } else {
                                this.mRRIntervalBuffer.remove(0);
                                this.mRRIntervalBuffer.add(Double.valueOf(this.mRRInterval));
                                if (this.mListStats.ListStdDev(this.mRRIntervalBuffer) < mRRStdThreshold) {
                                    double d3 = this.mRRInterval;
                                    this.mRRIntervalSamples = (int) ((this.mSamplingRate * d3) / 1000.0d);
                                    this.mHeartRate = (1000.0d / d3) * 60.0d;
                                    this.mNNIntervalBuffer.add(Double.valueOf(d3));
                                    this.mNPeakTimeBuffer.add(Double.valueOf(this.mRPeakPos));
                                    this.mNNBufferUpdate = true;
                                }
                                if (this.mNPeakTimeBuffer.size() >= 1 && this.mNPeakTimeBuffer.get(0).doubleValue() < this.mCurrentTime - this.mHRVWindowMilliSec) {
                                    this.mNNBufferFull = true;
                                    this.mNNIntervalBuffer.remove(0);
                                    this.mNPeakTimeBuffer.remove(0);
                                    this.mNNBufferUpdate = true;
                                }
                            }
                        }
                        this.mRPeakPosPrev = this.mRPeakPos;
                    }
                }
            }
        }
        return this.mHeartRate;
    }

    public void calculateTimeDomainHRVContinuous() {
        if (this.mNNBufferFull && this.mNNBufferUpdate) {
            List<Double> listListDiff = this.mListStats.ListDiff(this.mNNIntervalBuffer);
            this.mRMSSD = Math.sqrt(this.mListStats.ListMean(this.mListStats.ListPower(listListDiff, 2.0d)));
            this.mSDSD = this.mListStats.ListStdDev(listListDiff);
            this.mPNN50 = pNNx(listListDiff, 50.0d);
            double dListStdDev = this.mListStats.ListStdDev(this.mNNIntervalBuffer);
            this.mSDNN = dListStdDev;
            this.mSDNNBuffer.add(Double.valueOf(dListStdDev));
            double dListMean = this.mListStats.ListMean(this.mNNIntervalBuffer);
            this.mAVNN = dListMean;
            this.mAVNNBuffer.add(Double.valueOf(dListMean));
        }
    }

    public void calculateTimeDomainHRVFullDataSet() {
        this.mSDANN = this.mListStats.ListStdDev(this.mAVNNBuffer);
        this.mSDNNIDX = this.mListStats.ListMean(this.mSDNNBuffer);
    }

    public void calculateFreqDomainHRVContinuous() {
        if (this.mNNBufferFull && this.mNNBufferUpdate) {
            int i = ((int) 10.999999999999998d) + 1;
            int i2 = ((int) 25.0d) + 1;
            double[] dArr = new double[i];
            double[] dArr2 = new double[i2];
            for (int i3 = 0; i3 < i; i3++) {
                dArr[i3] = 0.04d + (i3 * 0.01d);
            }
            for (int i4 = 0; i4 < i2; i4++) {
                dArr2[i4] = 0.15d + (i4 * 0.01d);
            }
            List<Double> listCalculatePeriodogram = this.mLSPeriodogram.calculatePeriodogram(this.mNNIntervalBuffer, this.mNPeakTimeBuffer, dArr);
            List<Double> listCalculatePeriodogram2 = this.mLSPeriodogram.calculatePeriodogram(this.mNNIntervalBuffer, this.mNPeakTimeBuffer, dArr2);
            this.mLFPower = this.mListStats.ListSum(listCalculatePeriodogram);
            double dListSum = this.mListStats.ListSum(listCalculatePeriodogram2);
            this.mHFPower = dListSum;
            double d = this.mLFPower;
            this.mLFHFRatio = d / dListSum;
            this.mTotalPower = d + dListSum;
        }
    }

    public double calculatePNNx(double d) {
        if (this.mNPeakTimeBuffer.size() > 1 && this.mNPeakTimeBuffer.get(0).doubleValue() <= this.mCurrentTime - this.mHRVWindowMilliSec) {
            this.mPNNx = pNNx(this.mListStats.ListDiff(this.mNNIntervalBuffer), d);
        }
        return this.mPNNx;
    }

    private double pNNx(List<Double> list, double d) {
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2).doubleValue() > d) {
                i++;
            }
        }
        return (i / size) * 100.0d;
    }
}
