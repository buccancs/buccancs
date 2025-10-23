package com.shimmerresearch.driver;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.BluetoothProgressReportPerDevice;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.verisense.communication.SyncProgressDetails;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ShimmerDeviceCallbackAdapter implements Serializable {
    public static final boolean ONLY_UPDATE_RATE_IF_CHANGED = false;
    private static final long serialVersionUID = -3826489309767259792L;
    public double mLastSentPacketReceptionRateCurrent;
    public double mLastSentPacketReceptionRateOverall;
    private ShimmerDevice mShimmerDevice;
    private BluetoothProgressReportPerDevice progressReportPerDevice;
    private boolean useUniqueIdForFeedback;

    public ShimmerDeviceCallbackAdapter(ShimmerDevice shimmerDevice) {
        this.mLastSentPacketReceptionRateOverall = 0.0d;
        this.mLastSentPacketReceptionRateCurrent = 0.0d;
        this.useUniqueIdForFeedback = false;
        this.mShimmerDevice = shimmerDevice;
    }

    public ShimmerDeviceCallbackAdapter(ShimmerDevice shimmerDevice, boolean z) {
        this(shimmerDevice);
        this.useUniqueIdForFeedback = z;
    }

    public void startStreaming() {
        this.mLastSentPacketReceptionRateOverall = 0.0d;
        this.mLastSentPacketReceptionRateCurrent = 0.0d;
    }

    public void setBluetoothRadioState(ShimmerBluetooth.BT_STATE bt_state, boolean z) {
        if (bt_state == ShimmerBluetooth.BT_STATE.CONNECTED || bt_state == ShimmerBluetooth.BT_STATE.STREAMING) {
            this.mShimmerDevice.setIsConnected(true);
            this.mShimmerDevice.setIsInitialised(true);
            if (bt_state == ShimmerBluetooth.BT_STATE.STREAMING) {
                this.mShimmerDevice.setIsStreaming(true);
            } else {
                this.mShimmerDevice.setIsStreaming(false);
            }
        } else if (bt_state == ShimmerBluetooth.BT_STATE.DISCONNECTED || bt_state == ShimmerBluetooth.BT_STATE.CONNECTION_LOST || bt_state == ShimmerBluetooth.BT_STATE.CONNECTION_FAILED) {
            this.mShimmerDevice.setIsConnected(false);
            this.mShimmerDevice.setIsInitialised(false);
            this.mShimmerDevice.setIsStreaming(false);
        }
        this.mShimmerDevice.consolePrintLn("State change: " + bt_state.toString());
        if (z) {
            this.mShimmerDevice.sendCallBackMsg(0, new CallbackObject(3, bt_state, getMacId(), getComPort()));
        }
    }

    public void isReadyForStreaming() {
        this.mShimmerDevice.setIsInitialised(true);
        ShimmerBluetooth.BT_STATE bluetoothRadioState = this.mShimmerDevice.getBluetoothRadioState();
        this.mShimmerDevice.sendCallBackMsg(1, new CallbackObject(2, getMacId(), getComPort()));
        if (bluetoothRadioState == ShimmerBluetooth.BT_STATE.CONNECTING) {
            this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        }
        if (this.mShimmerDevice.isAutoStartStreaming()) {
            try {
                this.mShimmerDevice.startStreaming();
            } catch (ShimmerException e) {
                e.printStackTrace();
            }
        }
    }

    public void newSyncPayloadReceived(int i, boolean z, double d, String str) {
        this.mShimmerDevice.sendCallBackMsg(10, new CallbackObject(getMacId(), getComPort(), new SyncProgressDetails(i, z, d, str)));
    }

    public void readLoggedDataCompleted(String str) {
        this.mShimmerDevice.sendCallBackMsg(16, new CallbackObject(getMacId(), getComPort(), new SyncProgressDetails(str)));
    }

    public void eraseDataCompleted() {
        this.mShimmerDevice.sendCallBackMsg(14, new CallbackObject(getMacId(), getComPort(), (Object) true));
    }

    public void writeOpConfigCompleted() {
        this.mShimmerDevice.sendCallBackMsg(15, new CallbackObject(getMacId(), getComPort(), (Object) true));
    }

    public void hasStopStreaming() {
        this.mShimmerDevice.sendCallBackMsg(1, new CallbackObject(0, getMacId(), getComPort()));
        if (this.mShimmerDevice.isSDLogging()) {
            this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.SDLOGGING);
        } else {
            this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTED);
        }
    }

    public void isNowStreaming() {
        this.mShimmerDevice.sendCallBackMsg(1, new CallbackObject(1, getMacId(), getComPort()));
        if (this.mShimmerDevice.isSDLogging()) {
            this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING_AND_SDLOGGING);
        } else {
            this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.STREAMING);
        }
    }

    public void dockedStateChange() {
        this.mShimmerDevice.sendCallBackMsg(7, new CallbackObject(7, getMacId(), getComPort()));
    }

    public void inquiryDone() {
        this.mShimmerDevice.sendCallBackMsg(0, new CallbackObject(3, this.mShimmerDevice.getBluetoothRadioState(), getMacId(), getComPort()));
    }

    public void batteryStatusChanged() {
        this.mShimmerDevice.sendCallBackMsg(0, new CallbackObject(3, this.mShimmerDevice.getBluetoothRadioState(), getMacId(), getComPort()));
    }

    public void dataHandler(ObjectCluster objectCluster) {
        sendCallbackPacketReceptionRateOverall();
        this.mShimmerDevice.sendCallBackMsg(2, objectCluster);
    }

    public void sendCallbackPacketReceptionRateOverall() {
        double packetReceptionRateOverall = this.mShimmerDevice.getPacketReceptionRateOverall();
        sendCallBackMsgWithSameId(new CallbackObject(3, getMacId(), getComPort(), packetReceptionRateOverall));
        this.mLastSentPacketReceptionRateOverall = packetReceptionRateOverall;
    }

    public void sendCallbackPacketReceptionRateCurrent() {
        double packetReceptionRateCurrent = this.mShimmerDevice.getPacketReceptionRateCurrent();
        sendCallBackMsgWithSameId(new CallbackObject(6, getMacId(), getComPort(), packetReceptionRateCurrent));
        this.mLastSentPacketReceptionRateCurrent = packetReceptionRateCurrent;
    }

    public void sendCallBackMsgWithSameId(CallbackObject callbackObject) {
        this.mShimmerDevice.sendCallBackMsg(callbackObject.mIndicator, callbackObject);
    }

    public void sendCallBackDeviceException(ShimmerException shimmerException) {
        this.mShimmerDevice.sendCallBackMsg(10, shimmerException);
    }

    public void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd) {
        BluetoothProgressReportPerDevice bluetoothProgressReportPerDevice = this.progressReportPerDevice;
        if (bluetoothProgressReportPerDevice != null) {
            bluetoothProgressReportPerDevice.updateProgress(bluetoothProgressReportPerCmd);
            sendNewCallbackObjectProgressPerDevice(this.progressReportPerDevice);
            ShimmerDevice shimmerDevice = this.mShimmerDevice;
            shimmerDevice.consolePrintLn("MAC:\t" + shimmerDevice.getMacId() + "\tCOM:\t" + this.mShimmerDevice.getComPort() + "\tProgressCounter" + this.progressReportPerDevice.mProgressCounter + "\tProgressEndValue " + this.progressReportPerDevice.mProgressEndValue);
            if ((this.mShimmerDevice instanceof ShimmerBluetooth) && this.progressReportPerDevice.mProgressCounter == this.progressReportPerDevice.mProgressEndValue) {
                finishOperation(this.progressReportPerDevice.mCurrentOperationBtState);
            }
        }
    }

    public void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i) {
        this.mShimmerDevice.consolePrintLn(bt_state + " START");
        BluetoothProgressReportPerDevice bluetoothProgressReportPerDevice = new BluetoothProgressReportPerDevice(this.mShimmerDevice, bt_state, i);
        this.progressReportPerDevice = bluetoothProgressReportPerDevice;
        bluetoothProgressReportPerDevice.mOperationState = BluetoothProgressReportPerDevice.OperationState.INPROGRESS;
        sendNewCallbackObjectProgressPerDevice(this.progressReportPerDevice);
    }

    public void finishOperation(ShimmerBluetooth.BT_STATE bt_state) {
        BluetoothProgressReportPerDevice bluetoothProgressReportPerDevice = this.progressReportPerDevice;
        if (bluetoothProgressReportPerDevice != null) {
            this.mShimmerDevice.consolePrintLn("CURRENT OPERATION " + bluetoothProgressReportPerDevice.mCurrentOperationBtState + "\tFINISHED:" + bt_state);
            if (this.progressReportPerDevice.mCurrentOperationBtState == bt_state) {
                this.progressReportPerDevice.finishOperation();
                this.progressReportPerDevice.mOperationState = BluetoothProgressReportPerDevice.OperationState.SUCCESS;
                ShimmerDevice shimmerDevice = this.mShimmerDevice;
                if (shimmerDevice instanceof ShimmerBluetooth) {
                    ((ShimmerBluetooth) shimmerDevice).operationFinished();
                }
                sendNewCallbackObjectProgressPerDevice(this.progressReportPerDevice);
                this.progressReportPerDevice = null;
                return;
            }
            return;
        }
        this.mShimmerDevice.consolePrintLn("CURRENT OPERATION - UNKNOWN, null progressReportPerDevice\tFINISHED:" + bt_state);
    }

    private void sendNewCallbackObjectProgressPerDevice(BluetoothProgressReportPerDevice bluetoothProgressReportPerDevice) {
        this.mShimmerDevice.sendCallBackMsg(4, new CallbackObject(this.mShimmerDevice.getBluetoothRadioState(), getMacId(), getComPort(), bluetoothProgressReportPerDevice));
    }

    private String getMacId() {
        if (this.useUniqueIdForFeedback) {
            return this.mShimmerDevice.getUniqueId();
        }
        return this.mShimmerDevice.getMacId();
    }

    private String getComPort() {
        if (this.useUniqueIdForFeedback) {
            return this.mShimmerDevice.getUniqueId();
        }
        return this.mShimmerDevice.getComPort();
    }
}
