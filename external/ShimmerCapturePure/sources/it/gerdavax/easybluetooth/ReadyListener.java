package it.gerdavax.easybluetooth;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes4.dex */
public abstract class ReadyListener {
    private static final int READY = 1;
    private Handler delegate = new Handler() { // from class: it.gerdavax.easybluetooth.ReadyListener.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                ReadyListener.this.ready();
            }
        }
    };

    public abstract void ready();

    final void notifyReady() {
        Handler handler = this.delegate;
        handler.sendMessage(handler.obtainMessage(1));
    }
}
