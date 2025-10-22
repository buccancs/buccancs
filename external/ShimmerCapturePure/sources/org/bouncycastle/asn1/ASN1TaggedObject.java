package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    boolean empty = false;
    boolean explicit;
    ASN1Encodable obj;
    int tagNo;

    public ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        this.explicit = true;
        this.obj = null;
        if (aSN1Encodable instanceof ASN1Choice) {
            this.explicit = true;
        } else {
            this.explicit = z;
        }
        this.tagNo = i;
        if (!this.explicit) {
            boolean z2 = aSN1Encodable.toASN1Primitive() instanceof ASN1Set;
        }
        this.obj = aSN1Encodable;
    }

    public static ASN1TaggedObject getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        try {
            return getInstance(fromByteArray((byte[]) obj));
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
        }
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return (ASN1TaggedObject) aSN1TaggedObject.getObject();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
        if (this.tagNo != aSN1TaggedObject.tagNo || this.empty != aSN1TaggedObject.empty || this.explicit != aSN1TaggedObject.explicit) {
            return false;
        }
        ASN1Encodable aSN1Encodable = this.obj;
        return aSN1Encodable == null ? aSN1TaggedObject.obj == null : aSN1Encodable.toASN1Primitive().equals(aSN1TaggedObject.obj.toASN1Primitive());
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    public ASN1Primitive getObject() {
        ASN1Encodable aSN1Encodable = this.obj;
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive();
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i, boolean z) throws IOException {
        if (i == 4) {
            return ASN1OctetString.getInstance(this, z).parser();
        }
        if (i == 16) {
            return ASN1Sequence.getInstance(this, z).parser();
        }
        if (i == 17) {
            return ASN1Set.getInstance(this, z).parser();
        }
        if (z) {
            return getObject();
        }
        throw new ASN1Exception("implicit tagging not implemented for tag: " + i);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.tagNo;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int i = this.tagNo;
        ASN1Encodable aSN1Encodable = this.obj;
        return aSN1Encodable != null ? i ^ aSN1Encodable.hashCode() : i;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return new DERTaggedObject(this.explicit, this.tagNo, this.obj);
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return new DLTaggedObject(this.explicit, this.tagNo, this.obj);
    }

    public String toString() {
        return "[" + this.tagNo + "]" + this.obj;
    }
}
