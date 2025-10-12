package com.shimmerresearch.bluetooth;

public class BluetoothProgressReportPerCmd {

    public int mCommandCompleted;
    public int mNumberofRemainingCMDsInBuffer;
    public String mBluetoothAddress;
    public String mComPort;


    public BluetoothProgressReportPerCmd(int command, int numberofcmdsleft, String address, String comPort) {
        mCommandCompleted = command;
        mNumberofRemainingCMDsInBuffer = numberofcmdsleft;
        mBluetoothAddress = address;
        mComPort = comPort;
    }

}
