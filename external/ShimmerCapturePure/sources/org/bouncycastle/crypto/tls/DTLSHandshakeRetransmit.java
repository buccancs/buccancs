package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes5.dex */
interface DTLSHandshakeRetransmit {
    void receivedHandshakeRecord(int i, byte[] bArr, int i2, int i3) throws IOException;
}
