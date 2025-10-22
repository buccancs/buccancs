package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

/* loaded from: classes5.dex */
class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] bArr) throws IOException {
        this.encoded = bArr;
    }

    private void parse() {
        LazyConstructionEnumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.encoded);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.seq.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.encoded = null;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            aSN1OutputStream.writeEncoded(48, bArr);
        } else {
            super.toDLObject().encode(aSN1OutputStream);
        }
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength() throws IOException {
        byte[] bArr = this.encoded;
        return bArr != null ? StreamUtil.calculateBodyLength(bArr.length) + 1 + this.encoded.length : super.toDLObject().encodedLength();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized ASN1Encodable getObjectAt(int i) {
        if (this.encoded != null) {
            parse();
        }
        return super.getObjectAt(i);
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized Enumeration getObjects() {
        byte[] bArr = this.encoded;
        if (bArr == null) {
            return super.getObjects();
        }
        return new LazyConstructionEnumeration(bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized int size() {
        if (this.encoded != null) {
            parse();
        }
        return super.size();
    }

    @Override
        // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDERObject();
    }

    @Override
        // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDLObject();
    }
}
