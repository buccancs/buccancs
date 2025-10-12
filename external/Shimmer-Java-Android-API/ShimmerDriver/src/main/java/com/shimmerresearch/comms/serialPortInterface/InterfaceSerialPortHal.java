package com.shimmerresearch.comms.serialPortInterface;

import com.shimmerresearch.exceptions.ShimmerException;

public interface InterfaceSerialPortHal {


    public void connect() throws ShimmerException;

    public void disconnect() throws ShimmerException;

    public void closeSafely() throws ShimmerException;

    public void clearSerialPortRxBuffer() throws ShimmerException;

    public void txBytes(byte[] buf) throws ShimmerException;

    public byte[] rxBytes(int numBytes) throws ShimmerException;

    public boolean isSerialPortReaderStarted();

    public void setVerboseMode(boolean verboseMode, boolean isDebugMode);

    public boolean bytesAvailableToBeRead() throws ShimmerException;

    public int availableBytes() throws ShimmerException;

    public boolean isConnected();

    public boolean isDisonnected();

    public void eventDeviceConnected();

    public void eventDeviceDisconnected();

    public void registerSerialPortRxEventCallback(SerialPortListener shimmerSerialEventCallback);

    void addByteLevelDataCommListener(ByteLevelDataCommListener spl);

    public void clearByteLevelDataCommListener();

    public void setTimeout(int timeout);

}
