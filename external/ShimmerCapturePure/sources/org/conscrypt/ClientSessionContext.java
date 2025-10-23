package org.conscrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class ClientSessionContext extends AbstractSessionContext {
    private final Map<HostAndPort, List<NativeSslSession>> sessionsByHostAndPort;
    private SSLClientSessionCache persistentCache;

    ClientSessionContext() {
        super(10);
        this.sessionsByHostAndPort = new HashMap();
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    NativeSslSession getSessionFromPersistentCache(byte[] bArr) {
        return null;
    }

    public void setPersistentCache(SSLClientSessionCache sSLClientSessionCache) {
        this.persistentCache = sSLClientSessionCache;
    }

    synchronized NativeSslSession getCachedSession(String str, int i, SSLParametersImpl sSLParametersImpl) {
        if (str == null) {
            return null;
        }
        NativeSslSession session = getSession(str, i);
        if (session == null) {
            return null;
        }
        String protocol = session.getProtocol();
        for (String str2 : sSLParametersImpl.enabledProtocols) {
            if (protocol.equals(str2)) {
                String cipherSuite = session.getCipherSuite();
                for (String str3 : sSLParametersImpl.getEnabledCipherSuites()) {
                    if (cipherSuite.equals(str3)) {
                        if (session.isSingleUse()) {
                            removeSession(session);
                        }
                        return session;
                    }
                }
                return null;
            }
        }
        return null;
    }

    int size() {
        int size;
        synchronized (this.sessionsByHostAndPort) {
            Iterator<List<NativeSslSession>> it2 = this.sessionsByHostAndPort.values().iterator();
            size = 0;
            while (it2.hasNext()) {
                size += it2.next().size();
            }
        }
        return size;
    }

    private NativeSslSession getSession(String str, int i) {
        NativeSslSession nativeSslSession;
        byte[] sessionData;
        NativeSslSession nativeSslSessionNewInstance;
        if (str == null) {
            return null;
        }
        HostAndPort hostAndPort = new HostAndPort(str, i);
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
            nativeSslSession = (list == null || list.size() <= 0) ? null : list.get(0);
        }
        if (nativeSslSession != null && nativeSslSession.isValid()) {
            return nativeSslSession;
        }
        SSLClientSessionCache sSLClientSessionCache = this.persistentCache;
        if (sSLClientSessionCache == null || (sessionData = sSLClientSessionCache.getSessionData(str, i)) == null || (nativeSslSessionNewInstance = NativeSslSession.newInstance(this, sessionData, str, i)) == null || !nativeSslSessionNewInstance.isValid()) {
            return null;
        }
        putSession(hostAndPort, nativeSslSessionNewInstance);
        return nativeSslSessionNewInstance;
    }

    private void putSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> arrayList = this.sessionsByHostAndPort.get(hostAndPort);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.sessionsByHostAndPort.put(hostAndPort, arrayList);
            }
            if (arrayList.size() > 0 && arrayList.get(0).isSingleUse() != nativeSslSession.isSingleUse()) {
                while (!arrayList.isEmpty()) {
                    removeSession(arrayList.get(0));
                }
                this.sessionsByHostAndPort.put(hostAndPort, arrayList);
            }
            arrayList.add(nativeSslSession);
        }
    }

    private void removeSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
            if (list != null) {
                list.remove(nativeSslSession);
                if (list.isEmpty()) {
                    this.sessionsByHostAndPort.remove(hostAndPort);
                }
            }
        }
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    void onBeforeAddSession(NativeSslSession nativeSslSession) {
        byte[] bytes;
        String peerHost = nativeSslSession.getPeerHost();
        int peerPort = nativeSslSession.getPeerPort();
        if (peerHost == null) {
            return;
        }
        putSession(new HostAndPort(peerHost, peerPort), nativeSslSession);
        if (this.persistentCache == null || nativeSslSession.isSingleUse() || (bytes = nativeSslSession.toBytes()) == null) {
            return;
        }
        this.persistentCache.putSessionData(nativeSslSession.toSSLSession(), bytes);
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    void onBeforeRemoveSession(NativeSslSession nativeSslSession) {
        String peerHost = nativeSslSession.getPeerHost();
        if (peerHost == null) {
            return;
        }
        removeSession(new HostAndPort(peerHost, nativeSslSession.getPeerPort()), nativeSslSession);
    }

    private static final class HostAndPort {
        final String host;
        final int port;

        HostAndPort(String str, int i) {
            this.host = str;
            this.port = i;
        }

        public int hashCode() {
            return (this.host.hashCode() * 31) + this.port;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof HostAndPort)) {
                return false;
            }
            HostAndPort hostAndPort = (HostAndPort) obj;
            return this.host.equals(hostAndPort.host) && this.port == hostAndPort.port;
        }
    }
}
