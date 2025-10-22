package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes5.dex */
public interface ExchangePairGenerator {
    ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter);

    ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter);
}
