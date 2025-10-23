package org.bouncycastle.util.encoders;

import com.shimmerresearch.driver.ShimmerObject;

/* loaded from: classes5.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = ShimmerObject.ALL_CALIBRATION_RESPONSE;
        this.encodingTable[this.encodingTable.length - 1] = ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE;
        this.padding = ShimmerObject.GET_FW_VERSION_COMMAND;
        initialiseDecodingTable();
    }
}
