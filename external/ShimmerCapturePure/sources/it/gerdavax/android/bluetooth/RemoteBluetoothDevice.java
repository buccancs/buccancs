package it.gerdavax.android.bluetooth;

/* loaded from: classes4.dex */
public interface RemoteBluetoothDevice extends BluetoothDevice {
    @Override
        // it.gerdavax.android.bluetooth.BluetoothDevice
    String getAddress();

    int getDeviceClass();

    int getDeviceMajorClass();

    int getDeviceMinorClass();

    short getRSSI();

    void getRemoteServiceChannel(int i) throws Exception;

    int getServiceMajorClass();

    boolean isPaired();

    BluetoothSocket openSocket(int i) throws BluetoothException;

    void pair();

    void pair(String str);

    void setListener(RemoteBluetoothDeviceListener remoteBluetoothDeviceListener);

    void setPin(String str) throws BluetoothException;

    void unpair();
}
