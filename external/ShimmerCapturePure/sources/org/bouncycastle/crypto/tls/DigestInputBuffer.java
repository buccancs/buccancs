package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes5.dex */
class DigestInputBuffer extends ByteArrayOutputStream {
    DigestInputBuffer() {
    }

    void updateDigest(Digest digest) {
        digest.update(this.buf, 0, this.count);
    }
}
