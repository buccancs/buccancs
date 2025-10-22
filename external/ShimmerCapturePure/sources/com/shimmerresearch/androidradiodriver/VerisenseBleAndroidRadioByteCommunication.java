package com.shimmerresearch.androidradiodriver;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import bolts.TaskCompletionSource;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.communication.AbstractByteCommunication;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class VerisenseBleAndroidRadioByteCommunication extends AbstractByteCommunication {
    protected String TxID = "6E400002-B5A3-F393-E0A9-E50E24DCCA9E";
    protected String RxID = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E";
    protected String ServiceID = "6E400001-B5A3-F393-E0A9-E50E24DCCA9E";
    protected UUID sid = UUID.fromString(this.ServiceID);
    protected UUID txid = UUID.fromString(this.TxID);
    protected UUID rxid = UUID.fromString(this.RxID);
    BleDevice mBleDevice;
    String mMac;
    String uuid;
    TaskCompletionSource<String> mTaskConnect = new TaskCompletionSource<>();
    TaskCompletionSource<String> mTaskMTU = new TaskCompletionSource<>();

    public VerisenseBleAndroidRadioByteCommunication(String str) {
        this.mMac = str;
    }

    @Override // com.shimmerresearch.verisense.communication.AbstractByteCommunication
    public void stop() {
    }

    @Override // com.shimmerresearch.verisense.communication.AbstractByteCommunication
    public void connect() throws InterruptedException {
        this.mTaskConnect = new TaskCompletionSource<>();
        BleManager.getInstance().connect(this.mMac, new BleGattCallback() { // from class: com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication.1
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                System.out.println("Connecting");
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(BleDevice bleDevice, BleException bleException) {
                if (VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener != null) {
                    VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener.eventDisconnected();
                }
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                VerisenseBleAndroidRadioByteCommunication.this.mTaskMTU = new TaskCompletionSource<>();
                BleManager.getInstance().setMtu(bleDevice, 251, new BleMtuChangedCallback() { // from class: com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication.1.1
                    @Override // com.clj.fastble.callback.BleMtuChangedCallback
                    public void onSetMTUFailure(BleException bleException) {
                        System.out.println("MTU Failure");
                    }

                    @Override // com.clj.fastble.callback.BleMtuChangedCallback
                    public void onMtuChanged(int i2) {
                        System.out.println("MTU Changed: " + i2);
                        VerisenseBleAndroidRadioByteCommunication.this.mTaskMTU.setResult("MTU Changed: " + i2);
                    }
                });
                try {
                    VerisenseBleAndroidRadioByteCommunication.this.mTaskMTU.getTask().waitForCompletion(3L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bluetoothGatt.requestConnectionPriority(1);
                if (VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener != null) {
                    VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener.eventConnected();
                }
                VerisenseBleAndroidRadioByteCommunication.this.mBleDevice = bleDevice;
                VerisenseBleAndroidRadioByteCommunication.this.startServiceS(bleDevice);
                System.out.println(bleDevice.getMac() + " Connected");
                if (Build.VERSION.SDK_INT >= 26) {
                    bluetoothGatt.setPreferredPhy(2, 2, 0);
                }
                VerisenseBleAndroidRadioByteCommunication.this.mTaskConnect.setResult("Connected");
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                System.out.println();
            }
        });
        try {
            boolean zWaitForCompletion = this.mTaskConnect.getTask().waitForCompletion(5L, TimeUnit.SECONDS);
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
        BleManager.getInstance().notify(bleDevice, bluetoothGattCharacteristic.getService().getUuid().toString(), bluetoothGattCharacteristic.getUuid().toString(), new BleNotifyCallback() { // from class: com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication.2
            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifyFailure(BleException bleException) {
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onNotifySuccess() {
            }

            @Override // com.clj.fastble.callback.BleNotifyCallback
            public void onCharacteristicChanged(byte[] bArr) {
                if (VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener != null) {
                    try {
                        VerisenseBleAndroidRadioByteCommunication.this.mByteCommunicationListener.eventNewBytesReceived(bArr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.shimmerresearch.verisense.communication.AbstractByteCommunication
    public void disconnect() {
        BleManager.getInstance().disconnect(this.mBleDevice);
    }

    @Override // com.shimmerresearch.verisense.communication.AbstractByteCommunication
    public void writeBytes(byte[] bArr) {
        BleManager.getInstance().write(this.mBleDevice, this.sid.toString(), this.txid.toString(), bArr, false, new BleWriteCallback() { // from class: com.shimmerresearch.androidradiodriver.VerisenseBleAndroidRadioByteCommunication.3
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

    public String convertMacIDtoUUID(String str) {
        return "00000000-0000-0000-0000-" + str.replace(":", "");
    }

    @Override // com.shimmerresearch.verisense.communication.AbstractByteCommunication
    public String getUuid() {
        return convertMacIDtoUUID(this.mMac);
    }
}
