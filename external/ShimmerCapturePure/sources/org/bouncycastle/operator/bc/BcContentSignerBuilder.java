package org.bouncycastle.operator.bc;

import java.io.OutputStream;
import java.security.SecureRandom;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RuntimeOperatorException;

/* loaded from: classes5.dex */
public abstract class BcContentSignerBuilder {
    protected BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;
    private AlgorithmIdentifier digAlgId;
    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;

    public BcContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.sigAlgId = algorithmIdentifier;
        this.digAlgId = algorithmIdentifier2;
    }

    public ContentSigner build(AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer signerCreateSigner = createSigner(this.sigAlgId, this.digAlgId);
        if (this.random != null) {
            signerCreateSigner.init(true, new ParametersWithRandom(asymmetricKeyParameter, this.random));
        } else {
            signerCreateSigner.init(true, asymmetricKeyParameter);
        }
        return new ContentSigner(signerCreateSigner) { // from class: org.bouncycastle.operator.bc.BcContentSignerBuilder.1
            final /* synthetic */ Signer val$sig;
            private BcSignerOutputStream stream;

            {
                this.val$sig = signerCreateSigner;
                this.stream = new BcSignerOutputStream(signerCreateSigner);
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return BcContentSignerBuilder.this.sigAlgId;
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public OutputStream getOutputStream() {
                return this.stream;
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public byte[] getSignature() {
                try {
                    return this.stream.getSignature();
                } catch (CryptoException e) {
                    throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
                }
            }
        };
    }

    protected abstract Signer createSigner(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException;

    public BcContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
