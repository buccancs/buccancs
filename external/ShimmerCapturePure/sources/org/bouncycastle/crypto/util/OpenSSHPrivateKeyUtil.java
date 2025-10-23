package org.bouncycastle.crypto.util;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class OpenSSHPrivateKeyUtil {
    static final byte[] AUTH_MAGIC = Strings.toByteArray("openssh-key-v1\u0000");

    private OpenSSHPrivateKeyUtil() {
    }

    private static boolean allIntegers(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i < aSN1Sequence.size(); i++) {
            if (!(aSN1Sequence.getObjectAt(i) instanceof ASN1Integer)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodePrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (!(asymmetricKeyParameter instanceof RSAPrivateCrtKeyParameters) && !(asymmetricKeyParameter instanceof ECPrivateKeyParameters)) {
            if (asymmetricKeyParameter instanceof DSAPrivateKeyParameters) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                aSN1EncodableVector.add(new ASN1Integer(0L));
                DSAPrivateKeyParameters dSAPrivateKeyParameters = (DSAPrivateKeyParameters) asymmetricKeyParameter;
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getParameters().getP()));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getParameters().getQ()));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getParameters().getG()));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getParameters().getG().modPow(dSAPrivateKeyParameters.getX(), dSAPrivateKeyParameters.getParameters().getP())));
                aSN1EncodableVector.add(new ASN1Integer(dSAPrivateKeyParameters.getX()));
                try {
                    return new DERSequence(aSN1EncodableVector).getEncoded();
                } catch (Exception e) {
                    throw new IllegalStateException("unable to encode DSAPrivateKeyParameters " + e.getMessage());
                }
            }
            if (!(asymmetricKeyParameter instanceof Ed25519PrivateKeyParameters)) {
                throw new IllegalArgumentException("unable to convert " + asymmetricKeyParameter.getClass().getName() + " to openssh private key");
            }
            SSHBuilder sSHBuilder = new SSHBuilder();
            sSHBuilder.write(AUTH_MAGIC);
            sSHBuilder.writeString("none");
            sSHBuilder.writeString("none");
            sSHBuilder.u32(0L);
            sSHBuilder.u32(1L);
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = (Ed25519PrivateKeyParameters) asymmetricKeyParameter;
            sSHBuilder.rawArray(OpenSSHPublicKeyUtil.encodePublicKey(ed25519PrivateKeyParameters.generatePublicKey()));
            SSHBuilder sSHBuilder2 = new SSHBuilder();
            sSHBuilder2.u32(16711935L);
            sSHBuilder2.u32(16711935L);
            sSHBuilder2.writeString("ssh-ed25519");
            byte[] encoded = ed25519PrivateKeyParameters.generatePublicKey().getEncoded();
            sSHBuilder2.rawArray(encoded);
            sSHBuilder2.rawArray(Arrays.concatenate(ed25519PrivateKeyParameters.getEncoded(), encoded));
            sSHBuilder2.u32(0L);
            sSHBuilder.rawArray(sSHBuilder2.getBytes());
            return sSHBuilder.getBytes();
        }
        return PrivateKeyInfoFactory.createPrivateKeyInfo(asymmetricKeyParameter).parsePrivateKey().toASN1Primitive().getEncoded();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.crypto.params.AsymmetricKeyParameter parsePrivateKeyBlob(byte[] r10) {
        /*
            Method dump skipped, instructions count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.util.OpenSSHPrivateKeyUtil.parsePrivateKeyBlob(byte[]):org.bouncycastle.crypto.params.AsymmetricKeyParameter");
    }
}
