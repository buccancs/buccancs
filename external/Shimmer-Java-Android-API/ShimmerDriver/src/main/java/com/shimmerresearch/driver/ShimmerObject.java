package com.shimmerresearch.driver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.gyroOnTheFlyCal.GyroOnTheFlyCalModule;
import com.shimmerresearch.algorithms.gyroOnTheFlyCal.OnTheFlyGyroOffsetCal;
import com.shimmerresearch.comms.wiredProtocol.UartComponentPropertyDetails;
import com.shimmerresearch.comms.wiredProtocol.UartPacketDetails.UART_COMPONENT_AND_PROPERTY;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer2;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.Configuration.Shimmer3.DerivedSensorsBitMask;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.UtilCalibration;
import com.shimmerresearch.driver.calibration.CalibDetails.CALIB_READ_SOURCE;
import com.shimmerresearch.driver.shimmer2r3.BluetoothModuleVersionDetails;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driver.shimmerGq.ConfigByteLayoutShimmerGq802154;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.SensorDetailsRef;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.ShimmerSDCardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilParseData;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID_SR_CODES;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails.EXG_SETTINGS;
import com.shimmerresearch.exgConfig.ExGConfigOption;
import com.shimmerresearch.exgConfig.ExGConfigBytesDetails.EXG_SETTING_OPTIONS;
import com.shimmerresearch.exgConfig.ExGConfigOptionDetails.EXG_CHIP_INDEX;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorADC;
import com.shimmerresearch.sensors.SensorBridgeAmp;
import com.shimmerresearch.sensors.SensorECGToHRFw;
import com.shimmerresearch.sensors.SensorEXG;
import com.shimmerresearch.sensors.SensorGSR;
import com.shimmerresearch.sensors.SensorPPG;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.AbstractSensor.SENSORS;
import com.shimmerresearch.sensors.SensorADC.MICROCONTROLLER_ADC_PROPERTIES;
import com.shimmerresearch.sensors.bmpX80.SensorBMP180;
import com.shimmerresearch.sensors.bmpX80.SensorBMP280;
import com.shimmerresearch.sensors.bmpX80.SensorBMP390;
import com.shimmerresearch.sensors.bmpX80.SensorBMPX80;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;
import com.shimmerresearch.sensors.kionix.SensorKionixKXRB52042;
import com.shimmerresearch.sensors.kionix.SensorKionixKXTC92050;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS2MDL;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import com.shimmerresearch.sensors.lis2dw12.SensorLIS2DW12;
import com.shimmerresearch.sensors.lsm303.SensorLSM303;
import com.shimmerresearch.sensors.lsm303.SensorLSM303AH;
import com.shimmerresearch.sensors.lsm303.SensorLSM303DLHC;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9150;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9250;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;
import com.shimmerresearch.sensors.shimmer2.SensorMMA736x;
import com.shimmerresearch.sensors.shimmer2.SensorShimmer2Gyro;
import com.shimmerresearch.sensors.shimmer2.SensorShimmer2Mag;
import com.shimmerresearch.algorithms.orientation.GradDes3DOrientation;
import com.shimmerresearch.algorithms.orientation.GradDes3DOrientation9DoF;
import com.shimmerresearch.algorithms.orientation.Orientation3DObject;

public abstract class ShimmerObject extends ShimmerDevice implements Serializable {

    public static final int SENSOR_ACCEL = 0x80;
    public static final int SENSOR_DACCEL = 0x1000;
    public static final int SENSOR_GYRO = 0x40;
    public static final int SENSOR_MAG = 0x20;
    public static final int SENSOR_ECG = 0x10;
    public static final int SENSOR_EMG = 0x08;
    public static final int SENSOR_EXG1_24BIT = 0x10;
    public static final int SENSOR_EXG2_24BIT = 0x08;
    public static final int SHIMMER3_SENSOR_ECG = SENSOR_EXG1_24BIT + SENSOR_EXG2_24BIT;
    public static final int SHIMMER3_SENSOR_EMG = SENSOR_EXG1_24BIT;
    public static final int SENSOR_GSR = 0x04;
    public static final int SENSOR_EXP_BOARD_A7 = 0x02;
    public static final int SENSOR_EXP_BOARD_A0 = 0x01;
    public static final int SENSOR_EXP_BOARD = SENSOR_EXP_BOARD_A7 + SENSOR_EXP_BOARD_A0;
    public static final int SENSOR_BRIDGE_AMP = 0x8000;
    public static final int SENSOR_HEART = 0x4000;
    public static final int SENSOR_BATT = 0x2000;
    public static final int SENSOR_EXT_ADC_A7 = 0x02;
    public static final int SENSOR_EXT_ADC_A6 = 0x01;
    public static final int SENSOR_EXT_ADC_A15 = 0x0800;
    public static final int SENSOR_INT_ADC_A1 = 0x0400;
    public static final int SENSOR_INT_ADC_A12 = 0x0200;
    public static final int SENSOR_INT_ADC_A13 = 0x0100;
    public static final int SENSOR_INT_ADC_A14 = 0x800000;
    public static final int SENSOR_ALL_ADC_SHIMMER3 = SENSOR_INT_ADC_A14 | SENSOR_INT_ADC_A13 | SENSOR_INT_ADC_A12 | SENSOR_INT_ADC_A1 | SENSOR_EXT_ADC_A7 | SENSOR_EXT_ADC_A6 | SENSOR_EXT_ADC_A15;
    public static final int SENSOR_BMPX80 = 0x40000;
    public static final int SENSOR_EXG1_16BIT = 0x100000;
    public static final int SENSOR_EXG2_16BIT = 0x080000;
    public static final int SENSOR_ALT_ACCEL = 0x400000;
    public static final int SENSOR_ALT_MAG = 0X200000;
    public static final byte DATA_PACKET = (byte) 0x00;
    public static final byte INQUIRY_COMMAND = (byte) 0x01;
    public static final byte INQUIRY_RESPONSE = (byte) 0x02;
    public static final byte GET_SAMPLING_RATE_COMMAND = (byte) 0x03;
    public static final byte SAMPLING_RATE_RESPONSE = (byte) 0x04;
    public static final byte SET_SAMPLING_RATE_COMMAND = (byte) 0x05;
    public static final byte TOGGLE_LED_COMMAND = (byte) 0x06;
    public static final byte START_STREAMING_COMMAND = (byte) 0x07;
    public static final byte SET_SENSORS_COMMAND = (byte) 0x08;
    public static final byte SET_ACCEL_SENSITIVITY_COMMAND = (byte) 0x09;
    public static final byte ACCEL_SENSITIVITY_RESPONSE = (byte) 0x0A;
    public static final byte GET_ACCEL_SENSITIVITY_COMMAND = (byte) 0x0B;
    public static final byte SET_5V_REGULATOR_COMMAND = (byte) 0x0C;
    public static final byte SET_PMUX_COMMAND = (byte) 0x0D;
    public static final byte SET_CONFIG_BYTE0_COMMAND = (byte) 0x0E;
    public static final byte CONFIG_BYTE0_RESPONSE = (byte) 0x0F;
    public static final byte GET_CONFIG_BYTE0_COMMAND = (byte) 0x10;
    public static final byte STOP_STREAMING_COMMAND = (byte) 0x20;
    public static final byte SET_ACCEL_CALIBRATION_COMMAND = (byte) 0x11;
    public static final byte ACCEL_CALIBRATION_RESPONSE = (byte) 0x12;
    public static final byte SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND = (byte) 0x1A;
    public static final byte LSM303DLHC_ACCEL_CALIBRATION_RESPONSE = (byte) 0x1B;
    public static final byte GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND = (byte) 0x1C;
    public static final byte GET_ACCEL_CALIBRATION_COMMAND = (byte) 0x13;
    public static final byte SET_GYRO_CALIBRATION_COMMAND = (byte) 0x14;
    public static final byte GYRO_CALIBRATION_RESPONSE = (byte) 0x15;
    public static final byte GET_GYRO_CALIBRATION_COMMAND = (byte) 0x16;
    public static final byte SET_MAG_CALIBRATION_COMMAND = (byte) 0x17;
    public static final byte MAG_CALIBRATION_RESPONSE = (byte) 0x18;
    public static final byte GET_MAG_CALIBRATION_COMMAND = (byte) 0x19;
    public static final byte SET_GSR_RANGE_COMMAND = (byte) 0x21;
    public static final byte GSR_RANGE_RESPONSE = (byte) 0x22;
    public static final byte GET_GSR_RANGE_COMMAND = (byte) 0x23;
    public static final byte GET_SHIMMER_VERSION_COMMAND = (byte) 0x24;
    public static final byte GET_SHIMMER_VERSION_COMMAND_NEW = (byte) 0x3F;
    public static final byte GET_SHIMMER_VERSION_RESPONSE = (byte) 0x25;
    public static final byte SET_EMG_CALIBRATION_COMMAND = (byte) 0x26;
    public static final byte EMG_CALIBRATION_RESPONSE = (byte) 0x27;
    public static final byte GET_EMG_CALIBRATION_COMMAND = (byte) 0x28;
    public static final byte SET_ECG_CALIBRATION_COMMAND = (byte) 0x29;
    public static final byte ECG_CALIBRATION_RESPONSE = (byte) 0x2A;
    public static final byte GET_ECG_CALIBRATION_COMMAND = (byte) 0x2B;
    public static final byte GET_ALL_CALIBRATION_COMMAND = (byte) 0x2C;
    public static final byte ALL_CALIBRATION_RESPONSE = (byte) 0x2D;
    public static final byte GET_FW_VERSION_COMMAND = (byte) 0x2E;
    public static final byte FW_VERSION_RESPONSE = (byte) 0x2F;
    public static final byte SET_BLINK_LED = (byte) 0x30;
    public static final byte BLINK_LED_RESPONSE = (byte) 0x31;
    public static final byte GET_BLINK_LED = (byte) 0x32;
    public static final byte SET_GYRO_TEMP_VREF_COMMAND = (byte) 0x33;
    public static final byte SET_BUFFER_SIZE_COMMAND = (byte) 0x34;
    public static final byte BUFFER_SIZE_RESPONSE = (byte) 0x35;
    public static final byte GET_BUFFER_SIZE_COMMAND = (byte) 0x36;
    public static final byte SET_MAG_GAIN_COMMAND = (byte) 0x37;
    public static final byte MAG_GAIN_RESPONSE = (byte) 0x38;
    public static final byte GET_MAG_GAIN_COMMAND = (byte) 0x39;
    public static final byte SET_MAG_SAMPLING_RATE_COMMAND = (byte) 0x3A;
    public static final byte MAG_SAMPLING_RATE_RESPONSE = (byte) 0x3B;
    public static final byte GET_MAG_SAMPLING_RATE_COMMAND = (byte) 0x3C;
    public static final byte SET_ACCEL_SAMPLING_RATE_COMMAND = (byte) 0x40;
    public static final byte ACCEL_SAMPLING_RATE_RESPONSE = (byte) 0x41;
    public static final byte GET_ACCEL_SAMPLING_RATE_COMMAND = (byte) 0x42;
    public static final byte SET_LSM303DLHC_ACCEL_LPMODE_COMMAND = (byte) 0x43;
    public static final byte LSM303DLHC_ACCEL_LPMODE_RESPONSE = (byte) 0x44;
    public static final byte GET_LSM303DLHC_ACCEL_LPMODE_COMMAND = (byte) 0x45;
    public static final byte SET_LSM303DLHC_ACCEL_HRMODE_COMMAND = (byte) 0x46;
    public static final byte LSM303DLHC_ACCEL_HRMODE_RESPONSE = (byte) 0x47;
    public static final byte GET_LSM303DLHC_ACCEL_HRMODE_COMMAND = (byte) 0x48;
    public static final byte SET_MPU9150_GYRO_RANGE_COMMAND = (byte) 0x49;
    public static final byte MPU9150_GYRO_RANGE_RESPONSE = (byte) 0x4A;
    public static final byte GET_MPU9150_GYRO_RANGE_COMMAND = (byte) 0x4B;
    public static final byte SET_MPU9150_SAMPLING_RATE_COMMAND = (byte) 0x4C;
    public static final byte MPU9150_SAMPLING_RATE_RESPONSE = (byte) 0x4D;
    public static final byte GET_MPU9150_SAMPLING_RATE_COMMAND = (byte) 0x4E;
    public static final byte SET_BMP180_PRES_RESOLUTION_COMMAND = (byte) 0x52;
    public static final byte BMP180_PRES_RESOLUTION_RESPONSE = (byte) 0x53;
    public static final byte GET_BMP180_PRES_RESOLUTION_COMMAND = (byte) 0x54;
    ;
    public static final byte SET_BMP180_PRES_CALIBRATION_COMMAND = (byte) 0x55;
    public static final byte BMP180_PRES_CALIBRATION_RESPONSE = (byte) 0x56;
    public static final byte GET_BMP180_PRES_CALIBRATION_COMMAND = (byte) 0x57;
    public static final byte BMP180_CALIBRATION_COEFFICIENTS_RESPONSE = (byte) 0x58;
    public static final byte GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND = (byte) 0x59;
    public static final byte RESET_TO_DEFAULT_CONFIGURATION_COMMAND = (byte) 0x5A;
    public static final byte RESET_CALIBRATION_VALUE_COMMAND = (byte) 0x5B;
    public static final byte MPU9150_MAG_SENS_ADJ_VALS_RESPONSE = (byte) 0x5C;
    public static final byte GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND = (byte) 0x5D;
    public static final byte SET_INTERNAL_EXP_POWER_ENABLE_COMMAND = (byte) 0x5E;
    public static final byte INTERNAL_EXP_POWER_ENABLE_RESPONSE = (byte) 0x5F;
    public static final byte GET_INTERNAL_EXP_POWER_ENABLE_COMMAND = (byte) 0x60;
    public static final byte SET_EXG_REGS_COMMAND = (byte) 0x61;
    public static final byte EXG_REGS_RESPONSE = (byte) 0x62;
    public static final byte GET_EXG_REGS_COMMAND = (byte) 0x63;
    public static final byte DAUGHTER_CARD_ID_RESPONSE = (byte) 0x65;
    public static final byte GET_DAUGHTER_CARD_ID_COMMAND = (byte) 0x66;
    public static final byte SET_BAUD_RATE_COMMAND = (byte) 0x6A;
    public static final byte BAUD_RATE_RESPONSE = (byte) 0x6B;
    public static final byte GET_BAUD_RATE_COMMAND = (byte) 0x6C;
    public static final byte SET_DERIVED_CHANNEL_BYTES = (byte) 0x6D;
    public static final byte DERIVED_CHANNEL_BYTES_RESPONSE = (byte) 0x6E;
    public static final byte GET_DERIVED_CHANNEL_BYTES = (byte) 0x6F;
    public static final byte START_SDBT_COMMAND = (byte) 0x70;
    public static final byte STATUS_RESPONSE = (byte) 0x71;
    public static final byte GET_STATUS_COMMAND = (byte) 0x72;
    public static final byte SET_TRIAL_CONFIG_COMMAND = (byte) 0x73;
    public static final byte TRIAL_CONFIG_RESPONSE = (byte) 0x74;
    public static final byte GET_TRIAL_CONFIG_COMMAND = (byte) 0x75;
    public static final byte SET_CENTER_COMMAND = (byte) 0x76;
    public static final byte CENTER_RESPONSE = (byte) 0x77;
    public static final byte GET_CENTER_COMMAND = (byte) 0x78;
    public static final byte SET_SHIMMERNAME_COMMAND = (byte) 0x79;
    public static final byte SHIMMERNAME_RESPONSE = (byte) 0x7a;
    public static final byte GET_SHIMMERNAME_COMMAND = (byte) 0x7b;
    public static final byte SET_EXPID_COMMAND = (byte) 0x7c;
    public static final byte EXPID_RESPONSE = (byte) 0x7d;
    public static final byte GET_EXPID_COMMAND = (byte) 0x7e;
    public static final byte SET_MYID_COMMAND = (byte) 0x7F;
    public static final byte MYID_RESPONSE = (byte) 0x80;
    public static final byte GET_MYID_COMMAND = (byte) 0x81;
    public static final byte SET_NSHIMMER_COMMAND = (byte) 0x82;
    public static final byte NSHIMMER_RESPONSE = (byte) 0x83;
    public static final byte GET_NSHIMMER_COMMAND = (byte) 0x84;
    public static final byte SET_CONFIGTIME_COMMAND = (byte) 0x85;
    public static final byte CONFIGTIME_RESPONSE = (byte) 0x86;
    public static final byte GET_CONFIGTIME_COMMAND = (byte) 0x87;
    public static final byte DIR_RESPONSE = (byte) 0x88;
    public static final byte GET_DIR_COMMAND = (byte) 0x89;
    public static final byte INSTREAM_CMD_RESPONSE = (byte) 0x8A;
    public static final byte SET_INFOMEM_COMMAND = (byte) 0x8C;
    public static final byte INFOMEM_RESPONSE = (byte) 0x8D;
    public static final byte GET_INFOMEM_COMMAND = (byte) 0x8E;
    public static final byte SET_CRC_COMMAND = (byte) 0x8B;
    public static final byte SET_RWC_COMMAND = (byte) 0x8F;
    public static final byte RWC_RESPONSE = (byte) 0x90;
    public static final byte GET_RWC_COMMAND = (byte) 0x91;
    public static final byte ROUTINE_COMMUNICATION = (byte) 0xE0;
    public static final byte ACK_COMMAND_PROCESSED = (byte) 0xFF;
    public static final byte START_LOGGING_ONLY_COMMAND = (byte) 0x92;
    public static final byte STOP_LOGGING_ONLY_COMMAND = (byte) 0x93;
    public static final byte VBATT_RESPONSE = (byte) 0x94;
    public static final byte GET_VBATT_COMMAND = (byte) 0x95;
    public static final byte TEST_CONNECTION_COMMAND = (byte) 0x96;
    public static final byte STOP_SDBT_COMMAND = (byte) 0x97;
    public static final byte GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND = (byte) 0xA0;//(byte) InstructionsGet.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND_VALUE;
    public static final byte BMP280_CALIBRATION_COEFFICIENTS_RESPONSE = (byte) 0x9F;//(byte) InstructionsResponse.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE_VALUE;
    public static final byte GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND = (byte) 0xA7;
    public static final byte PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE = (byte) 0xA6;
    public static final byte SET_PRESSURE_OVERSAMPLING_RATIO_COMMAND = (byte) 0x52;
    public static final byte PRESSURE_OVERSAMPLING_RATIO_RESPONSE = (byte) 0x53;
    public static final byte GET_PRESSURE_OVERSAMPLING_RATIO_COMMAND = (byte) 0x54;
    public static final byte SET_PRESSURE_SAMPLING_RATE_COMMAND = (byte) 0xB5;
    public static final byte PRESSURE_SAMPLING_RATE_RESPONSE = (byte) 0xB6;
    public static final byte GET_PRESSURE_SAMPLING_RATE_COMMAND = (byte) 0xB7;
    public static final byte SET_CALIB_DUMP_COMMAND = (byte) 0x98;
    public static final byte RSP_CALIB_DUMP_COMMAND = (byte) 0x99;
    public static final byte GET_CALIB_DUMP_COMMAND = (byte) 0x9A;
    public static final byte UPD_CALIB_DUMP_COMMAND = (byte) 0x9B;
    public static final byte UPD_SDLOG_CFG_COMMAND = (byte) 0x9C;
    public static final byte GET_BT_FW_VERSION_STR_COMMAND = (byte) 0xA1;
    public static final byte BT_FW_VERSION_STR_RESPONSE = (byte) 0xA2;
    public static final byte SET_TEST = (byte) 0xA8;
    public static final int MAX_NUMBER_OF_SIGNALS = 77;
    public static final int MAX_INQUIRY_PACKET_SIZE = 47;
    public static final Map<String, TEST_MODE> mMapOfBluetoothDeviceTest;
    private static final long serialVersionUID = -1364568867018921219L;

    static {
        Map<String, TEST_MODE> aMap = new LinkedHashMap<String, TEST_MODE>();

        aMap.put(TEST_MODE.MAIN_TEST.getDescription(), TEST_MODE.MAIN_TEST);
        aMap.put(TEST_MODE.LED_TEST.getDescription(), TEST_MODE.LED_TEST);
        aMap.put(TEST_MODE.IC_TEST.getDescription(), TEST_MODE.IC_TEST);
        mMapOfBluetoothDeviceTest = Collections.unmodifiableMap(aMap);
    }

    public BiMap<String, String> mSensorBitmaptoName;
    public int mNChannels = 0;
    public int mRTCSetByBT = 1;
    public int OFFSET_LENGTH = 9;
    public SensorBMPX80 mSensorBMPX80 = new SensorBMP180(this);
    protected Map<String, ChannelDetails> mChannelMap = new LinkedHashMap<String, ChannelDetails>();
    protected int mBluetoothBaudRate = 9;

    protected int mPacketSize = 0;
    protected long mConfigByte0;
    protected int mBufferSize;
    protected String[] mSignalNameArray = new String[MAX_NUMBER_OF_SIGNALS];
    protected String[] mSignalDataTypeArray = new String[MAX_NUMBER_OF_SIGNALS];
    protected int mButtonStart = 0;
    protected int mDisableBluetooth = 0;
    protected int mMasterShimmer = 0;
    protected int mSingleTouch = 0;
    protected int mTCXO = 0;
    protected int mSyncWhenLogging = 0;
    protected int mSyncBroadcastInterval = 0;
    protected String mCenter = "";
    protected int mTrialId = 0;
    protected int mTrialNumberOfShimmers = 0;
    protected int mTrialDurationEstimatedInSecs = 0;
    protected int mTrialDurationMaximumInSecs = 0;
    protected String mMyBluetoothAddress = "";
    protected String mMacIdFromInfoMem = "";
    protected BluetoothModuleVersionDetails bluetoothModuleVersionDetails = new BluetoothModuleVersionDetails();
    protected List<String> syncNodesList = new ArrayList<String>();
    protected int mPpgAdcSelectionGsrBoard = 0;
    protected int mPpg1AdcSelectionProto3DeluxeBoard = 0;
    protected int mPpg2AdcSelectionProto3DeluxeBoard = 0;
    protected int mCurrentLEDStatus = 0;
    protected DescriptiveStatistics mVSenseBattMA = new DescriptiveStatistics(1024);

    protected double mDefaultOnTheFlyGyroCalibThreshold = 1.2;
    protected boolean mEnableCalibration = true;
    protected boolean mConfigFileCreationFlag = true;
    protected boolean mShimmerUsingConfigFromInfoMem = false;
    protected boolean mUseInfoMemConfigMethod = true;
    protected boolean mUseArraysDataStructureInObjectCluster = false;
    protected byte[] mInquiryResponseBytes;
    protected String mDirectoryName;
    protected int mGqPacketNumHeaderBytes = 0;
    protected int mSamplingDividerVBatt = 0;
    protected int mSamplingDividerGsr = 0;
    protected int mSamplingDividerPpg = 0;
    protected int mSamplingDividerLsm303dlhcAccel = 0;
    protected int mSamplingDividerBeacon = 0;
    protected double mLastReceivedTimeStampTicksUnwrapped = 0;
    protected double mCurrentTimeStampCycle = 0;
    protected long mInitialTimeStampTicksSd = 0;
    @Deprecated
    protected double mLastReceivedCalibratedTimeStamp = -1;
    protected boolean mStreamingStartTimeSaved = false;
    protected double mStreamingStartTimeMilliSecs;
    protected int mTimeStampPacketByteSize = 2;
    protected int mTimeStampTicksMaxValue = 65536;
    protected long mRTCDifferenceInTicks = 0;
    protected boolean mFirstTime = true;
    protected double mFirstTsOffsetFromInitialTsTicks = 0;
    protected SensorMMA736x mSensorMMA736x = new SensorMMA736x(this);
    protected SensorShimmer2Mag mSensorShimmer2Mag = new SensorShimmer2Mag(this);
    protected SensorShimmer2Gyro mSensorShimmer2Gyro = new SensorShimmer2Gyro(this);
    protected SensorMPU9X50 mSensorMpu9x50 = new SensorMPU9150(this);
    protected SensorADXL371 mSensorADXL371 = new SensorADXL371(this);
    protected double OffsetECGRALL = 2060;
    protected double GainECGRALL = 175;
    protected double OffsetECGLALL = 2060;
    protected double GainECGLALL = 175;
    protected double OffsetEMG = 2060;
    protected double GainEMG = 750;
    protected int mExGResolution = 1;
    protected boolean mIsExg1_24bitEnabled = false;
    protected boolean mIsExg2_24bitEnabled = false;
    protected boolean mIsExg1_16bitEnabled = false;
    protected boolean mIsExg2_16bitEnabled = false;
    protected byte[] mEMGCalRawParams = new byte[13];
    protected byte[] mECGCalRawParams = new byte[13];
    protected boolean mDefaultCalibrationParametersECG = true;
    protected boolean mDefaultCalibrationParametersEMG = true;
    protected ExGConfigBytesDetails mExGConfigBytesDetails = new ExGConfigBytesDetails();
    protected byte[] mEXG1RegisterArray = new byte[10];
    protected byte[] mEXG2RegisterArray = new byte[10];
    protected double exg1Ch1CalFactor24Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 23) - 1));
    protected double exg1Ch2CalFactor24Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 23) - 1));
    protected double exg2Ch1CalFactor24Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 23) - 1));
    protected double exg2Ch2CalFactor24Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 23) - 1));
    protected double exg1Ch1CalFactor16Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 15) - 1));
    protected double exg1Ch2CalFactor16Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 15) - 1));
    protected double exg2Ch1CalFactor16Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 15) - 1));
    protected double exg2Ch2CalFactor16Bit = (((2.42 * 1000) / 6) / (Math.pow(2, 15) - 1));
    protected int mGSRRange = 4;
    protected int mPastGSRRange = 4;
    protected int mPastGSRUncalibratedValue = 4;
    protected boolean mPastGSRFirstTime = true;
    Quat4d mQ = new Quat4d();
    transient GradDes3DOrientation mOrientationAlgo;
    private boolean isOverrideShowErrorLedsRtc = false;
    private int mShowErrorLedsRtc = 0;
    private boolean isOverrideShowErrorLedsSd = false;
    private int mShowErrorLedsSd = 0;
    private boolean mLowBattAutoStop = false;
    private double mLastKnownHeartRate = 0;
    private boolean mIsOrientationEnabled = false;
    @Deprecated
    private List<String[]> mExtraSignalProperties = null;
    private SensorKionixAccel mSensorKionixAccel = new SensorKionixKXRB52042(this);
    private SensorLSM303 mSensorLSM303 = new SensorLSM303DLHC(this);
    private SensorLIS2MDL mSensorLIS2MDL = new SensorLIS2MDL(this);
    private SensorLIS3MDL mSensorLIS3MDL = new SensorLIS3MDL(this);
    private SensorLIS2DW12 mSensorLIS2DW12 = new SensorLIS2DW12(this);
    private SensorLSM6DSV mSensorLSM6DSV = new SensorLSM6DSV(this);
    @Deprecated
    private int mEXG1RateSetting;
    @Deprecated
    private int mEXG1CH1GainSetting;
    @Deprecated
    private int mEXG1CH1GainValue;
    @Deprecated
    private int mEXG1CH2GainSetting;
    @Deprecated
    private int mEXG1CH2GainValue;
    @Deprecated
    private int mEXG2RateSetting;
    @Deprecated
    private int mEXG2CH1GainSetting;
    @Deprecated
    private int mEXG2CH1GainValue;
    @Deprecated
    private int mEXG2CH2PowerDown;
    @Deprecated
    private int mEXG2CH2GainSetting;
    @Deprecated
    private int mEXG2CH2GainValue;
    @Deprecated
    private int mEXGReferenceElectrode = -1;
    @Deprecated
    private int mLeadOffDetectionMode;
    @Deprecated
    private int mEXG1LeadOffCurrentMode;
    @Deprecated
    private int mEXG2LeadOffCurrentMode;
    @Deprecated
    private int mEXG1Comparators;
    @Deprecated
    private int mEXG2Comparators;
    @Deprecated
    private int mEXGRLDSense;
    @Deprecated
    private int mEXG1LeadOffSenseSelection;
    @Deprecated
    private int mEXG2LeadOffSenseSelection;
    @Deprecated
    private int mEXGLeadOffDetectionCurrent;
    @Deprecated
    private int mEXGLeadOffComparatorTreshold;
    @Deprecated
    private int mEXG2RespirationDetectState;
    @Deprecated
    private int mEXG2RespirationDetectFreq;
    @Deprecated
    private int mEXG2RespirationDetectPhase;

    @Deprecated
    public static BiMap<String, String> generateBiMapSensorIDtoSensorName(int shimmerVersion) {
        BiMap<String, String> sensorBitmaptoName = null;
        if (shimmerVersion != HW_ID.SHIMMER_2R) {
            final Map<String, String> tempSensorBMtoName = new HashMap<String, String>();
            tempSensorBMtoName.put(Integer.toString(SENSOR_GYRO), "Gyroscope");
            tempSensorBMtoName.put(Integer.toString(SENSOR_MAG), "Magnetometer");
            tempSensorBMtoName.put(Integer.toString(SENSOR_GSR), "GSR");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A7), "Exp Board A7");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A0), "Exp Board A0");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD), "Exp Board");
            tempSensorBMtoName.put(Integer.toString(SENSOR_BRIDGE_AMP), "Bridge Amplifier");
            tempSensorBMtoName.put(Integer.toString(SENSOR_HEART), "Heart Rate");
            tempSensorBMtoName.put(Integer.toString(SENSOR_BATT), "Battery Voltage");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A7), "External ADC A7");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A6), "External ADC A6");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A15), "External ADC A15");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A1), "Internal ADC A1");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A12), "Internal ADC A12");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A13), "Internal ADC A13");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A14), "Internal ADC A14");
            tempSensorBMtoName.put(Integer.toString(SENSOR_BMPX80), "Pressure");
            tempSensorBMtoName.put(Integer.toString(SENSOR_ACCEL), "Low Noise Accelerometer");
            tempSensorBMtoName.put(Integer.toString(SENSOR_DACCEL), "Wide Range Accelerometer");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXG1_24BIT), "EXG1");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXG2_24BIT), "EXG2");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXG1_16BIT), "EXG1 16Bit");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXG2_16BIT), "EXG2 16Bit");
            sensorBitmaptoName = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempSensorBMtoName));
        } else {
            final Map<String, String> tempSensorBMtoName = new HashMap<String, String>();
            tempSensorBMtoName.put(Integer.toString(SENSOR_ACCEL), "Accelerometer");
            tempSensorBMtoName.put(Integer.toString(SENSOR_GYRO), "Gyroscope");
            tempSensorBMtoName.put(Integer.toString(SENSOR_MAG), "Magnetometer");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EMG), "EMG");
            tempSensorBMtoName.put(Integer.toString(SENSOR_ECG), "ECG");
            tempSensorBMtoName.put(Integer.toString(SENSOR_GSR), "GSR");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A7), "Exp Board A7");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A0), "Exp Board A0");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD), "Exp Board");
            tempSensorBMtoName.put(Integer.toString(SENSOR_BRIDGE_AMP), "Bridge Amplifier");
            tempSensorBMtoName.put(Integer.toString(SENSOR_HEART), "Heart Rate");
            tempSensorBMtoName.put(Integer.toString(SENSOR_BATT), "Battery Voltage");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A7), "External ADC A7");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A6), "External ADC A6");
            tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A15), "External ADC A15");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A1), "Internal ADC A1");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A12), "Internal ADC A12");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A13), "Internal ADC A13");
            tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A14), "Internal ADC A14");
            sensorBitmaptoName = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempSensorBMtoName));
        }
        return sensorBitmaptoName;
    }

    @Deprecated
    public static String[] getListofSupportedSensors(int shimmerVersion) {
        String[] sensorNames = null;
        if (shimmerVersion == HW_ID.SHIMMER_2R || shimmerVersion == HW_ID.SHIMMER_2) {
            sensorNames = Configuration.Shimmer2.ListofCompatibleSensors;
        } else if (shimmerVersion == HW_ID.SHIMMER_3) {
            sensorNames = Configuration.Shimmer3.ListofCompatibleSensors;
        }
        return sensorNames;
    }

    @Deprecated
    public static String parseLeadOffComparatorTresholdToString(int treshold) {

        String tresholdString = "";
        switch (treshold) {
            case 0:
                tresholdString = "Pos:95% - Neg:5%";
                break;
            case 1:
                tresholdString = "Pos:92.5% - Neg:7.5%";
                break;
            case 2:
                tresholdString = "Pos:90% - Neg:10%";
                break;
            case 3:
                tresholdString = "Pos:87.5% - Neg:12.5%";
                break;
            case 4:
                tresholdString = "Pos:85% - Neg:15%";
                break;
            case 5:
                tresholdString = "Pos:80% - Neg:20%";
                break;
            case 6:
                tresholdString = "Pos:75% - Neg:25%";
                break;
            case 7:
                tresholdString = "Pos:70% - Neg:30%";
                break;
            default:
                tresholdString = "Treshold unread";
                break;
        }

        return tresholdString;
    }

    @Deprecated
    public static String parseLeadOffModeToString(int leadOffMode) {

        String modeString = "";
        switch (leadOffMode) {
            case 0:
                modeString += "Off";
                break;
            case 1:
                modeString += "DC Current";
                break;
            case 2:
                modeString += "AC Current";
                break;
            default:
                modeString += "No mode selected";
                break;
        }

        return modeString;
    }

    @Deprecated
    public static String parseLeadOffDetectionCurrentToString(int current) {

        String currentString = "";
        switch (current) {
            case 0:
                currentString += "6 nA";
                break;
            case 1:
                currentString += "22 nA";
                break;
            case 2:
                currentString += "6 uA";
                break;
            default:
                currentString += "22 uA";
                break;
        }

        return currentString;
    }


    @Deprecated
    public static List<String> getConfigColumnsShimmer3Legacy(ShimmerVerObject svo, ExpansionBoardDetails ebd) {
        List<String> configColumns = new ArrayList<String>();

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.SAMPLE_RATE);

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.ENABLE_SENSORS);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.DERIVED_SENSORS);

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RATE);
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_RANGE);
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_LPM);
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_HRM);
        } else {
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.WR_ACC_RATE);
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.WR_ACC_RANGE);
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.WR_ACC_LPM);
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.WR_ACC_HRM);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorMPU9250.DatabaseConfigHandle.GYRO_RATE);
        } else {
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.GYRO_RATE);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.MAG_RANGE);
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.MAG_RATE);
        } else {
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.MAG_RANGE);
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.MAG_RATE);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorMPU9250.DatabaseConfigHandle.GYRO_RANGE);
            configColumns.add(SensorMPU9250.DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE);
        } else {
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.GYRO_RANGE);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.ALTERNATIVE_ACC_RANGE);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedBmp280(svo, ebd)) {
            configColumns.add(SensorBMP280.DatabaseConfigHandle.PRESSURE_PRECISION_BMP280);
        } else {
            configColumns.add(SensorBMP180.DatabaseConfigHandle.PRESSURE_PRECISION_BMP180);
        }
        configColumns.add(SensorGSR.DatabaseConfigHandle.GSR_RANGE);

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.EXP_PWR);
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorMPU9250.DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE);
        } else {
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_DMP);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_LPF);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MOT_CAL_CFG);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MPL_SAMPLING_RATE);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MPL_SENSOR_FUSION);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MPL_GYRO_TC);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MPL_VECT_COMP);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MAG_DIST);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MPL_ENABLE);
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.MPU_MAG_SAMPLING_RATE);
        }

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.USER_BUTTON);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.RTC_SOURCE);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.MASTER_CONFIG);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.SINGLE_TOUCH_START);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.TXCO);

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.SHIMMER_VERSION);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.FW_VERSION);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.FW_VERSION_MAJOR);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.FW_VERSION_MINOR);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.FW_VERSION_INTERNAL);

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.CONFIG_TIME);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.REAL_TIME_CLOCK_DIFFERENCE);

        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_CONFIG_1);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_CONFIG_2);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_CH1_SET);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_CH2_SET);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_RLD_SENSE);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF_SENSE);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF_STATUS);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_RESPIRATION_1);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG1_RESPIRATION_2);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_CONFIG_1);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_CONFIG_2);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_CH1_SET);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_CH2_SET);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_RLD_SENSE);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF_SENSE);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF_STATUS);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_RESPIRATION_1);
        configColumns.add(SensorEXG.DatabaseConfigHandle.EXG2_RESPIRATION_2);

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL);
        } else {
            configColumns.addAll(SensorLSM303DLHC.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_WR_ACCEL);
        }

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorMPU9250.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO);
        } else {
            configColumns.addAll(SensorMPU9150.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_GYRO);
        }


        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorLSM303AH.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG);
        } else {
            configColumns.addAll(SensorLSM303DLHC.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MAG);
        }

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorKionixKXTC92050.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC);
        } else {
            configColumns.addAll(SensorKionixKXRB52042.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_LN_ACC);
        }

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorBMP280.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES);
        } else {
            configColumns.addAll(SensorBMP180.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES);
        }

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.addAll(SensorMPU9250.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_ACC);
            configColumns.addAll(SensorMPU9250.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MAG);
        } else {
            configColumns.addAll(SensorMPU9150.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_ACC);
            configColumns.addAll(SensorMPU9150.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_MAG);
            configColumns.addAll(SensorMPU9150.DatabaseConfigHandle.LIST_OF_CALIB_HANDLES_MPU_MPL_GYRO);
        }

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.INITIAL_TIMESTAMP);

        configColumns.add(ShimmerDevice.DatabaseConfigHandle.EXP_BOARD_ID);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.EXP_BOARD_REV);
        configColumns.add(ShimmerDevice.DatabaseConfigHandle.EXP_BOARD_REV_SPEC);

        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.WR_ACC_CALIB_TIME);
            configColumns.add(SensorLSM303AH.DatabaseConfigHandle.MAG_CALIB_TIME);
        } else {
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.WR_ACC_CALIB_TIME);
            configColumns.add(SensorLSM303DLHC.DatabaseConfigHandle.MAG_CALIB_TIME);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorMPU9250.DatabaseConfigHandle.GYRO_CALIB_TIME);
        } else {
            configColumns.add(SensorMPU9150.DatabaseConfigHandle.GYRO_CALIB_TIME);
        }
        if (svo != null && ebd != null && ShimmerObject.isSupportedNewImuSensors(svo, ebd)) {
            configColumns.add(SensorKionixKXTC92050.DatabaseConfigHandle.LN_ACC_CALIB_TIME);
        } else {
            configColumns.add(SensorKionixKXRB52042.DatabaseConfigHandle.LN_ACC_CALIB_TIME);
        }

        configColumns.add(DatabaseConfigHandleShimmerObject.SYNC_WHEN_LOGGING);
        configColumns.add(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_ESTIMATED);
        configColumns.add(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_MAXIMUM);

        return configColumns;
    }

    @Deprecated
    public static List<Double> getDbConfigValuesFromShimmerLegacy(ShimmerDevice shimmerDevice) {
        if (shimmerDevice instanceof ShimmerObject) {
            ShimmerObject shimmerObject = (ShimmerObject) shimmerDevice;
            List<Double> configValues = new ArrayList<Double>();
            configValues.add(shimmerObject.getSamplingRateShimmer());

            configValues.add((double) shimmerObject.getEnabledSensors());
            configValues.add((double) shimmerObject.getDerivedSensors());

            configValues.add((double) shimmerObject.getLSM303DigitalAccelRate());
            configValues.add((double) shimmerObject.getAccelRange());
            configValues.add((double) shimmerObject.getLowPowerAccelEnabled());
            configValues.add((double) shimmerObject.getHighResAccelWREnabled());
            configValues.add((double) shimmerObject.getMPU9X50GyroAccelRate());
            configValues.add((double) shimmerObject.getMagRange());
            configValues.add((double) shimmerObject.getMagRate());
            configValues.add((double) shimmerObject.getGyroRange());
            configValues.add((double) shimmerObject.getMPU9X50AccelRange());
            configValues.add((double) shimmerObject.getPressureResolution());
            configValues.add((double) shimmerObject.getGSRRange());
            configValues.add((double) shimmerObject.getInternalExpPower());

            if (!shimmerObject.isSupportedNewImuSensors()) {
                configValues.add((double) shimmerObject.getMPU9X50DMP());
                configValues.add((double) shimmerObject.getMPU9X50LPF());
                configValues.add((double) shimmerObject.getMPU9X50MotCalCfg());
                configValues.add((double) shimmerObject.getMPU9X50MPLSamplingRate());

                configValues.add((double) shimmerObject.getMPLSensorFusion());
                configValues.add((double) shimmerObject.getMPLGyroCalTC());
                configValues.add((double) shimmerObject.getMPLVectCompCal());
                configValues.add((double) shimmerObject.getMPLMagDistCal());
                configValues.add((double) shimmerObject.getMPLEnable());
            }
            configValues.add((double) shimmerObject.getMPU9X50MagSamplingRate());

            configValues.add((double) shimmerObject.getButtonStart());

            configValues.add((double) shimmerObject.getRTCSetByBT());

            configValues.add((double) shimmerObject.getMasterShimmer());
            configValues.add((double) shimmerObject.getSingleTouch());
            configValues.add((double) shimmerObject.getTCXO());

            configValues.add((double) shimmerObject.getHardwareVersion());
            configValues.add((double) shimmerObject.getFirmwareIdentifier());
            configValues.add((double) shimmerObject.getFirmwareVersionMajor());
            configValues.add((double) shimmerObject.getFirmwareVersionMinor());
            configValues.add((double) shimmerObject.getFirmwareVersionInternal());

            configValues.add((double) shimmerObject.getConfigTime());

            configValues.add((double) shimmerObject.getRTCDifferenceInTicks());

            byte[] exg1Array = shimmerObject.getEXG1RegisterArray();
            for (int i = 0; i < exg1Array.length; i++)
                configValues.add((double) (exg1Array[i] & 0xFF));

            byte[] exg2Array = shimmerObject.getEXG2RegisterArray();
            for (int i = 0; i < exg2Array.length; i++)
                configValues.add((double) (exg2Array[i] & 0xFF));

            double[][] offsetVectorWRAccel = shimmerObject.getOffsetVectorMatrixWRAccel();
            double[][] sensitivityMatrixWRAccel = shimmerObject.getSensitivityMatrixWRAccel();
            double[][] alignmentMatrixWRAccel = shimmerObject.getAlignmentMatrixWRAccel();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorWRAccel, sensitivityMatrixWRAccel, alignmentMatrixWRAccel);

            double[][] offsetVectorGyroscope = shimmerObject.getOffsetVectorMatrixGyro();
            double[][] sensitivityMatrixGyroscope = shimmerObject.getSensitivityMatrixGyro();
            double[][] alignmentMatrixGyroscope = shimmerObject.getAlignmentMatrixGyro();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorGyroscope, sensitivityMatrixGyroscope, alignmentMatrixGyroscope);

            double[][] offsetVectorMagnetometer = shimmerObject.getOffsetVectorMatrixMag();
            double[][] sensitivityMatrixMagnetometer = shimmerObject.getSensitivityMatrixMag();
            double[][] alignmentMatrixMagnetometer = shimmerObject.getAlignmentMatrixMag();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorMagnetometer, sensitivityMatrixMagnetometer, alignmentMatrixMagnetometer);

            double[][] offsetVectorAnalogAccel = shimmerObject.getOffsetVectorMatrixAccel();
            double[][] sensitivityMatrixAnalogAccel = shimmerObject.getSensitivityMatrixAccel();
            double[][] alignmentMatrixAnalogAccel = shimmerObject.getAlignmentMatrixAccel();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorAnalogAccel, sensitivityMatrixAnalogAccel, alignmentMatrixAnalogAccel);

            configValues.addAll(shimmerObject.mSensorBMPX80.getPressTempConfigValuesLegacy());

            double[][] offsetVectorMPLAccel = shimmerObject.getOffsetVectorMPLAccel();
            double[][] sensitivityMatrixMPLAccel = shimmerObject.getSensitivityMatrixMPLAccel();
            double[][] alignmentMatrixMPLAccel = shimmerObject.getAlignmentMatrixMPLAccel();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorMPLAccel, sensitivityMatrixMPLAccel, alignmentMatrixMPLAccel);

            double[][] offsetVectorMPLMag = shimmerObject.getOffsetVectorMPLMag();
            double[][] sensitivityMatrixMPLMag = shimmerObject.getSensitivityMatrixMPLMag();
            double[][] alignmentMatrixMPLMag = shimmerObject.getAlignmentMatrixMPLMag();
            addCalibKinematicToDbConfigValues(configValues, offsetVectorMPLMag, sensitivityMatrixMPLMag, alignmentMatrixMPLMag);

            if (!shimmerObject.isSupportedNewImuSensors()) {
                double[][] offsetVectorMPLGyro = shimmerObject.getOffsetVectorMPLGyro();
                double[][] sensitivityMatrixMPLGyro = shimmerObject.getSensitivityMatrixMPLGyro();
                double[][] alignmentMatrixMPLGyro = shimmerObject.getAlignmentMatrixMPLGyro();
                addCalibKinematicToDbConfigValues(configValues, offsetVectorMPLGyro, sensitivityMatrixMPLGyro, alignmentMatrixMPLGyro);
            }

            configValues.add((double) shimmerObject.getInitialTimeStampTicksSd());

            configValues.add((double) shimmerObject.getExpansionBoardId());
            configValues.add((double) shimmerObject.getExpansionBoardRev());
            configValues.add((double) shimmerObject.getExpansionBoardRevSpecial());

            configValues.add((double) shimmerObject.getCalibTimeWRAccel());
            configValues.add((double) shimmerObject.getCalibTimeMag());
            configValues.add((double) shimmerObject.getCalibTimeGyro());
            configValues.add((double) shimmerObject.getCalibTimeAccel());

            configValues.add((double) shimmerObject.getSyncWhenLogging());
            configValues.add((double) shimmerObject.getTrialDurationEstimatedInSecs());
            configValues.add((double) shimmerObject.getTrialDurationMaximumInSecs());

            if (shimmerObject.isShimmerGenGq()) {
                configValues.add((double) 0);
            }

            return configValues;
        }
        return null;
    }

    @Deprecated
    public static void addCalibKinematicToDbConfigValues(List<Double> configValues, double[][] offsetVector, double[][] sensitivityMatrix, double[][] alignmentMatrix) {
        configValues.add(offsetVector[0][0]);
        configValues.add(offsetVector[1][0]);
        configValues.add(offsetVector[2][0]);
        configValues.add(sensitivityMatrix[0][0]);
        configValues.add(sensitivityMatrix[1][1]);
        configValues.add(sensitivityMatrix[2][2]);
        configValues.add(alignmentMatrix[0][0]);
        configValues.add(alignmentMatrix[0][1]);
        configValues.add(alignmentMatrix[0][2]);
        configValues.add(alignmentMatrix[1][0]);
        configValues.add(alignmentMatrix[1][1]);
        configValues.add(alignmentMatrix[1][2]);
        configValues.add(alignmentMatrix[2][0]);
        configValues.add(alignmentMatrix[2][1]);
        configValues.add(alignmentMatrix[2][2]);
    }

    public static boolean isSupportedBmp280(ShimmerVerObject svo, ExpansionBoardDetails ebd) {
        return isSupportedNewImuSensors(svo, ebd);
    }

    public static boolean isShimmer3RwithHighGAccelSupport(ShimmerVerObject svo, ExpansionBoardDetails ebd) {

        if (svo == null || ebd == null) {
            return false;
        }

        int expBrdId = ebd.getExpansionBoardId();
        int expBrdRev = ebd.getExpansionBoardRev();
        int expBrdRevSpecial = ebd.getExpansionBoardRevSpecial();

        if ((svo.getHardwareVersion() == HW_ID.SHIMMER_3R) && (
                (expBrdId == HW_ID_SR_CODES.SHIMMER3)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_PROTO3_DELUXE && expBrdRev == 4 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_BR_AMP_UNIFIED && expBrdRev == 4 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 6 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 1)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 8 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 1)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 8 && expBrdRevSpecial == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isShimmer3RwithAltMagSupport(ShimmerVerObject svo, ExpansionBoardDetails ebd) {
        if (svo == null || ebd == null) {
            return false;
        }
        int expBrdId = ebd.getExpansionBoardId();
        int expBrdRev = ebd.getExpansionBoardRev();
        int expBrdRevSpecial = ebd.getExpansionBoardRevSpecial();

        if ((svo.getHardwareVersion() == HW_ID.SHIMMER_3R) && (
                (expBrdId == HW_ID_SR_CODES.SHIMMER3 && expBrdRev == 11 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_PROTO3_DELUXE && expBrdRev == 4 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_BR_AMP_UNIFIED && expBrdRev == 4 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 6 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 1)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev == 8 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 0)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 7 && expBrdRevSpecial == 1)
                        || (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 8 && expBrdRevSpecial == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSupportedNewImuSensors(ShimmerVerObject svo, ExpansionBoardDetails ebd) {
        if (svo == null || ebd == null) {
            return false;
        }

        int expBrdId = ebd.getExpansionBoardId();
        int expBrdRev = ebd.getExpansionBoardRev();
        int expBrdRevSpecial = ebd.getExpansionBoardRevSpecial();

        if ((svo.getHardwareVersion() == HW_ID.SHIMMER_3R) ||
                (svo.getHardwareVersion() == HW_ID.SHIMMER_3 && (
                        (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.EXG_UNIFIED)
                                || (expBrdId == HW_ID_SR_CODES.EXP_BRD_GSR_UNIFIED && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.GSR_UNIFIED)
                                || (expBrdId == HW_ID_SR_CODES.EXP_BRD_BR_AMP_UNIFIED && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.BRIDGE_AMP)
                                || (expBrdId == HW_ID_SR_CODES.SHIMMER3 && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.IMU)
                                || (expBrdRevSpecial == Configuration.Shimmer3.NEW_IMU_EXP_REV.ANY_EXP_BRD_WITH_SPECIAL_REV)
                                || (expBrdId == HW_ID_SR_CODES.EXP_BRD_PROTO3_DELUXE && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.PROTO3_DELUXE)
                                || (expBrdId == HW_ID_SR_CODES.EXP_BRD_PROTO3_MINI && expBrdRev >= Configuration.Shimmer3.NEW_IMU_EXP_REV.PROTO3_MINI)
                ))) {
            return true;
        } else {
            return false;
        }
    }

    protected abstract void checkBattery();

    public ObjectCluster setLSLTimeIfAvailable(ObjectCluster ojc) {
        return ojc;
    }

    @Override
    public ObjectCluster buildMsg(byte[] newPacket, COMMUNICATION_TYPE fwType, boolean isTimeSyncEnabled, double pcTimestampMs) {

        ObjectCluster objectCluster = new ObjectCluster();
        setLSLTimeIfAvailable(objectCluster);
        if (mUseArraysDataStructureInObjectCluster) {
            objectCluster.mEnableArraysDataStructure = true;
        }

        objectCluster.setShimmerName(mShimmerUserAssignedName);
        objectCluster.setMacAddress(mMyBluetoothAddress);
        objectCluster.mRawData = newPacket;

        if (fwType != COMMUNICATION_TYPE.BLUETOOTH && fwType != COMMUNICATION_TYPE.SD) {
            consolePrintErrLn("The Firmware is not compatible");
        }

        int numCalibratedData = mNChannels;
        int numUncalibratedData = mNChannels;
        int numUncalibratedDataUnits = mNChannels;
        int numCalibratedDataUnits = mNChannels;
        int numSensorNames = mNChannels;
        int numAdditionalChannels = 0;

        int indexAlgo = 0;

        if (fwType == COMMUNICATION_TYPE.BLUETOOTH) {
            objectCluster.setSystemTimeStamp(pcTimestampMs);
            numAdditionalChannels += 1;

            numAdditionalChannels += 4;

            numAdditionalChannels += 1;
        } else {
            if (fwType == COMMUNICATION_TYPE.SD && getHardwareVersion() == HW_ID.SHIMMER_3R) {
                numAdditionalChannels += 1;
            }

            if (!isRtcDifferenceSet()) {
            } else {
                numAdditionalChannels += 1;
            }
        }

        if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration()) {
            numAdditionalChannels += 1;
        }
        for (AbstractAlgorithm aA : getListOfEnabledAlgorithmModules()) {
            numAdditionalChannels += aA.getNumberOfEnabledChannels();
        }

        if (is3DOrientationEnabled()) {
            numAdditionalChannels += 8;
        }


        double[] calibratedData = new double[numCalibratedData + numAdditionalChannels];
        double[] uncalibratedData = new double[numUncalibratedData + numAdditionalChannels];
        String[] uncalibratedDataUnits = new String[numUncalibratedDataUnits + numAdditionalChannels];
        String[] calibratedDataUnits = new String[numCalibratedDataUnits + numAdditionalChannels];
        String[] sensorNames = new String[numSensorNames + numAdditionalChannels];

        double[] calDataAlgo = null;
        String[] calDataUnitsAlgo = null;
        String[] sensorNamesAlgo = null;

        if (is3DOrientationEnabled()) {
            calDataAlgo = new double[8];
            calDataUnitsAlgo = new String[8];
            sensorNamesAlgo = new String[8];
        }

        objectCluster.sensorDataArray = new SensorDataArray(numCalibratedData + numAdditionalChannels);


        System.arraycopy(mSignalNameArray, 0, sensorNames, 0, sensorNames.length);

        long[] newPacketInt = null;

        try {
            newPacketInt = UtilParseData.parseData(newPacket, mSignalDataTypeArray);
        } catch (IndexOutOfBoundsException e) {
            String debugString = "SignalDataTypeArray:";
            for (String s : mSignalDataTypeArray) {
                debugString += "\t" + s;
            }
            System.out.println(debugString);
            System.out.println("newPacket:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(newPacket));
            throw (e);
        }

        double[] tempData = new double[3];
        Vector3d accelerometer = new Vector3d();
        Vector3d magnetometer = new Vector3d();
        Vector3d gyroscope = new Vector3d();

        if (getHardwareVersion() == HW_ID.SHIMMER_SR30 || getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R
                || getHardwareVersion() == HW_ID.SHIMMER_GQ_802154_LR || getHardwareVersion() == HW_ID.SHIMMER_GQ_802154_NR || getHardwareVersion() == HW_ID.SHIMMER_2R_GQ) {

            parseTimestampShimmer3(fwType, objectCluster, uncalibratedData, uncalibratedDataUnits, calibratedData, calibratedDataUnits, sensorNames, newPacketInt);

            if (isTimeSyncEnabled && (fwType == COMMUNICATION_TYPE.SD)) {
                int iOffset = getSignalIndex(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET);
                double offsetValue = Double.NaN;
                if (OFFSET_LENGTH == 9) {
                    if (newPacketInt[iOffset] == 1152921504606846975L) {
                        offsetValue = Double.NaN;
                    } else {
                        offsetValue = (double) newPacketInt[iOffset];
                    }
                } else {
                    if (newPacketInt[iOffset] == 4294967295L) {
                        offsetValue = Double.NaN;
                    } else {
                        offsetValue = (double) newPacketInt[iOffset];
                    }
                }

                objectCluster.addDataToMap(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, offsetValue);
                objectCluster.addDataToMap(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, Double.NaN);
                uncalibratedData[iOffset] = offsetValue;
                calibratedData[iOffset] = Double.NaN;
                uncalibratedDataUnits[iOffset] = CHANNEL_UNITS.NO_UNITS;
                calibratedDataUnits[iOffset] = CHANNEL_UNITS.NO_UNITS;
            }

            objectCluster = callAdditionalServices(objectCluster);


            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.ACCEL_LN) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.ACCEL_LN) > 0)) {
                int iAccelX = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_LN_X);
                int iAccelY = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y);
                int iAccelZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z);
                tempData[0] = (double) newPacketInt[iAccelX];
                tempData[1] = (double) newPacketInt[iAccelY];
                tempData[2] = (double) newPacketInt[iAccelZ];

                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_X, CHANNEL_TYPE.UNCAL.toString().toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelZ]);
                uncalibratedData[iAccelX] = (double) newPacketInt[iAccelX];
                uncalibratedData[iAccelY] = (double) newPacketInt[iAccelY];
                uncalibratedData[iAccelZ] = (double) newPacketInt[iAccelZ];
                uncalibratedDataUnits[iAccelX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelZ] = CHANNEL_UNITS.NO_UNITS;


                if (mEnableCalibration) {
                    double[] accelCalibratedData;
                    accelCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsAccelLn());
                    calibratedData[iAccelX] = accelCalibratedData[0];
                    calibratedData[iAccelY] = accelCalibratedData[1];
                    calibratedData[iAccelZ] = accelCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[0], mSensorLSM6DSV.mIsUsingDefaultLNAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[1], mSensorLSM6DSV.mIsUsingDefaultLNAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[2], mSensorLSM6DSV.mIsUsingDefaultLNAccelParam);
                    } else if (isShimmerGen3()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[0], mSensorKionixAccel.mIsUsingDefaultLNAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[1], mSensorKionixAccel.mIsUsingDefaultLNAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, accelCalibratedData[2], mSensorKionixAccel.mIsUsingDefaultLNAccelParam);
                    }

                    calibratedDataUnits[iAccelX] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelY] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelZ] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    accelerometer.x = accelCalibratedData[0];
                    accelerometer.y = accelCalibratedData[1];
                    accelerometer.z = accelCalibratedData[2];
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.ACCEL_WR) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.ACCEL_WR) > 0)
            ) {
                int iAccelX = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_WR_X);
                int iAccelY = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y);
                int iAccelZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z);

                tempData[0] = (double) newPacketInt[iAccelX];
                tempData[1] = (double) newPacketInt[iAccelY];
                tempData[2] = (double) newPacketInt[iAccelZ];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelZ]);
                uncalibratedData[iAccelX] = (double) newPacketInt[iAccelX];
                uncalibratedData[iAccelY] = (double) newPacketInt[iAccelY];
                uncalibratedData[iAccelZ] = (double) newPacketInt[iAccelZ];
                uncalibratedDataUnits[iAccelX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelZ] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    double[] accelCalibratedData;
                    accelCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsAccelWr());
                    calibratedData[iAccelX] = accelCalibratedData[0];
                    calibratedData[iAccelY] = accelCalibratedData[1];
                    calibratedData[iAccelZ] = accelCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelX], mSensorLIS2DW12.isUsingDefaultWRAccelParam());
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelY], mSensorLIS2DW12.isUsingDefaultWRAccelParam());
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelZ], mSensorLIS2DW12.isUsingDefaultWRAccelParam());
                    } else if (isShimmerGen3()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelX], mSensorLSM303.isUsingDefaultWRAccelParam());
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelY], mSensorLSM303.isUsingDefaultWRAccelParam());
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, calibratedData[iAccelZ], mSensorLSM303.isUsingDefaultWRAccelParam());
                    }

                    calibratedDataUnits[iAccelX] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelY] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelZ] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    if (((mEnabledSensors & SENSOR_ACCEL) == 0)) {
                        accelerometer.x = accelCalibratedData[0];
                        accelerometer.y = accelCalibratedData[1];
                        accelerometer.z = accelCalibratedData[2];
                    }
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.ACCEL_ALT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.ACCEL_MPU) > 0)) {
                int iAccelX = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_X);
                int iAccelY = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Y);
                int iAccelZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Z);
                tempData[0] = (double) newPacketInt[iAccelX];
                tempData[1] = (double) newPacketInt[iAccelY];
                tempData[2] = (double) newPacketInt[iAccelZ];

                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_X, CHANNEL_TYPE.UNCAL.toString().toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelZ]);
                uncalibratedData[iAccelX] = (double) newPacketInt[iAccelX];
                uncalibratedData[iAccelY] = (double) newPacketInt[iAccelY];
                uncalibratedData[iAccelZ] = (double) newPacketInt[iAccelZ];
                uncalibratedDataUnits[iAccelX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelZ] = CHANNEL_UNITS.NO_UNITS;


                if (mEnableCalibration) {
                    double[] altAccelCalibratedData;
                    altAccelCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsAccelAlt());
                    calibratedData[iAccelX] = altAccelCalibratedData[0];
                    calibratedData[iAccelY] = altAccelCalibratedData[1];
                    calibratedData[iAccelZ] = altAccelCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, altAccelCalibratedData[0], mSensorADXL371.mIsUsingDefaultHighGAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, altAccelCalibratedData[1], mSensorADXL371.mIsUsingDefaultHighGAccelParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.ACCEL_CAL_UNIT, altAccelCalibratedData[2], mSensorADXL371.mIsUsingDefaultHighGAccelParam);
                    }

                    calibratedDataUnits[iAccelX] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelY] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    calibratedDataUnits[iAccelZ] = CHANNEL_UNITS.ACCEL_CAL_UNIT;
                    accelerometer.x = altAccelCalibratedData[0];
                    accelerometer.y = altAccelCalibratedData[1];
                    accelerometer.z = altAccelCalibratedData[2];
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.MAG_ALT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MAG_MPU) > 0)) {
                int iMagX = getSignalIndex(Shimmer3.ObjectClusterSensorName.ALT_MAG_X);
                int iMagY = getSignalIndex(Shimmer3.ObjectClusterSensorName.ALT_MAG_Y);
                int iMagZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.ALT_MAG_Z);
                tempData[0] = (double) newPacketInt[iMagX];
                tempData[1] = (double) newPacketInt[iMagY];
                tempData[2] = (double) newPacketInt[iMagZ];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagZ]);
                uncalibratedData[iMagX] = (double) newPacketInt[iMagX];
                uncalibratedData[iMagY] = (double) newPacketInt[iMagY];
                uncalibratedData[iMagZ] = (double) newPacketInt[iMagZ];
                uncalibratedDataUnits[iMagX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagZ] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    double[] altMagCalibratedData;
                    altMagCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsMagAlt());
                    calibratedData[iMagX] = altMagCalibratedData[0];
                    calibratedData[iMagY] = altMagCalibratedData[1];
                    calibratedData[iMagZ] = altMagCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, altMagCalibratedData[0], mSensorLIS3MDL.mIsUsingDefaultAltMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, altMagCalibratedData[1], mSensorLIS3MDL.mIsUsingDefaultAltMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ALT_MAG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, altMagCalibratedData[2], mSensorLIS3MDL.mIsUsingDefaultAltMagParam);
                    }
                    magnetometer.x = altMagCalibratedData[0];
                    magnetometer.y = altMagCalibratedData[1];
                    magnetometer.z = altMagCalibratedData[2];
                    calibratedDataUnits[iMagX] = CHANNEL_UNITS.MAG_CAL_UNIT;
                    calibratedDataUnits[iMagY] = CHANNEL_UNITS.MAG_CAL_UNIT;
                    calibratedDataUnits[iMagZ] = CHANNEL_UNITS.MAG_CAL_UNIT;
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.GYRO) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.GYRO) > 0)
            ) {
                int iGyroX = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_X);
                int iGyroY = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_Y);
                int iGyroZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_Z);
                tempData[0] = (double) newPacketInt[iGyroX];
                tempData[1] = (double) newPacketInt[iGyroY];
                tempData[2] = (double) newPacketInt[iGyroZ];


                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroZ]);
                uncalibratedData[iGyroX] = (double) newPacketInt[iGyroX];
                uncalibratedData[iGyroY] = (double) newPacketInt[iGyroY];
                uncalibratedData[iGyroZ] = (double) newPacketInt[iGyroZ];
                uncalibratedDataUnits[iGyroX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iGyroY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iGyroZ] = CHANNEL_UNITS.NO_UNITS;


                if (mEnableCalibration) {
                    double[] gyroCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsGyro());
                    calibratedData[iGyroX] = gyroCalibratedData[0];
                    calibratedData[iGyroY] = gyroCalibratedData[1];
                    calibratedData[iGyroZ] = gyroCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[0], mSensorLSM6DSV.mIsUsingDefaultGyroParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[1], mSensorLSM6DSV.mIsUsingDefaultGyroParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[2], mSensorLSM6DSV.mIsUsingDefaultGyroParam);
                    } else if (isShimmerGen3()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[0], mSensorMpu9x50.mIsUsingDefaultGyroParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[1], mSensorMpu9x50.mIsUsingDefaultGyroParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, gyroCalibratedData[2], mSensorMpu9x50.mIsUsingDefaultGyroParam);
                    }

                    gyroscope.x = gyroCalibratedData[0] * Math.PI / 180;
                    gyroscope.y = gyroCalibratedData[1] * Math.PI / 180;
                    gyroscope.z = gyroCalibratedData[2] * Math.PI / 180;
                    calibratedDataUnits[iGyroX] = CHANNEL_UNITS.GYRO_CAL_UNIT;
                    calibratedDataUnits[iGyroY] = CHANNEL_UNITS.GYRO_CAL_UNIT;
                    calibratedDataUnits[iGyroZ] = CHANNEL_UNITS.GYRO_CAL_UNIT;


                    if (isShimmerGen2() && isGyroOnTheFlyCalEnabled()) {
                        getOnTheFlyCalGyro().updateGyroOnTheFlyGyroOVCal(getCurrentCalibDetailsGyro(), gyroCalibratedData,
                                (double) newPacketInt[iGyroX], (double) newPacketInt[iGyroY], (double) newPacketInt[iGyroZ]);
                    }
                }

            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.MAG) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MAG) > 0)
            ) {
                int iMagX = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_X);
                int iMagY = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_Y);
                int iMagZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_Z);
                tempData[0] = (double) newPacketInt[iMagX];
                tempData[1] = (double) newPacketInt[iMagY];
                tempData[2] = (double) newPacketInt[iMagZ];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagZ]);
                uncalibratedData[iMagX] = (double) newPacketInt[iMagX];
                uncalibratedData[iMagY] = (double) newPacketInt[iMagY];
                uncalibratedData[iMagZ] = (double) newPacketInt[iMagZ];
                uncalibratedDataUnits[iMagX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagZ] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    double[] magCalibratedData;
                    magCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsMag());
                    calibratedData[iMagX] = magCalibratedData[0];
                    calibratedData[iMagY] = magCalibratedData[1];
                    calibratedData[iMagZ] = magCalibratedData[2];

                    if (isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[0], mSensorLIS2MDL.mIsUsingDefaultMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[1], mSensorLIS2MDL.mIsUsingDefaultMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[2], mSensorLIS2MDL.mIsUsingDefaultMagParam);
                    } else {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[0], mSensorLSM303.mIsUsingDefaultMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[1], mSensorLSM303.mIsUsingDefaultMagParam);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, magCalibratedData[2], mSensorLSM303.mIsUsingDefaultMagParam);
                    }
                    magnetometer.x = magCalibratedData[0];
                    magnetometer.y = magCalibratedData[1];
                    magnetometer.z = magCalibratedData[2];
                    calibratedDataUnits[iMagX] = CHANNEL_UNITS.MAG_CAL_UNIT;
                    calibratedDataUnits[iMagY] = CHANNEL_UNITS.MAG_CAL_UNIT;
                    calibratedDataUnits[iMagZ] = CHANNEL_UNITS.MAG_CAL_UNIT;

                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.BATTERY) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.BATTERY) > 0)
            ) {
                int iBatt = getSignalIndex(Shimmer3.ObjectClusterSensorName.BATTERY);
                tempData[0] = (double) newPacketInt[iBatt];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iBatt]);
                uncalibratedData[iBatt] = (double) newPacketInt[iBatt];
                uncalibratedDataUnits[iBatt] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iBatt] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1) * 2;
                    calibratedDataUnits[iBatt] = CHANNEL_UNITS.MILLIVOLTS;
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iBatt]);
                    mVSenseBattMA.addValue(calibratedData[iBatt]);
                    checkBattery();

                    mShimmerBattStatusDetails.calculateBattPercentage(calibratedData[iBatt] / 1000);
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXT_EXP_A7) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXT_EXP_A7) > 0)
            ) {
                int iA7;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA7 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_ADC_0);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_0, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA7]);
                } else {
                    iA7 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A7);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A7, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA7]);
                }
                tempData[0] = (double) newPacketInt[iA7];

                uncalibratedData[iA7] = (double) newPacketInt[iA7];
                uncalibratedDataUnits[iA7] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iA7] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    if (mShimmerVerObject.isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_0, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA7]);
                    } else {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A7, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA7]);
                    }

                    calibratedDataUnits[iA7] = CHANNEL_UNITS.MILLIVOLTS;

                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXT_EXP_A6) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXT_EXP_A6) > 0)
            ) {
                int iA6;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA6 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_ADC_1);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_1, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA6]);
                } else {
                    iA6 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A6);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A6, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA6]);
                }
                tempData[0] = (double) newPacketInt[iA6];

                uncalibratedData[iA6] = (double) newPacketInt[iA6];
                uncalibratedDataUnits[iA6] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iA6] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    if (mShimmerVerObject.isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_1, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA6]);
                    } else {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A6, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA6]);
                    }

                    calibratedDataUnits[iA6] = CHANNEL_UNITS.MILLIVOLTS;

                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXT_EXP_A15) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXT_EXP_A15) > 0)
            ) {
                int iA15;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA15 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_ADC_2);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_2, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA15]);
                } else {
                    iA15 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A15);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A15, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA15]);
                }
                tempData[0] = (double) newPacketInt[iA15];

                uncalibratedData[iA15] = (double) newPacketInt[iA15];
                uncalibratedDataUnits[iA15] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    calibratedData[iA15] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    if (mShimmerVerObject.isShimmerGen3R()) {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_ADC_2, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA15]);
                    } else {
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A15, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA15]);
                    }

                    calibratedDataUnits[iA15] = CHANNEL_UNITS.MILLIVOLTS;

                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.INT_EXP_A1) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.INT_EXP_A1) > 0)
            ) {
                int iA1;
                String sensorName;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_ADC_3);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_ADC_3;
                } else {
                    iA1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A1);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A1;
                }
                tempData[0] = (double) newPacketInt[iA1];

                if (isSupportedDerivedSensors()) {
                    if ((mDerivedSensors & DerivedSensorsBitMask.PPG2_1_14) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG2_A3;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG2_A1;
                        }
                    } else if ((mDerivedSensors & DerivedSensorsBitMask.RES_AMP) > 0) {
                        sensorName = Shimmer3.ObjectClusterSensorName.RESISTANCE_AMP;
                    } else if ((mDerivedSensors & DerivedSensorsBitMask.SKIN_TEMP) > 0) {
                        sensorName = Shimmer3.ObjectClusterSensorName.SKIN_TEMPERATURE_PROBE;
                    }

                }
                sensorNames[iA1] = sensorName;
                objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA1]);
                uncalibratedData[iA1] = (double) newPacketInt[iA1];
                uncalibratedDataUnits[iA1] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    if ((mDerivedSensors & DerivedSensorsBitMask.SKIN_TEMP) > 0) {
                        calibratedData[iA1] = SensorBridgeAmp.calibratePhillipsSkinTemperatureData(tempData[0]);
                        calibratedDataUnits[iA1] = CHANNEL_UNITS.DEGREES_CELSIUS;
                        objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_CELSIUS, calibratedData[iA1]);
                    } else {
                        calibratedData[iA1] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                        calibratedDataUnits[iA1] = CHANNEL_UNITS.MILLIVOLTS;
                        objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA1]);
                    }
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.INT_EXP_A12) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.INT_EXP_A12) > 0)
            ) {
                int iA12;
                String sensorName;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA12 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_ADC_0);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_ADC_0;
                } else {
                    iA12 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A12);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A12;
                }
                tempData[0] = (double) newPacketInt[iA12];
                if (isSupportedDerivedSensors()) {
                    if ((mDerivedSensors & DerivedSensorsBitMask.PPG_12_13) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG_A0;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG_A12;
                        }
                    } else if ((mDerivedSensors & DerivedSensorsBitMask.PPG1_12_13) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG1_A0;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG1_A12;
                        }
                    }

                }

                sensorNames[iA12] = sensorName;
                objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA12]);
                uncalibratedData[iA12] = (double) newPacketInt[iA12];
                uncalibratedDataUnits[iA12] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iA12] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA12]);
                    calibratedDataUnits[iA12] = CHANNEL_UNITS.MILLIVOLTS;

                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.INT_EXP_A13) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.INT_EXP_A13) > 0)
            ) {
                int iA13;
                String sensorName;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA13 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_ADC_1);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_ADC_1;
                } else {
                    iA13 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A13);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A13;
                }
                tempData[0] = (double) newPacketInt[iA13];
                if (isSupportedDerivedSensors()) {
                    if ((mDerivedSensors & DerivedSensorsBitMask.PPG_12_13) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG_A1;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG_A13;
                        }
                    } else if ((mDerivedSensors & DerivedSensorsBitMask.PPG1_12_13) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG1_A1;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG1_A13;
                        }
                    }

                }

                sensorNames[iA13] = sensorName;
                objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA13]);
                uncalibratedData[iA13] = (double) newPacketInt[iA13];
                uncalibratedDataUnits[iA13] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iA13] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA13]);
                    calibratedDataUnits[iA13] = CHANNEL_UNITS.MILLIVOLTS;
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.INT_EXP_A14) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.INT_EXP_A14) > 0)
            ) {
                int iA14;
                String sensorName;
                if (mShimmerVerObject.isShimmerGen3R()) {
                    iA14 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_ADC_2);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_ADC_2;
                } else {
                    iA14 = getSignalIndex(Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A14);
                    sensorName = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A14;
                }
                tempData[0] = (double) newPacketInt[iA14];
                if (isSupportedDerivedSensors()) {
                    if ((mDerivedSensors & DerivedSensorsBitMask.PPG2_1_14) > 0) {
                        if (isShimmerGen3R()) {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG2_A2;
                        } else {
                            sensorName = SensorPPG.ObjectClusterSensorName.PPG2_A14;
                        }
                    }
                }
                sensorNames[iA14] = sensorName;
                objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA14]);
                uncalibratedData[iA14] = (double) newPacketInt[iA14];
                uncalibratedDataUnits[iA14] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    calibratedData[iA14] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                    objectCluster.addDataToMap(sensorName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA14]);
                    calibratedDataUnits[iA14] = CHANNEL_UNITS.MILLIVOLTS;
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && ((mEnabledSensors & BTStream.ACCEL_LN) > 0 || (mEnabledSensors & BTStream.ACCEL_WR) > 0) && ((mEnabledSensors & BTStream.GYRO) > 0) && ((mEnabledSensors & BTStream.MAG) > 0) && is3DOrientationEnabled())
                    || ((fwType == COMMUNICATION_TYPE.SD) && ((mEnabledSensors & SDLogHeader.ACCEL_LN) > 0 || (mEnabledSensors & SDLogHeader.ACCEL_WR) > 0) && ((mEnabledSensors & SDLogHeader.GYRO) > 0) && ((mEnabledSensors & SDLogHeader.MAG) > 0) && is3DOrientationEnabled())) {

                if (mEnableCalibration) {
                    if (mOrientationAlgo == null) {
                        mOrientationAlgo = new GradDes3DOrientation((double) 1 / getSamplingRateShimmer());
                    }
                    Orientation3DObject q = mOrientationAlgo.update(accelerometer.x, accelerometer.y, accelerometer.z, gyroscope.x, gyroscope.y, gyroscope.z, magnetometer.x, magnetometer.y, magnetometer.z);
                    double theta, Rx, Ry, Rz, rho;

                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_A, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getTheta());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getAngleX());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getAngleY());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getAngleZ());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_W, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getQuaternionW());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getQuaternionX());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getQuaternionY());
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL, q.getQuaternionZ());

                    sensorNamesAlgo[indexAlgo] = Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_A;
                    sensorNamesAlgo[indexAlgo + 1] = Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_X;
                    sensorNamesAlgo[indexAlgo + 2] = Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Y;
                    sensorNamesAlgo[indexAlgo + 3] = Shimmer3.ObjectClusterSensorName.AXIS_ANGLE_9DOF_Z;
                    sensorNamesAlgo[indexAlgo + 4] = Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_W;
                    sensorNamesAlgo[indexAlgo + 5] = Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_X;
                    sensorNamesAlgo[indexAlgo + 6] = Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y;
                    sensorNamesAlgo[indexAlgo + 7] = Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z;

                    calDataUnitsAlgo[indexAlgo] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 1] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 2] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 3] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 4] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 5] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 6] = CHANNEL_UNITS.LOCAL;
                    calDataUnitsAlgo[indexAlgo + 7] = CHANNEL_UNITS.LOCAL;

                    calDataAlgo[indexAlgo] = q.getTheta();
                    calDataAlgo[indexAlgo + 1] = q.getAngleX();
                    calDataAlgo[indexAlgo + 2] = q.getAngleY();
                    calDataAlgo[indexAlgo + 3] = q.getAngleZ();
                    calDataAlgo[indexAlgo + 4] = q.getQuaternionW();
                    calDataAlgo[indexAlgo + 5] = q.getQuaternionX();
                    calDataAlgo[indexAlgo + 6] = q.getQuaternionY();
                    calDataAlgo[indexAlgo + 7] = q.getQuaternionZ();

                    indexAlgo += 7;
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG1_24BIT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG1_24BIT) > 0)) {
                int iexg1ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT);
                int iexg1ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT);
                int iexg1sta = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_STATUS);
                double exg1ch1 = (double) newPacketInt[iexg1ch1];
                double exg1ch2 = (double) newPacketInt[iexg1ch2];
                double exg1sta = (double) newPacketInt[iexg1sta];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_STATUS, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1sta);
                uncalibratedData[iexg1sta] = (double) newPacketInt[iexg1sta];
                uncalibratedData[iexg1ch1] = (double) newPacketInt[iexg1ch1];
                uncalibratedData[iexg1ch2] = (double) newPacketInt[iexg1ch2];
                uncalibratedDataUnits[iexg1sta] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg1ch1] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg1ch2] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    double calexg1ch1 = exg1ch1 * (((2.42 * 1000) / getExg1CH1GainValue()) / (Math.pow(2, 23) - 1));
                    double calexg1ch2 = exg1ch2 * (((2.42 * 1000) / getExg1CH2GainValue()) / (Math.pow(2, 23) - 1));
                    calibratedData[iexg1ch1] = calexg1ch1;
                    calibratedData[iexg1ch2] = calexg1ch2;
                    calibratedData[iexg1sta] = (double) newPacketInt[iexg1sta];
                    calibratedDataUnits[iexg1sta] = CHANNEL_UNITS.NO_UNITS;
                    calibratedDataUnits[iexg1ch1] = CHANNEL_UNITS.MILLIVOLTS;
                    calibratedDataUnits[iexg1ch2] = CHANNEL_UNITS.MILLIVOLTS;

                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_STATUS, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1sta);

                    if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration() || isEXGUsingDefaultECGGqConfiguration()) {

                        if (isShimmerGenGq()) {
                            sensorNames[iexg1ch1] = SensorEXG.ObjectClusterSensorName.ECG_GQ;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_GQ, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_GQ, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);

                            sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.ECG_LA_RL_24BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RL_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RL_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                        } else {
                            sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.ECG_LL_RA_24BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_RA_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_RA_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);

                            sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RA_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                        }
                    } else if (isEXGUsingDefaultEMGConfiguration()) {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EMG_CH1_24BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EMG_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    } else {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    }
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG2_24BIT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG2_24BIT) > 0)) {
                int iexg2ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT);
                int iexg2ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT);
                int iexg2sta = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_STATUS);
                double exg2ch1 = (double) newPacketInt[iexg2ch1];
                double exg2ch2 = (double) newPacketInt[iexg2ch2];
                double exg2sta = (double) newPacketInt[iexg2sta];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_STATUS, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2sta);
                uncalibratedData[iexg2sta] = (double) newPacketInt[iexg2sta];
                uncalibratedData[iexg2ch1] = (double) newPacketInt[iexg2ch1];
                uncalibratedData[iexg2ch2] = (double) newPacketInt[iexg2ch2];
                uncalibratedDataUnits[iexg2sta] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg2ch1] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg2ch2] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    double calexg2ch1 = exg2ch1 * (((2.42 * 1000) / getExg2CH1GainValue()) / (Math.pow(2, 23) - 1));
                    double calexg2ch2 = exg2ch2 * (((2.42 * 1000) / getExg2CH2GainValue()) / (Math.pow(2, 23) - 1));
                    calibratedData[iexg2ch1] = calexg2ch1;
                    calibratedData[iexg2ch2] = calexg2ch2;
                    calibratedData[iexg2sta] = (double) newPacketInt[iexg2sta];
                    calibratedDataUnits[iexg2sta] = CHANNEL_UNITS.NO_UNITS;
                    calibratedDataUnits[iexg2ch1] = CHANNEL_UNITS.MILLIVOLTS;
                    calibratedDataUnits[iexg2ch2] = CHANNEL_UNITS.MILLIVOLTS;

                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_STATUS, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2sta);

                    if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration()) {
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.ECG_VX_RL_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_VX_RL_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_VX_RL_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                        if (isEXGUsingDefaultRespirationConfiguration()) {
                            sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.ECG_RESP_24BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_RESP_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_RESP_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        }
                    } else if (isEXGUsingDefaultEMGConfiguration()) {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, 0);
                    } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    } else {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    }
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG1_16BIT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG1_16BIT) > 0)
            ) {
                int iexg1ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT);
                int iexg1ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT);
                int iexg1sta = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_STATUS);
                double exg1ch1 = (double) newPacketInt[iexg1ch1];
                double exg1ch2 = (double) newPacketInt[iexg1ch2];
                double exg1sta = (double) newPacketInt[iexg1sta];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_STATUS, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1sta);
                uncalibratedData[iexg1sta] = (double) newPacketInt[iexg1sta];
                uncalibratedData[iexg1ch1] = (double) newPacketInt[iexg1ch1];
                uncalibratedData[iexg1ch2] = (double) newPacketInt[iexg1ch2];
                uncalibratedDataUnits[iexg1sta] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg1ch1] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg1ch2] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    double calexg1ch1 = exg1ch1 * (((2.42 * 1000) / (getExg1CH1GainValue() * 2)) / (Math.pow(2, 15) - 1));
                    double calexg1ch2 = exg1ch2 * (((2.42 * 1000) / (getExg1CH2GainValue() * 2)) / (Math.pow(2, 15) - 1));
                    calibratedData[iexg1ch1] = calexg1ch1;
                    calibratedData[iexg1ch2] = calexg1ch2;
                    calibratedData[iexg1sta] = (double) newPacketInt[iexg1sta];
                    calibratedDataUnits[iexg1sta] = CHANNEL_UNITS.NO_UNITS;
                    calibratedDataUnits[iexg1ch1] = CHANNEL_UNITS.MILLIVOLTS;
                    calibratedDataUnits[iexg1ch2] = CHANNEL_UNITS.MILLIVOLTS;

                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_STATUS, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1sta);

                    if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration()) {

                        if (isShimmerGenGq()) {
                            sensorNames[iexg1ch1] = SensorEXG.ObjectClusterSensorName.ECG_GQ;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_GQ, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_GQ, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);

                            sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.ECG_LA_RL_16BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RL_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RL_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                        } else {
                            sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.ECG_LL_RA_16BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_RA_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_RA_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);

                            sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.ECG_LA_RA_16BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RA_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LA_RA_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                        }
                    } else if (isEXGUsingDefaultEMGConfiguration()) {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EMG_CH1_16BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EMG_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EMG_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP1_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    } else {
                        sensorNames[iexg1ch1] = Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT;
                        sensorNames[iexg1ch2] = Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg1ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg1ch2);
                    }
                }
            }
            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG2_16BIT) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG2_16BIT) > 0)
            ) {
                int iexg2ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT);
                int iexg2ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT);
                int iexg2sta = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG2_STATUS);
                double exg2ch1 = (double) newPacketInt[iexg2ch1];
                double exg2ch2 = (double) newPacketInt[iexg2ch2];
                double exg2sta = (double) newPacketInt[iexg2sta];
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_STATUS, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2sta);
                uncalibratedData[iexg2sta] = (double) newPacketInt[iexg2sta];
                uncalibratedData[iexg2ch1] = (double) newPacketInt[iexg2ch1];
                uncalibratedData[iexg2ch2] = (double) newPacketInt[iexg2ch2];
                uncalibratedDataUnits[iexg2sta] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg2ch1] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iexg2ch2] = CHANNEL_UNITS.NO_UNITS;

                if (mEnableCalibration) {
                    double calexg2ch1 = ((exg2ch1)) * (((2.42 * 1000) / (getExg2CH1GainValue() * 2)) / (Math.pow(2, 15) - 1));
                    double calexg2ch2 = ((exg2ch2)) * (((2.42 * 1000) / (getExg2CH2GainValue() * 2)) / (Math.pow(2, 15) - 1));
                    calibratedData[iexg2ch1] = calexg2ch1;
                    calibratedData[iexg2ch2] = calexg2ch2;
                    calibratedData[iexg2sta] = (double) newPacketInt[iexg2sta];
                    calibratedDataUnits[iexg2sta] = CHANNEL_UNITS.NO_UNITS;
                    calibratedDataUnits[iexg2ch1] = CHANNEL_UNITS.MILLIVOLTS;
                    calibratedDataUnits[iexg2ch2] = CHANNEL_UNITS.MILLIVOLTS;

                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_STATUS, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2sta);


                    if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration()) {
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.ECG_VX_RL_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_VX_RL_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_VX_RL_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                        if (isEXGUsingDefaultRespirationConfiguration()) {
                            sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.ECG_RESP_16BIT;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_RESP_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_RESP_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        }
                    } else if (isEXGUsingDefaultEMGConfiguration()) {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, 0);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, 0);
                    } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG_TEST_CHIP2_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    } else {
                        sensorNames[iexg2ch1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT;
                        sensorNames[iexg2ch2] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT;
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, exg2ch2);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch1);
                        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calexg2ch2);
                    }
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.BMP180) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.BMPX80) > 0)) {

                String signalNameBmpX80Temperature = SensorBMP180.ObjectClusterSensorName.TEMPERATURE_BMP180;
                String signalNameBmpX80Pressure = SensorBMP180.ObjectClusterSensorName.PRESSURE_BMP180;
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameBmpX80Temperature = SensorBMP390.ObjectClusterSensorName.TEMPERATURE_BMP390;
                    signalNameBmpX80Pressure = SensorBMP390.ObjectClusterSensorName.PRESSURE_BMP390;
                } else if (isSupportedBmp280()) {
                    signalNameBmpX80Temperature = SensorBMP280.ObjectClusterSensorName.TEMPERATURE_BMP280;
                    signalNameBmpX80Pressure = SensorBMP280.ObjectClusterSensorName.PRESSURE_BMP280;
                }

                int iUT = getSignalIndex(signalNameBmpX80Temperature);
                int iUP = getSignalIndex(signalNameBmpX80Pressure);
                double UT = (double) newPacketInt[iUT];
                double UP = (double) newPacketInt[iUP];

                objectCluster.addDataToMap(signalNameBmpX80Pressure, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, UP);
                objectCluster.addDataToMap(signalNameBmpX80Temperature, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, UT);
                uncalibratedData[iUT] = (double) newPacketInt[iUT];
                uncalibratedData[iUP] = (double) newPacketInt[iUP];
                uncalibratedDataUnits[iUT] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iUP] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    double[] bmp180caldata = mSensorBMPX80.calibratePressureSensorData(UP, UT);
                    objectCluster.addDataToMap(signalNameBmpX80Pressure, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.KPASCAL, bmp180caldata[0] / 1000);
                    objectCluster.addDataToMap(signalNameBmpX80Temperature, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_CELSIUS, bmp180caldata[1]);
                    calibratedData[iUT] = bmp180caldata[1];
                    calibratedData[iUP] = bmp180caldata[0] / 1000;
                    calibratedDataUnits[iUT] = CHANNEL_UNITS.DEGREES_CELSIUS;
                    calibratedDataUnits[iUP] = CHANNEL_UNITS.KPASCAL;
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.BRIDGE_AMP) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.BRIDGE_AMP) > 0)
            ) {
                int iBAHigh = getSignalIndex(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_HIGH);
                int iBALow = getSignalIndex(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_LOW);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iBAHigh]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iBALow]);
                uncalibratedData[iBAHigh] = (double) newPacketInt[iBAHigh];
                uncalibratedData[iBALow] = (double) newPacketInt[iBALow];
                uncalibratedDataUnits[iBAHigh] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iBALow] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iBAHigh];
                    tempData[1] = (double) newPacketInt[iBALow];
                    calibratedData[iBAHigh] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 60, 3, 551);
                    calibratedData[iBALow] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[1], 1950, 3, 183.7);
                    calibratedDataUnits[iBAHigh] = CHANNEL_UNITS.MILLIVOLTS;
                    calibratedDataUnits[iBALow] = CHANNEL_UNITS.MILLIVOLTS;
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iBAHigh]);
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iBALow]);
                }
            }

            if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.GSR) > 0)
                    || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.GSR) > 0)
            ) {
                String mainGsrSignalName = SensorGSR.ObjectClusterSensorName.GSR_RESISTANCE;
                if (isShimmerGenGq()) {
                    mainGsrSignalName = SensorGSR.ObjectClusterSensorName.GSR_GQ;
                }

                int iGSR = getSignalIndex(mainGsrSignalName);

                tempData[0] = (double) newPacketInt[iGSR];
                int gsrAdcValueUnCal = ((int) tempData[0] & 4095);

                int currentGSRRange = getGSRRange();
                if (getFirmwareIdentifier() == FW_ID.SDLOG && getFirmwareVersionMajor() == 0 && getFirmwareVersionMinor() == 9) {


                    if (currentGSRRange == 4) {
                        currentGSRRange = (49152 & (int) tempData[0]) >> 14;
                        if (mPastGSRFirstTime) {
                            mPastGSRRange = currentGSRRange;
                            mPastGSRFirstTime = false;
                        }
                        if (currentGSRRange != mPastGSRRange) {
                            if (Math.abs(mPastGSRUncalibratedValue - gsrAdcValueUnCal) < 300) {
                                currentGSRRange = mPastGSRRange;
                            } else {
                                mPastGSRRange = currentGSRRange;
                            }
                            mPastGSRUncalibratedValue = gsrAdcValueUnCal;
                        }
                    }
                } else {
                    if (currentGSRRange == 4) {
                        currentGSRRange = (49152 & (int) tempData[0]) >> 14;
                    }
                }


                if (mChannelMap.get(SensorGSR.ObjectClusterSensorName.GSR_RANGE) != null) {
                    objectCluster.addCalDataToMap(SensorGSR.channelGsrRange, currentGSRRange);
                    objectCluster.addUncalDataToMap(SensorGSR.channelGsrRange, currentGSRRange);
                }
                if (SensorGSR.sensorGsrRef.mListOfChannelsRef.contains(SensorGSR.channelGsrAdc.mObjectClusterName)) {
                    objectCluster.addUncalDataToMap(SensorGSR.channelGsrAdc, gsrAdcValueUnCal);
                    objectCluster.addCalDataToMap(SensorGSR.channelGsrAdc, SensorADC.calibrateMspAdcChannelToMillivolts(gsrAdcValueUnCal));
                }

                objectCluster.addDataToMap(mainGsrSignalName, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, gsrAdcValueUnCal);
                uncalibratedData[iGSR] = gsrAdcValueUnCal;
                uncalibratedDataUnits[iGSR] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    double gsrResistanceKOhms = 0.0;
                    double gsrConductanceUSiemens = 0.0;
                    if (currentGSRRange == 3 && gsrAdcValueUnCal < SensorGSR.GSR_UNCAL_LIMIT_RANGE3) {
                        gsrAdcValueUnCal = SensorGSR.GSR_UNCAL_LIMIT_RANGE3;
                    }
                    gsrResistanceKOhms = SensorGSR.calibrateGsrDataToKOhmsUsingAmplifierEq(gsrAdcValueUnCal, currentGSRRange, MICROCONTROLLER_ADC_PROPERTIES.SHIMMER2R3_3V0, SensorGSR.SHIMMER3_GSR_REF_RESISTORS_KOHMS);
                    gsrResistanceKOhms = SensorGSR.nudgeGsrResistance(gsrResistanceKOhms, getGSRRange(), SensorGSR.SHIMMER3_GSR_RESISTANCE_MIN_MAX_KOHMS);
                    gsrConductanceUSiemens = (1.0 / gsrResistanceKOhms) * 1000;

                    if (isShimmerGenGq()) {
                        calibratedData[iGSR] = gsrConductanceUSiemens;
                        calibratedDataUnits[iGSR] = CHANNEL_UNITS.U_SIEMENS;
                        objectCluster.addDataToMap(mainGsrSignalName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.U_SIEMENS, gsrConductanceUSiemens);
                    } else {
                        calibratedData[iGSR] = gsrResistanceKOhms;
                        calibratedDataUnits[iGSR] = CHANNEL_UNITS.KOHMS;
                        objectCluster.addDataToMap(mainGsrSignalName, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.KOHMS, gsrResistanceKOhms);

                        if (mChannelMap.get(SensorGSR.ObjectClusterSensorName.GSR_CONDUCTANCE) != null) {
                            objectCluster.addUncalDataToMap(SensorGSR.channelGsrMicroSiemens, gsrAdcValueUnCal);
                            objectCluster.addCalDataToMap(SensorGSR.channelGsrMicroSiemens, gsrConductanceUSiemens);

                        }
                    }
                }

            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.GYRO_MPU_MPL) > 0)) {
                int iGyroX = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_X);
                int iGyroY = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Y);
                int iGyroZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Z);
                calibratedData[iGyroX] = (double) newPacketInt[iGyroX] / Math.pow(2, 16);
                calibratedData[iGyroY] = (double) newPacketInt[iGyroY] / Math.pow(2, 16);
                calibratedData[iGyroZ] = (double) newPacketInt[iGyroZ] / Math.pow(2, 16);
                calibratedDataUnits[iGyroX] = CHANNEL_UNITS.GYRO_CAL_UNIT;
                calibratedDataUnits[iGyroY] = CHANNEL_UNITS.GYRO_CAL_UNIT;
                calibratedDataUnits[iGyroZ] = CHANNEL_UNITS.GYRO_CAL_UNIT;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, calibratedData[iGyroX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, calibratedData[iGyroY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GYRO_CAL_UNIT, calibratedData[iGyroZ]);
                uncalibratedData[iGyroX] = (double) newPacketInt[iGyroX];
                uncalibratedData[iGyroY] = (double) newPacketInt[iGyroY];
                uncalibratedData[iGyroZ] = (double) newPacketInt[iGyroZ];
                uncalibratedDataUnits[iGyroX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iGyroY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iGyroZ] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iGyroX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iGyroY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.GYRO_MPU_MPL_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iGyroZ]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.ACCEL_MPU_MPL) > 0)) {
                int iAccelX = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_X);
                int iAccelY = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Y);
                int iAccelZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Z);
                calibratedData[iAccelX] = (double) newPacketInt[iAccelX] / Math.pow(2, 16);
                calibratedData[iAccelY] = (double) newPacketInt[iAccelY] / Math.pow(2, 16);
                calibratedData[iAccelZ] = (double) newPacketInt[iAccelZ] / Math.pow(2, 16);
                calibratedDataUnits[iAccelX] = CHANNEL_UNITS.GRAVITY;
                calibratedDataUnits[iAccelY] = CHANNEL_UNITS.GRAVITY;
                calibratedDataUnits[iAccelZ] = CHANNEL_UNITS.GRAVITY;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GRAVITY, calibratedData[iAccelX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GRAVITY, calibratedData[iAccelY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.GRAVITY, calibratedData[iAccelZ]);
                uncalibratedData[iAccelX] = (double) newPacketInt[iAccelX];
                uncalibratedData[iAccelY] = (double) newPacketInt[iAccelX];
                uncalibratedData[iAccelZ] = (double) newPacketInt[iAccelX];
                uncalibratedDataUnits[iAccelX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iAccelZ] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iAccelX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iAccelY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ACCEL_MPU_MPL_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iAccelZ]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MAG_MPU_MPL) > 0)) {
                int iMagX = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_X);
                int iMagY = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Y);
                int iMagZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Z);
                calibratedData[iMagX] = (double) newPacketInt[iMagX] / Math.pow(2, 16);
                calibratedData[iMagY] = (double) newPacketInt[iMagY] / Math.pow(2, 16);
                calibratedData[iMagZ] = (double) newPacketInt[iMagZ] / Math.pow(2, 16);
                calibratedDataUnits[iMagX] = CHANNEL_UNITS.MAG_CAL_UNIT;
                calibratedDataUnits[iMagY] = CHANNEL_UNITS.MAG_CAL_UNIT;
                calibratedDataUnits[iMagZ] = CHANNEL_UNITS.MAG_CAL_UNIT;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, calibratedData[iMagX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, calibratedData[iMagY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MAG_CAL_UNIT, calibratedData[iMagZ]);
                uncalibratedData[iMagX] = (double) newPacketInt[iMagX];
                uncalibratedData[iMagY] = (double) newPacketInt[iMagY];
                uncalibratedData[iMagZ] = (double) newPacketInt[iMagZ];
                uncalibratedDataUnits[iMagX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iMagZ] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iMagX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iMagY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MAG_MPU_MPL_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iMagZ]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_QUAT_6DOF) > 0)) {
                int iQW = getSignalIndex(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_W);
                int iQX = getSignalIndex(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_X);
                int iQY = getSignalIndex(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Y);
                int iQZ = getSignalIndex(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Z);
                calibratedData[iQW] = (double) newPacketInt[iQW] / Math.pow(2, 30);
                calibratedData[iQX] = (double) newPacketInt[iQX] / Math.pow(2, 30);
                calibratedData[iQY] = (double) newPacketInt[iQY] / Math.pow(2, 30);
                calibratedData[iQZ] = (double) newPacketInt[iQZ] / Math.pow(2, 30);
                calibratedDataUnits[iQW] = CHANNEL_UNITS.NO_UNITS;
                calibratedDataUnits[iQX] = CHANNEL_UNITS.NO_UNITS;
                calibratedDataUnits[iQY] = CHANNEL_UNITS.NO_UNITS;
                calibratedDataUnits[iQZ] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_W, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iQW]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iQX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iQY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iQZ]);
                uncalibratedData[iQW] = (double) newPacketInt[iQW];
                uncalibratedData[iQX] = (double) newPacketInt[iQX];
                uncalibratedData[iQY] = (double) newPacketInt[iQY];
                uncalibratedData[iQZ] = (double) newPacketInt[iQZ];
                uncalibratedDataUnits[iQW] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iQX] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iQY] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iQZ] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_W, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iQW]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iQX]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iQY]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.QUAT_MPL_6DOF_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iQZ]);
            }
            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_TEMPERATURE) > 0)) {
                int iT = getSignalIndex(Shimmer3.ObjectClusterSensorName.MPL_TEMPERATURE);
                calibratedData[iT] = (double) newPacketInt[iT] / Math.pow(2, 16);
                calibratedDataUnits[iT] = CHANNEL_UNITS.DEGREES_CELSIUS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_TEMPERATURE, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_CELSIUS, calibratedData[iT]);
                uncalibratedData[iT] = (double) newPacketInt[iT];
                uncalibratedDataUnits[iT] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_TEMPERATURE, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iT]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_PEDOMETER) > 0)) {
                int iPedoCnt = getSignalIndex(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_CNT);
                int iPedoTime = getSignalIndex(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_TIME);
                calibratedData[iPedoCnt] = (double) newPacketInt[iPedoCnt];
                calibratedData[iPedoTime] = (double) newPacketInt[iPedoTime];
                calibratedDataUnits[iPedoCnt] = CHANNEL_UNITS.NO_UNITS;
                calibratedDataUnits[iPedoTime] = CHANNEL_UNITS.MILLISECONDS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_CNT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iPedoCnt]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_TIME, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS, calibratedData[iPedoTime]);
                uncalibratedData[iPedoCnt] = (double) newPacketInt[iPedoCnt];
                uncalibratedData[iPedoTime] = (double) newPacketInt[iPedoTime];
                uncalibratedDataUnits[iPedoCnt] = CHANNEL_UNITS.NO_UNITS;
                uncalibratedDataUnits[iPedoTime] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_CNT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iPedoCnt]);
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_PEDOM_TIME, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iPedoTime]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_HEADING) > 0)) {
                int iH = getSignalIndex(Shimmer3.ObjectClusterSensorName.MPL_HEADING);
                calibratedData[iH] = (double) newPacketInt[iH] / Math.pow(2, 16);
                calibratedDataUnits[iH] = CHANNEL_UNITS.DEGREES;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_HEADING, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES, calibratedData[iH]);
                uncalibratedData[iH] = (double) newPacketInt[iH];
                uncalibratedDataUnits[iH] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MPL_HEADING, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iH]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_TAP) > 0)) {
                int iTap = getSignalIndex(Shimmer3.ObjectClusterSensorName.TAPDIRANDTAPCNT);
                calibratedData[iTap] = (double) newPacketInt[iTap];
                calibratedDataUnits[iTap] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.TAPDIRANDTAPCNT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iTap]);
                uncalibratedData[iTap] = (double) newPacketInt[iTap];
                uncalibratedDataUnits[iTap] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.TAPDIRANDTAPCNT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iTap]);
            }

            if (((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.MPL_MOTION_ORIENT) > 0)) {
                int iMotOrient = getSignalIndex(Shimmer3.ObjectClusterSensorName.MOTIONANDORIENT);
                calibratedData[iMotOrient] = (double) newPacketInt[iMotOrient];
                calibratedDataUnits[iMotOrient] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MOTIONANDORIENT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.NO_UNITS, calibratedData[iMotOrient]);
                uncalibratedData[iMotOrient] = (double) newPacketInt[iMotOrient];
                uncalibratedDataUnits[iMotOrient] = CHANNEL_UNITS.NO_UNITS;
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.MOTIONANDORIENT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[iMotOrient]);
            }

            if ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.ECG_TO_HR_FW) > 0) {
                int sigIndex = getSignalIndex(Shimmer3.ObjectClusterSensorName.ECG_TO_HR_FW);

                double ecgToHrFW = (double) newPacketInt[sigIndex];
                if (ecgToHrFW == 255) {
                    ecgToHrFW = SensorECGToHRFw.INVALID_HR_SUBSTITUTE;
                }

                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_TO_HR_FW, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.BEATS_PER_MINUTE, ecgToHrFW);
                calibratedData[sigIndex] = (double) newPacketInt[sigIndex];
                calibratedDataUnits[sigIndex] = CHANNEL_UNITS.BEATS_PER_MINUTE;
            }

            int additionalChannelsOffset = calibratedData.length - numAdditionalChannels;
            if (fwType == COMMUNICATION_TYPE.BLUETOOTH) {
                additionalChannelsOffset += 1;
            }
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultRespirationConfiguration()) {
                    if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG1_16BIT) > 0)
                            || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG1_16BIT) > 0)) {

                        int iexg1ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT);
                        int iexg1ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT);

                        if (iexg1ch1 != -1 && iexg1ch2 != -1) {
                            sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.ECG_LL_LA_16BIT;

                            uncalibratedData[additionalChannelsOffset] = uncalibratedData[iexg1ch1] - uncalibratedData[iexg1ch2];
                            uncalibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.NO_UNITS;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_LA_16BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[additionalChannelsOffset]);

                            if (mEnableCalibration) {
                                calibratedData[additionalChannelsOffset] = calibratedData[iexg1ch1] - calibratedData[iexg1ch2];
                                calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.MILLIVOLTS;
                                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_LA_16BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[additionalChannelsOffset]);
                            }
                            additionalChannelsOffset += 1;
                        }
                    } else if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && (mEnabledSensors & BTStream.EXG1_24BIT) > 0)
                            || ((fwType == COMMUNICATION_TYPE.SD) && (mEnabledSensors & SDLogHeader.EXG1_24BIT) > 0)) {

                        int iexg1ch1 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT);
                        int iexg1ch2 = getSignalIndex(Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT);

                        if (iexg1ch1 != -1 && iexg1ch2 != -1) {
                            sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.ECG_LL_LA_24BIT;

                            uncalibratedData[additionalChannelsOffset] = uncalibratedData[iexg1ch1] - uncalibratedData[iexg1ch2];
                            uncalibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.NO_UNITS;
                            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_LA_24BIT, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, uncalibratedData[additionalChannelsOffset]);

                            if (mEnableCalibration) {
                                calibratedData[additionalChannelsOffset] = calibratedData[iexg1ch1] - calibratedData[iexg1ch2];
                                calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.MILLIVOLTS;
                                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.ECG_LL_LA_24BIT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[additionalChannelsOffset]);
                            }
                            additionalChannelsOffset += 1;
                        }
                    }
                }
            }

            if (fwType == COMMUNICATION_TYPE.BLUETOOTH) {
                double estimatedChargePercentage = (double) mShimmerBattStatusDetails.getEstimatedChargePercentage();
                if (!Double.isNaN(estimatedChargePercentage) && !Double.isInfinite(estimatedChargePercentage)) {
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.PERCENT, estimatedChargePercentage);
                    calibratedData[additionalChannelsOffset] = estimatedChargePercentage;
                    calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.PERCENT;
                    uncalibratedData[additionalChannelsOffset] = Double.NaN;
                    uncalibratedDataUnits[additionalChannelsOffset] = "";
                    sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.BATT_PERCENTAGE;
                }
                additionalChannelsOffset += 1;

                double packetReceptionRateCurrent = (double) getPacketReceptionRateCurrent();
                if (!Double.isNaN(packetReceptionRateCurrent) && !Double.isInfinite(packetReceptionRateCurrent)) {
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.PERCENT, packetReceptionRateCurrent);
                    calibratedData[additionalChannelsOffset] = packetReceptionRateCurrent;
                    calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.PERCENT;
                    uncalibratedData[additionalChannelsOffset] = Double.NaN;
                    uncalibratedDataUnits[additionalChannelsOffset] = "";
                    sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_CURRENT;
                }
                additionalChannelsOffset += 1;

                double packetReceptionRateOverall = (double) getPacketReceptionRateOverall();
                if (!Double.isNaN(packetReceptionRateOverall) && !Double.isInfinite(packetReceptionRateOverall)) {
                    objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.PERCENT, packetReceptionRateOverall);
                    calibratedData[additionalChannelsOffset] = packetReceptionRateOverall;
                    calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.PERCENT;
                    uncalibratedData[additionalChannelsOffset] = Double.NaN;
                    uncalibratedDataUnits[additionalChannelsOffset] = "";
                    sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.PACKET_RECEPTION_RATE_OVERALL;
                }
                additionalChannelsOffset += 1;

                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.SYSTEM_TIMESTAMP, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS, (double) pcTimestampMs);
                calibratedData[additionalChannelsOffset] = (double) pcTimestampMs;
                calibratedDataUnits[additionalChannelsOffset] = CHANNEL_UNITS.MILLISECONDS;
                uncalibratedData[additionalChannelsOffset] = Double.NaN;
                uncalibratedDataUnits[additionalChannelsOffset] = "";
                sensorNames[additionalChannelsOffset] = Shimmer3.ObjectClusterSensorName.SYSTEM_TIMESTAMP;
                additionalChannelsOffset += 1;

                processEventMarkerCh(objectCluster);

            }

            if (!objectCluster.mEnableArraysDataStructure) {
                objectCluster.mCalData = calibratedData;
                objectCluster.mUncalData = uncalibratedData;
                objectCluster.mUnitCal = calibratedDataUnits;
                objectCluster.mUnitUncal = uncalibratedDataUnits;
                objectCluster.mSensorNames = sensorNames;
                objectCluster.setIndexKeeper(additionalChannelsOffset);
            } else {
                if (((fwType == COMMUNICATION_TYPE.BLUETOOTH) && ((mEnabledSensors & BTStream.ACCEL_LN) > 0 || (mEnabledSensors & BTStream.ACCEL_WR) > 0) && ((mEnabledSensors & BTStream.GYRO) > 0) && ((mEnabledSensors & BTStream.MAG) > 0) && is3DOrientationEnabled())
                        || ((fwType == COMMUNICATION_TYPE.SD) && ((mEnabledSensors & SDLogHeader.ACCEL_LN) > 0 || (mEnabledSensors & SDLogHeader.ACCEL_WR) > 0) && ((mEnabledSensors & SDLogHeader.GYRO) > 0) && ((mEnabledSensors & SDLogHeader.MAG) > 0) && is3DOrientationEnabled())) {
                    int indexSensorNames = -1;
                    for (int i = 0; i < sensorNames.length; i++) {
                        if (sensorNames[i] == null
                                && sensorNames[i + 1] == null
                                && sensorNames[i + 2] == null
                                && sensorNames[i + 3] == null
                                && sensorNames[i + 4] == null
                                && sensorNames[i + 5] == null
                                && sensorNames[i + 6] == null
                                && sensorNames[i + 7] == null) {
                            indexSensorNames = i;
                            break;
                        }
                    }

                    System.arraycopy(sensorNamesAlgo, 0, sensorNames, indexSensorNames, (indexAlgo + 1));
                    System.arraycopy(calDataUnitsAlgo, 0, calibratedDataUnits, indexSensorNames, (indexAlgo + 1));
                    System.arraycopy(calDataAlgo, 0, calibratedData, indexSensorNames, (indexAlgo + 1));
                    additionalChannelsOffset += (indexAlgo + 1);
                }

                objectCluster.sensorDataArray.mCalData = calibratedData;
                objectCluster.sensorDataArray.mUncalData = uncalibratedData;
                objectCluster.sensorDataArray.mCalUnits = calibratedDataUnits;
                objectCluster.sensorDataArray.mUncalUnits = uncalibratedDataUnits;
                objectCluster.sensorDataArray.mSensorNames = sensorNames;
                objectCluster.setIndexKeeper(additionalChannelsOffset);
            }


        } else if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {

            int iTimeStamp = getSignalIndex(Configuration.Shimmer2.ObjectClusterSensorName.TIMESTAMP);
            double timestampUnwrappedTicks = unwrapTimeStamp((double) newPacketInt[iTimeStamp]);
            double timestampUnwrappedMilliSecs = timestampUnwrappedTicks / getRtcClockFreq() * 1000;

            incrementPacketsReceivedCounters();
            calculateTrialPacketLoss(timestampUnwrappedMilliSecs);

            if (fwType == COMMUNICATION_TYPE.BLUETOOTH) {
                objectCluster.addDataToMap(Configuration.Shimmer2.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iTimeStamp]);
                uncalibratedData[iTimeStamp] = (double) newPacketInt[iTimeStamp];
                uncalibratedDataUnits[iTimeStamp] = CHANNEL_UNITS.NO_UNITS;
                if (mEnableCalibration) {
                    objectCluster.addDataToMap(Configuration.Shimmer2.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS, timestampUnwrappedMilliSecs);
                    calibratedData[iTimeStamp] = timestampUnwrappedMilliSecs;
                    calibratedDataUnits[iTimeStamp] = CHANNEL_UNITS.MILLISECONDS;
                }
            }

            if ((mEnabledSensors & SENSOR_ACCEL) > 0) {
                int iAccelX = getSignalIndex(Shimmer2.ObjectClusterSensorName.ACCEL_X);
                int iAccelY = getSignalIndex(Shimmer2.ObjectClusterSensorName.ACCEL_Y);
                int iAccelZ = getSignalIndex(Shimmer2.ObjectClusterSensorName.ACCEL_Z);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelX]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelY]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iAccelZ]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iAccelX];
                    tempData[1] = (double) newPacketInt[iAccelY];
                    tempData[2] = (double) newPacketInt[iAccelZ];
                    double[] accelCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsAccelLn());
                    calibratedData[iAccelX] = accelCalibratedData[0];
                    calibratedData[iAccelY] = accelCalibratedData[1];
                    calibratedData[iAccelZ] = accelCalibratedData[2];
                    if (mSensorMMA736x.mIsUsingDefaultLNAccelParam) {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE + "*", accelCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE + "*", accelCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE + "*", accelCalibratedData[2]);
                        accelerometer.x = accelCalibratedData[0];
                        accelerometer.y = accelCalibratedData[1];
                        accelerometer.z = accelCalibratedData[2];
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE, accelCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE, accelCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ACCEL_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.METER_PER_SECOND_SQUARE, accelCalibratedData[2]);
                        accelerometer.x = accelCalibratedData[0];
                        accelerometer.y = accelCalibratedData[1];
                        accelerometer.z = accelCalibratedData[2];
                    }
                }

            }

            if ((mEnabledSensors & SENSOR_GYRO) > 0) {
                int iGyroX = getSignalIndex(Shimmer2.ObjectClusterSensorName.GYRO_X);
                int iGyroY = getSignalIndex(Shimmer2.ObjectClusterSensorName.GYRO_Y);
                int iGyroZ = getSignalIndex(Shimmer2.ObjectClusterSensorName.GYRO_Z);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroX]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroY]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGyroZ]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iGyroX];
                    tempData[1] = (double) newPacketInt[iGyroY];
                    tempData[2] = (double) newPacketInt[iGyroZ];
                    double[] gyroCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsGyro());
                    calibratedData[iGyroX] = gyroCalibratedData[0];
                    calibratedData[iGyroY] = gyroCalibratedData[1];
                    calibratedData[iGyroZ] = gyroCalibratedData[2];
                    if (mSensorShimmer2Gyro.mIsUsingDefaultGyroParam) {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND + "*", gyroCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND + "*", gyroCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND + "*", gyroCalibratedData[2]);
                        gyroscope.x = gyroCalibratedData[0] * Math.PI / 180;
                        gyroscope.y = gyroCalibratedData[1] * Math.PI / 180;
                        gyroscope.z = gyroCalibratedData[2] * Math.PI / 180;
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND, gyroCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND, gyroCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.DEGREES_PER_SECOND, gyroCalibratedData[2]);
                        gyroscope.x = gyroCalibratedData[0] * Math.PI / 180;
                        gyroscope.y = gyroCalibratedData[1] * Math.PI / 180;
                        gyroscope.z = gyroCalibratedData[2] * Math.PI / 180;

                        if (isShimmerGen2() && isGyroOnTheFlyCalEnabled()) {
                            getOnTheFlyCalGyro().updateGyroOnTheFlyGyroOVCal(getCurrentCalibDetailsGyro(), gyroCalibratedData, (double) newPacketInt[iGyroX], (double) newPacketInt[iGyroY], (double) newPacketInt[iGyroZ]);
                        }
                    }
                }
            }
            if ((mEnabledSensors & SENSOR_MAG) > 0) {
                int iMagX = getSignalIndex(Shimmer2.ObjectClusterSensorName.MAG_X);
                int iMagY = getSignalIndex(Shimmer2.ObjectClusterSensorName.MAG_Y);
                int iMagZ = getSignalIndex(Shimmer2.ObjectClusterSensorName.MAG_Z);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagX]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagY]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iMagZ]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iMagX];
                    tempData[1] = (double) newPacketInt[iMagY];
                    tempData[2] = (double) newPacketInt[iMagZ];
                    double[] magCalibratedData = UtilCalibration.calibrateInertialSensorData(tempData, getCurrentCalibDetailsMag());
                    calibratedData[iMagX] = magCalibratedData[0];
                    calibratedData[iMagY] = magCalibratedData[1];
                    calibratedData[iMagZ] = magCalibratedData[2];
                    if (mSensorShimmer2Mag.mIsUsingDefaultMagParam) {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX + "*", magCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX + "*", magCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX + "*", magCalibratedData[2]);
                        magnetometer.x = magCalibratedData[0];
                        magnetometer.y = magCalibratedData[1];
                        magnetometer.z = magCalibratedData[2];
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX, magCalibratedData[0]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX, magCalibratedData[1]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL_FLUX, magCalibratedData[2]);
                        magnetometer.x = magCalibratedData[0];
                        magnetometer.y = magCalibratedData[1];
                        magnetometer.z = magCalibratedData[2];
                    }
                }
            }


            if ((mEnabledSensors & SENSOR_ACCEL) > 0 && (mEnabledSensors & SENSOR_GYRO) > 0 && (mEnabledSensors & SENSOR_MAG) > 0 && is3DOrientationEnabled()) {
                if (mEnableCalibration) {
                    if (mOrientationAlgo == null) {
                        mOrientationAlgo = new GradDes3DOrientation((double) 1 / getSamplingRateShimmer());
                    }
                }
            }

            if ((mEnabledSensors & SENSOR_GSR) > 0) {
                int iGSR = getSignalIndex(Shimmer2.ObjectClusterSensorName.GSR);
                tempData[0] = (double) newPacketInt[iGSR];
                int newGSRRange = -1;
                int currentGSRRange = getGSRRange();

                double p1 = 0, p2 = 0;
                if (currentGSRRange == 4) {
                    newGSRRange = (49152 & (int) tempData[0]) >> 14;
                }
                if (currentGSRRange == 0 || newGSRRange == 0) {

                    p1 = 0.0373;
                    p2 = -24.9915;
                } else if (currentGSRRange == 1 || newGSRRange == 1) {
                    p1 = 0.0054;
                    p2 = -3.5194;
                } else if (currentGSRRange == 2 || newGSRRange == 2) {
                    p1 = 0.0015;
                    p2 = -1.0163;
                } else if (currentGSRRange == 3 || newGSRRange == 3) {
                    p1 = 4.5580e-04;
                    p2 = -0.3014;
                }
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GSR, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iGSR]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iGSR];
                    calibratedData[iGSR] = SensorGSR.calibrateGsrDataToResistance(tempData[0], p1, p2);
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.GSR, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.KOHMS, calibratedData[iGSR]);
                }
            }
            if ((mEnabledSensors & SENSOR_ECG) > 0) {
                int iECGRALL = getSignalIndex(Shimmer2.ObjectClusterSensorName.ECG_RA_LL);
                int iECGLALL = getSignalIndex(Shimmer2.ObjectClusterSensorName.ECG_LA_LL);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_RA_LL, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iECGRALL]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_LA_LL, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iECGLALL]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iECGRALL];
                    tempData[1] = (double) newPacketInt[iECGLALL];
                    calibratedData[iECGRALL] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], OffsetECGRALL, 3, GainECGRALL);
                    calibratedData[iECGLALL] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[1], OffsetECGLALL, 3, GainECGLALL);
                    if (mDefaultCalibrationParametersECG == true) {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_RA_LL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS + "*", calibratedData[iECGRALL]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_LA_LL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS + "*", calibratedData[iECGLALL]);
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_RA_LL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iECGRALL]);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.ECG_LA_LL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iECGLALL]);
                    }
                }
            }
            if ((mEnabledSensors & SENSOR_EMG) > 0) {
                int iEMG = getSignalIndex(Shimmer2.ObjectClusterSensorName.EMG);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EMG, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iEMG]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iEMG];
                    calibratedData[iEMG] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], OffsetEMG, 3, GainEMG);

                    if (mDefaultCalibrationParametersEMG == true) {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EMG, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS + "*", calibratedData[iEMG]);
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EMG, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iEMG]);
                    }
                }
            }
            if ((mEnabledSensors & SENSOR_BRIDGE_AMP) > 0) {
                int iBAHigh = getSignalIndex(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH);
                int iBALow = getSignalIndex(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iBAHigh]);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iBALow]);
                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iBAHigh];
                    tempData[1] = (double) newPacketInt[iBALow];
                    calibratedData[iBAHigh] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 60, 3, 551);
                    calibratedData[iBALow] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[1], 1950, 3, 183.7);

                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iBAHigh]);
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iBALow]);
                }
            }
            if ((mEnabledSensors & SENSOR_HEART) > 0) {
                int iHeartRate = getSignalIndex(Shimmer2.ObjectClusterSensorName.HEART_RATE);
                tempData[0] = (double) newPacketInt[iHeartRate];
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.HEART_RATE, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, tempData[0]);
                if (mEnableCalibration) {
                    calibratedData[iHeartRate] = tempData[0];
                    if (getFirmwareVersionMajor() == 0 && getFirmwareVersionMinor() == 1) {

                    } else {
                        if (tempData[0] == 0) {
                            calibratedData[iHeartRate] = mLastKnownHeartRate;
                        } else {
                            calibratedData[iHeartRate] = (int) (1024 / tempData[0] * 60);
                            mLastKnownHeartRate = calibratedData[iHeartRate];
                        }
                    }
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.HEART_RATE, CHANNEL_TYPE.CAL.toString(), "BPM", calibratedData[iHeartRate]);
                }
            }
            if ((mEnabledSensors & SENSOR_EXP_BOARD_A0) > 0) {
                int iA0 = getSignalIndex(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0);
                tempData[0] = (double) newPacketInt[iA0];
                if (getPMux() == 0) {
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA0]);
                    if (mEnableCalibration) {
                        calibratedData[iA0] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA0]);
                    }
                } else {
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.VOLT_REG, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA0]);
                    if (mEnableCalibration) {
                        calibratedData[iA0] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1) * 1.988;
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.VOLT_REG, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA0]);
                    }

                }
            }
            if ((mEnabledSensors & SENSOR_EXP_BOARD_A7) > 0) {
                int iA7 = getSignalIndex(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7);
                tempData[0] = (double) newPacketInt[iA7];
                if (getPMux() == 0) {
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA7]);
                    if (mEnableCalibration) {
                        calibratedData[iA7] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1);
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA7]);
                    } else {
                        objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA7]);
                        if (mEnableCalibration) {
                            calibratedData[iA7] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1) * 2;
                            objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA7]);
                        }

                    }
                }
            }
            if ((mEnabledSensors & SENSOR_BATT) > 0) {
                int iA0 = getSignalIndex(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.VOLT_REG, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA0]);

                int iA7 = getSignalIndex(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7);
                objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS, (double) newPacketInt[iA7]);


                if (mEnableCalibration) {
                    tempData[0] = (double) newPacketInt[iA0];
                    calibratedData[iA0] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1) * 1.988;
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.VOLT_REG, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA0]);

                    tempData[0] = (double) newPacketInt[iA7];
                    calibratedData[iA7] = SensorADC.calibrateU12AdcValueToMillivolts(tempData[0], 0, 3, 1) * 2;
                    objectCluster.addDataToMap(Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS, calibratedData[iA7]);
                    mVSenseBattMA.addValue(calibratedData[iA7]);
                    checkBattery();
                }
            }
        } else {
            consolePrintErrLn("The Hardware version is not compatible");
        }

        objectCluster = processData(objectCluster);

        return objectCluster;
    }

    protected void parseTimestampShimmer3(COMMUNICATION_TYPE fwType, ObjectCluster objectCluster, double[] uncalibratedData, String[] uncalibratedDataUnits, double[] calibratedData, String[] calibratedDataUnits, String[] sensorNames, long[] newPacketInt) {
        int iTimeStamp = getSignalIndex(Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP);
        double shimmerTimestampTicks = (double) newPacketInt[iTimeStamp];

        if (mFirstTime && fwType == COMMUNICATION_TYPE.SD) {
            mFirstTsOffsetFromInitialTsTicks = shimmerTimestampTicks;

            if (getFirmwareIdentifier() == FW_ID.STROKARE
                    && !isThisVerCompatibleWith(FW_ID.STROKARE, 1, 0, 1)) {
                long initialTsTicksOriginal = getInitialTimeStampTicksSd();
                long initialTsTicksNew = (long) ((initialTsTicksOriginal & 0xFFFF000000L) + shimmerTimestampTicks);
                setInitialTimeStampTicksSd(initialTsTicksNew);
            }

            mFirstTime = false;
        }

        double timestampUnwrappedTicks = unwrapTimeStamp(shimmerTimestampTicks);
        double timestampUnwrappedMilliSecs = timestampUnwrappedTicks / getRtcClockFreq() * 1000;

        incrementPacketsReceivedCounters();
        calculateTrialPacketLoss(timestampUnwrappedMilliSecs);

        double timestampUnwrappedWithOffsetTicks = 0;
        if (fwType == COMMUNICATION_TYPE.SD) {
            timestampUnwrappedWithOffsetTicks = timestampUnwrappedTicks + getInitialTimeStampTicksSd();

            if (isLegacySdLog()) {
                uncalibratedData[iTimeStamp] = shimmerTimestampTicks;
            } else {
                timestampUnwrappedWithOffsetTicks -= mFirstTsOffsetFromInitialTsTicks;
                uncalibratedData[iTimeStamp] = timestampUnwrappedWithOffsetTicks;
            }

            if (mEnableCalibration) {
                double timestampUnwrappedWithOffsetMilliSecs = timestampUnwrappedWithOffsetTicks / getRtcClockFreq() * 1000;
                calibratedData[iTimeStamp] = timestampUnwrappedWithOffsetMilliSecs;
            }
        } else if (fwType == COMMUNICATION_TYPE.BLUETOOTH) {
            uncalibratedData[iTimeStamp] = shimmerTimestampTicks;
            if (mEnableCalibration) {
                calibratedData[iTimeStamp] = timestampUnwrappedMilliSecs;
            }
        }

        uncalibratedDataUnits[iTimeStamp] = CHANNEL_UNITS.CLOCK_UNIT;
        objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.CLOCK_UNIT, uncalibratedData[iTimeStamp]);
        if (mEnableCalibration) {
            calibratedDataUnits[iTimeStamp] = CHANNEL_UNITS.MILLISECONDS;
            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS, calibratedData[iTimeStamp]);
            objectCluster.setTimeStampMilliSecs(calibratedData[iTimeStamp]);
        }


        if (fwType == COMMUNICATION_TYPE.SD && isRtcDifferenceSet()) {
            double rtcTimestampTicks = timestampUnwrappedWithOffsetTicks + getRTCDifferenceInTicks();


            objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.REAL_TIME_CLOCK, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.CLOCK_UNIT, rtcTimestampTicks);
            uncalibratedData[sensorNames.length - 1] = rtcTimestampTicks;
            uncalibratedDataUnits[sensorNames.length - 1] = CHANNEL_UNITS.CLOCK_UNIT;
            sensorNames[sensorNames.length - 1] = Shimmer3.ObjectClusterSensorName.REAL_TIME_CLOCK;
            if (mEnableCalibration) {
                double rtcTimestampMilliSecs = 0;
                if (fwType == COMMUNICATION_TYPE.SD) {
                    rtcTimestampMilliSecs = rtcTimestampTicks / getSamplingClockFreq() * 1000.0;
                } else {
                    rtcTimestampMilliSecs = timestampUnwrappedMilliSecs;
                    if (getInitialTimeStampTicksSd() != 0) {
                        rtcTimestampMilliSecs += ((double) getInitialTimeStampTicksSd() / getSamplingClockFreq() * 1000.0);
                    }
                    if (isRtcDifferenceSet()) {
                        rtcTimestampMilliSecs += ((double) getRTCDifferenceInTicks() / getSamplingClockFreq() * 1000.0);
                    }
                    if (mFirstTsOffsetFromInitialTsTicks != 0) {
                        rtcTimestampMilliSecs -= (mFirstTsOffsetFromInitialTsTicks / getSamplingClockFreq() * 1000.0);
                    }
                }
                objectCluster.addDataToMap(Shimmer3.ObjectClusterSensorName.REAL_TIME_CLOCK, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS, rtcTimestampMilliSecs);
                calibratedData[sensorNames.length - 1] = rtcTimestampMilliSecs;
                calibratedDataUnits[sensorNames.length - 1] = CHANNEL_UNITS.MILLISECONDS;
            }
        }
    }

    private void printSensorNames(List<String> list) {
        System.out.println("ObjectClusterSensorNames");
        for (String channelName : list) {
            System.out.println("\t" + channelName);
        }
        System.out.println("");
    }

    private void printSensorNames(String[] channelNames) {
        System.out.println("ObjectClusterChannelNames");
        for (String channelName : channelNames) {
            System.out.println("\t" + channelName);
        }
        System.out.println("");
    }

    protected int getSignalIndex(String signalName) {
        int iSignal = -1;
        for (int i = 0; i < mSignalNameArray.length; i++) {
            if (signalName == mSignalNameArray[i]) {
                return i;
            }
        }

        return iSignal;
    }

    public void interpretDataPacketFormat(int numChannels, byte[] signalId) {
        String[] signalNameArray = new String[MAX_NUMBER_OF_SIGNALS];
        String[] signalDataTypeArray = new String[MAX_NUMBER_OF_SIGNALS];
        int packetSize = mTimeStampPacketByteSize;

        int iTS = 0;

        if (getHardwareVersion() == HW_ID.SHIMMER_3R && this.getClass().getSimpleName().equals("ShimmerSDLog")) {
            mNChannels = numChannels;
            if (isSyncWhenLogging()
                    && (getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.GQ_802154
                    || (UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3LogAndStreamWithSDLogSyncSupport))
                    || (UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3RLogAndStreamWithSDLogSyncSupport)))) {
                signalNameArray[iTS] = SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET;
                if (OFFSET_LENGTH == 5) {
                    signalDataTypeArray[iTS] = "u32signed";
                    mNChannels += 1;
                    packetSize += 5;
                } else if (OFFSET_LENGTH == 9) {
                    signalDataTypeArray[iTS] = "u72";
                    mNChannels += 1;
                    packetSize += 9;
                }
                iTS++;
            }
        }


        if (getHardwareVersion() == HW_ID.SHIMMER_SR30 || getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            signalNameArray[iTS] = Configuration.Shimmer3.ObjectClusterSensorName.TIMESTAMP;
        } else {
            signalNameArray[iTS] = Configuration.Shimmer2.ObjectClusterSensorName.TIMESTAMP;
        }


        if (mTimeStampPacketByteSize == 2) {
            signalDataTypeArray[iTS] = "u16";
        } else if (mTimeStampPacketByteSize == 3) {
            signalDataTypeArray[iTS] = "u24";
        }

        int enabledSensors = 0x00;
        for (int i = iTS; i < numChannels + iTS; i++) {
            if ((byte) signalId[i - iTS] == (byte) 0x00) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30 || getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_LN_X;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_A_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.ACCEL_X;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x01) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30 || getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_LN_Y;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_A_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.ACCEL_Y;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x02) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30 || getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_LN_Z;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_A_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.ACCEL_Z;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x03) {

                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_X;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.BATTERY;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_VBATT;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.GYRO_X;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GYRO;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x04) {

                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_Y;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_X;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.GYRO_Y;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GYRO;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x05) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_Z;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.GYRO_Z;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GYRO;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x06) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.BATTERY;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_VBATT;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.MAG_X;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x07) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_X;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_X;
                    if (isSupportedNewImuSensors()) {
                        signalDataTypeArray[i + 1] = "i16";
                    } else {
                        signalDataTypeArray[i + 1] = "i16r";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.MAG_Y;
                    enabledSensors |= SENSOR_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x08) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_Y;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_Y;
                    if (isSupportedNewImuSensors()) {
                        signalDataTypeArray[i + 1] = "i16";
                    } else {
                        signalDataTypeArray[i + 1] = "i16r";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else {
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.MAG_Z;
                    enabledSensors |= SENSOR_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x09) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_WR_Z;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_Z;
                    if (isSupportedNewImuSensors()) {
                        signalDataTypeArray[i + 1] = "i16";
                    } else {
                        signalDataTypeArray[i + 1] = "i16r";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.ECG_RA_LL;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_ECG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0A) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_X;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_X;
                    if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                        signalDataTypeArray[i + 1] = "i16r";
                    } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalDataTypeArray[i + 1] = "i16";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.ECG_LA_LL;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_ECG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0B) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_Y;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_Y;
                    if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                        signalDataTypeArray[i + 1] = "i16r";
                    } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalDataTypeArray[i + 1] = "i16";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.GSR;
                    signalDataTypeArray[i + 1] = "u16";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GSR;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0C) {
                if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.MAG_Z;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.GYRO_Z;
                    if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                        signalDataTypeArray[i + 1] = "i16r";
                    } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalDataTypeArray[i + 1] = "i16";
                    }
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.GSR_RES;
                    signalDataTypeArray[i + 1] = "u16";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GSR;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0D) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_ADC_0;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A7;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXT_ADC_A7;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.EMG;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EMG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0E) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_ADC_1;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A6;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXT_ADC_A6;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXP_BOARD_A0;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x0F) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_ADC_2;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXT_EXP_ADC_A15;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXT_ADC_A15;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXP_BOARD_A7;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x10) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_ADC_3;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A1;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_INT_ADC_A1;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_BRIDGE_AMP;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x11) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_ADC_0;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A12;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_INT_ADC_A12;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_BRIDGE_AMP;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x12) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_ADC_1;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A13;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_INT_ADC_A13;
                } else {
                    signalNameArray[i + 1] = Shimmer2.ObjectClusterSensorName.HEART_RATE;
                    if (getFirmwareVersionMajor() == 0 && getFirmwareVersionMinor() == 1) {
                        signalDataTypeArray[i + 1] = "u8";
                        packetSize = packetSize + 1;
                    } else {
                        signalDataTypeArray[i + 1] = "u16";
                        packetSize = packetSize + 2;
                    }
                    enabledSensors |= SENSOR_HEART;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x13) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_ADC_2;
                        signalDataTypeArray[i + 1] = "u14";
                    } else {
                        signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.INT_EXP_ADC_A14;
                        signalDataTypeArray[i + 1] = "u12";
                    }

                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_INT_ADC_A14;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x14) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_X;
                    signalDataTypeArray[i + 1] = "i12*>";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x15) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Y;
                    signalDataTypeArray[i + 1] = "i12*>";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x16) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ACCEL_HIGHG_Z;
                    signalDataTypeArray[i + 1] = "i12*>";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_ACCEL;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x17) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ALT_MAG_X;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x18) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ALT_MAG_Y;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x19) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.ALT_MAG_Z;
                    signalDataTypeArray[i + 1] = "i16";
                    packetSize = packetSize + 2;
                    enabledSensors |= Configuration.Shimmer3.SensorBitmap.SENSOR_ALT_MAG;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1A) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                    String signalName = SensorBMP180.ObjectClusterSensorName.TEMPERATURE_BMP180;
                    if (isSupportedBmp280()) {
                        signalName = SensorBMP280.ObjectClusterSensorName.TEMPERATURE_BMP280;
                    }
                    signalNameArray[i + 1] = signalName;
                    signalDataTypeArray[i + 1] = "u16r";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_BMPX80;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    String signalName = SensorBMP390.ObjectClusterSensorName.TEMPERATURE_BMP390;
                    signalNameArray[i + 1] = signalName;
                    signalDataTypeArray[i + 1] = "u24";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_BMPX80;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1B) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                    String signalName = SensorBMP180.ObjectClusterSensorName.PRESSURE_BMP180;
                    if (isSupportedBmp280()) {
                        signalName = SensorBMP280.ObjectClusterSensorName.PRESSURE_BMP280;
                    }
                    signalNameArray[i + 1] = signalName;
                    signalDataTypeArray[i + 1] = "u24r";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_BMPX80;
                } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    String signalName = SensorBMP390.ObjectClusterSensorName.PRESSURE_BMP390;
                    signalNameArray[i + 1] = signalName;
                    signalDataTypeArray[i + 1] = "u24";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_BMPX80;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1C) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = SensorGSR.ObjectClusterSensorName.GSR_RESISTANCE;
                    signalDataTypeArray[i + 1] = "u16";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_GSR;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1D) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG1_STATUS;
                    signalDataTypeArray[i + 1] = "u8";
                    packetSize = packetSize + 1;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1E) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG1_CH1_24BIT;
                    signalDataTypeArray[i + 1] = "i24r";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_EXG1_24BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x1F) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG1_CH2_24BIT;
                    signalDataTypeArray[i + 1] = "i24r";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_EXG1_24BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x20) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG2_STATUS;
                    signalDataTypeArray[i + 1] = "u8";
                    packetSize = packetSize + 1;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x21) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_24BIT;
                    signalDataTypeArray[i + 1] = "i24r";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_EXG2_24BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x22) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_24BIT;
                    signalDataTypeArray[i + 1] = "i24r";
                    packetSize = packetSize + 3;
                    enabledSensors |= SENSOR_EXG2_24BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x23) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG1_CH1_16BIT;
                    signalDataTypeArray[i + 1] = "i16r";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXG1_16BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x24) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG1_CH2_16BIT;
                    signalDataTypeArray[i + 1] = "i16r";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXG1_16BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x25) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG2_CH1_16BIT;
                    signalDataTypeArray[i + 1] = "i16r";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXG2_16BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x26) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.EXG2_CH2_16BIT;
                    signalDataTypeArray[i + 1] = "i16r";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_EXG2_16BIT;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x27) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_HIGH;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_BRIDGE_AMP;
                }
            } else if ((byte) signalId[i - iTS] == (byte) 0x28) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    signalNameArray[i + 1] = Shimmer3.ObjectClusterSensorName.BRIDGE_AMP_LOW;
                    signalDataTypeArray[i + 1] = "u12";
                    packetSize = packetSize + 2;
                    enabledSensors |= SENSOR_BRIDGE_AMP;
                }
            } else {
                signalNameArray[i + 1] = Byte.toString(signalId[i - iTS]);
                signalDataTypeArray[i + 1] = "u12";
                packetSize = packetSize + 2;
            }
        }


        mSignalNameArray = signalNameArray;
        mSignalDataTypeArray = signalDataTypeArray;
        setPacketSize(packetSize);
    }

    @Deprecated
    public void generateBiMapSensorIDtoSensorName() {
        if (getHardwareVersion() != -1) {
            if (getHardwareVersion() != HW_ID.SHIMMER_2R) {
                final Map<String, String> tempSensorBMtoName = new HashMap<String, String>();
                tempSensorBMtoName.put(Integer.toString(SENSOR_BMPX80), "Pressure");
                tempSensorBMtoName.put(Integer.toString(SENSOR_GYRO), "Gyroscope");
                tempSensorBMtoName.put(Integer.toString(SENSOR_MAG), "Magnetometer");
                tempSensorBMtoName.put(Integer.toString(SHIMMER3_SENSOR_ECG), "ECG");
                tempSensorBMtoName.put(Integer.toString(SHIMMER3_SENSOR_EMG), "EMG");
                tempSensorBMtoName.put(Integer.toString(SENSOR_GSR), "GSR");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A7), "Exp Board A7");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A0), "Exp Board A0");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD), "Exp Board");
                tempSensorBMtoName.put(Integer.toString(SENSOR_BRIDGE_AMP), "Bridge Amplifier");
                tempSensorBMtoName.put(Integer.toString(SENSOR_HEART), "Heart Rate");
                tempSensorBMtoName.put(Integer.toString(SENSOR_BATT), "Battery Voltage");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A7), "External ADC A7");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A6), "External ADC A6");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A15), "External ADC A15");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A1), "Internal ADC A1");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A12), "Internal ADC A12");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A13), "Internal ADC A13");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A14), "Internal ADC A14");
                tempSensorBMtoName.put(Integer.toString(SENSOR_ACCEL), "Low Noise Accelerometer");
                tempSensorBMtoName.put(Integer.toString(SENSOR_DACCEL), "Wide Range Accelerometer");
                mSensorBitmaptoName = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempSensorBMtoName));
            } else {
                final Map<String, String> tempSensorBMtoName = new HashMap<String, String>();
                tempSensorBMtoName.put(Integer.toString(SENSOR_ACCEL), "Accelerometer");
                tempSensorBMtoName.put(Integer.toString(SENSOR_GYRO), "Gyroscope");
                tempSensorBMtoName.put(Integer.toString(SENSOR_MAG), "Magnetometer");
                tempSensorBMtoName.put(Integer.toString(SENSOR_ECG), "ECG");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EMG), "EMG");
                tempSensorBMtoName.put(Integer.toString(SENSOR_GSR), "GSR");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A7), "Exp Board A7");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD_A0), "Exp Board A0");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXP_BOARD), "Exp Board");
                tempSensorBMtoName.put(Integer.toString(SENSOR_BRIDGE_AMP), "Bridge Amplifier");
                tempSensorBMtoName.put(Integer.toString(SENSOR_HEART), "Heart Rate");
                tempSensorBMtoName.put(Integer.toString(SENSOR_BATT), "Battery Voltage");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A7), "External ADC A7");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A6), "External ADC A6");
                tempSensorBMtoName.put(Integer.toString(SENSOR_EXT_ADC_A15), "External ADC A15");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A1), "Internal ADC A1");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A12), "Internal ADC A12");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A13), "Internal ADC A13");
                tempSensorBMtoName.put(Integer.toString(SENSOR_INT_ADC_A14), "Internal ADC A14");
                mSensorBitmaptoName = ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempSensorBMtoName));
            }
        }
    }

    @Override
    public String[] getListofEnabledChannelSignals() {
        List<String> listofSignals = new ArrayList<String>();
        if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
            listofSignals.add("Timestamp");
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.ACCEL_X);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.ACCEL_Y);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.ACCEL_Z);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.GYRO_X);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.GYRO_Y);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.GYRO_Z);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.MAG_X);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.MAG_Y);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.MAG_Z);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.GSR);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.ECG_RA_LL);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.ECG_LA_LL);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EMG);
            }
            if (((mEnabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW);
            }
            if (((mEnabledSensors & 0xFF00) & SENSOR_HEART) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.HEART_RATE);
            }
            if ((((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A0) > 0) && getPMux() == 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0);
            }
            if ((((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A7) > 0 && getPMux() == 0)) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7);
            }
            if (((mEnabledSensors & 0xFFFF) & SENSOR_BATT) > 0) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.BATTERY);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.REG);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0 && is3DOrientationEnabled()) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EULER_9DOF_YAW);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EULER_9DOF_PITCH);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.EULER_9DOF_ROLL);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0 && is3DOrientationEnabled()) {
                listofSignals.add(Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_W);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_X);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y);
                listofSignals.add(Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z);
            }

        } else {
            return super.getListofEnabledChannelSignals();
        }

        String[] enabledSignals = listofSignals.toArray(new String[listofSignals.size()]);
        return enabledSignals;
    }

    protected void retrieveBiophysicalCalibrationParametersFromPacket(byte[] bufferCalibrationParameters, int packetType) {
        if (packetType == ECG_CALIBRATION_RESPONSE) {
            if (bufferCalibrationParameters[0] == -1 && bufferCalibrationParameters[1] == -1 && bufferCalibrationParameters[2] == -1 && bufferCalibrationParameters[3] == -1) {
                mDefaultCalibrationParametersECG = true;
            } else {
                mDefaultCalibrationParametersECG = false;
                OffsetECGLALL = (double) ((bufferCalibrationParameters[0] & 0xFF) << 8) + (bufferCalibrationParameters[1] & 0xFF);
                GainECGLALL = (double) ((bufferCalibrationParameters[2] & 0xFF) << 8) + (bufferCalibrationParameters[3] & 0xFF);
                OffsetECGRALL = (double) ((bufferCalibrationParameters[4] & 0xFF) << 8) + (bufferCalibrationParameters[5] & 0xFF);
                GainECGRALL = (double) ((bufferCalibrationParameters[6] & 0xFF) << 8) + (bufferCalibrationParameters[7] & 0xFF);
            }
        }

        if (packetType == EMG_CALIBRATION_RESPONSE) {

            if (bufferCalibrationParameters[0] == -1 && bufferCalibrationParameters[1] == -1 && bufferCalibrationParameters[2] == -1 && bufferCalibrationParameters[3] == -1) {
                mDefaultCalibrationParametersEMG = true;
            } else {
                mDefaultCalibrationParametersEMG = false;
                OffsetEMG = (double) ((bufferCalibrationParameters[0] & 0xFF) << 8) + (bufferCalibrationParameters[1] & 0xFF);
                GainEMG = (double) ((bufferCalibrationParameters[2] & 0xFF) << 8) + (bufferCalibrationParameters[3] & 0xFF);
            }
        }
    }


    protected double unwrapTimeStamp(double timeStampTicks) {
        double timestampUnwrappedTicks = calculateTimeStampUnwrapped(timeStampTicks);

        if (getLastReceivedTimeStampTicksUnwrapped() > timestampUnwrappedTicks) {
            mCurrentTimeStampCycle += 1;
            timestampUnwrappedTicks = calculateTimeStampUnwrapped(timeStampTicks);
        }

        setLastReceivedTimeStampTicksUnwrapped(timestampUnwrappedTicks);

        return timestampUnwrappedTicks;
    }

    private double calculateTimeStampUnwrapped(double timeStampTicks) {
        return timeStampTicks + (mTimeStampTicksMaxValue * mCurrentTimeStampCycle);
    }

    private void calculateTrialPacketLoss(double timestampUnwrappedMilliSecs) {
        if (!mStreamingStartTimeSaved) {
            mStreamingStartTimeSaved = true;
            mStreamingStartTimeMilliSecs = timestampUnwrappedMilliSecs;
        }

        if (mStreamingStartTimeMilliSecs > 0) {
            double timeDifference = timestampUnwrappedMilliSecs - mStreamingStartTimeMilliSecs;

            double expectedTimeDifference = (1 / getSamplingRateShimmer()) * 1000;

            long packetExpectedCount = (long) (timeDifference / expectedTimeDifference);
            setPacketExpectedCountOverall(packetExpectedCount);

            long packetReceivedCount = getPacketReceivedCountOverall();

            long packetLossCountPerTrial = packetExpectedCount + packetReceivedCount;
            setPacketLossCountPerTrial(packetLossCountPerTrial);

            double packetReceptionRateTrial = ((double) packetReceivedCount / (double) packetExpectedCount) * 100;
            setPacketReceptionRateOverall(packetReceptionRateTrial);
        }
    }

    public void resetCalibratedTimeStamp() {
        setLastReceivedTimeStampTicksUnwrapped(0);
        mLastReceivedCalibratedTimeStamp = -1;

        mStreamingStartTimeSaved = false;
        mStreamingStartTimeMilliSecs = -1;

        setCurrentTimeStampCycle(0);
    }

    protected void sendStatusMsgPacketLossDetected() {

    }

    public int getPMux() {
        if ((mConfigByte0 & (byte) 64) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    protected ObjectCluster callAdditionalServices(ObjectCluster objectCluster) {
        return objectCluster;
    }

    protected void interpretInqResponse(byte[] bufferInquiry) {
        try {
            if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
                setPacketSize(mTimeStampPacketByteSize + bufferInquiry[3] * 2);
                setSamplingRateShimmer((double) 1024 / bufferInquiry[0]);
                if (getMagRate() == 3 && getSamplingRateShimmer() > 10) {
                    setLowPowerMag(true);
                }
                setDigitalAccelRange(bufferInquiry[1]);
                mConfigByte0 = bufferInquiry[2] & 0xFF;
                mNChannels = bufferInquiry[3];
                mBufferSize = bufferInquiry[4];
                byte[] signalIdArray = new byte[mNChannels];
                System.arraycopy(bufferInquiry, 5, signalIdArray, 0, mNChannels);
                updateEnabledSensorsFromChannels(signalIdArray);
                interpretDataPacketFormat(mNChannels, signalIdArray);
                mInquiryResponseBytes = new byte[5 + mNChannels];
                System.arraycopy(bufferInquiry, 0, mInquiryResponseBytes, 0, mInquiryResponseBytes.length);
            } else if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                if (bufferInquiry.length >= 8) {
                    mPacketSize = mTimeStampPacketByteSize + bufferInquiry[6] * 2;
                    double samplingRate = convertSamplingRateBytesToFreq(bufferInquiry[0], bufferInquiry[1], getSamplingClockFreq());
                    setSamplingRateShimmer(samplingRate);
                    mNChannels = bufferInquiry[6];
                    mBufferSize = bufferInquiry[7];
                    mConfigByte0 = ((long) (bufferInquiry[2] & 0xFF) + ((long) (bufferInquiry[3] & 0xFF) << 8) + ((long) (bufferInquiry[4] & 0xFF) << 16) + ((long) (bufferInquiry[5] & 0xFF) << 24));
                    setDigitalAccelRange(((int) (mConfigByte0 & 0xC)) >> 2);
                    setGyroRange(((int) (mConfigByte0 & 196608)) >> 16);
                    setLSM303MagRange(((int) (mConfigByte0 & 14680064)) >> 21);
                    setLSM303DigitalAccelRate(((int) (mConfigByte0 & 0xF0)) >> 4);
                    setMPU9150GyroAccelRate(((int) (mConfigByte0 & 65280)) >> 8);
                    setMagRate(((int) (mConfigByte0 & 1835008)) >> 18);
                    setPressureResolution((((int) (mConfigByte0 >> 28)) & 3));
                    setGSRRange((((int) (mConfigByte0 >> 25)) & 7));
                    setInternalExpPower(((int) (mConfigByte0 >> 24)) & 1);
                    mInquiryResponseBytes = new byte[8 + mNChannels];
                    System.arraycopy(bufferInquiry, 0, mInquiryResponseBytes, 0, mInquiryResponseBytes.length);
                    if ((getLSM303DigitalAccelRate() == 2 && getSamplingRateShimmer() > 10)) {
                        setLowPowerAccelWR(true);
                    }
                    checkLowPowerGyro();
                    checkLowPowerMag();

                    if (bufferInquiry.length >= (8 + mNChannels)) {
                        byte[] signalIdArray = new byte[mNChannels];
                        System.arraycopy(bufferInquiry, 8, signalIdArray, 0, mNChannels);
                        updateEnabledSensorsFromChannels(signalIdArray);
                        if (mUseInfoMemConfigMethod && getFirmwareVersionCode() >= 6) {
                        } else {
                            setEnabledAndDerivedSensorsAndUpdateMaps(mEnabledSensors, mDerivedSensors);
                        }
                        interpretDataPacketFormat(mNChannels, signalIdArray);
                        checkExgResolutionFromEnabledSensorsVar();
                    }
                }
            } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                if (bufferInquiry.length >= 11) {
                    mPacketSize = mTimeStampPacketByteSize + bufferInquiry[9] * 2;
                    double samplingRate = convertSamplingRateBytesToFreq(bufferInquiry[0], bufferInquiry[1], getSamplingClockFreq());
                    setSamplingRateShimmer(samplingRate);
                    mNChannels = bufferInquiry[9];
                    mBufferSize = bufferInquiry[10];
                    mConfigByte0 = ((long) (bufferInquiry[2] & 0xFF) + ((long) (bufferInquiry[3] & 0xFF) << 8) + ((long) (bufferInquiry[4] & 0xFF) << 16) + ((long) (bufferInquiry[5] & 0xFF) << 24)
                            + ((long) (bufferInquiry[6] & 0xFF) << 32) + ((long) (bufferInquiry[7] & 0xFF) << 40) + ((long) (bufferInquiry[8] & 0xFF) << 48));
                    setDigitalAccelRange(((int) (mConfigByte0 & 0xC)) >> 2);
                    int gyroRange = (int) (((mConfigByte0 & 196608) >> 16) & 3);
                    int msbGyroRange = (int) ((mConfigByte0 >> 34) & 1);
                    gyroRange = gyroRange + (msbGyroRange << 2);
                    setGyroRange(gyroRange);
                    setAltMagRange(((int) (mConfigByte0 & 14680064)) >> 21);
                    setLIS2DW12DigitalAccelRate(((int) (mConfigByte0 & 0xF0)) >> 4);

                    setLowPowerAccelWR((getLIS2DW12DigitalAccelRate() == 1) ? true : false);
                    setLSM6DSVGyroAccelRate(((int) (mConfigByte0 & 65280)) >> 8);
                    checkLowPowerGyro();

                    int magSamplingRate = (int) ((mConfigByte0 >> 18) & 0x07);
                    int altMagSamplingRate = (int) ((mConfigByte0 >> 40) & 0x3F);
                    setMagRate(magSamplingRate);
                    setAltMagRate(altMagSamplingRate);

                    checkLowPowerMag();

                    setGSRRange((((int) (mConfigByte0 >> 25)) & 7));
                    setInternalExpPower(((int) (mConfigByte0 >> 24)) & 1);

                    int pressureResolution = (((int) (mConfigByte0 >> 28)) & 3);
                    int msbPressureResolution = (((int) (mConfigByte0 >> 32)) & 1);
                    pressureResolution = pressureResolution + (msbPressureResolution << 2);
                    setPressureResolution(pressureResolution);

                    mInquiryResponseBytes = new byte[11 + mNChannels];
                    System.arraycopy(bufferInquiry, 0, mInquiryResponseBytes, 0, mInquiryResponseBytes.length);

                    if (bufferInquiry.length >= (11 + mNChannels)) {
                        byte[] signalIdArray = new byte[mNChannels];
                        System.arraycopy(bufferInquiry, 11, signalIdArray, 0, mNChannels);
                        updateEnabledSensorsFromChannels(signalIdArray);
                        if (mUseInfoMemConfigMethod && getFirmwareVersionCode() >= 6) {
                        } else {
                            setEnabledAndDerivedSensorsAndUpdateMaps(mEnabledSensors, mDerivedSensors);
                        }
                        interpretDataPacketFormat(mNChannels, signalIdArray);
                        checkExgResolutionFromEnabledSensorsVar();
                    }
                }
            } else if (getHardwareVersion() == HW_ID.SHIMMER_SR30) {
                mPacketSize = mTimeStampPacketByteSize + bufferInquiry[2] * 2;
                setSamplingRateShimmer((double) 1024 / bufferInquiry[0]);
                setDigitalAccelRange(bufferInquiry[1]);
                mNChannels = bufferInquiry[2];
                mBufferSize = bufferInquiry[3];
                byte[] signalIdArray = new byte[mNChannels];
                System.arraycopy(bufferInquiry, 4, signalIdArray, 0, mNChannels);
                interpretDataPacketFormat(mNChannels, signalIdArray);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                disconnect();
            } catch (ShimmerException e) {
                e.printStackTrace();
            }
        }

    }

    @Deprecated
    protected void updateEnabledSensorsFromChannels(byte[] channels) {
        int enabledSensors = 0;
        for (int i = 0; i < channels.length; i++) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

                if (channels[i] == Configuration.Shimmer3.Channel.XAAccel || channels[i] == Configuration.Shimmer3.Channel.YAAccel || channels[i] == Configuration.Shimmer3.Channel.ZAAccel) {
                    enabledSensors = enabledSensors | SENSOR_ACCEL;
                }

                if (channels[i] == Configuration.Shimmer3.Channel.XDAccel || channels[i] == Configuration.Shimmer3.Channel.YDAccel || channels[i] == Configuration.Shimmer3.Channel.ZDAccel) {
                    enabledSensors = enabledSensors | SENSOR_DACCEL;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.XGyro || channels[i] == Configuration.Shimmer3.Channel.YGyro || channels[i] == Configuration.Shimmer3.Channel.ZGyro) {
                    enabledSensors = enabledSensors | SENSOR_GYRO;
                }

                if (channels[i] == Configuration.Shimmer3.Channel.XMag || channels[i] == Configuration.Shimmer3.Channel.YMag || channels[i] == Configuration.Shimmer3.Channel.ZMag) {
                    enabledSensors = enabledSensors | SENSOR_MAG;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.VBatt) {
                    enabledSensors = enabledSensors | SENSOR_BATT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc7) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A7;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc6) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A6;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc15) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A15;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc1) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A1;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc12) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A12;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc13) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A13;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc14) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A14;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc9) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A7;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc11) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A6;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.ExtAdc12) {
                    enabledSensors = enabledSensors | SENSOR_EXT_ADC_A15;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc17) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A1;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc10) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A12;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc15) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A13;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.IntAdc16) {
                    enabledSensors = enabledSensors | SENSOR_INT_ADC_A14;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.Pressure) {
                    enabledSensors = enabledSensors | SENSOR_BMPX80;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.Temperature) {
                    enabledSensors = enabledSensors | SENSOR_BMPX80;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.GsrRaw) {
                    enabledSensors = enabledSensors | SENSOR_GSR;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_1_STATUS) {
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_1_CH1_24BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG1_24BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_1_CH2_24BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG1_24BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_1_CH1_16BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG1_16BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_1_CH2_16BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG1_16BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_2_STATUS) {
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_2_CH1_24BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG2_24BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_2_CH2_24BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG2_24BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_2_CH1_16BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG2_16BIT;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.EXG_ADS1292R_2_CH2_16BIT) {
                    enabledSensors = enabledSensors | SENSOR_EXG2_16BIT;
                }
                if ((channels[i] == Configuration.Shimmer3.Channel.BridgeAmpHigh) || (channels[i] == Configuration.Shimmer3.Channel.BridgeAmpLow)) {
                    enabledSensors = enabledSensors | SENSOR_BRIDGE_AMP;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.XAlterAccel || channels[i] == Configuration.Shimmer3.Channel.YAlterAccel || channels[i] == Configuration.Shimmer3.Channel.ZAlterAccel) {
                    enabledSensors = enabledSensors | SENSOR_ALT_ACCEL;
                }
                if (channels[i] == Configuration.Shimmer3.Channel.XAlterMag || channels[i] == Configuration.Shimmer3.Channel.YAlterMag || channels[i] == Configuration.Shimmer3.Channel.ZAlterMag) {
                    enabledSensors = enabledSensors | SENSOR_ALT_MAG;
                }

            } else if (getHardwareVersion() == HW_ID.SHIMMER_2R) {
                if (channels[i] == Configuration.Shimmer2.Channel.XAccel || channels[i] == Configuration.Shimmer2.Channel.YAccel || channels[i] == Configuration.Shimmer2.Channel.ZAccel) {
                    enabledSensors = enabledSensors | SENSOR_ACCEL;
                }
                if (channels[i] == Configuration.Shimmer2.Channel.XGyro || channels[i] == Configuration.Shimmer2.Channel.YGyro || channels[i] == Configuration.Shimmer2.Channel.ZGyro) {
                    enabledSensors = enabledSensors | SENSOR_GYRO;
                }
                if (channels[i] == Configuration.Shimmer2.Channel.XMag || channels[i] == Configuration.Shimmer2.Channel.XMag || channels[i] == Configuration.Shimmer2.Channel.XMag) {
                    enabledSensors = enabledSensors | SENSOR_MAG;
                }
                if ((channels[i] == Configuration.Shimmer2.Channel.EcgLaLl) || (channels[i] == Configuration.Shimmer2.Channel.EcgRaLl)) {
                    enabledSensors = enabledSensors | SENSOR_ECG;
                } else if (channels[i] == Configuration.Shimmer2.Channel.Emg) {
                    enabledSensors = enabledSensors | SENSOR_EMG;
                } else if (channels[i] == Configuration.Shimmer2.Channel.AnExA0 && getPMux() == 0) {
                    enabledSensors = enabledSensors | SENSOR_EXP_BOARD_A0;
                } else if (channels[i] == Configuration.Shimmer2.Channel.AnExA7 && getPMux() == 0) {
                    enabledSensors = enabledSensors | SENSOR_EXP_BOARD_A7;
                } else if ((channels[i] == Configuration.Shimmer2.Channel.BridgeAmpHigh) || (channels[i] == Configuration.Shimmer2.Channel.BridgeAmpLow)) {
                    enabledSensors = enabledSensors | SENSOR_BRIDGE_AMP;
                } else if ((channels[i] == Configuration.Shimmer2.Channel.GsrRaw) || (channels[i] == Configuration.Shimmer2.Channel.GsrRes)) {
                    enabledSensors = enabledSensors | SENSOR_GSR;
                } else if (channels[i] == Configuration.Shimmer2.Channel.HeartRate) {
                    enabledSensors = enabledSensors | SENSOR_HEART;
                } else if (channels[i] == Configuration.Shimmer2.Channel.AnExA0 && getPMux() == 1) {
                    enabledSensors = enabledSensors | SENSOR_BATT;
                } else if (channels[i] == Configuration.Shimmer2.Channel.AnExA7 && getPMux() == 1) {
                    enabledSensors = enabledSensors | SENSOR_BATT;
                }
            }
        }
        mEnabledSensors = enabledSensors;
    }

    public String getBluetoothAddress() {
        return mMyBluetoothAddress;
    }

    @Deprecated
    public String getDeviceName() {
        return getShimmerUserAssignedName();
    }

    @Deprecated
    public void setDeviceName(String deviceName) {
        setShimmerUserAssignedName(deviceName);
    }

    public byte[] getRawInquiryResponse() {
        return mInquiryResponseBytes;
    }


    public byte[] getRawCalibrationParameters() {

        byte[] rawcal = new byte[1];
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(5);

                outputStream.write(getAccelCalRawParams().length);
                outputStream.write(getAccelCalRawParams());

                outputStream.write(getDigiAccelCalRawParams().length);
                outputStream.write(getDigiAccelCalRawParams());
                outputStream.write(getGyroCalRawParams().length);
                outputStream.write(getGyroCalRawParams());
                outputStream.write(getMagCalRawParams().length);
                outputStream.write(getMagCalRawParams());
                outputStream.write(getPressureRawCoefficients().length);
                outputStream.write(getPressureRawCoefficients());
                rawcal = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                outputStream.write(5);
                outputStream.write(getAccelCalRawParams().length);
                outputStream.write(getAccelCalRawParams());
                outputStream.write(getGyroCalRawParams().length);
                outputStream.write(getGyroCalRawParams());
                outputStream.write(getMagCalRawParams().length);
                outputStream.write(getMagCalRawParams());
                outputStream.write(mECGCalRawParams.length);
                outputStream.write(mECGCalRawParams);
                outputStream.write(mEMGCalRawParams.length);
                outputStream.write(mEMGCalRawParams);
                rawcal = outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            rawcal[0] = 0;
        }
        return rawcal;

    }

    @Deprecated
    public List<String> getListofEnabledSensors() {
        List<String> listofSensors = new ArrayList<String>();
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0) {
                listofSensors.add("Low Noise Accelerometer");
            }

            if ((mEnabledSensors & SENSOR_DACCEL) > 0) {
                listofSensors.add("Wide Range Accelerometer");
            }

        } else {
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0) {
                listofSensors.add("Accelerometer");
            }
        }

        if (((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
            listofSensors.add("Gyroscope");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0) {
            listofSensors.add("Magnetometer");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_GSR) > 0) {
            listofSensors.add("GSR");
        }
        if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
            if (((mEnabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                listofSensors.add("ECG");
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                listofSensors.add("EMG");
            }
        }
        if (((mEnabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
            listofSensors.add("Bridge Amplifier");
        }

        if (((mEnabledSensors & 0xFF00) & SENSOR_HEART) > 0) {
            listofSensors.add("Heart Rate");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A0) > 0 && (mEnabledSensors & SENSOR_BATT) == 0 && getHardwareVersion() != HW_ID.SHIMMER_3) {
            listofSensors.add("ExpBoard A0");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A7) > 0 && (mEnabledSensors & SENSOR_BATT) == 0 && getHardwareVersion() != HW_ID.SHIMMER_3) {
            listofSensors.add("ExpBoard A7");
        }
        if ((mEnabledSensors & SENSOR_BATT) > 0) {
            listofSensors.add("Battery Voltage");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_EXT_ADC_A7) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("External ADC A7");
        }
        if (((mEnabledSensors & 0xFF) & SENSOR_EXT_ADC_A6) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("External ADC A6");
        }
        if (((mEnabledSensors & 0xFFFF) & SENSOR_EXT_ADC_A15) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("External ADC A15");
        }
        if (((mEnabledSensors & 0xFFFF) & SENSOR_INT_ADC_A1) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("Internal ADC A1");
        }
        if (((mEnabledSensors & 0xFFFF) & SENSOR_INT_ADC_A12) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("Internal ADC A12");
        }
        if (((mEnabledSensors & 0xFFFFFF) & SENSOR_INT_ADC_A13) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("Internal ADC A13");
        }
        if (((mEnabledSensors & 0xFFFFFF) & SENSOR_INT_ADC_A14) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("Internal ADC A14");
        }
        if ((mEnabledSensors & SENSOR_BMPX80) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("Pressure");
        }
        if ((mEnabledSensors & SENSOR_EXG1_24BIT) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("EXG1");
        }
        if ((mEnabledSensors & SENSOR_EXG2_24BIT) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("EXG2");
        }
        if ((mEnabledSensors & SENSOR_EXG1_16BIT) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("EXG1 16Bit");
        }
        if ((mEnabledSensors & SENSOR_EXG2_16BIT) > 0 && getHardwareVersion() == HW_ID.SHIMMER_3) {
            listofSensors.add("EXG2 16Bit");
        }

        return listofSensors;
    }

    @Override
    public List<String[]> getListofEnabledChannelSignalsandFormats() {
        List<String[]> listofSignals = new ArrayList<String[]>();

        if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
            String[] channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLISECONDS};
            listofSignals.add(channel);
            channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.TIMESTAMP, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
            listofSignals.add(channel);
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0) {
                String unit = CHANNEL_UNITS.METER_PER_SECOND_SQUARE;
                if (mSensorMMA736x.mIsUsingDefaultLNAccelParam) {
                    unit += "*";
                }

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_X, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_Y, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_Z, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ACCEL_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

            }
            if (((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                String unit = CHANNEL_UNITS.DEGREES_PER_SECOND;
                if (mSensorShimmer2Gyro.mIsUsingDefaultGyroParam) {
                    unit += "*";
                }
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GYRO_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                String unit = CHANNEL_UNITS.LOCAL_FLUX;
                if (mSensorShimmer2Mag.mIsUsingDefaultMagParam) {
                    unit += "*";
                }
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_X, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_Y, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.MAG_Z, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GSR, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.KOHMS};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.GSR, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

            }
            if (((mEnabledSensors & 0xFF) & SENSOR_ECG) > 0) {

                String unit = CHANNEL_UNITS.MILLIVOLTS;
                if (mDefaultCalibrationParametersECG == true) {
                    unit += "*";
                }

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ECG_RA_LL, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ECG_RA_LL, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ECG_LA_LL, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.ECG_LA_LL, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

            }
            if (((mEnabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                String unit = CHANNEL_UNITS.MILLIVOLTS;
                if (mDefaultCalibrationParametersECG == true) {
                    unit += "*";
                }

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EMG, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EMG, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                String unit = CHANNEL_UNITS.MILLIVOLTS;
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_HIGH, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);

                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BRIDGE_AMP_LOW, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFF00) & SENSOR_HEART) > 0) {
                String unit = CHANNEL_UNITS.BEATS_PER_MINUTE;
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.HEART_RATE, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.HEART_RATE, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if ((((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A0) > 0) && getPMux() == 0) {
                String unit = CHANNEL_UNITS.MILLIVOLTS;
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EXP_BOARD_A0, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if ((((mEnabledSensors & 0xFF) & SENSOR_EXP_BOARD_A7) > 0 && getPMux() == 0)) {
                String unit = CHANNEL_UNITS.MILLIVOLTS;
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7, CHANNEL_TYPE.CAL.toString(), unit};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EXP_BOARD_A7, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFFFF) & SENSOR_BATT) > 0) {
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.REG, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.REG, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.MILLIVOLTS};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.BATTERY, CHANNEL_TYPE.UNCAL.toString(), CHANNEL_UNITS.NO_UNITS};
                listofSignals.add(channel);
            }
            if (((mEnabledSensors & 0xFF) & SENSOR_ACCEL) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_GYRO) > 0 && ((mEnabledSensors & 0xFF) & SENSOR_MAG) > 0 && is3DOrientationEnabled()) {
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EULER_9DOF_YAW, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EULER_9DOF_PITCH, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.EULER_9DOF_ROLL, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_W, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_X, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
                channel = new String[]{mShimmerUserAssignedName, Shimmer2.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z, CHANNEL_TYPE.CAL.toString(), CHANNEL_UNITS.LOCAL};
                listofSignals.add(channel);
            }

        } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            return super.getListofEnabledChannelSignalsandFormats();
        }


        if (mExtraSignalProperties != null) {
            listofSignals.addAll(mExtraSignalProperties);
        }

        listofSignals.addAll(getListofEnabledAlgorithmsSignalsandFormats());

        return listofSignals;
    }

    @Deprecated
    public void addAlgorithm(String key, AbstractAlgorithm aobj) {
        if (!doesAlgorithmAlreadyExist(aobj)) {
            mMapOfAlgorithmModules.put(key, aobj);
            String[] outputNameArray = aobj.getSignalOutputNameArray();
            String[] outputFormatArray = aobj.getSignalOutputFormatArray();
            String[] outputUnitArray = aobj.getSignalOutputUnitArray();

            for (int i = 0; i < outputNameArray.length; i++) {
                String[] prop = new String[4];
                prop[0] = mShimmerUserAssignedName;
                prop[1] = outputNameArray[i];
                prop[2] = outputFormatArray[i];
                prop[3] = outputUnitArray[i];
                addExtraSignalProperty(prop);
            }
        }
    }

    @Deprecated
    public void addExtraSignalProperty(String[] property) {
        if (mExtraSignalProperties == null) {
            mExtraSignalProperties = new ArrayList<String[]>();
        }
        mExtraSignalProperties.add(property);
    }

    @Deprecated
    public void clearExtraSignalProperties() {
        if (mExtraSignalProperties != null) {
            mExtraSignalProperties.clear();
        }
    }

    @Deprecated
    public void removeExtraSignalProperty(String[] property) {
        if (mExtraSignalProperties != null) {
            for (int i = mExtraSignalProperties.size() - 1; i > -1; i--) {
                String[] p = mExtraSignalProperties.get(i);
                if (p[0].equals(property[0]) && p[1].equals(property[1]) && p[2].equals(property[2]) && p[3].equals(property[3])) {
                    mExtraSignalProperties.remove(i);
                }

            }
        }
    }

    public int getRTCSetByBT() {
        return mRTCSetByBT;
    }

    public void setRTCSetByBT(int RTCSetByBT) {
        mRTCSetByBT = RTCSetByBT;
    }

    public long getRTCDifferenceInTicks() {
        return mRTCDifferenceInTicks;
    }

    public void setRTCDifferenceInTicks(long rtcOffset) {
        mRTCDifferenceInTicks = rtcOffset;
    }

    public boolean isRtcDifferenceSet() {
        return (mRTCDifferenceInTicks == 0) ? false : true;
    }

    public long getInitialTimeStampTicksSd() {
        return mInitialTimeStampTicksSd;
    }

    public void setInitialTimeStampTicksSd(long initialTimeStamp) {
        mInitialTimeStampTicksSd = initialTimeStamp;
    }

    public void setCurrentTimeStampCycle(double timeStampCycle) {
        mCurrentTimeStampCycle = timeStampCycle;
    }

    public double getCurrenTimeStampCycle() {
        return mCurrentTimeStampCycle;
    }

    public double getLastReceivedTimeStampTicksUnwrapped() {
        return mLastReceivedTimeStampTicksUnwrapped;
    }

    public void setLastReceivedTimeStampTicksUnwrapped(double lastReceivedTimeStampTicksUnwrapped) {
        mLastReceivedTimeStampTicksUnwrapped = lastReceivedTimeStampTicksUnwrapped;
    }

    public void updateTimestampByteLength() {
        if (getFirmwareVersionCode() >= 6) {
            mTimeStampPacketByteSize = 3;
        } else {//if (getFirmwareVersionCode()<6){
            mTimeStampPacketByteSize = 2;
        }
        mTimeStampTicksMaxValue = (int) Math.pow(2, 8 * mTimeStampPacketByteSize);
    }

    @Deprecated
    public String[] getListofSupportedSensors() {
        return getListofSupportedSensors(getHardwareVersion());
    }

    @Override
    protected double calcMaxSamplingRate() {
        double maxGUISamplingRate = super.calcMaxSamplingRate();

        if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
            maxGUISamplingRate = Math.min(1024.0, maxGUISamplingRate);
        } else if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            maxGUISamplingRate = Math.min(2048.0, maxGUISamplingRate);
        }
        return maxGUISamplingRate;
    }

    @Override
    protected void setSamplingRateSensors(double samplingRateShimmer) {
        super.setSamplingRateSensors(samplingRateShimmer);

        if (mShimmerVerObject.isShimmerGen3()) {
            setExGRateFromFreq(samplingRateShimmer);
        }
    }

    public boolean checkLowPowerMag() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Mag.checkLowPowerMag();
        } else if (isShimmerGen3()) {
            return mSensorLSM303.checkLowPowerMag();
        } else if (isShimmerGen3R()) {
            return mSensorLIS3MDL.checkLowPowerMag();
        }
        return false;
    }

    protected void set3DOrientation(boolean enable) {
        mIsOrientationEnabled = enable;
    }

    public boolean is3DOrientationEnabled() {
        return mIsOrientationEnabled;
    }


    protected void setupOrientation(int orientation, double samplingRate) {
        if (orientation == 1) {
            set3DOrientation(true);
            enableOnTheFlyGyroCal(true, (int) samplingRate, 1.2);
        } else {
            set3DOrientation(false);
            setOnTheFlyGyroCal(false);
        }
    }

    @Override
    public void setDefaultShimmerConfiguration() {
        if (getHardwareVersion() != -1) {

            setDefaultShimmerName();

            clearExgConfig(); // this currently must be called before sensorAndConfigMapsCreate() as it controls ExG booleans which affect EnabledSensors
            sensorAndConfigMapsCreate();

            if (getHardwareVersion() == HW_ID.SHIMMER_3) {
                if (getExpansionBoardId() == HW_ID_SR_CODES.SHIMMER_ECG_MD) {
                    setSensorEnabledState(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, true);
                } else {
                    setSensorEnabledState(Configuration.Shimmer3.SENSOR_ID.SHIMMER_ANALOG_ACCEL, true);
                    setSensorEnabledState(Configuration.Shimmer3.SENSOR_ID.SHIMMER_MPU9X50_GYRO, true);
                    setSensorEnabledState(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_MAG, true);
                    setSensorEnabledState(Configuration.Shimmer3.SENSOR_ID.SHIMMER_VBATT, true);

                    setIsAlgorithmEnabled(GyroOnTheFlyCalModule.GENERAL_ALGORITHM_NAME, true);
                }
            }

            mTrialName = DEFAULT_EXPERIMENT_NAME;

            mTrialNumberOfShimmers = 1;
            mTrialId = 0;

            setButtonStart(true);
            setShowErrorLedsRtc(true);
            setShowErrorLedsSd(true);

            setBluetoothBaudRate(9);

            setInternalExpPower(false);

            setExGResolution(1);
            setShimmer2rMagRate(0);

            setMasterShimmer(false);
            setSingleTouch(false);
            setTCXO(false);
            setLowBattAutoStop(false);

            setPacketSize(0);
            mConfigByte0 = 0;
            mNChannels = 0;
            mBufferSize = 0;
            mSyncBroadcastInterval = 0;
            setInitialTimeStampTicksSd(0);

            setShimmerAndSensorsSamplingRate(51.2);

            setLSM303MagRange(getMagRange());
            setAccelRange(getAccelRange());
            setGyroRange(getGyroRange());

            syncNodesList.clear();
        }
    }

    private void setDefaultShimmerName() {
        mShimmerUserAssignedName = DEFAULT_SHIMMER_NAME;

        String macParsed = getMacIdParsed();
        if (macParsed.isEmpty()) {
            return;
        }

        if (mShimmerUserAssignedName.length() > 7) { // 12 char max minus "_XXXX"
            mShimmerUserAssignedName = mShimmerUserAssignedName.substring(0, 7);
        }
        mShimmerUserAssignedName += "_" + macParsed;
        return;
    }

    @Override
    public void configBytesParse(byte[] configBytes, COMMUNICATION_TYPE commType) {
        String shimmerName = "";

        mInfoMemBytesOriginal = configBytes;

        if (!ConfigByteLayout.checkConfigBytesValid(configBytes)) {
            setDefaultShimmerConfiguration();
            mShimmerUsingConfigFromInfoMem = false;

            mConfigBytes = configBytes;
        } else {
            ConfigByteLayoutShimmer3 configByteLayoutCast = (ConfigByteLayoutShimmer3) mConfigByteLayout;
            mShimmerUsingConfigFromInfoMem = true;
            mConfigBytes = configBytes;
            createInfoMemLayoutObjectIfNeeded();

            byte samplingRateMSB = (byte) (configBytes[configByteLayoutCast.idxShimmerSamplingRate + 1] & configByteLayoutCast.maskShimmerSamplingRate);
            byte samplingRateLSB = (byte) (configBytes[configByteLayoutCast.idxShimmerSamplingRate] & configByteLayoutCast.maskShimmerSamplingRate);
            double samplingRate = convertSamplingRateBytesToFreq(samplingRateLSB, samplingRateMSB, getSamplingClockFreq());
            setShimmerAndSensorsSamplingRate(samplingRate);

            parseEnabledDerivedSensorsForMaps(configByteLayoutCast, configBytes);

            mBufferSize = (int) (configBytes[configByteLayoutCast.idxBufferSize] & configByteLayoutCast.maskBufferSize);

            setGSRRange((configBytes[configByteLayoutCast.idxConfigSetupByte3] >> configByteLayoutCast.bitShiftGSRRange) & configByteLayoutCast.maskGSRRange);
            mInternalExpPower = (configBytes[configByteLayoutCast.idxConfigSetupByte3] >> configByteLayoutCast.bitShiftEXPPowerEnable) & configByteLayoutCast.maskEXPPowerEnable;

            System.arraycopy(configBytes, configByteLayoutCast.idxEXGADS1292RChip1Config1, mEXG1RegisterArray, 0, 10);
            System.arraycopy(configBytes, configByteLayoutCast.idxEXGADS1292RChip2Config1, mEXG2RegisterArray, 0, 10);
            exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);

            mBluetoothBaudRate = configBytes[configByteLayoutCast.idxBtCommBaudRate] & configByteLayoutCast.maskBaudRate;

            byte[] bufferCalibrationParameters = new byte[configByteLayoutCast.lengthGeneralCalibrationBytes];


            byte[] shimmerNameBuffer = new byte[configByteLayoutCast.lengthShimmerName];
            System.arraycopy(configBytes, configByteLayoutCast.idxSDShimmerName, shimmerNameBuffer, 0, configByteLayoutCast.lengthShimmerName);
            for (byte b : shimmerNameBuffer) {
                if (!UtilShimmer.isAsciiPrintable((char) b)) {
                    break;
                }
                shimmerName += (char) b;
            }

            byte[] experimentNameBuffer = new byte[configByteLayoutCast.lengthExperimentName];
            System.arraycopy(configBytes, configByteLayoutCast.idxSDEXPIDName, experimentNameBuffer, 0, configByteLayoutCast.lengthExperimentName);
            String experimentName = "";
            for (byte b : experimentNameBuffer) {
                if (!UtilShimmer.isAsciiPrintable((char) b)) {
                    break;
                }
                experimentName += (char) b;
            }
            mTrialName = new String(experimentName);

            int bitShift = (configByteLayoutCast.lengthConfigTimeBytes - 1) * 8;
            mConfigTime = 0;
            for (int x = 0; x < configByteLayoutCast.lengthConfigTimeBytes; x++) {
                mConfigTime += (((long) (configBytes[configByteLayoutCast.idxSDConfigTime0 + x] & 0xFF)) << bitShift);
                bitShift -= 8;
            }

            if (isSupportedSdLogSync()) {
                mTrialId = configBytes[configByteLayoutCast.idxSDMyTrialID] & 0xFF;
                mTrialNumberOfShimmers = configBytes[configByteLayoutCast.idxSDNumOfShimmers] & 0xFF;
            }

            if (getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.LOGANDSTREAM || getFirmwareIdentifier() == FW_ID.STROKARE) {
                mButtonStart = (configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftButtonStart) & configByteLayoutCast.maskButtonStart;
                mDisableBluetooth = (configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftDisableBluetooth) & configByteLayoutCast.maskDisableBluetooth;
                setShowErrorLedsRtc((configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftShowErrorLedsRwc) & configByteLayoutCast.maskShowErrorLedsRwc);
                setShowErrorLedsSd((configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftShowErrorLedsSd) & configByteLayoutCast.maskShowErrorLedsSd);
            }

            if (isSupportedSdLogSync()) {
                mSyncWhenLogging = (configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftTimeSyncWhenLogging) & configByteLayoutCast.maskTimeSyncWhenLogging;
                mMasterShimmer = (configBytes[configByteLayoutCast.idxSDExperimentConfig0] >> configByteLayoutCast.bitShiftMasterShimmer) & configByteLayoutCast.maskTimeMasterShimmer;
                mSingleTouch = (configBytes[configByteLayoutCast.idxSDExperimentConfig1] >> configByteLayoutCast.bitShiftSingleTouch) & configByteLayoutCast.maskTimeSingleTouch;

                mSyncBroadcastInterval = (int) (configBytes[configByteLayoutCast.idxSDBTInterval] & 0xFF);

                setTrialDurationEstimatedInSecs((int) (configBytes[configByteLayoutCast.idxEstimatedExpLengthLsb] & 0xFF) + (((int) (configBytes[configByteLayoutCast.idxEstimatedExpLengthMsb] & 0xFF)) << 8));
                setTrialDurationMaximumInSecs((int) (configBytes[configByteLayoutCast.idxMaxExpLengthLsb] & 0xFF) + (((int) (configBytes[configByteLayoutCast.idxMaxExpLengthMsb] & 0xFF)) << 8));
            }

            if (getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.LOGANDSTREAM || getFirmwareIdentifier() == FW_ID.STROKARE) {
                mTCXO = (configBytes[configByteLayoutCast.idxSDExperimentConfig1] >> configByteLayoutCast.bitShiftTCX0) & configByteLayoutCast.maskTimeTCX0;
            }

            if (isVerCompatibleWithAnyOf(Configuration.Shimmer3.configOptionLowPowerAutoStop.mListOfCompatibleVersionInfo)) {
                setLowBattAutoStop((configBytes[configByteLayoutCast.idxSDExperimentConfig1] >> configByteLayoutCast.bitShiftLowBattStop) & configByteLayoutCast.maskLowBattStop);
            }

            byte[] macIdBytes = new byte[configByteLayoutCast.lengthMacIdBytes];
            System.arraycopy(configBytes, configByteLayoutCast.idxMacAddress, macIdBytes, 0, configByteLayoutCast.lengthMacIdBytes);
            mMacIdFromInfoMem = UtilShimmer.bytesToHexString(macIdBytes);


            if (((configBytes[configByteLayoutCast.idxSDConfigDelayFlag] >> configByteLayoutCast.bitShiftSDCfgFileWriteFlag) & configByteLayoutCast.maskSDCfgFileWriteFlag) == configByteLayoutCast.maskSDCfgFileWriteFlag) {
                mConfigFileCreationFlag = true;
            } else {
                mConfigFileCreationFlag = false;
            }


            if (isSupportedSdLogSync()) {
                syncNodesList.clear();
                for (int i = 0; i < configByteLayoutCast.maxNumOfExperimentNodes; i++) {
                    System.arraycopy(configBytes, configByteLayoutCast.idxNode0 + (i * configByteLayoutCast.lengthMacIdBytes), macIdBytes, 0, configByteLayoutCast.lengthMacIdBytes);
                    if ((Arrays.equals(macIdBytes, configByteLayoutCast.invalidMacId)) || (Arrays.equals(macIdBytes, configByteLayoutCast.invalidMacId))) {
                        break;
                    } else {
                        syncNodesList.add(UtilShimmer.bytesToHexString(macIdBytes));
                    }
                }
            }

            for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
                abstractSensor.configBytesParse(this, mConfigBytes, commType);
            }


        }

        checkAndCorrectShimmerName(shimmerName);
    }

    private void parseEnabledDerivedSensorsForMaps(ConfigByteLayoutShimmer3 infoMemLayoutCast, byte[] configBytes) {
        mEnabledSensors = ((long) configBytes[infoMemLayoutCast.idxSensors0] & infoMemLayoutCast.maskSensors) << infoMemLayoutCast.byteShiftSensors0;
        mEnabledSensors += ((long) configBytes[infoMemLayoutCast.idxSensors1] & infoMemLayoutCast.maskSensors) << infoMemLayoutCast.byteShiftSensors1;
        mEnabledSensors += ((long) configBytes[infoMemLayoutCast.idxSensors2] & infoMemLayoutCast.maskSensors) << infoMemLayoutCast.byteShiftSensors2;

        if (mShimmerVerObject.isSupportedMpl()) {
            mEnabledSensors += ((long) configBytes[infoMemLayoutCast.idxSensors3] & 0xFF) << infoMemLayoutCast.bitShiftSensors3;
            mEnabledSensors += ((long) configBytes[infoMemLayoutCast.idxSensors4] & 0xFF) << infoMemLayoutCast.bitShiftSensors4;
        }

        mDerivedSensors = (long) 0;
        if ((infoMemLayoutCast.idxDerivedSensors0 > 0) && (configBytes[infoMemLayoutCast.idxDerivedSensors0] != (byte) infoMemLayoutCast.maskDerivedChannelsByte)
                && (infoMemLayoutCast.idxDerivedSensors1 > 0) && (configBytes[infoMemLayoutCast.idxDerivedSensors1] != (byte) infoMemLayoutCast.maskDerivedChannelsByte)) {

            mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors0] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors0;
            mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors1] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors1;

            if (infoMemLayoutCast.idxDerivedSensors2 > 0) {
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors2] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors2;
            }

            if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors3] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors3;
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors4] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors4;
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors5] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors5;
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors6] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors6;
                mDerivedSensors |= ((long) configBytes[infoMemLayoutCast.idxDerivedSensors7] & infoMemLayoutCast.maskDerivedChannelsByte) << infoMemLayoutCast.byteShiftDerivedSensors7;
            }
        }

        setEnabledAndDerivedSensorsAndUpdateMaps(mEnabledSensors, mDerivedSensors);
    }

    @Override
    public byte[] configBytesGenerate(boolean generateForWritingToShimmer, COMMUNICATION_TYPE commType) {
        ConfigByteLayoutShimmer3 configByteLayoutCast = new ConfigByteLayoutShimmer3(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), HW_ID.SHIMMER_3);

        if (mShimmerVerObject.mHardwareVersion == HW_ID.UNKNOWN) {

        } else {
            configByteLayoutCast = new ConfigByteLayoutShimmer3(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), mShimmerVerObject.mHardwareVersion);
        }

        byte[] configByteBackup = mConfigBytes.clone();

        mConfigBytes = configByteLayoutCast.createConfigByteArrayEmpty(mConfigBytes.length);

        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            if (!generateForWritingToShimmer) {
                System.arraycopy(configByteBackup, 0, mConfigBytes, 0, (configByteBackup.length > mConfigBytes.length) ? mConfigBytes.length : configByteBackup.length);
            }

            byte[] samplingRateBytes = convertSamplingRateFreqToBytes(getSamplingRateShimmer(), getSamplingClockFreq());
            mConfigBytes[configByteLayoutCast.idxShimmerSamplingRate] = samplingRateBytes[0];
            mConfigBytes[configByteLayoutCast.idxShimmerSamplingRate + 1] = samplingRateBytes[1];

            mConfigBytes[configByteLayoutCast.idxBufferSize] = (byte) 1;//(byte) (mBufferSize & mInfoMemLayout.maskBufferSize);

            checkExgResolutionFromEnabledSensorsVar();
            refreshEnabledSensorsFromSensorMap();
            mConfigBytes[configByteLayoutCast.idxSensors0] = (byte) ((mEnabledSensors >> configByteLayoutCast.byteShiftSensors0) & configByteLayoutCast.maskSensors);
            mConfigBytes[configByteLayoutCast.idxSensors1] = (byte) ((mEnabledSensors >> configByteLayoutCast.byteShiftSensors1) & configByteLayoutCast.maskSensors);
            mConfigBytes[configByteLayoutCast.idxSensors2] = (byte) ((mEnabledSensors >> configByteLayoutCast.byteShiftSensors2) & configByteLayoutCast.maskSensors);

            mConfigBytes[configByteLayoutCast.idxConfigSetupByte0] = (byte) (0x00);
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte1] = (byte) (0x00);
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte2] = (byte) (0x00);
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte3] = (byte) (0x00);

            mConfigBytes[configByteLayoutCast.idxConfigSetupByte3] |= (byte) ((getGSRRange() & configByteLayoutCast.maskGSRRange) << configByteLayoutCast.bitShiftGSRRange);
            checkIfInternalExpBrdPowerIsNeeded();
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte3] |= (byte) ((mInternalExpPower & configByteLayoutCast.maskEXPPowerEnable) << configByteLayoutCast.bitShiftEXPPowerEnable);

            exgBytesGetFromConfig();
            System.arraycopy(mEXG1RegisterArray, 0, mConfigBytes, configByteLayoutCast.idxEXGADS1292RChip1Config1, 10);
            System.arraycopy(mEXG2RegisterArray, 0, mConfigBytes, configByteLayoutCast.idxEXGADS1292RChip2Config1, 10);

            mConfigBytes[configByteLayoutCast.idxBtCommBaudRate] = (byte) (mBluetoothBaudRate & configByteLayoutCast.maskBaudRate);


            if ((configByteLayoutCast.idxDerivedSensors0 > 0) && (configByteLayoutCast.idxDerivedSensors1 > 0)) {
                mConfigBytes[configByteLayoutCast.idxDerivedSensors0] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors0) & configByteLayoutCast.maskDerivedChannelsByte);
                mConfigBytes[configByteLayoutCast.idxDerivedSensors1] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors1) & configByteLayoutCast.maskDerivedChannelsByte);
                if (configByteLayoutCast.idxDerivedSensors2 > 0) {
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors2] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors2) & configByteLayoutCast.maskDerivedChannelsByte);
                }

                if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors3] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors3) & configByteLayoutCast.maskDerivedChannelsByte);
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors4] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors4) & configByteLayoutCast.maskDerivedChannelsByte);
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors5] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors5) & configByteLayoutCast.maskDerivedChannelsByte);
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors6] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors6) & configByteLayoutCast.maskDerivedChannelsByte);
                    mConfigBytes[configByteLayoutCast.idxDerivedSensors7] = (byte) ((mDerivedSensors >> configByteLayoutCast.byteShiftDerivedSensors7) & configByteLayoutCast.maskDerivedChannelsByte);
                }
            }


            for (int i = 0; i < configByteLayoutCast.lengthShimmerName; i++) {
                if (i < mShimmerUserAssignedName.length()) {
                    mConfigBytes[configByteLayoutCast.idxSDShimmerName + i] = (byte) mShimmerUserAssignedName.charAt(i);
                } else {
                    mConfigBytes[configByteLayoutCast.idxSDShimmerName + i] = (byte) 0xFF;
                }
            }

            for (int i = 0; i < configByteLayoutCast.lengthExperimentName; i++) {
                if (i < mTrialName.length()) {
                    mConfigBytes[configByteLayoutCast.idxSDEXPIDName + i] = (byte) mTrialName.charAt(i);
                } else {
                    mConfigBytes[configByteLayoutCast.idxSDEXPIDName + i] = (byte) 0xFF;
                }
            }

            mConfigBytes[configByteLayoutCast.idxSDConfigTime0] = (byte) ((mConfigTime >> configByteLayoutCast.bitShiftSDConfigTime0) & 0xFF);
            mConfigBytes[configByteLayoutCast.idxSDConfigTime1] = (byte) ((mConfigTime >> configByteLayoutCast.bitShiftSDConfigTime1) & 0xFF);
            mConfigBytes[configByteLayoutCast.idxSDConfigTime2] = (byte) ((mConfigTime >> configByteLayoutCast.bitShiftSDConfigTime2) & 0xFF);
            mConfigBytes[configByteLayoutCast.idxSDConfigTime3] = (byte) ((mConfigTime >> configByteLayoutCast.bitShiftSDConfigTime3) & 0xFF);

            if (isSupportedSdLogSync()) {
                mConfigBytes[configByteLayoutCast.idxSDMyTrialID] = (byte) (mTrialId & 0xFF);

                mConfigBytes[configByteLayoutCast.idxSDNumOfShimmers] = (byte) (mTrialNumberOfShimmers & 0xFF);
            }

            if (getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.LOGANDSTREAM || getFirmwareIdentifier() == FW_ID.STROKARE) {
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] = (byte) ((mButtonStart & configByteLayoutCast.maskButtonStart) << configByteLayoutCast.bitShiftButtonStart);
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((mDisableBluetooth & configByteLayoutCast.maskDisableBluetooth) << configByteLayoutCast.bitShiftDisableBluetooth);
                if (this.isOverrideShowErrorLedsRtc) {
                    mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((configByteLayoutCast.maskShowErrorLedsRwc) << configByteLayoutCast.bitShiftShowErrorLedsRwc);
                } else {
                    mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((mShowErrorLedsRtc & configByteLayoutCast.maskShowErrorLedsRwc) << configByteLayoutCast.bitShiftShowErrorLedsRwc);
                }
                if (this.isOverrideShowErrorLedsSd) {
                    mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((configByteLayoutCast.maskShowErrorLedsSd) << configByteLayoutCast.bitShiftShowErrorLedsSd);
                } else {
                    mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((mShowErrorLedsSd & configByteLayoutCast.maskShowErrorLedsSd) << configByteLayoutCast.bitShiftShowErrorLedsSd);
                }
            }

            mConfigBytes[configByteLayoutCast.idxSDExperimentConfig1] = 0;

            if (isSupportedSdLogSync()) {
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((mSyncWhenLogging & configByteLayoutCast.maskTimeSyncWhenLogging) << configByteLayoutCast.bitShiftTimeSyncWhenLogging);
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig0] |= (byte) ((mMasterShimmer & configByteLayoutCast.maskTimeMasterShimmer) << configByteLayoutCast.bitShiftMasterShimmer);

                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig1] |= (byte) ((mSingleTouch & configByteLayoutCast.maskTimeSingleTouch) << configByteLayoutCast.bitShiftSingleTouch);

                mConfigBytes[configByteLayoutCast.idxSDBTInterval] = (byte) (mSyncBroadcastInterval & 0xFF);

                mConfigBytes[configByteLayoutCast.idxEstimatedExpLengthLsb] = (byte) ((getTrialDurationEstimatedInSecs() >> 0) & 0xFF);
                mConfigBytes[configByteLayoutCast.idxEstimatedExpLengthMsb] = (byte) ((getTrialDurationEstimatedInSecs() >> 8) & 0xFF);
                mConfigBytes[configByteLayoutCast.idxMaxExpLengthLsb] = (byte) ((getTrialDurationMaximumInSecs() >> 0) & 0xFF);
                mConfigBytes[configByteLayoutCast.idxMaxExpLengthMsb] = (byte) ((getTrialDurationMaximumInSecs() >> 8) & 0xFF);
            }

            if (getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.LOGANDSTREAM || getFirmwareIdentifier() == FW_ID.STROKARE) {
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig1] |= (byte) ((mTCXO & configByteLayoutCast.maskTimeTCX0) << configByteLayoutCast.bitShiftTCX0);
            }

            if (isVerCompatibleWithAnyOf(Configuration.Shimmer3.configOptionLowPowerAutoStop.mListOfCompatibleVersionInfo)) {
                mConfigBytes[configByteLayoutCast.idxSDExperimentConfig1] |= (byte) ((isLowBattAutoStop() ? 1 : 0 & configByteLayoutCast.maskLowBattStop) << configByteLayoutCast.bitShiftLowBattStop);
            }

            if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM || getFirmwareIdentifier() == FW_ID.SDLOG || getFirmwareIdentifier() == FW_ID.STROKARE) {
                if (generateForWritingToShimmer) {
                    System.arraycopy(configByteLayoutCast.invalidMacId, 0, mConfigBytes, configByteLayoutCast.idxMacAddress, configByteLayoutCast.lengthMacIdBytes);

                    mConfigBytes[configByteLayoutCast.idxSDConfigDelayFlag] = 0;
                    byte configFileWriteBit = (byte) (mConfigFileCreationFlag ? (configByteLayoutCast.maskSDCfgFileWriteFlag << configByteLayoutCast.bitShiftSDCfgFileWriteFlag) : 0x00);
                    mConfigBytes[configByteLayoutCast.idxSDConfigDelayFlag] |= configFileWriteBit;

                    mConfigBytes[configByteLayoutCast.idxSDConfigDelayFlag] |= configByteLayoutCast.bitShiftSDCfgFileWriteFlag;

                }
            }

            if (isSupportedSdLogSync()) {
                for (int i = 0; i < configByteLayoutCast.maxNumOfExperimentNodes; i++) {
                    byte[] macIdArray;
                    if ((syncNodesList.size() > 0) && (i < syncNodesList.size()) && (mSyncWhenLogging > 0)) {
                        macIdArray = UtilShimmer.hexStringToByteArray(syncNodesList.get(i));
                    } else {
                        macIdArray = configByteLayoutCast.invalidMacId;
                    }
                    System.arraycopy(macIdArray, 0, mConfigBytes, configByteLayoutCast.idxNode0 + (i * configByteLayoutCast.lengthMacIdBytes), configByteLayoutCast.lengthMacIdBytes);
                }
            }


            for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
                abstractSensor.configBytesGenerate(this, mConfigBytes, commType);
            }
        }

        if (getFirmwareIdentifier() == FW_ID.STROKARE) {
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte0] &= ~((byte) (configByteLayoutCast.maskLSM303DLHCAccelSamplingRate << configByteLayoutCast.bitShiftLSM303DLHCAccelSamplingRate));
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte0] |= (byte) ((5 & configByteLayoutCast.maskLSM303DLHCAccelSamplingRate) << configByteLayoutCast.bitShiftLSM303DLHCAccelSamplingRate);
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte1] &= ~((byte) (configByteLayoutCast.maskMPU9150AccelGyroSamplingRate << configByteLayoutCast.bitShiftMPU9150AccelGyroSamplingRate));
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte1] |= (byte) ((155 & configByteLayoutCast.maskMPU9150AccelGyroSamplingRate) << configByteLayoutCast.bitShiftMPU9150AccelGyroSamplingRate);
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte2] &= ~((byte) (configByteLayoutCast.maskLSM303DLHCMagSamplingRate << configByteLayoutCast.bitShiftLSM303DLHCMagSamplingRate));
            mConfigBytes[configByteLayoutCast.idxConfigSetupByte2] |= (byte) ((6 & configByteLayoutCast.maskLSM303DLHCMagSamplingRate) << configByteLayoutCast.bitShiftLSM303DLHCMagSamplingRate);
        }

        return mConfigBytes;
    }

    public void setConfigFileCreationFlag(boolean state) {
        mConfigFileCreationFlag = state;
    }

    @Override
    public void handleSpecCasesUpdateEnabledSensors() {
        updateEnabledSensorsFromExgResolution();
    }

    @Override
    public void sensorAndConfigMapsCreate() {
        createMapOfSensorClasses();

        super.sensorAndConfigMapsCreateCommon();
    }

    private void updateSensorMapChannelsFromChannelMap(LinkedHashMap<Integer, SensorDetails> sensorMap) {
        Iterator<SensorDetails> iterator = sensorMap.values().iterator();
        while (iterator.hasNext()) {
            SensorDetails sensorDetails = iterator.next();
            for (String channelMapKey : sensorDetails.mSensorDetailsRef.mListOfChannelsRef) {
                ChannelDetails channelDetails = mChannelMap.get(channelMapKey);
                if (channelDetails != null) {
                    sensorDetails.mListOfChannels.add(channelDetails);
                }
            }
        }
    }

    private void createMapOfSensorClasses() {
        mMapOfSensorClasses = new LinkedHashMap<SENSORS, AbstractSensor>();

        if (isShimmerGen2()) {
            mSensorMMA736x = new SensorMMA736x(this);
            addSensorClass(mSensorMMA736x);

            mSensorShimmer2Mag = new SensorShimmer2Mag(this);
            addSensorClass(mSensorShimmer2Mag);

            mSensorShimmer2Gyro = new SensorShimmer2Gyro(this);
            addSensorClass(mSensorShimmer2Gyro);
        } else if (isShimmerGenGq()) {
            addSensorClass(SENSORS.GSR, new SensorGSR(mShimmerVerObject));
            addSensorClass(SENSORS.ECG_TO_HR, new SensorECGToHRFw(mShimmerVerObject));
        } else if (isShimmerGen3()) {
            if (isSupportedNoImuSensors()) {

            } else if (isSupportedNewImuSensors()) {
                mSensorBMPX80 = new SensorBMP280(this);
                addSensorClass(mSensorBMPX80);

                mSensorLSM303 = new SensorLSM303AH(this);
                addSensorClass(mSensorLSM303);

                mSensorKionixAccel = new SensorKionixKXTC92050(this);
                addSensorClass(mSensorKionixAccel);

                mSensorMpu9x50 = new SensorMPU9250(this);
                addSensorClass(mSensorMpu9x50);


            } else {
                mSensorBMPX80 = new SensorBMP180(this);
                addSensorClass(mSensorBMPX80);

                mSensorLSM303 = new SensorLSM303DLHC(this);
                addSensorClass(mSensorLSM303);

                mSensorKionixAccel = new SensorKionixKXRB52042(this);
                addSensorClass(mSensorKionixAccel);

                mSensorMpu9x50 = new SensorMPU9150(this);
                addSensorClass(mSensorMpu9x50);

            }

        } else if (isShimmerGen3R()) {
            mSensorBMPX80 = new SensorBMP390(this);
            addSensorClass(mSensorBMPX80);

            mSensorLSM6DSV = new SensorLSM6DSV(this);
            addSensorClass(mSensorLSM6DSV);

            if (isShimmer3RwithHighGAccelSupport()) {
                mSensorADXL371 = new SensorADXL371(this);
                addSensorClass(mSensorADXL371);
            }

            mSensorLIS2DW12 = new SensorLIS2DW12(this);
            addSensorClass(mSensorLIS2DW12);

            if (isShimmer3RwithAltMagSupport()) {
                mSensorLIS3MDL = new SensorLIS3MDL(this);
                addSensorClass(mSensorLIS3MDL);
            }

            mSensorLIS2MDL = new SensorLIS2MDL(this);
            addSensorClass(mSensorLIS2MDL);
        }
    }

    @Override
    protected void handleSpecialCasesAfterSensorMapCreate() {
        super.handleSpecialCasesAfterSensorMapCreate();

        if (getHardwareVersion() != -1) {
            if (isShimmerGen2()) {
                Map<Integer, SensorDetailsRef> sensorMapRef = Configuration.Shimmer2.mSensorMapRef;
                for (Integer key : sensorMapRef.keySet()) {
                    mSensorMap.put(key, new SensorDetails(false, 0, sensorMapRef.get(key)));
                }
                updateSensorMapChannelsFromChannelMap(mSensorMap);
            } else if (isShimmerGen3() || isShimmerGen3R()
                    || isShimmerGenGq()) { // need isShimmerGenGq() here for parsing GQ data through ShimmerSDLog
                if (isShimmerGen3R()) {
                    mChannelMap.putAll(Configuration.Shimmer3.mChannelMapRef3r);
                } else {
                    mChannelMap.putAll(Configuration.Shimmer3.mChannelMapRef);
                }

                if (isShimmerGenGq()) {
                    mChannelMap.remove(SensorGSR.ObjectClusterSensorName.GSR_RESISTANCE);
                    mChannelMap.remove(SensorGSR.ObjectClusterSensorName.GSR_ADC_VALUE);
                    mChannelMap.remove(SensorGSR.ObjectClusterSensorName.GSR_CONDUCTANCE);
                    mChannelMap.remove(SensorGSR.ObjectClusterSensorName.GSR_RANGE);

                    mChannelMap.put(SensorGSR.channelGsrMicroSiemensGq.mObjectClusterName, SensorGSR.channelGsrMicroSiemensGq);

                    mChannelMap.remove(SensorEXG.ObjectClusterSensorName.ECG_LA_RA_24BIT);
                    mChannelMap.putAll(SensorEXG.mChannelMapRefGq);
                }

                if (getFirmwareVersionCode() >= 6) {
                    mChannelMap.remove(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP);
                    mChannelMap.put(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP, SensorShimmerClock.channelShimmerClock3byte);
                }

                if (isShimmerGen3() || isShimmerGen3R()) {
                    mSensorMap.putAll(createSensorMapShimmer3());
                } else if (isShimmerGenGq()) {
                    LinkedHashMap<Integer, SensorDetails> sensorMap = new LinkedHashMap<Integer, SensorDetails>();
                    sensorMap.put(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP, new SensorDetails(false, 0, SensorShimmerClock.sensorShimmerClock));
                    sensorMap.put(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, new SensorDetails(false, 0, SensorEXG.sDRefEcgGq));
                    updateSensorMapChannelsFromChannelMap(sensorMap);
                    mSensorMap.putAll(sensorMap);
                }

                createSensorGroupMapShimmer3();

                if (getExpansionBoardId() == HW_ID_SR_CODES.SHIMMER_ECG_MD) {
                    mSensorGroupingMap.remove(Configuration.Shimmer3.LABEL_SENSOR_TILE.EXTERNAL_EXPANSION_ADC.ordinal());
                }

                createConfigOptionMapShimmer3();

            } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {

                Map<Integer, SensorDetailsRef> sensorMapRef = Configuration.ShimmerGqBle.mSensorMapRef;
                for (Integer key : sensorMapRef.keySet()) {
                    mSensorMap.put(key, new SensorDetails(false, 0, sensorMapRef.get(key)));
                }

                mSensorGroupingMap.putAll(Configuration.ShimmerGqBle.mSensorGroupingMapRef);
                mConfigOptionsMapSensors.putAll(Configuration.ShimmerGqBle.mConfigOptionsMapRef);

                updateSensorMapChannelsFromChannelMap(mSensorMap);
            }
        }

        SensorEXG.updateSensorMapForExgResolution(this, getExGResolution());
    }

    @Override
    public void generateParserMap() {
        SensorEXG.updateSensorMapForExgResolution(this, getExGResolution());
        super.generateParserMap();
    }

    private LinkedHashMap<Integer, SensorDetails> createSensorMapShimmer3() {
        LinkedHashMap<Integer, SensorDetails> sensorMap = new LinkedHashMap<Integer, SensorDetails>();

        ConfigByteLayout infoMemLayout = getConfigByteLayout();
        if (infoMemLayout != null) {
            Map<Integer, SensorDetailsRef> sensorMapRef;
            if (isShimmerGen3R()) {
                sensorMapRef = Configuration.Shimmer3.mSensorMapRef3r;
            } else {
                sensorMapRef = Configuration.Shimmer3.mSensorMapRef;
            }

            for (Integer key : sensorMapRef.keySet()) {

                int derivedChannelBitmapID = 0;
                if (infoMemLayout instanceof ConfigByteLayoutShimmer3) {
                    ConfigByteLayoutShimmer3 infoMemLayoutCast = (ConfigByteLayoutShimmer3) infoMemLayout;

                    if (key == Configuration.Shimmer3.SENSOR_ID.SHIMMER_RESISTANCE_AMP) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelResAmp;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_SKIN_TEMPERATURE_PROBE) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelSkinTemp;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg_ADC12ADC13;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A13) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg_ADC12ADC13;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg1_ADC12ADC13;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A13) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg1_ADC12ADC13;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg2_ADC1ADC14;
                    } else if (key == Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A14) {
                        derivedChannelBitmapID = infoMemLayoutCast.maskDerivedChannelPpg2_ADC1ADC14;
                    }
                }

                SensorDetails sensorDetails = new SensorDetails(false, derivedChannelBitmapID, sensorMapRef.get(key));
                sensorMap.put(key, sensorDetails);
            }

        }

        updateSensorMapChannelsFromChannelMap(sensorMap);

        return sensorMap;
    }

    private void createSensorGroupMapShimmer3() {
        Map<Integer, SensorGroupingDetails> groupMapRef;
        if (isShimmerGen3R()) {
            groupMapRef = Configuration.Shimmer3.mSensorGroupingMapRef3r;
        } else {
            groupMapRef = Configuration.Shimmer3.mSensorGroupingMapRef;
        }

        loadCompatibleSensorGroupEntries(groupMapRef);
    }


    private void createConfigOptionMapShimmer3() {
        generateConfigOptionsMap();
        Map<String, ConfigOptionDetailsSensor> configOptionsMapRef;
        if (isShimmerGen3R()) {
            configOptionsMapRef = Configuration.Shimmer3.mConfigOptionsMapRef3r;
        } else {
            configOptionsMapRef = Configuration.Shimmer3.mConfigOptionsMapRef;
        }
        loadCompatibleConfigOptionGroupEntries(configOptionsMapRef);
    }

    @Override
    public void checkConfigOptionValues(String stringKey) {
        if (mConfigOptionsMapSensors != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMapSensors.get(stringKey);
            if (configOptions != null) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    int nonStandardIndex = -1;
                    if (stringKey.equals(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE)) {
                        checkWhichExgRespPhaseValuesToUse();
                    } else if (stringKey.equals(SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE)) {
                        checkWhichExgRefElectrodeValuesToUse();
                    }
                } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {
                }
            }
        }
    }

    @Override
    public void handleSpecCasesBeforeSensorMapUpdateGeneral() {
        checkExgResolutionFromEnabledSensorsVar();
    }


    @Override
    public boolean handleSpecCasesBeforeSensorMapUpdatePerSensor(Integer sensorId) {
        if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {
            mSensorMap.get(sensorId).setIsEnabled(false);
            return true;
        } else if (((sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1)
                || (sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14))) {

            innerloop:
            if (mSensorMap.get(sensorId).mSensorDetailsRef.mListOfSensorIdsConflicting != null) {
                for (Integer conflictKey : mSensorMap.get(sensorId).mSensorDetailsRef.mListOfSensorIdsConflicting) {
                    if (mSensorMap.get(conflictKey) != null && mSensorMap.get(conflictKey).isDerivedChannel()) {
                        if ((mDerivedSensors & mSensorMap.get(conflictKey).mDerivedSensorBitmapID) == mSensorMap.get(conflictKey).mDerivedSensorBitmapID) {
                            mSensorMap.get(sensorId).setIsEnabled(false);
                            return true;
                        }
                    }
                }
            } else {
                System.out.println("2r:null");
            }
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_SHIMMER_STREAMING_PROPERTIES) {
            mSensorMap.get(sensorId).setIsEnabled(true);
            return true;
        }
        return false;
    }


    @Override
    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {

        checkExgResolutionFromEnabledSensorsVar();
        internalCheckExgModeAndUpdateSensorMap();


        if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12) != null) {
            if ((mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12).isEnabled()) || (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A13).isEnabled())) {
                mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY).setIsEnabled(true);
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12).isEnabled()) {
                    mPpgAdcSelectionGsrBoard = SensorPPG.ListOfPpgAdcSelectionConfigValues[1];
                } else if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A13).isEnabled()) {
                    mPpgAdcSelectionGsrBoard = SensorPPG.ListOfPpgAdcSelectionConfigValues[0];

                }
            } else {
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY) != null) {
                    mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY).setIsEnabled(false);
                } else {
                    System.out.println("2r:null");
                }

            }
        }
        if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12) != null) {
            if ((mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12).isEnabled()) || (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A13).isEnabled())) {
                mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY).setIsEnabled(true);
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12).isEnabled()) {
                    mPpg1AdcSelectionProto3DeluxeBoard = SensorPPG.ListOfPpg1AdcSelectionConfigValues[1];
                } else if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A13).isEnabled()) {
                    mPpg1AdcSelectionProto3DeluxeBoard = SensorPPG.ListOfPpg1AdcSelectionConfigValues[0];
                }
            } else {
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY) != null) {
                    mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY).setIsEnabled(false);
                } else {
                    System.out.println("2r:null");
                }
            }
        }
        if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1) != null) {
            if ((mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1).isEnabled()) || (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A14).isEnabled())) {
                mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY).setIsEnabled(true);
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1).isEnabled()) {
                    mPpg2AdcSelectionProto3DeluxeBoard = SensorPPG.ListOfPpg2AdcSelectionConfigValues[0];
                } else if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A14).isEnabled()) {
                    mPpg2AdcSelectionProto3DeluxeBoard = SensorPPG.ListOfPpg2AdcSelectionConfigValues[1];
                }
            } else {
                if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY) != null) {
                    mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY).setIsEnabled(false);
                } else {
                    System.out.println("2r:null");
                }
            }
        }

        enableShimmer3Timestamps();
    }

    private void enableShimmer3Timestamps() {
        SensorDetails sensorDetailTimestamp = mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP);
        if (sensorDetailTimestamp != null) {
            sensorDetailTimestamp.setIsEnabled(true);
        }

        SensorDetails sensorDetailSystemTimestamp = mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_SYSTEM_TIMESTAMP);
        if (sensorDetailSystemTimestamp != null) {
            sensorDetailSystemTimestamp.setIsEnabled(true);
        }

        SensorDetails sensorDetailStreamingProp = mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_SHIMMER_STREAMING_PROPERTIES);
        if (sensorDetailStreamingProp != null) {
            sensorDetailStreamingProp.setIsEnabled(true);
        }

    }

    public boolean checkIfSensorEnabled(int sensorId) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY) {
                if ((super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12))
                        || (super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A13)))
                    return true;
                else
                    return false;
            } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY) {
                if ((super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12))
                        || (super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A13)))
                    return true;
                else
                    return false;
            } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY) {
                if ((super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1))
                        || (super.isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A14)))
                    return true;
                else
                    return false;
            }
        }

        return super.isSensorEnabled(sensorId);
    }

    @Override
    public int handleSpecCasesBeforeSetSensorState(int sensorId, boolean state) {
        if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY) {
            mSensorMap.get(sensorId).setIsEnabled(state);
            if (SensorPPG.ListOfPpgAdcSelection[mPpgAdcSelectionGsrBoard].contains("A12")) {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A12;
            } else {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG_A13;
            }
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY) {
            mSensorMap.get(sensorId).setIsEnabled(state);
            if (SensorPPG.ListOfPpg1AdcSelection[mPpg1AdcSelectionProto3DeluxeBoard].contains("A12")) {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A12;
            } else {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_A13;
            }
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY) {
            mSensorMap.get(sensorId).setIsEnabled(state);
            if (SensorPPG.ListOfPpg2AdcSelection[mPpg2AdcSelectionProto3DeluxeBoard].contains("A14")) {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A14;
            } else {
                return Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_A1;
            }
        }
        return sensorId;
    }

    @Override
    public void checkShimmerConfigBeforeConfiguring() {

        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            if (mShimmerUserAssignedName.equals(DEFAULT_SHIMMER_NAME)) {
                setDefaultShimmerName();
            }

            setTrialNameAndCheck(getTrialName());

            if (!isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR)) {
                setDefaultGsrSensorConfig(false);
            }

            if (!SensorEXG.checkIsAnyExgChannelEnabled(mSensorMap)) {
                clearExgConfig();
            }

            super.checkShimmerConfigBeforeConfiguring();

            setSamplingRateSensors(getSamplingRateShimmer());
        }

    }

    @Override
    protected void checkIfInternalExpBrdPowerIsNeeded() {
        super.checkIfInternalExpBrdPowerIsNeeded();

        if (mInternalExpPower == 0) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                for (SensorDetails sensorDetails : mSensorMap.values()) {
                    if (sensorDetails.isInternalExpBrdPowerRequired()) {
                        mInternalExpPower = 1;
                        break;
                    } else {
                        if (isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1)
                                || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12)
                                || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13)
                                || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14)) {

                        } else {
                            mInternalExpPower = 0;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void setDefaultConfigForSensor(int sensorId, boolean state) {
        if (sensorId == Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR) {
            setDefaultGsrSensorConfig(state);
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {

            if (state) {
                if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION) {
                    setDefaultRespirationConfiguration(getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG) {
                    setDefaultECGConfiguration(getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG) {
                    setDefaultEMGConfiguration(getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST) {
                    setEXGTestSignal(getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {
                    setExgThreeUnipolarInput(getSamplingRateShimmer());
                } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM) {
                    setEXGCustom(getSamplingRateShimmer());
                }
            } else {
                if (!SensorEXG.checkIsAnyExgChannelEnabled(mSensorMap)) {
                    clearExgConfig();
                }
            }
        } else {
            super.setDefaultConfigForSensor(sensorId, state);
        }

    }

    private void setDefaultGsrSensorConfig(boolean state) {
        if (state) {
        } else {
            setGSRRange(4);
        }
    }

    public int getBufferSize() {
        return mBufferSize;
    }

    protected void setBufferSize(int mBufferSize) {
        this.mBufferSize = mBufferSize;
    }

    public int getMasterShimmer() {
        return mMasterShimmer;
    }

    public int getSingleTouch() {
        return mSingleTouch;
    }

    public int getTCXO() {
        return mTCXO;
    }

    private boolean isLowBattAutoStop() {
        return mLowBattAutoStop;
    }

    private void setLowBattAutoStop(int state) {
        setLowBattAutoStop(state > 0 ? true : false);
    }

    private void setLowBattAutoStop(boolean state) {
        mLowBattAutoStop = state;
    }

    public int getTrialNumberOfShimmers() {
        return mTrialNumberOfShimmers;
    }

    public int getTrialDurationEstimatedInSecs() {
        return mTrialDurationEstimatedInSecs;
    }

    public void setTrialDurationEstimatedInSecs(int trialDurationEstimatedInSecs) {
        mTrialDurationEstimatedInSecs = trialDurationEstimatedInSecs;
    }

    public void setExperimentDurationEstimatedInSecs(int experimentDurationEstimatedInSecs) {
        int maxValue = (int) ((Math.pow(2, 16)) - 1);
        if (experimentDurationEstimatedInSecs > maxValue) {
            experimentDurationEstimatedInSecs = maxValue;
        } else if (experimentDurationEstimatedInSecs <= 0) {
            experimentDurationEstimatedInSecs = 1;
        }
        setTrialDurationEstimatedInSecs(experimentDurationEstimatedInSecs);
    }

    public int getTrialDurationMaximumInSecs() {
        return mTrialDurationMaximumInSecs;
    }

    public void setTrialDurationMaximumInSecs(int trialDurationMaximumInSecs) {
        mTrialDurationMaximumInSecs = trialDurationMaximumInSecs;
    }

    public void setExperimentDurationMaximumInSecs(int experimentDurationMaximumInSecs) {
        int maxValue = (int) ((Math.pow(2, 16)) - 1);
        if (experimentDurationMaximumInSecs > maxValue) {
            experimentDurationMaximumInSecs = maxValue;
        } else if (experimentDurationMaximumInSecs < 0) {
            experimentDurationMaximumInSecs = 1;
        }
        setTrialDurationMaximumInSecs(experimentDurationMaximumInSecs);
    }

    public String getMacIdFromInfoMem() {
        return mMacIdFromInfoMem;
    }

    public int getExperimentId() {
        return mTrialId;
    }

    public void setExperimentId(int mExperimentId) {
        int maxValue = (int) ((Math.pow(2, 8)) - 1);
        if (mExperimentId > maxValue) {
            mExperimentId = maxValue;
        } else if (mExperimentId < 0) {
            mExperimentId = 1;
        }
        this.mTrialId = mExperimentId;
    }

    public List<String> getSyncNodesList() {
        return syncNodesList;
    }


    public void setSyncNodesList(List<String> syncNodesList) {
        this.syncNodesList = syncNodesList;
    }

    public int getSyncBroadcastInterval() {
        return mSyncBroadcastInterval;
    }

    protected void setSyncBroadcastInterval(int mSyncBroadcastInterval) {
        int maxValue = (int) ((Math.pow(2, 8)) - 1);
        if (mSyncBroadcastInterval > maxValue) {
            mSyncBroadcastInterval = maxValue;
        } else if (mSyncBroadcastInterval <= 0) {
            mSyncBroadcastInterval = 1;
        }
        this.mSyncBroadcastInterval = mSyncBroadcastInterval;
    }

    public int getSyncWhenLogging() {
        return mSyncWhenLogging;
    }

    public int getBluetoothBaudRate() {
        return mBluetoothBaudRate;
    }

    public void setBluetoothBaudRate(int mBluetoothBaudRate) {
        this.mBluetoothBaudRate = mBluetoothBaudRate;
    }


    public boolean getInitialized() {
        return mIsInitialised;
    }

    public int get5VReg() {
        if (getHardwareVersion() != HW_ID.SHIMMER_3 && getHardwareVersion() != HW_ID.SHIMMER_3R) {
            if ((mConfigByte0 & (byte) 128) != 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    public int getCurrentLEDStatus() {
        return mCurrentLEDStatus;
    }

    public int getBaudRate() {
        return mBluetoothBaudRate;
    }

    public int getShimmerVersion() {
        return getHardwareVersion();
    }


    public boolean isUsingDefaultECGParam() {
        return mDefaultCalibrationParametersECG;
    }

    public boolean isUsingDefaultEMGParam() {
        return mDefaultCalibrationParametersEMG;
    }

    public Map<String, ChannelDetails> getChannelMap() {
        return mChannelMap;
    }

    public boolean isUsingConfigFromInfoMem() {
        return mShimmerUsingConfigFromInfoMem;
    }

    public String getMacIdFromBtParsed() {
        if (this.mMyBluetoothAddress.length() >= 12) {
            return this.mMyBluetoothAddress.substring(8, 12);
        }
        return "0000";
    }

    @Override
    public String getMacId() {
        if (mMacIdFromUart != null) {
            if (!mMacIdFromUart.isEmpty()) {
                return mMacIdFromUart;
            } else {
                if (!mMacIdFromInfoMem.isEmpty()) {
                    return mMacIdFromInfoMem;
                } else {
                    if (!mMacIdFromInfoMem.isEmpty()) {
                        return mMacIdFromInfoMem;
                    } else {
                        return mMyBluetoothAddress;
                    }
                }
            }
        } else {
            return mMyBluetoothAddress;
        }

    }

    public int getSamplingDividerVBatt() {
        return mSamplingDividerVBatt;
    }

    public void setSamplingDividerVBatt(int mSamplingDividerVBatt) {
        this.mSamplingDividerVBatt = mSamplingDividerVBatt;
    }

    public int getSamplingDividerGsr() {
        return mSamplingDividerGsr;
    }

    public void setSamplingDividerGsr(int mSamplingDividerGsr) {
        this.mSamplingDividerGsr = mSamplingDividerGsr;
    }

    public int getGSRRange() {
        return mGSRRange;
    }

    public void setGSRRange(int i) {
        mGSRRange = i;
    }

    protected byte[] generateConfigBytesForWritingToShimmer() {
        return configBytesGenerate(true);
    }

    public void setExperimentNumberOfShimmers(int mExperimentNumberOfShimmers) {
        int maxValue = (int) ((Math.pow(2, 8)) - 1);
        if (mExperimentNumberOfShimmers > maxValue) {
            mExperimentNumberOfShimmers = maxValue;
        } else if (mExperimentNumberOfShimmers <= 0) {
            mExperimentNumberOfShimmers = 1;
        }
        this.mTrialNumberOfShimmers = mExperimentNumberOfShimmers;
    }

    public boolean isMasterShimmer() {
        return (this.mMasterShimmer > 0) ? true : false;
    }

    public void setMasterShimmer(boolean state) {
        setMasterShimmer(state ? 1 : 0);
    }

    public void setMasterShimmer(int state) {
        this.mMasterShimmer = state;
    }

    public boolean isSingleTouch() {
        return (this.mSingleTouch > 0) ? true : false;
    }

    public void setSingleTouch(boolean state) {
        setSingleTouch(state ? 1 : 0);
    }

    public void setSingleTouch(int state) {
        mSingleTouch = state;
    }

    public boolean isTCXO() {
        return (this.mTCXO > 0) ? true : false;
    }

    public void setTCXO(boolean state) {
        setTCXO(state ? 1 : 0);
    }

    public void setTCXO(int state) {
        this.mTCXO = state;
    }

    public boolean isSyncWhenLogging() {
        return (this.mSyncWhenLogging > 0) ? true : false;
    }

    public void setSyncWhenLogging(boolean state) {
        setSyncWhenLogging(state ? 1 : 0);
    }

    public void setSyncWhenLogging(int state) {
        this.mSyncWhenLogging = state;
    }

    public int getButtonStart() {
        return mButtonStart;
    }

    public int getDisableBluetooth() {
        return mDisableBluetooth;
    }

    public boolean isButtonStart() {
        return (this.mButtonStart > 0) ? true : false;
    }

    public void setButtonStart(boolean state) {
        setButtonStart(state ? 1 : 0);
    }

    public void setButtonStart(int state) {
        this.mButtonStart = state;
    }

    public boolean isDisableBluetooth() {
        return (this.mDisableBluetooth > 0) ? true : false;
    }

    public void setDisableBluetooth(boolean state) {
        setDisableBluetooth(state ? 1 : 0);
    }

    protected void setDisableBluetooth(int state) {
        this.mDisableBluetooth = state;
    }

    public boolean isShowErrorLedsSd() {
        return (this.mShowErrorLedsSd > 0) ? true : false;
    }

    public void setShowErrorLedsSd(boolean state) {
        this.mShowErrorLedsSd = (state ? 1 : 0);
    }


    public void setShowErrorLedsSd(int state) {
        this.mShowErrorLedsSd = state;
    }

    public boolean isShowErrorLedsRtc() {
        return (this.mShowErrorLedsRtc > 0) ? true : false;
    }

    public void setShowErrorLedsRtc(boolean state) {
        this.mShowErrorLedsRtc = (state ? 1 : 0);
    }

    public void setShowErrorLedsRtc(int state) {
        this.mShowErrorLedsRtc = state;
    }

    public void setIsOverrideShowErrorLedsRtc(boolean state) {
        this.isOverrideShowErrorLedsRtc = state;
    }

    public void setIsOverrideShowErrorLedsSd(boolean state) {
        this.isOverrideShowErrorLedsSd = state;
    }

    protected void setMacIdFromBt(String myBluetoothAddress) {
        this.mMyBluetoothAddress = myBluetoothAddress;
    }

    public void setPacketSize(int packetSize) {
        mPacketSize = packetSize;
    }

    public byte[] generateCalParamLSM303DLHCAccel() {
        return getCurrentCalibDetailsAccelWr().generateCalParamByteArray();
    }

    public byte[] generateCalParamLSM303DLHCMag() {
        return getCurrentCalibDetailsMag().generateCalParamByteArray();
    }

    public void parseCalibParamFromPacketAccelLsm(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsAccelWr().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    public void getCurrentCalibDetailsAccelAlt(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsAccelAlt().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    public void getCurrentCalibDetailsMagWr(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsMagAlt().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    public void parseCalibParamFromPacketMag(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsMag().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    public void setDefaultCalibrationShimmer3StandardImus() {
        setDefaultCalibrationShimmer3LowNoiseAccel();
        setDefaultCalibrationShimmer3WideRangeAccel();
        setDefaultCalibrationShimmer3Gyro();
        setDefaultCalibrationShimmer3Mag();
    }

    private void setDefaultCalibrationShimmer3LowNoiseAccel() {
        getCurrentCalibDetailsAccelLn().resetToDefaultParameters();
    }

    private void setDefaultCalibrationShimmer3WideRangeAccel() {
        getCurrentCalibDetailsAccelWr().resetToDefaultParameters();
    }

    private void setDefaultCalibrationShimmer3Mag() {
        getCurrentCalibDetailsMag().resetToDefaultParameters();
    }

    protected CalibDetailsKinematic getCurrentCalibDetailsAccelWr() {
        if (isShimmerGen3() || isShimmerGenGq()) {
            return mSensorLSM303.getCurrentCalibDetailsAccelWr();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getCurrentCalibDetailsAccelWr();
        }
        return null;
    }

    protected CalibDetailsKinematic getCurrentCalibDetailsMag() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Mag.getCurrentCalibDetailsMag();
        } else if (isShimmerGen3() || isShimmerGenGq()) {
            return mSensorLSM303.getCurrentCalibDetailsMag();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getCurrentCalibDetailsMag();
        }
        return null;
    }


    protected CalibDetailsKinematic getCurrentCalibDetailsMagAlt() {
        if (isShimmerGen3R()) {
            return mSensorLIS3MDL.getCurrentCalibDetailsMagAlt();
        }
        return null;
    }

    protected CalibDetailsKinematic getCurrentCalibDetailsAccelLn() {
        if (isShimmerGen2()) {
            return mSensorMMA736x.getCurrentCalibDetailsAccelLn();
        } else if (isShimmerGen3() || isShimmerGenGq()) {
            return mSensorKionixAccel.getCurrentCalibDetailsAccelLn();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.getCurrentCalibDetailsAccelLn();
        }
        return null;
    }

    protected CalibDetailsKinematic getCurrentCalibDetailsAccelAlt() {
        if (isShimmerGen3R()) {
            return mSensorADXL371.getCurrentCalibDetailsAccelHighG();
        }
        return null;
    }

    @Override
    public void setMapOfSensorCalibrationAll(TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfAllSensorCalibration) {
        super.setMapOfSensorCalibrationAll(mapOfAllSensorCalibration);

        for (Integer sensorId : mapOfAllSensorCalibration.keySet()) {
            TreeMap<Integer, CalibDetails> mapOfSensorCalibration = mapOfAllSensorCalibration.get(sensorId);
            setSensorCalibrationPerSensor(sensorId, mapOfSensorCalibration);
        }
    }

    public void updateCurrentAccelLnCalibInUse() {
        if (isShimmerGen2()) {
            mSensorMMA736x.updateCurrentAccelCalibInUse();
        } else if (isShimmerGen3()) {
            mSensorKionixAccel.updateCurrentAccelLnCalibInUse();
        } else if (isShimmerGen3R()) {
            mSensorLSM6DSV.updateCurrentAccelLnCalibInUse();
        }
    }

    public void updateCurrentMagCalibInUse() {
        if (isShimmerGen3R()) {
            mSensorLIS2MDL.updateCurrentMagCalibInUse();
        } else {
            mSensorLSM303.updateCurrentMagCalibInUse();
        }
    }

    public void updateCurrentAccelWrCalibInUse() {
        if (isShimmerGen3()) {
            mSensorLSM303.updateCurrentAccelWrCalibInUse();
        } else if (isShimmerGen3R()) {
            mSensorLIS2DW12.updateCurrentAccelWrCalibInUse();
        }
    }

    private void updateCurrentCalibInUse() {
        updateCurrentAccelLnCalibInUse();
        updateCurrentAccelWrCalibInUse();
        updateCurrentGyroCalibInUse();
        updateCurrentMagCalibInUse();
        updateCurrentPressureCalibInUse();
    }

    protected void retrievePressureCalibrationParametersFromPacket(byte[] pressureResoRes, CALIB_READ_SOURCE calibReadSource) {
        mSensorBMPX80.parseCalParamByteArray(pressureResoRes, calibReadSource);

        updateCurrentPressureCalibInUse();
    }


    public void updateCurrentPressureCalibInUse() {
        mSensorBMPX80.updateCurrentPressureCalibInUse();
    }

    public int getPressureResolution() {
        return mSensorBMPX80.getPressureResolution();
    }

    public void setPressureResolution(int i) {
        mSensorBMPX80.setPressureResolution(i);
    }

    public void setPressureCalib(
            double AC1, double AC2, double AC3,
            double AC4, double AC5, double AC6,
            double B1, double B2,
            double MB, double MC, double MD) {
        if (mSensorBMPX80 instanceof SensorBMP180) {
            ((SensorBMP180) mSensorBMPX80).setPressureCalib(AC1, AC2, AC3, AC4, AC5, AC6, B1, B2, MB, MC, MD);
        }
    }

    public byte[] getPressureRawCoefficients() {
        return mSensorBMPX80.mCalibDetailsBmpX80.getPressureRawCoefficients();
    }

    public byte[] getDigiAccelCalRawParams() {
        return getCurrentCalibDetailsAccelWr().generateCalParamByteArray();
    }

    public byte[] getAccelCalRawParams() {
        return getCurrentCalibDetailsAccelLn().generateCalParamByteArray();
    }

    public byte[] getMagCalRawParams() {
        return getCurrentCalibDetailsMag().generateCalParamByteArray();
    }


    public int getPpgAdcSelectionGsrBoard() {
        return mPpgAdcSelectionGsrBoard;
    }

    protected void setPpgAdcSelectionGsrBoard(int ppgAdcSelectionGsrBoard) {
        this.mPpgAdcSelectionGsrBoard = ppgAdcSelectionGsrBoard;
        int key = Configuration.Shimmer3.SENSOR_ID.HOST_PPG_DUMMY;
        this.setSensorEnabledState(key, mSensorMap.get(key).isEnabled());
    }


    public int getPpg1AdcSelectionProto3DeluxeBoard() {
        return mPpg1AdcSelectionProto3DeluxeBoard;
    }

    protected void setPpg1AdcSelectionProto3DeluxeBoard(int ppg1AdcSelectionProto3DeluxeBoard) {
        this.mPpg1AdcSelectionProto3DeluxeBoard = ppg1AdcSelectionProto3DeluxeBoard;
        int key = Configuration.Shimmer3.SENSOR_ID.HOST_PPG1_DUMMY;
        this.setSensorEnabledState(key, mSensorMap.get(key).isEnabled());
    }

    public int getPpg2AdcSelectionProto3DeluxeBoard() {
        return mPpg2AdcSelectionProto3DeluxeBoard;
    }

    protected void setPpg2AdcSelectionProto3DeluxeBoard(int ppg2AdcSelectionProto3DeluxeBoard) {
        this.mPpg2AdcSelectionProto3DeluxeBoard = ppg2AdcSelectionProto3DeluxeBoard;
        int key = Configuration.Shimmer3.SENSOR_ID.HOST_PPG2_DUMMY;
        this.setSensorEnabledState(key, mSensorMap.get(key).isEnabled());
    }

    public int getSamplingDividerPpg() {
        return mSamplingDividerPpg;
    }

    public void setSamplingDividerPpg(int mSamplingDividerPpg) {
        this.mSamplingDividerPpg = mSamplingDividerPpg;
    }

    public void exgBytesGetConfigFrom(EXG_CHIP_INDEX chipIndex, byte[] byteArray) {
        int index = 1;
        if (byteArray.length == 10) {
            index = 0;
        }

        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            System.arraycopy(byteArray, index, mEXG1RegisterArray, 0, 10);
            mEXG1RateSetting = mEXG1RegisterArray[0] & 7;
            mEXGLeadOffDetectionCurrent = (mEXG1RegisterArray[2] >> 2) & 3;
            mEXGLeadOffComparatorTreshold = (mEXG1RegisterArray[2] >> 5) & 7;
            mEXG1CH1GainSetting = (mEXG1RegisterArray[3] >> 4) & 7;
            mEXG1CH1GainValue = SensorEXG.convertEXGGainSettingToValue(mEXG1CH1GainSetting);
            mEXG1CH2GainSetting = (mEXG1RegisterArray[4] >> 4) & 7;
            mEXG1CH2GainValue = SensorEXG.convertEXGGainSettingToValue(mEXG1CH2GainSetting);
            mEXGReferenceElectrode = mEXG1RegisterArray[5] & 0x0F;
            mEXG1LeadOffCurrentMode = mEXG1RegisterArray[2] & 1;
            mEXG1Comparators = mEXG1RegisterArray[1] & 0x40;
            mEXGRLDSense = mEXG1RegisterArray[5] & 0x10;
            mEXG1LeadOffSenseSelection = mEXG1RegisterArray[6] & 0x0f;

            mExGConfigBytesDetails.updateFromRegisterArray(EXG_CHIP_INDEX.CHIP1, mEXG1RegisterArray);
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            System.arraycopy(byteArray, index, mEXG2RegisterArray, 0, 10);
            mEXG2RateSetting = mEXG2RegisterArray[0] & 7;
            mEXG2CH1GainSetting = (mEXG2RegisterArray[3] >> 4) & 7;
            mEXG2CH2PowerDown = (mEXG2RegisterArray[3] >> 7) & 1;
            mEXG2CH1GainValue = SensorEXG.convertEXGGainSettingToValue(mEXG2CH1GainSetting);
            mEXG2CH2GainSetting = (mEXG2RegisterArray[4] >> 4) & 7;
            mEXG2CH2GainValue = SensorEXG.convertEXGGainSettingToValue(mEXG2CH2GainSetting);
            mEXG2LeadOffCurrentMode = mEXG2RegisterArray[2] & 1;
            mEXG2Comparators = mEXG2RegisterArray[1] & 0x40;
            mEXG2LeadOffSenseSelection = mEXG2RegisterArray[6] & 0x0f;

            mEXG2RespirationDetectState = (mEXG2RegisterArray[8] >> 6) & 0x03;
            mEXG2RespirationDetectPhase = (mEXG2RegisterArray[8] >> 2) & 0x0F;
            mEXG2RespirationDetectFreq = (mEXG2RegisterArray[9] >> 2) & 0x01;

            mExGConfigBytesDetails.updateFromRegisterArray(EXG_CHIP_INDEX.CHIP2, mEXG2RegisterArray);
        }

    }

    public void exgBytesGetConfigFrom(byte[] EXG1RegisterArray, byte[] EXG2RegisterArray) {
        if (EXG1RegisterArray != null) {
            setEXG1RegisterArray(EXG1RegisterArray);
        }
        if (EXG2RegisterArray != null) {
            setEXG2RegisterArray(EXG2RegisterArray);
        }
        internalCheckExgModeAndUpdateSensorMap();
    }

    public void exgBytesGetFromConfig() {
        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    public void setDefaultECGConfiguration(double shimmerSamplingRate) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            clearExgConfig();
            setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_ECG);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

            setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4.configValueInt);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_NEG_INPUTS_CH2.RLD_CONNECTED_TO_IN2N);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_POS_INPUTS_CH2.RLD_CONNECTED_TO_IN2P);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_POS_INPUTS_CH1.RLD_CONNECTED_TO_IN1P);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

            setDefaultExgCommon(shimmerSamplingRate);
        }
    }

    protected void setDefaultEMGConfiguration(double shimmerSamplingRate) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            clearExgConfig();
            setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EMG);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

            setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_12.configValueInt);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.ROUTE_CH3_TO_CH1);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_POWER_DOWN.POWER_DOWN);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.POWER_DOWN);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.SHORTED);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

            setDefaultExgCommon(shimmerSamplingRate);
        }
    }

    protected void setEXGTestSignal(double shimmerSamplingRate) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            clearExgConfig();
            setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

            setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_1.configValueInt);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_SELECTION.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_FREQUENCY.SQUARE_WAVE_1KHZ);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL);

            setDefaultExgCommon(shimmerSamplingRate);
        }
    }

    protected void setDefaultRespirationConfiguration(double shimmerSamplingRate) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            clearExgConfig();
            setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

            setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4.configValueInt);


            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_DEMOD_CIRCUITRY.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_MOD_CIRCUITRY.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_32KHZ.PHASE_112_5);

            setDefaultExgCommon(shimmerSamplingRate);
        }
    }

    protected void setEXGCustom(double shimmerSamplingRate) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {

            clearExgConfig();
            setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_SELECTION.ON);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.TEST_SIGNAL_FREQUENCY.SQUARE_WAVE_1KHZ);

            setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4.configValueInt);

            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

            setDefaultExgCommon(shimmerSamplingRate);
        }
    }

    public void setExgThreeUnipolarInput(double shimmerSamplingRate) {
        clearExgConfig();
        setExgChannelBitsPerMode(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR);

        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG1.CONVERSION_MODES.CONTINUOUS);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.REFERENCE_BUFFER.ON);
        setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.VOLTAGE_REFERENCE.VREF_2_42V);

        setExGGainSetting(EXG_SETTING_OPTIONS.REG4.CH1_PGA_GAIN.GAIN_4.configValueInt);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT);

        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_BUFFER_POWER.ENABLED);
        setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG10.RLD_REFERENCE_SIGNAL.HALF_OF_SUPPLY);

        setDefaultExgCommon(shimmerSamplingRate);
    }

    private void setDefaultExgCommon(double shimmerSamplingRate) {
        if (ShimmerVerObject.isSupportedExgChipClocksJoined(getShimmerVerObject(), getExpansionBoardDetails())) {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG2.OSCILLATOR_CLOCK_CONNECTION.ON);
        }
        setExGRateFromFreq(shimmerSamplingRate);
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    protected void clearExgConfig() {
        setExgChannelBitsPerMode(-1);
        mExGConfigBytesDetails.startNewExGConig();

        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    protected boolean isExgPropertyEnabled(EXG_CHIP_INDEX chipIndex, ExGConfigOption option) {
        return mExGConfigBytesDetails.isExgPropertyEnabled(chipIndex, option);
    }

    protected void setExgPropertyBothChips(ExGConfigOption option) {
        mExGConfigBytesDetails.setExgPropertyBothChips(option);
        mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();

        exgBytesGetConfigFrom(mEXG1RegisterArray, mEXG2RegisterArray);
    }

    protected void setExgPropertySingleChip(EXG_CHIP_INDEX chipIndex, ExGConfigOption option) {
        mExGConfigBytesDetails.setExgPropertySingleChip(chipIndex, option);
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        }
        updateExgVariables(chipIndex);
    }


    public void setExgPropertySingleChipValue(EXG_CHIP_INDEX chipIndex, String propertyName, int value) {
        mExGConfigBytesDetails.setExgPropertyValue(chipIndex, propertyName, value);
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            mEXG1RegisterArray = mExGConfigBytesDetails.getEXG1RegisterArray();
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            mEXG2RegisterArray = mExGConfigBytesDetails.getEXG2RegisterArray();
        }
        updateExgVariables(chipIndex);
    }

    private void updateExgVariables(EXG_CHIP_INDEX chipIndex) {
        if (chipIndex == EXG_CHIP_INDEX.CHIP1) {
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP1, mEXG1RegisterArray);
        } else if (chipIndex == EXG_CHIP_INDEX.CHIP2) {
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP2, mEXG2RegisterArray);
        }
    }

    public int getExgPropertySingleChip(EXG_CHIP_INDEX chipIndex, String propertyName) {
        return mExGConfigBytesDetails.getExgPropertySingleChip(chipIndex, propertyName);
    }

    public HashMap<String, Integer> getMapOfExGSettingsChip1() {
        return mExGConfigBytesDetails.mMapOfExGSettingsChip1ThisShimmer;
    }

    public HashMap<String, Integer> getMapOfExGSettingsChip2() {
        return mExGConfigBytesDetails.mMapOfExGSettingsChip2ThisShimmer;
    }

    protected void checkExgResolutionFromEnabledSensorsVar() {
        ConfigByteLayout infoMemLayout = getConfigByteLayout();
        if (infoMemLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 infoMemLayoutCast = (ConfigByteLayoutShimmer3) infoMemLayout;
            mIsExg1_24bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg1_24bitFlag) > 0) ? true : false;
            mIsExg2_24bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg2_24bitFlag) > 0) ? true : false;
            mIsExg1_16bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg1_16bitFlag) > 0) ? true : false;
            mIsExg2_16bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg2_16bitFlag) > 0) ? true : false;
        } else if (infoMemLayout instanceof ConfigByteLayoutShimmerGq802154) {
            ConfigByteLayoutShimmerGq802154 infoMemLayoutCast = (ConfigByteLayoutShimmerGq802154) infoMemLayout;
            mIsExg1_24bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg1_24bitFlag) > 0) ? true : false;
            mIsExg2_24bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg2_24bitFlag) > 0) ? true : false;
            mIsExg1_16bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg1_16bitFlag) > 0) ? true : false;
            mIsExg2_16bitEnabled = ((mEnabledSensors & infoMemLayoutCast.maskExg2_16bitFlag) > 0) ? true : false;
        }

        if (mIsExg1_16bitEnabled || mIsExg2_16bitEnabled) {
            mExGResolution = 0;
        } else if (mIsExg1_24bitEnabled || mIsExg2_24bitEnabled) {
            mExGResolution = 1;
        }
    }

    private void updateEnabledSensorsFromExgResolution() {
        long enabledSensors = getEnabledSensors();
        ConfigByteLayout infoMemLayout = getConfigByteLayout();
        if (infoMemLayout instanceof ConfigByteLayoutShimmer3) {
            ConfigByteLayoutShimmer3 infoMemLayoutCast = (ConfigByteLayoutShimmer3) infoMemLayout;


            enabledSensors &= ~infoMemLayoutCast.maskExg1_24bitFlag;
            enabledSensors |= (mIsExg1_24bitEnabled ? infoMemLayoutCast.maskExg1_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_24bitFlag;
            enabledSensors |= (mIsExg2_24bitEnabled ? infoMemLayoutCast.maskExg2_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg1_16bitFlag;
            enabledSensors |= (mIsExg1_16bitEnabled ? infoMemLayoutCast.maskExg1_16bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_16bitFlag;
            enabledSensors |= (mIsExg2_16bitEnabled ? infoMemLayoutCast.maskExg2_16bitFlag : 0);
        } else if (infoMemLayout instanceof ConfigByteLayoutShimmerGq802154) {
            ConfigByteLayoutShimmerGq802154 infoMemLayoutCast = (ConfigByteLayoutShimmerGq802154) infoMemLayout;


            enabledSensors &= ~infoMemLayoutCast.maskExg1_24bitFlag;
            enabledSensors |= (mIsExg1_24bitEnabled ? infoMemLayoutCast.maskExg1_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_24bitFlag;
            enabledSensors |= (mIsExg2_24bitEnabled ? infoMemLayoutCast.maskExg2_24bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg1_16bitFlag;
            enabledSensors |= (mIsExg1_16bitEnabled ? infoMemLayoutCast.maskExg1_16bitFlag : 0);

            enabledSensors &= ~infoMemLayoutCast.maskExg2_16bitFlag;
            enabledSensors |= (mIsExg2_16bitEnabled ? infoMemLayoutCast.maskExg2_16bitFlag : 0);
        }

        setEnabledSensors(enabledSensors);
    }

    private void setExgChannelBitsPerMode(int sensorId) {
        mIsExg1_24bitEnabled = false;
        mIsExg2_24bitEnabled = false;
        mIsExg1_16bitEnabled = false;
        mIsExg2_16bitEnabled = false;

        boolean chip1Enabled = false;
        boolean chip2Enabled = false;
        if (sensorId == -1) {
            chip1Enabled = false;
            chip2Enabled = false;
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_ECG
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST
                || sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR) {
            chip1Enabled = true;
            chip2Enabled = true;
        } else if (sensorId == Configuration.Shimmer3.SENSOR_ID.HOST_EMG) {
            chip1Enabled = true;
            chip2Enabled = false;
        }

        if (mExGResolution == 1) {
            mIsExg1_24bitEnabled = chip1Enabled;
            mIsExg2_24bitEnabled = chip2Enabled;
        } else {
            mIsExg1_16bitEnabled = chip1Enabled;
            mIsExg2_16bitEnabled = chip2Enabled;
        }
    }

    public int getEXG1RateSetting() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE);
    }

    public int getEXGReferenceElectrode() {
        return mExGConfigBytesDetails.getEXGReferenceElectrode();
    }

    protected void setEXGReferenceElectrode(int valueToSet) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH2_RLD_NEG_INPUTS, ((valueToSet & 0x08) == 0x08) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH2_RLD_POS_INPUTS, ((valueToSet & 0x04) == 0x04) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH1_RLD_NEG_INPUTS, ((valueToSet & 0x02) == 0x02) ? 1 : 0);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG6_CH1_RLD_POS_INPUTS, ((valueToSet & 0x01) == 0x01) ? 1 : 0);
    }

    public int getEXGLeadOffDetectionCurrent() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT);
    }

    protected void setEXGLeadOffDetectionCurrent(int mEXGLeadOffDetectionCurrent) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT, mEXGLeadOffDetectionCurrent);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG3_LEAD_OFF_CURRENT, mEXGLeadOffDetectionCurrent);
    }

    public int getEXGLeadOffComparatorTreshold() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD);
    }

    protected void setEXGLeadOffComparatorTreshold(int mEXGLeadOffComparatorTreshold) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD, mEXGLeadOffComparatorTreshold);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG3_COMPARATOR_THRESHOLD, mEXGLeadOffComparatorTreshold);
    }

    public int getEXG2RespirationDetectFreq() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG10_RESPIRATION_CONTROL_FREQUENCY);
    }

    protected void setEXG2RespirationDetectFreq(int mEXG2RespirationDetectFreq) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG10_RESPIRATION_CONTROL_FREQUENCY, mEXG2RespirationDetectFreq);
        checkWhichExgRespPhaseValuesToUse();

        if (isExgRespirationDetectFreq32kHz()) {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_32KHZ.PHASE_112_5);
        } else {
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG9.RESPIRATION_PHASE_AT_64KHZ.PHASE_157_5);
        }
    }

    public int getEXG2RespirationDetectPhase() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_PHASE);
    }

    protected void setEXG2RespirationDetectPhase(int mEXG2RespirationDetectPhase) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_PHASE, mEXG2RespirationDetectPhase);
    }

    public byte[] getEXG1RegisterArray() {
        return mEXG1RegisterArray;
    }

    protected void setEXG1RegisterArray(byte[] EXG1RegisterArray) {
        setEXGRegisterArray(EXG_CHIP_INDEX.CHIP1, EXG1RegisterArray);
    }

    public byte[] getEXG2RegisterArray() {
        return mEXG2RegisterArray;
    }

    protected void setEXG2RegisterArray(byte[] EXG2RegisterArray) {
        setEXGRegisterArray(EXG_CHIP_INDEX.CHIP2, EXG2RegisterArray);
    }

    public int getExGResolution() {
        return mExGResolution;
    }

    protected void setExGResolution(int i) {
        mExGResolution = i;

        if (i == 0) {
			/*if ((mEnabledSensors & SENSOR_EXG1_16BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG1_16BIT;
			}
			if ((mEnabledSensors & SENSOR_EXG2_16BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG2_16BIT;
			}
			mEnabledSensors = SENSOR_EXG1_16BIT|SENSOR_EXG2_16BIT;
			*/

            if (mIsExg1_24bitEnabled) {
                mIsExg1_24bitEnabled = false;
                mIsExg1_16bitEnabled = true;
            }
            if (mIsExg2_24bitEnabled) {
                mIsExg2_24bitEnabled = false;
                mIsExg2_16bitEnabled = true;
            }
        } else if (i == 1) {
			/*if ((mEnabledSensors & SENSOR_EXG1_24BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG1_24BIT;
			}
			if ((mEnabledSensors & SENSOR_EXG2_24BIT)>0){
				mEnabledSensors=mEnabledSensors^SENSOR_EXG2_24BIT;
			}
			mEnabledSensors = SENSOR_EXG1_24BIT|SENSOR_EXG2_24BIT;
			*/
            if (mIsExg1_16bitEnabled) {
                mIsExg1_24bitEnabled = true;
                mIsExg1_16bitEnabled = false;
            }
            if (mIsExg2_16bitEnabled) {
                mIsExg2_24bitEnabled = true;
                mIsExg2_16bitEnabled = false;
            }
        }

        updateEnabledSensorsFromExgResolution();

    }

    public int getExGGainSetting() {
        return getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN);
    }

    protected void setExGGainSetting(int value) {
        setExGGainSetting(EXG_CHIP_INDEX.CHIP1, 1, value);
        setExGGainSetting(EXG_CHIP_INDEX.CHIP1, 2, value);
        setExGGainSetting(EXG_CHIP_INDEX.CHIP2, 1, value);
        setExGGainSetting(EXG_CHIP_INDEX.CHIP2, 2, value);
    }

    public int getExg1CH1GainValue() {
        return SensorEXG.convertEXGGainSettingToValue(getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN));
    }

    public int getExg1CH2GainValue() {
        return SensorEXG.convertEXGGainSettingToValue(getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN));
    }

    public int getExg2CH1GainValue() {
        return SensorEXG.convertEXGGainSettingToValue(getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN));
    }

    public int getExg2CH2GainValue() {
        return SensorEXG.convertEXGGainSettingToValue(getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN));
    }

    public boolean areExgChannelGainsEqual(List<EXG_CHIP_INDEX> listOfChipsToCheck) {
        boolean areEqual = true;
        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP1)) {
            if (getExg1CH1GainValue() != getExg1CH2GainValue()) {
                areEqual = false;
            }
        }

        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP2)) {
            if (getExg2CH1GainValue() != getExg2CH2GainValue()) {
                areEqual = false;
            }
        }

        if (listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP1) && listOfChipsToCheck.contains(EXG_CHIP_INDEX.CHIP2)) {
            if (getExg1CH1GainValue() != getExg2CH1GainValue()) {
                areEqual = false;
            }
        }
        return areEqual;
    }

    protected void setExGGainSetting(EXG_CHIP_INDEX chipID, int channel, int value) {
        if (chipID == EXG_CHIP_INDEX.CHIP1) {
            if (channel == 1) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, value);
                exg1Ch1CalFactor24Bit = (((2.42 * 1000) / getExg1CH1GainValue()) / (Math.pow(2, 23) - 1));
                exg1Ch1CalFactor16Bit = (((2.42 * 1000) / getExg1CH1GainValue() * 2) / (Math.pow(2, 15) - 1));
            } else if (channel == 2) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, value);
                exg1Ch2CalFactor24Bit = (((2.42 * 1000) / getExg1CH2GainValue()) / (Math.pow(2, 23) - 1));
                exg1Ch2CalFactor16Bit = (((2.42 * 1000) / getExg1CH2GainValue() * 2) / (Math.pow(2, 15) - 1));
            }
        } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
            if (channel == 1) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_PGA_GAIN, value);
                exg2Ch1CalFactor24Bit = (((2.42 * 1000) / getExg2CH1GainValue()) / (Math.pow(2, 23) - 1));
                exg2Ch1CalFactor16Bit = (((2.42 * 1000) / getExg2CH1GainValue() * 2) / (Math.pow(2, 15) - 1));
            } else if (channel == 2) {
                setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_PGA_GAIN, value);
                exg2Ch2CalFactor24Bit = (((2.42 * 1000) / getExg2CH2GainValue()) / (Math.pow(2, 23) - 1));
                exg2Ch2CalFactor16Bit = (((2.42 * 1000) / getExg2CH2GainValue() * 2) / (Math.pow(2, 15) - 1));
            }
        }
    }

    protected void setEXGRegisterArray(EXG_CHIP_INDEX chipId, byte[] EXGRegisterArray) {
        if (chipId == EXG_CHIP_INDEX.CHIP1) {
            this.mEXG1RegisterArray = EXGRegisterArray;
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP1, mEXG1RegisterArray);
        } else if (chipId == EXG_CHIP_INDEX.CHIP2) {
            this.mEXG2RegisterArray = EXGRegisterArray;
            exgBytesGetConfigFrom(EXG_CHIP_INDEX.CHIP2, mEXG2RegisterArray);
        }
    }

    protected void enableDefaultECGConfiguration() {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            setDefaultECGConfiguration(getSamplingRateShimmer());
        }
    }

    protected void enableDefaultEMGConfiguration() {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            setDefaultEMGConfiguration(getSamplingRateShimmer());
        }
    }

    protected void enableEXGTestSignal() {
        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            setEXGTestSignal(getSamplingRateShimmer());
        }
    }

    protected void setEXGRateSetting(int valueToSet) {
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
    }

    protected void setEXGRateSetting(EXG_CHIP_INDEX chipID, int valueToSet) {
        if (chipID == EXG_CHIP_INDEX.CHIP1) {
            setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
            setExgPropertySingleChipValue(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG1_DATA_RATE, (int) valueToSet);
        }
    }

    protected int getEXGLeadOffCurrentMode() {
        if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON)
                || isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON)
        ) {
            if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC)) {
                return 1;
            } else if (isExgPropertyEnabled(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.AC)) {
                return 2;
            }
        }
        return 0;
    }

    protected void setEXGLeadOffCurrentMode(int mode) {
        if (mode == 0) {
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);
            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.POWER_DOWN);
            }
        } else if (mode == 1) {
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.DC);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON);

            if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            } else {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            }

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);

            setExgPropertyBothChips(ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.LEAD_OFF_CURRENT.CURRENT_22NA);
            setExgPropertyBothChips(ExGConfigBytesDetails.EXG_SETTING_OPTIONS.REG3.COMPARATOR_THRESHOLD.POS90NEG10);

            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.NORMAL_OPERATION);
            }
        } else if (mode == 2) {
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG3.LEAD_OFF_FREQUENCY.AC);
            setExgPropertyBothChips(EXG_SETTING_OPTIONS.REG2.LEAD_OFF_COMPARATORS.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG6.RLD_LEAD_OFF_SENSE_FUNCTION.ON);

            if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            } else {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.ON);
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.ON);
            }

            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH2.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH2.ON);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_NEG_INPUTS_CH1.OFF);
            setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG7.LEAD_OFF_DETECT_POS_INPUTS_CH1.OFF);

            if (isEXGUsingDefaultEMGConfiguration()) {
                setExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTING_OPTIONS.REG5.CH2_POWER_DOWN.NORMAL_OPERATION);
            }
        }
    }

    @Deprecated
    public boolean isEXGUsingDefaultECGConfigurationForSDFW() {
        if (getFirmwareIdentifier() == FW_ID.GQ_802154) {
            if (((mEXG1RegisterArray[3] & 0x0F) == 0) && ((mEXG1RegisterArray[4] & 0x0F) == 0)) {
                return true;
            }
        } else {
            if (((mEXG1RegisterArray[3] & 0x0F) == 0) && ((mEXG1RegisterArray[4] & 0x0F) == 0) && ((mEXG2RegisterArray[3] & 0x0F) == 0) && ((mEXG2RegisterArray[4] & 0x0F) == 7)) {
                return true;
            }
        }
        return false;

    }

    public boolean isEXGUsingDefaultECGConfiguration() {
        if (getFirmwareIdentifier() == FW_ID.GQ_802154) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)) {
                return true;
            }
        } else {
            if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
                if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                        && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)
                        && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                        && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)) {

                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultEMGConfiguration() {
        if ((mIsExg1_16bitEnabled && !mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && !mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.ROUTE_CH3_TO_CH1.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.SHORTED.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultTestSignalConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.TEST_SIGNAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.TEST_SIGNAL.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultRespirationConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_MOD_CIRCUITRY) == EXG_SETTING_OPTIONS.REG9.RESPIRATION_MOD_CIRCUITRY.ON.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG9_RESPIRATION_DEMOD_CIRCUITRY) == EXG_SETTING_OPTIONS.REG9.RESPIRATION_DEMOD_CIRCUITRY.ON.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultThreeUnipolarConfiguration() {
        if ((mIsExg1_16bitEnabled && mIsExg2_16bitEnabled) || (mIsExg1_24bitEnabled && mIsExg2_24bitEnabled)) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.SHORTED.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP2, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.RLDIN_CONNECTED_TO_NEG_INPUT.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingDefaultECGGqConfiguration() {
        if (mShimmerVerObject.getFirmwareIdentifier() == FW_ID.GQ_802154) {
            if ((getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG4_CHANNEL_1_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG4.CH1_INPUT_SELECTION.NORMAL.configValueInt)
                    && (getExgPropertySingleChip(EXG_CHIP_INDEX.CHIP1, EXG_SETTINGS.REG5_CHANNEL_2_INPUT_SELECTION) == EXG_SETTING_OPTIONS.REG5.CH2_INPUT_SELECTION.NORMAL.configValueInt)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEXGUsingCustomSignalConfiguration() {
        if (mIsExg1_16bitEnabled || mIsExg2_16bitEnabled || mIsExg1_24bitEnabled || mIsExg2_24bitEnabled) {
            return true;
        }
        return false;
    }

    public boolean isExgRespirationDetectFreq32kHz() {
        return (getEXG2RespirationDetectFreq() == 0) ? true : false;
    }

    public int setExGRateFromFreq(double freq) {

        int valueToSet = 0x00;
        if (freq <= 125) {
            valueToSet = 0x00;
        } else if (freq <= 250) {
            valueToSet = 0x01;
        } else if (freq <= 500) {
            valueToSet = 0x02;
        } else if (freq <= 1000) {
            valueToSet = 0x03;
        } else if (freq <= 2000) {
            valueToSet = 0x04;
        } else if (freq <= 4000) {
            valueToSet = 0x05;
        } else if (freq <= 8000) {
            valueToSet = 0x06;
        } else {
            valueToSet = 0x02;
        }
        setEXGRateSetting(valueToSet);
        return mEXG1RateSetting;
    }

    private void internalCheckExgModeAndUpdateSensorMap() {
        if (mSensorMap != null) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                if (isEXGUsingDefaultRespirationConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, true);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                } else if (isEXGUsingDefaultECGConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, true);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                } else if (isEXGUsingDefaultEMGConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, true);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, true);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, true);
                } else if (isEXGUsingCustomSignalConfiguration()) {
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, true);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                    setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                } else {
                    if (mSensorMap.get(Configuration.Shimmer3.SENSOR_ID.HOST_ECG) != null) {
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, false);
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EMG, false);
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_TEST, false);
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_CUSTOM, false);
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION, false);
                        setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_THREE_UNIPOLAR, false);
                    }
                }
            } else if (isShimmerGenGq()) {
                setSensorEnabledStateBasic(Configuration.Shimmer3.SENSOR_ID.HOST_ECG, isEXGUsingDefaultECGGqConfiguration());
            }
        }
    }

    private void checkWhichExgRespPhaseValuesToUse() {
        String stringKey = SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE;
        if (mConfigOptionsMapSensors != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMapSensors.get(stringKey);
            if (configOptions != null) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    int nonStandardIndex = -1;
                    if (isExgRespirationDetectFreq32kHz()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_RESPIRATION_DETECT_PHASE.PHASE_32KHZ;
                    } else {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_RESPIRATION_DETECT_PHASE.PHASE_64KHZ;
                    }

                    if (nonStandardIndex != -1 && configOptions instanceof ConfigOptionDetailsSensor) {
                        ((ConfigOptionDetailsSensor) configOptions).setIndexOfValuesToUse(nonStandardIndex);
                    }

                    Integer[] configvalues = configOptions.getConfigValues();
                    if (!Arrays.asList(configvalues).contains(getEXG2RespirationDetectPhase())) {
                        setEXG2RespirationDetectPhase(configvalues[0]);
                    }

                }
            }
        }
    }

    private void checkWhichExgRefElectrodeValuesToUse() {
        String stringKey = SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE;
        if (mConfigOptionsMapSensors != null) {
            ConfigOptionDetails configOptions = mConfigOptionsMapSensors.get(stringKey);
            if (configOptions != null) {
                if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                    int nonStandardIndex = -1;
                    if (isEXGUsingDefaultRespirationConfiguration()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.RESP;
                    } else if (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultECGGqConfiguration()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.ECG;
                    } else if (isEXGUsingDefaultEMGConfiguration()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.EMG;
                    } else if (isEXGUsingDefaultTestSignalConfiguration()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.TEST;
                    } else if (isEXGUsingDefaultThreeUnipolarConfiguration()) {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.UNIPOLAR;
                    } else {
                        nonStandardIndex = ConfigOptionDetailsSensor.VALUE_INDEXES.EXG_REFERENCE_ELECTRODE.CUSTOM;
                    }

                    if (nonStandardIndex != -1 && configOptions instanceof ConfigOptionDetailsSensor) {
                        ((ConfigOptionDetailsSensor) configOptions).setIndexOfValuesToUse(nonStandardIndex);
                    }

                    Integer[] configvalues = configOptions.getConfigValues();

                    if (!Arrays.asList(configvalues).contains(getEXGReferenceElectrode())) {
                        consolePrintErrLn("EXG Ref not supported: " + getEXGReferenceElectrode() + "\tChanging to: " + configvalues[0]);
                        setEXGReferenceElectrode(configvalues[0]);
                    }
                }
            }
        }
    }

    @Deprecated
    public boolean isEXGUsingECG16Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_16BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_16BIT) > 0) {
            if (isEXGUsingDefaultECGConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public boolean isEXGUsingECG24Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_24BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_24BIT) > 0) {
            if (isEXGUsingDefaultECGConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public boolean isEXGUsingEMG16Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_16BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_16BIT) > 0) {
            if (isEXGUsingDefaultEMGConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public boolean isEXGUsingEMG24Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_24BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_24BIT) > 0) {
            if (isEXGUsingDefaultEMGConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public boolean isEXGUsingTestSignal16Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_16BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_16BIT) > 0) {
            if (isEXGUsingDefaultTestSignalConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public boolean isEXGUsingTestSignal24Configuration() {
        boolean using = false;
        if ((mEnabledSensors & SENSOR_EXG1_24BIT) > 0 && (mEnabledSensors & SENSOR_EXG2_24BIT) > 0) {
            if (isEXGUsingDefaultTestSignalConfiguration()) {
                using = true;
            }
        }
        return using;
    }

    @Deprecated
    public String parseReferenceElectrodeTotring(int referenceElectrode) {
        String refElectrode = "Unknown";

        if (referenceElectrode == 0 && (isEXGUsingDefaultECGConfiguration() || isEXGUsingDefaultEMGConfiguration()))
            refElectrode = "Fixed Potential";
        else if (referenceElectrode == 13 && isEXGUsingDefaultECGConfiguration())
            refElectrode = "Inverse Wilson CT";
        else if (referenceElectrode == 3 && isEXGUsingDefaultEMGConfiguration())
            refElectrode = "Inverse of Ch1";

        return refElectrode;
    }

    @Deprecated
    public int getReferenceElectrode() {
        return mEXGReferenceElectrode;
    }


    @Deprecated
    public int getLeadOffDetectionMode() {
        return mLeadOffDetectionMode;
    }

    @Deprecated
    public int getLeadOffDetectionCurrent() {
        return mEXGLeadOffDetectionCurrent;
    }

    @Deprecated
    public int getLeadOffComparatorTreshold() {
        return mEXGLeadOffComparatorTreshold;
    }

    @Deprecated
    public int getExGComparatorsChip1() {
        return mEXG1Comparators;
    }

    @Deprecated
    public int getExGComparatorsChip2() {
        return mEXG2Comparators;
    }

    public boolean isUsingValidLNAccelParam() {
        if (!UtilShimmer.isAllZeros(getAlignmentMatrixAccel()) && !UtilShimmer.isAllZeros(getSensitivityMatrixAccel())) {
            return true;
        } else {
            return false;
        }
    }

    public double getCalibTimeAccel() {
        return getCurrentCalibDetailsAccelLn().getCalibTimeMs();
    }

    public boolean isUsingDefaultLNAccelParam() {
        return getCurrentCalibDetailsAccelLn().isUsingDefaultParameters();
    }


    public double[][] getAlignmentMatrixAccel() {
        return getCurrentCalibDetailsAccelLn().getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixAccel() {
        return getCurrentCalibDetailsAccelLn().getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixAccel() {
        return getCurrentCalibDetailsAccelLn().getValidOffsetVector();
    }

    public byte[] generateCalParamByteArrayAccelLn() {
        return getCurrentCalibDetailsAccelLn().generateCalParamByteArray();
    }

    public void parseCalibParamFromPacketAccelAnalog(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsAccelLn().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    protected void setLowPowerMag(boolean enable) {
        if (isShimmerGen2()) {
            mSensorShimmer2Mag.setLowPowerMag(enable);
        } else if (isShimmerGen3()) {
            mSensorLSM303.setLowPowerMag(enable);
        } else if (isShimmerGen3R()) {
            mSensorLIS3MDL.setLowPowerMag(enable);
        }
    }

    protected void setMedPowerMag(boolean enable) {
        mSensorLIS3MDL.setLowPowerMag(enable);
    }

    protected void setHighPowerMag(boolean enable) {
        mSensorLIS3MDL.setLowPowerMag(enable);
    }

    protected void setUltraHighPowerMag(boolean enable) {
        mSensorLIS3MDL.setLowPowerMag(enable);
    }

    private void setShimmer2rMagRateFromFreq(double samplingRateShimmer) {
        mSensorShimmer2Mag.setShimmer2rMagRateFromFreq(samplingRateShimmer);
    }

    private void setShimmer2rMagRate(int magRate) {
        if (isShimmerGen2()) {
            mSensorShimmer2Mag.setShimmer2rMagRate(magRate);
        }
    }

    public boolean isLowPowerMagEnabled() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Mag.isLowPowerMagEnabled();
        } else if (isShimmerGen3()) {
            return mSensorLSM303.isLowPowerMagEnabled();
        } else if (isShimmerGen3R()) {
            return mSensorLIS3MDL.isLowPowerMagEnabled();
        }
        return false;
    }

    public boolean isMedPowerMagEnabled() {
        return mSensorLIS3MDL.isMedPowerMagEnabled();
    }

    public boolean isHighPowerMagEnabled() {
        return mSensorLIS3MDL.isMedPowerMagEnabled();
    }

    public boolean isUltraHighPowerMagEnabled() {
        return mSensorLIS3MDL.isMedPowerMagEnabled();
    }

    public void setDigitalAccelRate(int i) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLSM303DigitalAccelRate(i);
        } else if (isShimmerGen3R()) {
            mSensorLIS2DW12.setLIS2DW12DigitalAccelRate(i);
        }
    }

    public void setDigitalAccelRange(int i) {
        if (isShimmerGen2()) {
            mSensorMMA736x.setAccelRange(i);
        } else if (isShimmerGen3()) {
            mSensorLSM303.setLSM303AccelRange(i);
        } else if (isShimmerGen3R()) {
            mSensorLIS2DW12.setLIS2DW12AccelRange(i);
        }
    }

    public void setAltMagRange(int i) {
        mSensorLIS3MDL.setLIS3MDLAltMagRange(i);
    }

    public void setAltMagRate(int i) {
        mSensorLIS3MDL.setLIS3MDLAltMagRate(i);
    }

    @Deprecated
    public void setLSM303MagRange(int i) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLSM303MagRange(i);
        } else if (isShimmerGen3R()) {
            mSensorLIS2MDL.setLISMagRange(i);
        }
    }

    private int setLSM303MagRateFromFreq(double freq) {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.setLIS2MDLAltMagRateFromFreq(freq);
        } else {
            return mSensorLSM303.setLSM303MagRateFromFreq(freq);
        }
    }

    public int getWRAccelRate() throws Exception {

        if (isShimmerGen2()) {
            throw new Exception("WR Accel not configurable for Shimmer2");
        } else if (isShimmerGen3()) {
            return mSensorLSM303.getLSM303DigitalAccelRate();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getLIS2DW12DigitalAccelRate();
        }
        return (Integer) null;
    }

    public int getGyroRate() throws Exception {
        if (isShimmerGen2()) {
            throw new Exception("Gyro Rate is not configurable for Shimmer2");
        } else if (isShimmerGen3()) {
            return mSensorMpu9x50.getMPU9X50GyroAccelRate();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.getLSM6DSVGyroAccelRate();
        }
        return (Integer) null;
    }

    public int getMagRate() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Mag.getMagRate();
        } else if (isShimmerGen3()) {
            return mSensorLSM303.getLSM303MagRate();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getLIS2MDLMagRate();
        }
        return 0;
    }

    public void setMagRate(int magRate) {
        if (isShimmerGen2()) {
            mSensorShimmer2Mag.setShimmer2rMagRate(magRate);
        } else if (isShimmerGen3()) {
            mSensorLSM303.setLSM303MagRate(magRate);
        } else if (isShimmerGen3R()) {
            mSensorLIS2MDL.setLISMagRateInternal(magRate);
        }
    }

    private int setLSM303AccelRateFromFreq(double freq) {
        return mSensorLSM303.setLSM303AccelRateFromFreq(freq);
    }

    private int setLIS2DW12AccelRateFromFreq(double freq) {
        return mSensorLIS2DW12.setLIS2DW12AccelRateFromFreq(freq);
    }

    private void setDefaultLsm303AccelSensorConfig(boolean state) {
        mSensorLSM303.setDefaultLsm303AccelSensorConfig(state);
    }

    private void setDefaultLIS2DW12AccelSensorConfig(boolean state) {
        mSensorLIS2DW12.setDefaultLIS2DW12AccelSensorConfig(state);
    }

    private void setDefaultLsm303MagSensorConfig(boolean state) {
        mSensorLSM303.setDefaultLsm303MagSensorConfig(state);
    }

    public boolean isHighResAccelWR() {
        return mSensorLSM303.isHighResAccelWR();
    }

    public void setHighResAccelWR(boolean enable) {
        mSensorLSM303.setHighResAccelWR(enable);
    }

    public void setHighResAccelWR(int i) {
        mSensorLSM303.setHighResAccelWR(i);
    }

    public boolean isHighPerModeAccelWR() {
        return mSensorLIS2DW12.isHighPerModeAccelWR();
    }

    public void setHighPerModeAccelWR(boolean enable) {
        mSensorLIS2DW12.setHighPerModeAccelWR(enable);
    }

    public void setHighPerModeAccelWR(int i) {
        mSensorLIS2DW12.setHighPerModeAccelWR(i);
    }

    public boolean isLSM303DigitalAccelHRM() {
        return mSensorLSM303.isLSM303DigitalAccelHRM();
    }

    public boolean isLIS2DW12DigitalAccelHPM() {
        return mSensorLIS2DW12.isLIS2DW12DigitalAccelHPM();
    }

    public int getHighResAccelWREnabled() {
        return mSensorLSM303.getHighResAccelWREnabled();
    }

    public int getHighPerModeAccelWREnabled() {
        return mSensorLIS2DW12.getHighPerModeAccelWREnabled();
    }

    public double getCalibTimeWRAccel() {
        if (isShimmerGen3()) {
            return mSensorLSM303.getCalibTimeWRAccel();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getCalibTimeWRAccel();
        }
        return (Double) null;
    }

    public boolean isUsingDefaultWRAccelParam() {
        if (isShimmerGen3()) {
            return mSensorLSM303.isUsingDefaultWRAccelParam();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.isUsingDefaultWRAccelParam();
        }
        return (Boolean) null;
    }

    public boolean isUsingValidWRAccelParam() {
        if (isShimmerGen3()) {
            return mSensorLSM303.isUsingValidWRAccelParam();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.isUsingValidWRAccelParam();
        }
        return (Boolean) null;
    }

    public double getCalibTimeMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getCalibTimeMag();
        } else {
            return mSensorLSM303.getCalibTimeMag();
        }
    }

    public boolean isUsingDefaultMagParam() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.isUsingDefaultMagParam();
        } else {
            return mSensorLSM303.isUsingDefaultMagParam();
        }
    }

    public boolean isUsingValidMagParam() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.isUsingValidMagParam();
        } else {
            return mSensorLSM303.isUsingValidMagParam();
        }
    }

    public boolean isUsingDefaultMagAltParam() {
        return mSensorLIS3MDL.isUsingDefaultMagAltParam();
    }

    public boolean isUsingValidMagAltParam() {
        return mSensorLIS3MDL.isUsingValidMagAltParam();
    }

    public boolean isLowPowerAccelWR() {
        if (isShimmerGen3()) {
            return mSensorLSM303.isLowPowerAccelWR();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.isLowPowerAccelWR();
        }
        return (Boolean) null;
    }

    public void setLowPowerAccelWR(boolean enable) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLowPowerAccelWR(enable);
        } else if (isShimmerGen3R()) {
            mSensorLIS2DW12.setLowPowerAccelWR(enable);
        }
    }

    public boolean isLSM303DigitalAccelLPM() {
        return mSensorLSM303.isLSM303DigitalAccelLPM();
    }

    public boolean isLIS2DW12DigitalAccelLPM() {
        return mSensorLIS2DW12.isLIS2DW12DigitalAccelLPM();
    }

    public boolean isLowPowerAccelEnabled() {
        if (isShimmerGen3()) {
            return mSensorLSM303.isLowPowerAccelEnabled();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.isLowPowerAccelEnabled();
        }
        return (Boolean) null;
    }

    public int getLowPowerAccelEnabled() {
        if (isShimmerGen3()) {
            return mSensorLSM303.getLowPowerAccelEnabled();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getLowPowerAccelEnabled();
        }
        return (Integer) null;
    }

    public void setLowPowerAccelEnabled(int i) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLowPowerAccelEnabled(i);
        } else if (isShimmerGen3R()) {
            mSensorLIS2DW12.setLowPowerAccelEnabled(i);
        }
    }

    public int getLowPowerMagEnabled() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getLowPowerMagEnabled();
        } else {
            return mSensorLSM303.getLowPowerMagEnabled();
        }
    }

    public int getMedPowerMagEnabled() {
        return mSensorLIS3MDL.getMedPowerMagEnabled();
    }

    public int getHighPowerMagEnabled() {
        return mSensorLIS3MDL.getHighPowerMagEnabled();
    }

    public int getUltraHighPowerMagEnabled() {
        return mSensorLIS3MDL.getUltraHighPowerMagEnabled();
    }

    public int getAccelRange() {
        if (isShimmerGen2()) {
            return mSensorMMA736x.getAccelRange();
        } else if (isShimmerGen3()) {
            return mSensorLSM303.getAccelRange();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getAccelRange();
        }
        return (Integer) null;
    }

    public void setAccelRange(int i) {
        setDigitalAccelRange(i);
    }

    public int getMagRange() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Mag.getMagRange();
        } else if (isShimmerGen3()) {
            return mSensorLSM303.getMagRange();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getMagRange();
        }
        return 0;
    }

    public void setMagRange(int i) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLSM303MagRange(i);
        } else if (isShimmerGen3R()) {
            mSensorLIS2MDL.setLISMagRange(i);
        }
    }

    public int getLSM303DigitalAccelRate() {
        return mSensorLSM303.getLSM303DigitalAccelRate();
    }

    public void setLSM303DigitalAccelRate(int mLSM303DigitalAccelRate) {
        if (isShimmerGen3()) {
            mSensorLSM303.setLSM303DigitalAccelRate(mLSM303DigitalAccelRate);
        }
    }

    public int getLIS2DW12DigitalAccelRate() {
        return mSensorLIS2DW12.getLIS2DW12DigitalAccelRate();
    }

    public void setLIS2DW12DigitalAccelRate(int mLIS2DW12DigitalAccelRate) {
        if (isShimmerGen3R()) {
            mSensorLIS2DW12.setLIS2DW12DigitalAccelRate(mLIS2DW12DigitalAccelRate);
        }
    }

    public double[][] getAlignmentMatrixWRAccel() {
        if (isShimmerGen3()) {
            return mSensorLSM303.getAlignmentMatrixWRAccel();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getAlignmentMatrixWRAccel();
        }
        return null;
    }

    public double[][] getSensitivityMatrixWRAccel() {
        if (isShimmerGen3()) {
            return mSensorLSM303.getSensitivityMatrixWRAccel();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getSensitivityMatrixWRAccel();
        }
        return null;
    }

    public double[][] getOffsetVectorMatrixWRAccel() {
        if (isShimmerGen3()) {
            return mSensorLSM303.getOffsetVectorMatrixWRAccel();
        } else if (isShimmerGen3R()) {
            return mSensorLIS2DW12.getOffsetVectorMatrixWRAccel();
        }
        return null;
    }

    public double[][] getAlignmentMatrixMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getAlignmentMatrixMag();
        } else {
            return mSensorLSM303.getAlignmentMatrixMag();
        }
    }

    public double[][] getSensitivityMatrixMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getSensitivityMatrixMag();
        } else {
            return mSensorLSM303.getSensitivityMatrixMag();
        }
    }

    public double[][] getOffsetVectorMatrixMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS2MDL.getOffsetVectorMatrixMag();
        } else {
            return mSensorLSM303.getOffsetVectorMatrixMag();
        }
    }

    public double[][] getOffsetVectorMatrixHighGAccel() {
        if (isShimmerGen3R()) {
            return mSensorADXL371.getOffsetVectorMatrixHighGAccel();
        }
        return null;
    }

    public double[][] getAlignmentMatrixHighGAccel() {
        if (isShimmerGen3R()) {
            return mSensorADXL371.getAlignmentMatrixHighGAccel();
        }
        return null;
    }


    public double[][] getSensitivityMatrixHighGAccel() {
        if (isShimmerGen3R()) {
            return mSensorADXL371.getSensitivityMatrixHighGAccel();
        }
        return null;
    }

    public double[][] getOffsetVectorMatrixAltMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS3MDL.getOffsetVectorMatrixMagAlt();
        }
        return null;
    }

    public double[][] getAlignmentMatrixAltMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS3MDL.getAlignmentMatrixMagAlt();
        }
        return null;
    }

    public double[][] getSensitivityMatrixAltMag() {
        if (isShimmerGen3R()) {
            return mSensorLIS3MDL.getSensitivityMatrixMagAlt();
        }
        return null;
    }

    public int getSamplingDividerLsm303dlhcAccel() {
        return mSamplingDividerLsm303dlhcAccel;
    }

    public void setSamplingDividerLsm303dlhcAccel(int mSamplingDividerLsm303dlhcAccel) {
        this.mSamplingDividerLsm303dlhcAccel = mSamplingDividerLsm303dlhcAccel;
    }

    public byte[] getGyroCalRawParams() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Gyro.mCurrentCalibDetailsGyro.generateCalParamByteArray();
        } else if (isShimmerGen3()) {
            return mSensorMpu9x50.mCurrentCalibDetailsGyro.generateCalParamByteArray();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.mCurrentCalibDetailsGyro.generateCalParamByteArray();
        }
        return null;
    }

    public void updateCurrentGyroCalibInUse() {
        if (isShimmerGen2()) {
            mSensorShimmer2Gyro.updateCurrentCalibInUse();
        } else if (isShimmerGen3()) {
            mSensorMpu9x50.updateCurrentGyroCalibInUse();
        } else if (isShimmerGen3R()) {
            mSensorLSM6DSV.updateCurrentGyroCalibInUse();
        }
    }

    public void enableOnTheFlyGyroCal(boolean state, int bufferSize, double threshold) {
        if (isShimmerGen2()) {
            mSensorShimmer2Gyro.enableOnTheFlyGyroCal(state, bufferSize, threshold);
        } else {
            AbstractAlgorithm abstractAlgorithm = getAlgorithmModule(GyroOnTheFlyCalModule.GENERAL_ALGORITHM_NAME);
            if (abstractAlgorithm != null) {
                setIsAlgorithmEnabled(GyroOnTheFlyCalModule.GENERAL_ALGORITHM_NAME, state);
                GyroOnTheFlyCalModule gyroOnTheFlyCalModule = (GyroOnTheFlyCalModule) abstractAlgorithm;
                gyroOnTheFlyCalModule.enableOnTheFlyGyroCal(state, bufferSize, threshold);
            }
        }
    }

    public void setOnTheFlyGyroCal(boolean state) {
        if (isShimmerGen2()) {
            mSensorShimmer2Gyro.setOnTheFlyGyroCal(state);
        } else {
            setIsAlgorithmEnabled(GyroOnTheFlyCalModule.GENERAL_ALGORITHM_NAME, state);
        }
    }

    public boolean isGyroOnTheFlyCalEnabled() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Gyro.isGyroOnTheFlyCalEnabled();
        } else {
            return isAlgorithmEnabled(GyroOnTheFlyCalModule.GENERAL_ALGORITHM_NAME);
        }
    }

    private OnTheFlyGyroOffsetCal getOnTheFlyCalGyro() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Gyro.getOnTheFlyCalGyro();
        } else {
            return null;
        }
    }

    public CalibDetailsKinematic getCurrentCalibDetailsGyro() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Gyro.getCurrentCalibDetailsGyro();
        } else if (isShimmerGen3() || isShimmerGenGq()) {
            return mSensorMpu9x50.getCurrentCalibDetailsGyro();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.getCurrentCalibDetailsGyro();
        }
        return null;
    }

    public byte[] generateCalParamGyroscope() {
        return getCurrentCalibDetailsGyro().generateCalParamByteArray();
    }

    public void parseCalibParamFromPacketGyro(byte[] bufferCalibrationParameters, CALIB_READ_SOURCE calibReadSource) {
        getCurrentCalibDetailsGyro().parseCalParamByteArray(bufferCalibrationParameters, calibReadSource);
    }

    private void setDefaultCalibrationShimmer3Gyro() {
        getCurrentCalibDetailsGyro().resetToDefaultParameters();
    }

    public int setMPU9150GyroAccelRateFromFreq(double freq) {
        return mSensorMpu9x50.setMPU9150GyroAccelRateFromFreq(freq);
    }

    private int setMPU9150MagRateFromFreq(double freq) {
        return mSensorMpu9x50.setMPU9150MagRateFromFreq(freq);
    }

    private int setMPU9150MplRateFromFreq(double freq) {
        return mSensorMpu9x50.setMPU9150MplRateFromFreq(freq);
    }

    private void setDefaultMpu9150GyroSensorConfig(boolean state) {
        mSensorMpu9x50.setDefaultMpu9150GyroSensorConfig(state);
    }

    private void setDefaultMpu9150AccelSensorConfig(boolean state) {
        mSensorMpu9x50.setDefaultMpu9150AccelSensorConfig(state);
    }

    private void setDefaultMpu9150MplSensorConfig(boolean isSensorEnabled) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setDefaultMpu9150MplSensorConfig(isSensorEnabled);
        }
    }

    private boolean checkIfAMpuGyroOrAccelEnabled() {
        if (mSensorMpu9x50 != null) {
            return mSensorMpu9x50.checkIfAMpuGyroOrAccelEnabled();
        }
        return false;
    }

    private boolean checkIfAnyOtherMplChannelEnabled(int sensorId) {
        if (mSensorMpu9x50 != null) {
            return mSensorMpu9x50.checkIfAnyOtherMplChannelEnabled(sensorId);
        }
        return false;
    }

    public boolean checkIfAnyMplChannelEnabled() {
        if (mSensorMpu9x50 != null) {
            return mSensorMpu9x50.checkIfAnyMplChannelEnabled();
        }
        return false;
    }

    public int getMPU9X50GyroAccelRate() {
        return mSensorMpu9x50.getMPU9X50GyroAccelRate();
    }

    public void setMPU9150GyroAccelRate(int rate) {
        mSensorMpu9x50.setMPU9X50GyroAccelRate(rate);
    }

    public int getLSM6DSVGyroAccelRate() {
        return mSensorLSM6DSV.getLSM6DSVGyroAccelRate();
    }

    public void setLSM6DSVGyroAccelRate(int rate) {
        mSensorLSM6DSV.setLSM6DSVGyroAccelRate(rate);
    }

    public int getMPU9X50MotCalCfg() {
        return mSensorMpu9x50.getMPU9X50MotCalCfg();
    }

    public int getMPU9X50LPF() {
        return mSensorMpu9x50.getMPU9X50LPF();
    }

    public int getMPU9X50DMP() {
        return mSensorMpu9x50.getMPU9X50DMP();
    }

    public int getMPU9X50MPLSamplingRate() {
        return mSensorMpu9x50.getMPU9X50MPLSamplingRate();
    }

    public int getMPU9X50MagSamplingRate() {
        return mSensorMpu9x50.getMPU9X50MagSamplingRate();
    }

    public double getMPU9X50GyroAccelRateInHz() {
        return mSensorMpu9x50.getMPU9X50GyroAccelRateInHz();
    }

    public void setMPU9150AccelRange(int i) {
        mSensorMpu9x50.setMPU9X50AccelRange(i);
    }

    public int getMPU9X50AccelRange() {
        return mSensorMpu9x50.getMPU9X50AccelRange();
    }


    public int getGyroRange() {
        if (isShimmerGen2()) {
            return mSensorShimmer2Gyro.getGyroRange();
        } else if (isShimmerGen3()) {
            return mSensorMpu9x50.getGyroRange();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.getGyroRange();
        }
        return (Integer) null;
    }

    public void setGyroRange(int newRange) {
        if (isShimmerGen2()) {
            mSensorShimmer2Gyro.setGyroRange(newRange);
        } else if (isShimmerGen3()) {
            mSensorMpu9x50.setGyroRange(newRange);
        } else if (isShimmerGen3R()) {
            mSensorLSM6DSV.setGyroRange(newRange);
        }
    }

    protected void setMPU9150GyroRange(int i) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9150GyroRange(i);
        }
    }

    protected void setLSM6DSVGyroRange(int i) {
        if (mSensorLSM6DSV != null) {
            mSensorLSM6DSV.setLSM6DSVGyroRange(i);
        }
    }

    public void setMPU9150MPLSamplingRate(int mMPU9150MPLSamplingRate) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9150MPLSamplingRate(mMPU9150MPLSamplingRate);
        }
    }

    public void setMPU9150MagSamplingRate(int mMPU9150MagSamplingRate) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9X50MagSamplingRate(mMPU9150MagSamplingRate);
        }
    }

    public boolean isMPU9150DMP() {
        return mSensorMpu9x50.isMPU9150DMP();
    }

    public void setMPU9150DMP(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9150DMP(state);
        }
    }

    public void setMPU9150DMP(int i) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9150DMP(i);
        }
    }

    public int getMPLEnable() {
        return mSensorMpu9x50.getMPLEnable();
    }

    public boolean isMPLEnabled() {
        return mSensorMpu9x50.isMPLEnable();
    }

    public void setMPLEnabled(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLEnabled(state);
        }
    }

    public void setMPLEnabled(int state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLEnabled(state);
        }
    }

    public int getMPLGyroCalTC() {
        return mSensorMpu9x50.getMPLGyroCalTC();
    }

    public boolean isMPLGyroCalTC() {
        return mSensorMpu9x50.isMPLGyroCalTC();
    }

    public void setMPLGyroCalTC(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLGyroCalTC(state);
        }
    }

    public void setMPLGyroCalTC(int state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLGyroCalTC(state);
        }
    }

    public int getMPLVectCompCal() {
        return mSensorMpu9x50.getMPLVectCompCal();
    }

    public boolean isMPLVectCompCal() {
        return mSensorMpu9x50.isMPLVectCompCal();
    }

    public void setMPLVectCompCal(boolean state) {
        mSensorMpu9x50.setMPLVectCompCal(state);
    }

    public void setMPLVectCompCal(int state) {
        mSensorMpu9x50.setMPLVectCompCal(state);
    }

    public int getMPLMagDistCal() {
        return mSensorMpu9x50.getMPLMagDistCal();
    }

    public boolean isMPLMagDistCal() {
        return mSensorMpu9x50.isMPLMagDistCal();
    }

    public void setMPLMagDistCal(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLMagDistCal(state);
        }
    }

    public void setMPLMagDistCal(int state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLMagDistCal(state);
        }
    }

    public int getMPLSensorFusion() {
        return mSensorMpu9x50.getMPLSensorFusion();
    }

    public boolean isMPLSensorFusion() {
        return mSensorMpu9x50.isMPLSensorFusion();
    }

    public void setMPLSensorFusion(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLSensorFusion(state);
        }
    }

    public void setMPLSensorFusion(int state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPLSensorFusion(state);
        }
    }

    public void setMPU9150MotCalCfg(boolean state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9150MotCalCfg(state);
        }
    }

    public void setMPU9150MotCalCfg(int state) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9X150MotCalCfg(state);
        }
    }

    public void setMPU9150LPF(int mMPU9150LPF) {
        if (mSensorMpu9x50 != null) {
            mSensorMpu9x50.setMPU9X50LPF(mMPU9150LPF);
        }
    }

    public double getCalibTimeGyro() {
        return getCurrentCalibDetailsGyro().getCalibTimeMs();
    }

    public double[][] getAlignmentMatrixGyro() {
        return getCurrentCalibDetailsGyro().getValidAlignmentMatrix();
    }

    public double[][] getSensitivityMatrixGyro() {
        return getCurrentCalibDetailsGyro().getValidSensitivityMatrix();
    }

    public double[][] getOffsetVectorMatrixGyro() {
        return getCurrentCalibDetailsGyro().getValidOffsetVector();
    }

    public double[][] getOffsetVectorMPLAccel() {
        return mSensorMpu9x50.getOffsetVectorMPLAccel();
    }

    public double[][] getSensitivityMatrixMPLAccel() {
        return mSensorMpu9x50.getSensitivityMatrixMPLAccel();
    }

    public double[][] getAlignmentMatrixMPLAccel() {
        return mSensorMpu9x50.getAlignmentMatrixMPLAccel();
    }

    public double[][] getOffsetVectorMPLMag() {
        return mSensorMpu9x50.getOffsetVectorMPLMag();
    }

    public double[][] getSensitivityMatrixMPLMag() {
        return mSensorMpu9x50.getSensitivityMatrixMPLMag();
    }

    public double[][] getAlignmentMatrixMPLMag() {
        return mSensorMpu9x50.getAlignmentMatrixMPLMag();
    }

    public double[][] getOffsetVectorMPLGyro() {
        return mSensorMpu9x50.getOffsetVectorMPLGyro();
    }

    public double[][] getSensitivityMatrixMPLGyro() {
        return mSensorMpu9x50.getSensitivityMatrixMPLGyro();
    }

    public double[][] getAlignmentMatrixMPLGyro() {
        return mSensorMpu9x50.getAlignmentMatrixMPLGyro();
    }


    public boolean isLowPowerGyroEnabled() {
        if (isShimmerGen3()) {
            return mSensorMpu9x50.isLowPowerGyroEnabled();
        } else if (isShimmerGen3R()) {
            return mSensorLSM6DSV.isLowPowerGyroEnabled();
        }
        return (Boolean) null;
    }

    public boolean isUsingDefaultGyroParam() {
        return getCurrentCalibDetailsGyro().isUsingDefaultParameters();
    }

    public boolean isUsingValidGyroParam() {
        if (!UtilShimmer.isAllZeros(getAlignmentMatrixGyro()) && !UtilShimmer.isAllZeros(getSensitivityMatrixGyro())) {
            return true;
        } else {
            return false;
        }
    }

    protected void setLowPowerGyro(boolean enable) {
        if (mShimmerVerObject.isShimmerGen2()) {
            mSensorShimmer2Gyro.setLowPowerGyro(enable);
        } else if (mShimmerVerObject.isShimmerGen3()) {
            mSensorMpu9x50.setLowPowerGyro(enable);
        } else if (mShimmerVerObject.isShimmerGen3R()) {
            mSensorLSM6DSV.setLowPowerGyro(enable);
        }
    }

    public boolean checkLowPowerGyro() {
        if (mShimmerVerObject.isShimmerGen2()) {
            return mSensorShimmer2Gyro.checkLowPowerGyro();
        } else if (mShimmerVerObject.isShimmerGen3()) {
            return mSensorMpu9x50.checkLowPowerGyro();
        } else if (mShimmerVerObject.isShimmerGen3R()) {
            return mSensorLSM6DSV.checkLowPowerGyro();
        }
        return false;
    }


    public int getLowPowerGyroEnabled() {
        if (mShimmerVerObject.isShimmerGen2()) {
            return mSensorShimmer2Gyro.getLowPowerGyroEnabled();
        } else if (mShimmerVerObject.isShimmerGen3()) {
            return mSensorMpu9x50.getLowPowerGyroEnabled();
        } else if (mShimmerVerObject.isShimmerGen3R()) {
            return mSensorLSM6DSV.getLowPowerGyroEnabled();
        }
        return 0;
    }

    @Override
    public Object getConfigValueUsingConfigLabel(String identifier, String configLabel) {
        Object returnValue = super.getConfigValueUsingConfigLabel(identifier, configLabel);
        if (returnValue != null) {
            return returnValue;
        }

        if ((configLabel.equals(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE))
                || (configLabel.equals(SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE))) {
            checkConfigOptionValues(configLabel);
        }

        Integer sensorId = Configuration.Shimmer3.SENSOR_ID.RESERVED_ANY_SENSOR;
        try {
            sensorId = Integer.parseInt(identifier);
        } catch (NumberFormatException nFE) {
        }

        switch (configLabel) {
            case (Configuration.Shimmer3.GuiLabelConfig.USER_BUTTON_START):
                returnValue = isButtonStart();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SD_STREAM_WHEN_RECORDING):
                returnValue = isDisableBluetooth();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SINGLE_TOUCH_START):
                returnValue = isSingleTouch();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.ENABLE_ERROR_LEDS_RTC):
                returnValue = isShowErrorLedsRtc();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.ENABLE_ERROR_LEDS_SD):
                returnValue = isShowErrorLedsSd();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER):
                returnValue = isMasterShimmer();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING):
                returnValue = isSyncWhenLogging();
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.KINEMATIC_LPM):
                if (isLSM303DigitalAccelLPM() && checkLowPowerGyro() && checkLowPowerMag()) {
                    returnValue = true;
                } else {
                    returnValue = false;
                }
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.TCXO):
                returnValue = isTCXO();
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.LOW_POWER_AUTOSTOP):
                returnValue = isLowBattAutoStop();
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.BLUETOOTH_BAUD_RATE):
                returnValue = getBluetoothBaudRate();
                break;
            case (SensorGSR.GuiLabelConfig.GSR_RANGE):
                returnValue = getGSRRange();
                break;

            case (SensorEXG.GuiLabelConfig.EXG_RESOLUTION):
                returnValue = getExGResolution();
                break;

            case (SensorEXG.GuiLabelConfig.EXG_GAIN):
                returnValue = getExGGainSetting();
                break;

            case (SensorEXG.GuiLabelConfig.EXG_RATE):
                returnValue = getEXG1RateSetting();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE):
                returnValue = getEXGReferenceElectrode();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_DETECTION):
                returnValue = getEXGLeadOffCurrentMode();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_CURRENT):
                returnValue = getEXGLeadOffDetectionCurrent();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR):
                returnValue = getEXGLeadOffComparatorTreshold();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ):
                returnValue = getEXG2RespirationDetectFreq();
                break;
            case (SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE):
                returnValue = getEXG2RespirationDetectPhase();
                break;
            case (SensorPPG.GuiLabelConfig.PPG_ADC_SELECTION):
                returnValue = getPpgAdcSelectionGsrBoard();
                break;
            case (SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION):
                returnValue = getPpg1AdcSelectionProto3DeluxeBoard();
                break;
            case (SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION):
                returnValue = getPpg2AdcSelectionProto3DeluxeBoard();
                break;


            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_GSR):
                returnValue = getSamplingDividerGsr();
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL):
                returnValue = getSamplingDividerLsm303dlhcAccel();
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_PPG):
                returnValue = getSamplingDividerPpg();
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_VBATT):
                returnValue = getSamplingDividerVBatt();
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.BUFFER_SIZE):
                returnValue = Integer.toString(getBufferSize());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_MAC_FROM_INFOMEM):
                returnValue = getMacIdFromInfoMem();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_ID):
                returnValue = Integer.toString(getExperimentId());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS):
                returnValue = Integer.toString(getTrialNumberOfShimmers());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED):
                returnValue = Integer.toString(getTrialDurationEstimatedInSecs());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM):
                returnValue = Integer.toString(getTrialDurationMaximumInSecs());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.BROADCAST_INTERVAL):
                returnValue = Integer.toString(getSyncBroadcastInterval());
                break;


        }

        return returnValue;
    }

    @Override
    public Object setConfigValueUsingConfigLabel(String identifier, String configLabel, Object valueToSet) {

        Object returnValue = null;
        int buf = 0;

        switch (configLabel) {
            case (Configuration.Shimmer3.GuiLabelConfig.USER_BUTTON_START):
                setButtonStart((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SD_BT_STREAM_WHEN_RECORDING):
                setDisableBluetooth(!(boolean) valueToSet);
                setSyncWhenLogging(!(boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SD_STREAM_WHEN_RECORDING):
                setDisableBluetooth((boolean) valueToSet);
                setSyncWhenLogging(!(boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SINGLE_TOUCH_START):
                setSingleTouch((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_MASTER_SHIMMER):
                setMasterShimmer((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.ENABLE_ERROR_LEDS_RTC):
                setShowErrorLedsRtc((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.ENABLE_ERROR_LEDS_SD):
                setShowErrorLedsSd((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_SYNC_WHEN_LOGGING):
                setDisableBluetooth(!(boolean) valueToSet);
                setSyncWhenLogging((boolean) valueToSet);
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.KINEMATIC_LPM):
                setLowPowerAccelWR((boolean) valueToSet);
                setLowPowerGyro((boolean) valueToSet);
                setLowPowerMag((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.TCXO):
                setTCXO((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_BOOLEAN):
                setInternalExpPower((boolean) valueToSet);
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.LOW_POWER_AUTOSTOP):
                setLowBattAutoStop((boolean) valueToSet);
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.BLUETOOTH_BAUD_RATE):
                setBluetoothBaudRate((int) valueToSet);
                break;

            case (SensorGSR.GuiLabelConfig.GSR_RANGE):
                setGSRRange((int) valueToSet);
                break;

            case (SensorEXG.GuiLabelConfig.EXG_RESOLUTION):
                setExGResolution((int) valueToSet);
                break;

            case (SensorEXG.GuiLabelConfig.EXG_GAIN):
                setExGGainSetting((int) valueToSet);
                break;


            case (SensorEXG.GuiLabelConfig.EXG_RATE):
                setEXGRateSetting((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE):
                setEXGReferenceElectrode((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_DETECTION):
                setEXGLeadOffCurrentMode((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_CURRENT):
                setEXGLeadOffDetectionCurrent((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_LEAD_OFF_COMPARATOR):
                setEXGLeadOffComparatorTreshold((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_FREQ):
                setEXG2RespirationDetectFreq((int) valueToSet);
                break;
            case (SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE):
                setEXG2RespirationDetectPhase((int) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_INTEGER):
                setInternalExpPower((int) valueToSet);
                break;
            case (SensorPPG.GuiLabelConfig.PPG_ADC_SELECTION):
                setPpgAdcSelectionGsrBoard((int) valueToSet);
                break;
            case (SensorPPG.GuiLabelConfig.PPG1_ADC_SELECTION):
                setPpg1AdcSelectionProto3DeluxeBoard((int) valueToSet);
                break;
            case (SensorPPG.GuiLabelConfig.PPG2_ADC_SELECTION):
                setPpg2AdcSelectionProto3DeluxeBoard((int) valueToSet);
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_GSR):
                setSamplingDividerGsr((int) valueToSet);
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_LSM303DLHC_ACCEL):
                setSamplingDividerLsm303dlhcAccel((int) valueToSet);
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_PPG):
                setSamplingDividerPpg((int) valueToSet);
                break;
            case (Configuration.ShimmerGqBle.GuiLabelConfig.SAMPLING_RATE_DIVIDER_VBATT):
                setSamplingDividerVBatt((int) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_ID):
                if (!(((String) valueToSet).isEmpty())) {
                    buf = Integer.parseInt((String) valueToSet);
                }
                setExperimentId(buf);

                returnValue = Integer.toString(getExperimentId());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_NUMBER_OF_SHIMMERS):
                if (((String) valueToSet).isEmpty()) {
                    buf = 1;
                } else {
                    buf = Integer.parseInt((String) valueToSet);
                }
                setExperimentNumberOfShimmers(buf);

                returnValue = Integer.toString(getTrialNumberOfShimmers());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_DURATION_ESTIMATED):
                if (((String) valueToSet).isEmpty()) {
                    buf = 1;
                } else {
                    buf = Integer.parseInt((String) valueToSet);
                }
                setExperimentDurationEstimatedInSecs(buf);

                returnValue = Integer.toString(getTrialDurationEstimatedInSecs());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.EXPERIMENT_DURATION_MAXIMUM):
                if (((String) valueToSet).isEmpty()) {
                    buf = 1;
                } else {
                    buf = Integer.parseInt((String) valueToSet);
                }
                setExperimentDurationMaximumInSecs(buf);

                returnValue = Integer.toString(getTrialDurationMaximumInSecs());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.BROADCAST_INTERVAL):
                buf = 1;
                if (!(((String) valueToSet).isEmpty())) {
                    buf = Integer.parseInt((String) valueToSet);
                }
                setSyncBroadcastInterval(buf);

                returnValue = Integer.toString(getSyncBroadcastInterval());
                break;


            case (Configuration.Shimmer3.GuiLabelConfig.ENABLED_SENSORS):
                setEnabledAndDerivedSensorsAndUpdateMaps((int) valueToSet, mDerivedSensors);
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.ENABLED_SENSORS_IDS):
                setSensorIdsEnabled((Integer[]) valueToSet);
                break;

            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE):
                setLowPowerGyro(false);
                setLowPowerAccelWR(false);
                setLowPowerMag(false);
                returnValue = super.setConfigValueUsingConfigLabel(identifier, configLabel, valueToSet);
                checkLowPowerGyro();
                checkLowPowerMag();
                break;

            default:
                returnValue = super.setConfigValueUsingConfigLabel(identifier, configLabel, valueToSet);
                break;
        }

        if ((configLabel.equals(SensorEXG.GuiLabelConfig.EXG_RESPIRATION_DETECT_PHASE))
                || (configLabel.equals(SensorEXG.GuiLabelConfig.EXG_REFERENCE_ELECTRODE))) {
            checkConfigOptionValues(configLabel);
        }

        return returnValue;

    }

    public String getCenter() {
        return mCenter;
    }

    public void setCenter(String value) {
        mCenter = value;
    }

    @Override
    public void setShimmerVersionObject(ShimmerVerObject hwfw) {
        super.setShimmerVersionObject(hwfw);


        updateTimestampByteLength();
        updateCurrentCalibInUse();
    }

    @Override
    public LinkedHashMap<String, Object> generateConfigMap(COMMUNICATION_TYPE commType) {


        LinkedHashMap<String, Object> configMapForDb = super.generateConfigMap(commType);

        configMapForDb.put(SensorGSR.DatabaseConfigHandle.GSR_RANGE, (double) getGSRRange());

        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.USER_BUTTON, (double) getButtonStart());
        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.RTC_SOURCE, (double) getRTCSetByBT());
        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.MASTER_CONFIG, (double) getMasterShimmer());
        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.SINGLE_TOUCH_START, (double) getSingleTouch());
        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.TXCO, (double) getTCXO());
        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.REAL_TIME_CLOCK_DIFFERENCE, (double) getRTCDifferenceInTicks());

        SensorEXG.addExgConfigToDbConfigMap(configMapForDb, getEXG1RegisterArray(), getEXG2RegisterArray());

        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.INITIAL_TIMESTAMP, (double) getInitialTimeStampTicksSd());

        configMapForDb.put(DatabaseConfigHandleShimmerObject.SYNC_WHEN_LOGGING, (double) getSyncWhenLogging());
        configMapForDb.put(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_ESTIMATED, (double) getTrialDurationEstimatedInSecs());
        configMapForDb.put(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_MAXIMUM, (double) getTrialDurationMaximumInSecs());

        configMapForDb.put(ShimmerDevice.DatabaseConfigHandle.LOW_POWER_AUTOSTOP, (double) (isLowBattAutoStop() ? 1 : 0));

        /**
         if(svo!=null && svo.isShimmerGenGq()){
         configColumns.add(GSRMetricsModule.DatabaseConfigHandle.GSR_PEAK_HOLD_LENGTH_S);
         }
         if(shimmerObject.isShimmerGenGq()){
         configValues.add((double) 0);
         }
         configMapForDb.put(key, (double) value);
         */

        return configMapForDb;
    }


    @Override
    public void parseConfigMap(ShimmerVerObject svo, LinkedHashMap<String, Object> mapOfConfigPerShimmer, COMMUNICATION_TYPE commType) {
        super.parseConfigMap(svo, mapOfConfigPerShimmer, commType);

        if (mapOfConfigPerShimmer.containsKey(SensorGSR.DatabaseConfigHandle.GSR_RANGE)) {
            setGSRRange(((Double) mapOfConfigPerShimmer.get(SensorGSR.DatabaseConfigHandle.GSR_RANGE)).intValue());
        }

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.MASTER_CONFIG)) {
            setMasterShimmer(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.MASTER_CONFIG)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.SINGLE_TOUCH_START)) {
            setSingleTouch(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.SINGLE_TOUCH_START)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.TXCO)) {
            setTCXO(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.TXCO)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.REAL_TIME_CLOCK_DIFFERENCE)) {
            setRTCDifferenceInTicks(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.REAL_TIME_CLOCK_DIFFERENCE)).longValue());
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.USER_BUTTON)) {
            setButtonStart(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.USER_BUTTON)) > 0 ? true : false);
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.LOW_POWER_AUTOSTOP)) {
            setLowBattAutoStop(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.LOW_POWER_AUTOSTOP)) > 0 ? true : false);
        }

        byte[] exg1Bytes = SensorEXG.parseExgConfigFromDb(mapOfConfigPerShimmer, EXG_CHIP_INDEX.CHIP1,
                SensorEXG.DatabaseConfigHandle.EXG1_CONFIG_1,
                SensorEXG.DatabaseConfigHandle.EXG1_CONFIG_2,
                SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF,
                SensorEXG.DatabaseConfigHandle.EXG1_CH1_SET,
                SensorEXG.DatabaseConfigHandle.EXG1_CH2_SET,
                SensorEXG.DatabaseConfigHandle.EXG1_RLD_SENSE,
                SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF_SENSE,
                SensorEXG.DatabaseConfigHandle.EXG1_LEAD_OFF_STATUS,
                SensorEXG.DatabaseConfigHandle.EXG1_RESPIRATION_1,
                SensorEXG.DatabaseConfigHandle.EXG1_RESPIRATION_2);
        byte[] exg2Bytes = SensorEXG.parseExgConfigFromDb(mapOfConfigPerShimmer, EXG_CHIP_INDEX.CHIP2,
                SensorEXG.DatabaseConfigHandle.EXG2_CONFIG_1,
                SensorEXG.DatabaseConfigHandle.EXG2_CONFIG_2,
                SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF,
                SensorEXG.DatabaseConfigHandle.EXG2_CH1_SET,
                SensorEXG.DatabaseConfigHandle.EXG2_CH2_SET,
                SensorEXG.DatabaseConfigHandle.EXG2_RLD_SENSE,
                SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF_SENSE,
                SensorEXG.DatabaseConfigHandle.EXG2_LEAD_OFF_STATUS,
                SensorEXG.DatabaseConfigHandle.EXG2_RESPIRATION_1,
                SensorEXG.DatabaseConfigHandle.EXG2_RESPIRATION_2);
        exgBytesGetConfigFrom(exg1Bytes, exg2Bytes);
        checkExgResolutionFromEnabledSensorsVar();

        if (mapOfConfigPerShimmer.containsKey(SensorShimmerClock.DatabaseConfigHandle.INITIAL_TIMESTAMP)) {
            setInitialTimeStampTicksSd(((Double) mapOfConfigPerShimmer.get(SensorShimmerClock.DatabaseConfigHandle.INITIAL_TIMESTAMP)).longValue());
        }


        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandleShimmerObject.SYNC_WHEN_LOGGING)) {
            setSyncWhenLogging(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandleShimmerObject.SYNC_WHEN_LOGGING)).intValue());
        }

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_ESTIMATED)) {
            setExperimentDurationEstimatedInSecs(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_ESTIMATED)).intValue());
        }
        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_MAXIMUM)) {
            setExperimentDurationMaximumInSecs(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandleShimmerObject.TRIAL_DURATION_MAXIMUM)).intValue());
        }


    }

    @Deprecated
    public List<String> getConfigColumnsToInsertInDBLegacy() {
        return getConfigColumnsShimmer3Legacy(getShimmerVerObject(), getExpansionBoardDetails());
    }

    @Deprecated
    public List<Double> getShimmerConfigValuesToInsertInDBLegacy() {
        return getDbConfigValuesFromShimmerLegacy(this);
    }

    public void setUniqueID(String uniqueID) {
        mUniqueID = uniqueID;
        String[] idSplit = mUniqueID.split(".");
        if (idSplit.length >= 3) {
            mDockID = idSplit[0] + "." + idSplit[1];
            try {
                mSlotNumber = Integer.parseInt(idSplit[2]);
            } catch (NumberFormatException nFE) {
            }
        }
    }

    @Deprecated
    public void initialise(int hardwareVersion) {
        setHardwareVersionAndCreateSensorMaps(hardwareVersion);
    }


    public void updateShimmerDriveInfo(ShimmerSDCardDetails shimmerSDCardDetails) {
        this.mShimmerSDCardDetails = shimmerSDCardDetails;
    }

    public void setFirmwareIdentifier(int firmwareId) {
        ShimmerVerObject sVOFwId = new ShimmerVerObject(getHardwareVersion(), firmwareId, getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal());
        setShimmerVersionObject(sVOFwId);
    }

    public void setFirmwareVersion(int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        ShimmerVerObject sVOFwId = new ShimmerVerObject(getHardwareVersion(), getFirmwareIdentifier(), firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
        setShimmerVersionObject(sVOFwId);
    }

    public void setSamplingDividerBeacon(int mSamplingDividerBeacon) {
        this.mSamplingDividerBeacon = mSamplingDividerBeacon;
    }

    public void setExpansionBoardId(int expansionBoardId) {
        mExpansionBoardDetails.mExpansionBoardId = expansionBoardId;
    }


    @Override
    public void parseUartConfigResponse(UartComponentPropertyDetails cPD, byte[] response) {
        if (cPD == UART_COMPONENT_AND_PROPERTY.BAT.ENABLE) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                getSensorMap().get(Configuration.Shimmer3.SENSOR_ID.SHIMMER_VBATT).setIsEnabled((response[0] == 0) ? false : true);
            } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {
                getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.VBATT).setIsEnabled((response[0] == 0) ? false : true);
            }
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.BAT.FREQ_DIVIDER) {
            setSamplingDividerVBatt(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.ENABLE) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                getSensorMap().get(Configuration.Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL).setIsEnabled((response[0] == 0) ? false : true);
            } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {
                getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.LSM303DLHC_ACCEL).setIsEnabled((response[0] == 0) ? false : true);
            }
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.DATA_RATE) {
            setLSM303DigitalAccelRate(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.RANGE) {
            setDigitalAccelRange(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.LP_MODE) {
            setLowPowerAccelWR((response[0] == 0) ? false : true);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.HR_MODE) {
            setHighResAccelWR((response[0] == 0) ? false : true);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.FREQ_DIVIDER) {
            setSamplingDividerLsm303dlhcAccel(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.CALIBRATION) {
            parseCalibParamFromPacketAccelLsm(response, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.GSR.ENABLE) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                getSensorMap().get(Configuration.Shimmer3.SENSOR_ID.SHIMMER_GSR).setIsEnabled((response[0] == 0) ? false : true);
            } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {
                getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.GSR).setIsEnabled((response[0] == 0) ? false : true);
            }
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.GSR.RANGE) {
            setGSRRange(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.GSR.FREQ_DIVIDER) {
            setSamplingDividerGsr(response[0]);
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.BEACON.ENABLE) {
            if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_3R) {
                getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.BEACON).setIsEnabled((response[0] == 0) ? false : true);
            } else if (getHardwareVersion() == HW_ID.SHIMMER_GQ_BLE) {
                getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.BEACON).setIsEnabled((response[0] == 0) ? false : true);
            }
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.BEACON.FREQ_DIVIDER) {
            setSamplingDividerBeacon(response[0]);
        } else {
            super.parseUartConfigResponse(cPD, response);
        }
    }

    @Override
    public byte[] generateUartConfigMessage(UartComponentPropertyDetails cPD) {


        if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.ENABLE) {
            byte[] response = new byte[1];
            response[0] = (byte) (getSensorMap().get(Configuration.ShimmerGqBle.SENSOR_ID.LSM303DLHC_ACCEL).isEnabled() ? 1 : 0);
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.DATA_RATE) {
            byte[] response = new byte[1];
            response[0] = (byte) getLSM303DigitalAccelRate();
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.RANGE) {
            byte[] response = new byte[1];
            response[0] = (byte) getAccelRange();
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.LP_MODE) {
            byte[] response = new byte[1];
            response[0] = (byte) (isLowPowerAccelWR() ? 1 : 0);
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.HR_MODE) {
            byte[] response = new byte[1];
            response[0] = (byte) (isHighResAccelWR() ? 1 : 0);
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.FREQ_DIVIDER) {
            byte[] response = new byte[1];
            response[0] = (byte) (getSamplingDividerLsm303dlhcAccel());
            return response;
        } else if (cPD == UART_COMPONENT_AND_PROPERTY.LSM303DLHC_ACCEL.CALIBRATION) {
            byte[] response = generateCalParamLSM303DLHCAccel();
            return response;
        } else {
            return super.generateUartConfigMessage(cPD);
        }
    }

    @Override
    public double getMinAllowedSamplingRate() {
        double minAllowedSamplingRate = super.getMinAllowedSamplingRate();
        if (isMPLEnabled() || isMPU9150DMP()) {
            minAllowedSamplingRate = Math.max(51.2, minAllowedSamplingRate);
        }
        return minAllowedSamplingRate;
    }


    public boolean isSupportedErrorLedControl() {
        return (mShimmerVerObject.compareVersions(FW_ID.LOGANDSTREAM, 0, 7, 12)
                || mShimmerVerObject.compareVersions(FW_ID.STROKARE, ShimmerVerDetails.ANY_VERSION, ShimmerVerDetails.ANY_VERSION, ShimmerVerDetails.ANY_VERSION));
    }

    public boolean isSupportedBmp280() {
        return isSupportedNewImuSensors();
    }

    public boolean isSupportedNewImuSensors() {
        return isSupportedNewImuSensors(getShimmerVerObject(), getExpansionBoardDetails());
    }

    public boolean isShimmer3RwithHighGAccelSupport() {
        return isShimmer3RwithHighGAccelSupport(getShimmerVerObject(), getExpansionBoardDetails());
    }

    public boolean isShimmer3RwithAltMagSupport() {
        return isShimmer3RwithAltMagSupport(getShimmerVerObject(), getExpansionBoardDetails());
    }

    public boolean isSupportedBtBleControl() {
        BluetoothModuleVersionDetails bluetoothModuleVersionDetails = getBtFwVerDetails();
        return (bluetoothModuleVersionDetails.isBtModuleVersionKnown()
                && !bluetoothModuleVersionDetails.isBtModuleVersionRn41()
                && !bluetoothModuleVersionDetails.isBtModuleVersionRn42());
    }

    @Override
    public LinkedHashMap<String, ChannelDetails> getMapOfAllChannelsForStoringToDB(COMMUNICATION_TYPE commType, CHANNEL_TYPE channelType, boolean isKeyOJCName, boolean showDisabledChannels) {
        LinkedHashMap<String, ChannelDetails> mapOfChannelsForStoringToDb = super.getMapOfAllChannelsForStoringToDB(commType, channelType, isKeyOJCName, showDisabledChannels);

        if (commType != COMMUNICATION_TYPE.SD || (commType == COMMUNICATION_TYPE.SD && !isSyncWhenLogging())) {
            mapOfChannelsForStoringToDb.remove(SensorShimmerClock.ObjectClusterSensorName.TIMESTAMP_OFFSET);
        }

        if (!isRtcDifferenceSet()) {
            mapOfChannelsForStoringToDb.remove(SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK);
        }

        return mapOfChannelsForStoringToDb;
    }

    @Override
    public double getSamplingClockFreq() {
        if (isTCXO()) {
            if (isTcxoClock20MHz()) {
                return 312500.0;
            } else {
                return 255765.625;
            }
        } else {
            return super.getSamplingClockFreq();
        }
    }

    private boolean isTcxoClock20MHz() {
        ShimmerVerObject svo = getShimmerVerObject();
        ExpansionBoardDetails ebd = getExpansionBoardDetails();

        int expBrdId = ebd.getExpansionBoardId();
        int expBrdRev = ebd.getExpansionBoardRev();
        int expBrdRevSpecial = ebd.getExpansionBoardRevSpecial();

        if ((svo.getHardwareVersion() == HW_ID.SHIMMER_3 || svo.getHardwareVersion() == HW_ID.SHIMMER_3R) && (
                (expBrdId == HW_ID_SR_CODES.EXP_BRD_EXG_UNIFIED && expBrdRev == 1 && expBrdRevSpecial == 1))) {
            return true;
        } else {
            return false;
        }
    }

    public void setEnableCalibration(boolean enable) {
        mEnableCalibration = enable;
    }

    protected void determineCalibrationParamsForIMU() {
        if (isShimmerGen2()) {
            mSensorMMA736x.updateIsUsingDefaultLNAccelParam();
            mSensorShimmer2Mag.updateIsUsingDefaultMagParam();
            mSensorShimmer2Gyro.updateIsUsingDefaultGyroParam();
        } else if (isShimmerGen3()) {
            mSensorKionixAccel.updateIsUsingDefaultLNAccelParam();
            mSensorLSM303.updateIsUsingDefaultWRAccelParam();
            mSensorLSM303.updateIsUsingDefaultMagParam();
            mSensorMpu9x50.updateIsUsingDefaultGyroParam();
        } else if (isShimmerGen3R()) {
            mSensorLSM6DSV.updateIsUsingDefaultLNAccelParam();
            mSensorLIS2DW12.updateIsUsingDefaultWRAccelParam();
            mSensorLIS2MDL.updateIsUsingDefaultMagParam();
            mSensorLSM6DSV.updateIsUsingDefaultGyroParam();
        }
    }

    public void enableArraysDataStructure(boolean enable) {
        mUseArraysDataStructureInObjectCluster = enable;
    }

    public BluetoothModuleVersionDetails getBtFwVerDetails() {
        return bluetoothModuleVersionDetails;
    }

    public void setBtFwVerDetails(BluetoothModuleVersionDetails bluetoothModuleVersionDetails) {
        this.bluetoothModuleVersionDetails = bluetoothModuleVersionDetails;
    }

    @Override
    public String getRadioModel() {
        return getBtFwVerDetails().getUserFriendlyName();
    }

    public enum TEST_MODE {
        MAIN_TEST((byte) 0, "Main Test"),
        LED_TEST((byte) 1, "LED Test"),
        IC_TEST((byte) 2, "IC Test");

        private final byte byteInstruction;
        private final String description;

        TEST_MODE(byte byteInstruction, String description) {
            this.byteInstruction = byteInstruction;
            this.description = description;
        }

        public byte getByteInstruction() {
            return byteInstruction;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return description + " (" + String.format("0x%02X", byteInstruction) + ")";
        }


    }

    public static final class DatabaseConfigHandleShimmerObject {
        public static final String SYNC_WHEN_LOGGING = "Sync_When_Logging";
        public static final String TRIAL_DURATION_ESTIMATED = "Trial_Dur_Est";
        public static final String TRIAL_DURATION_MAXIMUM = "Trial_Dur_Max";
    }

    public class SDLogHeader {

        public final static int ACCEL_LN = 1 << 7;
        public final static int GYRO = 1 << 6;
        public final static int MAG = 1 << 5;
        public final static int EXG1_24BIT = 1 << 4;
        public final static int EXG2_24BIT = 1 << 3;
        public final static int GSR = 1 << 2;
        public final static int EXT_EXP_A7 = 1 << 1;
        public final static int EXT_EXP_A6 = 1 << 0;
        public final static int BRIDGE_AMP = 1 << 15;
        public final static int ECG_TO_HR_FW = 1 << 14;
        public final static int BATTERY = 1 << 13;
        public final static int ACCEL_WR = 1 << 12;
        public final static int EXT_EXP_A15 = 1 << 11;
        public final static int INT_EXP_A1 = 1 << 10;
        public final static int INT_EXP_A12 = 1 << 9;
        public final static int INT_EXP_A13 = 1 << 8;
        public final static int INT_EXP_A14 = 1 << 23;
        public final static int ACCEL_MPU = 1 << 22;
        public final static int MAG_MPU = 1 << 21;
        public final static int EXG1_16BIT = 1 << 20;
        public final static int EXG2_16BIT = 1 << 19;
        public final static int BMPX80 = 1 << 18;
        public final static int MPL_TEMPERATURE = 1 << 17;
        public final static long MPL_QUAT_6DOF = (long) 1 << 31;
        public final static int MPL_QUAT_9DOF = 1 << 30;
        public final static int MPL_EULER_6DOF = 1 << 29;
        public final static int MPL_EULER_9DOF = 1 << 28;
        public final static int MPL_HEADING = 1 << 27;
        public final static int MPL_PEDOMETER = 1 << 26;
        public final static int MPL_TAP = 1 << 25;
        public final static int MPL_MOTION_ORIENT = 1 << 24;
        public final static long GYRO_MPU_MPL = (long) 1 << 39;
        public final static long ACCEL_MPU_MPL = (long) 1 << 38;
        public final static long MAG_MPU_MPL = (long) 1 << 37;
        public final static long SD_SENSOR_MPL_QUAT_6DOF_RAW = (long) 1 << 36;
    }

    public class BTStream {

        public final static int ACCEL_LN = 0x80;
        public final static int GYRO = 0x40;
        public final static int MAG = 0x20;
        public final static int EXG1_24BIT = 0x10;
        public final static int EXG2_24BIT = 0x08;
        public final static int GSR = 0x04;
        public final static int EXT_EXP_A7 = 0x02;
        public final static int EXT_EXP_A6 = 0x01;
        public final static int BRIDGE_AMP = 0x8000;
        public final static int BATTERY = 0x2000;
        public final static int ACCEL_WR = 0x1000;
        public final static int EXT_EXP_A15 = 0x0800;
        public final static int INT_EXP_A1 = 0x0400;
        public final static int INT_EXP_A12 = 0x0200;
        public final static int INT_EXP_A13 = 0x0100;
        public final static int INT_EXP_A14 = 0x800000;
        public final static int ACCEL_MPU = 0x400000;
        public final static int MAG_MPU = 0x200000;
        public final static int EXG1_16BIT = 0x100000;
        public final static int EXG2_16BIT = 0x080000;
        public final static int BMP180 = 0x40000;
        public final static int MPL_TEMPERATURE = 1 << 22;
        public final static int ACCEL_ALT = 0x400000;
        public final static int MAG_ALT = 0X200000;
    }
}
