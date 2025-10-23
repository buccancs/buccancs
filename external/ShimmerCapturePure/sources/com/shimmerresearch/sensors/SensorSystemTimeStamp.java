package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorSystemTimeStamp extends AbstractSensor {
    public static final ChannelDetails channelSystemTimestamp;
    public static final ChannelDetails channelSystemTimestampDiff;
    public static final ChannelDetails channelSystemTimestampPlot;
    public static final ChannelDetails channelSystemTimestampPlotZeroed;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorSystemTimeStampRef;
    private static final long serialVersionUID = 8974371709657275355L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef("System_Timestamp", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, (List<String>) Arrays.asList("System_Timestamp", ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE, ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED));
        sensorSystemTimeStampRef = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP), sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails("System_Timestamp", "System_Timestamp", "System_Timestamp", Configuration.CHANNEL_UNITS.MILLISECONDS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, true);
        channelSystemTimestamp = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, "", Configuration.CHANNEL_UNITS.MILLISECONDS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, false);
        channelSystemTimestampPlot = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, "", Configuration.CHANNEL_UNITS.MILLISECONDS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, false);
        channelSystemTimestampPlotZeroed = channelDetails3;
        channelSystemTimestampDiff = new ChannelDetails(ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE, ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE, "", Configuration.CHANNEL_UNITS.MILLISECONDS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), false, false);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("System_Timestamp", channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, channelDetails2);
        linkedHashMap2.put(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, channelDetails3);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
    }

    public boolean doesDeviceSendATimestamp;

    public SensorSystemTimeStamp(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.SYSTEM_TIMESTAMP, shimmerVerObject);
        this.doesDeviceSendATimestamp = false;
        sensorSystemTimeStampRef.mIsApiSensor = true;
        ChannelDetails channelDetails = channelSystemTimestamp;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelDetails.mChannelFormatDerivedFromShimmerDataPacket = ChannelDetails.CHANNEL_TYPE.CAL;
        channelSystemTimestampPlot.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelSystemTimestampPlotZeroed.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelSystemTimestampDiff.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        initialise();
    }

    public SensorSystemTimeStamp(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.SYSTEM_TIMESTAMP, shimmerDevice);
        this.doesDeviceSendATimestamp = false;
        sensorSystemTimeStampRef.mIsApiSensor = true;
        ChannelDetails channelDetails = channelSystemTimestamp;
        channelDetails.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelDetails.mChannelFormatDerivedFromShimmerDataPacket = ChannelDetails.CHANNEL_TYPE.CAL;
        channelSystemTimestampPlot.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelSystemTimestampPlotZeroed.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelSystemTimestampDiff.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        initialise();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
            if (channelDetails.mObjectClusterName.equals("System_Timestamp")) {
                objectCluster.setSystemTimeStamp(d);
                objectCluster.addCalData(channelDetails, d);
                objectCluster.incrementIndexKeeper();
            } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT) && !this.doesDeviceSendATimestamp) {
                objectCluster.addCalData(channelDetails, d);
                objectCluster.incrementIndexKeeper();
            }
        }
        return objectCluster;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        return this.mSensorMap.containsKey(Integer.valueOf(i));
    }

    public static class ObjectClusterSensorName {
        public static final String SYSTEM_TIMESTAMP = "System_Timestamp";
        public static final String SYSTEM_TIMESTAMP_DIFFERENCE = "System_Timestamp_Difference";
        public static final String SYSTEM_TIMESTAMP_PLOT = "System_Timestamp_Plot";
        public static final String SYSTEM_TIMESTAMP_PLOT_ZEROED = "System_Timestamp_Plot_Zeroed";
    }
}
