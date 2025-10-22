package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.AuthEnvelopedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes5.dex */
class CMSAuthEnvelopedData {
    ContentInfo contentInfo;
    RecipientInformationStore recipientInfoStore;
    private ASN1Set authAttrs;
    private AlgorithmIdentifier authEncAlg;
    private byte[] mac;
    private OriginatorInfo originator;
    private ASN1Set unauthAttrs;

    public CMSAuthEnvelopedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSAuthEnvelopedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        AuthEnvelopedData authEnvelopedData = AuthEnvelopedData.getInstance(contentInfo.getContent());
        this.originator = authEnvelopedData.getOriginatorInfo();
        ASN1Set recipientInfos = authEnvelopedData.getRecipientInfos();
        this.authEncAlg = authEnvelopedData.getAuthEncryptedContentInfo().getContentEncryptionAlgorithm();
        this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(recipientInfos, this.authEncAlg, new CMSSecureReadable() { // from class: org.bouncycastle.cms.CMSAuthEnvelopedData.1
            @Override // org.bouncycastle.cms.CMSSecureReadable
            public InputStream getInputStream() throws CMSException, IOException {
                return null;
            }
        });
        this.authAttrs = authEnvelopedData.getAuthAttrs();
        this.mac = authEnvelopedData.getMac().getOctets();
        this.unauthAttrs = authEnvelopedData.getUnauthAttrs();
    }

    public CMSAuthEnvelopedData(byte[] bArr) throws CMSException {
        this(CMSUtils.readContentInfo(bArr));
    }
}
