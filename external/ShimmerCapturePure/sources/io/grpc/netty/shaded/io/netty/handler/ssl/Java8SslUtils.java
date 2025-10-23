package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.alts.CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLParameters;

/* loaded from: classes3.dex */
final class Java8SslUtils {
    private Java8SslUtils() {
    }

    static List<String> getSniHostNames(SSLParameters sSLParameters) {
        List serverNames = sSLParameters.getServerNames();
        if (serverNames == null || serverNames.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(serverNames.size());
        Iterator it2 = serverNames.iterator();
        while (it2.hasNext()) {
            SNIServerName sNIServerNameM6313m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6313m(it2.next());
            if (CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6317m((Object) sNIServerNameM6313m)) {
                arrayList.add(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6310m((Object) sNIServerNameM6313m).getAsciiName());
            } else {
                throw new IllegalArgumentException("Only " + CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m().getName() + " instances are supported, but found: " + sNIServerNameM6313m);
            }
        }
        return arrayList;
    }

    static void setSniHostNames(SSLParameters sSLParameters, List<String> list) {
        sSLParameters.setServerNames(getSniHostNames(list));
    }

    static List getSniHostNames(List<String> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6311m(it2.next()));
        }
        return arrayList;
    }

    static List getSniHostName(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m(bArr));
    }

    static boolean getUseCipherSuitesOrder(SSLParameters sSLParameters) {
        return sSLParameters.getUseCipherSuitesOrder();
    }

    static void setUseCipherSuitesOrder(SSLParameters sSLParameters, boolean z) {
        sSLParameters.setUseCipherSuitesOrder(z);
    }

    static void setSNIMatchers(SSLParameters sSLParameters, Collection<?> collection) {
        sSLParameters.setSNIMatchers(collection);
    }

    static boolean checkSniHostnameMatch(Collection<?> collection, byte[] bArr) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        SNIHostName sNIHostNameM = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m(bArr);
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            SNIMatcher sNIMatcherM6312m = CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m6312m(it2.next());
            if (sNIMatcherM6312m.getType() == 0 && sNIMatcherM6312m.matches(sNIHostNameM)) {
                return true;
            }
        }
        return false;
    }
}
