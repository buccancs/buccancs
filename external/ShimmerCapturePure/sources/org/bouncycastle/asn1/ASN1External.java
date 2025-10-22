package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class ASN1External extends ASN1Primitive {
    protected ASN1Primitive dataValueDescriptor;
    protected ASN1ObjectIdentifier directReference;
    protected int encoding;
    protected ASN1Primitive externalContent;
    protected ASN1Integer indirectReference;

    public ASN1External(ASN1EncodableVector aSN1EncodableVector) {
        int i = 0;
        ASN1Primitive objFromVector = getObjFromVector(aSN1EncodableVector, 0);
        if (objFromVector instanceof ASN1ObjectIdentifier) {
            this.directReference = (ASN1ObjectIdentifier) objFromVector;
            objFromVector = getObjFromVector(aSN1EncodableVector, 1);
            i = 1;
        }
        if (objFromVector instanceof ASN1Integer) {
            this.indirectReference = (ASN1Integer) objFromVector;
            i++;
            objFromVector = getObjFromVector(aSN1EncodableVector, i);
        }
        if (!(objFromVector instanceof ASN1TaggedObject)) {
            this.dataValueDescriptor = objFromVector;
            i++;
            objFromVector = getObjFromVector(aSN1EncodableVector, i);
        }
        if (aSN1EncodableVector.size() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        }
        if (!(objFromVector instanceof ASN1TaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objFromVector;
        setEncoding(aSN1TaggedObject.getTagNo());
        this.externalContent = aSN1TaggedObject.getObject();
    }

    public ASN1External(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, int i, ASN1Primitive aSN1Primitive2) {
        setDirectReference(aSN1ObjectIdentifier);
        setIndirectReference(aSN1Integer);
        setDataValueDescriptor(aSN1Primitive);
        setEncoding(i);
        setExternalContent(aSN1Primitive2.toASN1Primitive());
    }

    public ASN1External(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, DERTaggedObject dERTaggedObject) {
        this(aSN1ObjectIdentifier, aSN1Integer, aSN1Primitive, dERTaggedObject.getTagNo(), dERTaggedObject.toASN1Primitive());
    }

    private ASN1Primitive getObjFromVector(ASN1EncodableVector aSN1EncodableVector, int i) {
        if (aSN1EncodableVector.size() > i) {
            return aSN1EncodableVector.get(i).toASN1Primitive();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        ASN1Primitive aSN1Primitive2;
        ASN1Integer aSN1Integer;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        if (!(aSN1Primitive instanceof ASN1External)) {
            return false;
        }
        if (this == aSN1Primitive) {
            return true;
        }
        ASN1External aSN1External = (ASN1External) aSN1Primitive;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = this.directReference;
        if (aSN1ObjectIdentifier2 != null && ((aSN1ObjectIdentifier = aSN1External.directReference) == null || !aSN1ObjectIdentifier.equals(aSN1ObjectIdentifier2))) {
            return false;
        }
        ASN1Integer aSN1Integer2 = this.indirectReference;
        if (aSN1Integer2 != null && ((aSN1Integer = aSN1External.indirectReference) == null || !aSN1Integer.equals(aSN1Integer2))) {
            return false;
        }
        ASN1Primitive aSN1Primitive3 = this.dataValueDescriptor;
        if (aSN1Primitive3 == null || ((aSN1Primitive2 = aSN1External.dataValueDescriptor) != null && aSN1Primitive2.equals(aSN1Primitive3))) {
            return this.externalContent.equals(aSN1External.externalContent);
        }
        return false;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength() throws IOException {
        return getEncoded().length;
    }

    public ASN1Primitive getDataValueDescriptor() {
        return this.dataValueDescriptor;
    }

    private void setDataValueDescriptor(ASN1Primitive aSN1Primitive) {
        this.dataValueDescriptor = aSN1Primitive;
    }

    public ASN1ObjectIdentifier getDirectReference() {
        return this.directReference;
    }

    private void setDirectReference(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.directReference = aSN1ObjectIdentifier;
    }

    public int getEncoding() {
        return this.encoding;
    }

    private void setEncoding(int i) {
        if (i >= 0 && i <= 2) {
            this.encoding = i;
        } else {
            throw new IllegalArgumentException("invalid encoding value: " + i);
        }
    }

    public ASN1Primitive getExternalContent() {
        return this.externalContent;
    }

    private void setExternalContent(ASN1Primitive aSN1Primitive) {
        this.externalContent = aSN1Primitive;
    }

    public ASN1Integer getIndirectReference() {
        return this.indirectReference;
    }

    private void setIndirectReference(ASN1Integer aSN1Integer) {
        this.indirectReference = aSN1Integer;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.directReference;
        int iHashCode = aSN1ObjectIdentifier != null ? aSN1ObjectIdentifier.hashCode() : 0;
        ASN1Integer aSN1Integer = this.indirectReference;
        if (aSN1Integer != null) {
            iHashCode ^= aSN1Integer.hashCode();
        }
        ASN1Primitive aSN1Primitive = this.dataValueDescriptor;
        if (aSN1Primitive != null) {
            iHashCode ^= aSN1Primitive.hashCode();
        }
        return iHashCode ^ this.externalContent.hashCode();
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return true;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return this instanceof DERExternal ? this : new DERExternal(this.directReference, this.indirectReference, this.dataValueDescriptor, this.encoding, this.externalContent);
    }
}
