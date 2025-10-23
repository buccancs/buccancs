package com.shimmerresearch.androidradiodriver;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import bolts.TaskCompletionSource;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.FormatCluster;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerDeviceCallbackAdapter;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ThreadSafeByteFifoBuffer;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ChannelDetails;
import com.shimmerresearch.driverUtilities.SensorDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.sensors.kionix.SensorKionixAccel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import kotlin.time.DurationKt;

/* loaded from: classes2.dex */
public class Shimmer3BLEAndroid extends ShimmerBluetooth implements Serializable {
    public static final String TOAST = "toast";
    public final transient Handler mHandler;
    final String RxID_Shimmer3;
    final String RxID_Shimmer3R;
    final String ServiceID_Shimmer3;
    final String ServiceID_Shimmer3R;
    final String TxID_Shimmer3;
    final String TxID_Shimmer3R;
    protected transient ShimmerDeviceCallbackAdapter mDeviceCallbackAdapter;
    String RxID;
    String ServiceID;
    String TxID;
    transient BleDevice mBleDevice;
    transient ThreadSafeByteFifoBuffer mBuffer;
    String mMac;
    transient TaskCompletionSource<String> mTaskConnect;
    transient TaskCompletionSource<String> mTaskMTU;
    UUID rxid;
    UUID sid;
    UUID txid;
    String uuid;

    public Shimmer3BLEAndroid(String str) {
        this.TxID_Shimmer3 = "49535343-8841-43f4-a8d4-ecbe34729bb3";
        this.RxID_Shimmer3 = "49535343-1e4d-4bd9-ba61-23c647249616";
        this.ServiceID_Shimmer3 = "49535343-fe7d-4ae5-8fa9-9fafd205e455";
        this.TxID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA101";
        this.RxID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA102";
        this.ServiceID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA100";
        this.TxID = "";
        this.RxID = "";
        this.ServiceID = "";
        this.sid = null;
        this.txid = null;
        this.rxid = null;
        this.mDeviceCallbackAdapter = new ShimmerDeviceCallbackAdapter(this);
        this.mTaskConnect = new TaskCompletionSource<>();
        this.mTaskMTU = new TaskCompletionSource<>();
        this.mMac = str;
        this.mHandler = null;
    }

