package com.shimmerresearch.driver;

/* loaded from: classes2.dex */
public class SensorData {
    public boolean mDefaultCalibration;
    public String mFormatName;
    public double mSensorData;
    public String mSensorName;
    public String mSensorUnit;

    public SensorData(String str, String str2, String str3, double d, boolean z) {
        this.mSensorName = str;
        this.mFormatName = str2;
        this.mSensorUnit = str3;
        this.mSensorData = d;
        this.mDefaultCalibration = z;
    }

    public SensorData(String str, String str2, String str3, double d) {
        this.mSensorName = str;
        this.mFormatName = str2;
        this.mSensorUnit = str3;
        this.mSensorData = d;
    }
}
