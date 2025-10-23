package com.shimmerresearch.sensors.lisxmdl;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.UtilCalibration;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorLIS3MDL extends AbstractSensor {
    public static final byte ALT_MAG_CALIBRATION_RESPONSE = -80;
    public static final byte ALT_MAG_SAMPLING_RATE_RESPONSE = -77;
    public static final double[][] DefaultAlignmentLIS3MDL;
    public static final double[][] DefaultAlignmentMatrixAltMagShimmer3r;
    public static final byte GET_ALT_MAG_CALIBRATION_COMMAND = -79;
    public static final byte GET_ALT_MAG_SAMPLING_RATE_COMMAND = -76;
    public static final String[] ListofLIS3MDLAltMagRange;
    public static final Integer[] ListofLIS3MDLAltMagRangeConfigValues;
    public static final String[] ListofLIS3MDLAltMagRate;
    public static final Integer[] ListofLIS3MDLAltMagRateConfigValues;
    public static final byte SET_ALT_MAG_CALIBRATION_COMMAND = -81;
    public static final byte SET_ALT_MAG_SAMPLING_RATE_COMMAND = -78;
    public static final ChannelDetails channelLIS3MDLAltMagX;
    public static final ChannelDetails channelLIS3MDLAltMagY;
    public static final ChannelDetails channelLIS3MDLAltMagZ;
    public static final ConfigOptionDetailsSensor configOptionAltMagRange;
    public static final ConfigOptionDetailsSensor configOptionAltMagRate;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLisAltMag;
    public static final SensorDetailsRef sensorLIS3MDLAltMag;
    public static final double[][] DefaultOffsetVectorAltMagShimmer3r = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] DefaultSensitivityMatrixAltMag4GaShimmer3r = {new double[]{6842.0d, 0.0d, 0.0d}, new double[]{0.0d, 6842.0d, 0.0d}, new double[]{0.0d, 0.0d, 6842.0d}};
    public static final double[][] DefaultSensitivityMatrixAltMag8GaShimmer3r = {new double[]{3421.0d, 0.0d, 0.0d}, new double[]{0.0d, 3421.0d, 0.0d}, new double[]{0.0d, 0.0d, 3421.0d}};
    public static final double[][] DefaultSensitivityMatrixAltMag12GaShimmer3r = {new double[]{2281.0d, 0.0d, 0.0d}, new double[]{0.0d, 2281.0d, 0.0d}, new double[]{0.0d, 0.0d, 2281.0d}};
    public static final double[][] DefaultSensitivityMatrixAltMag16GaShimmer3r = {new double[]{1711.0d, 0.0d, 0.0d}, new double[]{0.0d, 1711.0d, 0.0d}, new double[]{0.0d, 0.0d, 1711.0d}};
    private static final long serialVersionUID = 4028368641088628178L;

    static {
        double[][] dArr = {new double[]{1.0d, 0.0d, 0.0d}, new double[]{0.0d, -1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        DefaultAlignmentLIS3MDL = dArr;
        DefaultAlignmentMatrixAltMagShimmer3r = dArr;
        String[] strArr = {"1000Hz", "560Hz", "300Hz", "155Hz", "80Hz", "20Hz", "10Hz"};
        ListofLIS3MDLAltMagRate = strArr;
        Integer[] numArr = {1, 17, 33, 49, 62, 58, 8};
        ListofLIS3MDLAltMagRateConfigValues = numArr;
        String[] strArr2 = {"+/- 4Ga", "+/- 8Ga", "+/- 12Ga", "+/- 16Ga"};
        ListofLIS3MDLAltMagRange = strArr2;
        Integer[] numArr2 = {0, 1, 2, 3};
        ListofLIS3MDLAltMagRangeConfigValues = numArr2;
        configOptionAltMagRange = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS3MDL_ALT_MAG_RANGE, DatabaseConfigHandle.ALT_MAG_RANGE, strArr2, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS3MDL);
        configOptionAltMagRate = new ConfigOptionDetailsSensor(GuiLabelConfig.LIS3MDL_ALT_MAG_RATE, DatabaseConfigHandle.ALT_MAG_RATE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS3MDL);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(2097152L, 2097152L, "Alternate Magnetometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS3MDL, Arrays.asList(GuiLabelConfig.LIS3MDL_ALT_MAG_RANGE, GuiLabelConfig.LIS3MDL_ALT_MAG_RATE), Arrays.asList(ObjectClusterSensorName.MAG_ALT_X, ObjectClusterSensorName.MAG_ALT_Y, ObjectClusterSensorName.MAG_ALT_Z));
        sensorLIS3MDLAltMag = sensorDetailsRef;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(41, sensorDetailsRef);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.MAG_ALT_X, ObjectClusterSensorName.MAG_ALT_X, DatabaseChannelHandles.ALT_MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 23);
        channelLIS3MDLAltMagX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.MAG_ALT_Y, ObjectClusterSensorName.MAG_ALT_Y, DatabaseChannelHandles.ALT_MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 24);
        channelLIS3MDLAltMagY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.MAG_ALT_Z, ObjectClusterSensorName.MAG_ALT_Z, DatabaseChannelHandles.ALT_MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 25);
        channelLIS3MDLAltMagZ = channelDetails3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.MAG_ALT_X, channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.MAG_ALT_Z, channelDetails3);
        linkedHashMap2.put(ObjectClusterSensorName.MAG_ALT_Y, channelDetails2);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupLisAltMag = new SensorGroupingDetails("Alternate Magnetometer", Arrays.asList(41), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLIS3MDL);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(Byte.valueOf(GET_ALT_MAG_CALIBRATION_COMMAND), new BtCommandDetails(GET_ALT_MAG_CALIBRATION_COMMAND, "GET_ALT_MAG_CALIBRATION_COMMAND", ALT_MAG_CALIBRATION_RESPONSE));
        linkedHashMap3.put(Byte.valueOf(GET_ALT_MAG_SAMPLING_RATE_COMMAND), new BtCommandDetails(GET_ALT_MAG_SAMPLING_RATE_COMMAND, "GET_ALT_MAG_SAMPLING_RATE_COMMAND", ALT_MAG_SAMPLING_RATE_RESPONSE));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap3);
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put(Byte.valueOf(SET_ALT_MAG_CALIBRATION_COMMAND), new BtCommandDetails(SET_ALT_MAG_CALIBRATION_COMMAND, "SET_ALT_MAG_CALIBRATION_COMMAND"));
        linkedHashMap4.put(Byte.valueOf(SET_ALT_MAG_SAMPLING_RATE_COMMAND), new BtCommandDetails(SET_ALT_MAG_SAMPLING_RATE_COMMAND, "SET_ALT_MAG_SAMPLING_RATE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap4);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsMagAlt;
    public boolean mIsUsingDefaultAltMagParam;
    protected int mAltMagRange;
    protected boolean mHighPowerMag;
    protected int mLISAltMagRate;
    protected boolean mLowPowerMag;
    protected boolean mMedPowerMag;
    protected int mSensorIdAltMag;
    protected boolean mUltraHighPowerMag;
    private CalibDetailsKinematic calibDetailsMag12;
    private CalibDetailsKinematic calibDetailsMag16;
    private CalibDetailsKinematic calibDetailsMag4;
    private CalibDetailsKinematic calibDetailsMag8;

    public SensorLIS3MDL() {
        super(AbstractSensor.SENSORS.LIS3MDL);
        this.mAltMagRange = 0;
        this.mIsUsingDefaultAltMagParam = true;
        this.mLISAltMagRate = 4;
        this.mSensorIdAltMag = -1;
        this.mLowPowerMag = false;
        this.mMedPowerMag = false;
        this.mHighPowerMag = false;
        this.mUltraHighPowerMag = false;
        Integer[] numArr = ListofLIS3MDLAltMagRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS3MDLAltMagRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixAltMagShimmer3r;
        double[][] dArr2 = DefaultSensitivityMatrixAltMag4GaShimmer3r;
        double[][] dArr3 = DefaultOffsetVectorAltMagShimmer3r;
        this.calibDetailsMag4 = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsMag8 = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixAltMag8GaShimmer3r, dArr3);
        this.calibDetailsMag12 = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixAltMag12GaShimmer3r, dArr3);
        this.calibDetailsMag16 = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixAltMag16GaShimmer3r, dArr3);
        this.mCurrentCalibDetailsMagAlt = this.calibDetailsMag4;
        initialise();
    }

    public SensorLIS3MDL(ShimmerObject shimmerObject) {
        super(AbstractSensor.SENSORS.LIS3MDL, shimmerObject);
        this.mAltMagRange = 0;
        this.mIsUsingDefaultAltMagParam = true;
        this.mLISAltMagRate = 4;
        this.mSensorIdAltMag = -1;
        this.mLowPowerMag = false;
        this.mMedPowerMag = false;
        this.mHighPowerMag = false;
        this.mUltraHighPowerMag = false;
        Integer[] numArr = ListofLIS3MDLAltMagRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS3MDLAltMagRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixAltMagShimmer3r;
        double[][] dArr2 = DefaultSensitivityMatrixAltMag4GaShimmer3r;
        double[][] dArr3 = DefaultOffsetVectorAltMagShimmer3r;
        this.calibDetailsMag4 = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsMag8 = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixAltMag8GaShimmer3r, dArr3);
        this.calibDetailsMag12 = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixAltMag12GaShimmer3r, dArr3);
        this.calibDetailsMag16 = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixAltMag16GaShimmer3r, dArr3);
        this.mCurrentCalibDetailsMagAlt = this.calibDetailsMag4;
        initialise();
    }

    public SensorLIS3MDL(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.LIS3MDL, shimmerDevice);
        this.mAltMagRange = 0;
        this.mIsUsingDefaultAltMagParam = true;
        this.mLISAltMagRate = 4;
        this.mSensorIdAltMag = -1;
        this.mLowPowerMag = false;
        this.mMedPowerMag = false;
        this.mHighPowerMag = false;
        this.mUltraHighPowerMag = false;
        Integer[] numArr = ListofLIS3MDLAltMagRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLIS3MDLAltMagRange;
        String str = strArr[0];
        double[][] dArr = DefaultAlignmentMatrixAltMagShimmer3r;
        double[][] dArr2 = DefaultSensitivityMatrixAltMag4GaShimmer3r;
        double[][] dArr3 = DefaultOffsetVectorAltMagShimmer3r;
        this.calibDetailsMag4 = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsMag8 = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, DefaultSensitivityMatrixAltMag8GaShimmer3r, dArr3);
        this.calibDetailsMag12 = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, DefaultSensitivityMatrixAltMag12GaShimmer3r, dArr3);
        this.calibDetailsMag16 = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, DefaultSensitivityMatrixAltMag16GaShimmer3r, dArr3);
        this.mCurrentCalibDetailsMagAlt = this.calibDetailsMag4;
        initialise();
    }

    public static int getMagRateFromFreq(boolean z, double d, int i) {
        if (!z) {
            return 0;
        }
        if (i == 0) {
            return 8;
        }
        if (d > 560.0d) {
            return 1;
        }
        if (d > 300.0d) {
            return 17;
        }
        if (d > 155.0d) {
            return 33;
        }
        if (d > 100.0d || d > 50.0d) {
            return 49;
        }
        if (d > 20.0d) {
            return 62;
        }
        return d > 10.0d ? 58 : 8;
    }

    public static int highPowerMode(double d) {
        if (d <= 1.25d) {
            return 34;
        }
        if (d <= 2.5d) {
            return 36;
        }
        if (d <= 5.0d) {
            return 38;
        }
        if (d <= 10.0d) {
            return 40;
        }
        if (d <= 20.0d) {
            return 42;
        }
        if (d <= 40.0d) {
            return 44;
        }
        if (d <= 80.0d) {
            return 46;
        }
        return d <= 300.0d ? 33 : 34;
    }

    public static int lowPowerMode(double d) {
        if (d <= 0.625d) {
            return 0;
        }
        if (d <= 1.25d) {
            return 2;
        }
        if (d <= 2.5d) {
            return 4;
        }
        if (d <= 5.0d) {
            return 6;
        }
        if (d <= 10.0d) {
            return 8;
        }
        if (d <= 20.0d) {
            return 10;
        }
        if (d <= 40.0d) {
            return 12;
        }
        if (d <= 80.0d) {
            return 14;
        }
        return d <= 1000.0d ? 1 : 0;
    }

    public static int medPowerMode(double d) {
        if (d <= 1.25d) {
            return 18;
        }
        if (d <= 2.5d) {
            return 20;
        }
        if (d <= 5.0d) {
            return 22;
        }
        if (d <= 10.0d) {
            return 24;
        }
        if (d <= 20.0d) {
            return 26;
        }
        if (d <= 40.0d) {
            return 28;
        }
        if (d <= 80.0d) {
            return 30;
        }
        return d <= 560.0d ? 17 : 18;
    }

    public static int ultraHighPowerMode(double d) {
        if (d <= 1.25d) {
            return 50;
        }
        if (d <= 2.5d) {
            return 52;
        }
        if (d <= 5.0d) {
            return 54;
        }
        if (d <= 10.0d) {
            return 56;
        }
        if (d <= 20.0d) {
            return 58;
        }
        if (d <= 40.0d) {
            return 60;
        }
        if (d <= 80.0d) {
            return 62;
        }
        return d <= 155.0d ? 49 : 50;
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return false;
    }

    public int getAltMagRange() {
        return this.mAltMagRange;
    }

    public void setAltMagRange(int i) {
        setLIS3MDLAltMagRange(i);
    }

    public CalibDetailsKinematic getCurrentCalibDetailsMagAlt() {
        return this.mCurrentCalibDetailsMagAlt;
    }

    public int getLIS3MDLAltMagRate() {
        return this.mLISAltMagRate;
    }

    public void setLIS3MDLAltMagRate(int i) {
        this.mLISAltMagRate = i;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public boolean isHighPowerMagEnabled() {
        return this.mHighPowerMag;
    }

    public boolean isLowPowerMagEnabled() {
        return this.mLowPowerMag;
    }

    public boolean isMedPowerMagEnabled() {
        return this.mMedPowerMag;
    }

    public boolean isUltraHighPowerMagEnabled() {
        return this.mUltraHighPowerMag;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.clear();
        addConfigOption(configOptionAltMagRange);
        addConfigOption(configOptionAltMagRate);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.ALT_MAG_3R.ordinal()), sensorGroupLisAltMag);
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return new ActionSetting(communication_type);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_RANGE, Integer.valueOf(getAltMagRange()));
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_RATE, Integer.valueOf(getLIS3MDLAltMagRate()));
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_LPM, Integer.valueOf(getLowPowerMagEnabled()));
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_MPM, Integer.valueOf(getMedPowerMagEnabled()));
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_HPM, Integer.valueOf(getHighPowerMagEnabled()));
        linkedHashMap.put(DatabaseConfigHandle.ALT_MAG_UPM, Integer.valueOf(getUltraHighPowerMagEnabled()));
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsMagAlt(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_ALT_CALIB_TIME);
        return linkedHashMap;
    }

    public void updateCurrentMagAltCalibInUse() {
        this.mCurrentCalibDetailsMagAlt = getCurrentCalibDetailsIfKinematic(this.mSensorIdAltMag, getAltMagRange());
    }

    public double getLIS3MDLAltMagRateInHz() {
        return ArrayUtils.contains(ListofLIS3MDLAltMagRateConfigValues, Integer.valueOf(this.mLISAltMagRate)) ? r0[this.mLISAltMagRate].intValue() : this.mLISAltMagRate;
    }

    public void setLIS3MDLAltMagRange(int i) {
        if (ArrayUtils.contains(ListofLIS3MDLAltMagRangeConfigValues, Integer.valueOf(i))) {
            this.mAltMagRange = i;
            updateCurrentMagAltCalibInUse();
        }
    }

    public int getLowPowerMagEnabled() {
        return isLowPowerMagEnabled() ? 1 : 0;
    }

    public int getMedPowerMagEnabled() {
        return isMedPowerMagEnabled() ? 1 : 0;
    }

    public int getHighPowerMagEnabled() {
        return isHighPowerMagEnabled() ? 1 : 0;
    }

    public int getUltraHighPowerMagEnabled() {
        return isUltraHighPowerMagEnabled() ? 1 : 0;
    }

    public void setLowPowerMag(boolean z) {
        this.mLowPowerMag = z;
        if (this.mShimmerDevice != null) {
            setLIS3MDLAltMagRateFromFreq(getSamplingRateShimmer());
        }
    }

    public void setMedPowerMag(boolean z) {
        this.mMedPowerMag = z;
        if (this.mShimmerDevice != null) {
            setLIS3MDLAltMagRateFromFreq(getSamplingRateShimmer());
        }
    }

    public void setHighPowerMag(boolean z) {
        this.mHighPowerMag = z;
        if (this.mShimmerDevice != null) {
            setLIS3MDLAltMagRateFromFreq(getSamplingRateShimmer());
        }
    }

    public void setUltraHighPowerMag(boolean z) {
        this.mUltraHighPowerMag = z;
        if (this.mShimmerDevice != null) {
            setLIS3MDLAltMagRateFromFreq(getSamplingRateShimmer());
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_LPM)) {
            setLowPowerMag(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_LPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_MPM)) {
            setMedPowerMag(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_MPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_HPM)) {
            setHighPowerMag(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_HPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_UPM)) {
            setUltraHighPowerMag(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_UPM)).doubleValue() > 0.0d);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_RANGE)) {
            setAltMagRange(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ALT_MAG_RATE)) {
            setLIS3MDLAltMagRate(((Double) linkedHashMap.get(DatabaseConfigHandle.ALT_MAG_RATE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 41, getAltMagRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG, DatabaseConfigHandle.MAG_ALT_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdAltMag = 41;
        super.initialise();
        this.mAltMagRange = ListofLIS3MDLAltMagRangeConfigValues[0].intValue();
        updateCurrentMagAltCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsMag4.mRangeValue), this.calibDetailsMag4);
        treeMap.put(Integer.valueOf(this.calibDetailsMag8.mRangeValue), this.calibDetailsMag8);
        treeMap.put(Integer.valueOf(this.calibDetailsMag12.mRangeValue), this.calibDetailsMag12);
        treeMap.put(Integer.valueOf(this.calibDetailsMag16.mRangeValue), this.calibDetailsMag16);
        setCalibrationMapPerSensor(41, treeMap);
        updateCurrentMagAltCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean isSensorUsingDefaultCal(int i) {
        if (i == 41) {
            return isUsingDefaultMagAltParam();
        }
        return false;
    }

    public double getCalibTimeMagAlt() {
        return this.mCurrentCalibDetailsMagAlt.getCalibTimeMs();
    }

    public boolean isUsingValidMagAltParam() {
        return (UtilShimmer.isAllZeros(getAlignmentMatrixMagAlt()) || UtilShimmer.isAllZeros(getSensitivityMatrixMagAlt())) ? false : true;
    }

    public void updateIsUsingDefaultAltMagParam() {
        this.mIsUsingDefaultAltMagParam = getCurrentCalibDetailsMagAlt().isUsingDefaultParameters();
    }

    public int setLIS3MDLAltMagRateFromFreq(double d) {
        boolean zIsSensorEnabled = isSensorEnabled(this.mSensorIdAltMag);
        if (isLowPowerMagEnabled()) {
            this.mLISAltMagRate = getMagRateFromFreqForSensor(zIsSensorEnabled, d, 0);
        } else {
            this.mLISAltMagRate = getMagRateFromFreqForSensor(zIsSensorEnabled, d, -1);
        }
        return this.mLISAltMagRate;
    }

    public boolean checkLowPowerMag() {
        setLowPowerMag(getLIS3MDLAltMagRate() == 8);
        return isLowPowerMagEnabled();
    }

    public boolean checkMedPowerMag() {
        setMedPowerMag(getLIS3MDLAltMagRate() >= 17 && getLIS3MDLAltMagRate() <= 30);
        return isMedPowerMagEnabled();
    }

    public boolean checkHighPowerMag() {
        setHighPowerMag(getLIS3MDLAltMagRate() >= 33 && getLIS3MDLAltMagRate() <= 46);
        return isHighPowerMagEnabled();
    }

    public boolean checkUltraHighPowerMag() {
        setUltraHighPowerMag(getLIS3MDLAltMagRate() >= 49 && getLIS3MDLAltMagRate() <= 62);
        return isUltraHighPowerMagEnabled();
    }

    public int getMagRateFromFreqForSensor(boolean z, double d, int i) {
        return getMagRateFromFreq(z, d, i);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        if (mEnableCalibration && sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Alternate Magnetometer") && this.mCurrentCalibDetailsMagAlt != null) {
            double[] dArr = new double[3];
            for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_X)) {
                    dArr[0] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_Y)) {
                    dArr[1] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_Z)) {
                    dArr[2] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                }
            }
            double[] dArrCalibrateInertialSensorData = UtilCalibration.calibrateInertialSensorData(dArr, this.mCurrentCalibDetailsMagAlt);
            if (sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Alternate Magnetometer")) {
                for (ChannelDetails channelDetails2 : sensorDetails.mListOfChannels) {
                    if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_X)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, isUsingDefaultMagAltParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_Y)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultMagAltParam());
                    } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.MAG_ALT_Z)) {
                        objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1, isUsingDefaultMagAltParam());
                    }
                }
            }
            if (this.mIsDebugOutput) {
                super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.MAG_ALT_X, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_ALT_Y, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_ALT_Z, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.MAG_ALT_X, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.MAG_ALT_Y, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.MAG_ALT_Z, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
            }
        }
        return objectClusterProcessDataCommon;
    }

    public boolean isUsingDefaultMagAltParam() {
        return getCurrentCalibDetailsMagAlt().isUsingDefaultParameters();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(this.mSensorIdAltMag)) {
            return;
        }
        setDefaultLisMagAltSensorConfig(false);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            int i = configByteLayoutShimmer3.idxConfigSetupByte2;
            bArr[i] = (byte) (bArr[i] | ((byte) ((getAltMagRange() & configByteLayoutShimmer3.maskLSM303DLHCMagRange) << configByteLayoutShimmer3.bitShiftLSM303DLHCMagRange)));
            int i2 = configByteLayoutShimmer3.idxConfigSetupByte5;
            bArr[i2] = (byte) (bArr[i2] | ((byte) ((getLIS3MDLAltMagRate() & configByteLayoutShimmer3.maskLIS3MDLAltMagSamplingRate) << configByteLayoutShimmer3.bitShiftLIS3MDLAltMagSamplingRate)));
            System.arraycopy(generateCalParamLIS3MDLMag(), 0, bArr, configByteLayoutShimmer3.idxLIS3MDLAltMagCalibration, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
        }
    }

    public byte[] generateCalParamLIS3MDLMag() {
        return this.mCurrentCalibDetailsMagAlt.generateCalParamByteArray();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            setLIS3MDLAltMagRange((bArr[configByteLayoutShimmer3.idxConfigSetupByte2] >> configByteLayoutShimmer3.bitShiftLSM303DLHCMagRange) & configByteLayoutShimmer3.maskLSM303DLHCMagRange);
            setLIS3MDLAltMagRate((bArr[configByteLayoutShimmer3.idxConfigSetupByte5] >> configByteLayoutShimmer3.bitShiftLIS3MDLAltMagSamplingRate) & configByteLayoutShimmer3.maskLIS3MDLAltMagSamplingRate);
            checkLowPowerMag();
            if (shimmerDevice.isConnected()) {
                getCurrentCalibDetailsMagAlt().mCalibReadSource = CalibDetails.CALIB_READ_SOURCE.INFOMEM;
            }
            byte[] bArr2 = new byte[configByteLayoutShimmer3.lengthGeneralCalibrationBytes];
            System.arraycopy(bArr, configByteLayoutShimmer3.idxLIS3MDLAltMagCalibration, bArr2, 0, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            parseCalibParamFromPacketMagAlt(bArr2, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
        }
    }

    public void parseCalibParamFromPacketMagAlt(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCurrentCalibDetailsMagAlt.parseCalParamByteArray(bArr, calib_read_source);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0087  */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object setConfigValueUsingConfigLabel(java.lang.Integer r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            int r0 = r6.hashCode()
            java.lang.String r1 = "Alternate Mag Range"
            java.lang.String r2 = "Alternate Mag Rate"
            r3 = -1
            switch(r0) {
                case -1794570909: goto L59;
                case -1770470693: goto L4e;
                case -225832749: goto L45;
                case -117740795: goto L3a;
                case 2539776: goto L2f;
                case 78727453: goto L24;
                case 1364248945: goto L19;
                case 1589113770: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L63
        L10:
            boolean r0 = r6.equals(r1)
            if (r0 != 0) goto L17
            goto L63
        L17:
            r3 = 7
            goto L63
        L19:
            java.lang.String r0 = "Alt Mag Ultra High-Power Mode"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L22
            goto L63
        L22:
            r3 = 6
            goto L63
        L24:
            java.lang.String r0 = "Range"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L2d
            goto L63
        L2d:
            r3 = 5
            goto L63
        L2f:
            java.lang.String r0 = "Rate"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L38
            goto L63
        L38:
            r3 = 4
            goto L63
        L3a:
            java.lang.String r0 = "Alt Mag High-Power Mode"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L43
            goto L63
        L43:
            r3 = 3
            goto L63
        L45:
            boolean r0 = r6.equals(r2)
            if (r0 != 0) goto L4c
            goto L63
        L4c:
            r3 = 2
            goto L63
        L4e:
            java.lang.String r0 = "Alt Mag Low-Power Mode"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L57
            goto L63
        L57:
            r3 = 1
            goto L63
        L59:
            java.lang.String r0 = "Alt Mag Med-Power Mode"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L62
            goto L63
        L62:
            r3 = 0
        L63:
            switch(r3) {
                case 0: goto Lb6;
                case 1: goto Lac;
                case 2: goto La2;
                case 3: goto L98;
                case 4: goto L87;
                case 5: goto L7b;
                case 6: goto L71;
                case 7: goto L67;
                default: goto L66;
            }
        L66:
            goto L93
        L67:
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r5 = r7.intValue()
            r4.setLIS3MDLAltMagRange(r5)
            goto Lbf
        L71:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            r4.setUltraHighPowerMag(r5)
            goto Lbf
        L7b:
            int r0 = r5.intValue()
            int r3 = r4.mSensorIdAltMag
            if (r0 != r3) goto L87
            r4.setConfigValueUsingConfigLabel(r1, r7)
            goto Lbf
        L87:
            int r0 = r5.intValue()
            int r1 = r4.mSensorIdAltMag
            if (r0 != r1) goto L93
            r4.setConfigValueUsingConfigLabel(r2, r7)
            goto Lbf
        L93:
            java.lang.Object r5 = super.setConfigValueUsingConfigLabelCommon(r5, r6, r7)
            goto Lc0
        L98:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            r4.setHighPowerMag(r5)
            goto Lbf
        La2:
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r5 = r7.intValue()
            r4.setLIS3MDLAltMagRate(r5)
            goto Lbf
        Lac:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            r4.setLowPowerMag(r5)
            goto Lbf
        Lb6:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            r4.setMedPowerMag(r5)
        Lbf:
            r5 = 0
        Lc0:
            boolean r7 = r6.equals(r2)
            if (r7 == 0) goto Lc9
            r4.checkConfigOptionValues(r6)
        Lc9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL.setConfigValueUsingConfigLabel(java.lang.Integer, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097  */
    @Override // com.shimmerresearch.sensors.AbstractSensor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getConfigValueUsingConfigLabel(java.lang.Integer r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Alternate Mag Rate"
            boolean r1 = r6.equals(r0)
            if (r1 == 0) goto Lb
            r4.checkConfigOptionValues(r6)
        Lb:
            r6.hashCode()
            int r1 = r6.hashCode()
            java.lang.String r2 = "Alternate Mag Range"
            r3 = -1
            switch(r1) {
                case -1794570909: goto L62;
                case -1770470693: goto L57;
                case -225832749: goto L4e;
                case -117740795: goto L43;
                case 2539776: goto L38;
                case 78727453: goto L2d;
                case 1364248945: goto L22;
                case 1589113770: goto L19;
                default: goto L18;
            }
        L18:
            goto L6c
        L19:
            boolean r1 = r6.equals(r2)
            if (r1 != 0) goto L20
            goto L6c
        L20:
            r3 = 7
            goto L6c
        L22:
            java.lang.String r1 = "Alt Mag Ultra High-Power Mode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L2b
            goto L6c
        L2b:
            r3 = 6
            goto L6c
        L2d:
            java.lang.String r1 = "Range"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L36
            goto L6c
        L36:
            r3 = 5
            goto L6c
        L38:
            java.lang.String r1 = "Rate"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L41
            goto L6c
        L41:
            r3 = 4
            goto L6c
        L43:
            java.lang.String r1 = "Alt Mag High-Power Mode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L4c
            goto L6c
        L4c:
            r3 = 3
            goto L6c
        L4e:
            boolean r1 = r6.equals(r0)
            if (r1 != 0) goto L55
            goto L6c
        L55:
            r3 = 2
            goto L6c
        L57:
            java.lang.String r1 = "Alt Mag Low-Power Mode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L60
            goto L6c
        L60:
            r3 = 1
            goto L6c
        L62:
            java.lang.String r1 = "Alt Mag Med-Power Mode"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L6b
            goto L6c
        L6b:
            r3 = 0
        L6c:
            switch(r3) {
                case 0: goto Lbc;
                case 1: goto Lb3;
                case 2: goto Laa;
                case 3: goto La1;
                case 4: goto L8f;
                case 5: goto L82;
                case 6: goto L79;
                case 7: goto L70;
                default: goto L6f;
            }
        L6f:
            goto L9c
        L70:
            int r5 = r4.getAltMagRange()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto Lc4
        L79:
            boolean r5 = r4.isUltraHighPowerMagEnabled()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto Lc4
        L82:
            int r1 = r5.intValue()
            int r3 = r4.mSensorIdAltMag
            if (r1 != r3) goto L8f
            java.lang.Object r5 = r4.getConfigValueUsingConfigLabel(r2)
            goto Lc4
        L8f:
            int r1 = r5.intValue()
            int r2 = r4.mSensorIdAltMag
            if (r1 != r2) goto L9c
            java.lang.Object r5 = r4.getConfigValueUsingConfigLabel(r0)
            goto Lc4
        L9c:
            java.lang.Object r5 = super.getConfigValueUsingConfigLabelCommon(r5, r6)
            goto Lc4
        La1:
            boolean r5 = r4.isHighPowerMagEnabled()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto Lc4
        Laa:
            int r5 = r4.getLIS3MDLAltMagRate()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto Lc4
        Lb3:
            boolean r5 = r4.isLowPowerMagEnabled()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            goto Lc4
        Lbc:
            boolean r5 = r4.isMedPowerMagEnabled()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
        Lc4:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL.getConfigValueUsingConfigLabel(java.lang.Integer, java.lang.String):java.lang.Object");
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        setLIS3MDLAltMagRateFromFreq(d);
        checkLowPowerMag();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        if (i != this.mSensorIdAltMag) {
            return true;
        }
        setDefaultLisMagAltSensorConfig(z);
        return true;
    }

    public void setDefaultLisMagAltSensorConfig(boolean z) {
        if (z) {
            setLIS3MDLAltMagRange(0);
        }
    }

    public double[][] getAlignmentMatrixMagAlt() {
        return this.mCurrentCalibDetailsMagAlt.getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixMagAlt() {
        return this.mCurrentCalibDetailsMagAlt.getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixMagAlt() {
        return this.mCurrentCalibDetailsMagAlt.getValidOffsetVector();
    }

    public static class DatabaseChannelHandles {
        public static final String ALT_MAG_X = "LIS3MDL_MAG_X";
        public static final String ALT_MAG_Y = "LIS3MDL_MAG_Y";
        public static final String ALT_MAG_Z = "LIS3MDL_MAG_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String ALT_MAG_HPM = "LIS3MDL_Mag_HPM";
        public static final String ALT_MAG_LPM = "LIS3MDL_Mag_LPM";
        public static final String ALT_MAG_MPM = "LIS3MDL_Mag_MPM";
        public static final String ALT_MAG_RANGE = "LIS3MDL_ALT_Mag_Range";
        public static final String ALT_MAG_RATE = "LIS3MDL_ALT_Mag_Rate";
        public static final String ALT_MAG_UPM = "LIS3MDL_Mag_UPM";
        public static final String MAG_ALT_CALIB_TIME = "LIS3MDL_Mag_ALT_Calib_Time";
        public static final String MAG_ALT_OFFSET_X = "LIS3MDL_Mag_ALT_Offset_X";
        public static final String MAG_ALT_OFFSET_Y = "LIS3MDL_Mag_ALT_Offset_Y";
        public static final String MAG_ALT_OFFSET_Z = "LIS3MDL_Mag_ALT_Offset_Z";
        public static final String MAG_ALT_GAIN_X = "LIS3MDL_Mag_ALT_Gain_X";
        public static final String MAG_ALT_GAIN_Y = "LIS3MDL_Mag_ALT_Gain_Y";
        public static final String MAG_ALT_GAIN_Z = "LIS3MDL_Mag_ALT_Gain_Z";
        public static final String MAG_ALT_ALIGN_XX = "LIS3MDL_Mag_ALT_Align_XX";
        public static final String MAG_ALT_ALIGN_XY = "LIS3MDL_Mag_ALT_Align_XY";
        public static final String MAG_ALT_ALIGN_XZ = "LIS3MDL_Mag_ALT_Align_XZ";
        public static final String MAG_ALT_ALIGN_YX = "LIS3MDL_Mag_ALT_Align_YX";
        public static final String MAG_ALT_ALIGN_YY = "LIS3MDL_Mag_ALT_Align_YY";
        public static final String MAG_ALT_ALIGN_YZ = "LIS3MDL_Mag_ALT_Align_YZ";
        public static final String MAG_ALT_ALIGN_ZX = "LIS3MDL_Mag_ALT_Align_ZX";
        public static final String MAG_ALT_ALIGN_ZY = "LIS3MDL_Mag_ALT_Align_ZY";
        public static final String MAG_ALT_ALIGN_ZZ = "LIS3MDL_Mag_ALT_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MAG = Arrays.asList(MAG_ALT_OFFSET_X, MAG_ALT_OFFSET_Y, MAG_ALT_OFFSET_Z, MAG_ALT_GAIN_X, MAG_ALT_GAIN_Y, MAG_ALT_GAIN_Z, MAG_ALT_ALIGN_XX, MAG_ALT_ALIGN_XY, MAG_ALT_ALIGN_XZ, MAG_ALT_ALIGN_YX, MAG_ALT_ALIGN_YY, MAG_ALT_ALIGN_YZ, MAG_ALT_ALIGN_ZX, MAG_ALT_ALIGN_ZY, MAG_ALT_ALIGN_ZZ);
    }

    public static class ObjectClusterSensorName {
        public static String MAG_ALT_X = "Mag_Alt_X";
        public static String MAG_ALT_Y = "Mag_Alt_Y";
        public static String MAG_ALT_Z = "Mag_Alt_Z";
    }

    public class GuiLabelConfig {
        public static final String LIS3MDL_ALT_MAG_CALIB_PARAM = "Alternate Mag Calibration Details";
        public static final String LIS3MDL_ALT_MAG_DEFAULT_CALIB = "Alternate Mag Default Calibration";
        public static final String LIS3MDL_ALT_MAG_HP = "Alt Mag High-Power Mode";
        public static final String LIS3MDL_ALT_MAG_LP = "Alt Mag Low-Power Mode";
        public static final String LIS3MDL_ALT_MAG_MP = "Alt Mag Med-Power Mode";
        public static final String LIS3MDL_ALT_MAG_RANGE = "Alternate Mag Range";
        public static final String LIS3MDL_ALT_MAG_RATE = "Alternate Mag Rate";
        public static final String LIS3MDL_ALT_MAG_UP = "Alt Mag Ultra High-Power Mode";
        public static final String LIS3MDL_ALT_MAG_VALID_CALIB = "Alternate Mag Valid Calibration";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String MAG_ALT = "Alternate Magnetometer";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String ALT_MAG = "Alternate Magnetometer";

        public LABEL_SENSOR_TILE() {
        }
    }
}
