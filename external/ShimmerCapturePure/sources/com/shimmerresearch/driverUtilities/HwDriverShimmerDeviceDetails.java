package com.shimmerresearch.driverUtilities;

import com.codeminders.hidapi.HIDDeviceInfo;
import com.shimmerresearch.driver.ShimmerDevice;

/* loaded from: classes2.dex */
public class HwDriverShimmerDeviceDetails {
    public HwDriverDeviceDetails compositeDevice;
    public DEVICE_TYPE deviceType;
    public HwDriverDeviceDetails diskDrive;
    public String mDockID;
    public String mFtdiSerialID;
    public HIDDeviceInfo mHidDeviceInfo;
    public int mNumberOfSlots;
    public String mShimmerDrivePath;
    public String mShimmerFwComPort;
    public String mShimmerUartComPort;
    public String mSmartDockFwComPort;
    public String mSmartDockUartComPort;
    public SPAN_VERSION mSpanVersion;
    public HwDriverDeviceDetails massStorageDevice;
    public HwDriverDeviceDetails portableDevice;
    public HwDriverDeviceDetails serialConverterBaseFw;
    public HwDriverDeviceDetails serialConverterBaseUart;
    public HwDriverDeviceDetails serialConverterShimmerFw;
    public HwDriverDeviceDetails serialConverterShimmerUart;
    public HwDriverDeviceDetails serialPortBaseFw;
    public HwDriverDeviceDetails serialPortBaseUart;
    public HwDriverDeviceDetails serialPortShimmerFw;
    public HwDriverDeviceDetails serialPortShimmerUart;
    public HwDriverDeviceDetails storageVolume;
    public HwDriverDeviceDetails usbHub;

    public HwDriverShimmerDeviceDetails() {
        this.deviceType = DEVICE_TYPE.UNKOWN;
        this.usbHub = null;
        this.compositeDevice = null;
        this.serialConverterBaseFw = null;
        this.serialConverterBaseUart = null;
        this.serialConverterShimmerFw = null;
        this.serialConverterShimmerUart = null;
        this.serialPortBaseFw = null;
        this.serialPortBaseUart = null;
        this.serialPortShimmerFw = null;
        this.serialPortShimmerUart = null;
        this.massStorageDevice = null;
        this.diskDrive = null;
        this.portableDevice = null;
        this.storageVolume = null;
        this.mDockID = "";
        this.mSmartDockFwComPort = "";
        this.mSmartDockUartComPort = "";
        this.mShimmerFwComPort = "";
        this.mShimmerUartComPort = "";
        this.mShimmerDrivePath = "";
        this.mNumberOfSlots = 1;
        this.mHidDeviceInfo = null;
        this.mFtdiSerialID = "";
        this.mSpanVersion = SPAN_VERSION.UNKNOWN;
    }

    public HwDriverShimmerDeviceDetails(String str, String str2, String str3, String str4, String str5, String str6) {
        this.deviceType = DEVICE_TYPE.UNKOWN;
        this.usbHub = null;
        this.compositeDevice = null;
        this.serialConverterBaseFw = null;
        this.serialConverterBaseUart = null;
        this.serialConverterShimmerFw = null;
        this.serialConverterShimmerUart = null;
        this.serialPortBaseFw = null;
        this.serialPortBaseUart = null;
        this.serialPortShimmerFw = null;
        this.serialPortShimmerUart = null;
        this.massStorageDevice = null;
        this.diskDrive = null;
        this.portableDevice = null;
        this.storageVolume = null;
        this.mDockID = "";
        this.mSmartDockFwComPort = "";
        this.mSmartDockUartComPort = "";
        this.mShimmerFwComPort = "";
        this.mShimmerUartComPort = "";
        this.mShimmerDrivePath = "";
        this.mNumberOfSlots = 1;
        this.mHidDeviceInfo = null;
        this.mFtdiSerialID = "";
        this.mSpanVersion = SPAN_VERSION.UNKNOWN;
        this.mDockID = str;
        this.mSmartDockFwComPort = str2;
        this.mSmartDockUartComPort = str3;
        this.mShimmerFwComPort = str4;
        this.mShimmerUartComPort = str5;
        this.mShimmerDrivePath = str6;
    }

