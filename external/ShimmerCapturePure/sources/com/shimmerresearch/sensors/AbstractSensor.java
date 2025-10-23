package com.shimmerresearch.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.SensorBattVoltage;
import com.shimmerresearch.verisense.sensors.ISensorConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public abstract class AbstractSensor implements Serializable {
    private static final long serialVersionUID = 3465427544416038676L;
    protected static boolean mEnableCalibration = true;
    public TreeMap<Integer, TreeMap<Integer, CalibDetails>> mCalibMap;
    public HashMap<String, ConfigOptionDetailsSensor> mConfigOptionsMap;
    public LinkedHashMap<Integer, SensorGroupingDetails> mSensorGroupingMap;
    public TreeMap<Integer, SensorDetails> mSensorMap;
    public SENSORS mSensorType;
    protected boolean mIsDebugOutput;
    protected String mSensorName;
    protected ShimmerDevice mShimmerDevice;
    protected ShimmerVerObject mShimmerVerObject;

    public AbstractSensor(SENSORS sensors) {
        this.mSensorType = null;
        this.mSensorName = "";
        this.mShimmerVerObject = new ShimmerVerObject();
        this.mIsDebugOutput = false;
        this.mSensorMap = new TreeMap<>();
        this.mConfigOptionsMap = new HashMap<>();
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mCalibMap = new TreeMap<>();
        this.mShimmerDevice = null;
        this.mSensorType = sensors;
        setSensorName(sensors.toString());
    }

    public AbstractSensor(SENSORS sensors, ShimmerVerObject shimmerVerObject) {
        this(sensors);
        if (shimmerVerObject != null) {
            this.mShimmerVerObject = shimmerVerObject;
        }
    }

    public AbstractSensor(SENSORS sensors, ShimmerDevice shimmerDevice) {
        this(sensors, shimmerDevice.getShimmerVerObject());
        this.mShimmerDevice = shimmerDevice;
    }

    public static void addCalibDetailsToDbMap(LinkedHashMap<String, Object> linkedHashMap, CalibDetailsKinematic calibDetailsKinematic, List<String> list, String str) {
        addCalibDetailsToDbMap(linkedHashMap, calibDetailsKinematic, str, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12), list.get(13), list.get(14));
    }

    public static void addCalibDetailsToDbMap(LinkedHashMap<String, Object> linkedHashMap, CalibDetailsKinematic calibDetailsKinematic, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        linkedHashMap.put(str2, Double.valueOf(calibDetailsKinematic.getValidOffsetVector()[0][0]));
        linkedHashMap.put(str3, Double.valueOf(calibDetailsKinematic.getValidOffsetVector()[1][0]));
        linkedHashMap.put(str4, Double.valueOf(calibDetailsKinematic.getValidOffsetVector()[2][0]));
        linkedHashMap.put(str5, Double.valueOf(calibDetailsKinematic.getValidSensitivityMatrix()[0][0]));
        linkedHashMap.put(str6, Double.valueOf(calibDetailsKinematic.getValidSensitivityMatrix()[1][1]));
        linkedHashMap.put(str7, Double.valueOf(calibDetailsKinematic.getValidSensitivityMatrix()[2][2]));
        linkedHashMap.put(str8, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[0][0]));
        linkedHashMap.put(str9, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[0][1]));
        linkedHashMap.put(str10, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[0][2]));
        linkedHashMap.put(str11, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[1][0]));
        linkedHashMap.put(str12, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[1][1]));
        linkedHashMap.put(str13, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[1][2]));
        linkedHashMap.put(str14, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[2][0]));
        linkedHashMap.put(str15, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[2][1]));
        linkedHashMap.put(str16, Double.valueOf(calibDetailsKinematic.getValidAlignmentMatrix()[2][2]));
        if (str.isEmpty()) {
            return;
        }
        linkedHashMap.put(str, Long.valueOf(calibDetailsKinematic.getCalibTimeMs()));
    }

    public static void addCalibDetailsToDbMap(LinkedHashMap<String, Object> linkedHashMap, List<String> list, double[][] dArr, double[][] dArr2, double[][] dArr3) {
        linkedHashMap.put(list.get(0), Double.valueOf(dArr[0][0]));
        linkedHashMap.put(list.get(1), Double.valueOf(dArr[1][0]));
        linkedHashMap.put(list.get(2), Double.valueOf(dArr[2][0]));
        linkedHashMap.put(list.get(3), Double.valueOf(dArr2[0][0]));
        linkedHashMap.put(list.get(4), Double.valueOf(dArr2[1][1]));
        linkedHashMap.put(list.get(5), Double.valueOf(dArr2[2][2]));
        linkedHashMap.put(list.get(6), Double.valueOf(dArr3[0][0]));
        linkedHashMap.put(list.get(7), Double.valueOf(dArr3[0][1]));
        linkedHashMap.put(list.get(8), Double.valueOf(dArr3[0][2]));
        linkedHashMap.put(list.get(9), Double.valueOf(dArr3[1][0]));
        linkedHashMap.put(list.get(10), Double.valueOf(dArr3[1][1]));
        linkedHashMap.put(list.get(11), Double.valueOf(dArr3[1][2]));
        linkedHashMap.put(list.get(12), Double.valueOf(dArr3[2][0]));
        linkedHashMap.put(list.get(13), Double.valueOf(dArr3[2][1]));
        linkedHashMap.put(list.get(14), Double.valueOf(dArr3[2][2]));
    }

    public static void parseCalibDetailsKinematicFromDb(CalibDetails calibDetails, LinkedHashMap<String, Object> linkedHashMap, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        if (calibDetails == null || !(calibDetails instanceof CalibDetailsKinematic)) {
            return;
        }
        ((CalibDetailsKinematic) calibDetails).parseCalibDetailsKinematicFromDb(linkedHashMap, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16);
    }

    public static String parseFromDBColumnToGUIChannel(Map<String, ChannelDetails> map, String str) {
        for (ChannelDetails channelDetails : map.values()) {
            if (channelDetails.getDatabaseChannelHandle().equals(str)) {
                return channelDetails.mObjectClusterName;
            }
        }
        return "";
    }

    public static String parseFromGUIChannelsToDBColumn(Map<String, ChannelDetails> map, String str) {
        ChannelDetails channelDetails = map.get(str);
        return channelDetails != null ? channelDetails.getDatabaseChannelHandle() : "";
    }

    public double calcMaxSamplingRate() {
        return Double.POSITIVE_INFINITY;
    }

    public abstract boolean checkConfigOptionValues(String str);

    public abstract void checkShimmerConfigBeforeConfiguring();

    public abstract void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type);

    public abstract void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type);

    public abstract LinkedHashMap<String, Object> generateConfigMap();

    public abstract void generateConfigOptionsMap();

    public abstract void generateSensorGroupMapping();

    public abstract void generateSensorMap();

    public HashMap<String, ConfigOptionDetailsSensor> getConfigMap() {
        return this.mConfigOptionsMap;
    }

    public HashMap<String, ConfigOptionDetailsSensor> getConfigOptionsMap() {
        return this.mConfigOptionsMap;
    }

    public abstract Object getConfigValueUsingConfigLabel(Integer num, String str);

    public LinkedHashMap<Integer, SensorGroupingDetails> getSensorGroupingMap() {
        return this.mSensorGroupingMap;
    }

    public String getSensorName() {
        return this.mSensorName;
    }

    public void setSensorName(String str) {
        this.mSensorName = str;
    }

    @Deprecated
    public abstract Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type);

    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {
    }

    public void handleSpecCasesBeforeSensorMapUpdateGeneral(ShimmerDevice shimmerDevice) {
    }

    public boolean handleSpecCasesBeforeSensorMapUpdatePerSensor(ShimmerDevice shimmerDevice, Integer num) {
        return false;
    }

    public int handleSpecCasesBeforeSetSensorState(int i, boolean z) {
        return i;
    }

    public void handleSpecCasesUpdateEnabledSensors() {
    }

    public void handleSpecialCasesAfterSensorMapCreate() {
    }

    public boolean isSensorUsingDefaultCal(int i) {
        return false;
    }

    public abstract void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap);

    public abstract ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d);

    public abstract boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type);

    public abstract Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj);

    public abstract boolean setDefaultConfigForSensor(int i, boolean z);

    public abstract void setSensorSamplingRate(double d);

    @Deprecated
    public abstract ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type);

    public void initialise() {
        if (this.mShimmerVerObject != null) {
            generateSensorMap();
            generateConfigOptionsMap();
            generateSensorGroupMapping();
            generateCalibMap();
            setDefaultConfigAllSensors();
        }
    }

    private void setDefaultConfigAllSensors() {
        Iterator<Integer> it2 = this.mSensorMap.keySet().iterator();
        while (it2.hasNext()) {
            setDefaultConfigForSensor(it2.next().intValue(), false);
        }
    }

    public int getNumberOfEnabledChannels(Configuration.COMMUNICATION_TYPE communication_type) {
        int size = 0;
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            if (sensorDetails.isEnabled(communication_type)) {
                size += sensorDetails.mListOfChannels.size();
            }
        }
        return size;
    }

    public boolean isAnySensorChannelEnabled(Configuration.COMMUNICATION_TYPE communication_type) {
        return getNumberOfEnabledChannels(communication_type) > 0;
    }

    public void setIsEnabledAllSensors(Configuration.COMMUNICATION_TYPE communication_type, boolean z) {
        Iterator<SensorDetails> it2 = this.mSensorMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().setIsEnabled(communication_type, z);
        }
    }

    public boolean setIsEnabledSensor(Configuration.COMMUNICATION_TYPE communication_type, boolean z, int i) {
        SensorDetails sensorDetails = this.mSensorMap.get(Integer.valueOf(i));
        if (sensorDetails == null) {
            return false;
        }
        sensorDetails.setIsEnabled(communication_type, z);
        return true;
    }

    @Deprecated
    public void updateStateFromEnabledSensorsVars(Configuration.COMMUNICATION_TYPE communication_type, long j, long j2) {
        Iterator<SensorDetails> it2 = this.mSensorMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().updateFromEnabledSensorsVars(communication_type, j, j2);
        }
    }

    public void updateSensorGroupingMap() {
        for (SensorGroupingDetails sensorGroupingDetails : this.mSensorGroupingMap.values()) {
            sensorGroupingDetails.mListOfConfigOptionKeysAssociated = new ArrayList();
            Iterator<Integer> it2 = sensorGroupingDetails.mListOfSensorIdsAssociated.iterator();
            while (it2.hasNext()) {
                SensorDetails sensorDetails = this.mSensorMap.get(it2.next());
                if (sensorDetails != null && sensorDetails.mSensorDetailsRef != null && sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated != null) {
                    for (String str : sensorDetails.mSensorDetailsRef.mListOfConfigOptionKeysAssociated) {
                        if (!sensorGroupingDetails.mListOfConfigOptionKeysAssociated.contains(str)) {
                            sensorGroupingDetails.mListOfConfigOptionKeysAssociated.add(str);
                        }
                    }
                }
            }
        }
    }

    public String getSensorGuiFriendlyLabel(int i) {
        SensorDetails sensorDetails = this.mSensorMap.get(Integer.valueOf(i));
        if (sensorDetails != null) {
            return sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel;
        }
        return null;
    }

    public SensorDetails getSensorDetails(int i) {
        return this.mSensorMap.get(Integer.valueOf(i));
    }

    public List<ShimmerVerObject> getSensorListOfCompatibleVersionInfo(int i) {
        SensorDetails sensorDetails = this.mSensorMap.get(Integer.valueOf(i));
        if (sensorDetails != null) {
            return sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo;
        }
        return null;
    }

    public void createLocalSensorMap(Map<Integer, SensorDetailsRef> map, Map<String, ChannelDetails> map2) {
        this.mSensorMap = new TreeMap<>();
        Iterator<Integer> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            int iIntValue = it2.next().intValue();
            SensorDetails sensorDetails = new SensorDetails(false, 0L, map.get(Integer.valueOf(iIntValue)));
            updateSensorDetailsWithChannels(sensorDetails, map2);
            this.mSensorMap.put(Integer.valueOf(iIntValue), sensorDetails);
        }
    }

    public void createLocalSensorMapWithCustomParser(Map<Integer, SensorDetailsRef> map, Map<String, ChannelDetails> map2) {
        this.mSensorMap = new TreeMap<>();
        Iterator<Integer> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            int iIntValue = it2.next().intValue();
            SensorDetails sensorDetails = new SensorDetails(false, 0L, map.get(Integer.valueOf(iIntValue))) { // from class: com.shimmerresearch.sensors.AbstractSensor.1
                @Override // com.shimmerresearch.driverUtilities.SensorDetails
                public ObjectCluster processData(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
                    return AbstractSensor.this.processDataCustom(this, bArr, communication_type, objectCluster, z, d);
                }
            };
            updateSensorDetailsWithChannels(sensorDetails, map2);
            this.mSensorMap.put(Integer.valueOf(iIntValue), sensorDetails);
        }
    }

    public void updateSensorDetailsWithChannels(SensorDetails sensorDetails, Map<String, ChannelDetails> map) {
        Iterator<String> it2 = sensorDetails.mSensorDetailsRef.mListOfChannelsRef.iterator();
        while (it2.hasNext()) {
            ChannelDetails channelDetails = map.get(it2.next());
            if (channelDetails != null) {
                sensorDetails.mListOfChannels.add(channelDetails);
            }
        }
    }

    public boolean isSensorEnabled(int i) {
        SensorDetails sensorDetails;
        TreeMap<Integer, SensorDetails> treeMap = this.mSensorMap;
        if (treeMap == null || (sensorDetails = treeMap.get(Integer.valueOf(i))) == null) {
            return false;
        }
        return sensorDetails.isEnabled();
    }

    public void setSamplingRateFromShimmer(double d) {
        setSensorSamplingRate(d);
    }

    protected double getSamplingRateShimmer() {
        ShimmerDevice shimmerDevice = this.mShimmerDevice;
        if (shimmerDevice != null) {
            return shimmerDevice.getSamplingRateShimmer();
        }
        return 128.0d;
    }

    public int getHardwareVersion() {
        return this.mShimmerVerObject.mHardwareVersion;
    }

    public void updateSensorDetailsWithCommsTypes(List<Configuration.COMMUNICATION_TYPE> list) {
        Iterator<SensorDetails> it2 = this.mSensorMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().updateSensorDetailsWithCommsTypes(list);
        }
    }

    public Object setConfigValueUsingConfigLabel(String str, Object obj) {
        return setConfigValueUsingConfigLabel(-1, str, obj);
    }

    public Object getConfigValueUsingConfigLabel(String str) {
        return getConfigValueUsingConfigLabel(-1, str);
    }

    public void consolePrintChannelsCal(ObjectCluster objectCluster, List<String[]> list) {
        String str = "";
        for (String[] strArr : list) {
            String str2 = str + strArr[0] + "_" + strArr[1] + ":";
            FormatCluster formatClusterReturnFormatCluster = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(strArr[0]), strArr[1]);
            str = (formatClusterReturnFormatCluster == null ? str2 + "null" : str2 + formatClusterReturnFormatCluster.mData) + "\t";
        }
        System.out.println(str);
    }

    protected void setCalibration(TreeMap<Integer, TreeMap<Integer, CalibDetails>> treeMap) {
        this.mCalibMap = new TreeMap<>();
        for (Integer num : this.mSensorMap.keySet()) {
            TreeMap<Integer, CalibDetails> treeMap2 = treeMap.get(num);
            if (treeMap2 != null) {
                setCalibrationMapPerSensor(num.intValue(), treeMap2);
            }
        }
    }

    public void setCalibrationMapPerSensor(int i, TreeMap<Integer, CalibDetails> treeMap) {
        this.mCalibMap.put(Integer.valueOf(i), treeMap);
    }

    public CalibDetails getCalibForSensor(int i, int i2) {
        TreeMap<Integer, CalibDetails> calibrationMapForSensor = getCalibrationMapForSensor(i);
        if (calibrationMapForSensor != null) {
            return calibrationMapForSensor.get(Integer.valueOf(i2));
        }
        return null;
    }

    public TreeMap<Integer, CalibDetails> getCalibrationMapForSensor(int i) {
        return this.mCalibMap.get(Integer.valueOf(i));
    }

    public void generateCalibMap() {
        this.mCalibMap = new TreeMap<>();
    }

    protected void setAllCalibSensitivityScaleFactor(CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        Iterator<Integer> it2 = this.mCalibMap.keySet().iterator();
        while (it2.hasNext()) {
            setCalibSensitivityScaleFactor(it2.next().intValue(), calibration_scale_factor);
        }
    }

    protected void setCalibSensitivityScaleFactor(int i, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR calibration_scale_factor) {
        TreeMap<Integer, CalibDetails> treeMap = this.mCalibMap.get(Integer.valueOf(i));
        if (treeMap != null) {
            Iterator<CalibDetails> it2 = treeMap.values().iterator();
            while (it2.hasNext()) {
                ((CalibDetailsKinematic) it2.next()).setSensitivityScaleFactor(calibration_scale_factor);
            }
        }
    }

    public Object setConfigValueUsingConfigLabelCommon(Integer num, String str, Object obj) {
        str.hashCode();
        if (str.equals("Calibration")) {
            setCalibrationMapPerSensor(num.intValue(), (TreeMap) obj);
            return null;
        }
        if (!str.equals("Calibration all")) {
            return null;
        }
        setCalibration((TreeMap) obj);
        return null;
    }

    public Object getConfigValueUsingConfigLabelCommon(Integer num, String str) {
        str.hashCode();
        if (str.equals("Calibration")) {
            return getCalibrationMapForSensor(num.intValue());
        }
        if (str.equals("Calibration all")) {
            return this.mCalibMap;
        }
        return null;
    }

    public void parseCalibDetailsKinematicFromDb(LinkedHashMap<String, Object> linkedHashMap, int i, int i2, List<String> list) {
        parseCalibDetailsKinematicFromDb(linkedHashMap, i, i2, list, "");
    }

    public void parseCalibDetailsKinematicFromDb(LinkedHashMap<String, Object> linkedHashMap, int i, int i2, List<String> list, String str) {
        parseCalibDetailsKinematicFromDb(linkedHashMap, i, i2, str, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12), list.get(13), list.get(14));
    }

    public void parseCalibDetailsKinematicFromDb(LinkedHashMap<String, Object> linkedHashMap, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        parseCalibDetailsKinematicFromDb(getCalibForSensor(i, i2), linkedHashMap, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16);
    }

    public CalibDetailsKinematic getCurrentCalibDetailsIfKinematic(int i, int i2) {
        CalibDetails calibForSensor = getCalibForSensor(i, i2);
        if (calibForSensor == null || !(calibForSensor instanceof CalibDetailsKinematic)) {
            return null;
        }
        return (CalibDetailsKinematic) calibForSensor;
    }

    public void addConfigOption(ConfigOptionDetailsSensor configOptionDetailsSensor) {
        this.mConfigOptionsMap.put(configOptionDetailsSensor.mGuiHandle, configOptionDetailsSensor);
    }

    public List<ISensorConfig> getSensorConfig() {
        return new ArrayList();
    }

    public void setSensorConfig(ISensorConfig iSensorConfig) {
    }

    public enum SENSORS {
        GSR("GSR"),
        ECG_TO_HR("ECG to Heart Rate"),
        EXG("EXG"),
        CLOCK("Shimmer Clock"),
        SYSTEM_TIMESTAMP("PC time"),
        MPU9X50("MPU Accel"),
        BMP180("BMP180"),
        KIONIXKXRB52042("Analog Accelerometer"),
        LSM303("LSM303"),
        PPG("PPG"),
        TEMPLATE("Template sensor - not a real sensor of course!"),
        ADC("ADC"),
        Battery(SensorBattVoltage.ObjectClusterSensorName.BATTERY),
        Bridge_Amplifier("Bridge Amplifier"),
        MMA776X("Shimmer2r Accelerometer"),
        KIONIXKXTC92050("Analog Accelerometer"),
        NONIN_ONYX_II("Nonin Onyx II"),
        QTI_DIRECT_TEMP("QTI DirectTemp"),
        KEYBOARD_MOUSE("Keyboard/Mouse Listener"),
        KEYBOARD("KeyboardListener"),
        BMP280("BMP280"),
        STC3100("STC3100"),
        WEBCAM_FRAME_NUMBER("Frame Number"),
        HOST_CPU_USAGE("Cpu Usage"),
        SWEATCH_ADC("Sweatch ADC"),
        SHIMMER2R_MAG("Shimmer2r Mag"),
        SHIMMER2R_GYRO("Shimmer2r Gyro"),
        LIS2DW12("LIS2DW12"),
        LSM6DS3("LSM6DS3"),
        MAX86150("MAX86150"),
        MAX86916("MAX86916"),
        BIOZ("MAX30001"),
        ADXL371("ADXL371"),
        LIS3MDL("LIS3MDL"),
        LIS2MDL("LIS2MDL"),
        LSM6DSV("LSM6DSV"),
        BMP390("BMP390");

        private final String text;

        SENSORS(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public static class DatabaseChannelHandlesCommon {
        public static final String NONE = "";
        public static final String TIMESTAMP_SYSTEM = "System_Timestamp";
    }

    public class GuiLabelConfigCommon {
        public static final String CALIBRATION_ALL = "Calibration all";
        public static final String CALIBRATION_CURRENT_PER_SENSOR = "Calibration Current";
        public static final String CALIBRATION_PER_SENSOR = "Calibration";
        public static final String RANGE = "Range";
        public static final String RATE = "Rate";

        public GuiLabelConfigCommon() {
        }
    }
}
