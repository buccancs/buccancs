package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.net.ssl.SSLEngine;

/* loaded from: classes3.dex */
final class Conscrypt {
    private static final Method IS_CONSCRYPT_SSLENGINE = loadIsConscryptEngine();
    private static final boolean CAN_INSTANCE_PROVIDER = canInstanceProvider();

    private Conscrypt() {
    }

    private static Method loadIsConscryptEngine() {
        try {
            return Class.forName("org.conscrypt.Conscrypt", true, ConscryptAlpnSslEngine.class.getClassLoader()).getMethod("isConscrypt", SSLEngine.class);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean canInstanceProvider() {
        try {
            Class.forName("org.conscrypt.OpenSSLProvider", true, ConscryptAlpnSslEngine.class.getClassLoader()).newInstance();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean isAvailable() {
        return CAN_INSTANCE_PROVIDER && IS_CONSCRYPT_SSLENGINE != null && ((PlatformDependent.javaVersion() >= 8 && PlatformDependent.javaVersion() < 15) || PlatformDependent.isAndroid());
    }

    static boolean isEngineSupported(SSLEngine sSLEngine) {
        return isAvailable() && isConscryptEngine(sSLEngine);
    }

    private static boolean isConscryptEngine(SSLEngine sSLEngine) {
        try {
            return ((Boolean) IS_CONSCRYPT_SSLENGINE.invoke(null, sSLEngine)).booleanValue();
        } catch (IllegalAccessException unused) {
            return false;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
