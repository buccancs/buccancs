package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;
    private static final byte[][] P = {new byte[]{SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 103, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, -24, 4, -3, -93, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.GET_CALIB_DUMP_COMMAND, ShimmerObject.START_LOGGING_ONLY_COMMAND, -128, ShimmerObject.GET_CENTER_COMMAND, -28, -35, -47, 56, 13, -58, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.SET_CALIB_DUMP_COMMAND, 24, -9, -20, ShimmerObject.GET_BAUD_RATE_COMMAND, 67, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 55, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, -6, 19, -108, 72, -14, -48, ShimmerObject.SET_CRC_COMMAND, ShimmerObject.SET_BLINK_LED, ShimmerObject.GET_NSHIMMER_COMMAND, 84, -33, 35, 25, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, 61, 89, -13, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, ShimmerObject.SET_NSHIMMER_COMMAND, ShimmerObject.GET_EXG_REGS_COMMAND, 1, ShimmerObject.NSHIMMER_RESPONSE, ShimmerObject.GET_FW_VERSION_COMMAND, -39, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.SET_EXPID_COMMAND, -90, -21, -91, -66, 22, 12, -29, ShimmerObject.SET_EXG_REGS_COMMAND, -64, ShimmerObject.SET_INFOMEM_COMMAND, 58, -11, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 44, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, 11, ByteSourceJsonBootstrapper.UTF8_BOM_2, 78, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, 83, ShimmerObject.SET_BAUD_RATE_COMMAND, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -15, -31, -26, -67, 69, -30, -12, -74, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, -52, -107, 3, 86, -44, 28, Ascii.RS, -41, -5, -61, ShimmerObject.GET_INFOMEM_COMMAND, -75, -23, -49, ByteSourceJsonBootstrapper.UTF8_BOM_3, -70, -22, ShimmerObject.CENTER_RESPONSE, 57, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, 51, -55, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, 9, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, -51, -7, -40, -27, -59, -71, 77, 68, 8, ShimmerObject.CONFIGTIME_RESPONSE, -25, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, Ascii.GS, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, -19, 6, ShimmerObject.START_SDBT_COMMAND, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, -46, 65, ShimmerObject.GET_SHIMMERNAME_COMMAND, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, 17, ShimmerObject.BLINK_LED_RESPONSE, -62, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.RWC_RESPONSE, 32, -10, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -1, ShimmerObject.TEST_CONNECTION_COMMAND, 92, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, -98, ShimmerObject.UPD_SDLOG_CFG_COMMAND, 82, 27, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, 10, ByteSourceJsonBootstrapper.UTF8_BOM_1, ShimmerObject.GET_RWC_COMMAND, ShimmerObject.SET_CONFIGTIME_COMMAND, 73, -18, ShimmerObject.ALL_CALIBRATION_RESPONSE, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, ShimmerObject.SET_RWC_COMMAND, 59, 71, ShimmerObject.GET_CONFIGTIME_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, 70, -42, 62, 105, 100, ShimmerObject.ECG_CALIBRATION_RESPONSE, -50, -53, ShimmerObject.FW_VERSION_RESPONSE, -4, ShimmerObject.STOP_SDBT_COMMAND, 5, ShimmerObject.SHIMMERNAME_RESPONSE, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 127, -43, 26, 75, 14, -89, 90, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, 20, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, ShimmerObject.DIR_RESPONSE, 60, 76, 2, -72, -38, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, 23, 85, Ascii.US, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.EXPID_RESPONSE, 87, -57, ShimmerObject.INFOMEM_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, -73, -60, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.GET_EXPID_COMMAND, 21, 34, 18, 88, 7, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, -34, 104, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, ShimmerObject.SET_TEST, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 64, -36, -2, ShimmerObject.GET_BLINK_LED, -92, -54, 16, 33, -16, -45, 93, 15, 0, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, -99, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 66, 74, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -63, ShimmerObject.ROUTINE_COMMUNICATION}, new byte[]{ShimmerObject.GET_TRIAL_CONFIG_COMMAND, -13, -58, -12, -37, ShimmerObject.GET_SHIMMERNAME_COMMAND, -5, -56, 74, -45, -26, ShimmerObject.BAUD_RATE_RESPONSE, 69, ShimmerObject.EXPID_RESPONSE, -24, 75, -42, ShimmerObject.GET_BLINK_LED, -40, -3, 55, ShimmerObject.STATUS_RESPONSE, -15, -31, ShimmerObject.SET_BLINK_LED, 15, -8, 27, ShimmerObject.GET_CONFIGTIME_COMMAND, -6, 6, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -70, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, ShimmerObject.INSTREAM_CMD_RESPONSE, 0, PSSSigner.TRAILER_IMPLICIT, -99, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, -63, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, 14, -128, 93, -46, -43, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, ShimmerObject.GET_NSHIMMER_COMMAND, 7, 20, -75, ShimmerObject.RWC_RESPONSE, 44, -93, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 76, 84, ShimmerObject.START_LOGGING_ONLY_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 56, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, -67, 90, -4, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.GET_BAUD_RATE_COMMAND, 66, -9, 16, ShimmerObject.SET_EXPID_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.SET_INFOMEM_COMMAND, 19, -107, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -57, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, 70, 59, ShimmerObject.START_SDBT_COMMAND, -54, -29, ShimmerObject.SET_CONFIGTIME_COMMAND, -53, 17, -48, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -72, -90, ShimmerObject.NSHIMMER_RESPONSE, 32, -1, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.CENTER_RESPONSE, -61, -52, 3, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, 8, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -25, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, -30, ShimmerObject.SET_SHIMMERNAME_COMMAND, 12, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.SET_NSHIMMER_COMMAND, 65, 58, -22, -71, -28, ShimmerObject.GET_CALIB_DUMP_COMMAND, -92, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.GET_EXPID_COMMAND, -38, ShimmerObject.SHIMMERNAME_RESPONSE, 23, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, -108, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, Ascii.GS, 61, -16, -34, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 11, ShimmerObject.GET_STATUS_COMMAND, -89, 28, ByteSourceJsonBootstrapper.UTF8_BOM_1, -47, 83, 62, ShimmerObject.SET_RWC_COMMAND, 51, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, -20, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.ECG_CALIBRATION_RESPONSE, 73, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.DIR_RESPONSE, -18, 33, -60, 26, -21, -39, -59, 57, ShimmerObject.RSP_CALIB_DUMP_COMMAND, -51, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.SET_CRC_COMMAND, 1, 24, 35, -35, Ascii.US, 78, ShimmerObject.ALL_CALIBRATION_RESPONSE, -7, 72, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, -14, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.GET_CENTER_COMMAND, 92, 88, 25, ShimmerObject.INFOMEM_RESPONSE, -27, ShimmerObject.SET_CALIB_DUMP_COMMAND, 87, 103, 127, 5, 100, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.GET_EXG_REGS_COMMAND, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, ShimmerObject.ROUTINE_COMMUNICATION, 77, 67, 105, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, ShimmerObject.GET_FW_VERSION_COMMAND, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 21, 89, ShimmerObject.SET_TEST, 10, -98, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 71, -33, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.SET_BAUD_RATE_COMMAND, -49, -36, 34, -55, -64, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.GET_DIR_COMMAND, -44, -19, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 18, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, 13, 82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 2, ShimmerObject.FW_VERSION_RESPONSE, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, -41, ShimmerObject.SET_EXG_REGS_COMMAND, Ascii.RS, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 4, -10, -62, 22, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.CONFIGTIME_RESPONSE, 86, 85, 9, -66, ShimmerObject.GET_RWC_COMMAND}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            byte[][] bArr = P;
            int i2 = bArr[0][i] & 255;
            iArr[0] = i2;
            iArr2[0] = Mx_X(i2) & 255;
            iArr3[0] = Mx_Y(i2) & 255;
            int i3 = bArr[1][i] & 255;
            iArr[1] = i3;
            iArr2[1] = Mx_X(i3) & 255;
            int iMx_Y = Mx_Y(i3) & 255;
            iArr3[1] = iMx_Y;
            this.gMDS0[i] = (iMx_Y << 24) | iArr[1] | (iArr2[1] << 8) | (iMx_Y << 16);
            int[] iArr4 = this.gMDS1;
            int i4 = iArr3[0];
            iArr4[i] = i4 | (i4 << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            int[] iArr5 = this.gMDS2;
            int i5 = iArr2[1];
            int i6 = iArr3[1];
            iArr5[i] = (iArr[1] << 16) | i5 | (i6 << 8) | (i6 << 24);
            int[] iArr6 = this.gMDS3;
            int i7 = iArr2[0];
            iArr6[i] = (i7 << 24) | (iArr[0] << 8) | i7 | (iArr3[0] << 16);
        }
    }

    private void Bits32ToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }

    private int BytesTo32Bits(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private int F32(int i, int[] iArr) {
        int i2;
        int i3;
        int iB0 = b0(i);
        int iB1 = b1(i);
        int iB2 = b2(i);
        int iB3 = b3(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = this.k64Cnt & 3;
        if (i8 != 0) {
            if (i8 == 1) {
                int[] iArr2 = this.gMDS0;
                byte[][] bArr = P;
                i2 = (iArr2[(bArr[0][iB0] & 255) ^ b0(i4)] ^ this.gMDS1[(bArr[0][iB1] & 255) ^ b1(i4)]) ^ this.gMDS2[(bArr[1][iB2] & 255) ^ b2(i4)];
                i3 = this.gMDS3[(bArr[1][iB3] & 255) ^ b3(i4)];
                return i2 ^ i3;
            }
            if (i8 != 2) {
                if (i8 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.gMDS0;
            byte[][] bArr2 = P;
            byte[] bArr3 = bArr2[0];
            i2 = (iArr3[(bArr3[(bArr3[iB0] & 255) ^ b0(i5)] & 255) ^ b0(i4)] ^ this.gMDS1[(bArr2[0][(bArr2[1][iB1] & 255) ^ b1(i5)] & 255) ^ b1(i4)]) ^ this.gMDS2[(bArr2[1][(bArr2[0][iB2] & 255) ^ b2(i5)] & 255) ^ b2(i4)];
            int[] iArr4 = this.gMDS3;
            byte[] bArr4 = bArr2[1];
            i3 = iArr4[(bArr4[(bArr4[iB3] & 255) ^ b3(i5)] & 255) ^ b3(i4)];
            return i2 ^ i3;
        }
        byte[][] bArr5 = P;
        iB0 = (bArr5[1][iB0] & 255) ^ b0(i7);
        iB1 = (bArr5[0][iB1] & 255) ^ b1(i7);
        iB2 = (bArr5[0][iB2] & 255) ^ b2(i7);
        iB3 = (bArr5[1][iB3] & 255) ^ b3(i7);
        byte[][] bArr6 = P;
        iB0 = (bArr6[1][iB0] & 255) ^ b0(i6);
        iB1 = (bArr6[1][iB1] & 255) ^ b1(i6);
        iB2 = (bArr6[0][iB2] & 255) ^ b2(i6);
        iB3 = (bArr6[0][iB3] & 255) ^ b3(i6);
        int[] iArr32 = this.gMDS0;
        byte[][] bArr22 = P;
        byte[] bArr32 = bArr22[0];
        i2 = (iArr32[(bArr32[(bArr32[iB0] & 255) ^ b0(i5)] & 255) ^ b0(i4)] ^ this.gMDS1[(bArr22[0][(bArr22[1][iB1] & 255) ^ b1(i5)] & 255) ^ b1(i4)]) ^ this.gMDS2[(bArr22[1][(bArr22[0][iB2] & 255) ^ b2(i5)] & 255) ^ b2(i4)];
        int[] iArr42 = this.gMDS3;
        byte[] bArr42 = bArr22[1];
        i3 = iArr42[(bArr42[(bArr42[iB3] & 255) ^ b3(i5)] & 255) ^ b3(i4)];
        return i2 ^ i3;
    }

    private int Fe32_0(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 24) & 255) * 2) + 513] ^ ((iArr[(i & 255) * 2] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 16) & 255) * 2) + 513] ^ ((iArr[((i >>> 24) & 255) * 2] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    private int LFSR2(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? 180 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int i) {
        return i ^ LFSR2(i);
    }

    private int Mx_Y(int i) {
        return LFSR2(i) ^ (LFSR1(i) ^ i);
    }

    private int RS_MDS_Encode(int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = RS_rem(i2);
        }
        int iRS_rem = i ^ i2;
        for (int i4 = 0; i4 < 4; i4++) {
            iRS_rem = RS_rem(iRS_rem);
        }
        return iRS_rem;
    }

    private int RS_rem(int i) {
        int i2 = i >>> 24;
        int i3 = i2 & 255;
        int i4 = ((i3 << 1) ^ ((i2 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i5 = ((i3 >>> 1) ^ ((i2 & 1) != 0 ? CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 : 0)) ^ i4;
        return ((((i << 8) ^ (i5 << 24)) ^ (i4 << 16)) ^ (i5 << 8)) ^ i3;
    }

    private int b0(int i) {
        return i & 255;
    }

    private int b1(int i) {
        return (i >>> 8) & 255;
    }

    private int b2(int i) {
        return (i >>> 16) & 255;
    }

    private int b3(int i) {
        return (i >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iBytesTo32Bits = BytesTo32Bits(bArr, i) ^ this.gSubKeys[4];
        int iBytesTo32Bits2 = BytesTo32Bits(bArr, i + 4) ^ this.gSubKeys[5];
        int iBytesTo32Bits3 = BytesTo32Bits(bArr, i + 8) ^ this.gSubKeys[6];
        int iBytesTo32Bits4 = BytesTo32Bits(bArr, i + 12) ^ this.gSubKeys[7];
        int i3 = 39;
        for (int i4 = 0; i4 < 16; i4 += 2) {
            int iFe32_0 = Fe32_0(iBytesTo32Bits);
            int iFe32_3 = Fe32_3(iBytesTo32Bits2);
            int[] iArr = this.gSubKeys;
            int i5 = iBytesTo32Bits4 ^ (((iFe32_3 * 2) + iFe32_0) + iArr[i3]);
            iBytesTo32Bits3 = ((iBytesTo32Bits3 >>> 31) | (iBytesTo32Bits3 << 1)) ^ ((iFe32_0 + iFe32_3) + iArr[i3 - 1]);
            iBytesTo32Bits4 = (i5 << 31) | (i5 >>> 1);
            int iFe32_02 = Fe32_0(iBytesTo32Bits3);
            int iFe32_32 = Fe32_3(iBytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i6 = i3 - 3;
            int i7 = iBytesTo32Bits2 ^ (((iFe32_32 * 2) + iFe32_02) + iArr2[i3 - 2]);
            i3 -= 4;
            iBytesTo32Bits = ((iBytesTo32Bits >>> 31) | (iBytesTo32Bits << 1)) ^ ((iFe32_02 + iFe32_32) + iArr2[i6]);
            iBytesTo32Bits2 = (i7 << 31) | (i7 >>> 1);
        }
        Bits32ToBytes(this.gSubKeys[0] ^ iBytesTo32Bits3, bArr2, i2);
        Bits32ToBytes(iBytesTo32Bits4 ^ this.gSubKeys[1], bArr2, i2 + 4);
        Bits32ToBytes(this.gSubKeys[2] ^ iBytesTo32Bits, bArr2, i2 + 8);
        Bits32ToBytes(this.gSubKeys[3] ^ iBytesTo32Bits2, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iBytesTo32Bits = BytesTo32Bits(bArr, i) ^ this.gSubKeys[0];
        int iBytesTo32Bits2 = BytesTo32Bits(bArr, i + 4) ^ this.gSubKeys[1];
        int iBytesTo32Bits3 = BytesTo32Bits(bArr, i + 8) ^ this.gSubKeys[2];
        int iBytesTo32Bits4 = BytesTo32Bits(bArr, i + 12) ^ this.gSubKeys[3];
        int i3 = 8;
        for (int i4 = 0; i4 < 16; i4 += 2) {
            int iFe32_0 = Fe32_0(iBytesTo32Bits);
            int iFe32_3 = Fe32_3(iBytesTo32Bits2);
            int[] iArr = this.gSubKeys;
            int i5 = iBytesTo32Bits3 ^ ((iFe32_0 + iFe32_3) + iArr[i3]);
            iBytesTo32Bits3 = (i5 << 31) | (i5 >>> 1);
            iBytesTo32Bits4 = ((iBytesTo32Bits4 >>> 31) | (iBytesTo32Bits4 << 1)) ^ ((iFe32_0 + (iFe32_3 * 2)) + iArr[i3 + 1]);
            int iFe32_02 = Fe32_0(iBytesTo32Bits3);
            int iFe32_32 = Fe32_3(iBytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i6 = i3 + 3;
            int i7 = iBytesTo32Bits ^ ((iFe32_02 + iFe32_32) + iArr2[i3 + 2]);
            iBytesTo32Bits = (i7 << 31) | (i7 >>> 1);
            i3 += 4;
            iBytesTo32Bits2 = ((iBytesTo32Bits2 >>> 31) | (iBytesTo32Bits2 << 1)) ^ ((iFe32_02 + (iFe32_32 * 2)) + iArr2[i6]);
        }
        Bits32ToBytes(this.gSubKeys[4] ^ iBytesTo32Bits3, bArr2, i2);
        Bits32ToBytes(iBytesTo32Bits4 ^ this.gSubKeys[5], bArr2, i2 + 4);
        Bits32ToBytes(this.gSubKeys[6] ^ iBytesTo32Bits, bArr2, i2 + 8);
        Bits32ToBytes(this.gSubKeys[7] ^ iBytesTo32Bits2, bArr2, i2 + 12);
    }

    private void setKey(byte[] bArr) {
        int iB0;
        int iB1;
        int iB2;
        int iB3;
        int iB22;
        int iB12;
        int iB02;
        int iB32;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        int i = this.k64Cnt;
        if (i < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (i > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (int i2 = 0; i2 < this.k64Cnt; i2++) {
            int i3 = i2 * 8;
            iArr[i2] = BytesTo32Bits(bArr, i3);
            int iBytesTo32Bits = BytesTo32Bits(bArr, i3 + 4);
            iArr2[i2] = iBytesTo32Bits;
            iArr3[(this.k64Cnt - 1) - i2] = RS_MDS_Encode(iArr[i2], iBytesTo32Bits);
        }
        for (int i4 = 0; i4 < 20; i4++) {
            int i5 = SK_STEP * i4;
            int iF32 = F32(i5, iArr);
            int iF322 = F32(i5 + 16843009, iArr2);
            int i6 = (iF322 >>> 24) | (iF322 << 8);
            int i7 = iF32 + i6;
            int[] iArr4 = this.gSubKeys;
            int i8 = i4 * 2;
            iArr4[i8] = i7;
            int i9 = i7 + i6;
            iArr4[i8 + 1] = (i9 << 9) | (i9 >>> 23);
        }
        int i10 = iArr3[0];
        int i11 = iArr3[1];
        int i12 = 2;
        int i13 = iArr3[2];
        int i14 = iArr3[3];
        this.gSBox = new int[1024];
        int i15 = 0;
        while (i15 < 256) {
            int i16 = this.k64Cnt & 3;
            if (i16 != 0) {
                if (i16 == 1) {
                    int[] iArr5 = this.gSBox;
                    int i17 = i15 * 2;
                    int[] iArr6 = this.gMDS0;
                    byte[][] bArr2 = P;
                    iArr5[i17] = iArr6[(bArr2[0][i15] & 255) ^ b0(i10)];
                    this.gSBox[i17 + 1] = this.gMDS1[(bArr2[0][i15] & 255) ^ b1(i10)];
                    this.gSBox[i17 + 512] = this.gMDS2[(bArr2[1][i15] & 255) ^ b2(i10)];
                    this.gSBox[i17 + 513] = this.gMDS3[(bArr2[1][i15] & 255) ^ b3(i10)];
                } else if (i16 == i12) {
                    iB32 = i15;
                    iB02 = iB32;
                    iB12 = iB02;
                    iB22 = iB12;
                    int[] iArr7 = this.gSBox;
                    int i18 = i15 * 2;
                    int[] iArr8 = this.gMDS0;
                    byte[][] bArr3 = P;
                    byte[] bArr4 = bArr3[0];
                    iArr7[i18] = iArr8[(bArr4[(bArr4[iB02] & 255) ^ b0(i11)] & 255) ^ b0(i10)];
                    this.gSBox[i18 + 1] = this.gMDS1[(bArr3[0][(bArr3[1][iB12] & 255) ^ b1(i11)] & 255) ^ b1(i10)];
                    this.gSBox[i18 + 512] = this.gMDS2[(bArr3[1][(bArr3[0][iB22] & 255) ^ b2(i11)] & 255) ^ b2(i10)];
                    int[] iArr9 = this.gMDS3;
                    byte[] bArr5 = bArr3[1];
                    this.gSBox[i18 + 513] = iArr9[(bArr5[(bArr5[iB32] & 255) ^ b3(i11)] & 255) ^ b3(i10)];
                } else if (i16 == 3) {
                    iB3 = i15;
                    iB0 = iB3;
                    iB1 = iB0;
                    iB2 = iB1;
                }
                i15++;
                i12 = 2;
            } else {
                byte[][] bArr6 = P;
                iB0 = (bArr6[1][i15] & 255) ^ b0(i14);
                iB1 = (bArr6[0][i15] & 255) ^ b1(i14);
                iB2 = (bArr6[0][i15] & 255) ^ b2(i14);
                iB3 = (bArr6[1][i15] & 255) ^ b3(i14);
            }
            byte[][] bArr7 = P;
            iB02 = (bArr7[1][iB0] & 255) ^ b0(i13);
            iB12 = (bArr7[1][iB1] & 255) ^ b1(i13);
            iB22 = (bArr7[0][iB2] & 255) ^ b2(i13);
            iB32 = (bArr7[0][iB3] & 255) ^ b3(i13);
            int[] iArr72 = this.gSBox;
            int i182 = i15 * 2;
            int[] iArr82 = this.gMDS0;
            byte[][] bArr32 = P;
            byte[] bArr42 = bArr32[0];
            iArr72[i182] = iArr82[(bArr42[(bArr42[iB02] & 255) ^ b0(i11)] & 255) ^ b0(i10)];
            this.gSBox[i182 + 1] = this.gMDS1[(bArr32[0][(bArr32[1][iB12] & 255) ^ b1(i11)] & 255) ^ b1(i10)];
            this.gSBox[i182 + 512] = this.gMDS2[(bArr32[1][(bArr32[0][iB22] & 255) ^ b2(i11)] & 255) ^ b2(i10)];
            int[] iArr92 = this.gMDS3;
            byte[] bArr52 = bArr32[1];
            this.gSBox[i182 + 513] = iArr92[(bArr52[(bArr52[iB32] & 255) ^ b3(i11)] & 255) ^ b3(i10)];
            i15++;
            i12 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = key;
        this.k64Cnt = key.length / 8;
        setKey(key);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
