package org.bouncycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Shorts;

/* loaded from: classes5.dex */
class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    protected TlsContext context;
    private DigestInputBuffer buf;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    private DeferredHash(Short sh, Digest digest) {
        this.buf = null;
        Hashtable hashtable = new Hashtable();
        this.hashes = hashtable;
        this.prfHashAlgorithm = sh;
        hashtable.put(sh, digest);
    }

    protected void checkStopBuffering() {
        if (this.buf == null || this.hashes.size() > 4) {
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            this.buf.updateDigest((Digest) enumerationElements.nextElement());
        }
        this.buf = null;
    }

    protected void checkTrackingHash(Short sh) {
        if (this.hashes.containsKey(sh)) {
            return;
        }
        this.hashes.put(sh, TlsUtils.createHash(sh.shortValue()));
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public Digest forkPRFHash() {
        checkStopBuffering();
        if (this.buf == null) {
            return TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        }
        Digest digestCreateHash = TlsUtils.createHash(this.prfHashAlgorithm.shortValue());
        this.buf.updateDigest(digestCreateHash);
        return digestCreateHash;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s) {
        Digest digest = (Digest) this.hashes.get(Shorts.valueOf(s));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(s) + " is not being tracked");
        }
        Digest digestCloneHash = TlsUtils.cloneHash(s, digest);
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(digestCloneHash);
        }
        byte[] bArr = new byte[digestCloneHash.getDigestSize()];
        digestCloneHash.doFinal(bArr, 0);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.context.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            CombinedHash combinedHash = new CombinedHash();
            combinedHash.init(this.context);
            this.buf.updateDigest(combinedHash);
            return combinedHash.notifyPRFDetermined();
        }
        Short shValueOf = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
        this.prfHashAlgorithm = shValueOf;
        checkTrackingHash(shValueOf);
        return this;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).reset();
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        checkStopBuffering();
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        Digest digestCloneHash = TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(digestCloneHash);
        }
        DeferredHash deferredHash = new DeferredHash(this.prfHashAlgorithm, digestCloneHash);
        deferredHash.init(this.context);
        return deferredHash;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s) {
        if (this.buf == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        checkTrackingHash(Shorts.valueOf(s));
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(b);
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).update(b);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i, i2);
            return;
        }
        Enumeration enumerationElements = this.hashes.elements();
        while (enumerationElements.hasMoreElements()) {
            ((Digest) enumerationElements.nextElement()).update(bArr, i, i2);
        }
    }
}
