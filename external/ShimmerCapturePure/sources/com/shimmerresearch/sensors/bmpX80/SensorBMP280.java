package com.shimmerresearch.sensors.bmpX80;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.bmpX80.SensorBMPX80;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorBMP280 extends SensorBMPX80 {
    public static final String[] ListofPressureResolutionBMP280;
    public static final Integer[] ListofPressureResolutionConfigValuesBMP280;
    public static final ChannelDetails channelBmp280Press;
    public static final ChannelDetails channelBmp280Temp;
    public static final ConfigOptionDetailsSensor configOptionPressureResolutionBMP280;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorDetailsRef sensorBmp280;
    public static final SensorGroupingDetails sensorGroupBmp280;
    private static final long serialVersionUID = 5173164657730440965L;

    static {
        String[] strArr = {"Low", "Standard", "High", "Ultra High"};
        ListofPressureResolutionBMP280 = strArr;
        Integer[] numArr = {0, 1, 2, 3};
        ListofPressureResolutionConfigValuesBMP280 = numArr;
        configOptionPressureResolutionBMP280 = new ConfigOptionDetailsSensor(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION, DatabaseConfigHandle.PRESSURE_PRECISION_BMP280, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBMP280);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(262144L, 262144L, "Pressure & Temperature", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBMP280, Arrays.asList(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION), Arrays.asList(ObjectClusterSensorName.TEMPERATURE_BMP280, ObjectClusterSensorName.PRESSURE_BMP280));
        sensorBmp280 = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(36, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        sensorGroupBmp280 = new SensorGroupingDetails("Pressure & Temperature", Arrays.asList(36), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoBMP280);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.PRESSURE_BMP280, ObjectClusterSensorName.PRESSURE_BMP280, DatabaseChannelHandles.PRESSURE_BMP280, ChannelDetails.CHANNEL_DATA_TYPE.UINT24, 3, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.KPASCAL, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelBmp280Press = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.TEMPERATURE_BMP280, ObjectClusterSensorName.TEMPERATURE_BMP280, DatabaseChannelHandles.TEMPERATURE_BMP280, ChannelDetails.CHANNEL_DATA_TYPE.UINT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, Configuration.CHANNEL_UNITS.DEGREES_CELSIUS, Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL));
        channelBmp280Temp = channelDetails2;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.PRESSURE_BMP280, channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.TEMPERATURE_BMP280, channelDetails2);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
    }

    private CalibDetailsBmp280 mCalibDetailsBmp280Lcl;

    public SensorBMP280(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.BMP280, shimmerVerObject);
        initialise();
    }

    public SensorBMP280(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.BMP280, shimmerDevice);
        initialise();
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionPressureResolutionBMP280);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        if (this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen4()) {
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.PRESSURE_TEMPERATURE_BMP280.ordinal()), sensorGroupBmp280);
        }
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        this.mCalibDetailsBmpX80 = new CalibDetailsBmp280();
        this.mCalibDetailsBmp280Lcl = (CalibDetailsBmp280) this.mCalibDetailsBmpX80;
        super.generateCalibMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(36)) {
            return;
        }
        setDefaultBmp280PressureSensorConfig(false);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
            if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.PRESSURE_BMP280)) {
                objectClusterProcessDataCommon.addCalData(channelDetails, ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(ObjectClusterSensorName.PRESSURE_BMP280), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData / 1000.0d, objectClusterProcessDataCommon.getIndexKeeper() - 2);
                objectClusterProcessDataCommon.incrementIndexKeeper();
            }
            if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.TEMPERATURE_BMP280)) {
                objectClusterProcessDataCommon.addCalData(channelDetails, ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(ObjectClusterSensorName.TEMPERATURE_BMP280), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData / 100.0d, objectClusterProcessDataCommon.getIndexKeeper() - 1);
                objectClusterProcessDataCommon.incrementIndexKeeper();
            }
        }
        super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.PRESSURE_BMP280, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.TEMPERATURE_BMP280, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.PRESSURE_BMP280, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.TEMPERATURE_BMP280, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
        return objectClusterProcessDataCommon;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        if (!str.equals(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION)) {
            return null;
        }
        setPressureResolution(((Integer) obj).intValue());
        return obj;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        if (str.equals(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION)) {
            return Integer.valueOf(getPressureResolution());
        }
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i)) || i != 36) {
            return false;
        }
        setDefaultBmp280PressureSensorConfig(z);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return this.mConfigOptionsMap.containsKey(str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        ActionSetting actionSetting = new ActionSetting(communication_type);
        str.hashCode();
        if (str.equals(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION)) {
            setPressureResolution(((Integer) obj).intValue());
        }
        return actionSetting;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.PRESSURE_PRECISION_BMP280, Integer.valueOf(getPressureResolution()));
        linkedHashMap.put(DatabaseConfigHandle.DIG_T1, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_T1));
        linkedHashMap.put(DatabaseConfigHandle.DIG_T2, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_T2));
        linkedHashMap.put(DatabaseConfigHandle.DIG_T3, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_T3));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P1, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P1));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P2, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P2));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P3, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P3));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P4, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P4));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P5, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P5));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P6, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P6));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P7, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P7));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P8, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P8));
        linkedHashMap.put(DatabaseConfigHandle.DIG_P9, Double.valueOf(this.mCalibDetailsBmp280Lcl.dig_P9));
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.PRESSURE_PRECISION_BMP280)) {
            setPressureResolution(((Double) linkedHashMap.get(DatabaseConfigHandle.PRESSURE_PRECISION_BMP280)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.DIG_T1) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_T2) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_T3) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P1) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P2) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P3) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P4) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P5) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P6) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P7) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P8) && linkedHashMap.containsKey(DatabaseConfigHandle.DIG_P9)) {
            setPressureCalib(((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_T1)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_T2)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_T3)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P1)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P2)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P3)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P4)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P5)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P6)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P7)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P8)).doubleValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DIG_P9)).doubleValue());
        }
    }

    public void setPressureCalib(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12) {
        this.mCalibDetailsBmp280Lcl.setPressureCalib(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12);
    }

    @Override // com.shimmerresearch.sensors.bmpX80.SensorBMPX80
    public void setPressureResolution(int i) {
        if (ArrayUtils.contains(ListofPressureResolutionConfigValuesBMP280, Integer.valueOf(i))) {
            this.mPressureResolution = i;
        }
        updateCurrentPressureCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.bmpX80.SensorBMPX80
    public double[] calibratePressureSensorData(double d, double d2) {
        return super.calibratePressureSensorData(d / Math.pow(2.0d, 4.0d), d2 * Math.pow(2.0d, 4.0d));
    }

    @Override // com.shimmerresearch.sensors.bmpX80.SensorBMPX80
    public List<Double> getPressTempConfigValuesLegacy() {
        ArrayList arrayList = new ArrayList();
        CalibDetailsBmp280 calibDetailsBmp280 = (CalibDetailsBmp280) this.mCalibDetailsBmpX80;
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_T1));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_T2));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_T3));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P1));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P2));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P3));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P4));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P5));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P6));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P7));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P8));
        arrayList.add(Double.valueOf(calibDetailsBmp280.dig_P9));
        return arrayList;
    }

    private void setDefaultBmp280PressureSensorConfig(boolean z) {
        if (z) {
            return;
        }
        this.mPressureResolution = 0;
    }

    public static class DatabaseChannelHandles {
        public static final String PRESSURE_BMP280 = "BMP280_Pressure";
        public static final String TEMPERATURE_BMP280 = "BMP280_Temperature";
    }

    public static final class DatabaseConfigHandle {
        public static final String PRESSURE_PRECISION_BMP280 = "BMP280_Pressure_Precision";
        public static final String DIG_T1 = "BMP280_DIG_T1";
        public static final String DIG_T2 = "BMP280_DIG_T2";
        public static final String DIG_T3 = "BMP280_DIG_T3";
        public static final String DIG_P1 = "BMP280_DIG_P1";
        public static final String DIG_P2 = "BMP280_DIG_P2";
        public static final String DIG_P3 = "BMP280_DIG_P3";
        public static final String DIG_P4 = "BMP280_DIG_P4";
        public static final String DIG_P5 = "BMP280_DIG_P5";
        public static final String DIG_P6 = "BMP280_DIG_P6";
        public static final String DIG_P7 = "BMP280_DIG_P7";
        public static final String DIG_P8 = "BMP280_DIG_P8";
        public static final String DIG_P9 = "BMP280_DIG_P9";
        public static final List<String> LIST_OF_CALIB_HANDLES = Arrays.asList(DIG_T1, DIG_T2, DIG_T3, DIG_P1, DIG_P2, DIG_P3, DIG_P4, DIG_P5, DIG_P6, DIG_P7, DIG_P8, DIG_P9);
    }

    public static final class ObjectClusterSensorName {
        public static final String PRESSURE_BMP280 = "Pressure_BMP280";
        public static final String TEMPERATURE_BMP280 = "Temperature_BMP280";
    }
}
