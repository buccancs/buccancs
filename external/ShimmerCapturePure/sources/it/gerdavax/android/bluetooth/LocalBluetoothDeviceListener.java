package it.gerdavax.android.bluetooth;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public interface LocalBluetoothDeviceListener {
    void bluetoothDisabled();

    void bluetoothEnabled();

    void deviceFound(String str);

    void scanCompleted(ArrayList<String> arrayList);

    void scanStarted();
}
