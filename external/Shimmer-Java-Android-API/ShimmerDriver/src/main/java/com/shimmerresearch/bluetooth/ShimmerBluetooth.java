package com.shimmerresearch.bluetooth;

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
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.comms.StringListener;
import com.shimmerresearch.comms.radioProtocol.LiteProtocol;
import com.shimmerresearch.comms.radioProtocol.MemReadDetails;
import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet;
import com.shimmerresearch.comms.wiredProtocol.AbstractCommsProtocolWired;
import com.shimmerresearch.comms.wiredProtocol.ShimmerCrc;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.Configuration.CHANNEL_UNITS;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.calibration.CalibDetails.CALIB_READ_SOURCE;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.exgConfig.ExGConfigOptionDetails.EXG_CHIP_INDEX;
import com.shimmerresearch.sensors.AbstractSensor.SENSORS;
import com.shimmerresearch.sensors.SensorGSR;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;
import com.shimmerresearch.sensors.bmpX80.SensorBMPX80;
import com.shimmerresearch.sensors.lsm303.SensorLSM303;
import com.shimmerresearch.sensors.mpu9x50.SensorMPU9X50;
import com.shimmerresearch.sensors.shimmer2.SensorMMA736x;
import com.shimmerresearch.sensors.shimmer2.SensorShimmer2Mag;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE;
import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

import bolts.TaskCompletionSource;

public abstract class ShimmerBluetooth extends ShimmerObject implements Serializable {

    public static final Map<Byte, BtCommandDetails> mBtCommandMapOther;

    public static final Map<Byte, BtCommandDetails> mBtGetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtSetCommandMap;
    public static final Map<Byte, BtCommandDetails> mBtResponseMap;
    public static final int MSG_IDENTIFIER_DATA_PACKET = 2;
    public static final int MSG_IDENTIFIER_DEVICE_PAIRED = 8;
    public static final int MSG_IDENTIFIER_DEVICE_UNPAIRED = 9;
    public static final int MSG_IDENTIFIER_NOTIFICATION_MESSAGE = 1;
    public static final int MSG_IDENTIFIER_PACKET_RECEPTION_RATE_OVERALL = 3;
    public static final int MSG_IDENTIFIER_PACKET_RECEPTION_RATE_CURRENT = 6;
    public static final int MSG_IDENTIFIER_PROGRESS_REPORT_ALL = 5;
    public static final int MSG_IDENTIFIER_PROGRESS_REPORT_PER_DEVICE = 4;
    public static final int MSG_IDENTIFIER_SHIMMER_DOCKED_STATE_CHANGE = 7;
    public static final int MSG_IDENTIFIER_SYNC_PROGRESS = 10;
    public static final int MSG_IDENTIFIER_PROGRESS_BT_PAIR_UNPAIR_ALL = 11;
    public static final int MSG_IDENTIFIER_PROGRESS_BT_PAIR_UNPAIR_PER_DEVICE = 12;
    public static final int MSG_IDENTIFIER_PROGRESS_BT_DISCOVERY_RESULTS = 13;
    public static final int MSG_IDENTIFIER_VERISENSE_ERASE_DATA_COMPLETED = 14;
    public static final int MSG_IDENTIFIER_VERISENSE_WRITE_OPCONFIG_COMPLETED = 15;
    public static final int MSG_IDENTIFIER_SYNC_COMPLETED = 16;
    public static final int MSG_IDENTIFIER_STATE_CHANGE = 0;
    public static final int MSG_IDENTIFIER_DEVICE_ERROR = 10;
    public static final int NOTIFICATION_SHIMMER_FULLY_INITIALIZED = 2;
    public static final int NOTIFICATION_SHIMMER_START_STREAMING = 1;
    public static final int NOTIFICATION_SHIMMER_STATE_CHANGE = 3;
    public static final int NOTIFICATION_SHIMMER_STOP_STREAMING = 0;
    private static final long serialVersionUID = 8439353551730215801L;
    private final static int NUMBER_OF_TX_RETRIES_LIMIT = 0;
    protected static BT_CRC_MODE DEFAULT_BT_CRC_MODE_IF_SUPPORTED = BT_CRC_MODE.ONE_BYTE_CRC;

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();

        aMap.put(DATA_PACKET, new BtCommandDetails(DATA_PACKET, "DATA_PACKET"));
        aMap.put(ROUTINE_COMMUNICATION, new BtCommandDetails(ROUTINE_COMMUNICATION, "ROUTINE_COMMUNICATION"));
        aMap.put(ACK_COMMAND_PROCESSED, new BtCommandDetails(ACK_COMMAND_PROCESSED, "ACK_COMMAND_PROCESSED"));

        mBtCommandMapOther = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();

        aMap.put(INQUIRY_COMMAND, new BtCommandDetails(INQUIRY_COMMAND, "INQUIRY_COMMAND", INQUIRY_RESPONSE));

        aMap.put(GET_SAMPLING_RATE_COMMAND, new BtCommandDetails(GET_SAMPLING_RATE_COMMAND, "GET_SAMPLING_RATE_COMMAND", SAMPLING_RATE_RESPONSE));
        aMap.put(GET_ACCEL_SENSITIVITY_COMMAND, new BtCommandDetails(GET_ACCEL_SENSITIVITY_COMMAND, "GET_ACCEL_SENSITIVITY_COMMAND", ACCEL_SENSITIVITY_RESPONSE));
        aMap.put(GET_CONFIG_BYTE0_COMMAND, new BtCommandDetails(GET_CONFIG_BYTE0_COMMAND, "GET_CONFIG_BYTE0_COMMAND", CONFIG_BYTE0_RESPONSE));
        aMap.put(GET_ACCEL_CALIBRATION_COMMAND, new BtCommandDetails(GET_ACCEL_CALIBRATION_COMMAND, "GET_ACCEL_CALIBRATION_COMMAND", ACCEL_CALIBRATION_RESPONSE));
        aMap.put(GET_GYRO_CALIBRATION_COMMAND, new BtCommandDetails(GET_GYRO_CALIBRATION_COMMAND, "GET_GYRO_CALIBRATION_COMMAND", GYRO_CALIBRATION_RESPONSE));
        aMap.put(GET_MAG_CALIBRATION_COMMAND, new BtCommandDetails(GET_MAG_CALIBRATION_COMMAND, "GET_MAG_CALIBRATION_COMMAND", MAG_CALIBRATION_RESPONSE));
        aMap.put(GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND, new BtCommandDetails(GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND, "GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND", LSM303DLHC_ACCEL_CALIBRATION_RESPONSE));
        aMap.put(GET_GSR_RANGE_COMMAND, new BtCommandDetails(GET_GSR_RANGE_COMMAND, "GET_GSR_RANGE_COMMAND", GSR_RANGE_RESPONSE));
        aMap.put(GET_SHIMMER_VERSION_COMMAND, new BtCommandDetails(GET_SHIMMER_VERSION_COMMAND, "GET_SHIMMER_VERSION_COMMAND", GET_SHIMMER_VERSION_RESPONSE));
        aMap.put(GET_SHIMMER_VERSION_COMMAND_NEW, new BtCommandDetails(GET_SHIMMER_VERSION_COMMAND_NEW, "GET_SHIMMER_VERSION_COMMAND_NEW", GET_SHIMMER_VERSION_RESPONSE));
        aMap.put(GET_EMG_CALIBRATION_COMMAND, new BtCommandDetails(GET_EMG_CALIBRATION_COMMAND, "GET_EMG_CALIBRATION_COMMAND", EMG_CALIBRATION_RESPONSE));
        aMap.put(GET_ECG_CALIBRATION_COMMAND, new BtCommandDetails(GET_ECG_CALIBRATION_COMMAND, "GET_ECG_CALIBRATION_COMMAND", ECG_CALIBRATION_RESPONSE));
        aMap.put(GET_ALL_CALIBRATION_COMMAND, new BtCommandDetails(GET_ALL_CALIBRATION_COMMAND, "GET_ALL_CALIBRATION_COMMAND", ALL_CALIBRATION_RESPONSE));
        aMap.put(GET_FW_VERSION_COMMAND, new BtCommandDetails(GET_FW_VERSION_COMMAND, "GET_FW_VERSION_COMMAND", FW_VERSION_RESPONSE));
        aMap.put(GET_BLINK_LED, new BtCommandDetails(GET_BLINK_LED, "GET_BLINK_LED", BLINK_LED_RESPONSE));
        aMap.put(GET_BUFFER_SIZE_COMMAND, new BtCommandDetails(GET_BUFFER_SIZE_COMMAND, "GET_BUFFER_SIZE_COMMAND", BUFFER_SIZE_RESPONSE));
        aMap.put(GET_MAG_GAIN_COMMAND, new BtCommandDetails(GET_MAG_GAIN_COMMAND, "GET_MAG_GAIN_COMMAND", MAG_GAIN_RESPONSE));
        aMap.put(GET_MAG_SAMPLING_RATE_COMMAND, new BtCommandDetails(GET_MAG_SAMPLING_RATE_COMMAND, "GET_MAG_SAMPLING_RATE_COMMAND", MAG_SAMPLING_RATE_RESPONSE));
        aMap.put(GET_ACCEL_SAMPLING_RATE_COMMAND, new BtCommandDetails(GET_ACCEL_SAMPLING_RATE_COMMAND, "GET_ACCEL_SAMPLING_RATE_COMMAND", ACCEL_SAMPLING_RATE_RESPONSE));
        aMap.put(GET_LSM303DLHC_ACCEL_LPMODE_COMMAND, new BtCommandDetails(GET_LSM303DLHC_ACCEL_LPMODE_COMMAND, "GET_LSM303DLHC_ACCEL_LPMODE_COMMAND", LSM303DLHC_ACCEL_LPMODE_RESPONSE));
        aMap.put(GET_LSM303DLHC_ACCEL_HRMODE_COMMAND, new BtCommandDetails(GET_LSM303DLHC_ACCEL_HRMODE_COMMAND, "GET_LSM303DLHC_ACCEL_HRMODE_COMMAND", LSM303DLHC_ACCEL_HRMODE_RESPONSE));
        aMap.put(GET_MPU9150_GYRO_RANGE_COMMAND, new BtCommandDetails(GET_MPU9150_GYRO_RANGE_COMMAND, "GET_MPU9150_GYRO_RANGE_COMMAND", MPU9150_GYRO_RANGE_RESPONSE));
        aMap.put(GET_MPU9150_SAMPLING_RATE_COMMAND, new BtCommandDetails(GET_MPU9150_SAMPLING_RATE_COMMAND, "GET_MPU9150_SAMPLING_RATE_COMMAND", MPU9150_SAMPLING_RATE_RESPONSE));
        aMap.put(GET_BMP180_PRES_RESOLUTION_COMMAND, new BtCommandDetails(GET_BMP180_PRES_RESOLUTION_COMMAND, "GET_BMP180_PRES_RESOLUTION_COMMAND", BMP180_PRES_RESOLUTION_RESPONSE));
        aMap.put(GET_BMP180_PRES_CALIBRATION_COMMAND, new BtCommandDetails(GET_BMP180_PRES_CALIBRATION_COMMAND, "GET_BMP180_PRES_CALIBRATION_COMMAND", BMP180_PRES_CALIBRATION_RESPONSE));
        aMap.put(GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND, new BtCommandDetails(GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND, "GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND", BMP180_CALIBRATION_COEFFICIENTS_RESPONSE));
        aMap.put(GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, new BtCommandDetails(GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, "GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND", BMP280_CALIBRATION_COEFFICIENTS_RESPONSE));
        aMap.put(GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND, new BtCommandDetails(GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND, "GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND", PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE));
        aMap.put(GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND, new BtCommandDetails(GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND, "GET_MPU9150_MAG_SENS_ADJ_VALS_COMMAND", MPU9150_MAG_SENS_ADJ_VALS_RESPONSE));
        aMap.put(GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, new BtCommandDetails(GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, "GET_INTERNAL_EXP_POWER_ENABLE_COMMAND", INTERNAL_EXP_POWER_ENABLE_RESPONSE));
        aMap.put(GET_EXG_REGS_COMMAND, new BtCommandDetails(GET_EXG_REGS_COMMAND, "GET_EXG_REGS_COMMAND", EXG_REGS_RESPONSE));
        aMap.put(GET_DAUGHTER_CARD_ID_COMMAND, new BtCommandDetails(GET_DAUGHTER_CARD_ID_COMMAND, "GET_DAUGHTER_CARD_ID_COMMAND", DAUGHTER_CARD_ID_RESPONSE));
        aMap.put(GET_BAUD_RATE_COMMAND, new BtCommandDetails(GET_BAUD_RATE_COMMAND, "GET_BAUD_RATE_COMMAND", BAUD_RATE_RESPONSE));
        aMap.put(GET_DERIVED_CHANNEL_BYTES, new BtCommandDetails(GET_DERIVED_CHANNEL_BYTES, "GET_DERIVED_CHANNEL_BYTES", DERIVED_CHANNEL_BYTES_RESPONSE));
        aMap.put(GET_STATUS_COMMAND, new BtCommandDetails(GET_STATUS_COMMAND, "GET_STATUS_COMMAND", STATUS_RESPONSE));
        aMap.put(GET_TRIAL_CONFIG_COMMAND, new BtCommandDetails(GET_TRIAL_CONFIG_COMMAND, "GET_TRIAL_CONFIG_COMMAND", TRIAL_CONFIG_RESPONSE));
        aMap.put(GET_CENTER_COMMAND, new BtCommandDetails(GET_CENTER_COMMAND, "GET_CENTER_COMMAND", CENTER_RESPONSE));
        aMap.put(GET_SHIMMERNAME_COMMAND, new BtCommandDetails(GET_SHIMMERNAME_COMMAND, "GET_SHIMMERNAME_COMMAND", SHIMMERNAME_RESPONSE));
        aMap.put(GET_EXPID_COMMAND, new BtCommandDetails(GET_EXPID_COMMAND, "GET_EXPID_COMMAND", EXPID_RESPONSE));
        aMap.put(GET_MYID_COMMAND, new BtCommandDetails(GET_MYID_COMMAND, "GET_MYID_COMMAND", MYID_RESPONSE));
        aMap.put(GET_NSHIMMER_COMMAND, new BtCommandDetails(GET_NSHIMMER_COMMAND, "GET_NSHIMMER_COMMAND", NSHIMMER_RESPONSE));
        aMap.put(GET_CONFIGTIME_COMMAND, new BtCommandDetails(GET_CONFIGTIME_COMMAND, "GET_CONFIGTIME_COMMAND", CONFIGTIME_RESPONSE));
        aMap.put(GET_DIR_COMMAND, new BtCommandDetails(GET_DIR_COMMAND, "GET_DIR_COMMAND", DIR_RESPONSE));
        aMap.put(GET_INFOMEM_COMMAND, new BtCommandDetails(GET_INFOMEM_COMMAND, "GET_INFOMEM_COMMAND", INFOMEM_RESPONSE));
        aMap.put(GET_CALIB_DUMP_COMMAND, new BtCommandDetails(GET_CALIB_DUMP_COMMAND, "GET_CALIB_DUMP_COMMAND", RSP_CALIB_DUMP_COMMAND));
        aMap.put(GET_RWC_COMMAND, new BtCommandDetails(GET_RWC_COMMAND, "GET_RWC_COMMAND", RWC_RESPONSE));
        aMap.put(GET_VBATT_COMMAND, new BtCommandDetails(GET_VBATT_COMMAND, "GET_VBATT_COMMAND", VBATT_RESPONSE));
        aMap.put(GET_BT_FW_VERSION_STR_COMMAND, new BtCommandDetails(GET_BT_FW_VERSION_STR_COMMAND, "GET_BT_FW_VERSION_STR_COMMAND", BT_FW_VERSION_STR_RESPONSE));

        mBtGetCommandMap = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();

        aMap.put(STOP_STREAMING_COMMAND, new BtCommandDetails(STOP_STREAMING_COMMAND, "STOP_STREAMING_COMMAND")); //doesn't receive ack

        aMap.put(RESET_TO_DEFAULT_CONFIGURATION_COMMAND, new BtCommandDetails(RESET_TO_DEFAULT_CONFIGURATION_COMMAND, "RESET_TO_DEFAULT_CONFIGURATION_COMMAND"));
        aMap.put(RESET_CALIBRATION_VALUE_COMMAND, new BtCommandDetails(RESET_CALIBRATION_VALUE_COMMAND, "RESET_CALIBRATION_VALUE_COMMAND"));

        aMap.put(TEST_CONNECTION_COMMAND, new BtCommandDetails(TEST_CONNECTION_COMMAND, "TEST_CONNECTION_COMMAND"));

        aMap.put(TOGGLE_LED_COMMAND, new BtCommandDetails(TOGGLE_LED_COMMAND, "TOGGLE_LED_COMMAND"));
        aMap.put(START_STREAMING_COMMAND, new BtCommandDetails(START_STREAMING_COMMAND, "START_STREAMING_COMMAND"));
        aMap.put(START_SDBT_COMMAND, new BtCommandDetails(START_SDBT_COMMAND, "START_SDBT_COMMAND"));
        aMap.put(STOP_SDBT_COMMAND, new BtCommandDetails(STOP_SDBT_COMMAND, "STOP_SDBT_COMMAND"));
        aMap.put(START_LOGGING_ONLY_COMMAND, new BtCommandDetails(START_LOGGING_ONLY_COMMAND, "START_LOGGING_ONLY_COMMAND"));
        aMap.put(STOP_LOGGING_ONLY_COMMAND, new BtCommandDetails(STOP_LOGGING_ONLY_COMMAND, "STOP_LOGGING_ONLY_COMMAND"));
        aMap.put(SET_SAMPLING_RATE_COMMAND, new BtCommandDetails(SET_SAMPLING_RATE_COMMAND, "SET_SAMPLING_RATE_COMMAND"));
        aMap.put(SET_SENSORS_COMMAND, new BtCommandDetails(SET_SENSORS_COMMAND, "SET_SENSORS_COMMAND"));
        aMap.put(SET_ACCEL_SENSITIVITY_COMMAND, new BtCommandDetails(SET_ACCEL_SENSITIVITY_COMMAND, "SET_ACCEL_SENSITIVITY_COMMAND"));
        aMap.put(SET_5V_REGULATOR_COMMAND, new BtCommandDetails(SET_5V_REGULATOR_COMMAND, "SET_5V_REGULATOR_COMMAND"));
        aMap.put(SET_PMUX_COMMAND, new BtCommandDetails(SET_PMUX_COMMAND, "SET_PMUX_COMMAND"));
        aMap.put(SET_CONFIG_BYTE0_COMMAND, new BtCommandDetails(SET_CONFIG_BYTE0_COMMAND, "SET_CONFIG_BYTE0_COMMAND"));
        aMap.put(SET_ACCEL_CALIBRATION_COMMAND, new BtCommandDetails(SET_ACCEL_CALIBRATION_COMMAND, "SET_ACCEL_CALIBRATION_COMMAND"));
        aMap.put(SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND, new BtCommandDetails(SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND, "SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND"));
        aMap.put(SET_GYRO_CALIBRATION_COMMAND, new BtCommandDetails(SET_GYRO_CALIBRATION_COMMAND, "SET_GYRO_CALIBRATION_COMMAND"));
        aMap.put(SET_MAG_CALIBRATION_COMMAND, new BtCommandDetails(SET_MAG_CALIBRATION_COMMAND, "SET_MAG_CALIBRATION_COMMAND"));
        aMap.put(SET_GSR_RANGE_COMMAND, new BtCommandDetails(SET_GSR_RANGE_COMMAND, "SET_GSR_RANGE_COMMAND"));
        aMap.put(SET_EMG_CALIBRATION_COMMAND, new BtCommandDetails(SET_EMG_CALIBRATION_COMMAND, "SET_EMG_CALIBRATION_COMMAND"));
        aMap.put(SET_ECG_CALIBRATION_COMMAND, new BtCommandDetails(SET_ECG_CALIBRATION_COMMAND, "SET_ECG_CALIBRATION_COMMAND"));
        aMap.put(SET_BLINK_LED, new BtCommandDetails(SET_BLINK_LED, "SET_BLINK_LED"));
        aMap.put(SET_GYRO_TEMP_VREF_COMMAND, new BtCommandDetails(SET_GYRO_TEMP_VREF_COMMAND, "SET_GYRO_TEMP_VREF_COMMAND"));
        aMap.put(SET_BUFFER_SIZE_COMMAND, new BtCommandDetails(SET_BUFFER_SIZE_COMMAND, "SET_BUFFER_SIZE_COMMAND"));
        aMap.put(SET_MAG_GAIN_COMMAND, new BtCommandDetails(SET_MAG_GAIN_COMMAND, "SET_MAG_GAIN_COMMAND"));
        aMap.put(SET_MAG_SAMPLING_RATE_COMMAND, new BtCommandDetails(SET_MAG_SAMPLING_RATE_COMMAND, "SET_MAG_SAMPLING_RATE_COMMAND"));
        aMap.put(SET_ACCEL_SAMPLING_RATE_COMMAND, new BtCommandDetails(SET_ACCEL_SAMPLING_RATE_COMMAND, "SET_ACCEL_SAMPLING_RATE_COMMAND"));
        aMap.put(SET_LSM303DLHC_ACCEL_LPMODE_COMMAND, new BtCommandDetails(SET_LSM303DLHC_ACCEL_LPMODE_COMMAND, "SET_LSM303DLHC_ACCEL_LPMODE_COMMAND"));
        aMap.put(SET_LSM303DLHC_ACCEL_HRMODE_COMMAND, new BtCommandDetails(SET_LSM303DLHC_ACCEL_HRMODE_COMMAND, "SET_LSM303DLHC_ACCEL_HRMODE_COMMAND"));
        aMap.put(SET_MPU9150_GYRO_RANGE_COMMAND, new BtCommandDetails(SET_MPU9150_GYRO_RANGE_COMMAND, "SET_MPU9150_GYRO_RANGE_COMMAND"));
        aMap.put(SET_MPU9150_SAMPLING_RATE_COMMAND, new BtCommandDetails(SET_MPU9150_SAMPLING_RATE_COMMAND, "SET_MPU9150_SAMPLING_RATE_COMMAND"));
        aMap.put(SET_BMP180_PRES_RESOLUTION_COMMAND, new BtCommandDetails(SET_BMP180_PRES_RESOLUTION_COMMAND, "SET_BMP180_PRES_RESOLUTION_COMMAND"));
        aMap.put(SET_BMP180_PRES_CALIBRATION_COMMAND, new BtCommandDetails(SET_BMP180_PRES_CALIBRATION_COMMAND, "SET_BMP180_PRES_CALIBRATION_COMMAND"));
        aMap.put(SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, new BtCommandDetails(SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, "SET_INTERNAL_EXP_POWER_ENABLE_COMMAND"));
        aMap.put(SET_EXG_REGS_COMMAND, new BtCommandDetails(SET_EXG_REGS_COMMAND, "SET_EXG_REGS_COMMAND"));
        aMap.put(SET_BAUD_RATE_COMMAND, new BtCommandDetails(SET_BAUD_RATE_COMMAND, "SET_BAUD_RATE_COMMAND"));
        aMap.put(SET_DERIVED_CHANNEL_BYTES, new BtCommandDetails(SET_DERIVED_CHANNEL_BYTES, "SET_DERIVED_CHANNEL_BYTES"));
        aMap.put(SET_TRIAL_CONFIG_COMMAND, new BtCommandDetails(SET_TRIAL_CONFIG_COMMAND, "SET_TRIAL_CONFIG_COMMAND"));
        aMap.put(SET_CENTER_COMMAND, new BtCommandDetails(SET_CENTER_COMMAND, "SET_CENTER_COMMAND"));
        aMap.put(SET_SHIMMERNAME_COMMAND, new BtCommandDetails(SET_SHIMMERNAME_COMMAND, "SET_SHIMMERNAME_COMMAND"));
        aMap.put(SET_EXPID_COMMAND, new BtCommandDetails(SET_EXPID_COMMAND, "SET_EXPID_COMMAND"));
        aMap.put(SET_MYID_COMMAND, new BtCommandDetails(SET_MYID_COMMAND, "SET_MYID_COMMAND"));
        aMap.put(SET_NSHIMMER_COMMAND, new BtCommandDetails(SET_NSHIMMER_COMMAND, "SET_NSHIMMER_COMMAND"));
        aMap.put(SET_CONFIGTIME_COMMAND, new BtCommandDetails(SET_CONFIGTIME_COMMAND, "SET_CONFIGTIME_COMMAND"));
        aMap.put(SET_INFOMEM_COMMAND, new BtCommandDetails(SET_INFOMEM_COMMAND, "SET_INFOMEM_COMMAND"));
        aMap.put(SET_CALIB_DUMP_COMMAND, new BtCommandDetails(SET_CALIB_DUMP_COMMAND, "SET_CALIB_DUMP_COMMAND"));
        aMap.put(UPD_CALIB_DUMP_COMMAND, new BtCommandDetails(UPD_CALIB_DUMP_COMMAND, "UPD_CALIB_DUMP_COMMAND"));
        aMap.put(UPD_SDLOG_CFG_COMMAND, new BtCommandDetails(UPD_SDLOG_CFG_COMMAND, "UPD_SDLOG_CFG_COMMAND"));
        aMap.put(SET_CRC_COMMAND, new BtCommandDetails(SET_CRC_COMMAND, "SET_CRC_COMMAND"));
        aMap.put(SET_RWC_COMMAND, new BtCommandDetails(SET_RWC_COMMAND, "SET_RWC_COMMAND"));
        aMap.put(SET_TEST, new BtCommandDetails(SET_TEST, "SET_TEST_COMMAND"));
        mBtSetCommandMap = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<Byte, BtCommandDetails> aMap = new LinkedHashMap<Byte, BtCommandDetails>();

