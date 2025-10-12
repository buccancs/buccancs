package com.shimmerresearch.driverUtilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_SOURCE;

public class SensorDetails implements Serializable {

    private static final long serialVersionUID = 1545530433767674139L;

    public Map<COMMUNICATION_TYPE, Boolean> mapOfIsEnabledPerCommsType = new ConcurrentHashMap<COMMUNICATION_TYPE, Boolean>();
    public long mDerivedSensorBitmapID = 0;
    public SensorDetailsRef mSensorDetailsRef;
    public List<ChannelDetails> mListOfChannels = new ArrayList<ChannelDetails>();

    {
        mapOfIsEnabledPerCommsType.put(COMMUNICATION_TYPE.BLUETOOTH, false);
        mapOfIsEnabledPerCommsType.put(COMMUNICATION_TYPE.SD, false);
        mapOfIsEnabledPerCommsType.put(COMMUNICATION_TYPE.DOCK, false);
    }

    public SensorDetails() {
    }

    public SensorDetails(boolean isEnabled, long derivedSensorBitmapID, SensorDetailsRef sensorDetailsRef) {
        setIsEnabled(isEnabled);
        mDerivedSensorBitmapID = derivedSensorBitmapID;
        mSensorDetailsRef = sensorDetailsRef;
    }

    public static ObjectCluster processShimmerChannelData(byte[] channelByteArray, ChannelDetails channelDetails, ObjectCluster objectCluster) {
        long parsedChannelData = UtilParseData.parseData(channelByteArray, channelDetails.mDefaultChannelDataType, channelDetails.mDefaultChannelDataEndian);
        objectCluster.addData(channelDetails.mObjectClusterName, channelDetails.mChannelFormatDerivedFromShimmerDataPacket, channelDetails.mDefaultUncalUnit, (double) parsedChannelData);
        return objectCluster;
    }

    public ObjectCluster processData(byte[] rawData, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {
        return processDataCommon(rawData, commType, objectCluster, isTimeSyncEnabled, pcTimestampMs);
    }

    public ObjectCluster processDataCommon(byte[] rawData, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {
        if (rawData != null && rawData.length > 0) {
            int index = 0;
            for (ChannelDetails channelDetails : mListOfChannels) {
                byte[] channelByteArray = new byte[channelDetails.mDefaultNumBytes];
                System.arraycopy(rawData, index, channelByteArray, 0, channelDetails.mDefaultNumBytes);
                objectCluster = processShimmerChannelData(channelByteArray, channelDetails, objectCluster);
                index += channelDetails.mDefaultNumBytes;
                objectCluster.incrementIndexKeeper();
            }

        }
        return objectCluster;
    }

    public void updateSensorDetailsWithCommsTypes(List<COMMUNICATION_TYPE> listOfSupportedCommsTypes) {
        for (COMMUNICATION_TYPE commsType : listOfSupportedCommsTypes) {
            if (!mapOfIsEnabledPerCommsType.containsKey(commsType)) {
                mapOfIsEnabledPerCommsType.put(commsType, false);
            }
        }

        Iterator<COMMUNICATION_TYPE> iterator = mapOfIsEnabledPerCommsType.keySet().iterator();
        while (iterator.hasNext()) {
            COMMUNICATION_TYPE commsType = iterator.next();
            if (!listOfSupportedCommsTypes.contains(commsType)) {
                iterator.remove();
            }
        }
    }

    public int getExpectedDataPacketSize() {
        int dataPacketSize = 0;
        Iterator<ChannelDetails> iterator = mListOfChannels.iterator();
        while (iterator.hasNext()) {
            ChannelDetails channelDetails = iterator.next();
            if (channelDetails.mChannelSource == CHANNEL_SOURCE.SHIMMER) {
                dataPacketSize += channelDetails.mDefaultNumBytes;
            }
        }
        return dataPacketSize;
    }

    public List<ChannelDetails> getListOfChannels() {
        return mListOfChannels;
    }

    public int getNumberOfChannels() {
        return mListOfChannels.size();
    }

    public void setIsEnabled(boolean state) {
        for (COMMUNICATION_TYPE commType : mapOfIsEnabledPerCommsType.keySet()) {
            setIsEnabled(commType, state);
        }
    }

    public void setIsEnabled(COMMUNICATION_TYPE commType, boolean state) {
        if (commType == null) {
            setIsEnabled(state);
        } else {
            mapOfIsEnabledPerCommsType.put(commType, state);
        }
    }

    public boolean isEnabled(COMMUNICATION_TYPE commType) {

        if (mapOfIsEnabledPerCommsType.containsKey(commType)) {
            return mapOfIsEnabledPerCommsType.get(commType);
        }
        return false;
    }

    public boolean isEnabled() {
        for (Boolean isEnabled : mapOfIsEnabledPerCommsType.values()) {
            if (isEnabled) {
                return true;
            }
        }
        return false;
    }

    public boolean isInternalExpBrdPowerRequired() {
        if (isEnabled() && mSensorDetailsRef.mIntExpBoardPowerRequired) {
            return true;
        }
        return false;
    }


    public int getExpectedPacketByteArray(COMMUNICATION_TYPE commType) {
        int count = 0;
        if (isEnabled(commType)) {
            for (ChannelDetails channelDetails : mListOfChannels) {
                count += channelDetails.mDefaultNumBytes;
            }
        }
        return count;
    }

    public boolean isApiSensor() {
        return mSensorDetailsRef.mIsApiSensor;
    }

    public boolean isDerivedChannel() {
        if (mDerivedSensorBitmapID > 0) {
            return true;
        }
        return false;
    }

    public void updateFromEnabledSensorsVars(long enabledSensors, long derivedSensors) {
        updateFromEnabledSensorsVars(null, enabledSensors, derivedSensors);
    }

    public void updateFromEnabledSensorsVars(COMMUNICATION_TYPE commType, long enabledSensors, long derivedSensors) {
        if (isApiSensor()) {
            return;
        }

        setIsEnabled(commType, false);
        if (isDerivedChannel()) {
            if ((derivedSensors & mDerivedSensorBitmapID) == mDerivedSensorBitmapID) {
                if ((enabledSensors & mSensorDetailsRef.mSensorBitmapIDSDLogHeader) > 0) {
                    setIsEnabled(commType, true);
                }
            }
        } else {
            if ((enabledSensors & mSensorDetailsRef.mSensorBitmapIDSDLogHeader) > 0) {
                setIsEnabled(commType, true);
            }
        }
    }

    public void getLenghtOfCalibBytes() {

    }


}
