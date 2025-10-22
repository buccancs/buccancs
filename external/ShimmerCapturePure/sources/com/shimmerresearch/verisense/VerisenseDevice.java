package com.shimmerresearch.verisense;

import com.shimmerresearch.algorithms.AbstractAlgorithm;
import com.shimmerresearch.algorithms.verisense.gyroAutoCal.GyroOnTheFlyCalModuleVerisense;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.radioProtocol.RadioListener;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerDeviceCallbackAdapter;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.calibration.CalibDetailsKinematic;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.AbstractSensor;
import com.shimmerresearch.sensors.SensorADC;
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication;
import com.shimmerresearch.verisense.communication.payloads.AbstractPayload;
import com.shimmerresearch.verisense.communication.payloads.OperationalConfigPayload;
import com.shimmerresearch.verisense.communication.payloads.ProductionConfigPayload;
import com.shimmerresearch.verisense.communication.payloads.StatusPayload;
import com.shimmerresearch.verisense.communication.payloads.TimePayload;
import com.shimmerresearch.verisense.payloaddesign.AsmBinaryFileConstants;
import com.shimmerresearch.verisense.payloaddesign.DataBlockDetails;
import com.shimmerresearch.verisense.payloaddesign.PayloadContentsDetails;
import com.shimmerresearch.verisense.payloaddesign.VerisenseTimeDetails;
import com.shimmerresearch.verisense.sensors.ISensorConfig;
import com.shimmerresearch.verisense.sensors.SensorBattVoltageVerisense;
import com.shimmerresearch.verisense.sensors.SensorGSRVerisense;
import com.shimmerresearch.verisense.sensors.SensorLIS2DW12;
import com.shimmerresearch.verisense.sensors.SensorLSM6DS3;
import com.shimmerresearch.verisense.sensors.SensorMAX86150;
import com.shimmerresearch.verisense.sensors.SensorMAX86916;
import com.shimmerresearch.verisense.sensors.SensorMAX86XXX;
import com.shimmerresearch.verisense.sensors.SensorVerisenseClock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes2.dex */
public class VerisenseDevice extends ShimmerDevice implements Serializable {
    public static final String VERISENSE_PREFIX = "Verisense";
    private static final long serialVersionUID = -5496745972549472824L;
    private static final Integer INVALID_VALUE = -1;
    public static List<AbstractSensor.SENSORS> LIST_OF_PPG_SENSORS = Arrays.asList(AbstractSensor.SENSORS.MAX86150, AbstractSensor.SENSORS.MAX86916);
    private static int DEFAULT_HW_ID = 61;
    public BATTERY_TYPE batteryType;
    public AsmBinaryFileConstants.DATA_COMPRESSION_MODE dataCompressionMode;
    public long endTimeMinutes;
    public long endTimeTicksLatest;
    public boolean isExtendedPayloadConfig;
    public PASSKEY_MODE passkeyMode;
    protected transient ShimmerDeviceCallbackAdapter mDeviceCallbackAdapter;
    protected HashMap<Configuration.COMMUNICATION_TYPE, VerisenseProtocolByteCommunication> mapOfVerisenseProtocolByteCommunication;
    VerisenseProtocolByteCommunication mProtocol;
    private int adaptiveSchedulerFailCount;
    private int adaptiveSchedulerInterval;
    private int bleConnectionRetriesPerDay;
    private BLE_TX_POWER bleTxPower;
    private boolean bluetoothEnabled;
    private Configuration.COMMUNICATION_TYPE currentStreamingCommsRoute;
    private ExpansionBoardDetails defaultEbd;
    private ShimmerVerObject defaultSvo;
    private boolean deviceEnabled;
    private Integer firstPayloadIndexAfterBoot;
    private HashMap<DataBlockDetails.DATABLOCK_SENSOR_ID, List<AbstractSensor.SENSORS>> mapOfSensorIdsPerDataBlock;
    private transient OperationalConfigPayload opConfig;
    private PendingEventSchedule pendingEventScheduleDataTransfer;
    private PendingEventSchedule pendingEventScheduleRwcSync;
    private PendingEventSchedule pendingEventScheduleStatusSync;
    private int ppgRecordingDurationSeconds;
    private int ppgRecordingIntervalMinutes;
    private boolean prioritiseLongTermFlash;
    private transient ProductionConfigPayload prodConfigPayload;
    private boolean recordingEnabled;
    private long recordingEndTimeMinutes;
    private long recordingStartTimeMinutes;
    private Integer resetCounter;
    private byte resetReason;
    private transient StatusPayload status;
    private boolean usbEnabled;

    public VerisenseDevice() {
        this.mDeviceCallbackAdapter = new ShimmerDeviceCallbackAdapter(this);
        this.dataCompressionMode = AsmBinaryFileConstants.DATA_COMPRESSION_MODE.NONE;
        this.passkeyMode = PASSKEY_MODE.NONE;
        this.batteryType = BATTERY_TYPE.ZINC_AIR;
        this.defaultSvo = new ShimmerVerObject(DEFAULT_HW_ID, -1, -1, -1, -1);
        this.defaultEbd = new ExpansionBoardDetails(DEFAULT_HW_ID, 0, INVALID_VALUE.intValue());
        this.resetReason = RESET_REASON.CLEARED.bitMask;
        this.resetCounter = null;
        this.firstPayloadIndexAfterBoot = null;
        this.isExtendedPayloadConfig = true;
        this.mapOfSensorIdsPerDataBlock = new HashMap<>();
        this.bluetoothEnabled = true;
        this.usbEnabled = false;
        this.prioritiseLongTermFlash = true;
        this.deviceEnabled = true;
        this.recordingEnabled = true;
        this.recordingStartTimeMinutes = 0L;
        this.recordingEndTimeMinutes = 0L;
        this.bleConnectionRetriesPerDay = 3;
        this.bleTxPower = BLE_TX_POWER.MINUS_12_DBM;
        this.pendingEventScheduleDataTransfer = new PendingEventSchedule(24, 60, 10, 15);
        this.pendingEventScheduleStatusSync = new PendingEventSchedule(24, 60, 10, 15);
        this.pendingEventScheduleRwcSync = new PendingEventSchedule(24, 60, 10, 15);
        this.adaptiveSchedulerInterval = 65535;
        this.adaptiveSchedulerFailCount = 255;
        this.ppgRecordingDurationSeconds = 0;
        this.ppgRecordingIntervalMinutes = 0;
        this.mapOfVerisenseProtocolByteCommunication = new HashMap<>();
        this.currentStreamingCommsRoute = Configuration.COMMUNICATION_TYPE.BLUETOOTH;
        this.endTimeMinutes = 0L;
        this.endTimeTicksLatest = -1L;
        super.setDefaultShimmerConfiguration();
        setUniqueId(HwDriverShimmerDeviceDetails.DEVICE_TYPE.VERISENSE.getLabel());
        setShimmerUserAssignedName(this.mUniqueID);
        setMacIdFromUart(this.mUniqueID);
    }

    public VerisenseDevice(Configuration.COMMUNICATION_TYPE communication_type) {
        this();
        addCommunicationRoute(communication_type);
    }

    public static double calibrateNrf52Temperatue(long j) {
        return j * 0.25d;
    }

    public static int correctHwVersion(int i) {
        if (i == 0) {
            return 64;
        }
        if (i == 1) {
            return 61;
        }
        return i;
    }

    public static boolean doesHwSupportMax86xxx(int i) {
        return i == 64 || i == 63 || i == 68;
    }

    public static boolean isChargerLm3658dPresent(int i, int i2, int i3) {
        return i == 62;
    }

    public static boolean isChargerLtc4123Present(int i, int i2, int i3) {
        if (i == 68 && i2 == 7 && i3 == 1) {
            return true;
        }
        return i == 68 && i2 >= 8;
    }

    public static boolean isExtendedPayloadConfig(byte b) {
        return (b & 16) == 16;
    }

    public static ShimmerVerObject parseFirmwareVersionToShimmerVerObject(byte[] bArr) {
        ShimmerVerObject shimmerVerObject = new ShimmerVerObject();
        shimmerVerObject.mHardwareVersion = DEFAULT_HW_ID;
        shimmerVerObject.mFirmwareVersionMajor = bArr[0] & 255;
        shimmerVerObject.mFirmwareVersionMinor = bArr[1] & 255;
        shimmerVerObject.mFirmwareVersionInternal = ((bArr[3] << 8) | (bArr[2] & 255)) & 65535;
        return shimmerVerObject;
    }

    public static boolean isFwMajorMinorInternalVerEqual(ShimmerVerObject shimmerVerObject, ShimmerVerObject shimmerVerObject2) {
        return shimmerVerObject.getFirmwareVersionMajor() == shimmerVerObject2.getFirmwareVersionMajor() && shimmerVerObject.getFirmwareVersionMinor() == shimmerVerObject2.getFirmwareVersionMinor() && shimmerVerObject.getFirmwareVersionInternal() == shimmerVerObject2.getFirmwareVersionInternal();
    }

    public static boolean compareFwVersions(ShimmerVerObject shimmerVerObject, ShimmerVerObject shimmerVerObject2) {
        return UtilShimmer.compareVersions(shimmerVerObject.getFirmwareVersionMajor(), shimmerVerObject.getFirmwareVersionMinor(), shimmerVerObject.getFirmwareVersionInternal(), shimmerVerObject2.getFirmwareVersionMajor(), shimmerVerObject2.getFirmwareVersionMinor(), shimmerVerObject2.getFirmwareVersionInternal());
    }

    public static boolean isSensorKeyAnAccel(AbstractSensor.SENSORS sensors) {
        return sensors == AbstractSensor.SENSORS.LIS2DW12 || sensors == AbstractSensor.SENSORS.LSM6DS3;
    }

    public static double calibrateVerisenseAdcChannelToVolts(int i, double d) {
        double d2;
        double d3;
        if (i == 62) {
            d2 = 0.75d;
            d3 = 4.0d;
        } else {
            d2 = 0.6d;
            d3 = 6.0d;
        }
        return SensorADC.calibrateU12AdcValueToVolts(d, 0.0d, d2, d3);
    }

    public static double calibrateVerisenseAdcChannelToMillivolts(int i, double d) {
        return calibrateVerisenseAdcChannelToVolts(i, d) * 1000.0d;
    }

