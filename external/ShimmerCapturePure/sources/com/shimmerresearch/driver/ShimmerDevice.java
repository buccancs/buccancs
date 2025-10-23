package com.shimmerresearch.driver;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmLoaderInterface;
import com.shimmerresearch.algorithms.AlgorithmResultObject;
import com.shimmerresearch.algorithms.gyroOnTheFlyCal.GyroOnTheFlyCalLoader;
import com.shimmerresearch.algorithms.orientation.OrientationModule6DOFLoader;
import com.shimmerresearch.algorithms.orientation.OrientationModule9DOFLoader;
import com.shimmerresearch.algorithms.verisense.gyroAutoCal.GyroOnTheFlyCalLoaderVerisense;
import com.shimmerresearch.bluetooth.DataProcessingInterface;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.wiredProtocol.UartComponentPropertyDetails;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.ShimmerLogDetails;
import com.shimmerresearch.driverUtilities.ShimmerSDCardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs;
import com.shimmerresearch.verisense.sensors.ISensorConfig;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public abstract class ShimmerDevice extends BasicProcessWithCallBack implements Serializable {
    public static final String DEFAULT_DOCKID = "Default.01";
    public static final String DEFAULT_EXPERIMENT_NAME = "DefaultTrial";
    public static final String DEFAULT_MAC_ID = "";
    public static final double DEFAULT_RECEPTION_RATE = 0.0d;
    public static final String DEFAULT_SHIMMER_NAME = "Shimmer";
    public static final String DEFAULT_SHIMMER_NAME_WITH_ERROR = "Shimmer_FFFF";
    public static final int DEFAULT_SLOTNUMBER = -1;
    public static final String DEVICE_ID = "Device_ID";
    public static final int EVENT_MARKER_DEFAULT = -1;
    public static final String INVALID_TRIAL_NAME_CHAR = "[^A-Za-z0-9._]";
    public static final double IRREGULAR_SAMPLING_RATE = 0.0d;
    public static final int RECONNECT_ATTEMPTS_MAX = 3;
    public static final String STRING_CONSTANT_NOT_AVAILABLE = "N/A";
    public static final String STRING_CONSTANT_PENDING = "Pending";
    public static final String STRING_CONSTANT_SD_ERROR = "SD Error";
    public static final String STRING_CONSTANT_UNKNOWN = "Unknown";
    protected static final int MAX_CALIB_DUMP_MAX = 4096;
    private static final List<AlgorithmLoaderInterface> OPEN_SOURCE_ALGORITHMS = Arrays.asList(new GyroOnTheFlyCalLoader(), new OrientationModule6DOFLoader(), new OrientationModule9DOFLoader(), new GyroOnTheFlyCalLoaderVerisense());
    private static final long serialVersionUID = 5087199076353402591L;
    public static int EXP_BOARD_MEMORY_LOCATION_FOR_BTRADIO_STATE = 2018;
    public static boolean mEnableDerivedSensors = true;
    private static boolean mEnableProcessMarkers = true;
    public String mActivityLog;
    public ShimmerBluetooth.BT_STATE mBluetoothRadioState;
    public byte[] mCalibBytes;
    public HashMap<Integer, String> mCalibBytesDescriptions;
    public transient CommsProtocolRadio mCommsProtocolRadio;
    public String mDockID;
    public DOCK_STATE mDockState;
    public HwDriverShimmerDeviceDetails.DEVICE_TYPE mDockType;
    public long mEventMarkers;
    public ExpansionBoardDetails mExpansionBoardDetails;
    public int mFwImageTotalSize;
    public String mFwImageWriteCurrentAction;
    public int mFwImageWriteProgress;
    public float mFwImageWriteSpeed;
    public boolean mIsTrialDetailsStoredOnDevice;
    public transient ObjectCluster mLastProcessedObjectCluster;
    public List<ShimmerException> mListOfDeviceExceptions;
    public List<MsgDock> mListOfFailMsg;
    public List<ShimmerLogDetails> mListofLogs;
    public HashMap<Configuration.COMMUNICATION_TYPE, Double> mMapOfSamplingRatesShimmer;
    public int mNumConnectionAttempts;
    public long mPacketExpectedCountCurrent;
    public long mPacketExpectedCountOverall;
    public long mPacketReceivedCountCurrent;
    public long mPacketReceivedCountOverall;
    public BTRADIO_STATE mRadioState;
    public boolean mReadDaughterIDSuccess;
    public boolean mReadHwFwSuccess;
    public ShimmerBattStatusDetails mShimmerBattStatusDetails;
    public long mShimmerLastReadRtcValueMilliSecs;
    public String mShimmerLastReadRtcValueParsed;
    public long mShimmerRealTimeClockConFigTime;
    public ShimmerSDCardDetails mShimmerSDCardDetails;
    public ShimmerVerObject mShimmerVerObject;
    public int mSlotNumber;
    public boolean mVerboseMode;
    public boolean writeRealWorldClockFromPcTimeSuccess;
    public String mUniqueID = "";
    public List<Configuration.COMMUNICATION_TYPE> mListOfAvailableCommunicationTypes = new ArrayList();
    public String mMacIdFromUart = "";
    public String mShimmerUserAssignedName = "";
    public String mAlternativeName = "";
    protected boolean mAutoStartStreaming;
    protected String mComPort;
    protected ConfigByteLayout mConfigByteLayout;
    protected byte[] mConfigBytes;
    protected long mConfigTime;
    protected DataProcessingInterface mDataProcessing;
    protected long mDerivedSensors;
    protected long mEnabledSensors;
    protected long mEventMarkersCodeLast;
    protected boolean mEventMarkersIsPulse;
    protected LinkedHashMap<String, Object> mFixedShimmerConfigMap;
    protected FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE mFixedShimmerConfigMode;
    protected boolean mHaveAttemptedToReadConfig;
    protected byte[] mInfoMemBytesOriginal;
    protected int mInternalExpPower;
    protected boolean mIsConnected;
    protected boolean mIsInitialised;
    protected boolean mIsSensing;
    protected boolean mIsStreaming;
    protected String mTrialName;
    protected LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> mMapOfSensorClasses = new LinkedHashMap<>();
    protected LinkedHashMap<Integer, SensorDetails> mSensorMap = new LinkedHashMap<>();
    protected HashMap<Configuration.COMMUNICATION_TYPE, TreeMap<Integer, SensorDetails>> mParserMap = new HashMap<>();
    protected Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapSensors = new HashMap();
    protected TreeMap<Integer, SensorGroupingDetails> mSensorGroupingMap = new TreeMap<>();
    protected Map<String, AbstractAlgorithm> mMapOfAlgorithmModules = new HashMap();
    protected ArrayList<String> mAlgorithmProcessingSequence = null;
    protected TreeMap<Integer, SensorGroupingDetails> mMapOfAlgorithmGrouping = new TreeMap<>();
    protected Map<String, ConfigOptionDetails> mConfigOptionsMapAlgorithms = new HashMap();
    private boolean mConfigurationReadSuccess;
    private boolean mIsDocked;
    private boolean mIsUsbPluggedIn;
    private long mPacketLossCountPerTrial;
    private double mPacketReceptionRateCurrent;
    private double mPacketReceptionRateOverall;
    private boolean mUpdateOnlyWhenStateChanges;
    private boolean mIsPlaybackDevice = false;
    private boolean mIsEnabledAlgorithmModulesDuringPlayback = false;

    public ShimmerDevice() {
        HashMap<Configuration.COMMUNICATION_TYPE, Double> map = new HashMap<>();
        this.mMapOfSamplingRatesShimmer = map;
        Configuration.COMMUNICATION_TYPE communication_type = Configuration.COMMUNICATION_TYPE.SD;
        Double dValueOf = Double.valueOf(51.2d);
        map.put(communication_type, dValueOf);
        this.mMapOfSamplingRatesShimmer.put(Configuration.COMMUNICATION_TYPE.BLUETOOTH, dValueOf);
        this.mDockID = DEFAULT_DOCKID;
        this.mDockType = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
        this.mSlotNumber = -1;
        this.mShimmerVerObject = new ShimmerVerObject();
        this.mExpansionBoardDetails = new ExpansionBoardDetails();
        this.mShimmerBattStatusDetails = new ShimmerBattStatusDetails();
        this.mShimmerSDCardDetails = new ShimmerSDCardDetails();
        this.mReadHwFwSuccess = false;
        this.mConfigurationReadSuccess = false;
        this.mReadDaughterIDSuccess = false;
        this.writeRealWorldClockFromPcTimeSuccess = false;
        this.mIsConnected = false;
        this.mIsSensing = false;
        this.mIsStreaming = false;
        this.mIsInitialised = false;
        this.mIsDocked = false;
        this.mIsUsbPluggedIn = false;
        this.mHaveAttemptedToReadConfig = false;
        this.mActivityLog = "";
        this.mFwImageWriteProgress = 0;
        this.mFwImageTotalSize = 0;
        this.mFwImageWriteSpeed = 0.0f;
        this.mFwImageWriteCurrentAction = "";
        this.mListOfFailMsg = new ArrayList();
        this.mListOfDeviceExceptions = new ArrayList();
        this.mShimmerRealTimeClockConFigTime = 0L;
        this.mShimmerLastReadRtcValueMilliSecs = 0L;
        this.mShimmerLastReadRtcValueParsed = "";
        this.mConfigBytes = ConfigByteLayout.createConfigByteArrayEmpty(512);
        this.mInfoMemBytesOriginal = ConfigByteLayout.createConfigByteArrayEmpty(512);
        this.mCalibBytes = new byte[0];
        this.mCalibBytesDescriptions = new HashMap<>();
        this.mTrialName = "";
        this.mPacketReceivedCountCurrent = 0L;
        this.mPacketExpectedCountCurrent = 0L;
        this.mPacketReceptionRateCurrent = 0.0d;
        this.mPacketReceivedCountOverall = 0L;
        this.mPacketExpectedCountOverall = 0L;
        this.mPacketReceptionRateOverall = 0.0d;
        this.mPacketLossCountPerTrial = 0L;
        this.mEventMarkersCodeLast = 0L;
        this.mEventMarkersIsPulse = false;
        this.mEventMarkers = -1L;
        this.mLastProcessedObjectCluster = null;
        this.mListofLogs = new ArrayList();
        this.mEnabledSensors = 0L;
        this.mDerivedSensors = 0L;
        this.mComPort = "";
        this.mCommsProtocolRadio = null;
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mDockState = DOCK_STATE.UNDOCKED;
        this.mRadioState = BTRADIO_STATE.UNKNOWN;
        this.mUpdateOnlyWhenStateChanges = false;
        this.mInternalExpPower = -1;
        this.mVerboseMode = true;
        this.mFixedShimmerConfigMode = FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE.NONE;
        this.mFixedShimmerConfigMap = null;
        this.mAutoStartStreaming = false;
        this.mIsTrialDetailsStoredOnDevice = true;
        this.mNumConnectionAttempts = -1;
        setThreadName(getClass().getSimpleName());
        setupDataProcessing();
    }

    protected static double convertSamplingRateBytesToFreq(byte b, byte b2, double d) {
        return d / ((b & 255) + ((b2 & 255) << 8));
    }

    public static double getRtcClockFreq() {
        return 32768.0d;
    }

    public static boolean isTrialOrShimmerNameInvalid(String str) {
        if (str.isEmpty()) {
            return true;
        }
        return Pattern.compile(INVALID_TRIAL_NAME_CHAR).matcher(str).find();
    }

    public static boolean isSupportedDerivedSensors(ShimmerVerObject shimmerVerObject) {
        return isVerCompatibleWith(shimmerVerObject, 3, 1, 0, 7, 0) || isVerCompatibleWith(shimmerVerObject, 3, 2, 0, 8, 69) || isVerCompatibleWith(shimmerVerObject, 3, 3, 0, 3, 17) || shimmerVerObject.isShimmerGen3R() || shimmerVerObject.isShimmerGenGq() || shimmerVerObject.isShimmerGen4();
    }

    public static boolean isSupportedNoImuSensors(ShimmerVerObject shimmerVerObject, ExpansionBoardDetails expansionBoardDetails) {
        if (shimmerVerObject != null && expansionBoardDetails != null) {
            int expansionBoardId = expansionBoardDetails.getExpansionBoardId();
            int expansionBoardRev = expansionBoardDetails.getExpansionBoardRev();
            int expansionBoardRevSpecial = expansionBoardDetails.getExpansionBoardRevSpecial();
            if (shimmerVerObject.getHardwareVersion() == 3 && expansionBoardId == 59 && expansionBoardRev == 3 && expansionBoardRevSpecial == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVerCompatibleWith(ShimmerVerObject shimmerVerObject, int i, int i2, int i3, int i4, int i5) {
        return shimmerVerObject.compareVersions(i, i2, i3, i4, i5);
    }

    protected static double roundSamplingRateToSupportedValue(double d, double d2) {
        return Double.valueOf(Math.round(Double.valueOf(d2 / Math.floor(d2 / d)).doubleValue() * 100.0d) / 100.0d).doubleValue();
    }

    protected static byte[] convertSamplingRateFreqToBytes(double d, double d2) {
        int iRound = (int) Math.round(d2 / d);
        return new byte[]{(byte) (iRound & 255), (byte) ((iRound >> 8) & 255)};
    }

    public static void printMapOfConfig(HashMap<String, Object> map) {
        System.out.println("Printing map of Config for DB, size = " + map.keySet().size());
        for (String str : map.keySet()) {
            String str2 = str + " = ";
            Object obj = map.get(str);
            if (obj instanceof String) {
                str2 = str2 + ((String) obj);
            } else if (obj instanceof Boolean) {
                str2 = str2 + Boolean.toString(((Boolean) obj).booleanValue());
            } else if (obj instanceof Double) {
                str2 = str2 + Double.toString(((Double) obj).doubleValue());
            } else if (obj instanceof Integer) {
                str2 = str2 + Integer.toString(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                str2 = str2 + Long.toString(((Long) obj).longValue());
            }
            System.out.println(str2);
        }
    }

    private void incrementPacketReceivedCountOverall() {
        this.mPacketReceivedCountOverall++;
    }

    public abstract byte[] configBytesGenerate(boolean z, Configuration.COMMUNICATION_TYPE communication_type);

    public abstract void configBytesParse(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type);

    public void configureFromClone(ShimmerDevice shimmerDevice) throws ShimmerException {
    }

    public void connect() throws ShimmerException {
    }

    public abstract void createConfigBytesLayout();

    protected abstract void dataHandler(ObjectCluster objectCluster);

    public abstract ShimmerDevice deepClone();

    public byte[] generateUartConfigMessage(UartComponentPropertyDetails uartComponentPropertyDetails) {
        return null;
    }

    public String getAlternativeName() {
        return this.mAlternativeName;
    }

    public void setAlternativeName(String str) {
        this.mAlternativeName = str;
    }

    public ShimmerBattStatusDetails getBattStatusDetails() {
        return this.mShimmerBattStatusDetails;
    }

    public void setBattStatusDetails(ShimmerBattStatusDetails shimmerBattStatusDetails) {
        this.mShimmerBattStatusDetails = shimmerBattStatusDetails;
    }

    public ShimmerBluetooth.BT_STATE getBluetoothRadioState() {
        return this.mBluetoothRadioState;
    }

    public CommsProtocolRadio getCommsProtocolRadio() {
        return this.mCommsProtocolRadio;
    }

    public void setCommsProtocolRadio(CommsProtocolRadio commsProtocolRadio) {
        consolePrintErrLn("Setting CommsProtocolRadio");
        this.mCommsProtocolRadio = commsProtocolRadio;
    }

    public List<Configuration.COMMUNICATION_TYPE> getCommunicationRoutes() {
        return this.mListOfAvailableCommunicationTypes;
    }

    public void setCommunicationRoutes(List<Configuration.COMMUNICATION_TYPE> list) {
        this.mListOfAvailableCommunicationTypes.clear();
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = list.iterator();
        while (it2.hasNext()) {
            addCommunicationRoute(it2.next());
        }
    }

    public Map<String, ConfigOptionDetailsSensor> getConfigOptionsMap() {
        return this.mConfigOptionsMapSensors;
    }

    public Map<String, ConfigOptionDetails> getConfigOptionsMapAlorithms() {
        return this.mConfigOptionsMapAlgorithms;
    }

    public long getConfigTime() {
        return this.mConfigTime;
    }

    public void setConfigTime(long j) {
        this.mConfigTime = j;
    }

    public DataProcessingInterface getDataProcessing() {
        return this.mDataProcessing;
    }

    public void setDataProcessing(DataProcessingInterface dataProcessingInterface) {
        this.mDataProcessing = dataProcessingInterface;
    }

    public long getDerivedSensors() {
        return this.mDerivedSensors;
    }

    public void setDerivedSensors(long j) {
        this.mDerivedSensors = j;
    }

    public long getEnabledSensors() {
        return this.mEnabledSensors;
    }

    @Deprecated
    public void setEnabledSensors(long j) {
        this.mEnabledSensors = j;
    }

    public ExpansionBoardDetails getExpansionBoardDetails() {
        return this.mExpansionBoardDetails;
    }

    public void setExpansionBoardDetails(ExpansionBoardDetails expansionBoardDetails) {
        this.mExpansionBoardDetails = expansionBoardDetails;
    }

    public FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE getFixedShimmerConfigMode() {
        return this.mFixedShimmerConfigMode;
    }

    public int getInternalExpPower() {
        return this.mInternalExpPower;
    }

    public String getMacId() {
        return this.mMacIdFromUart;
    }

    public String getMacIdFromUart() {
        return this.mMacIdFromUart;
    }

    public void setMacIdFromUart(String str) {
        this.mMacIdFromUart = str;
        updateThreadName();
    }

    public Map<String, AbstractAlgorithm> getMapOfAlgorithmModules() {
        return this.mMapOfAlgorithmModules;
    }

    public LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> getMapOfSensorsClasses() {
        return this.mMapOfSensorClasses;
    }

    public long getPacketLossCountPerTrial() {
        return this.mPacketLossCountPerTrial;
    }

    public void setPacketLossCountPerTrial(long j) {
        this.mPacketLossCountPerTrial = j;
    }

    public long getPacketReceivedCountCurrent() {
        return this.mPacketReceivedCountCurrent;
    }

    public long getPacketReceivedCountOverall() {
        return this.mPacketReceivedCountOverall;
    }

    public double getPacketReceptionRateCurrent() {
        return this.mPacketReceptionRateCurrent;
    }

    public void setPacketReceptionRateCurrent(double d) {
        this.mPacketReceptionRateCurrent = UtilShimmer.nudgeDouble(d, 0.0d, 100.0d);
    }

    public double getPacketReceptionRateOverall() {
        return this.mPacketReceptionRateOverall;
    }

    public void setPacketReceptionRateOverall(double d) {
        this.mPacketReceptionRateOverall = UtilShimmer.nudgeDouble(d, 0.0d, 100.0d);
    }

    public String getRadioModel() {
        return "";
    }

    public double getSamplingClockFreq() {
        return 32768.0d;
    }

    public TreeMap<Integer, SensorGroupingDetails> getSensorGroupingMap() {
        return this.mSensorGroupingMap;
    }

    public LinkedHashMap<Integer, SensorDetails> getSensorMap() {
        return this.mSensorMap;
    }

    public byte[] getShimmerConfigBytes() {
        return this.mConfigBytes;
    }

    public byte[] getShimmerInfoMemBytesOriginal() {
        return this.mInfoMemBytesOriginal;
    }

    public ShimmerSDCardDetails getShimmerSDCardDetails() {
        return this.mShimmerSDCardDetails;
    }

    public String getShimmerUserAssignedName() {
        return this.mShimmerUserAssignedName;
    }

    public void setShimmerUserAssignedName(String str) {
        String strReplaceAll;
        if (!str.isEmpty()) {
            strReplaceAll = str.replaceAll(INVALID_TRIAL_NAME_CHAR, "");
            char cCharAt = strReplaceAll.charAt(0);
            StringBuilder sb = new StringBuilder();
            sb.append(cCharAt);
            if (UtilShimmer.isNumeric(sb.toString())) {
                strReplaceAll = "S" + strReplaceAll;
            }
        } else {
            strReplaceAll = "Shimmer_" + getMacIdFromUartParsed();
        }
        if (strReplaceAll.length() > 12) {
            setShimmerUserAssignedNameNoLengthCheck(strReplaceAll.substring(0, 12));
        } else {
            setShimmerUserAssignedNameNoLengthCheck(strReplaceAll);
        }
    }

    public ShimmerVerObject getShimmerVerObject() {
        return this.mShimmerVerObject;
    }

    public String getTrialName() {
        return this.mTrialName;
    }

    public void setTrialName(String str) {
        this.mTrialName = str;
    }

    public String getUniqueId() {
        return this.mUniqueID;
    }

    public void setUniqueId(String str) {
        this.mUniqueID = str;
    }

    public boolean haveAttemptedToRead() {
        return this.mHaveAttemptedToReadConfig;
    }

    public void incrementPacketExpectedCountCurrent() {
        this.mPacketExpectedCountCurrent++;
    }

    public void incrementPacketReceivedCountCurrent() {
        this.mPacketReceivedCountCurrent++;
    }

    public void interpretDataPacketFormat() {
    }

    protected abstract void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type);

    public boolean isAutoStartStreaming() {
        return this.mAutoStartStreaming;
    }

    public void setAutoStartStreaming(boolean z) {
        this.mAutoStartStreaming = z;
    }

    public boolean isConfigurationReadSuccess() {
        return this.mConfigurationReadSuccess;
    }

    public void setConfigurationReadSuccess(boolean z) {
        this.mConfigurationReadSuccess = z;
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }

    public boolean isDocked() {
        return this.mIsDocked;
    }

    public boolean isEnabledAlgorithmModulesDuringPlayback() {
        return this.mIsEnabledAlgorithmModulesDuringPlayback;
    }

    public void setEnabledAlgorithmModulesDuringPlayback(boolean z) {
        this.mIsEnabledAlgorithmModulesDuringPlayback = z;
    }

    public boolean isInitialised() {
        return this.mIsInitialised;
    }

    public boolean isInternalExpPower() {
        return this.mInternalExpPower > 0;
    }

    public void setInternalExpPower(int i) {
        this.mInternalExpPower = i;
    }

    public void setInternalExpPower(boolean z) {
        setInternalExpPower(z ? 1 : 0);
    }

    public boolean isPlaybackDevice() {
        return this.mIsPlaybackDevice;
    }

    public boolean isReadRealTimeClockSet() {
        return this.mShimmerLastReadRtcValueMilliSecs > 1420070400000L;
    }

    public boolean isStreaming() {
        return this.mIsStreaming;
    }

    public boolean isUsbPluggedIn() {
        return this.mIsUsbPluggedIn;
    }

    public void parseUartConfigResponse(UartComponentPropertyDetails uartComponentPropertyDetails, byte[] bArr) {
    }

    public void resetEventMarkerValuetoDefault() {
        this.mEventMarkers = -1L;
    }

    public void resetPacketReceptionCurrentCounters() {
        this.mPacketExpectedCountCurrent = 0L;
        this.mPacketReceivedCountCurrent = 0L;
    }

    public abstract void sensorAndConfigMapsCreate();

    public void setAlgoProcessingSequence(ArrayList<String> arrayList) {
        this.mAlgorithmProcessingSequence = arrayList;
    }

    public void setDefaultShimmerConfiguration() {
    }

    public void setEnableProcessMarker(boolean z) {
        mEnableProcessMarkers = z;
    }

    public void setEventTriggered(long j, int i) {
        this.mEventMarkersCodeLast = j;
        long j2 = this.mEventMarkers;
        if (j2 > 0) {
            this.mEventMarkers = j2 + j;
        } else {
            this.mEventMarkers = j2 + j + 1;
        }
        if (i == 2) {
            this.mEventMarkersIsPulse = true;
        }
    }

    public void setEventUntrigger(long j) {
        long j2 = this.mEventMarkers - j;
        this.mEventMarkers = j2;
        if (j2 == 0) {
            this.mEventMarkers = -1L;
        }
    }

    public void setFixedShimmerConfig(FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE fixed_shimmer_config_mode) {
        this.mFixedShimmerConfigMode = fixed_shimmer_config_mode;
    }

    public void setHaveAttemptedToReadConfig(boolean z) {
        this.mHaveAttemptedToReadConfig = z;
    }

    public void setIsConnected(boolean z) {
        this.mIsConnected = z;
    }

    public void setIsInitialised(boolean z) {
        this.mIsInitialised = z;
    }

    public void setIsPlaybackDevice(boolean z) {
        this.mIsPlaybackDevice = z;
    }

    public void setIsSensing(boolean z) {
        this.mIsSensing = z;
    }

    public void setIsStreaming(boolean z) {
        this.mIsStreaming = z;
    }

    public boolean setIsUsbPluggedIn(boolean z) {
        boolean z2 = this.mIsUsbPluggedIn != z;
        this.mIsUsbPluggedIn = z;
        return z2;
    }

    public void setPacketExpectedCountOverall(long j) {
        this.mPacketExpectedCountOverall = j;
    }

    public void setRadio(AbstractSerialPortHal abstractSerialPortHal) {
    }

    public void setShimmerDriveInfo(ShimmerSDCardDetails shimmerSDCardDetails) {
        this.mShimmerSDCardDetails = shimmerSDCardDetails;
    }

    public void setShimmerVersionObject(ShimmerVerObject shimmerVerObject) {
        this.mShimmerVerObject = shimmerVerObject;
    }

    public void setVerboseMode(boolean z) {
        this.mVerboseMode = z;
    }

    public void setupDataProcessing() {
    }

    public void stateHandler(Object obj) {
    }

    public void setShimmerVersionObjectAndCreateSensorMap(ShimmerVerObject shimmerVerObject) {
        setShimmerVersionObject(shimmerVerObject);
        sensorAndConfigMapsCreate();
    }

    public void setHardwareVersionAndCreateSensorMaps(int i) {
        setShimmerVersionObjectAndCreateSensorMap(new ShimmerVerObject(i, getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal()));
    }

    public void clearShimmerVersionObject() {
        setShimmerVersionObject(new ShimmerVerObject());
    }

    public void clearShimmerVersionObjectAndCreateSensorMaps() {
        setShimmerVersionObjectAndCreateSensorMap(new ShimmerVerObject());
    }

    private void updateSensorDetailsWithCommsTypes() {
        LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> linkedHashMap = this.mMapOfSensorClasses;
        if (linkedHashMap != null) {
            Iterator<AbstractSensor> it2 = linkedHashMap.values().iterator();
            while (it2.hasNext()) {
                it2.next().updateSensorDetailsWithCommsTypes(this.mListOfAvailableCommunicationTypes);
            }
        }
    }

    private void updateSamplingRatesMapWithCommsTypes() {
        for (Configuration.COMMUNICATION_TYPE communication_type : this.mListOfAvailableCommunicationTypes) {
            if (!this.mMapOfSamplingRatesShimmer.containsKey(communication_type)) {
                this.mMapOfSamplingRatesShimmer.put(communication_type, Double.valueOf(getSamplingRateShimmer()));
            }
        }
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mMapOfSamplingRatesShimmer.keySet().iterator();
        while (it2.hasNext()) {
            if (!this.mListOfAvailableCommunicationTypes.contains(it2.next())) {
                it2.remove();
            }
        }
    }

    public void generateSensorAndParserMaps() {
        generateSensorMap();
        generateParserMap();
        generateConfigOptionsMap();
        generateSensorGroupingMap();
    }

    private void generateSensorMap() {
        this.mSensorMap = new LinkedHashMap<>();
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            this.mSensorMap.putAll(it2.next().mSensorMap);
        }
    }

    public void generateParserMap() {
        this.mParserMap = new HashMap<>();
        for (Configuration.COMMUNICATION_TYPE communication_type : this.mListOfAvailableCommunicationTypes) {
            for (Integer num : this.mSensorMap.keySet()) {
                SensorDetails sensorDetails = getSensorDetails(num);
                if (sensorDetails.isEnabled(communication_type)) {
                    TreeMap<Integer, SensorDetails> treeMap = this.mParserMap.get(communication_type);
                    if (treeMap == null) {
                        treeMap = new TreeMap<>();
                        this.mParserMap.put(communication_type, treeMap);
                    }
                    treeMap.put(num, sensorDetails);
                }
            }
        }
        updateExpectedDataPacketSize();
    }

    public void generateConfigOptionsMap() {
        this.mConfigOptionsMapSensors = new HashMap();
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            HashMap<String, ConfigOptionDetailsSensor> configOptionsMap = it2.next().getConfigOptionsMap();
            if (configOptionsMap != null && configOptionsMap.keySet().size() > 0) {
                loadCompatibleConfigOptionGroupEntries(configOptionsMap);
            }
        }
    }

    public void generateSensorGroupingMap() {
        this.mSensorGroupingMap = new TreeMap<>();
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            loadCompatibleSensorGroupEntries(it2.next().getSensorGroupingMap());
        }
    }

    protected void loadCompatibleSensorGroupEntries(Map<Integer, SensorGroupingDetails> map) {
        if (map != null) {
            for (Map.Entry<Integer, SensorGroupingDetails> entry : map.entrySet()) {
                if (isVerCompatibleWithAnyOf(entry.getValue().mListOfCompatibleVersionInfo)) {
                    this.mSensorGroupingMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    protected void loadCompatibleConfigOptionGroupEntries(Map<String, ConfigOptionDetailsSensor> map) {
        if (map != null) {
            for (Map.Entry<String, ConfigOptionDetailsSensor> entry : map.entrySet()) {
                if (isVerCompatibleWithAnyOf(entry.getValue().mListOfCompatibleVersionInfo)) {
                    this.mConfigOptionsMapSensors.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public void clearBattStatusDetails() {
        setBattStatusDetails(new ShimmerBattStatusDetails());
    }

    public void setShimmerUserAssignedNameNoLengthCheck(String str) {
        this.mShimmerUserAssignedName = str.replace(StringUtils.SPACE, "_");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setShimmerUserAssignedNameWithMac(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.length()
            r1 = 0
            if (r0 == 0) goto L1d
            char r0 = r5.charAt(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            boolean r0 = com.shimmerresearch.driverUtilities.UtilShimmer.isNumeric(r0)
            if (r0 == 0) goto L2b
        L1d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "S"
            r0.<init>(r2)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
        L2b:
            java.lang.String r0 = "[^A-Za-z0-9._]"
            java.lang.String r2 = ""
            java.lang.String r5 = r5.replaceAll(r0, r2)
            java.lang.String r0 = r4.getMacIdParsed()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "_"
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            int r2 = r5.length()
            int r3 = r0.length()
            int r2 = r2 + r3
            r3 = 12
            if (r2 <= r3) goto L6e
            int r2 = r0.length()
            int r3 = r3 - r2
            java.lang.String r5 = r5.substring(r1, r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            r1.append(r0)
            java.lang.String r5 = r1.toString()
            r4.setShimmerUserAssignedNameNoLengthCheck(r5)
            goto L80
        L6e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            r1.append(r0)
            java.lang.String r5 = r1.toString()
            r4.setShimmerUserAssignedNameNoLengthCheck(r5)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.driver.ShimmerDevice.setShimmerUserAssignedNameWithMac(java.lang.String):void");
    }

    public void setLastReadRealTimeClockValue(long j) {
        this.mShimmerLastReadRtcValueMilliSecs = j;
        this.mShimmerLastReadRtcValueParsed = UtilShimmer.fromMilToDateExcelCompatible(Long.toString(j), false);
    }

    public void setShimmerInfoMemBytes(byte[] bArr) {
        configBytesParse(bArr);
    }

    public void untriggerEventIfLastOneWasPulse() {
        if (this.mEventMarkersIsPulse) {
            this.mEventMarkersIsPulse = false;
            setEventUntrigger(this.mEventMarkersCodeLast);
        }
    }

    public void processEventMarkerCh(ObjectCluster objectCluster) {
        if (mEnableProcessMarkers) {
            objectCluster.addCalDataToMap(SensorShimmerClock.channelEventMarker, this.mEventMarkers);
            untriggerEventIfLastOneWasPulse();
        }
    }

    public void addDOCKCoummnicationRoute(String str, int i) {
        setDockInfo(str, i);
        addCommunicationRoute(Configuration.COMMUNICATION_TYPE.DOCK);
    }

    public void clearDockInfo(String str, int i) {
        setDockInfo(str, i);
    }

    public void setDockInfo(String str, int i) {
        this.mDockID = str;
        parseDockType();
        this.mSlotNumber = i;
        this.mUniqueID = this.mDockID + "." + String.format("%02d", Integer.valueOf(i));
    }

    public void addCommunicationRoute(Configuration.COMMUNICATION_TYPE communication_type) {
        if (!this.mListOfAvailableCommunicationTypes.contains(communication_type)) {
            this.mListOfAvailableCommunicationTypes.add(communication_type);
        }
        if (communication_type == Configuration.COMMUNICATION_TYPE.DOCK) {
            setIsDocked(true);
        }
        updateSensorDetailsWithCommsTypes();
        updateSamplingRatesMapWithCommsTypes();
    }

    public void setCommunicationRoute(Configuration.COMMUNICATION_TYPE communication_type) {
        this.mListOfAvailableCommunicationTypes.clear();
        addCommunicationRoute(communication_type);
    }

    public void removeCommunicationRoute(Configuration.COMMUNICATION_TYPE communication_type) {
        if (this.mListOfAvailableCommunicationTypes.contains(communication_type)) {
            this.mListOfAvailableCommunicationTypes.remove(communication_type);
        }
        if (communication_type == Configuration.COMMUNICATION_TYPE.DOCK) {
            setIsDocked(false);
            setFirstDockRead();
            clearDockInfo(this.mDockID, this.mSlotNumber);
        }
        updateSensorDetailsWithCommsTypes();
        updateSamplingRatesMapWithCommsTypes();
    }

    public boolean isCommunicationRouteAvailable(Configuration.COMMUNICATION_TYPE communication_type) {
        return this.mListOfAvailableCommunicationTypes.contains(communication_type);
    }

    public boolean isFirstSdAccess() {
        return this.mShimmerSDCardDetails.isFirstSdAccess();
    }

    public void setFirstSdAccess(boolean z) {
        this.mShimmerSDCardDetails.setFirstSdAccess(z);
    }

    public boolean isSDErrorOrNotPresent() {
        return this.mShimmerSDCardDetails.isSDError() || !this.mShimmerSDCardDetails.isSDPresent();
    }

    public boolean isSDError() {
        return this.mShimmerSDCardDetails.isSDError();
    }

    public void setIsSDError(boolean z) {
        this.mShimmerSDCardDetails.setIsSDError(z);
    }

    public boolean isSDPresent() {
        return this.mShimmerSDCardDetails.isSDPresent();
    }

    public void setIsSDPresent(boolean z) {
        this.mShimmerSDCardDetails.setIsSDPresent(z);
    }

    public boolean isSDLogging() {
        return this.mShimmerSDCardDetails.isSDLogging();
    }

    public void setIsSDLogging(boolean z) {
        this.mShimmerSDCardDetails.setIsSDLogging(z);
    }

    public long getDriveTotalSpace() {
        return this.mShimmerSDCardDetails.getDriveTotalSpace();
    }

    public String getDriveUsedSpaceParsed() {
        return this.mShimmerSDCardDetails.getDriveUsedSpaceParsed();
    }

    public long getDriveUsedSpace() {
        return this.mShimmerSDCardDetails.getDriveUsedSpace();
    }

    public void setDriveUsedSpace(long j) {
        this.mShimmerSDCardDetails.setDriveUsedSpaceBytes(j);
    }

    public long getDriveUsedSpaceKB() {
        return this.mShimmerSDCardDetails.getDriveUsedSpaceKB();
    }

    public void setDriveUsedSpaceKB(long j) {
        this.mShimmerSDCardDetails.setDriveUsedSpaceKB(j);
    }

    public String getDriveTotalSpaceParsed() {
        return this.mShimmerSDCardDetails.getDriveTotalSpaceParsed();
    }

    public long getDriveUsableSpace() {
        return this.mShimmerSDCardDetails.getDriveUsableSpace();
    }

    public long getDriveFreeSpace() {
        return this.mShimmerSDCardDetails.getDriveFreeSpace();
    }

    public String getDriveCapacityParsed() {
        if (!isSupportedSdCardAccess()) {
            return "Unknown";
        }
        if (isSDErrorOrNotPresent()) {
            return STRING_CONSTANT_SD_ERROR;
        }
        String driveUsedSpaceParsed = getDriveUsedSpaceParsed();
        if (driveUsedSpaceParsed.isEmpty()) {
            return (!isHaveAttemptedToRead() && isFirstSdAccess()) ? STRING_CONSTANT_PENDING : "Unknown";
        }
        if (isShimmerGenGq() && isSDLogging()) {
            driveUsedSpaceParsed = Long.toString(getDriveUsedSpaceKB()) + " KB";
        }
        return driveUsedSpaceParsed + " / " + getDriveTotalSpaceParsed();
    }

    public boolean isSDSpaceIncreasing() {
        return this.mShimmerSDCardDetails.isSDSpaceIncreasing();
    }

    public int getHardwareVersion() {
        return this.mShimmerVerObject.mHardwareVersion;
    }

    public void setHardwareVersion(int i) {
        setShimmerVersionObject(new ShimmerVerObject(i, getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal()));
    }

    public int getFirmwareIdentifier() {
        return this.mShimmerVerObject.mFirmwareIdentifier;
    }

    public int getFirmwareVersionMajor() {
        return this.mShimmerVerObject.mFirmwareVersionMajor;
    }

    public int getFirmwareVersionMinor() {
        return this.mShimmerVerObject.mFirmwareVersionMinor;
    }

    public int getFirmwareVersionInternal() {
        return this.mShimmerVerObject.mFirmwareVersionInternal;
    }

    public int getFirmwareVersionCode() {
        return this.mShimmerVerObject.mFirmwareVersionCode;
    }

    public String getFirmwareVersionParsed() {
        return this.mShimmerVerObject.mFirmwareVersionParsed;
    }

    public String getHardwareVersionParsed() {
        return this.mShimmerVerObject.getHardwareVersionParsed();
    }

    public int getExpansionBoardId() {
        return this.mExpansionBoardDetails.mExpansionBoardId;
    }

    public int getExpansionBoardRev() {
        return this.mExpansionBoardDetails.mExpansionBoardRev;
    }

    public int getExpansionBoardRevSpecial() {
        return this.mExpansionBoardDetails.mExpansionBoardRevSpecial;
    }

    public String getExpansionBoardParsed() {
        return this.mExpansionBoardDetails.getExpansionBoardParsed();
    }

    public String getExpansionBoardParsedWithVer() {
        return this.mExpansionBoardDetails.getExpansionBoardParsedWithVer();
    }

    public String getExpansionBoardVerParsed() {
        return this.mExpansionBoardDetails.getBoardVerString();
    }

    public void clearExpansionBoardDetails() {
        this.mExpansionBoardDetails = new ExpansionBoardDetails();
    }

    public void setExpansionBoardDetailsAndCreateSensorMap(ExpansionBoardDetails expansionBoardDetails) {
        setExpansionBoardDetails(expansionBoardDetails);
        sensorAndConfigMapsCreate();
    }

    public String getChargingStateParsed() {
        return this.mShimmerBattStatusDetails.getChargingStatusParsed();
    }

    public String getBattVoltage() {
        return this.mShimmerBattStatusDetails.getBattVoltageParsed();
    }

    public String getEstimatedChargePercentageParsed() {
        return this.mShimmerBattStatusDetails.getEstimatedChargePercentageParsed();
    }

    public Double getEstimatedChargePercentage() {
        return Double.valueOf(this.mShimmerBattStatusDetails.getEstimatedChargePercentage());
    }

    public ShimmerBattStatusDetails.BATTERY_LEVEL getEstimatedBatteryLevel() {
        return this.mShimmerBattStatusDetails.getEstimatedBatteryLevel();
    }

    public boolean setIsDocked(boolean z) {
        boolean z2 = this.mIsDocked != z;
        this.mIsDocked = z;
        if (z) {
            this.mDockState = DOCK_STATE.DOCKED;
        } else {
            this.mDockState = DOCK_STATE.UNDOCKED;
        }
        if (z2) {
            stateHandler(this.mDockState);
        }
        if (this.mUpdateOnlyWhenStateChanges) {
            return z2;
        }
        return true;
    }

    public boolean isSensing() {
        return this.mIsSensing || isSDLogging() || isStreaming();
    }

    public boolean isHaveAttemptedToRead() {
        return haveAttemptedToRead();
    }

    public boolean isShimmerError() {
        return isSDErrorOrNotPresent();
    }

    public String getMacIdFromUartParsed() {
        int length;
        String str = this.mMacIdFromUart;
        return (str == null || (length = str.length()) < 12) ? "0000" : this.mMacIdFromUart.substring(length - 4, length);
    }

    public HashMap<Integer, String> getMapOfConfigByteDescriptions() {
        ConfigByteLayout configByteLayout = this.mConfigByteLayout;
        if (configByteLayout != null) {
            return configByteLayout.getMapOfByteDescriptions();
        }
        return null;
    }

    @Deprecated
    public double getPacketReceptionRate() {
        return getPacketReceptionRateOverall();
    }

    protected void resetShimmerClock() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.CLOCK);
        if (sensorClass == null || !(sensorClass instanceof SensorShimmerClock)) {
            return;
        }
        ((SensorShimmerClock) sensorClass).resetShimmerClock();
    }

    protected void resetPacketLossVariables() {
        this.mPacketLossCountPerTrial = 0L;
        resetPacketReceptionOverallVariables();
        resetPacketReceptionCurrentVariables();
    }

    public void incrementPacketsReceivedCounters() {
        incrementPacketReceivedCountCurrent();
        incrementPacketReceivedCountOverall();
    }

    public void resetPacketReceptionOverallVariables() {
        this.mPacketExpectedCountOverall = 0L;
        this.mPacketReceivedCountOverall = 0L;
        setPacketReceptionRateOverall(0.0d);
    }

    public void resetPacketReceptionCurrentVariables() {
        resetPacketReceptionCurrentCounters();
        setPacketReceptionRateCurrent(0.0d);
    }

    public String getConfigTimeLongString() {
        return Long.toString(getConfigTime());
    }

    public String getConfigTimeParsed() {
        return UtilShimmer.convertSecondsToDateString(this.mConfigTime);
    }

    public String getConfigTimeExcelCompatible() {
        return UtilShimmer.fromMilToDateExcelCompatible(Long.toString(this.mConfigTime * 1000), false);
    }

    public ConfigByteLayout getConfigByteLayout() {
        createInfoMemLayoutObjectIfNeeded();
        return this.mConfigByteLayout;
    }

    public int getExpectedInfoMemByteLength() {
        createInfoMemLayoutObjectIfNeeded();
        return this.mConfigByteLayout.mInfoMemSize;
    }

    public void createInfoMemLayoutObjectIfNeeded() {
        ConfigByteLayout configByteLayout = this.mConfigByteLayout;
        if (configByteLayout == null || configByteLayout.isDifferent(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal())) {
            createConfigBytesLayout();
        }
    }

    protected void parseDockType() {
        if (this.mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASICDOCK.getLabel())) {
            this.mDockType = HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASICDOCK;
            setHaveAttemptedToReadConfig(true);
        } else if (this.mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE15.getLabel())) {
            this.mDockType = HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE15;
        } else if (this.mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE6.getLabel())) {
            this.mDockType = HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE6;
        } else {
            this.mDockType = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
        }
    }

    public ObjectCluster buildMsg(byte[] bArr, byte[] bArr2, Configuration.COMMUNICATION_TYPE communication_type, boolean z, double d) {
        interpretDataPacketFormat(bArr, communication_type);
        return buildMsg(bArr2, communication_type, z, d);
    }

    public ObjectCluster buildMsg(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, boolean z, double d) {
        ObjectCluster objectCluster = new ObjectCluster(this.mShimmerUserAssignedName, getMacId());
        objectCluster.mRawData = bArr;
        objectCluster.createArrayData(getNumberOfEnabledChannels(communication_type));
        incrementPacketsReceivedCounters();
        TreeMap<Integer, SensorDetails> treeMap = this.mParserMap.get(communication_type);
        if (treeMap != null) {
            int i = 0;
            for (SensorDetails sensorDetails : treeMap.values()) {
                int expectedPacketByteArray = sensorDetails.getExpectedPacketByteArray(communication_type);
                byte[] bArr2 = new byte[expectedPacketByteArray];
                if (expectedPacketByteArray != 0) {
                    if (i + expectedPacketByteArray <= bArr.length) {
                        System.arraycopy(bArr, i, bArr2, 0, expectedPacketByteArray);
                    } else {
                        consolePrintLn(this.mShimmerUserAssignedName + " ERROR PARSING " + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                    }
                }
                sensorDetails.processData(bArr2, communication_type, objectCluster, z, d);
                i += expectedPacketByteArray;
            }
        } else {
            consolePrintErrLn("ERROR!!!! Parser map null");
        }
        return processData(objectCluster);
    }

    protected ObjectCluster processData(ObjectCluster objectCluster) {
        DataProcessingInterface dataProcessingInterface = this.mDataProcessing;
        if (dataProcessingInterface != null) {
            objectCluster = dataProcessingInterface.processData(objectCluster);
        }
        return processAlgorithms(objectCluster);
    }

    public ObjectCluster processAlgorithms(ObjectCluster objectCluster) {
        ArrayList<String> arrayList = this.mAlgorithmProcessingSequence;
        if (arrayList != null) {
            Iterator<String> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                AbstractAlgorithm abstractAlgorithm = getMapOfAlgorithmModules().get(it2.next());
                if (abstractAlgorithm.isEnabled()) {
                    try {
                        AlgorithmResultObject algorithmResultObjectProcessDataRealTime = abstractAlgorithm.processDataRealTime(objectCluster);
                        if (algorithmResultObjectProcessDataRealTime != null) {
                            objectCluster = (ObjectCluster) algorithmResultObjectProcessDataRealTime.mResult;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (AbstractAlgorithm abstractAlgorithm2 : getMapOfAlgorithmModules().values()) {
                if (abstractAlgorithm2.isEnabled()) {
                    try {
                        AlgorithmResultObject algorithmResultObjectProcessDataRealTime2 = abstractAlgorithm2.processDataRealTime(objectCluster);
                        if (algorithmResultObjectProcessDataRealTime2 != null) {
                            objectCluster = (ObjectCluster) algorithmResultObjectProcessDataRealTime2.mResult;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return objectCluster;
    }

    public int getExpectedDataPacketSize() {
        if (this.mListOfAvailableCommunicationTypes.size() == 0) {
            return 0;
        }
        generateParserMap();
        return getExpectedDataPacketSize(this.mListOfAvailableCommunicationTypes.get(0));
    }

    public int getExpectedDataPacketSize(Configuration.COMMUNICATION_TYPE communication_type) {
        TreeMap<Integer, SensorDetails> treeMap = this.mParserMap.get(communication_type);
        int i = 0;
        if (treeMap != null) {
            Iterator<SensorDetails> it2 = treeMap.values().iterator();
            while (it2.hasNext()) {
                int expectedDataPacketSize = it2.next().getExpectedDataPacketSize();
                if (expectedDataPacketSize > 0) {
                    i += expectedDataPacketSize;
                }
            }
        }
        return i;
    }

    public int getNumberOfEnabledChannels(Configuration.COMMUNICATION_TYPE communication_type) {
        int numberOfChannels = 0;
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            if (sensorDetails.isEnabled(communication_type)) {
                numberOfChannels += sensorDetails.getNumberOfChannels();
            }
        }
        return numberOfChannels;
    }

    public String getMacIdParsed() {
        String macId = getMacId();
        if (macId.length() < 12) {
            return "";
        }
        String strReplace = macId.replace(":", "");
        return strReplace.substring(strReplace.length() - 4, strReplace.length());
    }

    public void setTrialNameAndCheck(String str) {
        String strReplaceAll = str.replaceAll(INVALID_TRIAL_NAME_CHAR, "");
        if (strReplaceAll.isEmpty()) {
            strReplaceAll = DEFAULT_EXPERIMENT_NAME;
        }
        if (strReplaceAll.length() > 12) {
            strReplaceAll = strReplaceAll.substring(0, 11);
        }
        setTrialName(strReplaceAll);
    }

    public boolean isTrialNameInvalid() {
        return isTrialOrShimmerNameInvalid(getTrialName());
    }

    public boolean isShimmerNameInvalid() {
        return isTrialOrShimmerNameInvalid(getShimmerUserAssignedName()) || getShimmerUserAssignedName().equals(DEFAULT_SHIMMER_NAME_WITH_ERROR);
    }

    public boolean isShimmerNameValid() {
        return !isShimmerNameInvalid();
    }

    public void setTrialConfig(String str, long j) {
        setTrialName(str);
        this.mConfigTime = j;
    }

    public void setFirstDockRead() {
        this.mShimmerSDCardDetails.setFirstDockRead();
        setConfigurationReadSuccess(false);
        this.mReadHwFwSuccess = false;
        this.mReadDaughterIDSuccess = false;
        this.writeRealWorldClockFromPcTimeSuccess = false;
    }

    public boolean doesSensorKeyExist(int i) {
        return this.mSensorMap.containsKey(Integer.valueOf(i));
    }

    public boolean isSensorEnabled(int i) {
        SensorDetails sensorDetails;
        if (this.mSensorMap == null || (sensorDetails = getSensorDetails(Integer.valueOf(i))) == null) {
            return false;
        }
        return sensorDetails.isEnabled();
    }

    public boolean isSensorEnabled(Configuration.COMMUNICATION_TYPE communication_type, int i) {
        SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(i));
        if (sensorDetails != null) {
            return sensorDetails.isEnabled(communication_type);
        }
        return false;
    }

    public String getSensorLabel(int i) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            String sensorGuiFriendlyLabel = it2.next().getSensorGuiFriendlyLabel(i);
            if (sensorGuiFriendlyLabel != null) {
                return sensorGuiFriendlyLabel;
            }
        }
        return null;
    }

    public List<ShimmerVerObject> getListOfCompatibleVersionInfoForSensor(int i) {
        SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(i));
        if (sensorDetails != null) {
            return sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo;
        }
        return null;
    }

    public Set<Integer> getSensorIdsSet() {
        TreeSet treeSet = new TreeSet();
        treeSet.addAll(this.mSensorMap.keySet());
        return treeSet;
    }

    public void configBytesParse(byte[] bArr) {
        configBytesParse(bArr, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
    }

    public byte[] configBytesGenerate(boolean z) {
        return configBytesGenerate(z, Configuration.COMMUNICATION_TYPE.BLUETOOTH);
    }

    public Object setConfigValueUsingConfigLabel(String str, Object obj) {
        return setConfigValueUsingConfigLabel("", str, obj);
    }

    public Object setConfigValueUsingConfigLabel(Integer num, String str, Object obj) {
        return setConfigValueUsingConfigLabel(Integer.toString(num.intValue()), str, obj);
    }

    public Object setConfigValueUsingConfigLabel(String str, String str2, Object obj) {
        Object sensorClassSetting = setSensorClassSetting(str, str2, obj);
        if (sensorClassSetting != null) {
            return sensorClassSetting;
        }
        setAlgorithmSettings(str, str2, obj);
        str2.hashCode();
        switch (str2) {
            case "Trial Name":
                setTrialNameAndCheck((String) obj);
                break;
            case "Calibration":
                setSensorCalibrationPerSensor(Integer.valueOf(Integer.parseInt(str)), (TreeMap) obj);
                break;
            case "Sampling Rate":
                Double dValueOf = Double.valueOf(1.0d);
                if (obj instanceof String) {
                    String str3 = (String) obj;
                    if (str3.isEmpty()) {
                        dValueOf = Double.valueOf(1.0d);
                    } else {
                        dValueOf = Double.valueOf(Double.parseDouble(str3));
                    }
                } else if (obj instanceof Double) {
                    dValueOf = (Double) obj;
                }
                setShimmerAndSensorsSamplingRate(dValueOf.doubleValue());
                break;
            case "Shimmer Name":
                setShimmerUserAssignedName((String) obj);
                break;
            case "Internal Expansion Board Power":
                setInternalExpPower(((Boolean) obj).booleanValue());
                break;
            case "Int Exp Power":
                setInternalExpPower(((Integer) obj).intValue());
                break;
            case "Calibration all":
                setMapOfSensorCalibrationAll((TreeMap) obj);
                break;
        }
        return sensorClassSetting;
    }

    public Object getConfigValueUsingConfigLabel(String str) {
        return getConfigValueUsingConfigLabel("", str);
    }

    public Object getConfigValueUsingConfigLabel(Integer num, String str) {
        return getConfigValueUsingConfigLabel(Integer.toString(num.intValue()), str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getConfigValueUsingConfigLabel(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.getSensorClassSetting(r4, r5)
            if (r0 == 0) goto L7
            return r0
        L7:
            java.lang.Object r0 = r3.getAlgorithmSettings(r4, r5)
            if (r0 == 0) goto Le
            return r0
        Le:
            r5.hashCode()
            int r1 = r5.hashCode()
            r2 = -1
            switch(r1) {
                case -1889687147: goto L69;
                case -1205780022: goto L5e;
                case 421587321: goto L53;
                case 753514122: goto L48;
                case 876997639: goto L3d;
                case 1517328881: goto L32;
                case 1590121035: goto L27;
                case 1626248427: goto L1c;
                default: goto L19;
            }
        L19:
            r5 = -1
            goto L73
        L1c:
            java.lang.String r1 = "Config Time"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L25
            goto L19
        L25:
            r5 = 7
            goto L73
        L27:
            java.lang.String r1 = "Calibration all"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L30
            goto L19
        L30:
            r5 = 6
            goto L73
        L32:
            java.lang.String r1 = "Int Exp Power"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L3b
            goto L19
        L3b:
            r5 = 5
            goto L73
        L3d:
            java.lang.String r1 = "Internal Expansion Board Power"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L46
            goto L19
        L46:
            r5 = 4
            goto L73
        L48:
            java.lang.String r1 = "Shimmer Name"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L51
            goto L19
        L51:
            r5 = 3
            goto L73
        L53:
            java.lang.String r1 = "Sampling Rate"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L5c
            goto L19
        L5c:
            r5 = 2
            goto L73
        L5e:
            java.lang.String r1 = "Calibration"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L67
            goto L19
        L67:
            r5 = 1
            goto L73
        L69:
            java.lang.String r1 = "Trial Name"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L72
            goto L19
        L72:
            r5 = 0
        L73:
            switch(r5) {
                case 0: goto Lc6;
                case 1: goto Lb5;
                case 2: goto L98;
                case 3: goto L93;
                case 4: goto L8a;
                case 5: goto L81;
                case 6: goto L7c;
                case 7: goto L77;
                default: goto L76;
            }
        L76:
            goto Lca
        L77:
            java.lang.String r0 = r3.getConfigTimeParsed()
            goto Lca
        L7c:
            java.util.TreeMap r0 = r3.getMapOfSensorCalibrationAll()
            goto Lca
        L81:
            int r4 = r3.getInternalExpPower()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            goto Lca
        L8a:
            boolean r4 = r3.isInternalExpPower()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
            goto Lca
        L93:
            java.lang.String r0 = r3.getShimmerUserAssignedName()
            goto Lca
        L98:
            double r4 = r3.getSamplingRateShimmer()
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            double r4 = r4.doubleValue()
            double r0 = r3.getSamplingClockFreq()
            double r4 = roundSamplingRateToSupportedValue(r4, r0)
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            java.lang.String r0 = r4.toString()
            goto Lca
        Lb5:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> Lc1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.NumberFormatException -> Lc1
        Lc1:
            java.util.TreeMap r0 = r3.getMapOfSensorCalibrationPerSensor(r5)
            goto Lca
        Lc6:
            java.lang.String r0 = r3.getTrialName()
        Lca:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shimmerresearch.driver.ShimmerDevice.getConfigValueUsingConfigLabel(java.lang.String, java.lang.String):java.lang.Object");
    }

    public String getConfigGuiValueUsingConfigLabel(Object obj, String str) {
        Object configValueUsingConfigLabel;
        if (obj instanceof Integer) {
            configValueUsingConfigLabel = getConfigValueUsingConfigLabel((Integer) obj, str);
        } else {
            configValueUsingConfigLabel = obj instanceof String ? getConfigValueUsingConfigLabel((String) obj, str) : null;
        }
        if (configValueUsingConfigLabel != null) {
            if (configValueUsingConfigLabel instanceof String) {
                return (String) configValueUsingConfigLabel;
            }
            if (configValueUsingConfigLabel instanceof Boolean) {
                return configValueUsingConfigLabel.toString();
            }
            if (configValueUsingConfigLabel instanceof Integer) {
                int iIntValue = ((Integer) configValueUsingConfigLabel).intValue();
                Map<String, ConfigOptionDetailsSensor> configOptionsMap = getConfigOptionsMap();
                if (configOptionsMap != null && configOptionsMap.containsKey(str) && configOptionsMap.get(str) != null) {
                    return getConfigOptionsMap().get(str).getConfigStringFromConfigValue(Integer.valueOf(iIntValue));
                }
                return Integer.toString(iIntValue);
            }
        }
        return "";
    }

    private Object setSensorClassSetting(String str, String str2, Object obj) {
        int iValueOf = -1;
        try {
            iValueOf = Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
        }
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        Object configValueUsingConfigLabel = null;
        while (it2.hasNext() && (configValueUsingConfigLabel = it2.next().setConfigValueUsingConfigLabel(iValueOf, str2, obj)) == null) {
        }
        return configValueUsingConfigLabel;
    }

    private Object getSensorClassSetting(String str, String str2) {
        int iValueOf = -1;
        try {
            iValueOf = Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
        }
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        Object configValueUsingConfigLabel = null;
        while (it2.hasNext() && (configValueUsingConfigLabel = it2.next().getConfigValueUsingConfigLabel(iValueOf, str2)) == null) {
        }
        return configValueUsingConfigLabel;
    }

    public void setAlgorithmSettings(String str, Object obj) {
        setAlgorithmSettings(null, str, obj);
    }

    public void setAlgorithmSettings(String str, String str2, Object obj) {
        List<AbstractAlgorithm> listOfAlgorithmModules;
        if (str != null && !str.isEmpty()) {
            listOfAlgorithmModules = getListOfAlgorithmModulesPerGroup(str);
        } else {
            listOfAlgorithmModules = getListOfAlgorithmModules();
        }
        if (listOfAlgorithmModules == null || listOfAlgorithmModules.isEmpty()) {
            return;
        }
        Iterator<AbstractAlgorithm> it2 = listOfAlgorithmModules.iterator();
        while (it2.hasNext()) {
            it2.next().setSettings(str2, obj);
        }
    }

    public Object getAlgorithmSettings(String str, String str2) {
        List<AbstractAlgorithm> listOfAlgorithmModulesPerGroup;
        if (str.isEmpty()) {
            listOfAlgorithmModulesPerGroup = getListOfAlgorithmModules();
        } else {
            listOfAlgorithmModulesPerGroup = getListOfAlgorithmModulesPerGroup(str);
        }
        Object settings = null;
        if (listOfAlgorithmModulesPerGroup != null && !listOfAlgorithmModulesPerGroup.isEmpty()) {
            Iterator<AbstractAlgorithm> it2 = listOfAlgorithmModulesPerGroup.iterator();
            while (it2.hasNext() && (settings = it2.next().getSettings(str2)) == null) {
            }
        }
        return settings;
    }

    public boolean isSupportedRtcConfigViaUart() {
        return this.mShimmerVerObject.isSupportedRtcConfigViaUart();
    }

    public boolean isSupportedConfigViaUart() {
        return this.mShimmerVerObject.isSupportedConfigViaUart();
    }

    public boolean isSupportedSdCardAccess() {
        return this.mShimmerVerObject.isSupportedSdCardAccess();
    }

    public boolean isSupportedBluetooth() {
        return this.mShimmerVerObject.isSupportedBluetooth();
    }

    public boolean isSupportedCalibDump() {
        return this.mShimmerVerObject.isSupportedCalibDump();
    }

    public boolean isShimmerGen2() {
        return this.mShimmerVerObject.isShimmerGen2();
    }

    public boolean isShimmerGen3() {
        return this.mShimmerVerObject.isShimmerGen3();
    }

    public boolean isShimmerGen3R() {
        return this.mShimmerVerObject.isShimmerGen3R();
    }

    public boolean isShimmerGen3or3R() {
        return this.mShimmerVerObject.isShimmerGen3() || this.mShimmerVerObject.isShimmerGen3R();
    }

    public boolean isShimmerGen4() {
        return this.mShimmerVerObject.isShimmerGen4();
    }

    public boolean isShimmerGenGq() {
        return this.mShimmerVerObject.isShimmerGenGq();
    }

    public boolean isShimmerVideoDevice() {
        return this.mShimmerVerObject.isShimmerVideoDevice();
    }

    public boolean isSupportedSrProgViaDock() {
        return this.mShimmerVerObject.compareVersions(3, 1, 0, 7, 13) || this.mShimmerVerObject.compareVersions(3, 3, 0, 8, 1) || isShimmerGen3R();
    }

    public boolean isSupportedSdLogSync() {
        return getFirmwareIdentifier() == 2 || UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3RLogAndStream) || UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3LogAndStreamWithSDLogSyncSupport) || getFirmwareIdentifier() == 15;
    }

    public boolean isHWAndFWSupportedBtBleControl() {
        return getFirmwareIdentifier() == 3 && this.mShimmerVerObject.compareVersions(3, 3, 1, 0, 4) && isShimmerGen3();
    }

    public boolean isLegacySdLog() {
        return getFirmwareIdentifier() == 2 && getFirmwareVersionMajor() == 0 && getFirmwareVersionMinor() == 5;
    }

    public boolean isSupportedDerivedSensors() {
        return isSupportedDerivedSensors(this.mShimmerVerObject);
    }

    public boolean isSupportedNoImuSensors() {
        return isSupportedNoImuSensors(getShimmerVerObject(), getExpansionBoardDetails());
    }

    public boolean isVerCompatibleWithAnyOf(List<ShimmerVerObject> list) {
        int shimmerExpansionBoardRev;
        if (list == null || list.isEmpty()) {
            return true;
        }
        for (ShimmerVerObject shimmerVerObject : list) {
            int shimmerExpansionBoardId = shimmerVerObject.getShimmerExpansionBoardId();
            if (shimmerExpansionBoardId == -1 || (shimmerExpansionBoardId == getExpansionBoardId() && ((shimmerExpansionBoardRev = shimmerVerObject.getShimmerExpansionBoardRev()) == -1 || shimmerExpansionBoardRev <= getExpansionBoardRev()))) {
                if (isThisVerCompatibleWith(shimmerVerObject.mHardwareVersion, shimmerVerObject.mFirmwareIdentifier, shimmerVerObject.mFirmwareVersionMajor, shimmerVerObject.mFirmwareVersionMinor, shimmerVerObject.mFirmwareVersionInternal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isThisVerCompatibleWith(int i, int i2, int i3, int i4) {
        return UtilShimmer.compareVersions(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), i, i2, i3, i4);
    }

    public boolean isThisVerCompatibleWith(int i, int i2, int i3, int i4, int i5) {
        return UtilShimmer.compareVersions(getHardwareVersion(), getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), i, i2, i3, i4, i5);
    }

    public void consolePrintExeptionLn(String str, StackTraceElement[] stackTraceElementArr) {
        if (this.mVerboseMode) {
            consolePrintErrLn(str + StringUtils.LF + UtilShimmer.convertStackTraceToString(stackTraceElementArr));
        }
    }

    public void consolePrintErrLn(String str) {
        if (this.mVerboseMode) {
            System.err.println(assemblePrintString(str));
        }
    }

    public void consolePrintLn(String str) {
        if (this.mVerboseMode) {
            System.out.println(assemblePrintString(str));
        }
    }

    public void consolePrint(String str) {
        if (this.mVerboseMode) {
            System.out.print(str);
        }
    }

    private String assemblePrintString(String str) {
        Calendar calendar = Calendar.getInstance();
        return ("[" + String.format("%02d", Integer.valueOf(calendar.get(11))) + ":" + String.format("%02d", Integer.valueOf(calendar.get(12))) + ":" + String.format("%02d", Integer.valueOf(calendar.get(13))) + ":" + String.format("%03d", Integer.valueOf(calendar.get(14))) + "]") + StringUtils.SPACE + getClass().getSimpleName() + " (Mac:" + getMacIdParsed() + " HashCode:" + Integer.toHexString(hashCode()) + "): " + str;
    }

    protected void checkIfInternalExpBrdPowerIsNeeded() {
        Iterator<SensorDetails> it2 = this.mSensorMap.values().iterator();
        while (it2.hasNext()) {
            if (it2.next().isInternalExpBrdPowerRequired()) {
                this.mInternalExpPower = 1;
                return;
            } else if (!isSensorEnabled(17) && !isSensorEnabled(7) && !isSensorEnabled(10) && !isSensorEnabled(13)) {
                this.mInternalExpPower = 0;
            }
        }
    }

    public void setSensorIdsEnabled(Integer[] numArr) {
        int i;
        Iterator<Integer> it2 = this.mSensorMap.keySet().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            } else {
                setSensorEnabledState(it2.next().intValue(), false);
            }
        }
        for (Integer num : numArr) {
            setSensorEnabledState(num.intValue(), true);
        }
    }

    public boolean setSensorEnabledState(int i, boolean z) {
        if (this.mSensorMap != null) {
            int iHandleSpecCasesBeforeSetSensorState = handleSpecCasesBeforeSetSensorState(i, z);
            SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(iHandleSpecCasesBeforeSetSensorState));
            if (sensorDetails != null) {
                List<Integer> list = sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired;
                if (list != null && list.size() > 0) {
                    Iterator<Integer> it2 = list.iterator();
                    while (it2.hasNext()) {
                        getSensorDetails(it2.next()).setIsEnabled(z);
                    }
                }
                sensorDetails.setIsEnabled(z);
                setSensorEnabledStateCommon(iHandleSpecCasesBeforeSetSensorState, sensorDetails.isEnabled());
                boolean z2 = sensorDetails.isEnabled() == z;
                if (!z2) {
                    consolePrintLn("WARNING!!! Failed to setSensorEnabledState for sensor:\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                    UtilShimmer.consolePrintCurrentStackTrace();
                }
                return z2;
            }
            consolePrintLn("WARNING!!! SensorID not found:" + iHandleSpecCasesBeforeSetSensorState);
            UtilShimmer.consolePrintCurrentStackTrace();
            return false;
        }
        consolePrintLn("setSensorEnabledState:\t SensorMap=null");
        return false;
    }

    public boolean setSensorEnabledState(int i, boolean z, Configuration.COMMUNICATION_TYPE communication_type) {
        if (this.mSensorMap != null) {
            int iHandleSpecCasesBeforeSetSensorState = handleSpecCasesBeforeSetSensorState(i, z);
            SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(iHandleSpecCasesBeforeSetSensorState));
            if (sensorDetails != null) {
                List<Integer> list = sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired;
                if (list != null && list.size() > 0) {
                    Iterator<Integer> it2 = list.iterator();
                    while (it2.hasNext()) {
                        getSensorDetails(it2.next()).setIsEnabled(communication_type, z);
                    }
                }
                sensorDetails.setIsEnabled(communication_type, z);
                setSensorEnabledStateCommon(iHandleSpecCasesBeforeSetSensorState, sensorDetails.isEnabled(communication_type));
                z = sensorDetails.isEnabled(communication_type) == z;
                if (!z) {
                    consolePrintErrLn("Failed to setSensorEnabledState for sensor:\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                }
            }
            return z;
        }
        consolePrintLn("setSensorEnabledState:\t SensorMap=null");
        return false;
    }

    private void setSensorEnabledStateCommon(int i, boolean z) {
        sensorMapConflictCheckandCorrect(i);
        setDefaultConfigForSensor(i, z);
        checkIfInternalExpBrdPowerIsNeeded();
        refreshEnabledSensorsFromSensorMap();
        generateParserMap();
        algorithmRequiredSensorCheck();
    }

    protected void setSensorEnabledStateBasic(int i, boolean z) {
        SensorDetails sensorDetails = this.mSensorMap.get(Integer.valueOf(i));
        if (sensorDetails != null) {
            sensorDetails.setIsEnabled(z);
        }
    }

    public int handleSpecCasesBeforeSetSensorState(int i, boolean z) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            int iHandleSpecCasesBeforeSetSensorState = it2.next().handleSpecCasesBeforeSetSensorState(i, z);
            if (iHandleSpecCasesBeforeSetSensorState != i) {
                return iHandleSpecCasesBeforeSetSensorState;
            }
        }
        return i;
    }

    public boolean isTimestampEnabled() {
        SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP));
        if (sensorDetails != null) {
            return sensorDetails.isEnabled();
        }
        return false;
    }

    protected void sensorMapConflictCheckandCorrect(int i) {
        SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(i));
        if (sensorDetails != null && sensorDetails.mSensorDetailsRef.mListOfSensorIdsConflicting != null) {
            for (Integer num : sensorDetails.mSensorDetailsRef.mListOfSensorIdsConflicting) {
                SensorDetails sensorDetails2 = getSensorDetails(num);
                if (sensorDetails2 != null) {
                    sensorDetails2.setIsEnabled(false);
                    if (sensorDetails2.isDerivedChannel()) {
                        this.mDerivedSensors &= ~sensorDetails2.mDerivedSensorBitmapID;
                    }
                    setDefaultConfigForSensor(num.intValue(), sensorDetails2.isEnabled());
                }
            }
        }
        sensorMapCheckandCorrectSensorDependencies();
        sensorMapCheckandCorrectHwDependencies();
    }

    protected void sensorMapCheckandCorrectSensorDependencies() {
        for (Integer num : this.mSensorMap.keySet()) {
            SensorDetails sensorDetails = getSensorDetails(num);
            if (sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired != null) {
                Iterator<Integer> it2 = sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (!getSensorDetails(it2.next()).isEnabled()) {
                        sensorDetails.setIsEnabled(false);
                        if (sensorDetails.isDerivedChannel()) {
                            this.mDerivedSensors &= ~sensorDetails.mDerivedSensorBitmapID;
                        }
                        setDefaultConfigForSensor(num.intValue(), sensorDetails.isEnabled());
                    }
                }
            }
        }
    }

    protected void sensorMapCheckandCorrectHwDependencies() {
        for (Integer num : this.mSensorMap.keySet()) {
            SensorDetails sensorDetails = getSensorDetails(num);
            if (sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo != null && !isVerCompatibleWithAnyOf(sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                sensorDetails.setIsEnabled(false);
                if (sensorDetails.isDerivedChannel()) {
                    this.mDerivedSensors &= ~sensorDetails.mDerivedSensorBitmapID;
                }
                setDefaultConfigForSensor(num.intValue(), sensorDetails.isEnabled());
            }
        }
    }

    protected void setDefaultConfigForSensor(int i, boolean z) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext() && !it2.next().setDefaultConfigForSensor(i, z)) {
        }
    }

    public void setEnabledAndDerivedSensorsAndUpdateMaps(long j, long j2) {
        setEnabledAndDerivedSensorsAndUpdateMaps(j, j2, null);
    }

    public void setEnabledAndDerivedSensorsAndUpdateMaps(long j, long j2, Configuration.COMMUNICATION_TYPE communication_type) {
        setEnabledSensors(j);
        setDerivedSensors(j2);
        sensorMapUpdateFromEnabledSensorsVars(communication_type);
        algorithmMapUpdateFromEnabledSensorsVars();
        setSamplingRateSensors(getSamplingRateShimmer());
        setSamplingRateAlgorithms(getSamplingRateShimmer());
        generateParserMap();
    }

    protected void handleSpecialCasesAfterSensorMapCreate() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().handleSpecialCasesAfterSensorMapCreate();
        }
    }

    public void refreshEnabledSensorsFromSensorMap() {
        if (this.mSensorMap == null || isShimmerGen2()) {
            return;
        }
        sensorMapCheckandCorrectHwDependencies();
        Iterator<SensorDetails> it2 = getListOfEnabledSensors().iterator();
        long j = 0;
        while (it2.hasNext()) {
            j |= it2.next().mSensorDetailsRef.mSensorBitmapIDSDLogHeader;
        }
        setEnabledSensors(j);
        updateDerivedSensors();
        handleSpecCasesUpdateEnabledSensors();
    }

    public void printSensorParserAndAlgoMaps() {
        consolePrintLn("");
        consolePrintLn("Enabled Sensors\t" + UtilShimmer.longToHexStringWithSpacesFormatted(this.mEnabledSensors, 5));
        consolePrintLn("Derived Sensors\t" + UtilShimmer.longToHexStringWithSpacesFormatted(this.mDerivedSensors, 8));
        consolePrintLn("SENSOR MAP");
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            consolePrintLn("\tSENSOR\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel + "\tIsEnabled:\t" + sensorDetails.isEnabled());
            if (sensorDetails.mListOfChannels.size() == 0) {
                consolePrintLn("\t\tChannels Missing!");
            } else {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    consolePrintLn("\t\tChannel:\t Channel:" + channelDetails.getChannelObjectClusterName() + "\tDbName:" + channelDetails.getDatabaseChannelHandle());
                }
            }
        }
        consolePrintLn("");
        consolePrintLn("PARSER MAP\tSize=" + this.mParserMap.keySet().size());
        for (Configuration.COMMUNICATION_TYPE communication_type : this.mParserMap.keySet()) {
            consolePrintLn("PARSER MAP\tCOMM TYPE:\t" + communication_type);
            for (SensorDetails sensorDetails2 : this.mParserMap.get(communication_type).values()) {
                consolePrintLn("\tSENSOR\t" + sensorDetails2.mSensorDetailsRef.mGuiFriendlyLabel);
                if (sensorDetails2.mListOfChannels.size() == 0) {
                    consolePrintLn("\t\tChannels Missing!");
                } else {
                    for (ChannelDetails channelDetails2 : sensorDetails2.mListOfChannels) {
                        consolePrintLn("\t\tNumBytes:" + channelDetails2.mDefaultNumBytes + "\tChannel:" + channelDetails2.getChannelObjectClusterName() + "\tDbName:" + channelDetails2.getDatabaseChannelHandle());
                    }
                }
            }
            consolePrintLn("");
        }
        consolePrintLn("");
        consolePrintLn("ALGO MAP");
        for (AbstractAlgorithm abstractAlgorithm : getListOfAlgorithmModules()) {
            consolePrintLn("\tALGO\t" + abstractAlgorithm.getAlgorithmName() + "\tIsEnabled:\t" + abstractAlgorithm.isEnabled());
            for (ChannelDetails channelDetails3 : abstractAlgorithm.getChannelDetails()) {
                consolePrintLn("\t\tChannel:\t" + channelDetails3.getChannelObjectClusterName() + "\tDbName:" + channelDetails3.getDatabaseChannelHandle());
            }
        }
        consolePrintLn("");
        LinkedHashMap<Configuration.COMMUNICATION_TYPE, List<ChannelDetails>> linkedHashMapGenerateObjectClusterIndexes = generateObjectClusterIndexes();
        consolePrintLn("\tObjectClusterIndexes:");
        for (Configuration.COMMUNICATION_TYPE communication_type2 : linkedHashMapGenerateObjectClusterIndexes.keySet()) {
            consolePrintLn("\tComm Type: " + communication_type2);
            List<ChannelDetails> list = linkedHashMapGenerateObjectClusterIndexes.get(communication_type2);
            for (int i = 0; i < list.size(); i++) {
                consolePrintLn("\t\t" + i + "\t" + list.get(i).mObjectClusterName);
            }
        }
        consolePrintLn("");
    }

    public LinkedHashMap<Configuration.COMMUNICATION_TYPE, List<ChannelDetails>> generateObjectClusterIndexes() {
        LinkedHashMap<Configuration.COMMUNICATION_TYPE, List<ChannelDetails>> linkedHashMap = new LinkedHashMap<>();
        for (Configuration.COMMUNICATION_TYPE communication_type : this.mParserMap.keySet()) {
            ArrayList arrayList = new ArrayList();
            Iterator<SensorDetails> it2 = this.mParserMap.get(communication_type).values().iterator();
            while (it2.hasNext()) {
                Iterator<ChannelDetails> it3 = it2.next().mListOfChannels.iterator();
                while (it3.hasNext()) {
                    arrayList.add(it3.next());
                }
            }
            Iterator<AbstractAlgorithm> it4 = getListOfEnabledAlgorithmModules().iterator();
            while (it4.hasNext()) {
                Iterator<ChannelDetails> it5 = it4.next().getChannelDetails().iterator();
                while (it5.hasNext()) {
                    arrayList.add(it5.next());
                }
            }
            linkedHashMap.put(communication_type, arrayList);
        }
        return linkedHashMap;
    }

    public void handleSpecCasesUpdateEnabledSensors() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().handleSpecCasesUpdateEnabledSensors();
        }
    }

    public List<SensorDetails> getListOfEnabledSensors() {
        ArrayList arrayList = new ArrayList();
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            if (sensorDetails.isEnabled()) {
                arrayList.add(sensorDetails);
            }
        }
        return arrayList;
    }

    public void algorithmMapUpdateFromEnabledSensorsVars() {
        for (AbstractAlgorithm abstractAlgorithm : getMapOfAlgorithmModules().values()) {
            if (mEnableDerivedSensors) {
                abstractAlgorithm.algorithmMapUpdateFromEnabledSensorsVars(this.mDerivedSensors);
            } else {
                abstractAlgorithm.algorithmMapUpdateFromEnabledSensorsVars(0L);
            }
        }
        initializeAlgorithms();
    }

    public void sensorMapUpdateFromEnabledSensorsVars() {
        sensorMapUpdateFromEnabledSensorsVars(null);
    }

    public void sensorMapUpdateFromEnabledSensorsVars(Configuration.COMMUNICATION_TYPE communication_type) {
        handleSpecCasesBeforeSensorMapUpdateGeneral();
        if (this.mSensorMap == null) {
            sensorAndConfigMapsCreate();
        }
        LinkedHashMap<Integer, SensorDetails> linkedHashMap = this.mSensorMap;
        if (linkedHashMap != null) {
            for (Integer num : linkedHashMap.keySet()) {
                if (!handleSpecCasesBeforeSensorMapUpdatePerSensor(num)) {
                    getSensorDetails(num).updateFromEnabledSensorsVars(this.mEnabledSensors, this.mDerivedSensors);
                }
            }
            handleSpecCasesAfterSensorMapUpdateFromEnabledSensors();
        }
    }

    public void handleSpecCasesBeforeSensorMapUpdateGeneral() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().handleSpecCasesBeforeSensorMapUpdateGeneral(this);
        }
    }

    public boolean handleSpecCasesBeforeSensorMapUpdatePerSensor(Integer num) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            if (it2.next().handleSpecCasesBeforeSensorMapUpdatePerSensor(this, num)) {
                return true;
            }
        }
        return false;
    }

    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().handleSpecCasesAfterSensorMapUpdateFromEnabledSensors();
        }
    }

    public List<Integer> sensorMapConflictCheck(Integer num) {
        ArrayList arrayList = new ArrayList();
        if ((getHardwareVersion() == 3 || getHardwareVersion() == 58 || getHardwareVersion() == 10) && getSensorDetails(num).mSensorDetailsRef.mListOfSensorIdsConflicting != null) {
            for (Integer num2 : getSensorDetails(num).mSensorDetailsRef.mListOfSensorIdsConflicting) {
                if (isSensorEnabled(num2.intValue())) {
                    arrayList.add(num2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public void checkShimmerConfigBeforeConfiguring() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().checkShimmerConfigBeforeConfiguring();
        }
        checkIfInternalExpBrdPowerIsNeeded();
    }

    public byte[] refreshShimmerInfoMemBytes() {
        return configBytesGenerate(false);
    }

    public void checkAndCorrectShimmerName(String str) {
        if (!str.isEmpty()) {
            this.mShimmerUserAssignedName = new String(str);
            return;
        }
        this.mShimmerUserAssignedName = "Shimmer_" + getMacIdFromUartParsed();
    }

    public void checkConfigOptionValues(String str) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext() && !it2.next().checkConfigOptionValues(str)) {
        }
    }

    public boolean isSensorUsingDefaultCal(int i) {
        for (AbstractSensor abstractSensor : this.mMapOfSensorClasses.values()) {
            if (abstractSensor.getSensorDetails(i) != null) {
                return abstractSensor.isSensorUsingDefaultCal(i);
            }
        }
        return false;
    }

    public boolean setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state) {
        boolean z = !this.mBluetoothRadioState.toString().equals(bt_state.toString());
        ShimmerBluetooth.BT_STATE bt_state2 = this.mBluetoothRadioState;
        this.mBluetoothRadioState = bt_state;
        consolePrintLn("State change: Was:" + bt_state2.toString() + "\tIs now:" + this.mBluetoothRadioState);
        if (this.mUpdateOnlyWhenStateChanges) {
            return z;
        }
        return true;
    }

    public String getBluetoothRadioStateString() {
        return this.mBluetoothRadioState.toString();
    }

    public void disconnect() throws ShimmerException {
        stopStreaming();
    }

    public boolean ignoreAndDisable(Integer num) {
        SensorDetails sensorDetails = getSensorDetails(num);
        if (sensorDetails != null) {
            Iterator<Integer> it2 = sensorDetails.mSensorDetailsRef.mListOfSensorIdsConflicting.iterator();
            while (it2.hasNext()) {
                SensorDetails sensorDetails2 = getSensorDetails(it2.next());
                if (sensorDetails2 != null && sensorDetails2.isDerivedChannel() && (this.mDerivedSensors & sensorDetails2.mDerivedSensorBitmapID) > 0) {
                    sensorDetails.setIsEnabled(false);
                    return true;
                }
            }
        }
        return false;
    }

    public void updateDerivedSensors() {
        setDerivedSensors(0L);
        updateDerivedSensorsFromSensorMap();
        updateDerivedSensorsFromAlgorithmMap();
    }

    private void updateDerivedSensorsFromSensorMap() {
        for (SensorDetails sensorDetails : getListOfEnabledSensors()) {
            if (sensorDetails.isDerivedChannel()) {
                this.mDerivedSensors |= sensorDetails.mDerivedSensorBitmapID;
            }
        }
    }

    private void updateDerivedSensorsFromAlgorithmMap() {
        Iterator<AbstractAlgorithm> it2 = getListOfEnabledAlgorithmModules().iterator();
        while (it2.hasNext()) {
            this.mDerivedSensors |= it2.next().getDerivedSensorBitmapID();
        }
    }

    public List<AlgorithmDetails> getListOfEnabledAlgorithmDetails() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractAlgorithm> it2 = getListOfEnabledAlgorithmModules().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().mAlgorithmDetails);
        }
        return arrayList;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModules() {
        ArrayList arrayList = new ArrayList();
        for (AbstractAlgorithm abstractAlgorithm : this.mMapOfAlgorithmModules.values()) {
            if (abstractAlgorithm.isEnabled()) {
                arrayList.add(abstractAlgorithm);
            }
        }
        return arrayList;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModules() {
        return new ArrayList(this.mMapOfAlgorithmModules.values());
    }

    public void setIsAlgorithmEnabledAndSyncGroup(String str, String str2, boolean z) {
        AbstractAlgorithm abstractAlgorithm = getMapOfAlgorithmModules().get(str);
        if (abstractAlgorithm != null) {
            HashMap<String, Object> mapSyncAlgoGroupConfig = syncAlgoGroupConfig(str2, z);
            setIsAlgorithmEnabled(str, z);
            if (z && mapSyncAlgoGroupConfig != null) {
                abstractAlgorithm.setAlgorithmSettings(mapSyncAlgoGroupConfig);
            }
            List<AbstractAlgorithm> listOfEnabledAlgorithmModulesPerGroup = getListOfEnabledAlgorithmModulesPerGroup(str2);
            if (listOfEnabledAlgorithmModulesPerGroup.size() == 0 || (z && listOfEnabledAlgorithmModulesPerGroup.size() == 1)) {
                setDefaultSettingsForAlgorithmGroup(str2);
            }
            syncAlgoGroupConfig(str2, z);
        }
    }

    public void setIsAlgorithmEnabled(String str, boolean z) {
        AbstractAlgorithm abstractAlgorithm = this.mMapOfAlgorithmModules.get(str);
        if (abstractAlgorithm != null && abstractAlgorithm.mAlgorithmDetails != null) {
            if (z) {
                Iterator<Integer> it2 = abstractAlgorithm.mAlgorithmDetails.mListOfRequiredSensors.iterator();
                while (it2.hasNext()) {
                    setSensorEnabledState(it2.next().intValue(), true);
                }
            }
            abstractAlgorithm.setIsEnabled(z);
        } else {
            consolePrintErrLn(str + " doesn't exist in this device");
        }
        updateDerivedSensors();
        initializeAlgorithms();
    }

    public void disableAllAlgorithms() {
        Iterator<AbstractAlgorithm> it2 = getMapOfAlgorithmModules().values().iterator();
        while (it2.hasNext()) {
            setIsAlgorithmEnabled(it2.next().getAlgorithmName(), false);
        }
    }

    public boolean isECGAlgoEnabled(AbstractAlgorithm abstractAlgorithm) {
        return (getSensorDetails(100).isEnabled() || getSensorDetails(103).isEnabled()) && abstractAlgorithm.mAlgorithmName.contains("ECGtoHR");
    }

    public void algorithmRequiredSensorCheck() {
        for (SensorGroupingDetails sensorGroupingDetails : this.mMapOfAlgorithmGrouping.values()) {
            Iterator<AlgorithmDetails> it2 = sensorGroupingDetails.mListOfAlgorithmDetails.iterator();
            while (it2.hasNext()) {
                AbstractAlgorithm abstractAlgorithm = this.mMapOfAlgorithmModules.get(it2.next().mAlgorithmName);
                if (abstractAlgorithm != null && abstractAlgorithm.isEnabled()) {
                    Iterator<Integer> it3 = abstractAlgorithm.mAlgorithmDetails.mListOfRequiredSensors.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Integer next = it3.next();
                            if (!isECGAlgoEnabled(abstractAlgorithm)) {
                                SensorDetails sensorDetails = getSensorDetails(next);
                                if (sensorDetails != null && !sensorDetails.isEnabled()) {
                                    setIsAlgorithmEnabledAndSyncGroup(abstractAlgorithm.mAlgorithmName, sensorGroupingDetails.mGroupName, false);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        initializeAlgorithms();
    }

    protected List<String[]> getListofEnabledAlgorithmsSignalsandFormats() {
        ArrayList arrayList = new ArrayList();
        Iterator<AlgorithmDetails> it2 = getListOfEnabledAlgorithmDetails().iterator();
        while (it2.hasNext()) {
            String[] signalStringArray = it2.next().getSignalStringArray();
            signalStringArray[0] = this.mShimmerUserAssignedName;
            arrayList.add(signalStringArray);
        }
        return arrayList;
    }

    public Map<String, AbstractAlgorithm> getSupportedAlgorithmChannels() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AbstractAlgorithm abstractAlgorithm : this.mMapOfAlgorithmModules.values()) {
            Iterator<Integer> it2 = abstractAlgorithm.mAlgorithmDetails.mListOfRequiredSensors.iterator();
            while (it2.hasNext()) {
                if (this.mSensorMap.containsKey(it2.next())) {
                    linkedHashMap.put(abstractAlgorithm.getAlgorithmName(), abstractAlgorithm);
                }
            }
        }
        return linkedHashMap;
    }

    public TreeMap<Integer, SensorGroupingDetails> getMapOfAlgorithmGrouping() {
        TreeMap<Integer, SensorGroupingDetails> treeMap = new TreeMap<>();
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        while (it2.hasNext()) {
            treeMap.putAll(it2.next().mMapOfAlgorithmGrouping);
        }
        this.mMapOfAlgorithmGrouping = treeMap;
        return treeMap;
    }

    public TreeMap<Integer, SensorGroupingDetails> getMapOfAlgorithmGroupingEnabled() {
        TreeMap<Integer, SensorGroupingDetails> treeMap = new TreeMap<>();
        Iterator<AbstractAlgorithm> it2 = getListOfEnabledAlgorithmModules().iterator();
        while (it2.hasNext()) {
            treeMap.putAll(it2.next().mMapOfAlgorithmGrouping);
        }
        this.mMapOfAlgorithmGrouping = treeMap;
        return treeMap;
    }

    protected void generateMapOfAlgorithmConfigOptions() {
        this.mConfigOptionsMapAlgorithms = new HashMap();
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        while (it2.hasNext()) {
            HashMap<String, ConfigOptionDetails> configOptionsMap = it2.next().getConfigOptionsMap();
            if (configOptionsMap != null && configOptionsMap.keySet().size() > 0) {
                for (String str : configOptionsMap.keySet()) {
                    if (!this.mConfigOptionsMapAlgorithms.containsKey(str)) {
                        this.mConfigOptionsMapAlgorithms.put(str, configOptionsMap.get(str));
                    }
                }
            }
        }
    }

    protected void generateMapOfAlgorithmGroupingMap() {
        this.mMapOfAlgorithmGrouping = new TreeMap<>();
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        while (it2.hasNext()) {
            TreeMap<Integer, SensorGroupingDetails> treeMap = it2.next().mMapOfAlgorithmGrouping;
            if (treeMap != null && treeMap.keySet().size() > 0) {
                this.mMapOfAlgorithmGrouping.putAll(treeMap);
            }
        }
    }

    protected void generateMapOfAlgorithmModules() {
        this.mMapOfAlgorithmModules = new HashMap();
        loadAlgorithms(OPEN_SOURCE_ALGORITHMS, null);
        DataProcessingInterface dataProcessingInterface = this.mDataProcessing;
        if (dataProcessingInterface != null) {
            dataProcessingInterface.updateMapOfAlgorithmModules(this);
        }
    }

    public void loadAlgorithms(List<AlgorithmLoaderInterface> list, Configuration.COMMUNICATION_TYPE communication_type) {
        Iterator<AlgorithmLoaderInterface> it2 = list.iterator();
        while (it2.hasNext()) {
            it2.next().initialiseSupportedAlgorithms(this, communication_type);
        }
    }

    public AbstractAlgorithm getAlgorithmModule(String str) {
        return this.mMapOfAlgorithmModules.get(str);
    }

    public void addAlgorithmModule(AbstractAlgorithm abstractAlgorithm) {
        this.mMapOfAlgorithmModules.put(abstractAlgorithm.mAlgorithmName, abstractAlgorithm);
    }

    protected void initializeAlgorithms() {
        for (AbstractAlgorithm abstractAlgorithm : this.mMapOfAlgorithmModules.values()) {
            try {
                if (abstractAlgorithm.isEnabled()) {
                    abstractAlgorithm.setShimmerSamplingRate(getSamplingRateShimmer());
                    abstractAlgorithm.initialize();
                } else {
                    abstractAlgorithm.isEnabled();
                }
            } catch (Exception e) {
                consolePrintException("Error initialising algorithm module\t" + abstractAlgorithm.getAlgorithmName(), e.getStackTrace());
            }
        }
    }

    @Deprecated
    public boolean doesAlgorithmAlreadyExist(AbstractAlgorithm abstractAlgorithm) {
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        while (it2.hasNext()) {
            if (it2.next().getAlgorithmName().equals(abstractAlgorithm.getAlgorithmName())) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void removeAlgorithm(AbstractAlgorithm abstractAlgorithm) {
        this.mMapOfAlgorithmModules.remove(abstractAlgorithm);
    }

    @Deprecated
    public void removeAlgorithm(String str) {
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        int i = 0;
        int i2 = -1;
        while (it2.hasNext()) {
            if (it2.next().getAlgorithmName().equals(str)) {
                i2 = i;
            }
            i++;
        }
        if (i2 >= 0) {
            this.mMapOfAlgorithmModules.remove(Integer.valueOf(i2));
        }
    }

    public void loadAlgorithmVariablesFromAnotherDevice(ShimmerDevice shimmerDevice) {
        for (Map.Entry<String, AbstractAlgorithm> entry : shimmerDevice.getMapOfAlgorithmModules().entrySet()) {
            AbstractAlgorithm algorithmModule = getAlgorithmModule(entry.getKey());
            if (algorithmModule != null) {
                algorithmModule.loadAlgorithmVariables(entry.getValue());
            }
        }
    }

    public HashMap<String, Object> syncAlgoGroupConfig(String str, boolean z) {
        List<AbstractAlgorithm> listOfEnabledAlgorithmModulesPerGroup = getListOfEnabledAlgorithmModulesPerGroup(str);
        if (listOfEnabledAlgorithmModulesPerGroup != null && listOfEnabledAlgorithmModulesPerGroup.size() > 0) {
            HashMap<String, Object> algorithmSettings = listOfEnabledAlgorithmModulesPerGroup.get(0).getAlgorithmSettings();
            Iterator<AbstractAlgorithm> it2 = getListOfAlgorithmModulesPerGroup(str).iterator();
            while (it2.hasNext()) {
                it2.next().setAlgorithmSettings(algorithmSettings);
            }
            return algorithmSettings;
        }
        getListOfAlgorithmModulesPerGroup(str);
        return null;
    }

    public void setShimmerAndSensorsSamplingRate(double d) {
        setShimmerAndSensorsSamplingRate(null, d);
    }

    public void setShimmerAndSensorsSamplingRate(Configuration.COMMUNICATION_TYPE communication_type, double d) {
        double dCorrectSamplingRate = correctSamplingRate(d);
        if (communication_type == null) {
            setSamplingRateShimmer(dCorrectSamplingRate);
        } else {
            setSamplingRateShimmer(communication_type, dCorrectSamplingRate);
        }
        setSamplingRateSensors(dCorrectSamplingRate);
        setSamplingRateAlgorithms(dCorrectSamplingRate);
    }

    public void setSamplingRateShimmer(Configuration.COMMUNICATION_TYPE communication_type, double d) {
        if (this.mListOfAvailableCommunicationTypes.contains(communication_type)) {
            this.mMapOfSamplingRatesShimmer.put(communication_type, Double.valueOf(d));
        }
    }

    public double getSamplingRateShimmer() {
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mMapOfSamplingRatesShimmer.keySet().iterator();
        double dMax = 0.0d;
        while (it2.hasNext()) {
            dMax = Math.max(dMax, getSamplingRateShimmer(it2.next()));
        }
        return dMax;
    }

    public void setSamplingRateShimmer(double d) {
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mMapOfSamplingRatesShimmer.keySet().iterator();
        while (it2.hasNext()) {
            setSamplingRateShimmer(it2.next(), d);
        }
    }

    public double getSamplingRateShimmer(Configuration.COMMUNICATION_TYPE communication_type) {
        HashMap<Configuration.COMMUNICATION_TYPE, Double> map;
        if (communication_type != null && (map = this.mMapOfSamplingRatesShimmer) != null && map.containsKey(communication_type)) {
            double dDoubleValue = this.mMapOfSamplingRatesShimmer.get(communication_type).doubleValue();
            if (!Double.isNaN(dDoubleValue)) {
                return dDoubleValue;
            }
        }
        return getSamplingRateShimmer();
    }

    public byte[] getSamplingRateBytesShimmer() {
        return convertSamplingRateFreqToBytes(getSamplingRateShimmer(), getSamplingClockFreq());
    }

    protected double correctSamplingRate(double d) {
        Double dValueOf;
        double dCalcMaxSamplingRate = calcMaxSamplingRate();
        double samplingClockFreq = getSamplingClockFreq();
        if (d < 1.0d) {
            d = 1.0d;
        } else if (d > dCalcMaxSamplingRate) {
            d = dCalcMaxSamplingRate;
        }
        double d2 = samplingClockFreq / d;
        if (Math.ceil(d2) - d2 < 0.05d) {
            dValueOf = Double.valueOf(samplingClockFreq / Math.ceil(d2));
        } else {
            dValueOf = Double.valueOf(samplingClockFreq / Math.floor(d2));
        }
        return Double.valueOf(Math.round(dValueOf.doubleValue() * 100.0d) / 100.0d).doubleValue();
    }

    protected double calcMaxSamplingRate() {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        double dMin = 2048.0d;
        while (it2.hasNext()) {
            dMin = Math.min(dMin, it2.next().calcMaxSamplingRate());
        }
        return dMin;
    }

    protected void setSamplingRateSensors(double d) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().setSamplingRateFromShimmer(d);
        }
    }

    private void setSamplingRateAlgorithms(double d) {
        Iterator<AbstractAlgorithm> it2 = this.mMapOfAlgorithmModules.values().iterator();
        while (it2.hasNext()) {
            it2.next().setSettings("Sampling Rate", Double.valueOf(d));
        }
    }

    public boolean isAlgorithmEnabled(String str) {
        AbstractAlgorithm abstractAlgorithm = this.mMapOfAlgorithmModules.get(str);
        return abstractAlgorithm != null && abstractAlgorithm.isEnabled();
    }

    public boolean isAnyAlgortihmChannelEnabled(List<AlgorithmDetails> list) {
        Iterator<AlgorithmDetails> it2 = list.iterator();
        while (it2.hasNext()) {
            if (isAlgorithmEnabled(it2.next().mAlgorithmName)) {
                return true;
            }
        }
        return false;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModulesPerGroup(String str) {
        for (SensorGroupingDetails sensorGroupingDetails : this.mMapOfAlgorithmGrouping.values()) {
            if (sensorGroupingDetails.mGroupName.equals(str)) {
                return getListOfEnabledAlgorithmModulesPerGroup(sensorGroupingDetails);
            }
        }
        return null;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModulesPerGroup(SensorGroupingDetails sensorGroupingDetails) {
        ArrayList arrayList = new ArrayList();
        if (sensorGroupingDetails != null) {
            Map<String, AbstractAlgorithm> supportedAlgorithmChannels = getSupportedAlgorithmChannels();
            Iterator<AlgorithmDetails> it2 = sensorGroupingDetails.mListOfAlgorithmDetails.iterator();
            while (it2.hasNext()) {
                AbstractAlgorithm abstractAlgorithm = supportedAlgorithmChannels.get(it2.next().mAlgorithmName);
                if (abstractAlgorithm != null && abstractAlgorithm.isEnabled()) {
                    arrayList.add(abstractAlgorithm);
                }
            }
        }
        return arrayList;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(String str) {
        for (SensorGroupingDetails sensorGroupingDetails : this.mMapOfAlgorithmGrouping.values()) {
            if (sensorGroupingDetails.mGroupName.equals(str)) {
                return getListOfAlgorithmModulesPerGroup(sensorGroupingDetails);
            }
        }
        return null;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            for (SensorGroupingDetails sensorGroupingDetails : this.mMapOfAlgorithmGrouping.values()) {
                if (sensorGroupingDetails.mGroupName.equals(str)) {
                    arrayList.addAll(getListOfAlgorithmModulesPerGroup(sensorGroupingDetails));
                }
            }
        }
        return arrayList;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(SensorGroupingDetails sensorGroupingDetails) {
        ArrayList arrayList = new ArrayList();
        if (sensorGroupingDetails != null) {
            Map<String, AbstractAlgorithm> supportedAlgorithmChannels = getSupportedAlgorithmChannels();
            Iterator<AlgorithmDetails> it2 = sensorGroupingDetails.mListOfAlgorithmDetails.iterator();
            while (it2.hasNext()) {
                AbstractAlgorithm abstractAlgorithm = supportedAlgorithmChannels.get(it2.next().mAlgorithmName);
                if (abstractAlgorithm != null) {
                    arrayList.add(abstractAlgorithm);
                }
            }
        }
        return arrayList;
    }

    public void resetAlgorithmBuffers() {
        Iterator<AbstractAlgorithm> it2 = getListOfEnabledAlgorithmModules().iterator();
        while (it2.hasNext()) {
            try {
                it2.next().resetAlgorithmBuffers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledSensorChannelsForStoringToDB(Configuration.COMMUNICATION_TYPE communication_type, ChannelDetails.CHANNEL_TYPE channel_type, boolean z) {
        boolean zIsEnabled;
        LinkedHashMap<String, ChannelDetails> linkedHashMap = new LinkedHashMap<>();
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            if (communication_type == null) {
                zIsEnabled = sensorDetails.isEnabled();
            } else {
                zIsEnabled = sensorDetails.isEnabled(communication_type);
            }
            if (zIsEnabled && !sensorDetails.mSensorDetailsRef.mIsDummySensor) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    if (channel_type == null || channelDetails.mListOfChannelTypes.contains(channel_type)) {
                        if (channelDetails.isStoreToDatabase()) {
                            linkedHashMap.put(z ? channelDetails.mObjectClusterName : channelDetails.getDatabaseChannelHandle(), channelDetails);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledAlgortihmChannelsToStoreInDB(Configuration.COMMUNICATION_TYPE communication_type, ChannelDetails.CHANNEL_TYPE channel_type, boolean z) {
        List<ChannelDetails> channelDetails;
        LinkedHashMap<String, ChannelDetails> linkedHashMap = new LinkedHashMap<>();
        for (AbstractAlgorithm abstractAlgorithm : getListOfEnabledAlgorithmModules()) {
            if (abstractAlgorithm.mListOfCommunicationTypesSupported.contains(communication_type) && (channelDetails = abstractAlgorithm.getChannelDetails()) != null) {
                for (ChannelDetails channelDetails2 : channelDetails) {
                    if (channel_type == null || channelDetails2.mListOfChannelTypes.contains(channel_type)) {
                        if (channelDetails2.mStoreToDatabase) {
                            linkedHashMap.put(z ? channelDetails2.mObjectClusterName : channelDetails2.getDatabaseChannelHandle(), channelDetails2);
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfAllChannelsForStoringToDB(Configuration.COMMUNICATION_TYPE communication_type, ChannelDetails.CHANNEL_TYPE channel_type, boolean z, boolean z2) {
        LinkedHashMap<String, ChannelDetails> mapOfEnabledSensorChannelsForStoringToDB = getMapOfEnabledSensorChannelsForStoringToDB(communication_type, channel_type, z);
        mapOfEnabledSensorChannelsForStoringToDB.putAll(getMapOfEnabledAlgortihmChannelsToStoreInDB(communication_type, channel_type, z));
        return filterOutUnwantedChannels(mapOfEnabledSensorChannelsForStoringToDB, communication_type, z);
    }

    private LinkedHashMap<String, ChannelDetails> filterOutUnwantedChannels(LinkedHashMap<String, ChannelDetails> linkedHashMap, Configuration.COMMUNICATION_TYPE communication_type, boolean z) {
        String str;
        if (communication_type == Configuration.COMMUNICATION_TYPE.SD) {
            str = "System_Timestamp";
        } else {
            str = communication_type == Configuration.COMMUNICATION_TYPE.BLUETOOTH ? z ? SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK : SensorShimmerClock.DatabaseChannelHandles.REAL_TIME_CLOCK : "";
        }
        if (!str.isEmpty()) {
            linkedHashMap.remove(str);
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfChannelsDetailsFromDbHandles(List<String> list) {
        LinkedHashMap<String, ChannelDetails> mapOfAllChannelsForStoringToDB = getMapOfAllChannelsForStoringToDB(null, ChannelDetails.CHANNEL_TYPE.CAL, false, true);
        Iterator<SensorDetails> it2 = this.mSensorMap.values().iterator();
        while (it2.hasNext()) {
            for (ChannelDetails channelDetails : it2.next().getListOfChannels()) {
                if (!mapOfAllChannelsForStoringToDB.containsKey(channelDetails.getDatabaseChannelHandle())) {
                    mapOfAllChannelsForStoringToDB.put(channelDetails.getDatabaseChannelHandle(), channelDetails);
                }
            }
        }
        Iterator<AbstractAlgorithm> it3 = this.mMapOfAlgorithmModules.values().iterator();
        while (it3.hasNext()) {
            for (ChannelDetails channelDetails2 : it3.next().getChannelDetails()) {
                if (!mapOfAllChannelsForStoringToDB.containsKey(channelDetails2.getDatabaseChannelHandle())) {
                    mapOfAllChannelsForStoringToDB.put(channelDetails2.getDatabaseChannelHandle(), channelDetails2);
                }
            }
        }
        LinkedHashMap<String, ChannelDetails> linkedHashMap = new LinkedHashMap<>();
        for (String str : list) {
            ChannelDetails channelDetails3 = mapOfAllChannelsForStoringToDB.get(str);
            if (channelDetails3 == null) {
                channelDetails3 = new ChannelDetails();
                channelDetails3.mGuiName = str;
                channelDetails3.mObjectClusterName = str;
                channelDetails3.setDatabaseChannelHandle(str.replace("-", "_"));
            }
            if (channelDetails3 != null && !channelDetails3.mObjectClusterName.contains("_DUMMY_")) {
                linkedHashMap.put(channelDetails3.getDatabaseChannelHandle(), channelDetails3);
            }
        }
        return linkedHashMap;
    }

    public LinkedHashMap<String, Object> generateConfigMap(Configuration.COMMUNICATION_TYPE communication_type) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(DatabaseConfigHandle.SAMPLE_RATE, Double.valueOf(communication_type == null ? getSamplingRateShimmer() : getSamplingRateShimmer(communication_type)));
        linkedHashMap.put(DatabaseConfigHandle.ENABLE_SENSORS, Long.valueOf(getEnabledSensors()));
        linkedHashMap.put(DatabaseConfigHandle.DERIVED_SENSORS, Long.valueOf(getDerivedSensors()));
        linkedHashMap.put(DatabaseConfigHandle.SHIMMER_VERSION, Integer.valueOf(getHardwareVersion()));
        linkedHashMap.put(DatabaseConfigHandle.FW_VERSION, Integer.valueOf(getFirmwareIdentifier()));
        linkedHashMap.put(DatabaseConfigHandle.FW_VERSION_MAJOR, Integer.valueOf(getFirmwareVersionMajor()));
        linkedHashMap.put(DatabaseConfigHandle.FW_VERSION_MINOR, Integer.valueOf(getFirmwareVersionMinor()));
        linkedHashMap.put(DatabaseConfigHandle.FW_VERSION_INTERNAL, Integer.valueOf(getFirmwareVersionInternal()));
        linkedHashMap.put(DatabaseConfigHandle.EXP_BOARD_ID, Integer.valueOf(getExpansionBoardId()));
        linkedHashMap.put(DatabaseConfigHandle.EXP_BOARD_REV, Integer.valueOf(getExpansionBoardRev()));
        linkedHashMap.put(DatabaseConfigHandle.EXP_BOARD_REV_SPEC, Integer.valueOf(getExpansionBoardRevSpecial()));
        linkedHashMap.put(DatabaseConfigHandle.EXP_PWR, Integer.valueOf(getInternalExpPower()));
        linkedHashMap.put(DatabaseConfigHandle.CONFIG_TIME, Long.valueOf(getConfigTime()));
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            LinkedHashMap<String, Object> linkedHashMapGenerateConfigMap = it2.next().generateConfigMap();
            if (linkedHashMapGenerateConfigMap != null) {
                linkedHashMap.putAll(linkedHashMapGenerateConfigMap);
            }
        }
        Iterator<AbstractAlgorithm> it3 = this.mMapOfAlgorithmModules.values().iterator();
        while (it3.hasNext()) {
            LinkedHashMap<String, Object> linkedHashMapGenerateConfigMap2 = it3.next().generateConfigMap();
            if (linkedHashMapGenerateConfigMap2 != null) {
                linkedHashMap.putAll(linkedHashMapGenerateConfigMap2);
            }
        }
        return linkedHashMap;
    }

    public void parseConfigMap(ShimmerVerObject shimmerVerObject, LinkedHashMap<String, Object> linkedHashMap, Configuration.COMMUNICATION_TYPE communication_type) {
        if (shimmerVerObject != null) {
            setShimmerVersionObject(shimmerVerObject);
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.EXP_BOARD_ID) && linkedHashMap.containsKey(DatabaseConfigHandle.EXP_BOARD_REV) && linkedHashMap.containsKey(DatabaseConfigHandle.EXP_BOARD_REV_SPEC)) {
            setExpansionBoardDetails(new ExpansionBoardDetails(((Double) linkedHashMap.get(DatabaseConfigHandle.EXP_BOARD_ID)).intValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.EXP_BOARD_REV)).intValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.EXP_BOARD_REV_SPEC)).intValue()));
        }
        sensorAndConfigMapsCreate();
        if (linkedHashMap.containsKey(DatabaseConfigHandle.ENABLE_SENSORS) && linkedHashMap.containsKey(DatabaseConfigHandle.DERIVED_SENSORS)) {
            setEnabledAndDerivedSensorsAndUpdateMaps(((Double) linkedHashMap.get(DatabaseConfigHandle.ENABLE_SENSORS)).longValue(), ((Double) linkedHashMap.get(DatabaseConfigHandle.DERIVED_SENSORS)).longValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.SAMPLE_RATE)) {
            double dDoubleValue = ((Double) linkedHashMap.get(DatabaseConfigHandle.SAMPLE_RATE)).doubleValue();
            if (communication_type == null) {
                setSamplingRateShimmer(dDoubleValue);
            } else {
                setSamplingRateShimmer(communication_type, dDoubleValue);
            }
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.EXP_PWR)) {
            setInternalExpPower(((Double) linkedHashMap.get(DatabaseConfigHandle.EXP_PWR)).intValue());
        }
        if (linkedHashMap.containsKey(DatabaseConfigHandle.CONFIG_TIME)) {
            setConfigTime(((Double) linkedHashMap.get(DatabaseConfigHandle.CONFIG_TIME)).longValue());
        }
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().parseConfigMap(linkedHashMap);
        }
        Iterator<AbstractAlgorithm> it3 = this.mMapOfAlgorithmModules.values().iterator();
        while (it3.hasNext()) {
            it3.next().parseConfigMapFromDb(linkedHashMap);
        }
    }

    public void printMapOfConfig() {
        Iterator<Configuration.COMMUNICATION_TYPE> it2 = this.mListOfAvailableCommunicationTypes.iterator();
        while (it2.hasNext()) {
            printMapOfConfig(generateConfigMap(it2.next()));
        }
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledChannelsForStreaming() {
        return getMapOfEnabledChannelsForStreaming(null);
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledChannelsForStreaming(Configuration.COMMUNICATION_TYPE communication_type) {
        boolean zIsEnabled;
        LinkedHashMap<String, ChannelDetails> linkedHashMap = new LinkedHashMap<>();
        for (SensorDetails sensorDetails : this.mSensorMap.values()) {
            if (communication_type == null) {
                zIsEnabled = sensorDetails.isEnabled();
            } else {
                zIsEnabled = sensorDetails.isEnabled(communication_type);
            }
            if (zIsEnabled) {
                for (ChannelDetails channelDetails : sensorDetails.getListOfChannels()) {
                    if (channelDetails.isShowWhileStreaming()) {
                        linkedHashMap.put(channelDetails.mObjectClusterName, channelDetails);
                    }
                }
            }
        }
        for (AbstractAlgorithm abstractAlgorithm : getMapOfAlgorithmModules().values()) {
            if (abstractAlgorithm.isEnabled()) {
                for (ChannelDetails channelDetails2 : abstractAlgorithm.getChannelDetails()) {
                    if (channelDetails2.isShowWhileStreaming()) {
                        linkedHashMap.put(channelDetails2.mObjectClusterName, channelDetails2);
                    }
                }
            }
        }
        if (linkedHashMap.size() == 0) {
            consolePrintLn(getMacIdFromUartParsed() + "\tNO SENSORS ENABLED");
        }
        return linkedHashMap;
    }

    public String[] getListofEnabledChannelSignals() {
        ArrayList arrayList = new ArrayList(getMapOfEnabledChannelsForStreaming().keySet());
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public List<String[]> getListofEnabledChannelSignalsandFormats() {
        ArrayList arrayList = new ArrayList();
        Iterator<ChannelDetails> it2 = getMapOfEnabledChannelsForStreaming().values().iterator();
        while (it2.hasNext()) {
            arrayList.addAll(it2.next().getListOfChannelSignalsAndFormats());
        }
        return arrayList;
    }

    public double getMinAllowedSamplingRate() {
        return getMinAllowedEnabledAlgorithmSamplingRate();
    }

    public double getMinAllowedEnabledAlgorithmSamplingRate() {
        Iterator<AbstractAlgorithm> it2 = getListOfEnabledAlgorithmModules().iterator();
        double dMax = 0.0d;
        while (it2.hasNext()) {
            dMax = Math.max(it2.next().getMinSamplingRateForAlgorithm(), dMax);
        }
        return dMax;
    }

    public void setDefaultSettingsForAlgorithmGroup(String str) {
        Iterator<AbstractAlgorithm> it2 = getListOfAlgorithmModulesPerGroup(str).iterator();
        while (it2.hasNext()) {
            it2.next().setDefaultSetting();
        }
    }

    public SensorDetails getSensorDetails(Integer num) {
        LinkedHashMap<Integer, SensorDetails> linkedHashMap = this.mSensorMap;
        if (linkedHashMap == null || !linkedHashMap.containsKey(num)) {
            return null;
        }
        return this.mSensorMap.get(num);
    }

    public void addSensorClass(AbstractSensor abstractSensor) {
        addSensorClass(abstractSensor.mSensorType, abstractSensor);
    }

    public void addSensorClass(AbstractSensor.SENSORS sensors, AbstractSensor abstractSensor) {
        this.mMapOfSensorClasses.put(sensors, abstractSensor);
    }

    public AbstractSensor getSensorClass(AbstractSensor.SENSORS sensors) {
        LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> linkedHashMap = this.mMapOfSensorClasses;
        if (linkedHashMap == null || !linkedHashMap.containsKey(sensors)) {
            return null;
        }
        return this.mMapOfSensorClasses.get(sensors);
    }

    @Deprecated
    public AbstractSensor getSensorClass(long j) {
        LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> linkedHashMap = this.mMapOfSensorClasses;
        if (linkedHashMap == null) {
            return null;
        }
        for (AbstractSensor.SENSORS sensors : linkedHashMap.keySet()) {
            if (sensors.ordinal() == j) {
                return getSensorClass(sensors);
            }
        }
        return null;
    }

    public void threadSleep(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            consolePrintLn("threadSleep ERROR");
            e.printStackTrace();
        }
    }

    public boolean isCalibrationValid() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfSensorCalibrationAll = getMapOfSensorCalibrationAll();
        Boolean bool = true;
        if (mapOfSensorCalibrationAll != null) {
            Integer num = null;
            for (Integer num2 : mapOfSensorCalibrationAll.keySet()) {
                TreeMap<Integer, CalibDetails> treeMap = getMapOfSensorCalibrationAll().get(num2);
                Object configValueUsingConfigLabel = getConfigValueUsingConfigLabel(num2, "Range");
                if (configValueUsingConfigLabel != null) {
                    if (configValueUsingConfigLabel instanceof Integer) {
                        num = (Integer) configValueUsingConfigLabel;
                    }
                    CalibDetails calibDetails = treeMap.get(num);
                    if (calibDetails != null && (calibDetails instanceof CalibDetailsKinematic)) {
                        CalibDetailsKinematic calibDetailsKinematic = (CalibDetailsKinematic) calibDetails;
                        if (calibDetailsKinematic.isCurrentValuesSet() && !calibDetailsKinematic.isAllCalibrationValid()) {
                            bool = false;
                        }
                    }
                }
            }
        }
        return bool.booleanValue();
    }

    public TreeMap<Integer, TreeMap<Integer, CalibDetailsKinematic>> getMapOfSensorCalibrationAllKinematic() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfSensorCalibrationAll = getMapOfSensorCalibrationAll();
        TreeMap<Integer, TreeMap<Integer, CalibDetailsKinematic>> treeMap = new TreeMap<>();
        for (Integer num : mapOfSensorCalibrationAll.keySet()) {
            TreeMap<Integer, CalibDetailsKinematic> treeMap2 = new TreeMap<>();
            TreeMap<Integer, CalibDetails> treeMap3 = mapOfSensorCalibrationAll.get(num);
            for (Integer num2 : treeMap3.keySet()) {
                CalibDetails calibDetails = treeMap3.get(num2);
                if (calibDetails instanceof CalibDetailsKinematic) {
                    treeMap2.put(num2, (CalibDetailsKinematic) calibDetails);
                }
            }
            if (!treeMap2.isEmpty()) {
                treeMap.put(num, treeMap2);
            }
        }
        return treeMap;
    }

    public TreeMap<Integer, TreeMap<Integer, CalibDetails>> getMapOfSensorCalibrationAll() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> treeMap = new TreeMap<>();
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            Object configValueUsingConfigLabel = it2.next().getConfigValueUsingConfigLabel("Calibration all");
            if (configValueUsingConfigLabel != null) {
                treeMap.putAll((TreeMap) configValueUsingConfigLabel);
            }
        }
        return treeMap;
    }

    public void setMapOfSensorCalibrationAll(TreeMap<Integer, TreeMap<Integer, CalibDetails>> treeMap) {
        for (Integer num : treeMap.keySet()) {
            AbstractSensor sensorClass = getSensorClass(num.intValue());
            if (sensorClass != null) {
                sensorClass.setConfigValueUsingConfigLabel("Calibration", treeMap.get(num));
            }
        }
    }

    public void resetAllCalibParametersToDefault() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfSensorCalibrationAll = getMapOfSensorCalibrationAll();
        if (mapOfSensorCalibrationAll != null) {
            for (TreeMap<Integer, CalibDetails> treeMap : mapOfSensorCalibrationAll.values()) {
                if (treeMap != null) {
                    for (CalibDetails calibDetails : treeMap.values()) {
                        if (calibDetails != null) {
                            calibDetails.resetToDefaultParameters();
                        }
                    }
                }
            }
        }
    }

    protected void setSensorCalibrationPerSensor(Integer num, TreeMap<Integer, CalibDetails> treeMap) {
        AbstractSensor sensorClass = getSensorClass(num.intValue());
        if (sensorClass != null) {
            sensorClass.setCalibrationMapPerSensor(num.intValue(), treeMap);
        }
    }

    public TreeMap<Integer, CalibDetails> getMapOfSensorCalibrationPerSensor(Integer num) {
        AbstractSensor sensorClass = getSensorClass(num.intValue());
        if (sensorClass != null) {
            return sensorClass.getCalibrationMapForSensor(num.intValue());
        }
        return null;
    }

    public TreeMap<Integer, CalibDetails> getMapOfSensorCalibrationPerSensor(AbstractSensor.SENSORS sensors, int i) {
        AbstractSensor sensorClass = getSensorClass(sensors);
        if (sensorClass != null) {
            return sensorClass.getCalibrationMapForSensor(i);
        }
        return null;
    }

    public byte[] calibByteDumpGenerate() {
        byte[] bArrAddAll = new byte[0];
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfSensorCalibrationAll = getMapOfSensorCalibrationAll();
        for (Integer num : mapOfSensorCalibrationAll.keySet()) {
            Iterator<CalibDetails> it2 = mapOfSensorCalibrationAll.get(num).values().iterator();
            while (it2.hasNext()) {
                byte[] bArrGenerateCalibDump = it2.next().generateCalibDump();
                if (bArrGenerateCalibDump != null) {
                    bArrAddAll = ArrayUtils.addAll(bArrAddAll, ArrayUtils.addAll(new byte[]{(byte) (num.intValue() & 255), (byte) ((num.intValue() >> 8) & 255)}, bArrGenerateCalibDump));
                }
            }
        }
        byte[] bArrAddAll2 = ArrayUtils.addAll(this.mShimmerVerObject.generateVersionByteArrayNew(), bArrAddAll);
        return ArrayUtils.addAll(new byte[]{(byte) (bArrAddAll2.length & 255), (byte) ((bArrAddAll2.length >> 8) & 255)}, bArrAddAll2);
    }

    public void calibByteDumpParse(byte[] bArr, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        this.mCalibBytesDescriptions = new HashMap<>();
        this.mCalibBytes = bArr;
        if (UtilShimmer.isAllZeros(bArr)) {
            return;
        }
        int i = 2;
        if (bArr.length > 2) {
            this.mCalibBytesDescriptions.put(0, "PacketLength_LSB");
            char c = 1;
            this.mCalibBytesDescriptions.put(1, "PacketLength_MSB");
            Arrays.copyOfRange(bArr, 0, 2);
            int i2 = 10;
            ShimmerVerObject shimmerVerObject = new ShimmerVerObject(Arrays.copyOfRange(bArr, 2, 10));
            this.mCalibBytesDescriptions.put(2, "HwID_LSB (" + shimmerVerObject.getHardwareVersionParsed() + ")");
            this.mCalibBytesDescriptions.put(3, "HwID_MSB");
            this.mCalibBytesDescriptions.put(4, "FwID_LSB (" + shimmerVerObject.mFirmwareIdentifierParsed + ")");
            this.mCalibBytesDescriptions.put(5, "FwID_MSB");
            this.mCalibBytesDescriptions.put(6, "FWVerMjr_LSB");
            this.mCalibBytesDescriptions.put(7, "FWVerMjr_MSB");
            this.mCalibBytesDescriptions.put(8, "FWVerMinor");
            this.mCalibBytesDescriptions.put(9, "FWVerInternal");
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 10, bArr.length);
            while (bArrCopyOfRange.length > 12) {
                byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCopyOfRange, 0, i);
                int i3 = 65535 & (bArrCopyOfRange2[0] | (bArrCopyOfRange2[c] << 8));
                SensorDetails sensorDetails = getSensorDetails(Integer.valueOf(i3));
                String str = (sensorDetails == null || sensorDetails.mSensorDetailsRef == null) ? "" : sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel;
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2), "SensorID_LSB (" + str + ")");
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 1), "SensorID_MSB");
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 2), "SensorRange");
                int i4 = Arrays.copyOfRange(bArrCopyOfRange, i, 3)[0] & 255;
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 3), "CalibByteLength");
                int i5 = bArrCopyOfRange[3] & 255;
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 4), "CalibTimeTicks_LSB");
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 11), "CalibTimeTicks_MSB");
                byte[] bArrCopyOfRange3 = Arrays.copyOfRange(bArrCopyOfRange, 4, 12);
                int i6 = i5 + 12;
                if (bArrCopyOfRange.length < i6) {
                    return;
                }
                this.mCalibBytesDescriptions.put(Integer.valueOf(i2 + 12), "Calib_Start");
                int i7 = i2 + i6;
                this.mCalibBytesDescriptions.put(Integer.valueOf(i7 - 1), "Calib_End");
                calibByteDumpParsePerSensor(i3, i4, bArrCopyOfRange3, Arrays.copyOfRange(bArrCopyOfRange, 12, i6), calib_read_source);
                bArrCopyOfRange = Arrays.copyOfRange(bArrCopyOfRange, i6, bArrCopyOfRange.length);
                i2 = i7;
                i = 2;
                c = 1;
            }
        }
    }

    protected void calibByteDumpParsePerSensor(int i, int i2, byte[] bArr, byte[] bArr2, CalibDetails.CALIB_READ_SOURCE calib_read_source) {
        CalibDetails calibDetails;
        TreeMap<Integer, CalibDetails> treeMap = getMapOfSensorCalibrationAll().get(Integer.valueOf(i));
        if (treeMap == null || (calibDetails = treeMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        calibDetails.parseCalibDump(bArr, bArr2, calib_read_source);
    }

    public void clearCommsProtocolRadio() throws ShimmerException {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.disconnect();
        }
        this.mCommsProtocolRadio = null;
    }

    public void operationPrepare() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.operationPrepare();
        }
    }

    public void operationStart(ShimmerBluetooth.BT_STATE bt_state) {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.operationStart(bt_state);
        }
    }

    public void startStreaming() throws ShimmerException {
        resetPacketLossVariables();
        generateParserMap();
        initializeAlgorithms();
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.startStreaming();
        }
    }

    public void stopStreaming() throws ShimmerException {
        resetPacketLossVariables();
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.stopStreaming();
        }
    }

    public void startSDLogging() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.startSDLogging();
        }
    }

    public void stopSDLogging() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.stopSDLogging();
        }
    }

    public void startDataLogAndStreaming() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.startDataLogAndStreaming();
        }
    }

    public void stopStreamingAndLogging() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.stopLoggingAndStreaming();
        }
    }

    public void updateExpectedDataPacketSize() {
        int expectedDataPacketSize = getExpectedDataPacketSize(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null) {
            commsProtocolRadio.setPacketSize(expectedDataPacketSize);
        }
    }

    public String getComPort() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        if (commsProtocolRadio != null && commsProtocolRadio.mRadioHal != null) {
            setComPort(((AbstractSerialPortHal) this.mCommsProtocolRadio.mRadioHal).getConnectionHandle());
        }
        return this.mComPort;
    }

    public void setComPort(String str) {
        this.mComPort = str;
        updateThreadName();
    }

    public boolean isReadyToConnect() {
        CommsProtocolRadio commsProtocolRadio = this.mCommsProtocolRadio;
        return commsProtocolRadio == null || commsProtocolRadio.mRadioHal == null || !this.mCommsProtocolRadio.mRadioHal.isConnected();
    }

    public String getBtConnectionHandle() {
        String comPort = getComPort();
        return (comPort == null || comPort.isEmpty()) ? getMacId() : comPort;
    }

    public void calculatePacketReceptionRateCurrent(int i) {
        setPacketReceptionRateCurrent((getPacketReceivedCountCurrent() / ((i / 1000.0d) * getSamplingRateShimmer())) * 100.0d);
        resetPacketReceptionCurrentCounters();
    }

    protected void initaliseDataProcessing() {
        DataProcessingInterface dataProcessingInterface = this.mDataProcessing;
        if (dataProcessingInterface != null) {
            dataProcessingInterface.initializeProcessData((int) getSamplingRateShimmer());
        }
    }

    public void updateThreadName() {
        String macIdParsed = getMacIdParsed();
        String simpleName = getClass().getSimpleName();
        if (!macIdParsed.isEmpty()) {
            simpleName = simpleName + "-" + macIdParsed;
        }
        setThreadName(simpleName);
    }

    protected void consolePrintException(String str, StackTraceElement[] stackTraceElementArr) {
        consolePrintLn("Exception!");
        System.out.println("Message: " + str);
        Exception exc = new Exception();
        exc.setStackTrace(stackTraceElementArr);
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        System.out.println(stringWriter.toString());
    }

    public void disableAllSensors() {
        Iterator<Integer> it2 = this.mSensorMap.keySet().iterator();
        while (it2.hasNext()) {
            setSensorEnabledState(it2.next().intValue(), false);
        }
    }

    public void addDeviceException(ShimmerException shimmerException) {
        shimmerException.mUniqueID = this.mUniqueID;
        this.mListOfDeviceExceptions.add(shimmerException);
        consolePrintLn(shimmerException.getErrStringFormatted());
    }

    public void sensorAndConfigMapsCreateCommon() {
        generateSensorAndParserMaps();
        generateMapOfAlgorithmModules();
        generateMapOfAlgorithmConfigOptions();
        generateMapOfAlgorithmGroupingMap();
        handleSpecialCasesAfterSensorMapCreate();
    }

    public boolean isFixedShimmerConfigModeSet() {
        return this.mFixedShimmerConfigMode != FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE.NONE;
    }

    public void addFixedShimmerConfig(String str, Object obj) {
        if (this.mFixedShimmerConfigMap == null) {
            this.mFixedShimmerConfigMap = new LinkedHashMap<>();
        }
        this.mFixedShimmerConfigMap.put(str, obj);
    }

    protected boolean setFixedConfigWhenConnecting() {
        return FixedShimmerConfigs.setFixedConfigWhenConnecting(this, this.mFixedShimmerConfigMode, this.mFixedShimmerConfigMap);
    }

    public List<ISensorConfig> getSensorConfig() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            arrayList.addAll(it2.next().getSensorConfig());
        }
        return arrayList;
    }

    public void setSensorConfig(List<ISensorConfig> list) {
        setSensorConfig((ISensorConfig[]) list.toArray(new ISensorConfig[list.size()]));
    }

    public void setSensorConfig(ISensorConfig[] iSensorConfigArr) {
        for (ISensorConfig iSensorConfig : iSensorConfigArr) {
            setSensorConfig(iSensorConfig);
        }
    }

    public void setSensorConfig(ISensorConfig iSensorConfig) {
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().setSensorConfig(iSensorConfig);
        }
    }

    public enum BTRADIO_STATE {
        BT_CLASSIC_BLE_ENABLED("BT Classic and BLE Enabled"),
        BT_CLASSIC_ENABLED("BT Classic Enabled"),
        BLE_ENABLED("BLE Enabled"),
        NONE_ENABLED("None Enabled"),
        UNKNOWN("Unknown");

        private final String text;

        BTRADIO_STATE(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public enum DOCK_STATE {
        DOCKED("Docked"),
        UNDOCKED("Undocked");

        private final String text;

        DOCK_STATE(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public enum SD_STATE {
        LOGGING("Logging"),
        NOT_LOGGING("Not_Logging");

        private final String text;

        SD_STATE(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public enum SENSING_STATE {
        SENSING("Sensing"),
        NOT_SENSING("Not Sensing");

        private final String text;

        SENSING_STATE(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    public static final class DatabaseConfigHandle {
        public static final String BAUD_RATE = "Baud_Rate";
        public static final String BROADCAST_INTERVAL = "Broadcast_Interval";
        public static final String CONFIG_TIME = "Config_Time";
        public static final String DERIVED_SENSORS = "Derived_Sensors";
        public static final String ENABLE_SENSORS = "Enable_Sensors";
        public static final String EXP_BOARD_ID = "Exp_Board_Id";
        public static final String EXP_BOARD_REV = "Exp_Board_Rev";
        public static final String EXP_BOARD_REV_SPEC = "Exp_Board_Rev_Special";
        public static final String EXP_PWR = "Exp_PWR";
        public static final String FW_VERSION = "FW_ID";
        public static final String FW_VERSION_INTERNAL = "FW_Version_Internal";
        public static final String FW_VERSION_MAJOR = "FW_Version_Major";
        public static final String FW_VERSION_MINOR = "FW_Version_Minor";
        public static final String INITIAL_TIMESTAMP = "Initial_TimeStamp";
        public static final String LOW_POWER_AUTOSTOP = "Low_Power_Autostop";
        public static final String MASTER_CONFIG = "Master";
        public static final String N_SHIMMER = "NShimmer";
        public static final String REAL_TIME_CLOCK_DIFFERENCE = "RTC_Difference";
        public static final String RTC_SOURCE = "Rtc_Source";
        public static final String SAMPLE_RATE = "Sample_Rate";
        public static final String SHIMMER_NAME = "Shimmer_Name";
        public static final String SHIMMER_VERSION = "Shimmer_Version";
        public static final String SINGLE_TOUCH_START = "Single_Touch_Start";
        public static final String SYNC_CONFIG = "Sync";
        public static final String TRIAL_ID = "Trial_Id";
        public static final String TRIAL_NAME = "Trial_Name";
        public static final String TXCO = "Txco";
        public static final String USER_BUTTON = "User_Button";
    }
}
