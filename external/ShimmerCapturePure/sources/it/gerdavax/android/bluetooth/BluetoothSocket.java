package it.gerdavax.android.bluetooth;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public interface BluetoothSocket {
    BluetoothSocket accept(int i) throws Exception;

    void closeSocket();

    InputStream getInputStream() throws Exception;

    OutputStream getOutputStream() throws Exception;

    int getPort();

    RemoteBluetoothDevice getRemoteBluetoothDevice();
}
