package com.shimmerresearch.managers.bluetoothManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.shimmerresearch.bluetooth.BluetoothProgressReportAll;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerRadioInitializer;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ByteLevelDataCommListener;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.Configuration.COMMUNICATION_TYPE;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerShell;
import com.shimmerresearch.driverUtilities.BluetoothDeviceDetails;
import com.shimmerresearch.driverUtilities.ExpansionBoardDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerDetails.HW_ID;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails.DEVICE_TYPE;
import com.shimmerresearch.exceptions.ConnectionExceptionListener;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.lsm303.SensorLSM303DLHC;
import com.shimmerresearch.shimmerConfig.FixedShimmerConfigs.FIXED_SHIMMER_CONFIG_MODE;
import com.shimmerresearch.thirdpartyDevices.noninOnyxII.NoninOnyxIIDevice;
import com.shimmerresearch.verisense.VerisenseDevice;
import com.shimmerresearch.verisense.communication.VerisenseProtocolByteCommunication;

public abstract class ShimmerBluetoothManager {

    public static final long SLEEP_BETWEEN_GROUP_ACTIONS_MS = 50;
    public static final String COMPORT_PREFIX = "COM";
    public static final String COMPORT_PREFIX_MAC = "/dev/";
    protected static final boolean USE_INFOMEM_CONFIG_METHOD = true;
    private static final List<Integer> HW_IDS_THAT_SUPPORT_CONFIG_VIA_BT = Arrays.asList(
            HW_ID.SHIMMER_3, HW_ID.SHIMMER_3R, HW_ID.SWEATCH, HW_ID.SHIMMER_4_SDK,
            HW_ID.VERISENSE_IMU, HW_ID.VERISENSE_DEV_BRD, HW_ID.VERISENSE_GSR_PLUS, HW_ID.VERISENSE_PPG, HW_ID.VERISENSE_PULSE_PLUS);
    public static ConcurrentHashMap<String, ShimmerDevice> mMapOfBtConnectedShimmers = new ConcurrentHashMap<String, ShimmerDevice>(7);
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBtComPorts = new TreeMap<String, BluetoothDeviceDetails>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBLEDevices = new TreeMap<String, BluetoothDeviceDetails>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBtComPortsDeepCopy = new TreeMap<String, BluetoothDeviceDetails>();
    public TreeMap<String, BluetoothDeviceDetails> mMapOfParsedBLEDevicesDeepCopy = new TreeMap<String, BluetoothDeviceDetails>();
    public HashMap<String, ConnectThread> mapOfConnectionThreads = new HashMap<String, ConnectThread>();
    protected boolean directConnectUnknownShimmer = false;
    protected int mSyncTrainingIntervalInSeconds = 15;
    protected int msDelayBetweenSetCommands = 0;
    protected BluetoothProgressReportAll mProgressReportAll;

    transient protected Timer mTimerTimerCalcReceptionRate;
    protected FIXED_SHIMMER_CONFIG_MODE mFixedShimmerConfigGlobal = FIXED_SHIMMER_CONFIG_MODE.NONE;
    protected boolean mAutoStartStreaming = false;
    private ConnectionExceptionListener connectionExceptionListener;
    private int mCalcReceptionRateMs = 1000;

    public ShimmerBluetoothManager() {
        startTimerCalcReceptionRate();
    }

    protected abstract void loadBtShimmers(Object... params);

    public abstract void addCallBack(BasicProcessWithCallBack basicProcess);

    public abstract void printMessage(String message);

    public abstract ShimmerDevice getShimmerGlobalMap(String bluetoothAddress);

    public abstract void putShimmerGlobalMap(String bluetoothAddress, ShimmerDevice shimmerDevice);

    protected abstract AbstractSerialPortHal createNewSerialPortComm(String comPort, String bluetoothAddress);

    protected abstract void connectExistingShimmer(Object... params) throws ShimmerException;


    protected abstract ShimmerDevice createNewShimmer3(String comPort, String bluetoothAddress);

    protected abstract ShimmerDevice createNewShimmer3(ShimmerRadioInitializer bldc, String bluetoothAddress);

