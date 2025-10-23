package org.bouncycastle.pkcs.jcajce;

import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.ScryptParams;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SecretKeySizeProvider;

/* loaded from: classes5.dex */
public class JcePKCSPBEInputDecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private boolean wrongPKCS12Zero = false;
    private SecretKeySizeProvider keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;

    public InputDecryptorProvider build(final char[] cArr) {
        return new InputDecryptorProvider() { // from class: org.bouncycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder.1
            private Cipher cipher;
            private AlgorithmIdentifier encryptionAlg;

            @Override // org.bouncycastle.operator.InputDecryptorProvider
            public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws InvalidKeySpecException, OperatorCreationException, InvalidKeyException, InvalidAlgorithmParameterException {
                SecretKey secretKeyGenerateSecret;
                Cipher cipher;
                AlgorithmParameterSpec gOST28147ParameterSpec;
                ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                try {
                    if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                        PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                        Cipher cipherCreateCipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(algorithm.getId());
                        this.cipher = cipherCreateCipher;
                        cipherCreateCipher.init(2, new PKCS12KeyWithParameters(cArr, JcePKCSPBEInputDecryptorProviderBuilder.this.wrongPKCS12Zero, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue()));
                        this.encryptionAlg = algorithmIdentifier;
                    } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                        if (MiscObjectIdentifiers.id_scrypt.equals(pBES2Parameters.getKeyDerivationFunc().getAlgorithm())) {
                            ScryptParams scryptParams = ScryptParams.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                            secretKeyGenerateSecret = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createSecretKeyFactory("SCRYPT").generateSecret(new ScryptKeySpec(cArr, scryptParams.getSalt(), scryptParams.getCostParameter().intValue(), scryptParams.getBlockSize().intValue(), scryptParams.getParallelizationParameter().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme()))));
                        } else {
                            SecretKeyFactory secretKeyFactoryCreateSecretKeyFactory = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
                            PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                            AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                            secretKeyGenerateSecret = pBKDF2Params.isDefaultPrf() ? secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2))) : secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
                        }
                        this.cipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
                        this.encryptionAlg = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                        ASN1Encodable parameters = pBES2Parameters.getEncryptionScheme().getParameters();
                        if (parameters instanceof ASN1OctetString) {
                            cipher = this.cipher;
                            gOST28147ParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
                        } else {
                            GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(parameters);
                            cipher = this.cipher;
                            gOST28147ParameterSpec = new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV());
                        }
                        cipher.init(2, secretKeyGenerateSecret, gOST28147ParameterSpec);
                    } else {
                        if (!algorithm.equals(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC) && !algorithm.equals(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC)) {
                            throw new OperatorCreationException("unable to create InputDecryptor: algorithm " + algorithm + " unknown.");
                        }
                        PBEParameter pBEParameter = PBEParameter.getInstance(algorithmIdentifier.getParameters());
                        Cipher cipherCreateCipher2 = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(algorithm.getId());
                        this.cipher = cipherCreateCipher2;
                        cipherCreateCipher2.init(2, new PBKDF1Key(cArr, PasswordConverter.ASCII), new PBEParameterSpec(pBEParameter.getSalt(), pBEParameter.getIterationCount().intValue()));
                    }
                    return new InputDecryptor() { // from class: org.bouncycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder.1.1
                        @Override // org.bouncycastle.operator.InputDecryptor
                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return AnonymousClass1.this.encryptionAlg;
                        }

                        @Override // org.bouncycastle.operator.InputDecryptor
                        public InputStream getInputStream(InputStream inputStream) {
                            return new CipherInputStream(inputStream, AnonymousClass1.this.cipher);
                        }
                    };
                } catch (Exception e) {
                    throw new OperatorCreationException("unable to create InputDecryptor: " + e.getMessage(), e);
                }
            }
        };
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setTryWrongPKCS12Zero(boolean z) {
        this.wrongPKCS12Zero = z;
        return this;
    }
}
