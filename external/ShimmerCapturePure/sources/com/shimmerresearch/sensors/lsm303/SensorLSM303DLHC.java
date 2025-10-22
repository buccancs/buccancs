package com.shimmerresearch.sensors.lsm303;

import com.shimmerresearch.bluetooth.BtCommandDetails;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.OldCalDetails;
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
public class SensorLSM303DLHC extends SensorLSM303 {
    public static final byte ACCEL_SAMPLING_RATE_RESPONSE = 65;
    public static final byte ACCEL_SENSITIVITY_RESPONSE = 10;
    public static final double[][] DefaultAlignmentLSM303DLHC;
    public static final double[][] DefaultAlignmentMatrixMagShimmer3;
    public static final double[][] DefaultAlignmentMatrixWideRangeAccelShimmer3;
    public static final double[][] DefaultOffsetVectorMagShimmer3;
    public static final double[][] DefaultOffsetVectorWideRangeAccelShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag1p3GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag1p9GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag2p5GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag4GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag4p7GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag5p6GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixMag8p1GaShimmer3;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel16gShimmer3;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel2gShimmer3;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel4gShimmer3;
    public static final double[][] DefaultSensitivityMatrixWideRangeAccel8gShimmer3;
    public static final byte GET_ACCEL_SAMPLING_RATE_COMMAND = 66;
    public static final byte GET_ACCEL_SENSITIVITY_COMMAND = 11;
    public static final byte GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND = 28;
    public static final byte GET_LSM303DLHC_ACCEL_HRMODE_COMMAND = 72;
    public static final byte GET_LSM303DLHC_ACCEL_LPMODE_COMMAND = 69;
    public static final byte GET_MAG_CALIBRATION_COMMAND = 25;
    public static final byte GET_MAG_GAIN_COMMAND = 57;
    public static final byte GET_MAG_SAMPLING_RATE_COMMAND = 60;
    public static final byte LSM303DLHC_ACCEL_CALIBRATION_RESPONSE = 27;
    public static final byte LSM303DLHC_ACCEL_HRMODE_RESPONSE = 71;
    public static final byte LSM303DLHC_ACCEL_LPMODE_RESPONSE = 68;
    public static final Integer[] ListofLSM303AccelRangeConfigValues;
    public static final String[] ListofLSM303DLHCAccelRateHr;
    public static final Integer[] ListofLSM303DLHCAccelRateHrConfigValues;
    public static final String[] ListofLSM303DLHCAccelRateLpm;
    public static final Integer[] ListofLSM303DLHCAccelRateLpmConfigValues;
    public static final String[] ListofLSM303DLHCMagRange;
    public static final Integer[] ListofLSM303DLHCMagRangeConfigValues;
    public static final String[] ListofLSM303DLHCMagRate;
    public static final Integer[] ListofLSM303DLHCMagRateConfigValues;
    public static final byte MAG_CALIBRATION_RESPONSE = 24;
    public static final byte MAG_GAIN_RESPONSE = 56;
    public static final byte MAG_SAMPLING_RATE_RESPONSE = 59;
    public static final byte SET_ACCEL_SAMPLING_RATE_COMMAND = 64;
    public static final byte SET_ACCEL_SENSITIVITY_COMMAND = 9;
    public static final byte SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND = 26;
    public static final byte SET_LSM303DLHC_ACCEL_HRMODE_COMMAND = 70;
    public static final byte SET_LSM303DLHC_ACCEL_LPMODE_COMMAND = 67;
    public static final byte SET_MAG_CALIBRATION_COMMAND = 23;
    public static final byte SET_MAG_GAIN_COMMAND = 55;
    public static final byte SET_MAG_SAMPLING_RATE_COMMAND = 58;
    public static final ChannelDetails channelLSM303DLHCAccelX;
    public static final ChannelDetails channelLSM303DLHCAccelY;
    public static final ChannelDetails channelLSM303DLHCAccelZ;
    public static final ChannelDetails channelLSM303DLHCMagX;
    public static final ChannelDetails channelLSM303DLHCMagY;
    public static final ChannelDetails channelLSM303DLHCMagZ;
    public static final ConfigOptionDetailsSensor configOptionAccelLpm;
    public static final ConfigOptionDetailsSensor configOptionAccelRange;
    public static final ConfigOptionDetailsSensor configOptionAccelRate;
    public static final ConfigOptionDetailsSensor configOptionMagLpm;
    public static final ConfigOptionDetailsSensor configOptionMagRange;
    public static final ConfigOptionDetailsSensor configOptionMagRate;
    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<String, ChannelDetails> mChannelMapRef;
    public static final Map<String, OldCalDetails> mOldCalRangeMap;
    public static final Map<Integer, SensorDetailsRef> mSensorMapRef;
    public static final SensorGroupingDetails sensorGroupLsmAccel;
    public static final SensorGroupingDetails sensorGroupLsmMag;
    public static final SensorDetailsRef sensorLSM303DLHCAccel;
    public static final SensorDetailsRef sensorLSM303DLHCMag;
    private static final long serialVersionUID = -2119834127313796684L;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("accel_wr_2g", new OldCalDetails("accel_wr_2g", 31, 0));
        linkedHashMap.put("accel_wr_4g", new OldCalDetails("accel_wr_4g", 31, 1));
        linkedHashMap.put("accel_wr_8g", new OldCalDetails("accel_wr_8g", 31, 2));
        linkedHashMap.put("accel_wr_16g", new OldCalDetails("accel_wr_16g", 31, 3));
        linkedHashMap.put("mag_13ga", new OldCalDetails("mag_13ga", 32, 1));
        linkedHashMap.put("mag_19ga", new OldCalDetails("mag_19ga", 32, 2));
        linkedHashMap.put("mag_25ga", new OldCalDetails("mag_25ga", 32, 3));
        linkedHashMap.put("mag_4ga", new OldCalDetails("mag_4ga", 32, 4));
        linkedHashMap.put("mag_47ga", new OldCalDetails("mag_47ga", 32, 5));
        linkedHashMap.put("mag_56ga", new OldCalDetails("mag_56ga", 32, 6));
        linkedHashMap.put("mag_81ga", new OldCalDetails("mag_81ga", 32, 7));
        mOldCalRangeMap = Collections.unmodifiableMap(linkedHashMap);
        double[][] dArr = {new double[]{-1.0d, 0.0d, 0.0d}, new double[]{0.0d, 1.0d, 0.0d}, new double[]{0.0d, 0.0d, -1.0d}};
        DefaultAlignmentLSM303DLHC = dArr;
        DefaultAlignmentMatrixWideRangeAccelShimmer3 = dArr;
        DefaultOffsetVectorWideRangeAccelShimmer3 = new double[][]{new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
        DefaultSensitivityMatrixWideRangeAccel2gShimmer3 = new double[][]{new double[]{1631.0d, 0.0d, 0.0d}, new double[]{0.0d, 1631.0d, 0.0d}, new double[]{0.0d, 0.0d, 1631.0d}};
        DefaultSensitivityMatrixWideRangeAccel4gShimmer3 = new double[][]{new double[]{815.0d, 0.0d, 0.0d}, new double[]{0.0d, 815.0d, 0.0d}, new double[]{0.0d, 0.0d, 815.0d}};
        DefaultSensitivityMatrixWideRangeAccel8gShimmer3 = new double[][]{new double[]{408.0d, 0.0d, 0.0d}, new double[]{0.0d, 408.0d, 0.0d}, new double[]{0.0d, 0.0d, 408.0d}};
        DefaultSensitivityMatrixWideRangeAccel16gShimmer3 = new double[][]{new double[]{135.0d, 0.0d, 0.0d}, new double[]{0.0d, 135.0d, 0.0d}, new double[]{0.0d, 0.0d, 135.0d}};
        DefaultAlignmentMatrixMagShimmer3 = dArr;
        DefaultOffsetVectorMagShimmer3 = new double[][]{new double[]{0.0d}, new double[]{0.0d}, new double[]{0.0d}};
        DefaultSensitivityMatrixMag1p3GaShimmer3 = new double[][]{new double[]{1100.0d, 0.0d, 0.0d}, new double[]{0.0d, 1100.0d, 0.0d}, new double[]{0.0d, 0.0d, 980.0d}};
        DefaultSensitivityMatrixMag1p9GaShimmer3 = new double[][]{new double[]{855.0d, 0.0d, 0.0d}, new double[]{0.0d, 855.0d, 0.0d}, new double[]{0.0d, 0.0d, 760.0d}};
        DefaultSensitivityMatrixMag2p5GaShimmer3 = new double[][]{new double[]{670.0d, 0.0d, 0.0d}, new double[]{0.0d, 670.0d, 0.0d}, new double[]{0.0d, 0.0d, 600.0d}};
        DefaultSensitivityMatrixMag4GaShimmer3 = new double[][]{new double[]{450.0d, 0.0d, 0.0d}, new double[]{0.0d, 450.0d, 0.0d}, new double[]{0.0d, 0.0d, 400.0d}};
        DefaultSensitivityMatrixMag4p7GaShimmer3 = new double[][]{new double[]{400.0d, 0.0d, 0.0d}, new double[]{0.0d, 400.0d, 0.0d}, new double[]{0.0d, 0.0d, 355.0d}};
        DefaultSensitivityMatrixMag5p6GaShimmer3 = new double[][]{new double[]{330.0d, 0.0d, 0.0d}, new double[]{0.0d, 330.0d, 0.0d}, new double[]{0.0d, 0.0d, 295.0d}};
        DefaultSensitivityMatrixMag8p1GaShimmer3 = new double[][]{new double[]{230.0d, 0.0d, 0.0d}, new double[]{0.0d, 230.0d, 0.0d}, new double[]{0.0d, 0.0d, 205.0d}};
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put((byte) 11, new BtCommandDetails((byte) 11, "GET_ACCEL_SENSITIVITY_COMMAND", (byte) 10));
        linkedHashMap2.put((byte) 25, new BtCommandDetails((byte) 25, "GET_MAG_CALIBRATION_COMMAND", (byte) 24));
        linkedHashMap2.put((byte) 28, new BtCommandDetails((byte) 28, "GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND", (byte) 27));
        linkedHashMap2.put((byte) 57, new BtCommandDetails((byte) 57, "GET_MAG_GAIN_COMMAND", (byte) 56));
        linkedHashMap2.put((byte) 60, new BtCommandDetails((byte) 60, "GET_MAG_SAMPLING_RATE_COMMAND", (byte) 59));
        linkedHashMap2.put((byte) 66, new BtCommandDetails((byte) 66, "GET_ACCEL_SAMPLING_RATE_COMMAND", (byte) 65));
        linkedHashMap2.put((byte) 69, new BtCommandDetails((byte) 69, "GET_LSM303DLHC_ACCEL_LPMODE_COMMAND", (byte) 68));
        linkedHashMap2.put((byte) 72, new BtCommandDetails((byte) 72, "GET_LSM303DLHC_ACCEL_HRMODE_COMMAND", (byte) 71));
        mBtGetCommandMap = Collections.unmodifiableMap(linkedHashMap2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put((byte) 9, new BtCommandDetails((byte) 9, "SET_ACCEL_SENSITIVITY_COMMAND"));
        linkedHashMap3.put((byte) 23, new BtCommandDetails((byte) 23, "SET_MAG_CALIBRATION_COMMAND"));
        linkedHashMap3.put((byte) 26, new BtCommandDetails((byte) 26, "SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND"));
        linkedHashMap3.put((byte) 55, new BtCommandDetails((byte) 55, "SET_MAG_GAIN_COMMAND"));
        linkedHashMap3.put((byte) 58, new BtCommandDetails((byte) 58, "SET_MAG_SAMPLING_RATE_COMMAND"));
        linkedHashMap3.put((byte) 64, new BtCommandDetails((byte) 64, "SET_ACCEL_SAMPLING_RATE_COMMAND"));
        linkedHashMap3.put((byte) 67, new BtCommandDetails((byte) 67, "SET_LSM303DLHC_ACCEL_LPMODE_COMMAND"));
        linkedHashMap3.put((byte) 70, new BtCommandDetails((byte) 70, "SET_LSM303DLHC_ACCEL_HRMODE_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(linkedHashMap3);
        Integer[] numArr = {0, 1, 2, 3};
        ListofLSM303AccelRangeConfigValues = numArr;
        String[] strArr = {"Power-down", "1.0Hz", "10.0Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "1344.0Hz"};
        ListofLSM303DLHCAccelRateHr = strArr;
        Integer[] numArr2 = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        ListofLSM303DLHCAccelRateHrConfigValues = numArr2;
        String[] strArr2 = {"Power-down", "1.0Hz", "10.0Hz", "25.0Hz", "50.0Hz", "100.0Hz", "200.0Hz", "400.0Hz", "1620.0Hz", "5376.0Hz"};
        ListofLSM303DLHCAccelRateLpm = strArr2;
        Integer[] numArr3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListofLSM303DLHCAccelRateLpmConfigValues = numArr3;
        configOptionAccelRange = new ConfigOptionDetailsSensor("Wide Range Accel Range", DatabaseConfigHandle.WR_ACC_RANGE, ListofLSM303AccelRange, numArr, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        configOptionAccelRate = new ConfigOptionDetailsSensor("Wide Range Accel Rate", DatabaseConfigHandle.WR_ACC_RATE, strArr, numArr2, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, (List<ConfigOptionObject>) Arrays.asList(new ConfigOptionObject(1, strArr2, numArr3)));
        String[] strArr3 = {"0.75Hz", "1.5Hz", "3.0Hz", "7.5Hz", "15.0Hz", "30.0Hz", "75.0Hz", "220.0Hz"};
        ListofLSM303DLHCMagRate = strArr3;
        Integer[] numArr4 = {0, 1, 2, 3, 4, 5, 6, 7};
        ListofLSM303DLHCMagRateConfigValues = numArr4;
        String[] strArr4 = {"+/- 1.3Ga", "+/- 1.9Ga", "+/- 2.5Ga", "+/- 4.0Ga", "+/- 4.7Ga", "+/- 5.6Ga", "+/- 8.1Ga"};
        ListofLSM303DLHCMagRange = strArr4;
        Integer[] numArr5 = {1, 2, 3, 4, 5, 6, 7};
        ListofLSM303DLHCMagRangeConfigValues = numArr5;
        configOptionMagRange = new ConfigOptionDetailsSensor("Mag Range", DatabaseConfigHandle.MAG_RANGE, strArr4, numArr5, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        configOptionMagRate = new ConfigOptionDetailsSensor("Mag Rate", DatabaseConfigHandle.MAG_RATE, strArr3, numArr4, ConfigOptionDetails.GUI_COMPONENT_TYPE.COMBOBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        configOptionAccelLpm = new ConfigOptionDetailsSensor("Wide Range Accel Low-Power Mode", DatabaseConfigHandle.WR_ACC_LPM, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX);
        configOptionMagLpm = new ConfigOptionDetailsSensor("Mag Low-Power Mode", null, ConfigOptionDetails.GUI_COMPONENT_TYPE.CHECKBOX, Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        SensorDetailsRef sensorDetailsRef = new SensorDetailsRef(4096L, 4096L, "Wide-Range Accelerometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, Arrays.asList("Wide Range Accel Range", "Wide Range Accel Rate"), Arrays.asList(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z));
        sensorLSM303DLHCAccel = sensorDetailsRef;
        SensorDetailsRef sensorDetailsRef2 = new SensorDetailsRef(32L, 32L, "Magnetometer", Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW, Arrays.asList("Mag Range", "Mag Rate"), Arrays.asList(SensorLSM303.ObjectClusterSensorName.MAG_X, SensorLSM303.ObjectClusterSensorName.MAG_Y, SensorLSM303.ObjectClusterSensorName.MAG_Z));
        sensorLSM303DLHCMag = sensorDetailsRef2;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put(31, sensorDetailsRef);
        linkedHashMap4.put(32, sensorDetailsRef2);
        mSensorMapRef = Collections.unmodifiableMap(linkedHashMap4);
        ChannelDetails channelDetails = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, DatabaseChannelHandles.WR_ACC_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 4);
        channelLSM303DLHCAccelX = channelDetails;
        ChannelDetails channelDetails2 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, DatabaseChannelHandles.WR_ACC_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 5);
        channelLSM303DLHCAccelY = channelDetails2;
        ChannelDetails channelDetails3 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, DatabaseChannelHandles.WR_ACC_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.LSB, "m/(s^2)", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 6);
        channelLSM303DLHCAccelZ = channelDetails3;
        ChannelDetails channelDetails4 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_X, SensorLSM303.ObjectClusterSensorName.MAG_X, DatabaseChannelHandles.MAG_X, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 7);
        channelLSM303DLHCMagX = channelDetails4;
        ChannelDetails channelDetails5 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_Y, SensorLSM303.ObjectClusterSensorName.MAG_Y, DatabaseChannelHandles.MAG_Y, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 8);
        channelLSM303DLHCMagY = channelDetails5;
        ChannelDetails channelDetails6 = new ChannelDetails(SensorLSM303.ObjectClusterSensorName.MAG_Z, SensorLSM303.ObjectClusterSensorName.MAG_Z, DatabaseChannelHandles.MAG_Z, ChannelDetails.CHANNEL_DATA_TYPE.INT16, 2, ChannelDetails.CHANNEL_DATA_ENDIAN.MSB, "local_flux", (List<ChannelDetails.CHANNEL_TYPE>) Arrays.asList(ChannelDetails.CHANNEL_TYPE.CAL, ChannelDetails.CHANNEL_TYPE.UNCAL), 9);
        channelLSM303DLHCMagZ = channelDetails6;
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_X, channelDetails);
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Y, channelDetails2);
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.ACCEL_WR_Z, channelDetails3);
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.MAG_X, channelDetails4);
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.MAG_Z, channelDetails6);
        linkedHashMap5.put(SensorLSM303.ObjectClusterSensorName.MAG_Y, channelDetails5);
        mChannelMapRef = Collections.unmodifiableMap(linkedHashMap5);
        sensorGroupLsmAccel = new SensorGroupingDetails("Wide-Range Accelerometer", Arrays.asList(31), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
        sensorGroupLsmMag = new SensorGroupingDetails("Magnetometer", Arrays.asList(32), Configuration.Shimmer3.CompatibilityInfoForMaps.listOfCompatibleVersionInfoAnyExpBoardStandardFW);
    }

