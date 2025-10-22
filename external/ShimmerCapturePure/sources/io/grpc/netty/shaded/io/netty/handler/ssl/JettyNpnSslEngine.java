package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.util.LinkedHashSet;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

import org.eclipse.jetty.npn.NextProtoNego;

/* loaded from: classes3.dex */
final class JettyNpnSslEngine extends JdkSslEngine {
    private static boolean available;

    JettyNpnSslEngine(SSLEngine sSLEngine, final JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
        super(sSLEngine);
        ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator, "applicationNegotiator");
        if (z) {
            final JdkApplicationProtocolNegotiator.ProtocolSelectionListener protocolSelectionListener = (JdkApplicationProtocolNegotiator.ProtocolSelectionListener) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols()), "protocolListener");
            NextProtoNego.put(sSLEngine, new NextProtoNego.ServerProvider() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JettyNpnSslEngine.1
                public void unsupported() {
                    protocolSelectionListener.unsupported();
                }

                public List<String> protocols() {
                    return jdkApplicationProtocolNegotiator.protocols();
                }

                public void protocolSelected(String str) throws Throwable {
                    try {
                        protocolSelectionListener.selected(str);
                    } catch (Throwable th) {
                        PlatformDependent.throwException(th);
                    }
                }
            });
        } else {
            final JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector = (JdkApplicationProtocolNegotiator.ProtocolSelector) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())), "protocolSelector");
            NextProtoNego.put(sSLEngine, new NextProtoNego.ClientProvider() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JettyNpnSslEngine.2
                public boolean supports() {
                    return true;
                }

                public void unsupported() {
                    protocolSelector.unsupported();
                }

                public String selectProtocol(List<String> list) throws Throwable {
                    try {
                        return protocolSelector.select(list);
                    } catch (Throwable th) {
                        PlatformDependent.throwException(th);
                        return null;
                    }
                }
            });
        }
    }

    static boolean isAvailable() throws ClassNotFoundException {
        updateAvailability();
        return available;
    }

    private static void updateAvailability() throws ClassNotFoundException {
        if (available) {
            return;
        }
        try {
            Class.forName("sun.security.ssl.NextProtoNegoExtension", true, null);
            available = true;
        } catch (Exception unused) {
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public void closeInbound() throws SSLException {
        NextProtoNego.remove(getWrappedEngine());
        super.closeInbound();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public void closeOutbound() {
        NextProtoNego.remove(getWrappedEngine());
        super.closeOutbound();
    }
}
