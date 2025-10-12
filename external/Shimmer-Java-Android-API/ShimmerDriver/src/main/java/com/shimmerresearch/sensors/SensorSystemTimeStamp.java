package com.shimmerresearch.sensors;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3.CompatibilityInfoForMaps;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_SOURCE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.sensors.AbstractSensor.DatabaseChannelHandlesCommon;
import com.shimmerresearch.sensors.SensorShimmerClock.GuiLabelSensors;


public class SensorSystemTimeStamp extends AbstractSensor {

    public static final SensorDetailsRef sensorSystemTimeStampRef = new SensorDetailsRef(
            GuiLabelSensors.SYSTEM_TIMESTAMP,
            CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW,
            Arrays.asList(SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP,
                    SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT,
                    SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE,
                    SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED
            ));
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final ChannelDetails channelSystemTimestamp = new ChannelDetails(
            ObjectClusterSensorName.SYSTEM_TIMESTAMP,
            ObjectClusterSensorName.SYSTEM_TIMESTAMP,
            DatabaseChannelHandlesCommon.TIMESTAMP_SYSTEM,
            CHANNEL_UNITS.MILLISECONDS,
            Arrays.asList(CHANNEL_TYPE.CAL), false, true);


    public static final ChannelDetails channelSystemTimestampPlot = new ChannelDetails(
            ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT,
            ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT,
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.MILLISECONDS,
            Arrays.asList(CHANNEL_TYPE.CAL), false, false);
    public static final ChannelDetails channelSystemTimestampPlotZeroed = new ChannelDetails(
            SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED,
            SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED,
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.MILLISECONDS,
            Arrays.asList(CHANNEL_TYPE.CAL), false, false);
    public static final ChannelDetails channelSystemTimestampDiff = new ChannelDetails(
            SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE,
            SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP_DIFFERENCE,
            DatabaseChannelHandlesCommon.NONE,
            CHANNEL_UNITS.MILLISECONDS,
            Arrays.asList(CHANNEL_TYPE.CAL), false, false);
    public static final Map<String, ChannelDetails> mChannelMapRef;
    private static final long serialVersionUID = 8974371709657275355L;

    static {
        Map<Integer, SensorDetailsRef> aMap = new LinkedHashMap<Integer, SensorDetailsRef>();
        aMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP, SensorSystemTimeStamp.sensorSystemTimeStampRef);
        mSensorMapRef = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, ChannelDetails> aMap = new LinkedHashMap<String, ChannelDetails>();
        aMap.put(ObjectClusterSensorName.SYSTEM_TIMESTAMP, channelSystemTimestamp);
        aMap.put(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT, channelSystemTimestampPlot);
        aMap.put(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT_ZEROED, channelSystemTimestampPlotZeroed);
        mChannelMapRef = Collections.unmodifiableMap(aMap);
    }

    public boolean doesDeviceSendATimestamp = false;

    {
        sensorSystemTimeStampRef.mIsApiSensor = true;
    }

    {
        channelSystemTimestamp.mChannelSource = CHANNEL_SOURCE.API;
        channelSystemTimestamp.mChannelFormatDerivedFromShimmerDataPacket = CHANNEL_TYPE.CAL;
    }

    {
        channelSystemTimestampPlot.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelSystemTimestampPlotZeroed.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelSystemTimestampDiff.mChannelSource = CHANNEL_SOURCE.API;
    }

    public SensorSystemTimeStamp(ShimmerVerObject svo) {
        super(SENSORS.SYSTEM_TIMESTAMP, svo);
        initialise();
    }


    public SensorSystemTimeStamp(ShimmerDevice shimmerDevice) {
        super(SENSORS.SYSTEM_TIMESTAMP, shimmerDevice);
        initialise();
    }

    @Override
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override
    public void generateSensorGroupMapping() {
    }

    @Override
    public void generateConfigOptionsMap() {
    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] sensorByteArray, COMMUNICATION_TYPE commType, ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pcTimestampMs) {

        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
            if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.SYSTEM_TIMESTAMP)) {
                objectCluster.setSystemTimeStamp(pcTimestampMs);
                objectCluster.addCalData(channelDetails, pcTimestampMs);
                objectCluster.incrementIndexKeeper();
            } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.SYSTEM_TIMESTAMP_PLOT)) {
                if (doesDeviceSendATimestamp) {
                } else {
                    objectCluster.addCalData(channelDetails, pcTimestampMs);
                    objectCluster.incrementIndexKeeper();
                }
            }
        }

        return objectCluster;
    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] mInfoMemBytes, COMMUNICATION_TYPE commType) {
    }

    @Override
    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        return null;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        return null;
    }

    @Override
    public void setSensorSamplingRate(double samplingRateHz) {
    }

    @Override
    public boolean setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        if (mSensorMap.containsKey(sensorId)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getSettings(String componentName, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public ActionSetting setSettings(String componentName, Object valueToSet, COMMUNICATION_TYPE commType) {
        return null;
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override
    public void parseConfigMap(
            LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
        return false;
    }

    @Override
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {

    }

    public static class ObjectClusterSensorName {
        public static final String SYSTEM_TIMESTAMP = "System_Timestamp";
        public static final String SYSTEM_TIMESTAMP_PLOT = "System_Timestamp_Plot";
        public static final String SYSTEM_TIMESTAMP_DIFFERENCE = "System_Timestamp_Difference";
        public static final String SYSTEM_TIMESTAMP_PLOT_ZEROED = "System_Timestamp_Plot_Zeroed";
    }


}
