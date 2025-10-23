package com.shimmerresearch.driver.shimmer2r3;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public class BluetoothModuleVersionDetails implements Serializable {
    private static final long serialVersionUID = -4602970064289358794L;
    public BT_MODULE_VERSION mBtModuleVersionParsed;
    public String mBtModuleVersionReceived;

    public BluetoothModuleVersionDetails() {
        BT_MODULE_VERSION bt_module_version = BT_MODULE_VERSION.NOT_READ;
        this.mBtModuleVersionParsed = bt_module_version;
        this.mBtModuleVersionReceived = bt_module_version.btModuleVerStrFull;
    }

    public String getBtModuleVersionReceived() {
        return this.mBtModuleVersionReceived;
    }

    public void parseBtModuleVerBytes(byte[] bArr) {
        this.mBtModuleVersionReceived = new String(bArr, StandardCharsets.UTF_8);
        this.mBtModuleVersionParsed = BT_MODULE_VERSION.UNKNOWN;
        for (BT_MODULE_VERSION bt_module_version : BT_MODULE_VERSION.values()) {
            if (bt_module_version == BT_MODULE_VERSION.NOT_READ) {
                if (this.mBtModuleVersionReceived.isEmpty()) {
                    this.mBtModuleVersionParsed = BT_MODULE_VERSION.NOT_READ;
                    return;
                }
            } else if (this.mBtModuleVersionReceived.contains(bt_module_version.btModuleVerStrComparison)) {
                this.mBtModuleVersionParsed = bt_module_version;
                return;
            }
        }
    }

    public String getUserFriendlyName() {
        return this.mBtModuleVersionParsed == BT_MODULE_VERSION.UNKNOWN ? this.mBtModuleVersionReceived : this.mBtModuleVersionParsed.btModuleVerStrUserFriendly;
    }

    public boolean isBtModuleVersionKnown() {
        return (this.mBtModuleVersionParsed == BT_MODULE_VERSION.UNKNOWN || this.mBtModuleVersionParsed == BT_MODULE_VERSION.NOT_READ) ? false : true;
    }

    public boolean isBtModuleVersionRn42() {
        return this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN42_VERSION_RESPONSE_V4_77 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN42_VERSION_RESPONSE_V6_15;
    }

    public boolean isBtModuleVersionRn41() {
        return this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN41_VERSION_RESPONSE_V4_77;
    }

    public boolean isBtModuleVersionRn4678() {
        return this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN4678_VERSION_RESPONSE_V1_00_5 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN4678_VERSION_RESPONSE_V1_11_0 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN4678_VERSION_RESPONSE_V1_13_5 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN4678_VERSION_RESPONSE_V1_22_0 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.RN4678_VERSION_RESPONSE_V1_23_0;
    }

    public boolean isBtModuleVersionVelaIf820() {
        return this.mBtModuleVersionParsed == BT_MODULE_VERSION.VELA_IF820_V01_04_12_12 || this.mBtModuleVersionParsed == BT_MODULE_VERSION.VELA_IF820_V01_04_16_16;
    }

    public enum BT_MODULE_VERSION {
        NOT_READ("", "", ""),
        RN41_VERSION_RESPONSE_V4_77("Ver 4.77 05/12/09 \r\n(c) Roving Networks", "Ver 4.77 05", "RN41 v4.77"),
        RN42_VERSION_RESPONSE_V4_77("Ver 4.77 RN-42 01/05/10 \r\n(c) Roving Networks", "Ver 4.77 RN-42", "RN42 v4.77"),
        RN42_VERSION_RESPONSE_V6_15("Ver 6.15 04/26/2013\r\n(c) Roving Networks", "Ver 6.15 04", "RN42 v6.15"),
        RN4678_VERSION_RESPONSE_V1_00_5("RN4678 V1.00.5 11/15/2016 (c)Microchip Technology Inc", "RN4678 V1.00.5 ", "RN4678 v1.00.5"),
        RN4678_VERSION_RESPONSE_V1_11_0("RN4678 V1.11.00 6/1/2017 (c)Microchip Technology Inc", "RN4678 V1.11.00 ", "RN4678 v1.11.0"),
        RN4678_VERSION_RESPONSE_V1_13_5("RN4678 V1.13.5 8/29/2018 (c)Microchip Technology Inc", "RN4678 V1.13.5 ", "RN4678 v1.15.5"),
        RN4678_VERSION_RESPONSE_V1_22_0("RN4678 V1.22 12/08/2020 (c)Microchip Technology Inc   ", "RN4678 V1.22 ", "RN4678 v1.23"),
        RN4678_VERSION_RESPONSE_V1_23_0("RN4678 V1.23 06/30/2021 (c)Microchip Technology Inc", "RN4678 V1.23 ", "RN4678 v1.23"),
        VELA_IF820_V01_04_12_12("CYW20820 app=v01.04.12.12", "CYW20820 app=v01.04.12.12", "IF820 v01.04.12.12"),
        VELA_IF820_V01_04_16_16("CYW20820 app=v01.04.16.16", "CYW20820 app=v01.04.16.16", "IF820 v01.04.16.16"),
        UNKNOWN("Unknown", "Unknown", "Unknown");

        public String btModuleVerStrComparison;
        public String btModuleVerStrFull;
        public String btModuleVerStrUserFriendly;

        BT_MODULE_VERSION(String str, String str2, String str3) {
            this.btModuleVerStrFull = str;
            this.btModuleVerStrComparison = str2;
            this.btModuleVerStrUserFriendly = str3;
        }
    }
}
