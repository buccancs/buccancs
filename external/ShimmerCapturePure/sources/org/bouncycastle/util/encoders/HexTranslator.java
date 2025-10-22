package org.bouncycastle.util.encoders;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;

/* loaded from: classes5.dex */
public class HexTranslator implements Translator {
    private static final byte[] hexTable = {ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND};

    @Override // org.bouncycastle.util.encoders.Translator
    public int decode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 / 2;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = (i5 * 2) + i;
            byte b = bArr[i6];
            byte b2 = bArr[i6 + 1];
            if (b < 97) {
                bArr2[i3] = (byte) ((b - 48) << 4);
            } else {
                bArr2[i3] = (byte) ((b + SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND) << 4);
            }
            if (b2 < 97) {
                bArr2[i3] = (byte) (bArr2[i3] + ((byte) (b2 - 48)));
            } else {
                bArr2[i3] = (byte) (bArr2[i3] + ((byte) (b2 + SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND)));
            }
            i3++;
        }
        return i4;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i3 + i5;
            byte[] bArr3 = hexTable;
            bArr2[i6] = bArr3[(bArr[i] >> 4) & 15];
            bArr2[i6 + 1] = bArr3[bArr[i] & 15];
            i++;
            i4++;
            i5 += 2;
        }
        return i2 * 2;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int getDecodedBlockSize() {
        return 1;
    }

    @Override // org.bouncycastle.util.encoders.Translator
    public int getEncodedBlockSize() {
        return 2;
    }
}
