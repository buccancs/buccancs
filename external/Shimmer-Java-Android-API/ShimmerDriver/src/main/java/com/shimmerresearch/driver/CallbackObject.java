package com.shimmerresearch.driver;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerDevice;
import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;

public class CallbackObject {

    public int mIndicator;
    public BT_STATE mState = BT_STATE.DISCONNECTED;
    public String mBluetoothAddress;
    public String mComPort;
    public double mPacketReceptionRate;
    public BluetoothProgressReportPerDevice mProgressReportPerDevice;
    public Object mMyObject;


    public CallbackObject(int ind, String myBlueAdd, String comPort) {
        mIndicator = ind;
        mBluetoothAddress = myBlueAdd;
        mComPort = comPort;
    }


    public CallbackObject(int ind, BT_STATE state, String myBlueAdd, String comPort) {
        mIndicator = ind;
        mState = state;
        mBluetoothAddress = myBlueAdd;
        mComPort = comPort;
    }

    public CallbackObject(int ind, String myBlueAdd, String comPort, double packetReceptionRate) {
        mIndicator = ind;
        mBluetoothAddress = myBlueAdd;
        mComPort = comPort;
        this.mPacketReceptionRate = packetReceptionRate;
    }

    public CallbackObject(BT_STATE state, String myBlueAdd, String comPort, BluetoothProgressReportPerDevice progressReportPerDevice) {
        mState = state;
        mBluetoothAddress = myBlueAdd;
        mComPort = comPort;
        mProgressReportPerDevice = progressReportPerDevice;
    }

    public CallbackObject(String myBlueAdd, Object myObject) {
        mBluetoothAddress = myBlueAdd;
        mMyObject = myObject;
    }

    public CallbackObject(String myBlueAdd, String comPort, Object myObject) {
        mBluetoothAddress = myBlueAdd;
        mComPort = comPort;
        mMyObject = myObject;
    }

}
