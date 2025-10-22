package com.shimmerresearch.driver;

/* loaded from: classes2.dex */
public class SensorDataArray {
    public double[] mCalData;
    public String[] mCalUnits;
    public boolean[] mIsUsingDefaultCalibrationParams;
    public String[] mSensorNames;
    public double[] mUncalData;
    public String[] mUncalUnits;
    public int mCalArraysIndex = 0;
    public int mUncalArraysIndex = 0;

    public SensorDataArray(int i) {
        this.mSensorNames = new String[i];
        this.mUncalUnits = new String[i];
        this.mCalUnits = new String[i];
        this.mUncalData = new double[i];
        this.mCalData = new double[i];
        this.mIsUsingDefaultCalibrationParams = new boolean[i];
    }
}
