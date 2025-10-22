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
import org.bouncycastle.crypto.params.RC2Parameters;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes5.dex */
public class RC2Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 8;
    private static byte[] piTable = {-39, ShimmerObject.GET_CENTER_COMMAND, -7, -60, 25, -35, -75, -19, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, -23, -3, ShimmerObject.SET_SHIMMERNAME_COMMAND, 74, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, -40, -99, -58, ShimmerObject.GET_EXPID_COMMAND, 55, ShimmerObject.NSHIMMER_RESPONSE, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, ShimmerObject.SET_CENTER_COMMAND, 83, ShimmerObject.GET_INFOMEM_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, 76, 100, ShimmerObject.DIR_RESPONSE, 68, ShimmerObject.SET_CRC_COMMAND, -5, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, 23, ShimmerObject.GET_CALIB_DUMP_COMMAND, 89, -11, ShimmerObject.GET_CONFIGTIME_COMMAND, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, 19, ShimmerObject.SET_EXG_REGS_COMMAND, 69, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.INFOMEM_RESPONSE, 9, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.EXPID_RESPONSE, ShimmerObject.GET_BLINK_LED, -67, ShimmerObject.SET_RWC_COMMAND, 64, -21, ShimmerObject.CONFIGTIME_RESPONSE, -73, ShimmerObject.GET_SHIMMERNAME_COMMAND, 11, -16, -107, 33, 34, 92, ShimmerObject.BAUD_RATE_RESPONSE, 78, ShimmerObject.SET_NSHIMMER_COMMAND, 84, -42, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -50, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, 28, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, 86, -64, 20, -89, ShimmerObject.SET_INFOMEM_COMMAND, -15, -36, 18, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, -54, Ascii.US, 59, -66, -28, -47, 66, 61, -44, ShimmerObject.SET_BLINK_LED, -93, 60, -74, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ByteSourceJsonBootstrapper.UTF8_BOM_3, 14, -38, 70, 105, 7, 87, ShimmerObject.EMG_CALIBRATION_RESPONSE, -14, Ascii.GS, ShimmerObject.UPD_CALIB_DUMP_COMMAND, PSSSigner.TRAILER_IMPLICIT, -108, 67, 3, -8, 17, -57, -10, ShimmerObject.RWC_RESPONSE, ByteSourceJsonBootstrapper.UTF8_BOM_1, 62, -25, 6, -61, -43, ShimmerObject.FW_VERSION_RESPONSE, -56, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, Ascii.RS, -41, 8, -24, -22, -34, -128, 82, -18, -9, ShimmerObject.GET_NSHIMMER_COMMAND, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, 77, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.TEST_CONNECTION_COMMAND, 26, -46, ShimmerObject.STATUS_RESPONSE, 90, 21, 73, ShimmerObject.TRIAL_CONFIG_RESPONSE, 75, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, -48, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 4, 24, -92, -20, -62, ShimmerObject.ROUTINE_COMMUNICATION, 65, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, 15, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -53, -52, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.GET_RWC_COMMAND, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, -12, ShimmerObject.START_SDBT_COMMAND, 57, ShimmerObject.RSP_CALIB_DUMP_COMMAND, ShimmerObject.SET_EXPID_COMMAND, 58, ShimmerObject.SET_CONFIGTIME_COMMAND, 35, -72, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, -4, 2, ShimmerObject.GET_BUFFER_SIZE_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, 85, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.ALL_CALIBRATION_RESPONSE, 93, -6, ShimmerObject.SET_CALIB_DUMP_COMMAND, -29, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.START_LOGGING_ONLY_COMMAND, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 5, -33, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, 16, 103, ShimmerObject.GET_BAUD_RATE_COMMAND, -70, -55, -45, 0, -26, -49, -31, -98, ShimmerObject.SET_TEST, 44, ShimmerObject.GET_EXG_REGS_COMMAND, 22, 1, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, 88, -30, ShimmerObject.GET_DIR_COMMAND, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 13, 56, ShimmerObject.SET_BUFFER_SIZE_COMMAND, 27, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, 51, -1, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, ByteSourceJsonBootstrapper.UTF8_BOM_2, 72, 12, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, -71, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, -51, ShimmerObject.GET_FW_VERSION_COMMAND, -59, -13, -37, 71, -27, -91, ShimmerObject.UPD_SDLOG_CFG_COMMAND, ShimmerObject.CENTER_RESPONSE, 10, -90, 32, 104, -2, 127, -63, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE};
    private boolean encrypting;
    private int[] workingKey;

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iRotateWordLeft = ((bArr[i + 7] & 255) << 8) + (bArr[i + 6] & 255);
        int iRotateWordLeft2 = ((bArr[i + 5] & 255) << 8) + (bArr[i + 4] & 255);
        int iRotateWordLeft3 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 2] & 255);
        int iRotateWordLeft4 = ((bArr[i + 1] & 255) << 8) + (bArr[i] & 255);
        for (int i3 = 60; i3 >= 44; i3 -= 4) {
            iRotateWordLeft = rotateWordLeft(iRotateWordLeft, 11) - ((((~iRotateWordLeft2) & iRotateWordLeft4) + (iRotateWordLeft3 & iRotateWordLeft2)) + this.workingKey[i3 + 3]);
            iRotateWordLeft2 = rotateWordLeft(iRotateWordLeft2, 13) - ((((~iRotateWordLeft3) & iRotateWordLeft) + (iRotateWordLeft4 & iRotateWordLeft3)) + this.workingKey[i3 + 2]);
            iRotateWordLeft3 = rotateWordLeft(iRotateWordLeft3, 14) - ((((~iRotateWordLeft4) & iRotateWordLeft2) + (iRotateWordLeft & iRotateWordLeft4)) + this.workingKey[i3 + 1]);
            iRotateWordLeft4 = rotateWordLeft(iRotateWordLeft4, 15) - ((((~iRotateWordLeft) & iRotateWordLeft3) + (iRotateWordLeft2 & iRotateWordLeft)) + this.workingKey[i3]);
        }
        int[] iArr = this.workingKey;
        int iRotateWordLeft5 = iRotateWordLeft - iArr[iRotateWordLeft2 & 63];
        int iRotateWordLeft6 = iRotateWordLeft2 - iArr[iRotateWordLeft3 & 63];
        int iRotateWordLeft7 = iRotateWordLeft3 - iArr[iRotateWordLeft4 & 63];
        int iRotateWordLeft8 = iRotateWordLeft4 - iArr[iRotateWordLeft5 & 63];
        for (int i4 = 40; i4 >= 20; i4 -= 4) {
            iRotateWordLeft5 = rotateWordLeft(iRotateWordLeft5, 11) - ((((~iRotateWordLeft6) & iRotateWordLeft8) + (iRotateWordLeft7 & iRotateWordLeft6)) + this.workingKey[i4 + 3]);
            iRotateWordLeft6 = rotateWordLeft(iRotateWordLeft6, 13) - ((((~iRotateWordLeft7) & iRotateWordLeft5) + (iRotateWordLeft8 & iRotateWordLeft7)) + this.workingKey[i4 + 2]);
            iRotateWordLeft7 = rotateWordLeft(iRotateWordLeft7, 14) - ((((~iRotateWordLeft8) & iRotateWordLeft6) + (iRotateWordLeft5 & iRotateWordLeft8)) + this.workingKey[i4 + 1]);
            iRotateWordLeft8 = rotateWordLeft(iRotateWordLeft8, 15) - ((((~iRotateWordLeft5) & iRotateWordLeft7) + (iRotateWordLeft6 & iRotateWordLeft5)) + this.workingKey[i4]);
        }
        int[] iArr2 = this.workingKey;
        int iRotateWordLeft9 = iRotateWordLeft5 - iArr2[iRotateWordLeft6 & 63];
        int iRotateWordLeft10 = iRotateWordLeft6 - iArr2[iRotateWordLeft7 & 63];
        int iRotateWordLeft11 = iRotateWordLeft7 - iArr2[iRotateWordLeft8 & 63];
        int iRotateWordLeft12 = iRotateWordLeft8 - iArr2[iRotateWordLeft9 & 63];
        for (int i5 = 16; i5 >= 0; i5 -= 4) {
            iRotateWordLeft9 = rotateWordLeft(iRotateWordLeft9, 11) - ((((~iRotateWordLeft10) & iRotateWordLeft12) + (iRotateWordLeft11 & iRotateWordLeft10)) + this.workingKey[i5 + 3]);
            iRotateWordLeft10 = rotateWordLeft(iRotateWordLeft10, 13) - ((((~iRotateWordLeft11) & iRotateWordLeft9) + (iRotateWordLeft12 & iRotateWordLeft11)) + this.workingKey[i5 + 2]);
            iRotateWordLeft11 = rotateWordLeft(iRotateWordLeft11, 14) - ((((~iRotateWordLeft12) & iRotateWordLeft10) + (iRotateWordLeft9 & iRotateWordLeft12)) + this.workingKey[i5 + 1]);
            iRotateWordLeft12 = rotateWordLeft(iRotateWordLeft12, 15) - ((((~iRotateWordLeft9) & iRotateWordLeft11) + (iRotateWordLeft10 & iRotateWordLeft9)) + this.workingKey[i5]);
        }
        bArr2[i2] = (byte) iRotateWordLeft12;
        bArr2[i2 + 1] = (byte) (iRotateWordLeft12 >> 8);
        bArr2[i2 + 2] = (byte) iRotateWordLeft11;
        bArr2[i2 + 3] = (byte) (iRotateWordLeft11 >> 8);
        bArr2[i2 + 4] = (byte) iRotateWordLeft10;
        bArr2[i2 + 5] = (byte) (iRotateWordLeft10 >> 8);
        bArr2[i2 + 6] = (byte) iRotateWordLeft9;
        bArr2[i2 + 7] = (byte) (iRotateWordLeft9 >> 8);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int iRotateWordLeft = ((bArr[i + 7] & 255) << 8) + (bArr[i + 6] & 255);
        int iRotateWordLeft2 = ((bArr[i + 5] & 255) << 8) + (bArr[i + 4] & 255);
        int iRotateWordLeft3 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 2] & 255);
        int iRotateWordLeft4 = ((bArr[i + 1] & 255) << 8) + (bArr[i] & 255);
        for (int i3 = 0; i3 <= 16; i3 += 4) {
            iRotateWordLeft4 = rotateWordLeft(iRotateWordLeft4 + ((~iRotateWordLeft) & iRotateWordLeft3) + (iRotateWordLeft2 & iRotateWordLeft) + this.workingKey[i3], 1);
            iRotateWordLeft3 = rotateWordLeft(iRotateWordLeft3 + ((~iRotateWordLeft4) & iRotateWordLeft2) + (iRotateWordLeft & iRotateWordLeft4) + this.workingKey[i3 + 1], 2);
            iRotateWordLeft2 = rotateWordLeft(iRotateWordLeft2 + ((~iRotateWordLeft3) & iRotateWordLeft) + (iRotateWordLeft4 & iRotateWordLeft3) + this.workingKey[i3 + 2], 3);
            iRotateWordLeft = rotateWordLeft(iRotateWordLeft + ((~iRotateWordLeft2) & iRotateWordLeft4) + (iRotateWordLeft3 & iRotateWordLeft2) + this.workingKey[i3 + 3], 5);
        }
        int[] iArr = this.workingKey;
        int iRotateWordLeft5 = iRotateWordLeft4 + iArr[iRotateWordLeft & 63];
        int iRotateWordLeft6 = iRotateWordLeft3 + iArr[iRotateWordLeft5 & 63];
        int iRotateWordLeft7 = iRotateWordLeft2 + iArr[iRotateWordLeft6 & 63];
        int iRotateWordLeft8 = iRotateWordLeft + iArr[iRotateWordLeft7 & 63];
        for (int i4 = 20; i4 <= 40; i4 += 4) {
            iRotateWordLeft5 = rotateWordLeft(iRotateWordLeft5 + ((~iRotateWordLeft8) & iRotateWordLeft6) + (iRotateWordLeft7 & iRotateWordLeft8) + this.workingKey[i4], 1);
            iRotateWordLeft6 = rotateWordLeft(iRotateWordLeft6 + ((~iRotateWordLeft5) & iRotateWordLeft7) + (iRotateWordLeft8 & iRotateWordLeft5) + this.workingKey[i4 + 1], 2);
            iRotateWordLeft7 = rotateWordLeft(iRotateWordLeft7 + ((~iRotateWordLeft6) & iRotateWordLeft8) + (iRotateWordLeft5 & iRotateWordLeft6) + this.workingKey[i4 + 2], 3);
            iRotateWordLeft8 = rotateWordLeft(iRotateWordLeft8 + ((~iRotateWordLeft7) & iRotateWordLeft5) + (iRotateWordLeft6 & iRotateWordLeft7) + this.workingKey[i4 + 3], 5);
        }
        int[] iArr2 = this.workingKey;
        int iRotateWordLeft9 = iRotateWordLeft5 + iArr2[iRotateWordLeft8 & 63];
        int iRotateWordLeft10 = iRotateWordLeft6 + iArr2[iRotateWordLeft9 & 63];
        int iRotateWordLeft11 = iRotateWordLeft7 + iArr2[iRotateWordLeft10 & 63];
        int iRotateWordLeft12 = iRotateWordLeft8 + iArr2[iRotateWordLeft11 & 63];
        for (int i5 = 44; i5 < 64; i5 += 4) {
            iRotateWordLeft9 = rotateWordLeft(iRotateWordLeft9 + ((~iRotateWordLeft12) & iRotateWordLeft10) + (iRotateWordLeft11 & iRotateWordLeft12) + this.workingKey[i5], 1);
            iRotateWordLeft10 = rotateWordLeft(iRotateWordLeft10 + ((~iRotateWordLeft9) & iRotateWordLeft11) + (iRotateWordLeft12 & iRotateWordLeft9) + this.workingKey[i5 + 1], 2);
            iRotateWordLeft11 = rotateWordLeft(iRotateWordLeft11 + ((~iRotateWordLeft10) & iRotateWordLeft12) + (iRotateWordLeft9 & iRotateWordLeft10) + this.workingKey[i5 + 2], 3);
            iRotateWordLeft12 = rotateWordLeft(iRotateWordLeft12 + ((~iRotateWordLeft11) & iRotateWordLeft9) + (iRotateWordLeft10 & iRotateWordLeft11) + this.workingKey[i5 + 3], 5);
        }
        bArr2[i2] = (byte) iRotateWordLeft9;
        bArr2[i2 + 1] = (byte) (iRotateWordLeft9 >> 8);
        bArr2[i2 + 2] = (byte) iRotateWordLeft10;
        bArr2[i2 + 3] = (byte) (iRotateWordLeft10 >> 8);
        bArr2[i2 + 4] = (byte) iRotateWordLeft11;
        bArr2[i2 + 5] = (byte) (iRotateWordLeft11 >> 8);
        bArr2[i2 + 6] = (byte) iRotateWordLeft12;
        bArr2[i2 + 7] = (byte) (iRotateWordLeft12 >> 8);
    }

    private int[] generateWorkingKey(byte[] bArr, int i) {
        int[] iArr = new int[128];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        int length = bArr.length;
        if (length < 128) {
            int i3 = iArr[length - 1];
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                i3 = piTable[(i3 + iArr[i4]) & 255] & 255;
                int i6 = length + 1;
                iArr[length] = i3;
                if (i6 >= 128) {
                    break;
                }
                length = i6;
                i4 = i5;
            }
        }
        int i7 = (i + 7) >> 3;
        int i8 = 128 - i7;
        int i9 = piTable[(255 >> ((-i) & 7)) & iArr[i8]] & 255;
        iArr[i8] = i9;
        for (int i10 = 127 - i7; i10 >= 0; i10--) {
            i9 = piTable[i9 ^ iArr[i10 + i7]] & 255;
            iArr[i10] = i9;
        }
        int[] iArr2 = new int[64];
        for (int i11 = 0; i11 != 64; i11++) {
            int i12 = i11 * 2;
            iArr2[i11] = iArr[i12] + (iArr[i12 + 1] << 8);
        }
        return iArr2;
    }

    private int rotateWordLeft(int i, int i2) {
        int i3 = i & 65535;
        return (i3 >> (16 - i2)) | (i3 << i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.encrypting = z;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters) cipherParameters;
            this.workingKey = generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
        } else if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = generateWorkingKey(key, key.length * 8);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("RC2 engine not initialised");
        }
        if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 8;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
