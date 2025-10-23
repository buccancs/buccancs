package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;

import java.util.Arrays;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class ShimmerStreamingProperties extends AbstractSensor {
    public static final ChannelDetails channelRssi = new ChannelDetails(ObjectClusterSensorName.RSSI, ObjectClusterSensorName.RSSI, ObjectClusterSensorName.RSSI, Configuration.CHANNEL_UNITS.NO_UNITS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), true, false);
    public static final ChannelDetails channelSensorDistance = new ChannelDetails(ObjectClusterSensorName.SENSOR_DISTANCE, ObjectClusterSensorName.SENSOR_DISTANCE, ObjectClusterSensorName.SENSOR_DISTANCE, Configuration.CHANNEL_UNITS.MILLIMETER, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL), true, false);
    private static final long serialVersionUID = 3069449933266283483L;

    public ShimmerStreamingProperties(AbstractSensor.SENSORS sensors, ShimmerDevice shimmerDevice) {
        super(sensors, shimmerDevice);
        channelRssi.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
        channelSensorDistance.mChannelSource = ChannelDetails.CHANNEL_SOURCE.API;
    }

    public static ObjectCluster calculateRssiChannels(ObjectCluster objectCluster, double d, int i) {
        if (Double.isNaN(d)) {
            d = -100.0d;
        }
        objectCluster.addCalDataToMap(channelRssi, d);
        objectCluster.addCalDataToMap(channelSensorDistance, UtilShimmer.calculateDistanceFromRssi((long) d, i));
        return objectCluster;
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
    public void generateSensorMap() {
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
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        return null;
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
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public static class DatabaseChannelHandles {
        public static final String EVENT_CHANNEL = "Event";
    }

    public static class GuiLabelSensors {
        public static final String EVENT_MARKER = "Event Marker";
    }

    public static class ObjectClusterSensorName {
        public static final String BATT_PERCENTAGE = "Batt_Percentage";
        public static final String EVENT_MARKER = "Event_Marker";
        public static final String PACKET_RECEPTION_RATE_CURRENT = "Packet_Reception_Rate_Current";
        public static final String PACKET_RECEPTION_RATE_OVERALL = "Packet_Reception_Rate_Trial";
        public static final String RSSI = "RSSI";
        public static final String SENSOR_DISTANCE = "Distance";
    }
}
