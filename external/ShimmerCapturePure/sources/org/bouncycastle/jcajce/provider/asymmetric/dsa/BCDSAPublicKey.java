package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPublicKeySpec;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class BCDSAPublicKey implements DSAPublicKey {
    private static final long serialVersionUID = 1752452449903495175L;
    private static BigInteger ZERO = BigInteger.valueOf(0);
    private transient DSAParams dsaSpec;
    private transient DSAPublicKeyParameters lwKeyParams;
    private BigInteger y;

    BCDSAPublicKey(DSAPublicKey dSAPublicKey) {
        this.y = dSAPublicKey.getY();
        this.dsaSpec = dSAPublicKey.getParams();
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    BCDSAPublicKey(DSAPublicKeySpec dSAPublicKeySpec) {
        this.y = dSAPublicKeySpec.getY();
        this.dsaSpec = new DSAParameterSpec(dSAPublicKeySpec.getP(), dSAPublicKeySpec.getQ(), dSAPublicKeySpec.getG());
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    public BCDSAPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            this.y = ((ASN1Integer) subjectPublicKeyInfo.parsePublicKey()).getValue();
            if (isNotNull(subjectPublicKeyInfo.getAlgorithm().getParameters())) {
                DSAParameter dSAParameter = DSAParameter.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
                this.dsaSpec = new DSAParameterSpec(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
            } else {
                this.dsaSpec = null;
            }
            this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
        } catch (IOException unused) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
    }

    BCDSAPublicKey(DSAPublicKeyParameters dSAPublicKeyParameters) {
        this.y = dSAPublicKeyParameters.getY();
        this.dsaSpec = dSAPublicKeyParameters != null ? new DSAParameterSpec(dSAPublicKeyParameters.getParameters().getP(), dSAPublicKeyParameters.getParameters().getQ(), dSAPublicKeyParameters.getParameters().getG()) : null;
        this.lwKeyParams = dSAPublicKeyParameters;
    }

    private boolean isNotNull(ASN1Encodable aSN1Encodable) {
        return (aSN1Encodable == null || DERNull.INSTANCE.equals(aSN1Encodable.toASN1Primitive())) ? false : true;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        BigInteger bigInteger = (BigInteger) objectInputStream.readObject();
        if (bigInteger.equals(ZERO)) {
            this.dsaSpec = null;
        } else {
            this.dsaSpec = new DSAParameterSpec(bigInteger, (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
        }
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        BigInteger g;
        objectOutputStream.defaultWriteObject();
        DSAParams dSAParams = this.dsaSpec;
        if (dSAParams == null) {
            g = ZERO;
        } else {
            objectOutputStream.writeObject(dSAParams.getP());
            objectOutputStream.writeObject(this.dsaSpec.getQ());
            g = this.dsaSpec.getG();
        }
        objectOutputStream.writeObject(g);
    }

    DSAPublicKeyParameters engineGetKeyParameters() {
        return this.lwKeyParams;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPublicKey)) {
            return false;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey) obj;
        return this.dsaSpec != null ? getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() != null && getParams().getG().equals(dSAPublicKey.getParams().getG()) && getParams().getP().equals(dSAPublicKey.getParams().getP()) && getParams().getQ().equals(dSAPublicKey.getParams().getQ()) : getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() == null;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DSA";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        AlgorithmIdentifier algorithmIdentifier;
        ASN1Integer aSN1Integer;
        if (this.dsaSpec == null) {
            algorithmIdentifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa);
            aSN1Integer = new ASN1Integer(this.y);
        } else {
            algorithmIdentifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(this.dsaSpec.getP(), this.dsaSpec.getQ(), this.dsaSpec.getG()).toASN1Primitive());
            aSN1Integer = new ASN1Integer(this.y);
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(algorithmIdentifier, aSN1Integer);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // java.security.interfaces.DSAKey
    public DSAParams getParams() {
        return this.dsaSpec;
    }

    @Override // java.security.interfaces.DSAPublicKey
    public BigInteger getY() {
        return this.y;
    }

    public int hashCode() {
        return this.dsaSpec != null ? ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode() : getY().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DSA Public Key [");
        String strLineSeparator = Strings.lineSeparator();
        stringBuffer.append(DSAUtil.generateKeyFingerprint(this.y, getParams())).append("]").append(strLineSeparator);
        stringBuffer.append("            Y: ").append(getY().toString(16)).append(strLineSeparator);
        return stringBuffer.toString();
    }
}
