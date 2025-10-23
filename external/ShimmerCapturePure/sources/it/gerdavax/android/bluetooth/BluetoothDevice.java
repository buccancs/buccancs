package it.gerdavax.android.bluetooth;

/* loaded from: classes4.dex */
public interface BluetoothDevice {

    String getAddress() throws BluetoothException;

    String getName() throws BluetoothException;

    public static final class BluetoothClasses {
        public static final int DEVICE_MAJOR_AV = 1024;
        public static final int DEVICE_MAJOR_COMPUTER = 256;
        public static final int DEVICE_MAJOR_IMAGING = 1536;
        public static final int DEVICE_MAJOR_LAN = 768;
        public static final int DEVICE_MAJOR_PERIPHERAL = 1280;
        public static final int DEVICE_MAJOR_PHONE = 512;
        public static final int DEVICE_MAJOR_UNCLASSIFIED = 7936;
        public static final int DEVICE_MINOR_COMPUTER_DESKTOP = 4;
        public static final int DEVICE_MINOR_COMPUTER_LAPTOP = 12;
        public static final int DEVICE_MINOR_COMPUTER_MISC = 0;
        public static final int DEVICE_MINOR_COMPUTER_PDA = 20;
        public static final int DEVICE_MINOR_COMPUTER_SERVER = 8;
        public static final int DEVICE_MINOR_COMPUTER_SUBLAPTOP = 16;
        public static final int DEVICE_MINOR_COMPUTER_WATCH_SIZE = 24;
        public static final int DEVICE_MINOR_PHONE_CELLULAR = 4;
        public static final int DEVICE_MINOR_PHONE_CORDLESS = 8;
        public static final int DEVICE_MINOR_PHONE_MISC = 0;
        public static final int DEVICE_MINOR_PHONE_MODEM = 16;
        public static final int DEVICE_MINOR_PHONE_SMARTPHONE = 12;
        public static final int SERVICE_MAJOR_CLASS_AUDIO = 2097152;
        public static final int SERVICE_MAJOR_CLASS_CAPTURE = 524288;
        public static final int SERVICE_MAJOR_CLASS_INFORMATION = 8388608;
        public static final int SERVICE_MAJOR_CLASS_NETWORK = 131072;
        public static final int SERVICE_MAJOR_CLASS_OBEX = 1048576;
        public static final int SERVICE_MAJOR_CLASS_POSITION = 65536;
        public static final int SERVICE_MAJOR_CLASS_RENDER = 262144;
        public static final int SERVICE_MAJOR_CLASS_TELEPHONY = 4194304;
    }

    public static final class BluetoothProfiles {
        public static final int UUID_AV_REMOTE_CONTROLLER_PROFILE = 4366;
        public static final int UUID_BASIC_PRINTING_PROFILE = 4386;
        public static final int UUID_BASIC_PRINTING_STATUS_PROFILE = 4387;
        public static final int UUID_CORDLESS_TELEPHONY_PROFILE = 4361;
        public static final int UUID_DUNP_PROFILE = 4355;
        public static final int UUID_DUN_PPP_PROFILE = 4354;
        public static final int UUID_FAX_PROFILE = 4369;
        public static final int UUID_GA_HEADSET_AUDIO_GATEWAY_PROFILE = 4370;
        public static final int UUID_GA_HEADSET_PROFILE = 4360;
        public static final int UUID_HANDSFREE_AUDIO_GATEWAY_PROFILE = 4383;
        public static final int UUID_HANDSFREE_PROFILE = 4382;
        public static final int UUID_HARDCOPY_CABLE_REPLACEMENT_PRINT_PROFILE = 4390;
        public static final int UUID_HARDCOPY_CABLE_REPLACEMENT_PROFILE = 4389;
        public static final int UUID_HARDCOPY_CABLE_REPLACEMENT_SCAN_PROFILE = 4391;
        public static final int UUID_HUMAN_DEVICE_INTERFACE_PROFILE = 4388;
        public static final int UUID_OBEX_FTP_PROFILE = 4358;
        public static final int UUID_OBEX_OBJECT_PUSH_PROFILE = 4357;
        public static final int UUID_SERIAL_PORT_PROFILE = 4353;
    }

    public static final class BluetoothProtocols {
        public static final int PROTOCOL_BNEP = 15;
        public static final int PROTOCOL_FTP = 10;
        public static final int PROTOCOL_HTTP = 12;
        public static final int PROTOCOL_IP = 9;
        public static final int PROTOCOL_L2CAP = 256;
        public static final int PROTOCOL_OBEX = 8;
        public static final int PROTOCOL_RFCOMM = 3;
        public static final int PROTOCOL_SDP = 1;
        public static final int PROTOCOL_TCP = 4;
        public static final int PROTOCOL_TCS_BIN = 5;
        public static final int PROTOCOL_UDP = 2;
    }
}
