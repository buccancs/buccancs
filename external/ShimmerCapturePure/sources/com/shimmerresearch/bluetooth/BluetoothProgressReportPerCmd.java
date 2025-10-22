package com.shimmerresearch.bluetooth;

/* loaded from: classes2.dex */
public class BluetoothProgressReportPerCmd {
    public String mBluetoothAddress;
    public String mComPort;
    public int mCommandCompleted;
    public int mNumberofRemainingCMDsInBuffer;

    public BluetoothProgressReportPerCmd(int i, int i2, String str, String str2) {
        this.mCommandCompleted = i;
        this.mNumberofRemainingCMDsInBuffer = i2;
        this.mBluetoothAddress = str;
        this.mComPort = str2;
    }
}
