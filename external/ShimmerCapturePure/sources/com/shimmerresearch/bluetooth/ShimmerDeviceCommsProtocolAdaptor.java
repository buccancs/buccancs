package com.shimmerresearch.bluetooth;

import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.comms.radioProtocol.CommsProtocolRadio;
import com.shimmerresearch.comms.radioProtocol.LiteProtocol;
import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public class ShimmerDeviceCommsProtocolAdaptor {
    private ShimmerDevice mShimmerDevice;

    public ShimmerDeviceCommsProtocolAdaptor(ShimmerDevice shimmerDevice) {
        this.mShimmerDevice = shimmerDevice;
    }

    public void connect() throws ShimmerException {
        this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTING);
        if (this.mShimmerDevice.mCommsProtocolRadio != null) {
            try {
                this.mShimmerDevice.mCommsProtocolRadio.connect();
            } catch (ShimmerException e) {
                this.mShimmerDevice.consolePrintLn("Failed to Connect");
                this.mShimmerDevice.consolePrintLn(e.getErrStringFormatted());
                this.mShimmerDevice.disconnect();
                this.mShimmerDevice.setBluetoothRadioState(ShimmerBluetooth.BT_STATE.CONNECTION_FAILED);
                throw e;
            }
        }
    }

    public void setIsInitialised(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mIsInitialised = z;
    }

    public void setIsSensing(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mIsSensing = z;
    }

    public void setIsStreaming(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mIsStreaming = z;
    }

    public void setHaveAttemptedToReadConfig(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mHaveAttemptedToReadConfig = z;
    }

    public void setIsSDLogging(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mIsSDLogging = z;
    }

    public void setIsDocked(boolean z) {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        if (commsProtocolRadio == null || commsProtocolRadio.mRadioProtocol == null || !(commsProtocolRadio.mRadioProtocol instanceof LiteProtocol)) {
            return;
        }
        ((LiteProtocol) commsProtocolRadio.mRadioProtocol).mIsDocked = z;
    }

    public boolean isConnected() {
        CommsProtocolRadio commsProtocolRadio = this.mShimmerDevice.mCommsProtocolRadio;
        return commsProtocolRadio != null && commsProtocolRadio.isConnected();
    }
}
