package com.shimmerresearch.shimmer3.communication;

import com.shimmerresearch.verisense.communication.ByteCommunicationListener;

import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

public interface ByteCommunication {

    int getInputBufferBytesCount() throws SerialPortException;

    boolean isOpened();

    boolean closePort() throws SerialPortException;

    boolean openPort() throws SerialPortException;

    byte[] readBytes(int byteCount, int timeout) throws SerialPortTimeoutException, SerialPortException;

    boolean writeBytes(byte[] buffer) throws SerialPortException;

    boolean setParams(int i, int j, int k, int l) throws SerialPortException;

    boolean purgePort(int i) throws SerialPortException;

    public void setByteCommunicationListener(ByteCommunicationListener byteCommListener);

    public void removeRadioListenerList();

}