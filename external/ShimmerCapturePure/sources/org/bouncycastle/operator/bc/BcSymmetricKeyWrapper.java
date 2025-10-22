package org.bouncycastle.operator.bc;

import java.security.SecureRandom;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;

/* loaded from: classes5.dex */
public class BcSymmetricKeyWrapper extends SymmetricKeyWrapper {
    private SecureRandom random;
    private Wrapper wrapper;
    private KeyParameter wrappingKey;

    public BcSymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, Wrapper wrapper, KeyParameter keyParameter) {
        super(algorithmIdentifier);
        this.wrapper = wrapper;
        this.wrappingKey = keyParameter;
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Wrapper wrapper;
        CipherParameters parametersWithRandom;
        byte[] keyBytes = OperatorUtils.getKeyBytes(genericKey);
        if (this.random == null) {
            wrapper = this.wrapper;
            parametersWithRandom = this.wrappingKey;
        } else {
            wrapper = this.wrapper;
            parametersWithRandom = new ParametersWithRandom(this.wrappingKey, this.random);
        }
        wrapper.init(true, parametersWithRandom);
        return this.wrapper.wrap(keyBytes, 0, keyBytes.length);
    }

    public BcSymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
