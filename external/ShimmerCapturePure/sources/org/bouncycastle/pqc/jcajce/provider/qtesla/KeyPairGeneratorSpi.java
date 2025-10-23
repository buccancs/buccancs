package org.bouncycastle.pqc.jcajce.provider.qtesla;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.qtesla.QTESLAKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.qtesla.QTESLAKeyPairGenerator;
import org.bouncycastle.pqc.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.qtesla.QTESLAPublicKeyParameters;
import org.bouncycastle.pqc.crypto.qtesla.QTESLASecurityCategory;
import org.bouncycastle.pqc.jcajce.spec.QTESLAParameterSpec;
import org.bouncycastle.util.Integers;

/* loaded from: classes5.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    private static final Map catLookup;

    static {
        HashMap map = new HashMap();
        catLookup = map;
        map.put(QTESLASecurityCategory.getName(0), Integers.valueOf(0));
        map.put(QTESLASecurityCategory.getName(1), Integers.valueOf(1));
        map.put(QTESLASecurityCategory.getName(2), Integers.valueOf(2));
        map.put(QTESLASecurityCategory.getName(3), Integers.valueOf(3));
        map.put(QTESLASecurityCategory.getName(4), Integers.valueOf(4));
    }

    private QTESLAKeyPairGenerator engine;
    private boolean initialised;
    private QTESLAKeyGenerationParameters param;
    private SecureRandom random;

    public KeyPairGeneratorSpi() {
        super("qTESLA");
        this.engine = new QTESLAKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            QTESLAKeyGenerationParameters qTESLAKeyGenerationParameters = new QTESLAKeyGenerationParameters(3, this.random);
            this.param = qTESLAKeyGenerationParameters;
            this.engine.init(qTESLAKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCqTESLAPublicKey((QTESLAPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCqTESLAPrivateKey((QTESLAPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof QTESLAParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a QTESLAParameterSpec");
        }
        QTESLAKeyGenerationParameters qTESLAKeyGenerationParameters = new QTESLAKeyGenerationParameters(((Integer) catLookup.get(((QTESLAParameterSpec) algorithmParameterSpec).getSecurityCategory())).intValue(), secureRandom);
        this.param = qTESLAKeyGenerationParameters;
        this.engine.init(qTESLAKeyGenerationParameters);
        this.initialised = true;
    }
}
