package com.shimmerresearch.driver;

import com.shimmerresearch.driverUtilities.DockJobDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class MsgDock {
    public static final int MSG_ID_APPLY_EVENTS_UPDATE = 410;
    public static final int MSG_ID_APPLY_SD_CARD_GSR_HRV_PROCESSING = 411;
    public static final int MSG_ID_APPLY_ZSCORE_RESULT_AGGREGATOR_FAILURE = 502;
    public static final int MSG_ID_APPLY_ZSCORE_RESULT_AGGREGATOR_PROCESSING = 500;
    public static final int MSG_ID_APPLY_ZSCORE_RESULT_AGGREGATOR_SUCCESS = 501;
    public static final int MSG_ID_BSL_FW_WRITE_BUSY = 33;
    public static final int MSG_ID_BSL_FW_WRITE_FAIL = 32;
    public static final int MSG_ID_BSL_FW_WRITE_FINISHED_ALL = 36;
    public static final int MSG_ID_BSL_FW_WRITE_FINISHED_PER_DOCK = 35;
    public static final int MSG_ID_BSL_FW_WRITE_PROGRESS = 31;
    public static final int MSG_ID_BSL_FW_WRITE_PROGRESS_LOG = 34;
    public static final int MSG_ID_BSL_FW_WRITE_SUCCESS = 30;
    public static final int MSG_ID_BT_TO_DB_BUFFER_FILLED = 171;
    public static final int MSG_ID_BT_TO_DB_FAIL = 172;
    public static final int MSG_ID_BT_TO_DB_FINISHED = 170;
    public static final int MSG_ID_BT_TO_DB_RECORDING_THREAD_STARTED = 186;
    public static final int MSG_ID_BT_TO_DB_STARTED = 173;
    public static final int MSG_ID_CLEARSKY_ALG_FAILED = 432;
    public static final int MSG_ID_CLEARSKY_ALG_FINISHED = 431;
    public static final int MSG_ID_CLEARSKY_ALG_UPDATE = 430;
    public static final int MSG_ID_DATA_OPERATION_FINISHED = 301;
    public static final int MSG_ID_DATA_OPERATION_PROGRESS = 300;
    public static final int MSG_ID_DATA_SESSION_FINISHED = 110;
    public static final int MSG_ID_DATA_SYNC_UPDATE = 109;
    public static final int MSG_ID_DATA_TO_ALGO = 190;
    public static final int MSG_ID_DB_MANAGER_LOADED_SUCCESS = 183;
    public static final int MSG_ID_DB_NUM_DB = 180;
    public static final int MSG_ID_DB_PLAYBACK_STARTED_STOPPED = 185;
    public static final int MSG_ID_DB_PLAYBACK_TIMER_UPDATE = 184;
    public static final int MSG_ID_DB_READING_DB_END = 182;
    public static final int MSG_ID_DB_READING_DB_START = 181;
    public static final int MSG_ID_DOCKMANAGER_INITIALIZED_FAIL = 92;
    public static final int MSG_ID_DOCKMANAGER_INITIALIZED_SUCCESS = 91;
    public static final int MSG_ID_DOCK_INFOMEM_WRITE_FINISHED_ALL = 54;
    public static final int MSG_ID_DOCK_INFOMEM_WRITE_FINISHED_PER_DOCK = 53;
    public static final int MSG_ID_DOCK_INITIALISED_STATE_CHANGE = 94;
    public static final int MSG_ID_DOCK_JOB_FAIL_PER_DOCK = 58;
    public static final int MSG_ID_DOCK_JOB_STARTED_PER_DOCK = 56;
    public static final int MSG_ID_DOCK_JOB_SUCCESS_PER_DOCK = 57;
    public static final int MSG_ID_DOCK_OPERATION_CANCELLED = 202;
    public static final int MSG_ID_DOCK_OPERATION_FINISHED = 201;
    public static final int MSG_ID_DOCK_OPERATION_PROGRESS = 200;
    public static final int MSG_ID_DOCK_PLUGGED_UNPLUGGED = 93;
    public static final int MSG_ID_DOCK_SD_CLEAR_FAILED = 113;
    public static final int MSG_ID_DOCK_SD_CLEAR_FINISHED_PER_DOCK = 114;
    public static final int MSG_ID_DOCK_SD_CLEAR_SUCCESS = 115;
    public static final int MSG_ID_DOCK_SD_CLEAR_UPDATE = 112;
    public static final int MSG_ID_DOCK_SD_COPY_COMPLETED = 111;
    public static final int MSG_ID_DOCK_SD_COPY_FAILED = 108;
    public static final int MSG_ID_DOCK_SD_COPY_PER_FILE_COMPLETE = 105;
    public static final int MSG_ID_DOCK_SD_COPY_PER_FILE_START = 106;
    public static final int MSG_ID_DOCK_SD_COPY_SUCCESS = 107;
    public static final int MSG_ID_DOCK_SD_SCAN_FAIL = 103;
    public static final int MSG_ID_DOCK_SD_SCAN_FINISHED_PER_DOCK = 104;
    public static final int MSG_ID_DOCK_SD_SCAN_SUCCESS = 102;
    public static final int MSG_ID_DOCK_SD_SCAN_UPDATE = 101;
    public static final int MSG_ID_DOCK_SLOT_DOCKED = 40;
    public static final int MSG_ID_DOCK_SLOT_REMOVED = 41;
    public static final int MSG_ID_DOCK_STATE_CHANGE = 100;
    public static final int MSG_ID_EVENT_PULSE = 160;
    public static final int MSG_ID_EVENT_TOGGLE_END = 162;
    public static final int MSG_ID_EVENT_TOGGLE_START = 161;
    public static final int MSG_ID_EVENT_TRIAL_AND_CONFIG_TIME = 163;
    public static final int MSG_ID_IMPORT_DATA_PARSER_TO_FILE_FAILURE_IO_EXCEPTION = 139;
    public static final int MSG_ID_IMPORT_DATA_PARSER_TO_FILE_SUCCESS = 138;
    public static final int MSG_ID_IMPORT_DATA_PARSER_TO_FILE_UPDATE = 137;
    public static final int MSG_ID_IMPORT_DB_PARSER_FAILURE_IO_EXCEPTION = 133;
    public static final int MSG_ID_IMPORT_DB_PARSER_FAILURE_SQLITE_EXCEPTION = 132;
    public static final int MSG_ID_IMPORT_DB_PARSER_SUCCESS = 131;
    public static final int MSG_ID_IMPORT_DB_PARSER_UPDATE = 130;
    public static final int MSG_ID_IMPORT_DB_SYNC_FAILURE_SQLITE_EXCEPTION = 134;
    public static final int MSG_ID_NEURO_MANAGER_END_TRIAL = 1018;
    public static final int MSG_ID_PROCESS_DATA_OPERATION_FINISHED = 401;
    public static final int MSG_ID_PROCESS_DATA_OPERATION_PROGRESS = 400;
    public static final int MSG_ID_PROCESS_PEAKS_CANCELLED = 523;
    public static final int MSG_ID_PROCESS_PEAKS_FAILURE = 522;
    public static final int MSG_ID_PROCESS_PEAKS_SUCCESS = 521;
    public static final int MSG_ID_PROCESS_PEAKS_UPDATE = 520;
    public static final int MSG_ID_PROCESS_ZSCORE_CANCELLED = 513;
    public static final int MSG_ID_PROCESS_ZSCORE_DELETE_TEMP_DATA = 514;
    public static final int MSG_ID_PROCESS_ZSCORE_FAILURE = 512;
    public static final int MSG_ID_PROCESS_ZSCORE_SUCCESS = 511;
    public static final int MSG_ID_PROCESS_ZSCORE_UPDATE = 510;
    public static final int MSG_ID_SHIMMERUART_INFOMEM_WRITE_FAIL = 50;
    public static final int MSG_ID_SHIMMERUART_INFOMEM_WRITE_PROGRESS = 51;
    public static final int MSG_ID_SHIMMERUART_INFOMEM_WRITE_SUCCESS = 49;
    public static final int MSG_ID_SHIMMERUART_PACKET_RX = 52;
    public static final int MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_ERROR = 47;
    public static final int MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_FAIL = 48;
    public static final int MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_SUCCESS = 46;
    public static final int MSG_ID_SHIMMERUART_UNEXPECTED_PACKET = 59;
    public static final int MSG_ID_SHIMMER_DETAILS_CLEARED = 45;
    public static final int MSG_ID_SMARTDOCK_ACTIVE_SLOT_CHANGE = 44;
    public static final int MSG_ID_SMARTDOCK_IS_BUSY = 42;
    public static final int MSG_ID_SMARTDOCK_IS_FREE = 43;
    public static final int MSG_ID_SMARTDOCK_NO_STATE = 44;
    public static final int MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_DISCONNECTED = 118;
    public static final int MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_WITHOUT_SD = 116;
    public static final int MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_WITH_SD = 117;
    public static final int MSG_ID_SMARTDOCK_UART_ALL_SHIMMER_RST = 124;
    public static final int MSG_ID_SMARTDOCK_UART_AUTONOTIFY_MESSAGE = 111;
    public static final int MSG_ID_SMARTDOCK_UART_AUTONOTIFY_STATE_OFF = 113;
    public static final int MSG_ID_SMARTDOCK_UART_AUTONOTIFY_STATE_ON = 112;
    public static final int MSG_ID_SMARTDOCK_UART_BOOT_MESSAGE = 119;
    public static final int MSG_ID_SMARTDOCK_UART_BSL_MASK_DOCK_STATE = 121;
    public static final int MSG_ID_SMARTDOCK_UART_BSL_MASK_SHIMMER_STATE = 122;
    public static final int MSG_ID_SMARTDOCK_UART_ERROR = 129;
    public static final int MSG_ID_SMARTDOCK_UART_GPIO_TO_SHIMMERS = 123;
    public static final int MSG_ID_SMARTDOCK_UART_HWFW_VERSION = 115;
    public static final int MSG_ID_SMARTDOCK_UART_INDICATOR_LEDS = 125;
    public static final int MSG_ID_SMARTDOCK_UART_QUERY_SLOTS = 114;
    public static final int MSG_ID_SMARTDOCK_UART_SLOT_MAP_UPDATE = 120;
    public static final int MSG_ID_SOURCE_ALGORITHM = 1005;
    public static final int MSG_ID_SOURCE_ALGORITHM_MANAGER = 1006;
    public static final int MSG_ID_SOURCE_BLUETOOTH_MANAGER = 1002;
    public static final int MSG_ID_SOURCE_DATABASE_MANAGER = 1010;
    public static final int MSG_ID_SOURCE_DATA_IMPORT_MANAGER = 1011;
    public static final int MSG_ID_SOURCE_DATA_PROCESS_MANAGER = 1012;
    public static final int MSG_ID_SOURCE_DOCK_MANAGER = 1003;
    public static final int MSG_ID_SOURCE_EVENT_MARKERS = 1007;
    public static final int MSG_ID_SOURCE_NEUROHOME_SERVER = 1015;
    public static final int MSG_ID_SOURCE_PLATFORM_MANAGER = 1004;
    public static final int MSG_ID_SOURCE_PLOT_MANAGER = 1001;
    public static final int MSG_ID_SOURCE_RESULT_AGGREGATOR = 1008;
    public static final int MSG_ID_SOURCE_RESULT_AGGREGATOR_STOP = 1009;
    public static final int MSG_ID_SOURCE_S3_DOWNLOAD_MANAGER = 1017;
    public static final int MSG_ID_SOURCE_S3_UPLOAD_MANAGER = 1016;
    public static final int MSG_ID_SOURCE_SIMULATOR = 1014;
    public static final int MSG_ID_SOURCE_SPAN_MANAGER = 1000;
    public static final int MSG_ID_SOURCE_VIDEO_MANAGER = 1013;
    public static final int MSG_ID_SPANMANAGER_INITIALIZED_SUCCESS = 95;
    public static final int MSG_ID_UNKNOWN = 0;
    public static final Map<Integer, String> mMapOfMsgCodes;

    static {
        TreeMap treeMap = new TreeMap();
        treeMap.put(0, "MSG_ID_UNKNOWN");
        treeMap.put(30, "MSG_ID_BSL_FW_WRITE_SUCCESS");
        treeMap.put(31, "MSG_ID_BSL_FW_WRITE_PROGRESS");
        treeMap.put(32, "MSG_ID_BSL_FW_WRITE_FAIL");
        treeMap.put(33, "MSG_ID_BSL_FW_WRITE_BUSY");
        treeMap.put(34, "MSG_ID_BSL_FW_WRITE_PROGRESS_LOG");
        treeMap.put(35, "MSG_ID_BSL_FW_WRITE_FINISHED_PER_DOCK");
        treeMap.put(36, "MSG_ID_BSL_FW_WRITE_FINISHED_ALL");
        treeMap.put(40, "MSG_ID_SMARTDOCK_SLOT_DOCKED");
        treeMap.put(41, "MSG_ID_SMARTDOCK_SLOT_REMOVED");
        treeMap.put(44, "MSG_ID_SMARTDOCK_NO_STATE");
        treeMap.put(42, "MSG_ID_SMARTDOCK_IS_BUSY");
        treeMap.put(43, "MSG_ID_SMARTDOCK_IS_FREE");
        treeMap.put(44, "MSG_ID_SMARTDOCK_ACTIVE_SLOT_CHANGE");
        treeMap.put(46, "MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_SUCCESS");
        treeMap.put(47, "MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_ERROR");
        treeMap.put(48, "MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_FAIL");
        treeMap.put(49, "MSG_ID_SHIMMERUART_INFOMEM_WRITE_SUCCESS");
        treeMap.put(50, "MSG_ID_SHIMMERUART_INFOMEM_WRITE_FAIL");
        treeMap.put(52, "MSG_ID_SHIMMERUART_PACKET_RX");
        treeMap.put(59, "MSG_ID_SHIMMERUART_UNEXPECTED_PACKET");
        treeMap.put(53, "MSG_ID_DOCK_INFOMEM_WRITE_FINISHED_PER_DOCK");
        treeMap.put(54, "MSG_ID_DOCK_INFOMEM_WRITE_FINISHED_ALL");
        treeMap.put(56, "MSG_ID_DOCK_JOB_STARTED_PER_DOCK");
        treeMap.put(57, "MSG_ID_DOCK_JOB_SUCCESS_PER_DOCK");
        treeMap.put(58, "MSG_ID_DOCK_JOB_FAIL_PER_DOCK");
        treeMap.put(91, "MSG_ID_DOCKMANAGER_INITIALIZED_SUCCESS");
        treeMap.put(92, "MSG_ID_DOCKMANAGER_INITIALIZED_FAIL");
        treeMap.put(93, "MSG_ID_DOCK_PLUGGED_UNPLUGGED");
        treeMap.put(94, "MSG_ID_DOCK_INITIALISED_STATE_CHANGE");
        treeMap.put(100, "MSG_ID_DOCK_STATE_CHANGE");
        treeMap.put(51, "MSG_ID_SHIMMERUART_INFOMEM_WRITE_PROGRESS");
        treeMap.put(101, "MSG_ID_DOCK_SD_SCAN_UPDATE");
        treeMap.put(102, "MSG_ID_DOCK_SD_SCAN_SUCCESS");
        treeMap.put(103, "MSG_ID_DOCK_SD_SCAN_FAILED");
        treeMap.put(104, "MSG_ID_DOCK_SD_SCAN_FINISHED_PER_DOCK");
        treeMap.put(105, "MSG_ID_DOCK_SD_COPY_UPDATE");
        treeMap.put(107, "MSG_ID_DOCK_SD_COPY_SUCCESS");
        treeMap.put(108, "MSG_ID_DOCK_SD_COPY_FAILED");
        treeMap.put(111, "MSG_ID_DOCK_SD_COPY_COMPLETED");
        treeMap.put(112, "MSG_ID_DOCK_SD_CLEAR_UPDATE");
        treeMap.put(109, "MSG_ID_DATA_SYNC_UPDATE");
        treeMap.put(111, "MSG_ID_SMARTDOCK_UART_AUTONOTIFY_MESSAGE");
        treeMap.put(112, "MSG_ID_SMARTDOCK_UART_AUTONOTIFY_STATE_ON");
        treeMap.put(113, "MSG_ID_SMARTDOCK_UART_AUTONOTIFY_STATE_OFF");
        treeMap.put(114, "MSG_ID_SMARTDOCK_UART_QUERY_SLOTS");
        treeMap.put(115, "MSG_ID_SMARTDOCK_UART_HWFW_VERSION");
        treeMap.put(116, "MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_WITHOUT_SD");
        treeMap.put(117, "MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_WITH_SD");
        treeMap.put(118, "MSG_ID_SMARTDOCK_UART_ACTIVE_SLOT_DISCONNECTED");
        treeMap.put(119, "MSG_ID_SMARTDOCK_UART_BOOT_MESSAGE");
        treeMap.put(120, "MSG_ID_SMARTDOCK_UART_SLOT_MAP_UPDATE");
        treeMap.put(121, "MSG_ID_SMARTDOCK_UART_BSL_MASK_DOCK_STATE");
        treeMap.put(122, "MSG_ID_SMARTDOCK_UART_BSL_MASK_SHIMMER_STATE");
        treeMap.put(123, "MSG_ID_SMARTDOCK_UART_GPIO_TO_SHIMMERS");
        treeMap.put(124, "MSG_ID_SMARTDOCK_UART_ALL_SHIMMER_RST");
        treeMap.put(125, "MSG_ID_SMARTDOCK_UART_INDICATOR_LEDS");
        treeMap.put(129, "MSG_ID_SMARTDOCK_UART_ERROR");
        treeMap.put(200, "MSG_ID_OPERATION_PROGRESS");
        treeMap.put(201, "MSG_ID_OPERATION_FINISHED");
        treeMap.put(202, "MSG_ID_DOCK_OPERATION_CANCELLED");
        treeMap.put(300, "MSG_ID_DATA_OPERATION_PROGRESS");
        treeMap.put(301, "MSG_ID_DATA_OPERATION_FINISHED");
        treeMap.put(130, "MSG_IDENTIFIER_DB_PARSER_UPDATE");
        treeMap.put(131, "MSG_IDENTIFIER_DB_PARSER_SUCCESS");
        treeMap.put(132, "MSG_IDENTIFIER_DB_PARSER_FAILURE_SQLITE_EXCEPTION");
        treeMap.put(133, "MSG_IDENTIFIER_DB_PARSER_FAILURE_IO_EXCEPTION");
        treeMap.put(134, "MSG_IDENTIFIER_DB_SYNC_FAILURE_SQLITE_EXCEPTION");
        treeMap.put(137, "MSG_IDENTIFIER_DATA_PARSER_TO_FILE_UPDATE");
        treeMap.put(138, "MSG_IDENTIFIER_DATA_PARSER_TO_FILE_SUCCESS");
        treeMap.put(139, "MSG_IDENTIFIER_DATA_PARSER_TO_FILE_FAILURE_IO_EXCEPTION");
        treeMap.put(170, "MSG_ID_BT_TO_DB_FINISHED");
        treeMap.put(171, "MSG_ID_BT_TO_DB_BUFFER_FILLED");
        treeMap.put(186, "MSG_ID_BT_TO_DB_RECORDING");
        treeMap.put(172, "MSG_ID_BT_TO_DB_FAIL");
        treeMap.put(184, "MSG_ID_DB_PLAYBACK_TIMER_UPDATE");
        treeMap.put(185, "MSG_ID_DB_PLAYBACK_STARTED_STOPPED");
        treeMap.put(1000, "MSG_ID_SOURCE_SPAN_MANAGER");
        treeMap.put(1001, "MSG_ID_SOURCE_PLOT_MANAGER");
        treeMap.put(1002, "MSG_ID_SOURCE_BLUETOOTH_MANAGER");
        treeMap.put(1003, "MSG_ID_SOURCE_DOCK_MANAGER");
        treeMap.put(1004, "MSG_ID_SOURCE_PLATFORM_MANAGER");
        treeMap.put(1005, "MSG_ID_SOURCE_ALGORITHM");
        treeMap.put(1006, "MSG_ID_SOURCE_ALGORITHM_MANAGER");
        treeMap.put(1007, "MSG_ID_SOURCE_EVENT_MARKERS");
        treeMap.put(1008, "MSG_ID_SOURCE_RESULT_AGGREGATOR");
        treeMap.put(1010, "MSG_ID_SOURCE_DATABASE_MANAGER");
        treeMap.put(1011, "MSG_ID_SOURCE_DATA_IMPORT_MANAGER");
        treeMap.put(1012, "MSG_ID_SOURCE_DATA_PROCESS_MANAGER");
        treeMap.put(1013, "MSG_ID_SOURCE_VIDEO_MANAGER");
        mMapOfMsgCodes = Collections.unmodifiableMap(treeMap);
    }

    public String mBSLComPort;
    public int mConnectionType;
    public DockJobDetails mCurrentJobDetails;
    public int mCurrentOperation;
    public String mDockID;
    public HwDriverShimmerDeviceDetails.DEVICE_STATE mDockState;
    public int mErrorCode;
    public int mErrorCodeLowBsl;
    public int mErrorCodeLowLevel;
    public ShimmerException.ExceptionLevel mExceptionLevel;
    public String mExceptionMsg;
    public StackTraceElement[] mExceptionStackTrace;
    public int mFwImageTotalSize;
    public String mFwImageWriteCurrentAction;
    public int mFwImageWriteProgress;
    public float mFwImageWriteSpeed;
    public int mIndicatorLEDsBitmap;
    public String mMacID;
    public String mMessage;
    public int mMsgID;
    public Object mObject;
    public int mSessionId;
    public byte[] mSlotMap;
    public int mSlotNumber;
    public String mUARTComPort;
    public String mUniqueID;
    public double mValue;

    public MsgDock(int i) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
    }

    public MsgDock(int i, int i2) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
        this.mCurrentOperation = i2;
    }

    public MsgDock(int i, String str) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
        this.mUniqueID = str;
        String[] strArrSplit = str.split("\\.");
        this.mDockID = strArrSplit[0] + "." + strArrSplit[1];
        if (strArrSplit.length > 2) {
            this.mSlotNumber = Integer.parseInt(strArrSplit[2]);
        } else {
            this.mSlotNumber = -1;
        }
    }

    public MsgDock(String str, int i) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
        this.mDockID = str;
        this.mUniqueID = str;
        this.mSlotNumber = -1;
    }

    public MsgDock(int i, String str, int i2) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
        this.mSlotNumber = i2;
        this.mDockID = str;
        this.mUniqueID = convertDockIdAndSlotNumberToUniqueId(str, i2);
    }

    public MsgDock(int i, String str, DockJobDetails dockJobDetails) {
        this.mMsgID = -1;
        this.mCurrentOperation = -1;
        this.mSlotNumber = -1;
        this.mUniqueID = "";
        this.mBSLComPort = "";
        this.mUARTComPort = "";
        this.mDockID = "";
        this.mMacID = "";
        this.mDockState = HwDriverShimmerDeviceDetails.DEVICE_STATE.STATE_NONE;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteProgress = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mMessage = "";
        this.mFwImageWriteCurrentAction = "";
        this.mExceptionLevel = ShimmerException.ExceptionLevel.HIGH;
        this.mSlotMap = new byte[0];
        this.mConnectionType = 0;
        this.mIndicatorLEDsBitmap = 0;
        this.mMsgID = i;
        this.mDockID = str;
        this.mUniqueID = str;
        this.mSlotNumber = convertUniqueIdToSlotNumer(str);
        this.mCurrentJobDetails = dockJobDetails;
    }

    public MsgDock(int i, String str, int i2, String str2) {
        this(i, str, i2);
        this.mMacID = str2;
    }

    public static int convertUniqueIdToSlotNumer(String str) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length > 2) {
            return Integer.parseInt(strArrSplit[2]);
        }
        return -1;
    }

    public static String convertUniqueIdToDockId(String str) {
        String[] strArrSplit = str.split("\\.");
        return strArrSplit[0] + "." + strArrSplit[1];
    }

    public static String convertDockIdAndSlotNumberToUniqueId(String str, int i) {
        return str + "." + String.format("%02d", Integer.valueOf(i));
    }

    public String getMsgDockErrString() {
        String str;
        String str2 = this.mUniqueID;
        if (this.mSlotNumber == -1) {
            str2 = this.mDockID;
        }
        String str3 = ShimmerException.mMapOfErrorCodes.containsKey(Integer.valueOf(this.mMsgID)) ? ShimmerException.mMapOfErrorCodes.get(Integer.valueOf(this.mMsgID)) : "Unknown Error";
        String str4 = ShimmerException.mMapOfErrorCodes.containsKey(Integer.valueOf(this.mErrorCode)) ? ShimmerException.mMapOfErrorCodes.get(Integer.valueOf(this.mErrorCode)) : "Unknown Error";
        String str5 = ShimmerException.mMapOfErrorCodes.containsKey(Integer.valueOf(this.mErrorCodeLowLevel)) ? ShimmerException.mMapOfErrorCodes.get(Integer.valueOf(this.mErrorCodeLowLevel)) : "Unknown Error";
        String str6 = this.mExceptionMsg;
        if (str6 == null || str6.isEmpty()) {
            str = "";
        } else {
            str = "Further info: " + this.mExceptionMsg;
        }
        if (this.mMsgID == 48 && this.mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASICDOCK.getLabel()) && this.mErrorCodeLowLevel == 7005) {
            return "CAUGHT MSGDOCK EXCEPTION - " + str2 + " - MSG_ID_SHIMMERUART_READ_SHIMMER_DETAILS_FAIL " + str4 + StringUtils.SPACE + str5;
        }
        String str7 = "CAUGHT MSGDOCK EXCEPTION\n\tUniqueID: " + str2 + "\n\tMsgID: (" + this.mMsgID + ") " + str3 + "\n\tAction: (" + this.mErrorCode + ") " + str4 + "\n\tLowLevelError: (" + this.mErrorCodeLowLevel + ") " + str5 + "\n\t" + str + StringUtils.LF;
        StackTraceElement[] stackTraceElementArr = this.mExceptionStackTrace;
        if (stackTraceElementArr == null) {
            return str7;
        }
        String strConvertStackTraceToString = UtilShimmer.convertStackTraceToString(stackTraceElementArr);
        if (strConvertStackTraceToString.isEmpty()) {
            return str7;
        }
        return str7 + strConvertStackTraceToString;
    }
}
