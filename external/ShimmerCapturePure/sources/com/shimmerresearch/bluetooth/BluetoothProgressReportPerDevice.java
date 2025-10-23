package com.shimmerresearch.bluetooth;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.ShimmerDevice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class BluetoothProgressReportPerDevice implements Serializable {
    public static final String[] mListOfOperationStates = {ShimmerDevice.STRING_CONSTANT_PENDING, "In Progress", "Success", "Warning", "Fail", "Cancelled"};
    private static final long serialVersionUID = -7997745169511235203L;
    public String mBluetoothAddress;
    public String mComPort;
    public int mCommandCompleted;
    public ShimmerBluetooth.BT_STATE mCurrentOperationBtState;
    public int mNumberofRemainingCMDsInBuffer;
    public OperationState mOperationState;
    public int mProgressCounter;
    public int mProgressEndValue;
    public int mProgressPercentageComplete;
    public float mProgressSpeed;
    public ShimmerBluetoothDetailsMini mShimmerBluetoothDetailsMini;

    public BluetoothProgressReportPerDevice(String str, ShimmerBluetooth.BT_STATE bt_state, int i) {
        this.mOperationState = OperationState.PENDING;
        this.mCurrentOperationBtState = ShimmerBluetooth.BT_STATE.DISCONNECTED;
        this.mShimmerBluetoothDetailsMini = new ShimmerBluetoothDetailsMini();
        this.mProgressCounter = 0;
        this.mProgressPercentageComplete = 0;
        this.mProgressSpeed = 0.0f;
        this.mComPort = str;
        this.mCurrentOperationBtState = bt_state;
        this.mProgressEndValue = i;
    }

    public BluetoothProgressReportPerDevice(ShimmerDevice shimmerDevice, ShimmerBluetooth.BT_STATE bt_state, int i) {
        this(shimmerDevice.getComPort(), bt_state, i);
        updateShimmerDeviceMini(shimmerDevice);
    }

    public void finishOperation() {
        this.mProgressCounter = this.mProgressEndValue;
        this.mProgressPercentageComplete = 100;
    }

    public void updateProgress(int i, int i2) {
        int i3 = this.mProgressEndValue;
        int i4 = (i3 - i) + 1;
        this.mProgressCounter = i4;
        this.mCommandCompleted = i2;
        if (i4 < 0) {
            this.mProgressCounter = 0;
        }
        if (this.mProgressCounter > i3) {
            this.mProgressCounter = i3;
        }
        if (i3 != 0) {
            this.mProgressPercentageComplete = (int) ((this.mProgressCounter / i3) * 100.0d);
        }
    }

    public void updateProgress(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        updateProgress(bluetoothProgressReportPerCmd.mNumberofRemainingCMDsInBuffer, bluetoothProgressReportPerCmd.mCommandCompleted);
    }

    public void updateShimmerDeviceMini(ShimmerDevice shimmerDevice) {
        if (shimmerDevice != null) {
            if (shimmerDevice instanceof ShimmerBluetooth) {
                ShimmerBluetooth shimmerBluetooth = (ShimmerBluetooth) shimmerDevice;
                this.mShimmerBluetoothDetailsMini.mUniqueID = shimmerBluetooth.getComPort();
                this.mShimmerBluetoothDetailsMini.mShimmerMacID = shimmerBluetooth.getBluetoothAddress();
                this.mShimmerBluetoothDetailsMini.mShimmerMacIDParsed = shimmerBluetooth.getMacIdFromBtParsed();
            } else {
                this.mShimmerBluetoothDetailsMini.mUniqueID = shimmerDevice.getComPort();
                this.mShimmerBluetoothDetailsMini.mShimmerMacID = shimmerDevice.getMacId();
                this.mShimmerBluetoothDetailsMini.mShimmerMacIDParsed = shimmerDevice.getMacIdParsed();
            }
            this.mShimmerBluetoothDetailsMini.mFirmwareVersionParsed = shimmerDevice.getFirmwareVersionParsed();
            this.mShimmerBluetoothDetailsMini.mShimmerUserAssignedName = shimmerDevice.getShimmerUserAssignedName();
        }
    }

    public BluetoothProgressReportPerDevice deepClone() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return (BluetoothProgressReportPerDevice) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public enum OperationState {
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

        public ShimmerBluetoothDetailsMini() {
        }
    }
}
