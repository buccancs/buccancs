package org.conscrypt;

import io.grpc.alts.CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline2;

/* loaded from: classes5.dex */
final class Java8PlatformUtil {
    private Java8PlatformUtil() {
    }

    static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        sSLParametersImpl.setEndpointIdentificationAlgorithm(sSLParameters.getEndpointIdentificationAlgorithm());
        sSLParametersImpl.setUseCipherSuitesOrder(sSLParameters.getUseCipherSuitesOrder());
        List serverNames = sSLParameters.getServerNames();
        if (serverNames != null) {
            Iterator it2 = serverNames.iterator();
            while (it2.hasNext()) {
                SNIServerName sNIServerNameM6313m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6313m(it2.next());
                if (sNIServerNameM6313m.getType() == 0) {
                    abstractConscryptSocket.setHostname(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6310m((Object) sNIServerNameM6313m).getAsciiName());
                    return;
                }
            }
        }
    }

    static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        sSLParameters.setEndpointIdentificationAlgorithm(sSLParametersImpl.getEndpointIdentificationAlgorithm());
        sSLParameters.setUseCipherSuitesOrder(sSLParametersImpl.getUseCipherSuitesOrder());
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(abstractConscryptSocket.getHostname())) {
            StreamsKt$$ExternalSyntheticApiModelOutline2.m40103m();
            sSLParameters.setServerNames(Collections.singletonList(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6311m(abstractConscryptSocket.getHostname())));
        }
    }

    static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        sSLParametersImpl.setEndpointIdentificationAlgorithm(sSLParameters.getEndpointIdentificationAlgorithm());
        sSLParametersImpl.setUseCipherSuitesOrder(sSLParameters.getUseCipherSuitesOrder());
        List serverNames = sSLParameters.getServerNames();
        if (serverNames != null) {
            Iterator it2 = serverNames.iterator();
            while (it2.hasNext()) {
                SNIServerName sNIServerNameM6313m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6313m(it2.next());
                if (sNIServerNameM6313m.getType() == 0) {
                    conscryptEngine.setHostname(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6310m((Object) sNIServerNameM6313m).getAsciiName());
                    return;
                }
            }
        }
    }

    static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        sSLParameters.setEndpointIdentificationAlgorithm(sSLParametersImpl.getEndpointIdentificationAlgorithm());
        sSLParameters.setUseCipherSuitesOrder(sSLParametersImpl.getUseCipherSuitesOrder());
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(conscryptEngine.getHostname())) {
            StreamsKt$$ExternalSyntheticApiModelOutline2.m40103m();
            sSLParameters.setServerNames(Collections.singletonList(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6311m(conscryptEngine.getHostname())));
        }
    }

    static SSLEngine wrapEngine(ConscryptEngine conscryptEngine) {
        return new Java8EngineWrapper(conscryptEngine);
    }

    static SSLEngine unwrapEngine(SSLEngine sSLEngine) {
        return Java8EngineWrapper.getDelegate(sSLEngine);
    }

    static SSLSession wrapSSLSession(ExternalSession externalSession) {
        return new Java8ExtendedSSLSession(externalSession);
    }
}
