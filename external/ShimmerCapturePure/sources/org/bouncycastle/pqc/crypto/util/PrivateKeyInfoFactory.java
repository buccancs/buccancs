package org.bouncycastle.pqc.crypto.util;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.asn1.XMSSKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTKeyParams;
import org.bouncycastle.pqc.asn1.XMSSMTPrivateKey;
import org.bouncycastle.pqc.asn1.XMSSPrivateKey;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.qtesla.QTESLAPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class PrivateKeyInfoFactory {
    private PrivateKeyInfoFactory() {
    }

    public static PrivateKeyInfo createPrivateKeyInfo(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        return createPrivateKeyInfo(asymmetricKeyParameter, null);
    }

    public static PrivateKeyInfo createPrivateKeyInfo(AsymmetricKeyParameter asymmetricKeyParameter, ASN1Set aSN1Set) throws IOException {
        if (asymmetricKeyParameter instanceof QTESLAPrivateKeyParameters) {
            QTESLAPrivateKeyParameters qTESLAPrivateKeyParameters = (QTESLAPrivateKeyParameters) asymmetricKeyParameter;
            return new PrivateKeyInfo(Utils.qTeslaLookupAlgID(qTESLAPrivateKeyParameters.getSecurityCategory()), new DEROctetString(qTESLAPrivateKeyParameters.getSecret()), aSN1Set);
        }
        if (asymmetricKeyParameter instanceof SPHINCSPrivateKeyParameters) {
            SPHINCSPrivateKeyParameters sPHINCSPrivateKeyParameters = (SPHINCSPrivateKeyParameters) asymmetricKeyParameter;
            return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.sphincs256, new SPHINCS256KeyParams(Utils.sphincs256LookupTreeAlgID(sPHINCSPrivateKeyParameters.getTreeDigest()))), new DEROctetString(sPHINCSPrivateKeyParameters.getKeyData()));
        }
        if (!(asymmetricKeyParameter instanceof NHPrivateKeyParameters)) {
            if (asymmetricKeyParameter instanceof XMSSPrivateKeyParameters) {
                XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters) asymmetricKeyParameter;
                return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.xmss, new XMSSKeyParams(xMSSPrivateKeyParameters.getParameters().getHeight(), Utils.xmssLookupTreeAlgID(xMSSPrivateKeyParameters.getTreeDigest()))), xmssCreateKeyStructure(xMSSPrivateKeyParameters));
            }
            if (!(asymmetricKeyParameter instanceof XMSSMTPrivateKeyParameters)) {
                throw new IOException("key parameters not recognized");
            }
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = (XMSSMTPrivateKeyParameters) asymmetricKeyParameter;
            return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(xMSSMTPrivateKeyParameters.getParameters().getHeight(), xMSSMTPrivateKeyParameters.getParameters().getLayers(), Utils.xmssLookupTreeAlgID(xMSSMTPrivateKeyParameters.getTreeDigest()))), xmssmtCreateKeyStructure(xMSSMTPrivateKeyParameters));
        }
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.newHope);
        short[] secData = ((NHPrivateKeyParameters) asymmetricKeyParameter).getSecData();
        byte[] bArr = new byte[secData.length * 2];
        for (int i = 0; i != secData.length; i++) {
            Pack.shortToLittleEndian(secData[i], bArr, i * 2);
        }
        return new PrivateKeyInfo(algorithmIdentifier, new DEROctetString(bArr));
    }

    private static XMSSPrivateKey xmssCreateKeyStructure(XMSSPrivateKeyParameters xMSSPrivateKeyParameters) {
        byte[] byteArray = xMSSPrivateKeyParameters.toByteArray();
        int digestSize = xMSSPrivateKeyParameters.getParameters().getDigestSize();
        int height = xMSSPrivateKeyParameters.getParameters().getHeight();
        int iBytesToXBigEndian = (int) XMSSUtil.bytesToXBigEndian(byteArray, 0, 4);
        if (!XMSSUtil.isIndexValid(height, iBytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        byte[] bArrExtractBytesAtOffset = XMSSUtil.extractBytesAtOffset(byteArray, 4, digestSize);
        int i = 4 + digestSize;
        byte[] bArrExtractBytesAtOffset2 = XMSSUtil.extractBytesAtOffset(byteArray, i, digestSize);
        int i2 = i + digestSize;
        byte[] bArrExtractBytesAtOffset3 = XMSSUtil.extractBytesAtOffset(byteArray, i2, digestSize);
        int i3 = i2 + digestSize;
        byte[] bArrExtractBytesAtOffset4 = XMSSUtil.extractBytesAtOffset(byteArray, i3, digestSize);
        int i4 = i3 + digestSize;
        return new XMSSPrivateKey(iBytesToXBigEndian, bArrExtractBytesAtOffset, bArrExtractBytesAtOffset2, bArrExtractBytesAtOffset3, bArrExtractBytesAtOffset4, XMSSUtil.extractBytesAtOffset(byteArray, i4, byteArray.length - i4));
    }

    private static XMSSMTPrivateKey xmssmtCreateKeyStructure(XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters) {
        byte[] byteArray = xMSSMTPrivateKeyParameters.toByteArray();
        int digestSize = xMSSMTPrivateKeyParameters.getParameters().getDigestSize();
        int height = xMSSMTPrivateKeyParameters.getParameters().getHeight();
        int i = (height + 7) / 8;
        int iBytesToXBigEndian = (int) XMSSUtil.bytesToXBigEndian(byteArray, 0, i);
        if (!XMSSUtil.isIndexValid(height, iBytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        byte[] bArrExtractBytesAtOffset = XMSSUtil.extractBytesAtOffset(byteArray, i, digestSize);
        int i2 = i + digestSize;
        byte[] bArrExtractBytesAtOffset2 = XMSSUtil.extractBytesAtOffset(byteArray, i2, digestSize);
        int i3 = i2 + digestSize;
        byte[] bArrExtractBytesAtOffset3 = XMSSUtil.extractBytesAtOffset(byteArray, i3, digestSize);
        int i4 = i3 + digestSize;
        byte[] bArrExtractBytesAtOffset4 = XMSSUtil.extractBytesAtOffset(byteArray, i4, digestSize);
        int i5 = i4 + digestSize;
        return new XMSSMTPrivateKey(iBytesToXBigEndian, bArrExtractBytesAtOffset, bArrExtractBytesAtOffset2, bArrExtractBytesAtOffset3, bArrExtractBytesAtOffset4, XMSSUtil.extractBytesAtOffset(byteArray, i5, byteArray.length - i5));
    }
}
