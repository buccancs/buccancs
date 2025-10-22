package com.shimmerresearch.comms.serialPortInterface;

import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public interface InterfaceSerialPortHal {
    void addByteLevelDataCommListener(ByteLevelDataCommListener byteLevelDataCommListener);

    int availableBytes() throws ShimmerException;

    boolean bytesAvailableToBeRead() throws ShimmerException;

    void clearByteLevelDataCommListener();

    void clearSerialPortRxBuffer() throws ShimmerException;

    void closeSafely() throws ShimmerException;

    void connect() throws ShimmerException;

    void disconnect() throws ShimmerException;

    void eventDeviceConnected();

    void eventDeviceDisconnected();

    boolean isConnected();

    boolean isDisonnected();

    boolean isSerialPortReaderStarted();

    void registerSerialPortRxEventCallback(SerialPortListener serialPortListener);

    byte[] rxBytes(int i) throws ShimmerException;

    void setTimeout(int i);

    void setVerboseMode(boolean z, boolean z2);

    void txBytes(byte[] bArr) throws ShimmerException;
}
