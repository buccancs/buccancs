package com.shimmerresearch.driver;

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
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.AlgorithmResultObject;
import com.shimmerresearch.algorithms.gyroOnTheFlyCal.GyroOnTheFlyCalLoader;
import com.shimmerresearch.algorithms.AlgorithmDetails;
import com.shimmerresearch.algorithms.AlgorithmLoaderInterface;
import com.shimmerresearch.algorithms.orientation.OrientationModule6DOFLoader;
import com.shimmerresearch.algorithms.orientation.OrientationModule9DOFLoader;
import com.shimmerresearch.algorithms.verisense.gyroAutoCal.GyroOnTheFlyCalLoaderVerisense;
import com.shimmerresearch.bluetooth.DataProcessingInterface;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.wiredProtocol.UartComponentPropertyDetails;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.calibration.CalibDetails;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driver.calibration.CalibDetails.CALIB_READ_SOURCE;
import com.shimmerresearch.driverUtilities.AssembleShimmerConfig;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.ConfigOptionDetailsSensor;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.SensorGroupingDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails.BATTERY_LEVEL;
import com.shimmerresearch.driverUtilities.ShimmerLogDetails;
import com.shimmerresearch.driverUtilities.ShimmerSDCardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.driverUtilities.ChannelDetails.CHANNEL_TYPE;
import com.shimmerresearch.driverUtilities.ConfigOptionDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails.DEVICE_TYPE;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.FW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID_SR_CODES;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.AbstractSensor.SENSORS;
import com.shimmerresearch.sensors.SensorSystemTimeStamp;
import com.shimmerresearch.sensors.SensorShimmerClock;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE;
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication;
import com.shimmerresearch.verisense.sensors.ISensorConfig;

public abstract class ShimmerDevice extends BasicProcessWithCallBack implements Serializable {

    public static final String DEFAULT_DOCKID = "Default.01";
    public static final int DEFAULT_SLOTNUMBER = -1;
    public static final String DEFAULT_SHIMMER_NAME = "Shimmer";
    public static final String DEFAULT_SHIMMER_NAME_WITH_ERROR = DEFAULT_SHIMMER_NAME + "_" + "FFFF";
    public static final String DEFAULT_EXPERIMENT_NAME = "DefaultTrial";
    public static final String DEFAULT_MAC_ID = "";
    public static final String DEVICE_ID = "Device_ID";
    public static final String STRING_CONSTANT_PENDING = "Pending";
    public static final String STRING_CONSTANT_UNKNOWN = "Unknown";
    public static final String STRING_CONSTANT_NOT_AVAILABLE = "N/A";
    public static final String STRING_CONSTANT_SD_ERROR = "SD Error";
    public static final double IRREGULAR_SAMPLING_RATE = 0.0;
    public static final String INVALID_TRIAL_NAME_CHAR = "[^A-Za-z0-9._]";
    public static final double DEFAULT_RECEPTION_RATE = 0.0;
    public final static int EVENT_MARKER_DEFAULT = -1;
    public static final int RECONNECT_ATTEMPTS_MAX = 3;
    protected static final int MAX_CALIB_DUMP_MAX = 4096;
    private static final long serialVersionUID = 5087199076353402591L;
    private static final List<AlgorithmLoaderInterface> OPEN_SOURCE_ALGORITHMS = Arrays.asList(
            new GyroOnTheFlyCalLoader(),
            new OrientationModule6DOFLoader(),
            new OrientationModule9DOFLoader(),
            new GyroOnTheFlyCalLoaderVerisense());
    public static boolean mEnableDerivedSensors = true;
    public static int EXP_BOARD_MEMORY_LOCATION_FOR_BTRADIO_STATE = 2018;
    private static boolean mEnableProcessMarkers = true;
    public String mUniqueID = "";
    public List<COMMUNICATION_TYPE> mListOfAvailableCommunicationTypes = new ArrayList<COMMUNICATION_TYPE>();
    public String mMacIdFromUart = DEFAULT_MAC_ID;
    public String mShimmerUserAssignedName = "";
    public String mAlternativeName = "";
    public HashMap<COMMUNICATION_TYPE, Double> mMapOfSamplingRatesShimmer = new HashMap<COMMUNICATION_TYPE, Double>();
    public String mDockID = DEFAULT_DOCKID;
    public DEVICE_TYPE mDockType = DEVICE_TYPE.UNKOWN;
    public int mSlotNumber = DEFAULT_SLOTNUMBER;
    public ShimmerVerObject mShimmerVerObject = new ShimmerVerObject();
    public ExpansionBoardDetails mExpansionBoardDetails = new ExpansionBoardDetails();
    public ShimmerBattStatusDetails mShimmerBattStatusDetails = new ShimmerBattStatusDetails();
    public ShimmerSDCardDetails mShimmerSDCardDetails = new ShimmerSDCardDetails();
    public boolean mReadHwFwSuccess = false;
    public boolean mReadDaughterIDSuccess = false;
    public boolean writeRealWorldClockFromPcTimeSuccess = false;
    public String mActivityLog = "";
    public int mFwImageWriteProgress = 0;
    public int mFwImageTotalSize = 0;
    public float mFwImageWriteSpeed = 0;
    public String mFwImageWriteCurrentAction = "";
    public List<MsgDock> mListOfFailMsg = new ArrayList<MsgDock>();
    public List<ShimmerException> mListOfDeviceExceptions = new ArrayList<ShimmerException>();
    public long mShimmerRealTimeClockConFigTime = 0;
    public long mShimmerLastReadRtcValueMilliSecs = 0;
    public String mShimmerLastReadRtcValueParsed = "";
    public byte[] mCalibBytes = new byte[]{};
    public HashMap<Integer, String> mCalibBytesDescriptions = new HashMap<Integer, String>();
    public long mPacketReceivedCountCurrent = 0;
    public long mPacketExpectedCountCurrent = 0;
    public long mPacketReceivedCountOverall = 0;
    public long mPacketExpectedCountOverall = 0;
    public long mEventMarkers = EVENT_MARKER_DEFAULT;
    public transient ObjectCluster mLastProcessedObjectCluster = null;
    public List<ShimmerLogDetails> mListofLogs = new ArrayList<ShimmerLogDetails>();
    public transient CommsProtocolRadio mCommsProtocolRadio = null;
    public BT_STATE mBluetoothRadioState = BT_STATE.DISCONNECTED;
    public DOCK_STATE mDockState = DOCK_STATE.UNDOCKED;
    public BTRADIO_STATE mRadioState = BTRADIO_STATE.UNKNOWN;
    public boolean mVerboseMode = true;
    public boolean mIsTrialDetailsStoredOnDevice = true;
    public int mNumConnectionAttempts = -1;
    protected LinkedHashMap<SENSORS, AbstractSensor> mMapOfSensorClasses = new LinkedHashMap<SENSORS, AbstractSensor>();
    protected LinkedHashMap<Integer, SensorDetails> mSensorMap = new LinkedHashMap<Integer, SensorDetails>();
    protected HashMap<COMMUNICATION_TYPE, TreeMap<Integer, SensorDetails>> mParserMap = new HashMap<COMMUNICATION_TYPE, TreeMap<Integer, SensorDetails>>();
    protected Map<String, ConfigOptionDetailsSensor> mConfigOptionsMapSensors = new HashMap<String, ConfigOptionDetailsSensor>();
    protected TreeMap<Integer, SensorGroupingDetails> mSensorGroupingMap = new TreeMap<Integer, SensorGroupingDetails>();
    protected Map<String, AbstractAlgorithm> mMapOfAlgorithmModules = new HashMap<String, AbstractAlgorithm>();
    protected ArrayList<String> mAlgorithmProcessingSequence = null;
    protected TreeMap<Integer, SensorGroupingDetails> mMapOfAlgorithmGrouping = new TreeMap<Integer, SensorGroupingDetails>();
    protected Map<String, ConfigOptionDetails> mConfigOptionsMapAlgorithms = new HashMap<String, ConfigOptionDetails>();
    protected boolean mIsConnected = false;
    protected boolean mIsSensing = false;
    protected boolean mIsStreaming = false;
    protected boolean mIsInitialised = false;
    protected boolean mHaveAttemptedToReadConfig = false;
    protected ConfigByteLayout mConfigByteLayout;// = new InfoMemLayoutShimmer3();
    protected byte[] mConfigBytes = ConfigByteLayout.createConfigByteArrayEmpty(512);
    protected byte[] mInfoMemBytesOriginal = ConfigByteLayout.createConfigByteArrayEmpty(512);
    protected String mTrialName = "";
    protected long mConfigTime;
    protected long mEventMarkersCodeLast = 0;
    protected boolean mEventMarkersIsPulse = false;
    protected long mEnabledSensors = (long) 0;
    protected long mDerivedSensors = (long) 0;
    protected String mComPort = "";
    protected int mInternalExpPower = -1;
    protected DataProcessingInterface mDataProcessing;
    protected FIXED_SHIMMER_CONFIG_MODE mFixedShimmerConfigMode = FIXED_SHIMMER_CONFIG_MODE.NONE;
    protected LinkedHashMap<String, Object> mFixedShimmerConfigMap = null;
    protected boolean mAutoStartStreaming = false;
    private boolean mIsPlaybackDevice = false;
    private boolean mIsEnabledAlgorithmModulesDuringPlayback = false;
    private boolean mConfigurationReadSuccess = false;
    private boolean mIsDocked = false;
    private boolean mIsUsbPluggedIn = false;
    private double mPacketReceptionRateCurrent = DEFAULT_RECEPTION_RATE;
    private double mPacketReceptionRateOverall = DEFAULT_RECEPTION_RATE;
    private long mPacketLossCountPerTrial = 0;
    private boolean mUpdateOnlyWhenStateChanges = false;

    {
        mMapOfSamplingRatesShimmer.put(COMMUNICATION_TYPE.SD, 51.2);
        mMapOfSamplingRatesShimmer.put(COMMUNICATION_TYPE.BLUETOOTH, 51.2);
    }

    public ShimmerDevice() {
        setThreadName(this.getClass().getSimpleName());
        setupDataProcessing();
    }

    public static boolean isTrialOrShimmerNameInvalid(String name) {
        if (name.isEmpty()) {
            return true;
        }

        Pattern p = Pattern.compile(INVALID_TRIAL_NAME_CHAR);
        Matcher m = p.matcher(name);
        return m.find();
    }

