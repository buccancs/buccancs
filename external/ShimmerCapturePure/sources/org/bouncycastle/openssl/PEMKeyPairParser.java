package org.bouncycastle.openssl;

import java.io.IOException;

/* loaded from: classes5.dex */
interface PEMKeyPairParser {
    PEMKeyPair parse(byte[] bArr) throws IOException;
}