    private CalibDetailsKinematic calibDetailsAccelWr16g;
    private CalibDetailsKinematic calibDetailsAccelWr2g;
    private CalibDetailsKinematic calibDetailsAccelWr4g;
    private CalibDetailsKinematic calibDetailsAccelWr8g;
    private CalibDetailsKinematic calibDetailsMag1p3;
    private CalibDetailsKinematic calibDetailsMag1p9;
    private CalibDetailsKinematic calibDetailsMag2p5;
    private CalibDetailsKinematic calibDetailsMag4p0;
    private CalibDetailsKinematic calibDetailsMag4p7;
    private CalibDetailsKinematic calibDetailsMag5p6;
    private CalibDetailsKinematic calibDetailsMag8p1;

    public SensorLSM303DLHC() {
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
        Integer[] numArr2 = ListofLSM303DLHCMagRangeConfigValues;
        int iIntValue2 = numArr2[0].intValue();
        String[] strArr = ListofLSM303DLHCMagRange;
        String str2 = strArr[0];
        double[][] dArr4 = DefaultAlignmentMatrixMagShimmer3;
        double[][] dArr5 = DefaultSensitivityMatrixMag1p3GaShimmer3;
        double[][] dArr6 = DefaultOffsetVectorMagShimmer3;
        this.calibDetailsMag1p3 = new CalibDetailsKinematic(iIntValue2, str2, dArr4, dArr5, dArr6);
        this.calibDetailsMag1p9 = new CalibDetailsKinematic(numArr2[1].intValue(), strArr[1], dArr4, DefaultSensitivityMatrixMag1p9GaShimmer3, dArr6);
        this.calibDetailsMag2p5 = new CalibDetailsKinematic(numArr2[2].intValue(), strArr[2], dArr4, DefaultSensitivityMatrixMag2p5GaShimmer3, dArr6);
        this.calibDetailsMag4p0 = new CalibDetailsKinematic(numArr2[3].intValue(), strArr[3], dArr4, DefaultSensitivityMatrixMag4GaShimmer3, dArr6);
        this.calibDetailsMag4p7 = new CalibDetailsKinematic(numArr2[4].intValue(), strArr[4], dArr4, DefaultSensitivityMatrixMag4p7GaShimmer3, dArr6);
        this.calibDetailsMag5p6 = new CalibDetailsKinematic(numArr2[5].intValue(), strArr[5], dArr4, DefaultSensitivityMatrixMag5p6GaShimmer3, dArr6);
        this.calibDetailsMag8p1 = new CalibDetailsKinematic(numArr2[6].intValue(), strArr[6], dArr4, DefaultSensitivityMatrixMag8p1GaShimmer3, dArr6);
        initialise();
    }