    public static boolean isSupportedDerivedSensors(ShimmerVerObject svo) {
        if ((isVerCompatibleWith(svo, HW_ID.SHIMMER_3, FW_ID.BTSTREAM, 0, 7, 0))
                || (isVerCompatibleWith(svo, HW_ID.SHIMMER_3, FW_ID.SDLOG, 0, 8, 69))
                || (isVerCompatibleWith(svo, HW_ID.SHIMMER_3, FW_ID.LOGANDSTREAM, 0, 3, 17))
                || (svo.isShimmerGen3R())
                || (svo.isShimmerGenGq())
                || (svo.isShimmerGen4())) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedNoImuSensors(ShimmerVerObject svo, ExpansionBoardDetails ebd) {
        if (svo == null || ebd == null) {
            return false;
        }

        int expBrdId = ebd.getExpansionBoardId();
        int expBrdRev = ebd.getExpansionBoardRev();
        int expBrdRevSpecial = ebd.getExpansionBoardRevSpecial();

        if (svo.getHardwareVersion() == HW_ID.SHIMMER_3 && (
                (expBrdId == HW_ID_SR_CODES.SHIMMER_ECG_MD && expBrdRev == 3 && expBrdRevSpecial == 1)
        )) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isVerCompatibleWith(ShimmerVerObject svo, int hardwareVersion, int firmwareIdentifier, int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        return svo.compareVersions(hardwareVersion, firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
    }


    protected static double roundSamplingRateToSupportedValue(double originalSamplingRate, double samplingClockFreq) {
        Double roundedSamplingRate = samplingClockFreq / Math.floor(samplingClockFreq / originalSamplingRate);
        roundedSamplingRate = (double) Math.round(roundedSamplingRate * 100) / 100;
        return roundedSamplingRate;
    }

    protected static double convertSamplingRateBytesToFreq(byte samplingRateLSB, byte samplingRateMSB, double samplingClockFreq) {


        double samplingRate = samplingClockFreq / (double) ((int) (samplingRateLSB & 0xFF) + ((int) (samplingRateMSB & 0xFF) << 8));
        return samplingRate;
    }

    protected static byte[] convertSamplingRateFreqToBytes(double samplingRateFreq, double samplingClockFreq) {
        byte[] buf = new byte[2];


        int samplingRate = (int) Math.round(samplingClockFreq / samplingRateFreq);
        buf[0] = (byte) (samplingRate & 0xFF);
        buf[1] = (byte) ((samplingRate >> 8) & 0xFF);

        return buf;
    }

    public static void printMapOfConfig(HashMap<String, Object> mapOfConfigForDb) {
        System.out.println("Printing map of Config for DB, size = " + mapOfConfigForDb.keySet().size());
        for (String configLbl : mapOfConfigForDb.keySet()) {
            String stringToPrint = configLbl + " = ";
            Object val = mapOfConfigForDb.get(configLbl);

            if (val instanceof String) {
                stringToPrint += (String) val;
            } else if (val instanceof Boolean) {
                stringToPrint += Boolean.toString((boolean) val);
            } else if (val instanceof Double) {
                stringToPrint += Double.toString((double) val);
            } else if (val instanceof Integer) {
                stringToPrint += Integer.toString((int) val);
            } else if (val instanceof Long) {
                stringToPrint += Long.toString((long) val);
            }
            System.out.println(stringToPrint);
        }
    }

    public static double getRtcClockFreq() {
        return 32768.0;
    }

    public abstract ShimmerDevice deepClone();

    public abstract void sensorAndConfigMapsCreate();


    protected abstract void interpretDataPacketFormat(Object object, COMMUNICATION_TYPE commType);


    public abstract void configBytesParse(byte[] configBytes, COMMUNICATION_TYPE commType);

    public abstract byte[] configBytesGenerate(boolean generateForWritingToShimmer, COMMUNICATION_TYPE commType);

    public abstract void createConfigBytesLayout();

    protected abstract void dataHandler(ObjectCluster ojc);

    public void setShimmerVersionObject(ShimmerVerObject sVO) {
        mShimmerVerObject = sVO;
    }

    public void setShimmerVersionObjectAndCreateSensorMap(ShimmerVerObject sVO) {
        setShimmerVersionObject(sVO);
        sensorAndConfigMapsCreate();
    }

    public void setHardwareVersionAndCreateSensorMaps(int hardwareVersion) {
        ShimmerVerObject sVO = new ShimmerVerObject(hardwareVersion, getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal());
        setShimmerVersionObjectAndCreateSensorMap(sVO);
    }

    public void clearShimmerVersionObject() {
        setShimmerVersionObject(new ShimmerVerObject());
    }

    public void clearShimmerVersionObjectAndCreateSensorMaps() {

        setShimmerVersionObjectAndCreateSensorMap(new ShimmerVerObject());
    }

    private void updateSensorDetailsWithCommsTypes() {
        if (mMapOfSensorClasses != null) {
            for (AbstractSensor absensorSensor : mMapOfSensorClasses.values()) {
                absensorSensor.updateSensorDetailsWithCommsTypes(mListOfAvailableCommunicationTypes);
            }
        }
    }

    private void updateSamplingRatesMapWithCommsTypes() {
        for (COMMUNICATION_TYPE commType : mListOfAvailableCommunicationTypes) {
            if (!mMapOfSamplingRatesShimmer.containsKey(commType)) {
                mMapOfSamplingRatesShimmer.put(commType, getSamplingRateShimmer());
            }
        }

        Iterator<COMMUNICATION_TYPE> iterator = mMapOfSamplingRatesShimmer.keySet().iterator();
        while (iterator.hasNext()) {
            COMMUNICATION_TYPE commType = iterator.next();
            if (!mListOfAvailableCommunicationTypes.contains(commType)) {
                iterator.remove();
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
        mSensorMap = new LinkedHashMap<Integer, SensorDetails>();
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
            mSensorMap.putAll(abstractSensor.mSensorMap);
        }
    }

    public void generateParserMap() {

        mParserMap = new HashMap<COMMUNICATION_TYPE, TreeMap<Integer, SensorDetails>>();
        for (COMMUNICATION_TYPE commType : mListOfAvailableCommunicationTypes) {
            for (Integer sensorId : mSensorMap.keySet()) {
                SensorDetails sensorDetails = getSensorDetails(sensorId);
                if (sensorDetails.isEnabled(commType)) {
                    TreeMap<Integer, SensorDetails> parserMapPerComm = mParserMap.get(commType);
                    if (parserMapPerComm == null) {
                        parserMapPerComm = new TreeMap<Integer, SensorDetails>();
                        mParserMap.put(commType, parserMapPerComm);
                    }
                    parserMapPerComm.put(sensorId, sensorDetails);
                }
            }
        }

        updateExpectedDataPacketSize();
    }

    public void generateConfigOptionsMap() {
        mConfigOptionsMapSensors = new HashMap<String, ConfigOptionDetailsSensor>();
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
            HashMap<String, ConfigOptionDetailsSensor> configOptionsMapPerSensor = abstractSensor.getConfigOptionsMap();

            if (configOptionsMapPerSensor != null && configOptionsMapPerSensor.keySet().size() > 0) {
                loadCompatibleConfigOptionGroupEntries(configOptionsMapPerSensor);

            }
        }
    }

    public void generateSensorGroupingMap() {
        mSensorGroupingMap = new TreeMap<Integer, SensorGroupingDetails>();

        Iterator<AbstractSensor> iteratorSensors = mMapOfSensorClasses.values().iterator();
        while (iteratorSensors.hasNext()) {
            AbstractSensor sensor = iteratorSensors.next();
            loadCompatibleSensorGroupEntries(sensor.getSensorGroupingMap());
        }
    }

    protected void loadCompatibleSensorGroupEntries(Map<Integer, SensorGroupingDetails> groupMapRef) {
        if (groupMapRef != null) {
            Iterator<Entry<Integer, SensorGroupingDetails>> iteratorSensorGroupMap = groupMapRef.entrySet().iterator();
            while (iteratorSensorGroupMap.hasNext()) {
                Entry<Integer, SensorGroupingDetails> entry = iteratorSensorGroupMap.next();
                if (isVerCompatibleWithAnyOf(entry.getValue().mListOfCompatibleVersionInfo)) {
                    mSensorGroupingMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    protected void loadCompatibleConfigOptionGroupEntries(Map<String, ConfigOptionDetailsSensor> configOptionMapRef) {
        if (configOptionMapRef != null) {
            Iterator<Entry<String, ConfigOptionDetailsSensor>> iteratorConfigOptionMap = configOptionMapRef.entrySet().iterator();
            while (iteratorConfigOptionMap.hasNext()) {
                Entry<String, ConfigOptionDetailsSensor> entry = iteratorConfigOptionMap.next();
                if (isVerCompatibleWithAnyOf(entry.getValue().mListOfCompatibleVersionInfo)) {
                    mConfigOptionsMapSensors.put(entry.getKey(), entry.getValue());
                } else {
                }
            }
        }
    }

    public TreeMap<Integer, SensorGroupingDetails> getSensorGroupingMap() {
        return mSensorGroupingMap;
    }

    public Map<String, ConfigOptionDetailsSensor> getConfigOptionsMap() {
        return mConfigOptionsMapSensors;
    }

    public Map<String, ConfigOptionDetails> getConfigOptionsMapAlorithms() {
        return mConfigOptionsMapAlgorithms;
    }

    public void clearBattStatusDetails() {
        setBattStatusDetails(new ShimmerBattStatusDetails());
    }

    public void setShimmerUserAssignedNameNoLengthCheck(String shimmerUserAssignedName) {
        String nameToSet = shimmerUserAssignedName.replace(" ", "_");
        mShimmerUserAssignedName = nameToSet;
    }

    public void setShimmerUserAssignedNameWithMac(String shimmerUserAssignedName) {
        if (shimmerUserAssignedName.length() == 0 || UtilShimmer.isNumeric("" + shimmerUserAssignedName.charAt(0))) {
            shimmerUserAssignedName = "S" + shimmerUserAssignedName;
        }

        shimmerUserAssignedName = shimmerUserAssignedName.replaceAll(INVALID_TRIAL_NAME_CHAR, "");

        String addition = "_" + getMacIdParsed();
        if ((shimmerUserAssignedName.length() + addition.length()) > 12) {
            setShimmerUserAssignedNameNoLengthCheck(shimmerUserAssignedName.substring(0, (12 - addition.length())) + addition);
        } else {
            setShimmerUserAssignedNameNoLengthCheck(shimmerUserAssignedName + addition);
        }
    }

    public void setLastReadRealTimeClockValue(long time) {
        mShimmerLastReadRtcValueMilliSecs = time;
        mShimmerLastReadRtcValueParsed = UtilShimmer.fromMilToDateExcelCompatible(Long.toString(time), false);
    }

    public boolean isReadRealTimeClockSet() {
        return mShimmerLastReadRtcValueMilliSecs > 1420070400000L ? true : false;
    }

    public void setShimmerInfoMemBytes(byte[] shimmerInfoMemBytes) {
        configBytesParse(shimmerInfoMemBytes);
    }


    public void setEventTriggered(long eventCode, int eventType) {
        mEventMarkersCodeLast = eventCode;

        if (mEventMarkers > 0) {
            mEventMarkers = mEventMarkers + eventCode;
        } else {
            mEventMarkers = mEventMarkers + eventCode + (-EVENT_MARKER_DEFAULT);
        }

        if (eventType == 2) {
            mEventMarkersIsPulse = true;
        }
    }

    public void setEventUntrigger(long eventCode) {
        mEventMarkers = mEventMarkers - eventCode;
        if (mEventMarkers == 0) {
            mEventMarkers = EVENT_MARKER_DEFAULT;
        }
    }

    public void untriggerEventIfLastOneWasPulse() {
        if (mEventMarkersIsPulse) {
            mEventMarkersIsPulse = false;
            setEventUntrigger(mEventMarkersCodeLast);
        }
    }

    public void processEventMarkerCh(ObjectCluster objectCluster) {
        if (mEnableProcessMarkers) {
            objectCluster.addCalDataToMap(SensorShimmerClock.channelEventMarker, mEventMarkers);
            untriggerEventIfLastOneWasPulse();
        }
    }


    public void addDOCKCoummnicationRoute(String dockId, int slotNumber) {
        setDockInfo(dockId, slotNumber);
        addCommunicationRoute(COMMUNICATION_TYPE.DOCK);
    }

    public void clearDockInfo(String dockId, int slotNumber) {
        setDockInfo(dockId, slotNumber);
    }

    public void setDockInfo(String dockId, int slotNumber) {
        mDockID = dockId;
        parseDockType();
        mSlotNumber = slotNumber;
        mUniqueID = mDockID + "." + String.format("%02d", mSlotNumber);
    }

    public void addCommunicationRoute(COMMUNICATION_TYPE communicationType) {
        if (!mListOfAvailableCommunicationTypes.contains(communicationType)) {
            mListOfAvailableCommunicationTypes.add(communicationType);
        }

        if (communicationType == COMMUNICATION_TYPE.DOCK) {
            setIsDocked(true);
        }

        updateSensorDetailsWithCommsTypes();
        updateSamplingRatesMapWithCommsTypes();
    }

    public void setCommunicationRoute(COMMUNICATION_TYPE communicationType) {
        mListOfAvailableCommunicationTypes.clear();
        addCommunicationRoute(communicationType);
    }

    public void removeCommunicationRoute(COMMUNICATION_TYPE communicationType) {
        if (mListOfAvailableCommunicationTypes.contains(communicationType)) {
            mListOfAvailableCommunicationTypes.remove(communicationType);
        }

        if (communicationType == COMMUNICATION_TYPE.DOCK) {
            setIsDocked(false);
            setFirstDockRead();
            clearDockInfo(mDockID, mSlotNumber);
        }

        updateSensorDetailsWithCommsTypes();
        updateSamplingRatesMapWithCommsTypes();
    }

    public List<COMMUNICATION_TYPE> getCommunicationRoutes() {
        return mListOfAvailableCommunicationTypes;
    }

    public void setCommunicationRoutes(List<COMMUNICATION_TYPE> communicationTypes) {
        mListOfAvailableCommunicationTypes.clear();
        for (COMMUNICATION_TYPE communicationType : communicationTypes) {
            addCommunicationRoute(communicationType);
        }
    }

    public boolean isCommunicationRouteAvailable(COMMUNICATION_TYPE commType) {
        return mListOfAvailableCommunicationTypes.contains(commType);
    }


    public boolean isFirstSdAccess() {
        return mShimmerSDCardDetails.isFirstSdAccess();
    }

    public void setFirstSdAccess(boolean state) {
        this.mShimmerSDCardDetails.setFirstSdAccess(state);
    }

    public boolean isSDErrorOrNotPresent() {
        return (mShimmerSDCardDetails.isSDError() || !mShimmerSDCardDetails.isSDPresent());
    }

    public boolean isSDError() {
        return mShimmerSDCardDetails.isSDError();
    }

    public void setIsSDError(boolean state) {
        this.mShimmerSDCardDetails.setIsSDError(state);
    }

    public boolean isSDPresent() {
        return this.mShimmerSDCardDetails.isSDPresent();
    }

    public void setIsSDPresent(boolean state) {
        this.mShimmerSDCardDetails.setIsSDPresent(state);
    }

    public boolean isSDLogging() {
        return mShimmerSDCardDetails.isSDLogging();
    }

    public void setIsSDLogging(boolean state) {
        mShimmerSDCardDetails.setIsSDLogging(state);
    }

    public void setShimmerDriveInfo(ShimmerSDCardDetails shimmerSDCardDetails) {
        mShimmerSDCardDetails = shimmerSDCardDetails;
    }

    public ShimmerSDCardDetails getShimmerSDCardDetails() {
        return mShimmerSDCardDetails;
    }

    public long getDriveTotalSpace() {
        return mShimmerSDCardDetails.getDriveTotalSpace();
    }

    public String getDriveUsedSpaceParsed() {
        return mShimmerSDCardDetails.getDriveUsedSpaceParsed();
    }

    public long getDriveUsedSpace() {
        return mShimmerSDCardDetails.getDriveUsedSpace();
    }

    public void setDriveUsedSpace(long driveUsedSpace) {
        mShimmerSDCardDetails.setDriveUsedSpaceBytes(driveUsedSpace);
    }

    public long getDriveUsedSpaceKB() {
        return mShimmerSDCardDetails.getDriveUsedSpaceKB();
    }

    public void setDriveUsedSpaceKB(long driveUsedSpaceKB) {
        mShimmerSDCardDetails.setDriveUsedSpaceKB(driveUsedSpaceKB);
    }

    public String getDriveTotalSpaceParsed() {
        return mShimmerSDCardDetails.getDriveTotalSpaceParsed();
    }

    public long getDriveUsableSpace() {
        return mShimmerSDCardDetails.getDriveUsableSpace();
    }

    public long getDriveFreeSpace() {
        return mShimmerSDCardDetails.getDriveFreeSpace();
    }

    public String getDriveCapacityParsed() {
        String strPending = STRING_CONSTANT_PENDING;

        if (!isSupportedSdCardAccess()) {
            return STRING_CONSTANT_UNKNOWN;
        }

        if (isSDErrorOrNotPresent()) {
            return STRING_CONSTANT_SD_ERROR;
        } else {
            String driveUsedSpaceParsed = getDriveUsedSpaceParsed();
            if (!driveUsedSpaceParsed.isEmpty()) {
                if (this.isShimmerGenGq() && isSDLogging()) {
                    driveUsedSpaceParsed = Long.toString(getDriveUsedSpaceKB()) + " KB";
                }

                return (driveUsedSpaceParsed + " / " + getDriveTotalSpaceParsed());
            } else {
                return (isHaveAttemptedToRead() ? STRING_CONSTANT_UNKNOWN : (isFirstSdAccess() ? strPending : STRING_CONSTANT_UNKNOWN));
            }
        }
    }

    public boolean isSDSpaceIncreasing() {
        return mShimmerSDCardDetails.isSDSpaceIncreasing();
    }


    public boolean isConfigurationReadSuccess() {
        return mConfigurationReadSuccess;
    }

    public void setConfigurationReadSuccess(boolean configurationReadSuccess) {
        mConfigurationReadSuccess = configurationReadSuccess;
    }

    public ShimmerVerObject getShimmerVerObject() {
        return mShimmerVerObject;
    }

    public int getHardwareVersion() {
        return mShimmerVerObject.mHardwareVersion;
    }

    public void setHardwareVersion(int hardwareVersion) {
        ShimmerVerObject sVO = new ShimmerVerObject(hardwareVersion, getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal());
        setShimmerVersionObject(sVO);
    }

    public int getFirmwareIdentifier() {
        return mShimmerVerObject.mFirmwareIdentifier;
    }

    public int getFirmwareVersionMajor() {
        return mShimmerVerObject.mFirmwareVersionMajor;
    }

    public int getFirmwareVersionMinor() {
        return mShimmerVerObject.mFirmwareVersionMinor;
    }

    public int getFirmwareVersionInternal() {
        return mShimmerVerObject.mFirmwareVersionInternal;
    }

    public int getFirmwareVersionCode() {
        return mShimmerVerObject.mFirmwareVersionCode;
    }

    public String getFirmwareVersionParsed() {
        return mShimmerVerObject.mFirmwareVersionParsed;
    }

    public String getHardwareVersionParsed() {
        return mShimmerVerObject.getHardwareVersionParsed();
    }

    public ExpansionBoardDetails getExpansionBoardDetails() {
        return mExpansionBoardDetails;
    }

    public void setExpansionBoardDetails(ExpansionBoardDetails eBD) {
        mExpansionBoardDetails = eBD;
    }

    public int getExpansionBoardId() {
        return mExpansionBoardDetails.mExpansionBoardId;
    }

    public int getExpansionBoardRev() {
        return mExpansionBoardDetails.mExpansionBoardRev;
    }

    public int getExpansionBoardRevSpecial() {
        return mExpansionBoardDetails.mExpansionBoardRevSpecial;
    }

    public String getExpansionBoardParsed() {
        return mExpansionBoardDetails.getExpansionBoardParsed();
    }

    public String getExpansionBoardParsedWithVer() {
        return mExpansionBoardDetails.getExpansionBoardParsedWithVer();
    }

    public String getExpansionBoardVerParsed() {
        return mExpansionBoardDetails.getBoardVerString();
    }

    public void clearExpansionBoardDetails() {
        mExpansionBoardDetails = new ExpansionBoardDetails();
    }

    public void setExpansionBoardDetailsAndCreateSensorMap(ExpansionBoardDetails eBD) {
        setExpansionBoardDetails(eBD);
        sensorAndConfigMapsCreate();
    }

    public ShimmerBattStatusDetails getBattStatusDetails() {
        return mShimmerBattStatusDetails;
    }

    public void setBattStatusDetails(ShimmerBattStatusDetails shimmerBattStatusDetails) {
        mShimmerBattStatusDetails = shimmerBattStatusDetails;
    }

    public String getChargingStateParsed() {
        return mShimmerBattStatusDetails.getChargingStatusParsed();
    }

    public String getBattVoltage() {
        return mShimmerBattStatusDetails.getBattVoltageParsed();
    }

    public String getEstimatedChargePercentageParsed() {
        return mShimmerBattStatusDetails.getEstimatedChargePercentageParsed();
    }

    public Double getEstimatedChargePercentage() {
        return mShimmerBattStatusDetails.getEstimatedChargePercentage();
    }

    public BATTERY_LEVEL getEstimatedBatteryLevel() {
        return mShimmerBattStatusDetails.getEstimatedBatteryLevel();
    }

    public boolean setIsDocked(boolean docked) {
        boolean changed = false;
        if (mIsDocked != docked) {
            changed = true;
        }
        mIsDocked = docked;
        if (mIsDocked) {
            mDockState = DOCK_STATE.DOCKED;
        } else {
            mDockState = DOCK_STATE.UNDOCKED;
        }
        if (changed) {
            stateHandler(mDockState);
        }
        if (!mUpdateOnlyWhenStateChanges) {
            return true;
        }
        return changed;
    }

    public boolean setIsUsbPluggedIn(boolean usbPluggedIn) {
        boolean changed = false;
        if (mIsUsbPluggedIn != usbPluggedIn) {
            changed = true;
        }
        mIsUsbPluggedIn = usbPluggedIn;
        return changed;

    }

    public void stateHandler(Object obj) {

    }

    public boolean isDocked() {
        return mIsDocked;
    }

    public boolean isUsbPluggedIn() {
        return mIsUsbPluggedIn;
    }

    public void setIsConnected(boolean state) {
        mIsConnected = state;
    }

    public boolean isConnected() {
        return mIsConnected;
    }

    public boolean isStreaming() {
        return mIsStreaming;
    }

    public void setIsStreaming(boolean state) {
        mIsStreaming = state;
    }

    public boolean isSensing() {
        return (mIsSensing || isSDLogging() || isStreaming());
    }

    public void setIsSensing(boolean state) {
        mIsSensing = state;
    }

    public void setIsInitialised(boolean isInitialized) {
        mIsInitialised = isInitialized;
    }

    public boolean isInitialised() {
        return mIsInitialised;
    }

    public boolean isHaveAttemptedToRead() {
        return haveAttemptedToRead();
    }

    public boolean haveAttemptedToRead() {
        return mHaveAttemptedToReadConfig;
    }

    public boolean isShimmerError() {
        if (isSDErrorOrNotPresent()) {
            return true;
        }
        return false;
    }

    public void setHaveAttemptedToReadConfig(boolean haveAttemptedToRead) {
        mHaveAttemptedToReadConfig = haveAttemptedToRead;
    }

    public String getMacIdFromUart() {
        return mMacIdFromUart;
    }

    public void setMacIdFromUart(String macIdFromUart) {
        this.mMacIdFromUart = macIdFromUart;
        updateThreadName();
    }

    public String getMacIdFromUartParsed() {
        if (mMacIdFromUart != null) {
            int length = mMacIdFromUart.length();
            if (length >= 12) {
                return this.mMacIdFromUart.substring(length - 4, length);
            }
        }
        return "0000";
    }

    public String getShimmerUserAssignedName() {
        return mShimmerUserAssignedName;
    }

    public void setShimmerUserAssignedName(String shimmerUserAssignedName) {
        if (!shimmerUserAssignedName.isEmpty()) {
            shimmerUserAssignedName = shimmerUserAssignedName.replaceAll(INVALID_TRIAL_NAME_CHAR, "");

            if (UtilShimmer.isNumeric("" + shimmerUserAssignedName.charAt(0))) {
                shimmerUserAssignedName = "S" + shimmerUserAssignedName;
            }
        } else {
            shimmerUserAssignedName = ShimmerObject.DEFAULT_SHIMMER_NAME + "_" + this.getMacIdFromUartParsed();
        }

        if (shimmerUserAssignedName.length() > 12) {
            setShimmerUserAssignedNameNoLengthCheck(shimmerUserAssignedName.substring(0, 12));
        } else {
            setShimmerUserAssignedNameNoLengthCheck(shimmerUserAssignedName);
        }
    }

    public byte[] getShimmerConfigBytes() {
        return mConfigBytes;
    }

    public byte[] getShimmerInfoMemBytesOriginal() {
        return mInfoMemBytesOriginal;
    }

    public HashMap<Integer, String> getMapOfConfigByteDescriptions() {
        if (mConfigByteLayout != null) {
            return mConfigByteLayout.getMapOfByteDescriptions();
        }
        return null;
    }

    @Deprecated
    public double getPacketReceptionRate() {
        return getPacketReceptionRateOverall();
    }

    public double getPacketReceptionRateOverall() {
        return mPacketReceptionRateOverall;
    }

    public void setPacketReceptionRateOverall(double packetReceptionRateTrial) {
        mPacketReceptionRateOverall = UtilShimmer.nudgeDouble(packetReceptionRateTrial, 0.0, 100.0);
    }

    public void resetEventMarkerValuetoDefault() {
        mEventMarkers = EVENT_MARKER_DEFAULT;
    }

    protected void resetShimmerClock() {
        AbstractSensor abstractSensor = getSensorClass(AbstractSensor.SENSORS.CLOCK);
        if (abstractSensor != null && abstractSensor instanceof SensorShimmerClock) {
            SensorShimmerClock shimmerClock = (SensorShimmerClock) abstractSensor;
            shimmerClock.resetShimmerClock();
        }
    }

    protected void resetPacketLossVariables() {
        mPacketLossCountPerTrial = 0;
        resetPacketReceptionOverallVariables();
        resetPacketReceptionCurrentVariables();
    }

    public void incrementPacketsReceivedCounters() {
        incrementPacketReceivedCountCurrent();
        incrementPacketReceivedCountOverall();
    }

    public void setPacketExpectedCountOverall(long packetReceivedCountOverall) {
        mPacketExpectedCountOverall = packetReceivedCountOverall;
    }

    public long getPacketReceivedCountOverall() {
        return mPacketReceivedCountOverall;
    }

    private void incrementPacketReceivedCountOverall() {
        mPacketReceivedCountOverall += 1;
    }

    public void resetPacketReceptionOverallVariables() {
        mPacketExpectedCountOverall = 0;
        mPacketReceivedCountOverall = 0;
        setPacketReceptionRateOverall(DEFAULT_RECEPTION_RATE);
    }

    public long getPacketReceivedCountCurrent() {
        return mPacketReceivedCountCurrent;
    }

    public void incrementPacketReceivedCountCurrent() {
        mPacketReceivedCountCurrent += 1;
    }

    public void incrementPacketExpectedCountCurrent() {
        mPacketExpectedCountCurrent += 1;
    }

    public void resetPacketReceptionCurrentCounters() {
        mPacketExpectedCountCurrent = 0;
        mPacketReceivedCountCurrent = 0;
    }

    public void resetPacketReceptionCurrentVariables() {
        resetPacketReceptionCurrentCounters();
        setPacketReceptionRateCurrent(DEFAULT_RECEPTION_RATE);
    }

    public double getPacketReceptionRateCurrent() {
        return mPacketReceptionRateCurrent;
    }

    public void setPacketReceptionRateCurrent(double packetReceptionRateCurrent) {
        mPacketReceptionRateCurrent = UtilShimmer.nudgeDouble(packetReceptionRateCurrent, 0.0, 100.0);
    }

    public long getPacketLossCountPerTrial() {
        return mPacketLossCountPerTrial;
    }

    public void setPacketLossCountPerTrial(long packetLossCountPerTrial) {
        mPacketLossCountPerTrial = packetLossCountPerTrial;
    }


    public long getConfigTime() {
        return mConfigTime;
    }

    public void setConfigTime(long trialConfigTime) {
        mConfigTime = trialConfigTime;
    }

    public String getConfigTimeLongString() {
        return Long.toString(getConfigTime());
    }

    public String getConfigTimeParsed() {
        return UtilShimmer.convertSecondsToDateString(mConfigTime);
    }

    public String getConfigTimeExcelCompatible() {
        return UtilShimmer.fromMilToDateExcelCompatible(Long.toString(mConfigTime * 1000L), false);
    }

    public ConfigByteLayout getConfigByteLayout() {
        createInfoMemLayoutObjectIfNeeded();
        return mConfigByteLayout;
    }

    public int getExpectedInfoMemByteLength() {
        createInfoMemLayoutObjectIfNeeded();
        return mConfigByteLayout.mInfoMemSize;
    }

    public void createInfoMemLayoutObjectIfNeeded() {
        boolean create = false;
        if (mConfigByteLayout == null) {
            create = true;
        } else {
            if (mConfigByteLayout.isDifferent(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal())) {
                create = true;
            }
        }

        if (create) {
            createConfigBytesLayout();
        }
    }


    protected void parseDockType() {
        if (mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASICDOCK.getLabel())) {
            mDockType = DEVICE_TYPE.BASICDOCK;
            setHaveAttemptedToReadConfig(true);
        } else if (mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE15.getLabel())) {
            mDockType = DEVICE_TYPE.BASE15;
        } else if (mDockID.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.BASE6.getLabel())) {
            mDockType = DEVICE_TYPE.BASE6;
        } else {
            mDockType = DEVICE_TYPE.UNKOWN;
        }
    }

    public ObjectCluster buildMsg(byte[] dataPacketFormat, byte[] packetByteArray, COMMUNICATION_TYPE commType, boolean isTimeSyncEnabled, double pcTimestampMs) {
        interpretDataPacketFormat(dataPacketFormat, commType);
        return buildMsg(packetByteArray, commType, isTimeSyncEnabled, pcTimestampMs);
    }

    public ObjectCluster buildMsg(byte[] newPacket, COMMUNICATION_TYPE commType, boolean isTimeSyncEnabled, double pcTimestampMs) {
        boolean debug = false;

        if (debug)
            consolePrintLn("Packet: " + UtilShimmer.bytesToHexStringWithSpacesFormatted(newPacket));

        ObjectCluster ojc = new ObjectCluster(mShimmerUserAssignedName, getMacId());
        ojc.mRawData = newPacket;
        ojc.createArrayData(getNumberOfEnabledChannels(commType));

        incrementPacketsReceivedCounters();

        if (debug)
            System.out.println("\nNew Parser loop. Packet length:\t" + newPacket.length);

        TreeMap<Integer, SensorDetails> parserMapPerComm = mParserMap.get(commType);
        if (parserMapPerComm != null) {
            int index = 0;
            for (SensorDetails sensor : parserMapPerComm.values()) {
                int length = sensor.getExpectedPacketByteArray(commType);
                byte[] sensorByteArray = new byte[length];
                if (length != 0) {
                    if ((index + sensorByteArray.length) <= newPacket.length) {
                        System.arraycopy(newPacket, index, sensorByteArray, 0, sensorByteArray.length);
                    } else {
                        consolePrintLn(mShimmerUserAssignedName + " ERROR PARSING " + sensor.mSensorDetailsRef.mGuiFriendlyLabel);
                    }
                }
                sensor.processData(sensorByteArray, commType, ojc, isTimeSyncEnabled, pcTimestampMs);

                if (debug)
                    System.out.println(sensor.mSensorDetailsRef.mGuiFriendlyLabel + "\texpectedPacketArraySize:" + length + "\tcurrentIndex:" + index);
                index += length;
            }
        } else {
            consolePrintErrLn("ERROR!!!! Parser map null");
        }

        ojc = processData(ojc);

        return ojc;
    }

    protected ObjectCluster processData(ObjectCluster ojc) {
        if (mDataProcessing != null) {
            ojc = mDataProcessing.processData(ojc);
        }

        ojc = processAlgorithms(ojc);
        return ojc;
    }

    public ObjectCluster processAlgorithms(ObjectCluster ojc) {
        if (mAlgorithmProcessingSequence != null) {
            for (String algoKey : mAlgorithmProcessingSequence) {
                AbstractAlgorithm aA = getMapOfAlgorithmModules().get(algoKey);
                if (aA.isEnabled()) {
                    try {
                        AlgorithmResultObject algorithmResultObject = aA.processDataRealTime(ojc);
                        if (algorithmResultObject != null) {
                            ojc = (ObjectCluster) algorithmResultObject.mResult;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (AbstractAlgorithm aA : getMapOfAlgorithmModules().values()) {
                if (aA.isEnabled()) {
                    try {
                        AlgorithmResultObject algorithmResultObject = aA.processDataRealTime(ojc);
                        if (algorithmResultObject != null) {
                            ojc = (ObjectCluster) algorithmResultObject.mResult;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ojc;
    }

    public void interpretDataPacketFormat() {
    }

    public int getExpectedDataPacketSize() {
        if (mListOfAvailableCommunicationTypes.size() == 0) {
            return 0;
        }
        generateParserMap();
        return getExpectedDataPacketSize(mListOfAvailableCommunicationTypes.get(0));
    }

    public int getExpectedDataPacketSize(COMMUNICATION_TYPE commsType) {
        int dataPacketSize = 0;
        TreeMap<Integer, SensorDetails> parserMapPerCommType = mParserMap.get(commsType);
        if (parserMapPerCommType != null) {
            Iterator<SensorDetails> iterator = parserMapPerCommType.values().iterator();
            while (iterator.hasNext()) {
                SensorDetails sensorDetails = iterator.next();
                int expectedPktSize = sensorDetails.getExpectedDataPacketSize();
                if (expectedPktSize > 0) {
                    dataPacketSize += expectedPktSize;
                }
            }
        }
        return dataPacketSize;
    }

    public byte[] generateUartConfigMessage(UartComponentPropertyDetails cPD) {
        return null;
    }

    public void parseUartConfigResponse(UartComponentPropertyDetails cPD, byte[] response) {
    }

    public int getNumberOfEnabledChannels(COMMUNICATION_TYPE commType) {
        int total = 0;
        Iterator<SensorDetails> iterator = mSensorMap.values().iterator();
        while (iterator.hasNext()) {
            SensorDetails sensorEnabledDetails = iterator.next();
            if (sensorEnabledDetails.isEnabled(commType)) {
                total += sensorEnabledDetails.getNumberOfChannels();
            }
        }
        return total;
    }

    public String getMacIdParsed() {
        String macToUse = getMacId();
        if (macToUse.length() >= 12) {
            macToUse = macToUse.replace(":", "");
            return macToUse.substring(macToUse.length() - 4, macToUse.length());
        }
        return "";
    }

    public String getMacId() {
        return mMacIdFromUart;
    }

    public void setTrialNameAndCheck(String trialName) {
        trialName = trialName.replaceAll(INVALID_TRIAL_NAME_CHAR, "");

        if (trialName.isEmpty()) {
            trialName = DEFAULT_EXPERIMENT_NAME;
        }

        if (trialName.length() > 12) {
            trialName = trialName.substring(0, 11);
        }

        setTrialName(trialName);
    }

    public String getTrialName() {
        return mTrialName;
    }

    public void setTrialName(String trialName) {
        this.mTrialName = trialName;
    }

    public boolean isTrialNameInvalid() {
        return isTrialOrShimmerNameInvalid(getTrialName());
    }

    public boolean isShimmerNameInvalid() {
        return isTrialOrShimmerNameInvalid(getShimmerUserAssignedName())
                || getShimmerUserAssignedName().equals(DEFAULT_SHIMMER_NAME_WITH_ERROR);
    }


    public boolean isShimmerNameValid() {
        return !isShimmerNameInvalid();
    }

    public void setTrialConfig(String trialName, long trialConfigTime) {
        setTrialName(trialName);
        mConfigTime = trialConfigTime;
    }

    public void setFirstDockRead() {
        mShimmerSDCardDetails.setFirstDockRead();
        setConfigurationReadSuccess(false);
        mReadHwFwSuccess = false;
        mReadDaughterIDSuccess = false;
        writeRealWorldClockFromPcTimeSuccess = false;
    }

    public LinkedHashMap<SENSORS, AbstractSensor> getMapOfSensorsClasses() {
        return mMapOfSensorClasses;
    }

    public boolean doesSensorKeyExist(int sensorKey) {
        return (mSensorMap.containsKey(sensorKey));
    }

    public boolean isSensorEnabled(int sensorId) {
        if (mSensorMap != null) {
            SensorDetails sensorDetails = getSensorDetails(sensorId);
            if (sensorDetails != null) {
                return sensorDetails.isEnabled();
            }
        }
        return false;
    }

    public boolean isSensorEnabled(COMMUNICATION_TYPE commType, int sensorKey) {
        SensorDetails sensorDetails = getSensorDetails(sensorKey);
        if (sensorDetails != null) {
            return sensorDetails.isEnabled(commType);
        }
        return false;
    }

    public String getSensorLabel(int sensorId) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            String guiFriendlyLabel = abstractSensor.getSensorGuiFriendlyLabel(sensorId);
            if (guiFriendlyLabel != null) {
                return guiFriendlyLabel;
            }
        }
        return null;
    }

    public List<ShimmerVerObject> getListOfCompatibleVersionInfoForSensor(int sensorId) {
        SensorDetails sensorDetails = getSensorDetails(sensorId);
        if (sensorDetails != null) {
            return sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo;
        }
        return null;
    }

    public Set<Integer> getSensorIdsSet() {
        TreeSet<Integer> setOfSensorIds = new TreeSet<Integer>();
        setOfSensorIds.addAll(mSensorMap.keySet());
        return setOfSensorIds;
    }

    public void setDefaultShimmerConfiguration() {
    }

    public void configBytesParse(byte[] configBytes) {
        configBytesParse(configBytes, COMMUNICATION_TYPE.BLUETOOTH);
    }

    public byte[] configBytesGenerate(boolean generateForWritingToShimmer) {
        return configBytesGenerate(generateForWritingToShimmer, COMMUNICATION_TYPE.BLUETOOTH);
    }

    public Object setConfigValueUsingConfigLabel(String configLabel, Object valueToSet) {
        return setConfigValueUsingConfigLabel("", configLabel, valueToSet);
    }

    public Object setConfigValueUsingConfigLabel(Integer sensorId, String configLabel, Object valueToSet) {
        return setConfigValueUsingConfigLabel(Integer.toString(sensorId), configLabel, valueToSet);
    }

    public Object setConfigValueUsingConfigLabel(String identifier, String configLabel, Object valueToSet) {


        Object returnValue = null;

        returnValue = setSensorClassSetting(identifier, configLabel, valueToSet);
        if (returnValue != null) {
            return returnValue;
        }

        setAlgorithmSettings(identifier, configLabel, valueToSet);

        switch (configLabel) {
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_BOOLEAN):
                setInternalExpPower((boolean) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_INTEGER):
                setInternalExpPower((int) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_USER_ASSIGNED_NAME):
                setShimmerUserAssignedName((String) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.TRIAL_NAME):
                setTrialNameAndCheck((String) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE):
                Double enteredSamplingRate = 1.0;
                if (valueToSet instanceof String) {
                    if (((String) valueToSet).isEmpty()) {
                        enteredSamplingRate = 1.0;
                    } else {
                        enteredSamplingRate = Double.parseDouble((String) valueToSet);
                    }
                } else if (valueToSet instanceof Double) {
                    enteredSamplingRate = (Double) valueToSet;
                }

                setShimmerAndSensorsSamplingRate(enteredSamplingRate);

                returnValue = Double.toString(getSamplingRateShimmer());
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.CALIBRATION_ALL):
                setMapOfSensorCalibrationAll((TreeMap<Integer, TreeMap<Integer, CalibDetails>>) valueToSet);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.CALIBRATION_PER_SENSOR):
                setSensorCalibrationPerSensor(Integer.parseInt(identifier), (TreeMap<Integer, CalibDetails>) valueToSet);
                break;
            default:
                break;
        }


        return returnValue;
    }

    public Object getConfigValueUsingConfigLabel(String configLabel) {
        return getConfigValueUsingConfigLabel("", configLabel);
    }

    public Object getConfigValueUsingConfigLabel(Integer sensorId, String configLabel) {
        return getConfigValueUsingConfigLabel(Integer.toString(sensorId), configLabel);
    }

    public Object getConfigValueUsingConfigLabel(String identifier, String configLabel) {
        Object returnValue = null;

        returnValue = getSensorClassSetting(identifier, configLabel);
        if (returnValue != null) {
            return returnValue;
        }

        returnValue = getAlgorithmSettings(identifier, configLabel);
        if (returnValue != null) {
            return returnValue;
        }

        switch (configLabel) {
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_BOOLEAN):
                returnValue = isInternalExpPower();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.INT_EXP_BRD_POWER_INTEGER):
                returnValue = getInternalExpPower();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_SAMPLING_RATE):
                Double readSamplingRate = getSamplingRateShimmer();
                Double actualSamplingRate = roundSamplingRateToSupportedValue(readSamplingRate, getSamplingClockFreq());
                returnValue = actualSamplingRate.toString();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.SHIMMER_USER_ASSIGNED_NAME):
                returnValue = getShimmerUserAssignedName();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.TRIAL_NAME):
                returnValue = getTrialName();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.CONFIG_TIME):
                returnValue = getConfigTimeParsed();
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.CALIBRATION_PER_SENSOR):
                Integer sensorId = Configuration.Shimmer3.SENSOR_ID.RESERVED_ANY_SENSOR;
                try {
                    sensorId = Integer.parseInt(identifier);
                } catch (NumberFormatException nFE) {
                }

                returnValue = getMapOfSensorCalibrationPerSensor(sensorId);
                break;
            case (Configuration.Shimmer3.GuiLabelConfig.CALIBRATION_ALL):
                returnValue = getMapOfSensorCalibrationAll();
                break;
            default:
                break;
        }

        return returnValue;
    }

    public String getConfigGuiValueUsingConfigLabel(Object sensorId, String configLabel) {
        String guiValue = "";

        Object configValue = null;
        if (sensorId instanceof Integer) {
            configValue = getConfigValueUsingConfigLabel((Integer) sensorId, configLabel);
        } else if (sensorId instanceof String) {
            configValue = getConfigValueUsingConfigLabel((String) sensorId, configLabel);
        }

        if (configValue != null) {
            if (configValue instanceof String) {
                guiValue = (String) configValue;
            } else if (configValue instanceof Boolean) {
                guiValue = configValue.toString();
            } else if (configValue instanceof Integer) {
                int configInt = (int) configValue;
                Map<String, ConfigOptionDetailsSensor> mapOfConfigOptions = getConfigOptionsMap();
                if (mapOfConfigOptions != null && mapOfConfigOptions.containsKey(configLabel) && mapOfConfigOptions.get(configLabel) != null) {
                    ConfigOptionDetails configOption = getConfigOptionsMap().get(configLabel);

                    guiValue = configOption.getConfigStringFromConfigValue(configInt);

                } else {
                    guiValue = Integer.toString(configInt);
                }

            }
        }

        return guiValue;
    }

    private Object setSensorClassSetting(String identifier, String configLabel, Object valueToSet) {
        Object returnValue = null;
        Integer sensorId = Configuration.Shimmer3.SENSOR_ID.RESERVED_ANY_SENSOR;
        try {
            sensorId = Integer.parseInt(identifier);
        } catch (NumberFormatException eFE) {
        }
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
            returnValue = abstractSensor.setConfigValueUsingConfigLabel(sensorId, configLabel, valueToSet);
            if (returnValue != null) {
                return returnValue;
            }
        }
        return returnValue;
    }

