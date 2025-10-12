package com.shimmerresearch.comms;

import java.nio.ByteOrder;

import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.comms.serialPortInterface.SerialPortListener;
import com.shimmerresearch.driverUtilities.ByteUtils;
import com.shimmerresearch.exceptions.ShimmerException;

public class TestRadioSerialPort extends AbstractSerialPortHal implements SerialPortListener {
    boolean mFirstTime = true;
    int mCount = 0;
    byte header = (byte) 0xA5;
    boolean mRadioSimulatorThread = true;
    int mPacketSize = 5 * 100;
    private transient SerialPortListener mShimmerSerialEventCallback;

    @Override
    public void connect() throws ShimmerException {

    }

    @Override
    public void disconnect() throws ShimmerException {

    }

    @Override
    public void closeSafely() throws ShimmerException {

    }

    @Override
    public void clearSerialPortRxBuffer() throws ShimmerException {

    }

    @Override
    public void txBytes(byte[] bytes) throws ShimmerException {
        if (bytes[0] == (byte) 0xA4 && bytes[1] == (byte) 1) {
            mShimmerSerialEventCallback.serialPortRxEvent(1);

        }
        if (bytes[0] == (byte) 0xA4 && bytes[1] == (byte) 0) {
            stopRadioSimulator();
        }

    }

    @Override
    public byte[] rxBytes(int numBytes) throws ShimmerException {
        if (mFirstTime) {
            mFirstTime = false;
            startRadioSimulator();
            return new byte[]{0};
        }
        byte[] finalPacket = new byte[mPacketSize];
        for (int i = 0; i < mPacketSize; i = i + 5) {
            byte[] data = ByteUtils.intToByteArray(mCount, ByteOrder.LITTLE_ENDIAN);
            byte[] packet = new byte[5];
            packet[0] = header;
            System.arraycopy(data, 0, packet, 1, data.length);
            mCount++;
            System.arraycopy(packet, 0, finalPacket, i, packet.length);
        }
        return finalPacket;
    }

    @Override
    public boolean isSerialPortReaderStarted() {
        return false;
    }

    @Override
    public void setVerboseMode(boolean verboseMode, boolean isDebugMode) {

    }

    @Override
    public boolean bytesAvailableToBeRead() throws ShimmerException {
        return false;
    }

    @Override
    public int availableBytes() throws ShimmerException {
        return 0;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isDisonnected() {
        return false;
    }

    @Override
    public void registerSerialPortRxEventCallback(SerialPortListener shimmerSerialEventCallback) {
        mShimmerSerialEventCallback = shimmerSerialEventCallback;
    }

    @Override
    public void serialPortRxEvent(int byteLength) {

    }

    public void startRadioSimulator() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mRadioSimulatorThread = true;
                while (mRadioSimulatorThread) {

                    mShimmerSerialEventCallback.serialPortRxEvent(mPacketSize);

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        thread.start();
    }

    public void stopRadioSimulator() {
        mRadioSimulatorThread = false;
        mCount = 0;
        mFirstTime = true;
    }
}
