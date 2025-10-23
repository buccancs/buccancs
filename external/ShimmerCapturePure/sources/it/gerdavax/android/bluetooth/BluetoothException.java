package it.gerdavax.android.bluetooth;

/* loaded from: classes4.dex */
public class BluetoothException extends Exception {
    private static final long serialVersionUID = -2281359045593775915L;

    public BluetoothException(Throwable th) {
        super(th);
    }

    public BluetoothException(String str) {
        super(str);
    }

    public BluetoothException(String str, Throwable th) {
        super(str, th);
    }
}
