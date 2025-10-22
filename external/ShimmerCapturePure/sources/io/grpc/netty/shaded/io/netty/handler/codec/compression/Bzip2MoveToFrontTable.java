package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes3.dex */
final class Bzip2MoveToFrontTable {
    private final byte[] mtf = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, Ascii.GS, Ascii.RS, Ascii.US, 32, 33, 34, 35, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 44, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, 58, 59, 60, 61, 62, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, 92, 93, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.GET_SHIMMERNAME_COMMAND, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.EXPID_RESPONSE, ShimmerObject.GET_EXPID_COMMAND, 127, -128, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.SET_NSHIMMER_COMMAND, ShimmerObject.NSHIMMER_RESPONSE, ShimmerObject.GET_NSHIMMER_COMMAND, ShimmerObject.SET_CONFIGTIME_COMMAND, ShimmerObject.CONFIGTIME_RESPONSE, ShimmerObject.GET_CONFIGTIME_COMMAND, ShimmerObject.DIR_RESPONSE, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.SET_CRC_COMMAND, ShimmerObject.SET_INFOMEM_COMMAND, ShimmerObject.INFOMEM_RESPONSE, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.RWC_RESPONSE, ShimmerObject.GET_RWC_COMMAND, ShimmerObject.START_LOGGING_ONLY_COMMAND, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -108, -107, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.SET_CALIB_DUMP_COMMAND, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.GET_CALIB_DUMP_COMMAND, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -99, -98, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, -93, -92, -91, -90, -89, ShimmerObject.SET_TEST, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -75, -74, -73, -72, -71, -70, ByteSourceJsonBootstrapper.UTF8_BOM_2, PSSSigner.TRAILER_IMPLICIT, -67, -66, ByteSourceJsonBootstrapper.UTF8_BOM_3, -64, -63, -62, -61, -60, -59, -58, -57, -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, -36, -35, -34, -33, ShimmerObject.ROUTINE_COMMUNICATION, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, ByteSourceJsonBootstrapper.UTF8_BOM_1, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1};

    Bzip2MoveToFrontTable() {
    }

    int valueToFront(byte b) {
        byte[] bArr = this.mtf;
        int i = 0;
        byte b2 = bArr[0];
        if (b != b2) {
            bArr[0] = b;
            while (b != b2) {
                i++;
                byte[] bArr2 = this.mtf;
                byte b3 = bArr2[i];
                bArr2[i] = b2;
                b2 = b3;
            }
        }
        return i;
    }

    byte indexToFront(int i) {
        byte[] bArr = this.mtf;
        byte b = bArr[i];
        System.arraycopy(bArr, 0, bArr, 1, i);
        this.mtf[0] = b;
        return b;
    }
}
