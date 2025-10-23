package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SensorDetailsRef implements Serializable {
    private static final long serialVersionUID = 4567211941610864326L;
    public String mGuiFriendlyLabel;
    public int mHeaderByteMask;
    public String mHeaderFileLabel;
    public boolean mIntExpBoardPowerRequired;
    public boolean mIsApiSensor;
    public boolean mIsDummySensor;
    public List<String> mListOfChannelsRef;
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo;
    public List<String> mListOfConfigOptionKeysAssociated;
    public List<Integer> mListOfSensorIdsConflicting;
    public List<Integer> mListOfSensorIdsRequired;
    public int mNumChannels;
    public long mSensorBitmapIDSDLogHeader;
    public long mSensorBitmapIDStreaming;

    public SensorDetailsRef(long j, long j2, String str) {
        this.mSensorBitmapIDStreaming = 0L;
        this.mSensorBitmapIDSDLogHeader = 0L;
        this.mGuiFriendlyLabel = "";
        this.mListOfSensorIdsRequired = null;
        this.mListOfSensorIdsConflicting = null;
        this.mIntExpBoardPowerRequired = false;
        this.mListOfConfigOptionKeysAssociated = null;
        this.mListOfCompatibleVersionInfo = null;
        this.mListOfChannelsRef = new ArrayList();
        this.mIsDummySensor = false;
        this.mIsApiSensor = false;
        this.mHeaderFileLabel = "";
        this.mHeaderByteMask = 0;
        this.mNumChannels = 0;
        this.mSensorBitmapIDStreaming = j;
        this.mSensorBitmapIDSDLogHeader = j2;
        if (str != null) {
            this.mGuiFriendlyLabel = str;
        }
    }

    public SensorDetailsRef(long j, long j2, String str, List<ShimmerVerObject> list, List<String> list2, List<String> list3) {
        this(j, j2, str);
        if (list != null) {
            this.mListOfCompatibleVersionInfo = list;
        }
        if (list2 != null) {
            this.mListOfConfigOptionKeysAssociated = list2;
        }
        if (list3 != null) {
            this.mListOfChannelsRef = list3;
        }
    }

    public SensorDetailsRef(long j, long j2, String str, List<ShimmerVerObject> list, List<Integer> list2, List<String> list3, List<String> list4, boolean z) {
        this(j, j2, str, list, list3, list4);
        if (list2 != null) {
            this.mListOfSensorIdsConflicting = list2;
        }
        this.mIntExpBoardPowerRequired = z;
    }

    public SensorDetailsRef(String str, List<ShimmerVerObject> list, List<String> list2) {
        this.mSensorBitmapIDStreaming = 0L;
        this.mSensorBitmapIDSDLogHeader = 0L;
        this.mGuiFriendlyLabel = "";
        this.mListOfSensorIdsRequired = null;
        this.mListOfSensorIdsConflicting = null;
        this.mIntExpBoardPowerRequired = false;
        this.mListOfConfigOptionKeysAssociated = null;
        this.mListOfCompatibleVersionInfo = null;
        this.mListOfChannelsRef = new ArrayList();
        this.mIsDummySensor = false;
        this.mIsApiSensor = false;
        this.mHeaderFileLabel = "";
        this.mHeaderByteMask = 0;
        this.mNumChannels = 0;
        if (str != null) {
            this.mGuiFriendlyLabel = str;
        }
        if (list != null) {
            this.mListOfCompatibleVersionInfo = list;
        }
        if (list2 != null) {
            this.mListOfChannelsRef = list2;
        }
    }
}
