package it.gerdavax.easybluetooth;

import it.gerdavax.android.bluetooth.BluetoothSocket;
import it.gerdavax.util.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
class BtSocket1Impl implements BtSocket {
    private final Logger log;
    private BluetoothSocket socket;

    public BtSocket1Impl(BluetoothSocket bluetoothSocket) {
        this.socket = null;
        Logger logger = Logger.getLogger("EASYBT");
        this.log = logger;
        this.socket = bluetoothSocket;
        logger.v(this, "creating " + this);
    }

    @Override // it.gerdavax.easybluetooth.BtSocket
    public void close() throws IOException {
        this.log.v(this, "about to close " + this);
        this.socket.closeSocket();
    }

    @Override // it.gerdavax.easybluetooth.BtSocket
    public InputStream getInputStream() throws Exception {
        this.log.v(this, "getInputStream " + this);
        return this.socket.getInputStream();
    }

    @Override // it.gerdavax.easybluetooth.BtSocket
    public OutputStream getOutputStream() throws Exception {
        this.log.v(this, "getOutputStream " + this);
        return this.socket.getOutputStream();
    }
}
