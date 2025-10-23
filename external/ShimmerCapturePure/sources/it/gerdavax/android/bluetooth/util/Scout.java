package it.gerdavax.android.bluetooth.util;

/* loaded from: classes4.dex */
public class Scout {
    private Scout() {
    }

    public static void exploreBluetoothDevice() {
    }

    public static void exploreClass(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName(str);
            System.out.println("className" + cls);
            ReflectionUtils.printMethods(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scoutRfcommSocket() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.bluetooth.RfcommSocket");
            System.out.println("android.bluetooth.RfcommSocket?" + cls);
            ReflectionUtils.printMethods(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scoutDatabase() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.bluetooth.Database");
            System.out.println("android.bluetooth.Database?" + cls);
            ReflectionUtils.printMethods(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
