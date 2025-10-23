package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;

import java.lang.reflect.Array;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private static final byte[] S = {ShimmerObject.GET_EXG_REGS_COMMAND, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_SHIMMERNAME_COMMAND, -14, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, -59, ShimmerObject.SET_BLINK_LED, 1, 103, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, -2, -41, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, ShimmerObject.SET_CENTER_COMMAND, -54, ShimmerObject.SET_NSHIMMER_COMMAND, -55, ShimmerObject.EXPID_RESPONSE, -6, 89, 71, -16, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, -44, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -92, ShimmerObject.GET_STATUS_COMMAND, -64, -73, -3, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.GET_BUFFER_SIZE_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, -9, -52, ShimmerObject.SET_BUFFER_SIZE_COMMAND, -91, -27, -15, ShimmerObject.STATUS_RESPONSE, -40, ShimmerObject.BLINK_LED_RESPONSE, 21, 4, -57, 35, -61, 24, ShimmerObject.TEST_CONNECTION_COMMAND, 5, ShimmerObject.GET_CALIB_DUMP_COMMAND, 7, 18, -128, -30, -21, ShimmerObject.EMG_CALIBRATION_RESPONSE, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 9, ShimmerObject.NSHIMMER_RESPONSE, 44, 26, 27, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 90, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, 82, 59, -42, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -29, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.GET_NSHIMMER_COMMAND, 83, -47, 0, -19, 32, -4, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, ShimmerObject.SET_BAUD_RATE_COMMAND, -53, -66, 57, 74, 76, 88, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, -5, 67, 77, 51, ShimmerObject.SET_CONFIGTIME_COMMAND, 69, -7, 2, 127, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 60, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.SET_TEST, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -93, 64, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.START_LOGGING_ONLY_COMMAND, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.STOP_SDBT_COMMAND, 68, 23, -60, -89, ShimmerObject.GET_EXPID_COMMAND, 61, 100, 93, 25, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.GET_MYID_COMMAND, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, -36, 34, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.RWC_RESPONSE, ShimmerObject.DIR_RESPONSE, 70, -18, -72, 20, -34, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 11, -37, ShimmerObject.ROUTINE_COMMUNICATION, ShimmerObject.GET_BLINK_LED, 58, 10, 73, 6, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, 92, -62, -45, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_RWC_COMMAND, -107, -28, ShimmerObject.SET_SHIMMERNAME_COMMAND, -25, -56, 55, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.INFOMEM_RESPONSE, -43, 78, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, ShimmerObject.GET_BAUD_RATE_COMMAND, 86, -12, -22, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.SHIMMERNAME_RESPONSE, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 8, -70, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.GET_FW_VERSION_COMMAND, 28, -90, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -58, -24, -35, ShimmerObject.TRIAL_CONFIG_RESPONSE, Ascii.US, 75, -67, ShimmerObject.SET_CRC_COMMAND, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.START_SDBT_COMMAND, 62, -75, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 72, 3, -10, 14, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, 87, -71, ShimmerObject.CONFIGTIME_RESPONSE, -63, Ascii.GS, -98, -31, -8, ShimmerObject.SET_CALIB_DUMP_COMMAND, 17, 105, -39, ShimmerObject.GET_INFOMEM_COMMAND, -108, ShimmerObject.UPD_CALIB_DUMP_COMMAND, Ascii.RS, ShimmerObject.GET_CONFIGTIME_COMMAND, -23, -50, 85, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -33, ShimmerObject.SET_INFOMEM_COMMAND, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.GET_DIR_COMMAND, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.ALL_CALIBRATION_RESPONSE, 15, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, 22};
    private static final byte[] Si = {82, 9, ShimmerObject.SET_BAUD_RATE_COMMAND, -43, ShimmerObject.SET_BLINK_LED, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -93, -98, ShimmerObject.GET_MYID_COMMAND, -13, -41, -5, ShimmerObject.SET_EXPID_COMMAND, -29, 57, ShimmerObject.SET_NSHIMMER_COMMAND, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, -1, ShimmerObject.GET_CONFIGTIME_COMMAND, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.GET_INFOMEM_COMMAND, 67, 68, -60, -34, -23, -53, 84, ShimmerObject.GET_SHIMMERNAME_COMMAND, -108, ShimmerObject.GET_BLINK_LED, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -39, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, 73, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.SET_CRC_COMMAND, -47, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, -8, -10, 100, ShimmerObject.CONFIGTIME_RESPONSE, 104, ShimmerObject.SET_CALIB_DUMP_COMMAND, 22, -44, -92, 92, -52, 93, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, -74, ShimmerObject.START_LOGGING_ONLY_COMMAND, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.START_SDBT_COMMAND, 72, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, -3, -19, -71, -38, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 21, 70, 87, -89, ShimmerObject.INFOMEM_RESPONSE, -99, ShimmerObject.GET_NSHIMMER_COMMAND, ShimmerObject.RWC_RESPONSE, -40, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 0, ShimmerObject.SET_INFOMEM_COMMAND, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 69, 6, -48, 44, Ascii.RS, ShimmerObject.SET_RWC_COMMAND, -54, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, 15, 2, -63, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, -67, 3, 1, 19, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.BAUD_RATE_RESPONSE, 58, ShimmerObject.GET_RWC_COMMAND, 17, 65, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, 103, -36, -22, ShimmerObject.STOP_SDBT_COMMAND, -14, -49, -50, -16, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -26, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TEST_CONNECTION_COMMAND, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, 34, -25, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.SET_CONFIGTIME_COMMAND, -30, -7, 55, -24, 28, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, -33, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 71, -15, 26, ShimmerObject.STATUS_RESPONSE, Ascii.GS, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -59, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, -73, ShimmerObject.EXG_REGS_RESPONSE, 14, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, 24, -66, 27, -4, 86, 62, 75, -58, -46, ShimmerObject.SET_SHIMMERNAME_COMMAND, 32, ShimmerObject.GET_CALIB_DUMP_COMMAND, -37, -64, -2, ShimmerObject.GET_CENTER_COMMAND, -51, 90, -12, Ascii.US, -35, ShimmerObject.SET_TEST, 51, ShimmerObject.DIR_RESPONSE, 7, -57, ShimmerObject.BLINK_LED_RESPONSE, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, 18, 16, 89, ShimmerObject.EMG_CALIBRATION_RESPONSE, -128, -20, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 127, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 25, -75, 74, 13, ShimmerObject.ALL_CALIBRATION_RESPONSE, -27, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -55, ShimmerObject.UPD_SDLOG_CFG_COMMAND, ByteSourceJsonBootstrapper.UTF8_BOM_1, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, ShimmerObject.ROUTINE_COMMUNICATION, 59, 77, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.ECG_CALIBRATION_RESPONSE, -11, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, ShimmerObject.NSHIMMER_RESPONSE, 83, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.SET_EXG_REGS_COMMAND, 23, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 4, ShimmerObject.GET_EXPID_COMMAND, -70, ShimmerObject.CENTER_RESPONSE, -42, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, -31, 105, 20, ShimmerObject.GET_EXG_REGS_COMMAND, 85, 33, 12, ShimmerObject.EXPID_RESPONSE};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    public AESLightEngine() {
    }

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private static int inv_mcol(int i) {
        int iShift = shift(i, 8) ^ i;
        int iFFmulX = i ^ FFmulX(iShift);
        int iFFmulX2 = iShift ^ FFmulX2(iFFmulX);
        return iFFmulX ^ (iFFmulX2 ^ shift(iFFmulX2, 16));
    }

    private static int mcol(int i) {
        int iShift = shift(i, 8);
        int i2 = i ^ iShift;
        return FFmulX(i2) ^ (iShift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.C0;
        int i2 = this.ROUNDS;
        int[] iArr2 = iArr[i2];
        int i3 = i ^ iArr2[0];
        int i4 = this.C1 ^ iArr2[1];
        int i5 = this.C2 ^ iArr2[2];
        int i6 = i2 - 1;
        int iInv_mcol = iArr2[3] ^ this.C3;
        while (true) {
            byte[] bArr = Si;
            int i7 = i3 & 255;
            if (i6 <= 1) {
                int iInv_mcol2 = inv_mcol((((bArr[i7] & 255) ^ ((bArr[(iInv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << 24)) ^ iArr[i6][0];
                int iInv_mcol3 = inv_mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << 24)) ^ iArr[i6][1];
                int iInv_mcol4 = inv_mcol((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol >> 24) & 255] << 24)) ^ iArr[i6][2];
                int iInv_mcol5 = inv_mcol((bArr[(i3 >> 24) & 255] << 24) ^ (((bArr[iInv_mcol & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
                int i8 = (((bArr[iInv_mcol2 & 255] & 255) ^ ((bArr[(iInv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol3 >> 24) & 255] << 24);
                int[] iArr3 = iArr[0];
                this.C0 = i8 ^ iArr3[0];
                this.C1 = ((((bArr[iInv_mcol3 & 255] & 255) ^ ((bArr[(iInv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol5 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol4 >> 24) & 255] << 24)) ^ iArr3[1];
                this.C2 = ((((bArr[iInv_mcol4 & 255] & 255) ^ ((bArr[(iInv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol5 >> 24) & 255] << 24)) ^ iArr3[2];
                this.C3 = ((((bArr[iInv_mcol5 & 255] & 255) ^ ((bArr[(iInv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol2 >> 24) & 255] << 24)) ^ iArr3[3];
                return;
            }
            int iInv_mcol6 = inv_mcol((((bArr[i7] & 255) ^ ((bArr[(iInv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << 24)) ^ iArr[i6][0];
            int iInv_mcol7 = inv_mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr[(i5 >> 24) & 255] << 24)) ^ iArr[i6][1];
            int iInv_mcol8 = inv_mcol((((bArr[i5 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol >> 24) & 255] << 24)) ^ iArr[i6][2];
            int iInv_mcol9 = inv_mcol((bArr[(i3 >> 24) & 255] << 24) ^ (((bArr[iInv_mcol & 255] & 255) ^ ((bArr[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)));
            int i9 = i6 - 1;
            int i10 = iInv_mcol9 ^ iArr[i6][3];
            int iInv_mcol10 = inv_mcol((((bArr[iInv_mcol6 & 255] & 255) ^ ((bArr[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol7 >> 24) & 255] << 24)) ^ iArr[i9][0];
            int iInv_mcol11 = inv_mcol((((bArr[iInv_mcol7 & 255] & 255) ^ ((bArr[(iInv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i10 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol8 >> 24) & 255] << 24)) ^ iArr[i9][1];
            int iInv_mcol12 = inv_mcol((((bArr[iInv_mcol8 & 255] & 255) ^ ((bArr[(iInv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr[(i10 >> 24) & 255] << 24)) ^ iArr[i9][2];
            i6 -= 2;
            iInv_mcol = iArr[i9][3] ^ inv_mcol((((bArr[i10 & 255] & 255) ^ ((bArr[(iInv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iInv_mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr[(iInv_mcol6 >> 24) & 255] << 24));
            i3 = iInv_mcol10;
            i4 = iInv_mcol11;
            i5 = iInv_mcol12;
        }
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.C0;
        int[] iArr2 = iArr[0];
        int i2 = i ^ iArr2[0];
        int i3 = this.C1 ^ iArr2[1];
        int i4 = this.C2 ^ iArr2[2];
        int iMcol = iArr2[3] ^ this.C3;
        int i5 = 1;
        while (i5 < this.ROUNDS - 1) {
            byte[] bArr = S;
            int iMcol2 = mcol((((bArr[i2 & 255] & 255) ^ ((bArr[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr[(iMcol >> 24) & 255] << 24)) ^ iArr[i5][0];
            int iMcol3 = mcol((((bArr[i3 & 255] & 255) ^ ((bArr[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iMcol >> 16) & 255] & 255) << 16)) ^ (bArr[(i2 >> 24) & 255] << 24)) ^ iArr[i5][1];
            int iMcol4 = mcol((((bArr[i4 & 255] & 255) ^ ((bArr[(iMcol >> 8) & 255] & 255) << 8)) ^ ((bArr[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr[(i3 >> 24) & 255] << 24)) ^ iArr[i5][2];
            int iMcol5 = mcol(((((bArr[(i2 >> 8) & 255] & 255) << 8) ^ (bArr[iMcol & 255] & 255)) ^ ((bArr[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr[(i4 >> 24) & 255] << 24));
            int i6 = i5 + 1;
            int i7 = iMcol5 ^ iArr[i5][3];
            int iMcol6 = mcol((((bArr[iMcol2 & 255] & 255) ^ ((bArr[(iMcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iMcol4 >> 16) & 255] & 255) << 16)) ^ (bArr[(i7 >> 24) & 255] << 24)) ^ iArr[i6][0];
            int iMcol7 = mcol((((bArr[iMcol3 & 255] & 255) ^ ((bArr[(iMcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr[(iMcol2 >> 24) & 255] << 24)) ^ iArr[i6][1];
            int iMcol8 = mcol((((bArr[iMcol4 & 255] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iMcol2 >> 16) & 255] & 255) << 16)) ^ (bArr[(iMcol3 >> 24) & 255] << 24)) ^ iArr[i6][2];
            i5 += 2;
            iMcol = iArr[i6][3] ^ mcol((((bArr[i7 & 255] & 255) ^ ((bArr[(iMcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr[(iMcol3 >> 16) & 255] & 255) << 16)) ^ (bArr[(iMcol4 >> 24) & 255] << 24));
            i2 = iMcol6;
            i3 = iMcol7;
            i4 = iMcol8;
        }
        byte[] bArr2 = S;
        int iMcol9 = mcol((((bArr2[i2 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iMcol >> 24) & 255] << 24)) ^ iArr[i5][0];
        int iMcol10 = mcol((((bArr2[i3 & 255] & 255) ^ ((bArr2[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iMcol >> 16) & 255] & 255) << 16)) ^ (bArr2[(i2 >> 24) & 255] << 24)) ^ iArr[i5][1];
        int iMcol11 = mcol((((bArr2[i4 & 255] & 255) ^ ((bArr2[(iMcol >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i2 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i3 >> 24) & 255] << 24)) ^ iArr[i5][2];
        int iMcol12 = mcol(((((bArr2[(i2 >> 8) & 255] & 255) << 8) ^ (bArr2[iMcol & 255] & 255)) ^ ((bArr2[(i3 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i4 >> 24) & 255] << 24)) ^ iArr[i5][3];
        int i8 = (((bArr2[iMcol9 & 255] & 255) ^ ((bArr2[(iMcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iMcol11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iMcol12 >> 24) & 255] << 24);
        int[] iArr3 = iArr[i5 + 1];
        this.C0 = iArr3[0] ^ i8;
        this.C1 = ((((bArr2[iMcol10 & 255] & 255) ^ ((bArr2[(iMcol11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iMcol12 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iMcol9 >> 24) & 255] << 24)) ^ iArr3[1];
        this.C2 = ((((bArr2[iMcol11 & 255] & 255) ^ ((bArr2[(iMcol12 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iMcol9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iMcol10 >> 24) & 255] << 24)) ^ iArr3[2];
        this.C3 = ((((bArr2[iMcol12 & 255] & 255) ^ ((bArr2[(iMcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(iMcol10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(iMcol11 >> 24) & 255] << 24)) ^ iArr3[3];
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i + 7, 4);
        if (i == 4) {
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt;
            int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt2;
            int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt3;
            int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt4;
            for (int i2 = 1; i2 <= 10; i2++) {
                iLittleEndianToInt ^= subWord(shift(iLittleEndianToInt4, 8)) ^ rcon[i2 - 1];
                int[] iArr2 = iArr[i2];
                iArr2[0] = iLittleEndianToInt;
                iLittleEndianToInt2 ^= iLittleEndianToInt;
                iArr2[1] = iLittleEndianToInt2;
                iLittleEndianToInt3 ^= iLittleEndianToInt2;
                iArr2[2] = iLittleEndianToInt3;
                iLittleEndianToInt4 ^= iLittleEndianToInt3;
                iArr2[3] = iLittleEndianToInt4;
            }
        } else if (i == 6) {
            int iLittleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt5;
            int iLittleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt6;
            int iLittleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt7;
            int iLittleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt8;
            int iLittleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt9;
            int iLittleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt10;
            int iSubWord = iLittleEndianToInt5 ^ (subWord(shift(iLittleEndianToInt10, 8)) ^ 1);
            int[] iArr3 = iArr[1];
            iArr3[2] = iSubWord;
            int i3 = iLittleEndianToInt6 ^ iSubWord;
            iArr3[3] = i3;
            int i4 = iLittleEndianToInt7 ^ i3;
            int[] iArr4 = iArr[2];
            iArr4[0] = i4;
            int i5 = iLittleEndianToInt8 ^ i4;
            iArr4[1] = i5;
            int i6 = iLittleEndianToInt9 ^ i5;
            iArr4[2] = i6;
            int i7 = iLittleEndianToInt10 ^ i6;
            iArr4[3] = i7;
            int i8 = 2;
            for (int i9 = 3; i9 < 12; i9 += 3) {
                int iSubWord2 = iSubWord ^ (subWord(shift(i7, 8)) ^ i8);
                int[] iArr5 = iArr[i9];
                iArr5[0] = iSubWord2;
                int i10 = i3 ^ iSubWord2;
                iArr5[1] = i10;
                int i11 = i4 ^ i10;
                iArr5[2] = i11;
                int i12 = i5 ^ i11;
                iArr5[3] = i12;
                int i13 = i6 ^ i12;
                int i14 = i9 + 1;
                int[] iArr6 = iArr[i14];
                iArr6[0] = i13;
                int i15 = i7 ^ i13;
                iArr6[1] = i15;
                int iSubWord3 = subWord(shift(i15, 8)) ^ (i8 << 1);
                i8 <<= 2;
                iSubWord = iSubWord2 ^ iSubWord3;
                int[] iArr7 = iArr[i14];
                iArr7[2] = iSubWord;
                i3 = i10 ^ iSubWord;
                iArr7[3] = i3;
                i4 = i11 ^ i3;
                int[] iArr8 = iArr[i9 + 2];
                iArr8[0] = i4;
                i5 = i12 ^ i4;
                iArr8[1] = i5;
                i6 = i13 ^ i5;
                iArr8[2] = i6;
                i7 = i15 ^ i6;
                iArr8[3] = i7;
            }
            int iSubWord4 = (subWord(shift(i7, 8)) ^ i8) ^ iSubWord;
            int[] iArr9 = iArr[12];
            iArr9[0] = iSubWord4;
            int i16 = iSubWord4 ^ i3;
            iArr9[1] = i16;
            int i17 = i16 ^ i4;
            iArr9[2] = i17;
            iArr9[3] = i17 ^ i5;
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int iLittleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt11;
            int iLittleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt12;
            int iLittleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt13;
            int iLittleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt14;
            int iLittleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt15;
            int iLittleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt16;
            int iLittleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = iLittleEndianToInt17;
            int iLittleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = iLittleEndianToInt18;
            int i18 = 1;
            for (int i19 = 2; i19 < 14; i19 += 2) {
                int iSubWord5 = subWord(shift(iLittleEndianToInt18, 8)) ^ i18;
                i18 <<= 1;
                iLittleEndianToInt11 ^= iSubWord5;
                int[] iArr10 = iArr[i19];
                iArr10[0] = iLittleEndianToInt11;
                iLittleEndianToInt12 ^= iLittleEndianToInt11;
                iArr10[1] = iLittleEndianToInt12;
                iLittleEndianToInt13 ^= iLittleEndianToInt12;
                iArr10[2] = iLittleEndianToInt13;
                iLittleEndianToInt14 ^= iLittleEndianToInt13;
                iArr10[3] = iLittleEndianToInt14;
                iLittleEndianToInt15 ^= subWord(iLittleEndianToInt14);
                int[] iArr11 = iArr[i19 + 1];
                iArr11[0] = iLittleEndianToInt15;
                iLittleEndianToInt16 ^= iLittleEndianToInt15;
                iArr11[1] = iLittleEndianToInt16;
                iLittleEndianToInt17 ^= iLittleEndianToInt16;
                iArr11[2] = iLittleEndianToInt17;
                iLittleEndianToInt18 ^= iLittleEndianToInt17;
                iArr11[3] = iLittleEndianToInt18;
            }
            int iSubWord6 = (subWord(shift(iLittleEndianToInt18, 8)) ^ i18) ^ iLittleEndianToInt11;
            int[] iArr12 = iArr[14];
            iArr12[0] = iSubWord6;
            int i20 = iSubWord6 ^ iLittleEndianToInt12;
            iArr12[1] = i20;
            int i21 = i20 ^ iLittleEndianToInt13;
            iArr12[2] = i21;
            iArr12[3] = i21 ^ iLittleEndianToInt14;
        }
        if (!z) {
            for (int i22 = 1; i22 < this.ROUNDS; i22++) {
                for (int i23 = 0; i23 < 4; i23++) {
                    int[] iArr13 = iArr[i22];
                    iArr13[i23] = inv_mcol(iArr13[i23]);
                }
            }
        }
        return iArr;
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = this.C0;
        bArr[i] = (byte) i2;
        bArr[i + 1] = (byte) (i2 >> 8);
        bArr[i + 2] = (byte) (i2 >> 16);
        bArr[i + 3] = (byte) (i2 >> 24);
        int i3 = this.C1;
        bArr[i + 4] = (byte) i3;
        bArr[i + 5] = (byte) (i3 >> 8);
        bArr[i + 6] = (byte) (i3 >> 16);
        bArr[i + 7] = (byte) (i3 >> 24);
        int i4 = this.C2;
        bArr[i + 8] = (byte) i4;
        bArr[i + 9] = (byte) (i4 >> 8);
        bArr[i + 10] = (byte) (i4 >> 16);
        bArr[i + 11] = (byte) (i4 >> 24);
        int i5 = this.C3;
        bArr[i + 12] = (byte) i5;
        bArr[i + 13] = (byte) (i5 >> 8);
        bArr[i + 14] = (byte) (i5 >> 16);
        bArr[i + 15] = (byte) (i5 >> 24);
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.C0 = ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16) | (bArr[i + 3] << 24);
        this.C1 = ((bArr[i + 5] & 255) << 8) | (bArr[i + 4] & 255) | ((bArr[i + 6] & 255) << 16) | (bArr[i + 7] << 24);
        this.C2 = ((bArr[i + 9] & 255) << 8) | (bArr[i + 8] & 255) | ((bArr[i + 10] & 255) << 16) | (bArr[i + 11] << 24);
        int i2 = ((bArr[i + 13] & 255) << 8) | (bArr[i + 12] & 255);
        this.C3 = (bArr[i + 15] << 24) | i2 | ((bArr[i + 14] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        int[][] iArr = this.WorkingKey;
        if (z) {
            encryptBlock(iArr);
        } else {
            decryptBlock(iArr);
        }
        packBlock(bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
