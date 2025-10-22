package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driverUtilities.ChannelDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class SensorDetails implements Serializable {
    private static final long serialVersionUID = 1545530433767674139L;
    public long mDerivedSensorBitmapID;
    public List<ChannelDetails> mListOfChannels;
    public SensorDetailsRef mSensorDetailsRef;
    public Map<Configuration.COMMUNICATION_TYPE, Boolean> mapOfIsEnabledPerCommsType;

    public SensorDetails() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.mapOfIsEnabledPerCommsType = concurrentHashMap;
        concurrentHashMap.put(Configuration.COMMUNICATION_TYPE.BLUETOOTH, false);
        this.mapOfIsEnabledPerCommsType.put(Configuration.COMMUNICATION_TYPE.SD, false);
        this.mapOfIsEnabledPerCommsType.put(Configuration.COMMUNICATION_TYPE.DOCK, false);
        this.mDerivedSensorBitmapID = 0L;
        this.mListOfChannels = new ArrayList();
    }

    public SensorDetails(boolean z, long j, SensorDetailsRef sensorDetailsRef) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.mapOfIsEnabledPerCommsType = concurrentHashMap;
        concurrentHashMap.put(Configuration.COMMUNICATION_TYPE.BLUETOOTH, false);
        this.mapOfIsEnabledPerCommsType.put(Configuration.COMMUNICATION_TYPE.SD, false);
        this.mapOfIsEnabledPerCommsType.put(Configuration.COMMUNICATION_TYPE.DOCK, false);
        this.mDerivedSensorBitmapID = 0L;
        this.mListOfChannels = new ArrayList();
        setIsEnabled(z);
        this.mDerivedSensorBitmapID = j;
        this.mSensorDetailsRef = sensorDetailsRef;
    }

    public static ObjectCluster processShimmerChannelData(byte[] bArr, ChannelDetails channelDetails, ObjectCluster objectCluster) {
        objectCluster.addData(channelDetails.mObjectClusterName, channelDetails.mChannelFormatDerivedFromShimmerDataPacket, channelDetails.mDefaultUncalUnit, UtilParseData.parseData(bArr, channelDetails.mDefaultChannelDataType, channelDetails.mDefaultChannelDataEndian));
        return objectCluster;
    }

    public void getLenghtOfCalibBytes() {
    }

    public List<ChannelDetails> getListOfChannels() {
        return this.mListOfChannels;
    }

    public boolean isDerivedChannel() {
        return this.mDerivedSensorBitmapID > 0;
    }

    public ObjectCluster processData(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        return processDataCommon(bArr, communication_type, objectCluster, z, d);
    }

    public ObjectCluster processDataCommon(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            for (ChannelDetails channelDetails : this.mListOfChannels) {
                byte[] bArr2 = new byte[channelDetails.mDefaultNumBytes];
                System.arraycopy(bArr, i, bArr2, 0, channelDetails.mDefaultNumBytes);
                objectCluster = processShimmerChannelData(bArr2, channelDetails, objectCluster);
                i += channelDetails.mDefaultNumBytes;
                objectCluster.incrementIndexKeeper();
            }
        }
        return objectCluster;
    }

    public void updateSensorDetailsWithCommsTypes(List<Configuration.COMMUNICATION_TYPE> list) {
        for (Configuration.COMMUNICATION_TYPE communication_type : list) {
            if (!this.mapOfIsEnabledPerCommsType.containsKey(communication_type)) {
                this.mapOfIsEnabledPerCommsType.put(communication_type, false);
            }
        }
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mapOfIsEnabledPerCommsType.keySet().iterator();
        while (it2.hasNext()) {
            if (!list.contains(it2.next())) {
                it2.remove();
            }
        }
    }

    public int getExpectedDataPacketSize() {
        int i = 0;
        for (ChannelDetails channelDetails : this.mListOfChannels) {
            if (channelDetails.mChannelSource == ChannelDetails.CHANNEL_SOURCE.SHIMMER) {
                i += channelDetails.mDefaultNumBytes;
            }
        }
        return i;
    }

    public int getNumberOfChannels() {
        return this.mListOfChannels.size();
    }

    public void setIsEnabled(boolean z) {
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mapOfIsEnabledPerCommsType.keySet().iterator();
        while (it2.hasNext()) {
            setIsEnabled(it2.next(), z);
        }
    }

    public void setIsEnabled(Configuration.COMMUNICATION_TYPE communication_type, boolean z) {
        if (communication_type == null) {
            setIsEnabled(z);
        } else {
            this.mapOfIsEnabledPerCommsType.put(communication_type, Boolean.valueOf(z));
        }
    }

    public boolean isEnabled(Configuration.COMMUNICATION_TYPE communication_type) {
        if (this.mapOfIsEnabledPerCommsType.containsKey(communication_type)) {
            return this.mapOfIsEnabledPerCommsType.get(communication_type).booleanValue();
        }
        return false;
    }

    public boolean isEnabled() {
        Iterator<Boolean> it2 = this.mapOfIsEnabledPerCommsType.values().iterator();
        while (it2.hasNext()) {
            if (it2.next().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInternalExpBrdPowerRequired() {
        return isEnabled() && this.mSensorDetailsRef.mIntExpBoardPowerRequired;
    }

    public int getExpectedPacketByteArray(Configuration.COMMUNICATION_TYPE communication_type) {
        int i = 0;
        if (isEnabled(communication_type)) {
            Iterator<ChannelDetails> it2 = this.mListOfChannels.iterator();
            while (it2.hasNext()) {
                i += it2.next().mDefaultNumBytes;
            }
        }
        return i;
    }

    public boolean isApiSensor() {
        return this.mSensorDetailsRef.mIsApiSensor;
    }

    public void updateFromEnabledSensorsVars(long j, long j2) {
        updateFromEnabledSensorsVars(null, j, j2);
    }

    public void updateFromEnabledSensorsVars(Configuration.COMMUNICATION_TYPE communication_type, long j, long j2) {
        if (isApiSensor()) {
            return;
        }
        setIsEnabled(communication_type, false);
        if (!isDerivedChannel()) {
            if ((j & this.mSensorDetailsRef.mSensorBitmapIDSDLogHeader) > 0) {
                setIsEnabled(communication_type, true);
            }
        } else {
            long j3 = this.mDerivedSensorBitmapID;
            if ((j2 & j3) != j3 || (j & this.mSensorDetailsRef.mSensorBitmapIDSDLogHeader) <= 0) {
                return;
            }
            setIsEnabled(communication_type, true);
        }
    }
}
