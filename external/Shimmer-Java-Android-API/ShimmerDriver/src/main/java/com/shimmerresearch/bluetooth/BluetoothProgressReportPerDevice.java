package com.shimmerresearch.bluetooth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.ShimmerDevice;

public class BluetoothProgressReportPerDevice implements Serializable {

    public static final String[] mListOfOperationStates = new String[]{
            "Pending",
            "In Progress",
            "Success",
            "Warning",
            "Fail",
            "Cancelled"
    };
    private static final long serialVersionUID = -7997745169511235203L;
    public int mCommandCompleted;
    public int mNumberofRemainingCMDsInBuffer;
    public String mBluetoothAddress;
    public String mComPort;
    public OperationState mOperationState = OperationState.PENDING;
    public BT_STATE mCurrentOperationBtState = BT_STATE.DISCONNECTED;
    public ShimmerBluetoothDetailsMini mShimmerBluetoothDetailsMini = new ShimmerBluetoothDetailsMini();
    public int mProgressCounter = 0;
    public int mProgressPercentageComplete = 0;
    public int mProgressEndValue = 100;
    public float mProgressSpeed = 0;

    public BluetoothProgressReportPerDevice(String comPort, BT_STATE currentOperationBtState, int endValue) {
        mComPort = comPort;
        mCurrentOperationBtState = currentOperationBtState;
        mProgressEndValue = endValue;
    }


    public BluetoothProgressReportPerDevice(ShimmerDevice shimmerDevice, BT_STATE currentOperationBtState, int endValue) {
        this(shimmerDevice.getComPort(), currentOperationBtState, endValue);
        updateShimmerDeviceMini(shimmerDevice);
    }

    public void updateProgress(BluetoothProgressReportPerCmd pRPC) {
        updateProgress(pRPC.mNumberofRemainingCMDsInBuffer, pRPC.mCommandCompleted);
    }

    public void updateProgress(int numberofRemainingCMDsInBuffer, int commandsCompleted) {
        mProgressCounter = mProgressEndValue - numberofRemainingCMDsInBuffer + 1;

        mCommandCompleted = commandsCompleted;

        if (mProgressCounter < 0)
            mProgressCounter = 0;
        if (mProgressCounter > mProgressEndValue)
            mProgressCounter = mProgressEndValue;

        if (mProgressEndValue != 0) {
            mProgressPercentageComplete = (int) (((double) mProgressCounter / (double) mProgressEndValue) * 100);
        }
    }

    public void updateShimmerDeviceMini(ShimmerDevice shimmerDevice) {
        if (shimmerDevice != null) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                mShimmerBluetoothDetailsMini.mUniqueID = shimmerBluetooth.getComPort();
                mShimmerBluetoothDetailsMini.mShimmerMacID = shimmerBluetooth.getBluetoothAddress();
                mShimmerBluetoothDetailsMini.mShimmerMacIDParsed = shimmerBluetooth.getMacIdFromBtParsed();
            } else {
                mShimmerBluetoothDetailsMini.mUniqueID = shimmerDevice.getComPort();
                mShimmerBluetoothDetailsMini.mShimmerMacID = shimmerDevice.getMacId();
                mShimmerBluetoothDetailsMini.mShimmerMacIDParsed = shimmerDevice.getMacIdParsed();
            }

            mShimmerBluetoothDetailsMini.mFirmwareVersionParsed = shimmerDevice.getFirmwareVersionParsed();
            mShimmerBluetoothDetailsMini.mShimmerUserAssignedName = shimmerDevice.getShimmerUserAssignedName();

        }

    }

    public void finishOperation() {
        mProgressCounter = mProgressEndValue;
        mProgressPercentageComplete = 100;
    }


    public BluetoothProgressReportPerDevice deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BluetoothProgressReportPerDevice) ois.readObject();
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static enum OperationState {
        PENDING,
        INPROGRESS,
        SUCCESS,
        FAIL_WITH_WARNING,
        FAIL_OUTRIGHT,
        CANCELLED
    }


    public class ShimmerBluetoothDetailsMini implements Serializable {
        private static final long serialVersionUID = 4289859702565448002L;

        public int mSlotNumber = -1;
        public String mShimmerMacID = "";
        public String mShimmerMacIDParsed = "";

        public String mDockID = "";
        public String mUniqueID = "";


        public long mShimmerLastReadRealTimeClockValue = 0;
        public String mShimmerLastReadRtcValueParsed = "";

        public String mFirmwareVersionParsed = "";

        public String mShimmerUserAssignedName = "";
    }


}