        aMap.put(INQUIRY_RESPONSE, new BtCommandDetails(INQUIRY_RESPONSE, "INQUIRY_RESPONSE", -1));
        aMap.put(GET_SHIMMER_VERSION_RESPONSE, new BtCommandDetails(GET_SHIMMER_VERSION_RESPONSE, "GET_SHIMMER_VERSION_RESPONSE", 1));
        aMap.put(SAMPLING_RATE_RESPONSE, new BtCommandDetails(SAMPLING_RATE_RESPONSE, "SAMPLING_RATE_RESPONSE", -1));
        aMap.put(ACCEL_SENSITIVITY_RESPONSE, new BtCommandDetails(ACCEL_SENSITIVITY_RESPONSE, "ACCEL_SENSITIVITY_RESPONSE", 1));
        aMap.put(CONFIG_BYTE0_RESPONSE, new BtCommandDetails(CONFIG_BYTE0_RESPONSE, "CONFIG_BYTE0_RESPONSE", -1));
        aMap.put(ACCEL_CALIBRATION_RESPONSE, new BtCommandDetails(ACCEL_CALIBRATION_RESPONSE, "ACCEL_CALIBRATION_RESPONSE", 21));
        aMap.put(LSM303DLHC_ACCEL_CALIBRATION_RESPONSE, new BtCommandDetails(LSM303DLHC_ACCEL_CALIBRATION_RESPONSE, "LSM303DLHC_ACCEL_CALIBRATION_RESPONSE", 21));
        aMap.put(GYRO_CALIBRATION_RESPONSE, new BtCommandDetails(GYRO_CALIBRATION_RESPONSE, "GYRO_CALIBRATION_RESPONSE", 21));
        aMap.put(MAG_CALIBRATION_RESPONSE, new BtCommandDetails(MAG_CALIBRATION_RESPONSE, "MAG_CALIBRATION_RESPONSE", 21));
        aMap.put(GSR_RANGE_RESPONSE, new BtCommandDetails(GSR_RANGE_RESPONSE, "GSR_RANGE_RESPONSE", 1));
        aMap.put(EMG_CALIBRATION_RESPONSE, new BtCommandDetails(EMG_CALIBRATION_RESPONSE, "EMG_CALIBRATION_RESPONSE", 4));
        aMap.put(ECG_CALIBRATION_RESPONSE, new BtCommandDetails(ECG_CALIBRATION_RESPONSE, "ECG_CALIBRATION_RESPONSE", 8));
        aMap.put(ALL_CALIBRATION_RESPONSE, new BtCommandDetails(ALL_CALIBRATION_RESPONSE, "ALL_CALIBRATION_RESPONSE", -1));
        aMap.put(FW_VERSION_RESPONSE, new BtCommandDetails(FW_VERSION_RESPONSE, "FW_VERSION_RESPONSE", 6));
        aMap.put(BLINK_LED_RESPONSE, new BtCommandDetails(BLINK_LED_RESPONSE, "BLINK_LED_RESPONSE", 1));
        aMap.put(BUFFER_SIZE_RESPONSE, new BtCommandDetails(BUFFER_SIZE_RESPONSE, "BUFFER_SIZE_RESPONSE", 1));
        aMap.put(MAG_GAIN_RESPONSE, new BtCommandDetails(MAG_GAIN_RESPONSE, "MAG_GAIN_RESPONSE", 1));
        aMap.put(MAG_SAMPLING_RATE_RESPONSE, new BtCommandDetails(MAG_SAMPLING_RATE_RESPONSE, "MAG_SAMPLING_RATE_RESPONSE", 1));
        aMap.put(ACCEL_SAMPLING_RATE_RESPONSE, new BtCommandDetails(ACCEL_SAMPLING_RATE_RESPONSE, "ACCEL_SAMPLING_RATE_RESPONSE", 1));
        aMap.put(LSM303DLHC_ACCEL_LPMODE_RESPONSE, new BtCommandDetails(LSM303DLHC_ACCEL_LPMODE_RESPONSE, "LSM303DLHC_ACCEL_LPMODE_RESPONSE", 1));
        aMap.put(LSM303DLHC_ACCEL_HRMODE_RESPONSE, new BtCommandDetails(LSM303DLHC_ACCEL_HRMODE_RESPONSE, "LSM303DLHC_ACCEL_HRMODE_RESPONSE", 1));
        aMap.put(MPU9150_GYRO_RANGE_RESPONSE, new BtCommandDetails(MPU9150_GYRO_RANGE_RESPONSE, "MPU9150_GYRO_RANGE_RESPONSE", 1));
        aMap.put(MPU9150_SAMPLING_RATE_RESPONSE, new BtCommandDetails(MPU9150_SAMPLING_RATE_RESPONSE, "MPU9150_SAMPLING_RATE_RESPONSE", 1));
        aMap.put(BMP180_PRES_RESOLUTION_RESPONSE, new BtCommandDetails(BMP180_PRES_RESOLUTION_RESPONSE, "BMP180_PRES_RESOLUTION_RESPONSE", 1));
        aMap.put(BMP180_PRES_CALIBRATION_RESPONSE, new BtCommandDetails(BMP180_PRES_CALIBRATION_RESPONSE, "BMP180_PRES_CALIBRATION_RESPONSE", -1));
        aMap.put(BMP180_CALIBRATION_COEFFICIENTS_RESPONSE, new BtCommandDetails(BMP180_CALIBRATION_COEFFICIENTS_RESPONSE, "BMP180_CALIBRATION_COEFFICIENTS_RESPONSE", 22));
        aMap.put(BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, new BtCommandDetails(BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, "BMP280_CALIBRATION_COEFFICIENTS_RESPONSE", 24));
        aMap.put(PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE, new BtCommandDetails(PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE, "PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE", 21));
        aMap.put(MPU9150_MAG_SENS_ADJ_VALS_RESPONSE, new BtCommandDetails(MPU9150_MAG_SENS_ADJ_VALS_RESPONSE, "MPU9150_MAG_SENS_ADJ_VALS_RESPONSE", -1));
        aMap.put(INTERNAL_EXP_POWER_ENABLE_RESPONSE, new BtCommandDetails(INTERNAL_EXP_POWER_ENABLE_RESPONSE, "INTERNAL_EXP_POWER_ENABLE_RESPONSE", 1));
        aMap.put(EXG_REGS_RESPONSE, new BtCommandDetails(EXG_REGS_RESPONSE, "EXG_REGS_RESPONSE", 11));
        aMap.put(DAUGHTER_CARD_ID_RESPONSE, new BtCommandDetails(DAUGHTER_CARD_ID_RESPONSE, "DAUGHTER_CARD_ID_RESPONSE", 3 + 1));
        aMap.put(BAUD_RATE_RESPONSE, new BtCommandDetails(BAUD_RATE_RESPONSE, "BAUD_RATE_RESPONSE", 1));
        aMap.put(DERIVED_CHANNEL_BYTES_RESPONSE, new BtCommandDetails(DERIVED_CHANNEL_BYTES_RESPONSE, "DERIVED_CHANNEL_BYTES_RESPONSE", 3));
        aMap.put(STATUS_RESPONSE, new BtCommandDetails(STATUS_RESPONSE, "STATUS_RESPONSE", 1));
        aMap.put(TRIAL_CONFIG_RESPONSE, new BtCommandDetails(TRIAL_CONFIG_RESPONSE, "TRIAL_CONFIG_RESPONSE", 3));
        aMap.put(CENTER_RESPONSE, new BtCommandDetails(CENTER_RESPONSE, "CENTER_RESPONSE", 1));
        aMap.put(SHIMMERNAME_RESPONSE, new BtCommandDetails(SHIMMERNAME_RESPONSE, "SHIMMERNAME_RESPONSE", 1));                            // first byte indicates length to subsequently read //TODO RS: '1' be '-1' or 1+-1?
        aMap.put(EXPID_RESPONSE, new BtCommandDetails(EXPID_RESPONSE, "EXPID_RESPONSE", 1));                                        // first byte indicates length to subsequently read //TODO RS: '1' be '-1' or 1+-1?
        aMap.put(MYID_RESPONSE, new BtCommandDetails(MYID_RESPONSE, "MYID_RESPONSE", -1));
        aMap.put(NSHIMMER_RESPONSE, new BtCommandDetails(NSHIMMER_RESPONSE, "NSHIMMER_RESPONSE", -1));
        aMap.put(CONFIGTIME_RESPONSE, new BtCommandDetails(CONFIGTIME_RESPONSE, "CONFIGTIME_RESPONSE", 1));
        aMap.put(DIR_RESPONSE, new BtCommandDetails(DIR_RESPONSE, "DIR_RESPONSE", 1));
        aMap.put(INSTREAM_CMD_RESPONSE, new BtCommandDetails(INSTREAM_CMD_RESPONSE, "INSTREAM_CMD_RESPONSE", 1));
        aMap.put(INFOMEM_RESPONSE, new BtCommandDetails(INFOMEM_RESPONSE, "INFOMEM_RESPONSE", -1));
        aMap.put(RSP_CALIB_DUMP_COMMAND, new BtCommandDetails(RSP_CALIB_DUMP_COMMAND, "RSP_CALIB_DUMP_COMMAND", -1));
        aMap.put(RWC_RESPONSE, new BtCommandDetails(RWC_RESPONSE, "RWC_RESPONSE", 8));
        aMap.put(VBATT_RESPONSE, new BtCommandDetails(VBATT_RESPONSE, "VBATT_RESPONSE", 3));
        aMap.put(BT_FW_VERSION_STR_RESPONSE, new BtCommandDetails(BT_FW_VERSION_STR_RESPONSE, "BT_FW_VERSION_STR_RESPONSE", -1));

