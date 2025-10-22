package it.gerdavax.android.bluetooth.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class ReflectionUtils {
    private ReflectionUtils() {
    }

    public static int readStaticConstantValue(Class cls, String str) throws Exception {
        return cls.getField(str).getInt(null);
    }

    public static void printMethods(String str) {
        try {
            printMethods(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMethods(Class cls) {
        System.out.println("\n*** METHODS OF " + cls.toString());
        try {
            for (Constructor<?> constructor : cls.getConstructors()) {
                System.out.println(constructor.toString());
            }
            for (Method method : cls.getMethods()) {
                System.out.println(method.toString());
            }
        } catch (Throwable unused) {
            System.out.println("\n*** Can't print METHODS OF " + cls.toString());
        }
    }

    public static void printFields(Class cls) throws SecurityException {
        System.out.println("\n*** FIELDS OF " + cls.toString());
        for (Field field : cls.getFields()) {
            System.out.println(field.toString());
        }
    }

    public static void readField(Class cls, String str) throws NoSuchFieldException {
        try {
            Field field = cls.getField("SCAN_MODE_CONNECTABLE");
            System.out.println("SCAN_MODE_CONNECTABLE VALUE: " + field.getInt(null));
            Field field2 = cls.getField("SCAN_MODE_CONNECTABLE_DISCOVERABLE");
            System.out.println("SCAN_MODE_CONNECTABLE_DISCOVERABLE VALUE: " + field2.getInt(null));
            Field field3 = cls.getField("SCAN_MODE_NONE");
            System.out.println("SCAN_MODE_NONE VALUE: " + field3.getInt(null));
        } catch (Exception unused) {
        }
    }
}
