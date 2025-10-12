package com.shimmerresearch.shimmer3.communication;

import com.shimmerresearch.comms.serialPortInterface.AbstractSerialPortHal;
import com.shimmerresearch.driverUtilities.UtilShimmer;
import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public class ByteCommunicationJSSC implements ByteCommunication {
    protected transient SerialPort mSerialPort = null;
    private String mAddress;
    private boolean mPrintValues = false;

    public ByteCommunicationJSSC(String address) {
        mAddress = address;
        mSerialPort = new SerialPort(mAddress);
    }

    public ByteCommunicationJSSC(SerialPort serialPort) {
        mSerialPort = serialPort;
        mAddress = mSerialPort.getPortName();
    }

    @Override
    public int getInputBufferBytesCount() throws SerialPortException {
        return mSerialPort.getInputBufferBytesCount();
    }

    @Override
    public boolean isOpened() {
        return mSerialPort.isOpened();
    }

    @Override
    public boolean closePort() throws SerialPortException {
        return mSerialPort.closePort();
    }

    @Override
    public boolean openPort() throws SerialPortException {
        mSerialPort.openPort();
        mSerialPort.setParams(115200, 8, 1, 0);
        return mSerialPort.isOpened();
    }

    @Override
    public byte[] readBytes(int byteCount, int timeout) throws SerialPortTimeoutException, SerialPortException {
        byte[] rxbytes = mSerialPort.readBytes(byteCount, timeout);
        if (mPrintValues)
            System.out.println("READ BYTES: " + UtilShimmer.bytesToHexString(rxbytes));
        return rxbytes;
    }

    @Override
    public boolean writeBytes(byte[] buffer) throws SerialPortException {
        if (mPrintValues)
            System.out.println("WRITE BYTES: " + UtilShimmer.bytesToHexString(buffer));
        return mSerialPort.writeBytes(buffer);
    }

    @Override
    public boolean setParams(int i, int j, int k, int l) throws SerialPortException {
        return false;
    }

    @Override
    public boolean purgePort(int i) throws SerialPortException {
        return mSerialPort.purgePort(i);
    }

    @Override
    public void setByteCommunicationListener(ByteCommunicationListener byteCommListener) {

    }

    @Override
    public void removeRadioListenerList() {

    }

    public void setSerialPort(SerialPort sp) {
        mSerialPort = sp;
    }
}
