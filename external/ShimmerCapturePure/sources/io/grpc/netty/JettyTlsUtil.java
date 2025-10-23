package io.grpc.netty;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* loaded from: classes2.dex */
final class JettyTlsUtil {
    private static Throwable jettyAlpnUnavailabilityCause;
    private static Throwable jettyNpnUnavailabilityCause;

    private JettyTlsUtil() {
    }

    static synchronized boolean isJettyAlpnConfigured() {
        try {
            Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
        } catch (ClassNotFoundException e) {
            jettyAlpnUnavailabilityCause = e;
            return false;
        }
        return true;
    }

    static synchronized Throwable getJettyAlpnUnavailabilityCause() {
        if (jettyAlpnUnavailabilityCause == null) {
            isJettyAlpnConfigured();
        }
        return jettyAlpnUnavailabilityCause;
    }

    static synchronized boolean isJettyNpnConfigured() {
        try {
            Class.forName("org.eclipse.jetty.npn.NextProtoNego", true, null);
        } catch (ClassNotFoundException e) {
            jettyNpnUnavailabilityCause = e;
            return false;
        }
        return true;
    }

    static synchronized Throwable getJettyNpnUnavailabilityCause() {
        if (jettyNpnUnavailabilityCause == null) {
            isJettyNpnConfigured();
        }
        return jettyNpnUnavailabilityCause;
    }

    static boolean isJava9AlpnAvailable() {
        return getJava9AlpnUnavailabilityCause() == null;
    }

    static Throwable getJava9AlpnUnavailabilityCause() {
        return Java9AlpnUnavailabilityCauseHolder.cause;
    }

    private static class Java9AlpnUnavailabilityCauseHolder {
        static final Throwable cause = checkAlpnAvailability();

        private Java9AlpnUnavailabilityCauseHolder() {
        }

        static Throwable checkAlpnAvailability() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                ((Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: io.grpc.netty.JettyTlsUtil.Java9AlpnUnavailabilityCauseHolder.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                })).invoke(sSLContext.createSSLEngine(), new Object[0]);
                return null;
            } catch (Throwable th) {
                return th;
            }
        }
    }
}