    public SensorLSM303DLHC(ShimmerDevice shimmerDevice) {
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
        Integer[] numArr2 = ListofLSM303DLHCMagRangeConfigValues;
        int iIntValue2 = numArr2[0].intValue();
        String[] strArr = ListofLSM303DLHCMagRange;
        String str2 = strArr[0];
        double[][] dArr4 = DefaultAlignmentMatrixMagShimmer3;
        double[][] dArr5 = DefaultSensitivityMatrixMag1p3GaShimmer3;
        double[][] dArr6 = DefaultOffsetVectorMagShimmer3;
        this.calibDetailsMag1p3 = new CalibDetailsKinematic(iIntValue2, str2, dArr4, dArr5, dArr6);
        this.calibDetailsMag1p9 = new CalibDetailsKinematic(numArr2[1].intValue(), strArr[1], dArr4, DefaultSensitivityMatrixMag1p9GaShimmer3, dArr6);
        this.calibDetailsMag2p5 = new CalibDetailsKinematic(numArr2[2].intValue(), strArr[2], dArr4, DefaultSensitivityMatrixMag2p5GaShimmer3, dArr6);
        this.calibDetailsMag4p0 = new CalibDetailsKinematic(numArr2[3].intValue(), strArr[3], dArr4, DefaultSensitivityMatrixMag4GaShimmer3, dArr6);
        this.calibDetailsMag4p7 = new CalibDetailsKinematic(numArr2[4].intValue(), strArr[4], dArr4, DefaultSensitivityMatrixMag4p7GaShimmer3, dArr6);
        this.calibDetailsMag5p6 = new CalibDetailsKinematic(numArr2[5].intValue(), strArr[5], dArr4, DefaultSensitivityMatrixMag5p6GaShimmer3, dArr6);
        this.calibDetailsMag8p1 = new CalibDetailsKinematic(numArr2[6].intValue(), strArr[6], dArr4, DefaultSensitivityMatrixMag8p1GaShimmer3, dArr6);
        initialise();
    }