    public static boolean isChargerLtc4123Present(ShimmerVerObject shimmerVerObject) {
        return isChargerLtc4123Present(shimmerVerObject.getShimmerExpansionBoardId(), shimmerVerObject.getShimmerExpansionBoardRev(), shimmerVerObject.getShimmerExpansionBoardRevSpecial());
    }

    public static boolean isChargerLm3658dPresent(ShimmerVerObject shimmerVerObject) {
        return isChargerLm3658dPresent(shimmerVerObject.getShimmerExpansionBoardId(), shimmerVerObject.getShimmerExpansionBoardRev(), shimmerVerObject.getShimmerExpansionBoardRevSpecial());
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected double correctSamplingRate(double d) {
        return d;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void createConfigBytesLayout() {
    }

    public int getAdaptiveSchedulerFailCount() {
        return this.adaptiveSchedulerFailCount;
    }

    public void setAdaptiveSchedulerFailCount(int i) {
        this.adaptiveSchedulerFailCount = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 8.0d) - 1.0d));
    }

    public int getAdaptiveSchedulerInterval() {
        return this.adaptiveSchedulerInterval;
    }

    public void setAdaptiveSchedulerInterval(int i) {
        this.adaptiveSchedulerInterval = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 16.0d) - 1.0d));
    }

    public BATTERY_TYPE getBatteryType() {
        return this.batteryType;
    }

    public void setBatteryType(BATTERY_TYPE battery_type) {
        this.batteryType = battery_type;
    }

    public int getBleConnectionRetriesPerDay() {
        return this.bleConnectionRetriesPerDay;
    }

    public void setBleConnectionRetriesPerDay(int i) {
        this.bleConnectionRetriesPerDay = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 8.0d) - 1.0d));
    }

    public BLE_TX_POWER getBleTxPower() {
        return this.bleTxPower;
    }

    public void setBleTxPower(BLE_TX_POWER ble_tx_power) {
        this.bleTxPower = ble_tx_power;
    }

    public Configuration.COMMUNICATION_TYPE getCurrentStreamingCommsRoute() {
        return this.currentStreamingCommsRoute;
    }

    public void setCurrentStreamingCommsRoute(Configuration.COMMUNICATION_TYPE communication_type) {
        this.currentStreamingCommsRoute = communication_type;
    }

    public AsmBinaryFileConstants.DATA_COMPRESSION_MODE getDataCompressionMode() {
        return this.dataCompressionMode;
    }

    public void setDataCompressionMode(AsmBinaryFileConstants.DATA_COMPRESSION_MODE data_compression_mode) {
        this.dataCompressionMode = data_compression_mode;
    }

    public Integer getFirstPayloadIndexAfterBoot() {
        return this.firstPayloadIndexAfterBoot;
    }

    public HashMap<DataBlockDetails.DATABLOCK_SENSOR_ID, List<AbstractSensor.SENSORS>> getMapOfSensorIdsPerDataBlock() {
        return this.mapOfSensorIdsPerDataBlock;
    }

    public HashMap<Configuration.COMMUNICATION_TYPE, VerisenseProtocolByteCommunication> getMapOfVerisenseProtocolByteCommunication() {
        return this.mapOfVerisenseProtocolByteCommunication;
    }

    public void setMapOfVerisenseProtocolByteCommunication(HashMap<Configuration.COMMUNICATION_TYPE, VerisenseProtocolByteCommunication> map) {
        this.mapOfVerisenseProtocolByteCommunication = map;
    }

    public PASSKEY_MODE getPasskeyMode() {
        return this.passkeyMode;
    }

    public void setPasskeyMode(PASSKEY_MODE passkey_mode) {
        this.passkeyMode = passkey_mode;
    }

    public PendingEventSchedule getPendingEventScheduleDataTransfer() {
        return this.pendingEventScheduleDataTransfer;
    }

    public void setPendingEventScheduleDataTransfer(PendingEventSchedule pendingEventSchedule) {
        this.pendingEventScheduleDataTransfer = pendingEventSchedule;
    }

    public PendingEventSchedule getPendingEventScheduleRwcSync() {
        return this.pendingEventScheduleRwcSync;
    }

    public void setPendingEventScheduleRwcSync(PendingEventSchedule pendingEventSchedule) {
        this.pendingEventScheduleRwcSync = pendingEventSchedule;
    }

    public PendingEventSchedule getPendingEventScheduleStatusSync() {
        return this.pendingEventScheduleStatusSync;
    }

    public void setPendingEventScheduleStatusSync(PendingEventSchedule pendingEventSchedule) {
        this.pendingEventScheduleStatusSync = pendingEventSchedule;
    }

    public int getPpgRecordingDurationSeconds() {
        return this.ppgRecordingDurationSeconds;
    }

    public void setPpgRecordingDurationSeconds(int i) {
        this.ppgRecordingDurationSeconds = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 16.0d) - 1.0d));
    }

    public int getPpgRecordingIntervalMinutes() {
        return this.ppgRecordingIntervalMinutes;
    }

    public void setPpgRecordingIntervalMinutes(int i) {
        this.ppgRecordingIntervalMinutes = UtilShimmer.nudgeInteger(i, 0, (int) (Math.pow(2.0d, 16.0d) - 1.0d));
    }

    public long getRecordingEndTimeMinutes() {
        return this.recordingEndTimeMinutes;
    }

    public void setRecordingEndTimeMinutes(long j) {
        this.recordingEndTimeMinutes = UtilShimmer.nudgeLong(j, 0L, (long) (Math.pow(2.0d, 32.0d) - 1.0d));
    }

    public long getRecordingStartTimeMinutes() {
        return this.recordingStartTimeMinutes;
    }

    public void setRecordingStartTimeMinutes(long j) {
        this.recordingStartTimeMinutes = UtilShimmer.nudgeLong(j, 0L, (long) (Math.pow(2.0d, 32.0d) - 1.0d));
    }

    public Integer getResetCounter() {
        return this.resetCounter;
    }

    public void setResetCounter(Integer num) {
        this.resetCounter = num;
    }

    public byte getResetReason() {
        return this.resetReason;
    }

    public void setResetReason(byte b) {
        this.resetReason = b;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    public boolean isBluetoothEnabled() {
        return this.bluetoothEnabled;
    }

    public void setBluetoothEnabled(boolean z) {
        this.bluetoothEnabled = z;
    }

    public boolean isDeviceEnabled() {
        return this.deviceEnabled;
    }

    public void setDeviceEnabled(boolean z) {
        this.deviceEnabled = z;
    }

    public boolean isPrioritiseLongTermFlash() {
        return this.prioritiseLongTermFlash;
    }

    public void setPrioritiseLongTermFlash(boolean z) {
        this.prioritiseLongTermFlash = z;
    }

    public boolean isRecordingEnabled() {
        return this.recordingEnabled;
    }

    public void setRecordingEnabled(boolean z) {
        this.recordingEnabled = z;
    }

    public boolean isUsbEnabled() {
        return this.usbEnabled;
    }

    public void setUsbEnabled(boolean z) {
        this.usbEnabled = z;
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
    }

    public void setFirstPayloadResetAfterBoot(Integer num) {
        this.firstPayloadIndexAfterBoot = num;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public String getFirmwareVersionParsed() {
        return this.mShimmerVerObject.mFirmwareVersionMajor == -1 ? "N/A" : this.mShimmerVerObject.getFirmwareVersionParsedVersionNumberFilled();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void setShimmerUserAssignedName(String str) {
        String strReplaceAll;
        if (!str.isEmpty()) {
            strReplaceAll = str.replace("-", "_").replaceAll(ShimmerDevice.INVALID_TRIAL_NAME_CHAR, "");
            char cCharAt = strReplaceAll.charAt(0);
            StringBuilder sb = new StringBuilder();
            sb.append(cCharAt);
            if (UtilShimmer.isNumeric(sb.toString())) {
                strReplaceAll = "S" + strReplaceAll;
            }
        } else {
            strReplaceAll = "Shimmer_" + getMacIdFromUartParsed();
        }
        if (strReplaceAll.length() > 20) {
            setShimmerUserAssignedNameNoLengthCheck(strReplaceAll.substring(0, 22));
        } else {
            setShimmerUserAssignedNameNoLengthCheck(strReplaceAll);
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public byte[] configBytesGenerate(boolean z, Configuration.COMMUNICATION_TYPE communication_type) {
        byte[] defaultPayloadConfigForFwVersion;
        if (communication_type == Configuration.COMMUNICATION_TYPE.SD) {
            defaultPayloadConfigForFwVersion = new byte[PayloadContentsDetails.calculatePayloadConfigBytesSize(this.mShimmerVerObject)];
            long enabledSensors = getEnabledSensors();
            defaultPayloadConfigForFwVersion[0] = (byte) (enabledSensors & 255);
            if (isPayloadDesignV4orAbove()) {
                defaultPayloadConfigForFwVersion[13] = (byte) (255 & (enabledSensors >> 8));
            }
            byte b = (byte) ((this.isExtendedPayloadConfig ? (byte) 16 : (byte) 0) | defaultPayloadConfigForFwVersion[0]);
            defaultPayloadConfigForFwVersion[0] = b;
            defaultPayloadConfigForFwVersion[0] = (byte) (b | (this.dataCompressionMode.ordinal() & 3));
            Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
            while (it2.hasNext()) {
                it2.next().configBytesGenerate(this, defaultPayloadConfigForFwVersion, communication_type);
            }
            if (this.isExtendedPayloadConfig) {
                defaultPayloadConfigForFwVersion[2] = (byte) (this.mShimmerVerObject.mFirmwareVersionMajor & 255);
                defaultPayloadConfigForFwVersion[3] = (byte) (this.mShimmerVerObject.mFirmwareVersionMinor & 255);
                defaultPayloadConfigForFwVersion[4] = (byte) (this.mShimmerVerObject.mFirmwareVersionInternal & 255);
                defaultPayloadConfigForFwVersion[5] = (byte) ((this.mShimmerVerObject.mFirmwareVersionInternal >> 8) & 255);
                if (isPayloadDesignV2orAbove()) {
                    defaultPayloadConfigForFwVersion[6] = this.resetReason;
                }
                if (isPayloadDesignV3orAbove()) {
                    Integer num = this.resetCounter;
                    int iIntValue = num == null ? 0 : num.intValue();
                    defaultPayloadConfigForFwVersion[7] = (byte) (iIntValue & 255);
                    defaultPayloadConfigForFwVersion[8] = (byte) ((iIntValue >> 8) & 255);
                    Integer num2 = this.firstPayloadIndexAfterBoot;
                    int iIntValue2 = num2 != null ? num2.intValue() : 0;
                    defaultPayloadConfigForFwVersion[9] = (byte) (iIntValue2 & 255);
                    defaultPayloadConfigForFwVersion[10] = (byte) ((iIntValue2 >> 8) & 255);
                }
                if (isPayloadDesignV4orAbove()) {
                    defaultPayloadConfigForFwVersion[11] = (byte) getExpansionBoardId();
                    defaultPayloadConfigForFwVersion[12] = (byte) getExpansionBoardRev();
                }
            }
        } else {
            if (z) {
                defaultPayloadConfigForFwVersion = OperationalConfigPayload.getDefaultPayloadConfigForFwVersion(this.mShimmerVerObject);
            } else {
                defaultPayloadConfigForFwVersion = new byte[OperationalConfigPayload.getPayloadConfigSizeForFwVersion(this.mShimmerVerObject)];
            }
            defaultPayloadConfigForFwVersion[0] = 90;
            long enabledSensors2 = getEnabledSensors();
            byte b2 = (byte) (224 & enabledSensors2);
            defaultPayloadConfigForFwVersion[1] = b2;
            long j = enabledSensors2 >> 8;
            byte b3 = (byte) (j & 252);
            defaultPayloadConfigForFwVersion[2] = b3;
            defaultPayloadConfigForFwVersion[3] = (byte) (j & 2);
            byte b4 = (byte) ((this.bluetoothEnabled ? (byte) 16 : (byte) 0) | b2);
            defaultPayloadConfigForFwVersion[1] = b4;
            byte b5 = (byte) (b4 | (this.usbEnabled ? (byte) 8 : (byte) 0));
            defaultPayloadConfigForFwVersion[1] = b5;
            byte b6 = (byte) (b5 | (this.prioritiseLongTermFlash ? (byte) 4 : (byte) 0));
            defaultPayloadConfigForFwVersion[1] = b6;
            byte b7 = (byte) (b6 | (this.deviceEnabled ? (byte) 2 : (byte) 0));
            defaultPayloadConfigForFwVersion[1] = b7;
            defaultPayloadConfigForFwVersion[1] = (byte) (b7 | (this.recordingEnabled ? 1 : 0));
            defaultPayloadConfigForFwVersion[2] = (byte) (b3 | (this.dataCompressionMode.ordinal() & 3));
            byte bOrdinal = (byte) (defaultPayloadConfigForFwVersion[3] | ((this.passkeyMode.ordinal() & 3) << 2));
            defaultPayloadConfigForFwVersion[3] = bOrdinal;
            defaultPayloadConfigForFwVersion[3] = (byte) (bOrdinal | (this.batteryType.ordinal() & 1));
            long j2 = this.recordingStartTimeMinutes;
            defaultPayloadConfigForFwVersion[21] = (byte) (j2 & 255);
            defaultPayloadConfigForFwVersion[22] = (byte) ((j2 >> 8) & 255);
            defaultPayloadConfigForFwVersion[23] = (byte) ((j2 >> 16) & 255);
            defaultPayloadConfigForFwVersion[24] = (byte) ((j2 >> 24) & 255);
            long j3 = this.recordingEndTimeMinutes;
            defaultPayloadConfigForFwVersion[25] = (byte) (j3 & 255);
            defaultPayloadConfigForFwVersion[26] = (byte) ((j3 >> 8) & 255);
            defaultPayloadConfigForFwVersion[27] = (byte) ((j3 >> 16) & 255);
            defaultPayloadConfigForFwVersion[28] = (byte) ((j3 >> 24) & 255);
            defaultPayloadConfigForFwVersion[30] = (byte) this.bleConnectionRetriesPerDay;
            defaultPayloadConfigForFwVersion[31] = this.bleTxPower.getByteMask();
            byte[] bArrGenerateByteArray = this.pendingEventScheduleDataTransfer.generateByteArray();
            System.arraycopy(bArrGenerateByteArray, 0, defaultPayloadConfigForFwVersion, 32, bArrGenerateByteArray.length);
            byte[] bArrGenerateByteArray2 = this.pendingEventScheduleStatusSync.generateByteArray();
            System.arraycopy(bArrGenerateByteArray2, 0, defaultPayloadConfigForFwVersion, 38, bArrGenerateByteArray2.length);
            byte[] bArrGenerateByteArray3 = this.pendingEventScheduleRwcSync.generateByteArray();
            System.arraycopy(bArrGenerateByteArray3, 0, defaultPayloadConfigForFwVersion, 44, bArrGenerateByteArray3.length);
            int i = this.adaptiveSchedulerInterval;
            defaultPayloadConfigForFwVersion[52] = (byte) (i & 255);
            defaultPayloadConfigForFwVersion[53] = (byte) ((i >> 8) & 255);
            defaultPayloadConfigForFwVersion[54] = (byte) this.adaptiveSchedulerFailCount;
            int i2 = this.ppgRecordingDurationSeconds;
            defaultPayloadConfigForFwVersion[55] = (byte) (i2 & 255);
            defaultPayloadConfigForFwVersion[56] = (byte) ((i2 >> 8) & 255);
            int i3 = this.ppgRecordingIntervalMinutes;
            defaultPayloadConfigForFwVersion[57] = (byte) (i3 & 255);
            defaultPayloadConfigForFwVersion[58] = (byte) ((i3 >> 8) & 255);
            Iterator<AbstractSensor> it3 = this.mMapOfSensorClasses.values().iterator();
            while (it3.hasNext()) {
                it3.next().configBytesGenerate(this, defaultPayloadConfigForFwVersion, communication_type);
            }
        }
        this.mConfigBytes = defaultPayloadConfigForFwVersion;
        return defaultPayloadConfigForFwVersion;
    }

    public void configBytesParseAndInitialiseAlgorithms(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        configBytesParse(bArr, communication_type);
        initaliseDataProcessing();
        if (isPayloadDesignV8orAbove()) {
            initializeAlgorithmsWithDifferentRatesPerSensor();
        } else {
            initializeAlgorithms();
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void configureFromClone(ShimmerDevice shimmerDevice) throws ShimmerException {
        super.configureFromClone(shimmerDevice);
        configBytesParse(shimmerDevice.configBytesGenerate(true));
    }

    private void writeAndReadOperationalConfig() throws ShimmerException {
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).writeAndReadOperationalConfig(getShimmerConfigBytes());
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void configBytesParse(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type) {
        long j;
        this.mConfigBytes = bArr;
        byte b = 252;
        if (communication_type == Configuration.COMMUNICATION_TYPE.SD) {
            this.isExtendedPayloadConfig = isExtendedPayloadConfig(bArr[0]);
            this.dataCompressionMode = AsmBinaryFileConstants.DATA_COMPRESSION_MODE.values()[3 & bArr[0]];
            ShimmerVerObject firmwareVersionToShimmerVerObject = this.defaultSvo;
            ExpansionBoardDetails expansionBoardDetails = this.defaultEbd;
            if (this.isExtendedPayloadConfig) {
                byte[] bArr2 = new byte[4];
                System.arraycopy(bArr, 2, bArr2, 0, 4);
                firmwareVersionToShimmerVerObject = parseFirmwareVersionToShimmerVerObject(bArr2);
            }
            setShimmerVersionObject(firmwareVersionToShimmerVerObject);
            if (this.isExtendedPayloadConfig) {
                setResetReason(bArr[6]);
                if (isPayloadDesignV3orAbove()) {
                    setResetCounter(Integer.valueOf(((bArr[8] & 255) << 8) | (bArr[7] & 255)));
                    setFirstPayloadResetAfterBoot(Integer.valueOf(((bArr[10] & 255) << 8) | (bArr[9] & 255)));
                }
                if (isPayloadDesignV4orAbove()) {
                    int iCorrectHwVersion = correctHwVersion(bArr[11]);
                    byte b2 = bArr[12];
                    setHardwareVersion(iCorrectHwVersion);
                    expansionBoardDetails = new ExpansionBoardDetails(iCorrectHwVersion, b2, INVALID_VALUE.intValue());
                }
            }
            setExpansionBoardDetails(expansionBoardDetails);
            sensorAndConfigMapsCreate();
            long j2 = bArr[0] & ShimmerObject.ROUTINE_COMMUNICATION;
            if (isPayloadDesignV12orAbove()) {
                b = 254;
            } else if (!isPayloadDesignV4orAbove()) {
                b = 0;
            }
            if (b > 0) {
                j2 |= (bArr[13] & b) << 8;
            }
            j = j2;
        } else {
            byte b3 = bArr[1];
            long j3 = (b3 & ShimmerObject.ROUTINE_COMMUNICATION) | ((252 & bArr[2]) << 8) | ((bArr[3] & 2) << 8);
            this.bluetoothEnabled = (b3 & 16) == 16;
            this.usbEnabled = (b3 & 8) == 8;
            this.prioritiseLongTermFlash = (b3 & 4) == 4;
            this.deviceEnabled = (b3 & 2) == 2;
            this.recordingEnabled = (b3 & 1) == 1;
            this.dataCompressionMode = AsmBinaryFileConstants.DATA_COMPRESSION_MODE.values()[bArr[2] & 3];
            this.passkeyMode = PASSKEY_MODE.values()[(bArr[3] >> 2) & 3];
            this.batteryType = BATTERY_TYPE.values()[1 & bArr[3]];
            this.recordingStartTimeMinutes = AbstractPayload.parseByteArrayAtIndex(bArr, 21, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            this.recordingEndTimeMinutes = AbstractPayload.parseByteArrayAtIndex(bArr, 25, ChannelDetails.CHANNEL_DATA_TYPE.UINT24);
            this.bleConnectionRetriesPerDay = bArr[30];
            this.bleTxPower = BLE_TX_POWER.getSettingFromMask(bArr[31]);
            this.pendingEventScheduleDataTransfer = new PendingEventSchedule(bArr, 32);
            this.pendingEventScheduleStatusSync = new PendingEventSchedule(bArr, 38);
            this.pendingEventScheduleRwcSync = new PendingEventSchedule(bArr, 44);
            this.adaptiveSchedulerInterval = (int) AbstractPayload.parseByteArrayAtIndex(bArr, 52, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            this.adaptiveSchedulerFailCount = bArr[54] & 255;
            this.ppgRecordingDurationSeconds = (int) AbstractPayload.parseByteArrayAtIndex(bArr, 55, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            this.ppgRecordingIntervalMinutes = (int) AbstractPayload.parseByteArrayAtIndex(bArr, 57, ChannelDetails.CHANNEL_DATA_TYPE.UINT16);
            j = j3;
        }
        setEnabledAndDerivedSensorsAndUpdateMaps(j, this.mDerivedSensors, communication_type);
        Iterator<AbstractSensor> it2 = this.mMapOfSensorClasses.values().iterator();
        while (it2.hasNext()) {
            it2.next().configBytesParse(this, bArr, communication_type);
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void algorithmMapUpdateFromEnabledSensorsVars() {
        updateDerivedSensorsFromEnabledSensors();
        super.algorithmMapUpdateFromEnabledSensorsVars();
    }

    public void updateDerivedSensorsFromEnabledSensors() {
        this.mDerivedSensors = 0L;
        if (isSensorEnabled(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO)) {
            this.mDerivedSensors |= GyroOnTheFlyCalModuleVerisense.algoGyroOnTheFlyCalVerisense.mDerivedSensorBitmapID;
        }
    }

    public void initializeAlgorithmsWithDifferentRatesPerSensor() {
        for (AbstractAlgorithm abstractAlgorithm : this.mMapOfAlgorithmModules.values()) {
            try {
                if (abstractAlgorithm.isEnabled()) {
                    AbstractSensor.SENSORS sensors = abstractAlgorithm instanceof GyroOnTheFlyCalModuleVerisense ? AbstractSensor.SENSORS.LSM6DS3 : null;
                    if (sensors != null) {
                        abstractAlgorithm.setShimmerSamplingRate(getSamplingRateForSensor(sensors));
                        abstractAlgorithm.initialize();
                    }
                }
            } catch (Exception e) {
                consolePrintException("Error initialising algorithm module\t" + abstractAlgorithm.getAlgorithmName(), e.getStackTrace());
            }
        }
    }

    public boolean isPayloadDesignV1orAbove() {
        return PayloadContentsDetails.isPayloadDesignV1orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV2orAbove() {
        return PayloadContentsDetails.isPayloadDesignV2orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV3orAbove() {
        return PayloadContentsDetails.isPayloadDesignV3orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV4orAbove() {
        return PayloadContentsDetails.isPayloadDesignV4orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV5orAbove() {
        return PayloadContentsDetails.isPayloadDesignV5orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV6orAbove() {
        return PayloadContentsDetails.isPayloadDesignV6orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV7orAbove() {
        return PayloadContentsDetails.isPayloadDesignV7orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV8orAbove() {
        return PayloadContentsDetails.isPayloadDesignV8orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV9orAbove() {
        return PayloadContentsDetails.isPayloadDesignV9orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV10orAbove() {
        return PayloadContentsDetails.isPayloadDesignV10orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV11orAbove() {
        return PayloadContentsDetails.isPayloadDesignV11orAbove(getShimmerVerObject());
    }

    public boolean isPayloadDesignV12orAbove() {
        return PayloadContentsDetails.isPayloadDesignV12orAbove(getShimmerVerObject());
    }

    public boolean isCsvHeaderDesignAzMarkingPoint() {
        return PayloadContentsDetails.isCsvHeaderDesignAzMarkingPoint(getShimmerVerObject());
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public double getSamplingRateShimmer() {
        if (isSensorEnabled(2005)) {
            return getSamplingRateForSensor(AbstractSensor.SENSORS.LIS2DW12);
        }
        if (isEitherLsm6ds3ChannelEnabled()) {
            return getSamplingRateForSensor(AbstractSensor.SENSORS.LSM6DS3);
        }
        if (isHwPpgAndAnyMaxChEnabled()) {
            return getSamplingRateForSensor(getPpgSensorClassKey());
        }
        if (isSensorEnabled(Configuration.Verisense.SENSOR_ID.VBATT)) {
            return getSamplingRateForSensor(AbstractSensor.SENSORS.Battery);
        }
        if (isSensorEnabled(2014)) {
            return getSamplingRateForSensor(AbstractSensor.SENSORS.GSR);
        }
        return 0.0d;
    }

    public AbstractSensor getAbstractSensorPpg() {
        return getSensorClass(getPpgSensorClassKey());
    }

    public AbstractSensor.SENSORS getPpgSensorClassKey() {
        for (AbstractSensor.SENSORS sensors : LIST_OF_PPG_SENSORS) {
            if (getSensorClass(sensors) != null) {
                return sensors;
            }
        }
        return null;
    }

    public AbstractSensor.SENSORS getPrimaryAccel() {
        if (isSensorEnabled(2005)) {
            return AbstractSensor.SENSORS.LIS2DW12;
        }
        if (isSensorEnabled(2007)) {
            return AbstractSensor.SENSORS.LSM6DS3;
        }
        return null;
    }

    public double getSamplingRateAccel() {
        return getSamplingRateForSensor(getPrimaryAccel());
    }

    public double getSamplingRateForSensor(AbstractSensor.SENSORS sensors) {
        AbstractSensor sensorClass;
        Object configValueUsingConfigLabel;
        if (sensors == null || (sensorClass = getSensorClass(sensors)) == null || (configValueUsingConfigLabel = sensorClass.getConfigValueUsingConfigLabel(AbstractSensor.GuiLabelConfigCommon.RATE)) == null || !(configValueUsingConfigLabel instanceof Double)) {
            return 0.0d;
        }
        return ((Double) configValueUsingConfigLabel).doubleValue();
    }

    public double getFastestSamplingRateOfSensors(Configuration.COMMUNICATION_TYPE communication_type) {
        double dMax = 0.0d;
        if (this.mMapOfSensorClasses != null) {
            Iterator<Map.Entry<AbstractSensor.SENSORS, AbstractSensor>> it2 = getMapOfEnabledAbstractSensors(communication_type).entrySet().iterator();
            while (it2.hasNext()) {
                dMax = Math.max(dMax, getSamplingRateForSensor(it2.next().getKey()));
            }
        }
        return dMax;
    }

    public LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> getMapOfEnabledAbstractSensors(Configuration.COMMUNICATION_TYPE communication_type) {
        LinkedHashMap<AbstractSensor.SENSORS, AbstractSensor> linkedHashMap = new LinkedHashMap<>();
        if (this.mMapOfSensorClasses != null) {
            for (Map.Entry<AbstractSensor.SENSORS, AbstractSensor> entry : this.mMapOfSensorClasses.entrySet()) {
                if (entry.getValue().isAnySensorChannelEnabled(communication_type)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return linkedHashMap;
    }

    public boolean compareFwVersions(ShimmerVerObject shimmerVerObject) {
        return compareFwVersions(this.mShimmerVerObject, shimmerVerObject);
    }

    public String generateResetReasonStr() {
        StringBuilder sb = new StringBuilder(CSV_HEADER_LINES.RESET);
        if (isPayloadDesignV2orAbove()) {
            String str = StringUtils.SPACE + UtilShimmer.byteToHexStringFormatted(this.resetReason);
            if (this.resetReason == RESET_REASON.POR_OR_BOD.bitMask) {
                sb.append(RESET_REASON.POR_OR_BOD.descriptionShort + str);
            } else if (this.resetReason == RESET_REASON.CLEARED.bitMask) {
                sb.append(RESET_REASON.CLEARED.descriptionShort + str);
            } else {
                ArrayList arrayList = new ArrayList();
                for (RESET_REASON reset_reason : RESET_REASON.values()) {
                    if (reset_reason != RESET_REASON.POR_OR_BOD && reset_reason != RESET_REASON.CLEARED && (this.resetReason & reset_reason.bitMask) == reset_reason.bitMask) {
                        arrayList.add(reset_reason);
                    }
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    sb.append(((RESET_REASON) arrayList.get(i)).descriptionShort);
                    if (i != arrayList.size() - 1) {
                        sb.append(";");
                    }
                }
                sb.append(str);
            }
        } else {
            sb.append("N/A");
        }
        sb.append("; Count = ");
        if (isPayloadDesignV3orAbove()) {
            Object obj = this.resetCounter;
            if (obj == null) {
                obj = "N/A";
            }
            sb.append(obj);
        } else {
            sb.append("N/A");
        }
        sb.append("; Start Index = ");
        if (isPayloadDesignV3orAbove()) {
            Integer num = this.firstPayloadIndexAfterBoot;
            sb.append(num != null ? num : "N/A");
        } else {
            sb.append("N/A");
        }
        return sb.toString();
    }

    public String generateSensorConfigStrSingleSensor(AbstractSensor.SENSORS sensors, double d) {
        StringBuilder sb = new StringBuilder(CSV_LINE_TITLES.SENSOR_CONFIG);
        if (isCsvHeaderDesignAzMarkingPoint()) {
            sb.append(SENSOR_CONFIG_STRINGS.RATE);
            sb.append(getSamplingRateShimmer());
            sb.append(" Hz; ");
        }
        if (sensors == AbstractSensor.SENSORS.LIS2DW12 && isSensorEnabled(2005)) {
            SensorLIS2DW12 sensorLIS2DW12 = getSensorLIS2DW12();
            if (isCsvHeaderDesignAzMarkingPoint()) {
                sb.append("Accel1 ");
            } else {
                sb.append((CharSequence) generateCalcSamplingRateConfigStr(sensors, sensorLIS2DW12.getAccelRateFreq(), d));
            }
            sb.append(SENSOR_CONFIG_STRINGS.RANGE);
            sb.append(sensorLIS2DW12.getAccelRangeString());
            if (isCsvHeaderDesignAzMarkingPoint()) {
                sb.append("; Mode = ");
                sb.append(sensorLIS2DW12.getAccelModeString());
                sb.append("; LP_Mode = ");
                sb.append(sensorLIS2DW12.getAccelLpModeString());
            } else {
                sb.append("; Mode = ");
                sb.append(sensorLIS2DW12.getAccelModeMergedString());
                sb.append("; Resolution = ");
                sb.append(sensorLIS2DW12.getAccelResolutionString());
            }
            if (!isCsvHeaderDesignAzMarkingPoint()) {
                sb.append(VectorFormat.DEFAULT_SUFFIX);
            }
        } else if (sensors == AbstractSensor.SENSORS.LSM6DS3 && isEitherLsm6ds3ChannelEnabled()) {
            SensorLSM6DS3 sensorLSM6DS3 = getSensorLSM6DS3();
            if (isCsvHeaderDesignAzMarkingPoint()) {
                sb.append("Accel2 ");
            } else {
                sb.append((CharSequence) generateCalcSamplingRateConfigStr(sensors, sensorLSM6DS3.getRateFreq(), d));
                sb.append("Accel ");
            }
            sb.append(SENSOR_CONFIG_STRINGS.RANGE);
            if (isSensorEnabled(2007) || isCsvHeaderDesignAzMarkingPoint()) {
                sb.append(sensorLSM6DS3.getAccelRangeString());
            } else {
                sb.append("Off");
            }
            sb.append("; Gyro Range = ");
            if (isSensorEnabled(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO) || isCsvHeaderDesignAzMarkingPoint()) {
                sb.append(sensorLSM6DS3.getGyroRangeString());
            } else {
                sb.append("Off");
            }
            if (!isCsvHeaderDesignAzMarkingPoint()) {
                sb.append("; Resolution = 16-bit}");
            }
        } else if (LIST_OF_PPG_SENSORS.contains(sensors) && isHwPpgAndAnyMaxChEnabled()) {
            AbstractSensor abstractSensorPpg = getAbstractSensorPpg();
            if (abstractSensorPpg != null) {
                SensorMAX86XXX sensorMAX86XXX = (SensorMAX86XXX) abstractSensorPpg;
                if (isCsvHeaderDesignAzMarkingPoint()) {
                    sb.append("PPG ");
                } else {
                    sb.append((CharSequence) generateCalcSamplingRateConfigStr(sensors, sensorMAX86XXX.getSamplingRateFreq(), d));
                }
                sb.append("Pulse Width = ");
                sb.append(SensorMAX86XXX.CONFIG_OPTION_PPG_PULSE_WIDTH.getConfigStringFromConfigValue(Integer.valueOf(sensorMAX86XXX.getPpgPulseWidthConfigValue())).replaceAll(Configuration.CHANNEL_UNITS.MICROSECONDS, " us"));
                if (isPayloadDesignV5orAbove()) {
                    sb.append(VectorFormat.DEFAULT_SEPARATOR);
                    ArrayList arrayList = new ArrayList();
                    int[] iArr = {2008, 2009, Configuration.Verisense.SENSOR_ID.MAX86916_PPG_GREEN, Configuration.Verisense.SENSOR_ID.MAX86916_PPG_BLUE};
                    for (int i = 0; i < 4; i++) {
                        int i2 = iArr[i];
                        if (isSensorEnabled(i2)) {
                            arrayList.add(Integer.valueOf(i2));
                        }
                    }
                    if (arrayList.size() > 0) {
                        sb.append("LED Amplitude [");
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (i3 > 0 && i3 < arrayList.size()) {
                                sb.append(", ");
                            }
                            Integer num = (Integer) arrayList.get(i3);
                            if (num.intValue() == 2008) {
                                sb.append("Red = ");
                                sb.append(sensorMAX86XXX.getPpgLedAmplitudeRedString());
                            } else if (num.intValue() == 2009) {
                                sb.append("IR = ");
                                sb.append(sensorMAX86XXX.getPpgLedAmplitudeIrString());
                            } else if (abstractSensorPpg instanceof SensorMAX86916) {
                                SensorMAX86916 sensorMAX86916 = (SensorMAX86916) abstractSensorPpg;
                                if (num.intValue() == 2011) {
                                    sb.append("Green = ");
                                    sb.append(sensorMAX86916.getPpgLedAmplitudeGreenString());
                                } else if (num.intValue() == 2012) {
                                    sb.append("Blue = ");
                                    sb.append(sensorMAX86916.getPpgLedAmplitudeBlueString());
                                }
                            }
                        }
                        sb.append("]");
                    }
                } else if (abstractSensorPpg instanceof SensorMAX86150) {
                    sb.append("; Amplitude = ");
                    sb.append(((SensorMAX86150) abstractSensorPpg).getPpgLedAmplitudeRedString());
                }
                sb.append("; Sample Average = ");
                sb.append(SensorMAX86XXX.CONFIG_OPTION_PPG_SAMPLE_AVG.getConfigStringFromConfigValue(Integer.valueOf(sensorMAX86XXX.getPpgSampleAverageConfigValue())));
                sb.append("; Resolution = ");
                sb.append(SensorMAX86XXX.CONFIG_OPTION_PPG_ADC_RESOLUTION.getConfigStringFromConfigValue(Integer.valueOf(sensorMAX86XXX.getPpgAdcResolutionConfigValue())));
                if (!isCsvHeaderDesignAzMarkingPoint()) {
                    sb.append(VectorFormat.DEFAULT_SUFFIX);
                }
            }
        } else if ((sensors == AbstractSensor.SENSORS.Battery && isSensorEnabled(Configuration.Verisense.SENSOR_ID.VBATT)) || (sensors == AbstractSensor.SENSORS.GSR && isSensorEnabled(2014))) {
            sb.append((CharSequence) generateCalcSamplingRateConfigStr(sensors, getSamplingRateForSensor(sensors), d));
            sb.append("Resolution = 12-bit}");
        }
        return sb.toString();
    }

    private StringBuilder generateCalcSamplingRateConfigStr(AbstractSensor.SENSORS sensors, double d, double d2) {
        StringBuilder sb = new StringBuilder();
        sb.append(sensors.toString());
        sb.append(" {Sampling Rate [Configured = ");
        sb.append(d);
        sb.append(" Hz, Calculated = ");
        if (!Double.isNaN(d2)) {
            sb.append(UtilVerisenseDriver.formatDoubleToNdecimalPlaces(d2, 3));
            sb.append(" Hz");
        } else {
            sb.append(UtilVerisenseDriver.UNAVAILABLE);
        }
        sb.append("]; ");
        return sb;
    }

    public double calibrateTemperature(long j) {
        if (isPayloadDesignV9orAbove()) {
            return calibrateNrf52Temperatue(j);
        }
        if (isSensorEnabled(2005)) {
            return SensorLIS2DW12.calibrateTemperature(j);
        }
        if (isEitherLsm6ds3ChannelEnabled()) {
            return SensorLSM6DS3.calibrateTemperature(j);
        }
        if (isHwPpgAndAnyMaxChEnabled()) {
            return calibrateNrf52Temperatue(j);
        }
        return 0.0d;
    }

    public boolean isSpiChannelEnabled() {
        return isSensorEnabled(2005) || isEitherLsm6ds3ChannelEnabled();
    }

    public boolean isHwPpgAndAnyMaxChEnabled() {
        return doesHwSupportMax86xxx() && (isSensorEnabled(2008) || isSensorEnabled(2009) || isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86150_ECG) || isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86916_PPG_GREEN) || isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86916_PPG_BLUE));
    }

    public boolean isAnAdcChEnabled() {
        return isSensorEnabled(Configuration.Verisense.SENSOR_ID.VBATT) || isSensorEnabled(2014);
    }

    public boolean isAnAccelEnabled() {
        return isSensorEnabled(2005) || isSensorEnabled(2007);
    }

    public boolean isEitherLsm6ds3ChannelEnabled() {
        return isSensorEnabled(2007) || isSensorEnabled(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO);
    }

    public boolean doesHwSupportMax86xxx() {
        return doesHwSupportMax86xxx(getHardwareVersion());
    }

    public boolean doesHwSupportUsb() {
        return getHardwareVersion() != 62;
    }

    public boolean doesHwSupportLsm6ds3() {
        int hardwareVersion = getHardwareVersion();
        return hardwareVersion == 64 || hardwareVersion == 61 || hardwareVersion == 62 || hardwareVersion == 63;
    }

    public boolean doesHwSupportShortTermFlash() {
        int hardwareVersion = getHardwareVersion();
        return hardwareVersion == 64 || hardwareVersion == 61 || hardwareVersion == 62;
    }

    public boolean doesHwSupportMax30002() {
        int hardwareVersion = getHardwareVersion();
        int expansionBoardRev = getExpansionBoardRev();
        if (hardwareVersion != 64) {
            return hardwareVersion == 68 && expansionBoardRev >= 1 && expansionBoardRev <= 4;
        }
        return true;
    }

    public boolean doesHwSupportGsr() {
        int hardwareVersion = getHardwareVersion();
        return hardwareVersion == 62 || (hardwareVersion == 68 && getExpansionBoardRev() >= 5);
    }

    public CalibDetailsKinematic getCurrentCalibDetails(int i) {
        if (!isSensorEnabled(i)) {
            return null;
        }
        CalibDetailsKinematic calibDetailsKinematic = (CalibDetailsKinematic) getConfigValueUsingConfigLabel(Integer.valueOf(i), AbstractSensor.GuiLabelConfigCommon.CALIBRATION_CURRENT_PER_SENSOR);
        if (calibDetailsKinematic.isOffsetVectorUsingDefault() && calibDetailsKinematic.isSensitivityUsingDefault()) {
            return null;
        }
        return calibDetailsKinematic;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public VerisenseDevice deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (VerisenseDevice) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void sensorAndConfigMapsCreate() {
        this.mMapOfSensorClasses = new LinkedHashMap<>();
        addSensorClass(AbstractSensor.SENSORS.CLOCK, new SensorVerisenseClock(this));
        addSensorClass(AbstractSensor.SENSORS.LIS2DW12, new SensorLIS2DW12(this));
        if (compareFwVersions(getShimmerVerObject(), FW_CHANGES.CCF21_010_3)) {
            addSensorClass(AbstractSensor.SENSORS.Battery, new SensorBattVoltageVerisense(this));
        }
        if (doesHwSupportLsm6ds3()) {
            addSensorClass(AbstractSensor.SENSORS.LSM6DS3, new SensorLSM6DS3(this));
        }
        if (doesHwSupportMax86xxx()) {
            int hardwareVersion = getHardwareVersion();
            int expansionBoardRev = getExpansionBoardRev();
            if ((hardwareVersion == 64 || hardwareVersion == 63) && (expansionBoardRev == -1 || expansionBoardRev == 0)) {
                addSensorClass(AbstractSensor.SENSORS.MAX86150, new SensorMAX86150(this));
            } else {
                addSensorClass(AbstractSensor.SENSORS.MAX86916, new SensorMAX86916(this));
            }
        }
        doesHwSupportMax30002();
        if (doesHwSupportGsr()) {
            addSensorClass(AbstractSensor.SENSORS.GSR, new SensorGSRVerisense(getShimmerVerObject()));
        }
        super.sensorAndConfigMapsCreateCommon();
        generateParserMap();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void handleSpecCasesAfterSensorMapUpdateFromEnabledSensors() {
        SensorDetails sensorDetails = getSensorDetails(2000);
        if (sensorDetails != null) {
            sensorDetails.setIsEnabled(true);
        }
    }

    public void resetCalibParamAndAlgorithmBuffers() {
        resetAllCalibParametersToDefault();
        resetAlgorithmBuffers();
    }

    public void resetAlgorithmBufferGyroOnTheFly() {
        for (AbstractAlgorithm abstractAlgorithm : getListOfEnabledAlgorithmModules()) {
            if (abstractAlgorithm instanceof GyroOnTheFlyCalModuleVerisense) {
                try {
                    abstractAlgorithm.resetAlgorithmBuffers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void resetAlgorithmBuffersAssociatedWithSensor(AbstractSensor.SENSORS sensors) {
        if (sensors == AbstractSensor.SENSORS.LSM6DS3) {
            resetAlgorithmBufferGyroOnTheFly();
        }
    }

    public ObjectCluster buildMsgForSensorList(byte[] bArr, Configuration.COMMUNICATION_TYPE communication_type, List<AbstractSensor.SENSORS> list, double d) {
        ObjectCluster objectCluster = new ObjectCluster(this.mShimmerUserAssignedName, getMacId());
        objectCluster.mRawData = bArr;
        objectCluster.createArrayData(getNumberOfEnabledChannels(communication_type));
        incrementPacketsReceivedCounters();
        Iterator<AbstractSensor.SENSORS> it2 = list.iterator();
        int i = 0;
        while (it2.hasNext()) {
            TreeMap<Integer, SensorDetails> treeMap = getSensorClass(it2.next()).mSensorMap;
            if (treeMap != null) {
                int i2 = i;
                for (SensorDetails sensorDetails : treeMap.values()) {
                    if (sensorDetails.isEnabled()) {
                        int expectedPacketByteArray = sensorDetails.getExpectedPacketByteArray(communication_type);
                        byte[] bArr2 = new byte[expectedPacketByteArray];
                        if (expectedPacketByteArray != 0) {
                            if (i2 + expectedPacketByteArray <= bArr.length) {
                                System.arraycopy(bArr, i2, bArr2, 0, expectedPacketByteArray);
                            } else {
                                consolePrintLn(this.mShimmerUserAssignedName + " ERROR PARSING " + sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel);
                            }
                        }
                        sensorDetails.processData(bArr2, communication_type, objectCluster, false, d);
                        i2 += expectedPacketByteArray;
                    }
                }
                i = i2;
            } else {
                consolePrintErrLn("ERROR!!!! Sensor Map null");
            }
        }
        return processData(objectCluster);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected ObjectCluster processData(ObjectCluster objectCluster) {
        return super.processData(objectCluster);
    }

    public boolean isFwMajorMinorInternalVerSet() {
        ShimmerVerObject shimmerVerObject = getShimmerVerObject();
        return (shimmerVerObject.mFirmwareVersionMajor == -1 || shimmerVerObject.mFirmwareVersionMinor == -1 || shimmerVerObject.mFirmwareVersionInternal == -1) ? false : true;
    }

    public int getExpectedDataPacketSize(AbstractSensor.SENSORS sensors) {
        int expectedDataPacketSize = 0;
        for (SensorDetails sensorDetails : getSensorClass(sensors).mSensorMap.values()) {
            if (sensorDetails.isEnabled()) {
                expectedDataPacketSize += sensorDetails.getExpectedDataPacketSize();
            }
        }
        return expectedDataPacketSize;
    }

    public List<AbstractSensor.SENSORS> getSensorKeysForDatablockId(DataBlockDetails.DATABLOCK_SENSOR_ID datablock_sensor_id) {
        ArrayList arrayList = new ArrayList();
        int i = AnonymousClass2.$SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID[datablock_sensor_id.ordinal()];
        if (i == 1) {
            if (isSensorEnabled(Configuration.Verisense.SENSOR_ID.VBATT)) {
                arrayList.add(AbstractSensor.SENSORS.Battery);
            }
            if (isSensorEnabled(2014)) {
                arrayList.add(AbstractSensor.SENSORS.GSR);
            }
        } else if (i == 2) {
            arrayList.add(AbstractSensor.SENSORS.LIS2DW12);
        } else if (i == 3) {
            arrayList.add(AbstractSensor.SENSORS.LSM6DS3);
        } else if (i == 4) {
            AbstractSensor.SENSORS ppgSensorClassKey = getPpgSensorClassKey();
            if (ppgSensorClassKey != null) {
                arrayList.add(ppgSensorClassKey);
            }
        } else if (i == 5) {
            arrayList.add(AbstractSensor.SENSORS.BIOZ);
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int calculateFifoBlockSize(AbstractSensor.SENSORS sensors) {
        switch (AnonymousClass2.$SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[sensors.ordinal()]) {
            case 1:
                return 192;
            case 2:
                AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.LSM6DS3);
                if (sensorClass != null) {
                    return ((SensorLSM6DS3) sensorClass).getFifoByteSizeInChip();
                }
                return Integer.MIN_VALUE;
            case 3:
            case 4:
                int i = isSensorEnabled(2008) ? 51 : 0;
                if (isSensorEnabled(2009)) {
                    i += 51;
                }
                if (sensors == AbstractSensor.SENSORS.MAX86150 && isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86150_ECG)) {
                    i += 51;
                }
                if (sensors != AbstractSensor.SENSORS.MAX86916) {
                    return i;
                }
                if (isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86916_PPG_GREEN)) {
                    i += 51;
                }
                int i2 = i;
                return isSensorEnabled(Configuration.Verisense.SENSOR_ID.MAX86916_PPG_BLUE) ? i2 + 51 : i2;
            case 5:
            case 6:
                return SensorBattVoltageVerisense.ADC_BYTE_BUFFER_SIZE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    public void setProtocol(Configuration.COMMUNICATION_TYPE communication_type, VerisenseProtocolByteCommunication verisenseProtocolByteCommunication) {
        addCommunicationRoute(communication_type);
        this.mapOfVerisenseProtocolByteCommunication.put(communication_type, verisenseProtocolByteCommunication);
        verisenseProtocolByteCommunication.addRadioListener(new RadioListener() { // from class: com.shimmerresearch.verisense.VerisenseDevice.1
            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void connected() {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventDockedStateChange() {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventError(ShimmerException shimmerException) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventLogAndStreamStatusChangedCallback(int i) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventNewResponse(byte[] bArr) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetHaveAttemptedToRead(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetIsDocked(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetIsInitialised(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetIsSDLogging(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetIsSensing(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventSetIsStreaming(boolean z) {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void initialiseStreamingCallback() {
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void startOperationCallback(ShimmerBluetooth.BT_STATE bt_state, int i) {
                VerisenseDevice.this.startOperation(bt_state, i);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void finishOperationCallback(ShimmerBluetooth.BT_STATE bt_state) {
                VerisenseDevice.this.finishOperation(bt_state);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void sendProgressReportCallback(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
                VerisenseDevice.this.sendProgressReport(bluetoothProgressReportPerCmd);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void isNowStreamingCallback() {
                VerisenseDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void hasStopStreamingCallback() {
                VerisenseDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventResponseReceived(int i, Object obj) {
                System.out.println("VerisenseDevice Response: " + i + StringUtils.SPACE + obj.getClass().getSimpleName());
                System.out.println(((AbstractPayload) obj).generateDebugString());
                if (obj instanceof StatusPayload) {
                    VerisenseDevice.this.status = (StatusPayload) obj;
                    return;
                }
                if (obj instanceof ProductionConfigPayload) {
                    VerisenseDevice.this.prodConfigPayload = (ProductionConfigPayload) obj;
                    VerisenseDevice verisenseDevice = VerisenseDevice.this;
                    verisenseDevice.setUniqueId(verisenseDevice.prodConfigPayload.verisenseId);
                    VerisenseDevice verisenseDevice2 = VerisenseDevice.this;
                    verisenseDevice2.setShimmerVersionObject(verisenseDevice2.prodConfigPayload.shimmerVerObject);
                    VerisenseDevice verisenseDevice3 = VerisenseDevice.this;
                    verisenseDevice3.setHardwareVersion(verisenseDevice3.prodConfigPayload.expansionBoardDetails.mExpansionBoardId);
                    VerisenseDevice verisenseDevice4 = VerisenseDevice.this;
                    verisenseDevice4.setExpansionBoardDetails(verisenseDevice4.prodConfigPayload.expansionBoardDetails);
                    VerisenseDevice.this.sensorAndConfigMapsCreate();
                    return;
                }
                if (obj instanceof OperationalConfigPayload) {
                    VerisenseDevice.this.opConfig = (OperationalConfigPayload) obj;
                    VerisenseDevice verisenseDevice5 = VerisenseDevice.this;
                    verisenseDevice5.configBytesParseAndInitialiseAlgorithms(verisenseDevice5.opConfig.getPayloadContents(), VerisenseDevice.this.currentStreamingCommsRoute);
                    VerisenseDevice.this.printSensorParserAndAlgoMaps();
                    return;
                }
                boolean z = obj instanceof TimePayload;
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventNewPacket(byte[] bArr, long j) {
                System.out.println("VerisenseDevice New Packet: " + j);
                try {
                    DataBlockDetails dataBlockMetaData = VerisenseDevice.this.parseDataBlockMetaData(bArr, j);
                    VerisenseDevice verisenseDevice = VerisenseDevice.this;
                    verisenseDevice.parseDataBlockData(dataBlockMetaData, bArr, 4, verisenseDevice.currentStreamingCommsRoute);
                    System.out.println("Number of ObjectClusters generated: " + dataBlockMetaData.getOjcArray().length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventAckReceived(int i) {
                if (i == 68) {
                    VerisenseDevice.this.mDeviceCallbackAdapter.writeOpConfigCompleted();
                } else if (i == 2569) {
                    VerisenseDevice.this.mDeviceCallbackAdapter.eraseDataCompleted();
                }
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void disconnected() {
                VerisenseDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void isNowStreamLoggedDataCallback() {
                VerisenseDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING_LOGGED_DATA);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void hasStopStreamLoggedDataCallback(String str) {
                VerisenseDevice.this.mDeviceCallbackAdapter.readLoggedDataCompleted(str);
                VerisenseDevice.this.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
            }

            @Override // com.shimmerresearch.comms.radioProtocol.RadioListener
            public void eventNewSyncPayloadReceived(int i, boolean z, double d, String str) {
                VerisenseDevice.this.mDeviceCallbackAdapter.newSyncPayloadReceived(i, z, d, str);
            }
        });
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void removeCommunicationRoute(Configuration.COMMUNICATION_TYPE communication_type) {
        super.removeCommunicationRoute(communication_type);
        this.mapOfVerisenseProtocolByteCommunication.remove(communication_type);
    }

    public DataBlockDetails parseDataBlockMetaData(byte[] bArr, long j) throws IOException {
        DataBlockDetails dataBlockMetaData = parseDataBlockMetaData(bArr, 0, 0, 0, 0);
        long endTimeTicks = dataBlockMetaData.getTimeDetailsUcClock().getEndTimeTicks();
        long j2 = this.endTimeTicksLatest;
        if (j2 != -1 && endTimeTicks < j2) {
            this.endTimeMinutes++;
        }
        this.endTimeTicksLatest = endTimeTicks;
        dataBlockMetaData.setUcClockOrRwcClockEndTimeMinutesAndCalculateTimings(this.endTimeMinutes, true);
        VerisenseTimeDetails timeDetailsRwc = dataBlockMetaData.getTimeDetailsRwc();
        VerisenseTimeDetails timeDetailsUcClock = dataBlockMetaData.getTimeDetailsUcClock();
        timeDetailsRwc.setStartTimeMs(timeDetailsUcClock.getStartTimeMs());
        timeDetailsRwc.setEndTimeMs(timeDetailsUcClock.getEndTimeMs());
        return dataBlockMetaData;
    }

    public DataBlockDetails parseDataBlockMetaData(byte[] bArr, int i, int i2, int i3, int i4) throws IOException {
        byte b = bArr[i];
        long timeTicksAtIndex = VerisenseTimeDetails.parseTimeTicksAtIndex(bArr, i + 1);
        DataBlockDetails.DATABLOCK_SENSOR_ID[] datablock_sensor_idArrValues = DataBlockDetails.DATABLOCK_SENSOR_ID.values();
        int expectedDataPacketSize = 0;
        if (b > 0 && b < datablock_sensor_idArrValues.length - 1) {
            DataBlockDetails.DATABLOCK_SENSOR_ID datablock_sensor_id = datablock_sensor_idArrValues[b];
            DataBlockDetails dataBlockDetails = new DataBlockDetails(datablock_sensor_id, i4, i3, getOrCreateListOfSensorClassKeysForDataBlockId(datablock_sensor_id), i2, i, timeTicksAtIndex, isPayloadDesignV11orAbove());
            AbstractSensor.SENSORS sensors = dataBlockDetails.listOfSensorClassKeys.get(0);
            int iCalculateFifoBlockSize = calculateFifoBlockSize(sensors);
            Iterator<AbstractSensor.SENSORS> it2 = dataBlockDetails.getListOfSensorClassKeys().iterator();
            while (it2.hasNext()) {
                expectedDataPacketSize += getExpectedDataPacketSize(it2.next());
            }
            dataBlockDetails.setMetadata(iCalculateFifoBlockSize, expectedDataPacketSize, getSamplingRateForSensor(sensors));
            return dataBlockDetails;
        }
        throw new IOException("Error parsing Data Block. SensorID=" + UtilShimmer.intToHexStringFormatted(b, 1, false) + " not supported. File byte index " + UtilShimmer.intToHexStringFormatted(i2, 4, true) + ", Payload index " + i4 + ", DataBlock index in payload " + i3 + ". See console report.");
    }

    public void clearMapOfSensorClassKeysForDataBlockId() {
        this.mapOfSensorIdsPerDataBlock.clear();
    }

    public List<AbstractSensor.SENSORS> getOrCreateListOfSensorClassKeysForDataBlockId(DataBlockDetails.DATABLOCK_SENSOR_ID datablock_sensor_id) {
        List<AbstractSensor.SENSORS> sensorKeysForDatablockId = this.mapOfSensorIdsPerDataBlock.get(datablock_sensor_id);
        if (sensorKeysForDatablockId == null) {
            sensorKeysForDatablockId = getSensorKeysForDatablockId(datablock_sensor_id);
            if (isPayloadDesignV8orAbove()) {
                sensorKeysForDatablockId.add(AbstractSensor.SENSORS.CLOCK);
            }
            this.mapOfSensorIdsPerDataBlock.put(datablock_sensor_id, sensorKeysForDatablockId);
        }
        return sensorKeysForDatablockId;
    }

    public void parseDataBlockData(DataBlockDetails dataBlockDetails, byte[] bArr, int i, Configuration.COMMUNICATION_TYPE communication_type) {
        double startTimeRwcMs = dataBlockDetails.getStartTimeRwcMs();
        for (int i2 = 0; i2 < dataBlockDetails.getSampleCount(); i2++) {
            int i3 = dataBlockDetails.dataPacketSize;
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, i3);
            ObjectCluster objectClusterBuildMsgForSensorList = buildMsgForSensorList(bArr2, communication_type, dataBlockDetails.listOfSensorClassKeys, startTimeRwcMs);
            dataHandler(objectClusterBuildMsgForSensorList);
            dataBlockDetails.setOjcArrayAtIndex(i2, objectClusterBuildMsgForSensorList);
            startTimeRwcMs += dataBlockDetails.getTimestampDiffInS() * 1000.0d;
            i += dataBlockDetails.dataPacketSize;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void dataHandler(ObjectCluster objectCluster) {
        this.mDeviceCallbackAdapter.dataHandler(objectCluster);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void connect() throws Exception {
        try {
            connect(this.currentStreamingCommsRoute);
        } catch (Exception e) {
            if (this.currentStreamingCommsRoute.equals(Configuration.COMMUNICATION_TYPE.BLUETOOTH)) {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
            }
            throw e;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void disconnect() throws ShimmerException {
        disconnect(this.currentStreamingCommsRoute);
    }

    public void disconnect(Configuration.COMMUNICATION_TYPE communication_type) throws ShimmerException {
        VerisenseProtocolByteCommunication verisenseProtocolByteCommunication = this.mapOfVerisenseProtocolByteCommunication.get(communication_type);
        if (verisenseProtocolByteCommunication != null) {
            verisenseProtocolByteCommunication.disconnect();
            if (communication_type.equals(Configuration.COMMUNICATION_TYPE.BLUETOOTH)) {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
                return;
            }
            return;
        }
        throw new ShimmerException("VerisenseProtocolByteCommunication not set");
    }

    public void connect(Configuration.COMMUNICATION_TYPE communication_type) throws ShimmerException {
        VerisenseProtocolByteCommunication verisenseProtocolByteCommunication = this.mapOfVerisenseProtocolByteCommunication.get(communication_type);
        if (verisenseProtocolByteCommunication != null) {
            try {
                if (communication_type.equals(Configuration.COMMUNICATION_TYPE.BLUETOOTH)) {
                    setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTING);
                }
                verisenseProtocolByteCommunication.connect();
                verisenseProtocolByteCommunication.readStatus();
                verisenseProtocolByteCommunication.readProductionConfig();
                verisenseProtocolByteCommunication.readOperationalConfig();
                if (communication_type.equals(Configuration.COMMUNICATION_TYPE.BLUETOOTH)) {
                    setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
                    this.mDeviceCallbackAdapter.isReadyForStreaming();
                    return;
                }
                return;
            } catch (ShimmerException e) {
                e.printStackTrace();
                try {
                    disconnect(communication_type);
                } catch (ShimmerException unused) {
                    e.printStackTrace();
                }
                throw e;
            }
        }
        throw new ShimmerException("VerisenseProtocolByteCommunication not set");
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void startStreaming() throws ShimmerException {
        super.startStreaming();
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).startStreaming();
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.CLOCK);
        if (sensorClass == null || !(sensorClass instanceof SensorVerisenseClock)) {
            return;
        }
        ((SensorVerisenseClock) sensorClass).getSystemTimestampPlot().reset();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void stopStreaming() throws ShimmerException {
        super.stopStreaming();
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).stopStreaming();
    }

    public void readLoggedData() throws ShimmerException {
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).readLoggedData();
    }

    public void setRootPathForBinFile(String str) {
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).setRootPathForBinFile(str);
    }

    public void deleteData() throws Exception {
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).eraseDataTask().waitForCompletion();
    }

    public SensorLIS2DW12 getSensorLIS2DW12() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.LIS2DW12);
        if (sensorClass == null || !(sensorClass instanceof SensorLIS2DW12)) {
            return null;
        }
        return (SensorLIS2DW12) sensorClass;
    }

    public SensorLSM6DS3 getSensorLSM6DS3() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.LSM6DS3);
        if (sensorClass == null || !(sensorClass instanceof SensorLSM6DS3)) {
            return null;
        }
        return (SensorLSM6DS3) sensorClass;
    }

    public SensorGSRVerisense getSensorGsr() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.GSR);
        if (sensorClass == null || !(sensorClass instanceof SensorGSRVerisense)) {
            return null;
        }
        return (SensorGSRVerisense) sensorClass;
    }

    public SensorMAX86916 getSensorMax86916() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.MAX86916);
        if (sensorClass == null || !(sensorClass instanceof SensorMAX86916)) {
            return null;
        }
        return (SensorMAX86916) sensorClass;
    }

    public SensorBattVoltageVerisense getSensorBatteryVoltage() {
        AbstractSensor sensorClass = getSensorClass(AbstractSensor.SENSORS.Battery);
        if (sensorClass == null || !(sensorClass instanceof SensorBattVoltageVerisense)) {
            return null;
        }
        return (SensorBattVoltageVerisense) sensorClass;
    }

    public Integer[] setSensorsEnabled(int[] iArr) {
        disableAllSensors();
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (setSensorEnabledState(i, true)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
    }

    public boolean setSensorEnabledStateAccel1(boolean z) {
        return setSensorEnabledState(2005, z);
    }

    public boolean setSensorEnabledStateAccel2(boolean z) {
        return setSensorEnabledState(2007, z);
    }

    public boolean setSensorEnabledStateGyro(boolean z) {
        return setSensorEnabledState(Configuration.Verisense.SENSOR_ID.LSM6DS3_GYRO, z);
    }

    public boolean setSensorEnabledStateGsr(boolean z) {
        return setSensorEnabledState(2014, z);
    }

    public boolean setSensorEnabledStatePpg(boolean z) {
        return setSensorEnabledState(Configuration.Verisense.SENSOR_ID.MAX86916_PPG_BLUE, z);
    }

    public boolean setSensorEnabledStateBatteryVoltage(boolean z) {
        return setSensorEnabledState(Configuration.Verisense.SENSOR_ID.VBATT, z);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state) {
        boolean bluetoothRadioState = super.setBluetoothRadioState(bt_state);
        this.mDeviceCallbackAdapter.setBluetoothRadioState(bt_state, bluetoothRadioState);
        return bluetoothRadioState;
    }

    public void stopCommunicationProcess(Configuration.COMMUNICATION_TYPE communication_type) {
        this.mapOfVerisenseProtocolByteCommunication.get(communication_type).stop();
    }

    public void setPpgContinuousRecording() {
        setPpgRecordingDurationSeconds(0);
        setPpgRecordingIntervalMinutes(0);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public List<ISensorConfig> getSensorConfig() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.getSensorConfig());
        arrayList.add(this.bleTxPower);
        return arrayList;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void setSensorConfig(ISensorConfig iSensorConfig) {
        if (iSensorConfig instanceof BLE_TX_POWER) {
            setBleTxPower((BLE_TX_POWER) iSensorConfig);
        } else {
            super.setSensorConfig(iSensorConfig);
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public String getTrialName() {
        return this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).getTrialName();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void setTrialName(String str) {
        super.setTrialName(str);
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).setTrialName(str);
    }

    public String getParticipantID() {
        return this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).participantID;
    }

    public void setParticipantID(String str) {
        this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).participantID = str;
    }

    public String getDataFilePath() {
        return this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).getDataFilePath();
    }

    @Deprecated
    public int getPayloadIndex() {
        return this.mapOfVerisenseProtocolByteCommunication.get(this.currentStreamingCommsRoute).rxVerisenseMessageInProgress.payloadIndex;
    }

    protected void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        this.mDeviceCallbackAdapter.sendProgressReport(bluetoothProgressReportPerCmd);
    }

    public void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i) {
        this.mDeviceCallbackAdapter.startOperation(bt_state, i);
    }

    public void finishOperation(ShimmerBluetooth.BT_STATE bt_state) {
        this.mDeviceCallbackAdapter.finishOperation(bt_state);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void operationStart(ShimmerBluetooth.BT_STATE bt_state) {
        startOperation(bt_state, 1);
        try {
            writeAndReadOperationalConfig();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    public boolean isChargerLtc4123Present() {
        return isChargerLtc4123Present(getShimmerVerObject());
    }

    public boolean isChargerLm3658dPresent() {
        return isChargerLm3658dPresent(getShimmerVerObject());
    }

    public enum BATTERY_TYPE {
        ZINC_AIR,
        NIMH
    }

    public enum PASSKEY_MODE {
        SECURE,
        NONE,
        CUSTOM
    }

    public enum RESET_REASON {
        POR_OR_BOD((byte) 0, "POR_OR_BOD", "Battery died or disconnected"),
        RESETPIN((byte) 1, "RESETPIN", "Reset pin triggered"),
        DOG((byte) 2, "WATCHDOG", "FW froze"),
        SREQ((byte) 4, "SREQ", "FW update"),
        LOCKUP((byte) 8, "LOCKUP", "FW lockup"),
        OFF((byte) 16, "OFF", "Off"),
        LPCOMP((byte) 32, "LPCOMP", "Low-power comparator"),
        DIF((byte) 64, "DIF", "DIF"),
        VBUS((byte) -128, "VBUS", "VBUS"),
        CLEARED((byte) -1, "None", "None");

        public byte bitMask;
        public String descriptionLong;
        public String descriptionShort;

        RESET_REASON(byte b, String str, String str2) {
            this.bitMask = b;
            this.descriptionShort = str;
            this.descriptionLong = str2;
        }

        public static RESET_REASON findValueForShortDescription(String str) {
            for (RESET_REASON reset_reason : values()) {
                if (str.equals(reset_reason.descriptionShort)) {
                    return reset_reason;
                }
            }
            return null;
        }
    }

    public enum BLE_TX_POWER implements ISensorConfig {
        PLUS_8_DBM("+8 dBm", (byte) 8),
        PLUS_7_DBM("+7 dBm", (byte) 7),
        PLUS_6_DBM("+6 dBm", (byte) 6),
        PLUS_5_DBM("+5 dBm", (byte) 5),
        PLUS_4_DBM("+4 dBm", (byte) 4),
        PLUS_3_DBM("+3 dBm", (byte) 3),
        PLUS_2_DBM("+2 dBm", (byte) 2),
        PLUS_0_DBM("0 dBm", (byte) 0),
        MINUS_4_DBM("-4 dBm", (byte) -4),
        MINUS_8_DBM("-8 dBm", (byte) -8),
        MINUS_12_DBM("-12 dBm", (byte) -12),
        MINUS_16_DBM("-16 dBm", (byte) -16),
        MINUS_20_DBM("-20 dBm", (byte) -20),
        MINUS_40_DBM("-40 dBm", (byte) -1);

        private byte byteMask;
        private String label;

        BLE_TX_POWER(String str, byte b) {
            this.label = str;
            this.byteMask = b;
        }

        public static BLE_TX_POWER getSettingFromMask(byte b) {
            for (BLE_TX_POWER ble_tx_power : values()) {
                if (ble_tx_power.getByteMask() == b) {
                    return ble_tx_power;
                }
            }
            return null;
        }

        public byte getByteMask() {
            return this.byteMask;
        }

        public String getLabel() {
            return this.label;
        }
    }

    public enum CHARGER_STATUS_LTC4123 {
        ERROR("Error", "Zinc-Air battery/reverse polarity detection/ battery temperature out of range/UVCL at the beginning of the charge cycle"),
        CHARGING("Charging", "Powered on/charging"),
        COMPLETE("Charging complete", "Charging complete"),
        NOT_CHARGING("Not Charging", "No power /not charging");

        public String descriptionLong;
        public String descriptionShort;

        CHARGER_STATUS_LTC4123(String str, String str2) {
            this.descriptionShort = str;
            this.descriptionLong = str2;
        }
    }

    public enum CHARGER_STATUS_LM3658D {
        POWER_DOWN("Power-down", "Power-Down, charging is suspended or interrupted"),
        CHARGING("Charging", "Pre-qualification mode, CC and CV charging, Top-off mode"),
        COMPLETE("Complete", "Charge is completed"),
        NOT_CHARGING("Bad Battery", "Bad battery (Safety timer expired), or LDO mode");

        public String descriptionLong;
        public String descriptionShort;

        CHARGER_STATUS_LM3658D(String str, String str2) {
            this.descriptionShort = str;
            this.descriptionLong = str2;
        }
    }

    public static class CSV_LINE_TITLES {
        public static final String ALGORITHM_CONFIG = "Algorithm config: ";
        public static final String SENSOR_CONFIG = "Sensor config: ";
    }

    public static class FW_CHANGES {
        public static final ShimmerVerObject CCF19_027 = new ShimmerVerObject(-1, 0, 34, 1);
        public static final ShimmerVerObject CCF19_035 = new ShimmerVerObject(-1, 1, 1, 2);
        public static final ShimmerVerObject CCF20_012_1 = new ShimmerVerObject(-1, 1, 2, 2);
        public static final ShimmerVerObject CCF20_012_2 = new ShimmerVerObject(-1, 1, 2, 9);
        public static final ShimmerVerObject CCF20_012_3 = new ShimmerVerObject(-1, 1, 2, 35);
        public static final ShimmerVerObject CCF20_012_4 = new ShimmerVerObject(-1, 1, 2, 70);
        public static final ShimmerVerObject CCF20_012_5 = new ShimmerVerObject(-1, 1, 2, 73);
        public static final ShimmerVerObject CCF20_012_6 = new ShimmerVerObject(-1, 1, 2, 74);
        public static final ShimmerVerObject CCF21_010_1 = new ShimmerVerObject(-1, 1, 2, 85);
        public static final ShimmerVerObject CCF21_010_2 = new ShimmerVerObject(-1, 1, 2, 87);
        public static final ShimmerVerObject CCF21_010_3 = new ShimmerVerObject(-1, 1, 2, 88);
    }

    public static class FW_SPECIAL_VERSIONS {
        public static final ShimmerVerObject V_0_31_000 = new ShimmerVerObject(-1, 0, 32, 0);
        public static final ShimmerVerObject V_1_02_071 = new ShimmerVerObject(-1, 1, 2, 71);
    }

    public static class SENSOR_CONFIG_STRINGS {
        public static final String RANGE = "Range = ";
        public static final String RATE = "Rate = ";
        public static final String RESOLUTION = "Resolution = ";
        public static final String SAMPLING_RATE_CALCULATED = "Calculated = ";
        public static final String SAMPLING_RATE_CONFIGURED = "Configured = ";
    }

    /* renamed from: com.shimmerresearch.verisense.VerisenseDevice$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS;
        static final /* synthetic */ int[] $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID;

        static {
            int[] iArr = new int[AbstractSensor.SENSORS.values().length];
            $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS = iArr;
            try {
                iArr[AbstractSensor.SENSORS.LIS2DW12.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.LSM6DS3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.MAX86150.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.MAX86916.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.Battery.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.GSR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$shimmerresearch$sensors$AbstractSensor$SENSORS[AbstractSensor.SENSORS.BIOZ.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[DataBlockDetails.DATABLOCK_SENSOR_ID.values().length];
            $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID = iArr2;
            try {
                iArr2[DataBlockDetails.DATABLOCK_SENSOR_ID.ADC.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID[DataBlockDetails.DATABLOCK_SENSOR_ID.ACCEL_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID[DataBlockDetails.DATABLOCK_SENSOR_ID.GYRO_ACCEL2.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID[DataBlockDetails.DATABLOCK_SENSOR_ID.PPG.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$shimmerresearch$verisense$payloaddesign$DataBlockDetails$DATABLOCK_SENSOR_ID[DataBlockDetails.DATABLOCK_SENSOR_ID.BIOZ.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public class CSV_HEADER_LINES {
        public static final String RESET = "Reset: Reason = ";

        public CSV_HEADER_LINES() {
        }
    }
}
