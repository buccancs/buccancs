package com.shimmerresearch.bluetooth;

/* loaded from: classes2.dex */
public class RawBytePacketWithPCTimeStamp {
    public byte[] mDataArray;
    public long mSystemTimeStamp;

    public RawBytePacketWithPCTimeStamp(byte[] bArr, long j) {
        this.mDataArray = bArr;
        this.mSystemTimeStamp = j;
    }
}
