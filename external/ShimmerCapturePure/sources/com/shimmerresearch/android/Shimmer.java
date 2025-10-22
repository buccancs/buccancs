package com.shimmerresearch.android;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.Configuration;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.driver.shimmer2r3.ConfigByteLayoutShimmer3;
import com.shimmerresearch.driverUtilities.ShimmerBattStatusDetails;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.verisense.communication.VerisenseMessage;
import it.gerdavax.easybluetooth.BtSocket;
import it.gerdavax.easybluetooth.LocalDevice;
import it.gerdavax.easybluetooth.RemoteDevice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Shimmer extends ShimmerBluetooth {

    @Deprecated
    public static final int MESSAGE_ACK_RECEIVED = 4;

    @Deprecated
    public static final int MESSAGE_DEVICE_NAME = 5;

    @Deprecated
    public static final int MESSAGE_LOG_AND_STREAM_STATUS_CHANGED = 13;

    @Deprecated
    public static final int MESSAGE_PACKET_LOSS_DETECTED = 11;

    @Deprecated
    public static final int MESSAGE_PROGRESS_REPORT = 14;

    @Deprecated
    public static final int MESSAGE_READ = 2;

    @Deprecated
    public static final int MESSAGE_STATE_CHANGE = 0;

    @Deprecated
    public static final int MESSAGE_STOP_STREAMING_COMPLETE = 9;
    public static final int MESSAGE_TOAST = 999;

    @Deprecated
    public static final int MSG_STATE_FULLY_INITIALIZED = 3;
    public static final int MSG_STATE_STOP_STREAMING = 5;
    public static final int MSG_STATE_STREAMING = 4;
    public static final String TOAST = "toast";
    private final transient BluetoothAdapter mAdapter;
    private final transient BroadcastReceiver mReceiver;
    protected String mClassName;
    transient List<Handler> mHandlerList;
    private transient LocalDevice localDevice;
    private transient BluetoothAdapter mBluetoothAdapter;
    private int mBluetoothLib;
    private transient ConnectThread mConnectThread;
    private transient ConnectedThread mConnectedThread;
    private transient Context mContext;
    private boolean mContinuousStateUpdates;
    private boolean mDummy;
    private transient DataInputStream mInStream;
    private UUID mSPP_UUID;
    private transient OutputStream mmOutStream;

    public Shimmer(Handler handler, Context context) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(0, handler);
        this.mSetupDeviceWhileConnecting = false;
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    public Shimmer(ArrayList<Handler> arrayList, Context context) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList = arrayList;
        this.mSetupDeviceWhileConnecting = false;
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    @Deprecated
    public Shimmer(Handler handler, String str, Boolean bool) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = false;
        this.mUseProcessingThread = true;
    }

    @Deprecated
    public Shimmer(Context context, Handler handler, String str, Boolean bool) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = false;
        this.mUseProcessingThread = true;
    }

    @Deprecated
    public Shimmer(Context context, Handler handler, String str, double d, int i, int i2, long j, boolean z) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setSamplingRateShimmer(d);
        setDigitalAccelRange(i);
        this.mGSRRange = i2;
        this.mSetEnabledSensors = j;
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = true;
        this.mUseProcessingThread = true;
    }

    @Deprecated
    public Shimmer(Context context, Handler handler, String str, double d, int i, int i2, long j, boolean z, int i3) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setSamplingRateShimmer(d);
        setDigitalAccelRange(i);
        setMagRange(i3);
        this.mGSRRange = i2;
        this.mSetEnabledSensors = j;
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = true;
        this.mUseProcessingThread = true;
    }

    @Deprecated
    public Shimmer(Context context, Handler handler, String str, double d, int i, int i2, long j, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setSamplingRateShimmer(d);
        setDigitalAccelRange(i);
        this.mGSRRange = i2;
        this.mSetEnabledSensors = j;
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = true;
        setLowPowerMag(z4);
        setLowPowerAccelWR(z2);
        setLowPowerGyro(z3);
        setGyroRange(i3);
        setMagRange(i4);
        this.mUseProcessingThread = true;
    }

    @Deprecated
    public Shimmer(Context context, Handler handler, String str, double d, int i, int i2, long j, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4, byte[] bArr, byte[] bArr2) {
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setSamplingRateShimmer(d);
        setDigitalAccelRange(i);
        this.mGSRRange = i2;
        this.mSetEnabledSensors = j;
        this.mShimmerUserAssignedName = str;
        this.mSetupDeviceWhileConnecting = true;
        setLowPowerMag(z4);
        setLowPowerAccelWR(z2);
        setLowPowerGyro(z3);
        setGyroRange(i3);
        setMagRange(i4);
        this.mSetupEXG = true;
        this.mEXG1RegisterArray = bArr;
        this.mEXG2RegisterArray = bArr2;
        this.mUseProcessingThread = true;
    }

    public Shimmer(Handler handler, String str, double d, int i, int i2, Integer[] numArr, int i3, int i4, int i5, int i6, Context context) {
        super(str, d, numArr, i, i2, i3, i4, i6);
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setupOrientation(i5, d);
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    public Shimmer(Handler handler, String str, double d, int i, int i2, int i3, int i4, int i5, Context context) {
        super(str, d, i3, i, i2, i4);
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        setupOrientation(i5, d);
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setupOrientation(i5, d);
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    public Shimmer(Handler handler, String str, double d, int i, int i2, Integer[] numArr, int i3, int i4, int i5, int i6, boolean z, Context context) {
        super(str, d, numArr, i, i2, i3, i4, i6);
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setupOrientation(i5, d);
        setEnableCalibration(z);
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    public Shimmer(Handler handler, String str, double d, int i, int i2, int i3, int i4, int i5, boolean z, Context context) {
        super(str, d, i3, i, i2, i4);
        this.mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        this.mDummy = false;
        this.mmOutStream = null;
        this.mClassName = ShimmerDevice.DEFAULT_SHIMMER_NAME;
        this.mBluetoothLib = 0;
        this.mBluetoothAdapter = null;
        this.mHandlerList = new ArrayList();
        setEnableProcessMarker(false);
        this.mContinuousStateUpdates = true;
        setUseInfoMemConfigMethod(true);
        this.mReceiver = new BroadcastReceiver() { // from class: com.shimmerresearch.android.Shimmer.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(Shimmer.this.mMyBluetoothAddress)) {
                    Shimmer.this.connectionLost();
                }
            }
        };
        setupOrientation(i5, d);
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothRadioState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mHandlerList.add(handler);
        setupOrientation(i5, d);
        setEnableCalibration(z);
        this.mUseProcessingThread = true;
        this.mContext = context;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void batteryStatusChanged() {
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void dockedStateChange() {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean doesSensorKeyExist(int i) {
        return false;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void finishOperation(ShimmerBluetooth.BT_STATE bt_state) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void interpretDataPacketFormat(Object obj, Configuration.COMMUNICATION_TYPE communication_type) {
    }

    @Override // com.shimmerresearch.driver.BasicProcessWithCallBack
    protected void processMsgFromCallback(ShimmerMsg shimmerMsg) {
    }

    public void setContinuousStateUpdates(boolean z) {
        this.mContinuousStateUpdates = z;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void startOperation(ShimmerBluetooth.BT_STATE bt_state) {
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i) {
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void stateHandler(Object obj) {
    }

    protected void unregisterDisconnectListener() {
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this.mReceiver);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    protected void registerDisconnectListener() {
        if (this.mContext != null) {
            System.out.println("initialize process 0) register disconnect listener");
            ((BluetoothManager) this.mContext.getSystemService("bluetooth")).getAdapter();
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public synchronized void connect(String str, String str2) {
        ConnectThread connectThread;
        ConnectThread connectThread2;
        registerDisconnectListener();
        this.mIamAlive = false;
        getListofInstructions().clear();
        this.mFirstTime = true;
        if (str2 == "default") {
            this.mMyBluetoothAddress = str;
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.mBluetoothAdapter = defaultAdapter;
            BluetoothDevice remoteDevice = defaultAdapter.getRemoteDevice(str);
            if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTING && (connectThread2 = this.mConnectThread) != null) {
                connectThread2.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread = this.mConnectedThread;
            if (connectedThread != null) {
                connectedThread.cancel();
                this.mConnectedThread = null;
            }
            ConnectThread connectThread3 = new ConnectThread(remoteDevice);
            this.mConnectThread = connectThread3;
            connectThread3.start();
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTING);
        } else if (str2 == "gerdavax") {
            this.mMyBluetoothAddress = str;
            if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTING && (connectThread = this.mConnectThread) != null) {
                connectThread.cancel();
                this.mConnectThread = null;
            }
            ConnectedThread connectedThread2 = this.mConnectedThread;
            if (connectedThread2 != null) {
                connectedThread2.cancel();
                this.mConnectedThread = null;
            }
            if (str == null) {
                return;
            }
            Log.d("ConnectionStatus", "Get Local Device  " + str);
            LocalDevice localDevice = LocalDevice.getInstance();
            this.localDevice = localDevice;
            new ConnectThreadArduino(localDevice.getRemoteForAddr(str), this).start();
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTING);
        }
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket) {
        System.out.println("initialize process 2) connected and start initialize");
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        this.mConnectedThread = new ConnectedThread(bluetoothSocket);
        this.mIOThread = new ShimmerBluetooth.IOThread();
        this.mIOThread.setName("IO Thread " + bluetoothSocket.getRemoteDevice().getAddress());
        this.mIOThread.start();
        if (this.mUseProcessingThread) {
            this.mPThread = new ShimmerBluetooth.ProcessingThread();
            this.mPThread.setName("P Thread " + bluetoothSocket.getRemoteDevice().getAddress());
            this.mPThread.start();
        }
        sendMsgToHandlerList(5);
        initialize();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public void stop() throws IOException {
        if (this.mTimerReadStatus != null) {
            this.mTimerReadStatus.cancel();
            this.mTimerReadStatus.purge();
        }
        if (this.mTimerCheckAlive != null) {
            this.mTimerCheckAlive.cancel();
            this.mTimerCheckAlive.purge();
            this.mTimerCheckAlive = null;
        }
        if (this.mTimerCheckForAckOrResp != null) {
            this.mTimerCheckForAckOrResp.cancel();
            this.mTimerCheckForAckOrResp.purge();
        }
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        this.mIsStreaming = false;
        this.mIsInitialised = false;
        if (this.mIOThread != null) {
            this.mIOThread.stop = true;
            this.mIOThread = null;
            if (this.mUseProcessingThread) {
                this.mPThread.stop = true;
                this.mPThread = null;
            }
        }
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void clearSingleDataPacketFromBuffers(byte[] bArr, int i) {
        byte[] byteArray = this.mByteArrayOutputStream.toByteArray();
        if (byteArray.length - i < 0) {
            return;
        }
        int length = byteArray.length - i;
        byte[] bArr2 = new byte[length];
        System.arraycopy(byteArray, i, bArr2, 0, length);
        this.mByteArrayOutputStream.reset();
        try {
            this.mByteArrayOutputStream.write(bArr2);
            if (this.mEnablePCTimeStamps) {
                for (int i2 = 0; i2 < i; i2++) {
                    try {
                        this.mListofPCTimeStamps.remove(0);
                    } catch (Exception e) {
                        consolePrintException(e.getMessage(), e.getStackTrace());
                    }
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void processPacket() {
        setIamAlive(true);
        byte[] byteArray = this.mByteArrayOutputStream.toByteArray();
        if (this.mByteArrayOutputStream.size() > 10000) {
            this.mByteArrayOutputStream.reset();
            this.mListofPCTimeStamps.clear();
            consolePrintLn("Byte Array Size (reset): " + this.mByteArrayOutputStream.size());
            return;
        }
        int packetSizeWithCrc = getPacketSizeWithCrc() + 2;
        byte[] bArr = new byte[packetSizeWithCrc];
        System.arraycopy(byteArray, 0, bArr, 0, packetSizeWithCrc);
        if (bArr[0] == 0 && bArr[getPacketSizeWithCrc() + 1] == 0) {
            if (this.mBtCommsCrcModeCurrent != ShimmerBluetooth.BT_CRC_MODE.OFF && !checkCrc(bArr, getPacketSize() + 1)) {
                discardFirstBufferByte();
                return;
            } else {
                processDataPacket(bArr);
                clearSingleDataPacketFromBuffers(bArr, getPacketSizeWithCrc() + 1);
                return;
            }
        }
        if (bArr[0] == 0 && bArr[getPacketSizeWithCrc() + 1] == -1) {
            if (this.mByteArrayOutputStream.size() > getPacketSizeWithCrc() + 2) {
                byte[] byteArray2 = this.mByteArrayOutputStream.toByteArray();
                int packetSizeWithCrc2 = getPacketSizeWithCrc() + 3;
                byte[] bArr2 = new byte[packetSizeWithCrc2];
                System.arraycopy(byteArray2, 0, bArr2, 0, packetSizeWithCrc2);
                if (bArr2[getPacketSizeWithCrc() + 2] == 0) {
                    processDataPacket(bArr2);
                    clearSingleDataPacketFromBuffers(bArr2, getPacketSizeWithCrc() + 2);
                    if (isKnownSetCommand(this.mCurrentCommand)) {
                        stopTimerCheckForAckOrResp();
                        this.mWaitForAck = false;
                        processAckFromSetCommand(this.mCurrentCommand);
                        this.mTransactionCompleted = true;
                        setInstructionStackLock(false);
                    }
                    printLogDataForDebugging("Ack Received for Command: \t\t\t" + btCommandToString(this.mCurrentCommand));
                    return;
                }
                if (isSupportedInStreamCmds() && bArr2[getPacketSizeWithCrc() + 2] == -118) {
                    printLogDataForDebugging("COMMAND TXed and ACK RECEIVED IN STREAM");
                    printLogDataForDebugging("INS CMD RESP");
                    stopTimerCheckForAckOrResp();
                    this.mWaitForResponse = false;
                    this.mWaitForAck = false;
                    processInstreamResponse(true);
                    if (getListofInstructions().size() > 0) {
                        removeInstruction(0);
                    }
                    this.mTransactionCompleted = true;
                    setInstructionStackLock(false);
                    return;
                }
                printLogDataForDebugging("Unknown parsing error while streaming");
                discardFirstBufferByte();
                return;
            }
            return;
        }
        printLogDataForDebugging("Packet syncing problem:\tExpected: " + (getPacketSizeWithCrc() + 2) + "bytes. Buffer contains " + this.mByteArrayOutputStream.size() + "bytes\nBuffer = " + UtilShimmer.bytesToHexStringWithSpacesFormatted(this.mByteArrayOutputStream.toByteArray()));
        discardFirstBufferByte();
    }

    protected byte[] getDataFromArrayOutputStream(int i) {
        if (this.mByteArrayOutputStream.size() < getPacketSizeWithCrc() + i) {
            return null;
        }
        byte[] byteArray = this.mByteArrayOutputStream.toByteArray();
        int packetSizeWithCrc = getPacketSizeWithCrc() + i;
        byte[] bArr = new byte[packetSizeWithCrc];
        System.arraycopy(byteArray, 0, bArr, 0, packetSizeWithCrc);
        return bArr;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void processInstreamResponse(boolean z) {
        if (!this.mBluetoothRadioState.equals(ShimmerBluetooth.BT_STATE.STREAMING)) {
            super.processInstreamResponse(z);
            return;
        }
        byte[] dataFromArrayOutputStream = getDataFromArrayOutputStream(4);
        if (dataFromArrayOutputStream != null) {
            int i = 1;
            byte b = dataFromArrayOutputStream[dataFromArrayOutputStream.length - 1];
            consolePrintLn("In-stream received = " + btCommandToString(b));
            int i2 = 5;
            if (b == -120) {
                printLogDataForDebugging("IN STREAM: DIR_RESPONSE");
                byte[] dataFromArrayOutputStream2 = getDataFromArrayOutputStream(5);
                if (dataFromArrayOutputStream2 != null) {
                    int i3 = dataFromArrayOutputStream2[dataFromArrayOutputStream2.length - 1];
                    byte[] bArr = new byte[i3];
                    byte[] dataFromArrayOutputStream3 = getDataFromArrayOutputStream(i3 + 5);
                    System.arraycopy(dataFromArrayOutputStream3, dataFromArrayOutputStream3.length - i3, bArr, 0, i3);
                    this.mDirectoryName = new String(bArr);
                    printLogDataForDebugging("Directory Name = " + this.mDirectoryName);
                    processDataPacket(dataFromArrayOutputStream3);
                    clearSingleDataPacketFromBuffers(dataFromArrayOutputStream3, dataFromArrayOutputStream3.length + this.mBtCommsCrcModeCurrent.getNumCrcBytes());
                    return;
                }
                return;
            }
            if (b != 113) {
                if (b == -108) {
                    printLogDataForDebugging("IN STREAM: VBATT_RESPONSE");
                    byte[] dataFromArrayOutputStream4 = getDataFromArrayOutputStream(7);
                    if (dataFromArrayOutputStream4 != null) {
                        byte[] bArr2 = new byte[3];
                        System.arraycopy(dataFromArrayOutputStream4, dataFromArrayOutputStream4.length - 3, bArr2, 0, 3);
                        ShimmerBattStatusDetails shimmerBattStatusDetails = new ShimmerBattStatusDetails(((bArr2[1] & 255) << 8) + (bArr2[0] & 255), bArr2[2]);
                        setBattStatusDetails(shimmerBattStatusDetails);
                        printLogDataForDebugging("Battery Status:\tVoltage=" + shimmerBattStatusDetails.getBattVoltageParsed() + "\tCharging status=" + shimmerBattStatusDetails.getChargingStatusParsed() + "\tBatt %=" + shimmerBattStatusDetails.getEstimatedChargePercentageParsed());
                        processDataPacket(dataFromArrayOutputStream4);
                        clearSingleDataPacketFromBuffers(dataFromArrayOutputStream4, dataFromArrayOutputStream4.length + this.mBtCommsCrcModeCurrent.getNumCrcBytes());
                        return;
                    }
                    return;
                }
                discardFirstBufferByte();
                return;
            }
            printLogDataForDebugging("IN STREAM: STATUS_RESPONSE");
            if (isSupportedUSBPluggedInStatus()) {
                i2 = 6;
                i = 2;
            }
            byte[] dataFromArrayOutputStream5 = getDataFromArrayOutputStream(i2);
            if (dataFromArrayOutputStream5 != null) {
                byte[] bArr3 = new byte[i];
                System.arraycopy(dataFromArrayOutputStream5, dataFromArrayOutputStream5.length - i, bArr3, 0, i);
                parseStatusByte(bArr3);
                if (!isSupportedRtcStateInStatus()) {
                    if (!this.mIsSensing && !isInitialised()) {
                        writeRealTimeClock();
                    }
                } else if (!isSDLogging() && (!isInitialised() || !this.mIsRtcSet)) {
                    writeRealTimeClock();
                }
                eventLogAndStreamStatusChanged(this.mCurrentCommand);
                processDataPacket(dataFromArrayOutputStream5);
                clearSingleDataPacketFromBuffers(dataFromArrayOutputStream5, dataFromArrayOutputStream5.length + this.mBtCommsCrcModeCurrent.getNumCrcBytes());
                return;
            }
            return;
        }
        printLogDataForDebugging("IN STREAM: buffer temp is null, byte array output stream size is " + this.mByteArrayOutputStream.size());
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void processWhileStreaming() throws IOException {
        byte[] bytes = readBytes(availableBytes());
        if (bytes != null) {
            try {
                if (bytes.length > 0) {
                    this.mByteArrayOutputStream.write(bytes);
                }
                if (this.mEnablePCTimeStamps) {
                    for (byte b : bytes) {
                        this.mListofPCTimeStamps.add(Long.valueOf(System.currentTimeMillis()));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            printLogDataForDebugging("readbyte null");
        }
        while (this.mByteArrayOutputStream.size() >= getPacketSizeWithCrc() + 2) {
            int size = this.mByteArrayOutputStream.size();
            processPacket();
            if (size == this.mByteArrayOutputStream.size()) {
                consolePrintLn("processWhileStreaming: Leaving while loop");
                return;
            }
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void clearSerialBuffer() throws IOException {
        startTimerCheckForSerialPortClear();
        byte[] bytes = new byte[0];
        while (availableBytes() != 0) {
            if (bytesAvailableToBeRead()) {
                bytes = readBytes(availableBytes());
                if (this.mSerialPortReadTimeout) {
                    break;
                }
            }
        }
        if (bytes.length > 0) {
            printLogDataForDebugging("Clearing Buffer:\t\t" + UtilShimmer.bytesToHexStringWithSpacesFormatted(bytes));
        }
        stopTimerCheckForSerialPortClear();
    }

    public void write(byte[] bArr) throws IOException {
        this.mConnectedThread.write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionFailed() {
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        this.mIsInitialised = false;
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Unable to connect device");
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void connectionLost() {
        if (this.mIOThread != null) {
            this.mIOThread.stop = true;
            this.mIOThread = null;
            if (this.mUseProcessingThread) {
                this.mPThread.stop = true;
                this.mPThread = null;
            }
        }
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.DISCONNECTED);
        this.mIsInitialised = false;
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device connection was lost");
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void inquiryDone() {
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Inquiry done for device-> " + this.mMyBluetoothAddress);
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
        isReadyForStreaming();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void isReadyForStreaming() {
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device " + this.mMyBluetoothAddress + " is ready for Streaming");
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
        if (!this.mIsInitialised) {
            this.mIsInitialised = true;
        }
        if (isSDLogging() && this.mIsInitialised) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.SDLOGGING);
        } else {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        }
        sendMsgToHandlerListTarget(1, new CallbackObject(2, getMacId(), getComPort()));
        sendMsgToHandlerListTarget(0, -1, -1, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress(), this.mBluetoothRadioState));
        Log.d(this.mClassName, "Shimmer " + this.mMyBluetoothAddress + " Initialization completed and is ready for Streaming");
        if (this.mAutoStartStreaming) {
            try {
                startStreaming();
            } catch (ShimmerException e) {
                connectionLost();
                e.printStackTrace();
            }
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void isNowStreaming() {
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device " + this.mMyBluetoothAddress + " is now Streaming");
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
        Log.d(this.mClassName, "Shimmer " + this.mMyBluetoothAddress + " is now Streaming");
        if (isSDLogging()) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING);
        } else {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
        }
        sendMsgToHandlerListTarget(1, new CallbackObject(1, getMacId(), getComPort()));
        sendMsgToHandlerListTarget(0, 4, -1, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress(), this.mBluetoothRadioState));
    }

    public boolean getStreamingStatus() {
        return this.mIsStreaming;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public boolean getInstructionStatus() {
        return this.mTransactionCompleted;
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth, com.shimmerresearch.driver.ShimmerObject
    protected void sendStatusMsgPacketLossDetected() {
        sendMsgToHandlerListTarget(11, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress()));
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected boolean bytesAvailableToBeRead() {
        try {
            return this.mInStream.available() != 0;
        } catch (IOException e) {
            connectionLost();
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected int availableBytes() {
        try {
            return this.mInStream.available();
        } catch (IOException e) {
            connectionLost();
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void writeBytes(byte[] bArr) throws IOException {
        write(bArr);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected byte[] readBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        try {
            this.mInStream.readFully(bArr, 0, i);
            return bArr;
        } catch (IOException e) {
            connectionLost();
            System.out.println("Connection Lost");
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected byte readByte() throws IOException {
        byte[] bArr = new byte[1];
        try {
            this.mInStream.readFully(bArr, 0, 1);
            return bArr[0];
        } catch (IOException e) {
            connectionLost();
            e.printStackTrace();
            return (byte) 0;
        }
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    protected void dataHandler(ObjectCluster objectCluster) {
        sendMsgToHandlerListTarget(2, objectCluster);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void sendStatusMSGtoUI(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("toast", str);
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void printLogDataForDebugging(String str) {
        Log.d(this.mClassName, str);
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void hasStopStreaming() {
        startTimerReadStatus();
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device " + this.mMyBluetoothAddress + " stopped streaming");
        if (isSDLogging()) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.SDLOGGING);
        } else {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        }
        sendMsgToHandlerList(MESSAGE_TOAST, bundle);
        sendMsgToHandlerListTarget(1, new CallbackObject(0, getMacId(), getComPort()));
        sendMsgToHandlerListTarget(0, 5, -1, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress(), this.mBluetoothRadioState));
    }

    protected void logAndStreamStatusChanged() {
        if (this.mCurrentCommand == -109) {
            if (this.mIsStreaming) {
                setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
                return;
            } else if (this.mIsConnected) {
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
        setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        if (this.mContinuousStateUpdates) {
            sendMsgToHandlerListTarget(0, -1, -1, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress(), getBluetoothRadioState()));
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        sendMsgToHandlerListTarget(14, bluetoothProgressReportPerCmd);
    }

    public ShimmerBluetooth.BT_STATE getState() {
        return this.mBluetoothRadioState;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state) {
        boolean bluetoothRadioState = super.setBluetoothRadioState(bt_state);
        if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTED) {
            this.mIsConnected = true;
            this.mIsStreaming = false;
        } else if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.SDLOGGING) {
            this.mIsConnected = true;
            this.mIsInitialised = true;
            this.mIsStreaming = false;
        } else if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.STREAMING) {
            this.mIsStreaming = true;
        } else if (this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.DISCONNECTED || this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTION_LOST || this.mBluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTION_FAILED) {
            this.mIsConnected = false;
            this.mIsStreaming = false;
            this.mIsInitialised = false;
            unregisterDisconnectListener();
        }
        if (this.mContinuousStateUpdates) {
            sendMsgToHandlerListTarget(0, -1, -1, new ObjectCluster(this.mShimmerUserAssignedName, getBluetoothAddress(), bt_state));
        }
        return bluetoothRadioState;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public boolean isConnected() {
        return this.mIsConnected;
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public void disconnect() throws IOException {
        stop();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    protected void eventLogAndStreamStatusChanged(byte b) {
        logAndStreamStatusChanged();
    }

    @Override // com.shimmerresearch.driver.ShimmerDevice
    public ShimmerDevice deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (Shimmer) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
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
    public Set<Integer> getSensorIdsSet() {
        return super.getSensorIdsSet();
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public void readBattery() throws IOException {
        if (isStreaming()) {
            writeBytes(new byte[]{-107});
        } else {
            super.readBattery();
        }
    }

    @Override // com.shimmerresearch.bluetooth.ShimmerBluetooth
    public void readStatusLogAndStream() throws IOException {
        if (isSupportedInStreamCmds()) {
            if (isStreaming()) {
                writeBytes(new byte[]{ShimmerObject.GET_STATUS_COMMAND});
            } else {
                super.readStatusLogAndStream();
            }
        }
    }

    public void setRadio(BluetoothSocket bluetoothSocket) {
        System.out.println("initialize process set radio");
        registerDisconnectListener();
        if (bluetoothSocket.isConnected()) {
            setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTING);
            this.mMyBluetoothAddress = bluetoothSocket.getRemoteDevice().getAddress();
            connected(bluetoothSocket);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgToHandlerList(int i) {
        for (Handler handler : this.mHandlerList) {
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(i));
            }
        }
    }

    private void sendMsgToHandlerList(int i, Bundle bundle) {
        for (Handler handler : this.mHandlerList) {
            if (handler != null) {
                Message messageObtainMessage = handler.obtainMessage(i);
                messageObtainMessage.setData(bundle);
                handler.sendMessage(messageObtainMessage);
            }
        }
    }

    private void sendMsgToHandlerListTarget(int i, Object obj) {
        for (Handler handler : this.mHandlerList) {
            if (handler != null) {
                handler.obtainMessage(i, obj).sendToTarget();
            }
        }
    }

    private void sendMsgToHandlerListTarget(int i, int i2, int i3, Object obj) {
        for (Handler handler : this.mHandlerList) {
            if (handler != null) {
                handler.obtainMessage(i, i2, i3, obj).sendToTarget();
            }
        }
    }

    public void addHandler(Handler handler) {
        this.mHandlerList.add(handler);
    }

    private class ConnectThread extends Thread {
        private final BluetoothDevice mmDevice;
        private final BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice bluetoothDevice) throws IOException {
            BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord;
            this.mmDevice = bluetoothDevice;
            Log.d(Shimmer.this.mClassName, "Start of Default ConnectThread");
            try {
                bluetoothSocketCreateRfcommSocketToServiceRecord = bluetoothDevice.createRfcommSocketToServiceRecord(Shimmer.this.mSPP_UUID);
            } catch (IOException unused) {
                Shimmer.this.connectionLost();
                bluetoothSocketCreateRfcommSocketToServiceRecord = null;
            }
            this.mmSocket = bluetoothSocketCreateRfcommSocketToServiceRecord;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            System.out.println("initialize process 1) start connecting thread");
            setName("ConnectThread");
            Shimmer.this.mAdapter.cancelDiscovery();
            try {
                this.mmSocket.connect();
                synchronized (Shimmer.this) {
                    Shimmer.this.mConnectThread = null;
                }
                Shimmer.this.connected(this.mmSocket);
            } catch (IOException unused) {
                Shimmer.this.connectionFailed();
                try {
                    this.mmSocket.close();
                } catch (IOException unused2) {
                }
            }
        }

        public void cancel() throws IOException {
            try {
                this.mmSocket.close();
            } catch (IOException unused) {
            }
        }
    }

    private class ConnectThreadArduino extends Thread {
        private final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        private final RemoteDevice mDevice;
        Shimmer shimmer;
        private BtSocket mSocket;

        public ConnectThreadArduino(RemoteDevice remoteDevice, Shimmer shimmer) {
            this.shimmer = shimmer;
            this.mDevice = remoteDevice;
            Log.d(Shimmer.this.mClassName, " Start of ArduinoConnectThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            boolean zEnsurePaired;
            try {
                try {
                    zEnsurePaired = this.mDevice.ensurePaired();
                } catch (Exception e) {
                    Log.d(Shimmer.this.mClassName, "Connection Failed");
                    Shimmer.this.connectionFailed();
                    e.printStackTrace();
                    BtSocket btSocket = this.mSocket;
                    if (btSocket != null) {
                        try {
                            btSocket.close();
                            Log.d(Shimmer.this.mClassName, "Arduinothreadclose");
                            return;
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    return;
                }
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                zEnsurePaired = false;
            }
            if (!zEnsurePaired) {
                Thread.sleep(VerisenseMessage.TIMEOUT_MS.DATA_TRANSFER);
                zEnsurePaired = this.mDevice.ensurePaired();
            }
            if (!zEnsurePaired) {
                Log.d(Shimmer.this.mClassName, "not paired!");
                Shimmer.this.connectionFailed();
                return;
            }
            Log.d(Shimmer.this.mClassName, "is paired!");
            try {
                this.mSocket = this.mDevice.openSocket(this.SPP_UUID);
            } catch (Exception unused2) {
                Log.d(Shimmer.this.mClassName, "Connection via SDP unsuccessful, try to connect via port directly");
                this.mSocket = this.mDevice.openSocket(1);
                Log.d(Shimmer.this.mClassName, "I am here");
            }
            Log.d(Shimmer.this.mClassName, "Going to Manage Socket");
            if (this.shimmer.getState() != ShimmerBluetooth.BT_STATE.DISCONNECTED) {
                Log.d(Shimmer.this.mClassName, "ManagingSocket");
                manageConnectedSocket(this.mSocket);
            }
        }

        public void cancel() {
            try {
                BtSocket btSocket = this.mSocket;
                if (btSocket != null) {
                    btSocket.close();
                }
            } catch (IOException unused) {
                Log.e(ShimmerDevice.DEFAULT_SHIMMER_NAME, "cannot close socket to " + this.mDevice.getAddress());
            }
        }

        private void manageConnectedSocket(BtSocket btSocket) {
            String address = this.mDevice.getAddress();
            Shimmer shimmer = Shimmer.this;
            shimmer.mConnectedThread = shimmer.new ConnectedThread(btSocket, address);
            Log.d(Shimmer.this.mClassName, "ConnectedThread is about to start");
            Shimmer.this.mIOThread = new ShimmerBluetooth.IOThread();
            Shimmer.this.mIOThread.start();
            if (Shimmer.this.mUseProcessingThread) {
                Shimmer.this.mPThread = new ShimmerBluetooth.ProcessingThread();
                Shimmer.this.mPThread.start();
            }
            Shimmer.this.mMyBluetoothAddress = this.mDevice.getAddress();
            Shimmer.this.sendMsgToHandlerList(5);
            while (!Shimmer.this.mIOThread.isAlive()) {
            }
            Log.d(Shimmer.this.mClassName, "alive!!");
            Shimmer.this.initialize();
        }
    }

    private class ConnectedThread {
        private BtSocket mSocket;
        private BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket bluetoothSocket) throws IOException {
            InputStream inputStream;
            OutputStream outputStream = null;
            this.mSocket = null;
            this.mmSocket = bluetoothSocket;
            try {
                inputStream = bluetoothSocket.getInputStream();
            } catch (IOException unused) {
                inputStream = null;
            }
            try {
                outputStream = bluetoothSocket.getOutputStream();
            } catch (IOException unused2) {
                Shimmer.this.connectionLost();
                Shimmer.this.mInStream = new DataInputStream(inputStream);
                Shimmer.this.mmOutStream = outputStream;
            }
            Shimmer.this.mInStream = new DataInputStream(inputStream);
            Shimmer.this.mmOutStream = outputStream;
        }

        public ConnectedThread(BtSocket btSocket, String str) {
            InputStream inputStream;
            OutputStream outputStream = null;
            this.mmSocket = null;
            this.mSocket = btSocket;
            try {
                inputStream = btSocket.getInputStream();
                try {
                    outputStream = btSocket.getOutputStream();
                } catch (Exception unused) {
                    Log.d(Shimmer.this.mClassName, "Connected Thread Error");
                    Shimmer.this.connectionLost();
                    Shimmer.this.mInStream = new DataInputStream(inputStream);
                    Shimmer.this.mmOutStream = outputStream;
                }
            } catch (Exception unused2) {
                inputStream = null;
            }
            Shimmer.this.mInStream = new DataInputStream(inputStream);
            Shimmer.this.mmOutStream = outputStream;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void write(byte[] bArr) throws IOException {
            try {
                Shimmer.this.mmOutStream.write(bArr);
                Log.d(Shimmer.this.mClassName, "Command transmitted: " + Shimmer.this.mMyBluetoothAddress + "; Command Issued: " + ((int) Shimmer.this.mCurrentCommand));
            } catch (IOException unused) {
                Log.d(Shimmer.this.mClassName, "Command NOT transmitted: " + Shimmer.this.mMyBluetoothAddress + "; Command Issued: " + ((int) Shimmer.this.mCurrentCommand));
            }
        }

        public void cancel() throws IOException {
            if (Shimmer.this.mInStream != null) {
                try {
                    Shimmer.this.mInStream.close();
                } catch (IOException unused) {
                }
            }
            if (Shimmer.this.mmOutStream != null) {
                try {
                    Shimmer.this.mmOutStream.close();
                } catch (IOException unused2) {
                }
            }
            if (this.mmSocket != null) {
                try {
                    if (Shimmer.this.mBluetoothLib == 0) {
                        this.mmSocket.close();
                    } else {
                        this.mSocket.close();
                    }
                } catch (IOException unused3) {
                }
            }
        }
    }
}