    public HwDriverShimmerDeviceDetails(DEVICE_TYPE device_type, HIDDeviceInfo hIDDeviceInfo) {
        this.deviceType = DEVICE_TYPE.UNKOWN;
        this.usbHub = null;
        this.compositeDevice = null;
        this.serialConverterBaseFw = null;
        this.serialConverterBaseUart = null;
        this.serialConverterShimmerFw = null;
        this.serialConverterShimmerUart = null;
        this.serialPortBaseFw = null;
        this.serialPortBaseUart = null;
        this.serialPortShimmerFw = null;
        this.serialPortShimmerUart = null;
        this.massStorageDevice = null;
        this.diskDrive = null;
        this.portableDevice = null;
        this.storageVolume = null;
        this.mDockID = "";
        this.mSmartDockFwComPort = "";
        this.mSmartDockUartComPort = "";
        this.mShimmerFwComPort = "";
        this.mShimmerUartComPort = "";
        this.mShimmerDrivePath = "";
        this.mNumberOfSlots = 1;
        this.mHidDeviceInfo = null;
        this.mFtdiSerialID = "";
        this.mSpanVersion = SPAN_VERSION.UNKNOWN;
        this.deviceType = device_type;
        this.mHidDeviceInfo = hIDDeviceInfo;
        if (hIDDeviceInfo != null) {
            this.mDockID = hIDDeviceInfo.getSerial_number();
        }
    }

    public static DEVICE_TYPE getDeviceTypeFromLabel(String str) {
        for (DEVICE_TYPE device_type : DEVICE_TYPE.values()) {
            if (device_type.getLabel().equals(str)) {
                return device_type;
            }
        }
        return DEVICE_TYPE.UNKOWN;
    }

    public void setDeviceType(DEVICE_TYPE device_type) {
        this.deviceType = device_type;
        if (device_type == DEVICE_TYPE.BASICDOCK) {
            this.mNumberOfSlots = 1;
        } else if (this.deviceType == DEVICE_TYPE.BASE15) {
            this.mNumberOfSlots = 15;
        } else if (this.deviceType == DEVICE_TYPE.BASE6) {
            this.mNumberOfSlots = 6;
        }
    }

    public String getProduct_string() {
        HIDDeviceInfo hIDDeviceInfo = this.mHidDeviceInfo;
        if (hIDDeviceInfo != null) {
            return hIDDeviceInfo.getProduct_string();
        }
        return this.deviceType.getLabel();
    }

    public enum DEVICE_STATE {
        STATE_NONE,
        STATE_READY,
        STATE_BUSY
    }

    public enum SPAN_VERSION {
        UNKNOWN,
        SPAN_SR1_3_1,
        SPAN_SR1_3_0,
        VIRTUAL;

        public boolean isSupported() {
            return this == SPAN_SR1_3_1;
        }
    }

    public enum DEVICE_TYPE {
        UNKOWN("Unknown"),
        BASICDOCK("Dock"),
        BASE15("Base15U"),
        BASE6("Base6U"),
        SPAN("Span"),
        RN42(SH_SEARCH.BT.RN42),
        RNBT(SH_SEARCH.BT.RNBT),
        SHIMMER3(SH_SEARCH.BT.SHIMMER3),
        SHIMMER3_OUTPUT("OUTPUT"),
        SHIMMER_ECG_MD(SH_SEARCH.BT.SHIMMER_ECG_MD),
        SHIMMER3R("Shimmer3R"),
        SHIMMER4(SH_SEARCH.BT.SHIMMER4),
        LUMAFIT(SH_SEARCH.BT.MANUFACTURER_LUMAFIT),
        NONIN_ONYX_II("Nonin_Onyx_II"),
        QTI_DIRECTTEMP("Qti_DirectTemp"),
        KEYBOARD_AND_MOUSE("KeyboardAndMouse"),
        ARDUINO("Arduino"),
        WII_BALANCE_BOARD("Wii_BalanceBoard"),
        WEBCAM("Webcam"),
        HOST_CPU_USAGE("CPU Usage"),
        SWEATCH("SwEatch"),
        VERISENSE("Verisense"),
        KEYBOARD("Keyboard");

        private String deviceLabel;

        DEVICE_TYPE(String str) {
            this.deviceLabel = str;
        }

        public String getLabel() {
            return this.deviceLabel;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.deviceLabel;
        }
    }

