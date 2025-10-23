package org.bouncycastle.jcajce.provider.asymmetric.edec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.crypto.agreement.X25519Agreement;
import org.bouncycastle.crypto.agreement.X448Agreement;
import org.bouncycastle.crypto.agreement.XDHUnifiedAgreement;
import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.XDHUPrivateParameters;
import org.bouncycastle.crypto.params.XDHUPublicParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.spec.DHUParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;

/* loaded from: classes5.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    private RawAgreement agreement;
    private DHUParameterSpec dhuSpec;
    private byte[] result;

    KeyAgreementSpi(String str) {
        super(str, null);
    }

    KeyAgreementSpi(String str, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
    }

    private RawAgreement getAgreement(String str) throws InvalidKeyException {
        if (this.kaAlgorithm.equals("XDH") || this.kaAlgorithm.startsWith(str)) {
            int iIndexOf = this.kaAlgorithm.indexOf(85);
            boolean zStartsWith = str.startsWith(XDHParameterSpec.X448);
            return iIndexOf > 0 ? zStartsWith ? new XDHUnifiedAgreement(new X448Agreement()) : new XDHUnifiedAgreement(new X25519Agreement()) : zStartsWith ? new X448Agreement() : new X25519Agreement();
        }
        throw new InvalidKeyException("inappropriate key for " + this.kaAlgorithm);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    protected byte[] calcSecret() {
        return this.result;
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected Key engineDoPhase(Key key, boolean z) throws IllegalStateException, InvalidKeyException {
        if (this.agreement == null) {
            throw new IllegalStateException(this.kaAlgorithm + " not initialised.");
        }
        if (!z) {
            throw new IllegalStateException(this.kaAlgorithm + " can only be between two parties.");
        }
        if (!(key instanceof BCXDHPublicKey)) {
            throw new InvalidKeyException("cannot identify XDH private key");
        }
        AsymmetricKeyParameter asymmetricKeyParameterEngineGetKeyParameters = ((BCXDHPublicKey) key).engineGetKeyParameters();
        byte[] bArr = new byte[this.agreement.getAgreementSize()];
        this.result = bArr;
        DHUParameterSpec dHUParameterSpec = this.dhuSpec;
        if (dHUParameterSpec != null) {
            this.agreement.calculateAgreement(new XDHUPublicParameters(asymmetricKeyParameterEngineGetKeyParameters, ((BCXDHPublicKey) dHUParameterSpec.getOtherPartyEphemeralKey()).engineGetKeyParameters()), this.result, 0);
            return null;
        }
        this.agreement.calculateAgreement(asymmetricKeyParameterEngineGetKeyParameters, bArr, 0);
        return null;
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (!(key instanceof BCXDHPrivateKey)) {
            throw new InvalidKeyException("cannot identify XDH private key");
        }
        AsymmetricKeyParameter asymmetricKeyParameterEngineGetKeyParameters = ((BCXDHPrivateKey) key).engineGetKeyParameters();
        this.agreement = getAgreement(asymmetricKeyParameterEngineGetKeyParameters instanceof X448PrivateKeyParameters ? XDHParameterSpec.X448 : XDHParameterSpec.X25519);
        this.agreement.init(asymmetricKeyParameterEngineGetKeyParameters);
        if (this.kdf != null) {
            this.ukmParameters = new byte[0];
        } else {
            this.ukmParameters = null;
        }
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof BCXDHPrivateKey)) {
            throw new InvalidKeyException("cannot identify XDH private key");
        }
        AsymmetricKeyParameter asymmetricKeyParameterEngineGetKeyParameters = ((BCXDHPrivateKey) key).engineGetKeyParameters();
        this.agreement = getAgreement(asymmetricKeyParameterEngineGetKeyParameters instanceof X448PrivateKeyParameters ? XDHParameterSpec.X448 : XDHParameterSpec.X25519);
        this.ukmParameters = null;
        if (!(algorithmParameterSpec instanceof DHUParameterSpec)) {
            this.agreement.init(asymmetricKeyParameterEngineGetKeyParameters);
            if (!(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) {
                throw new InvalidAlgorithmParameterException("unknown ParameterSpec");
            }
            if (this.kdf == null) {
                throw new InvalidAlgorithmParameterException("no KDF specified for UserKeyingMaterialSpec");
            }
            this.ukmParameters = ((UserKeyingMaterialSpec) algorithmParameterSpec).getUserKeyingMaterial();
        } else {
            if (this.kaAlgorithm.indexOf(85) < 0) {
                throw new InvalidAlgorithmParameterException("agreement algorithm not DHU based");
            }
            DHUParameterSpec dHUParameterSpec = (DHUParameterSpec) algorithmParameterSpec;
            this.dhuSpec = dHUParameterSpec;
            this.ukmParameters = dHUParameterSpec.getUserKeyingMaterial();
            this.agreement.init(new XDHUPrivateParameters(asymmetricKeyParameterEngineGetKeyParameters, ((BCXDHPrivateKey) this.dhuSpec.getEphemeralPrivateKey()).engineGetKeyParameters(), ((BCXDHPublicKey) this.dhuSpec.getEphemeralPublicKey()).engineGetKeyParameters()));
        }
        if (this.kdf == null || this.ukmParameters != null) {
            return;
        }
        this.ukmParameters = new byte[0];
    }

    public static final class X25519 extends KeyAgreementSpi {
        public X25519() {
            super(XDHParameterSpec.X25519);
        }
    }

    public static class X25519UwithSHA256CKDF extends KeyAgreementSpi {
        public X25519UwithSHA256CKDF() {
            super("X25519UwithSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X25519UwithSHA256KDF extends KeyAgreementSpi {
        public X25519UwithSHA256KDF() {
            super("X25519UwithSHA256KDF", new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static final class X25519withSHA256CKDF extends KeyAgreementSpi {
        public X25519withSHA256CKDF() {
            super("X25519withSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static final class X25519withSHA256KDF extends KeyAgreementSpi {
        public X25519withSHA256KDF() {
            super("X25519withSHA256KDF", new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X25519withSHA384CKDF extends KeyAgreementSpi {
        public X25519withSHA384CKDF() {
            super("X25519withSHA384CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class X25519withSHA512CKDF extends KeyAgreementSpi {
        public X25519withSHA512CKDF() {
            super("X25519withSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448 extends KeyAgreementSpi {
        public X448() {
            super(XDHParameterSpec.X448);
        }
    }

    public static class X448UwithSHA512CKDF extends KeyAgreementSpi {
        public X448UwithSHA512CKDF() {
            super("X448UwithSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class X448UwithSHA512KDF extends KeyAgreementSpi {
        public X448UwithSHA512KDF() {
            super("X448UwithSHA512KDF", new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448withSHA256CKDF extends KeyAgreementSpi {
        public X448withSHA256CKDF() {
            super("X448withSHA256CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class X448withSHA384CKDF extends KeyAgreementSpi {
        public X448withSHA384CKDF() {
            super("X448withSHA384CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static final class X448withSHA512CKDF extends KeyAgreementSpi {
        public X448withSHA512CKDF() {
            super("X448withSHA512CKDF", new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class X448withSHA512KDF extends KeyAgreementSpi {
        public X448withSHA512KDF() {
            super("X448withSHA512KDF", new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static final class XDH extends KeyAgreementSpi {
        public XDH() {
            super("XDH");
        }
    }
}
