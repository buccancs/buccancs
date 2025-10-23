package it.gerdavax.easybluetooth;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes4.dex */
public abstract class ScanListener {
    private static final int DEVICE_FOUND = 1;
    private static final int SCAN_COMPLETED = 2;
    private Handler delegate = new Handler() { // from class: it.gerdavax.easybluetooth.ScanListener.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                ScanListener.this.deviceFound((RemoteDevice) message.obj);
            } else {
                if (i != 2) {
                    return;
                }
                ScanListener.this.scanCompleted();
            }
        }
    };

    public abstract void deviceFound(RemoteDevice remoteDevice);

    public abstract void scanCompleted();

    final void notifyScanCompleted() {
        Handler handler = this.delegate;
        handler.sendMessage(handler.obtainMessage(2));
    }

    final void notifyDeviceFound(RemoteDevice remoteDevice) {
        Handler handler = this.delegate;
        handler.sendMessage(handler.obtainMessage(1, remoteDevice));
    }
}
