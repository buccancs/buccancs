package io.grpc.netty.shaded.io.netty.util.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class ThrowableUtil {
    private ThrowableUtil() {
    }

    public static <T extends Throwable> T unknownStackTrace(T t, Class<?> cls, String str) {
        t.setStackTrace(new StackTraceElement[]{new StackTraceElement(cls.getName(), str, null, -1)});
        return t;
    }

    public static String stackTraceToString(Throwable th) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        try {
            return new String(byteArrayOutputStream.toByteArray());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean haveSuppressed() {
        return PlatformDependent.javaVersion() >= 7;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        if (haveSuppressed()) {
            th.addSuppressed(th2);
        }
    }

    public static void addSuppressedAndClear(Throwable th, List<Throwable> list) {
        addSuppressed(th, list);
        list.clear();
    }

    public static void addSuppressed(Throwable th, List<Throwable> list) {
        Iterator<Throwable> it2 = list.iterator();
        while (it2.hasNext()) {
            addSuppressed(th, it2.next());
        }
    }
}
