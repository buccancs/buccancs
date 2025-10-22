package kotlin.io.encoding;

import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.lsm6dsv.SensorLSM6DSV;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* compiled from: Base64.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0006\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"base64DecodeMap", "", "getBase64DecodeMap$annotations", "()V", "base64EncodeMap", "", "base64UrlDecodeMap", "getBase64UrlDecodeMap$annotations", "base64UrlEncodeMap", "isInMimeAlphabet", "", "symbol", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class Base64Kt {
    private static final int[] base64DecodeMap;
    private static final byte[] base64EncodeMap;
    private static final int[] base64UrlDecodeMap;
    private static final byte[] base64UrlEncodeMap;

    static {
        byte[] bArr = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, ShimmerObject.GET_ECG_CALIBRATION_COMMAND, ShimmerObject.FW_VERSION_RESPONSE};
        base64EncodeMap = bArr;
        int[] iArr = new int[256];
        int i = 0;
        ArraysKt.fill$default(iArr, -1, 0, 0, 6, (Object) null);
        iArr[61] = -2;
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            iArr[bArr[i2]] = i3;
            i2++;
            i3++;
        }
        base64DecodeMap = iArr;
        byte[] bArr2 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, SensorLSM6DSV.SET_ALT_ACCEL_RANGE_COMMAND, SensorLSM6DSV.ALT_ACCEL_RANGE_RESPONSE, SensorLSM6DSV.GET_ALT_ACCEL_RANGE_COMMAND, 82, 83, 84, 85, 86, 87, 88, 89, 90, ShimmerObject.SET_EXG_REGS_COMMAND, ShimmerObject.EXG_REGS_RESPONSE, ShimmerObject.GET_EXG_REGS_COMMAND, 100, ShimmerObject.DAUGHTER_CARD_ID_RESPONSE, ShimmerObject.GET_DAUGHTER_CARD_ID_COMMAND, 103, 104, 105, ShimmerObject.SET_BAUD_RATE_COMMAND, ShimmerObject.BAUD_RATE_RESPONSE, ShimmerObject.GET_BAUD_RATE_COMMAND, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, ShimmerObject.DERIVED_CHANNEL_BYTES_RESPONSE, ShimmerObject.GET_DERIVED_CHANNEL_BYTES, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.STATUS_RESPONSE, ShimmerObject.GET_STATUS_COMMAND, ShimmerObject.SET_TRIAL_CONFIG_COMMAND, ShimmerObject.TRIAL_CONFIG_RESPONSE, ShimmerObject.GET_TRIAL_CONFIG_COMMAND, ShimmerObject.SET_CENTER_COMMAND, ShimmerObject.CENTER_RESPONSE, ShimmerObject.GET_CENTER_COMMAND, ShimmerObject.SET_SHIMMERNAME_COMMAND, ShimmerObject.SHIMMERNAME_RESPONSE, ShimmerObject.SET_BLINK_LED, ShimmerObject.BLINK_LED_RESPONSE, ShimmerObject.GET_BLINK_LED, 51, ShimmerObject.SET_BUFFER_SIZE_COMMAND, ShimmerObject.BUFFER_SIZE_RESPONSE, ShimmerObject.GET_BUFFER_SIZE_COMMAND, 55, 56, 57, ShimmerObject.ALL_CALIBRATION_RESPONSE, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE};
        base64UrlEncodeMap = bArr2;
        int[] iArr2 = new int[256];
        ArraysKt.fill$default(iArr2, -1, 0, 0, 6, (Object) null);
        iArr2[61] = -2;
        int length2 = bArr2.length;
        int i4 = 0;
        while (i < length2) {
            iArr2[bArr2[i]] = i4;
            i++;
            i4++;
        }
        base64UrlDecodeMap = iArr2;
    }

    private static /* synthetic */ void getBase64DecodeMap$annotations() {
    }

    private static /* synthetic */ void getBase64UrlDecodeMap$annotations() {
    }

    public static final boolean isInMimeAlphabet(int i) {
        if (i >= 0) {
            int[] iArr = base64DecodeMap;
            if (i < iArr.length && iArr[i] != -1) {
                return true;
            }
        }
        return false;
    }
}
