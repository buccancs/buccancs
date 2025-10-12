package com.shimmerresearch.sensors;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_SOURCE;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;

public class ShimmerStreamingProperties extends AbstractSensor {

    public static final ChannelDetails channelRssi = new ChannelDetails(
            ObjectClusterSensorName.RSSI,
            ObjectClusterSensorName.RSSI,
            ObjectClusterSensorName.RSSI,
            CHANNEL_UNITS.NO_UNITS,
            Arrays.asList(CHANNEL_TYPE.CAL), true, false);
    public static final ChannelDetails channelSensorDistance = new ChannelDetails(
            ObjectClusterSensorName.SENSOR_DISTANCE,
            ObjectClusterSensorName.SENSOR_DISTANCE,
            ObjectClusterSensorName.SENSOR_DISTANCE,
            CHANNEL_UNITS.MILLIMETER,
            Arrays.asList(CHANNEL_TYPE.CAL), true, false);
    private static final long serialVersionUID = 3069449933266283483L;

    {
        channelRssi.mChannelSource = CHANNEL_SOURCE.API;
    }

    {
        channelSensorDistance.mChannelSource = CHANNEL_SOURCE.API;
    }

    public ShimmerStreamingProperties(SENSORS sensorType, ShimmerDevice shimmerDevice) {
        super(sensorType, shimmerDevice);
    }

        public static ObjectCluster calculateRssiChannels(ObjectCluster objectCluster, double rssi, int txPower) {
        if (Double.isNaN(rssi)) {
            rssi = -100.0;
        }

        objectCluster.addCalDataToMap(ShimmerStreamingProperties.channelRssi, rssi);
        double distance = UtilShimmer.calculateDistanceFromRssi((long) rssi, txPower);
        objectCluster.addCalDataToMap(ShimmerStreamingProperties.channelSensorDistance, distance);
        return objectCluster;
    }

    @Override
    public void generateSensorMap() {

    }

    @Override
    public void generateConfigOptionsMap() {

    }

    @Override
    public void generateSensorGroupMapping() {

    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {

    }

    @Override
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] rawData, COMMUNICATION_TYPE commType,
                                           ObjectCluster objectCluster, boolean isTimeSyncEnabled, double pctimeStampMs) {
        return null;
    }

    @Override
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {

    }

    @Override
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] configBytes, COMMUNICATION_TYPE commType) {

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
        return false;
    }

    @Override
    public boolean checkConfigOptionValues(String stringKey) {
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
    public boolean processResponse(int responseCommand, Object parsedResponse, COMMUNICATION_TYPE commType) {
        return false;
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    @Override
    public void parseConfigMap(LinkedHashMap<String, Object> mapOfConfigPerShimmer) {

    }

    public static class GuiLabelSensors {
        public static final String EVENT_MARKER = "Event Marker";
    }

    public static class ObjectClusterSensorName {
        public static final String BATT_PERCENTAGE = SensorBattVoltage.ObjectClusterSensorName.BATT_PERCENTAGE;
        public static final String PACKET_RECEPTION_RATE_CURRENT = "Packet_Reception_Rate_Current";
        public static final String PACKET_RECEPTION_RATE_OVERALL = "Packet_Reception_Rate_Trial";
        public static final String EVENT_MARKER = "Event_Marker";

        public static final String RSSI = "RSSI";
        public static final String SENSOR_DISTANCE = "Distance";
    }

    public static class DatabaseChannelHandles {
        public static final String EVENT_CHANNEL = "Event";
    }

}
