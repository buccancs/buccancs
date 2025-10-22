package it.gerdavax.android.bluetooth;

/* loaded from: classes4.dex */
public interface RemoteBluetoothDeviceListener {
    void gotServiceChannel(int i, int i2);

    void paired();

    void pinRequested();

    void serviceChannelNotAvailable(int i);
}
