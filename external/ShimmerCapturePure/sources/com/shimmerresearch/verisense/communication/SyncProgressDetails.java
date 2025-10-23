package com.shimmerresearch.verisense.communication;

/* loaded from: classes2.dex */
public class SyncProgressDetails {
    public String mBinFilePath;
    public boolean mCRCError;
    public int mPayloadIndex;
    public double mTransferRateBytes;

    public SyncProgressDetails(int i, boolean z, double d, String str) {
        this.mPayloadIndex = i;
        this.mCRCError = z;
        this.mTransferRateBytes = d;
        this.mBinFilePath = str;
    }

    public SyncProgressDetails(String str) {
        this.mBinFilePath = str;
    }
}
