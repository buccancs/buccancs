package com.shimmerresearch.comms.serialPortInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSerialPortHal implements InterfaceSerialPortHal {

    public static int SERIAL_PORT_TIMEOUT_500 = 500; // was 2000
    public static int SERIAL_PORT_TIMEOUT_2000 = 2000; // was 2000
    public int mSerialPortTimeout = SERIAL_PORT_TIMEOUT_500;
    private String mConnectionHandle = "";

    transient private List<ByteLevelDataCommListener> mByteLevelDataCommListener = new ArrayList<ByteLevelDataCommListener>();

    public void clearByteLevelDataCommListener() {
        mByteLevelDataCommListener.clear();
    }

    public String getConnectionHandle() {
        return mConnectionHandle;
    }

    public void setConnectionHandle(String connectionHandle) {
        mConnectionHandle = connectionHandle;
    }

    @Override
    public void eventDeviceConnected() {
        if (mByteLevelDataCommListener.size() != 0) {
            for (ByteLevelDataCommListener commListerner : mByteLevelDataCommListener) {
                commListerner.eventConnected();
            }
        }
    }

    @Override
    public void eventDeviceDisconnected() {
        if (mByteLevelDataCommListener.size() != 0) {
            for (ByteLevelDataCommListener commListerner : mByteLevelDataCommListener) {
                commListerner.eventDisconnected();
            }
        }
    }

    @Override
    public void addByteLevelDataCommListener(ByteLevelDataCommListener spl) {
        mByteLevelDataCommListener.add(spl);
    }

    @Override
    public void setTimeout(int timeout) {
        mSerialPortTimeout = timeout;
    }

    public final static class SHIMMER_UART_BAUD_RATES {
        public final static int SHIMMER3_DOCKED = 115200;
        public final static int SPAN = 230400;
    }
}