package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class DSTU7564Digest implements ExtendedDigest, Memoable {
    private static final int NB_1024 = 16;
    private static final int NB_512 = 8;
    private static final int NR_1024 = 14;
    private static final int NR_512 = 10;
    private static final byte[] S0 = {ShimmerObject.SET_TEST, 67, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, 6, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.GET_BAUD_RATE_COMMAND, 89, ShimmerObject.STATUS_RESPONSE, -33, ShimmerObject.GET_CONFIGTIME_COMMAND, -107, 23, -16, -40, 9, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, -13, Ascii.GS, -53, -55, 77, 44, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.ROUTINE_COMMUNICATION, ShimmerObject.STOP_SDBT_COMMAND, -3, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, 75, 69, 57, 62, -35, -93, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -74, ShimmerObject.GET_CALIB_DUMP_COMMAND, 14, Ascii.US, ByteSourceJsonBootstrapper.UTF8_BOM_3, 21, -31, 73, -46, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -58, ShimmerObject.START_LOGGING_ONLY_COMMAND, ShimmerObject.GET_STATUS_COMMAND, -98, ShimmerObject.SET_EXG_REGS_COMMAND, -47, ShimmerObject.GET_EXG_REGS_COMMAND, -6, -18, -12, 25, -43, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, 88, -92, ByteSourceJsonBootstrapper.UTF8_BOM_2, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, -36, -14, ShimmerObject.NSHIMMER_RESPONSE, 55, 66, -28, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.GET_BLINK_LED, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -52, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 74, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 4, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.GET_FW_VERSION_COMMAND, -25, -30, 90, ShimmerObject.TEST_CONNECTION_COMMAND, 22, 35, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, -62, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 15, PSSSigner.TRAILER_IMPLICIT, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 71, 65, ShimmerObject.SET_BUFFER_SIZE_COMMAND, 72, -4, -73, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.DIR_RESPONSE, -91, 83, ShimmerObject.CONFIGTIME_RESPONSE, -7, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, -37, 56, ShimmerObject.GET_SHIMMERNAME_COMMAND, -61, Ascii.RS, 34, 51, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -57, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, 59, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.CENTER_RESPONSE, -70, -11, 20, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, 8, 85, ShimmerObject.UPD_CALIB_DUMP_COMMAND, 76, -2, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 92, -38, 24, 70, -51, ShimmerObject.EXPID_RESPONSE, 33, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, 27, ShimmerObject.GET_DIR_COMMAND, -1, -21, ShimmerObject.GET_NSHIMMER_COMMAND, 105, 58, -99, -41, -45, ShimmerObject.START_SDBT_COMMAND, 103, 64, -75, -34, 93, ShimmerObject.SET_BLINK_LED, ShimmerObject.GET_RWC_COMMAND, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.GET_CENTER_COMMAND, 17, 1, -27, 0, 104, ShimmerObject.SET_CALIB_DUMP_COMMAND, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, -59, 2, -90, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, 11, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, ShimmerObject.SET_CENTER_COMMAND, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, -66, -50, -67, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, -23, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.BLINK_LED_RESPONSE, 28, -20, -15, ShimmerObject.RSP_CALIB_DUMP_COMMAND, -108, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, -10, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, ByteSourceJsonBootstrapper.UTF8_BOM_1, -24, ShimmerObject.SET_INFOMEM_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, 3, -44, 127, -5, 5, -63, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.RWC_RESPONSE, 32, 61, ShimmerObject.SET_NSHIMMER_COMMAND, -9, -22, 10, 13, ShimmerObject.GET_EXPID_COMMAND, -8, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 26, -60, 7, 87, -72, 60, ShimmerObject.EXG_REGS_RESPONSE, -29, -56, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 82, 100, 16, -48, -39, 19, 12, 18, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -71, -49, -42, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.INFOMEM_RESPONSE, ShimmerObject.GET_MYID_COMMAND, 84, -64, -19, 78, 68, -89, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.SET_CONFIGTIME_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, -26, -54, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.SET_CRC_COMMAND, 86, -128};
    private static final byte[] S1 = {-50, ByteSourceJsonBootstrapper.UTF8_BOM_2, -21, ShimmerObject.START_LOGGING_ONLY_COMMAND, -22, -53, 19, -63, -23, 58, -42, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, -46, ShimmerObject.RWC_RESPONSE, 23, -8, 66, 21, 86, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, 28, ShimmerObject.DIR_RESPONSE, 67, -59, 92, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -70, -11, 87, 103, ShimmerObject.INFOMEM_RESPONSE, ShimmerObject.BLINK_LED_RESPONSE, -10, 100, 88, -98, -12, 34, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 15, 2, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, -33, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 77, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.GET_FW_VERSION_COMMAND, -9, 8, 93, 68, 62, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, 20, -56, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, 26, ShimmerObject.BAUD_RATE_RESPONSE, 105, -13, -67, 51, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, -6, -47, ShimmerObject.UPD_CALIB_DUMP_COMMAND, 104, 78, 22, -107, ShimmerObject.GET_RWC_COMMAND, -18, 76, ShimmerObject.GET_EXG_REGS_COMMAND, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, -52, 60, 25, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.GET_MYID_COMMAND, 73, ShimmerObject.GET_SHIMMERNAME_COMMAND, -39, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, 55, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -54, -25, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 72, -3, ShimmerObject.TEST_CONNECTION_COMMAND, 69, -4, 65, 18, 13, ShimmerObject.SET_SHIMMERNAME_COMMAND, -27, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.SET_INFOMEM_COMMAND, -29, 32, ShimmerObject.SET_BLINK_LED, -36, -73, ShimmerObject.GET_BAUD_RATE_COMMAND, 74, -75, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.STOP_SDBT_COMMAND, -44, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, 6, -92, -91, ShimmerObject.NSHIMMER_RESPONSE, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.ECG_CALIBRATION_RESPONSE, -38, -55, 0, ShimmerObject.GET_EXPID_COMMAND, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, 85, ByteSourceJsonBootstrapper.UTF8_BOM_3, 17, -43, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -49, 14, 10, 61, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, ShimmerObject.EXPID_RESPONSE, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, 27, -2, -60, 71, 9, ShimmerObject.CONFIGTIME_RESPONSE, 11, ShimmerObject.SET_RWC_COMMAND, -99, ShimmerObject.SET_BAUD_RATE_COMMAND, 7, -71, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, ShimmerObject.SET_CALIB_DUMP_COMMAND, 24, ShimmerObject.GET_BLINK_LED, ShimmerObject.STATUS_RESPONSE, 75, ByteSourceJsonBootstrapper.UTF8_BOM_1, 59, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, -28, 64, -1, -61, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, -26, ShimmerObject.GET_CENTER_COMMAND, -7, ShimmerObject.SET_CRC_COMMAND, 70, -128, Ascii.RS, 56, -31, -72, ShimmerObject.SET_TEST, ShimmerObject.ROUTINE_COMMUNICATION, 12, 35, ShimmerObject.SET_CENTER_COMMAND, Ascii.GS, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, 5, -15, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, -108, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.GET_CALIB_DUMP_COMMAND, ShimmerObject.GET_NSHIMMER_COMMAND, -24, -93, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, ShimmerObject.CENTER_RESPONSE, -45, ShimmerObject.SET_CONFIGTIME_COMMAND, -30, 82, -14, ShimmerObject.SET_NSHIMMER_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, 83, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, ShimmerObject.SET_EXG_REGS_COMMAND, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, 57, ShimmerObject.BUFFER_SIZE_RESPONSE, -34, -51, Ascii.US, ShimmerObject.RSP_CALIB_DUMP_COMMAND, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, 44, -35, -48, ShimmerObject.GET_CONFIGTIME_COMMAND, -66, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -90, -20, 4, -58, 3, ShimmerObject.SET_BUFFER_SIZE_COMMAND, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 33, 127, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.EMG_CALIBRATION_RESPONSE, -57, -64, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -41};
    private static final byte[] S2 = {ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -39, ShimmerObject.GET_CALIB_DUMP_COMMAND, -75, ShimmerObject.SET_CALIB_DUMP_COMMAND, 34, 69, -4, -70, ShimmerObject.SET_BAUD_RATE_COMMAND, -33, 2, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, -36, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 89, 74, 23, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, -62, -108, -12, ByteSourceJsonBootstrapper.UTF8_BOM_2, -93, ShimmerObject.EXG_REGS_RESPONSE, -28, ShimmerObject.STATUS_RESPONSE, -44, -51, ShimmerObject.START_SDBT_COMMAND, 22, -31, 73, 60, -64, -40, 92, ShimmerObject.UPD_CALIB_DUMP_COMMAND, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.SET_CONFIGTIME_COMMAND, 83, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, -56, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.ROUTINE_COMMUNICATION, -47, ShimmerObject.GET_STATUS_COMMAND, -90, 44, -60, -29, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.GET_CENTER_COMMAND, -73, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, 9, 59, 14, 65, 76, -34, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.RWC_RESPONSE, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, -91, -41, 3, 17, 0, -61, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.START_LOGGING_ONLY_COMMAND, ByteSourceJsonBootstrapper.UTF8_BOM_1, 78, 18, -99, ShimmerObject.EXPID_RESPONSE, -53, ShimmerObject.BUFFER_SIZE_RESPONSE, 16, -43, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, -98, 77, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 85, -58, -48, ShimmerObject.GET_SHIMMERNAME_COMMAND, 24, ShimmerObject.STOP_SDBT_COMMAND, -45, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -26, 72, 86, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.CENTER_RESPONSE, -52, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -71, -30, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, -72, ShimmerObject.FW_VERSION_RESPONSE, 21, -92, ShimmerObject.SET_EXPID_COMMAND, -38, 56, Ascii.RS, 11, 5, -42, 20, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.GET_EXPID_COMMAND, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, -3, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, -27, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 51, ShimmerObject.GET_CONFIGTIME_COMMAND, -55, -16, 93, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.DIR_RESPONSE, ShimmerObject.INFOMEM_RESPONSE, -57, -9, Ascii.GS, -23, -20, -19, -128, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, ShimmerObject.EMG_CALIBRATION_RESPONSE, -49, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.SET_TEST, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 15, 55, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.SET_BLINK_LED, -107, -46, 62, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, 64, ShimmerObject.NSHIMMER_RESPONSE, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 105, 87, Ascii.US, 7, 28, ShimmerObject.INSTREAM_CMD_RESPONSE, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, ShimmerObject.GET_INFOMEM_COMMAND, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, -18, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, -7, -54, 58, 26, -5, 13, -63, -2, -6, -14, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, -67, ShimmerObject.TEST_CONNECTION_COMMAND, -35, 67, 82, -74, 8, -13, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, -66, 25, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.GET_BLINK_LED, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, -22, 75, 100, ShimmerObject.GET_NSHIMMER_COMMAND, ShimmerObject.SET_NSHIMMER_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, -11, ShimmerObject.SET_SHIMMERNAME_COMMAND, ByteSourceJsonBootstrapper.UTF8_BOM_3, 1, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.GET_EXG_REGS_COMMAND, 27, 35, 61, 104, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, -24, ShimmerObject.GET_RWC_COMMAND, -10, -1, 19, 88, -15, 71, 10, 127, -59, -89, -25, ShimmerObject.SET_EXG_REGS_COMMAND, 90, 6, 70, 68, 66, 4, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, -37, 57, ShimmerObject.CONFIGTIME_RESPONSE, 84, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.SET_INFOMEM_COMMAND, ShimmerObject.SET_BUFFER_SIZE_COMMAND, 33, ShimmerObject.SET_CRC_COMMAND, -8, 12, ShimmerObject.TRIAL_CONFIG_RESPONSE, 103};
    private static final byte[] S3 = {104, ShimmerObject.INFOMEM_RESPONSE, -54, 77, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 75, 78, ShimmerObject.ECG_CALIBRATION_RESPONSE, -44, 82, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 84, Ascii.RS, 25, Ascii.US, 34, 3, 70, 61, ShimmerObject.ALL_CALIBRATION_RESPONSE, 74, 83, ShimmerObject.NSHIMMER_RESPONSE, 19, ShimmerObject.INSTREAM_CMD_RESPONSE, -73, -43, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.SET_SHIMMERNAME_COMMAND, -11, -67, 88, ShimmerObject.FW_VERSION_RESPONSE, 13, 2, -19, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -98, 17, -14, 62, 85, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -47, 22, 60, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, ShimmerObject.START_SDBT_COMMAND, 93, -13, 69, 64, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, -75, 56, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 14, -27, -12, -7, ShimmerObject.CONFIGTIME_RESPONSE, -23, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, -42, ShimmerObject.SET_CONFIGTIME_COMMAND, 35, -49, ShimmerObject.GET_BLINK_LED, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.BLINK_LED_RESPONSE, 20, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, -18, -56, 72, -45, ShimmerObject.SET_BLINK_LED, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.START_LOGGING_ONLY_COMMAND, 65, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, 24, -60, 44, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, 68, 21, -3, 55, -66, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.DIR_RESPONSE, -40, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -6, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -22, PSSSigner.TRAILER_IMPLICIT, ShimmerObject.EXG_REGS_RESPONSE, 12, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, -90, ShimmerObject.SET_TEST, -20, 103, 32, -37, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -35, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.GET_EXPID_COMMAND, 16, -15, ShimmerObject.GET_SHIMMERNAME_COMMAND, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.GET_EXG_REGS_COMMAND, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, 5, ShimmerObject.GET_CALIB_DUMP_COMMAND, 67, ShimmerObject.CENTER_RESPONSE, 33, ByteSourceJsonBootstrapper.UTF8_BOM_3, ShimmerObject.EMG_CALIBRATION_RESPONSE, 9, -61, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, -74, -41, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -62, -21, -64, -92, ShimmerObject.SET_CRC_COMMAND, ShimmerObject.SET_INFOMEM_COMMAND, Ascii.GS, -5, -1, -63, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.GET_FW_VERSION_COMMAND, -8, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, -10, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.RWC_RESPONSE, 0, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 1, -59, -38, 71, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, -51, 105, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, -30, ShimmerObject.SHIMMERNAME_RESPONSE, -89, -58, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, 15, 10, 6, -26, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, ShimmerObject.TEST_CONNECTION_COMMAND, -93, 28, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.SET_BAUD_RATE_COMMAND, 18, ShimmerObject.GET_NSHIMMER_COMMAND, 57, -25, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, ShimmerObject.SET_NSHIMMER_COMMAND, -9, -2, -99, ShimmerObject.GET_CONFIGTIME_COMMAND, 92, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, -34, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -91, -4, -128, ByteSourceJsonBootstrapper.UTF8_BOM_1, -53, ByteSourceJsonBootstrapper.UTF8_BOM_2, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.SET_CENTER_COMMAND, -70, 90, ShimmerObject.EXPID_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, 11, -107, -29, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.SET_CALIB_DUMP_COMMAND, 59, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 100, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, -36, -16, 89, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 76, 23, 127, ShimmerObject.GET_RWC_COMMAND, -72, -55, 87, 27, ShimmerObject.ROUTINE_COMMUNICATION, ShimmerObject.SET_EXG_REGS_COMMAND};
    private int blockSize;
    private byte[] buf;
    private int bufOff;
    private int columns;
    private int hashSize;
    private long inputBlocks;
    private int rounds;
    private long[] state;
    private long[] tempState1;
    private long[] tempState2;

    public DSTU7564Digest(int i) {
        int i2;
        if (i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
        }
        this.hashSize = i >>> 3;
        if (i > 256) {
            this.columns = 16;
            i2 = 14;
        } else {
            this.columns = 8;
            i2 = 10;
        }
        this.rounds = i2;
        int i3 = this.columns;
        int i4 = i3 << 3;
        this.blockSize = i4;
        long[] jArr = new long[i3];
        this.state = jArr;
        jArr[0] = i4;
        this.tempState1 = new long[i3];
        this.tempState2 = new long[i3];
        this.buf = new byte[i4];
    }

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        copyIn(dSTU7564Digest);
    }

    private static long mixColumn(long j) {
        long j2 = ((9187201950435737471L & j) << 1) ^ (((j & (-9187201950435737472L)) >>> 7) * 29);
        long jRotate = rotate(8, j) ^ j;
        long jRotate2 = (jRotate ^ rotate(16, jRotate)) ^ rotate(48, j);
        long j3 = (j ^ jRotate2) ^ j2;
        return ((rotate(32, (((j3 & 4629771061636907072L) >>> 6) * 29) ^ (((((-9187201950435737472L) & j3) >>> 6) * 29) ^ ((4557430888798830399L & j3) << 2))) ^ jRotate2) ^ rotate(40, j2)) ^ rotate(48, j2);
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void P(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = i;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] ^ j;
                j += 16;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void Q(long[] jArr) {
        for (int i = 0; i < this.rounds; i++) {
            long j = ((((this.columns - 1) << 4) ^ i) << 56) | 67818912035696883L;
            for (int i2 = 0; i2 < this.columns; i2++) {
                jArr[i2] = jArr[i2] + j;
                j -= LockFreeTaskQueueCore.FROZEN_MASK;
            }
            shiftRows(jArr);
            subBytes(jArr);
            mixColumns(jArr);
        }
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.rounds = dSTU7564Digest.rounds;
        int i = this.columns;
        if (i <= 0 || i != dSTU7564Digest.columns) {
            this.columns = dSTU7564Digest.columns;
            this.state = Arrays.clone(dSTU7564Digest.state);
            int i2 = this.columns;
            this.tempState1 = new long[i2];
            this.tempState2 = new long[i2];
            this.buf = Arrays.clone(dSTU7564Digest.buf);
        } else {
            System.arraycopy(dSTU7564Digest.state, 0, this.state, 0, i);
            System.arraycopy(dSTU7564Digest.buf, 0, this.buf, 0, this.blockSize);
        }
        this.inputBlocks = dSTU7564Digest.inputBlocks;
        this.bufOff = dSTU7564Digest.bufOff;
    }

    private void mixColumns(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void processBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.columns; i2++) {
            long jLittleEndianToLong = Pack.littleEndianToLong(bArr, i);
            i += 8;
            this.tempState1[i2] = this.state[i2] ^ jLittleEndianToLong;
            this.tempState2[i2] = jLittleEndianToLong;
        }
        P(this.tempState1);
        Q(this.tempState2);
        for (int i3 = 0; i3 < this.columns; i3++) {
            long[] jArr = this.state;
            jArr[i3] = jArr[i3] ^ (this.tempState1[i3] ^ this.tempState2[i3]);
        }
    }

    private void shiftRows(long[] jArr) {
        int i = this.columns;
        if (i == 8) {
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = (j ^ j5) & (-4294967296L);
            long j10 = j ^ j9;
            long j11 = j5 ^ j9;
            long j12 = (j2 ^ j6) & 72057594021150720L;
            long j13 = j2 ^ j12;
            long j14 = j6 ^ j12;
            long j15 = (j3 ^ j7) & 281474976645120L;
            long j16 = j3 ^ j15;
            long j17 = j7 ^ j15;
            long j18 = (j4 ^ j8) & 1099511627520L;
            long j19 = j4 ^ j18;
            long j20 = j8 ^ j18;
            long j21 = (j10 ^ j16) & (-281470681808896L);
            long j22 = j10 ^ j21;
            long j23 = j16 ^ j21;
            long j24 = (j13 ^ j19) & 72056494543077120L;
            long j25 = j13 ^ j24;
            long j26 = j19 ^ j24;
            long j27 = (j11 ^ j17) & (-281470681808896L);
            long j28 = j11 ^ j27;
            long j29 = j17 ^ j27;
            long j30 = (j14 ^ j20) & 72056494543077120L;
            long j31 = j14 ^ j30;
            long j32 = j20 ^ j30;
            long j33 = (j22 ^ j25) & (-71777214294589696L);
            long j34 = j22 ^ j33;
            long j35 = j25 ^ j33;
            long j36 = (j23 ^ j26) & (-71777214294589696L);
            long j37 = j23 ^ j36;
            long j38 = j26 ^ j36;
            long j39 = (j28 ^ j31) & (-71777214294589696L);
            long j40 = (j29 ^ j32) & (-71777214294589696L);
            jArr[0] = j34;
            jArr[1] = j35;
            jArr[2] = j37;
            jArr[3] = j38;
            jArr[4] = j28 ^ j39;
            jArr[5] = j31 ^ j39;
            jArr[6] = j29 ^ j40;
            jArr[7] = j32 ^ j40;
            return;
        }
        if (i != 16) {
            throw new IllegalStateException("unsupported state size: only 512/1024 are allowed");
        }
        long j41 = jArr[0];
        long j42 = jArr[1];
        long j43 = jArr[2];
        long j44 = jArr[3];
        long j45 = jArr[4];
        long j46 = jArr[5];
        long j47 = jArr[6];
        long j48 = jArr[7];
        long j49 = jArr[8];
        long j50 = jArr[9];
        long j51 = jArr[10];
        long j52 = jArr[11];
        long j53 = jArr[12];
        long j54 = jArr[13];
        long j55 = jArr[14];
        long j56 = jArr[15];
        long j57 = (j41 ^ j49) & (-72057594037927936L);
        long j58 = j41 ^ j57;
        long j59 = j49 ^ j57;
        long j60 = (j42 ^ j50) & (-72057594037927936L);
        long j61 = j42 ^ j60;
        long j62 = j50 ^ j60;
        long j63 = (j43 ^ j51) & (-281474976710656L);
        long j64 = j43 ^ j63;
        long j65 = j51 ^ j63;
        long j66 = (j44 ^ j52) & (-1099511627776L);
        long j67 = j44 ^ j66;
        long j68 = j52 ^ j66;
        long j69 = (j45 ^ j53) & (-4294967296L);
        long j70 = j45 ^ j69;
        long j71 = j53 ^ j69;
        long j72 = (j46 ^ j54) & 72057594021150720L;
        long j73 = j46 ^ j72;
        long j74 = j54 ^ j72;
        long j75 = (j47 ^ j55) & 72057594037862400L;
        long j76 = j47 ^ j75;
        long j77 = j55 ^ j75;
        long j78 = (j48 ^ j56) & 72057594037927680L;
        long j79 = j48 ^ j78;
        long j80 = j56 ^ j78;
        long j81 = (j58 ^ j70) & 72057589742960640L;
        long j82 = j58 ^ j81;
        long j83 = j70 ^ j81;
        long j84 = (j61 ^ j73) & (-16777216);
        long j85 = j61 ^ j84;
        long j86 = j73 ^ j84;
        long j87 = (j64 ^ j76) & (-71776119061282816L);
        long j88 = j64 ^ j87;
        long j89 = j76 ^ j87;
        long j90 = (j67 ^ j79) & (-72056494526300416L);
        long j91 = j67 ^ j90;
        long j92 = j79 ^ j90;
        long j93 = (j59 ^ j71) & 72057589742960640L;
        long j94 = j59 ^ j93;
        long j95 = j71 ^ j93;
        long j96 = (j62 ^ j74) & (-16777216);
        long j97 = j62 ^ j96;
        long j98 = j74 ^ j96;
        long j99 = (j65 ^ j77) & (-71776119061282816L);
        long j100 = j65 ^ j99;
        long j101 = j77 ^ j99;
        long j102 = (j68 ^ j80) & (-72056494526300416L);
        long j103 = j68 ^ j102;
        long j104 = j80 ^ j102;
        long j105 = (j82 ^ j88) & (-281470681808896L);
        long j106 = j82 ^ j105;
        long j107 = j88 ^ j105;
        long j108 = (j85 ^ j91) & 72056494543077120L;
        long j109 = j85 ^ j108;
        long j110 = j91 ^ j108;
        long j111 = (j83 ^ j89) & (-281470681808896L);
        long j112 = j83 ^ j111;
        long j113 = j89 ^ j111;
        long j114 = (j86 ^ j92) & 72056494543077120L;
        long j115 = j86 ^ j114;
        long j116 = j92 ^ j114;
        long j117 = (j94 ^ j100) & (-281470681808896L);
        long j118 = j94 ^ j117;
        long j119 = j100 ^ j117;
        long j120 = (j97 ^ j103) & 72056494543077120L;
        long j121 = j97 ^ j120;
        long j122 = j103 ^ j120;
        long j123 = (j95 ^ j101) & (-281470681808896L);
        long j124 = j95 ^ j123;
        long j125 = j101 ^ j123;
        long j126 = (j98 ^ j104) & 72056494543077120L;
        long j127 = j98 ^ j126;
        long j128 = j104 ^ j126;
        long j129 = (j106 ^ j109) & (-71777214294589696L);
        long j130 = j106 ^ j129;
        long j131 = j109 ^ j129;
        long j132 = (j107 ^ j110) & (-71777214294589696L);
        long j133 = j107 ^ j132;
        long j134 = j110 ^ j132;
        long j135 = (j112 ^ j115) & (-71777214294589696L);
        long j136 = j112 ^ j135;
        long j137 = j115 ^ j135;
        long j138 = (j113 ^ j116) & (-71777214294589696L);
        long j139 = j113 ^ j138;
        long j140 = j116 ^ j138;
        long j141 = (j118 ^ j121) & (-71777214294589696L);
        long j142 = j118 ^ j141;
        long j143 = j121 ^ j141;
        long j144 = (j119 ^ j122) & (-71777214294589696L);
        long j145 = j119 ^ j144;
        long j146 = j122 ^ j144;
        long j147 = (j124 ^ j127) & (-71777214294589696L);
        long j148 = (j125 ^ j128) & (-71777214294589696L);
        jArr[0] = j130;
        jArr[1] = j131;
        jArr[2] = j133;
        jArr[3] = j134;
        jArr[4] = j136;
        jArr[5] = j137;
        jArr[6] = j139;
        jArr[7] = j140;
        jArr[8] = j142;
        jArr[9] = j143;
        jArr[10] = j145;
        jArr[11] = j146;
        jArr[12] = j124 ^ j147;
        jArr[13] = j127 ^ j147;
        jArr[14] = j125 ^ j148;
        jArr[15] = j128 ^ j148;
    }

    private void subBytes(long[] jArr) {
        for (int i = 0; i < this.columns; i++) {
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte b = S0[i2 & 255];
            byte b2 = S1[(i2 >>> 8) & 255];
            byte b3 = S2[(i2 >>> 16) & 255];
            byte[] bArr = S3;
            jArr[i] = (((bArr[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16)) & 4294967295L) | (((((r1[i3 & 255] & 255) | ((r6[(i3 >>> 8) & 255] & 255) << 8)) | ((r8[(i3 >>> 16) & 255] & 255) << 16)) | (bArr[i3 >>> 24] << 24)) << 32);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = this.bufOff;
        byte[] bArr2 = this.buf;
        int i5 = i4 + 1;
        this.bufOff = i5;
        bArr2[i4] = -128;
        int i6 = this.blockSize - 12;
        int i7 = 0;
        if (i5 > i6) {
            while (true) {
                int i8 = this.bufOff;
                if (i8 >= this.blockSize) {
                    break;
                }
                byte[] bArr3 = this.buf;
                this.bufOff = i8 + 1;
                bArr3[i8] = 0;
            }
            this.bufOff = 0;
            processBlock(this.buf, 0);
        }
        while (true) {
            i2 = this.bufOff;
            if (i2 >= i6) {
                break;
            }
            byte[] bArr4 = this.buf;
            this.bufOff = i2 + 1;
            bArr4[i2] = 0;
        }
        long j = (((this.inputBlocks & 4294967295L) * this.blockSize) + i4) << 3;
        Pack.intToLittleEndian((int) j, this.buf, i2);
        int i9 = this.bufOff + 4;
        this.bufOff = i9;
        Pack.longToLittleEndian((j >>> 32) + (((this.inputBlocks >>> 32) * this.blockSize) << 3), this.buf, i9);
        processBlock(this.buf, 0);
        System.arraycopy(this.state, 0, this.tempState1, 0, this.columns);
        P(this.tempState1);
        while (true) {
            i3 = this.columns;
            if (i7 >= i3) {
                break;
            }
            long[] jArr = this.state;
            jArr[i7] = jArr[i7] ^ this.tempState1[i7];
            i7++;
        }
        for (int i10 = i3 - (this.hashSize >>> 3); i10 < this.columns; i10++) {
            Pack.longToLittleEndian(this.state[i10], bArr, i);
            i += 8;
        }
        reset();
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "DSTU7564";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.hashSize;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.fill(this.state, 0L);
        this.state[0] = this.blockSize;
        this.inputBlocks = 0L;
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((DSTU7564Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.buf;
        int i = this.bufOff;
        int i2 = i + 1;
        this.bufOff = i2;
        bArr[i] = b;
        if (i2 == this.blockSize) {
            processBlock(bArr, 0);
            this.bufOff = 0;
            this.inputBlocks++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.bufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i2--;
            i++;
        }
        if (i2 > 0) {
            while (i2 >= this.blockSize) {
                processBlock(bArr, i);
                int i3 = this.blockSize;
                i += i3;
                i2 -= i3;
                this.inputBlocks++;
            }
            while (i2 > 0) {
                update(bArr[i]);
                i2--;
                i++;
            }
        }
    }
}