    protected abstract Shimmer4sdk createNewShimmer4(String comPort, String bluetoothAddress);

    protected abstract Shimmer4sdk createNewShimmer4(ShimmerRadioInitializer radioInitializer, String bluetoothAddress);

    public void connectShimmerThroughCommPort(String comPort) {
        connectShimmer(comPort, null, null);
    }

    public void connectShimmer(String connectionHandle, ShimmerVerObject shimmerVerObject, ExpansionBoardDetails expansionBoardDetails) {
        BluetoothDeviceDetails bluetoothDetails = getBluetoothDeviceDetails(connectionHandle);

        if (bluetoothDetails != null) {
            ConnectThread connectThread = new ConnectThread(connectionHandle, shimmerVerObject, expansionBoardDetails);
            mapOfConnectionThreads.put(connectThread.connectionHandle, connectThread);
            connectThread.start();
        } else {
            sendFeedbackOnConnectStartException(connectionHandle);
        }
    }

    public void connectShimmerThroughBTAddress(String bluetoothAddress) {
        ConnectThread connectThread = new ConnectThread(bluetoothAddress);
        mapOfConnectionThreads.put(connectThread.connectionHandle, connectThread);
        connectThread.start();
    }

    public void connectShimmerThroughBTAddress(BluetoothDeviceDetails deviceDetails) {
        ConnectThread connectThread = new ConnectThread(deviceDetails);
        connectThread.start();
    }

    public void disconnectShimmer(String connectionHandle) {
        printMessage("Attempting to disconnect from connection handle = " + connectionHandle);
        ShimmerDevice shimmerDevice = getShimmerDeviceBtConnected(connectionHandle);
        if (shimmerDevice != null) {
            printMessage("Disconnecting from " + shimmerDevice.getClass().getSimpleName() + " with connection handle = " + connectionHandle);
            disconnectShimmer(shimmerDevice);
        }
    }

    public void disconnectAllDevices() {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice shimmerDevice = iterator.next();
            disconnectShimmer(shimmerDevice);
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
        mMapOfBtConnectedShimmers.clear();
    }

    public void disconnectShimmer(ShimmerDevice shimmerDevice) {
        try {
            shimmerDevice.disconnect();
        } catch (
                ShimmerException e) {
            printMessage(e.getErrStringFormatted());
        }
    }

    public void startStreaming(String bluetoothAddress) throws ShimmerException {
        ShimmerDevice shimmerDevice = getShimmerDeviceBtConnectedFromMac(bluetoothAddress);
        if (shimmerDevice != null) {
            startStreaming(shimmerDevice);
        }
    }

    public void startStreamingAllDevices() {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice shimmerDevice = iterator.next();
            try {
                startStreaming(shimmerDevice);
            } catch (
                    ShimmerException e) {
                e.printStackTrace();
            }
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }

    public void startStreaming(ShimmerDevice shimmerDevice) throws ShimmerException {
        if (shimmerDevice.isConnected()) {
            shimmerDevice.startStreaming();
        }
    }

    public void stopStreamingAllDevices() {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice shimmerDevice = iterator.next();
            try {
                stopStreaming(shimmerDevice);
            } catch (
                    ShimmerException e) {
                e.printStackTrace();
            }
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }

