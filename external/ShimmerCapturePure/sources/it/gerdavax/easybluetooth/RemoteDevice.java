package it.gerdavax.easybluetooth;

import java.util.UUID;

/* loaded from: classes4.dex */
public abstract class RemoteDevice {
    public abstract boolean ensurePaired(String str);

    public abstract String getAddress();

    public abstract String getFriendlyName();

    public abstract int getRSSI();

    public abstract BtSocket openSocket(int i) throws Exception;

    public abstract BtSocket openSocket(UUID uuid) throws Exception;

    public final boolean ensurePaired() {
        return ensurePaired(null);
    }

    public String toString() {
        return String.valueOf(getFriendlyName()) + "@" + getAddress();
    }
}
