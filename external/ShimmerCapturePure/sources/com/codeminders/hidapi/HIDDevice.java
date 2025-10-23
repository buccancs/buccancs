package com.codeminders.hidapi;

import java.io.IOException;

/* loaded from: classes.dex */
public class HIDDevice {
    protected long peer;

    protected HIDDevice(long j) {
        this.peer = j;
    }

    public native void close() throws IOException;

    public native void disableBlocking() throws IOException;

    public native void enableBlocking() throws IOException;

    public native int getFeatureReport(byte[] bArr) throws IOException;

    public native String getIndexedString(int i) throws IOException;

    public native String getManufacturerString() throws IOException;

    public native String getProductString() throws IOException;

    public native String getSerialNumberString() throws IOException;

    public int hashCode() {
        long j = this.peer;
        return (int) (j ^ (j >>> 32));
    }

    public native int read(byte[] bArr) throws IOException;

    public native int readTimeout(byte[] bArr, int i);

    public native int sendFeatureReport(byte[] bArr) throws IOException;

    public native int write(byte[] bArr) throws IOException;

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof HIDDevice) && ((HIDDevice) obj).peer == this.peer;
    }
}
