package com.shimmerresearch.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FormatCluster implements Serializable {

    private static final long serialVersionUID = -5291610942413655763L;

    public String mFormat;
    public String mUnits;
    public double mData = Double.NaN;
    public List<Double> mDataObject;
    public boolean mIsUsingDefaultCalibration = false;

    public FormatCluster(String format, String units) {
        mFormat = format;
        mUnits = units;
    }

    public FormatCluster(String format, String units, double data) {
        this(format, units);
        mData = data;
    }

    public FormatCluster(String format, String units, double data, List<Double> dataObject) {
        this(format, units, data);
        mDataObject = dataObject;
    }

    public FormatCluster(String format, String units, List<Double> dataObject) {
        this(format, units);
        mDataObject = dataObject;
    }

    public FormatCluster(String format, String units, double data, boolean isUsingDefaultCalibration) {
        this(format, units, data);
        mIsUsingDefaultCalibration = isUsingDefaultCalibration;
    }

    public List<Double> getDataObject() {
        if (mDataObject == null) {
            return (new ArrayList<Double>());
        }
        return mDataObject;
    }

}
