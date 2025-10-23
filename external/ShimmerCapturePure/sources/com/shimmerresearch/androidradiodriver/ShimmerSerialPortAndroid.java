package com.shimmerresearch.androidradiodriver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.ErrorCodesSerialPort;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.driver.ShimmerDevice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/* loaded from: classes2.dex */
public class ShimmerSerialPortAndroid extends AbstractSerialPortHal {
    public String mBluetoothAddress;
    public ShimmerBluetooth.BT_STATE mState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
    private transient BluetoothDevice device;
    private transient BluetoothSocket mBluetoothSocket;
    private transient DataInputStream mInStream;
    private transient OutputStream mOutStream;
    private transient SerialPortListener mShimmerSerialEventCallback;
    private boolean mUseListenerThread;
    private UUID mSPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private transient BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public ShimmerSerialPortAndroid(String str) {
        this.mBluetoothAddress = str;
    }

    public ShimmerSerialPortAndroid(String str, boolean z) {
        this.mBluetoothAddress = str;
        this.mUseListenerThread = z;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void clearSerialPortRxBuffer() {
    }

    public BluetoothSocket getBluetoothSocket() {
        return this.mBluetoothSocket;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isDisonnected() {
        return false;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isSerialPortReaderStarted() {
        return this.mInStream != null;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void registerSerialPortRxEventCallback(SerialPortListener serialPortListener) {
        this.mShimmerSerialEventCallback = serialPortListener;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void setVerboseMode(boolean z, boolean z2) {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void connect() throws IOException {
        System.out.println("initialize process shimmer serial port android");
        if (this.mState == ShimmerBluetooth.BT_STATE.DISCONNECTED) {
            createBluetoothSocket();
            connectBluetoothSocket();
            if (this.mBluetoothSocket != null) {
                if (getIOStreams()) {
                    this.mState = ShimmerBluetooth.BT_STATE.CONNECTED;
                    eventDeviceConnected();
                    return;
                } else {
                    eventDeviceDisconnected();
                    Log.e(ShimmerDevice.DEFAULT_SHIMMER_NAME, "Could not get IO Stream!");
                    catchException(new NullPointerException(), ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_OPENING);
                    return;
                }
            }
            eventDeviceDisconnected();
            Log.e(ShimmerDevice.DEFAULT_SHIMMER_NAME, "Shimmer device is not online!");
            catchException(new NullPointerException(), ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTION);
        }
    }

    private void createBluetoothSocket() {
        try {
            BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(this.mBluetoothAddress);
            this.device = remoteDevice;
            this.mBluetoothSocket = remoteDevice.createRfcommSocketToServiceRecord(this.mSPP_UUID);
        } catch (IOException e) {
            catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_OPENING);
        }
    }

    private void connectBluetoothSocket() throws IOException {
        try {
            this.mBluetoothSocket.connect();
        } catch (IOException e) {
            closeBluetoothSocket();
            catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_OPENING);
        }
    }

    private boolean getIOStreams() {
        try {
            this.mInStream = new DataInputStream(this.mBluetoothSocket.getInputStream());
            this.mOutStream = this.mBluetoothSocket.getOutputStream();
            if (!this.mUseListenerThread) {
                return true;
            }
            startListening();
            return true;
        } catch (IOException e) {
            catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_OPENING);
            return false;
        }
    }

    private void closeBluetoothSocket() {
        try {
            try {
                BluetoothSocket bluetoothSocket = this.mBluetoothSocket;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (IOException e) {
                catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_CLOSING);
            }
        } finally {
            this.mBluetoothSocket = null;
        }
    }

    private void closeInputStream() {
        try {
            try {
                DataInputStream dataInputStream = this.mInStream;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_CLOSING);
            }
        } finally {
            this.mInStream = null;
        }
    }

    private void closeOutputStream() {
        try {
            try {
                OutputStream outputStream = this.mOutStream;
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                catchException(e, ErrorCodesSerialPort.SHIMMERUART_COMM_ERR_PORT_EXCEPTON_CLOSING);
            }
        } finally {
            this.mOutStream = null;
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void disconnect() {
        closeBTConnection();
        eventDeviceDisconnected();
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void closeSafely() {
        closeBTConnection();
        eventDeviceDisconnected();
    }

    private void closeBTConnection() {
        closeInputStream();
        closeOutputStream();
        closeBluetoothSocket();
        this.mState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void txBytes(byte[] bArr) throws IOException {
        synchronized (this) {
            if (this.mState == ShimmerBluetooth.BT_STATE.DISCONNECTED) {
                return;
            }
            write(bArr);
        }
    }

    private void write(byte[] bArr) throws IOException {
        try {
            this.mOutStream.write(bArr);
        } catch (IOException e) {
            catchException(e, 2008);
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public byte[] rxBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        DataInputStream dataInputStream = this.mInStream;
        if (dataInputStream == null) {
            return null;
        }
        try {
            dataInputStream.readFully(bArr, 0, i);
            return bArr;
        } catch (IOException e) {
            catchException(e, 2009);
            return null;
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean bytesAvailableToBeRead() {
        try {
            DataInputStream dataInputStream = this.mInStream;
            if (dataInputStream != null) {
                return dataInputStream.available() != 0;
            }
            System.out.println("IN STREAM NULL");
            return false;
        } catch (IOException e) {
            catchException(e, 2009);
            return false;
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public int availableBytes() {
        try {
            return this.mInStream.available();
        } catch (IOException e) {
            catchException(e, 2009);
            return 0;
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isConnected() {
        BluetoothSocket bluetoothSocket = this.mBluetoothSocket;
        if (bluetoothSocket != null) {
            return bluetoothSocket.isConnected();
        }
        return false;
    }

    private void catchException(Exception exc, int i) {
        exc.printStackTrace();
        eventDeviceDisconnected();
    }

    public void startListening() {
        new Thread(new Runnable() { // from class: com.shimmerresearch.androidradiodriver.ShimmerSerialPortAndroid.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        try {
                            int iAvailable = ShimmerSerialPortAndroid.this.mInStream.available();
                            if (iAvailable >= 1) {
                                ShimmerSerialPortAndroid.this.mShimmerSerialEventCallback.serialPortRxEvent(iAvailable);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                ShimmerSerialPortAndroid.this.mInStream.close();
                                return;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            ShimmerSerialPortAndroid.this.mInStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        throw th;
                    }
                }
            }
        }).start();
    }
}
