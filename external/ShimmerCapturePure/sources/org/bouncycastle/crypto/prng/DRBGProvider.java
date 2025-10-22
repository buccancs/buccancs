package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

/* loaded from: classes5.dex */
interface DRBGProvider {
    SP80090DRBG get(EntropySource entropySource);
}
