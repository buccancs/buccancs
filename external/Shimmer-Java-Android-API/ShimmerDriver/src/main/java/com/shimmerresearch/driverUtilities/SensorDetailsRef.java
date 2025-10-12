package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SensorDetailsRef implements Serializable {

        private static final long serialVersionUID = 4567211941610864326L;

        public long mSensorBitmapIDStreaming = 0;
        public long mSensorBitmapIDSDLogHeader = 0;


    public String mGuiFriendlyLabel = "";
    public List<Integer> mListOfSensorIdsRequired = null;
    public List<Integer> mListOfSensorIdsConflicting = null;
    public boolean mIntExpBoardPowerRequired = false;
    public List<String> mListOfConfigOptionKeysAssociated = null;
    public List<ShimmerVerObject> mListOfCompatibleVersionInfo = null;

    public List<String> mListOfChannelsRef = new ArrayList<String>();

    public boolean mIsDummySensor = false;
    public boolean mIsApiSensor = false;

    public String mHeaderFileLabel = "";
    public int mHeaderByteMask = 0;
    public int mNumChannels = 0;



        public SensorDetailsRef(
            long sensorBitmapIDStreaming,
            long sensorBitmapIDSDLogHeader,
            String guiFriendlyLabel) {
        mSensorBitmapIDStreaming = sensorBitmapIDStreaming;
        mSensorBitmapIDSDLogHeader = sensorBitmapIDSDLogHeader;
        if (guiFriendlyLabel != null) {
            mGuiFriendlyLabel = guiFriendlyLabel;
        }
    }

        public SensorDetailsRef(
            long sensorBitmapIDStreaming,
            long sensorBitmapIDSDLogHeader,
            String guiFriendlyLabel,
            List<ShimmerVerObject> listOfCompatibleVersionInfo,
            List<String> listOfConfigOptionKeysAssociated,
            List<String> listOfChannelsRef) {
        this(sensorBitmapIDStreaming, sensorBitmapIDSDLogHeader, guiFriendlyLabel);
        if (listOfCompatibleVersionInfo != null) {
            mListOfCompatibleVersionInfo = listOfCompatibleVersionInfo;
        }
        if (listOfConfigOptionKeysAssociated != null) {
            mListOfConfigOptionKeysAssociated = listOfConfigOptionKeysAssociated;
        }
        if (listOfChannelsRef != null) {
            mListOfChannelsRef = listOfChannelsRef;
        }
    }

        public SensorDetailsRef(
            long sensorBitmapIDStreaming,
            long sensorBitmapIDSDLogHeader,
            String guiFriendlyLabel,
            List<ShimmerVerObject> listOfCompatibleVersionInfo,
            List<Integer> listOfSensorIdsConflicting,
            List<String> listOfConfigOptionKeysAssociated,
            List<String> listOfChannelsRef,
            boolean intExpBoardPowerRequired) {
        this(sensorBitmapIDStreaming,
                sensorBitmapIDSDLogHeader,
                guiFriendlyLabel,
                listOfCompatibleVersionInfo,
                listOfConfigOptionKeysAssociated,
                listOfChannelsRef);
        if (listOfSensorIdsConflicting != null) {
            mListOfSensorIdsConflicting = listOfSensorIdsConflicting;
        }
        mIntExpBoardPowerRequired = intExpBoardPowerRequired;
    }

        public SensorDetailsRef(
            String guiFriendlyLabel,
            List<ShimmerVerObject> listOfCompatibleVersionInfo,
            List<String> listOfChannelsRef) {
        if (guiFriendlyLabel != null) {
            mGuiFriendlyLabel = guiFriendlyLabel;
        }
        if (listOfCompatibleVersionInfo != null) {
            mListOfCompatibleVersionInfo = listOfCompatibleVersionInfo;
        }
        if (listOfChannelsRef != null) {
            mListOfChannelsRef = listOfChannelsRef;
        }
    }

}