    public static final class BASE_HARDWARE_IDS {
        public static final int BASE15U = 1;
        public static final int BASE6U = 2;
    }

    public static final class SH_SEARCH {
        public static final String[] BASIC_DOCK_OSX = {"SHIMMER USB READER"};
        public static final String[] DOCK = {"Shimmer Dock"};
        public static final String[] BASE6 = {"Base6"};
        public static final String[] BASE15 = {"Base15", "SmartDock", "SMART DOCK"};
        public static final String[] SPAN_SR1_3_0 = {"SHIMMER RESEARCH SPAN 5/6/2009"};
        public static final String[] SPAN_SR1_3_1 = {"SHIMMER SPAN SR1-3.1"};
        public static final String[] DISK_DRIVE = {ShimmerDevice.DEFAULT_SHIMMER_NAME, "Generic"};
        public static final String[] PORTABLE_DEVICE = {ShimmerDevice.DEFAULT_SHIMMER_NAME, "Generic"};
        public static final String[] STORAGE_VOL = {"shimmer", "Generic"};
        public static final String[] COMP_DEV_DOCK = {"VID_0403&PID_6010"};
        public static final String[] COMP_DEV_BASE = {"VID_0403&PID_6011"};
        public static final String[] USB_HUB = {"VID_0424&PID_2640"};
        public static final String[] SHIMMER_3R = {"VID_0483&PID_52A4"};
        public static final String[] SHIMMER = {"shimmer"};
        public static final String[] SHIMMER3R_COMPOSITE_DEVICE = {"SHIMMER COMPOSITE DEVICE"};
        public static final String[] SHIMMER3R_MSC_DEVICE = {"SHIMMER MSC DEVICE"};

        public static final class BT {
            public static final String SHIMMER3_CYW20820_BLE_SUFFIX = "-BLE";
            public static final String SHIMMER3_RN4678_BLE = "S3BLE";
            public static final String VERISENSE = "Verisense";
            public static final String[] WIN_DRIVER = {"BTHENUM"};
            public static final String SHIMMER3 = "Shimmer3";
            public static final String SHIMMER_ECG_MD = "ShimmerECGmd";
            public static final String SHIMMER4 = "Shimmer4";
            public static final String RN42 = "RN42";
            public static final String RNBT = "RNBT";
            public static final String[] SHIMMER_DEVICE = {SHIMMER3, DEVICE_TYPE.SHIMMER3_OUTPUT.getLabel(), SHIMMER_ECG_MD, SHIMMER4, RN42, RNBT, "Verisense"};
            public static final String MANUFACTURER_NONIN = "Nonin";
            public static final String MANUFACTURER_LUMAFIT = "Lumafit";
            public static final String[] THIRD_PARTY_DEVICE = {MANUFACTURER_NONIN, MANUFACTURER_LUMAFIT};

            public static final class TOSHIBA_DRIVER {
                public static final String[] ENUM_NAME = {"BLUETOOTH"};
                public static final String[] MANUFACTURER = {"TOSHIBA"};
            }
        }

        public static final class MASS_STORAGE_DEVICE {
            public static final String[] HARDWARE_ID = {"VID_0424&PID_4050"};
            public static final String[] BUS_DESCRIPTION = {"SHIMMER", "Ultra Fast Media Reader"};
        }

        public static final class SERIAL_CONVERTER {
            public static final String[] DOCK = {"VID_0403&PID_6010&MI"};
            public static final String[] BASE = {"VID_0403&PID_6011&MI"};
        }

        public static final class SERIAL_PORT {
            public static final String FTDI_FT2232H_PROD_ID = "6010";
            public static final String FTDI_FT4232H_PROD_ID = "6011";
            public static final String FTDI_VEND_ID = "0403";
            public static final String VIRTUAL_PORT = "COM0COM";
            public static final String[] FTDI_FT2232H = {"VID_0403&PID_6010&MI"};
            public static final String[] FTDI_FT4232H = {"VID_0403&PID_6011&MI"};
        }

        public static final class SERVICE_DESCRIPTION {
            public static final String[] COMPOSITE_DEVICE = {"USB Composite Device"};
            public static final String[] SERIAL_CONVERTER = {"USB Serial Converter"};
        }
    }
}
