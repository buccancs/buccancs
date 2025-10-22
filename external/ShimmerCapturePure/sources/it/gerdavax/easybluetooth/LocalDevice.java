package it.gerdavax.easybluetooth;

import android.content.Context;
import android.os.Build;
import it.gerdavax.util.Logger;

/* loaded from: classes4.dex */
public abstract class LocalDevice {
    protected static final Logger log = Logger.getLogger("EASYBT");
    private static final int SDK_NUM_2_0 = 5;
    private static LocalDevice instance;
    protected Context ctx;
    protected ScanListener scanListener = null;

    public static synchronized LocalDevice getInstance() {
        if (instance == null) {
            int versionNumber = getVersionNumber();
            log.i(LocalDevice.class, "Parsed version number is " + versionNumber);
            if (versionNumber < 5) {
                instance = new LocalDevice1Impl();
            } else {
                instance = new LocalDevice2Impl();
            }
        }
        log.i(LocalDevice.class, "Returning: " + instance);
        return instance;
    }

    private static int getVersionNumber() {
        return Integer.parseInt(Build.VERSION.SDK.trim());
    }

    public void destroy() {
        this.ctx = null;
    }

    public abstract void ensureDiscoverable();

    public abstract RemoteDevice getRemoteForAddr(String str);

    public void init(Context context, ReadyListener readyListener) {
        this.ctx = context;
    }

    public abstract ServerControl listenForConnection(ConnectionListener connectionListener, int i);

    public void scan(ScanListener scanListener) {
        this.scanListener = scanListener;
    }

    public abstract void stopScan();
}
