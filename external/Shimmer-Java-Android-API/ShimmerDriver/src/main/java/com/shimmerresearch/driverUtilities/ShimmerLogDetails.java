package com.shimmerresearch.driverUtilities;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.shimmerresearch.driver.ShimmerDevice;

public class ShimmerLogDetails implements Serializable {

    private static final long serialVersionUID = 1413674780783463461L;

    public String mAbsolutePath;
    public String mFileName;
    public String mTrialName;
    public String mConfigTime;
    public String mFullTrialName;
    public String mSdFileNumber;

    public long mRTCDifference;
    public String mSessionName;
    public int mDbSessionNumber = -1;

    public long mFileSize;
    public long mInitialTimeStamp;
    public long mDuration;
    public int mSlotID;
    public String mUniqueId;
    public String mDockID;
    public String mAbsolutePathWhereFileWasCopied;

    public String mNewSessionName;
    public int mNewSessionId;
    public double mStartingRTC; // this is = (mInitialTimeStamp/32768*1000) + (mRTCDifference/32768*1000);
    public int mNumOfShimmers;
    public double mRTCUserInput;

    public ArrayList<ArrayList<Double>> offsetTimestampPairsPerSession = null;

    public transient ShimmerDevice mShimmerDevice = null;

    public String mShimmerUserAssignedName;
    public String mMacAddress;
    public long mConfigTimeStamp;
    public ShimmerVerObject mShimmerVersionObject;
    public int mMasterShimmer;
    public double mSampleRate;
    public double mEnabledSensors;
    public double mDerivedSensors;


    public LinkedHashMap<String, SensorParsingDetails> mMapOfSensorsToParse = new LinkedHashMap<String, SensorParsingDetails>();
    public String mHeaderFileAbsoluteFilePath = "";

    public ShimmerLogDetails() {
    }

    ;

    public ShimmerLogDetails(String mAbolutePath, String mFileName,
                             String mTrialName, String mConfigTime,
                             String mSessionName, long mFileSize, int mSlotID,
                             String mMacAddress, String mDockID, String mUniqueId) {
        this();

        this.mAbsolutePath = mAbolutePath;
        this.mFileName = mFileName;
        this.mFileSize = mFileSize;

        setTrialSessionConfigTime(mTrialName, mSessionName, mConfigTime);

        this.mMacAddress = mMacAddress;

        this.mDockID = mDockID;
        this.mUniqueId = mUniqueId;
        this.mSlotID = mSlotID;
    }

    public void updateFromShimmerDevice(ShimmerDevice shimmerDevice) {
        mConfigTimeStamp = shimmerDevice.getConfigTime();
        mMacAddress = shimmerDevice.getMacId();

        mShimmerVersionObject = shimmerDevice.getShimmerVerObject();

        mSampleRate = shimmerDevice.getSamplingRateShimmer();
        mEnabledSensors = shimmerDevice.getEnabledSensors();
        mDerivedSensors = shimmerDevice.getDerivedSensors();
        mShimmerUserAssignedName = shimmerDevice.getShimmerUserAssignedName();

        mShimmerDevice = shimmerDevice;
    }

    public void updateFromFile(File l) {
        mAbsolutePath = l.getAbsolutePath();
        mFileName = l.getName();
        mFileSize = l.length();
    }

    public void setTrialSessionConfigTime(String trialName, String sessionName, String configTime) {
        mTrialName = trialName;
        mSessionName = sessionName;
        mConfigTime = configTime;
        mFullTrialName = mTrialName + "_" + mConfigTime;
    }

        public int getmHardwareVersion() {
        return mShimmerVersionObject.getHardwareVersion();
    }

        public int getmFirmwareIdentifier() {
        return mShimmerVersionObject.getFirmwareIdentifier();
    }

        public int getmFirmwareVersionMajor() {
        return mShimmerVersionObject.getFirmwareVersionMajor();
    }

        public int getmFirmwareVersionMinor() {
        return mShimmerVersionObject.getFirmwareVersionMinor();
    }

        public int getmFirmwareVersionInternal() {
        return mShimmerVersionObject.getFirmwareVersionInternal();
    }


}
