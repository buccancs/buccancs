package com.shimmerresearch.androidradiodriver;

import java.util.UUID;

/* loaded from: classes2.dex */
public class Shimmer3BleAndroidRadioByteCommunication extends VerisenseBleAndroidRadioByteCommunication {
    public Shimmer3BleAndroidRadioByteCommunication(String str) {
        super(str);
        this.TxID = "49535343-8841-43f4-a8d4-ecbe34729bb3";
        this.RxID = "49535343-1e4d-4bd9-ba61-23c647249616";
        this.ServiceID = "49535343-fe7d-4ae5-8fa9-9fafd205e455";
        this.sid = UUID.fromString(this.ServiceID);
        this.txid = UUID.fromString(this.TxID);
        this.rxid = UUID.fromString(this.RxID);
    }
}