    public void stopStreaming(String bluetoothAddress) throws ShimmerException {
        ShimmerDevice shimmerDevice = getShimmerDeviceBtConnectedFromMac(bluetoothAddress);
        if (shimmerDevice != null) {
            stopStreaming(shimmerDevice);
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

    public void startSDLogging(List<ShimmerDevice> listOfShimmers) {
        for (ShimmerDevice shimmerDevice : listOfShimmers) {
            startSDLogging(shimmerDevice);
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }

    public void stopSDLogging(ShimmerDevice shimmerDevice) {
        shimmerDevice.stopSDLogging();
    }

    public void stopSDLogging(List<ShimmerDevice> listOfShimmers) {
        for (ShimmerDevice shimmerDevice : listOfShimmers) {
            stopSDLogging(shimmerDevice);
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }


    public void startStreamingAndLogging(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.isConnected()) {
            shimmerDevice.startDataLogAndStreaming();
        }
    }

    public void stopStreamingAndLogging(ShimmerDevice shimmerDevice) {
        if (shimmerDevice.isConnected() && (shimmerDevice.isSDLogging() || shimmerDevice.isStreaming())) {
            shimmerDevice.stopStreamingAndLogging();
        }
    }

    public void toggleLed(String connectionHandle) {
        ShimmerDevice selectedShimmer = getShimmerDeviceBtConnected(connectionHandle);
        if (selectedShimmer != null) {
            toggleLed(selectedShimmer);
        }
    }

    public void toggleLed(List<String> listOfShimmersToToggle) {
        for (String connectionHandle : listOfShimmersToToggle) {
            toggleLed(connectionHandle);
            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }

    public void toggleLed(ShimmerDevice selectedShimmer) {
        if (selectedShimmer != null && selectedShimmer.isConnected()) {
            if (selectedShimmer instanceof ShimmerBluetooth) {
                ((ShimmerBluetooth) selectedShimmer).toggleLed();
            } else if (selectedShimmer instanceof Shimmer4sdk) {
                ((Shimmer4sdk) selectedShimmer).toggleLed();
            }
        }
    }


    @Deprecated
    public void setCadenceBTConfig(ShimmerDevice device) {
        ShimmerDevice shimmerDevice = getShimmerDeviceBtConnected(device.getBtConnectionHandle());

        if (shimmerDevice != null) {
            shimmerDevice.operationPrepare();

            shimmerDevice.setDefaultShimmerConfiguration();
            shimmerDevice.disableAllAlgorithms();
            shimmerDevice.disableAllSensors();

            if (shimmerDevice.getSensorIdsSet().contains(Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL)) {
                shimmerDevice.setSensorEnabledState(Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL, true);

                shimmerDevice.setConfigValueUsingConfigLabel(
                        Shimmer3.SENSOR_ID.SHIMMER_LSM303_ACCEL,
                        SensorLSM303DLHC.GuiLabelConfig.LSM303_ACCEL_RANGE,
                        1);
            }

            shimmerDevice.setShimmerAndSensorsSamplingRate(51.20);

            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                shimmerBluetooth.writeConfigBytes(shimmerDevice.getShimmerConfigBytes());
                shimmerBluetooth.writeEnabledSensors(shimmerDevice.getEnabledSensors());
            }

            shimmerDevice.operationStart(BT_STATE.CONFIGURING);
        }
    }

    public void configureShimmer(ShimmerDevice shimmerClone, boolean writeCalibrationDump) {
        if (shimmerClone instanceof ShimmerBluetooth) {
            ((ShimmerBluetooth) shimmerClone).setWriteCalibrationDumpWhenConfiguringForClone(writeCalibrationDump);
        }
        configureShimmers(Arrays.asList(shimmerClone));
    }

    public void configureShimmer(ShimmerDevice shimmerClone) {
        configureShimmers(Arrays.asList(shimmerClone));
    }

    public void configureShimmers(List<ShimmerDevice> listOfShimmerClones) {

        mProgressReportAll = new BluetoothProgressReportAll(BT_STATE.CONFIGURING, listOfShimmerClones);

        for (ShimmerDevice cloneShimmer : listOfShimmerClones) {

            ShimmerDevice originalShimmerDevice = getShimmerDeviceBtConnected(cloneShimmer.getMacId());
            int cloneHwId = cloneShimmer.getHardwareVersion();
            if (originalShimmerDevice != null
                    && HW_IDS_THAT_SUPPORT_CONFIG_VIA_BT.contains(cloneHwId)
                    && originalShimmerDevice.getClass().isInstance(cloneShimmer)) {
                originalShimmerDevice.operationPrepare();
                try {
                    originalShimmerDevice.configureFromClone(cloneShimmer);
                    originalShimmerDevice.operationStart(BT_STATE.CONFIGURING);
                } catch (
                        ShimmerException e) {
                    e.printStackTrace();
                }
            } else {
                printMessage("Hardware ID not supported currently: " + cloneHwId);
            }

            threadSleep(SLEEP_BETWEEN_GROUP_ACTIONS_MS);
        }
    }


    public void setEventTriggered(long eventCode, int eventType) {
        for (ShimmerDevice spc : mMapOfBtConnectedShimmers.values()) {
            if (spc.isStreaming() || spc.isConnected()) {
                spc.setEventTriggered(eventCode, eventType);
            }
        }
    }

    public void setEventUntriggered(long eventCode) {
        for (ShimmerDevice spc : mMapOfBtConnectedShimmers.values()) {
            if (spc.isStreaming() || spc.isConnected()) {
                spc.setEventUntrigger(eventCode);
            }
        }
    }

    public void resetEventMarkerValuestoDefault() {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice shimmerDevice = iterator.next();
            shimmerDevice.resetEventMarkerValuetoDefault();
        }
    }


    public void startTimerCalcReceptionRate() {
        if (mTimerTimerCalcReceptionRate == null) {
            mTimerTimerCalcReceptionRate = new Timer("BluetoothManager_TimerCalcReceptionRate");
            mTimerTimerCalcReceptionRate.schedule(new TimerTaskCalcReceptionRate(), 0, mCalcReceptionRateMs);
        }
    }

    public void stopTimerCalcReceptionRate() {
        if (mTimerTimerCalcReceptionRate != null) {
            mTimerTimerCalcReceptionRate.cancel();
            mTimerTimerCalcReceptionRate.purge();
            mTimerTimerCalcReceptionRate = null;
        }
    }

    public ShimmerDevice getShimmerDeviceFromName(String deviceName) {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice stemp = (ShimmerDevice) iterator.next();
            if (stemp.getShimmerUserAssignedName().equals(deviceName)) {
                return stemp;
            }
        }
        return null;
    }


    protected void putShimmerBtConnectedMap(ShimmerDevice shimmerDevice) {
        String connectionHandle = shimmerDevice.getBtConnectionHandle();
        ShimmerDevice existingShimmer = getShimmerDeviceBtConnected(connectionHandle);
        if (existingShimmer == null) {
            System.err.println("Putting Shimmer in BT connected map with connectionHandle:" + (connectionHandle.isEmpty() ? "EMPTY" : connectionHandle));
            mMapOfBtConnectedShimmers.put(connectionHandle, shimmerDevice);
        }
    }

    public ShimmerDevice getShimmerDeviceBtConnected(String connectionHandle) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(connectionHandle);
        if (shimmerDevice == null) {
            shimmerDevice = getShimmerDeviceBtConnectedFromMac(connectionHandle);
        }
        return shimmerDevice;
    }

