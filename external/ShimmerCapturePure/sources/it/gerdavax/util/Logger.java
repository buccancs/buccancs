package it.gerdavax.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class Logger {
    private static int LOGLEVEL = 2;
    private static Map<String, Logger> loggers = new HashMap();
    private String TAG;

    private Logger(String str) {
        this.TAG = str;
    }

    private static boolean isLoggable(int i) {
        return LOGLEVEL <= i;
    }

    public static void setLogLevel(int i) {
        if (2 > i || i > 7) {
            return;
        }
        LOGLEVEL = i;
    }

    public static synchronized Logger getLogger(Object obj) {
        Logger logger;
        String string = obj.toString();
        logger = loggers.get(string);
        if (logger == null) {
            logger = new Logger(string);
            loggers.put(string, logger);
        }
        return logger;
    }

    public static synchronized Logger getLogger() {
        return getLogger("LOG");
    }

    private static String getSourceString(Object obj) {
        if (obj instanceof Class) {
            return String.valueOf(((Class) obj).getName()) + " - ";
        }
        return String.valueOf(obj.getClass().getName()) + " - ";
    }

    public void v(String str) {
        if (isLoggable(2)) {
            Log.v(this.TAG, str);
        }
    }

    public void v(String str, Throwable th) {
        if (isLoggable(2)) {
            Log.v(this.TAG, str, th);
        }
    }

    public void v(Object obj, String str) {
        if (isLoggable(2)) {
            Log.v(this.TAG, String.valueOf(getSourceString(obj)) + str);
        }
    }

    public void v(Object obj, String str, Throwable th) {
        if (isLoggable(2)) {
            Log.v(this.TAG, String.valueOf(getSourceString(obj)) + str, th);
        }
    }

    public void d(String str) {
        if (isLoggable(3)) {
            Log.d(this.TAG, str);
        }
    }

    public void d(String str, Throwable th) {
        if (isLoggable(3)) {
            Log.d(this.TAG, str, th);
        }
    }

    public void d(Object obj, String str) {
        if (isLoggable(3)) {
            Log.d(this.TAG, String.valueOf(getSourceString(obj)) + str);
        }
    }

    public void d(Object obj, String str, Throwable th) {
        if (isLoggable(3)) {
            Log.d(this.TAG, String.valueOf(getSourceString(obj)) + str, th);
        }
    }

    public void i(String str) {
        if (isLoggable(4)) {
            Log.i(this.TAG, str);
        }
    }

    public void i(String str, Throwable th) {
        if (isLoggable(4)) {
            Log.i(this.TAG, str, th);
        }
    }

    public void i(Object obj, String str) {
        if (isLoggable(4)) {
            Log.i(this.TAG, String.valueOf(getSourceString(obj)) + str);
        }
    }

    public void i(Object obj, String str, Throwable th) {
        if (isLoggable(4)) {
            Log.i(this.TAG, String.valueOf(getSourceString(obj)) + str, th);
        }
    }

    public void w(String str) {
        if (isLoggable(5)) {
            Log.w(this.TAG, str);
        }
    }

    public void w(String str, Throwable th) {
        if (isLoggable(5)) {
            Log.w(this.TAG, str, th);
        }
    }

    public void w(Object obj, String str) {
        if (isLoggable(5)) {
            Log.w(this.TAG, String.valueOf(getSourceString(obj)) + str);
        }
    }

    public void w(Object obj, String str, Throwable th) {
        if (isLoggable(5)) {
            Log.w(this.TAG, String.valueOf(getSourceString(obj)) + str, th);
        }
    }

    public void w(Throwable th) {
        if (isLoggable(5)) {
            Log.w(this.TAG, th);
        }
    }

    public void w(Object obj, Throwable th) {
        if (isLoggable(5)) {
            Log.w(this.TAG, getSourceString(obj), th);
        }
    }

    public void e(String str) {
        if (isLoggable(6)) {
            Log.e(this.TAG, str);
        }
    }

    public void e(String str, Throwable th) {
        if (isLoggable(6)) {
            Log.e(this.TAG, str, th);
        }
    }

    public void e(Object obj, String str) {
        if (isLoggable(6)) {
            Log.e(this.TAG, String.valueOf(getSourceString(obj)) + str);
        }
    }

    public void e(Object obj, String str, Throwable th) {
        if (isLoggable(6)) {
            Log.e(this.TAG, String.valueOf(getSourceString(obj)) + str, th);
        }
    }
}
