package org.conscrypt;

/* loaded from: classes5.dex */
public final class ServerSessionContext extends AbstractSessionContext {
    private SSLServerSessionCache persistentCache;

    ServerSessionContext() {
        super(100);
        NativeCrypto.SSL_CTX_set_session_id_context(this.sslCtxNativePointer, this, new byte[]{32});
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    void onBeforeRemoveSession(NativeSslSession nativeSslSession) {
    }

    public void setPersistentCache(SSLServerSessionCache sSLServerSessionCache) {
        this.persistentCache = sSLServerSessionCache;
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    NativeSslSession getSessionFromPersistentCache(byte[] bArr) {
        byte[] sessionData;
        NativeSslSession nativeSslSessionNewInstance;
        SSLServerSessionCache sSLServerSessionCache = this.persistentCache;
        if (sSLServerSessionCache == null || (sessionData = sSLServerSessionCache.getSessionData(bArr)) == null || (nativeSslSessionNewInstance = NativeSslSession.newInstance(this, sessionData, null, -1)) == null || !nativeSslSessionNewInstance.isValid()) {
            return null;
        }
        cacheSession(nativeSslSessionNewInstance);
        return nativeSslSessionNewInstance;
    }

    @Override
        // org.conscrypt.AbstractSessionContext
    void onBeforeAddSession(NativeSslSession nativeSslSession) {
        byte[] bytes;
        if (this.persistentCache == null || (bytes = nativeSslSession.toBytes()) == null) {
            return;
        }
        this.persistentCache.putSessionData(nativeSslSession.toSSLSession(), bytes);
    }
}
