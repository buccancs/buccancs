package org.bouncycastle.cms;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes5.dex */
class CMSEnvelopedHelper {

    CMSEnvelopedHelper() {
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable) {
        return buildRecipientInformationStore(aSN1Set, algorithmIdentifier, cMSSecureReadable, null);
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != aSN1Set.size(); i++) {
            readRecipientInfo(arrayList, RecipientInfo.getInstance(aSN1Set.getObjectAt(i)), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        }
        return new RecipientInformationStore(arrayList);
    }

    private static void readRecipientInfo(List list, RecipientInfo recipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        Object passwordRecipientInformation;
        ASN1Encodable info = recipientInfo.getInfo();
        if (info instanceof KeyTransRecipientInfo) {
            passwordRecipientInformation = new KeyTransRecipientInformation((KeyTransRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        } else if (info instanceof KEKRecipientInfo) {
            passwordRecipientInformation = new KEKRecipientInformation((KEKRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        } else if (info instanceof KeyAgreeRecipientInfo) {
            KeyAgreeRecipientInformation.readRecipientInfo(list, (KeyAgreeRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
            return;
        } else if (!(info instanceof PasswordRecipientInfo)) {
            return;
        } else {
            passwordRecipientInformation = new PasswordRecipientInformation((PasswordRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        }
        list.add(passwordRecipientInformation);
    }

    static class CMSAuthenticatedSecureReadable implements CMSSecureReadable {
        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSAuthenticatedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        @Override // org.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws CMSException, IOException {
            return this.readable.getInputStream();
        }
    }

    static class CMSDigestAuthenticatedSecureReadable implements CMSSecureReadable {
        private DigestCalculator digestCalculator;
        private CMSReadable readable;

        public CMSDigestAuthenticatedSecureReadable(DigestCalculator digestCalculator, CMSReadable cMSReadable) {
            this.digestCalculator = digestCalculator;
            this.readable = cMSReadable;
        }

        public byte[] getDigest() {
            return this.digestCalculator.getDigest();
        }

        @Override // org.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws CMSException, IOException {
            return new FilterInputStream(this.readable.getInputStream()) { // from class: org.bouncycastle.cms.CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable.1
                @Override // java.io.FilterInputStream, java.io.InputStream
                public int read() throws IOException {
                    int i = this.in.read();
                    if (i >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(i);
                    }
                    return i;
                }

                @Override // java.io.FilterInputStream, java.io.InputStream
                public int read(byte[] bArr, int i, int i2) throws IOException {
                    int i3 = this.in.read(bArr, i, i2);
                    if (i3 >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(bArr, i, i3);
                    }
                    return i3;
                }
            };
        }
    }

    static class CMSEnvelopedSecureReadable implements CMSSecureReadable {
        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSEnvelopedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        @Override // org.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws CMSException, IOException {
            return this.readable.getInputStream();
        }
    }
}