    public ShimmerDevice getShimmerDeviceBtConnectedFromMac(String macAddress) {
        Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
        while (iterator.hasNext()) {
            ShimmerDevice shimmerDevice = iterator.next();
            if (shimmerDevice.getMacId().toUpperCase().equals(macAddress.toUpperCase())) {
                return shimmerDevice;
            }
        }
        return null;
    }

    public void removeShimmerDeviceBtConnected(String connectionHandle) {
        mMapOfBtConnectedShimmers.remove(connectionHandle);
    }

    protected BluetoothDeviceDetails getBluetoothDeviceDetails(String connectionHandle) {
        if (!connectionHandle.contains(COMPORT_PREFIX) && !connectionHandle.contains(COMPORT_PREFIX_MAC)) {
            return getBLEDeviceDetails(connectionHandle);
        }
        return mMapOfParsedBtComPorts.get(connectionHandle);
    }

    protected BluetoothDeviceDetails getBLEDeviceDetails(String connectionHandle) {
        return mMapOfParsedBLEDevices.get(connectionHandle);
    }

    public ConcurrentHashMap<String, ShimmerDevice> getMapOfBtConnectedShimmers() {
        return mMapOfBtConnectedShimmers;
    }

    public String getStateParsed(String comPort) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(comPort);
        if (shimmerDevice != null) {
            return shimmerDevice.getBluetoothRadioStateString();
        }
        return "Not Initialised";
    }

    @Deprecated
    public String getDeviceState(String comPort) {
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(comPort);
        if (shimmerDevice != null) {
            if (shimmerDevice.isConnected()
                    && !shimmerDevice.isStreaming()) {
                return "Connected";
            } else if (shimmerDevice.isConnected()
                    && shimmerDevice.isStreaming()) {
                return "Streaming";
            } else {
                return null;

            }
        } else {
            return null;
        }
    }

    @Deprecated
    public int getDeviceStateInt(String comPort) {

        ShimmerDevice shimmerDevice = getShimmerDeviceBtConnected(comPort);
        if (shimmerDevice != null) {
            if (shimmerDevice.isConnected()
                    && shimmerDevice.isStreaming()) {
                return BT_STATE.STREAMING.ordinal();
            } else {
                return BT_STATE.DISCONNECTED.ordinal();
            }
        } else {
            return BT_STATE.DISCONNECTED.ordinal();
        }
    }

    public List<String> getListOfConnectedDevicesUserAssignedName() {
        List<String> list = new ArrayList<String>();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                list.add(shimmerDevice.getShimmerUserAssignedName());
            }
        }
        return list;
    }

    public List<String> getListOfConnectedDevicesComPort() {
        List<String> list = new ArrayList<String>();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                list.add(shimmerDevice.getComPort());
            }
        }
        return list;
    }

    public List<ShimmerDevice> getListOfConnectedDevices() {
        List<ShimmerDevice> list = new ArrayList<ShimmerDevice>();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice.isConnected()) {
                list.add(shimmerDevice);
            }
        }
        return list;
    }

    public List<String[]> getListOfSignalsFromAllDevices() {
        List<String[]> list = new ArrayList<String[]>();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth spc = (ShimmerBluetooth) shimmerDevice;
                list.addAll(spc.getListofEnabledChannelSignalsandFormats());
            } else if (shimmerDevice instanceof Shimmer4sdk) {

            }
        }
        return list;
    }

    public List<String[]> getListOfSignalsFromAllConnectedDevices() {
        List<String[]> list = new ArrayList<String[]>();
        for (ShimmerDevice shimmerDevice : mMapOfBtConnectedShimmers.values()) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth spc = (ShimmerBluetooth) shimmerDevice;
                if (spc.isConnected()) {
                    list.addAll(spc.getListofEnabledChannelSignalsandFormats());
                }
            } else if (shimmerDevice instanceof Shimmer4sdk) {

            }
        }
        return list;
    }

    public String getComPortFromFriendlyName(String friendlyName) {
        for (BluetoothDeviceDetails bdd : mMapOfParsedBtComPorts.values()) {
            if (bdd.mFriendlyName.equals(friendlyName)) {
                return bdd.mComPort;
            }
        }
        return null;
    }

    public List<String[]> getListOfSignalsFromDevices(String address) {
        List<String[]> list = new ArrayList<String[]>();
        ShimmerDevice shimmerDevice = mMapOfBtConnectedShimmers.get(address);
        if (shimmerDevice != null) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth spc = (ShimmerBluetooth) shimmerDevice;
                list.addAll(spc.getListofEnabledChannelSignalsandFormats());
            } else if (shimmerDevice instanceof Shimmer4sdk) {

            }
        }
        return list;
    }

    public void stopConnectionThread(String connectionHandle) {
        ConnectThread connectThread = mapOfConnectionThreads.get(connectionHandle);
        if (connectThread != null) {
            connectThread.disconnect();
        }
    }


    protected void connectVerisenseDevice(BluetoothDeviceDetails bdd) {

    }

    protected void connectShimmer3BleGrpc(BluetoothDeviceDetails bdd) {

    }

    protected void connectNoninOnyxII(String comPort, String bluetoothAddress) throws ShimmerException {
        NoninOnyxIIDevice noninDevice = new NoninOnyxIIDevice(comPort, bluetoothAddress);
        connectThirdPartyDevice(noninDevice, comPort, bluetoothAddress);
    }

    protected void connectThirdPartyDevice(ShimmerDevice shimmerDevice, String comPort, String bluetoothAddress) throws ShimmerException {
        shimmerDevice.setRadio(createNewSerialPortComm(comPort, bluetoothAddress));

        initializeNewShimmerCommon(shimmerDevice);

        shimmerDevice.connect();
    }

    protected ShimmerDevice createNewSweatchDevice(String comPort, String bluetoothAddress) {
        return null;
    }


    protected ShimmerDevice createNewSweatchDevice(ShimmerRadioInitializer shimmerRadioInitializer, String bluetoothAddress) {
        return null;
    }

    protected void initializeNewShimmerCommon(ShimmerDevice shimmerDevice) {
        shimmerDevice.addCommunicationRoute(COMMUNICATION_TYPE.BLUETOOTH);

        putShimmerBtConnectedMap(shimmerDevice);
        putShimmerGlobalMap(shimmerDevice.getMacId(), shimmerDevice);

        addCallBack(shimmerDevice);
        shimmerDevice.updateThreadName();

        if (!shimmerDevice.isFixedShimmerConfigModeSet()) {
            shimmerDevice.setFixedShimmerConfig(mFixedShimmerConfigGlobal);
        }
        if (mFixedShimmerConfigGlobal == FIXED_SHIMMER_CONFIG_MODE.NEUHOME || mFixedShimmerConfigGlobal == FIXED_SHIMMER_CONFIG_MODE.NEUHOMEGSRONLY) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ((ShimmerBluetooth) shimmerDevice).setSetupDeviceWhileConnecting(true);
            }
        }
        shimmerDevice.setAutoStartStreaming(mAutoStartStreaming);
    }

    public void setFixedConfig(FIXED_SHIMMER_CONFIG_MODE fixedConfig) {
        this.mFixedShimmerConfigGlobal = fixedConfig;
    }

    public void setAutoStartStreaming(boolean state) {
        mAutoStartStreaming = state;
    }

    public void setConnectionExceptionListener(ConnectionExceptionListener listener) {
        this.connectionExceptionListener = listener;
    }

    private void sendFeedbackOnConnectStartException(String connectionHandle) {
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectStartException(connectionHandle);
        }
    }

    private void sendFeedbackOnConnectionStart(String connectionHandle) {
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectionStart(connectionHandle);
        }
    }

    private void sendFeedbackOnConnectionException(ShimmerException e) {
        if (connectionExceptionListener != null) {
            connectionExceptionListener.onConnectionException(e);
        }
    }

    private void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DEVICE_TYPE useMockHWID(DEVICE_TYPE deviceTypeDetected) {
        return deviceTypeDetected;
    }

    public class TimerTaskCalcReceptionRate extends TimerTask {
        public void run() {
            Iterator<ShimmerDevice> iterator = mMapOfBtConnectedShimmers.values().iterator();
            while (iterator.hasNext()) {
                ShimmerDevice shimmerDevice = (ShimmerDevice) iterator.next();
                if (shimmerDevice.isStreaming()) {
                    shimmerDevice.calculatePacketReceptionRateCurrent(mCalcReceptionRateMs);
                }
            }
        }
    }

    protected class ConnectThread extends Thread {

        String comPort;
        String bluetoothAddress;
        String bluetoothModuleDeviceName;
        ShimmerVerObject shimmerVerObject;
        ExpansionBoardDetails expansionBoardDetails;
        DEVICE_TYPE deviceTypeDetected = DEVICE_TYPE.UNKOWN;
        boolean connectThroughComPort;
        String connectionHandle;
        BluetoothDeviceDetails mDeviceDetails;
        ShimmerRadioInitializer shimmerRadioInitializer = null;

        public ConnectThread(String comPort, ShimmerVerObject shimmerVerObject, ExpansionBoardDetails expansionBoardDetails) {
            this.comPort = comPort;
            this.connectionHandle = comPort;
            this.shimmerVerObject = shimmerVerObject;
            this.expansionBoardDetails = expansionBoardDetails;
            this.connectThroughComPort = true;
            this.setName(getClass().getSimpleName() + "_" + connectionHandle);
        }

        public ConnectThread(String macAddress) {
            this.bluetoothAddress = macAddress;
            this.connectionHandle = macAddress;
            this.connectThroughComPort = false;
            this.setName(getClass().getSimpleName() + "_" + connectionHandle);
        }

        public ConnectThread(BluetoothDeviceDetails devDetails) {
            directConnectUnknownShimmer = false;
            this.bluetoothAddress = devDetails.mShimmerMacId;
            this.connectionHandle = devDetails.mShimmerMacId;
            this.connectThroughComPort = false;
            this.mDeviceDetails = devDetails;
            deviceTypeDetected = devDetails.mDeviceTypeDetected;
            mDeviceDetails = devDetails;
            this.setName(getClass().getSimpleName() + "_" + connectionHandle);
        }

        public void disconnect() {
            if (shimmerRadioInitializer != null) {
                try {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();
                } catch (
                        ShimmerException e) {
                    e.printStackTrace();
                }
            }
            disconnectShimmer(bluetoothAddress);
        }

        @Override
        public void run() {
            if (directConnectUnknownShimmer) {
                try {
                    connectUnknownShimmer();
                } catch (
                        ShimmerException e) {
                    System.err.println(e.getErrStringFormatted());
                    return;
                }
            } else {
                try {
                    BluetoothDeviceDetails bluetoothDetails = getBluetoothDeviceDetails(connectionHandle);

                    if (mDeviceDetails != null) {
                        bluetoothDetails = mDeviceDetails;
                    }
                    if (bluetoothDetails == null) {
                        printMessage("NULL BluetoothDeviceDetails for ConnectionHandle: " + connectionHandle + ", returning...");
                        sendFeedbackOnConnectStartException(connectionHandle);
                        return;
                    }

                    setBluetoothDeviceDetails(bluetoothDetails);

                    sendFeedbackOnConnectionStart(connectionHandle);

                    if (deviceTypeDetected == DEVICE_TYPE.NONIN_ONYX_II) {
                        connectNoninOnyxII(comPort, bluetoothAddress);
                    }
                    if (deviceTypeDetected == DEVICE_TYPE.VERISENSE) {
                        connectVerisenseDevice(bluetoothDetails);
                    } else if (deviceTypeDetected == DEVICE_TYPE.LUMAFIT) {
                    } else if (deviceTypeDetected == DEVICE_TYPE.ARDUINO) {
                    } else {
                        ShimmerDevice shimmerDevice = getShimmerGlobalMap(bluetoothAddress);
                        if (shimmerDevice == null || shimmerDevice instanceof ShimmerShell) {
                            shimmerDevice = createShimmerIfKnown();
                        }

                        if (shimmerDevice != null && !(shimmerDevice instanceof ShimmerShell)) {
                            printMessage("Connecting to " + shimmerDevice.getClass().getSimpleName() + " with connection handle = " + (connectThroughComPort ? comPort : bluetoothAddress));
                            if (connectThroughComPort) {
                                if (!comPort.contains(COMPORT_PREFIX) && !comPort.contains(COMPORT_PREFIX_MAC)) {
                                    connectShimmer3BleGrpc(bluetoothDetails);
                                } else {
                                    connectExistingShimmer(shimmerDevice, comPort, bluetoothAddress);
                                }
                            } else {
                                connectExistingShimmer(shimmerDevice, bluetoothAddress);
                            }
                        } else {
                            connectUnknownShimmer();
                        }
                    }
                } catch (
                        ShimmerException e) {
                    sendFeedbackOnConnectionException(e);
                    printMessage(e.getErrStringFormatted());
                    return;
                }
            }
        }

        private ShimmerDevice createShimmerIfKnown() {
            ShimmerDevice shimmerDevice = null;
            deviceTypeDetected = useMockHWID(deviceTypeDetected);
            if (deviceTypeDetected == DEVICE_TYPE.SHIMMER3
                    || deviceTypeDetected == DEVICE_TYPE.SHIMMER3_OUTPUT
                    || deviceTypeDetected == DEVICE_TYPE.SHIMMER_ECG_MD) {
                shimmerDevice = createNewShimmer3(comPort, bluetoothAddress);
            } else if (deviceTypeDetected == DEVICE_TYPE.SHIMMER4) {
                shimmerDevice = createNewShimmer4(comPort, bluetoothAddress);
            } else if (deviceTypeDetected == DEVICE_TYPE.SWEATCH) {
                shimmerDevice = createNewSweatchDevice(comPort, bluetoothAddress);
            }

            if (shimmerDevice != null) {
                if (shimmerVerObject != null) {
                    shimmerDevice.setShimmerVersionObjectAndCreateSensorMap(shimmerVerObject);
                }
                if (expansionBoardDetails != null) {
                    shimmerDevice.setExpansionBoardDetailsAndCreateSensorMap(expansionBoardDetails);
                }
            }

            return shimmerDevice;
        }

        private BluetoothDeviceDetails setBluetoothDeviceDetails(BluetoothDeviceDetails portDetails) {
            if (portDetails != null) {
                bluetoothAddress = portDetails.mShimmerMacId;
                bluetoothModuleDeviceName = portDetails.mFriendlyName;
                deviceTypeDetected = portDetails.mDeviceTypeDetected;
            } else {
                bluetoothAddress = UtilShimmer.MAC_ADDRESS_ZEROS;
                bluetoothModuleDeviceName = "Unknown";
                deviceTypeDetected = DEVICE_TYPE.UNKOWN;
            }

            return portDetails;
        }


        protected void connectUnknownShimmer() throws ShimmerException {
            printMessage("Connecting to new Shimmer with connection handle = " + (connectThroughComPort ? comPort : bluetoothAddress));

            shimmerRadioInitializer = new ShimmerRadioInitializer();
            final AbstractSerialPortHal serialPortComm = createNewSerialPortComm(comPort, bluetoothAddress);
            shimmerRadioInitializer.setSerialCommPort(serialPortComm);
            serialPortComm.addByteLevelDataCommListener(new ByteLevelDataCommListener() {
                @Override
                public void eventConnected() {
                    resolveUnknownShimmer(shimmerRadioInitializer);
                }

                @Override
                public void eventDisconnected() {
                    sendFeedbackOnConnectStartException(serialPortComm.getConnectionHandle());
                }
            });

            serialPortComm.connect();
        }

        protected ShimmerDevice resolveUnknownShimmer(ShimmerRadioInitializer shimmerRadioInitializer) {

            ShimmerDevice shimmerDeviceNew = null;

            try {
                ShimmerVerObject sVO = shimmerRadioInitializer.readShimmerVerObject();
                if (sVO.isShimmerGen2() || sVO.isShimmerGen3()) {
                    shimmerDeviceNew = createNewShimmer3(shimmerRadioInitializer, bluetoothAddress);
                } else if (sVO.isShimmerGen3R()) {
                    shimmerDeviceNew = createNewShimmer3(shimmerRadioInitializer, bluetoothAddress);
                } else if (sVO.isShimmerGen4()) {
                    shimmerDeviceNew = createNewShimmer4(shimmerRadioInitializer, bluetoothAddress);
                } else if (sVO.isSweatchDevice()) {
                    shimmerDeviceNew = createNewSweatchDevice(shimmerRadioInitializer, bluetoothAddress);
                }


                if (shimmerDeviceNew != null) {
                    shimmerDeviceNew.setComPort(comPort);
                    shimmerDeviceNew.setMacIdFromUart(bluetoothAddress);
                    shimmerDeviceNew.setShimmerVersionObjectAndCreateSensorMap(sVO);


                    initializeNewShimmerCommon(shimmerDeviceNew);


                } else {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();


                }
            } catch (
                    ShimmerException e) {
                e.printStackTrace();
                try {
                    shimmerRadioInitializer.getSerialCommPort().disconnect();
                } catch (
                        Exception ex) {
                    ex.printStackTrace();
                }
            }

            return shimmerDeviceNew;
        }

    }

}
