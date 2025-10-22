package com.shimmerresearch.verisense.sensors;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.UtilCalibration;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;
import com.shimmerresearch.verisense.VerisenseDevice;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import kotlin.UByte$$ExternalSyntheticBackport0;

/* loaded from: classes2.dex */
public class SensorLIS2DW12 extends AbstractSensor {
    public static final String ACCEL_ID = "Accel1";
    public static final ChannelDetails CHANNEL_LISDW12_ACCEL_X;
    public static final ChannelDetails CHANNEL_LISDW12_ACCEL_Y;
    public static final ChannelDetails CHANNEL_LISDW12_ACCEL_Z;
    public static final Map<String, ChannelDetails> CHANNEL_MAP_REF;
    public static final int FIFO_SIZE_IN_CHIP = 192;
    public static final int MAX_FIFOS_IN_PAYLOAD = 169;
    public static final SensorDetailsRef SENOSR_LIS2DW12_ACCEL;
    public static final Map<Integer, SensorDetailsRef> SENSOR_MAP_REF;
    public static final String[] LIS2DW12_RESOLUTION = {"12-bit", "14-bit"};
    public static final String[] LIS2DW12_MODE_MERGED = {"High-Performance Mode", "Low-Power Mode 1, RMS Noise = 4.5 mg", "Low-Power Mode 2, RMS Noise = 2.4 mg", "Low-Power Mode 3, RMS Noise = 1.8 mg", "Low-Power Mode 4, RMS Noise = 1.3 mg"};
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_ACCEL_RANGE = new ConfigOptionDetailsSensor("Range", DatabaseConfigHandle.LIS2DW12_RANGE, LIS2DW12_ACCEL_RANGE.getLabels(), LIS2DW12_ACCEL_RANGE.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_ACCEL_RATE_LP = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS2DW12_RATE, DatabaseConfigHandle.LIS2DW12_RATE, LIS2DW12_ACCEL_RATE.getLabelsLp(), LIS2DW12_ACCEL_RATE.getConfigValuesLp(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_ACCEL_RATE_HP = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS2DW12_RATE, DatabaseConfigHandle.LIS2DW12_RATE, LIS2DW12_ACCEL_RATE.getLabelsHp(), LIS2DW12_ACCEL_RATE.getConfigValuesLp(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_ACCEL_MODE = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS2DW12_MODE, DatabaseConfigHandle.LIS2DW12_MODE, LIS2DW12_MODE.getLabels(), LIS2DW12_MODE.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    public static final ConfigOptionDetailsSensor CONFIG_OPTION_ACCEL_LP_MODE = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS2DW12_LP_MODE, DatabaseConfigHandle.LIS2DW12_LP_MODE, LIS2DW12_LP_MODE.getLabels(), LIS2DW12_LP_MODE.getConfigValues(), ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12);
    public static final double[][] DEFAULT_OFFSET_VECTOR_LIS2DW12 = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] DEFAULT_ALIGNMENT_MATRIX_LIS2DW12 = {new double[]{0.0d, 0.0d, 1.0d}, new double[]{1.0d, 0.0d, 0.0d}, new double[]{0.0d, 1.0d, 0.0d}};
    public static final double[][] DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_2G = {new double[]{1671.665922915d, 0.0d, 0.0d}, new double[]{0.0d, 1671.665922915d, 0.0d}, new double[]{0.0d, 0.0d, 1671.665922915d}};
    public static final double[][] DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_4G = {new double[]{835.832961457d, 0.0d, 0.0d}, new double[]{0.0d, 835.832961457d, 0.0d}, new double[]{0.0d, 0.0d, 835.832961457d}};
    public static final double[][] DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_8G = {new double[]{417.916480729d, 0.0d, 0.0d}, new double[]{0.0d, 417.916480729d, 0.0d}, new double[]{0.0d, 0.0d, 417.916480729d}};
    public static final double[][] DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_16G = {new double[]{208.958240364d, 0.0d, 0.0d}, new double[]{0.0d, 208.958240364d, 0.0d}, new double[]{0.0d, 0.0d, 208.958240364d}};
    private static final long serialVersionUID = -3219602356151087121L;

    static {
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(128L, 128L, GuiLabelSensors.ACCEL1, Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12, Arrays.asList(2007, Integer.valueOf(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO)), Arrays.asList("Range", GuiLabelConfig.LIS2DW12_RATE), Arrays.asList(ObjectClusterSensorName.LIS2DW12_ACC_X, ObjectClusterSensorName.LIS2DW12_ACC_Y, ObjectClusterSensorName.LIS2DW12_ACC_Z), false);
        SENOSR_LIS2DW12_ACCEL = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(2005, sensorDetailsRef);
        SENSOR_MAP_REF = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.LIS2DW12_ACC_X, ObjectClusterSensorName.LIS2DW12_ACC_X, "LIS2DW12_ACC_X", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL, ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.DERIVED));
        CHANNEL_LISDW12_ACCEL_X = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.LIS2DW12_ACC_Y, ObjectClusterSensorName.LIS2DW12_ACC_Y, "LIS2DW12_ACC_Y", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL, ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.DERIVED));
        CHANNEL_LISDW12_ACCEL_Y = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.LIS2DW12_ACC_Z, ObjectClusterSensorName.LIS2DW12_ACC_Z, "LIS2DW12_ACC_Z", ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", Arrays.asList(ChannelDetails.CHANNEL_TYPE.UNCAL, ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.DERIVED));
        CHANNEL_LISDW12_ACCEL_Z = channelDetails3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.LIS2DW12_ACC_X, channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.LIS2DW12_ACC_Y, channelDetails2);
        linkedHashMap2.put(ObjectClusterSensorName.LIS2DW12_ACC_Z, channelDetails3);
        CHANNEL_MAP_REF = Collections.unmodifiableMap(linkedHashMap2);
    }

    public CalibDetailsKinematic calibDetailsAccel16g;
    public CalibDetailsKinematic calibDetailsAccel2g;
    public CalibDetailsKinematic calibDetailsAccel4g;
    public CalibDetailsKinematic calibDetailsAccel8g;
    public CalibDetailsKinematic mCurrentCalibDetailsAccel;
    protected LIS2DW12_BW_FILT bwFilt;
    protected LIS2DW12_FILTERED_DATA_TYPE_SELECTION fds;
    protected LIS2DW12_FIFO_MODE fifoMode;
    protected LIS2DW12_FIFO_THRESHOLD fifoThreshold;
    protected LIS2DW12_HP_REF_MODE hpFilterMode;
    protected LIS2DW12_LOW_NOISE lowNoise;
    protected LIS2DW12_LP_MODE lpMode;
    protected LIS2DW12_MODE mode;
    protected LIS2DW12_ACCEL_RANGE range;
    protected LIS2DW12_ACCEL_RATE rate;

    public SensorLIS2DW12(VerisenseDevice verisenseDevice) {
        super(AbstractSensor.SENSORS.LIS2DW12, verisenseDevice);
        this.range = LIS2DW12_ACCEL_RANGE.RANGE_4G;
        this.rate = LIS2DW12_ACCEL_RATE.LOW_POWER_25_0_HZ;
        this.mode = LIS2DW12_MODE.HIGH_PERFORMANCE;
        this.lpMode = LIS2DW12_LP_MODE.LOW_POWER1_12BIT_4_5_MG_NOISE;
        this.bwFilt = LIS2DW12_BW_FILT.ODR_DIVIDED_BY_2;
        this.fds = LIS2DW12_FILTERED_DATA_TYPE_SELECTION.LOW_PASS_FILTER_PATH_SELECTED;
        this.lowNoise = LIS2DW12_LOW_NOISE.DISABLED;
        this.hpFilterMode = LIS2DW12_HP_REF_MODE.DISABLED;
        this.fifoMode = LIS2DW12_FIFO_MODE.CONTINUOUS_TO_FIFO_MODE;
        this.fifoThreshold = LIS2DW12_FIFO_THRESHOLD.SAMPLE_31;
        int iIntValue = LIS2DW12_ACCEL_RANGE.RANGE_2G.configValue.intValue();
        String str = LIS2DW12_ACCEL_RANGE.RANGE_2G.label;
        double[][] dArr = DEFAULT_ALIGNMENT_MATRIX_LIS2DW12;
        double[][] dArr2 = DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_2G;
        double[][] dArr3 = DEFAULT_OFFSET_VECTOR_LIS2DW12;
        this.calibDetailsAccel2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccel4g = new CalibDetailsKinematic(LIS2DW12_ACCEL_RANGE.RANGE_4G.configValue.intValue(), LIS2DW12_ACCEL_RANGE.RANGE_4G.label, dArr, DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_4G, dArr3);
        this.calibDetailsAccel8g = new CalibDetailsKinematic(LIS2DW12_ACCEL_RANGE.RANGE_8G.configValue.intValue(), LIS2DW12_ACCEL_RANGE.RANGE_8G.label, dArr, DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_8G, dArr3);
        this.calibDetailsAccel16g = new CalibDetailsKinematic(LIS2DW12_ACCEL_RANGE.RANGE_16G.configValue.intValue(), LIS2DW12_ACCEL_RANGE.RANGE_16G.label, dArr, DEFAULT_SENSITIVITY_MATRIX_LIS2DW12_16G, dArr3);
        this.mCurrentCalibDetailsAccel = this.calibDetailsAccel2g;
        initialise();
    }

    public static double calibrateTemperature(long j) {
        return ((j >> 4) / 16.0d) + 25.0d;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        return null;
    }

    public LIS2DW12_LP_MODE getAccelLpMode() {
        return this.lpMode;
    }

    public void setAccelLpMode(LIS2DW12_LP_MODE lis2dw12_lp_mode) {
        this.lpMode = lis2dw12_lp_mode;
    }

    public LIS2DW12_MODE getAccelMode() {
        return this.mode;
    }

    public void setAccelMode(LIS2DW12_MODE lis2dw12_mode) {
        this.mode = lis2dw12_mode;
    }

    public LIS2DW12_ACCEL_RANGE getAccelRange() {
        return this.range;
    }

    public void setAccelRange(LIS2DW12_ACCEL_RANGE lis2dw12_accel_range) {
        this.range = lis2dw12_accel_range;
        updateCurrentAccelCalibInUse();
    }

    public LIS2DW12_ACCEL_RATE getAccelRate() {
        return this.rate;
    }

    public void setAccelRate(LIS2DW12_ACCEL_RATE lis2dw12_accel_rate) {
        this.rate = lis2dw12_accel_rate;
        setAccelMode(lis2dw12_accel_rate.mode);
    }

    public LIS2DW12_BW_FILT getBwFilt() {
        return this.bwFilt;
    }

    public void setBwFilt(LIS2DW12_BW_FILT lis2dw12_bw_filt) {
        this.bwFilt = lis2dw12_bw_filt;
    }

    public LIS2DW12_FIFO_MODE getFifoMode() {
        return this.fifoMode;
    }

    public void setFifoMode(LIS2DW12_FIFO_MODE lis2dw12_fifo_mode) {
        this.fifoMode = lis2dw12_fifo_mode;
    }

    public LIS2DW12_FIFO_THRESHOLD getFifoThreshold() {
        return this.fifoThreshold;
    }

    public void setFifoThreshold(LIS2DW12_FIFO_THRESHOLD lis2dw12_fifo_threshold) {
        this.fifoThreshold = lis2dw12_fifo_threshold;
    }

    public LIS2DW12_FILTERED_DATA_TYPE_SELECTION getFilteredDataTypeSelection() {
        return this.fds;
    }

    public LIS2DW12_HP_REF_MODE getHpFilterMode() {
        return this.hpFilterMode;
    }

    public void setHpFilterMode(LIS2DW12_HP_REF_MODE lis2dw12_hp_ref_mode) {
        this.hpFilterMode = lis2dw12_hp_ref_mode;
    }

    public LIS2DW12_LOW_NOISE getLowNoise() {
        return this.lowNoise;
    }

    public void setLowNoise(LIS2DW12_LOW_NOISE lis2dw12_low_noise) {
        this.lowNoise = lis2dw12_low_noise;
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

    public void setFds(LIS2DW12_FILTERED_DATA_TYPE_SELECTION lis2dw12_filtered_data_type_selection) {
        this.fds = lis2dw12_filtered_data_type_selection;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(SENSOR_MAP_REF, CHANNEL_MAP_REF);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.clear();
        addConfigOption(CONFIG_OPTION_ACCEL_RANGE);
        addConfigOption(CONFIG_OPTION_ACCEL_LP_MODE);
        addConfigOption(CONFIG_OPTION_ACCEL_MODE);
        addConfigOption(CONFIG_OPTION_ACCEL_RATE_HP);
        addConfigOption(CONFIG_OPTION_ACCEL_RATE_LP);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        int iOrdinal = Configuration.Shimmer3.LABEL_SENSOR_TILE.BRIDGE_AMPLIFIER.ordinal();
        if (this.mShimmerVerObject.isShimmerGenVerisense()) {
            this.mSensorGroupingMap.put(Integer.valueOf(iOrdinal), new SensorGroupingDetails("ACCEL", Arrays.asList(2005), Configuration.Verisense.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS2DW12));
        }
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        ChannelDetails channelDetails = CHANNEL_LISDW12_ACCEL_X;
        ChannelDetails channelDetails2 = CHANNEL_LISDW12_ACCEL_Y;
        ChannelDetails channelDetails3 = CHANNEL_LISDW12_ACCEL_Z;
        double[] dArr = {objectClusterProcessDataCommon.getFormatClusterValue(channelDetails, ChannelDetails.CHANNEL_TYPE.UNCAL), objectClusterProcessDataCommon.getFormatClusterValue(channelDetails2, ChannelDetails.CHANNEL_TYPE.UNCAL), objectClusterProcessDataCommon.getFormatClusterValue(channelDetails3, ChannelDetails.CHANNEL_TYPE.UNCAL)};
        if (UByte$$ExternalSyntheticBackport0.m38878m(dArr[0]) && UByte$$ExternalSyntheticBackport0.m38878m(dArr[1]) && UByte$$ExternalSyntheticBackport0.m38878m(dArr[2])) {
            double[] dArrCalibrateInertialSensorData = UtilCalibration.calibrateInertialSensorData(dArr, this.mCurrentCalibDetailsAccel.getDefaultMatrixMultipliedInverseAMSM(), this.mCurrentCalibDetailsAccel.getDefaultOffsetVector());
            objectClusterProcessDataCommon.addCalData(channelDetails, dArrCalibrateInertialSensorData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3);
            objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2);
            objectClusterProcessDataCommon.addCalData(channelDetails3, dArrCalibrateInertialSensorData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1);
            if (this.mCurrentCalibDetailsAccel.isCurrentValuesSet()) {
                double[] dArrCalibrateImuData = UtilCalibration.calibrateImuData(dArrCalibrateInertialSensorData, this.mCurrentCalibDetailsAccel.getCurrentSensitivityMatrix(), this.mCurrentCalibDetailsAccel.getCurrentOffsetVector());
                objectClusterProcessDataCommon.addData(channelDetails.mObjectClusterName, ChannelDetails.CHANNEL_TYPE.DERIVED, channelDetails.mDefaultCalUnits, dArrCalibrateImuData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, false);
                objectClusterProcessDataCommon.addData(channelDetails2.mObjectClusterName, ChannelDetails.CHANNEL_TYPE.DERIVED, channelDetails2.mDefaultCalUnits, dArrCalibrateImuData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, false);
                objectClusterProcessDataCommon.addData(channelDetails3.mObjectClusterName, ChannelDetails.CHANNEL_TYPE.DERIVED, channelDetails3.mDefaultCalUnits, dArrCalibrateImuData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1, false);
            }
        }
        return objectClusterProcessDataCommon;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        if (isSensorEnabled(2005)) {
            ConfigByteLayoutLis2dw12 configByteLayoutLis2dw12 = new ConfigByteLayoutLis2dw12(communication_type);
            int i = configByteLayoutLis2dw12.idxFsAccel1;
            bArr[i] = (byte) (bArr[i] & (~(configByteLayoutLis2dw12.maskbitFsAccel1 << configByteLayoutLis2dw12.bitShiftFsAccel1)));
            int i2 = configByteLayoutLis2dw12.idxFsAccel1;
            bArr[i2] = (byte) (bArr[i2] | ((getAccelRangeConfigValue() & configByteLayoutLis2dw12.maskbitFsAccel1) << configByteLayoutLis2dw12.bitShiftFsAccel1));
            bArr[configByteLayoutLis2dw12.idxAccel1Cfg0] = 0;
            int i3 = configByteLayoutLis2dw12.idxAccel1Cfg0;
            bArr[i3] = (byte) (bArr[i3] | ((getAccelRateConfigValue() & configByteLayoutLis2dw12.maskAccelRate) << configByteLayoutLis2dw12.bitShiftAccelRate));
            int i4 = configByteLayoutLis2dw12.idxAccel1Cfg0;
            bArr[i4] = (byte) (bArr[i4] | ((getAccelModeConfigValue() & configByteLayoutLis2dw12.maskMode) << configByteLayoutLis2dw12.bitShiftMode));
            int i5 = configByteLayoutLis2dw12.idxAccel1Cfg0;
            bArr[i5] = (byte) (bArr[i5] | ((getAccelLpModeConfigValue() & configByteLayoutLis2dw12.maskLpMode) << configByteLayoutLis2dw12.bitShiftLpMode));
            if (configByteLayoutLis2dw12.idxAccel1Cfg1 >= 0) {
                int i6 = configByteLayoutLis2dw12.idxAccel1Cfg1;
                bArr[i6] = (byte) (bArr[i6] & (configByteLayoutLis2dw12.maskbitFsAccel1 << configByteLayoutLis2dw12.bitShiftFsAccel1));
                int i7 = configByteLayoutLis2dw12.idxAccel1Cfg1;
                bArr[i7] = (byte) (bArr[i7] | ((getBwFilt().configValue.intValue() & configByteLayoutLis2dw12.maskBwFilt) << configByteLayoutLis2dw12.bitShiftBwFilt));
                int i8 = configByteLayoutLis2dw12.idxAccel1Cfg1;
                bArr[i8] = (byte) (bArr[i8] | ((getFilteredDataTypeSelection().configValue.intValue() & configByteLayoutLis2dw12.maskFds) << configByteLayoutLis2dw12.bitShiftFds));
                int i9 = configByteLayoutLis2dw12.idxAccel1Cfg1;
                bArr[i9] = (byte) (bArr[i9] | ((getLowNoise().configValue.intValue() & configByteLayoutLis2dw12.maskLowNoise) << configByteLayoutLis2dw12.bitShiftLowNoise));
            }
            if (configByteLayoutLis2dw12.idxAccel1Cfg2 >= 0) {
                int i10 = configByteLayoutLis2dw12.idxAccel1Cfg2;
                bArr[i10] = (byte) (bArr[i10] & (~(configByteLayoutLis2dw12.maskHpRefMode << configByteLayoutLis2dw12.bitShiftHpRefMode)));
                int i11 = configByteLayoutLis2dw12.idxAccel1Cfg2;
                bArr[i11] = (byte) (bArr[i11] | ((getHpFilterMode().configValue.intValue() & configByteLayoutLis2dw12.maskHpRefMode) << configByteLayoutLis2dw12.bitShiftHpRefMode));
            }
            if (configByteLayoutLis2dw12.idxAccel1Cfg3 >= 0) {
                bArr[configByteLayoutLis2dw12.idxAccel1Cfg3] = 0;
                int i12 = configByteLayoutLis2dw12.idxAccel1Cfg3;
                bArr[i12] = (byte) (bArr[i12] | ((getFifoMode().configValue.intValue() & configByteLayoutLis2dw12.maskFifoMode) << configByteLayoutLis2dw12.bitShiftFifoMode));
                int i13 = configByteLayoutLis2dw12.idxAccel1Cfg3;
                bArr[i13] = (byte) (((getFifoThreshold().configValue.intValue() & configByteLayoutLis2dw12.maskFifoThreshold) << configByteLayoutLis2dw12.bitShiftFifoThreshold) | bArr[i13]);
            }
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        if (isSensorEnabled(2005)) {
            ConfigByteLayoutLis2dw12 configByteLayoutLis2dw12 = new ConfigByteLayoutLis2dw12(communication_type);
            setAccelRangeConfigValue((bArr[configByteLayoutLis2dw12.idxFsAccel1] >> configByteLayoutLis2dw12.bitShiftFsAccel1) & configByteLayoutLis2dw12.maskbitFsAccel1);
            byte b = bArr[configByteLayoutLis2dw12.idxAccel1Cfg0];
            setAccelModeConfigValue((b >> configByteLayoutLis2dw12.bitShiftMode) & configByteLayoutLis2dw12.maskMode);
            setAccelLpModeConfigValue(b & 3);
            setAccelRateConfigValue((b >> configByteLayoutLis2dw12.bitShiftAccelRate) & configByteLayoutLis2dw12.maskAccelRate);
            if (configByteLayoutLis2dw12.idxAccel1Cfg1 >= 0) {
                setBwFilt(LIS2DW12_BW_FILT.getForConfigValue((bArr[configByteLayoutLis2dw12.idxAccel1Cfg1] >> configByteLayoutLis2dw12.bitShiftBwFilt) & configByteLayoutLis2dw12.maskBwFilt));
                setFds(LIS2DW12_FILTERED_DATA_TYPE_SELECTION.getForConfigValue((bArr[configByteLayoutLis2dw12.idxAccel1Cfg1] >> configByteLayoutLis2dw12.bitShiftFds) & configByteLayoutLis2dw12.maskFds));
                setLowNoise(LIS2DW12_LOW_NOISE.getForConfigValue((bArr[configByteLayoutLis2dw12.idxAccel1Cfg1] >> configByteLayoutLis2dw12.bitShiftLowNoise) & configByteLayoutLis2dw12.maskLowNoise));
            }
            if (configByteLayoutLis2dw12.idxAccel1Cfg2 >= 0) {
                setHpFilterMode(LIS2DW12_HP_REF_MODE.getForConfigValue((bArr[configByteLayoutLis2dw12.idxAccel1Cfg2] >> configByteLayoutLis2dw12.bitShiftHpRefMode) & configByteLayoutLis2dw12.maskHpRefMode));
            }
            if (configByteLayoutLis2dw12.idxAccel1Cfg3 >= 0) {
                setFifoMode(LIS2DW12_FIFO_MODE.getForConfigValue((bArr[configByteLayoutLis2dw12.idxAccel1Cfg3] >> configByteLayoutLis2dw12.bitShiftFifoMode) & configByteLayoutLis2dw12.maskFifoMode));
                setFifoThreshold(LIS2DW12_FIFO_THRESHOLD.getForConfigValue(configByteLayoutLis2dw12.maskFifoThreshold & (bArr[configByteLayoutLis2dw12.idxAccel1Cfg3] >> configByteLayoutLis2dw12.bitShiftFifoThreshold)));
            }
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "Mode":
                setAccelModeConfigValue(((Integer) obj).intValue());
                return null;
            case "Range":
                setAccelRangeConfigValue(((Integer) obj).intValue());
                return null;
            case "Accel_Rate":
                setAccelRateConfigValue(((Integer) obj).intValue());
                return null;
            case "LP Mode":
                setAccelLpModeConfigValue(((Integer) obj).intValue());
                return null;
            default:
                return super.setConfigValueUsingConfigLabelCommon(num, str, obj);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        switch (str) {
            case "Mode":
                return Integer.valueOf(getAccelModeConfigValue());
            case "Rate":
                return Double.valueOf(getAccelRateFreq());
            case "Range":
                return Integer.valueOf(getAccelRangeConfigValue());
            case "Calibration Current":
                if (num.intValue() == 2005) {
                    return this.mCurrentCalibDetailsAccel;
                }
                return null;
            case "Accel_Rate":
                return Integer.valueOf(getAccelRateConfigValue());
            case "LP Mode":
                return Integer.valueOf(getAccelLpModeConfigValue());
            default:
                return super.getConfigValueUsingConfigLabelCommon(num, str);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        int accelModeConfigValue = getAccelModeConfigValue();
        setAccelRateConfigValue(d == 0.0d ? 0 : (d > 1.6d || accelModeConfigValue != 0) ? d <= 12.5d ? 2 : d <= 25.0d ? 3 : d <= 50.0d ? 4 : d <= 100.0d ? 5 : (d <= 200.0d || accelModeConfigValue == 0) ? 6 : d <= 400.0d ? 7 : d <= 800.0d ? 8 : 9 : 1);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        setAccelRange(LIS2DW12_ACCEL_RANGE.RANGE_4G);
        setAccelRate(LIS2DW12_ACCEL_RATE.LOW_POWER_25_0_HZ);
        setAccelMode(LIS2DW12_MODE.HIGH_PERFORMANCE);
        setAccelLpMode(LIS2DW12_LP_MODE.LOW_POWER1_12BIT_4_5_MG_NOISE);
        setBwFilt(LIS2DW12_BW_FILT.ODR_DIVIDED_BY_2);
        setFds(LIS2DW12_FILTERED_DATA_TYPE_SELECTION.LOW_PASS_FILTER_PATH_SELECTED);
        setLowNoise(LIS2DW12_LOW_NOISE.DISABLED);
        setHpFilterMode(LIS2DW12_HP_REF_MODE.DISABLED);
        setFifoMode(LIS2DW12_FIFO_MODE.CONTINUOUS_TO_FIFO_MODE);
        setFifoThreshold(LIS2DW12_FIFO_THRESHOLD.SAMPLE_31);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        super.initialise();
        updateCurrentAccelCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsAccel2g.mRangeValue), this.calibDetailsAccel2g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccel4g.mRangeValue), this.calibDetailsAccel4g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccel8g.mRangeValue), this.calibDetailsAccel8g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccel16g.mRangeValue), this.calibDetailsAccel16g);
        setCalibrationMapPerSensor(2005, treeMap);
        updateCurrentAccelCalibInUse();
    }

    public void updateCurrentAccelCalibInUse() {
        this.mCurrentCalibDetailsAccel = getCurrentCalibDetailsIfKinematic(2005, getAccelRangeConfigValue());
    }

    public int getAccelRangeConfigValue() {
        return this.range.configValue.intValue();
    }

    public void setAccelRangeConfigValue(int i) {
        setAccelRange(LIS2DW12_ACCEL_RANGE.getForConfigValue(i));
    }

    public int getAccelRateConfigValue() {
        return getAccelRate().configValue.intValue();
    }

    public void setAccelRateConfigValue(int i) {
        setAccelRate(LIS2DW12_ACCEL_RATE.getForConfigValue(i, this.mode));
    }

    public double getAccelRateFreq() {
        return getAccelRate().freqHz;
    }

    public int getAccelModeConfigValue() {
        return this.mode.configValue.intValue();
    }

    public void setAccelModeConfigValue(int i) {
        setAccelMode(LIS2DW12_MODE.getForConfigValue(i));
    }

    public int getAccelLpModeConfigValue() {
        return this.lpMode.configValue.intValue();
    }

    public void setAccelLpModeConfigValue(int i) {
        setAccelLpMode(LIS2DW12_LP_MODE.getForConfigValue(i));
    }

    public String getAccelLpModeString() {
        return this.mode == LIS2DW12_MODE.LOW_POWER ? CONFIG_OPTION_ACCEL_LP_MODE.getConfigStringFromConfigValue(Integer.valueOf(getAccelLpModeConfigValue())) : "N/A";
    }

    public String getAccelModeString() {
        return CONFIG_OPTION_ACCEL_MODE.getConfigStringFromConfigValue(Integer.valueOf(getAccelModeConfigValue()));
    }

    public String getAccelRangeString() {
        String strReplaceAll = CONFIG_OPTION_ACCEL_RANGE.getConfigStringFromConfigValue(Integer.valueOf(getAccelRangeConfigValue())).replaceAll(UtilShimmer.UNICODE_PLUS_MINUS, "+-");
        return (!(this.mShimmerDevice instanceof VerisenseDevice) || ((VerisenseDevice) this.mShimmerDevice).isCsvHeaderDesignAzMarkingPoint()) ? strReplaceAll : strReplaceAll.replaceAll(Configuration.CHANNEL_UNITS.GRAVITY, " g");
    }

    public String getAccelModeMergedString() {
        if (this.mode == LIS2DW12_MODE.HIGH_PERFORMANCE) {
            return LIS2DW12_MODE_MERGED[0];
        }
        return LIS2DW12_MODE_MERGED[this.lpMode.configValue.intValue() + 1];
    }

    public String getAccelResolutionString() {
        if (this.mode == LIS2DW12_MODE.LOW_POWER && this.lpMode == LIS2DW12_LP_MODE.LOW_POWER1_12BIT_4_5_MG_NOISE) {
            return LIS2DW12_RESOLUTION[0];
        }
        return LIS2DW12_RESOLUTION[1];
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public List<ISensorConfig> getSensorConfig() {
        List<ISensorConfig> sensorConfig = super.getSensorConfig();
        sensorConfig.add(getFilteredDataTypeSelection());
        sensorConfig.add(getAccelLpMode());
        sensorConfig.add(getAccelMode());
        sensorConfig.add(getAccelRange());
        sensorConfig.add(getAccelRate());
        sensorConfig.add(getBwFilt());
        sensorConfig.add(getLowNoise());
        sensorConfig.add(getHpFilterMode());
        sensorConfig.add(getFifoMode());
        sensorConfig.add(getFifoThreshold());
        return sensorConfig;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorConfig(ISensorConfig iSensorConfig) {
        boolean z = iSensorConfig instanceof LIS2DW12_ACCEL_RATE;
        if (z) {
            setAccelRate((LIS2DW12_ACCEL_RATE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_LP_MODE) {
            setAccelLpMode((LIS2DW12_LP_MODE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_ACCEL_RANGE) {
            setAccelRange((LIS2DW12_ACCEL_RANGE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_FILTERED_DATA_TYPE_SELECTION) {
            setFds((LIS2DW12_FILTERED_DATA_TYPE_SELECTION) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_LOW_NOISE) {
            setLowNoise((LIS2DW12_LOW_NOISE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_MODE) {
            setAccelMode((LIS2DW12_MODE) iSensorConfig);
            return;
        }
        if (z) {
            setAccelRate((LIS2DW12_ACCEL_RATE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_BW_FILT) {
            setBwFilt((LIS2DW12_BW_FILT) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_HP_REF_MODE) {
            setHpFilterMode((LIS2DW12_HP_REF_MODE) iSensorConfig);
            return;
        }
        if (iSensorConfig instanceof LIS2DW12_FIFO_MODE) {
            setFifoMode((LIS2DW12_FIFO_MODE) iSensorConfig);
        } else if (iSensorConfig instanceof LIS2DW12_FIFO_THRESHOLD) {
            setFifoThreshold((LIS2DW12_FIFO_THRESHOLD) iSensorConfig);
        } else {
            super.setSensorConfig(iSensorConfig);
        }
    }

    public enum LIS2DW12_FILTERED_DATA_TYPE_SELECTION implements ISensorConfig {
        LOW_PASS_FILTER_PATH_SELECTED("Low-pass filter path selected", 0),
        HIGH_PASS_FILTER_PATH_SELECTED("High-pass filter path selected", 1);

        static Map<Integer, LIS2DW12_FILTERED_DATA_TYPE_SELECTION> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_FILTERED_DATA_TYPE_SELECTION lis2dw12_filtered_data_type_selection : values()) {
                REF_MAP.put(lis2dw12_filtered_data_type_selection.label, lis2dw12_filtered_data_type_selection.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_FILTERED_DATA_TYPE_SELECTION lis2dw12_filtered_data_type_selection2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_filtered_data_type_selection2.configValue, lis2dw12_filtered_data_type_selection2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_FILTERED_DATA_TYPE_SELECTION(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_FILTERED_DATA_TYPE_SELECTION getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, LOW_PASS_FILTER_PATH_SELECTED.configValue.intValue(), HIGH_PASS_FILTER_PATH_SELECTED.configValue.intValue())));
        }
    }

    public enum LIS2DW12_LOW_NOISE implements ISensorConfig {
        DISABLED("Disabled", 0),
        ENABLED("Enabled", 1);

        static Map<Integer, LIS2DW12_LOW_NOISE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_LOW_NOISE lis2dw12_low_noise : values()) {
                REF_MAP.put(lis2dw12_low_noise.label, lis2dw12_low_noise.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_LOW_NOISE lis2dw12_low_noise2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_low_noise2.configValue, lis2dw12_low_noise2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_LOW_NOISE(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_LOW_NOISE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, DISABLED.configValue.intValue(), ENABLED.configValue.intValue())));
        }
    }

    public enum LIS2DW12_BW_FILT implements ISensorConfig {
        ODR_DIVIDED_BY_2("ODR/2 (up to ODR = 800 Hz, 400 Hz when ODR = 1600 Hz)", 0),
        ODR_DIVIDED_BY_4("ODR/4 (HP/LP)", 1),
        ODR_DIVIDED_BY_10("ODR/10 (HP/LP)", 2),
        ODR_DIVIDED_BY_20("ODR/20 (HP/LP)", 3);

        static Map<Integer, LIS2DW12_BW_FILT> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_BW_FILT lis2dw12_bw_filt : values()) {
                REF_MAP.put(lis2dw12_bw_filt.label, lis2dw12_bw_filt.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_BW_FILT lis2dw12_bw_filt2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_bw_filt2.configValue, lis2dw12_bw_filt2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_BW_FILT(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_BW_FILT getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, ODR_DIVIDED_BY_2.configValue.intValue(), ODR_DIVIDED_BY_20.configValue.intValue())));
        }
    }

    public enum LIS2DW12_MODE implements ISensorConfig {
        LOW_POWER("Low-Power Mode (12/14-bit resolution)", 0),
        HIGH_PERFORMANCE("High-Performance Mode (14-bit resolution)", 1);

        static Map<Integer, LIS2DW12_MODE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_MODE lis2dw12_mode : values()) {
                REF_MAP.put(lis2dw12_mode.label, lis2dw12_mode.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_MODE lis2dw12_mode2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_mode2.configValue, lis2dw12_mode2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_MODE(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_MODE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, LOW_POWER.configValue.intValue(), HIGH_PERFORMANCE.configValue.intValue())));
        }
    }

    public enum LIS2DW12_ACCEL_RATE implements ISensorConfig {
        POWER_DOWN("Power-down", 0, 0.0d, LIS2DW12_MODE.LOW_POWER),
        HIGH_PERFORMANCE_12_5_HZ("12.5Hz", 1, 12.5d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_25_0_HZ("25.0Hz", 3, 25.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_50_0_HZ("50.0Hz", 4, 50.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_100_0_HZ("100.0Hz", 5, 100.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_200_0_HZ("200.0Hz", 6, 200.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_400_0_HZ("400.0Hz", 7, 400.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_800_0_HZ("800.0Hz", 8, 800.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        HIGH_PERFORMANCE_1600_0_HZ("1600.0Hz", 9, 1600.0d, LIS2DW12_MODE.HIGH_PERFORMANCE),
        LOW_POWER_1_6_HZ("1.6Hz", 1, 1.6d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_12_5_HZ("12.5Hz", 2, 12.5d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_25_0_HZ("25.0Hz", 3, 25.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_50_0_HZ("50.0Hz", 4, 50.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_100_0_HZ("100.0Hz", 5, 100.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_200_0_HZ("200.0Hz", 6, 200.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_200_0_HZ_ALT1("200.0Hz", 7, 200.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_200_0_HZ_ALT2("200.0Hz", 8, 200.0d, LIS2DW12_MODE.LOW_POWER),
        LOW_POWER_200_0_HZ_ALT3("200.0Hz", 9, 200.0d, LIS2DW12_MODE.LOW_POWER);

        public static Map<String, Integer> REF_MAP_HP = new HashMap();
        public static Map<String, Integer> REF_MAP_LP = new HashMap();

        static {
            for (LIS2DW12_ACCEL_RATE lis2dw12_accel_rate : values()) {
                if (lis2dw12_accel_rate.mode == LIS2DW12_MODE.HIGH_PERFORMANCE || lis2dw12_accel_rate == POWER_DOWN) {
                    REF_MAP_HP.put(lis2dw12_accel_rate.label, lis2dw12_accel_rate.configValue);
                }
                if (lis2dw12_accel_rate.mode == LIS2DW12_MODE.LOW_POWER || lis2dw12_accel_rate == POWER_DOWN) {
                    REF_MAP_LP.put(lis2dw12_accel_rate.label, lis2dw12_accel_rate.configValue);
                }
            }
        }

        public Integer configValue;
        public double freqHz;
        public String label;
        public LIS2DW12_MODE mode;

        LIS2DW12_ACCEL_RATE(String str, int i, double d, LIS2DW12_MODE lis2dw12_mode) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
            this.freqHz = d;
            this.mode = lis2dw12_mode;
        }

        public static String[] getLabelsHp() {
            return (String[]) REF_MAP_HP.keySet().toArray(new String[REF_MAP_HP.keySet().size()]);
        }

        public static Integer[] getConfigValuesHp() {
            return (Integer[]) REF_MAP_HP.values().toArray(new Integer[REF_MAP_HP.values().size()]);
        }

        public static String[] getLabelsLp() {
            return (String[]) REF_MAP_LP.keySet().toArray(new String[REF_MAP_LP.keySet().size()]);
        }

        public static Integer[] getConfigValuesLp() {
            return (Integer[]) REF_MAP_LP.values().toArray(new Integer[REF_MAP_LP.values().size()]);
        }

        public static LIS2DW12_ACCEL_RATE getForConfigValue(int i, LIS2DW12_MODE lis2dw12_mode) {
            LIS2DW12_ACCEL_RATE lis2dw12_accel_rate = POWER_DOWN;
            int iNudgeInteger = UtilShimmer.nudgeInteger(i, lis2dw12_accel_rate.configValue.intValue(), HIGH_PERFORMANCE_1600_0_HZ.configValue.intValue());
            if (iNudgeInteger == lis2dw12_accel_rate.configValue.intValue()) {
                return lis2dw12_accel_rate;
            }
            for (LIS2DW12_ACCEL_RATE lis2dw12_accel_rate2 : values()) {
                if (lis2dw12_mode == lis2dw12_accel_rate2.mode && lis2dw12_accel_rate2.configValue.intValue() == iNudgeInteger) {
                    return lis2dw12_accel_rate2;
                }
            }
            return POWER_DOWN;
        }
    }

    public enum LIS2DW12_ACCEL_RANGE implements ISensorConfig {
        RANGE_2G(SensorKionixAccel.LN_ACCEL_RANGE_STRING, 0),
        RANGE_4G("± 4g", 1),
        RANGE_8G("± 8g", 2),
        RANGE_16G("± 16g", 3);

        static Map<Integer, LIS2DW12_ACCEL_RANGE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_ACCEL_RANGE lis2dw12_accel_range : values()) {
                REF_MAP.put(lis2dw12_accel_range.label, lis2dw12_accel_range.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_ACCEL_RANGE lis2dw12_accel_range2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_accel_range2.configValue, lis2dw12_accel_range2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_ACCEL_RANGE(String str, Integer num) {
            this.label = str;
            this.configValue = num;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_ACCEL_RANGE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, RANGE_2G.configValue.intValue(), RANGE_16G.configValue.intValue())));
        }
    }

    public enum LIS2DW12_LP_MODE implements ISensorConfig {
        LOW_POWER1_12BIT_4_5_MG_NOISE("LP1: 12-bit resolution, Noise=4.5mg(RMS)", 0),
        LOW_POWER2_14BIT_2_4_MG_NOISE("LP2: 14-bit resolution, Noise=2.4mg(RMS)", 1),
        LOW_POWER3_14BIT_1_8_MG_NOISE("LP3: 14-bit resolution, Noise=1.8mg(RMS)", 2),
        LOW_POWER4_14BIT_1_3_MG_NOISE("LP4: 14-bit resolution, Noise=1.3mg(RMS)", 3);

        static Map<Integer, LIS2DW12_LP_MODE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_LP_MODE lis2dw12_lp_mode : values()) {
                REF_MAP.put(lis2dw12_lp_mode.label, lis2dw12_lp_mode.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_LP_MODE lis2dw12_lp_mode2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_lp_mode2.configValue, lis2dw12_lp_mode2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_LP_MODE(String str, Integer num) {
            this.label = str;
            this.configValue = num;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_LP_MODE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, LOW_POWER1_12BIT_4_5_MG_NOISE.configValue.intValue(), LOW_POWER4_14BIT_1_3_MG_NOISE.configValue.intValue())));
        }
    }

    public enum LIS2DW12_HP_REF_MODE implements ISensorConfig {
        DISABLED("High-pass filter reference mode disabled", 0),
        ENABLED("High-pass filter reference mode enabled", 1);

        static Map<Integer, LIS2DW12_HP_REF_MODE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_HP_REF_MODE lis2dw12_hp_ref_mode : values()) {
                REF_MAP.put(lis2dw12_hp_ref_mode.label, lis2dw12_hp_ref_mode.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_HP_REF_MODE lis2dw12_hp_ref_mode2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_hp_ref_mode2.configValue, lis2dw12_hp_ref_mode2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_HP_REF_MODE(String str, int i) {
            this.label = str;
            this.configValue = Integer.valueOf(i);
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_HP_REF_MODE getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, DISABLED.configValue.intValue(), ENABLED.configValue.intValue())));
        }
    }

    public enum LIS2DW12_FIFO_MODE implements ISensorConfig {
        BYPASS_MODE("Bypass mode: FIFO turned off", 0),
        FIFO_MODE("FIFO mode: Stops collecting data when FIFO is full", 1),
        CONTINUOUS_TO_FIFO_MODE("Continuous-to-FIFO: Stream mode until trigger is deasserted, then FIFO mode", 3),
        BYPASS_TO_FIFO_MODE("Bypass-to-Continuous: Bypass mode until trigger is deasserted, then FIFO mode", 4),
        CONTINUOUS_MODE("Continuous mode: If the FIFO is full, the new sample overwrites the older sample", 6);

        static Map<Integer, LIS2DW12_FIFO_MODE> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_FIFO_MODE lis2dw12_fifo_mode : values()) {
                REF_MAP.put(lis2dw12_fifo_mode.label, lis2dw12_fifo_mode.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_FIFO_MODE lis2dw12_fifo_mode2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_fifo_mode2.configValue, lis2dw12_fifo_mode2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_FIFO_MODE(String str, Integer num) {
            this.label = str;
            this.configValue = num;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_FIFO_MODE getForConfigValue(int i) {
            for (LIS2DW12_FIFO_MODE lis2dw12_fifo_mode : values()) {
                if (i == lis2dw12_fifo_mode.configValue.intValue()) {
                    return lis2dw12_fifo_mode;
                }
            }
            return CONTINUOUS_TO_FIFO_MODE;
        }
    }

    public enum LIS2DW12_FIFO_THRESHOLD implements ISensorConfig {
        SAMPLE_0("0", 0),
        SAMPLE_1("1", 1),
        SAMPLE_2("2", 2),
        SAMPLE_3("3", 3),
        SAMPLE_4("4", 4),
        SAMPLE_5("5", 5),
        SAMPLE_6("6", 6),
        SAMPLE_7("7", 7),
        SAMPLE_8("8", 8),
        SAMPLE_9("9", 9),
        SAMPLE_10("10", 10),
        SAMPLE_11("11", 11),
        SAMPLE_12("12", 12),
        SAMPLE_13("13", 13),
        SAMPLE_14("14", 14),
        SAMPLE_15("15", 15),
        SAMPLE_16("16", 16),
        SAMPLE_17("17", 17),
        SAMPLE_18("18", 18),
        SAMPLE_19("19", 19),
        SAMPLE_20("20", 20),
        SAMPLE_21("21", 21),
        SAMPLE_22("22", 22),
        SAMPLE_23("23", 23),
        SAMPLE_24("24", 24),
        SAMPLE_25("25", 25),
        SAMPLE_26("26", 26),
        SAMPLE_27("27", 27),
        SAMPLE_28("28", 28),
        SAMPLE_29("29", 29),
        SAMPLE_30("30", 30),
        SAMPLE_31("31", 31);

        static Map<Integer, LIS2DW12_FIFO_THRESHOLD> BY_CONFIG_VALUE;
        static Map<String, Integer> REF_MAP = new HashMap();

        static {
            for (LIS2DW12_FIFO_THRESHOLD lis2dw12_fifo_threshold : values()) {
                REF_MAP.put(lis2dw12_fifo_threshold.label, lis2dw12_fifo_threshold.configValue);
            }
            BY_CONFIG_VALUE = new HashMap();
            for (LIS2DW12_FIFO_THRESHOLD lis2dw12_fifo_threshold2 : values()) {
                BY_CONFIG_VALUE.put(lis2dw12_fifo_threshold2.configValue, lis2dw12_fifo_threshold2);
            }
        }

        Integer configValue;
        String label;

        LIS2DW12_FIFO_THRESHOLD(String str, Integer num) {
            this.label = str;
            this.configValue = num;
        }

        public static String[] getLabels() {
            return (String[]) REF_MAP.keySet().toArray(new String[REF_MAP.keySet().size()]);
        }

        public static Integer[] getConfigValues() {
            return (Integer[]) REF_MAP.values().toArray(new Integer[REF_MAP.values().size()]);
        }

        public static LIS2DW12_FIFO_THRESHOLD getForConfigValue(int i) {
            return BY_CONFIG_VALUE.get(Integer.valueOf(UtilShimmer.nudgeInteger(i, SAMPLE_0.configValue.intValue(), SAMPLE_31.configValue.intValue())));
        }
    }

    public static class DatabaseChannelHandles {
        public static final String LIS2DW12_ACC_X = "LIS2DW12_ACC_X";
        public static final String LIS2DW12_ACC_Y = "LIS2DW12_ACC_Y";
        public static final String LIS2DW12_ACC_Z = "LIS2DW12_ACC_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String LIS2DW12_LP_MODE = "LIS2DW12_LpMode";
        public static final String LIS2DW12_MODE = "LIS2DW12_Mode";
        public static final String LIS2DW12_RANGE = "LIS2DW12_Mag_Range";
        public static final String LIS2DW12_RATE = "LIS2DW12_Mag_Rate";
    }

    public static class LABEL_SENSOR_TILE {
        public static final String ACCEL = "ACCEL";
    }

    public static class ObjectClusterSensorName {
        public static String LIS2DW12_ACC_X = "Accel1_X";
        public static String LIS2DW12_ACC_Y = "Accel1_Y";
        public static String LIS2DW12_ACC_Z = "Accel1_Z";
    }

    public class GuiLabelSensors {
        public static final String ACCEL1 = "Accelerometer1";

        public GuiLabelSensors() {
        }
    }

    public class GuiLabelConfig {
        public static final String LIS2DW12_LP_MODE = "LP Mode";
        public static final String LIS2DW12_MODE = "Mode";
        public static final String LIS2DW12_RANGE = "Range";
        public static final String LIS2DW12_RATE = "Accel_Rate";

        public GuiLabelConfig() {
        }
    }

    private class ConfigByteLayoutLis2dw12 {
        public int bitShiftFsAccel1;
        public int idxAccel1Cfg0;
        public int idxAccel1Cfg1;
        public int idxAccel1Cfg2;
        public int idxAccel1Cfg3;
        public int idxFsAccel1;
        public int maskbitFsAccel1 = 3;
        public int maskAccelRate = 15;
        public int bitShiftAccelRate = 4;
        public int maskMode = 3;
        public int bitShiftMode = 2;
        public int maskLpMode = 3;
        public int bitShiftLpMode = 0;
        public int maskBwFilt = 3;
        public int bitShiftBwFilt = 6;
        public int maskFds = 1;
        public int bitShiftFds = 3;
        public int maskHpRefMode = 1;
        public int bitShiftHpRefMode = 1;
        public int maskLowNoise = 1;
        public int bitShiftLowNoise = 2;
        public int maskFifoMode = 7;
        public int bitShiftFifoMode = 5;
        public int maskFifoThreshold = 31;
        public int bitShiftFifoThreshold = 0;

        public ConfigByteLayoutLis2dw12(Configuration.COMMUNICATION_TYPE communication_type) {
            this.idxAccel1Cfg0 = -1;
            this.idxAccel1Cfg1 = -1;
            this.idxAccel1Cfg2 = -1;
            this.idxAccel1Cfg3 = -1;
            this.idxFsAccel1 = -1;
            this.bitShiftFsAccel1 = 0;
            if (communication_type == Configuration.COMMUNICATION_TYPE.SD) {
                this.idxFsAccel1 = 0;
                this.bitShiftFsAccel1 = 2;
                this.idxAccel1Cfg0 = 1;
            } else {
                this.idxAccel1Cfg0 = 5;
                this.idxAccel1Cfg1 = 6;
                this.idxAccel1Cfg2 = 7;
                this.idxAccel1Cfg3 = 8;
                this.idxFsAccel1 = 6;
                this.bitShiftFsAccel1 = 4;
            }
        }
    }
}
