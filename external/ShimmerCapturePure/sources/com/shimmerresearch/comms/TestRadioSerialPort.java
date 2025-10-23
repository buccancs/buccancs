package com.shimmerresearch.comms;

import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.driverUtilities.ByteUtils;
import com.shimmerresearch.exceptions.ShimmerException;

import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class TestRadioSerialPort extends AbstractSerialPortHal implements SerialPortListener {
    boolean mFirstTime = true;
    int mCount = 0;
    byte header = -91;
    boolean mRadioSimulatorThread = true;
    int mPacketSize = 500;
    private transient SerialPortListener mShimmerSerialEventCallback;

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public int availableBytes() throws ShimmerException {
        return 0;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean bytesAvailableToBeRead() throws ShimmerException {
        return false;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void clearSerialPortRxBuffer() throws ShimmerException {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void closeSafely() throws ShimmerException {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void connect() throws ShimmerException {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void disconnect() throws ShimmerException {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isConnected() {
        return false;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isDisonnected() {
        return false;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public boolean isSerialPortReaderStarted() {
        return false;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void registerSerialPortRxEventCallback(SerialPortListener serialPortListener) {
        this.mShimmerSerialEventCallback = serialPortListener;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.SerialPortListener
    public void serialPortRxEvent(int i) {
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void setVerboseMode(boolean z, boolean z2) {
    }

    public void stopRadioSimulator() {
        this.mRadioSimulatorThread = false;
        this.mCount = 0;
        this.mFirstTime = true;
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public void txBytes(byte[] bArr) throws ShimmerException {
        if (bArr[0] == -92 && bArr[1] == 1) {
            this.mShimmerSerialEventCallback.serialPortRxEvent(1);
        }
        if (bArr[0] == -92 && bArr[1] == 0) {
            stopRadioSimulator();
        }
    }

    @Override // com.shimmerresearch.comms.serialPortInterface.InterfaceSerialPortHal
    public byte[] rxBytes(int i) throws ShimmerException {
        if (this.mFirstTime) {
            this.mFirstTime = false;
            startRadioSimulator();
            return new byte[]{0};
        }
        byte[] bArr = new byte[this.mPacketSize];
        for (int i2 = 0; i2 < this.mPacketSize; i2 += 5) {
            byte[] bArrIntToByteArray = ByteUtils.intToByteArray(this.mCount, ByteOrder.LITTLE_ENDIAN);
            byte[] bArr2 = new byte[5];
            bArr2[0] = this.header;
            System.arraycopy(bArrIntToByteArray, 0, bArr2, 1, bArrIntToByteArray.length);
            this.mCount++;
            System.arraycopy(bArr2, 0, bArr, i2, 5);
        }
        return bArr;
    }

    public void startRadioSimulator() {
        new Thread(new Runnable() { // from class: com.shimmerresearch.comms.TestRadioSerialPort.1
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                TestRadioSerialPort.this.mRadioSimulatorThread = true;
                while (TestRadioSerialPort.this.mRadioSimulatorThread) {
                    TestRadioSerialPort.this.mShimmerSerialEventCallback.serialPortRxEvent(TestRadioSerialPort.this.mPacketSize);
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