    private Object getSensorClassSetting(String identifier, String configLabel) {
        Object returnValue = null;
        Integer sensorId = Configuration.Shimmer3.SENSOR_ID.RESERVED_ANY_SENSOR;
        try {
            sensorId = Integer.parseInt(identifier);
        } catch (NumberFormatException eFE) {
        }
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
            returnValue = abstractSensor.getConfigValueUsingConfigLabel(sensorId, configLabel);
            if (returnValue != null) {
                return returnValue;
            }
        }
        return returnValue;
    }

    public void setAlgorithmSettings(String configLabel, Object valueToSet) {
        setAlgorithmSettings(null, configLabel, valueToSet);
    }

    public void setAlgorithmSettings(String groupName, String configLabel, Object valueToSet) {
        List<AbstractAlgorithm> listOfAlgorithms = null;

        if ((groupName != null && !groupName.isEmpty())) {
            listOfAlgorithms = getListOfAlgorithmModulesPerGroup(groupName);
        } else {
            listOfAlgorithms = getListOfAlgorithmModules();
        }

        if (listOfAlgorithms != null && !listOfAlgorithms.isEmpty()) {
            for (AbstractAlgorithm abstractAlgorithm : listOfAlgorithms) {
                abstractAlgorithm.setSettings(configLabel, valueToSet);
            }
        }
    }

    public Object getAlgorithmSettings(String groupName, String configLabel) {
        List<AbstractAlgorithm> listOfAlgorithms = null;
        if (groupName.isEmpty()) {
            listOfAlgorithms = getListOfAlgorithmModules();
        } else {
            listOfAlgorithms = getListOfAlgorithmModulesPerGroup(groupName);
        }

        Object returnValue = null;
        if (listOfAlgorithms != null && !listOfAlgorithms.isEmpty()) {
            for (AbstractAlgorithm abstractAlgorithm : listOfAlgorithms) {
                returnValue = abstractAlgorithm.getSettings(configLabel);
                if (returnValue != null) {
                    return returnValue;
                }
            }
        }
        return returnValue;
    }

    public boolean isSupportedRtcConfigViaUart() {
        return mShimmerVerObject.isSupportedRtcConfigViaUart();
    }

    public boolean isSupportedConfigViaUart() {
        return mShimmerVerObject.isSupportedConfigViaUart();
    }

    public boolean isSupportedSdCardAccess() {
        return mShimmerVerObject.isSupportedSdCardAccess();
    }

    public boolean isSupportedBluetooth() {
        return mShimmerVerObject.isSupportedBluetooth();
    }

    public boolean isSupportedCalibDump() {
        return mShimmerVerObject.isSupportedCalibDump();
    }

    public boolean isShimmerGen2() {
        return mShimmerVerObject.isShimmerGen2();
    }

    public boolean isShimmerGen3() {
        return mShimmerVerObject.isShimmerGen3();
    }

    public boolean isShimmerGen3R() {
        return mShimmerVerObject.isShimmerGen3R();
    }

    public boolean isShimmerGen3or3R() {
        return mShimmerVerObject.isShimmerGen3() || mShimmerVerObject.isShimmerGen3R();
    }

    public boolean isShimmerGen4() {
        return mShimmerVerObject.isShimmerGen4();
    }

    public boolean isShimmerGenGq() {
        return mShimmerVerObject.isShimmerGenGq();
    }

    public boolean isShimmerVideoDevice() {
        return mShimmerVerObject.isShimmerVideoDevice();
    }

    public boolean isSupportedSrProgViaDock() {
        if (mShimmerVerObject.compareVersions(HW_ID.SHIMMER_3, FW_ID.BTSTREAM, 0, 7, 13)
                || mShimmerVerObject.compareVersions(HW_ID.SHIMMER_3, FW_ID.LOGANDSTREAM, 0, 8, 1)
                || isShimmerGen3R()) {
            return true;
        }
        return false;
    }

    public boolean isSupportedSdLogSync() {
        if (getFirmwareIdentifier() == ShimmerVerDetails.FW_ID.SDLOG
                || (UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3RLogAndStream))
                || (UtilShimmer.compareVersions(getShimmerVerObject(), Configuration.Shimmer3.CompatibilityInfoForMaps.svoShimmer3LogAndStreamWithSDLogSyncSupport))
                || getFirmwareIdentifier() == ShimmerVerDetails.FW_ID.STROKARE) {
            return true;
        }
        return false;
    }

    public boolean isHWAndFWSupportedBtBleControl() {
        if ((isShimmerGen3() && getFirmwareIdentifier() == ShimmerVerDetails.FW_ID.LOGANDSTREAM
                && mShimmerVerObject.compareVersions(HW_ID.SHIMMER_3, FW_ID.LOGANDSTREAM, 1, 0, 4))
                || (isShimmerGen3R() && getFirmwareIdentifier() == ShimmerVerDetails.FW_ID.LOGANDSTREAM
                && mShimmerVerObject.compareVersions(HW_ID.SHIMMER_3R, FW_ID.LOGANDSTREAM, 1, 0, 40))) {
            return true;
        }
        return false;
    }

    public boolean isLegacySdLog() {
        if (getFirmwareIdentifier() == FW_ID.SDLOG && getFirmwareVersionMajor() == 0 && getFirmwareVersionMinor() == 5) {
            return true;
        }
        return false;
    }

    public boolean isSupportedDerivedSensors() {
        return isSupportedDerivedSensors(mShimmerVerObject);
    }

    public boolean isSupportedNoImuSensors() {
        return isSupportedNoImuSensors(getShimmerVerObject(), getExpansionBoardDetails());
    }

    public boolean isVerCompatibleWithAnyOf(List<ShimmerVerObject> listOfCompatibleVersionInfo) {

        if (listOfCompatibleVersionInfo == null || listOfCompatibleVersionInfo.isEmpty()) {
            return true;
        }

        for (ShimmerVerObject compatibleVersionInfo : listOfCompatibleVersionInfo) {
            int expBrdIdToCompare = compatibleVersionInfo.getShimmerExpansionBoardId();
            if (expBrdIdToCompare != ShimmerVerDetails.ANY_VERSION) {
                if (expBrdIdToCompare != getExpansionBoardId()) {
                    continue;
                }

                int expBrdRevToCompare = compatibleVersionInfo.getShimmerExpansionBoardRev();
                if (expBrdRevToCompare != ShimmerVerDetails.ANY_VERSION) {
                    if (expBrdRevToCompare > getExpansionBoardRev()) {
                        continue;
                    }
                }
            } else {
            }

            if (isThisVerCompatibleWith(
                    compatibleVersionInfo.mHardwareVersion,
                    compatibleVersionInfo.mFirmwareIdentifier,
                    compatibleVersionInfo.mFirmwareVersionMajor,
                    compatibleVersionInfo.mFirmwareVersionMinor,
                    compatibleVersionInfo.mFirmwareVersionInternal)) {
                return true;
            }

        }
        return false;
    }

    public boolean isThisVerCompatibleWith(int firmwareIdentifier, int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        return UtilShimmer.compareVersions(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(),
                firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
    }

    public boolean isThisVerCompatibleWith(int hardwareVersion, int firmwareIdentifier, int firmwareVersionMajor, int firmwareVersionMinor, int firmwareVersionInternal) {
        return UtilShimmer.compareVersions(getHardwareVersion(), getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(),
                hardwareVersion, firmwareIdentifier, firmwareVersionMajor, firmwareVersionMinor, firmwareVersionInternal);
    }


    public void consolePrintExeptionLn(String message, StackTraceElement[] stackTrace) {
        if (mVerboseMode) {
            consolePrintErrLn(message + "\n" + UtilShimmer.convertStackTraceToString(stackTrace));
        }
    }

    public void consolePrintErrLn(String message) {
        if (mVerboseMode) {
            System.err.println(assemblePrintString(message));
        }
    }

    public void consolePrintLn(String message) {
        if (mVerboseMode) {
            System.out.println(assemblePrintString(message));
        }
    }

    public void consolePrint(String message) {
        if (mVerboseMode) {
            System.out.print(message);
        }
    }

    private String assemblePrintString(String message) {
        Calendar rightNow = Calendar.getInstance();
        String rightNowString = "[" + String.format("%02d", rightNow.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%02d", rightNow.get(Calendar.MINUTE))
                + ":" + String.format("%02d", rightNow.get(Calendar.SECOND))
                + ":" + String.format("%03d", rightNow.get(Calendar.MILLISECOND)) + "]";
        return (rightNowString
                + " " + getClass().getSimpleName()
                + " (Mac:" + getMacIdParsed()
                + " HashCode:" + Integer.toHexString(this.hashCode()) + ")"
                + ": " + message);
    }

    public void setVerboseMode(boolean verboseMode) {
        mVerboseMode = verboseMode;
    }

    protected void checkIfInternalExpBrdPowerIsNeeded() {
        for (SensorDetails sensorEnabledDetails : mSensorMap.values()) {
            if (sensorEnabledDetails.isInternalExpBrdPowerRequired()) {
                mInternalExpPower = 1;
                return;
            } else {
                if (isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A1)
                        || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A12)
                        || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A13)
                        || isSensorEnabled(Configuration.Shimmer3.SENSOR_ID.SHIMMER_INT_EXP_ADC_A14)) {

                } else {
                    mInternalExpPower = 0;
                }
            }
        }
    }

    public boolean isInternalExpPower() {
        return (mInternalExpPower > 0) ? true : false;
    }

    public int getInternalExpPower() {
        return mInternalExpPower;
    }

    public void setInternalExpPower(int state) {
        this.mInternalExpPower = state;
    }

    public void setInternalExpPower(boolean state) {
        setInternalExpPower(state ? 1 : 0);
    }

    public LinkedHashMap<Integer, SensorDetails> getSensorMap() {
        return mSensorMap;
    }

    public void setSensorIdsEnabled(Integer[] sensorIds) {
        for (Integer sensorId : mSensorMap.keySet()) {
            setSensorEnabledState(sensorId, false);
        }

        for (Integer sensorId : sensorIds) {
            setSensorEnabledState(sensorId, true);
        }
    }

    public boolean setSensorEnabledState(int sensorId, boolean state) {
        if (mSensorMap != null) {
            sensorId = handleSpecCasesBeforeSetSensorState(sensorId, state);

            SensorDetails sensorDetails = getSensorDetails(sensorId);
            if (sensorDetails != null) {
                List<Integer> listOfRequiredKeys = sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired;
                if (listOfRequiredKeys != null && listOfRequiredKeys.size() > 0) {
                    for (Integer i : listOfRequiredKeys) {
                        getSensorDetails(i).setIsEnabled(state);
                    }
                }

                sensorDetails.setIsEnabled(state);

                setSensorEnabledStateCommon(sensorId, sensorDetails.isEnabled());

                boolean result = sensorDetails.isEnabled();
                boolean successfullySet = result == state ? true : false;
                if (!successfullySet) {
                    consolePrintLn("WARNING!!! Failed to setSensorEnabledState for sensor:\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                    UtilShimmer.consolePrintCurrentStackTrace();
                }
                return successfullySet;
            } else {
                consolePrintLn("WARNING!!! SensorID not found:" + sensorId);
                UtilShimmer.consolePrintCurrentStackTrace();
            }
            return false;
        } else {
            consolePrintLn("setSensorEnabledState:\t SensorMap=null");
            return false;
        }
    }

    public boolean setSensorEnabledState(int sensorId, boolean state, COMMUNICATION_TYPE commType) {
        if (mSensorMap != null) {
            sensorId = handleSpecCasesBeforeSetSensorState(sensorId, state);

            SensorDetails sensorDetails = getSensorDetails(sensorId);
            if (sensorDetails != null) {

                List<Integer> listOfRequiredKeys = sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired;
                if (listOfRequiredKeys != null && listOfRequiredKeys.size() > 0) {
                    for (Integer i : listOfRequiredKeys) {
                        getSensorDetails(i).setIsEnabled(commType, state);
                    }
                }

                sensorDetails.setIsEnabled(commType, state);

                setSensorEnabledStateCommon(sensorId, sensorDetails.isEnabled(commType));

                boolean result = sensorDetails.isEnabled(commType);
                boolean successfullySet = result == state ? true : false;
                if (!successfullySet) {
                    consolePrintErrLn("Failed to setSensorEnabledState for sensor:\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                }
                return successfullySet;
            }
            return false;
        } else {
            consolePrintLn("setSensorEnabledState:\t SensorMap=null");
            return false;
        }
    }


    private void setSensorEnabledStateCommon(int sensorId, boolean state) {
        sensorMapConflictCheckandCorrect(sensorId);
        setDefaultConfigForSensor(sensorId, state);

        checkIfInternalExpBrdPowerIsNeeded();

        refreshEnabledSensorsFromSensorMap();

        generateParserMap();
        algorithmRequiredSensorCheck();

    }

    protected void setSensorEnabledStateBasic(int sensorId, boolean state) {
        SensorDetails sensorDetails = mSensorMap.get(sensorId);
        if (sensorDetails != null) {
            sensorDetails.setIsEnabled(state);
        }
    }

    public int handleSpecCasesBeforeSetSensorState(int sensorId, boolean state) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            int newSensorId = abstractSensor.handleSpecCasesBeforeSetSensorState(sensorId, state);
            if (newSensorId != sensorId) {
                return newSensorId;
            }
        }
        return sensorId;
    }

    public boolean isTimestampEnabled() {
        SensorDetails sensorDetails = getSensorDetails(Configuration.Shimmer3.SENSOR_ID.SHIMMER_TIMESTAMP);
        if (sensorDetails != null) {
            return sensorDetails.isEnabled();
        }
        return false;
    }

    protected void sensorMapConflictCheckandCorrect(int originalSensorId) {
        SensorDetails sdOriginal = getSensorDetails(originalSensorId);
        if (sdOriginal != null) {
            if (sdOriginal.mSensorDetailsRef.mListOfSensorIdsConflicting != null) {
                for (Integer sensorIdConflicting : sdOriginal.mSensorDetailsRef.mListOfSensorIdsConflicting) {
                    SensorDetails sdConflicting = getSensorDetails(sensorIdConflicting);
                    if (sdConflicting != null) {
                        sdConflicting.setIsEnabled(false);
                        if (sdConflicting.isDerivedChannel()) {
                            mDerivedSensors &= ~sdConflicting.mDerivedSensorBitmapID;
                        }
                        setDefaultConfigForSensor(sensorIdConflicting, sdConflicting.isEnabled());
                    }
                }
            }
        }

        sensorMapCheckandCorrectSensorDependencies();
        sensorMapCheckandCorrectHwDependencies();
    }


    protected void sensorMapCheckandCorrectSensorDependencies() {
        for (Integer sensorId : mSensorMap.keySet()) {
            SensorDetails sensorDetails = getSensorDetails(sensorId);
            if (sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired != null) {
                for (Integer requiredSensorKey : sensorDetails.mSensorDetailsRef.mListOfSensorIdsRequired) {
                    if (!getSensorDetails(requiredSensorKey).isEnabled()) {
                        sensorDetails.setIsEnabled(false);
                        if (sensorDetails.isDerivedChannel()) {
                            mDerivedSensors &= ~sensorDetails.mDerivedSensorBitmapID;
                        }
                        setDefaultConfigForSensor(sensorId, sensorDetails.isEnabled());
                        break;
                    }
                }
            }
        }
    }

    protected void sensorMapCheckandCorrectHwDependencies() {
        for (Integer sensorId : mSensorMap.keySet()) {
            SensorDetails sensorDetails = getSensorDetails(sensorId);
            if (sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo != null) {
                if (!isVerCompatibleWithAnyOf(sensorDetails.mSensorDetailsRef.mListOfCompatibleVersionInfo)) {
                    sensorDetails.setIsEnabled(false);
                    if (sensorDetails.isDerivedChannel()) {
                        mDerivedSensors &= ~sensorDetails.mDerivedSensorBitmapID;
                    }
                    setDefaultConfigForSensor(sensorId, sensorDetails.isEnabled());
                }
            }
        }
    }

    protected void setDefaultConfigForSensor(int sensorId, boolean isSensorEnabled) {
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {
            if (abstractSensor.setDefaultConfigForSensor(sensorId, isSensorEnabled)) {
                break;
            }
        }
    }

    public long getEnabledSensors() {
        return mEnabledSensors;
    }

    /**
     * This method has been deprecated, and we recommend users to use either {@link com.shimmerresearch.driver.ShimmerDevice#setSensorEnabledState(int sensorId, boolean state)}
     * <br> or {@link com.shimmerresearch.driver.ShimmerDevice#setSensorIdsEnabled(Integer[] sensorIds)}.
     * <p>
     * The enabled sensors that are set in the ShimmerDevice class can then be written to the physical device by the following methods:<br>
     * A) Clone device - Create a virtual representation of a Shimmer device by calling deepClone(). Update the sensor states and/or other desired settings on the clone device.
     * Call {@link AssembleShimmerConfig} to generate a Shimmer config for the clone. Then call configureShimmer(clone) from ShimmerBluetoothManager to write the clone settings to the physical device.
     * <p> B) Call {@link #writeConfigBytes()} after changing the sensor states.
     *
     * @param mEnabledSensors
     */
    @Deprecated
    public void setEnabledSensors(long mEnabledSensors) {
        this.mEnabledSensors = mEnabledSensors;
    }

    public long getDerivedSensors() {
        return mDerivedSensors;
    }

    public void setDerivedSensors(long mDerivedSensors) {
        this.mDerivedSensors = mDerivedSensors;
    }

    public void setEnabledAndDerivedSensorsAndUpdateMaps(long enabledSensors, long derivedSensors) {
        setEnabledAndDerivedSensorsAndUpdateMaps(enabledSensors, derivedSensors, null);
    }

    public void setEnabledAndDerivedSensorsAndUpdateMaps(long enabledSensors, long derivedSensors, COMMUNICATION_TYPE commsType) {
        setEnabledSensors(enabledSensors);
        setDerivedSensors(derivedSensors);
        sensorMapUpdateFromEnabledSensorsVars(commsType);
        algorithmMapUpdateFromEnabledSensorsVars();

        setSamplingRateSensors(getSamplingRateShimmer());
        setSamplingRateAlgorithms(getSamplingRateShimmer());

        generateParserMap();
    }

    protected void handleSpecialCasesAfterSensorMapCreate() {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.handleSpecialCasesAfterSensorMapCreate();
        }
    }


    public void refreshEnabledSensorsFromSensorMap() {
        if (mSensorMap != null) {


            if (!isShimmerGen2()) {
                long enabledSensors = 0;
                sensorMapCheckandCorrectHwDependencies();
                List<SensorDetails> listOfEnabledSensors = getListOfEnabledSensors();
                for (SensorDetails sED : listOfEnabledSensors) {
                    enabledSensors |= sED.mSensorDetailsRef.mSensorBitmapIDSDLogHeader;
                }
                setEnabledSensors(enabledSensors);

                updateDerivedSensors();

                handleSpecCasesUpdateEnabledSensors();
            }
        }
    }

    public void printSensorParserAndAlgoMaps() {
        consolePrintLn("");
        consolePrintLn("Enabled Sensors\t" + UtilShimmer.longToHexStringWithSpacesFormatted(mEnabledSensors, 5));
        consolePrintLn("Derived Sensors\t" + UtilShimmer.longToHexStringWithSpacesFormatted(mDerivedSensors, 8));

        consolePrintLn("SENSOR MAP");
        for (SensorDetails sensorDetails : mSensorMap.values()) {
            consolePrintLn("\tSENSOR\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel + "\tIsEnabled:\t" + sensorDetails.isEnabled());
            if (sensorDetails.mListOfChannels.size() == 0) {
                consolePrintLn("\t\t" + "Channels Missing!");
            } else {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    consolePrintLn("\t\tChannel:\t Channel:" + channelDetails.getChannelObjectClusterName() + "\tDbName:" + channelDetails.getDatabaseChannelHandle());
                }
            }
        }
        consolePrintLn("");

        consolePrintLn("PARSER MAP" + "\tSize=" + mParserMap.keySet().size());
        for (COMMUNICATION_TYPE commType : mParserMap.keySet()) {
            consolePrintLn("PARSER MAP\tCOMM TYPE:\t" + commType);
            for (SensorDetails sensorDetails : mParserMap.get(commType).values()) {
                consolePrintLn("\tSENSOR\t" + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                if (sensorDetails.mListOfChannels.size() == 0) {
                    consolePrintLn("\t\t" + "Channels Missing!");
                } else {
                    for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                        consolePrintLn("\t\tNumBytes:" + channelDetails.mDefaultNumBytes + "\tChannel:" + channelDetails.getChannelObjectClusterName() + "\tDbName:" + channelDetails.getDatabaseChannelHandle());
                    }
                }
            }
            consolePrintLn("");
        }
        consolePrintLn("");

        consolePrintLn("ALGO MAP");
        List<AbstractAlgorithm> mapOfEnabledAlgoModules = getListOfAlgorithmModules();
        for (AbstractAlgorithm abstractAlgorithm : mapOfEnabledAlgoModules) {
            consolePrintLn("\tALGO\t" + abstractAlgorithm.getAlgorithmName() + "\tIsEnabled:\t" + abstractAlgorithm.isEnabled());
            List<ChannelDetails> listOfChannelDetails = abstractAlgorithm.getChannelDetails();
            for (ChannelDetails channelDetails : listOfChannelDetails) {
                consolePrintLn("\t\tChannel:\t" + channelDetails.getChannelObjectClusterName() + "\tDbName:" + channelDetails.getDatabaseChannelHandle());
            }
        }
        consolePrintLn("");

        LinkedHashMap<COMMUNICATION_TYPE, List<ChannelDetails>> mapOfOjcChannels = generateObjectClusterIndexes();
        consolePrintLn("\tObjectClusterIndexes:");
        for (COMMUNICATION_TYPE commType : mapOfOjcChannels.keySet()) {
            consolePrintLn("\tComm Type: " + commType);
            List<ChannelDetails> listOfOjcChannels = mapOfOjcChannels.get(commType);
            for (int i = 0; i < listOfOjcChannels.size(); i++) {
                consolePrintLn("\t\t" + i + "\t" + listOfOjcChannels.get(i).mObjectClusterName);
            }
        }

        consolePrintLn("");
    }

    public LinkedHashMap<COMMUNICATION_TYPE, List<ChannelDetails>> generateObjectClusterIndexes() {
        LinkedHashMap<COMMUNICATION_TYPE, List<ChannelDetails>> mapOfOjcChannels = new LinkedHashMap<COMMUNICATION_TYPE, List<ChannelDetails>>();

        for (COMMUNICATION_TYPE commType : mParserMap.keySet()) {
            List<ChannelDetails> listOfOjcChannels = new ArrayList<ChannelDetails>();

            TreeMap<Integer, SensorDetails> mapPerCommType = mParserMap.get(commType);
            for (SensorDetails sensorDetails : mapPerCommType.values()) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    listOfOjcChannels.add(channelDetails);
                }
            }

            List<AbstractAlgorithm> mapOfEnabledAlgoModules = getListOfEnabledAlgorithmModules();
            for (AbstractAlgorithm abstractAlgorithm : mapOfEnabledAlgoModules) {
                List<ChannelDetails> listOfChannelDetails = abstractAlgorithm.getChannelDetails();
                for (ChannelDetails channelDetails : listOfChannelDetails) {
                    listOfOjcChannels.add(channelDetails);
                }
            }
            mapOfOjcChannels.put(commType, listOfOjcChannels);
        }

        return mapOfOjcChannels;
    }

    public void handleSpecCasesUpdateEnabledSensors() {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.handleSpecCasesUpdateEnabledSensors();
        }
    }


    public List<SensorDetails> getListOfEnabledSensors() {
        List<SensorDetails> listOfEnabledSensors = new ArrayList<SensorDetails>();
        for (SensorDetails sED : mSensorMap.values()) {
            if (sED.isEnabled()) {
                listOfEnabledSensors.add(sED);
            }
        }
        return listOfEnabledSensors;
    }

    public void algorithmMapUpdateFromEnabledSensorsVars() {
        for (AbstractAlgorithm aA : getMapOfAlgorithmModules().values()) {
            if (mEnableDerivedSensors) {
                aA.algorithmMapUpdateFromEnabledSensorsVars(mDerivedSensors);
            } else {
                aA.algorithmMapUpdateFromEnabledSensorsVars(0);
            }
        }
        initializeAlgorithms();
    }


    public void sensorMapUpdateFromEnabledSensorsVars() {
        sensorMapUpdateFromEnabledSensorsVars(null);
    }

    public void sensorMapUpdateFromEnabledSensorsVars(COMMUNICATION_TYPE commType) {
        handleSpecCasesBeforeSensorMapUpdateGeneral();

        if (mSensorMap == null) {
            sensorAndConfigMapsCreate();
        }

        if (mSensorMap != null) {
            mapLoop:
            for (Integer sensorId : mSensorMap.keySet()) {
                if (handleSpecCasesBeforeSensorMapUpdatePerSensor(sensorId)) {
                    continue mapLoop;
                }

                SensorDetails sensorDetails = getSensorDetails(sensorId);
                sensorDetails.updateFromEnabledSensorsVars(mEnabledSensors, mDerivedSensors);
            }

            handleSpecCasesAfterSensorMapUpdateFromEnabledSensors();
        }

    }

    public void handleSpecCasesBeforeSensorMapUpdateGeneral() {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.handleSpecCasesBeforeSensorMapUpdateGeneral(this);
        }
    }

    public boolean handleSpecCasesBeforeSensorMapUpdatePerSensor(Integer sensorId) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            if (abstractSensor.handleSpecCasesBeforeSensorMapUpdatePerSensor(this, sensorId)) {
                return true;
            }
        }
        return false;
    }

    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.handleSpecCasesAfterSensorMapUpdateFromEnabledSensors();
        }
    }


    public List<Integer> sensorMapConflictCheck(Integer key) {
        List<Integer> listOfChannelConflicts = new ArrayList<Integer>();


        if (getHardwareVersion() == HW_ID.SHIMMER_3 || getHardwareVersion() == HW_ID.SHIMMER_4_SDK || getHardwareVersion() == HW_ID.SHIMMER_3R) {
            if (getSensorDetails(key).mSensorDetailsRef.mListOfSensorIdsConflicting != null) {
                for (Integer sensorId : getSensorDetails(key).mSensorDetailsRef.mListOfSensorIdsConflicting) {
                    if (isSensorEnabled(sensorId)) {
                        listOfChannelConflicts.add(sensorId);
                    }
                }
            }
        }

        if (listOfChannelConflicts.isEmpty()) {
            return null;
        } else {
            return listOfChannelConflicts;
        }
    }

    public void checkShimmerConfigBeforeConfiguring() {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.checkShimmerConfigBeforeConfiguring();
        }

        checkIfInternalExpBrdPowerIsNeeded();
    }


    public byte[] refreshShimmerInfoMemBytes() {
        return configBytesGenerate(false);
    }

    public void checkAndCorrectShimmerName(String shimmerName) {
        if (!shimmerName.isEmpty()) {
            mShimmerUserAssignedName = new String(shimmerName);
        } else {
            mShimmerUserAssignedName = DEFAULT_SHIMMER_NAME + "_" + getMacIdFromUartParsed();
        }
    }


    public void checkConfigOptionValues(String stringKey) {
        for (AbstractSensor abstractSensor : mMapOfSensorClasses.values()) {

            if (abstractSensor.checkConfigOptionValues(stringKey)) {
                break;
            }

        }
    }

    public boolean isSensorUsingDefaultCal(int sensorId) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            SensorDetails sensorDetails = abstractSensor.getSensorDetails(sensorId);
            if (sensorDetails != null) {
                return abstractSensor.isSensorUsingDefaultCal(sensorId);
            }
        }
        return false;
    }

    public boolean setBluetoothRadioState(BT_STATE state) {
        boolean changed = true;
        if (mBluetoothRadioState.toString().equals(state.toString())) {
            changed = false;
        }
        BT_STATE stateStored = mBluetoothRadioState;
        mBluetoothRadioState = state;
        consolePrintLn("State change: Was:" + stateStored.toString() + "\tIs now:" + mBluetoothRadioState);
        if (!mUpdateOnlyWhenStateChanges) {
            return true;
        }
        return changed;
    }


    public BT_STATE getBluetoothRadioState() {
        return mBluetoothRadioState;
    }

    public String getBluetoothRadioStateString() {
        return mBluetoothRadioState.toString();
    }

    public void connect() throws ShimmerException {

    }

    public void disconnect() throws ShimmerException {
        stopStreaming();
    }

    public boolean ignoreAndDisable(Integer sensorId) {
        SensorDetails sensorDetails = getSensorDetails(sensorId);
        if (sensorDetails != null) {
            for (Integer conflictKey : sensorDetails.mSensorDetailsRef.mListOfSensorIdsConflicting) {
                SensorDetails conflictingSensor = getSensorDetails(conflictKey);
                if (conflictingSensor != null) {
                    if (conflictingSensor.isDerivedChannel()) {
                        if ((mDerivedSensors & conflictingSensor.mDerivedSensorBitmapID) > 0) {
                            sensorDetails.setIsEnabled(false);
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }


    public void updateDerivedSensors() {
        setDerivedSensors(0);

        updateDerivedSensorsFromSensorMap();
        updateDerivedSensorsFromAlgorithmMap();
    }

    private void updateDerivedSensorsFromSensorMap() {
        List<SensorDetails> listOfEnabledSensors = getListOfEnabledSensors();
        for (SensorDetails sED : listOfEnabledSensors) {
            if (sED.isDerivedChannel()) {
                mDerivedSensors |= sED.mDerivedSensorBitmapID;
            }
        }
    }

    private void updateDerivedSensorsFromAlgorithmMap() {
        List<AbstractAlgorithm> listOfEnabledAlgorithms = getListOfEnabledAlgorithmModules();
        for (AbstractAlgorithm aA : listOfEnabledAlgorithms) {
            mDerivedSensors |= aA.getDerivedSensorBitmapID();
        }
    }

    public List<AlgorithmDetails> getListOfEnabledAlgorithmDetails() {
        List<AlgorithmDetails> listOfEnabledAlgorithms = new ArrayList<AlgorithmDetails>();
        for (AbstractAlgorithm aa : getListOfEnabledAlgorithmModules()) {
            listOfEnabledAlgorithms.add(aa.mAlgorithmDetails);
        }
        return listOfEnabledAlgorithms;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModules() {
        List<AbstractAlgorithm> listOfEnabledAlgorithms = new ArrayList<AbstractAlgorithm>();
        for (AbstractAlgorithm aa : mMapOfAlgorithmModules.values()) {
            if (aa.isEnabled()) {
                listOfEnabledAlgorithms.add(aa);
            }
        }
        return listOfEnabledAlgorithms;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModules() {
        return new ArrayList<AbstractAlgorithm>(mMapOfAlgorithmModules.values());
    }

    public void setIsAlgorithmEnabledAndSyncGroup(String algorithmName, String groupName, boolean state) {
        AbstractAlgorithm abstractAlgorithmToChange = getMapOfAlgorithmModules().get(algorithmName);
        if (abstractAlgorithmToChange != null) {
            HashMap<String, Object> mapOfAlgoSettings = syncAlgoGroupConfig(groupName, state);

            setIsAlgorithmEnabled(algorithmName, state);

            if (state && mapOfAlgoSettings != null) {
                abstractAlgorithmToChange.setAlgorithmSettings(mapOfAlgoSettings);
            }

            List<AbstractAlgorithm> listOfEnabledAlgosForGroup = getListOfEnabledAlgorithmModulesPerGroup(groupName);
            if ((listOfEnabledAlgosForGroup.size() == 0)
                    || (state && listOfEnabledAlgosForGroup.size() == 1)) {
                setDefaultSettingsForAlgorithmGroup(groupName);
            }

            syncAlgoGroupConfig(groupName, state);
        }
    }

    public void setIsAlgorithmEnabled(String algorithmName, boolean state) {
        AbstractAlgorithm abstractAlgorithm = mMapOfAlgorithmModules.get(algorithmName);

        if (abstractAlgorithm != null && abstractAlgorithm.mAlgorithmDetails != null) {
            if (state) {
                for (Integer sensorId : abstractAlgorithm.mAlgorithmDetails.mListOfRequiredSensors) {
                    setSensorEnabledState(sensorId, true);
                }
            }
            abstractAlgorithm.setIsEnabled(state);
        } else {
            consolePrintErrLn(algorithmName + " doesn't exist in this device");
        }
        updateDerivedSensors();
        initializeAlgorithms();
    }

    public void disableAllAlgorithms() {
        for (AbstractAlgorithm abstractAlgorithmToChange : getMapOfAlgorithmModules().values()) {
            setIsAlgorithmEnabled(abstractAlgorithmToChange.getAlgorithmName(), false);
        }
    }

    public boolean isECGAlgoEnabled(AbstractAlgorithm abstractAlgorithm) {
        SensorDetails ecgSensorDetails = getSensorDetails(Configuration.Shimmer3.SENSOR_ID.HOST_ECG);
        SensorDetails respSensorDetails = getSensorDetails(Configuration.Shimmer3.SENSOR_ID.HOST_EXG_RESPIRATION);
        if ((ecgSensorDetails.isEnabled() || respSensorDetails.isEnabled()) && abstractAlgorithm.mAlgorithmName.contains("ECGtoHR")) {
            return true;
        }
        return false;
    }

    public void algorithmRequiredSensorCheck() {
        for (SensorGroupingDetails sGD : mMapOfAlgorithmGrouping.values()) {
            for (AlgorithmDetails algorithmDetails : sGD.mListOfAlgorithmDetails) {
                AbstractAlgorithm abstractAlgorithm = mMapOfAlgorithmModules.get(algorithmDetails.mAlgorithmName);
                if (abstractAlgorithm != null && abstractAlgorithm.isEnabled()) {
                    innerLoop:
                    for (Integer sensor : abstractAlgorithm.mAlgorithmDetails.mListOfRequiredSensors) {
                        if (isECGAlgoEnabled(abstractAlgorithm)) {
                            break innerLoop;
                        }
                        SensorDetails sensorDetails = getSensorDetails(sensor);
                        if (sensorDetails != null && !sensorDetails.isEnabled()) {
                            setIsAlgorithmEnabledAndSyncGroup(abstractAlgorithm.mAlgorithmName, sGD.mGroupName, false);
                            break innerLoop;
                        }
                    }
                }
            }
        }
        initializeAlgorithms();
    }


    protected List<String[]> getListofEnabledAlgorithmsSignalsandFormats() {
        List<String[]> listAlgoSignalProperties = new ArrayList<String[]>();
        List<AlgorithmDetails> listOfEnabledAlgorithms = getListOfEnabledAlgorithmDetails();
        for (AlgorithmDetails aD : listOfEnabledAlgorithms) {
            String[] signalStringArray = aD.getSignalStringArray();
            signalStringArray[0] = mShimmerUserAssignedName;
            listAlgoSignalProperties.add(signalStringArray);
        }
        return listAlgoSignalProperties;
    }

    public Map<String, AbstractAlgorithm> getSupportedAlgorithmChannels() {
        Map<String, AbstractAlgorithm> mSupportedAlgorithmChannelsMap = new LinkedHashMap<String, AbstractAlgorithm>();
        for (AbstractAlgorithm aA : mMapOfAlgorithmModules.values()) {
            for (Integer sensorId : aA.mAlgorithmDetails.mListOfRequiredSensors) {
                if (mSensorMap.containsKey(sensorId)) {
                    mSupportedAlgorithmChannelsMap.put(aA.getAlgorithmName(), aA);
                }
            }
        }
        return mSupportedAlgorithmChannelsMap;
    }

    public TreeMap<Integer, SensorGroupingDetails> getMapOfAlgorithmGrouping() {
        TreeMap<Integer, SensorGroupingDetails> algorithmGroupingMap = new TreeMap<Integer, SensorGroupingDetails>();
        for (AbstractAlgorithm abstractAlgorithm : mMapOfAlgorithmModules.values()) {
            algorithmGroupingMap.putAll(abstractAlgorithm.mMapOfAlgorithmGrouping);
        }
        mMapOfAlgorithmGrouping = algorithmGroupingMap;
        return mMapOfAlgorithmGrouping;
    }

    public TreeMap<Integer, SensorGroupingDetails> getMapOfAlgorithmGroupingEnabled() {
        TreeMap<Integer, SensorGroupingDetails> algorithmGroupingMap = new TreeMap<Integer, SensorGroupingDetails>();
        for (AbstractAlgorithm abstractAlgorithm : getListOfEnabledAlgorithmModules()) {
            algorithmGroupingMap.putAll(abstractAlgorithm.mMapOfAlgorithmGrouping);
        }
        mMapOfAlgorithmGrouping = algorithmGroupingMap;
        return mMapOfAlgorithmGrouping;
    }


    protected void generateMapOfAlgorithmConfigOptions() {
        mConfigOptionsMapAlgorithms = new HashMap<String, ConfigOptionDetails>();
        for (AbstractAlgorithm abstractAlgorithm : mMapOfAlgorithmModules.values()) {
            HashMap<String, ConfigOptionDetails> configOptionsMapPerAlgorithm = abstractAlgorithm.getConfigOptionsMap();

            if (configOptionsMapPerAlgorithm != null && configOptionsMapPerAlgorithm.keySet().size() > 0) {
                for (String s : configOptionsMapPerAlgorithm.keySet()) {
                    if (mConfigOptionsMapAlgorithms.containsKey(s)) {
                    } else {
                        mConfigOptionsMapAlgorithms.put(s, configOptionsMapPerAlgorithm.get(s));
                    }
                }
            }
        }
    }

    protected void generateMapOfAlgorithmGroupingMap() {
        mMapOfAlgorithmGrouping = new TreeMap<Integer, SensorGroupingDetails>();
        for (AbstractAlgorithm abstractAlgorithm : mMapOfAlgorithmModules.values()) {
            TreeMap<Integer, SensorGroupingDetails> algorithmGroupingMap = abstractAlgorithm.mMapOfAlgorithmGrouping;
            if (algorithmGroupingMap != null && algorithmGroupingMap.keySet().size() > 0) {
                mMapOfAlgorithmGrouping.putAll(algorithmGroupingMap);
            }
        }
    }

    public void setupDataProcessing() {
    }


    protected void generateMapOfAlgorithmModules() {

        mMapOfAlgorithmModules = new HashMap<String, AbstractAlgorithm>();

        loadAlgorithms(OPEN_SOURCE_ALGORITHMS, null);

        if (mDataProcessing != null) {
            mDataProcessing.updateMapOfAlgorithmModules(this);
        }

    }

    public void loadAlgorithms(List<AlgorithmLoaderInterface> listOfAlgorithms, COMMUNICATION_TYPE commType) {
        for (AlgorithmLoaderInterface algorithmLoader : listOfAlgorithms) {
            algorithmLoader.initialiseSupportedAlgorithms(this, commType);
        }
    }

    public void setAlgoProcessingSequence(ArrayList<String> algoOrder) {
        mAlgorithmProcessingSequence = algoOrder;
    }

    public Map<String, AbstractAlgorithm> getMapOfAlgorithmModules() {
        return mMapOfAlgorithmModules;
    }

    public AbstractAlgorithm getAlgorithmModule(String algorithmName) {
        return mMapOfAlgorithmModules.get(algorithmName);
    }

    public void addAlgorithmModule(AbstractAlgorithm abstractAlgorithm) {
        mMapOfAlgorithmModules.put(abstractAlgorithm.mAlgorithmName, abstractAlgorithm);
    }

    protected void initializeAlgorithms() {
        for (AbstractAlgorithm aa : mMapOfAlgorithmModules.values()) {
            try {
                if (aa.isEnabled()) {
                    aa.setShimmerSamplingRate(getSamplingRateShimmer());
                    aa.initialize();
                } else if (!aa.isEnabled()) {
                }
            } catch (Exception e1) {
                consolePrintException("Error initialising algorithm module\t" + aa.getAlgorithmName(), e1.getStackTrace());
            }
        }
    }

    @Deprecated
    public boolean doesAlgorithmAlreadyExist(AbstractAlgorithm obj) {
        for (AbstractAlgorithm aA : mMapOfAlgorithmModules.values()) {
            if (aA.getAlgorithmName().equals(obj.getAlgorithmName())) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void removeAlgorithm(AbstractAlgorithm aobj) {
        mMapOfAlgorithmModules.remove(aobj);
    }

    @Deprecated
    public void removeAlgorithm(String algoName) {
        int index = 0;
        int keepIndex = -1;
        for (AbstractAlgorithm aA : mMapOfAlgorithmModules.values()) {
            if (aA.getAlgorithmName().equals(algoName)) {
                keepIndex = index;
            }
            index++;
        }

        if (keepIndex >= 0) {
            mMapOfAlgorithmModules.remove(keepIndex);
        }
    }

    public void loadAlgorithmVariablesFromAnotherDevice(ShimmerDevice shimmerDevice) {
        Map<String, AbstractAlgorithm> mapOfSourceAlgorithmModules = shimmerDevice.getMapOfAlgorithmModules();
        Iterator<Entry<String, AbstractAlgorithm>> iterator = mapOfSourceAlgorithmModules.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, AbstractAlgorithm> algorithmModuleEntry = iterator.next();

            AbstractAlgorithm abstractAlgorithm = getAlgorithmModule(algorithmModuleEntry.getKey());
            if (abstractAlgorithm != null) {
                abstractAlgorithm.loadAlgorithmVariables(algorithmModuleEntry.getValue());
            }

        }
    }

    public HashMap<String, Object> syncAlgoGroupConfig(String groupName, boolean enabled) {
        HashMap<String, Object> mapOfAlgoSettings = null;

        List<AbstractAlgorithm> listOfEnabledAlgoModulesPerGroup = getListOfEnabledAlgorithmModulesPerGroup(groupName);
        if (listOfEnabledAlgoModulesPerGroup != null && listOfEnabledAlgoModulesPerGroup.size() > 0) {
            AbstractAlgorithm firstEnabledAlgo = listOfEnabledAlgoModulesPerGroup.get(0);

            mapOfAlgoSettings = firstEnabledAlgo.getAlgorithmSettings();

            List<AbstractAlgorithm> listOfAlgoModulesPerGroup = getListOfAlgorithmModulesPerGroup(groupName);
            Iterator<AbstractAlgorithm> iterator = listOfAlgoModulesPerGroup.iterator();
            while (iterator.hasNext()) {
                AbstractAlgorithm abstractAlgorithm = iterator.next();
                abstractAlgorithm.setAlgorithmSettings(mapOfAlgoSettings);
            }
        } else {
            List<AbstractAlgorithm> listOfAlgoModulesPerGroup = getListOfAlgorithmModulesPerGroup(groupName);
        }

        return mapOfAlgoSettings;
    }


    public void setShimmerAndSensorsSamplingRate(double rateHz) {
        setShimmerAndSensorsSamplingRate(null, rateHz);
    }

    public void setShimmerAndSensorsSamplingRate(COMMUNICATION_TYPE communicationType, double rateHz) {
        double correctedRate = correctSamplingRate(rateHz);
        if (communicationType == null) {
            setSamplingRateShimmer(correctedRate);
        } else {
            setSamplingRateShimmer(communicationType, correctedRate);
        }

        setSamplingRateSensors(correctedRate);
        setSamplingRateAlgorithms(correctedRate);
    }

    public void setSamplingRateShimmer(COMMUNICATION_TYPE communicationType, double rateHz) {
        if (mListOfAvailableCommunicationTypes.contains(communicationType)) {
            mMapOfSamplingRatesShimmer.put(communicationType, rateHz);
        }
    }

    public double getSamplingRateShimmer() {
        double maxSetRate = 0.0;
        Iterator<COMMUNICATION_TYPE> iterator = mMapOfSamplingRatesShimmer.keySet().iterator();
        while (iterator.hasNext()) {
            COMMUNICATION_TYPE commType = iterator.next();
            double samplingRate = getSamplingRateShimmer(commType);
            maxSetRate = Math.max(maxSetRate, samplingRate);
        }
        return maxSetRate;
    }

    public void setSamplingRateShimmer(double rateHz) {

        Iterator<COMMUNICATION_TYPE> iterator = mMapOfSamplingRatesShimmer.keySet().iterator();
        while (iterator.hasNext()) {
            setSamplingRateShimmer(iterator.next(), rateHz);
        }
    }

    public double getSamplingRateShimmer(COMMUNICATION_TYPE commsType) {
        if (commsType != null && mMapOfSamplingRatesShimmer != null && mMapOfSamplingRatesShimmer.containsKey(commsType)) {
            double samplingRate = mMapOfSamplingRatesShimmer.get(commsType);
            if (!Double.isNaN(samplingRate)) {
                return samplingRate;
            }
        }

        return getSamplingRateShimmer();
    }

    public byte[] getSamplingRateBytesShimmer() {
        return convertSamplingRateFreqToBytes(getSamplingRateShimmer(), getSamplingClockFreq());
    }

    protected double correctSamplingRate(double rateHz) {
        double maxSamplingRateHz = calcMaxSamplingRate();
        double maxShimmerSamplingRateTicks = getSamplingClockFreq();

        if (rateHz < 1) {
            rateHz = 1.0;
        } else if (rateHz > maxSamplingRateHz) {
            rateHz = maxSamplingRateHz;
        }

        Double actualSamplingRate;
        if ((Math.ceil(maxShimmerSamplingRateTicks / rateHz) - maxShimmerSamplingRateTicks / rateHz) < 0.05) {
            actualSamplingRate = maxShimmerSamplingRateTicks / Math.ceil(maxShimmerSamplingRateTicks / rateHz);
        } else {
            actualSamplingRate = maxShimmerSamplingRateTicks / Math.floor(maxShimmerSamplingRateTicks / rateHz);
        }

        actualSamplingRate = (double) Math.round(actualSamplingRate * 100) / 100;
        return actualSamplingRate;
    }

    protected double calcMaxSamplingRate() {
        double maxGUISamplingRate = 2048.0;
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            double sensorMaxRate = abstractSensor.calcMaxSamplingRate();
            maxGUISamplingRate = Math.min(maxGUISamplingRate, sensorMaxRate);
        }
        return maxGUISamplingRate;
    }

    protected void setSamplingRateSensors(double samplingRateShimmer) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.setSamplingRateFromShimmer(samplingRateShimmer);
        }
    }

    private void setSamplingRateAlgorithms(double samplingRateShimmer) {
        Iterator<AbstractAlgorithm> iterator = mMapOfAlgorithmModules.values().iterator();
        while (iterator.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iterator.next();
            abstractAlgorithm.setSettings(AbstractAlgorithm.GuiLabelConfigCommon.SHIMMER_SAMPLING_RATE, samplingRateShimmer);
        }
    }

    public boolean isAlgorithmEnabled(String algorithmName) {
        AbstractAlgorithm abstractAlgorithm = mMapOfAlgorithmModules.get(algorithmName);
        if (abstractAlgorithm != null && abstractAlgorithm.isEnabled()) {
            return true;
        }
        return false;
    }

    public boolean isAnyAlgortihmChannelEnabled(List<AlgorithmDetails> listOfAlgorithmDetails) {
        for (AlgorithmDetails algortihmDetails : listOfAlgorithmDetails) {
            if (isAlgorithmEnabled(algortihmDetails.mAlgorithmName)) {
                return true;
            }
        }
        return false;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModulesPerGroup(String groupName) {
        for (SensorGroupingDetails sGD : mMapOfAlgorithmGrouping.values()) {
            if (sGD.mGroupName.equals(groupName)) {
                return getListOfEnabledAlgorithmModulesPerGroup(sGD);
            }
        }
        return null;
    }

    public List<AbstractAlgorithm> getListOfEnabledAlgorithmModulesPerGroup(SensorGroupingDetails sGD) {
        List<AbstractAlgorithm> listOfEnabledAlgorthimsPerGroup = new ArrayList<AbstractAlgorithm>();
        if (sGD != null) {
            Map<String, AbstractAlgorithm> mapOfSupportAlgorithms = getSupportedAlgorithmChannels();
            for (AlgorithmDetails aD : sGD.mListOfAlgorithmDetails) {
                AbstractAlgorithm aA = mapOfSupportAlgorithms.get(aD.mAlgorithmName);
                if (aA != null) {
                    if (aA.isEnabled()) {
                        listOfEnabledAlgorthimsPerGroup.add(aA);
                    }
                }
            }
        }
        return listOfEnabledAlgorthimsPerGroup;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(String groupName) {
        for (SensorGroupingDetails sGD : mMapOfAlgorithmGrouping.values()) {
            if (sGD.mGroupName.equals(groupName)) {
                return getListOfAlgorithmModulesPerGroup(sGD);
            }
        }
        return null;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(String[] listOfGroupNames) {

        List<AbstractAlgorithm> listOfAbstractAlgorithms = new ArrayList<AbstractAlgorithm>();

        for (String groupName : listOfGroupNames) {
            for (SensorGroupingDetails sGD : mMapOfAlgorithmGrouping.values()) {
                if (sGD.mGroupName.equals(groupName)) {
                    listOfAbstractAlgorithms.addAll(getListOfAlgorithmModulesPerGroup(sGD));
                }
            }
        }
        return listOfAbstractAlgorithms;
    }

    public List<AbstractAlgorithm> getListOfAlgorithmModulesPerGroup(SensorGroupingDetails sGD) {
        List<AbstractAlgorithm> listOfEnabledAlgorthimsPerGroup = new ArrayList<AbstractAlgorithm>();
        if (sGD != null) {
            Map<String, AbstractAlgorithm> mapOfSupportAlgorithms = getSupportedAlgorithmChannels();
            for (AlgorithmDetails aD : sGD.mListOfAlgorithmDetails) {
                AbstractAlgorithm aA = mapOfSupportAlgorithms.get(aD.mAlgorithmName);
                if (aA != null) {
                    listOfEnabledAlgorthimsPerGroup.add(aA);
                }
            }
        }
        return listOfEnabledAlgorthimsPerGroup;
    }

    public void resetAlgorithmBuffers() {
        List<AbstractAlgorithm> listOfEnabledAlgorithmModules = getListOfEnabledAlgorithmModules();
        Iterator<AbstractAlgorithm> iterator = listOfEnabledAlgorithmModules.iterator();
        while (iterator.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iterator.next();
            try {
                abstractAlgorithm.resetAlgorithmBuffers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledSensorChannelsForStoringToDB(COMMUNICATION_TYPE commType, CHANNEL_TYPE channelType, boolean isKeyOJCName) {
        LinkedHashMap<String, ChannelDetails> mapOfEnabledChannelsForStoringToDb = new LinkedHashMap<String, ChannelDetails>();
        Iterator<SensorDetails> iterator = mSensorMap.values().iterator();
        while (iterator.hasNext()) {
            SensorDetails sensorDetails = iterator.next();

            boolean isEnabled = false;
            if (commType == null) {
                isEnabled = sensorDetails.isEnabled();
            } else {
                isEnabled = sensorDetails.isEnabled(commType);
            }

            if (isEnabled && !sensorDetails.mSensorDetailsRef.mIsDummySensor) {
                for (ChannelDetails channelDetails : sensorDetails.mListOfChannels) {
                    if (channelType != null && !channelDetails.mListOfChannelTypes.contains(channelType)) {
                        continue;
                    }

                    if (channelDetails.isStoreToDatabase()) {
                        String key = (isKeyOJCName ? channelDetails.mObjectClusterName : channelDetails.getDatabaseChannelHandle());
                        mapOfEnabledChannelsForStoringToDb.put(key, channelDetails);
                    }
                }
            }
        }
        return mapOfEnabledChannelsForStoringToDb;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledAlgortihmChannelsToStoreInDB(COMMUNICATION_TYPE commType, CHANNEL_TYPE channelType, boolean isKeyOJCName) {
        LinkedHashMap<String, ChannelDetails> mapOfEnabledChannelsForStoringToDb = new LinkedHashMap<String, ChannelDetails>();

        Iterator<AbstractAlgorithm> iteratorAlgorithms = getListOfEnabledAlgorithmModules().iterator();
        while (iteratorAlgorithms.hasNext()) {
            AbstractAlgorithm algorithm = iteratorAlgorithms.next();

            if (!algorithm.mListOfCommunicationTypesSupported.contains(commType)) {
                continue;
            }

            List<ChannelDetails> listOfDetails = algorithm.getChannelDetails();
            if (listOfDetails != null) {
                for (ChannelDetails channelDetails : listOfDetails) {
                    if (channelType != null && !channelDetails.mListOfChannelTypes.contains(channelType)) {
                        continue;
                    }

                    if (channelDetails.mStoreToDatabase) {
                        String key = (isKeyOJCName ? channelDetails.mObjectClusterName : channelDetails.getDatabaseChannelHandle());
                        mapOfEnabledChannelsForStoringToDb.put(key, channelDetails);
                    }
                }
            }
        }

        return mapOfEnabledChannelsForStoringToDb;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfAllChannelsForStoringToDB(COMMUNICATION_TYPE commType, CHANNEL_TYPE channelType, boolean isKeyOJCName, boolean showDisabledChannels) {

        LinkedHashMap<String, ChannelDetails> mapOfChannelsForStoringToDb = getMapOfEnabledSensorChannelsForStoringToDB(commType, channelType, isKeyOJCName);
        LinkedHashMap<String, ChannelDetails> mapOfAlgoChannelsForStoringToDb = getMapOfEnabledAlgortihmChannelsToStoreInDB(commType, channelType, isKeyOJCName);
        mapOfChannelsForStoringToDb.putAll(mapOfAlgoChannelsForStoringToDb);

        mapOfChannelsForStoringToDb = filterOutUnwantedChannels(mapOfChannelsForStoringToDb, commType, isKeyOJCName);

        return mapOfChannelsForStoringToDb;
    }


    private LinkedHashMap<String, ChannelDetails> filterOutUnwantedChannels(LinkedHashMap<String, ChannelDetails> mapOfChannelsForStoringToDb, COMMUNICATION_TYPE commType, boolean isKeyOJCName) {
        String channelToRemove = "";
        if (commType == COMMUNICATION_TYPE.SD) {
            channelToRemove = isKeyOJCName ? SensorSystemTimeStamp.ObjectClusterSensorName.SYSTEM_TIMESTAMP : AbstractSensor.DatabaseChannelHandlesCommon.TIMESTAMP_SYSTEM;
        } else if (commType == COMMUNICATION_TYPE.BLUETOOTH) {
            channelToRemove = isKeyOJCName ? SensorShimmerClock.ObjectClusterSensorName.REAL_TIME_CLOCK : SensorShimmerClock.DatabaseChannelHandles.REAL_TIME_CLOCK;
        }
        if (!channelToRemove.isEmpty()) {
            mapOfChannelsForStoringToDb.remove(channelToRemove);
        }
        return mapOfChannelsForStoringToDb;
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfChannelsDetailsFromDbHandles(List<String> listOfDbChannelHandles) {
        boolean showDisabledChannels = true;
        boolean showUnknownChannels = true;

        LinkedHashMap<String, ChannelDetails> channelDetailsMap = getMapOfAllChannelsForStoringToDB(null, CHANNEL_TYPE.CAL, false, showDisabledChannels);

        for (SensorDetails sensorDetails : mSensorMap.values()) {
            for (ChannelDetails channelDetails : sensorDetails.getListOfChannels()) {
                if (!channelDetailsMap.containsKey(channelDetails.getDatabaseChannelHandle())) {
                    channelDetailsMap.put(channelDetails.getDatabaseChannelHandle(), channelDetails);
                }
            }
        }
        for (AbstractAlgorithm algorithm : mMapOfAlgorithmModules.values()) {
            for (ChannelDetails channelDetails : algorithm.getChannelDetails()) {
                if (!channelDetailsMap.containsKey(channelDetails.getDatabaseChannelHandle())) {
                    channelDetailsMap.put(channelDetails.getDatabaseChannelHandle(), channelDetails);
                }
            }
        }


        LinkedHashMap<String, ChannelDetails> mapOfChannelsFound = new LinkedHashMap<String, ChannelDetails>();
        for (String dbChannelHandle : listOfDbChannelHandles) {
            ChannelDetails channelDetails = channelDetailsMap.get(dbChannelHandle);

            if (channelDetails == null) {
                if (showUnknownChannels) {
                    channelDetails = new ChannelDetails();
                    channelDetails.mGuiName = dbChannelHandle;
                    channelDetails.mObjectClusterName = dbChannelHandle;
                    String dbColumnName = dbChannelHandle.replace("-", "_");
                    channelDetails.setDatabaseChannelHandle(dbColumnName);
                }
            }

            if (channelDetails != null) {
                if (channelDetails.mObjectClusterName.contains("_DUMMY_")) {
                    continue;
                }

                mapOfChannelsFound.put(channelDetails.getDatabaseChannelHandle(), channelDetails);
            }
        }

        return mapOfChannelsFound;
    }

    public LinkedHashMap<String, Object> generateConfigMap(COMMUNICATION_TYPE commType) {
        LinkedHashMap<String, Object> mapOfConfig = new LinkedHashMap<String, Object>();

        double samplingRateToStore = commType == null ? getSamplingRateShimmer() : getSamplingRateShimmer(commType);
        mapOfConfig.put(DatabaseConfigHandle.SAMPLE_RATE, samplingRateToStore);
        mapOfConfig.put(DatabaseConfigHandle.ENABLE_SENSORS, getEnabledSensors());
        mapOfConfig.put(DatabaseConfigHandle.DERIVED_SENSORS, getDerivedSensors());

        mapOfConfig.put(DatabaseConfigHandle.SHIMMER_VERSION, getHardwareVersion());
        mapOfConfig.put(DatabaseConfigHandle.FW_VERSION, getFirmwareIdentifier());
        mapOfConfig.put(DatabaseConfigHandle.FW_VERSION_MAJOR, getFirmwareVersionMajor());
        mapOfConfig.put(DatabaseConfigHandle.FW_VERSION_MINOR, getFirmwareVersionMinor());
        mapOfConfig.put(DatabaseConfigHandle.FW_VERSION_INTERNAL, getFirmwareVersionInternal());

        mapOfConfig.put(DatabaseConfigHandle.EXP_BOARD_ID, getExpansionBoardId());
        mapOfConfig.put(DatabaseConfigHandle.EXP_BOARD_REV, getExpansionBoardRev());
        mapOfConfig.put(DatabaseConfigHandle.EXP_BOARD_REV_SPEC, getExpansionBoardRevSpecial());

        mapOfConfig.put(DatabaseConfigHandle.EXP_PWR, getInternalExpPower());
        mapOfConfig.put(DatabaseConfigHandle.CONFIG_TIME, getConfigTime());

        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            LinkedHashMap<String, Object> configMapPerSensor = abstractSensor.generateConfigMap();
            if (configMapPerSensor != null) {
                mapOfConfig.putAll(configMapPerSensor);
            }
        }

        Iterator<AbstractAlgorithm> iteratorAlgorithms = mMapOfAlgorithmModules.values().iterator();
        while (iteratorAlgorithms.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iteratorAlgorithms.next();
            LinkedHashMap<String, Object> configMapPerAlgorithm = abstractAlgorithm.generateConfigMap();
            if (configMapPerAlgorithm != null) {
                mapOfConfig.putAll(configMapPerAlgorithm);
            }
        }


        return mapOfConfig;
    }

    public void parseConfigMap(ShimmerVerObject svo, LinkedHashMap<String, Object> mapOfConfigPerShimmer, COMMUNICATION_TYPE commType) {

        if (svo != null) {
            setShimmerVersionObject(svo);
        }

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.EXP_BOARD_ID)
                && mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.EXP_BOARD_REV)
                && mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.EXP_BOARD_REV_SPEC)) {
            ExpansionBoardDetails eBD = new ExpansionBoardDetails(
                    ((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.EXP_BOARD_ID)).intValue(),
                    ((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.EXP_BOARD_REV)).intValue(),
                    ((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.EXP_BOARD_REV_SPEC)).intValue());
            setExpansionBoardDetails(eBD);
        }

        sensorAndConfigMapsCreate();

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.ENABLE_SENSORS)
                && mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.DERIVED_SENSORS)) {
            long enabledSensors = ((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.ENABLE_SENSORS)).longValue();
            long derivedSensors = ((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.DERIVED_SENSORS)).longValue();
            setEnabledAndDerivedSensorsAndUpdateMaps(enabledSensors, derivedSensors);
        }


        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.SAMPLE_RATE)) {
            double samplingRate = (Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.SAMPLE_RATE);
            if (commType == null) {
                setSamplingRateShimmer(samplingRate);
            } else {
                setSamplingRateShimmer(commType, samplingRate);
            }
        }

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.EXP_PWR)) {
            setInternalExpPower(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.EXP_PWR)).intValue());
        }

        if (mapOfConfigPerShimmer.containsKey(DatabaseConfigHandle.CONFIG_TIME)) {
            setConfigTime(((Double) mapOfConfigPerShimmer.get(DatabaseConfigHandle.CONFIG_TIME)).longValue());
        }

        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.parseConfigMap(mapOfConfigPerShimmer);
        }

        Iterator<AbstractAlgorithm> iteratorAlgorithms = mMapOfAlgorithmModules.values().iterator();
        while (iteratorAlgorithms.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iteratorAlgorithms.next();
            abstractAlgorithm.parseConfigMapFromDb(mapOfConfigPerShimmer);
        }

    }

    public void printMapOfConfig() {
        for (COMMUNICATION_TYPE commType : mListOfAvailableCommunicationTypes) {
            HashMap<String, Object> mapOfConfigForDb = generateConfigMap(commType);
            printMapOfConfig(mapOfConfigForDb);
        }
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledChannelsForStreaming() {
        return getMapOfEnabledChannelsForStreaming(null);
    }

    public LinkedHashMap<String, ChannelDetails> getMapOfEnabledChannelsForStreaming(COMMUNICATION_TYPE commType) {
        LinkedHashMap<String, ChannelDetails> mapOfChannels = new LinkedHashMap<String, ChannelDetails>();
        Iterator<SensorDetails> iteratorSensors = mSensorMap.values().iterator();
        while (iteratorSensors.hasNext()) {
            SensorDetails sensorDetails = iteratorSensors.next();

            boolean isEnabled = false;
            if (commType == null) {
                isEnabled = sensorDetails.isEnabled();
            } else {
                isEnabled = sensorDetails.isEnabled(commType);
            }

            if (isEnabled) {
                for (ChannelDetails channelDetails : sensorDetails.getListOfChannels()) {
                    if (channelDetails.isShowWhileStreaming()) {
                        mapOfChannels.put(channelDetails.mObjectClusterName, channelDetails);
                    }
                }
            }
        }

        Iterator<AbstractAlgorithm> iteratorAlgorithms = getMapOfAlgorithmModules().values().iterator();
        while (iteratorAlgorithms.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iteratorAlgorithms.next();
            if (abstractAlgorithm.isEnabled()) {
                for (ChannelDetails channelDetails : abstractAlgorithm.getChannelDetails()) {
                    if (channelDetails.isShowWhileStreaming()) {
                        mapOfChannels.put(channelDetails.mObjectClusterName, channelDetails);
                    }
                }
            }
        }

        if (mapOfChannels.size() == 0) {
            consolePrintLn(getMacIdFromUartParsed() + "\tNO SENSORS ENABLED");
        }

        return mapOfChannels;
    }

    public String[] getListofEnabledChannelSignals() {
        List<String> listofSignals = new ArrayList<String>(getMapOfEnabledChannelsForStreaming().keySet());

        String[] enabledSignals = listofSignals.toArray(new String[listofSignals.size()]);
        return enabledSignals;
    }

    public List<String[]> getListofEnabledChannelSignalsandFormats() {
        List<String[]> listofEnabledSensorSignalsandFormats = new ArrayList<String[]>();
        Iterator<ChannelDetails> iterator = getMapOfEnabledChannelsForStreaming().values().iterator();
        while (iterator.hasNext()) {
            ChannelDetails channelDetails = iterator.next();
            listofEnabledSensorSignalsandFormats.addAll(channelDetails.getListOfChannelSignalsAndFormats());
        }
        return listofEnabledSensorSignalsandFormats;
    }


    public double getMinAllowedSamplingRate() {
        return getMinAllowedEnabledAlgorithmSamplingRate();
    }

    public double getMinAllowedEnabledAlgorithmSamplingRate() {
        double minAllowedAlgoSamplingRate = 0.0;
        Iterator<AbstractAlgorithm> iterator = getListOfEnabledAlgorithmModules().iterator();
        while (iterator.hasNext()) {
            AbstractAlgorithm abstractAlgorithm = iterator.next();
            minAllowedAlgoSamplingRate = Math.max(abstractAlgorithm.getMinSamplingRateForAlgorithm(), minAllowedAlgoSamplingRate);
        }
        return minAllowedAlgoSamplingRate;
    }

    public void setDefaultSettingsForAlgorithmGroup(String groupName) {
        List<AbstractAlgorithm> listOfAlgosForGroup = getListOfAlgorithmModulesPerGroup(groupName);
        for (AbstractAlgorithm abstractAlgorithm : listOfAlgosForGroup) {
            abstractAlgorithm.setDefaultSetting();
        }
    }

    public SensorDetails getSensorDetails(Integer sensorId) {
        if (mSensorMap != null && mSensorMap.containsKey(sensorId)) {
            return mSensorMap.get(sensorId);
        }
        return null;
    }

    public void addSensorClass(AbstractSensor abstractSensor) {
        addSensorClass(abstractSensor.mSensorType, abstractSensor);
    }

    public void addSensorClass(AbstractSensor.SENSORS sensorClassKey, AbstractSensor abstractSensor) {
        mMapOfSensorClasses.put(sensorClassKey, abstractSensor);
    }

    public AbstractSensor getSensorClass(AbstractSensor.SENSORS sensorClassKey) {
        if (mMapOfSensorClasses != null && mMapOfSensorClasses.containsKey(sensorClassKey)) {
            return mMapOfSensorClasses.get(sensorClassKey);
        }
        return null;
    }

    @Deprecated
    public AbstractSensor getSensorClass(long sensorClassKeyInt) {
        if (mMapOfSensorClasses != null) {
            for (AbstractSensor.SENSORS sensorClassKey : mMapOfSensorClasses.keySet()) {
                if (sensorClassKey.ordinal() == sensorClassKeyInt) {
                    return getSensorClass(sensorClassKey);
                }
            }
        }
        return null;
    }

    public void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            consolePrintLn("threadSleep ERROR");
            e.printStackTrace();
        }
    }

    public boolean isCalibrationValid() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> calibrationMap = getMapOfSensorCalibrationAll();
        Boolean validCal = true;
        Integer rangeCast = null;

        if (calibrationMap != null) {
            Iterator<Integer> iterator = calibrationMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer sensorId = iterator.next();
                TreeMap<Integer, CalibDetails> mapOfKinematicDetails = getMapOfSensorCalibrationAll().get(sensorId);

                Object returnedRange = getConfigValueUsingConfigLabel(sensorId, AbstractSensor.GuiLabelConfigCommon.RANGE);
                if (returnedRange != null) {
                    if (returnedRange instanceof Integer) {
                        rangeCast = (Integer) returnedRange;
                    }

                    CalibDetails calibDetails = mapOfKinematicDetails.get(rangeCast);
                    if (calibDetails != null && calibDetails instanceof CalibDetailsKinematic) {
                        CalibDetailsKinematic kinematicDetails = (CalibDetailsKinematic) calibDetails;
                        if (kinematicDetails.isCurrentValuesSet()) {
                            if (!kinematicDetails.isAllCalibrationValid()) {
                                validCal = false;
                            }
                        }
                    }
                }
            }
        }
        return validCal;
    }

    public TreeMap<Integer, TreeMap<Integer, CalibDetailsKinematic>> getMapOfSensorCalibrationAllKinematic() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfAllSensorCalibration = getMapOfSensorCalibrationAll();

        TreeMap<Integer, TreeMap<Integer, CalibDetailsKinematic>> mapOfAllSensorCalibrationKinematic = new TreeMap<Integer, TreeMap<Integer, CalibDetailsKinematic>>();
        Iterator<Integer> iteratorSensor = mapOfAllSensorCalibration.keySet().iterator();
        while (iteratorSensor.hasNext()) {
            Integer sensorId = iteratorSensor.next();
            TreeMap<Integer, CalibDetailsKinematic> mapOfSensorCalibrationKinematic = new TreeMap<Integer, CalibDetailsKinematic>();
            TreeMap<Integer, CalibDetails> mapOfCalibPerRange = mapOfAllSensorCalibration.get(sensorId);
            Iterator<Integer> iteratorRange = mapOfCalibPerRange.keySet().iterator();
            while (iteratorRange.hasNext()) {
                Integer range = iteratorRange.next();
                CalibDetails calibDetails = mapOfCalibPerRange.get(range);
                if (calibDetails instanceof CalibDetailsKinematic) {
                    mapOfSensorCalibrationKinematic.put(range, (CalibDetailsKinematic) calibDetails);
                }
            }

            if (!mapOfSensorCalibrationKinematic.isEmpty()) {
                mapOfAllSensorCalibrationKinematic.put(sensorId, mapOfSensorCalibrationKinematic);
            }

        }
        return mapOfAllSensorCalibrationKinematic;
    }

    public TreeMap<Integer, TreeMap<Integer, CalibDetails>> getMapOfSensorCalibrationAll() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfSensorCalibration = new TreeMap<Integer, TreeMap<Integer, CalibDetails>>();
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            Object returnVal = abstractSensor.getConfigValueUsingConfigLabel(AbstractSensor.GuiLabelConfigCommon.CALIBRATION_ALL);
            if (returnVal != null) {
                TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfCalibDetails = (TreeMap<Integer, TreeMap<Integer, CalibDetails>>) returnVal;
                mapOfSensorCalibration.putAll(mapOfCalibDetails);
            }
        }
        return mapOfSensorCalibration;
    }


    public void setMapOfSensorCalibrationAll(TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfKinematicSensorCalibration) {
        Iterator<Integer> iterator = mapOfKinematicSensorCalibration.keySet().iterator();
        while (iterator.hasNext()) {
            Integer sensorId = iterator.next();
            AbstractSensor abstractSensor = getSensorClass(sensorId);
            if (abstractSensor != null) {
                abstractSensor.setConfigValueUsingConfigLabel(AbstractSensor.GuiLabelConfigCommon.CALIBRATION_PER_SENSOR, mapOfKinematicSensorCalibration.get(sensorId));
            }
        }
    }


    public void resetAllCalibParametersToDefault() {
        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfCalibAll = getMapOfSensorCalibrationAll();
        if (mapOfCalibAll != null) {
            for (TreeMap<Integer, CalibDetails> mapOfCalibPerSensor : mapOfCalibAll.values()) {
                if (mapOfCalibPerSensor != null) {
                    for (CalibDetails calibDetails : mapOfCalibPerSensor.values()) {
                        if (calibDetails != null) {
                            calibDetails.resetToDefaultParameters();
                        }
                    }
                }
            }
        }
    }

    protected void setSensorCalibrationPerSensor(Integer sensorId, TreeMap<Integer, CalibDetails> mapOfSensorCalibration) {
        AbstractSensor abstractSensor = getSensorClass(sensorId);
        if (abstractSensor != null) {
            abstractSensor.setCalibrationMapPerSensor(sensorId, mapOfSensorCalibration);
        }
    }

    public TreeMap<Integer, CalibDetails> getMapOfSensorCalibrationPerSensor(Integer sensorId) {
        AbstractSensor abstractSensor = getSensorClass(sensorId);
        if (abstractSensor != null) {
            return abstractSensor.getCalibrationMapForSensor(sensorId);
        }
        return null;
    }

    public TreeMap<Integer, CalibDetails> getMapOfSensorCalibrationPerSensor(SENSORS sensorClassKey, int sensorId) {
        AbstractSensor abstractSensor = getSensorClass(sensorClassKey);
        if (abstractSensor != null) {
            return abstractSensor.getCalibrationMapForSensor(sensorId);
        }
        return null;
    }

    public byte[] calibByteDumpGenerate() {
        byte[] calibBytesAll = new byte[]{};


        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfAllCalib = getMapOfSensorCalibrationAll();
        for (Integer sensorId : mapOfAllCalib.keySet()) {
            TreeMap<Integer, CalibDetails> calibMapPerSensor = mapOfAllCalib.get(sensorId);
            for (CalibDetails calibDetailsPerRange : calibMapPerSensor.values()) {

                byte[] calibBytesPerSensor = calibDetailsPerRange.generateCalibDump();
                if (calibBytesPerSensor != null) {
                    byte[] calibSensorKeyBytes = new byte[2];
                    calibSensorKeyBytes[0] = (byte) ((sensorId >> 0) & 0xFF);
                    calibSensorKeyBytes[1] = (byte) ((sensorId >> 8) & 0xFF);
                    calibBytesPerSensor = ArrayUtils.addAll(calibSensorKeyBytes, calibBytesPerSensor);

                    byte[] newCalibBytesAll = ArrayUtils.addAll(calibBytesAll, calibBytesPerSensor);
                    calibBytesAll = newCalibBytesAll;
                }


            }
        }

        byte[] svoBytes = mShimmerVerObject.generateVersionByteArrayNew();
        byte[] concatBytes = ArrayUtils.addAll(svoBytes, calibBytesAll);

        byte[] packetLength = new byte[2];
        packetLength[0] = (byte) (concatBytes.length & 0xFF);
        packetLength[1] = (byte) ((concatBytes.length >> 8) & 0xFF);

        concatBytes = ArrayUtils.addAll(packetLength, concatBytes);


        return concatBytes;
    }

    public void calibByteDumpParse(byte[] calibBytesAll, CALIB_READ_SOURCE calibReadSource) {

        mCalibBytesDescriptions = new HashMap<Integer, String>();
        mCalibBytes = calibBytesAll;

        if (UtilShimmer.isAllZeros(calibBytesAll)) {
            return;
        }

        if (calibBytesAll.length > 2) {
            mCalibBytesDescriptions.put(0, "PacketLength_LSB");
            mCalibBytesDescriptions.put(1, "PacketLength_MSB");
            byte[] packetLength = Arrays.copyOfRange(calibBytesAll, 0, 2);

            byte[] svoBytes = Arrays.copyOfRange(calibBytesAll, 2, 10);
            ShimmerVerObject svo = new ShimmerVerObject(svoBytes);

            mCalibBytesDescriptions.put(2, "HwID_LSB (" + svo.getHardwareVersionParsed() + ")");
            mCalibBytesDescriptions.put(3, "HwID_MSB");
            mCalibBytesDescriptions.put(4, "FwID_LSB (" + svo.mFirmwareIdentifierParsed + ")");
            mCalibBytesDescriptions.put(5, "FwID_MSB");
            mCalibBytesDescriptions.put(6, "FWVerMjr_LSB");
            mCalibBytesDescriptions.put(7, "FWVerMjr_MSB");
            mCalibBytesDescriptions.put(8, "FWVerMinor");
            mCalibBytesDescriptions.put(9, "FWVerInternal");

            int currentOffset = 10;
            byte[] remainingBytes = Arrays.copyOfRange(calibBytesAll, 10, calibBytesAll.length);
            ;
            while (remainingBytes.length > 12) {
                byte[] sensorIdBytes = Arrays.copyOfRange(remainingBytes, 0, 2);
                int sensorId = ((sensorIdBytes[1] << 8) | sensorIdBytes[0]) & 0xFFFF;

                String sensorName = "";
                SensorDetails sensorDetails = getSensorDetails(sensorId);
                if (sensorDetails != null && sensorDetails.mSensorDetailsRef != null) {
                    sensorName = sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel;
                }
                mCalibBytesDescriptions.put(currentOffset, "SensorID_LSB (" + sensorName + ")");
                mCalibBytesDescriptions.put(currentOffset + 1, "SensorID_MSB");

                mCalibBytesDescriptions.put(currentOffset + 2, "SensorRange");
                byte[] rangeIdBytes = Arrays.copyOfRange(remainingBytes, 2, 3);
                int rangeValue = (rangeIdBytes[0] & 0xFF);

                mCalibBytesDescriptions.put(currentOffset + 3, "CalibByteLength");
                int calibLength = (remainingBytes[3] & 0xFF);

                mCalibBytesDescriptions.put(currentOffset + 4, "CalibTimeTicks_LSB");
                mCalibBytesDescriptions.put(currentOffset + 11, "CalibTimeTicks_MSB");
                byte[] calibTimeBytesTicks = Arrays.copyOfRange(remainingBytes, 4, 12);


                int endIndex = 12 + calibLength;
                if (remainingBytes.length >= endIndex) {
                    mCalibBytesDescriptions.put(currentOffset + 12, "Calib_Start");
                    mCalibBytesDescriptions.put(currentOffset + endIndex - 1, "Calib_End");
                    byte[] calibBytes = Arrays.copyOfRange(remainingBytes, 12, endIndex);


                    calibByteDumpParsePerSensor(sensorId, rangeValue, calibTimeBytesTicks, calibBytes, calibReadSource);
                    remainingBytes = Arrays.copyOfRange(remainingBytes, endIndex, remainingBytes.length);

                    currentOffset += endIndex;
                } else {
                    break;
                }

            }
        }
    }

    protected void calibByteDumpParsePerSensor(int sensorId, int rangeValue, byte[] calibTimeBytesTicks, byte[] calibBytes, CALIB_READ_SOURCE calibReadSource) {

        TreeMap<Integer, TreeMap<Integer, CalibDetails>> mapOfAllCalib = getMapOfSensorCalibrationAll();
        TreeMap<Integer, CalibDetails> mapOfCalibPerSensor = mapOfAllCalib.get(sensorId);
        if (mapOfCalibPerSensor != null) {
            CalibDetails calibDetailsPerRange = mapOfCalibPerSensor.get(rangeValue);
            if (calibDetailsPerRange != null) {
                calibDetailsPerRange.parseCalibDump(calibTimeBytesTicks, calibBytes, calibReadSource);

            }
        }
    }

    public CommsProtocolRadio getCommsProtocolRadio() {
        return mCommsProtocolRadio;
    }

    public void setCommsProtocolRadio(CommsProtocolRadio commsProtocolRadio) {
        consolePrintErrLn("Setting CommsProtocolRadio");
        this.mCommsProtocolRadio = commsProtocolRadio;
    }

    public void clearCommsProtocolRadio() throws ShimmerException {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.disconnect();
        }
        mCommsProtocolRadio = null;
    }

    public void operationPrepare() {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.operationPrepare();
        }
    }


    public void operationStart(BT_STATE configuring) {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.operationStart(configuring);
        }
    }

    public void startStreaming() throws ShimmerException {
        resetPacketLossVariables();
        generateParserMap();
        initializeAlgorithms();
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.startStreaming();
        }
    }

    public void stopStreaming() throws ShimmerException {
        resetPacketLossVariables();
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.stopStreaming();
        }
    }

    public void startSDLogging() {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.startSDLogging();
        }
    }

    public void stopSDLogging() {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.stopSDLogging();
        }
    }

    public void startDataLogAndStreaming() {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.startDataLogAndStreaming();
        }
    }

    public void stopStreamingAndLogging() {
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.stopLoggingAndStreaming();
        }
    }

    public void updateExpectedDataPacketSize() {
        int expectedDataPacketSize = getExpectedDataPacketSize(COMMUNICATION_TYPE.BLUETOOTH);
        if (mCommsProtocolRadio != null) {
            mCommsProtocolRadio.setPacketSize(expectedDataPacketSize);
        }
    }

    public String getComPort() {
        if (mCommsProtocolRadio != null && mCommsProtocolRadio.mRadioHal != null) {
            setComPort(((AbstractSerialPortHal) mCommsProtocolRadio.mRadioHal).getConnectionHandle());
        }
        return mComPort;
    }

    public void setComPort(String comPort) {
        mComPort = comPort;
        updateThreadName();
    }

    public boolean isReadyToConnect() {
        if (mCommsProtocolRadio == null
                || mCommsProtocolRadio.mRadioHal == null
                || !mCommsProtocolRadio.mRadioHal.isConnected()) {
            return true;
        }
        return false;
    }

    public String getBtConnectionHandle() {
        String comPort = getComPort();
        if (comPort != null && !comPort.isEmpty()) {
            return comPort;
        } else {
            return getMacId();
        }
    }

    public String getUniqueId() {
        return mUniqueID;
    }

    public void setUniqueId(String uniqueId) {
        mUniqueID = uniqueId;
    }


    public void calculatePacketReceptionRateCurrent(int intervalMs) {
        double numPacketsShouldHaveReceived = (((double) intervalMs) / 1000) * getSamplingRateShimmer();
        double packetsReceivedSinceLastRequest = getPacketReceivedCountCurrent();
        setPacketReceptionRateCurrent((packetsReceivedSinceLastRequest / numPacketsShouldHaveReceived) * 100.0);

        resetPacketReceptionCurrentCounters();
    }

    public DataProcessingInterface getDataProcessing() {
        return mDataProcessing;
    }

    public void setDataProcessing(DataProcessingInterface dataProcessing) {
        mDataProcessing = dataProcessing;
    }

    protected void initaliseDataProcessing() {
        if (mDataProcessing != null) {
            mDataProcessing.initializeProcessData((int) getSamplingRateShimmer());
        }
    }

    public void updateThreadName() {
        String macId = getMacIdParsed();
        String newThreadName = this.getClass().getSimpleName();
        if (!macId.isEmpty()) {
            newThreadName += "-" + macId;
        }
        setThreadName(newThreadName);
    }


    protected void consolePrintException(String message, StackTraceElement[] stackTrace) {
        consolePrintLn("Exception!");
        System.out.println("Message: " + message);

        Exception e = new Exception();
        e.setStackTrace(stackTrace);
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        String strStackTrace = sWriter.toString();
        System.out.println(strStackTrace);
    }

    public void disableAllSensors() {
        for (int sensorId : mSensorMap.keySet()) {
            setSensorEnabledState(sensorId, false);
        }
    }

    public void setRadio(AbstractSerialPortHal commsProtocolRadio) {
    }


    public void addDeviceException(ShimmerException dE) {
        dE.mUniqueID = mUniqueID;
        mListOfDeviceExceptions.add(dE);
        consolePrintLn(dE.getErrStringFormatted());
    }

    public void sensorAndConfigMapsCreateCommon() {
        generateSensorAndParserMaps();

        generateMapOfAlgorithmModules();
        generateMapOfAlgorithmConfigOptions();
        generateMapOfAlgorithmGroupingMap();

        handleSpecialCasesAfterSensorMapCreate();
    }

    public String getAlternativeName() {
        return mAlternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        mAlternativeName = alternativeName;
    }


    public boolean isFixedShimmerConfigModeSet() {
        return (mFixedShimmerConfigMode == FIXED_SHIMMER_CONFIG_MODE.NONE ? false : true);
    }

    public FIXED_SHIMMER_CONFIG_MODE getFixedShimmerConfigMode() {
        return mFixedShimmerConfigMode;
    }

    public void setFixedShimmerConfig(FIXED_SHIMMER_CONFIG_MODE fixedShimmerConfig) {
        mFixedShimmerConfigMode = fixedShimmerConfig;
    }

    public void addFixedShimmerConfig(String configKey, Object configValue) {
        if (mFixedShimmerConfigMap == null) {
            mFixedShimmerConfigMap = new LinkedHashMap<String, Object>();
        }
        mFixedShimmerConfigMap.put(configKey, configValue);
    }

    protected boolean setFixedConfigWhenConnecting() {
        return FixedShimmerConfigs.setFixedConfigWhenConnecting(this, mFixedShimmerConfigMode, mFixedShimmerConfigMap);
    }

    public boolean isAutoStartStreaming() {
        return mAutoStartStreaming;
    }

    public void setAutoStartStreaming(boolean state) {
        mAutoStartStreaming = state;
    }

    public void setEnableProcessMarker(boolean enable) {
        mEnableProcessMarkers = enable;
    }

    public double getSamplingClockFreq() {
        return 32768.0;
    }

    public boolean isEnabledAlgorithmModulesDuringPlayback() {
        return mIsEnabledAlgorithmModulesDuringPlayback;
    }

    public void setEnabledAlgorithmModulesDuringPlayback(boolean enableAlgorithmModulesDuringPlayback) {
        mIsEnabledAlgorithmModulesDuringPlayback = enableAlgorithmModulesDuringPlayback;
    }

    public void configureFromClone(ShimmerDevice shimmerDeviceClone) throws ShimmerException {
    }

    public void setIsPlaybackDevice(boolean isPlaybackDevice) {
        mIsPlaybackDevice = isPlaybackDevice;
    }

    public boolean isPlaybackDevice() {
        return mIsPlaybackDevice;
    }

    public List<ISensorConfig> getSensorConfig() {
        List<ISensorConfig> listOfSensorConfig = new ArrayList<>();

        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            listOfSensorConfig.addAll(abstractSensor.getSensorConfig());
        }

        return listOfSensorConfig;
    }

    public void setSensorConfig(List<ISensorConfig> listOfSensorConfig) {
        setSensorConfig(listOfSensorConfig.toArray(new ISensorConfig[listOfSensorConfig.size()]));
    }

    public void setSensorConfig(ISensorConfig[] arrayOfSensorConfig) {
        for (ISensorConfig sensorConfig : arrayOfSensorConfig) {
            setSensorConfig(sensorConfig);
        }
    }

    public void setSensorConfig(ISensorConfig sensorConfig) {
        Iterator<AbstractSensor> iterator = mMapOfSensorClasses.values().iterator();
        while (iterator.hasNext()) {
            AbstractSensor abstractSensor = iterator.next();
            abstractSensor.setSensorConfig(sensorConfig);
        }
    }

    public String getRadioModel() {
        return "";
    }

    public enum BTRADIO_STATE {

        BT_CLASSIC_BLE_ENABLED("BT Classic and BLE Enabled"),
        BT_CLASSIC_ENABLED("BT Classic Enabled"),
        BLE_ENABLED("BLE Enabled"),
        NONE_ENABLED("None Enabled"),
        UNKNOWN("Unknown");

        private final String text;

        private BTRADIO_STATE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

    }

    public enum DOCK_STATE {
        DOCKED("Docked"),
        UNDOCKED("Undocked");

        private final String text;

        private DOCK_STATE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum SD_STATE {
        LOGGING("Logging"),
        NOT_LOGGING("Not_Logging");

        private final String text;

        private SD_STATE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum SENSING_STATE {
        SENSING("Sensing"),
        NOT_SENSING("Not Sensing");

        private final String text;

        private SENSING_STATE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static final class DatabaseConfigHandle {
        public static final String TRIAL_NAME = "Trial_Name";

        public static final String SHIMMER_NAME = "Shimmer_Name";

        public static final String SAMPLE_RATE = "Sample_Rate";
        public static final String ENABLE_SENSORS = "Enable_Sensors";
        public static final String DERIVED_SENSORS = "Derived_Sensors";

        public static final String USER_BUTTON = "User_Button";
        public static final String RTC_SOURCE = "Rtc_Source";
        public static final String SYNC_CONFIG = "Sync";
        public static final String MASTER_CONFIG = "Master";
        public static final String SINGLE_TOUCH_START = "Single_Touch_Start";
        public static final String TXCO = "Txco";
        public static final String BROADCAST_INTERVAL = "Broadcast_Interval";
        public static final String BAUD_RATE = "Baud_Rate";
        public static final String TRIAL_ID = "Trial_Id";
        public static final String N_SHIMMER = "NShimmer";
        public static final String LOW_POWER_AUTOSTOP = "Low_Power_Autostop";

        public static final String SHIMMER_VERSION = "Shimmer_Version";
        public static final String FW_VERSION = "FW_ID";
        public static final String FW_VERSION_MAJOR = "FW_Version_Major";
        public static final String FW_VERSION_MINOR = "FW_Version_Minor";
        public static final String FW_VERSION_INTERNAL = "FW_Version_Internal";

        public static final String INITIAL_TIMESTAMP = "Initial_TimeStamp";
        public static final String EXP_BOARD_ID = "Exp_Board_Id";
        public static final String EXP_BOARD_REV = "Exp_Board_Rev";
        public static final String EXP_BOARD_REV_SPEC = "Exp_Board_Rev_Special";

        public static final String CONFIG_TIME = "Config_Time";


        public static final String EXP_PWR = "Exp_PWR";
        public static final String REAL_TIME_CLOCK_DIFFERENCE = "RTC_Difference";
    }

}