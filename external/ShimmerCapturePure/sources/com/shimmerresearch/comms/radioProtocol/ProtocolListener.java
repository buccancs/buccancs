package com.shimmerresearch.comms.radioProtocol;

import com.shimmerresearch.bluetooth.BluetoothProgressReportPerCmd;
import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.exceptions.ShimmerException;

/* loaded from: classes2.dex */
public interface ProtocolListener {
    @Deprecated
    void eventAckInstruction(byte[] bArr);

    void eventAckReceived(int i);

    @Deprecated
    void eventByteResponseWhileStreaming(byte[] bArr);

    void eventDockedStateChange();

    void eventKillConnectionRequest(ShimmerException shimmerException);

    void eventLogAndStreamStatusChangedCallback(int i);

    void eventNewPacket(byte[] bArr, long j);

    void eventNewResponse(byte[] bArr);

    void eventResponseReceived(int i, Object obj);

    void eventSetHaveAttemptedToRead(boolean z);

    void eventSetIsDocked(boolean z);

    void eventSetIsInitialised(boolean z);

    void eventSetIsSDLogging(boolean z);

    void eventSetIsSensing(boolean z);

    void eventSetIsStreaming(boolean z);

    void finishOperation(ShimmerBluetooth.BT_STATE bt_state);

    void hasStopStreaming();

    void initialiseStreamingCallback();

    void isNowStreaming();

    void sendProgressReport(BluetoothProgressReportPerCmd bluetoothProgressReportPerCmd);

    void sendStatusMSGtoUI(String str);

    void startOperation(ShimmerBluetooth.BT_STATE bt_state, int i);
}
