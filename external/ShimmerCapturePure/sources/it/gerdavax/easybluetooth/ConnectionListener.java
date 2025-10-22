package it.gerdavax.easybluetooth;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes4.dex */
public abstract class ConnectionListener {
    private static final int CONNECTION_ERROR = 2;
    private static final int CONNECTION_WAITING = 1;
    private Handler delegate = new Handler() { // from class: it.gerdavax.easybluetooth.ConnectionListener.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                ConnectionListener.this.connectionWaiting((BtSocket) message.obj);
            } else {
                if (i != 2) {
                    return;
                }
                ConnectionListener.this.connectionError();
            }
        }
    };

    public abstract void connectionError();

    public abstract void connectionWaiting(BtSocket btSocket);

    final void notifyConnectionError() {
        Handler handler = this.delegate;
        handler.sendMessage(handler.obtainMessage(2));
    }

    final void notifyConnectionWaiting(BtSocket btSocket) {
        Handler handler = this.delegate;
        handler.sendMessage(handler.obtainMessage(1, btSocket));
    }
}
