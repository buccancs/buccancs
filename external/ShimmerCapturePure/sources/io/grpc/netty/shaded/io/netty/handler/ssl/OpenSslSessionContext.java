package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SessionTicketKey;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;

/* loaded from: classes3.dex */
public abstract class OpenSslSessionContext implements SSLSessionContext {
    private static final Enumeration<byte[]> EMPTY = new EmptyEnumeration();
    final ReferenceCountedOpenSslContext context;
    private final OpenSslKeyMaterialProvider provider;
    private final OpenSslSessionStats stats;

    OpenSslSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, OpenSslKeyMaterialProvider openSslKeyMaterialProvider) {
        this.context = referenceCountedOpenSslContext;
        this.provider = openSslKeyMaterialProvider;
        this.stats = new OpenSslSessionStats(referenceCountedOpenSslContext);
    }

    @Override // javax.net.ssl.SSLSessionContext
    public Enumeration<byte[]> getIds() {
        return EMPTY;
    }

    public abstract boolean isSessionCacheEnabled();

    public abstract void setSessionCacheEnabled(boolean z);

    public OpenSslSessionStats stats() {
        return this.stats;
    }

    final boolean useKeyManager() {
        return this.provider != null;
    }

    @Override // javax.net.ssl.SSLSessionContext
    public SSLSession getSession(byte[] bArr) {
        ObjectUtil.checkNotNull(bArr, "bytes");
        return null;
    }

    @Deprecated
    public void setTicketKeys(byte[] bArr) {
        if (bArr.length % 48 != 0) {
            throw new IllegalArgumentException("keys.length % 48 != 0");
        }
        int length = bArr.length / 48;
        SessionTicketKey[] sessionTicketKeyArr = new SessionTicketKey[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 17) {
            int i3 = i + 16;
            i += 32;
            sessionTicketKeyArr[i2 + 16] = new SessionTicketKey(Arrays.copyOfRange(bArr, i, 16), Arrays.copyOfRange(bArr, i3, 16), Arrays.copyOfRange(bArr, i3, 16));
        }
        Lock lockWriteLock = this.context.ctxLock.writeLock();
        lockWriteLock.lock();
        try {
            SSLContext.clearOptions(this.context.ctx, SSL.SSL_OP_NO_TICKET);
            SSLContext.setSessionTicketKeys(this.context.ctx, sessionTicketKeyArr);
        } finally {
            lockWriteLock.unlock();
        }
    }

    public void setTicketKeys(OpenSslSessionTicketKey... openSslSessionTicketKeyArr) {
        ObjectUtil.checkNotNull(openSslSessionTicketKeyArr, "keys");
        int length = openSslSessionTicketKeyArr.length;
        SessionTicketKey[] sessionTicketKeyArr = new SessionTicketKey[length];
        for (int i = 0; i < length; i++) {
            sessionTicketKeyArr[i] = openSslSessionTicketKeyArr[i].key;
        }
        Lock lockWriteLock = this.context.ctxLock.writeLock();
        lockWriteLock.lock();
        try {
            SSLContext.clearOptions(this.context.ctx, SSL.SSL_OP_NO_TICKET);
            if (length > 0) {
                SSLContext.setSessionTicketKeys(this.context.ctx, sessionTicketKeyArr);
            }
        } finally {
            lockWriteLock.unlock();
        }
    }

    final void destroy() {
        OpenSslKeyMaterialProvider openSslKeyMaterialProvider = this.provider;
        if (openSslKeyMaterialProvider != null) {
            openSslKeyMaterialProvider.destroy();
        }
    }

    private static final class EmptyEnumeration implements Enumeration<byte[]> {
        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public byte[] nextElement() {
            throw new NoSuchElementException();
        }
    }
}
