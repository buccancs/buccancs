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
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    private static final byte[] PI = {-4, -18, -35, 17, -49, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.BLINK_LED_RESPONSE, 22, -5, -60, -6, -38, 35, -59, 4, 77, -23, ShimmerObject.CENTER_RESPONSE, -16, -37, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.RSP_CALIB_DUMP_COMMAND, -70, 23, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -15, ByteSourceJsonBootstrapper.UTF8_BOM_2, 20, -51, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, -63, -7, 24, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, 90, -30, 92, ByteSourceJsonBootstrapper.UTF8_BOM_1, 33, ShimmerObject.GET_MYID_COMMAND, 28, 60, 66, ShimmerObject.SET_CRC_COMMAND, 1, ShimmerObject.GET_INFOMEM_COMMAND, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, 5, ShimmerObject.GET_NSHIMMER_COMMAND, 2, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, -29, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.SET_RWC_COMMAND, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, 6, 11, -19, ShimmerObject.SET_CALIB_DUMP_COMMAND, 127, -44, -45, Ascii.US, -21, ShimmerObject.SET_BUFFER_SIZE_COMMAND, 44, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -22, -56, 72, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, -14, ShimmerObject.ECG_CALIBRATION_RESPONSE, 104, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, -3, 58, -50, -52, -75, ShimmerObject.START_SDBT_COMMAND, 14, 86, 8, 12, ShimmerObject.SET_CENTER_COMMAND, 18, ByteSourceJsonBootstrapper.UTF8_BOM_3, ShimmerObject.GET_STATUS_COMMAND, 19, 71, ShimmerObject.UPD_SDLOG_CFG_COMMAND, -73, 93, ShimmerObject.GET_CONFIGTIME_COMMAND, 21, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, 16, ShimmerObject.GET_SHIMMERNAME_COMMAND, ShimmerObject.GET_CALIB_DUMP_COMMAND, -57, -13, ShimmerObject.GET_RWC_COMMAND, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, -99, -98, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.GET_BLINK_LED, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 25, 61, -1, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.GET_EXPID_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, 84, -58, -128, -61, -67, 13, 87, -33, -11, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, 62, ShimmerObject.SET_TEST, 67, -55, -41, ShimmerObject.SET_SHIMMERNAME_COMMAND, -42, -10, ShimmerObject.SET_EXPID_COMMAND, 34, -71, 3, ShimmerObject.ROUTINE_COMMUNICATION, 15, -20, -34, ShimmerObject.SHIMMERNAME_RESPONSE, -108, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, PSSSigner.TRAILER_IMPLICIT, -36, -24, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, 78, 51, 10, 74, -89, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, Ascii.RS, 0, ShimmerObject.EXG_REGS_RESPONSE, 68, 26, -72, 56, ShimmerObject.SET_NSHIMMER_COMMAND, 100, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, 65, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, 69, 70, ShimmerObject.START_LOGGING_ONLY_COMMAND, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 85, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.SET_INFOMEM_COMMAND, -93, -91, ShimmerObject.EXPID_RESPONSE, 105, -43, -107, 59, 7, 88, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 64, ShimmerObject.CONFIGTIME_RESPONSE, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, Ascii.GS, -9, ShimmerObject.SET_BLINK_LED, 55, ShimmerObject.BAUD_RATE_RESPONSE, -28, ShimmerObject.DIR_RESPONSE, -39, -25, ShimmerObject.GET_DIR_COMMAND, -31, 27, ShimmerObject.NSHIMMER_RESPONSE, 73, 76, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, -8, -2, ShimmerObject.INFOMEM_RESPONSE, 83, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, ShimmerObject.RWC_RESPONSE, -54, -40, ShimmerObject.SET_CONFIGTIME_COMMAND, ShimmerObject.SET_EXG_REGS_COMMAND, 32, ShimmerObject.STATUS_RESPONSE, 103, -92, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 9, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, -53, ShimmerObject.UPD_CALIB_DUMP_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, -48, -66, -27, ShimmerObject.GET_BAUD_RATE_COMMAND, 82, 89, -90, ShimmerObject.TRIAL_CONFIG_RESPONSE, -46, -26, -12, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, -64, -47, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, -62, 57, 75, ShimmerObject.GET_EXG_REGS_COMMAND, -74};
    private static final byte[] inversePI = {-91, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.GET_BLINK_LED, ShimmerObject.SET_RWC_COMMAND, 14, ShimmerObject.SET_BLINK_LED, 56, -64, 84, -26, -98, 57, 85, ShimmerObject.GET_EXPID_COMMAND, 82, ShimmerObject.GET_RWC_COMMAND, 100, 3, 87, 90, 28, ShimmerObject.GET_INTERNAL_EXP_POWER_ENABLE_COMMAND, 7, 24, 33, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TEST, -47, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, -58, -92, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.ROUTINE_COMMUNICATION, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.INFOMEM_RESPONSE, 12, ShimmerObject.SET_NSHIMMER_COMMAND, -22, SensorADXL371.GET_ALT_ACCEL_SAMPLING_RATE_COMMAND, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.GET_CALIB_DUMP_COMMAND, ShimmerObject.GET_EXG_REGS_COMMAND, 73, -27, 66, -28, 21, -73, -56, 6, ShimmerObject.START_SDBT_COMMAND, -99, 65, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, 25, -55, SensorADXL371.ALT_ACCEL_CALIBRATION_RESPONSE, -4, 77, ByteSourceJsonBootstrapper.UTF8_BOM_3, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.GET_NSHIMMER_COMMAND, -43, -61, SensorLIS3MDL.SET_ALT_MAG_CALIBRATION_COMMAND, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, ShimmerObject.CONFIGTIME_RESPONSE, -89, SensorLIS3MDL.GET_ALT_MAG_CALIBRATION_COMMAND, SensorLIS3MDL.SET_ALT_MAG_SAMPLING_RATE_COMMAND, ShimmerObject.RESET_CALIBRATION_VALUE_COMMAND, 70, -45, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, -3, -44, 15, ShimmerObject.UPD_SDLOG_CFG_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.UPD_CALIB_DUMP_COMMAND, 67, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, ShimmerObject.SET_SHIMMERNAME_COMMAND, -74, 83, 127, -63, -16, 35, -25, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, -75, Ascii.RS, ShimmerObject.BT_FW_VERSION_STR_RESPONSE, -33, -90, -2, SensorADXL371.SET_ALT_ACCEL_SAMPLING_RATE_COMMAND, 34, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, ShimmerObject.BUFFER_SIZE_RESPONSE, -54, -18, ShimmerObject.GET_CENTER_COMMAND, 5, ShimmerObject.BAUD_RATE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, -31, 89, -93, -14, ShimmerObject.STATUS_RESPONSE, 86, 17, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.GET_DIR_COMMAND, -108, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.SET_INFOMEM_COMMAND, ByteSourceJsonBootstrapper.UTF8_BOM_2, ShimmerObject.CENTER_RESPONSE, 60, ShimmerObject.GET_SHIMMERNAME_COMMAND, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, SensorADXL371.GET_ALT_ACCEL_CALIBRATION_COMMAND, -46, ShimmerObject.BLINK_LED_RESPONSE, -34, -60, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, -52, -49, ShimmerObject.SET_CENTER_COMMAND, 44, -72, -40, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.GET_BUFFER_SIZE_COMMAND, -37, 105, SensorLIS3MDL.ALT_MAG_SAMPLING_RATE_RESPONSE, 20, -107, -66, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_BT_FW_VERSION_STR_COMMAND, 59, 22, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, -23, 92, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, 55, ShimmerObject.SET_EXG_REGS_COMMAND, 75, -71, -29, -70, -15, ShimmerObject.GET_BMP280_CALIBRATION_COEFFICIENTS_COMMAND, ShimmerObject.SET_CONFIGTIME_COMMAND, ShimmerObject.NSHIMMER_RESPONSE, -38, 71, -59, SensorLIS3MDL.ALT_MAG_CALIBRATION_RESPONSE, 51, -6, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, -62, -10, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, -1, 93, SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, ShimmerObject.GET_INFOMEM_COMMAND, 23, 27, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.EXPID_RESPONSE, -20, 88, -9, Ascii.US, -5, ShimmerObject.SET_EXPID_COMMAND, 9, 13, ShimmerObject.SHIMMERNAME_RESPONSE, 103, 69, ShimmerObject.GET_CONFIGTIME_COMMAND, -36, -24, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, Ascii.GS, 78, 4, -21, -8, -13, 62, 61, -67, ShimmerObject.INSTREAM_CMD_RESPONSE, ShimmerObject.DIR_RESPONSE, -35, -51, 11, 19, ShimmerObject.SET_CALIB_DUMP_COMMAND, 2, ShimmerObject.STOP_LOGGING_ONLY_COMMAND, -128, ShimmerObject.RWC_RESPONSE, -48, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.SET_BUFFER_SIZE_COMMAND, -53, -19, -12, -50, ShimmerObject.RSP_CALIB_DUMP_COMMAND, 16, 68, 64, ShimmerObject.START_LOGGING_ONLY_COMMAND, 58, 1, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, 18, 26, 72, 104, -11, ShimmerObject.GET_MYID_COMMAND, ShimmerObject.SET_CRC_COMMAND, -57, -42, 32, 10, 8, 0, 76, -41, ShimmerObject.TRIAL_CONFIG_RESPONSE};
    private final byte[] lFactors = {-108, 32, ShimmerObject.SET_CONFIGTIME_COMMAND, 16, -62, -64, 1, -5, 1, -64, -62, 16, ShimmerObject.SET_CONFIGTIME_COMMAND, 32, -108, 1};
    private boolean forEncryption;
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    public GOST3412_2015Engine() {
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256][];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    private void C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        L(bArr);
    }

    private void F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArrLSX = LSX(bArr, bArr2);
        X(bArrLSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(bArrLSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArrCopyOf = new byte[16];
        System.arraycopy(bArr, i, bArrCopyOf, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArrCopyOf = Arrays.copyOf(LSX(this.subKeys[i4], bArrCopyOf), 16);
            }
            X(bArrCopyOf, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArrCopyOf = Arrays.copyOf(XSL(bArr3[i3], bArrCopyOf), 16);
                i3--;
            }
            X(bArrCopyOf, bArr3[0]);
        }
        System.arraycopy(bArrCopyOf, 0, bArr2, i2, 16);
    }

    private void L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        X(bArrCopyOf, bArr2);
        S(bArrCopyOf);
        L(bArrCopyOf);
        return bArrCopyOf;
    }

    private void R(byte[] bArr) {
        byte bL = l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = bL;
    }

    private void S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = PI[unsignedByte(bArr[i])];
        }
    }

    private void X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        X(bArrCopyOf, bArr2);
        inverseL(bArrCopyOf);
        inverseS(bArrCopyOf);
        return bArrCopyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10][];
        for (int i2 = 0; i2 < 10; i2++) {
            this.subKeys[i2] = new byte[this.SUB_LENGTH];
        }
        int i3 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.SUB_LENGTH;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                C(bArr7, ((i5 - 1) * 8) + i6);
                F(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
        }
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte bL = l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = bL;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private byte l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else {
            if (cipherParameters == null) {
                return;
            }
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (this.subKeys == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        GOST3412_2015Func(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
