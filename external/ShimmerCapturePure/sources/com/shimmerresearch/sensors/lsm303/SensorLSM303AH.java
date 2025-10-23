package com.shimmerresearch.sensors.lsm303;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ConfigOptionObject;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.lsm303.SensorLSM303;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorLSM303AH extends SensorLSM303 {
    public static final double[][] DefaultAlignmentLSM303AH;
    public static final double[][] DefaultAlignmentMatrixMagShimmer3;
    public static final double[][] DefaultAlignmentMatrixWideRangeAccelShimmer3;
    public static final String[] ListofLSM303AHAccelRateHr;
    public static final Integer[] ListofLSM303AHAccelRateHrConfigValues;
    public static final String[] ListofLSM303AHAccelRateLpm;
    public static final Integer[] ListofLSM303AHAccelRateLpmConfigValues;
    public static final String[] ListofLSM303AHMagRange;
    public static final Integer[] ListofLSM303AHMagRangeConfigValues;
    public static final String[] ListofLSM303AHMagRate;
    public static final Integer[] ListofLSM303AHMagRateConfigValues;
    public static final Integer[] ListofLSM303AccelRangeConfigValues;
    public static final ChannelDetails channelLSM303AHAccelX;
    public static final ChannelDetails channelLSM303AHAccelY;
    public static final ChannelDetails channelLSM303AHMagX;
    public static final ChannelDetails channelLSM303AHMagY;
    public static final ChannelDetails channelLSM303AHMagZ;
    public static final ChannelDetails channelLSM30AH3AccelZ;
    public static final ConfigOptionDetailsSensor configOptionAccelRange;
    public static final ConfigOptionDetailsSensor configOptionAccelRate;
    public static final ConfigOptionDetailsSensor configOptionMagRange;
    public static final ConfigOptionDetailsSensor configOptionMagRate;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLsmAccel;
    public static final SensorGroupingDetails sensorGroupLsmMag;
    public static final SensorDetailsRef sensorLSM303AHAccel;
    public static final SensorDetailsRef sensorLSM303AHMag;
    public static final double[][] DefaultOffsetVectorWideRangeAccelShimmer3 = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel2gShimmer3 = {new double[]{1671.0d, 0.0d, 0.0d}, new double[]{0.0d, 1671.0d, 0.0d}, new double[]{0.0d, 0.0d, 1671.0d}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel4gShimmer3 = {new double[]{836.0d, 0.0d, 0.0d}, new double[]{0.0d, 836.0d, 0.0d}, new double[]{0.0d, 0.0d, 836.0d}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel8gShimmer3 = {new double[]{418.0d, 0.0d, 0.0d}, new double[]{0.0d, 418.0d, 0.0d}, new double[]{0.0d, 0.0d, 418.0d}};
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel16gShimmer3 = {new double[]{209.0d, 0.0d, 0.0d}, new double[]{0.0d, 209.0d, 0.0d}, new double[]{0.0d, 0.0d, 209.0d}};
    public static final double[][] DefaultOffsetVectorMagShimmer3 = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] DefaultSensitivityMatrixMag50GaShimmer3 = {new double[]{667.0d, 0.0d, 0.0d}, new double[]{0.0d, 667.0d, 0.0d}, new double[]{0.0d, 0.0d, 667.0d}};
    public static final ConfigOptionDetailsSensor configOptionAccelLpm = new ConfigOptionDetailsSensor("Wide Range Accel Low-Power Mode", DatabaseConfigHandle.WR_ACC_LPM, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX);
    private static final long serialVersionUID = 5566898733753766631L;

    static {
        double[][] dArr = {new double[]{0.0d, -1.0d, 0.0d}, new double[]{1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        DefaultAlignmentLSM303AH = dArr;
        DefaultAlignmentMatrixWideRangeAccelShimmer3 = dArr;
        DefaultAlignmentMatrixMagShimmer3 = dArr;
        Integer[] numArr = {0, 2, 3, 1};
        ListofLSM303AccelRangeConfigValues = numArr;
        String[] strArr = {"Power-down", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "800.0Hz", "1600.0Hz", "3200.0Hz", "6400.0Hz"};
        ListofLSM303AHAccelRateHr = strArr;
        Integer[] numArr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ListofLSM303AHAccelRateHrConfigValues = numArr2;
        String[] strArr2 = {"Power-down", "1.0Hz", "12.5Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "800.0Hz"};
        ListofLSM303AHAccelRateLpm = strArr2;
        Integer[] numArr3 = {0, 8, 9, 10, 11, 12, 13, 14, 15};
        ListofLSM303AHAccelRateLpmConfigValues = numArr3;
        configOptionAccelRange = new ConfigOptionDetailsSensor("Wide Range Accel Range", DatabaseConfigHandle.WR_ACC_RANGE, ListofLSM303AccelRange, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
        configOptionAccelRate = new ConfigOptionDetailsSensor("Wide Range Accel Rate", DatabaseConfigHandle.WR_ACC_RATE, strArr, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH, (List<ConfigOptionObject>) Arrays.asList(new ConfigOptionObject(1, strArr2, numArr3)));
        String[] strArr3 = {"10.0Hz", "20.0Hz", "50.0Hz", "100.0Hz"};
        ListofLSM303AHMagRate = strArr3;
        Integer[] numArr4 = {0, 1, 2, 3};
        ListofLSM303AHMagRateConfigValues = numArr4;
        String[] strArr4 = {"+/- 49.152Ga"};
        ListofLSM303AHMagRange = strArr4;
        Integer[] numArr5 = {0};
        ListofLSM303AHMagRangeConfigValues = numArr5;
        configOptionMagRange = new ConfigOptionDetailsSensor("Mag Range", DatabaseConfigHandle.MAG_RANGE, strArr4, numArr5, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
        configOptionMagRate = new ConfigOptionDetailsSensor("Mag Rate", DatabaseConfigHandle.MAG_RATE, strArr3, numArr4, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(4096L, 4096L, "Wide-Range Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH, Arrays.asList("Wide Range Accel Range", "Wide Range Accel Rate"), Arrays.asList(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z));
        sensorLSM303AHAccel = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef(32L, 32L, "Magnetometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH, Arrays.asList("Mag Range", "Mag Rate"), Arrays.asList(SensorLSM303.ObjectClusterSensorName.MAG_X, SensorLSM303.ObjectClusterSensorName.MAG_Y, SensorLSM303.ObjectClusterSensorName.MAG_Z));
        sensorLSM303AHMag = sensorDetailsRef2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(31, sensorDetailsRef);
        linkedHashMap.put(32, sensorDetailsRef2);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, DatabaseChannelHandles.WR_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 4);
        channelLSM303AHAccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, DatabaseChannelHandles.WR_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 5);
        channelLSM303AHAccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, DatabaseChannelHandles.WR_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 6);
        channelLSM30AH3AccelZ = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_X, SensorLSM303.ObjectClusterSensorName.MAG_X, DatabaseChannelHandles.MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 7);
        channelLSM303AHMagX = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_Y, SensorLSM303.ObjectClusterSensorName.MAG_Y, DatabaseChannelHandles.MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 8);
        channelLSM303AHMagY = channelDetails5;
        ChannelDetails channelDetails6 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_Z, SensorLSM303.ObjectClusterSensorName.MAG_Z, DatabaseChannelHandles.MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 9);
        channelLSM303AHMagZ = channelDetails6;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, channelDetails);
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, channelDetails2);
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, channelDetails3);
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.MAG_X, channelDetails4);
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.MAG_Z, channelDetails6);
        linkedHashMap2.put(SensorLSM303.ObjectClusterSensorName.MAG_Y, channelDetails5);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupLsmAccel = new SensorGroupingDetails("Wide-Range Accelerometer", Arrays.asList(31), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
        sensorGroupLsmMag = new SensorGroupingDetails("Magnetometer", Arrays.asList(32), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM303AH);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsMag;
    private CalibDetailsKinematic calibDetailsAccelWr16g;
    private CalibDetailsKinematic calibDetailsAccelWr2g;
    private CalibDetailsKinematic calibDetailsAccelWr4g;
    private CalibDetailsKinematic calibDetailsAccelWr8g;
    private CalibDetailsKinematic calibDetailsMag50Ga;

    public SensorLSM303AH() {
        Integer[] numArr = ListofLSM303AccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String str = ListofLSM303AccelRange[0];
        double[][] dArr = DefaultAlignmentMatrixWideRangeAccelShimmer3;
        double[][] dArr2 = DefaultSensitivityMatrixWideRangeAccel2gShimmer3;
        double[][] dArr3 = DefaultOffsetVectorWideRangeAccelShimmer3;
        this.calibDetailsAccelWr2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelWr4g = new CalibDetailsKinematic(numArr[1].intValue(), ListofLSM303AccelRange[1], dArr, DefaultSensitivityMatrixWideRangeAccel4gShimmer3, dArr3);
        this.calibDetailsAccelWr8g = new CalibDetailsKinematic(numArr[2].intValue(), ListofLSM303AccelRange[2], dArr, DefaultSensitivityMatrixWideRangeAccel8gShimmer3, dArr3);
        this.calibDetailsAccelWr16g = new CalibDetailsKinematic(numArr[3].intValue(), ListofLSM303AccelRange[3], dArr, DefaultSensitivityMatrixWideRangeAccel16gShimmer3, dArr3);
        CalibDetailsKinematic calibDetailsKinematic = new CalibDetailsKinematic(ListofLSM303AHMagRangeConfigValues[0].intValue(), ListofLSM303AHMagRange[0], DefaultAlignmentMatrixMagShimmer3, DefaultSensitivityMatrixMag50GaShimmer3, DefaultOffsetVectorMagShimmer3);
        this.calibDetailsMag50Ga = calibDetailsKinematic;
        this.mCurrentCalibDetailsMag = calibDetailsKinematic;
        initialise();
    }

    public SensorLSM303AH(ShimmerDevice shimmerDevice) {
        super(shimmerDevice);
        Integer[] numArr = ListofLSM303AccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String str = ListofLSM303AccelRange[0];
        double[][] dArr = DefaultAlignmentMatrixWideRangeAccelShimmer3;
        double[][] dArr2 = DefaultSensitivityMatrixWideRangeAccel2gShimmer3;
        double[][] dArr3 = DefaultOffsetVectorWideRangeAccelShimmer3;
        this.calibDetailsAccelWr2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelWr4g = new CalibDetailsKinematic(numArr[1].intValue(), ListofLSM303AccelRange[1], dArr, DefaultSensitivityMatrixWideRangeAccel4gShimmer3, dArr3);
        this.calibDetailsAccelWr8g = new CalibDetailsKinematic(numArr[2].intValue(), ListofLSM303AccelRange[2], dArr, DefaultSensitivityMatrixWideRangeAccel8gShimmer3, dArr3);
        this.calibDetailsAccelWr16g = new CalibDetailsKinematic(numArr[3].intValue(), ListofLSM303AccelRange[3], dArr, DefaultSensitivityMatrixWideRangeAccel16gShimmer3, dArr3);
        CalibDetailsKinematic calibDetailsKinematic = new CalibDetailsKinematic(ListofLSM303AHMagRangeConfigValues[0].intValue(), ListofLSM303AHMagRange[0], DefaultAlignmentMatrixMagShimmer3, DefaultSensitivityMatrixMag50GaShimmer3, DefaultOffsetVectorMagShimmer3);
        this.calibDetailsMag50Ga = calibDetailsKinematic;
        this.mCurrentCalibDetailsMag = calibDetailsKinematic;
        initialise();
    }

    public static int getAccelRateFromFreq(boolean z, double d, boolean z2) {
        if (!z) {
            return 0;
        }
        if (z2) {
            if (d >= 1.0d) {
                if (d >= 12.5d) {
                    if (d < 25.0d) {
                        return 10;
                    }
                    if (d < 50.0d) {
                        return 11;
                    }
                    if (d < 100.0d) {
                        return 12;
                    }
                    if (d < 200.0d) {
                        return 13;
                    }
                    return d < 400.0d ? 14 : 15;
                }
            }
            return 8;
        }
        if (d < 12.5d) {
            return 1;
        }
        if (d < 25.0d) {
            return 2;
        }
        if (d < 50.0d) {
            return 3;
        }
        if (d < 100.0d) {
            return 4;
        }
        if (d < 200.0d) {
            return 5;
        }
        if (d < 400.0d) {
            return 6;
        }
        if (d < 800.0d) {
            return 7;
        }
        if (d >= 1600.0d) {
            return d < 3200.0d ? 9 : 10;
        }
        return 8;
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public int getMagRateFromFreqForSensor(boolean z, double d, boolean z2) {
        if (!z || d < 10.0d) {
            return 0;
        }
        if (d < 20.0d || z2) {
            return 1;
        }
        return d < 50.0d ? 2 : 3;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public void setLSM303MagRange(int i) {
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
    public void generateConfigOptionsMap() {
        addConfigOption(configOptionAccelRange);
        addConfigOption(configOptionMagRange);
        addConfigOption(configOptionAccelRate);
        addConfigOption(configOptionMagRate);
        addConfigOption(configOptionAccelLpm);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL.ordinal()), sensorGroupLsmAccel);
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.MAG.ordinal()), sensorGroupLsmMag);
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_RATE, Integer.valueOf(getLSM303DigitalAccelRate()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_RANGE, Integer.valueOf(getAccelRange()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_LPM, Integer.valueOf(getLowPowerAccelEnabled()));
        linkedHashMap.put(DatabaseConfigHandle.WR_ACC_HRM, Integer.valueOf(getHighResAccelWREnabled()));
        SensorLSM303.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsAccelWr(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL, DatabaseConfigHandle.WR_ACC_CALIB_TIME);
        linkedHashMap.put(DatabaseConfigHandle.MAG_RANGE, Integer.valueOf(getMagRange()));
        linkedHashMap.put(DatabaseConfigHandle.MAG_RATE, Integer.valueOf(getLSM303MagRate()));
        SensorLSM303.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsMag(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_CALIB_TIME);
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_LPM)) {
            setLowPowerAccelWR(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_LPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_HRM)) {
            setHighResAccelWR(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_HRM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_RATE)) {
            setLSM303DigitalAccelRate(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.WR_ACC_RANGE)) {
            setLSM303AccelRange(((Double) linkedHashMap.get(DatabaseConfigHandle.WR_ACC_RANGE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 31, getAccelRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL, DatabaseConfigHandle.WR_ACC_CALIB_TIME);
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MAG_RANGE)) {
            setLSM303MagRange(((Double) linkedHashMap.get(DatabaseConfigHandle.MAG_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.MAG_RATE)) {
            setLSM303MagRate(((Double) linkedHashMap.get(DatabaseConfigHandle.MAG_RATE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 32, getMagRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdAccel = 31;
        this.mSensorIdMag = 32;
        super.initialise();
        this.mMagRange = ListofLSM303AHMagRangeConfigValues[0].intValue();
        updateCurrentAccelWrCalibInUse();
        updateCurrentMagCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr2g.mRangeValue), this.calibDetailsAccelWr2g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr4g.mRangeValue), this.calibDetailsAccelWr4g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr8g.mRangeValue), this.calibDetailsAccelWr8g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelWr16g.mRangeValue), this.calibDetailsAccelWr16g);
        setCalibrationMapPerSensor(this.mSensorIdAccel, treeMap);
        updateCurrentAccelWrCalibInUse();
        TreeMap<Integer, CalibDetails> treeMap2 = new TreeMap<>();
        treeMap2.put(Integer.valueOf(this.calibDetailsMag50Ga.mRangeValue), this.calibDetailsMag50Ga);
        setCalibrationMapPerSensor(this.mSensorIdMag, treeMap2);
        updateCurrentMagCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public void setLSM303AccelRange(int i) {
        if (ArrayUtils.contains(ListofLSM303AccelRangeConfigValues, Integer.valueOf(i))) {
            this.mAccelRange = i;
            updateCurrentAccelWrCalibInUse();
        }
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public void setLSM303DigitalAccelRate(int i) {
        super.setLSM303DigitalAccelRateInternal(i);
        int i2 = 0;
        if (this.mLowPowerAccelWR) {
            Integer[] numArr = ListofLSM303AHAccelRateLpmConfigValues;
            int length = numArr.length;
            while (i2 < length) {
                if (numArr[i2].intValue() == i) {
                    return;
                } else {
                    i2++;
                }
            }
            super.setLSM303DigitalAccelRateInternal(ListofLSM303AHAccelRateLpmConfigValues[r5.length - 1].intValue());
            return;
        }
        Integer[] numArr2 = ListofLSM303AHAccelRateHrConfigValues;
        int length2 = numArr2.length;
        while (i2 < length2) {
            if (numArr2[i2].intValue() == i) {
                return;
            } else {
                i2++;
            }
        }
        super.setLSM303DigitalAccelRateInternal(ListofLSM303AHAccelRateHrConfigValues[r5.length - 1].intValue());
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public boolean checkLowPowerMag() {
        setLowPowerMag(getLSM303MagRate() == 0);
        return isLowPowerMagEnabled();
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public int getAccelRateFromFreqForSensor(boolean z, double d, boolean z2) {
        return getAccelRateFromFreq(z, d, z2);
    }

    public static class DatabaseChannelHandles {
        public static final String MAG_X = "LSM303AHTR_MAG_X";
        public static final String MAG_Y = "LSM303AHTR_MAG_Y";
        public static final String MAG_Z = "LSM303AHTR_MAG_Z";
        public static final String WR_ACC_X = "LSM303AHTR_ACC_X";
        public static final String WR_ACC_Y = "LSM303AHTR_ACC_Y";
        public static final String WR_ACC_Z = "LSM303AHTR_ACC_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String MAG_CALIB_TIME = "LSM303AHTR_Mag_Calib_Time";
        public static final String MAG_RANGE = "LSM303AHTR_Mag_Range";
        public static final String MAG_RATE = "LSM303AHTR_Mag_Rate";
        public static final String WR_ACC = "LSM303AHTR_Acc";
        public static final String WR_ACC_CALIB_TIME = "LSM303AHTR_Acc_Calib_Time";
        public static final String WR_ACC_HRM = "LSM303AHTR_Acc_HRM";
        public static final String WR_ACC_LPM = "LSM303AHTR_Acc_LPM";
        public static final String WR_ACC_RANGE = "LSM303AHTR_Acc_Range";
        public static final String WR_ACC_RATE = "LSM303AHTR_Acc_Rate";
        public static final String MAG_OFFSET_X = "LSM303AHTR_Mag_Offset_X";
        public static final String MAG_OFFSET_Y = "LSM303AHTR_Mag_Offset_Y";
        public static final String MAG_OFFSET_Z = "LSM303AHTR_Mag_Offset_Z";
        public static final String MAG_GAIN_X = "LSM303AHTR_Mag_Gain_X";
        public static final String MAG_GAIN_Y = "LSM303AHTR_Mag_Gain_Y";
        public static final String MAG_GAIN_Z = "LSM303AHTR_Mag_Gain_Z";
        public static final String MAG_ALIGN_XX = "LSM303AHTR_Mag_Align_XX";
        public static final String MAG_ALIGN_XY = "LSM303AHTR_Mag_Align_XY";
        public static final String MAG_ALIGN_XZ = "LSM303AHTR_Mag_Align_XZ";
        public static final String MAG_ALIGN_YX = "LSM303AHTR_Mag_Align_YX";
        public static final String MAG_ALIGN_YY = "LSM303AHTR_Mag_Align_YY";
        public static final String MAG_ALIGN_YZ = "LSM303AHTR_Mag_Align_YZ";
        public static final String MAG_ALIGN_ZX = "LSM303AHTR_Mag_Align_ZX";
        public static final String MAG_ALIGN_ZY = "LSM303AHTR_Mag_Align_ZY";
        public static final String MAG_ALIGN_ZZ = "LSM303AHTR_Mag_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MAG = Arrays.asList(MAG_OFFSET_X, MAG_OFFSET_Y, MAG_OFFSET_Z, MAG_GAIN_X, MAG_GAIN_Y, MAG_GAIN_Z, MAG_ALIGN_XX, MAG_ALIGN_XY, MAG_ALIGN_XZ, MAG_ALIGN_YX, MAG_ALIGN_YY, MAG_ALIGN_YZ, MAG_ALIGN_ZX, MAG_ALIGN_ZY, MAG_ALIGN_ZZ);
        public static final String WR_ACC_OFFSET_X = "LSM303AHTR_Acc_Offset_X";
        public static final String WR_ACC_OFFSET_Y = "LSM303AHTR_Acc_Offset_Y";
        public static final String WR_ACC_OFFSET_Z = "LSM303AHTR_Acc_Offset_Z";
        public static final String WR_ACC_GAIN_X = "LSM303AHTR_Acc_Gain_X";
        public static final String WR_ACC_GAIN_Y = "LSM303AHTR_Acc_Gain_Y";
        public static final String WR_ACC_GAIN_Z = "LSM303AHTR_Acc_Gain_Z";
        public static final String WR_ACC_ALIGN_XX = "LSM303AHTR_Acc_Align_XX";
        public static final String WR_ACC_ALIGN_XY = "LSM303AHTR_Acc_Align_XY";
        public static final String WR_ACC_ALIGN_XZ = "LSM303AHTR_Acc_Align_XZ";
        public static final String WR_ACC_ALIGN_YX = "LSM303AHTR_Acc_Align_YX";
        public static final String WR_ACC_ALIGN_YY = "LSM303AHTR_Acc_Align_YY";
        public static final String WR_ACC_ALIGN_YZ = "LSM303AHTR_Acc_Align_YZ";
        public static final String WR_ACC_ALIGN_ZX = "LSM303AHTR_Acc_Align_ZX";
        public static final String WR_ACC_ALIGN_ZY = "LSM303AHTR_Acc_Align_ZY";
        public static final String WR_ACC_ALIGN_ZZ = "LSM303AHTR_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_WR_ACCEL = Arrays.asList(WR_ACC_OFFSET_X, WR_ACC_OFFSET_Y, WR_ACC_OFFSET_Z, WR_ACC_GAIN_X, WR_ACC_GAIN_Y, WR_ACC_GAIN_Z, WR_ACC_ALIGN_XX, WR_ACC_ALIGN_XY, WR_ACC_ALIGN_XZ, WR_ACC_ALIGN_YX, WR_ACC_ALIGN_YY, WR_ACC_ALIGN_YZ, WR_ACC_ALIGN_ZX, WR_ACC_ALIGN_ZY, WR_ACC_ALIGN_ZZ);
    }
}