    public static int getAccelRateFromFreq(boolean z, double d, boolean z2) {
        if (!z) {
            return 0;
        }
        if (d <= 1.0d) {
            return 1;
        }
        if (d <= 10.0d || z2) {
            return 2;
        }
        if (d <= 25.0d) {
            return 3;
        }
        if (d <= 50.0d) {
            return 4;
        }
        if (d <= 100.0d) {
            return 5;
        }
        if (d <= 200.0d) {
            return 6;
        }
        return d <= 400.0d ? 7 : 9;
    }

    public static int getMagRateFromFreq(boolean z, double d, boolean z2) {
        if (!z || d <= 0.75d) {
            return 0;
        }
        if (d <= 1.0d) {
            return 1;
        }
        if (d <= 3.0d) {
            return 2;
        }
        if (d <= 7.5d) {
            return 3;
        }
        if (d <= 15.0d || z2) {
            return 4;
        }
        if (d <= 30.0d) {
            return 5;
        }
        return d <= 75.0d ? 6 : 7;
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
        if (this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen4()) {
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.WIDE_RANGE_ACCEL.ordinal()), sensorGroupLsmAccel);
            this.mSensorGroupingMap.put(Integer.valueOf(Configuration.Shimmer3.LABEL_SENSOR_TILE.MAG.ordinal()), sensorGroupLsmMag);
        }
        super.updateSensorGroupingMap();
    }

    @Override // com.shimmerresearch.sensors.AbstractSensor
    public ActionSetting setSettings(String str, Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
        return new ActionSetting(communication_type);
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
        this.mMagRange = ListofLSM303DLHCMagRangeConfigValues[0].intValue();
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
        setCalibrationMapPerSensor(31, treeMap);
        updateCurrentAccelWrCalibInUse();
        TreeMap<Integer, CalibDetails> treeMap2 = new TreeMap<>();
        treeMap2.put(Integer.valueOf(this.calibDetailsMag1p3.mRangeValue), this.calibDetailsMag1p3);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag1p9.mRangeValue), this.calibDetailsMag1p9);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag2p5.mRangeValue), this.calibDetailsMag2p5);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag4p0.mRangeValue), this.calibDetailsMag4p0);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag4p7.mRangeValue), this.calibDetailsMag4p7);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag5p6.mRangeValue), this.calibDetailsMag5p6);
        treeMap2.put(Integer.valueOf(this.calibDetailsMag8p1.mRangeValue), this.calibDetailsMag8p1);
        setCalibrationMapPerSensor(32, treeMap2);
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
    public void setLSM303MagRange(int i) {
        if (ArrayUtils.contains(ListofLSM303DLHCMagRangeConfigValues, Integer.valueOf(i))) {
            this.mMagRange = i;
            updateCurrentAccelWrCalibInUse();
        }
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public boolean checkLowPowerMag() {
        setLowPowerMag(getLSM303MagRate() <= 4);
        return isLowPowerMagEnabled();
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public int getAccelRateFromFreqForSensor(boolean z, double d, boolean z2) {
        return getAccelRateFromFreq(z, d, z2);
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public void setLSM303DigitalAccelRate(int i) {
        super.setLSM303DigitalAccelRateInternal(i);
        if (this.mLowPowerAccelWR && i == 8) {
            super.setLSM303DigitalAccelRateInternal(9);
        }
    }

    @Override // com.shimmerresearch.sensors.lsm303.SensorLSM303
    public int getMagRateFromFreqForSensor(boolean z, double d, boolean z2) {
        return getMagRateFromFreq(z, d, z2);
    }

    public static class DatabaseChannelHandles {
        public static final String MAG_X = "LSM303DLHC_MAG_X";
        public static final String MAG_Y = "LSM303DLHC_MAG_Y";
        public static final String MAG_Z = "LSM303DLHC_MAG_Z";
        public static final String WR_ACC_X = "LSM303DLHC_ACC_X";
        public static final String WR_ACC_Y = "LSM303DLHC_ACC_Y";
        public static final String WR_ACC_Z = "LSM303DLHC_ACC_Z";
    }

    public static final class DatabaseConfigHandle {
        public static final String MAG_CALIB_TIME = "LSM303DLHC_Mag_Calib_Time";
        public static final String MAG_RANGE = "LSM303DLHC_Mag_Range";
        public static final String MAG_RATE = "LSM303DLHC_Mag_Rate";
        public static final String WR_ACC_CALIB_TIME = "LSM303DLHC_Acc_Calib_Time";
        public static final String WR_ACC_HRM = "LSM303DLHC_Acc_HRM";
        public static final String WR_ACC_LPM = "LSM303DLHC_Acc_LPM";
        public static final String WR_ACC_RANGE = "LSM303DLHC_Acc_Range";
        public static final String WR_ACC_RATE = "LSM303DLHC_Acc_Rate";
        public static final String MAG_OFFSET_X = "LSM303DLHC_Mag_Offset_X";
        public static final String MAG_OFFSET_Y = "LSM303DLHC_Mag_Offset_Y";
        public static final String MAG_OFFSET_Z = "LSM303DLHC_Mag_Offset_Z";
        public static final String MAG_GAIN_X = "LSM303DLHC_Mag_Gain_X";
        public static final String MAG_GAIN_Y = "LSM303DLHC_Mag_Gain_Y";
        public static final String MAG_GAIN_Z = "LSM303DLHC_Mag_Gain_Z";
        public static final String MAG_ALIGN_XX = "LSM303DLHC_Mag_Align_XX";
        public static final String MAG_ALIGN_XY = "LSM303DLHC_Mag_Align_XY";
        public static final String MAG_ALIGN_XZ = "LSM303DLHC_Mag_Align_XZ";
        public static final String MAG_ALIGN_YX = "LSM303DLHC_Mag_Align_YX";
        public static final String MAG_ALIGN_YY = "LSM303DLHC_Mag_Align_YY";
        public static final String MAG_ALIGN_YZ = "LSM303DLHC_Mag_Align_YZ";
        public static final String MAG_ALIGN_ZX = "LSM303DLHC_Mag_Align_ZX";
        public static final String MAG_ALIGN_ZY = "LSM303DLHC_Mag_Align_ZY";
        public static final String MAG_ALIGN_ZZ = "LSM303DLHC_Mag_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_MAG = Arrays.asList(MAG_OFFSET_X, MAG_OFFSET_Y, MAG_OFFSET_Z, MAG_GAIN_X, MAG_GAIN_Y, MAG_GAIN_Z, MAG_ALIGN_XX, MAG_ALIGN_XY, MAG_ALIGN_XZ, MAG_ALIGN_YX, MAG_ALIGN_YY, MAG_ALIGN_YZ, MAG_ALIGN_ZX, MAG_ALIGN_ZY, MAG_ALIGN_ZZ);
        public static final String WR_ACC_OFFSET_X = "LSM303DLHC_Acc_Offset_X";
        public static final String WR_ACC_OFFSET_Y = "LSM303DLHC_Acc_Offset_Y";
        public static final String WR_ACC_OFFSET_Z = "LSM303DLHC_Acc_Offset_Z";
        public static final String WR_ACC_GAIN_X = "LSM303DLHC_Acc_Gain_X";
        public static final String WR_ACC_GAIN_Y = "LSM303DLHC_Acc_Gain_Y";
        public static final String WR_ACC_GAIN_Z = "LSM303DLHC_Acc_Gain_Z";
        public static final String WR_ACC_ALIGN_XX = "LSM303DLHC_Acc_Align_XX";
        public static final String WR_ACC_ALIGN_XY = "LSM303DLHC_Acc_Align_XY";
        public static final String WR_ACC_ALIGN_XZ = "LSM303DLHC_Acc_Align_XZ";
        public static final String WR_ACC_ALIGN_YX = "LSM303DLHC_Acc_Align_YX";
        public static final String WR_ACC_ALIGN_YY = "LSM303DLHC_Acc_Align_YY";
        public static final String WR_ACC_ALIGN_YZ = "LSM303DLHC_Acc_Align_YZ";
        public static final String WR_ACC_ALIGN_ZX = "LSM303DLHC_Acc_Align_ZX";
        public static final String WR_ACC_ALIGN_ZY = "LSM303DLHC_Acc_Align_ZY";
        public static final String WR_ACC_ALIGN_ZZ = "LSM303DLHC_Acc_Align_ZZ";
        public static final List<String> LIST_OF_CALIB_HANDLES_WR_ACCEL = Arrays.asList(WR_ACC_OFFSET_X, WR_ACC_OFFSET_Y, WR_ACC_OFFSET_Z, WR_ACC_GAIN_X, WR_ACC_GAIN_Y, WR_ACC_GAIN_Z, WR_ACC_ALIGN_XX, WR_ACC_ALIGN_XY, WR_ACC_ALIGN_XZ, WR_ACC_ALIGN_YX, WR_ACC_ALIGN_YY, WR_ACC_ALIGN_YZ, WR_ACC_ALIGN_ZX, WR_ACC_ALIGN_ZY, WR_ACC_ALIGN_ZZ);
    }
}
