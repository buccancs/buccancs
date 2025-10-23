package com.shimmerresearch.driverUtilities;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.sensors.SensorEXG;
import com.shimmerresearch.sensors.SensorGSR;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class ShimmerVerDetails implements Serializable {
    public static final int ANY_VERSION = -1;
    public static final Map<Integer, String> mMapOfShimmerHardware;
    public static final Map<Integer, String> mMapOfShimmerRevisions;
    private static final long serialVersionUID = -7940733886215010795L;

    static {
        TreeMap treeMap = new TreeMap();
        treeMap.put(-1, "Unknown");
        treeMap.put(0, "Shimmer1");
        treeMap.put(1, "Shimmer2");
        treeMap.put(2, "Shimmer2r");
        treeMap.put(3, HwDriverShimmerDeviceDetails.SH_SEARCH.BT.SHIMMER3);
        treeMap.put(10, "Shimmer3R");
        treeMap.put(4, "Shimmer_SR30");
        treeMap.put(5, "ShimmerGQBle");
        treeMap.put(4, "SwEatch");
        treeMap.put(9, "Shimmer2rGQ");
        treeMap.put(56, "ShimmerGQ");
        treeMap.put(57, "ShimmerGQ");
        treeMap.put(58, "Shimmer4 SDK");
        treeMap.put(59, "Shimmer ECGmd");
        treeMap.put(1000, "Nonin Onyx II");
        treeMap.put(1001, "QTI Direct Temp");
        treeMap.put(1002, "Keyboard and Mouse");
        treeMap.put(1009, "Keyboard");
        treeMap.put(1003, "Arduino");
        treeMap.put(1004, "Webcam");
        treeMap.put(1005, "Webcam");
        treeMap.put(1006, "Webcam");
        treeMap.put(1008, "Webcam");
        treeMap.put(61, "Verisense IMU");
        treeMap.put(62, "Verisense GSR+");
        treeMap.put(63, "Verisense PPG");
        treeMap.put(64, "Verisense Dev Brd");
        treeMap.put(68, "Verisense Pulse+");
        mMapOfShimmerRevisions = Collections.unmodifiableMap(treeMap);
        TreeMap treeMap2 = new TreeMap();
        treeMap2.put(255, "None");
        treeMap2.put(-1, "Unknown");
        treeMap2.put(8, "Bridge Amplifier+");
        treeMap2.put(9, "Span");
        treeMap2.put(14, SensorGSR.LABEL_SENSOR_TILE.GSR);
        treeMap2.put(31, "IMU");
        treeMap2.put(36, "PROTO3 Mini");
        treeMap2.put(37, SensorEXG.LABEL_SENSOR_TILE.EXG);
        treeMap2.put(38, "PROTO3 Deluxe");
        treeMap2.put(41, "Base15U");
        treeMap2.put(42, "Base6U");
        treeMap2.put(44, Configuration.Shimmer3.GuiLabelSensors.ADXL377_ACCEL_200G);
        treeMap2.put(46, "GPS");
        treeMap2.put(47, "ECG/EMG/Resp");
        treeMap2.put(48, SensorGSR.LABEL_SENSOR_TILE.GSR);
        treeMap2.put(49, "Bridge Amplifier+");
        treeMap2.put(55, "High-g Accel");
        treeMap2.put(56, "ShimmerGQ 802.15.4");
        treeMap2.put(57, "ShimmerGQ 802.15.4");
        treeMap2.put(58, "Shimmer4 SDK");
        treeMap2.put(59, "Shimmer ECGmd");
        treeMap2.put(61, "Verisense IMU");
        treeMap2.put(62, "Verisense GSR+");
        treeMap2.put(63, "Verisense PPG");
        treeMap2.put(64, "Verisense Dev Brd");
        treeMap2.put(68, "Verisense Pulse+");
        mMapOfShimmerHardware = Collections.unmodifiableMap(treeMap2);
    }

    public static final class FW_LABEL {
        public static final String UNKNOWN = "Unknown";
    }

    public static final class HW_ID {
        public static final int ARDUINO = 1003;
        public static final int HOST_CPU_USAGE = 1007;
        public static final int KEYBOARD = 1009;
        public static final int KEYBOARD_AND_MOUSE = 1002;
        public static final int NONIN_ONYX_II = 1000;
        public static final int QTI_DIRECT_TEMP = 1001;
        public static final int RESULT_AGGREGATOR = 2000;
        public static final int SHIMMER_1 = 0;
        public static final int SHIMMER_2 = 1;
        public static final int SHIMMER_2R = 2;
        public static final int SHIMMER_2R_GQ = 9;
        public static final int SHIMMER_3 = 3;
        public static final int SHIMMER_3R = 10;
        public static final int SHIMMER_4_SDK = 58;
        public static final int SHIMMER_ECG_MD = 59;
        public static final int SHIMMER_GQ_802154_LR = 56;
        public static final int SHIMMER_GQ_802154_NR = 57;
        public static final int SHIMMER_GQ_BLE = 5;
        public static final int SHIMMER_SR30 = 4;
        public static final int SPAN = 7;
        public static final int SWEATCH = 4;
        public static final int UNKNOWN = -1;
        public static final int VERISENSE_DEV_BRD = 64;
        public static final int VERISENSE_GSR_PLUS = 62;
        public static final int VERISENSE_IMU = 61;
        public static final int VERISENSE_PPG = 63;
        public static final int VERISENSE_PULSE_PLUS = 68;
        public static final int WEBCAM_DIGIOPTIX_SMART_GLASSES = 1008;
        public static final int WEBCAM_GENERIC = 1004;
        public static final int WEBCAM_LOGITECH_HD_C920 = 1005;
        public static final int WEBCAM_LOGITECH_HD_C930E = 1006;
    }

    public static final class HW_ID_SR_CODES {
        public static final int BASE15U = 41;
        public static final int BASE6U = 42;
        public static final int EXP_BRD_ADXL377_ACCEL_200G = 44;
        public static final int EXP_BRD_BR_AMP = 8;
        public static final int EXP_BRD_BR_AMP_UNIFIED = 49;
        public static final int EXP_BRD_EXG = 37;
        public static final int EXP_BRD_EXG_UNIFIED = 47;
        public static final int EXP_BRD_GPS = 46;
        public static final int EXP_BRD_GSR = 14;
        public static final int EXP_BRD_GSR_UNIFIED = 48;
        public static final int EXP_BRD_H3LIS331DL_ACCEL_HIGH_G = 55;
        public static final int EXP_BRD_PROTO3_DELUXE = 38;
        public static final int EXP_BRD_PROTO3_MINI = 36;
        public static final int LOG_FILE = -2;
        public static final int NONE = 255;
        public static final int SHIMMER3 = 31;
        public static final int SHIMMER_4_SDK = 58;
        public static final int SHIMMER_ECG_MD = 59;
        public static final int SHIMMER_GQ_802154_LR = 56;
        public static final int SHIMMER_GQ_802154_NR = 57;
        public static final int SPAN = 9;
        public static final int UNKNOWN = -1;
        public static final int VERISENSE_DEV_BRD = 64;
        public static final int VERISENSE_GSR_PLUS = 62;
        public static final int VERISENSE_IMU = 61;
        public static final int VERISENSE_PPG = 63;
        public static final int VERISENSE_PULSE_PLUS = 68;
    }

    public static final class FW_ID {
        public static final int BIOSENSICS_GPIO_TEST = 6;
        public static final int BOILER_PLATE = 0;
        public static final int BTSTREAM = 1;
        public static final int BTSTREAM_CE = 14;
        public static final int BTSTREAM_UARTSTREAM = 13;
        public static final int EXGSTREAM = 10;
        public static final int GQ_802154 = 9;
        public static final int GQ_BLE = 5;
        public static final int LOGANDSTREAM = 3;
        public static final int MOVOTEC_PSAD = 8;
        public static final int SDLOG = 2;
        public static final int SHIMMER4_SDK_STOCK = 12;
        public static final int SHIMMER_ECG_MD = 16;
        public static final int SPAN = 11;
        public static final int STROKARE = 15;
        public static final int SWEATCH = 4;
        public static final int UNKNOWN = -1;
        public static final int VERISENSE = 17;
        public static final Map<Integer, String> mMapOfFirmwareLabels;

        static {
            TreeMap treeMap = new TreeMap();
            treeMap.put(1, "BtStream");
            treeMap.put(2, "SDLog");
            treeMap.put(3, "LogAndStream");
            treeMap.put(4, "SwEatch");
            treeMap.put(5, "GQ_BLE");
            treeMap.put(6, "GPIO_TEST");
            treeMap.put(9, "GQ_802154");
            treeMap.put(11, "SPAN");
            treeMap.put(12, "Shimmer4SDK");
            treeMap.put(15, "StroKare");
            treeMap.put(16, HwDriverShimmerDeviceDetails.SH_SEARCH.BT.SHIMMER_ECG_MD);
            treeMap.put(17, "Verisense");
            mMapOfFirmwareLabels = Collections.unmodifiableMap(treeMap);
        }

        public static final class BASES {
            public static final int BASE15U_REV2 = 0;
            public static final int BASE15U_REV4 = 1;
            public static final int BASE6U = 2;
        }
    }
}