    public Shimmer3BLEAndroid(int i, String str, Handler handler) {
        this.TxID_Shimmer3 = "49535343-8841-43f4-a8d4-ecbe34729bb3";
        this.RxID_Shimmer3 = "49535343-1e4d-4bd9-ba61-23c647249616";
        this.ServiceID_Shimmer3 = "49535343-fe7d-4ae5-8fa9-9fafd205e455";
        this.TxID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA101";
        this.RxID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA102";
        this.ServiceID_Shimmer3R = "65333333-A115-11E2-9E9A-0800200CA100";
        this.TxID = "";
        this.RxID = "";
        this.ServiceID = "";
        this.sid = null;
        this.txid = null;
        this.rxid = null;
        this.mDeviceCallbackAdapter = new ShimmerDeviceCallbackAdapter(this);
        this.mTaskConnect = new TaskCompletionSource<>();
        this.mTaskMTU = new TaskCompletionSource<>();
        if (i == 10) {
            this.sid = UUID.fromString("65333333-A115-11E2-9E9A-0800200CA100");
            this.txid = UUID.fromString("65333333-A115-11E2-9E9A-0800200CA101");
            this.rxid = UUID.fromString("65333333-A115-11E2-9E9A-0800200CA102");
        } else if (i == 3) {
            this.sid = UUID.fromString("49535343-fe7d-4ae5-8fa9-9fafd205e455");
            this.txid = UUID.fromString("49535343-8841-43f4-a8d4-ecbe34729bb3");
            this.rxid = UUID.fromString("49535343-1e4d-4bd9-ba61-23c647249616");
        }
        this.mMac = str;
        this.mHandler = handler;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void sendStatusMSGtoUI(String str) {
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth, com.shimmerresearch.driver.ShimmerObject
    protected void sendStatusMsgPacketLossDetected() {
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    public void sendCallBackMsg(int i, Object obj) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.obtainMessage(i, obj).sendToTarget();
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public void connect(String str, String str2) {
        this.mTaskConnect = new TaskCompletionSource<>();
        BleManager.getInstance().connect(this.mMac, new BleGattCallback() { // from class: com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid.1
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(BleDevice bleDevice, BleException bleException) {
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                System.out.println("Connecting");
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                Shimmer3BLEAndroid.this.mBuffer = new ThreadSafeByteFifoBuffer(DurationKt.NANOS_IN_MILLIS);
                Shimmer3BLEAndroid.this.mTaskMTU = new TaskCompletionSource<>();
                BleManager.getInstance().setMtu(bleDevice, 251, new BleMtuChangedCallback() { // from class: com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid.1.1
                    @Override // com.clj.fastble.callback.BleMtuChangedCallback
                    public void onSetMTUFailure(BleException bleException) {
                        System.out.println("MTU Failure");
                    }

                    @Override // com.clj.fastble.callback.BleMtuChangedCallback
                    public void onMtuChanged(int i2) {
                        System.out.println("MTU Changed: " + i2);
                        Shimmer3BLEAndroid.this.mTaskMTU.setResult("MTU Changed: " + i2);
                    }
                });
                try {
                    Shimmer3BLEAndroid.this.mTaskMTU.getTask().waitForCompletion(3L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bluetoothGatt.requestConnectionPriority(1);
                Shimmer3BLEAndroid.this.mBleDevice = bleDevice;
                Shimmer3BLEAndroid.this.startServiceS(bleDevice);
                System.out.println(bleDevice.getMac() + " Connected");
                if (Build.VERSION.SDK_INT >= 26) {
                    bluetoothGatt.setPreferredPhy(2, 2, 0);
                }
                Shimmer3BLEAndroid.this.mTaskConnect.setResult("Connected");
                Shimmer3BLEAndroid.this.sendCallBackMsg(0, new ObjectCluster("", bleDevice.getMac(), ShimmerBluetooth.BT_STATE.CONNECTED));
                Bundle bundle = new Bundle();
                bundle.putString("toast", "Device connection established");
                Shimmer3BLEAndroid.this.sendMsgToHandlerList(Shimmer.MESSAGE_TOAST, bundle);
                Shimmer3BLEAndroid.this.mIOThread = new ShimmerBluetooth.IOThread();
                Shimmer3BLEAndroid.this.mIOThread.start();
                if (Shimmer3BLEAndroid.this.mUseProcessingThread) {
                    Shimmer3BLEAndroid.this.mPThread = new ShimmerBluetooth.ProcessingThread();
                    Shimmer3BLEAndroid.this.mPThread.start();
                }
                Shimmer3BLEAndroid.this.initialize();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                Shimmer3BLEAndroid.this.sendCallBackMsg(0, new ObjectCluster("", bleDevice.getMac(), ShimmerBluetooth.BT_STATE.DISCONNECTED));
                Bundle bundle = new Bundle();
                bundle.putString("toast", "Device connection was lost");
                Shimmer3BLEAndroid.this.sendMsgToHandlerList(Shimmer.MESSAGE_TOAST, bundle);
                System.out.println();
            }
        });
        try {
            boolean zWaitForCompletion = this.mTaskConnect.getTask().waitForCompletion(10L, TimeUnit.SECONDS);
            Thread.sleep(200L);
            if (zWaitForCompletion) {
                return;
            }
            System.out.println("Connect fail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startServiceS(BleDevice bleDevice) {
        for (BluetoothGattService bluetoothGattService : BleManager.getInstance().getBluetoothGattServices(bleDevice)) {
            System.out.println(bluetoothGattService.getUuid());
            if (bluetoothGattService.getUuid().compareTo(this.sid) == 0) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                    if (bluetoothGattCharacteristic.getUuid().compareTo(this.txid) != 0 && bluetoothGattCharacteristic.getUuid().compareTo(this.rxid) == 0) {
                        newConnectedBLEDevice(bleDevice, bluetoothGattCharacteristic);
                    }
                }
            }
        }
    }

    public void newConnectedBLEDevice(BleDevice bleDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BleManager.getInstance().notify(bleDevice, bluetoothGattCharacteristic.getService().getUuid().toString(), bluetoothGattCharacteristic.getUuid().toString(), new BleNotifyCallback() { // from class: com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid.2
            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifyFailure(BleException bleException) {
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifySuccess() {
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onCharacteristicChanged(byte[] bArr) {
                try {
                    Shimmer3BLEAndroid.this.mBuffer.write(bArr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected boolean bytesAvailableToBeRead() {
        return this.mBuffer.size() > 0;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected int availableBytes() {
        return this.mBuffer.size();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public void writeBytes(byte[] bArr) {
        BleManager.getInstance().write(this.mBleDevice, this.sid.toString(), this.txid.toString(), bArr, false, new BleWriteCallback() { // from class: com.shimmerresearch.androidradiodriver.Shimmer3BLEAndroid.3
            @Override // com.clj.fastble.callback.BleWriteCallback
            public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                System.out.println("Write Success " + UtilShimmer.bytesToHexStringWithSpacesFormatted(bArr2));
            }

            @Override // com.clj.fastble.callback.BleWriteCallback
            public void onWriteFailure(BleException bleException) {
                System.out.println("Write Fail");
            }
        });
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void stop() {
        try {
            disconnect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        this.mDeviceCallbackAdapter.sendProgressReport(bluetoothProgressReportPerCmd);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void isReadyForStreaming() {
        this.mDeviceCallbackAdapter.isReadyForStreaming();
        restartTimersIfNull();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void isNowStreaming() {
        this.mDeviceCallbackAdapter.isNowStreaming();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void hasStopStreaming() {
        this.mDeviceCallbackAdapter.hasStopStreaming();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void inquiryDone() {
        this.mDeviceCallbackAdapter.inquiryDone();
        isReadyForStreaming();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void printLogDataForDebugging(String str) {
        consolePrintLn(str);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void connectionLost() {
        try {
            disconnect();
        } catch (ShimmerException e) {
            e.printStackTrace();
        }
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTION_LOST);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state) {
        boolean bluetoothRadioState = super.setBluetoothRadioState(bt_state);
        this.mDeviceCallbackAdapter.setBluetoothRadioState(bt_state, bluetoothRadioState);
        return bluetoothRadioState;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void startOperation(ShimmerBluetooth.BT_STATE bt_state) {
        startOperation(bt_state, 1);
        consolePrintLn(bt_state + " START");
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void finishOperation(ShimmerBluetooth.BT_STATE bt_state) {
        this.mDeviceCallbackAdapter.finishOperation(bt_state);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i) {
        this.mDeviceCallbackAdapter.startOperation(bt_state, i);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void eventLogAndStreamStatusChanged(byte b) {
        if (b == -109) {
            if (this.mIsStreaming) {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
                return;
            } else if (isConnected()) {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
                return;
            } else {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
                return;
            }
        }
        if (this.mIsStreaming && isSDLogging()) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING);
            return;
        }
        if (this.mIsStreaming) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
            return;
        }
        if (isSDLogging()) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.SDLOGGING);
            return;
        }
        if (!this.mIsStreaming && !isSDLogging() && isConnected() && this.mBluetoothRadioState != ShimmerBluetooth.BT_STATE.CONNECTED) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        }
        sendCallBackMsg(0, new CallbackObject(3, this.mBluetoothRadioState, getMacId(), getComPort()));
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void batteryStatusChanged() {
        this.mDeviceCallbackAdapter.batteryStatusChanged();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected byte[] readBytes(int i) {
        try {
            return this.mBuffer.read(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected byte readByte() {
        try {
            return this.mBuffer.read();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return (byte) -1;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void dockedStateChange() {
        this.mDeviceCallbackAdapter.dockedStateChange();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public ShimmerDevice deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (ShimmerDevice) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void createConfigBytesLayout() {
        this.mConfigByteLayout = new ConfigByteLayoutShimmer3(getFirmwareIdentifier(), getFirmwareVersionMajor(), getFirmwareVersionMinor(), getFirmwareVersionInternal(), getHardwareVersion());
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void dataHandler(ObjectCluster objectCluster) {
        this.mDeviceCallbackAdapter.dataHandler(objectCluster);
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
        System.out.println(shimmerMsg.mIdentifier);
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void disconnect() throws ShimmerException {
        stopAllTimers();
        BleManager.getInstance().disconnect(this.mBleDevice);
        closeConnection();
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device connection was lost");
        sendMsgToHandlerList(Shimmer.MESSAGE_TOAST, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgToHandlerList(int i, Bundle bundle) {
        Handler handler = this.mHandler;
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage(i);
            messageObtainMessage.setData(bundle);
            this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    private void closeConnection() {
        try {
            if (this.mIOThread != null) {
                this.mIOThread.stop = true;
                while (this.mIOThread != null && this.mIOThread.isAlive()) {
                }
                this.mIOThread = null;
                if (this.mUseProcessingThread) {
                    this.mPThread.stop = true;
                    this.mPThread = null;
                }
            }
            this.mIsStreaming = false;
            this.mIsInitialised = false;
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        } catch (Exception e) {
            consolePrintException(e.getMessage(), e.getStackTrace());
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public String getSensorLabel(int i) {
        super.getSensorLabel(i);
        SensorDetails sensorDetails = this.mSensorMap.get(Integer.valueOf(i));
        if (sensorDetails != null) {
            return sensorDetails.mSensorDetailsRef.mGuiFriendlyLabel;
        }
        return null;
    }

    public class SensorDataReceived extends BasicProcessWithCallBack {
        public SensorDataReceived() {
        }

        @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
        protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
            System.out.println(shimmerMsg.mIdentifier);
            int i = shimmerMsg.mIdentifier;
            Object obj = shimmerMsg.mB;
            if (i == 0) {
                CallbackObject callbackObject = (CallbackObject) obj;
                if (callbackObject.mState == ShimmerBluetooth.BT_STATE.CONNECTING || callbackObject.mState == ShimmerBluetooth.BT_STATE.CONNECTED || callbackObject.mState == ShimmerBluetooth.BT_STATE.DISCONNECTED) {
                    return;
                }
                ShimmerBluetooth.BT_STATE bt_state = callbackObject.mState;
                ShimmerBluetooth.BT_STATE bt_state2 = ShimmerBluetooth.BT_STATE.CONNECTION_LOST;
                return;
            }
            if (i == 1) {
                int i2 = ((CallbackObject) obj).mIndicator;
                return;
            }
            if (i == 2) {
                ObjectCluster objectCluster = (ObjectCluster) shimmerMsg.mB;
                FormatCluster formatClusterReturnFormatCluster = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_X), ChannelDetails.CHANNEL_TYPE.CAL.toString());
                FormatCluster formatClusterReturnFormatCluster2 = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Y), ChannelDetails.CHANNEL_TYPE.CAL.toString());
                FormatCluster formatClusterReturnFormatCluster3 = ObjectCluster.returnFormatCluster(objectCluster.getCollectionOfFormatClusters(SensorKionixAccel.ObjectClusterSensorName.ACCEL_LN_Z), ChannelDetails.CHANNEL_TYPE.CAL.toString());
                if (formatClusterReturnFormatCluster != null) {
                    System.out.println("X:" + formatClusterReturnFormatCluster.mData + " Y:" + formatClusterReturnFormatCluster2.mData + " Z:" + formatClusterReturnFormatCluster3.mData);
                    return;
                }
                System.out.println("ERROR! FormatCluster is Null!");
            }
        }
    }
}
