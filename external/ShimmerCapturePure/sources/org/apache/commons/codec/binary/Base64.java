package org.apache.commons.codec.binary;

import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;

import java.math.BigInteger;

/* loaded from: classes5.dex */
public class Base64 extends BaseNCodec {
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_6BITS = 63;
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, ShimmerObject.GET_SHIMMER_VERSION_COMMAND_NEW, -1, 26, 27, 28, Ascii.GS, Ascii.RS, Ascii.US, 32, 33, 34, 35, ShimmerObject.GET_SHIMMER_VERSION_COMMAND, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, ShimmerObject.SET_EMG_CALIBRATION_COMMAND, ShimmerObject.EMG_CALIBRATION_RESPONSE, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, ShimmerObject.ECG_CALIBRATION_RESPONSE, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, 44, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.GET_FW_VERSION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51};
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    private int bitWorkArea;

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr == null) {
            this.encodeSize = 4;
            this.lineSeparator = null;
        } else {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException(new StringBuffer("lineSeparator must not contain base64 characters: [").append(StringUtils.newStringUtf8(bArr)).append("]").toString());
            }
            if (i > 0) {
                this.encodeSize = bArr.length + 4;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength > i) {
            throw new IllegalArgumentException(new StringBuffer("Input array too big, the output array would be bigger (").append(encodedLength).append(") than the specified maximum size of ").append(i).toString());
        }
        return base64.encode(bArr);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("encodeInteger called with null parameter");
        }
        return encodeBase64(toIntegerBytes(bigInteger), false);
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int iBitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i = 1;
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == iBitLength / 8) {
            return byteArray;
        }
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
        } else {
            i = 0;
        }
        int i2 = iBitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    @Override
        // org.apache.commons.codec.binary.BaseNCodec
    void encode(byte[] bArr, int i, int i2) {
        if (this.eof) {
            return;
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                ensureBufferSize(this.encodeSize);
                this.modulus = (this.modulus + 1) % 3;
                int i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += 256;
                }
                this.bitWorkArea = (this.bitWorkArea << 8) + i5;
                if (this.modulus == 0) {
                    byte[] bArr2 = this.buffer;
                    int i6 = this.pos;
                    this.pos = i6 + 1;
                    bArr2[i6] = this.encodeTable[(this.bitWorkArea >> 18) & 63];
                    byte[] bArr3 = this.buffer;
                    int i7 = this.pos;
                    this.pos = i7 + 1;
                    bArr3[i7] = this.encodeTable[(this.bitWorkArea >> 12) & 63];
                    byte[] bArr4 = this.buffer;
                    int i8 = this.pos;
                    this.pos = i8 + 1;
                    bArr4[i8] = this.encodeTable[(this.bitWorkArea >> 6) & 63];
                    byte[] bArr5 = this.buffer;
                    int i9 = this.pos;
                    this.pos = i9 + 1;
                    bArr5[i9] = this.encodeTable[this.bitWorkArea & 63];
                    this.currentLinePos += 4;
                    if (this.lineLength > 0 && this.lineLength <= this.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
                        this.pos += this.lineSeparator.length;
                        this.currentLinePos = 0;
                    }
                }
                i3++;
                i = i4;
            }
            return;
        }
        this.eof = true;
        if (this.modulus == 0 && this.lineLength == 0) {
            return;
        }
        ensureBufferSize(this.encodeSize);
        int i10 = this.pos;
        int i11 = this.modulus;
        if (i11 == 1) {
            byte[] bArr6 = this.buffer;
            int i12 = this.pos;
            this.pos = i12 + 1;
            bArr6[i12] = this.encodeTable[(this.bitWorkArea >> 2) & 63];
            byte[] bArr7 = this.buffer;
            int i13 = this.pos;
            this.pos = i13 + 1;
            byte[] bArr8 = this.encodeTable;
            bArr7[i13] = bArr8[(this.bitWorkArea << 4) & 63];
            if (bArr8 == STANDARD_ENCODE_TABLE) {
                byte[] bArr9 = this.buffer;
                int i14 = this.pos;
                this.pos = i14 + 1;
                bArr9[i14] = 61;
                byte[] bArr10 = this.buffer;
                int i15 = this.pos;
                this.pos = i15 + 1;
                bArr10[i15] = 61;
            }
        } else if (i11 == 2) {
            byte[] bArr11 = this.buffer;
            int i16 = this.pos;
            this.pos = i16 + 1;
            bArr11[i16] = this.encodeTable[(this.bitWorkArea >> 10) & 63];
            byte[] bArr12 = this.buffer;
            int i17 = this.pos;
            this.pos = i17 + 1;
            bArr12[i17] = this.encodeTable[(this.bitWorkArea >> 4) & 63];
            byte[] bArr13 = this.buffer;
            int i18 = this.pos;
            this.pos = i18 + 1;
            byte[] bArr14 = this.encodeTable;
            bArr13[i18] = bArr14[(this.bitWorkArea << 2) & 63];
            if (bArr14 == STANDARD_ENCODE_TABLE) {
                byte[] bArr15 = this.buffer;
                int i19 = this.pos;
                this.pos = i19 + 1;
                bArr15[i19] = 61;
            }
        }
        this.currentLinePos += this.pos - i10;
        if (this.lineLength <= 0 || this.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
        this.pos += this.lineSeparator.length;
    }

    @Override
        // org.apache.commons.codec.binary.BaseNCodec
    void decode(byte[] bArr, int i, int i2) {
        byte b;
        if (this.eof) {
            return;
        }
        if (i2 < 0) {
            this.eof = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            ensureBufferSize(this.decodeSize);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == 61) {
                this.eof = true;
                break;
            }
            if (b2 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    this.modulus = (this.modulus + 1) % 4;
                    this.bitWorkArea = (this.bitWorkArea << 6) + b;
                    if (this.modulus == 0) {
                        byte[] bArr3 = this.buffer;
                        int i5 = this.pos;
                        this.pos = i5 + 1;
                        bArr3[i5] = (byte) ((this.bitWorkArea >> 16) & 255);
                        byte[] bArr4 = this.buffer;
                        int i6 = this.pos;
                        this.pos = i6 + 1;
                        bArr4[i6] = (byte) ((this.bitWorkArea >> 8) & 255);
                        byte[] bArr5 = this.buffer;
                        int i7 = this.pos;
                        this.pos = i7 + 1;
                        bArr5[i7] = (byte) (this.bitWorkArea & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!this.eof || this.modulus == 0) {
            return;
        }
        ensureBufferSize(this.decodeSize);
        int i8 = this.modulus;
        if (i8 == 2) {
            this.bitWorkArea >>= 4;
            byte[] bArr6 = this.buffer;
            int i9 = this.pos;
            this.pos = i9 + 1;
            bArr6[i9] = (byte) (this.bitWorkArea & 255);
            return;
        }
        if (i8 != 3) {
            return;
        }
        this.bitWorkArea >>= 2;
        byte[] bArr7 = this.buffer;
        int i10 = this.pos;
        this.pos = i10 + 1;
        bArr7[i10] = (byte) ((this.bitWorkArea >> 8) & 255);
        byte[] bArr8 = this.buffer;
        int i11 = this.pos;
        this.pos = i11 + 1;
        bArr8[i11] = (byte) (this.bitWorkArea & 255);
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
