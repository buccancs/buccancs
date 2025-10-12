package com.shimmerresearch.driver;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;

public abstract class ConfigByteLayout implements Serializable {

    private static final long serialVersionUID = -5729543049033754281L;

    public byte[] invalidMacId = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    public byte[] invalidMacId2 = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
    public int MSP430_5XX_INFOMEM_D_ADDRESS = 0x001800;
    public int MSP430_5XX_INFOMEM_C_ADDRESS = 0x001880;
    public int MSP430_5XX_INFOMEM_B_ADDRESS = 0x001900;
    public int MSP430_5XX_INFOMEM_A_ADDRESS = 0x001980;
    public int MSP430_5XX_INFOMEM_LAST_ADDRESS = 0x0019FF;
    protected ShimmerVerObject mShimmerVerObject = new ShimmerVerObject();
    protected int mInfoMemSize = 512;

    public static byte[] createConfigByteArrayEmpty(int size) {
        byte[] newArray = new byte[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = (byte) 0x00;
        }
        return newArray;
    }

    public static byte[] createConfigByteArrayDefaultMemoryValues(int size) {
        byte[] newArray = new byte[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = (byte) 0xFF;
        }
        return newArray;
    }

    public static boolean checkConfigBytesValid(byte[] infoMemContents) {
        byte[] comparisonBuffer = new byte[]{-1, -1, -1, -1, -1, -1};
        byte[] detectionBuffer = new byte[comparisonBuffer.length];
        System.arraycopy(infoMemContents, 0, detectionBuffer, 0, detectionBuffer.length);
        if (Arrays.equals(comparisonBuffer, detectionBuffer)) {
            return false;
        }
        return true;
    }

    public abstract int calculateConfigByteLength(ShimmerVerObject shimmerVerObject);

    public int calculateConfigByteLength() {
        return calculateConfigByteLength(mShimmerVerObject);
    }

    public byte[] createConfigByteArrayEmpty() {
        return createConfigByteArrayEmpty(calculateConfigByteLength());
    }

    public boolean isDifferent(int firmwareIdentifier, int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        if ((mShimmerVerObject.mFirmwareIdentifier != firmwareIdentifier)
                || (mShimmerVerObject.mFirmwareVersionMajor != firmwareVersionMajor)
                || (mShimmerVerObject.mFirmwareVersionMinor != firmwareVersionMinor)
                || (mShimmerVerObject.mFirmwareVersionInternal != firmwareVersionInternal)) {
            return true;
        }
        return false;
    }

    protected boolean compareVersions(int compFwIdent, int compMajor, int compMinor, int compInternal) {

        return mShimmerVerObject.compareVersions(compFwIdent, compMajor, compMinor, compInternal);
    }


    public HashMap<Integer, String> getMapOfByteDescriptions() {
        return null;
    }

}