        mBtResponseMap = Collections.unmodifiableMap(aMap);
    }

    private final int ACK_TIMER_DURATION = 2;                                    // Duration to wait for an ack packet (seconds)
    public List<Long> mListofPCTimeStamps = new ArrayList<Long>();
    public boolean mIsRedLedOn = false;
    public boolean mIsRtcSet = false;
    protected long mSetEnabledSensors = SENSOR_ACCEL;                                // Only used during the initialization process, see initialize();
    protected boolean mSendProgressReport = true;
    protected boolean mOperationUnderway = false;
    protected byte mCurrentCommand;
    protected boolean mWaitForAck = false;
    protected boolean mWaitForResponse = false;
    protected boolean mTransactionCompleted = true;                                    // Variable is used to ensure a command has finished execution prior to executing the next command (see initialize())
    transient protected IOThread mIOThread;
    transient protected ProcessingThread mPThread;
    protected boolean mSetupDeviceWhileConnecting = false;
    protected Stack<Byte> byteStack = new Stack<Byte>();
    protected double mLowBattLimit = 3.4;
    protected int numBytesToReadFromExpBoard = 0;
    protected boolean mIamAlive = false;
    protected boolean mDummyRead = false, mDummyReadStarted = false;
    @Deprecated
    protected boolean mFirstTime = true;
    protected int mTempIntValue;                                                // A temporary variable used to store Integer value, used mainly to store a value while waiting for an acknowledge packet (e.g. when writeGRange() is called, the range is stored temporarily and used to update GSRRange when the acknowledge packet is received.
    protected long tempEnabledSensors;
    protected HashMap<Byte, MemReadDetails> mMapOfMemReadDetails = new HashMap<Byte, MemReadDetails>();
    protected boolean mSync = true;
    protected boolean mSetupEXG = false;
    transient protected Timer mTimerCheckForAckOrResp;
    transient protected Timer mTimerCheckAlive;
    transient protected Timer mTimerReadStatus;
    transient protected Timer mTimerReadBattStatus;
    transient protected Timer mTimerConnecting;
    transient protected Timer mTimerCheckSerialPortClear;
    protected transient ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
    protected boolean mUseProcessingThread = false;
    protected boolean mEnablePCTimeStamps = true;
    protected BT_CRC_MODE mBtCommsCrcModeCurrent = BT_CRC_MODE.OFF;
    protected boolean mSerialPortReadTimeout;
    transient protected boolean InShimmerTest = false;

    ArrayBlockingQueue<RawBytePacketWithPCTimeStamp> mABQPacketByeArray = new ArrayBlockingQueue<RawBytePacketWithPCTimeStamp>(10000);
    transient StringListener mStringTestListener;
    transient ByteCommunicationListener mTestByteListener;
    private int mNumberofTXRetriesCount = 1;
    private boolean mWriteCalibrationDumpWhenConfiguringForClone = true;
    private boolean mInstructionStackLock = false;
    @Deprecated
    // mContinousSync doesn't do anything
    private boolean mContinousSync = false;
    private boolean mUseLegacyDelayToDelayForResponse = false;
    private SystemTimestampPlot systemTimestampPlot = new SystemTimestampPlot();
    private List<byte[]> mListofInstructions = new ArrayList<byte[]>();
    private byte mTempByteValue;
    private int mTempChipID;
    private byte[] mMemBuffer;
    private byte[] cmdcalibrationParameters = new byte[22];
    private int mCountDeadConnection = 0;

    private boolean mCheckIfConnectionisAlive = false;
    private int mNumOfMemSetCmds = 0;

    public ShimmerBluetooth() {
        addCommunicationRoute(COMMUNICATION_TYPE.BLUETOOTH);
    }

    public ShimmerBluetooth(String userAssignedName, double samplingRate, Integer[] sensorIdsToEnable, int accelRange, int gsrRange, int gyroRange, int magRange, int pressureResolution) {
        this(userAssignedName, samplingRate, sensorIdsToEnable, accelRange, gsrRange, magRange, pressureResolution);

        addFixedShimmerConfig(SensorMPU9X50.GuiLabelConfig.MPU9X50_GYRO_RANGE, gyroRange);
    }

    public ShimmerBluetooth(String userAssignedName, double samplingRate, Integer[] sensorIdsToEnable, int accelRange, int gsrRange, int magRange, int pressureResolution) {
        this();

        setShimmerUserAssignedName(userAssignedName);

        setFixedShimmerConfig(FIXED_SHIMMER_CONFIG_MODE.USER);
        if (sensorIdsToEnable != null) {
            addFixedShimmerConfig(Shimmer3.GuiLabelConfig.ENABLED_SENSORS_IDS, sensorIdsToEnable);
        }
        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE, samplingRate);
        addFixedShimmerConfig(SensorLSM303.GuiLabelConfig.LSM303_ACCEL_RANGE, accelRange);
        addFixedShimmerConfig(SensorGSR.GuiLabelConfig.GSR_RANGE, gsrRange);
        addFixedShimmerConfig(SensorLSM303.GuiLabelConfig.LSM303_MAG_RANGE, magRange);
        addFixedShimmerConfig(SensorBMPX80.GuiLabelConfig.PRESSURE_RESOLUTION, pressureResolution);
        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.SHIMMER_USER_ASSIGNED_NAME, userAssignedName);
    }

    public ShimmerBluetooth(String userAssignedName, double samplingRate, int enabledSensors, int accelRange, int gsrRange, int magRange) {
        this();

        setShimmerUserAssignedName(userAssignedName);
        setFixedShimmerConfig(FIXED_SHIMMER_CONFIG_MODE.USER);
        addFixedShimmerConfig(Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE, samplingRate);
        addFixedShimmerConfig(SensorGSR.GuiLabelConfig.GSR_RANGE, gsrRange);
        addFixedShimmerConfig(SensorMMA736x.GuiLabelConfig.ACCEL_RANGE, accelRange);
        addFixedShimmerConfig(SensorShimmer2Mag.GuiLabelConfig.MAG_RANGE, magRange);
        mSetEnabledSensors = enabledSensors;
        setSetupDeviceWhileConnecting(true);
        setSamplingRateShimmer(samplingRate);
    }

    private static boolean isKnownResponse(byte response) {
        return mBtResponseMap.containsKey(response);
    }

    private static boolean isKnownGetCommand(byte getCmd) {
        return mBtGetCommandMap.containsKey(getCmd);
    }

    protected static boolean isKnownSetCommand(byte setCmd) {
        return mBtSetCommandMap.containsKey(setCmd);
    }

    public static String btCommandToString(byte command) {

        Map<Byte, BtCommandDetails> mapToSearch = null;

        if (mBtCommandMapOther.containsKey(command)) {
            mapToSearch = mBtCommandMapOther;
        } else if (isKnownSetCommand(command)) {
            mapToSearch = mBtSetCommandMap;
        } else if (isKnownGetCommand(command)) {
            mapToSearch = mBtGetCommandMap;
        } else if (isKnownResponse(command)) {
            mapToSearch = mBtResponseMap;
        }

        if (mapToSearch != null)
            return UtilShimmer.byteToHexStringFormatted(command) + " " + mapToSearch.get(command).mDescription;
        else {
            return UtilShimmer.byteToHexStringFormatted(command) + "UNKNOWN";
        }
    }

    public static void setDefaultBtCrcModeToUseIfFwSupported(BT_CRC_MODE btCommsCrcMode) {
        DEFAULT_BT_CRC_MODE_IF_SUPPORTED = btCommsCrcMode;
    }

    public static BT_CRC_MODE getDefaultBtCrcModeIfFwSupported() {
        return DEFAULT_BT_CRC_MODE_IF_SUPPORTED;
    }

    protected abstract void connect(String address, String bluetoothLibrary);

    protected abstract boolean bytesAvailableToBeRead();

    protected abstract int availableBytes();

    protected abstract void writeBytes(byte[] data);

    protected abstract void stop();

    protected abstract void sendProgressReport(BluetoothProgressReportPerCmd pr);


    protected abstract void isReadyForStreaming();

    protected abstract void isNowStreaming();

    protected abstract void hasStopStreaming();

    protected abstract void sendStatusMsgPacketLossDetected();

    protected abstract void inquiryDone();

    protected abstract void sendStatusMSGtoUI(String msg);

    protected abstract void printLogDataForDebugging(String msg);

    protected abstract void connectionLost();

    protected abstract void startOperation(BT_STATE currentOperation);

    protected abstract void finishOperation(BT_STATE currentOperation);

    protected abstract void startOperation(BT_STATE currentOperation, int totalNumOfCmds);


    protected abstract void eventLogAndStreamStatusChanged(byte currentCommand);

    protected abstract void batteryStatusChanged();

    protected abstract byte[] readBytes(int numberofBytes);

    protected abstract byte readByte();

    protected abstract void dockedStateChange();

    protected void processPacket() {
        setIamAlive(true);
        byte[] bufferTemp = mByteArrayOutputStream.toByteArray();

        if (bufferTemp[0] == DATA_PACKET
                && bufferTemp[getPacketSizeWithCrc() + 1] == DATA_PACKET) {

            if (mBtCommsCrcModeCurrent != BT_CRC_MODE.OFF && !checkCrc(bufferTemp, getPacketSize() + 1)) {
                discardFirstBufferByte();
                return;
            }

            processDataPacket(bufferTemp);
            clearSingleDataPacketFromBuffers(bufferTemp, getPacketSizeWithCrc() + 1);
        } else if (bufferTemp[0] == DATA_PACKET
                && bufferTemp[getPacketSizeWithCrc() + 1] == ACK_COMMAND_PROCESSED) {
            if (mByteArrayOutputStream.size() > getPacketSizeWithCrc() + 2) {

                if (bufferTemp[getPacketSizeWithCrc() + 2] == DATA_PACKET) {
                    processDataPacket(bufferTemp);
                    clearSingleDataPacketFromBuffers(bufferTemp, getPacketSizeWithCrc() + 2);

                    if (isKnownSetCommand(mCurrentCommand)) {
                        stopTimerCheckForAckOrResp();
                        mWaitForAck = false;

                        processAckFromSetCommand(mCurrentCommand);

                        mTransactionCompleted = true;
                        setInstructionStackLock(false);
                    }
                    printLogDataForDebugging("Ack Received for Command: \t\t\t" + btCommandToString(mCurrentCommand));
                } else if (isSupportedInStreamCmds() && bufferTemp[getPacketSizeWithCrc() + 2] == INSTREAM_CMD_RESPONSE) {
                    printLogDataForDebugging("COMMAND TXed and ACK RECEIVED IN STREAM");
                    printLogDataForDebugging("INS CMD RESP");

                    stopTimerCheckForAckOrResp();
                    mWaitForResponse = false;
                    mWaitForAck = false;

                    processInstreamResponse(true);

                    if (getListofInstructions().size() > 0) {
                        removeInstruction(0);
                    }

                    mTransactionCompleted = true;
                    setInstructionStackLock(false);

                    processDataPacket(bufferTemp);
                    clearBuffers();
                } else {
                    printLogDataForDebugging("Unknown parsing error while streaming");
                }
            }
            if (mByteArrayOutputStream.size() > getPacketSizeWithCrc() + 2) {
                printLogDataForDebugging("Unknown packet error (check with JC):\tExpected: " + (getPacketSizeWithCrc() + 2) + "bytes but buffer contains " + mByteArrayOutputStream.size() + "bytes");
                discardFirstBufferByte();
            }

        } else {
            printLogDataForDebugging("Packet syncing problem:\tExpected: " + (getPacketSizeWithCrc() + 2) + "bytes. Buffer contains " + mByteArrayOutputStream.size() + "bytes"
                    + "\nBuffer = " + UtilShimmer.bytesToHexStringWithSpacesFormatted(mByteArrayOutputStream.toByteArray()));
            discardFirstBufferByte();
        }
    }

    protected void processWhileStreaming() {
        byte[] byteBuffer = readBytes(1);
        if (byteBuffer != null) {
            mByteArrayOutputStream.write(byteBuffer[0]);
            if (mEnablePCTimeStamps) {
                mListofPCTimeStamps.add(System.currentTimeMillis());
            }
        } else {
            printLogDataForDebugging("readbyte null");
        }

        if (mByteArrayOutputStream.size() >= getPacketSizeWithCrc() + 2) {
            processPacket();
        }
    }

    public boolean checkCrc(byte[] bufferTemp, int length) {
        byte[] crcCalc = ShimmerCrc.shimmerUartCrcCalc(bufferTemp, length);

        if (mBtCommsCrcModeCurrent == BT_CRC_MODE.ONE_BYTE_CRC || mBtCommsCrcModeCurrent == BT_CRC_MODE.TWO_BYTE_CRC) {
            if (bufferTemp[getPacketSize() + 1] != crcCalc[0]) {
                return false;
            }
        }

        if (mBtCommsCrcModeCurrent == BT_CRC_MODE.TWO_BYTE_CRC) {
            if (bufferTemp[getPacketSize() + 2] != crcCalc[1]) {
                discardFirstBufferByte();
                return false;
            }
        }
        return true;
    }

    private void clearCrcBytesFromBuffer(byte responseCommand) {
        byte[] crcBytes = readBytes(mBtCommsCrcModeCurrent.numCrcBytes);
        consolePrintLn("Clearing CRC bytes:\t\t\t" + btCommandToString(responseCommand) + " " + UtilShimmer.bytesToHexStringWithSpacesFormatted(crcBytes));
    }

    private void processSpecialGetCmdsAfterAck(byte currentCommand) {
        byte[] insBytes = getListofInstructions().get(0);

        if (currentCommand == GET_EXG_REGS_COMMAND) {
            mTempChipID = insBytes[1];
        } else if (currentCommand == GET_INFOMEM_COMMAND || currentCommand == GET_CALIB_DUMP_COMMAND) {
            MemReadDetails memReadDetails = mMapOfMemReadDetails.get(currentCommand);
            if (memReadDetails != null) {
                memReadDetails.mCurrentMemAddress = ((insBytes[3] & 0xFF) << 8) + (insBytes[2] & 0xFF);
                memReadDetails.mCurrentMemLengthToRead = (insBytes[1] & 0xFF);
            }
        }
    }

    protected void processInstreamResponse(boolean shouldClearCrcFromBuffer) {
        boolean responseWasParsed = true;

        byte[] inStreamResponseCommandBuffer = readBytes(1, INSTREAM_CMD_RESPONSE);
        if (inStreamResponseCommandBuffer != null) {
            byte inStreamResponseCommand = inStreamResponseCommandBuffer[0];
            consolePrintLn("In-stream received = " + btCommandToString(inStreamResponseCommand));

            if (inStreamResponseCommand == DIR_RESPONSE) {
                byte[] responseData = readBytes(1, inStreamResponseCommand);
                if (responseData != null) {
                    int directoryNameLength = responseData[0];
                    byte[] bufferDirectoryName = new byte[directoryNameLength];
                    bufferDirectoryName = readBytes(directoryNameLength, inStreamResponseCommand);
                    if (bufferDirectoryName != null) {
                        String tempDirectory = new String(bufferDirectoryName);
                        mDirectoryName = tempDirectory;
                        printLogDataForDebugging("Directory Name = " + mDirectoryName);
                    }
                }
            } else if (inStreamResponseCommand == STATUS_RESPONSE) {
                int statusBytesToRead = 0;
                if (isSupportedUSBPluggedInStatus()) {
                    statusBytesToRead = 2;
                } else {
                    statusBytesToRead = 1;
                }
                byte[] responseData = readBytes(statusBytesToRead, inStreamResponseCommand);
                if (responseData != null) {
                    parseStatusByte(responseData);

                    if (!isSupportedRtcStateInStatus()) {
                        if (!mIsSensing && !isInitialised()) {
                            writeRealTimeClock();
                        }
                    } else {
                        if (!isSDLogging() && (!isInitialised() || !mIsRtcSet)) {
                            writeRealTimeClock();
                        }
                    }

                    eventLogAndStreamStatusChanged(mCurrentCommand);
                }
            } else if (inStreamResponseCommand == VBATT_RESPONSE) {
                byte[] responseData = readBytes(3, inStreamResponseCommand);
                if (responseData != null) {
                    ShimmerBattStatusDetails battStatusDetails = new ShimmerBattStatusDetails(((responseData[1] & 0xFF) << 8) + (responseData[0] & 0xFF), responseData[2]);
                    setBattStatusDetails(battStatusDetails);
                    printLogDataForDebugging("Battery Status:"
                            + "\tVoltage=" + battStatusDetails.getBattVoltageParsed()
                            + "\tCharging status=" + battStatusDetails.getChargingStatusParsed()
                            + "\tBatt %=" + battStatusDetails.getEstimatedChargePercentageParsed());
                }
            } else {
                responseWasParsed = false;
            }

            if (shouldClearCrcFromBuffer && mBtCommsCrcModeCurrent != BT_CRC_MODE.OFF && responseWasParsed) {
                clearCrcBytesFromBuffer(inStreamResponseCommand);
            }
        }

    }

    private void processFirmwareVerResponse() {

        if (getHardwareVersion() == HW_ID.SHIMMER_2R) {
            initializeShimmer2R();
        } else if (getHardwareVersion() == HW_ID.SHIMMER_3) {
            initializeShimmer3();
        } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
            initializeShimmer3R();
        }
        stopTimerConnectingTimeout();
        startTimerCheckIfAlive();
    }

    private void buildAndSendMsg(byte[] packet, COMMUNICATION_TYPE fwType, boolean timeSync, long pcTimeStamp) {
        ObjectCluster objectCluster = null;
        try {
            objectCluster = buildMsg(packet, fwType, timeSync, pcTimeStamp);
            objectCluster = systemTimestampPlot.processSystemTimestampPlot(objectCluster);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        dataHandler(objectCluster);
    }

    protected void clearSerialBuffer() {
        if (!InShimmerTest) {
            startTimerCheckForSerialPortClear();

            List<Byte> buffer = new ArrayList<Byte>();
            while (availableBytes() != 0) {
                if (bytesAvailableToBeRead()) {
                    byte[] tb = readBytes(1);
                    if (tb != null) {
                        buffer.add(tb[0]);
                    }

                    if (mSerialPortReadTimeout) {
                        break;
                    }
                }
            }

            if (buffer.size() > 0) {
                byte[] newBuf = ArrayUtils.toPrimitive(buffer.toArray(new Byte[buffer.size()]));
                String msg = "Clearing Buffer:\t\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(newBuf);
                printLogDataForDebugging(msg);
            }

            stopTimerCheckForSerialPortClear();
        }
    }

    public void processDataPacket(byte[] bufferTemp) {
        byte[] newPacket = new byte[getPacketSize()];
        System.arraycopy(bufferTemp, 1, newPacket, 0, getPacketSize());

        if (mUseProcessingThread) {
            if (mABQPacketByeArray.remainingCapacity() == 0) {
                mABQPacketByeArray.remove();
            }
            if (mEnablePCTimeStamps) {
                mABQPacketByeArray.add(new RawBytePacketWithPCTimeStamp(newPacket, mListofPCTimeStamps.get(0)));
            } else {
                mABQPacketByeArray.add(new RawBytePacketWithPCTimeStamp(newPacket, System.currentTimeMillis()));
            }
        } else {
            if (mEnablePCTimeStamps) {
                buildAndSendMsg(newPacket, COMMUNICATION_TYPE.BLUETOOTH, false, mListofPCTimeStamps.get(0));
            } else {
                buildAndSendMsg(newPacket, COMMUNICATION_TYPE.BLUETOOTH, false, System.currentTimeMillis());
            }
        }
    }

    protected void clearSingleDataPacketFromBuffers(byte[] bufferTemp, int packetSize) {
        mByteArrayOutputStream.reset();
        mByteArrayOutputStream.write(bufferTemp[packetSize]);
        if (mEnablePCTimeStamps) {
            for (int i = 0; i < packetSize; i++) {
                try {
                    mListofPCTimeStamps.remove(0);
                } catch (
                        Exception e) {
                    consolePrintException(e.getMessage(), e.getStackTrace());
                }
            }
        }
    }

    protected void clearBuffers() {
        mByteArrayOutputStream.reset();
        mListofPCTimeStamps.clear();
    }

    protected void discardFirstBufferByte() {
        byte[] bTemp = mByteArrayOutputStream.toByteArray();
        mByteArrayOutputStream.reset();
        mByteArrayOutputStream.write(bTemp, 1, bTemp.length - 1);
        if (mEnablePCTimeStamps) {
            mListofPCTimeStamps.remove(0);
        }
        consolePrintLn("Throw Byte" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bTemp));
    }


    private void processResponseCommand(byte responseCommand) {
        boolean responseWasParsed = true;

        if (responseCommand == INQUIRY_RESPONSE) {
            delayForBtResponse(500);
            List<Byte> buffer = new ArrayList<Byte>();
			/*
			if (Math.random()>0.5 && mIsInitialised==true){
				if(bytesAvailableToBeRead()
						&& mCurrentCommand!=TEST_CONNECTION_COMMAND
						&& mCurrentCommand!=GET_STATUS_COMMAND
						&& mCurrentCommand!= GET_VBATT_COMMAND
						&& mCurrentCommand!=START_STREAMING_COMMAND
						&& mCurrentCommand!=STOP_STREAMING_COMMAND
						&& mCurrentCommand!=SET_RWC_COMMAND
						&& mCurrentCommand!=GET_RWC_COMMAND){
					readByte();
				}
			}
			*/

            int lengthSettings = 8;
            int idxLengthChannels = 6;
            if (mShimmerVerObject.isShimmerGen2()) {
                lengthSettings = 5;
                idxLengthChannels = 3;
            } else if (mShimmerVerObject.isShimmerGen3R()) {
                lengthSettings = 11;
                idxLengthChannels = 9;
            }

            byte[] settings = readBytes(lengthSettings, responseCommand);
            if (settings != null) {
                for (byte b : settings) {
                    buffer.add(b);
                }
            }
            int lengthChannels = (int) buffer.get(idxLengthChannels);
            if (lengthChannels > 0) {
                byte[] channels = readBytes(lengthChannels, responseCommand);
                if (channels != null) {
                    for (byte b : channels) {
                        buffer.add(b);
                    }
                }
            }

            byte[] bufferInquiry = new byte[buffer.size()];
            for (int i = 0; i < bufferInquiry.length; i++) {
                bufferInquiry[i] = (byte) buffer.get(i);
            }

            printLogDataForDebugging("Inquiry Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bufferInquiry));

            interpretInqResponse(bufferInquiry);

            inquiryDone();
        } else if (responseCommand == SAMPLING_RATE_RESPONSE) {
            if (!mIsStreaming) {
                if (isShimmerGen2()) {
                    byte[] bufferSR = readBytes(1, responseCommand);
                    if (bufferSR != null) {
                        if (mCurrentCommand == GET_SAMPLING_RATE_COMMAND) {
                            double val = (double) (bufferSR[0] & (byte) ACK_COMMAND_PROCESSED);
                            setSamplingRateShimmer(1024 / val);
                        }
                    }
                } else if (isShimmerGen3or3R()) {
                    byte[] bufferSR = readBytes(2, responseCommand);
                    if (bufferSR != null) {
                        setSamplingRateShimmer(convertSamplingRateBytesToFreq(bufferSR[0], bufferSR[1], getSamplingClockFreq()));
                    }
                }
            }

            printLogDataForDebugging("Sampling Rate Response Received: " + Double.toString(getSamplingRateShimmer()));
        } else if (responseCommand == GET_SHIMMER_VERSION_RESPONSE) {
            delayForBtResponse(100);
            byte[] bufferShimmerVersion = new byte[1];
            bufferShimmerVersion = readBytes(1, responseCommand);
            if (bufferShimmerVersion != null) {
                setHardwareVersion((int) bufferShimmerVersion[0]);

                printLogDataForDebugging("Shimmer Version (HW) Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bufferShimmerVersion));

                readFWVersion();
            }
        } else if (responseCommand == FW_VERSION_RESPONSE) {
            delayForBtResponse(200);
            byte[] bufferInquiry = readBytes(6, responseCommand);
            if (bufferInquiry != null) {
                int firmwareIdentifier = (int) ((bufferInquiry[1] & 0xFF) << 8) + (int) (bufferInquiry[0] & 0xFF);
                int firmwareVersionMajor = (int) ((bufferInquiry[3] & 0xFF) << 8) + (int) (bufferInquiry[2] & 0xFF);
                int firmwareVersionMinor = ((int) ((bufferInquiry[4] & 0xFF)));
                int firmwareVersionInternal = (int) (bufferInquiry[5] & 0xFF);
                ShimmerVerObject shimmerVerObject = new ShimmerVerObject(getHardwareVersion(), firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
                setShimmerVersionObjectAndCreateSensorMap(shimmerVerObject);

                printLogDataForDebugging("FW Version Response Received. FW Code: " + getFirmwareVersionCode());
                printLogDataForDebugging("FW Version Response Received: " + getFirmwareVersionParsed());

                if (getFirmwareVersionCode() >= 5) {
                    readExpansionBoardID();
                }
            }
        } else if (responseCommand == ALL_CALIBRATION_RESPONSE) {
            if (isShimmerGen3or3R()) {
                processAccelCalReadBytes();
                processGyroCalReadBytes();
                processMagCalReadBytes();
                processLsm303dlhcAccelCalReadBytes();
                if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
                }
            } else {
                processAccelCalReadBytes();
                processGyroCalReadBytes();
                processMagCalReadBytes();
                processShimmer2EmgCalReadBytes();
                processShimmer2EcgCalReadBytes();
            }
        } else if (responseCommand == ACCEL_CALIBRATION_RESPONSE) {
            processAccelCalReadBytes();
        } else if (responseCommand == GYRO_CALIBRATION_RESPONSE) {
            processGyroCalReadBytes();
        } else if (responseCommand == MAG_CALIBRATION_RESPONSE) {
            processMagCalReadBytes();
        } else if (responseCommand == ECG_CALIBRATION_RESPONSE) {
            processShimmer2EcgCalReadBytes();
        } else if (responseCommand == EMG_CALIBRATION_RESPONSE) {
            processShimmer2EmgCalReadBytes();
        } else if (responseCommand == LSM303DLHC_ACCEL_CALIBRATION_RESPONSE) {
            processLsm303dlhcAccelCalReadBytes();
        } else if (responseCommand == CONFIG_BYTE0_RESPONSE) {
            if (getHardwareVersion() == HW_ID.SHIMMER_2R || getHardwareVersion() == HW_ID.SHIMMER_2) {
                byte[] bufferConfigByte0 = readBytes(1, responseCommand);
                if (bufferConfigByte0 != null) {
                    mConfigByte0 = bufferConfigByte0[0] & 0xFF;
                }
            } else {
                byte[] bufferConfigByte0 = readBytes(4, responseCommand);
                if (bufferConfigByte0 != null) {
                    mConfigByte0 = ((long) (bufferConfigByte0[0] & 0xFF) + ((long) (bufferConfigByte0[1] & 0xFF) << 8) + ((long) (bufferConfigByte0[2] & 0xFF) << 16) + ((long) (bufferConfigByte0[3] & 0xFF) << 24));
                }
            }
        } else if (responseCommand == DERIVED_CHANNEL_BYTES_RESPONSE) {
            byte[] byteArray = readBytes(3, responseCommand);
            if (byteArray != null) {
                mDerivedSensors = (long) (((byteArray[2] & 0xFF) << 16) + ((byteArray[1] & 0xFF) << 8) + (byteArray[0] & 0xFF));
            }

            if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                byteArray = readBytes(5, responseCommand);
                if (byteArray != null) {
                    mDerivedSensors |= ((long) (byteArray[0] & 0xFF)) << (8 * 3);
                    mDerivedSensors |= ((long) (byteArray[1] & 0xFF)) << (8 * 4);
                    mDerivedSensors |= ((long) (byteArray[2] & 0xFF)) << (8 * 5);
                    mDerivedSensors |= ((long) (byteArray[3] & 0xFF)) << (8 * 6);
                    mDerivedSensors |= ((long) (byteArray[4] & 0xFF)) << (8 * 7);
                }
            }

            if (mEnabledSensors != 0) {
                setEnabledAndDerivedSensorsAndUpdateMaps(mEnabledSensors, mDerivedSensors);
                inquiryDone();
            }
        } else if (responseCommand == ACCEL_SENSITIVITY_RESPONSE) {
            byte[] bufferAccelSensitivity = readBytes(1, responseCommand);
            if (bufferAccelSensitivity != null) {
                setAccelRange(bufferAccelSensitivity[0]);
            }
        } else if (responseCommand == MPU9150_GYRO_RANGE_RESPONSE) {
            byte[] bufferGyroSensitivity = readBytes(1, responseCommand);
            if (bufferGyroSensitivity != null) {
                setGyroRange(bufferGyroSensitivity[0]);
            }
        } else if (responseCommand == GSR_RANGE_RESPONSE) {
            byte[] bufferGSRRange = readBytes(1, responseCommand);
            if (bufferGSRRange != null) {
                setGSRRange(bufferGSRRange[0]);

                printLogDataForDebugging("GSR Range Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bufferGSRRange));
            }
        } else if (responseCommand == BLINK_LED_RESPONSE) {
            byte[] byteled = readBytes(1, responseCommand);
            if (byteled != null) {
                mCurrentLEDStatus = byteled[0] & 0xFF;
            }
        } else if (responseCommand == BUFFER_SIZE_RESPONSE) {
            byte[] byteBufferSize = readBytes(1, responseCommand);
            if (byteBufferSize != null) {
                mBufferSize = byteBufferSize[0] & 0xFF;
            }
        } else if (responseCommand == MAG_GAIN_RESPONSE) {
            byte[] bufferAns = readBytes(1, responseCommand);
            if (bufferAns != null) {
                setLSM303MagRange(bufferAns[0]);
            }
        } else if (responseCommand == MAG_SAMPLING_RATE_RESPONSE) {
            byte[] bufferAns = readBytes(1, responseCommand);
            if (bufferAns != null) {
                setMagRate(bufferAns[0]);

                printLogDataForDebugging("Mag Sampling Rate Response Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bufferAns));
            }
        } else if (responseCommand == ACCEL_SAMPLING_RATE_RESPONSE) {
            byte[] bufferAns = readBytes(1, responseCommand);
            if (bufferAns != null) {
                setLSM303DigitalAccelRate(bufferAns[0]);
            }
        } else if (responseCommand == BMP180_CALIBRATION_COEFFICIENTS_RESPONSE) {
            delayForBtResponse(100);


            byte[] pressureResoRes = new byte[22];
            pressureResoRes = readBytes(22, responseCommand);
            if (pressureResoRes != null) {
                retrievePressureCalibrationParametersFromPacket(pressureResoRes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
            }
        } else if (responseCommand == BMP280_CALIBRATION_COEFFICIENTS_RESPONSE) {
            byte[] pressureResoRes = new byte[24];
            pressureResoRes = readBytes(24, responseCommand);
            if (pressureResoRes != null) {
                retrievePressureCalibrationParametersFromPacket(pressureResoRes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
                printLogDataForDebugging("BMP280 CALIB Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(pressureResoRes));
            }
        } else if (responseCommand == PRESSURE_CALIBRATION_COEFFICIENTS_RESPONSE) {
            byte[] pressureResoRes = null;
            byte[] filteredByteArray = null;
            if (mSensorBMPX80.mSensorType.equals(SENSORS.BMP390)) {
                pressureResoRes = new byte[23];
                pressureResoRes = readBytes(23, responseCommand);
                printLogDataForDebugging("BMP390 CALIB Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(pressureResoRes));
            } else if (mSensorBMPX80.mSensorType.equals(SENSORS.BMP280)) {
                pressureResoRes = new byte[26];
                pressureResoRes = readBytes(26, responseCommand);
                printLogDataForDebugging("BMP280 CALIB Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(pressureResoRes));
            } else if (mSensorBMPX80.mSensorType.equals(SENSORS.BMP180)) {
                pressureResoRes = new byte[24];
                pressureResoRes = readBytes(24, responseCommand);
                printLogDataForDebugging("BMP180 CALIB Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(pressureResoRes));
            }

            if (pressureResoRes != null) {
                filteredByteArray = Arrays.copyOfRange(pressureResoRes, 2, pressureResoRes.length);
                retrievePressureCalibrationParametersFromPacket(filteredByteArray, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
            }
        } else if (responseCommand == EXG_REGS_RESPONSE) {
            delayForBtResponse(300);
            byte[] bufferAns = readBytes(11, responseCommand);
            if (bufferAns != null) {
                if (mTempChipID == EXG_CHIP_INDEX.CHIP1.ordinal()) {
                    byte[] EXG1RegisterArray = new byte[10];
                    System.arraycopy(bufferAns, 1, EXG1RegisterArray, 0, 10);
                    setEXG1RegisterArray(EXG1RegisterArray);
                } else if (mTempChipID == EXG_CHIP_INDEX.CHIP2.ordinal()) {
                    byte[] EXG2RegisterArray = new byte[10];
                    System.arraycopy(bufferAns, 1, EXG2RegisterArray, 0, 10);
                    setEXG2RegisterArray(EXG2RegisterArray);
                }
            }
        } else if (responseCommand == DAUGHTER_CARD_ID_RESPONSE) {
            byte[] expBoardArray = readBytes(numBytesToReadFromExpBoard + 1, responseCommand);
            if (expBoardArray != null) {
                byte[] expBoardArraySplit = Arrays.copyOfRange(expBoardArray, 1, 4);
                setExpansionBoardDetailsAndCreateSensorMap(new ExpansionBoardDetails(expBoardArraySplit));
            }
        } else if (responseCommand == BAUD_RATE_RESPONSE) {
            byte[] bufferBaud = readBytes(1, responseCommand);
            if (bufferBaud != null) {
                mBluetoothBaudRate = bufferBaud[0] & 0xFF;
            }
        } else if (responseCommand == TRIAL_CONFIG_RESPONSE) {
            byte[] data = readBytes(3, responseCommand);
            if (data != null) {
                fillTrialShimmer3(data);
            }
        } else if (responseCommand == CENTER_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);
            if (length != null) {
                byte[] data = readBytes(length[0], responseCommand);
                if (data != null) {
                    String center = new String(data);
                    setCenter(center);
                }
            }
        } else if (responseCommand == SHIMMERNAME_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);
            if (length != null) {
                byte[] data = readBytes(length[0], responseCommand);
                if (data != null) {
                    String name = new String(data);
                    setShimmerUserAssignedName(name);
                }
            }
        } else if (responseCommand == EXPID_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);
            if (length != null) {
                byte[] data = readBytes(length[0], responseCommand);
                if (data != null) {
                    String name = new String(data);
                    setTrialNameAndCheck(name);
                }
            }
        } else if (responseCommand == CONFIGTIME_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);
            if (length != null) {
                byte[] data = readBytes(length[0], responseCommand);
                if (data != null) {
                    String time = new String(data);
                    if (time.isEmpty()) {
                        setConfigTime(0);
                    } else {
                        setConfigTime(Long.parseLong(time));
                    }
                }
            }
        } else if (responseCommand == RWC_RESPONSE) {
            byte[] responseData = readBytes(8, responseCommand);
            if (responseData != null) {
                responseData = Arrays.copyOf(responseData, 8);
                ArrayUtils.reverse(responseData);
                long responseTime = (long) (((double) (ByteBuffer.wrap(responseData).getLong()) / 32.768)); // / 1000

                setLastReadRealTimeClockValue(responseTime);
            }
        } else if (responseCommand == INSTREAM_CMD_RESPONSE) {
            processInstreamResponse(false);
        } else if (responseCommand == LSM303DLHC_ACCEL_LPMODE_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                setLowPowerAccelWR(((int) (responseData[0] & 0xFF)) >= 1 ? true : false);
            }
        } else if (responseCommand == LSM303DLHC_ACCEL_HRMODE_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                setHighResAccelWR(((int) (responseData[0] & 0xFF)) >= 1 ? true : false);
            }
        } else if (responseCommand == MYID_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                mTrialId = (int) (responseData[0] & 0xFF);
            }
        } else if (responseCommand == NSHIMMER_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                mTrialNumberOfShimmers = (int) (responseData[0] & 0xFF);
            }
        } else if (responseCommand == MPU9150_SAMPLING_RATE_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                setMPU9150MPLSamplingRate(((int) (responseData[0] & 0xFF)));
            }
        } else if (responseCommand == BMP180_PRES_RESOLUTION_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                setPressureResolution((int) (responseData[0] & 0xFF));
            }
        } else if (responseCommand == BMP180_PRES_CALIBRATION_RESPONSE) {
        } else if (responseCommand == MPU9150_MAG_SENS_ADJ_VALS_RESPONSE) {
        } else if (responseCommand == INTERNAL_EXP_POWER_ENABLE_RESPONSE) {
            byte[] responseData = readBytes(1, responseCommand);
            if (responseData != null) {
                setInternalExpPower((int) (responseData[0] & 0xFF));
            }
        } else if (responseCommand == INFOMEM_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);
            if (length != null) {
                int lengthToRead = (int) (length[0] & 0xFF);
                byte[] responseData = readBytes(lengthToRead, responseCommand);
                if (responseData != null) {
                    printLogDataForDebugging("INFOMEM_RESPONSE Received: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(responseData));

                    mMemBuffer = ArrayUtils.addAll(mMemBuffer, responseData);


                    MemReadDetails memReadDetails = mMapOfMemReadDetails.get(GET_INFOMEM_COMMAND);
                    if (memReadDetails != null) {
                        int currentEndAddress = memReadDetails.mCurrentMemAddress + memReadDetails.mCurrentMemLengthToRead;
                        if (currentEndAddress >= memReadDetails.mEndMemAddress) {
                            setShimmerInfoMemBytes(mMemBuffer);
                            clearMemReadBuffer(GET_INFOMEM_COMMAND);
                        }
                    }
                }
            }
        } else if (responseCommand == RSP_CALIB_DUMP_COMMAND) {
            byte[] responseData = readBytes(3, responseCommand);
            if (responseData != null) {

                int currentMemLength = responseData[0] & 0xFF;
                int currentMemOffset = ((responseData[2] & 0xFF) << 8) | (responseData[1] & 0xFF);

                responseData = readBytes(currentMemLength, responseCommand);
                if (responseData != null) {
                    mMemBuffer = ArrayUtils.addAll(mMemBuffer, responseData);

                    printLogDataForDebugging("CALIB_DUMP Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(responseData));
                    printLogDataForDebugging("CALIB_DUMP concat:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(mMemBuffer));

                    MemReadDetails memReadDetails = mMapOfMemReadDetails.get(GET_CALIB_DUMP_COMMAND);
                    if (memReadDetails != null) {
                        if (memReadDetails.mCurrentMemAddress == 0) {
                            int totalCalibLength = (responseData[1] & 0xFF) << 8 | (responseData[0] & 0xFF);
                            memReadDetails.setTotalMemLengthToRead(totalCalibLength + 2);

                            if (memReadDetails.getTotalMemLengthToRead() > memReadDetails.mCurrentMemLengthToRead) {
                                int nextAddress = memReadDetails.mCurrentMemLengthToRead;
                                int remainingBytes = memReadDetails.getTotalMemLengthToRead() - memReadDetails.mCurrentMemLengthToRead;
                                readCalibrationDump(nextAddress, remainingBytes);
                                rePioritiseReadCalibDumpInstructions();
                            }
                        }

                        int currentEndAddress = memReadDetails.mCurrentMemAddress + memReadDetails.mCurrentMemLengthToRead;
                        if (currentEndAddress >= memReadDetails.mEndMemAddress) {
                            calibByteDumpParse(mMemBuffer, CALIB_READ_SOURCE.RADIO_DUMP);
                            clearMemReadBuffer(GET_CALIB_DUMP_COMMAND);
                        }
                    }
                }
            }
        } else if (responseCommand == BT_FW_VERSION_STR_RESPONSE) {
            byte[] length = readBytes(1, responseCommand);

            if (length != null && length.length > 0) {
                int lengthToRead = (int) (length[0] & 0xFF);
                byte[] responseData = readBytes(lengthToRead, responseCommand);
                if (responseData != null) {
                    printLogDataForDebugging("BT_FW_VERSION_STR_RESPONSE Received:\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(responseData));
                    super.getBtFwVerDetails().parseBtModuleVerBytes(responseData);
                    printLogDataForDebugging(super.getBtFwVerDetails().mBtModuleVersionParsed.btModuleVerStrFull);
                }
            }
        } else {
            consolePrintLn("Unhandled BT response: " + responseCommand);
            responseWasParsed = false;
        }

        if (mBtCommsCrcModeCurrent != BT_CRC_MODE.OFF && responseWasParsed) {
            clearCrcBytesFromBuffer(responseCommand);
        }
    }

    protected void processAckFromSetCommand(byte currentCommand) {
        if (getListofInstructions().size() > 0) {
            if (getListofInstructions().get(0) != null) {

                if (currentCommand == START_STREAMING_COMMAND || currentCommand == START_SDBT_COMMAND) {
                    mIsStreaming = true;
                    if (currentCommand == START_SDBT_COMMAND) {
                        setIsSDLogging(true);
                        eventLogAndStreamStatusChanged(mCurrentCommand);
                    }
                    byteStack.clear();
                    isNowStreaming();
                } else if ((currentCommand == STOP_STREAMING_COMMAND) || (currentCommand == STOP_SDBT_COMMAND)) {
                    mIsStreaming = false;
                    if (currentCommand == STOP_SDBT_COMMAND) {
                        setIsSDLogging(false);
                        eventLogAndStreamStatusChanged(mCurrentCommand);
                    }

                    byteStack.clear();

                    clearSerialBuffer();

                    hasStopStreaming();
                    removeAllNulls();
                } else if (currentCommand == START_LOGGING_ONLY_COMMAND) {
                    setIsSDLogging(true);
                    eventLogAndStreamStatusChanged(mCurrentCommand);
                } else if (currentCommand == STOP_LOGGING_ONLY_COMMAND) {
                    setIsSDLogging(false);
                    eventLogAndStreamStatusChanged(mCurrentCommand);
                } else if (currentCommand == SET_SAMPLING_RATE_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    double tempdouble = -1;
                    if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {
                        tempdouble = (double) 1024 / instruction[1];
                    } else {
                        tempdouble = convertSamplingRateBytesToFreq(instruction[1], instruction[2], getSamplingClockFreq());
                    }
                    setSamplingRateShimmer(tempdouble);

                    if (getHardwareVersion() == HW_ID.SHIMMER_3) {
						/*if(mShimmerSamplingRate<=128){
							writeEXGRateSetting(1,0);
							writeEXGRateSetting(2,0);
						}
						else if(mShimmerSamplingRate<=256){
							writeEXGRateSetting(1,1);
							writeEXGRateSetting(2,1);
						}
						else if(mShimmerSamplingRate<=512){
							writeEXGRateSetting(1,2);
							writeEXGRateSetting(2,2);
						}*/
                    }
                } else if (currentCommand == SET_BUFFER_SIZE_COMMAND) {
                    mBufferSize = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == SET_GYRO_TEMP_VREF_COMMAND) {
                    mConfigByte0 = mTempByteValue;
                } else if (currentCommand == SET_BLINK_LED) {
                    mCurrentLEDStatus = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == TEST_CONNECTION_COMMAND) {
                } else if (currentCommand == SET_GSR_RANGE_COMMAND) {
                    setGSRRange((int) ((byte[]) getListofInstructions().get(0))[1]);
                } else if (currentCommand == SET_CONFIG_BYTE0_COMMAND) {
                    mConfigByte0 = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == SET_PMUX_COMMAND) {
                    if (((byte[]) getListofInstructions().get(0))[1] == 1) {
                        mConfigByte0 = (byte) ((byte) (mConfigByte0 | 64) & (0xFF));
                    } else if (((byte[]) getListofInstructions().get(0))[1] == 0) {
                        mConfigByte0 = (byte) ((byte) (mConfigByte0 & 191) & (0xFF));
                    }
                } else if (currentCommand == SET_BMP180_PRES_RESOLUTION_COMMAND) {
                    setPressureResolution((int) ((byte[]) getListofInstructions().get(0))[1]);
                } else if (currentCommand == SET_5V_REGULATOR_COMMAND) {
                    if (((byte[]) getListofInstructions().get(0))[1] == 1) {
                        mConfigByte0 = (byte) (mConfigByte0 | 128);
                    } else if (((byte[]) getListofInstructions().get(0))[1] == 0) {
                        mConfigByte0 = (byte) (mConfigByte0 & 127);
                    }
                } else if (currentCommand == SET_INTERNAL_EXP_POWER_ENABLE_COMMAND) {
                    if (((byte[]) getListofInstructions().get(0))[1] == 1) {
                        mConfigByte0 = (mConfigByte0 | 16777216);
                        mInternalExpPower = 1;
                    } else if (((byte[]) getListofInstructions().get(0))[1] == 0) {
                        mConfigByte0 = mConfigByte0 & 4278190079l;
                        mInternalExpPower = 0;
                    }
                } else if (currentCommand == SET_ACCEL_SENSITIVITY_COMMAND) {
                    setAccelRange((int) (((byte[]) getListofInstructions().get(0))[1]));
                } else if (currentCommand == SET_ACCEL_CALIBRATION_COMMAND) {
                    byte[] calibBytes = Arrays.copyOfRange(getListofInstructions().get(0), 1, getListofInstructions().get(0).length);
                    parseCalibParamFromPacketAccelAnalog(calibBytes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
                } else if (currentCommand == SET_GYRO_CALIBRATION_COMMAND) {
                    byte[] calibBytes = Arrays.copyOfRange(getListofInstructions().get(0), 1, getListofInstructions().get(0).length);
                    parseCalibParamFromPacketGyro(calibBytes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
                } else if (currentCommand == SET_MAG_CALIBRATION_COMMAND) {
                    byte[] calibBytes = Arrays.copyOfRange(getListofInstructions().get(0), 1, getListofInstructions().get(0).length);
                    parseCalibParamFromPacketMag(calibBytes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
                } else if (currentCommand == SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND) {
                    byte[] calibBytes = Arrays.copyOfRange(getListofInstructions().get(0), 1, getListofInstructions().get(0).length);
                    parseCalibParamFromPacketAccelLsm(calibBytes, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
                } else if (currentCommand == SET_MPU9150_GYRO_RANGE_COMMAND) {
                    setGyroRange((int) (((byte[]) getListofInstructions().get(0))[1]));
                } else if (currentCommand == SET_MAG_SAMPLING_RATE_COMMAND) {
                    setMagRate(mTempIntValue);
                } else if (currentCommand == SET_ACCEL_SAMPLING_RATE_COMMAND) {
                    setLSM303DigitalAccelRate(mTempIntValue);
                } else if (currentCommand == SET_MPU9150_SAMPLING_RATE_COMMAND) {
                    setMPU9150GyroAccelRate(mTempIntValue);
                } else if (currentCommand == SET_EXG_REGS_COMMAND) {
                    byte[] bytearray = getListofInstructions().get(0);
                    if (bytearray[1] == EXG_CHIP_INDEX.CHIP1.ordinal()) {
                        byte[] EXG1RegisterArray = new byte[10];
                        System.arraycopy(bytearray, 4, EXG1RegisterArray, 0, 10);
                        setEXG1RegisterArray(EXG1RegisterArray);
                    } else if (bytearray[1] == EXG_CHIP_INDEX.CHIP2.ordinal()) {
                        byte[] EXG2RegisterArray = new byte[10];
                        System.arraycopy(bytearray, 4, EXG2RegisterArray, 0, 10);
                        setEXG2RegisterArray(EXG2RegisterArray);
                    }
                } else if (currentCommand == SET_SENSORS_COMMAND) {
                    mEnabledSensors = tempEnabledSensors;
                    if (isShimmerGen3or3R()) {
                        checkExgResolutionFromEnabledSensorsVar();
                    }
                    byteStack.clear();
                } else if (currentCommand == SET_MAG_GAIN_COMMAND) {
                    setLSM303MagRange((int) ((byte[]) getListofInstructions().get(0))[1]);
                } else if (currentCommand == SET_ECG_CALIBRATION_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    mDefaultCalibrationParametersECG = false;
                    OffsetECGLALL = (double) ((instruction[0] & 0xFF) << 8) + (instruction[1] & 0xFF);
                    GainECGLALL = (double) ((instruction[2] & 0xFF) << 8) + (instruction[3] & 0xFF);
                    OffsetECGRALL = (double) ((instruction[4] & 0xFF) << 8) + (instruction[5] & 0xFF);
                    GainECGRALL = (double) ((instruction[6] & 0xFF) << 8) + (instruction[7] & 0xFF);
                } else if (currentCommand == SET_EMG_CALIBRATION_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    mDefaultCalibrationParametersEMG = false;
                    OffsetEMG = (double) ((instruction[0] & 0xFF) << 8) + (instruction[1] & 0xFF);
                    GainEMG = (double) ((instruction[2] & 0xFF) << 8) + (instruction[3] & 0xFF);
                } else if (currentCommand == SET_DERIVED_CHANNEL_BYTES) {
                    byte[] instruction = getListofInstructions().get(0);
                    mDerivedSensors = (long) (((instruction[3] & 0xFF) << 16) + ((instruction[2] & 0xFF) << 8) + (instruction[1] & 0xFF));

                    if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                        mDerivedSensors |= ((long) (instruction[4] & 0xFF)) << (8 * 3);
                        mDerivedSensors |= ((long) (instruction[5] & 0xFF)) << (8 * 4);
                        mDerivedSensors |= ((long) (instruction[6] & 0xFF)) << (8 * 5);
                        mDerivedSensors |= ((long) (instruction[7] & 0xFF)) << (8 * 6);
                        mDerivedSensors |= ((long) (instruction[8] & 0xFF)) << (8 * 7);
                    }

                    if (mEnabledSensors != 0) {
                        setEnabledAndDerivedSensorsAndUpdateMaps(mEnabledSensors, mDerivedSensors);
                        inquiryDone();
                    }
                } else if (currentCommand == SET_SHIMMERNAME_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] nameArray = new byte[instruction[1]];
                    System.arraycopy(instruction, 2, nameArray, 0, instruction[1]);
                    String name = new String(nameArray);
                    setShimmerUserAssignedName(name);
                } else if (currentCommand == SET_EXPID_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] nameArray = new byte[instruction[1]];
                    System.arraycopy(instruction, 2, nameArray, 0, instruction[1]);
                    String name = new String(nameArray);
                    setTrialNameAndCheck(name);
                } else if (currentCommand == SET_RWC_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] rwcTimeArray = new byte[8];
                    System.arraycopy(instruction, 1, rwcTimeArray, 0, 8);
                    long milliseconds = UtilShimmer.convertShimmerRtcDataBytesToMilliSecondsLSB(rwcTimeArray);
                    mShimmerRealTimeClockConFigTime = milliseconds;
                } else if (currentCommand == SET_CONFIGTIME_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] timeArray = new byte[instruction[1]];
                    System.arraycopy(instruction, 2, timeArray, 0, instruction[1]);
                    String time = new String(timeArray);
                    setConfigTime(Long.parseLong(time));
                } else if (currentCommand == SET_CENTER_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] centerArray = new byte[instruction[1]];
                    System.arraycopy(instruction, 2, centerArray, 0, instruction[1]);
                    String center = new String(centerArray);
                    setCenter(center);
                } else if (currentCommand == SET_TRIAL_CONFIG_COMMAND) {
                    byte[] instruction = getListofInstructions().get(0);
                    byte[] dataArray = new byte[3];
                    System.arraycopy(instruction, 1, dataArray, 0, 3);

                    fillTrialShimmer3(dataArray);
                } else if (currentCommand == SET_BAUD_RATE_COMMAND) {
                    mBluetoothBaudRate = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == TOGGLE_LED_COMMAND) {
                } else if (currentCommand == SET_LSM303DLHC_ACCEL_LPMODE_COMMAND) {
                    setLowPowerAccelWR((int) ((byte[]) getListofInstructions().get(0))[1] > 1 ? true : false);
                } else if (currentCommand == SET_LSM303DLHC_ACCEL_HRMODE_COMMAND) {
                    setHighResAccelWR((int) ((byte[]) getListofInstructions().get(0))[1] > 1 ? true : false);
                } else if (currentCommand == SET_MYID_COMMAND) {
                    mTrialId = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == SET_NSHIMMER_COMMAND) {
                    mTrialNumberOfShimmers = (int) ((byte[]) getListofInstructions().get(0))[1];
                } else if (currentCommand == RESET_TO_DEFAULT_CONFIGURATION_COMMAND) {
                } else if (currentCommand == RESET_CALIBRATION_VALUE_COMMAND) {
                } else if (currentCommand == SET_INFOMEM_COMMAND
                        || currentCommand == SET_CALIB_DUMP_COMMAND) {

                    mNumOfMemSetCmds -= 1;
                    if (!mShimmerVerObject.isBtMemoryUpdateCommandSupported()) {
                        if (mNumOfMemSetCmds == 0) {
                            threadSleep(LiteProtocol.DELAY_BETWEEN_CONFIG_BYTE_WRITES);
                        } else {
                            threadSleep(LiteProtocol.DELAY_AFTER_CONFIG_BYTE_WRITE);
                        }
                    }
                } else if (currentCommand == InstructionsSet.UPD_CONFIG_MEMORY_COMMAND_VALUE) {
                    if (mShimmerVerObject.isBtMemoryUpdateCommandSupported()) {
                        threadSleep(LiteProtocol.DELAY_AFTER_CONFIG_BYTE_WRITE);
                    }
                } else if (currentCommand == SET_CRC_COMMAND) {
                    setCurrentBtCommsCrcMode(BT_CRC_MODE.values()[(int) ((byte[]) getListofInstructions().get(0))[1]]);
                } else if (currentCommand == SET_TEST) {
                    InShimmerTest = true;
                } else {
                    printLogDataForDebugging("Unhandled set command: " + btCommandToString(currentCommand));
                }

                removeInstruction(0);
            }

        }
    }

    private void delayForBtResponse(long millis) {
        if (mUseLegacyDelayToDelayForResponse)
            threadSleep(millis);
    }

    public void setDelayForBtRespone(boolean delay) {
        mUseLegacyDelayToDelayForResponse = delay;
    }

    public boolean setDelayForBtResponse() {
        return mUseLegacyDelayToDelayForResponse;
    }

    private void processAccelCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(21, ACCEL_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            parseCalibParamFromPacketAccelAnalog(bufferCalibrationParameters, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
        }
    }

    private void processGyroCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(21, GYRO_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            parseCalibParamFromPacketGyro(bufferCalibrationParameters, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
        }
    }

    private void processMagCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(21, MAG_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            parseCalibParamFromPacketMag(bufferCalibrationParameters, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
        }
    }

    private void processLsm303dlhcAccelCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(21, LSM303DLHC_ACCEL_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            parseCalibParamFromPacketAccelLsm(bufferCalibrationParameters, CALIB_READ_SOURCE.LEGACY_BT_COMMAND);
        }
    }

    private void processShimmer2EcgCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(8, ECG_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            mECGCalRawParams = new byte[9];
            System.arraycopy(bufferCalibrationParameters, 0, mECGCalRawParams, 1, 8);
            mECGCalRawParams[0] = ECG_CALIBRATION_RESPONSE;
            retrieveBiophysicalCalibrationParametersFromPacket(bufferCalibrationParameters, ECG_CALIBRATION_RESPONSE);
        }
    }

    private void processShimmer2EmgCalReadBytes() {
        delayForBtResponse(100);
        byte[] bufferCalibrationParameters = readBytes(4, EMG_CALIBRATION_RESPONSE);
        if (bufferCalibrationParameters != null) {
            mEMGCalRawParams = new byte[5];
            System.arraycopy(bufferCalibrationParameters, 0, mEMGCalRawParams, 1, 4);
            mEMGCalRawParams[0] = EMG_CALIBRATION_RESPONSE;
            retrieveBiophysicalCalibrationParametersFromPacket(bufferCalibrationParameters, EMG_CALIBRATION_RESPONSE);
        }
    }

    protected byte[] readBytes(int numBytes, byte btCommand) {
        byte[] response = readBytes(numBytes);
        if (response == null) {
            printLogDataForDebugging("NULL serial readBytes response for command: " + btCommandToString(btCommand));
        }
        return response;
    }

    protected void parseStatusByte(byte[] statusByte) {
        Boolean savedDockedState = isDocked();

        setIsDocked(((statusByte[0] & (0x01 << 0)) > 0) ? true : false);
        setIsSensing(((statusByte[0] & (0x01 << 1)) > 0) ? true : false);
        if (isSupportedRtcStateInStatus()) {
            mIsRtcSet = ((statusByte[0] & (0x01 << 2)) > 0) ? true : false;
        }
        setIsSDLogging(((statusByte[0] & (0x01 << 3)) > 0) ? true : false);
        setIsStreaming(((statusByte[0] & (0x01 << 4)) > 0) ? true : false);
        if (isSupportedSdInfoInStatus()) {
            setIsSDPresent(((statusByte[0] & (0x01 << 5)) > 0) ? true : false);
            setIsSDError(((statusByte[0] & (0x01 << 6)) > 0) ? true : false);
        }
        if (isSupportedRedLedStateInStatus()) {
            mIsRedLedOn = ((statusByte[0] & (0x01 << 7)) > 0) ? true : false;
        }

        consolePrintLn("\nStatus Response = \n" + UtilShimmer.byteToHexStringFormatted(statusByte[0])
                + "\t" + "IsDocked = " + isDocked()
                + "\t" + "IsSensing = " + mIsSensing
                + "\t" + "IsRtcSet = " + mIsRtcSet
                + "\t" + "IsSDLogging = " + isSDLogging()
                + "\t" + "IsStreaming = " + mIsStreaming
                + "\t" + "mIsSdError = " + isSDError()
                + "\t" + "mIsSdPresent = " + isSDPresent()
                + "\t" + "mIsRedLedOn = " + mIsRedLedOn);

        if (statusByte.length > 1) {
            setIsUsbPluggedIn(((statusByte[1] & (0x01 << 0)) > 0) ? true : false);

            consolePrintLn("\nStatus Response = \n" + UtilShimmer.byteToHexStringFormatted(statusByte[1])
                    + "\t" + "IsUsbPluggedIn = " + isUsbPluggedIn());

        }

        if (savedDockedState != isDocked()) {
            dockedStateChange();
        }
    }

    public boolean isSupportedRedLedStateInStatus() {
        return isThisVerCompatibleWith(FW_ID.LOGANDSTREAM, 0, 7, 10);
    }

    public boolean isSupportedRtcStateInStatus() {
        return isThisVerCompatibleWith(FW_ID.LOGANDSTREAM, 0, 7, 14);
    }

    public boolean isSupportedSdInfoInStatus() {
        return isThisVerCompatibleWith(FW_ID.LOGANDSTREAM, 0, 7, 12);
    }

    public boolean isSupportedUSBPluggedInStatus() {
        return isThisVerCompatibleWith(HW_ID.SHIMMER_3R, FW_ID.LOGANDSTREAM, 1, 0, 24);
    }

    protected boolean isSupportedInStreamCmds() {
        if ((getFirmwareIdentifier() == FW_ID.LOGANDSTREAM
                || isThisVerCompatibleWith(FW_ID.BTSTREAM, 0, 8, 1)) && getHardwareVersion() != HW_ID.SHIMMER_2R) {
            return true;
        }
        return false;
    }

    private byte[] convertStackToByteArray(Stack<Byte> b, int packetSize) {
        byte[] returnByte = new byte[packetSize];
        b.remove(0);
        for (int i = 0; i < packetSize; i++) {
            returnByte[packetSize - 1 - i] = (byte) b.pop();
        }
        return returnByte;
    }


    protected void initialize() {
        clearShimmerVersionObjectAndCreateSensorMaps();
        resetCurrentCrcMode();

        stopTimerReadStatus();
        stopTimerCheckForAckOrResp();
        setInstructionStackLock(false);

        dummyReadSamplingRate();

        readShimmerVersionNew();
    }

    public void initializeBoilerPlate() {
        readSamplingRate();
        readConfigByte0();
        readCalibrationParameters("Accelerometer");
        readCalibrationParameters("Magnetometer");
        readCalibrationParameters("Gyroscope");
        if (isSetupDeviceWhileConnecting() && getHardwareVersion() != 4) {
            writeAccelRange(getAccelRange());
            writeGSRRange(getGSRRange());
            writeShimmerAndSensorsSamplingRate(getSamplingRateShimmer());
            writeEnabledSensors(mSetEnabledSensors);
            setContinuousSync(mContinousSync);
        } else {
            inquiry();
        }
    }

    private void initializeShimmer2R() {
        if (mSendProgressReport) {
            operationPrepare();
            setBluetoothRadioState(BT_STATE.CONNECTING);
        }
        readSamplingRate();
        readGSRRange();
        readMagSamplingRate();
        writeBufferSize(1);
        readLEDCommand();
        readConfigByte0();
        readCalibrationParameters("All");
        if (isSetupDeviceWhileConnecting()) {
            setFixedConfigWhenConnecting();
            writeMagRange(getMagRange());
            writeAccelRange(getAccelRange());
            writeGSRRange(getGSRRange());
            writeShimmerAndSensorsSamplingRate(getSamplingRateShimmer());
            writeEnabledSensors(mSetEnabledSensors);
            setContinuousSync(mContinousSync);
        } else {
            if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {

            } else {
                readMagRange();
            }
            inquiry();
        }
        if (mSendProgressReport) {
            startOperation(BT_STATE.CONNECTING, getListofInstructions().size());

            setInstructionStackLock(false);
        }
    }

    private void initializeShimmer3R() {
        initializeShimmer3or3R(HW_ID.SHIMMER_3R);
    }

    private void initializeShimmer3() {
        initializeShimmer3or3R(HW_ID.SHIMMER_3);
    }

    private void initializeShimmer3or3R(int hardwareVersion) {
        setHardwareVersionAndCreateSensorMaps(hardwareVersion);

        setHaveAttemptedToReadConfig(true);

        if (mSendProgressReport) {
            operationPrepare();
            setBluetoothRadioState(BT_STATE.CONNECTING);
        }


        if (isBtCrcModeSupported()) {
            writeBtCommsCrcMode(DEFAULT_BT_CRC_MODE_IF_SUPPORTED);
        }

        if (isSetupDeviceWhileConnecting()) {
            if (mFixedShimmerConfigMode != null && mFixedShimmerConfigMode != FIXED_SHIMMER_CONFIG_MODE.NONE) {
                boolean triggerConfig = setFixedConfigWhenConnecting();
                if (triggerConfig) {
                    writeConfigBytes(false);
                }
            } else {
                if (this.mUseInfoMemConfigMethod && getFirmwareVersionCode() >= 6) {
                    writeConfigBytes(false);
                } else {
                    if (isThisVerCompatibleWith(HW_ID.SHIMMER_3, FW_ID.LOGANDSTREAM, 0, 5, 2)) {
                        writeShimmerUserAssignedName(getShimmerUserAssignedName());
                    }
                    if (mSetupEXG) {
                        writeEXGConfiguration();
                        mSetupEXG = false;
                    }
                    writeGSRRange(getGSRRange());
                    writeAccelRange(getAccelRange());
                    writeGyroRange(getGyroRange());
                    writeMagRange(getMagRange());
                    writeShimmerAndSensorsSamplingRate(getSamplingRateShimmer());
                    writeInternalExpPower(1);
                }
            }
        }

        if (this.mUseInfoMemConfigMethod && getFirmwareVersionCode() >= 6) {
            readConfigBytes();
            readPressureCalibrationCoefficients();
        } else {
            readSamplingRate();
            readMagRange();
            readAccelRange();
            readGyroRange();
            readAccelSamplingRate();
            readCalibrationParameters("All");
            readPressureCalibrationCoefficients();
            readEXGConfigurations();

            readDerivedChannelBytes();

            if (isThisVerCompatibleWith(HW_ID.SHIMMER_3, FW_ID.LOGANDSTREAM, 0, 5, 2)) {
                readTrial();
                readConfigTime();
                readShimmerName();
                readExperimentName();
            }
        }

        readLEDCommand();

        if (getShimmerVerObject().isSupportedBtStatusRequest()) {
            readStatusLogAndStream();
        }

        if (getShimmerVerObject().isSupportedBtBatteryRequest()) {
            readBattery();
        }

        if (getShimmerVerObject().isSupportedBtFwVerRequest()) {
            readBtFwVersion();
        }

        if (hardwareVersion == HW_ID.SHIMMER_3R || !isDocked()) {
            readCalibrationDump();
        }

        if (isSetupDeviceWhileConnecting()) {
            if (mFixedShimmerConfigMode != null && mFixedShimmerConfigMode != FIXED_SHIMMER_CONFIG_MODE.NONE) {
                writeEnabledSensors(mEnabledSensors);
            } else {
                writeEnabledSensors(mSetEnabledSensors);
            }
        } else {
            inquiry();
        }

        if (mSendProgressReport) {
            startOperation(BT_STATE.CONNECTING, getListofInstructions().size());

            setInstructionStackLock(false);
        }

        startTimerReadStatus();
        startTimerReadBattStatus();
    }

    @Override
    public void setFixedShimmerConfig(FIXED_SHIMMER_CONFIG_MODE fixedShimmerConfig) {
        super.setFixedShimmerConfig(fixedShimmerConfig);

        if (fixedShimmerConfig != null && fixedShimmerConfig != FIXED_SHIMMER_CONFIG_MODE.NONE) {
            setSetupDeviceWhileConnecting(true);
        }
    }


    public void setSendProgressReport(boolean state) {
        mSendProgressReport = state;
    }

    @Override
    public void operationPrepare() {
        stopAllTimers();

        clearAllInstructions();
        while (getListofInstructions().size() > 0)
            ;
        setInstructionStackLock(true);
        mOperationUnderway = true;
    }

    public void operationWaitForFinish() {
        setInstructionStackLock(false);
        while (getListofInstructions().size() > 0)
            ;
    }

    @Override
    public void operationStart(BT_STATE btState) {
        startOperation(btState, getListofInstructions().size());
        setInstructionStackLock(false);
    }

    public void operationFinished() {
        mOperationUnderway = false;
    }


    @Override
    public void startStreaming() throws ShimmerException {
        super.startStreaming();

        initialiseStreaming();

        mByteArrayOutputStream.reset();
        mListofPCTimeStamps.clear();
        writeInstruction(START_STREAMING_COMMAND);
    }

    @Override
    public void startDataLogAndStreaming() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            initialiseStreaming();

            writeInstruction(START_SDBT_COMMAND);
        }
    }

    private void initialiseStreaming() {
        stopTimerReadStatus();

        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM && getFirmwareVersionCode() >= 6) {
            if (!isSDLogging()) {
                readRealTimeClock();
            }
        }
        determineCalibrationParamsForIMU();
        initaliseDataProcessing();

        systemTimestampPlot.reset();

        resetCalibratedTimeStamp();
        mSync = true;
    }

    @Override
    public void startSDLogging() {
        if (!isSDLogging()) {
            writeRealTimeClock();
        }
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM && getFirmwareVersionCode() >= 6) {
            writeInstruction(START_LOGGING_ONLY_COMMAND);
        }
    }

    @Override
    public void stopSDLogging() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM && getFirmwareVersionCode() >= 6) {
            writeInstruction(STOP_LOGGING_ONLY_COMMAND);
        }
    }

    @Override
    public void stopStreaming() {
        if (isStreaming()) {
            writeInstruction(STOP_STREAMING_COMMAND);

            stopTimerReadStatus();
        }
    }


    @Override
    public void stopStreamingAndLogging() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(STOP_SDBT_COMMAND);
            stopTimerReadStatus();
        }
    }


    public void stopAllTimers() {
        stopTimerReadStatus();
        stopTimerCheckAlive();
        stopTimerCheckForAckOrResp();
        stopTimerReadBattStatus();
        stopTimerConnectingTimeout();
    }

    public void stopTimerCheckForAckOrResp() {
        if (mTimerCheckForAckOrResp != null) {
            try {
                mTimerCheckForAckOrResp.cancel();
                mTimerCheckForAckOrResp.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerCheckForAckOrResp = null;
        }
    }

    public synchronized void startTimerCheckForAckOrResp(int seconds) {
        if (mTimerCheckForAckOrResp != null) {
            try {
                mTimerCheckForAckOrResp.cancel();
                mTimerCheckForAckOrResp.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerCheckForAckOrResp = null;
        }
        printLogDataForDebugging("Waiting for ack/response for command:\t" + btCommandToString(mCurrentCommand));
        mTimerCheckForAckOrResp = new Timer("Shimmer_" + getMacIdParsed() + "_TimerCheckForResp");
        mTimerCheckForAckOrResp.schedule(new checkForAckOrRespTask(), seconds * 1000);
    }

    private void killConnection() {
        printLogDataForDebugging("Killing Connection");
        stop();
    }


    public void startTimerReadStatus() {
        if (isSupportedInStreamCmds()) {
            if (mTimerReadStatus == null) {
                mTimerReadStatus = new Timer("Shimmer_" + getMacIdParsed() + "_TimerReadStatus");
            }
            mTimerReadStatus.schedule(new readStatusTask(), 0, LiteProtocol.TIMER_READ_STATUS_PERIOD);
        }
    }

    public void stopTimerReadStatus() {
        if (mTimerReadStatus != null) {
            try {
                mTimerReadStatus.cancel();
                mTimerReadStatus.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerReadStatus = null;
        }
    }

    public void startTimerCheckIfAlive() {
        if (mCheckIfConnectionisAlive) {
            if (mTimerCheckAlive == null) {
                mTimerCheckAlive = new Timer("Shimmer_" + getMacIdParsed() + "_TimerCheckAlive");
            }
            if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM
                    || getFirmwareIdentifier() == FW_ID.BTSTREAM) {
                mTimerCheckAlive.schedule(new checkIfAliveTask(), LiteProtocol.TIMER_CHECK_ALIVE_PERIOD, LiteProtocol.TIMER_CHECK_ALIVE_PERIOD);
            }
        }
    }

    public void stopTimerCheckAlive() {
        if (mTimerCheckAlive != null) {
            try {
                mTimerCheckAlive.cancel();
                mTimerCheckAlive.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerCheckAlive = null;
        }
    }

    public void startTimerReadBattStatus() {
        if (((getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) && (getFirmwareVersionCode() >= 6))
                || isThisVerCompatibleWith(FW_ID.BTSTREAM, 0, 8, 1)) {
            if (mTimerReadBattStatus == null) {
                mTimerReadBattStatus = new Timer("Shimmer_" + getMacIdParsed() + "_TimerBattStatus");
            }
            mTimerReadBattStatus.schedule(new readBattStatusTask(), LiteProtocol.TIMER_READ_BATT_STATUS_PERIOD, LiteProtocol.TIMER_READ_BATT_STATUS_PERIOD);
        }
    }

    public void stopTimerReadBattStatus() {
        if (mTimerReadBattStatus != null) {
            try {
                mTimerReadBattStatus.cancel();
                mTimerReadBattStatus.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerReadBattStatus = null;
        }
    }

    public void restartTimersIfNull() {
        if (mTimerCheckAlive == null && mTimerReadStatus == null && mTimerReadBattStatus == null) {
            startTimerCheckIfAlive();
            startTimerReadStatus();
            startTimerReadBattStatus();
        }
    }

    public void startTimerConnectingTimeout() {
        mTimerConnecting = new Timer();
        mTimerConnecting.schedule(new connectingTimeoutTask(), LiteProtocol.TIMER_CONNECTING_TIMEOUT);
        consolePrintLn("Started connecting timer...");
    }

    public void stopTimerConnectingTimeout() {
        if (mTimerConnecting != null) {
            consolePrintLn("Stopped connecting timer...");
            try {
                mTimerConnecting.cancel();
                mTimerConnecting.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerConnecting = null;
        }
    }

    public void stopTimerCheckForSerialPortClear() {
        if (mTimerCheckSerialPortClear != null) {
            try {
                mTimerCheckSerialPortClear.cancel();
                mTimerCheckSerialPortClear.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }

            mTimerCheckSerialPortClear = null;
        }
    }

    public synchronized void startTimerCheckForSerialPortClear() {
        if (mTimerCheckSerialPortClear != null) {
            try {
                mTimerCheckSerialPortClear.cancel();
                mTimerCheckSerialPortClear.purge();
            } catch (
                    NullPointerException npe) {
                npe.printStackTrace();
            }
            mTimerCheckSerialPortClear = null;
        }
        mSerialPortReadTimeout = false;
        printLogDataForDebugging("Waiting for serial port to clear");
        mTimerCheckSerialPortClear = new Timer("Shimmer_" + getMacIdParsed() + "_TimerCheckForSerialPortClear");
        mTimerCheckSerialPortClear.schedule(new checkForSerialPortClear(), 5000);
    }

    public void readFWVersion() {
        writeInstruction(GET_FW_VERSION_COMMAND);
    }

    @Deprecated
    public void readShimmerVersionDeprecated() {
        writeInstruction(GET_SHIMMER_VERSION_COMMAND);
    }

    public void readShimmerVersionNew() {

        writeInstruction(GET_SHIMMER_VERSION_COMMAND_NEW);
    }

    public void readPressureCalibrationCoefficients() {
        if (getHardwareVersion() == HW_ID.SHIMMER_3) {
            if (getFirmwareVersionCode() >= 9) {
                writeInstruction(GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND);
            } else if (getFirmwareVersionCode() > 1) {
                if (isSupportedBmp280()) {
                    writeInstruction(GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND);
                } else {
                    writeInstruction(GET_BMP180_CALIBRATION_COEFFICIENTS_COMMAND);
                }
            }
        } else if (getHardwareVersion() == HW_ID.SHIMMER_3R) {
            writeInstruction(GET_PRESSURE_CALIBRATION_COEFFICIENTS_COMMAND);
        }
    }

    public void readBattery() {
        writeInstruction(GET_VBATT_COMMAND);
    }

    public void readExpansionBoardByBytes(int numBytes, int offset) {
        if (getFirmwareVersionCode() >= 5) {
            if (numBytes + offset <= 256) {
                numBytesToReadFromExpBoard = numBytes;
                writeInstruction(new byte[]{GET_DAUGHTER_CARD_ID_COMMAND, (byte) numBytes, (byte) offset});
            }
        }
    }


    public void readExpansionBoardID() {
        if (getFirmwareVersionCode() >= 5) {
            numBytesToReadFromExpBoard = 3;
            int offset = 0;
            writeInstruction(new byte[]{GET_DAUGHTER_CARD_ID_COMMAND, (byte) numBytesToReadFromExpBoard, (byte) offset});
        }
    }

    public void readDirectoryName() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_DIR_COMMAND);
        }
    }

    public void readStatusLogAndStream() {
        if (isSupportedInStreamCmds()) {
            writeInstruction(GET_STATUS_COMMAND);
            consolePrintLn("GET_STATUS_COMMAND Instruction added to the list");
        }
    }

    public void readBtFwVersion() {
        if (getShimmerVerObject().isSupportedBtFwVerRequest()) {
            writeInstruction(GET_BT_FW_VERSION_STR_COMMAND);
            consolePrintLn("GET_BT_FW_VERSION_STR_COMMAND Instruction added to the list");
        }
    }

    public void writeGyroSamplingRate(int rate) {
        if (isShimmerGen3or3R()) {
            mTempIntValue = rate;
            writeInstruction(new byte[]{SET_MPU9150_SAMPLING_RATE_COMMAND, (byte) rate});
        }
    }

    /**
     * This method has been deprecated, and we recommend users to use either {@link com.shimmerresearch.driver.ShimmerDevice#setSensorEnabledState(int sensorId, boolean state)}
     * <br> or {@link com.shimmerresearch.driver.ShimmerDevice#setSensorIdsEnabled(Integer[] sensorIds)}.
     * <p>
     * The enabled sensors that are set in the ShimmerDevice class can then be written to the physical device by the following methods:<br>
     * A) Clone device - Create a virtual representation of a Shimmer device by calling deepClone(). Update the sensor states and/or other desired settings on the clone device.
     * Call {@link AssembleShimmerConfig} to generate a Shimmer config for the clone. Then call configureShimmer(clone) from ShimmerBluetoothManager to write the clone settings to the physical device.
     * <p> B) Call {@link #writeConfigBytes()} after changing the sensor states.
     * <p> <br>
     * Method documentation:<br>
     * Transmits a command to the Shimmer device to enable the sensors. To
     * enable multiple sensors an or operator should be used (e.g.
     * writeEnabledSensors(SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG)). Command should
     * not be used consecutively. Valid values are SENSOR_ACCEL, SENSOR_GYRO,
     * SENSOR_MAG, SENSOR_ECG, SENSOR_EMG, SENSOR_GSR, SENSOR_EXP_BOARD_A7,
     * SENSOR_EXP_BOARD_A0, SENSOR_BRIDGE_AMP and SENSOR_HEART. SENSOR_BATT
     *
     * @param enabledSensors e.g SENSOR_ACCEL|SENSOR_GYRO|SENSOR_MAG
     */
    @Deprecated
    public void writeEnabledSensors(long enabledSensors) {

        if (getFirmwareVersionCode() <= 1) {
            if (!sensorConflictCheck(enabledSensors)) {
                return;
            } else {
                enabledSensors = generateSensorBitmapForHardwareControl(enabledSensors);
            }
        }

        tempEnabledSensors = enabledSensors;

        byte secondByte = (byte) ((enabledSensors & 0xFF00) >> 8);
        byte firstByte = (byte) (enabledSensors & 0xFF);

        if (isShimmerGen3or3R()) {
            byte thirdByte = (byte) ((enabledSensors & 0xFF0000) >> 16);
            writeInstruction(new byte[]{SET_SENSORS_COMMAND, (byte) firstByte, (byte) secondByte, (byte) thirdByte});
        } else {
            writeInstruction(new byte[]{SET_SENSORS_COMMAND, (byte) firstByte, (byte) secondByte});
        }
        inquiry();
    }

    public void writePressureResolution(int setting) {
        if (getHardwareVersion() == HW_ID.SHIMMER_3) {
            writeInstruction(new byte[]{SET_BMP180_PRES_RESOLUTION_COMMAND, (byte) setting});
        }
    }

    public void writeTestConnectionCommand() {
        if (getFirmwareVersionCode() >= 6) {
            writeInstruction(TEST_CONNECTION_COMMAND);
        }
    }

    public void writePMux(int setBit) {
        writeInstruction(new byte[]{SET_PMUX_COMMAND, (byte) setBit});
    }

    public void writeInternalExpPower(int setBit) {
        if ((getHardwareVersion() == HW_ID.SHIMMER_3R) || (getHardwareVersion() == HW_ID.SHIMMER_3 && getFirmwareVersionCode() >= 2)) {
            writeInstruction(new byte[]{SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, (byte) setBit});
        } else {

        }
    }


    public void writeFiveVoltReg(int setBit) {
        writeInstruction(new byte[]{SET_5V_REGULATOR_COMMAND, (byte) setBit});
    }

    public void readMagSamplingRate() {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(GET_MAG_SAMPLING_RATE_COMMAND);
        }
    }

    public void writeMagSamplingRate(int rate) {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            mTempIntValue = rate;
            writeInstruction(new byte[]{SET_MAG_SAMPLING_RATE_COMMAND, (byte) rate});
        }
    }

    public void readAccelSamplingRate() {
        if (isShimmerGen3or3R()) {
            writeInstruction(GET_ACCEL_SAMPLING_RATE_COMMAND);
        }
    }

    public void writeAccelSamplingRate(int rate) {
        if (isShimmerGen3or3R()) {
            mTempIntValue = rate;
            writeInstruction(new byte[]{SET_ACCEL_SAMPLING_RATE_COMMAND, (byte) rate});
        }
    }

    /*public void writeConfigGyroTempVref(int setBit) {
    while(getInstructionStatus()==false) {};
        if(setBit==1) {
            mTempByteValue=(byte) (mConfigByte0|32);
        }
        else if(setBit==0) {
            mTempByteValue=(byte)(mConfigByte0 & 223);
        }
        mCurrentCommand=SET_GYRO_TEMP_VREF_COMMAND;
        write(new byte[]{SET_GYRO_TEMP_VREF_COMMAND,(byte) setBit});
        mWaitForAck=true;
        mTransactionCompleted=false;
        responseTimer(ACK_TIMER_DURATION);
}*/
    public void readAccelRange() {
        writeInstruction(GET_ACCEL_SENSITIVITY_COMMAND);
    }

    public boolean startInShimmerTest(TEST_MODE mode) {

        stopTimerCheckAlive();
        TaskCompletionSource tcs = new TaskCompletionSource<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        setListener(new ByteCommunicationListener() {

            @Override
            public void eventConnected() {

            }

            @Override
            public void eventDisconnected() {

            }

            @Override
            public void eventNewBytesReceived(byte[] rxBytes) {

                System.out.println("Test : " + UtilShimmer.bytesToHexString(rxBytes));
                try {
                    outputStream.write(rxBytes);
                    String result = new String(outputStream.toByteArray());
                    System.out.println(result);
                    if (mStringTestListener != null) {
                        mStringTestListener.eventNewStringRx(result);
                    }
                    if (result.contains(AbstractCommsProtocolWired.TEST_ENDING)) {
                        tcs.setResult(true);
                    }
                } catch (
                        IOException e) {
                    e.printStackTrace();
                    tcs.setResult(false);
                }


            }

        });
        writeInstruction(new byte[]{SET_TEST, mode.getByteInstruction()});


        boolean completed = false;
        try {
            completed = tcs.getTask().waitForCompletion(AbstractCommsProtocolWired.TIMEOUT_IN_SHIMMER_TEST, TimeUnit.SECONDS);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
            InShimmerTest = false;
            startTimerCheckIfAlive();
            return false;
        }
        startTimerCheckIfAlive();
        InShimmerTest = false;
        return completed;
    }


    public void addTestStringListener(StringListener stringTestListener) {
        mStringTestListener = stringTestListener;
    }

    public void setListener(ByteCommunicationListener listener) {
        mTestByteListener = listener;
    }

    public void writeAccelRange(int range) {
        writeInstruction(new byte[]{SET_ACCEL_SENSITIVITY_COMMAND, (byte) range});
        setAccelRange((int) range);
    }

    public void readDerivedChannelBytes() {
        if (getFirmwareVersionCode() >= 6) {
            writeInstruction(GET_DERIVED_CHANNEL_BYTES);
        }
    }

    public void writeDerivedChannelBytes(byte[] channel) {
        if (getFirmwareVersionCode() >= 6) {
            if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                writeInstruction(new byte[]{SET_DERIVED_CHANNEL_BYTES, channel[0], channel[1], channel[2],
                        channel[3], channel[4], channel[5], channel[6], channel[7]});
            } else {
                writeInstruction(new byte[]{SET_DERIVED_CHANNEL_BYTES, channel[0], channel[1], channel[2]});
            }
        }
    }

    public void writeDerivedChannels(long channels) {
        if (getFirmwareVersionCode() >= 6) {
            if (mShimmerVerObject.isSupportedEightByteDerivedSensors()) {
                byte[] channel = new byte[8];
                channel[7] = (byte) ((channels >> (8 * 7)) & 0xFF);
                channel[6] = (byte) ((channels >> (8 * 6)) & 0xFF);
                channel[5] = (byte) ((channels >> (8 * 5)) & 0xFF);
                channel[4] = (byte) ((channels >> (8 * 4)) & 0xFF);
                channel[3] = (byte) ((channels >> (8 * 3)) & 0xFF);
                channel[2] = (byte) ((channels >> (8 * 2)) & 0xFF);
                channel[1] = (byte) ((channels >> (8 * 1)) & 0xFF);
                channel[0] = (byte) ((channels >> (8 * 0)) & 0xFF);
                writeInstruction(new byte[]{SET_DERIVED_CHANNEL_BYTES, channel[0], channel[1], channel[2], channel[3], channel[4], channel[5], channel[6], channel[7]});
            } else {
                byte[] channel = new byte[3];
                channel[2] = (byte) ((channels >> 16) & 0xFF);
                channel[1] = (byte) ((channels >> 8) & 0xFF);
                channel[0] = (byte) ((channels >> 0) & 0xFF);
                writeInstruction(new byte[]{SET_DERIVED_CHANNEL_BYTES, channel[0], channel[1], channel[2]});
            }
        }
    }

    public void readTrial() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_TRIAL_CONFIG_COMMAND);
        }
    }

    public void writeTrial() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] trial_config_byte = combineTrialConfig();
            byte[] tosend = new byte[4];
            tosend[0] = SET_TRIAL_CONFIG_COMMAND;
            tosend[1] = trial_config_byte[0];
            tosend[2] = trial_config_byte[1];
            tosend[3] = (byte) getSyncBroadcastInterval();
            writeInstruction(tosend);
        }
    }

    public void fillTrialShimmer3(byte[] packet) {
        SplitTrialConfig(packet[0] + (packet[1] << 8));
        setSyncBroadcastInterval((int) packet[2]);
    }

    public void SplitTrialConfig(int val) {
        if (isSupportedErrorLedControl()) {
            setShowErrorLedsSd(((val >> 0) & 0x01) == 1);
        }
        setMasterShimmer(((val >> 1) & 0x01) == 1);
        mSync = ((val >> 2) & 0x01) == 1;
        if (isSupportedErrorLedControl()) {
            setShowErrorLedsRtc(((val >> 4) & 0x01) == 1);
        }
        setButtonStart(((val >> 5) & 0x01) == 1);
        setDisableBluetooth(((val >> 3) & 0x01) == 1);
        setInternalExpPower(((val >> 11) & 0x01) == 1);
        setTCXO(((val >> 12) & 0x01) == 1);
        setSingleTouch(((val >> 15) & 0x01) == 1);
    }

    public byte[] combineTrialConfig() {
        short trialConfig = (short) (
                (((isMasterShimmer() ? 1 : 0) & 0x01) << 1)
                        + (((mSync ? 1 : 0) & 0x01) << 2)
                        + (((isButtonStart() ? 1 : 0) & 0x01) << 5)
                        + (((true ? 1 : 0) & 0x01) << 10)
                        + (((isInternalExpPower() ? 1 : 0) & 0x01) << 11)
                        + (((isTCXO() ? 1 : 0) & 0x01) << 12)
                        + (((isSingleTouch() ? 1 : 0) & 0x01) << 15)
        );

        if (isSupportedErrorLedControl()) {
            trialConfig += +(((isShowErrorLedsSd() ? 1 : 0) & 0x01) << 0);
            trialConfig += +(((isShowErrorLedsRtc() ? 1 : 0) & 0x01) << 4);
        }

        byte[] ret = new byte[2];
        ret[0] = (byte) (trialConfig & 0xff);
        ret[1] = (byte) ((trialConfig >> 8) & 0xff);
        return ret;
    }

    public void readConfigTime() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_CONFIGTIME_COMMAND);
        }
    }

    public void writeConfigTime(String time) {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] toSendTime = time.getBytes();
            byte[] toSend = new byte[2 + toSendTime.length];
            toSend[0] = SET_CONFIGTIME_COMMAND;
            toSend[1] = (byte) toSendTime.length;
            System.arraycopy(toSendTime, 0, toSend, 2, toSendTime.length);
            writeInstruction(toSend);
        }
    }

    public void writeConfigTime(long time) {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            String timeString = Long.toString(time);
            byte[] toSendTime = timeString.getBytes();
            byte[] toSend = new byte[2 + toSendTime.length];
            toSend[0] = SET_CONFIGTIME_COMMAND;
            toSend[1] = (byte) toSendTime.length;
            System.arraycopy(toSendTime, 0, toSend, 2, toSendTime.length);
            writeInstruction(toSend);
        }
    }

    public void readShimmerName() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_SHIMMERNAME_COMMAND);
        }
    }

    public void writeShimmerName() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeShimmerUserAssignedName(getShimmerUserAssignedName());
        }
    }

    public void writeShimmerUserAssignedName(String name) {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] toSendName = name.getBytes();
            byte[] toSend = new byte[2 + toSendName.length];
            toSend[0] = SET_SHIMMERNAME_COMMAND;
            toSend[1] = (byte) toSendName.length;
            System.arraycopy(toSendName, 0, toSend, 2, toSendName.length);
            writeInstruction(toSend);
        }
    }

    public void readCenter() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_CENTER_COMMAND);
        }
    }

    public void writeCenter(String center) {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] toSendCenter = center.getBytes();
            byte[] toSend = new byte[2 + toSendCenter.length];
            toSend[0] = SET_CENTER_COMMAND;
            toSend[1] = (byte) toSendCenter.length;
            System.arraycopy(toSendCenter, 0, toSend, 2, toSendCenter.length);
            writeInstruction(toSend);
        }
    }

    public void readExperimentName() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_EXPID_COMMAND);
        }
    }

    public void writeExperimentName(String name) {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] toSendName = name.getBytes();
            byte[] toSend = new byte[2 + toSendName.length];
            toSend[0] = SET_EXPID_COMMAND;
            toSend[1] = (byte) toSendName.length;
            System.arraycopy(toSendName, 0, toSend, 2, toSendName.length);
            writeInstruction(toSend);
        }
    }

    public void readRealTimeClock() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            writeInstruction(GET_RWC_COMMAND);
        }
    }

    public void writeRealTimeClock() {
        if (getFirmwareIdentifier() == FW_ID.LOGANDSTREAM) {
            byte[] bytearraycommand = new byte[9];
            bytearraycommand[0] = SET_RWC_COMMAND;
            writeInstruction(bytearraycommand);
        }
    }

    public void readGyroRange() {
        writeInstruction(GET_MPU9150_GYRO_RANGE_COMMAND);
    }

    public void writeGyroRange(int range) {
        if (isShimmerGen3or3R()) {
            writeInstruction(new byte[]{SET_MPU9150_GYRO_RANGE_COMMAND, (byte) range});
            setGyroRange((int) range);
        }
    }

    public void readSamplingRate() {
        writeInstruction(GET_SAMPLING_RATE_COMMAND);
    }

    protected void dummyReadSamplingRate() {
        mDummyRead = true;
        mDummyReadStarted = false;

        while (!mDummyReadStarted) {
            threadSleep(DUMMY_READ_WAIT_TIME_MS.START);
        }
    }

    public void writeShimmerAndSensorsSamplingRate(double rate) {
        if (mIsInitialised || isSetupDeviceWhileConnecting()) {
            setShimmerAndSensorsSamplingRate(rate);
            if (getHardwareVersion() == HW_ID.SHIMMER_2 || getHardwareVersion() == HW_ID.SHIMMER_2R) {

                writeMagSamplingRate(getMagRate());

                int samplingByteValue = (int) (1024 / getSamplingRateShimmer());
                writeInstruction(new byte[]{SET_SAMPLING_RATE_COMMAND, (byte) Math.rint(samplingByteValue), 0x00});
            } else if (isShimmerGen3or3R()) {

                writeMagSamplingRate(getMagRate());
                try {
                    writeAccelSamplingRate(getWRAccelRate());
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
                try {
                    writeGyroSamplingRate(getGyroRate());
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
                writeExgSamplingRate(rate);

                byte[] buf = convertSamplingRateFreqToBytes(getSamplingRateShimmer(), getSamplingClockFreq());


                int samplingByteValue;
                if ((Math.ceil(getSamplingClockFreq() / getSamplingRateShimmer()) - getSamplingClockFreq() / getSamplingRateShimmer()) < 0.05) {
                    samplingByteValue = (int) Math.ceil(getSamplingClockFreq() / getSamplingRateShimmer());
                } else {
                    samplingByteValue = (int) Math.floor(getSamplingClockFreq() / getSamplingRateShimmer());
                }

                writeInstruction(new byte[]{SET_SAMPLING_RATE_COMMAND, (byte) (samplingByteValue & 0xFF), (byte) ((samplingByteValue >> 8) & 0xFF)});
            }
        }
    }

    private void writeExgSamplingRate(double rate) {
        if (rate <= 125) {
            writeEXGRateSetting(0);
        } else if (rate <= 250) {
            writeEXGRateSetting(1);
        } else if (rate <= 500) {
            writeEXGRateSetting(2);
        } else if (rate <= 1000) {
            writeEXGRateSetting(3);
        } else {
            writeEXGRateSetting(4);
        }
    }

    public void readEXGConfigurations() {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            readEXGConfigurations(EXG_CHIP_INDEX.CHIP1);
            readEXGConfigurations(EXG_CHIP_INDEX.CHIP2);
        }
    }

    public void readEXGConfigurations(EXG_CHIP_INDEX chipID) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            writeInstruction(new byte[]{GET_EXG_REGS_COMMAND, (byte) (chipID.ordinal()), 0, 10});
        }
    }

    public void writeEXGLeadOffComparatorTreshold(int treshold) {
        if (getFirmwareVersionCode() > 2) {
            if (treshold >= 0 && treshold <= 7) {
                setEXGLeadOffComparatorTreshold(treshold);
                writeEXGConfiguration();
            }
        }
    }

    public void writeEXGLeadOffDetectionCurrent(int leadOffCurrent) {
        if (getFirmwareVersionCode() > 2) {
            if (leadOffCurrent >= 0 && leadOffCurrent <= 3) {
                setEXGLeadOffDetectionCurrent(leadOffCurrent);
                writeEXGConfiguration();
            }
        }
    }

    public void writeEXGLeadOffDetectionMode(int detectionMode) {

        if (getFirmwareVersionCode() > 2) {
            if (detectionMode >= 0 && detectionMode <= 2) {
                setEXGLeadOffCurrentMode(detectionMode);
                writeEXGConfiguration();
            }
        }
    }

    public void writeEXGReferenceElectrode(int referenceElectrode) {
        if (getFirmwareVersionCode() > 2) {
            if (referenceElectrode >= 0 && referenceElectrode <= 15) {
                setEXGReferenceElectrode(referenceElectrode);
                writeEXGConfiguration(getEXG1RegisterArray(), EXG_CHIP_INDEX.CHIP1);
            }
        }
    }

    public void writeEXGRateSetting(int rateSetting) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            if (rateSetting >= 0 && rateSetting <= 4) {
                setEXGRateSetting(rateSetting);
                writeEXGConfiguration();
            }
        }
    }

    public void writeEXGRateSetting(EXG_CHIP_INDEX chipID, int rateSetting) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            if (rateSetting >= 0 && rateSetting <= 4) {
                setEXGRateSetting(chipID, rateSetting);
                if (chipID == EXG_CHIP_INDEX.CHIP1) {
                    writeEXGConfiguration(getEXG1RegisterArray(), chipID);
                } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
                    writeEXGConfiguration(getEXG2RegisterArray(), chipID);
                }
            }
        }
    }

    public void writeEXGGainSetting(int gainSetting) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            if (gainSetting >= 0 && gainSetting <= 6) {
                setExGGainSetting(gainSetting);
                writeEXGConfiguration();
            }
        }
    }

    public void writeEXGGainSetting(EXG_CHIP_INDEX chipID, int channel, int gainSetting) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            if (gainSetting >= 0 && gainSetting <= 6) {
                setExGGainSetting(chipID, channel, gainSetting);
                if (chipID == EXG_CHIP_INDEX.CHIP1) {
                    writeEXGConfiguration(getEXG1RegisterArray(), chipID);
                } else if (chipID == EXG_CHIP_INDEX.CHIP2) {
                    writeEXGConfiguration(getEXG2RegisterArray(), chipID);
                }
            }
        }
    }

    public void writeEXGConfiguration() {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            writeEXGConfiguration(getEXG1RegisterArray(), EXG_CHIP_INDEX.CHIP1);
            writeEXGConfiguration(getEXG2RegisterArray(), EXG_CHIP_INDEX.CHIP2);
        }
    }

    public void writeEXGConfiguration(byte[] reg, EXG_CHIP_INDEX chipID) {
        if ((getFirmwareVersionInternal() >= 8 && getFirmwareVersionCode() == 2) || getFirmwareVersionCode() > 2) {
            writeInstruction(new byte[]{SET_EXG_REGS_COMMAND, (byte) (chipID.ordinal()), 0, 10, reg[0], reg[1], reg[2], reg[3], reg[4], reg[5], reg[6], reg[7], reg[8], reg[9]});
        }
    }

    public void readGSRRange() {
        writeInstruction(GET_GSR_RANGE_COMMAND);
    }

    public void writeGSRRange(int range) {
        if (isShimmerGen3or3R()) {
            if (getFirmwareVersionCode() != 1 || getFirmwareVersionInternal() > 4) {
                writeInstruction(new byte[]{SET_GSR_RANGE_COMMAND, (byte) range});
            }
        } else {
            writeInstruction(new byte[]{SET_GSR_RANGE_COMMAND, (byte) range});
        }
    }

    public void readMagRange() {
        writeInstruction(GET_MAG_GAIN_COMMAND);
    }

    public void writeMagRange(int range) {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(new byte[]{SET_MAG_GAIN_COMMAND, (byte) range});
        }
    }

    public void readCalibrationParameters(String sensor) {
        if (!mIsInitialised) {
            if (getFirmwareVersionCode() == 1 && getFirmwareVersionInternal() == 0 && getHardwareVersion() != HW_ID.SHIMMER_3) {
            }
        }
        if (sensor.equals("Accelerometer")) {
            writeInstruction(GET_ACCEL_CALIBRATION_COMMAND);
        } else if (sensor.equals("Gyroscope")) {
            writeInstruction(GET_GYRO_CALIBRATION_COMMAND);
        } else if (sensor.equals("Magnetometer")) {
            writeInstruction(GET_MAG_CALIBRATION_COMMAND);
        } else if (sensor.equals("All")) {
            writeInstruction(GET_ALL_CALIBRATION_COMMAND);
        } else if (sensor.equals("ECG")) {
            writeInstruction(GET_ECG_CALIBRATION_COMMAND);
        } else if (sensor.equals("EMG")) {
            writeInstruction(GET_EMG_CALIBRATION_COMMAND);
        } else if (sensor.equals("Wide-Range Accelerometer")) {
            writeInstruction(GET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND);
        }
    }

    public void writeAccelCalibrationParameters(byte[] calibrationParameters) {
        cmdcalibrationParameters[0] = SET_ACCEL_CALIBRATION_COMMAND;
        System.arraycopy(calibrationParameters, 0, cmdcalibrationParameters, 1, 21);
        writeInstruction(cmdcalibrationParameters);
    }

    public void writeGyroCalibrationParameters(byte[] calibrationParameters) {
        cmdcalibrationParameters[0] = SET_GYRO_CALIBRATION_COMMAND;
        System.arraycopy(calibrationParameters, 0, cmdcalibrationParameters, 1, 21);
        writeInstruction(cmdcalibrationParameters);
    }

    public void writeMagCalibrationParameters(byte[] calibrationParameters) {
        cmdcalibrationParameters[0] = SET_MAG_CALIBRATION_COMMAND;
        System.arraycopy(calibrationParameters, 0, cmdcalibrationParameters, 1, 21);
        writeInstruction(cmdcalibrationParameters);
    }

    public void writeWRAccelCalibrationParameters(byte[] calibrationParameters) {
        if (isShimmerGen3or3R()) {
            cmdcalibrationParameters[0] = SET_LSM303DLHC_ACCEL_CALIBRATION_COMMAND;
            System.arraycopy(calibrationParameters, 0, cmdcalibrationParameters, 1, 21);
            writeInstruction(cmdcalibrationParameters);
        }
    }

    public void readECGCalibrationParameters() {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(GET_ECG_CALIBRATION_COMMAND);
        }
    }

    public void writeECGCalibrationParameters(int offsetrall, int gainrall, int offsetlall, int gainlall) {
        byte[] data = new byte[8];
        data[0] = (byte) ((offsetlall >> 8) & 0xFF);
        data[1] = (byte) ((offsetlall) & 0xFF);
        data[2] = (byte) ((gainlall >> 8) & 0xFF);
        data[3] = (byte) ((gainlall) & 0xFF);
        data[4] = (byte) ((offsetrall >> 8) & 0xFF);
        data[5] = (byte) ((offsetrall) & 0xFF);
        data[6] = (byte) ((gainrall >> 8) & 0xFF);
        data[7] = (byte) ((gainrall) & 0xFF);
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(new byte[]{SET_ECG_CALIBRATION_COMMAND, data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]});
        }
    }

    public void readEMGCalibrationParameters() {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(GET_EMG_CALIBRATION_COMMAND);
        }
    }

    public void writeEMGCalibrationParameters(int offset, int gain) {
        byte[] data = new byte[4];
        data[0] = (byte) ((offset >> 8) & 0xFF);
        data[1] = (byte) ((offset) & 0xFF);
        data[2] = (byte) ((gain >> 8) & 0xFF);
        data[3] = (byte) ((gain) & 0xFF);
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(new byte[]{SET_EMG_CALIBRATION_COMMAND, data[0], data[1], data[2], data[3]});
        }
    }

    public void readBaudRate() {
        if (getFirmwareVersionCode() >= 5) {
            writeInstruction(GET_BAUD_RATE_COMMAND);
        }
    }

    public void writeBaudRate() {
        writeBaudRate(mBluetoothBaudRate);
    }

    public void writeBaudRate(int value) {
        if (getFirmwareVersionCode() >= 5) {
            if (value >= 0 && value <= 10) {
                mBluetoothBaudRate = value;
                writeInstruction(new byte[]{SET_BAUD_RATE_COMMAND, (byte) value});
                delayForBtResponse(200);
                this.reconnect();
            }
        }
    }

    public void readConfigByte0() {
        writeInstruction(GET_CONFIG_BYTE0_COMMAND);
    }

    public void writeConfigByte0(byte configByte0) {
        writeInstruction(new byte[]{SET_CONFIG_BYTE0_COMMAND, (byte) configByte0});
    }

    public void readBufferSize() {
        writeInstruction(GET_BUFFER_SIZE_COMMAND);
    }

    public void writeBufferSize(int size) {
        writeInstruction(new byte[]{SET_BUFFER_SIZE_COMMAND, (byte) size});
    }

    public void readLEDCommand() {
        writeInstruction(GET_BLINK_LED);
    }

    public void writeLEDCommand(int command) {
        if (isThisVerCompatibleWith(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0)) {
        } else {
            writeInstruction(new byte[]{SET_BLINK_LED, (byte) command});
        }
    }

    public void readCalibrationDump() {
        if (this.getFirmwareVersionCode() >= 7) {
            readCalibrationDump(0, 128);
        }
    }

    private void readCalibrationDump(int address, int size) {
        if (this.getFirmwareVersionCode() >= 7) {
            readMem(GET_CALIB_DUMP_COMMAND, address, size, MAX_CALIB_DUMP_MAX);
        }
    }

    public void rePioritiseReadCalibDumpInstructions() {
        List<byte[]> listOfInstructions = new ArrayList<byte[]>();

        Iterator<byte[]> iterator = mListofInstructions.iterator();
        while (iterator.hasNext()) {
            byte[] instruction = iterator.next();
            if (instruction[0] == (byte) GET_CALIB_DUMP_COMMAND) {
                listOfInstructions.add(instruction);
                iterator.remove();
            }
        }

        if (listOfInstructions.size() > 0) {
            mListofInstructions.addAll(0, listOfInstructions);
        }
    }

    public void writeCalibrationDump() {
        writeCalibrationDump(mCalibBytes);
    }

    public void writeCalibrationDump(byte[] calibDump) {
        if (this.getFirmwareVersionCode() >= 7) {
            writeMem(SET_CALIB_DUMP_COMMAND, 0, calibDump, MAX_CALIB_DUMP_MAX);
            readCalibrationDump(0, calibDump.length);
        }
    }

    public void readConfigBytes() {
        if (this.getFirmwareVersionCode() >= 6) {
            int size = mConfigByteLayout.calculateConfigByteLength();
            readConfigBytes(mConfigByteLayout.MSP430_5XX_INFOMEM_D_ADDRESS, size, mConfigByteLayout.MSP430_5XX_INFOMEM_LAST_ADDRESS);
        }
    }

    public void readConfigBytes(int address, int size, int maxMemAddress) {
        readMem(GET_INFOMEM_COMMAND, address, size, maxMemAddress);
    }

    public void readMem(byte command, int address, int size, int maxMemAddress) {
        if (this.getFirmwareVersionCode() >= 6) {
            mMapOfMemReadDetails.put(command, new MemReadDetails(command, address, size, maxMemAddress));

            if (size > (maxMemAddress - address + 1)) {
            } else {
                int maxBytesRXed = 128;
                int numBytesRemaining = size;
                int currentPacketNumBytes;
                int currentBytePointer = 0;
                int currentStartAddr = address;

                while (numBytesRemaining > 0) {
                    if (numBytesRemaining > maxBytesRXed) {
                        currentPacketNumBytes = maxBytesRXed;
                    } else {
                        currentPacketNumBytes = numBytesRemaining;
                    }

                    byte[] rxBuf = new byte[]{};
                    readMemCommand(command, currentStartAddr, currentPacketNumBytes);

                    currentBytePointer += currentPacketNumBytes;
                    numBytesRemaining -= currentPacketNumBytes;
                    currentStartAddr += currentPacketNumBytes;
                }
            }
        }
    }

    protected void clearMemReadBuffers() {
        mMapOfMemReadDetails.clear();
        mMemBuffer = new byte[]{};
    }

    protected void clearMemReadBuffer(int command) {
        mMapOfMemReadDetails.remove(command);
        mMemBuffer = new byte[]{};
    }

    public void readMemCommand(int command, int address, int size) {
        if (this.getFirmwareVersionCode() >= 6) {
            byte[] memLengthToRead = new byte[]{(byte) size};
            byte[] memAddressToRead = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
            ArrayUtils.reverse(memAddressToRead);

            byte[] instructionBuffer = new byte[1 + memLengthToRead.length + memAddressToRead.length];
            instructionBuffer[0] = (byte) command;
            System.arraycopy(memLengthToRead, 0, instructionBuffer, 1, memLengthToRead.length);
            System.arraycopy(memAddressToRead, 0, instructionBuffer, 1 + memLengthToRead.length, memAddressToRead.length);

            writeInstruction(instructionBuffer);
        }
    }

    public void writeConfigBytes() {
        writeConfigBytes(true);
    }

    public void writeConfigBytes(boolean readBackAfter) {
        if (this.getFirmwareVersionCode() >= 6) {
            writeConfigBytes(mConfigByteLayout.MSP430_5XX_INFOMEM_D_ADDRESS, generateConfigBytesForWritingToShimmer(), mConfigByteLayout.MSP430_5XX_INFOMEM_LAST_ADDRESS, readBackAfter);
        }
    }

    public void writeConfigBytes(byte[] buf) {
        if (this.getFirmwareVersionCode() >= 6) {
            writeConfigBytes(mConfigByteLayout.MSP430_5XX_INFOMEM_D_ADDRESS, buf, mConfigByteLayout.MSP430_5XX_INFOMEM_LAST_ADDRESS, true);
        }
    }

    public void writeConfigBytes(int startAddress, byte[] buf, int maxMemAddress, boolean readBackAfter) {
        if (this.getFirmwareVersionCode() >= 6) {
            writeMem(SET_INFOMEM_COMMAND, startAddress, buf, maxMemAddress);

            if (readBackAfter) {
                readConfigBytes();
            }
        }
    }

    public void writeMem(int command, int startAddress, byte[] buf, int maxMemAddress) {
        this.mNumOfMemSetCmds = 0;

        if (this.getFirmwareVersionCode() >= 6) {
            int address = startAddress;
            if (buf.length > (maxMemAddress - address + 1)) {
            } else {
                int currentStartAddr = startAddress;
                int currentPacketNumBytes;
                int numBytesRemaining = buf.length;
                int currentBytePointer = 0;
                int maxPacketSize = 128;

                while (numBytesRemaining > 0) {
                    if (numBytesRemaining > maxPacketSize) {
                        currentPacketNumBytes = maxPacketSize;
                    } else {
                        currentPacketNumBytes = numBytesRemaining;
                    }

                    byte[] infoSegBuf = Arrays.copyOfRange(buf, currentBytePointer, currentBytePointer + currentPacketNumBytes);

                    writeMemCommand(command, currentStartAddr, infoSegBuf);
                    mNumOfMemSetCmds += 1;

                    currentStartAddr += currentPacketNumBytes;
                    numBytesRemaining -= currentPacketNumBytes;
                    currentBytePointer += currentPacketNumBytes;
                }
            }
        }
    }

    public void writeMemCommand(int command, int address, byte[] infoMemBytes) {
        if (this.getFirmwareVersionCode() >= 6) {
            byte[] memLengthToWrite = new byte[]{(byte) infoMemBytes.length};
            byte[] memAddressToWrite = ByteBuffer.allocate(2).putShort((short) (address & 0xFFFF)).array();
            ArrayUtils.reverse(memAddressToWrite);

            byte[] instructionBuffer = new byte[1 + memLengthToWrite.length + memAddressToWrite.length + infoMemBytes.length];
            instructionBuffer[0] = (byte) command;
            System.arraycopy(memLengthToWrite, 0, instructionBuffer, 1, memLengthToWrite.length);
            System.arraycopy(memAddressToWrite, 0, instructionBuffer, 1 + memLengthToWrite.length, memAddressToWrite.length);
            System.arraycopy(infoMemBytes, 0, instructionBuffer, 1 + memLengthToWrite.length + memAddressToWrite.length, infoMemBytes.length);

            writeInstruction(instructionBuffer);
        }
    }

    public boolean getInstructionStatus() {
        boolean instructionStatus = false;
        if (mTransactionCompleted) {
            instructionStatus = true;
        } else {
            instructionStatus = false;
        }
        return instructionStatus;
    }

    public int getPacketSize() {
        return mPacketSize;
    }

    public int getPacketSizeWithCrc() {
        return getPacketSize() + mBtCommsCrcModeCurrent.getNumCrcBytes();
    }

    public String getDirectoryName() {
        if (mDirectoryName != null)
            return mDirectoryName;
        else
            return "Directory not read yet";
    }

    public double getBattLimitWarning() {
        return mLowBattLimit;
    }

    public void setBattLimitWarning(double limit) {
        mLowBattLimit = limit;
    }

    public int getEXG1CH1GainValue() {

        int gain = -1;
        while (!getListofInstructions().isEmpty())
            ;
        int tmpGain = getExg1CH1GainValue();
        if (tmpGain == 1 || tmpGain == 2 || tmpGain == 3 || tmpGain == 4 || tmpGain == 6 || tmpGain == 8 || tmpGain == 12) {
            gain = tmpGain;
        }
        return gain;
    }

    public int getEXG1CH2GainValue() {

        int gain = -1;
        while (!getListofInstructions().isEmpty())
            ;
        int tmpGain = getExg1CH2GainValue();
        if (tmpGain == 1 || tmpGain == 2 || tmpGain == 3 || tmpGain == 4 || tmpGain == 6 || tmpGain == 8 || tmpGain == 12) {
            gain = tmpGain;
        }
        return gain;
    }

    public int getEXG2CH1GainValue() {

        int gain = -1;
        while (!getListofInstructions().isEmpty())
            ;
        int tmpGain = getExg2CH1GainValue();
        if (tmpGain == 1 || tmpGain == 2 || tmpGain == 3 || tmpGain == 4 || tmpGain == 6 || tmpGain == 8 || tmpGain == 12) {
            gain = tmpGain;
        }
        return gain;
    }


    public int getEXG2CH2GainValue() {

        int gain = -1;
        while (!getListofInstructions().isEmpty())
            ;
        int tmpGain = getExg2CH2GainValue();
        if (tmpGain == 1 || tmpGain == 2 || tmpGain == 3 || tmpGain == 4 || tmpGain == 6 || tmpGain == 8 || tmpGain == 12) {
            gain = tmpGain;
        }
        return gain;
    }

    @Deprecated
    //mContinousSync doesn't do anything
    public boolean getContinuousSync() {
        return mContinousSync;
    }

    @Deprecated
    //mContinousSync doesn't do anything
    public void setContinuousSync(boolean continousSync) {
        mContinousSync = continousSync;
    }

    public void enable3DOrientation(boolean enable) {
        set3DOrientation(enable);
    }

    public void enableLowPowerAccel(boolean enable) {
        enableHighResolutionMode(!enable);
        writeAccelSamplingRate(getLSM303DigitalAccelRate());
    }

    private void enableHighResolutionMode(boolean enable) {
        while (!getInstructionStatus()) {
        }
        ;

        if (getFirmwareVersionCode() == 1 && getFirmwareVersionInternal() == 0) {

        } else if (getHardwareVersion() == HW_ID.SHIMMER_3) {
            setLowPowerAccelWR(!enable);
            if (enable) {
                writeInstruction(new byte[]{SET_LSM303DLHC_ACCEL_HRMODE_COMMAND, (byte) 0x01});
                writeInstruction(new byte[]{SET_LSM303DLHC_ACCEL_LPMODE_COMMAND, (byte) 0x00});
            } else {
                writeInstruction(new byte[]{SET_LSM303DLHC_ACCEL_HRMODE_COMMAND, (byte) 0x00});
                writeInstruction(new byte[]{SET_LSM303DLHC_ACCEL_LPMODE_COMMAND, (byte) 0x01});
            }
        }
    }

    public void enableLowPowerGyro(boolean enable) {
        setLowPowerGyro(enable);
        writeGyroSamplingRate(getMPU9X50GyroAccelRate());
    }

    public void enableLowPowerMag(boolean enable) {
        setLowPowerMag(enable);
        writeMagSamplingRate(getMagRate());
    }

    public void enableDefaultECGConfiguration() {
        if (getHardwareVersion() == 3) {
            setDefaultECGConfiguration(getSamplingRateShimmer());
            writeEXGConfiguration();
        }
    }


    public void enableDefaultEMGConfiguration() {
        if (getHardwareVersion() == 3) {
            setDefaultEMGConfiguration(getSamplingRateShimmer());
            writeEXGConfiguration();
        }
    }

    public void enableEXGTestSignal() {
        if (getHardwareVersion() == 3) {
            setEXGTestSignal(getSamplingRateShimmer());
            writeEXGConfiguration();
        }
    }

    public boolean disableBtCommsCrc() {
        return writeBtCommsCrcMode(BT_CRC_MODE.OFF);
    }


    public boolean enableBtCommsOneByteCrc() {
        return writeBtCommsCrcMode(BT_CRC_MODE.ONE_BYTE_CRC);
    }

    public boolean enableBtCommsTwoByteCrc() {
        return writeBtCommsCrcMode(BT_CRC_MODE.TWO_BYTE_CRC);
    }

    public boolean writeBtCommsCrcMode(BT_CRC_MODE btCrcMode) {
        if (getFirmwareVersionCode() >= 8) {
            writeInstruction(new byte[]{SET_CRC_COMMAND, (byte) (btCrcMode.ordinal())});
            return true;
        }
        return false;
    }

    public BT_CRC_MODE getCurrentBtCommsCrcMode() {
        return mBtCommsCrcModeCurrent;
    }

    public void setCurrentBtCommsCrcMode(BT_CRC_MODE btCommsCrcMode) {
        mBtCommsCrcModeCurrent = btCommsCrcMode;
    }

    private void resetCurrentCrcMode() {
        setCurrentBtCommsCrcMode(BT_CRC_MODE.OFF);
    }

    public boolean isBtCrcModeSupported() {
        return getFirmwareVersionCode() >= 8;
    }


    private long disableBit(long number, long disablebitvalue) {
        if ((number & disablebitvalue) > 0) {
            number = number ^ disablebitvalue;
        }
        return number;
    }

    public void reconnect() {
        if (isConnected() && !mIsStreaming) {
            String msgReconnect = "Reconnecting the Shimmer...";
            sendStatusMSGtoUI(msgReconnect);
            stop();
            threadSleep(300);
            connect(mMyBluetoothAddress, "default");
            setUniqueID(this.mMacIdFromUart);
        }
    }

    public void inquiry() {
        writeInstruction(INQUIRY_COMMAND);
    }

    public long sensorConflictCheckandCorrection(long enabledSensors, long sensorToCheck) {

        if (getHardwareVersion() == HW_ID.SHIMMER_2R || getHardwareVersion() == HW_ID.SHIMMER_2) {
            if ((sensorToCheck & SENSOR_GYRO) > 0 || (sensorToCheck & SENSOR_MAG) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_ECG);
                enabledSensors = disableBit(enabledSensors, SENSOR_EMG);
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_BRIDGE_AMP) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_ECG);
                enabledSensors = disableBit(enabledSensors, SENSOR_EMG);
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_GYRO);
                enabledSensors = disableBit(enabledSensors, SENSOR_MAG);
            } else if ((sensorToCheck & SENSOR_GSR) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_ECG);
                enabledSensors = disableBit(enabledSensors, SENSOR_EMG);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
                enabledSensors = disableBit(enabledSensors, SENSOR_GYRO);
                enabledSensors = disableBit(enabledSensors, SENSOR_MAG);
            } else if ((sensorToCheck & SENSOR_ECG) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EMG);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
                enabledSensors = disableBit(enabledSensors, SENSOR_GYRO);
                enabledSensors = disableBit(enabledSensors, SENSOR_MAG);
            } else if ((sensorToCheck & SENSOR_EMG) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_ECG);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
                enabledSensors = disableBit(enabledSensors, SENSOR_GYRO);
                enabledSensors = disableBit(enabledSensors, SENSOR_MAG);
            } else if ((sensorToCheck & SENSOR_HEART) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_EXP_BOARD_A0);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXP_BOARD_A7);
            } else if ((sensorToCheck & SENSOR_EXP_BOARD_A0) > 0 || (sensorToCheck & SENSOR_EXP_BOARD_A7) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_HEART);
                enabledSensors = disableBit(enabledSensors, SENSOR_BATT);
            } else if ((sensorToCheck & SENSOR_BATT) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_EXP_BOARD_A0);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXP_BOARD_A7);
            }
        } else if (isShimmerGen3or3R()) {

            if ((sensorToCheck & SENSOR_GSR) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A1);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A14);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_EXG1_16BIT) > 0 || (sensorToCheck & SENSOR_EXG2_16BIT) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A1);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A12);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A13);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A14);
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_EXG1_24BIT) > 0 || (sensorToCheck & SENSOR_EXG2_24BIT) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A1);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A12);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A13);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A14);
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_BRIDGE_AMP) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A12);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A13);
                enabledSensors = disableBit(enabledSensors, SENSOR_INT_ADC_A14);
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
            } else if ((sensorToCheck & SENSOR_INT_ADC_A14) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
            } else if ((sensorToCheck & SENSOR_INT_ADC_A12) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_INT_ADC_A13) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            } else if ((sensorToCheck & SENSOR_INT_ADC_A14) > 0) {
                enabledSensors = disableBit(enabledSensors, SENSOR_GSR);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_16BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG1_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_EXG2_24BIT);
                enabledSensors = disableBit(enabledSensors, SENSOR_BRIDGE_AMP);
            }
        }
        enabledSensors = enabledSensors ^ sensorToCheck;
        return enabledSensors;
    }

    public boolean sensorConflictCheck(long enabledSensors) {
        boolean pass = true;
        if (getHardwareVersion() != HW_ID.SHIMMER_3 && getHardwareVersion() != HW_ID.SHIMMER_3R) {
            if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EMG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_ECG) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (get5VReg() == 1) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_EXP_BOARD_A0) > 0) {
                if (((enabledSensors & 0xFFFFF) & SENSOR_BATT) > 0) {
                    pass = false;
                } else if (getPMux() == 1) {
                    writePMux(0);
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_EXP_BOARD_A7) > 0) {
                if (((enabledSensors & 0xFFFFF) & SENSOR_BATT) > 0) {
                    pass = false;
                } else if (getPMux() == 1) {
                    writePMux(0);
                }
            }

            if (((enabledSensors & 0xFFFFF) & SENSOR_BATT) > 0) {
                if (((enabledSensors & 0xFF) & SENSOR_EXP_BOARD_A7) > 0) {
                    pass = false;
                }
                if (((enabledSensors & 0xFF) & SENSOR_EXP_BOARD_A0) > 0) {
                    pass = false;
                }
                if (((enabledSensors & 0xFFFFF) & SENSOR_BATT) > 0) {
                    if (getPMux() == 0) {

                        writePMux(1);
                    }
                }
            }
            if (!pass) {

            }
        } else {

            if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0 || ((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {

                if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A1) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A12) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A13) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A14) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0 || ((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {

                if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A1) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A12) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A13) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A14) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }


            if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {

                if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A1) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A14) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {

                if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A12) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A13) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A14) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A1) > 0) {

                if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A12) > 0) {

                if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF00) & SENSOR_INT_ADC_A13) > 0) {

                if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }

            if (((enabledSensors & 0xFF0000) & SENSOR_INT_ADC_A14) > 0) {

                if (((enabledSensors & 0xFF) & SENSOR_GSR) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG1_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF0000) & SENSOR_EXG2_16BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                    pass = false;
                } else if (((enabledSensors & 0xFF00) & SENSOR_BRIDGE_AMP) > 0) {
                    pass = false;
                }
            }
        }

        return pass;
    }

    private long generateSensorBitmapForHardwareControl(long enabledSensors) {
        long hardwareSensorBitmap = 0;

        if (getHardwareVersion() == HW_ID.SHIMMER_2R || getHardwareVersion() == HW_ID.SHIMMER_2) {
            if (((enabledSensors & 0xFFFFF) & SENSOR_BATT) > 0) {
                enabledSensors = enabledSensors & 0xFFFF;
                enabledSensors = enabledSensors | SENSOR_EXP_BOARD_A0 | SENSOR_EXP_BOARD_A7;
            }
            hardwareSensorBitmap = enabledSensors;
        } else if (isShimmerGen3or3R()) {
            if (((enabledSensors & 0xFF) & SENSOR_ACCEL) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_A_ACCEL;
            }
            if ((enabledSensors & SENSOR_DACCEL) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_D_ACCEL;
            }
            if (((enabledSensors & 0xFF) & SENSOR_EXG1_24BIT) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXG1_24BIT;
            }
            if (((enabledSensors & 0xFF) & SENSOR_EXG2_24BIT) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXG2_24BIT;
            }

            if ((enabledSensors & SENSOR_EXG1_16BIT) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXG1_16BIT;
            }
            if ((enabledSensors & SENSOR_EXG2_16BIT) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXG2_16BIT;
            }
            if (((enabledSensors & 0xFF) & SENSOR_GYRO) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_GYRO;
            }
            if (((enabledSensors & 0xFF) & SENSOR_MAG) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_MAG;
            }
            if ((enabledSensors & SENSOR_BATT) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_VBATT;
            }
            if ((enabledSensors & SENSOR_EXT_ADC_A7) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXT_A7;
            }
            if ((enabledSensors & SENSOR_EXT_ADC_A6) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXT_A6;
            }
            if ((enabledSensors & SENSOR_EXT_ADC_A15) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_EXT_A15;
            }
            if ((enabledSensors & SENSOR_INT_ADC_A1) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_INT_A1;
            }
            if ((enabledSensors & SENSOR_INT_ADC_A12) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_INT_A12;
            }
            if ((enabledSensors & SENSOR_INT_ADC_A13) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_INT_A13;
            }
            if ((enabledSensors & SENSOR_INT_ADC_A14) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_INT_A14;
            }
            if ((enabledSensors & SENSOR_BMPX80) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_BMP180;
            }
            if ((enabledSensors & SENSOR_GSR) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_GSR;
            }
            if ((enabledSensors & SENSOR_BRIDGE_AMP) > 0) {
                hardwareSensorBitmap = hardwareSensorBitmap | Configuration.Shimmer3.SensorBitmap.SENSOR_BRIDGE_AMP;
            }
        } else {
            hardwareSensorBitmap = enabledSensors;
        }

        return hardwareSensorBitmap;
    }

    public void toggleLed() {
        writeInstruction(TOGGLE_LED_COMMAND);
        readLEDCommand();
    }

    @Override
    protected void checkBattery() {
        if (mIsStreaming) {
            if ((isShimmerGen3or3R()) && getFirmwareVersionCode() == FW_ID.LOGANDSTREAM) {
                if (!mWaitForAck) {
                    if (mVSenseBattMA.getMean() < mLowBattLimit * 1000 * 0.8) {
                        if (mCurrentLEDStatus != 2) {
                            writeLEDCommand(2);
                        }
                    } else if (mVSenseBattMA.getMean() < mLowBattLimit * 1000) {
                        if (mCurrentLEDStatus != 1) {
                            writeLEDCommand(1);
                        }
                    } else if (mVSenseBattMA.getMean() > mLowBattLimit * 1000 + 100) {
                        if (mCurrentLEDStatus != 0) {
                            writeLEDCommand(0);
                        }
                    }

                }
            }
            if (getHardwareVersion() == HW_ID.SHIMMER_2R) {
                if (!mWaitForAck) {
                    if (mVSenseBattMA.getMean() < mLowBattLimit * 1000) {
                        if (mCurrentLEDStatus != 1) {
                            writeLEDCommand(1);
                        }
                    } else if (mVSenseBattMA.getMean() > mLowBattLimit * 1000 + 100) {
                        if (mCurrentLEDStatus != 0) {
                            writeLEDCommand(0);
                        }
                    }
                }
            }
        }
    }

    public void enableCheckifAlive(boolean set) {
        mCheckIfConnectionisAlive = set;
    }

    protected synchronized List<byte[]> getListofInstructions() {
        synchronized (mListofInstructions) {
            return mListofInstructions;
        }
    }

    private synchronized void clearAllInstructions() {
        synchronized (mListofInstructions) {
            getListofInstructions().clear();
        }
    }

    private synchronized void removeAllNulls() {
        synchronized (mListofInstructions) {
            getListofInstructions().removeAll(Collections.singleton(null));
        }
    }


    private synchronized void checkAndRemoveFirstInstructionIfNull() {
        synchronized (mListofInstructions) {
            if (!getListofInstructions().isEmpty()) {
                if (getListofInstructions().get(0) == null) {
                    removeInstruction(0);
                    printLogDataForDebugging("Null Removed");
                }
            }
        }
    }

    private byte[] getInstruction() {
        synchronized (mListofInstructions) {
            if (!mListofInstructions.isEmpty()) {
                if (mListofInstructions.get(0) != null) {
                    byte[] insBytes = (byte[]) getListofInstructions().get(0);
                    return insBytes;
                } else {
                    return mListofInstructions.remove(0);
                }
            }
        }
        return null;
    }

    protected synchronized void removeInstruction(int index) {
        synchronized (mListofInstructions) {
            try {
                mListofInstructions.remove(index);
            } catch (
                    IndexOutOfBoundsException e) {
                consolePrintLn("Tried to remove BT instruction but it was already gone.");
                consolePrintException(e.getMessage(), e.getStackTrace());
            }
        }
    }

    private synchronized void writeInstruction(int commandValue) {
        writeInstruction(new byte[]{(byte) (commandValue & 0xFF)});
    }

    private synchronized void writeInstruction(byte[] instruction) {
        synchronized (mListofInstructions) {
            mListofInstructions.add(instruction);
        }
    }

    private synchronized void writeInstructionFirst(byte[] instruction) {
        synchronized (mListofInstructions) {
            mListofInstructions.add(0, instruction);
        }
    }

    public boolean isInstructionStackLock() {
        return mInstructionStackLock;
    }

    protected void setInstructionStackLock(boolean state) {
        this.mInstructionStackLock = state;
    }


    public byte[] generateConfigBytesForWritingToShimmer() {
        super.setConfigFileCreationFlag(true);
        return super.configBytesGenerate(true);
    }

    @Override
    public void setBattStatusDetails(ShimmerBattStatusDetails s) {
        super.setBattStatusDetails(s);

        batteryStatusChanged();
    }

    @Override
    public void clearBattStatusDetails() {
        super.clearBattStatusDetails();

        batteryStatusChanged();
    }

    public boolean isUseInfoMemConfigMethod() {
        return this.mUseInfoMemConfigMethod;
    }

    public void setUseInfoMemConfigMethod(boolean useInfoMemConfigMethod) {
        this.mUseInfoMemConfigMethod = useInfoMemConfigMethod;
    }

    public boolean isSetupDeviceWhileConnecting() {
        return mSetupDeviceWhileConnecting;
    }

    public void setSetupDeviceWhileConnecting(boolean state) {
        this.mSetupDeviceWhileConnecting = state;
    }

    protected boolean isIamAlive() {
        return mIamAlive;
    }

    ;

    protected void setIamAlive(boolean state) {
        mIamAlive = state;
    }

    ;

    public boolean isThisVerCompatibleWith(int hardwareVersion, int firmwareIdentifier, int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        return UtilShimmer.compareVersions(getHardwareVersion(), getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(),
                hardwareVersion, firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
    }

    public void enablePCTimeStamps(boolean enable) {
        mEnablePCTimeStamps = enable;
    }

    @Override
    public void configureFromClone(ShimmerDevice shimmerDeviceClone) throws ShimmerException {
        super.configureFromClone(shimmerDeviceClone);

        ShimmerBluetooth cloneShimmerCast = (ShimmerBluetooth) shimmerDeviceClone;

        setSendProgressReport(true);

        if (isUseInfoMemConfigMethod()) {
            writeConfigBytes(cloneShimmerCast.getShimmerConfigBytes());
            writeEnabledSensors(cloneShimmerCast.getEnabledSensors());
        } else {
            writeShimmerAndSensorsSamplingRate(cloneShimmerCast.getSamplingRateShimmer());

            writeAccelRange(cloneShimmerCast.getAccelRange());
            writeGSRRange(cloneShimmerCast.getGSRRange());
            writeGyroRange(cloneShimmerCast.getGyroRange());
            writeMagRange(cloneShimmerCast.getMagRange());
            writePressureResolution(cloneShimmerCast.getPressureResolution());

            enableLowPowerAccel(cloneShimmerCast.isLowPowerAccelWR());
            enableLowPowerGyro(cloneShimmerCast.isLowPowerGyroEnabled());
            enableLowPowerMag(cloneShimmerCast.isLowPowerMagEnabled());

            writeAccelSamplingRate(cloneShimmerCast.getLSM303DigitalAccelRate());
            writeGyroSamplingRate(cloneShimmerCast.getMPU9X50GyroAccelRate());
            writeMagSamplingRate(cloneShimmerCast.getMagRate());


            writeEXGConfiguration(cloneShimmerCast.getEXG1RegisterArray(), EXG_CHIP_INDEX.CHIP1);
            writeEXGConfiguration(cloneShimmerCast.getEXG2RegisterArray(), EXG_CHIP_INDEX.CHIP2);

            writeInternalExpPower(cloneShimmerCast.getInternalExpPower());
            writeShimmerUserAssignedName(cloneShimmerCast.getShimmerUserAssignedName());
            writeExperimentName(cloneShimmerCast.getTrialName());
            writeConfigTime(cloneShimmerCast.getConfigTime());

            writeDerivedChannels(cloneShimmerCast.getDerivedSensors());

            writeEnabledSensors(cloneShimmerCast.getEnabledSensors());
        }

        if (cloneShimmerCast.getWriteCalibrationDumpWhenConfiguringForClone()) {
            writeCalibrationDump(cloneShimmerCast.calibByteDumpGenerate());
        }
    }

    public boolean getWriteCalibrationDumpWhenConfiguringForClone() {
        return mWriteCalibrationDumpWhenConfiguringForClone;
    }

    public void setWriteCalibrationDumpWhenConfiguringForClone(boolean enable) {
        mWriteCalibrationDumpWhenConfiguringForClone = enable;
    }

    public enum BT_STATE {
        DISCONNECTED("Disconnected"),
        CONNECTING("Connecting"),
        CONNECTED("Ready"),
        STREAMING("Streaming"),
        STREAMING_AND_SDLOGGING("Streaming and SD Logging"),
        STREAMING_LOGGED_DATA("Streaming Logged Data"),
        SDLOGGING("SD Logging"),
        CONFIGURING("Configuring"),
        CONNECTION_LOST("Lost connection"),
        CONNECTION_FAILED("Connection Failed");

        private final String text;

        private BT_STATE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum BT_CRC_MODE {
        OFF(0),
        ONE_BYTE_CRC(1),
        TWO_BYTE_CRC(2);

        private int numCrcBytes;

        private BT_CRC_MODE(int numCrcBytes) {
            this.numCrcBytes = numCrcBytes;
        }

        public int getNumCrcBytes() {
            return numCrcBytes;
        }
    }

    private class DUMMY_READ_WAIT_TIME_MS {
        private final static int START = 50;
        private final static int INITIAL_AFTER_WRITE = 200;
        private final static int CHECK_INTERVAL = 100;
        private final static int TIMEOUT = 5000;
    }

    public class ProcessingThread extends Thread {
        public boolean stop = false;
        int count = 0;

        public void run() {
            while (!stop) {
                if (!mABQPacketByeArray.isEmpty()) {
                    count++;
                    if (count % 1000 == 0) {
                        consolePrintLn("Queue Size: " + mABQPacketByeArray.size());
                        printLogDataForDebugging("Queue Size: " + mABQPacketByeArray.size() + "\n");
                    }
                    RawBytePacketWithPCTimeStamp rbp = mABQPacketByeArray.remove();
                    buildAndSendMsg(rbp.mDataArray, COMMUNICATION_TYPE.BLUETOOTH, false, rbp.mSystemTimeStamp);
                }
            }
        }
    }

    public class IOThread extends Thread {
        public boolean stop = false;
        protected byte[] byteBuffer = {0};

        public void run() {
            while (!stop) {
                if (InShimmerTest) {
                    if (bytesAvailableToBeRead()) {
                        byte[] data = readBytes(availableBytes());
                        if (data != null) {
                            if (data.length > 0) {
                                if (mTestByteListener != null) {
                                    mTestByteListener.eventNewBytesReceived(data);
                                }
                            }
                        }
                    }
                } else {
                    if (mDummyRead) {
                        performDummyRead();
                    }

                    if (!isInstructionStackLock()) {
                        processNextInstruction();
                    }

                    if (mIsStreaming) {
                        processWhileStreaming();
                    } else if (bytesAvailableToBeRead()) {
                        if (mWaitForAck) {
                            processNotStreamingWaitForAck();
                        } else if (mWaitForResponse) {
                            processNotStreamingWaitForResp();
                        }

                        processBytesAvailableAndInstreamSupported();

                    }
                }

            }
        }

        private void performDummyRead() {
            mDummyReadStarted = true;

            byte dummyReadCommand = GET_SAMPLING_RATE_COMMAND;
            printLogDataForDebugging("Writing command to help clear serial port buffer:\t" + btCommandToString(dummyReadCommand));
            writeBytes(new byte[]{(byte) (dummyReadCommand & 0xFF)});

            int totalWaitTimeMs = DUMMY_READ_WAIT_TIME_MS.INITIAL_AFTER_WRITE;
            threadSleep(DUMMY_READ_WAIT_TIME_MS.INITIAL_AFTER_WRITE);
            while (!stop && bytesAvailableToBeRead()) {
                if (totalWaitTimeMs >= DUMMY_READ_WAIT_TIME_MS.TIMEOUT) {
                    printLogDataForDebugging("Dummy read timeout");
                    break;
                }

                clearSerialBuffer();
                threadSleep(DUMMY_READ_WAIT_TIME_MS.CHECK_INTERVAL);
                totalWaitTimeMs += DUMMY_READ_WAIT_TIME_MS.CHECK_INTERVAL;
            }

            mDummyRead = false;
            mDummyReadStarted = false;
        }

        private void processNextInstruction() {

            byte[] insBytes = getInstruction();
            if (insBytes != null) {
                mCurrentCommand = insBytes[0];
                setInstructionStackLock(true);
                mWaitForAck = true;

                if (!mIsStreaming) {
                    clearSerialBuffer();
                }
                if (mCurrentCommand == SET_RWC_COMMAND) {
                    byte[] rtcTimeArray = UtilShimmer.convertMilliSecondsToShimmerRtcDataBytesLSB(System.currentTimeMillis());
                    System.arraycopy(rtcTimeArray, 0, insBytes, 1, 8);
                }
                if (mCurrentCommand == STOP_STREAMING_COMMAND
                        || mCurrentCommand == STOP_SDBT_COMMAND) {
                } else {
                    if (mCurrentCommand == SET_SENSORS_COMMAND
                            && getShimmerVersion() == HW_ID.SHIMMER_2R) {
                        startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 8);
                    } else if ((mCurrentCommand == GET_FW_VERSION_COMMAND)
                            || (mCurrentCommand == GET_SAMPLING_RATE_COMMAND)
                            || (mCurrentCommand == GET_SHIMMER_VERSION_COMMAND_NEW)) {
                        startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
                    } else {
                        if (mIsStreaming) {
                            startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
                        } else {
                            startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 3);
                        }
                    }
                }
                threadSleep((int) ((Math.random() + .1) * 100.0));
                writeBytes(insBytes);
                printLogDataForDebugging("Command Transmitted: \t\t\t" + btCommandToString(mCurrentCommand) + " " + UtilShimmer.bytesToHexStringWithSpacesFormatted(insBytes));

                if (mCurrentCommand == STOP_STREAMING_COMMAND
                        || mCurrentCommand == STOP_SDBT_COMMAND) {
                    mIsStreaming = false;
                    if (mCurrentCommand == STOP_SDBT_COMMAND) {
                        setIsSDLogging(false);
                    }
                    removeAllNulls();
                } else {
						/*
						if((mCurrentCommand==GET_FW_VERSION_COMMAND)
								||(mCurrentCommand==GET_SAMPLING_RATE_COMMAND)
								||(mCurrentCommand==GET_SHIMMER_VERSION_COMMAND_NEW)){
							startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
						}
						else {
							if(mIsStreaming){
								startTimerCheckForAckOrResp(ACK_TIMER_DURATION);
							}
							else {
								startTimerCheckForAckOrResp(ACK_TIMER_DURATION+3);
							}
						}*/
                }


                mTransactionCompleted = false;
            } else {
                if (!mIsStreaming && !bytesAvailableToBeRead()) {
                    threadSleep(50);
                }
            }
        }

        private void processNotStreamingWaitForAck() {
			/*
			if (Math.random()>0.9 && mIsInitialised==true){
				if(bytesAvailableToBeRead()
						&& mCurrentCommand!=TEST_CONNECTION_COMMAND
						&& mCurrentCommand!=GET_STATUS_COMMAND
						&& mCurrentCommand!= GET_VBATT_COMMAND
						&& mCurrentCommand!=START_STREAMING_COMMAND
						&& mCurrentCommand!=STOP_STREAMING_COMMAND
						&& mCurrentCommand!=SET_RWC_COMMAND
						&& mCurrentCommand!=GET_RWC_COMMAND){
					tb=readBytes(1);
					tb=null;
				}
			}
			*/

            if (bytesAvailableToBeRead()) {
                byteBuffer = readBytes(1);
                if (byteBuffer != null) {
                    mNumberofTXRetriesCount = 0;
                    setIamAlive(true);

                    if (mCurrentCommand == STOP_STREAMING_COMMAND
                            || mCurrentCommand == STOP_SDBT_COMMAND) {
                        stopTimerCheckForAckOrResp();

                        threadSleep(400);

                        mIsStreaming = false;
                        mTransactionCompleted = true;
                        mWaitForAck = false;

                        byteStack.clear();

                        clearSerialBuffer();

                        hasStopStreaming();
                        removeInstruction(0);
                        removeAllNulls();
                        if (mCurrentCommand == STOP_SDBT_COMMAND) {
                            eventLogAndStreamStatusChanged(mCurrentCommand);
                        }
                        setInstructionStackLock(false);
                    }
                    if ((byte) byteBuffer[0] == ACK_COMMAND_PROCESSED) {

                        mWaitForAck = false;
                        printLogDataForDebugging("Ack Received for Command: \t\t" + btCommandToString(mCurrentCommand));

                        if (mCurrentCommand != GET_STATUS_COMMAND
                                && mCurrentCommand != TEST_CONNECTION_COMMAND
                                && mCurrentCommand != SET_BLINK_LED
                                && mOperationUnderway) {
                            sendProgressReport(new BluetoothProgressReportPerCmd(mCurrentCommand, getListofInstructions().size(), mMyBluetoothAddress, getComPort()));
                        }

                        if (isKnownSetCommand(mCurrentCommand)) {
                            stopTimerCheckForAckOrResp();

                            processAckFromSetCommand(mCurrentCommand);

                            mTransactionCompleted = true;
                            setInstructionStackLock(false);
                        } else if (isKnownGetCommand(mCurrentCommand)) {
                            processSpecialGetCmdsAfterAck(mCurrentCommand);
                            mWaitForResponse = true;
                            removeInstruction(0);
                        }

                    }
                }
            }
        }

        private void processNotStreamingWaitForResp() {
            if (bytesAvailableToBeRead()) {
                byteBuffer = readBytes(1);
                if (byteBuffer != null) {
                    setIamAlive(true);

                    if (isKnownResponse(byteBuffer[0])) {
                        byte responseCommand = byteBuffer[0];

                        processResponseCommand(responseCommand);
                        stopTimerCheckForAckOrResp();
                        mWaitForResponse = false;
                        mTransactionCompleted = true;
                        setInstructionStackLock(false);
                        printLogDataForDebugging("Response Received:\t\t\t" + btCommandToString(responseCommand));


                        if ((isShimmerGen2() && (byteBuffer[0] == FW_VERSION_RESPONSE))
                                || (getFirmwareVersionCode() >= 5 && (byteBuffer[0] == DAUGHTER_CARD_ID_RESPONSE))) {
                            processFirmwareVerResponse();
                        }
                    }
                }
            }
        }

        private void processBytesAvailableAndInstreamSupported() {
            if (isSupportedInStreamCmds()
                    && !mWaitForAck
                    && !mWaitForResponse
                    && bytesAvailableToBeRead()) {

                byteBuffer = readBytes(1);
                if (byteBuffer != null) {
                    if (byteBuffer[0] == ACK_COMMAND_PROCESSED) {
                        printLogDataForDebugging("ACK RECEIVED , Connected State!!");
                        byteBuffer = readBytes(1, INSTREAM_CMD_RESPONSE);
                        if (byteBuffer != null && byteBuffer[0] == ACK_COMMAND_PROCESSED) { //an android fix.. not fully investigated (JC)
                            byteBuffer = readBytes(1, INSTREAM_CMD_RESPONSE);
                        }
                        if (byteBuffer != null && byteBuffer[0] == INSTREAM_CMD_RESPONSE) {
                            processResponseCommand(INSTREAM_CMD_RESPONSE);
                        }
                    }
                }

                clearSerialBuffer();
            }
        }

    }

    class checkForAckOrRespTask extends TimerTask {

        @Override
        public void run() {
            {
                consolePrintLn("Command:\t" + btCommandToString(mCurrentCommand) + " timeout");
                if (mWaitForAck) {
                    printLogDataForDebugging("Ack not received");
                }
                if (mWaitForResponse) {
                    printLogDataForDebugging("Response not received");
                    sendStatusMSGtoUI("Response not received, please reset Shimmer Device." + mMyBluetoothAddress);
                }
                if (mIsStreaming && getPacketReceptionRateOverall() < 100) {
                    printLogDataForDebugging("Packet RR:  " + Double.toString(getPacketReceptionRateOverall()));
                }

                if (mCurrentCommand == START_LOGGING_ONLY_COMMAND) {

                    printLogDataForDebugging("START_LOGGING_ONLY_COMMAND response not received. Send feedback to the GUI without killing the connection");

                    setIsSDLogging(true);
                    eventLogAndStreamStatusChanged(mCurrentCommand);
                    mWaitForAck = false;
                    mWaitForResponse = false;

                    removeInstruction(0);
                    mTransactionCompleted = true;
                    setInstructionStackLock(false);

                    return;
                } else if (mCurrentCommand == STOP_LOGGING_ONLY_COMMAND) {

                    printLogDataForDebugging("STOP_LOGGING_ONLY_COMMAND response not received. Send feedback to the GUI without killing the connection");

                    setIsSDLogging(false);
                    eventLogAndStreamStatusChanged(mCurrentCommand);
                    mWaitForAck = false;
                    mWaitForResponse = false;

                    removeInstruction(0);
                    mTransactionCompleted = true;
                    setInstructionStackLock(false);

                    return;
                }


                if (mCurrentCommand == GET_FW_VERSION_COMMAND) {
                    setShimmerVersionObjectAndCreateSensorMap(new ShimmerVerObject(HW_ID.SHIMMER_2R, FW_ID.BOILER_PLATE, 0, 1, 0));


                    initializeBoilerPlate();
                } else if (mCurrentCommand == GET_SHIMMER_VERSION_COMMAND_NEW) { //in case the new command doesn't work, try the old command
                    clearAllInstructions();
                    readShimmerVersionDeprecated();
                }


                if (mIsStreaming) {
                    stopTimerCheckForAckOrResp();
                    mWaitForAck = false;
                    mTransactionCompleted = true;
                    setInstructionStackLock(false);
                    clearAllInstructions();
                } else {

                    clearSerialBuffer();
                    stopTimerCheckForAckOrResp();
                    printLogDataForDebugging("RETRY TX COUNT: " + Integer.toString(mNumberofTXRetriesCount) + " left of " + NUMBER_OF_TX_RETRIES_LIMIT);
                    if (mNumberofTXRetriesCount >= NUMBER_OF_TX_RETRIES_LIMIT && mCurrentCommand != GET_SHIMMER_VERSION_COMMAND_NEW && !mIsInitialised) {
                        connectionLost();
                    } else if (mNumberofTXRetriesCount >= NUMBER_OF_TX_RETRIES_LIMIT && mIsInitialised) {
                        connectionLost();
                    } else {
                        mWaitForAck = false;
                        mWaitForResponse = false;
                        mTransactionCompleted = true;
                        setInstructionStackLock(false);
                        startTimerCheckForAckOrResp(ACK_TIMER_DURATION + 3);
                    }

                    mNumberofTXRetriesCount++;
                }
            }
        }
    }

    public class readStatusTask extends TimerTask {

        @Override
        public void run() {
            if (getListofInstructions().size() == 0
                    && !getListofInstructions().contains(GET_STATUS_COMMAND)) {
                readStatusLogAndStream();
            }
        }

    }

    private class checkIfAliveTask extends TimerTask {

        @Override
        public void run() {
            if (InShimmerTest) {
                return;
            }
            if (isIamAlive()) {
                mCountDeadConnection = 0;
                setIamAlive(false);
            } else {
                if (isSupportedInStreamCmds() && mIsStreaming) {
                    mCountDeadConnection++;
                } else if (getFirmwareIdentifier() == FW_ID.BTSTREAM) {
                    mCountDeadConnection++;
                }

                if (getFirmwareVersionCode() >= 6 && !mIsStreaming) {
                    if (getListofInstructions().size() == 0
                            && !getListofInstructions().contains(TEST_CONNECTION_COMMAND)) {
                        consolePrintLn("Check Alive Task");
                        if (isSupportedInStreamCmds()) {
                        } else if (getFirmwareIdentifier() == FW_ID.BTSTREAM) {
                            writeTestConnectionCommand();
                        }
                    }
                } else {
                    consolePrintLn("Check Alive Task");
                    writeLEDCommand(0);
                }
                if (mCountDeadConnection > 5) {
                    connectionLost();
                }
            }
        }
    }

    private class readBattStatusTask extends TimerTask {
        @Override
        public void run() {
            consolePrintLn("Read Batt Task");
            readBattery();
        }
    }

    private class connectingTimeoutTask extends TimerTask {
        @Override
        public void run() {
            if (mBluetoothRadioState == BT_STATE.CONNECTING) {
                connectionLost();
                stopTimerConnectingTimeout();
                consolePrintLn("Connecting timer timed out, connection lost");
            }
        }
    }

    class checkForSerialPortClear extends TimerTask {
        @Override
        public void run() {
            System.out.println("Timeout while trying to clear buffer");
            mSerialPortReadTimeout = true;
            connectionLost();
        }
    }

}
