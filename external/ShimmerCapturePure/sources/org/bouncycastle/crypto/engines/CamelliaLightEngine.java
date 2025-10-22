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

/* loaded from: classes5.dex */
public class CamelliaLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int MASK8 = 255;
    private static final int[] SIGMA = {-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final byte[] SBOX1 = {ShimmerObject.START_SDBT_COMMAND, ShimmerObject.SET_NSHIMMER_COMMAND, 44, -20, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, ShimmerObject.EMG_CALIBRATION_RESPONSE, -64, -27, -28, ShimmerObject.SET_CONFIGTIME_COMMAND, 87, ShimmerObject.BUFFER_SIZE_RESPONSE, -22, 12, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 65, 35, ByteSourceJsonBootstrapper.UTF8_BOM_1, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, 69, 25, -91, 33, -19, 14, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, 78, Ascii.GS, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.START_LOGGING_ONLY_COMMAND, -67, ShimmerObject.CONFIGTIME_RESPONSE, -72, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.SET_EXPID_COMMAND, -21, Ascii.US, -50, 62, ShimmerObject.SET_BLINK_LED, -36, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -59, 11, 26, -90, -31, 57, -54, -43, 71, 93, 61, -39, 1, 90, -42, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 86, ShimmerObject.GET_BAUD_RATE_COMMAND, 77, ShimmerObject.SET_CRC_COMMAND, 13, ShimmerObject.GET_CALIB_DUMP_COMMAND, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, -5, -52, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.TRIAL_CONFIG_RESPONSE, 18, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 32, -16, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.GET_NSHIMMER_COMMAND, ShimmerObject.RSP_CALIB_DUMP_COMMAND, -33, 76, -53, -62, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.GET_EXPID_COMMAND, ShimmerObject.SET_CENTER_COMMAND, 5, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, -73, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, ShimmerObject.BLINK_LED_RESPONSE, -47, 23, 4, -41, 20, 88, 58, ShimmerObject.SET_EXG_REGS_COMMAND, -34, 27, 17, 28, ShimmerObject.GET_BLINK_LED, 15, ShimmerObject.UPD_SDLOG_CFG_COMMAND, 22, 83, 24, -14, 34, -2, 68, -49, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, -61, -75, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.GET_RWC_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, 8, -24, ShimmerObject.SET_TEST, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -4, 105, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, -48, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, ShimmerObject.EXPID_RESPONSE, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.GET_DIR_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.STOP_SDBT_COMMAND, 84, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, Ascii.RS, -107, ShimmerObject.ROUTINE_COMMUNICATION, -1, 100, -46, 16, -60, 0, 72, -93, -9, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, -37, ShimmerObject.INSTREAM_CMD_RESPONSE, 3, -26, -38, 9, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, -35, -108, ShimmerObject.GET_CONFIGTIME_COMMAND, 92, ShimmerObject.NSHIMMER_RESPONSE, 2, -51, 74, ShimmerObject.RWC_RESPONSE, 51, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 103, -10, -13, -99, 127, ByteSourceJsonBootstrapper.UTF8_BOM_3, -30, 82, ShimmerObject.UPD_CALIB_DUMP_COMMAND, -40, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, -56, 55, -58, 59, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, 75, 19, -66, ShimmerObject.GET_EXG_REGS_COMMAND, ShimmerObject.GET_FW_VERSION_COMMAND, -23, ShimmerObject.SET_SHIMMERNAME_COMMAND, -89, ShimmerObject.SET_INFOMEM_COMMAND, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, PSSSigner.TRAILER_IMPLICIT, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -11, -7, -74, ShimmerObject.FW_VERSION_RESPONSE, -3, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, 89, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_CALIB_DUMP_COMMAND, 6, ShimmerObject.SET_BAUD_RATE_COMMAND, -25, 70, ShimmerObject.STATUS_RESPONSE, -70, -44, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 66, ShimmerObject.DIR_RESPONSE, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, ShimmerObject.INFOMEM_RESPONSE, -6, ShimmerObject.GET_STATUS_COMMAND, 7, -71, 85, -8, -18, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 10, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 73, ShimmerObject.ECG_CALIBRATION_RESPONSE, 104, 60, 56, -15, -92, 64, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -45, ShimmerObject.GET_SHIMMERNAME_COMMAND, ByteSourceJsonBootstrapper.UTF8_BOM_2, -55, 67, -63, 21, -29, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, -12, ShimmerObject.CENTER_RESPONSE, -57, -128, -98};
    private boolean _keyis128;
    private boolean initialized;
    private int[] subkey = new int[96];
    private int[] kw = new int[8];
    private int[] ke = new int[12];
    private int[] state = new int[4];

    private static void decroldq(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 2;
        int i5 = i2 + 1;
        int i6 = 32 - i;
        iArr2[i4] = (iArr[i2] << i) | (iArr[i5] >>> i6);
        int i7 = i3 + 3;
        int i8 = i2 + 2;
        iArr2[i7] = (iArr[i5] << i) | (iArr[i8] >>> i6);
        int i9 = i2 + 3;
        iArr2[i3] = (iArr[i8] << i) | (iArr[i9] >>> i6);
        int i10 = i3 + 1;
        iArr2[i10] = (iArr[i9] << i) | (iArr[i2] >>> i6);
        iArr[i2] = iArr2[i4];
        iArr[i5] = iArr2[i7];
        iArr[i8] = iArr2[i3];
        iArr[i9] = iArr2[i10];
    }

    private static void decroldqo32(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 2;
        int i5 = i2 + 1;
        int i6 = i - 32;
        int i7 = i2 + 2;
        int i8 = 64 - i;
        iArr2[i4] = (iArr[i5] << i6) | (iArr[i7] >>> i8);
        int i9 = i3 + 3;
        int i10 = i2 + 3;
        iArr2[i9] = (iArr[i7] << i6) | (iArr[i10] >>> i8);
        iArr2[i3] = (iArr[i10] << i6) | (iArr[i2] >>> i8);
        int i11 = i3 + 1;
        iArr2[i11] = (iArr[i5] >>> i8) | (iArr[i2] << i6);
        iArr[i2] = iArr2[i4];
        iArr[i5] = iArr2[i9];
        iArr[i7] = iArr2[i3];
        iArr[i10] = iArr2[i11];
    }

    private static int leftRotate(int i, int i2) {
        return (i << i2) + (i >>> (32 - i2));
    }

    private static int rightRotate(int i, int i2) {
        return (i >>> i2) + (i << (32 - i2));
    }

    private static void roldq(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i2 + 1;
        int i5 = 32 - i;
        iArr2[i3] = (iArr[i2] << i) | (iArr[i4] >>> i5);
        int i6 = i3 + 1;
        int i7 = i2 + 2;
        iArr2[i6] = (iArr[i4] << i) | (iArr[i7] >>> i5);
        int i8 = i3 + 2;
        int i9 = i2 + 3;
        iArr2[i8] = (iArr[i7] << i) | (iArr[i9] >>> i5);
        int i10 = i3 + 3;
        iArr2[i10] = (iArr[i9] << i) | (iArr[i2] >>> i5);
        iArr[i2] = iArr2[i3];
        iArr[i4] = iArr2[i6];
        iArr[i7] = iArr2[i8];
        iArr[i9] = iArr2[i10];
    }

    private static void roldqo32(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i2 + 1;
        int i5 = i - 32;
        int i6 = i2 + 2;
        int i7 = 64 - i;
        iArr2[i3] = (iArr[i4] << i5) | (iArr[i6] >>> i7);
        int i8 = i3 + 1;
        int i9 = i2 + 3;
        iArr2[i8] = (iArr[i6] << i5) | (iArr[i9] >>> i7);
        int i10 = i3 + 2;
        iArr2[i10] = (iArr[i9] << i5) | (iArr[i2] >>> i7);
        int i11 = i3 + 3;
        iArr2[i11] = (iArr[i4] >>> i7) | (iArr[i2] << i5);
        iArr[i2] = iArr2[i3];
        iArr[i4] = iArr2[i8];
        iArr[i6] = iArr2[i10];
        iArr[i9] = iArr2[i11];
    }

    private int bytes2int(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = (i2 << 8) + (bArr[i3 + i] & 255);
        }
        return i2;
    }

    private void camelliaF2(int[] iArr, int[] iArr2, int i) {
        int i2 = iArr[0] ^ iArr2[i];
        int iSbox4 = sbox4(i2 & 255) | (sbox3((i2 >>> 8) & 255) << 8) | (sbox2((i2 >>> 16) & 255) << 16);
        byte[] bArr = SBOX1;
        int i3 = ((bArr[(i2 >>> 24) & 255] & 255) << 24) | iSbox4;
        int i4 = iArr[1] ^ iArr2[i + 1];
        int iLeftRotate = leftRotate((sbox2((i4 >>> 24) & 255) << 24) | (bArr[i4 & 255] & 255) | (sbox4((i4 >>> 8) & 255) << 8) | (sbox3((i4 >>> 16) & 255) << 16), 8);
        int i5 = i3 ^ iLeftRotate;
        int iLeftRotate2 = leftRotate(iLeftRotate, 8) ^ i5;
        int iRightRotate = rightRotate(i5, 8) ^ iLeftRotate2;
        iArr[2] = (leftRotate(iLeftRotate2, 16) ^ iRightRotate) ^ iArr[2];
        iArr[3] = leftRotate(iRightRotate, 8) ^ iArr[3];
        int i6 = iArr[2] ^ iArr2[i + 2];
        int iSbox42 = ((bArr[(i6 >>> 24) & 255] & 255) << 24) | sbox4(i6 & 255) | (sbox3((i6 >>> 8) & 255) << 8) | (sbox2((i6 >>> 16) & 255) << 16);
        int i7 = iArr2[i + 3] ^ iArr[3];
        int iLeftRotate3 = leftRotate((sbox2((i7 >>> 24) & 255) << 24) | (bArr[i7 & 255] & 255) | (sbox4((i7 >>> 8) & 255) << 8) | (sbox3((i7 >>> 16) & 255) << 16), 8);
        int i8 = iSbox42 ^ iLeftRotate3;
        int iLeftRotate4 = leftRotate(iLeftRotate3, 8) ^ i8;
        int iRightRotate2 = rightRotate(i8, 8) ^ iLeftRotate4;
        iArr[0] = (leftRotate(iLeftRotate4, 16) ^ iRightRotate2) ^ iArr[0];
        iArr[1] = iArr[1] ^ leftRotate(iRightRotate2, 8);
    }

    private void camelliaFLs(int[] iArr, int[] iArr2, int i) {
        int iLeftRotate = iArr[1] ^ leftRotate(iArr[0] & iArr2[i], 1);
        iArr[1] = iLeftRotate;
        iArr[0] = (iLeftRotate | iArr2[i + 1]) ^ iArr[0];
        int i2 = iArr[2];
        int i3 = iArr2[i + 3];
        int i4 = iArr[3];
        int i5 = i2 ^ (i3 | i4);
        iArr[2] = i5;
        iArr[3] = leftRotate(iArr2[i + 2] & i5, 1) ^ i4;
    }

    private void int2bytes(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[(3 - i3) + i2] = (byte) i;
            i >>>= 8;
        }
    }

    private byte lRot8(byte b, int i) {
        return (byte) (((b & 255) >>> (8 - i)) | (b << i));
    }

    private int processBlock128(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.state[i3] = bytes2int(bArr, (i3 * 4) + i);
            int[] iArr = this.state;
            iArr[i3] = iArr[i3] ^ this.kw[i3];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        int[] iArr2 = this.state;
        int i4 = iArr2[2];
        int[] iArr3 = this.kw;
        int i5 = iArr3[4] ^ i4;
        iArr2[2] = i5;
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        int2bytes(i5, bArr2, i2);
        int2bytes(this.state[3], bArr2, i2 + 4);
        int2bytes(this.state[0], bArr2, i2 + 8);
        int2bytes(this.state[1], bArr2, i2 + 12);
        return 16;
    }

    private int processBlock192or256(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.state[i3] = bytes2int(bArr, (i3 * 4) + i);
            int[] iArr = this.state;
            iArr[i3] = iArr[i3] ^ this.kw[i3];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        camelliaFLs(this.state, this.ke, 8);
        camelliaF2(this.state, this.subkey, 36);
        camelliaF2(this.state, this.subkey, 40);
        camelliaF2(this.state, this.subkey, 44);
        int[] iArr2 = this.state;
        int i4 = iArr2[2];
        int[] iArr3 = this.kw;
        int i5 = i4 ^ iArr3[4];
        iArr2[2] = i5;
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        int2bytes(i5, bArr2, i2);
        int2bytes(this.state[3], bArr2, i2 + 4);
        int2bytes(this.state[0], bArr2, i2 + 8);
        int2bytes(this.state[1], bArr2, i2 + 12);
        return 16;
    }

    private int sbox2(int i) {
        return lRot8(SBOX1[i], 1) & 255;
    }

    private int sbox3(int i) {
        return lRot8(SBOX1[i], 7) & 255;
    }

    private int sbox4(int i) {
        return SBOX1[lRot8((byte) i, 1) & 255] & 255;
    }

    private void setKey(boolean z, byte[] bArr) {
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        int length = bArr.length;
        if (length != 16) {
            if (length == 24) {
                iArr[0] = bytes2int(bArr, 0);
                iArr[1] = bytes2int(bArr, 4);
                iArr[2] = bytes2int(bArr, 8);
                iArr[3] = bytes2int(bArr, 12);
                iArr[4] = bytes2int(bArr, 16);
                int iBytes2int = bytes2int(bArr, 20);
                iArr[5] = iBytes2int;
                iArr[6] = ~iArr[4];
                iArr[7] = ~iBytes2int;
            } else {
                if (length != 32) {
                    throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
                }
                iArr[0] = bytes2int(bArr, 0);
                iArr[1] = bytes2int(bArr, 4);
                iArr[2] = bytes2int(bArr, 8);
                iArr[3] = bytes2int(bArr, 12);
                iArr[4] = bytes2int(bArr, 16);
                iArr[5] = bytes2int(bArr, 20);
                iArr[6] = bytes2int(bArr, 24);
                iArr[7] = bytes2int(bArr, 28);
            }
            this._keyis128 = false;
        } else {
            this._keyis128 = true;
            iArr[0] = bytes2int(bArr, 0);
            iArr[1] = bytes2int(bArr, 4);
            iArr[2] = bytes2int(bArr, 8);
            iArr[3] = bytes2int(bArr, 12);
            iArr[7] = 0;
            iArr[6] = 0;
            iArr[5] = 0;
            iArr[4] = 0;
        }
        for (int i = 0; i < 4; i++) {
            iArr2[i] = iArr[i] ^ iArr[i + 4];
        }
        camelliaF2(iArr2, SIGMA, 0);
        for (int i2 = 0; i2 < 4; i2++) {
            iArr2[i2] = iArr2[i2] ^ iArr[i2];
        }
        camelliaF2(iArr2, SIGMA, 4);
        if (this._keyis128) {
            int[] iArr5 = this.kw;
            if (z) {
                iArr5[0] = iArr[0];
                iArr5[1] = iArr[1];
                iArr5[2] = iArr[2];
                iArr5[3] = iArr[3];
                roldq(15, iArr, 0, this.subkey, 4);
                roldq(30, iArr, 0, this.subkey, 12);
                roldq(15, iArr, 0, iArr4, 0);
                int[] iArr6 = this.subkey;
                iArr6[18] = iArr4[2];
                iArr6[19] = iArr4[3];
                roldq(17, iArr, 0, this.ke, 4);
                roldq(17, iArr, 0, this.subkey, 24);
                roldq(17, iArr, 0, this.subkey, 32);
                int[] iArr7 = this.subkey;
                iArr7[0] = iArr2[0];
                iArr7[1] = iArr2[1];
                iArr7[2] = iArr2[2];
                iArr7[3] = iArr2[3];
                roldq(15, iArr2, 0, iArr7, 8);
                roldq(15, iArr2, 0, this.ke, 0);
                roldq(15, iArr2, 0, iArr4, 0);
                int[] iArr8 = this.subkey;
                iArr8[16] = iArr4[0];
                iArr8[17] = iArr4[1];
                roldq(15, iArr2, 0, iArr8, 20);
                roldqo32(34, iArr2, 0, this.subkey, 28);
                roldq(17, iArr2, 0, this.kw, 4);
                return;
            }
            iArr5[4] = iArr[0];
            iArr5[5] = iArr[1];
            iArr5[6] = iArr[2];
            iArr5[7] = iArr[3];
            decroldq(15, iArr, 0, this.subkey, 28);
            decroldq(30, iArr, 0, this.subkey, 20);
            decroldq(15, iArr, 0, iArr4, 0);
            int[] iArr9 = this.subkey;
            iArr9[16] = iArr4[0];
            iArr9[17] = iArr4[1];
            decroldq(17, iArr, 0, this.ke, 0);
            decroldq(17, iArr, 0, this.subkey, 8);
            decroldq(17, iArr, 0, this.subkey, 0);
            int[] iArr10 = this.subkey;
            iArr10[34] = iArr2[0];
            iArr10[35] = iArr2[1];
            iArr10[32] = iArr2[2];
            iArr10[33] = iArr2[3];
            decroldq(15, iArr2, 0, iArr10, 24);
            decroldq(15, iArr2, 0, this.ke, 4);
            decroldq(15, iArr2, 0, iArr4, 0);
            int[] iArr11 = this.subkey;
            iArr11[18] = iArr4[2];
            iArr11[19] = iArr4[3];
            decroldq(15, iArr2, 0, iArr11, 12);
            decroldqo32(34, iArr2, 0, this.subkey, 4);
            roldq(17, iArr2, 0, this.kw, 0);
            return;
        }
        for (int i3 = 0; i3 < 4; i3++) {
            iArr3[i3] = iArr2[i3] ^ iArr[i3 + 4];
        }
        camelliaF2(iArr3, SIGMA, 8);
        int[] iArr12 = this.kw;
        if (z) {
            iArr12[0] = iArr[0];
            iArr12[1] = iArr[1];
            iArr12[2] = iArr[2];
            iArr12[3] = iArr[3];
            roldqo32(45, iArr, 0, this.subkey, 16);
            roldq(15, iArr, 0, this.ke, 4);
            roldq(17, iArr, 0, this.subkey, 32);
            roldqo32(34, iArr, 0, this.subkey, 44);
            roldq(15, iArr, 4, this.subkey, 4);
            roldq(15, iArr, 4, this.ke, 0);
            roldq(30, iArr, 4, this.subkey, 24);
            roldqo32(34, iArr, 4, this.subkey, 36);
            roldq(15, iArr2, 0, this.subkey, 8);
            roldq(30, iArr2, 0, this.subkey, 20);
            int[] iArr13 = this.ke;
            iArr13[8] = iArr2[1];
            iArr13[9] = iArr2[2];
            iArr13[10] = iArr2[3];
            iArr13[11] = iArr2[0];
            roldqo32(49, iArr2, 0, this.subkey, 40);
            int[] iArr14 = this.subkey;
            iArr14[0] = iArr3[0];
            iArr14[1] = iArr3[1];
            iArr14[2] = iArr3[2];
            iArr14[3] = iArr3[3];
            roldq(30, iArr3, 0, iArr14, 12);
            roldq(30, iArr3, 0, this.subkey, 28);
            roldqo32(51, iArr3, 0, this.kw, 4);
            return;
        }
        iArr12[4] = iArr[0];
        iArr12[5] = iArr[1];
        iArr12[6] = iArr[2];
        iArr12[7] = iArr[3];
        decroldqo32(45, iArr, 0, this.subkey, 28);
        decroldq(15, iArr, 0, this.ke, 4);
        decroldq(17, iArr, 0, this.subkey, 12);
        decroldqo32(34, iArr, 0, this.subkey, 0);
        decroldq(15, iArr, 4, this.subkey, 40);
        decroldq(15, iArr, 4, this.ke, 8);
        decroldq(30, iArr, 4, this.subkey, 20);
        decroldqo32(34, iArr, 4, this.subkey, 8);
        decroldq(15, iArr2, 0, this.subkey, 36);
        decroldq(30, iArr2, 0, this.subkey, 24);
        int[] iArr15 = this.ke;
        iArr15[2] = iArr2[1];
        iArr15[3] = iArr2[2];
        iArr15[0] = iArr2[3];
        iArr15[1] = iArr2[0];
        decroldqo32(49, iArr2, 0, this.subkey, 4);
        int[] iArr16 = this.subkey;
        iArr16[46] = iArr3[0];
        iArr16[47] = iArr3[1];
        iArr16[44] = iArr3[2];
        iArr16[45] = iArr3[3];
        decroldq(30, iArr3, 0, iArr16, 32);
        decroldq(30, iArr3, 0, this.subkey, 16);
        roldqo32(51, iArr3, 0, this.kw, 0);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Camellia";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        setKey(z, ((KeyParameter) cipherParameters).getKey());
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException {
        if (!this.initialized) {
            throw new IllegalStateException("Camellia is not initialized");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 <= bArr2.length) {
            return this._keyis128 ? processBlock128(bArr, i, bArr2, i2) : processBlock192or256(bArr, i, bArr2, i2);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
