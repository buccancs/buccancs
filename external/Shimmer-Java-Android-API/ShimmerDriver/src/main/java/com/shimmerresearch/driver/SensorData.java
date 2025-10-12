package com.shimmerresearch.driver;

public class SensorData {


    public String mSensorName;
    public String mFormatName;
    public String mSensorUnit;
    public double mSensorData;
    public boolean mDefaultCalibration;

    public SensorData(String sensorName, String formatName, String unit, double data, boolean defaultCalibration) {
        mSensorName = sensorName;
        mFormatName = formatName;
        mSensorUnit = unit;
        mSensorData = data;
        mDefaultCalibration = defaultCalibration;
    }

    public SensorData(String sensorName, String formatName, String unit, double data) {
        mSensorName = sensorName;
        mFormatName = formatName;
        mSensorUnit = unit;
        mSensorData = data;
    }


}