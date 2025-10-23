package com.shimmerresearch.managers.bluetoothManager;

import com.shimmerresearch.bluetooth.BluetoothProgressReportAll;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerRadioInitializer;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerShell;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;
import com.shimmerresearch.driverUtilities.BluetoothDeviceDetails;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ConnectionExceptionListener;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs;
import com.shimmerresearch.thirdpartyDevices.noninOnyxII.NoninOnyxIIDevice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public abstract class ShimmerBluetoothManager {
    public static final String COMPORT_PREFIX = "COM";
    public static final String COMPORT_PREFIX_MAC = "/dev/";
    public static final long SLEEP_BETWEEN_GROUP_ACTIONS_MS = 50;
    protected static final boolean USE_INFOMEM_CONFIG_METHOD = true;
    private static final List<Integer> HW_IDS_THAT_SUPPORT_CONFIG_VIA_BT = Arrays.asList(3, 10, 4, 58, 61, 64, 62, 63, 68);
    public static ConcurrentHashMap<String, ShimmerDevice> mMapOfBtConnectedShimmers = new ConcurrentHashMap<>(7);
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBtComPorts = new TreeMap<>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBLEDevices = new TreeMap<>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBtComPortsDeepCopy = new TreeMap<>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBLEDevicesDeepCopy = new TreeMap<>();
    public HashMap<String, ConnectThread> mapOfConnectionThreads = new HashMap<>();
    protected BluetoothProgressReportAll mProgressReportAll;
    protected transient Timer mTimerTimerCalcReceptionRate;
    protected boolean directConnectUnknownShimmer = false;
    protected int mSyncTrainingIntervalInSeconds = 15;
    protected int msDelayBetweenSetCommands = 0;
    protected FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE mFixedShimmerConfigGlobal = FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE.NONE;
    protected boolean mAutoStartStreaming = false;
    private ConnectionExceptionListener connectionExceptionListener;
    private int mCalcReceptionRateMs = 1000;

    public ShimmerBluetoothManager() {
        startTimerCalcReceptionRate();
    }

    public abstract void addCallBack(BasicProcessWithCallBack basicProcessWithCallBack);

    protected abstract void connectExistingShimmer(Object... objArr) throws ShimmerException;

    protected void connectShimmer3BleGrpc(BluetoothDeviceDetails bluetoothDeviceDetails) {
    }

    protected void connectVerisenseDevice(BluetoothDeviceDetails bluetoothDeviceDetails) {
    }

    protected abstract AbstractSerialPortHal createNewSerialPortComm(String str, String str2);

    protected abstract ShimmerDevice createNewShimmer3(ShimmerRadioInitializer shimmerRadioInitializer, String str);

    protected abstract ShimmerDevice createNewShimmer3(String str, String str2);

    protected abstract Shimmer4sdk createNewShimmer4(ShimmerRadioInitializer shimmerRadioInitializer, String str);

    protected abstract Shimmer4sdk createNewShimmer4(String str, String str2);

    protected ShimmerDevice createNewSweatchDevice(ShimmerRadioInitializer shimmerRadioInitializer, String str) {
        return null;
    }

    protected ShimmerDevice createNewSweatchDevice(String str, String str2) {
        return null;
    }

    public ConcurrentHashMap<String, ShimmerDevice> getMapOfBtConnectedShimmers() {
        return mMapOfBtConnectedShimmers;
    }

    public abstract ShimmerDevice getShimmerGlobalMap(String str);

    protected abstract void loadBtShimmers(Object... objArr);

    public abstract void printMessage(String str);

    public abstract void putShimmerGlobalMap(String str, ShimmerDevice shimmerDevice);

    public void setAutoStartStreaming(boolean z) {
        this.mAutoStartStreaming = z;
    }

    public void setConnectionExceptionListener(ConnectionExceptionListener connectionExceptionListener) {
        this.connectionExceptionListener = connectionExceptionListener;
    }

    public void setFixedConfig(FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE fixed_shimmer_config_mode) {
        this.mFixedShimmerConfigGlobal = fixed_shimmer_config_mode;
    }

    public HwDriverShimmerDeviceDetails.DEVICE_TYPE useMockHWID(HwDriverShimmerDeviceDetails.DEVICE_TYPE device_type) {
        return device_type;
    }

    public void connectShimmerThroughCommPort(String str) {
        connectShimmer(str, null, null);
    }

    public void connectShimmer(String str, ShimmerVerObject shimmerVerObject, ExpansionBoardDetails expansionBoardDetails) {
        if (getBluetoothDeviceDetails(str) != null) {
            ConnectThread connectThread = new ConnectThread(str, shimmerVerObject, expansionBoardDetails);
            this.mapOfConnectionThreads.put(connectThread.connectionHandle, connectThread);
            connectThread.start();
            return;
        }
        sendFeedbackOnConnectStartException(str);
    }

    public void connectShimmerThroughBTAddress(String str) {
        ConnectThread connectThread = new ConnectThread(str);
        this.mapOfConnectionThreads.put(connectThread.connectionHandle, connectThread);
        connectThread.start();
    }

    public void connectShimmerThroughBTAddress(BluetoothDeviceDetails bluetoothDeviceDetails) {
        new ConnectThread(bluetoothDeviceDetails).start();
    }

    public void disconnectShimmer(String str) {
        printMessage("Attempting to disconnect from connection handle = " + str);
        ShimmerDevice shimmerDeviceBtConnected = getShimmerDeviceBtConnected(str);
        if (shimmerDeviceBtConnected != null) {
            printMessage("Disconnecting from " + shimmerDeviceBtConnected.getClass().getSimpleName() + " with connection handle = " + str);
            disconnectShimmer(shimmerDeviceBtConnected);
        }
    }

    public void disconnectAllDevices() throws InterruptedException {
        Iterator<ShimmerDevice> it2 = mMapOfBtConnectedShimmers.values().iterator();
        while (it2.hasNext()) {
            disconnectShimmer(it2.next());
            threadSleep(50L);
        }
        mMapOfBtConnectedShimmers.clear();
    }

    public void disconnectShimmer(ShimmerDevice shimmerDevice) {
        try {
            shimmerDevice.disconnect();
        } catch (ShimmerException e) {
            printMessage(e.getErrStringFormatted());
        }
    }

    public void startStreaming(String str) throws ShimmerException {
        ShimmerDevice shimmerDeviceBtConnectedFromMac = getShimmerDeviceBtConnectedFromMac(str);
        if (shimmerDeviceBtConnectedFromMac != null) {
            startStreaming(shimmerDeviceBtConnectedFromMac);
        }
    }

    public void startStreamingAllDevices() throws InterruptedException {
        Iterator<ShimmerDevice> it2 = mMapOfBtConnectedShimmers.values().iterator();
        while (it2.hasNext()) {
            try {
                startStreaming(it2.next());
            } catch (ShimmerException e) {
                e.printStackTrace();
            }
            threadSleep(50L);
        }
    }

    public void startStreaming(ShimmerDevice shimmerDevice) throws ShimmerException {
        if (shimmerDevice.isConnected()) {
            shimmerDevice.startStreaming();
        }
    }

    public void stopStreamingAllDevices() throws InterruptedException {
        Iterator<ShimmerDevice> it2 = mMapOfBtConnectedShimmers.values().iterator();
        while (it2.hasNext()) {
            try {
                stopStreaming(it2.next());
            } catch (ShimmerException e) {
                e.printStackTrace();
            }
            threadSleep(50L);
        }
    }

    public void stopStreaming(String str) throws ShimmerException {
        ShimmerDevice shimmerDeviceBtConnectedFromMac = getShimmerDeviceBtConnectedFromMac(str);
        if (shimmerDeviceBtConnectedFromMac != null) {
            stopStreaming(shimmerDeviceBtConnectedFromMac);
        }
    }

    public void stopStreaming(ShimmerDevice shimmerDevice) throws ShimmerException {
        if (shimmerDevice.isConnected() && shimmerDevice.isStreaming()) {
            shimmerDevice.stopStreaming();
        }
    }

    public void startSDLogging(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.isConnected()) {
            shimmerDevice.startSDLogging();
        }
    }

    public void startSDLogging(List<ShimmerDevice> list) throws InterruptedException {
        Iterator<ShimmerDevice> it2 = list.iterator();
        while (it2.hasNext()) {
            startSDLogging(it2.next());
            threadSleep(50L);
        }
    }

    public void stopSDLogging(ShimmerDevice shimmerDevice) {
        shimmerDevice.stopSDLogging();
    }

    public void stopSDLogging(List<ShimmerDevice> list) throws InterruptedException {
        Iterator<ShimmerDevice> it2 = list.iterator();
        while (it2.hasNext()) {
            stopSDLogging(it2.next());
            threadSleep(50L);
        }
    }

    public void startStreamingAndLogging(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.isConnected()) {
            shimmerDevice.startDataLogAndStreaming();
        }
    }

    public void stopStreamingAndLogging(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.isConnected()) {
            if (shimmerDevice.isSDLogging() || shimmerDevice.isStreaming()) {
                shimmerDevice.stopStreamingAndLogging();
            }
        }
    }

    public void toggleLed(String str) {
        ShimmerDevice shimmerDeviceBtConnected = getShimmerDeviceBtConnected(str);
        if (shimmerDeviceBtConnected != null) {
            toggleLed(shimmerDeviceBtConnected);
        }
    }

    public void toggleLed(List<String> list) throws InterruptedException {
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            toggleLed(it2.next());
            threadSleep(50L);
        }
    }

    public void toggleLed(ShimmerDevice shimmerDevice) {
        if (shimmerDevice == null || !shimmerDevice.isConnected()) {
            return;
        }
        if (shimmerDevice instanceof ShimmerBluetooth) {
            ((ShimmerBluetooth) shimmerDevice).toggleLed();
        } else if (shimmerDevice instanceof Shimmer4sdk) {
            ((Shimmer4sdk) shimmerDevice).toggleLed();
        }
    }

    @Deprecated
    public void setCadenceBTConfig(ShimmerDevice shimmerDevice) {
        ShimmerDevice shimmerDeviceBtConnected = getShimmerDeviceBtConnected(shimmerDevice.getBtConnectionHandle());
        if (shimmerDeviceBtConnected != null) {
            shimmerDeviceBtConnected.operationPrepare();
            shimmerDeviceBtConnected.setDefaultShimmerConfiguration();
            shimmerDeviceBtConnected.disableAllAlgorithms();
            shimmerDeviceBtConnected.disableAllSensors();
            if (shimmerDeviceBtConnected.getSensorIdsSet().contains(31)) {
                shimmerDeviceBtConnected.setSensorEnabledState(31, true);
                shimmerDeviceBtConnected.setConfigValueUsingConfigLabel((Integer) 31, "Wide Range Accel Range", (Object) 1);
            }
            shimmerDeviceBtConnected.setShimmerAndSensorsSamplingRate(51.2d);
            if (shimmerDeviceBtConnected instanceof ShimmerBluetooth) {
                ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDeviceBtConnected;
                shimmerBluetooth.writeConfigBytes(shimmerDeviceBtConnected.getShimmerConfigBytes());
                shimmerBluetooth.writeEnabledSensors(shimmerDeviceBtConnected.getEnabledSensors());
            }
            shimmerDeviceBtConnected.operationStart(ShimmerBluetooth.BT_STATE.CONFIGURING);
        }
    }

    public void configureShimmer(ShimmerDevice shimmerDevice, boolean z) throws InterruptedException {
        if (shimmerDevice instanceof ShimmerBluetooth) {
            ((ShimmerBluetooth) shimmerDevice).setWriteCalibrationDumpWhenConfiguringForClone(z);
        }
        configureShimmers(Arrays.asList(shimmerDevice));
    }

    public void configureShimmer(ShimmerDevice shimmerDevice) throws InterruptedException {
        configureShimmers(Arrays.asList(shimmerDevice));
    }

    public void configureShimmers(List<ShimmerDevice> list) throws InterruptedException {
        this.mProgressReportAll = new BluetoothProgressReportAll(ShimmerBluetooth.BT_STATE.CONFIGURING, list);
        for (ShimmerDevice shimmerDevice : list) {
            ShimmerDevice shimmerDeviceBtConnected = getShimmerDeviceBtConnected(shimmerDevice.getMacId());
            int hardwareVersion = shimmerDevice.getHardwareVersion();
            if (shimmerDeviceBtConnected != null && HW_IDS_THAT_SUPPORT_CONFIG_VIA_BT.contains(Integer.valueOf(hardwareVersion)) && shimmerDeviceBtConnected.getClass().isInstance(shimmerDevice)) {
                shimmerDeviceBtConnected.operationPrepare();
                try {
                    shimmerDeviceBtConnected.configureFromClone(shimmerDevice);
                    shimmerDeviceBtConnected.operationStart(ShimmerBluetooth.BT_STATE.CONFIGURING);
                } catch (ShimmerException e) {
                    e.printStackTrace();
                }
            } else {
                printMessage("Hardware ID not supported currently: " + hardwareVersion);
            }
            threadSleep(50L);
        }
    }

    public void setEventTriggered(long j, int i) {
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isStreaming() || shimmerDevice.isConnected()) {
                shimmerDevice.setEventTriggered(j, i);
            }
        }
    }

    public void setEventUntriggered(long j) {
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isStreaming() || shimmerDevice.isConnected()) {
                shimmerDevice.setEventUntrigger(j);
            }
        }
    }

    public void resetEventMarkerValuestoDefault() {
        Iterator<ShimmerDevice> it2 = mMapOfBtConnectedShimmers.values().iterator();
        while (it2.hasNext()) {
            it2.next().resetEventMarkerValuetoDefault();
        }
    }

    public void startTimerCalcReceptionRate() {
        if (this.mTimerTimerCalcReceptionRate == null) {
            Timer timer = new Timer("BluetoothManager_TimerCalcReceptionRate");
            this.mTimerTimerCalcReceptionRate = timer;
            timer.schedule(new TimerTaskCalcReceptionRate(), 0L, this.mCalcReceptionRateMs);
        }
    }

    public void stopTimerCalcReceptionRate() {
        Timer timer = this.mTimerTimerCalcReceptionRate;
        if (timer != null) {
            timer.cancel();
            this.mTimerTimerCalcReceptionRate.purge();
            this.mTimerTimerCalcReceptionRate = null;
        }
    }

    public ShimmerDevice getShimmerDeviceFromName(String str) {
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.getShimmerUserAssignedName().equals(str)) {
                return shimmerDevice;
            }
        }
        return null;
    }

    protected void putShimmerBtConnectedMap(ShimmerDevice shimmerDevice) {
        String btConnectionHandle = shimmerDevice.getBtConnectionHandle();
        if (getShimmerDeviceBtConnected(btConnectionHandle) == null) {
            System.err.println("Putting Shimmer in BT connected map with connectionHandle:" + (btConnectionHandle.isEmpty() ? "EMPTY" : btConnectionHandle));
            mMapOfBtConnectedShimmers.put(btConnectionHandle, shimmerDevice);
        }
    }

    public ShimmerDevice getShimmerDeviceBtConnected(String str) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(str);
        return shimmerDevice == null ? getShimmerDeviceBtConnectedFromMac(str) : shimmerDevice;
    }

    public ShimmerDevice getShimmerDeviceBtConnectedFromMac(String str) {
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.getMacId().toUpperCase().equals(str.toUpperCase())) {
                return shimmerDevice;
            }
        }
        return null;
    }

    public void removeShimmerDeviceBtConnected(String str) {
        mMapOfBtConnectedShimmers.remove(str);
    }

    protected BluetoothDeviceDetails getBluetoothDeviceDetails(String str) {
        if (!str.contains(COMPORT_PREFIX) && !str.contains(COMPORT_PREFIX_MAC)) {
            return getBLEDeviceDetails(str);
        }
        return this.mMapOfParsedBtComPorts.get(str);
    }

    protected BluetoothDeviceDetails getBLEDeviceDetails(String str) {
        return this.mMapOfParsedBLEDevices.get(str);
    }

    public String getStateParsed(String str) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(str);
        return shimmerDevice != null ? shimmerDevice.getBluetoothRadioStateString() : "Not Initialised";
    }

    @Deprecated
    public String getDeviceState(String str) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(str);
        if (shimmerDevice != null) {
            if (shimmerDevice.isConnected() && !shimmerDevice.isStreaming()) {
                return "Connected";
            }
            if (shimmerDevice.isConnected() && shimmerDevice.isStreaming()) {
                return "Streaming";
            }
        }
        return null;
    }

    @Deprecated
    public int getDeviceStateInt(String str) {
        ShimmerDevice shimmerDeviceBtConnected = getShimmerDeviceBtConnected(str);
        if (shimmerDeviceBtConnected != null) {
            if (shimmerDeviceBtConnected.isConnected() && shimmerDeviceBtConnected.isStreaming()) {
                return ShimmerBluetooth.BT_STATE.STREAMING.ordinal();
            }
            return ShimmerBluetooth.BT_STATE.DISCONNECTED.ordinal();
        }
        return ShimmerBluetooth.BT_STATE.DISCONNECTED.ordinal();
    }

    public List<String> getListOfConnectedDevicesUserAssignedName() {
        ArrayList arrayList = new ArrayList();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                arrayList.add(shimmerDevice.getShimmerUserAssignedName());
            }
        }
        return arrayList;
    }

    public List<String> getListOfConnectedDevicesComPort() {
        ArrayList arrayList = new ArrayList();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                arrayList.add(shimmerDevice.getComPort());
            }
        }
        return arrayList;
    }

    public List<ShimmerDevice> getListOfConnectedDevices() {
        ArrayList arrayList = new ArrayList();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                arrayList.add(shimmerDevice);
            }
        }
        return arrayList;
    }

    public List<String[]> getListOfSignalsFromAllDevices() {
        ArrayList arrayList = new ArrayList();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                arrayList.addAll(((ShimmerBluetooth) shimmerDevice).getListofEnabledChannelSignalsandFormats());
            } else {
                boolean z = shimmerDevice instanceof Shimmer4sdk;
            }
        }
        return arrayList;
    }

    public List<String[]> getListOfSignalsFromAllConnectedDevices() {
        ArrayList arrayList = new ArrayList();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                if (shimmerBluetooth.isConnected()) {
                    arrayList.addAll(shimmerBluetooth.getListofEnabledChannelSignalsandFormats());
                }
            } else {
                boolean z = shimmerDevice instanceof Shimmer4sdk;
            }
        }
        return arrayList;
    }

    public String getComPortFromFriendlyName(String str) {
        for (BluetoothDeviceDetails bluetoothDeviceDetails : this.mMapOfParsedBtComPorts.values()) {
            if (bluetoothDeviceDetails.mFriendlyName.equals(str)) {
                return bluetoothDeviceDetails.mComPort;
            }
        }
        return null;
    }

    public List<String[]> getListOfSignalsFromDevices(String str) {
        ArrayList arrayList = new ArrayList();
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(str);
        if (shimmerDevice != null) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                arrayList.addAll(((ShimmerBluetooth) shimmerDevice).getListofEnabledChannelSignalsandFormats());
            } else {
                boolean z = shimmerDevice instanceof Shimmer4sdk;
            }
        }
        return arrayList;
    }

    public void stopConnectionThread(String str) {
        ConnectThread connectThread = this.mapOfConnectionThreads.get(str);
        if (connectThread != null) {
            connectThread.disconnect();
        }
    }

    protected void connectNoninOnyxII(String str, String str2) throws ShimmerException {
        connectThirdPartyDevice(new NoninOnyxIIDevice(str, str2), str, str2);
    }

    protected void connectThirdPartyDevice(ShimmerDevice shimmerDevice, String str, String str2) throws ShimmerException {
        shimmerDevice.setRadio(createNewSerialPortComm(str, str2));
        initializeNewShimmerCommon(shimmerDevice);
        shimmerDevice.connect();
    }

    protected void initializeNewShimmerCommon(ShimmerDevice shimmerDevice) {
        shimmerDevice.addCommunicationRoute(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        putShimmerBtConnectedMap(shimmerDevice);
        putShimmerGlobalMap(shimmerDevice.getMacId(), shimmerDevice);
        addCallBack(shimmerDevice);
        shimmerDevice.updateThreadName();
        if (!shimmerDevice.isFixedShimmerConfigModeSet()) {
            shimmerDevice.setFixedShimmerConfig(this.mFixedShimmerConfigGlobal);
        }
        if ((this.mFixedShimmerConfigGlobal == FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE.NEUHOME || this.mFixedShimmerConfigGlobal == FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE.NEUHOMEGSRONLY) && (shimmerDevice instanceof ShimmerBluetooth)) {
            ((ShimmerBluetooth) shimmerDevice).setSetupDeviceWhileConnecting(true);
        }
        shimmerDevice.setAutoStartStreaming(this.mAutoStartStreaming);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFeedbackOnConnectStartException(String str) {
        ConnectionExceptionListener connectionExceptionListener = this.connectionExceptionListener;
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectStartException(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFeedbackOnConnectionStart(String str) {
        ConnectionExceptionListener connectionExceptionListener = this.connectionExceptionListener;
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectionStart(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFeedbackOnConnectionException(ShimmerException shimmerException) {
        ConnectionExceptionListener connectionExceptionListener = this.connectionExceptionListener;
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectionException(shimmerException);
        }
    }

    private void threadSleep(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class TimerTaskCalcReceptionRate extends TimerTask {
        public TimerTaskCalcReceptionRate() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            for (ShimmerDevice shimmerDevice : ShimmerBluetoothManager.mMapOfBtConnectedShimmers.values()) {
                if (shimmerDevice.isStreaming()) {
                    shimmerDevice.calculatePacketReceptionRateCurrent(ShimmerBluetoothManager.this.mCalcReceptionRateMs);
                }
            }
        }
    }

    protected class ConnectThread extends Thread {
        String bluetoothAddress;
        String bluetoothModuleDeviceName;
        String comPort;
        boolean connectThroughComPort;
        String connectionHandle;
        HwDriverShimmerDeviceDetails.DEVICE_TYPE deviceTypeDetected;
        ExpansionBoardDetails expansionBoardDetails;
        BluetoothDeviceDetails mDeviceDetails;
        ShimmerRadioInitializer shimmerRadioInitializer;
        ShimmerVerObject shimmerVerObject;

        public ConnectThread(String str, ShimmerVerObject shimmerVerObject, ExpansionBoardDetails expansionBoardDetails) {
            this.deviceTypeDetected = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
            this.shimmerRadioInitializer = null;
            this.comPort = str;
            this.connectionHandle = str;
            this.shimmerVerObject = shimmerVerObject;
            this.expansionBoardDetails = expansionBoardDetails;
            this.connectThroughComPort = true;
            setName(getClass().getSimpleName() + "_" + this.connectionHandle);
        }

        public ConnectThread(String str) {
            this.deviceTypeDetected = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
            this.shimmerRadioInitializer = null;
            this.bluetoothAddress = str;
            this.connectionHandle = str;
            this.connectThroughComPort = false;
            setName(getClass().getSimpleName() + "_" + this.connectionHandle);
        }

        public ConnectThread(BluetoothDeviceDetails bluetoothDeviceDetails) {
            this.deviceTypeDetected = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
            this.shimmerRadioInitializer = null;
            ShimmerBluetoothManager.this.directConnectUnknownShimmer = false;
            this.bluetoothAddress = bluetoothDeviceDetails.mShimmerMacId;
            this.connectionHandle = bluetoothDeviceDetails.mShimmerMacId;
            this.connectThroughComPort = false;
            this.mDeviceDetails = bluetoothDeviceDetails;
            this.deviceTypeDetected = bluetoothDeviceDetails.mDeviceTypeDetected;
            this.mDeviceDetails = bluetoothDeviceDetails;
            setName(getClass().getSimpleName() + "_" + this.connectionHandle);
        }

        public void disconnect() {
            ShimmerRadioInitializer shimmerRadioInitializer = this.shimmerRadioInitializer;
            if (shimmerRadioInitializer != null) {
                try {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();
                } catch (ShimmerException e) {
                    e.printStackTrace();
                }
            }
            ShimmerBluetoothManager.this.disconnectShimmer(this.bluetoothAddress);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (ShimmerBluetoothManager.this.directConnectUnknownShimmer) {
                try {
                    connectUnknownShimmer();
                    return;
                } catch (ShimmerException e) {
                    System.err.println(e.getErrStringFormatted());
                    return;
                }
            }
            try {
                BluetoothDeviceDetails bluetoothDeviceDetails = ShimmerBluetoothManager.this.getBluetoothDeviceDetails(this.connectionHandle);
                BluetoothDeviceDetails bluetoothDeviceDetails2 = this.mDeviceDetails;
                if (bluetoothDeviceDetails2 != null) {
                    bluetoothDeviceDetails = bluetoothDeviceDetails2;
                }
                if (bluetoothDeviceDetails == null) {
                    ShimmerBluetoothManager.this.printMessage("NULL BluetoothDeviceDetails for ConnectionHandle: " + this.connectionHandle + ", returning...");
                    ShimmerBluetoothManager.this.sendFeedbackOnConnectStartException(this.connectionHandle);
                    return;
                }
                setBluetoothDeviceDetails(bluetoothDeviceDetails);
                ShimmerBluetoothManager.this.sendFeedbackOnConnectionStart(this.connectionHandle);
                if (this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.NONIN_ONYX_II) {
                    ShimmerBluetoothManager.this.connectNoninOnyxII(this.comPort, this.bluetoothAddress);
                }
                if (this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.VERISENSE) {
                    ShimmerBluetoothManager.this.connectVerisenseDevice(bluetoothDeviceDetails);
                    return;
                }
                if (this.deviceTypeDetected != HwDriverShimmerDeviceDetails.DEVICE_TYPE.LUMAFIT && this.deviceTypeDetected != HwDriverShimmerDeviceDetails.DEVICE_TYPE.ARDUINO) {
                    ShimmerDevice shimmerGlobalMap = ShimmerBluetoothManager.this.getShimmerGlobalMap(this.bluetoothAddress);
                    if (shimmerGlobalMap == null || (shimmerGlobalMap instanceof ShimmerShell)) {
                        shimmerGlobalMap = createShimmerIfKnown();
                    }
                    if (shimmerGlobalMap != null && !(shimmerGlobalMap instanceof ShimmerShell)) {
                        ShimmerBluetoothManager.this.printMessage("Connecting to " + shimmerGlobalMap.getClass().getSimpleName() + " with connection handle = " + (this.connectThroughComPort ? this.comPort : this.bluetoothAddress));
                        if (this.connectThroughComPort) {
                            if (!this.comPort.contains(ShimmerBluetoothManager.COMPORT_PREFIX) && !this.comPort.contains(ShimmerBluetoothManager.COMPORT_PREFIX_MAC)) {
                                ShimmerBluetoothManager.this.connectShimmer3BleGrpc(bluetoothDeviceDetails);
                                return;
                            } else {
                                ShimmerBluetoothManager.this.connectExistingShimmer(shimmerGlobalMap, this.comPort, this.bluetoothAddress);
                                return;
                            }
                        }
                        ShimmerBluetoothManager.this.connectExistingShimmer(shimmerGlobalMap, this.bluetoothAddress);
                        return;
                    }
                    connectUnknownShimmer();
                }
            } catch (ShimmerException e2) {
                ShimmerBluetoothManager.this.sendFeedbackOnConnectionException(e2);
                ShimmerBluetoothManager.this.printMessage(e2.getErrStringFormatted());
            }
        }

        private ShimmerDevice createShimmerIfKnown() {
            ShimmerDevice shimmerDeviceCreateNewShimmer3;
            HwDriverShimmerDeviceDetails.DEVICE_TYPE device_typeUseMockHWID = ShimmerBluetoothManager.this.useMockHWID(this.deviceTypeDetected);
            this.deviceTypeDetected = device_typeUseMockHWID;
            if (device_typeUseMockHWID == HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3 || this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3_OUTPUT || this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER_ECG_MD) {
                shimmerDeviceCreateNewShimmer3 = ShimmerBluetoothManager.this.createNewShimmer3(this.comPort, this.bluetoothAddress);
            } else if (this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER4) {
                shimmerDeviceCreateNewShimmer3 = ShimmerBluetoothManager.this.createNewShimmer4(this.comPort, this.bluetoothAddress);
            } else {
                shimmerDeviceCreateNewShimmer3 = this.deviceTypeDetected == HwDriverShimmerDeviceDetails.DEVICE_TYPE.SWEATCH ? ShimmerBluetoothManager.this.createNewSweatchDevice(this.comPort, this.bluetoothAddress) : null;
            }
            if (shimmerDeviceCreateNewShimmer3 != null) {
                ShimmerVerObject shimmerVerObject = this.shimmerVerObject;
                if (shimmerVerObject != null) {
                    shimmerDeviceCreateNewShimmer3.setShimmerVersionObjectAndCreateSensorMap(shimmerVerObject);
                }
                ExpansionBoardDetails expansionBoardDetails = this.expansionBoardDetails;
                if (expansionBoardDetails != null) {
                    shimmerDeviceCreateNewShimmer3.setExpansionBoardDetailsAndCreateSensorMap(expansionBoardDetails);
                }
            }
            return shimmerDeviceCreateNewShimmer3;
        }

        private BluetoothDeviceDetails setBluetoothDeviceDetails(BluetoothDeviceDetails bluetoothDeviceDetails) {
            if (bluetoothDeviceDetails != null) {
                this.bluetoothAddress = bluetoothDeviceDetails.mShimmerMacId;
                this.bluetoothModuleDeviceName = bluetoothDeviceDetails.mFriendlyName;
                this.deviceTypeDetected = bluetoothDeviceDetails.mDeviceTypeDetected;
            } else {
                this.bluetoothAddress = UtilShimmer.MAC_ADDRESS_ZEROS;
                this.bluetoothModuleDeviceName = "Unknown";
                this.deviceTypeDetected = HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN;
            }
            return bluetoothDeviceDetails;
        }

        protected void connectUnknownShimmer() throws ShimmerException {
            ShimmerBluetoothManager.this.printMessage("Connecting to new Shimmer with connection handle = " + (this.connectThroughComPort ? this.comPort : this.bluetoothAddress));
            this.shimmerRadioInitializer = new ShimmerRadioInitializer();
            final AbstractSerialPortHal abstractSerialPortHalCreateNewSerialPortComm = ShimmerBluetoothManager.this.createNewSerialPortComm(this.comPort, this.bluetoothAddress);
            this.shimmerRadioInitializer.setSerialCommPort(abstractSerialPortHalCreateNewSerialPortComm);
            abstractSerialPortHalCreateNewSerialPortComm.addByteLevelDataCommListener(new ByteLevelDataCommListener() { // from class: com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager.ConnectThread.1
                @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
                public void eventConnected() {
                    ConnectThread connectThread = ConnectThread.this;
                    connectThread.resolveUnknownShimmer(connectThread.shimmerRadioInitializer);
                }

                @Override // com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener
                public void eventDisconnected() {
                    ShimmerBluetoothManager.this.sendFeedbackOnConnectStartException(abstractSerialPortHalCreateNewSerialPortComm.getConnectionHandle());
                }
            });
            abstractSerialPortHalCreateNewSerialPortComm.connect();
        }

        protected ShimmerDevice resolveUnknownShimmer(ShimmerRadioInitializer shimmerRadioInitializer) {
            ShimmerDevice shimmerDeviceCreateNewShimmer3 = null;
            try {
                ShimmerVerObject shimmerVerObject = shimmerRadioInitializer.readShimmerVerObject();
                if (shimmerVerObject.isShimmerGen2() || shimmerVerObject.isShimmerGen3() || shimmerVerObject.isShimmerGen3R()) {
                    shimmerDeviceCreateNewShimmer3 = ShimmerBluetoothManager.this.createNewShimmer3(shimmerRadioInitializer, this.bluetoothAddress);
                } else if (shimmerVerObject.isShimmerGen4()) {
                    shimmerDeviceCreateNewShimmer3 = ShimmerBluetoothManager.this.createNewShimmer4(shimmerRadioInitializer, this.bluetoothAddress);
                } else if (shimmerVerObject.isSweatchDevice()) {
                    shimmerDeviceCreateNewShimmer3 = ShimmerBluetoothManager.this.createNewSweatchDevice(shimmerRadioInitializer, this.bluetoothAddress);
                }
                if (shimmerDeviceCreateNewShimmer3 != null) {
                    shimmerDeviceCreateNewShimmer3.setComPort(this.comPort);
                    shimmerDeviceCreateNewShimmer3.setMacIdFromUart(this.bluetoothAddress);
                    shimmerDeviceCreateNewShimmer3.setShimmerVersionObjectAndCreateSensorMap(shimmerVerObject);
                    ShimmerBluetoothManager.this.initializeNewShimmerCommon(shimmerDeviceCreateNewShimmer3);
                } else {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();
                }
            } catch (ShimmerException e) {
                e.printStackTrace();
                try {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return shimmerDeviceCreateNewShimmer3;
        }
    }
}
