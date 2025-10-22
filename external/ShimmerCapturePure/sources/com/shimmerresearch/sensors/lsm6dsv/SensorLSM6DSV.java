package com.shimmerresearch.sensors.lsm6dsv;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.ConfigByteLayout;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
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
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.ActionSetting;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes2.dex */
public class SensorLSM6DSV extends AbstractSensor {
    public static final byte ALT_ACCEL_RANGE_RESPONSE = 80;
    public static final double[][] AlignmentMatrixGyroShimmer3r;
    public static final byte GET_ALT_ACCEL_RANGE_COMMAND = 81;
    public static final byte GET_GYRO_CALIBRATION_COMMAND = 22;
    public static final byte GET_LN_ACCEL_CALIBRATION_COMMAND = 19;
    public static final byte GET_LSM6DSV_GYRO_RANGE_COMMAND = 75;
    public static final byte GET_LSM6DSV_SAMPLING_RATE_COMMAND = 78;
    public static final byte GYRO_CALIBRATION_RESPONSE = 21;
    public static final byte LN_ACCEL_CALIBRATION_RESPONSE = 18;
    public static final byte LSM6DSV_GYRO_RANGE_RESPONSE = 74;
    public static final byte LSM6DSV_SAMPLING_RATE_RESPONSE = 77;
    public static final String[] ListofGyroRange;
    public static final String[] ListofLSM6DSVAccelRange;
    public static final Integer[] ListofLSM6DSVAccelRangeConfigValues;
    public static final Integer[] ListofLSM6DSVGyroRangeConfigValues;
    public static final String[] ListofLSM6DSVGyroRate;
    public static final Integer[] ListofLSM6DSVGyroRateConfigValues;
    public static final Double[] ListofLSM6DSVGyroRateDouble;
    public static final double[][] OffsetVectorGyroShimmer3r;
    public static final byte SET_ALT_ACCEL_RANGE_COMMAND = 79;
    public static final byte SET_GYRO_CALIBRATION_COMMAND = 20;
    public static final byte SET_LN_ACCEL_CALIBRATION_COMMAND = 17;
    public static final byte SET_LSM6DSV_GYRO_RANGE_COMMAND = 73;
    public static final byte SET_LSM6DSV_SAMPLING_RATE_COMMAND = 76;
    public static final double[][] SensitivityMatrixGyro1000dpsShimmer3r;
    public static final double[][] SensitivityMatrixGyro125dpsShimmer3r;
    public static final double[][] SensitivityMatrixGyro2000dpsShimmer3r;
    public static final double[][] SensitivityMatrixGyro250dpsShimmer3r;
    public static final double[][] SensitivityMatrixGyro4000dpsShimmer3r;
    public static final double[][] SensitivityMatrixGyro500dpsShimmer3r;
    public static final ChannelDetails channelAccelX;
    public static final ChannelDetails channelAccelY;
    public static final ChannelDetails channelAccelZ;
    public static final ChannelDetails channelGyroX;
    public static final ChannelDetails channelGyroY;
    public static final ChannelDetails channelGyroZ;
    public static final ConfigOptionDetailsSensor configOptionLSM6DSVAccelRange;
    public static final ConfigOptionDetailsSensor configOptionLSM6DSVGyroLpm;
    public static final ConfigOptionDetailsSensor configOptionLSM6DSVGyroRange;
    public static final ConfigOptionDetailsSensor configOptionLSM6DSVGyroRate;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final List<Integer> mListOfMplChannels;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLnAccelLSM6DSV;
    public static final SensorDetailsRef sensorLSM6DSVAccelRef;
    public static final SensorDetailsRef sensorLSM6DSVGyroRef;
    public static final double[][] AlignmentMatrixLowNoiseAccelShimmer3r = {new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
    public static final double[][] OffsetVectorLowNoiseAccelShimmer3r = {new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel2gShimmer3r = {new double[]{1672.0d, 0.0d, 0.0d}, new double[]{0.0d, 1672.0d, 0.0d}, new double[]{0.0d, 0.0d, 1672.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel4gShimmer3r = {new double[]{836.0d, 0.0d, 0.0d}, new double[]{0.0d, 836.0d, 0.0d}, new double[]{0.0d, 0.0d, 836.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel8gShimmer3r = {new double[]{418.0d, 0.0d, 0.0d}, new double[]{0.0d, 418.0d, 0.0d}, new double[]{0.0d, 0.0d, 418.0d}};
    public static final double[][] SensitivityMatrixLowNoiseAccel16gShimmer3r = {new double[]{209.0d, 0.0d, 0.0d}, new double[]{0.0d, 209.0d, 0.0d}, new double[]{0.0d, 0.0d, 209.0d}};
    private static final long serialVersionUID = -1336807717590498430L;

    static {
        Integer[] numArr = {0, 1, 2, 3};
        ListofLSM6DSVAccelRangeConfigValues = numArr;
        String[] strArr = {SensorKionixAccel.LN_ACCEL_RANGE_STRING, "± 4g", "± 8g", "± 16g"};
        ListofLSM6DSVAccelRange = strArr;
        AlignmentMatrixGyroShimmer3r = new double[][]{new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        OffsetVectorGyroShimmer3r = new double[][]{new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
        SensitivityMatrixGyro125dpsShimmer3r = new double[][]{new double[]{229.0d, 0.0d, 0.0d}, new double[]{0.0d, 229.0d, 0.0d}, new double[]{0.0d, 0.0d, 229.0d}};
        SensitivityMatrixGyro250dpsShimmer3r = new double[][]{new double[]{114.0d, 0.0d, 0.0d}, new double[]{0.0d, 114.0d, 0.0d}, new double[]{0.0d, 0.0d, 114.0d}};
        SensitivityMatrixGyro500dpsShimmer3r = new double[][]{new double[]{57.0d, 0.0d, 0.0d}, new double[]{0.0d, 57.0d, 0.0d}, new double[]{0.0d, 0.0d, 57.0d}};
        SensitivityMatrixGyro1000dpsShimmer3r = new double[][]{new double[]{29.0d, 0.0d, 0.0d}, new double[]{0.0d, 29.0d, 0.0d}, new double[]{0.0d, 0.0d, 29.0d}};
        SensitivityMatrixGyro2000dpsShimmer3r = new double[][]{new double[]{14.0d, 0.0d, 0.0d}, new double[]{0.0d, 14.0d, 0.0d}, new double[]{0.0d, 0.0d, 14.0d}};
        SensitivityMatrixGyro4000dpsShimmer3r = new double[][]{new double[]{7.0d, 0.0d, 0.0d}, new double[]{0.0d, 7.0d, 0.0d}, new double[]{0.0d, 0.0d, 7.0d}};
        String[] strArr2 = {"+/- 125dps", "+/- 250dps", "+/- 500dps", "+/- 1000dps", "+/- 2000dps", "+/- 4000dps"};
        ListofGyroRange = strArr2;
        Integer[] numArr2 = {0, 1, 2, 3, 4, 5};
        ListofLSM6DSVGyroRangeConfigValues = numArr2;
        String[] strArr3 = {"Power-down", "1.875Hz", "7.5Hz", "12.0Hz", "30.0Hz", "60.0Hz", "120.0Hz", "240.0Hz", "480.0Hz", "960.0Hz", "1920.0Hz", "3840.0Hz", "7680.0Hz"};
        ListofLSM6DSVGyroRate = strArr3;
        ListofLSM6DSVGyroRateDouble = new Double[]{Double.valueOf(0.0d), Double.valueOf(1.875d), Double.valueOf(7.5d), Double.valueOf(12.0d), Double.valueOf(30.0d), Double.valueOf(60.0d), Double.valueOf(120.0d), Double.valueOf(240.0d), Double.valueOf(480.0d), Double.valueOf(960.0d), Double.valueOf(1920.0d), Double.valueOf(3840.0d), Double.valueOf(7680.0d)};
        Integer[] numArr3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ListofLSM6DSVGyroRateConfigValues = numArr3;
        mListOfMplChannels = Arrays.asList(35, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61);
        configOptionLSM6DSVGyroRange = new ConfigOptionDetailsSensor("Gyro Range", DatabaseConfigHandle.GYRO_RANGE, strArr2, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV);
        configOptionLSM6DSVAccelRange = new ConfigOptionDetailsSensor(GuiLabelConfig.LSM6DSV_ACCEL_RANGE, DatabaseConfigHandle.ACCEL_RANGE, strArr, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV);
        configOptionLSM6DSVGyroRate = new ConfigOptionDetailsSensor("Gyro Sampling Rate", DatabaseConfigHandle.GYRO_RATE, strArr3, numArr3, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV);
        configOptionLSM6DSVGyroLpm = new ConfigOptionDetailsSensor("Gyro Low-Power Mode", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(128L, 128L, "Low-Noise Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV, Arrays.asList(GuiLabelConfig.LSM6DSV_ACCEL_RANGE), Arrays.asList(ObjectClusterSensorName.ACCEL_LN_X, ObjectClusterSensorName.ACCEL_LN_Y, ObjectClusterSensorName.ACCEL_LN_Z));
        sensorLSM6DSVAccelRef = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef(64L, 64L, "Gyroscope", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV, Arrays.asList("Gyro Range", "Gyro Sampling Rate"), Arrays.asList(ObjectClusterSensorName.GYRO_X, ObjectClusterSensorName.GYRO_Y, ObjectClusterSensorName.GYRO_Z));
        sensorLSM6DSVGyroRef = sensorDetailsRef2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(37, sensorDetailsRef);
        linkedHashMap.put(38, sensorDetailsRef2);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap);
        ChannelDetails channelDetails = new ChannelDetails(ObjectClusterSensorName.ACCEL_LN_X, ObjectClusterSensorName.ACCEL_LN_X, DatabaseChannelHandles.LN_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 0);
        channelAccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(ObjectClusterSensorName.ACCEL_LN_Y, ObjectClusterSensorName.ACCEL_LN_Y, DatabaseChannelHandles.LN_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 1);
        channelAccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(ObjectClusterSensorName.ACCEL_LN_Z, ObjectClusterSensorName.ACCEL_LN_Z, DatabaseChannelHandles.LN_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.UINT12, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 2);
        channelAccelZ = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(ObjectClusterSensorName.GYRO_X, ObjectClusterSensorName.GYRO_X, DatabaseChannelHandles.GYRO_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 10);
        channelGyroX = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(ObjectClusterSensorName.GYRO_Y, ObjectClusterSensorName.GYRO_Y, DatabaseChannelHandles.GYRO_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 11);
        channelGyroY = channelDetails5;
        ChannelDetails channelDetails6 = new ChannelDetails(ObjectClusterSensorName.GYRO_Z, ObjectClusterSensorName.GYRO_Z, DatabaseChannelHandles.GYRO_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "deg/s", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 12);
        channelGyroZ = channelDetails6;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(ObjectClusterSensorName.ACCEL_LN_X, channelDetails);
        linkedHashMap2.put(ObjectClusterSensorName.ACCEL_LN_Y, channelDetails2);
        linkedHashMap2.put(ObjectClusterSensorName.ACCEL_LN_Z, channelDetails3);
        linkedHashMap2.put(ObjectClusterSensorName.GYRO_X, channelDetails4);
        linkedHashMap2.put(ObjectClusterSensorName.GYRO_Y, channelDetails5);
        linkedHashMap2.put(ObjectClusterSensorName.GYRO_Z, channelDetails6);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap2);
        sensorGroupLnAccelLSM6DSV = new SensorGroupingDetails("Low-Noise Accelerometer", Arrays.asList(37), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put((byte) 19, new BtCommandDetails((byte) 19, "GET_LN_ACCEL_CALIBRATION_COMMAND", (byte) 18));
        linkedHashMap3.put((byte) 22, new BtCommandDetails((byte) 22, "GET_GYRO CALIBRATION_COMMAND", (byte) 21));
        linkedHashMap3.put((byte) 75, new BtCommandDetails((byte) 75, "GET_LSM6DSV GYRO RANGE_COMMAND", (byte) 74));
        linkedHashMap3.put((byte) 78, new BtCommandDetails((byte) 78, "GET_LSM6DSV_SAMPLING_RATE_COMMAND", (byte) 77));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap3);
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put((byte) 17, new BtCommandDetails((byte) 17, "SET_LN_ACCEL_CALIBRATION_COMMAND"));
        linkedHashMap4.put((byte) 20, new BtCommandDetails((byte) 20, "SET_GYRO_CALIBRATION_COMMAND"));
        linkedHashMap4.put((byte) 73, new BtCommandDetails((byte) 73, "SET_LSM6DSV_GYRO_RANGE_COMMAND"));
        linkedHashMap4.put((byte) 76, new BtCommandDetails((byte) 76, "SET_LSM6DSV_SAMPLING_RATE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap4);
    }

    public CalibDetailsKinematic mCurrentCalibDetailsAccelLn;
    public CalibDetailsKinematic mCurrentCalibDetailsGyro;
    public boolean mIsUsingDefaultGyroParam;
    public boolean mIsUsingDefaultLNAccelParam;
    protected int mLSM6DSVGyroAccelRate;
    protected int mLSM6DSVLPF;
    protected boolean mLowPowerGyro;
    protected int mSensorIdAccelLN;
    protected int mSensorIdGyro;
    private CalibDetailsKinematic calibDetailsAccelLn16g;
    private CalibDetailsKinematic calibDetailsAccelLn2g;
    private CalibDetailsKinematic calibDetailsAccelLn4g;
    private CalibDetailsKinematic calibDetailsAccelLn8g;
    private CalibDetailsKinematic calibDetailsGyro1000;
    private CalibDetailsKinematic calibDetailsGyro125;
    private CalibDetailsKinematic calibDetailsGyro2000;
    private CalibDetailsKinematic calibDetailsGyro250;
    private CalibDetailsKinematic calibDetailsGyro4000;
    private CalibDetailsKinematic calibDetailsGyro500;
    private boolean debugGyroRate;
    private int mAccelRange;
    private int mGyroRange;

    public SensorLSM6DSV() {
        super(AbstractSensor.SENSORS.LSM6DSV);
        this.mSensorIdAccelLN = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultLNAccelParam = true;
        Integer[] numArr = ListofLSM6DSVAccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLSM6DSVAccelRange;
        String str = strArr[0];
        double[][] dArr = AlignmentMatrixLowNoiseAccelShimmer3r;
        double[][] dArr2 = SensitivityMatrixLowNoiseAccel2gShimmer3r;
        double[][] dArr3 = OffsetVectorLowNoiseAccelShimmer3r;
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelLn4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, SensitivityMatrixLowNoiseAccel4gShimmer3r, dArr3);
        this.calibDetailsAccelLn8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, SensitivityMatrixLowNoiseAccel8gShimmer3r, dArr3);
        this.calibDetailsAccelLn16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, SensitivityMatrixLowNoiseAccel16gShimmer3r, dArr3);
        this.mCurrentCalibDetailsAccelLn = this.calibDetailsAccelLn2g;
        this.mIsUsingDefaultGyroParam = true;
        this.mGyroRange = 0;
        this.mLSM6DSVGyroAccelRate = 0;
        this.mSensorIdGyro = -1;
        this.mLowPowerGyro = false;
        this.debugGyroRate = false;
        this.mLSM6DSVLPF = 0;
        Integer[] numArr2 = ListofLSM6DSVGyroRangeConfigValues;
        int iIntValue2 = numArr2[0].intValue();
        String[] strArr2 = ListofGyroRange;
        String str2 = strArr2[0];
        double[][] dArr4 = AlignmentMatrixGyroShimmer3r;
        double[][] dArr5 = SensitivityMatrixGyro125dpsShimmer3r;
        double[][] dArr6 = OffsetVectorGyroShimmer3r;
        this.calibDetailsGyro125 = new CalibDetailsKinematic(iIntValue2, str2, dArr4, dArr5, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro250 = new CalibDetailsKinematic(numArr2[1].intValue(), strArr2[1], dArr4, SensitivityMatrixGyro250dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro500 = new CalibDetailsKinematic(numArr2[2].intValue(), strArr2[2], dArr4, SensitivityMatrixGyro500dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro1000 = new CalibDetailsKinematic(numArr2[3].intValue(), strArr2[3], dArr4, SensitivityMatrixGyro1000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro2000 = new CalibDetailsKinematic(numArr2[4].intValue(), strArr2[4], dArr4, SensitivityMatrixGyro2000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro4000 = new CalibDetailsKinematic(numArr2[5].intValue(), strArr2[5], dArr4, SensitivityMatrixGyro4000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.mCurrentCalibDetailsGyro = this.calibDetailsGyro250;
        initialise();
    }

    public SensorLSM6DSV(ShimmerVerObject shimmerVerObject) {
        super(AbstractSensor.SENSORS.LSM6DSV, shimmerVerObject);
        this.mSensorIdAccelLN = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultLNAccelParam = true;
        Integer[] numArr = ListofLSM6DSVAccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLSM6DSVAccelRange;
        String str = strArr[0];
        double[][] dArr = AlignmentMatrixLowNoiseAccelShimmer3r;
        double[][] dArr2 = SensitivityMatrixLowNoiseAccel2gShimmer3r;
        double[][] dArr3 = OffsetVectorLowNoiseAccelShimmer3r;
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelLn4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, SensitivityMatrixLowNoiseAccel4gShimmer3r, dArr3);
        this.calibDetailsAccelLn8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, SensitivityMatrixLowNoiseAccel8gShimmer3r, dArr3);
        this.calibDetailsAccelLn16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, SensitivityMatrixLowNoiseAccel16gShimmer3r, dArr3);
        this.mCurrentCalibDetailsAccelLn = this.calibDetailsAccelLn2g;
        this.mIsUsingDefaultGyroParam = true;
        this.mGyroRange = 0;
        this.mLSM6DSVGyroAccelRate = 0;
        this.mSensorIdGyro = -1;
        this.mLowPowerGyro = false;
        this.debugGyroRate = false;
        this.mLSM6DSVLPF = 0;
        Integer[] numArr2 = ListofLSM6DSVGyroRangeConfigValues;
        int iIntValue2 = numArr2[0].intValue();
        String[] strArr2 = ListofGyroRange;
        String str2 = strArr2[0];
        double[][] dArr4 = AlignmentMatrixGyroShimmer3r;
        double[][] dArr5 = SensitivityMatrixGyro125dpsShimmer3r;
        double[][] dArr6 = OffsetVectorGyroShimmer3r;
        this.calibDetailsGyro125 = new CalibDetailsKinematic(iIntValue2, str2, dArr4, dArr5, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro250 = new CalibDetailsKinematic(numArr2[1].intValue(), strArr2[1], dArr4, SensitivityMatrixGyro250dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro500 = new CalibDetailsKinematic(numArr2[2].intValue(), strArr2[2], dArr4, SensitivityMatrixGyro500dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro1000 = new CalibDetailsKinematic(numArr2[3].intValue(), strArr2[3], dArr4, SensitivityMatrixGyro1000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro2000 = new CalibDetailsKinematic(numArr2[4].intValue(), strArr2[4], dArr4, SensitivityMatrixGyro2000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro4000 = new CalibDetailsKinematic(numArr2[5].intValue(), strArr2[5], dArr4, SensitivityMatrixGyro4000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.mCurrentCalibDetailsGyro = this.calibDetailsGyro250;
        initialise();
    }

    public SensorLSM6DSV(ShimmerDevice shimmerDevice) {
        super(AbstractSensor.SENSORS.LSM6DSV, shimmerDevice);
        this.mSensorIdAccelLN = -1;
        this.mAccelRange = 0;
        this.mIsUsingDefaultLNAccelParam = true;
        Integer[] numArr = ListofLSM6DSVAccelRangeConfigValues;
        int iIntValue = numArr[0].intValue();
        String[] strArr = ListofLSM6DSVAccelRange;
        String str = strArr[0];
        double[][] dArr = AlignmentMatrixLowNoiseAccelShimmer3r;
        double[][] dArr2 = SensitivityMatrixLowNoiseAccel2gShimmer3r;
        double[][] dArr3 = OffsetVectorLowNoiseAccelShimmer3r;
        this.calibDetailsAccelLn2g = new CalibDetailsKinematic(iIntValue, str, dArr, dArr2, dArr3);
        this.calibDetailsAccelLn4g = new CalibDetailsKinematic(numArr[1].intValue(), strArr[1], dArr, SensitivityMatrixLowNoiseAccel4gShimmer3r, dArr3);
        this.calibDetailsAccelLn8g = new CalibDetailsKinematic(numArr[2].intValue(), strArr[2], dArr, SensitivityMatrixLowNoiseAccel8gShimmer3r, dArr3);
        this.calibDetailsAccelLn16g = new CalibDetailsKinematic(numArr[3].intValue(), strArr[3], dArr, SensitivityMatrixLowNoiseAccel16gShimmer3r, dArr3);
        this.mCurrentCalibDetailsAccelLn = this.calibDetailsAccelLn2g;
        this.mIsUsingDefaultGyroParam = true;
        this.mGyroRange = 0;
        this.mLSM6DSVGyroAccelRate = 0;
        this.mSensorIdGyro = -1;
        this.mLowPowerGyro = false;
        this.debugGyroRate = false;
        this.mLSM6DSVLPF = 0;
        Integer[] numArr2 = ListofLSM6DSVGyroRangeConfigValues;
        int iIntValue2 = numArr2[0].intValue();
        String[] strArr2 = ListofGyroRange;
        String str2 = strArr2[0];
        double[][] dArr4 = AlignmentMatrixGyroShimmer3r;
        double[][] dArr5 = SensitivityMatrixGyro125dpsShimmer3r;
        double[][] dArr6 = OffsetVectorGyroShimmer3r;
        this.calibDetailsGyro125 = new CalibDetailsKinematic(iIntValue2, str2, dArr4, dArr5, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro250 = new CalibDetailsKinematic(numArr2[1].intValue(), strArr2[1], dArr4, SensitivityMatrixGyro250dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro500 = new CalibDetailsKinematic(numArr2[2].intValue(), strArr2[2], dArr4, SensitivityMatrixGyro500dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro1000 = new CalibDetailsKinematic(numArr2[3].intValue(), strArr2[3], dArr4, SensitivityMatrixGyro1000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro2000 = new CalibDetailsKinematic(numArr2[4].intValue(), strArr2[4], dArr4, SensitivityMatrixGyro2000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.calibDetailsGyro4000 = new CalibDetailsKinematic(numArr2[5].intValue(), strArr2[5], dArr4, SensitivityMatrixGyro4000dpsShimmer3r, dArr6, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        this.mCurrentCalibDetailsGyro = this.calibDetailsGyro250;
        initialise();
    }

    public static int getGyroRateFromFreq(boolean z, double d, boolean z2) {
        if (z) {
            if (z2) {
                return 1;
            }
            if (d <= 7.5d) {
                return 2;
            }
            if (d <= 30.0d) {
                return 4;
            }
            if (d <= 60.0d) {
                return 5;
            }
            if (d <= 120.0d) {
                return 6;
            }
            if (d <= 240.0d) {
                return 7;
            }
            if (d <= 480.0d) {
                return 8;
            }
            if (d <= 960.0d) {
                return 9;
            }
            if (d <= 1920.0d) {
                return 10;
            }
            if (d <= 3840.0d) {
                return 11;
            }
            if (d <= 7680.0d) {
                return 12;
            }
        }
        return 0;
    }

    public static String parseFromDBColumnToGUIChannel(String str) {
        return AbstractSensor.parseFromDBColumnToGUIChannel(mChannelMapRef, str);
    }

    public static String parseFromGUIChannelsToDBColumn(String str) {
        return AbstractSensor.parseFromGUIChannelsToDBColumn(mChannelMapRef, str);
    }

    public boolean checkLowPowerGyro() {
        if (this.mLSM6DSVGyroAccelRate == 1) {
            this.mLowPowerGyro = true;
        } else {
            this.mLowPowerGyro = false;
        }
        return this.mLowPowerGyro;
    }

    public int getAccelRange() {
        return this.mAccelRange;
    }

    public void setAccelRange(int i) {
        setLSM6DSVAccelRange(i);
    }

    public CalibDetailsKinematic getCurrentCalibDetailsGyro() {
        return this.mCurrentCalibDetailsGyro;
    }

    public int getGyroRange() {
        return this.mGyroRange;
    }

    public void setGyroRange(int i) {
        setLSM6DSVGyroRange(i);
    }

    public int getLSM6DSVGyroAccelRate() {
        return this.mLSM6DSVGyroAccelRate;
    }

    public void setLSM6DSVGyroAccelRate(int i) {
        this.mLSM6DSVGyroAccelRate = i;
    }

    public int getLowPowerGyroEnabled() {
        return this.mLowPowerGyro ? 1 : 0;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getSettings(String str, Configuration.COMMUNICATION_TYPE communication_type) {
        return null;
    }

    public boolean isLowPowerGyroEnabled() {
        return this.mLowPowerGyro;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean processResponse(int i, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return false;
    }

    private byte[] generateCalParamAnalogAccel() {
        return this.mCurrentCalibDetailsAccelLn.generateCalParamByteArray();
    }

    public void parseCalibParamFromPacketAccelAnalog(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCurrentCalibDetailsAccelLn.parseCalParamByteArray(bArr, calib_read_source);
    }

    private void setDefaultCalibrationShimmer3rLowNoiseAccel() {
        this.mCurrentCalibDetailsAccelLn.resetToDefaultParameters();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public String getSensorName() {
        return this.mSensorName;
    }

    public boolean isUsingDefaultLNAccelParam() {
        return this.mCurrentCalibDetailsAccelLn.isUsingDefaultParameters();
    }

    public double[][] getAlignmentMatrixAccel() {
        return this.mCurrentCalibDetailsAccelLn.getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixAccel() {
        return this.mCurrentCalibDetailsAccelLn.getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixAccel() {
        return this.mCurrentCalibDetailsAccelLn.getValidOffsetVector();
    }

    public void updateCurrentAccelLnCalibInUse() {
        this.mCurrentCalibDetailsAccelLn = getCurrentCalibDetailsIfKinematic(this.mSensorIdAccelLN, getAccelRange());
    }

    public CalibDetailsKinematic getCurrentCalibDetailsAccelLn() {
        if (this.mCurrentCalibDetailsAccelLn == null) {
            updateCurrentAccelLnCalibInUse();
        }
        return this.mCurrentCalibDetailsAccelLn;
    }

    public byte[] generateCalParamByteArrayAccelLn() {
        return getCurrentCalibDetailsAccelLn().generateCalParamByteArray();
    }

    public void setLSM6DSVAccelRange(int i) {
        if (ArrayUtils.contains(ListofLSM6DSVAccelRangeConfigValues, Integer.valueOf(i))) {
            this.mAccelRange = i;
            updateCurrentAccelLnCalibInUse();
        }
    }

    public void setDefaultLSM6DSVAccelSensorConfig(boolean z) {
        if (z) {
            setLSM6DSVAccelRange(0);
        }
    }

    public void setLSM6DSVGyroRange(int i) {
        if (ArrayUtils.contains(ListofLSM6DSVGyroRangeConfigValues, Integer.valueOf(i))) {
            if (checkIfAnyMplChannelEnabled()) {
                i = 3;
            }
            this.mGyroRange = i;
            updateCurrentGyroCalibInUse();
        }
    }

    public boolean checkIfAnyMplChannelEnabled() {
        if (!this.mShimmerVerObject.isSupportedMpl() || this.mSensorMap.keySet().size() <= 0) {
            return false;
        }
        Iterator<Integer> it2 = mListOfMplChannels.iterator();
        while (it2.hasNext()) {
            if (isSensorEnabled(it2.next().intValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfAMpuGyroOrAccelEnabled() {
        return isSensorEnabled(this.mSensorIdGyro) || isSensorEnabled(this.mSensorIdAccelLN);
    }

    public int setLSM6DSVGyroAccelRateFromFreq(double d) {
        setLSM6DSVGyroAccelRate(getGyroRateFromFreqForSensor(isSensorEnabled(this.mSensorIdGyro) || isSensorEnabled(this.mSensorIdAccelLN), d, this.mLowPowerGyro));
        return this.mLSM6DSVGyroAccelRate;
    }

    public int getGyroRateFromFreqForSensor(boolean z, double d, boolean z2) {
        return getGyroRateFromFreq(z, d, z2);
    }

    public void setLowPowerGyro(boolean z) {
        this.mLowPowerGyro = z;
        if (this.mShimmerDevice != null) {
            setLSM6DSVGyroAccelRateFromFreq(getSamplingRateShimmer());
        }
    }

    public double[][] getAlignmentMatrixGyro() {
        return this.mCurrentCalibDetailsGyro.getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixGyro() {
        return this.mCurrentCalibDetailsGyro.getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixGyro() {
        return this.mCurrentCalibDetailsGyro.getValidOffsetVector();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorMap() {
        super.createLocalSensorMapWithCustomParser(mSensorMapRef, mChannelMapRef);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public LinkedHashMap<String, Object> generateConfigMap() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.ACCEL_RANGE, Integer.valueOf(getAccelRange()));
        linkedHashMap.put(DatabaseConfigHandle.GYRO_RANGE, Integer.valueOf(getGyroRange()));
        linkedHashMap.put(DatabaseConfigHandle.GYRO_RATE, Integer.valueOf(getLSM6DSVGyroAccelRate()));
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsAccelLn(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC, DatabaseConfigHandle.LN_ACC_CALIB_TIME);
        AbstractSensor.addCalibDetailsToDbMap(linkedHashMap, getCurrentCalibDetailsGyro(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO, DatabaseConfigHandle.GYRO_CALIB_TIME);
        return linkedHashMap;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void parseConfigMap(LinkedHashMap<String, Object> linkedHashMap) {
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ACCEL_RANGE)) {
            setAccelRange(((Double) linkedHashMap.get(DatabaseConfigHandle.ACCEL_RANGE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.GYRO_RATE)) {
            setLSM6DSVGyroAccelRate(((Double) linkedHashMap.get(DatabaseConfigHandle.GYRO_RATE)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.GYRO_RANGE)) {
            setGyroRange(((Double) linkedHashMap.get(DatabaseConfigHandle.GYRO_RANGE)).intValue());
        }
        parseCalibDetailsKinematicFromDb(linkedHashMap, 37, getAccelRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC, DatabaseConfigHandle.LN_ACC_CALIB_TIME);
        parseCalibDetailsKinematicFromDb(linkedHashMap, 38, getGyroRange(), DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO, DatabaseConfigHandle.GYRO_CALIB_TIME);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateConfigOptionsMap() {
        this.mConfigOptionsMap.clear();
        addConfigOption(configOptionLSM6DSVGyroRange);
        addConfigOption(configOptionLSM6DSVAccelRange);
        addConfigOption(configOptionLSM6DSVGyroRate);
        addConfigOption(configOptionLSM6DSVGyroLpm);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateSensorGroupMapping() {
        this.mSensorGroupingMap = new LinkedHashMap<>();
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.LOW_NOISE_ACCEL_3R.ordinal()), sensorGroupLnAccelLSM6DSV);
        this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.GYRO_3R.ordinal()), new SensorGroupingDetails("Gyroscope", Arrays.asList(38), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoLSM6DSV));
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ObjectCluster processDataCustom(SensorDetails sensorDetails, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, ObjectCluster objectCluster, boolean z, double d) {
        ObjectCluster objectClusterProcessDataCommon = sensorDetails.processDataCommon(bArr, communication_type, objectCluster, z, d);
        if (mEnableCalibration && this.mCurrentCalibDetailsAccelLn != null) {
            double[] dArr = new double[3];
            for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_X)) {
                    dArr[0] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Y)) {
                    dArr[1] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Z)) {
                    dArr[2] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails.mObjectClusterName), channelDetails.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                }
            }
            double[] dArrCalibrateInertialSensorData = UtilCalibration.calibrateInertialSensorData(dArr, this.mCurrentCalibDetailsAccelLn);
            for (ChannelDetails channelDetails2 : sensorDetails.mListOfChannels) {
                if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_X)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, isUsingDefaultLNAccelParam());
                } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Y)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultLNAccelParam());
                } else if (channelDetails2.mObjectClusterName.equals(ObjectClusterSensorName.ACCEL_LN_Z)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails2, dArrCalibrateInertialSensorData[2], objectClusterProcessDataCommon.getIndexKeeper() - 1, isUsingDefaultLNAccelParam());
                }
            }
        }
        if (this.mIsDebugOutput) {
            super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.ACCEL_LN_X, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_LN_Y, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_LN_Z, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_LN_X, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_LN_Y, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.ACCEL_LN_Z, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
        }
        if (mEnableCalibration && this.mCurrentCalibDetailsGyro != null && sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel.equals("Gyroscope")) {
            double[] dArr2 = new double[3];
            for (ChannelDetails channelDetails3 : sensorDetails.mListOfChannels) {
                if (channelDetails3.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_X)) {
                    dArr2[0] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails3.mObjectClusterName), channelDetails3.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails3.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_Y)) {
                    dArr2[1] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails3.mObjectClusterName), channelDetails3.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                } else if (channelDetails3.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_Z)) {
                    dArr2[2] = ObjectCluster.returnFormatCluster(objectClusterProcessDataCommon.getCollectionOfFormatClusters(channelDetails3.mObjectClusterName), channelDetails3.mChannelFormatDerivedFromShimmerDataPacket.toString()).mData;
                }
            }
            double[] dArrCalibrateInertialSensorData2 = UtilCalibration.calibrateInertialSensorData(dArr2, getCurrentCalibDetailsGyro());
            for (ChannelDetails channelDetails4 : sensorDetails.mListOfChannels) {
                if (channelDetails4.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_X)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails4, dArrCalibrateInertialSensorData2[0], objectClusterProcessDataCommon.getIndexKeeper() - 3, isUsingDefaultGyroParam());
                } else if (channelDetails4.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_Y)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails4, dArrCalibrateInertialSensorData2[1], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultGyroParam());
                } else if (channelDetails4.mObjectClusterName.equals(ObjectClusterSensorName.GYRO_Z)) {
                    objectClusterProcessDataCommon.addCalData(channelDetails4, dArrCalibrateInertialSensorData2[2], objectClusterProcessDataCommon.getIndexKeeper() - 2, isUsingDefaultGyroParam());
                }
            }
        }
        if (this.mIsDebugOutput) {
            super.consolePrintChannelsCal(objectClusterProcessDataCommon, Arrays.asList(new String[]{ObjectClusterSensorName.GYRO_X, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.GYRO_Y, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.GYRO_Z, ChannelDetails.CHANNEL_TYPE.UNCAL.toString()}, new String[]{ObjectClusterSensorName.GYRO_X, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.GYRO_Y, ChannelDetails.CHANNEL_TYPE.CAL.toString()}, new String[]{ObjectClusterSensorName.GYRO_Z, ChannelDetails.CHANNEL_TYPE.CAL.toString()}));
        }
        return objectClusterProcessDataCommon;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesGenerate(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            int i = configByteLayoutShimmer3.idxConfigSetupByte1;
            bArr[i] = (byte) (bArr[i] | ((byte) ((getLSM6DSVGyroAccelRate() & configByteLayoutShimmer3.maskMPU9150AccelGyroSamplingRate) << configByteLayoutShimmer3.bitShiftMPU9150AccelGyroSamplingRate)));
            int i2 = configByteLayoutShimmer3.idxConfigSetupByte2;
            bArr[i2] = (byte) (bArr[i2] | ((byte) ((getGyroRange() & configByteLayoutShimmer3.maskMPU9150GyroRange) << configByteLayoutShimmer3.bitShiftMPU9150GyroRange)));
            int i3 = configByteLayoutShimmer3.idxConfigSetupByte3;
            bArr[i3] = (byte) (bArr[i3] | ((byte) ((getAccelRange() & configByteLayoutShimmer3.maskMPU9150AccelRange) << configByteLayoutShimmer3.bitShiftMPU9150AccelRange)));
            int i4 = configByteLayoutShimmer3.idxConfigSetupByte4;
            bArr[i4] = (byte) (bArr[i4] | ((byte) (((getGyroRange() >> 2) & configByteLayoutShimmer3.maskLSM6DSVGyroRangeMSB) << configByteLayoutShimmer3.bitShiftLSM6DSVGyroRangeMSB)));
            System.arraycopy(generateCalParamByteArrayAccelLn(), 0, bArr, configByteLayoutShimmer3.idxAnalogAccelCalibration, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            System.arraycopy(generateCalParamGyroscope(), 0, bArr, configByteLayoutShimmer3.idxMPU9150GyroCalibration, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void configBytesParse(ShimmerDevice shimmerDevice, byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        ConfigByteLayout configByteLayout = shimmerDevice.getConfigByteLayout();
        if (configByteLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 configByteLayoutShimmer3 = (ConfigByteLayoutShimmer3) configByteLayout;
            setLSM6DSVAccelRange((bArr[configByteLayoutShimmer3.idxConfigSetupByte3] >> configByteLayoutShimmer3.bitShiftMPU9150AccelRange) & configByteLayoutShimmer3.maskMPU9150AccelRange);
            if (shimmerDevice.isConnected()) {
                getCurrentCalibDetailsAccelLn().mCalibReadSource = CalibDetails.CALIB_READ_SOURCE.INFOMEM;
            }
            byte[] bArr2 = new byte[configByteLayoutShimmer3.lengthGeneralCalibrationBytes];
            System.arraycopy(bArr, configByteLayoutShimmer3.idxAnalogAccelCalibration, bArr2, 0, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            parseCalibParamFromPacketAccelAnalog(bArr2, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
            setLSM6DSVGyroAccelRate((bArr[configByteLayoutShimmer3.idxConfigSetupByte1] >> configByteLayoutShimmer3.bitShiftMPU9150AccelGyroSamplingRate) & configByteLayoutShimmer3.maskMPU9150AccelGyroSamplingRate);
            checkLowPowerGyro();
            setGyroRange(((bArr[configByteLayoutShimmer3.idxConfigSetupByte2] >> configByteLayoutShimmer3.bitShiftMPU9150GyroRange) & configByteLayoutShimmer3.maskMPU9150GyroRange) | (((bArr[configByteLayoutShimmer3.idxConfigSetupByte4] >> configByteLayoutShimmer3.bitShiftLSM6DSVGyroRangeMSB) & configByteLayoutShimmer3.maskLSM6DSVGyroRangeMSB) << 2));
            if (shimmerDevice.isConnected()) {
                getCurrentCalibDetailsGyro().mCalibReadSource = CalibDetails.CALIB_READ_SOURCE.INFOMEM;
            }
            byte[] bArr3 = new byte[configByteLayoutShimmer3.lengthGeneralCalibrationBytes];
            System.arraycopy(bArr, configByteLayoutShimmer3.idxMPU9150GyroCalibration, bArr3, 0, configByteLayoutShimmer3.lengthGeneralCalibrationBytes);
            parseCalibParamFromPacketGyro(bArr3, CalibDetails.CALIB_READ_SOURCE.INFOMEM);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "Gyro Low-Power Mode":
                setLowPowerGyro(((Boolean) obj).booleanValue());
                return null;
            case "Rate":
                if (num.intValue() == this.mSensorIdGyro) {
                    setConfigValueUsingConfigLabel("Gyro Sampling Rate", obj);
                }
                return null;
            case "Range":
                if (num.intValue() == this.mSensorIdGyro) {
                    setConfigValueUsingConfigLabel("Gyro Range", obj);
                } else if (num.intValue() == this.mSensorIdAccelLN) {
                    setConfigValueUsingConfigLabel(GuiLabelConfig.LSM6DSV_ACCEL_RANGE, obj);
                }
                return null;
            case "Low Noise Accel Range":
                setLSM6DSVAccelRange(((Integer) obj).intValue());
                return null;
            case "Gyro Range":
                setLSM6DSVGyroRange(((Integer) obj).intValue());
                return null;
            case "Gyro Sampling Rate":
                double d = String.valueOf(obj).isEmpty() ? 4.0d : Double.parseDouble(String.valueOf(obj));
                setLowPowerGyro(false);
                if (this.debugGyroRate && this.mShimmerDevice != null) {
                    System.out.println("Gyro Rate change from freq:\t" + this.mShimmerDevice.getMacId() + "\tGuiLabelConfig\t" + d);
                }
                setLSM6DSVGyroAccelRateFromFreq(d);
                return Double.toString(Math.round(getLSM6DSVGyroAccelRateInHz() * 100.0d) / 100.0d);
            default:
                return super.setConfigValueUsingConfigLabelCommon(num, str, obj);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        str.hashCode();
        switch (str) {
            case "Gyro Low-Power Mode":
                return Boolean.valueOf(checkLowPowerGyro());
            case "Gyro Sampling Rate Hertz":
                return Double.valueOf(getLSM6DSVGyroAccelRateInHz());
            case "Rate":
                if (num.intValue() == this.mSensorIdGyro) {
                    return getConfigValueUsingConfigLabel("Gyro Sampling Rate");
                }
                return null;
            case "Range":
                if (num.intValue() == this.mSensorIdGyro) {
                    return getConfigValueUsingConfigLabel("Gyro Range");
                }
                if (num.intValue() == this.mSensorIdAccelLN) {
                    return getConfigValueUsingConfigLabel(GuiLabelConfig.LSM6DSV_ACCEL_RANGE);
                }
                return null;
            case "Low Noise Accel Range":
                return Integer.valueOf(getAccelRange());
            case "Gyro Range":
                return Integer.valueOf(getGyroRange());
            case "Gyro Sampling Rate":
                return Integer.valueOf(getLSM6DSVGyroAccelRate());
            default:
                return super.getConfigValueUsingConfigLabelCommon(num, str);
        }
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setSensorSamplingRate(double d) {
        if (this.debugGyroRate && this.mShimmerDevice != null) {
            System.out.println("Gyro Rate change from freq:\t" + this.mShimmerDevice.getMacId() + "\tsetSamplingRateSensors\t" + d);
        }
        setLSM6DSVGyroAccelRateFromFreq(d);
        checkLowPowerGyro();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean setDefaultConfigForSensor(int i, boolean z) {
        if (!this.mSensorMap.containsKey(Integer.valueOf(i))) {
            return false;
        }
        if (i == this.mSensorIdAccelLN) {
            setDefaultLSM6DSVAccelSensorConfig(z);
            return true;
        }
        if (i != this.mSensorIdGyro) {
            return true;
        }
        setDefaultLSM6DSVGyroSensorConfig(z);
        return true;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean checkConfigOptionValues(String str) {
        return this.mConfigOptionsMap.containsKey(str);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return new ActionSetting(communication_type);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void checkShimmerConfigBeforeConfiguring() {
        if (isSensorEnabled(this.mSensorIdGyro) || isSensorEnabled(this.mSensorIdAccelLN)) {
            return;
        }
        setDefaultLSM6DSVGyroSensorConfig(false);
        setDefaultLSM6DSVAccelSensorConfig(false);
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void initialise() {
        this.mSensorIdAccelLN = 37;
        this.mSensorIdGyro = 38;
        super.initialise();
        updateCurrentAccelLnCalibInUse();
        setCalibSensitivityScaleFactor(this.mSensorIdGyro, CalibDetailsKinematic.CALIBRATION_SCALE_FACTOR.ONE_HUNDRED);
        updateCurrentGyroCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void generateCalibMap() {
        super.generateCalibMap();
        TreeMap<Integer, CalibDetails> treeMap = new TreeMap<>();
        treeMap.put(Integer.valueOf(this.calibDetailsAccelLn2g.mRangeValue), this.calibDetailsAccelLn2g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelLn4g.mRangeValue), this.calibDetailsAccelLn4g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelLn8g.mRangeValue), this.calibDetailsAccelLn8g);
        treeMap.put(Integer.valueOf(this.calibDetailsAccelLn16g.mRangeValue), this.calibDetailsAccelLn16g);
        setCalibrationMapPerSensor(this.mSensorIdAccelLN, treeMap);
        updateCurrentAccelLnCalibInUse();
        TreeMap<Integer, CalibDetails> treeMap2 = new TreeMap<>();
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro125.mRangeValue), this.calibDetailsGyro125);
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro250.mRangeValue), this.calibDetailsGyro250);
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro500.mRangeValue), this.calibDetailsGyro500);
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro1000.mRangeValue), this.calibDetailsGyro1000);
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro2000.mRangeValue), this.calibDetailsGyro2000);
        treeMap2.put(Integer.valueOf(this.calibDetailsGyro4000.mRangeValue), this.calibDetailsGyro4000);
        setCalibrationMapPerSensor(this.mSensorIdGyro, treeMap2);
        updateCurrentGyroCalibInUse();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public boolean isSensorUsingDefaultCal(int i) {
        if (i == 37) {
            return isUsingDefaultLNAccelParam();
        }
        if (i == 38) {
            return isUsingDefaultGyroParam();
        }
        return false;
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public void setCalibrationMapPerSensor(int i, TreeMap<Integer, CalibDetails> treeMap) {
        super.setCalibrationMapPerSensor(i, treeMap);
    }

    public void updateIsUsingDefaultLNAccelParam() {
        this.mIsUsingDefaultLNAccelParam = getCurrentCalibDetailsAccelLn().isUsingDefaultParameters();
    }

    public void updateIsUsingDefaultGyroParam() {
        this.mIsUsingDefaultGyroParam = getCurrentCalibDetailsGyro().isUsingDefaultParameters();
    }

    public void updateCurrentGyroCalibInUse() {
        this.mCurrentCalibDetailsGyro = getCurrentCalibDetailsIfKinematic(this.mSensorIdGyro, getGyroRange());
    }

    public boolean isUsingDefaultGyroParam() {
        return this.mCurrentCalibDetailsGyro.isUsingDefaultParameters();
    }

    public byte[] generateCalParamGyroscope() {
        return this.mCurrentCalibDetailsGyro.generateCalParamByteArray();
    }

    private boolean isGyroUsingDefaultParameters() {
        return this.mCurrentCalibDetailsGyro.isUsingDefaultParameters();
    }

    public void parseCalibParamFromPacketGyro(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCurrentCalibDetailsGyro.parseCalParamByteArray(bArr, calib_read_source);
    }

    private boolean checkIfDefaultGyroCal(double[][] dArr, double[][] dArr2, double[][] dArr3) {
        return this.mCurrentCalibDetailsGyro.isUsingDefaultParameters();
    }

    public double getLSM6DSVGyroAccelRateInHz() {
        return ArrayUtils.contains(ListofLSM6DSVGyroRateConfigValues, Integer.valueOf(this.mLSM6DSVGyroAccelRate)) ? ListofLSM6DSVGyroRateDouble[this.mLSM6DSVGyroAccelRate].doubleValue() : this.mLSM6DSVGyroAccelRate;
    }

    public void setDefaultLSM6DSVGyroSensorConfig(boolean z) {
        if (z) {
            setLowPowerGyro(false);
        } else {
            setLowPowerGyro(true);
        }
        setGyroRange(1);
    }

    public static class DatabaseChannelHandles {
        public static final String GYRO_X = "LSM6DSV_GYRO_X";
        public static final String GYRO_Y = "LSM6DSV_GYRO_Y";
        public static final String GYRO_Z = "LSM6DSV_GYRO_Z";
        public static final String LN_ACC_X = "LSM6DSV_X";
        public static final String LN_ACC_Y = "LSM6DSV_Y";
        public static final String LN_ACC_Z = "LSM6DSV_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String ACCEL_RANGE = "LSM6DSV_Accel_Range";
        public static final String GYRO_CALIB_TIME = "LSM6DSV_Gyro_Calib_Time";
        public static final String GYRO_RANGE = "LSM6DSV_Gyro_Range";
        public static final String GYRO_RATE = "LSM6DSV_Gyro_Rate";
        public static final String LN_ACC_CALIB_TIME = "LSM6DSV_Acc_Calib_Time";
        public static final String LN_ACC_OFFSET_X = "LSM6DSV_Acc_Offset_X";
        public static final String LN_ACC_OFFSET_Y = "LSM6DSV_Acc_Offset_Y";
        public static final String LN_ACC_OFFSET_Z = "LSM6DSV_Acc_Offset_Z";
        public static final String LN_ACC_GAIN_X = "LSM6DSV_Acc_Gain_X";
        public static final String LN_ACC_GAIN_Y = "LSM6DSV_Acc_Gain_Y";
        public static final String LN_ACC_GAIN_Z = "LSM6DSV_Acc_Gain_Z";
        public static final String LN_ACC_ALIGN_XX = "LSM6DSV_Acc_Align_XX";
        public static final String LN_ACC_ALIGN_XY = "LSM6DSV_Acc_Align_XY";
        public static final String LN_ACC_ALIGN_XZ = "LSM6DSV_Acc_Align_XZ";
        public static final String LN_ACC_ALIGN_YX = "LSM6DSV_Acc_Align_YX";
        public static final String LN_ACC_ALIGN_YY = "LSM6DSV_Acc_Align_YY";
        public static final String LN_ACC_ALIGN_YZ = "LSM6DSV_Acc_Align_YZ";
        public static final String LN_ACC_ALIGN_ZX = "LSM6DSV_Acc_Align_ZX";
        public static final String LN_ACC_ALIGN_ZY = "LSM6DSV_Acc_Align_ZY";
        public static final String LN_ACC_ALIGN_ZZ = "LSM6DSV_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_LN_ACC = Arrays.asList(LN_ACC_OFFSET_X, LN_ACC_OFFSET_Y, LN_ACC_OFFSET_Z, LN_ACC_GAIN_X, LN_ACC_GAIN_Y, LN_ACC_GAIN_Z, LN_ACC_ALIGN_XX, LN_ACC_ALIGN_XY, LN_ACC_ALIGN_XZ, LN_ACC_ALIGN_YX, LN_ACC_ALIGN_YY, LN_ACC_ALIGN_YZ, LN_ACC_ALIGN_ZX, LN_ACC_ALIGN_ZY, LN_ACC_ALIGN_ZZ);
        public static final String GYRO_OFFSET_X = "LSM6DSV_Gyro_Offset_X";
        public static final String GYRO_OFFSET_Y = "LSM6DSV_Gyro_Offset_Y";
        public static final String GYRO_OFFSET_Z = "LSM6DSV_Gyro_Offset_Z";
        public static final String GYRO_GAIN_X = "LSM6DSV_Gyro_Gain_X";
        public static final String GYRO_GAIN_Y = "LSM6DSV_Gyro_Gain_Y";
        public static final String GYRO_GAIN_Z = "LSM6DSV_Gyro_Gain_Z";
        public static final String GYRO_ALIGN_XX = "LSM6DSV_Gyro_Align_XX";
        public static final String GYRO_ALIGN_XY = "LSM6DSV_Gyro_Align_XY";
        public static final String GYRO_ALIGN_XZ = "LSM6DSV_Gyro_Align_XZ";
        public static final String GYRO_ALIGN_YX = "LSM6DSV_Gyro_Align_YX";
        public static final String GYRO_ALIGN_YY = "LSM6DSV_Gyro_Align_YY";
        public static final String GYRO_ALIGN_YZ = "LSM6DSV_Gyro_Align_YZ";
        public static final String GYRO_ALIGN_ZX = "LSM6DSV_Gyro_Align_ZX";
        public static final String GYRO_ALIGN_ZY = "LSM6DSV_Gyro_Align_ZY";
        public static final String GYRO_ALIGN_ZZ = "LSM6DSV_Gyro_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_GYRO = Arrays.asList(GYRO_OFFSET_X, GYRO_OFFSET_Y, GYRO_OFFSET_Z, GYRO_GAIN_X, GYRO_GAIN_Y, GYRO_GAIN_Z, GYRO_ALIGN_XX, GYRO_ALIGN_XY, GYRO_ALIGN_XZ, GYRO_ALIGN_YX, GYRO_ALIGN_YY, GYRO_ALIGN_YZ, GYRO_ALIGN_ZX, GYRO_ALIGN_ZY, GYRO_ALIGN_ZZ);
    }

    public static class ObjectClusterSensorName {
        public static String ACCEL_LN_X = "Accel_LN_X";
        public static String ACCEL_LN_Y = "Accel_LN_Y";
        public static String ACCEL_LN_Z = "Accel_LN_Z";
        public static String GYRO_X = "Gyro_X";
        public static String GYRO_Y = "Gyro_Y";
        public static String GYRO_Z = "Gyro_Z";
    }

    public class GuiLabelConfig {
        public static final String LSM6DSV_ACCEL_CALIB_PARAM = "Low Noise Accel Calibration Details";
        public static final String LSM6DSV_ACCEL_DEFAULT_CALIB = "Low Noise Accel Default Calibration";
        public static final String LSM6DSV_ACCEL_RANGE = "Low Noise Accel Range";
        public static final String LSM6DSV_ACCEL_VALID_CALIB = "Low Noise Accel Valid Calibration";
        public static final String LSM6DSV_GYRO_LPM = "Gyro Low-Power Mode";
        public static final String LSM6DSV_GYRO_RANGE = "Gyro Range";
        public static final String LSM6DSV_GYRO_RATE = "Gyro Sampling Rate";
        public static final String LSM6DSV_GYRO_RATE_HZ = "Gyro Sampling Rate Hertz";

        public GuiLabelConfig() {
        }
    }

    public class GuiLabelSensors {
        public static final String ACCEL_LN = "Low-Noise Accelerometer";
        public static final String GYRO = "Gyroscope";

        public GuiLabelSensors() {
        }
    }

    public class LABEL_SENSOR_TILE {
        public static final String GYRO = "Gyroscope";
        public static final String LOW_NOISE_ACCEL = "Low-Noise Accelerometer";

        public LABEL_SENSOR_TILE() {
        }
    }
}
