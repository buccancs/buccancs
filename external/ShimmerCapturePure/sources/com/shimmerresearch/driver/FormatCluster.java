package com.shimmerresearch.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FormatCluster implements Serializable {
    private static final long serialVersionUID = -5291610942413655763L;
    public double mData;
    public List<Double> mDataObject;
    public String mFormat;
    public boolean mIsUsingDefaultCalibration;
    public String mUnits;

    public FormatCluster(String str, String str2) {
        this.mData = Double.NaN;
        this.mIsUsingDefaultCalibration = false;
        this.mFormat = str;
        this.mUnits = str2;
    }

    public FormatCluster(String str, String str2, double d) {
        this(str, str2);
        this.mData = d;
    }

    public FormatCluster(String str, String str2, double d, List<Double> list) {
        this(str, str2, d);
        this.mDataObject = list;
    }

    public FormatCluster(String str, String str2, List<Double> list) {
        this(str, str2);
        this.mDataObject = list;
    }

    public FormatCluster(String str, String str2, double d, boolean z) {
        this(str, str2, d);
        this.mIsUsingDefaultCalibration = z;
    }

    public List<Double> getDataObject() {
        List<Double> list = this.mDataObject;
        return list == null ? new ArrayList() : list;
    }
}
