package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.util.io.TeeInputStream;

/* loaded from: classes5.dex */
public class TlsDHEKeyExchange extends TlsDHKeyExchange {
    protected TlsSignerCredentials serverCredentials;

    public TlsDHEKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        this(i, vector, new DefaultTlsDHVerifier(), dHParameters);
    }

    public TlsDHEKeyExchange(int i, Vector vector, TlsDHVerifier tlsDHVerifier, DHParameters dHParameters) {
        super(i, vector, tlsDHVerifier, dHParameters);
        this.serverCredentials = null;
    }

    @Override
    // org.bouncycastle.crypto.tls.TlsDHKeyExchange, org.bouncycastle.crypto.tls.AbstractTlsKeyExchange, org.bouncycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        if (this.dhParameters == null) {
            throw new TlsFatalAlert((short) 80);
        }
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, digestInputBuffer);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
        Digest digestCreateHash = TlsUtils.createHash(signatureAndHashAlgorithm);
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        digestCreateHash.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        digestCreateHash.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        digestInputBuffer.updateDigest(digestCreateHash);
        byte[] bArr = new byte[digestCreateHash.getDigestSize()];
        digestCreateHash.doFinal(bArr, 0);
        new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr)).encode(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signerCreateVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        signerCreateVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signerCreateVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signerCreateVerifyer;
    }

    @Override // org.bouncycastle.crypto.tls.AbstractTlsKeyExchange, org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
        processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
    }

    @Override
    // org.bouncycastle.crypto.tls.TlsDHKeyExchange, org.bouncycastle.crypto.tls.AbstractTlsKeyExchange, org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        this.dhParameters = TlsDHUtils.receiveDHParameters(this.dhVerifier, teeInputStream);
        this.dhAgreePublicKey = new DHPublicKeyParameters(TlsDHUtils.readDHParameter(teeInputStream), this.dhParameters);
        DigitallySigned signature = parseSignature(inputStream);
        Signer signerInitVerifyer = initVerifyer(this.tlsSigner, signature.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(signerInitVerifyer);
        if (!signerInitVerifyer.verifySignature(signature.getSignature())) {
            throw new TlsFatalAlert((short) 51);
        }
    }
}
