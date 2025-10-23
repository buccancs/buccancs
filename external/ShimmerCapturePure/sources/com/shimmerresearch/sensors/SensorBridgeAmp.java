package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SensorBridgeAmp extends AbstractSensor {
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorBridgeAmplifierRef;
    public static final SensorGroupingDetails sensorGroupBrAmp;
    public static final SensorGroupingDetails sensorGroupBrAmpTemperature;
    public static final SensorDetailsRef sensorResistanceAmpRef;
    public static final SensorDetailsRef sensorSkinTempProbeRef;
    private static final long serialVersionUID = 3440151728338729991L;
    public static ChannelDetails channelBridgeAmpHigh = null;
    public static ChannelDetails channelBridgeAmpLow = null;
    public static ChannelDetails channelResistanceAmp = null;
    public static ChannelDetails channelSkinTemp = null;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(32768L, 32768L, "Bridge Amplifier+", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBrAmp, Arrays.asList(7, 10, 13, 19, 100, 101, 102, 116, 103, 106), null, Arrays.asList(ObjectClusterSensorName.BRIDGE_AMP_HIGH, ObjectClusterSensorName.BRIDGE_AMP_LOW), true);
        sensorBridgeAmplifierRef = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef(SensorADC.sensorADC_INT_EXP_ADC_A1Ref.mSensorBitmapIDStreaming, SensorADC.sensorADC_INT_EXP_ADC_A1Ref.mSensorBitmapIDSDLogHeader, "Resistance Amp", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBrAmp, Arrays.asList(17, 7, 104, 19, 100, 101, 102, 116, 103, 106), null, Arrays.asList(ObjectClusterSensorName.RESISTANCE_AMP), true);
        sensorResistanceAmpRef = sensorDetailsRef2;
        SensorDetailsRef sensorDetailsRef3 = new SensorDetailsRef(SensorADC.sensorADC_INT_EXP_ADC_A1Ref.mSensorBitmapIDStreaming, SensorADC.sensorADC_INT_EXP_ADC_A1Ref.mSensorBitmapIDSDLogHeader, "Skin Temperature", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBrAmp, Arrays.asList(17, 7, 16, 19, 100, 101, 102, 116, 103, 106), null, Arrays.asList("Skin_Temperature"), true);
        sensorSkinTempProbeRef = sensorDetailsRef3;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(15, sensorDetailsRef);
        linkedHashMap.put(16, sensorDetailsRef2);
        linkedHashMap.put(104, sensorDetailsRef3);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        channelBridgeAmpHigh = new ChannelDetails(ObjectClusterSensorName.BRIDGE_AMP_HIGH, ObjectClusterSensorName.BRIDGE_AMP_HIGH, DatabaseChannelHandles.BRIDGE_AMPLIFIER_HIGH, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.MILLIVOLTS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 39);
        channelBridgeAmpLow = new ChannelDetails(ObjectClusterSensorName.BRIDGE_AMP_LOW, ObjectClusterSensorName.BRIDGE_AMP_LOW, DatabaseChannelHandles.BRIDGE_AMPLIFIER_LOW, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.MILLIVOLTS, (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 40);
        channelSkinTemp = new ChannelDetails("Skin_Temperature", "Skin_Temperature", DatabaseChannelHandles.SKIN_TEMPERATURE, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.DEGREES_CELSIUS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelResistanceAmp = new ChannelDetails(ObjectClusterSensorName.RESISTANCE_AMP, ObjectClusterSensorName.RESISTANCE_AMP, DatabaseChannelHandles.RESISTANCE_AMPLIFIER, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, Configuration.CHANNEL_UNITS.MILLIVOLTS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.BRIDGE_AMP_HIGH, channelBridgeAmpHigh);
        linkedHashMap2.put(ObjectClusterSensorName.BRIDGE_AMP_LOW, channelBridgeAmpLow);
        linkedHashMap2.put("Skin_Temperature", channelSkinTemp);
        linkedHashMap2.put(ObjectClusterSensorName.RESISTANCE_AMP, channelResistanceAmp);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupBrAmp = new SensorGroupingDetails("Bridge Amplifier+", Arrays.asList(15, 16), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBrAmp);
        sensorGroupBrAmpTemperature = new SensorGroupingDetails("Skin Temperature", Arrays.asList(104), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBrAmp);
    }

    public SensorBridgeAmp(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.Bridge_Amplifier, shimmerVerObject);
        initialise();
    }

    public static double processBAAdcChannel(ChannelDetails channelDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d, double d2, double d3, double d4) {
        return SensorADC.calibrateU12AdcValueToMillivolts(ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData, d2, d3, d4);
    }

    public static double calibratePhillipsSkinTemperatureData(double d) {
        return (Math.log((200.0d * d) / (30300.0d - d)) * (-27.42d)) + 56.502d;
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

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        long j;
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
        for (Integer num : this.mSensorMap.keySet()) {
            if (num.intValue() == 104) {
                j = 2;
            } else if (num.intValue() == 15) {
                j = 32768;
            } else {
                j = num.intValue() == 16 ? 1L : 0L;
            }
            if (j > 0) {
                this.mSensorMap.get(num).mDerivedSensorBitmapID = j;
            }
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        if (this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen4()) {
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER.ordinal()), sensorGroupBrAmp);
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER_SUPP.ordinal()), sensorGroupBrAmpTemperature);
        }
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        if (mEnableCalibration) {
            sensorDetails.mListOfChannels.size();
            for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.BRIDGE_AMP_HIGH)) {
                    objectCluster.addCalData(channelDetails, processBAAdcChannel(channelDetails, bArr, communication_type, objectCluster, z, d, 60.0d, 3.0d, 551.0d), objectCluster.getIndexKeeper() - 2);
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.BRIDGE_AMP_LOW)) {
                    objectCluster.addCalData(channelDetails, processBAAdcChannel(channelDetails, bArr, communication_type, objectCluster, z, d, 1950.0d, 3.0d, 183.7d), objectCluster.getIndexKeeper() - 1);
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.RESISTANCE_AMP)) {
                    objectCluster.addCalData(channelDetails, SensorADC.calibrateU12AdcValueToMillivolts(ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData, 0.0d, 3.0d, 1.0d), objectCluster.getIndexKeeper() - 1);
                } else if (channelDetails.mObjectClusterName.equals("Skin_Temperature")) {
                    objectCluster.addCalData(channelDetails, calibratePhillipsSkinTemperatureData(ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData), objectCluster.getIndexKeeper() - 1);
                }
            }
        }
        return objectCluster;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return this.mConfigOptionsMap.containsKey(str);
    }

    public static class DatabaseChannelHandles {
        public static final String BRIDGE_AMPLIFIER_HIGH = "F5437a_Int_A13_BR_AMP_HIGH";
        public static final String BRIDGE_AMPLIFIER_LOW = "F5437a_Int_A13_BR_AMP_LOW";
        public static final String RESISTANCE_AMPLIFIER = "F5437a_Int_A1_RES_AMP";
        public static final String SKIN_TEMPERATURE = "Philips_21091A_Int_A1_SKIN_TEMP";
    }

    public static class ObjectClusterSensorName {
        public static final String BRIDGE_AMP_HIGH = "Bridge_Amp_High";
        public static final String BRIDGE_AMP_LOW = "Bridge_Amp_Low";
        public static final String RESISTANCE_AMP = "Resistance_Amp";
        public static final String SKIN_TEMPERATURE_PROBE = "Skin_Temperature";
    }

    public class SDLogHeaderDerivedSensors {
        public static final int BRIDGE_AMP = 32768;
        public static final int RES_AMP = 1;
        public static final int SKIN_TEMP = 2;

        public SDLogHeaderDerivedSensors() {
        }
    }

    public class BTStreamDerivedSensors {
        public static final int BRIDGE_AMP = 32768;
        public static final int RES_AMP = 1;
        public static final int SKIN_TEMP = 2;

        public BTStreamDerivedSensors() {
        }
    }

    public class GuiLabelConfig {
        public GuiLabelConfig() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String BRIDGE_AMPLIFIER = "Bridge Amplifier+";
        public static final String BRIDGE_AMPLIFIER_SUPP = "Skin Temperature";

        public LABEL_SENSOR_TILE() {
        }
    }

    public class GuiLabelSensors {
        public static final String BRAMP_HIGHGAIN = "High Gain";
        public static final String BRAMP_LOWGAIN = "Low Gain";
        public static final String BRIDGE_AMPLIFIER = "Bridge Amplifier+";
        public static final String RESISTANCE_AMP = "Resistance Amp";
        public static final String SKIN_TEMP_PROBE = "Skin Temperature";

        public GuiLabelSensors() {
        }
    }
}
