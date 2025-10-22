package com.shimmerresearch.exception;

/* loaded from: classes2.dex */
public class DeviceNotPairedException extends RuntimeException {
    String btAddress;

    public DeviceNotPairedException(String str) {
        this.btAddress = str;
    }

    public DeviceNotPairedException(String str, String str2) {
        super(str2);
        this.btAddress = str;
    }

    public DeviceNotPairedException(String str, String str2, Throwable th) {
        super(str2, th);
        this.btAddress = str;
    }

    public String getBluetoothAddress() {
        return this.btAddress;
    }
}
