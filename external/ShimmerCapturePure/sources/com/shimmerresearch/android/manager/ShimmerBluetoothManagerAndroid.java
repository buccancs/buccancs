package com.shimmerresearch.android.manager;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.Shimmer4Android;
import com.shimmerresearch.android.VerisenseDeviceAndroid;
import com.shimmerresearch.android.protocol.VerisenseProtocolByteCommunicationAndroid;
import com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid;
import com.shimmerresearch.androidradiodriver.ShimmerRadioInitializerAndroid;
import com.shimmerresearch.androidradiodriver.ShimmerSerialPortAndroid;
import com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.bluetooth.ShimmerRadioInitializer;
import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.comms.radioProtocol.LiteProtocol;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ErrorCodesSerialPort;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.shimmer4sdk.Shimmer4sdk;
import com.shimmerresearch.driverUtilities.BluetoothDeviceDetails;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.HwDriverShimmerDeviceDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.ShimmerVerObject;
import com.shimmerresearch.exception.DeviceNotPairedException;
import com.shimmerresearch.exceptions.ConnectionExceptionListener;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class ShimmerBluetoothManagerAndroid extends ShimmerBluetoothManager {
    static final String VERISENSE_NAME_NO_PAIRING_REQUIRED = "Verisense-00";
    private static final String DEFAULT_SHIMMER_NAME = "ShimmerDevice";
    private static final long SCAN_PERIOD = 10000;
    private static final String TAG = "ShimmerBluetoothManagerAndroid";
    protected Handler mHandler;
    BluetoothAdapter mBluetoothAdapter;
    Context mContext;
    ProgressDialog mProgressDialog;
    List<BluetoothDevice> listScanBleDevice = new ArrayList();
    private BluetoothLeScanner bluetoothLeScanner;
    private boolean scanning;
    private boolean AllowAutoPairing = true;
    private Handler handler = new Handler();
    private ScanCallback leScanCallback = new ScanCallback() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.5
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            scanResult.getDevice();
            if (ShimmerBluetoothManagerAndroid.this.listScanBleDevice.contains(scanResult.getDevice())) {
                return;
            }
            ShimmerBluetoothManagerAndroid.this.listScanBleDevice.add(scanResult.getDevice());
        }
    };

    public ShimmerBluetoothManagerAndroid(Context context, Handler handler) throws Exception {
        ShimmerRadioInitializer.useLegacyDelayBeforeBtRead(true);
        this.mContext = context;
        this.mHandler = handler;
        if (handler == null) {
            throw new Exception("Handler is NULL");
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (!checkBtEnabled(defaultAdapter)) {
            throw new Exception("Bluetooth not Enabled");
        }
        loadBtShimmers(new Object[0]);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void addCallBack(BasicProcessWithCallBack basicProcessWithCallBack) {
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected ShimmerDevice createNewShimmer3(String str, String str2) {
        return null;
    }

    public void enablePairingOnConnect(boolean z) {
        this.AllowAutoPairing = z;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void connectBluetoothDevice(BluetoothDevice bluetoothDevice) {
        String address = bluetoothDevice.getAddress();
        addDiscoveredDevice(address);
        super.connectShimmerThroughBTAddress(address);
    }

    public boolean checkIfDeviceRequiresPairing(String str) {
        return !str.contains(VERISENSE_NAME_NO_PAIRING_REQUIRED);
    }

    public void connectShimmerThroughBTAddress(final String str, String str2, Context context) {
        if (isDevicePaired(str) || this.AllowAutoPairing) {
            if (!isDevicePaired(str) && checkIfDeviceRequiresPairing(str2)) {
                if (context != null) {
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Pairing Device");
                    progressDialog.setMessage("Trying to pair device...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    this.mProgressDialog = progressDialog;
                } else {
                    Toast.makeText(this.mContext, "Attempting to pair device, please wait...", 1).show();
                }
            }
            addDiscoveredDevice(str);
            super.connectShimmerThroughBTAddress(new BluetoothDeviceDetails("", str, str2));
            super.setConnectionExceptionListener(new ConnectionExceptionListener() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.1
                @Override // com.shimmerresearch.exceptions.ConnectionExceptionListener
                public void onConnectionStart(String str3) {
                }

                @Override // com.shimmerresearch.exceptions.ConnectionExceptionListener
                public void onConnectionException(Exception exc) {
                    if (ShimmerBluetoothManagerAndroid.this.mProgressDialog != null) {
                        ShimmerBluetoothManagerAndroid.this.mProgressDialog.dismiss();
                    }
                    ShimmerBluetoothManagerAndroid.this.mHandler.obtainMessage(0, -1, -1, new ObjectCluster("", str, ShimmerBluetooth.BT_STATE.DISCONNECTED)).sendToTarget();
                }

                @Override // com.shimmerresearch.exceptions.ConnectionExceptionListener
                public void onConnectStartException(String str3) {
                    if (ShimmerBluetoothManagerAndroid.this.mProgressDialog != null) {
                        ShimmerBluetoothManagerAndroid.this.mProgressDialog.dismiss();
                    }
                    ShimmerBluetoothManagerAndroid.this.mHandler.obtainMessage(0, -1, -1, new ObjectCluster("", str, ShimmerBluetooth.BT_STATE.DISCONNECTED)).sendToTarget();
                }
            });
            return;
        }
        throw new DeviceNotPairedException(str, "Device " + str + " not paired");
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void connectVerisenseDevice(BluetoothDeviceDetails bluetoothDeviceDetails) {
        VerisenseProtocolByteCommunicationAndroid verisenseProtocolByteCommunicationAndroid = new VerisenseProtocolByteCommunicationAndroid(new VerisenseBleAndroidRadioByteCommunication(bluetoothDeviceDetails.mShimmerMacId));
        final VerisenseDeviceAndroid verisenseDeviceAndroid = new VerisenseDeviceAndroid(this.mHandler);
        verisenseDeviceAndroid.setMacIdFromUart(bluetoothDeviceDetails.mShimmerMacId);
        verisenseDeviceAndroid.setProtocol(Configuration.COMMUNICATION_TYPE.BLUETOOTH, verisenseProtocolByteCommunicationAndroid);
        initializeNewShimmerCommon(verisenseDeviceAndroid);
        new Thread() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    verisenseDeviceAndroid.connect();
                } catch (ShimmerException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void connectShimmerThroughBTAddress(String str, String str2, BT_TYPE bt_type) {
        if (bt_type.equals(BT_TYPE.BT_CLASSIC)) {
            connectShimmerThroughBTAddress(str);
        } else {
            connectShimmer3BLEThroughBTAddress(str, str2, null);
        }
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void connectShimmerThroughBTAddress(String str) {
        connectShimmerThroughBTAddress(str, "", (Context) null);
    }

    public void connectShimmer3BLEThroughBTAddress(final String str, String str2, Context context) {
        final Shimmer3BLEAndroid shimmer3BLEAndroid;
        if (str2.contains(HwDriverShimmerDeviceDetails.DEVICE_TYPE.SHIMMER3R.toString())) {
            shimmer3BLEAndroid = new Shimmer3BLEAndroid(10, str, this.mHandler);
        } else {
            shimmer3BLEAndroid = new Shimmer3BLEAndroid(3, str, this.mHandler);
        }
        shimmer3BLEAndroid.setMacIdFromUart(str);
        initializeNewShimmerCommon(shimmer3BLEAndroid);
        new Thread() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                shimmer3BLEAndroid.connect(str, "default");
            }
        }.start();
    }

    private void doDiscovery() {
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
        this.mBluetoothAdapter.startDiscovery();
    }

    private void scanLeDevice(String str) {
        getScannedBleDevices();
        BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        this.bluetoothLeScanner = bluetoothLeScanner;
        if (!this.scanning) {
            this.handler.postDelayed(new Runnable() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.4
                @Override // java.lang.Runnable
                public void run() {
                    ShimmerBluetoothManagerAndroid.this.scanning = false;
                    ShimmerBluetoothManagerAndroid.this.bluetoothLeScanner.stopScan(ShimmerBluetoothManagerAndroid.this.leScanCallback);
                }
            }, 10000L);
            this.scanning = true;
            scanForAllBleDevices();
        } else {
            this.scanning = false;
            bluetoothLeScanner.stopScan(this.leScanCallback);
        }
    }

    private void scanForAllBleDevices() {
        this.bluetoothLeScanner.startScan(this.leScanCallback);
    }

    private void scanForSpecificBleDevices(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ScanFilter.Builder().setDeviceAddress(str).build());
        this.bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().build(), this.leScanCallback);
    }

    private void getScannedBleDevices() {
        Iterator<BluetoothDevice> it2 = this.listScanBleDevice.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next().getAddress());
        }
    }

    public boolean isDevicePaired(String str) {
        Iterator<BluetoothDevice> it2 = this.mBluetoothAdapter.getBondedDevices().iterator();
        while (it2.hasNext()) {
            if (it2.next().getAddress().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public TreeMap<Integer, String> getMapOfErrorCodes(int[] iArr) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.putAll(ErrorCodesSerialPort.mMapOfErrorCodes);
        return treeMap;
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public ShimmerDevice getShimmerGlobalMap(String str) {
        return mMapOfBtConnectedShimmers.get(str);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void putShimmerGlobalMap(String str, ShimmerDevice shimmerDevice) {
        mMapOfBtConnectedShimmers.put(str, shimmerDevice);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected AbstractSerialPortHal createNewSerialPortComm(String str, String str2) {
        return new ShimmerSerialPortAndroid(str2);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void printMessage(String str) {
        Log.i(TAG, str);
    }

    protected Shimmer4sdk initializeNewShimmer4(ShimmerRadioInitializer shimmerRadioInitializer) {
        Shimmer4Android shimmer4Android = new Shimmer4Android(this.mHandler);
        mMapOfBtConnectedShimmers.put(shimmer4Android.getBtConnectionHandle(), shimmer4Android);
        return null;
    }

    private ShimmerRadioInitializer getRadioInitializer(String str) {
        return new ShimmerRadioInitializerAndroid(str);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected void connectExistingShimmer(Object... objArr) {
        ShimmerDevice shimmerDevice = (ShimmerDevice) objArr[0];
        String str = (String) objArr[1];
        if (shimmerDevice instanceof Shimmer) {
            ((Shimmer) shimmerDevice).connect(str, "default");
        } else if (shimmerDevice instanceof Shimmer4Android) {
            connectExistingShimmer4((Shimmer4Android) shimmerDevice, str);
        }
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected ShimmerDevice createNewShimmer3(ShimmerRadioInitializer shimmerRadioInitializer, String str) {
        ShimmerRadioInitializer.useLegacyDelayBeforeBtRead(true);
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        AbstractSerialPortHal abstractSerialPortHal = (ShimmerSerialPortAndroid) shimmerRadioInitializer.getSerialCommPort();
        Shimmer shimmer = new Shimmer(this.mHandler, this.mContext);
        shimmer.setDelayForBtRespone(true);
        mMapOfBtConnectedShimmers.put(str, shimmer);
        try {
            ShimmerVerObject shimmerVerObject = shimmerRadioInitializer.readShimmerVerObject();
            if (!shimmerVerObject.isShimmerGen3() && !shimmerVerObject.isShimmerGen3R()) {
                if (shimmerVerObject.isShimmerGen2()) {
                    return initializeShimmer2r(abstractSerialPortHal, shimmer);
                }
                return null;
            }
            return initializeShimmer3(abstractSerialPortHal, shimmer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected ShimmerDevice initializeShimmer2r(AbstractSerialPortHal abstractSerialPortHal, ShimmerDevice shimmerDevice) {
        ((Shimmer) shimmerDevice).setRadio(((ShimmerSerialPortAndroid) abstractSerialPortHal).getBluetoothSocket());
        shimmerDevice.addCommunicationRoute(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        return shimmerDevice;
    }

    protected ShimmerDevice initializeShimmer3(AbstractSerialPortHal abstractSerialPortHal, ShimmerDevice shimmerDevice) {
        ((Shimmer) shimmerDevice).setRadio(((ShimmerSerialPortAndroid) abstractSerialPortHal).getBluetoothSocket());
        shimmerDevice.addCommunicationRoute(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        return shimmerDevice;
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void configureShimmer(final ShimmerDevice shimmerDevice) {
        new Thread() { // from class: com.shimmerresearch.android.manager.ShimmerBluetoothManagerAndroid.6
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                ShimmerBluetoothManagerAndroid.this.configureShimmers(Arrays.asList(shimmerDevice));
            }
        }.start();
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected Shimmer4sdk createNewShimmer4(String str, String str2) {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        Shimmer4Android shimmer4Android = new Shimmer4Android(this.mHandler);
        mMapOfBtConnectedShimmers.put(str2, shimmer4Android);
        return shimmer4Android;
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected Shimmer4sdk createNewShimmer4(ShimmerRadioInitializer shimmerRadioInitializer, String str) {
        ShimmerRadioInitializer.useLegacyDelayBeforeBtRead(true);
        ShimmerSerialPortAndroid shimmerSerialPortAndroid = (ShimmerSerialPortAndroid) shimmerRadioInitializer.getSerialCommPort();
        Shimmer4sdk shimmer4sdkCreateNewShimmer4 = createNewShimmer4("", str);
        if (shimmerSerialPortAndroid != null) {
            shimmer4sdkCreateNewShimmer4.setCommsProtocolRadio(new CommsProtocolRadio(shimmerSerialPortAndroid, new LiteProtocol(str)));
        }
        return shimmer4sdkCreateNewShimmer4;
    }

    private void connectExistingShimmer4(Shimmer4Android shimmer4Android, String str) {
        shimmer4Android.addCommunicationRoute(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
        shimmer4Android.setMacIdFromUart(str);
        if (shimmer4Android.isReadyToConnect()) {
            shimmer4Android.setCommsProtocolRadio(new CommsProtocolRadio(((ShimmerRadioInitializerAndroid) getRadioInitializer(str)).getSerialCommPort(), new LiteProtocol(str)));
            try {
                shimmer4Android.connect();
            } catch (ShimmerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    protected BluetoothDeviceDetails getBluetoothDeviceDetails(String str) {
        return this.mMapOfParsedBtComPorts.get(str);
    }

    private void addDiscoveredDevice(String str) {
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        BluetoothDeviceDetails bluetoothDeviceDetails = new BluetoothDeviceDetails("", remoteDevice.getAddress(), remoteDevice.getName());
        this.mMapOfParsedBtComPortsDeepCopy.put(str, bluetoothDeviceDetails);
        this.mMapOfParsedBtComPorts.put(str, bluetoothDeviceDetails);
    }

    @Override // com.shimmerresearch.managers.bluetoothManager.ShimmerBluetoothManager
    public void loadBtShimmers(Object... objArr) {
        TreeMap<String, BluetoothDeviceDetails> treeMapMapBTDevicesToComPortDetails = mapBTDevicesToComPortDetails(this.mBluetoothAdapter.getBondedDevices());
        this.mMapOfParsedBtComPortsDeepCopy.clear();
        this.mMapOfParsedBtComPorts.clear();
        this.mMapOfParsedBtComPortsDeepCopy.putAll(treeMapMapBTDevicesToComPortDetails);
        this.mMapOfParsedBtComPorts.putAll(treeMapMapBTDevicesToComPortDetails);
    }

    private TreeMap<String, BluetoothDeviceDetails> mapBTDevicesToComPortDetails(Set<BluetoothDevice> set) {
        TreeMap<String, BluetoothDeviceDetails> treeMap = new TreeMap<>();
        for (BluetoothDevice bluetoothDevice : set) {
            BluetoothDeviceDetails bluetoothDeviceDetails = new BluetoothDeviceDetails("", bluetoothDevice.getAddress(), bluetoothDevice.getName());
            if (bluetoothDeviceDetails.mDeviceTypeDetected != HwDriverShimmerDeviceDetails.DEVICE_TYPE.UNKOWN) {
                treeMap.put(bluetoothDevice.getAddress(), bluetoothDeviceDetails);
            }
        }
        return treeMap;
    }

    boolean checkBtEnabled(BluetoothAdapter bluetoothAdapter) {
        return bluetoothAdapter.isEnabled();
    }

    public HashMap<String, Object> getHashMapOfShimmersConnected() {
        HashMap<String, Object> map = new HashMap<>(7);
        for (ShimmerDevice shimmerDevice : getListOfConnectedDevices()) {
            map.put(shimmerDevice.getMacId(), shimmerDevice);
        }
        return map;
    }

    public void addHandler(Handler handler) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() != ShimmerBluetooth.BT_STATE.DISCONNECTED) {
                shimmer.addHandler(handler);
            }
        }
    }

    public void toggleAllLEDS() {
        new HashMap(7);
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                shimmer.toggleLed();
            }
        }
    }

    public void toggleLED(String str) {
        new HashMap(7);
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.toggleLed();
                }
            }
        }
    }

    public void setAllAccelRange(int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                shimmer.writeAccelRange(i);
            }
        }
    }

    public void setAllGSRRange(int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                shimmer.writeGSRRange(i);
            }
        }
    }

    public void setAllEnabledSensors(int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                shimmer.writeEnabledSensors(i);
            }
        }
    }

    public void writePMux(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writePMux(i);
                }
            }
        }
    }

    public void write5VReg(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writeFiveVoltReg(i);
                }
            }
        }
    }

    public List<String[]> getListofEnabledSensorSignals(String str) {
        HashMap<String, Object> hashMapOfShimmersConnected = getHashMapOfShimmersConnected();
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it2 = hashMapOfShimmersConnected.values().iterator();
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            ShimmerBluetooth.BT_STATE bluetoothRadioState = shimmerDevice.getBluetoothRadioState();
            if (bluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING || bluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING) {
                if (!strReplace.equals(str)) {
                    continue;
                } else {
                    if (shimmerDevice.getShimmerVerObject().getHardwareVersion() == 2) {
                        return ((Shimmer) shimmerDevice).getListofEnabledChannelSignalsandFormats();
                    }
                    Iterator<SensorDetails> it3 = shimmerDevice.getListOfEnabledSensors().iterator();
                    while (it3.hasNext()) {
                        for (ChannelDetails channelDetails : it3.next().mListOfChannels) {
                            for (ChannelDetails.CHANNEL_TYPE channel_type : channelDetails.mListOfChannelTypes) {
                                String[] strArr = {shimmerDevice.getShimmerUserAssignedName(), channelDetails.mObjectClusterName, channel_type.toString(), ""};
                                if (!channel_type.equals(ChannelDetails.CHANNEL_TYPE.UNCAL)) {
                                    channel_type.equals(ChannelDetails.CHANNEL_TYPE.CAL);
                                }
                                arrayList.add(strArr);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public long getEnabledSensors(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        long enabledSensors = 0;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() != ShimmerBluetooth.BT_STATE.DISCONNECTED) {
                enabledSensors = shimmer.getEnabledSensors();
            }
        }
        return enabledSensors;
    }

    public void writeAccelRange(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writeAccelRange(i);
                }
            }
        }
    }

    public void writeGyroRange(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writeGyroRange(i);
                }
            }
        }
    }

    public void writePressureResolution(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writePressureResolution(i);
                }
            }
        }
    }

    public void writeMagRange(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writeMagRange(i);
                }
            }
        }
    }

    public void writeGSRRange(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.writeGSRRange(i);
                }
            }
        }
    }

    public double getSamplingRate(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        double samplingRateShimmer = -1.0d;
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            if (strReplace.equals(str)) {
                samplingRateShimmer = shimmerDevice.getSamplingRateShimmer(Configuration.COMMUNICATION_TYPE.BLUETOOTH);
            }
        }
        return samplingRateShimmer;
    }

    public int getAccelRange(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        int accelRange = -1;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                accelRange = shimmer.getAccelRange();
            }
        }
        return accelRange;
    }

    public ShimmerBluetooth.BT_STATE getShimmerState(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        ShimmerBluetooth.BT_STATE bluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                bluetoothRadioState = shimmer.getBluetoothRadioState();
            }
        }
        return bluetoothRadioState;
    }

    public int getGSRRange(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        int gSRRange = -1;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                gSRRange = shimmer.getGSRRange();
            }
        }
        return gSRRange;
    }

    public int get5VReg(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        int i = -1;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                i = shimmer.get5VReg();
            }
        }
        return i;
    }

    public boolean isLowPowerMagEnabled(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        boolean zIsLowPowerMagEnabled = false;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                zIsLowPowerMagEnabled = shimmer.isLowPowerMagEnabled();
            }
        }
        return zIsLowPowerMagEnabled;
    }

    public int getpmux(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        int pMux = -1;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                pMux = shimmer.getPMux();
            }
        }
        return pMux;
    }

    public void startLogging(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.STREAMING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.startSDLogging();
                }
            }
        }
    }

    public void stopLogging(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    shimmer.stopSDLogging();
                }
            }
        }
    }

    public void startLoggingAndStreaming(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                shimmer.startDataLogAndStreaming();
            }
        }
    }

    public long sensorConflictCheckandCorrection(String str, long j, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        long jSensorConflictCheckandCorrection = 0;
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothAddress().equals(str)) {
                jSensorConflictCheckandCorrection = shimmer.sensorConflictCheckandCorrection(j, i);
            }
        }
        return jSensorConflictCheckandCorrection;
    }

    public List<String> getListofEnabledSensors(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        List<String> listofEnabledSensors = null;
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            if (shimmerDevice.getMacId().equals(str)) {
                if (shimmerDevice instanceof Shimmer) {
                    listofEnabledSensors = ((Shimmer) shimmerDevice).getListofEnabledSensors();
                } else if (shimmerDevice instanceof Shimmer4Android) {
                    listofEnabledSensors = new ArrayList<>();
                }
            }
        }
        return listofEnabledSensors;
    }

    public boolean bluetoothAddressComparator(String str, String str2) {
        return str2.replace(":", "").equals(str.replace(":", ""));
    }

    public void setBlinkLEDCMD(String str) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer != null) {
            if ((shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) && shimmer.getBluetoothAddress().equals(str)) {
                if (shimmer.getCurrentLEDStatus() == 0) {
                    shimmer.writeLEDCommand(1);
                } else {
                    shimmer.writeLEDCommand(0);
                }
            }
        }
    }

    public void enableLowPowerMag(String str, boolean z) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer != null) {
            if ((shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) && shimmer.getBluetoothAddress().equals(str)) {
                shimmer.enableLowPowerMag(z);
            }
        }
    }

    public void setBattLimitWarning(String str, double d) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || !shimmer.getBluetoothAddress().equals(str)) {
            return;
        }
        shimmer.setBattLimitWarning(d);
    }

    public double getBattLimitWarning(String str) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || !shimmer.getBluetoothAddress().equals(str)) {
            return -1.0d;
        }
        return shimmer.getBattLimitWarning();
    }

    public double getPacketReceptionRate(String str) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || !shimmer.getBluetoothAddress().equals(str)) {
            return -1.0d;
        }
        return shimmer.getPacketReceptionRate();
    }

    public boolean DevicesConnected(String str) {
        HashMap<String, Object> hashMapOfShimmersConnected = getHashMapOfShimmersConnected();
        boolean z = false;
        if (str == null) {
            return false;
        }
        Iterator<Object> it2 = hashMapOfShimmersConnected.values().iterator();
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            if (shimmerDevice.getBluetoothRadioState() != ShimmerBluetooth.BT_STATE.DISCONNECTED && strReplace.equals(str)) {
                z = true;
            }
        }
        return z;
    }

    public boolean DeviceIsLogging(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            if (shimmerDevice.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.SDLOGGING || shimmerDevice.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING) {
                if (strReplace.equals(str)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean DeviceIsStreaming(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            ShimmerDevice shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            ShimmerBluetooth.BT_STATE bluetoothRadioState = shimmerDevice.getBluetoothRadioState();
            if (bluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING || bluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING) {
                if (strReplace.equals(str)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public String getFWVersion(String str) {
        ShimmerDevice shimmerDevice = (ShimmerDevice) getHashMapOfShimmersConnected().get(str);
        if (shimmerDevice == null) {
            return "";
        }
        return shimmerDevice.getFirmwareVersionMajor() + "." + shimmerDevice.getFirmwareVersionMinor();
    }

    public int getShimmerVersion(String str) {
        ShimmerDevice shimmerDevice = (ShimmerDevice) getHashMapOfShimmersConnected().get(str);
        if (shimmerDevice != null) {
            return shimmerDevice.getHardwareVersion();
        }
        return 0;
    }

    public ShimmerDevice getShimmer(String str) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        ShimmerDevice shimmerDevice = null;
        while (it2.hasNext()) {
            shimmerDevice = (ShimmerDevice) it2.next();
            String strReplace = shimmerDevice.getMacId().replace(":", "");
            str = str.replace(":", "");
            if (strReplace.equals(str)) {
                break;
            }
        }
        return shimmerDevice;
    }

    public void writeEXGSetting(String str, int i) {
        Iterator<Object> it2 = getHashMapOfShimmersConnected().values().iterator();
        while (it2.hasNext()) {
            Shimmer shimmer = (Shimmer) it2.next();
            if (shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.CONNECTED || shimmer.getBluetoothRadioState() == ShimmerBluetooth.BT_STATE.SDLOGGING) {
                if (shimmer.getBluetoothAddress().equals(str)) {
                    if (i == 0) {
                        shimmer.enableDefaultECGConfiguration();
                    } else if (i == 1) {
                        shimmer.enableDefaultEMGConfiguration();
                    } else if (i == 2) {
                        shimmer.enableEXGTestSignal();
                    }
                }
            }
        }
    }

    public boolean isUsingLogAndStreamFW(String str) {
        ShimmerDevice shimmerDevice = (ShimmerDevice) getHashMapOfShimmersConnected().get(str);
        return shimmerDevice != null && shimmerDevice.getFirmwareIdentifier() == 3;
    }

    public void readStatusLogAndStream(String str) throws IOException {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || shimmer.getFirmwareIdentifier() != 3) {
            return;
        }
        shimmer.readStatusLogAndStream();
    }

    public boolean isSensing(String str) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || shimmer.getFirmwareIdentifier() != 3) {
            return false;
        }
        return shimmer.isSensing();
    }

    public boolean isDocked(String str) {
        Shimmer shimmer = (Shimmer) getHashMapOfShimmersConnected().get(str);
        if (shimmer == null || shimmer.getFirmwareIdentifier() != 3) {
            return false;
        }
        return shimmer.isDocked();
    }

    public enum BT_TYPE {
        BT_CLASSIC,
        BLE
    }
}
