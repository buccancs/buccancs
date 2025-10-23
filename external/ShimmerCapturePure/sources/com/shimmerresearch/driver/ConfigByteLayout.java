package com.shimmerresearch.driver;

import com.shimmerresearch.driverUtilities.ShimmerVerObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes2.dex */
public abstract class ConfigByteLayout implements Serializable {
    private static final long serialVersionUID = -5729543049033754281L;
    public byte[] invalidMacId = {-1, -1, -1, -1, -1, -1};
    public byte[] invalidMacId2 = {0, 0, 0, 0, 0, 0};
    public int MSP430_5XX_INFOMEM_D_ADDRESS = 6144;
    public int MSP430_5XX_INFOMEM_C_ADDRESS = 6272;
    public int MSP430_5XX_INFOMEM_B_ADDRESS = 6400;
    public int MSP430_5XX_INFOMEM_A_ADDRESS = 6528;
    public int MSP430_5XX_INFOMEM_LAST_ADDRESS = 6655;
    protected ShimmerVerObject mShimmerVerObject = new ShimmerVerObject();
    protected int mInfoMemSize = 512;

    public static byte[] createConfigByteArrayEmpty(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = 0;
        }
        return bArr;
    }

    public static byte[] createConfigByteArrayDefaultMemoryValues(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = -1;
        }
        return bArr;
    }

    public static boolean checkConfigBytesValid(byte[] bArr) {
        byte[] bArr2 = new byte[6];
        System.arraycopy(bArr, 0, bArr2, 0, 6);
        return !Arrays.equals(new byte[]{-1, -1, -1, -1, -1, -1}, bArr2);
    }

    public abstract int calculateConfigByteLength(ShimmerVerObject shimmerVerObject);

    public HashMap<Integer, String> getMapOfByteDescriptions() {
        return null;
    }

    public int calculateConfigByteLength() {
        return calculateConfigByteLength(this.mShimmerVerObject);
    }

    public byte[] createConfigByteArrayEmpty() {
        return createConfigByteArrayEmpty(calculateConfigByteLength());
    }

    public boolean isDifferent(int i, int i2, int i3, int i4) {
        return (this.mShimmerVerObject.mFirmwareIdentifier == i && this.mShimmerVerObject.mFirmwareVersionMajor == i2 && this.mShimmerVerObject.mFirmwareVersionMinor == i3 && this.mShimmerVerObject.mFirmwareVersionInternal == i4) ? false : true;
    }

    protected boolean compareVersions(int i, int i2, int i3, int i4) {
        return this.mShimmerVerObject.compareVersions(i, i2, i3, i4);
    }
}
