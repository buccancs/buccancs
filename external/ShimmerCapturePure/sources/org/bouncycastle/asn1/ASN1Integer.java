package org.bouncycastle.asn1;

import java.io.IOException;
import java.math.BigInteger;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

/* loaded from: classes5.dex */
public class ASN1Integer extends ASN1Primitive {
    private final byte[] bytes;

    public ASN1Integer(long j) {
        this.bytes = BigInteger.valueOf(j).toByteArray();
    }

    public ASN1Integer(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public ASN1Integer(byte[] bArr) {
        this(bArr, true);
    }

    ASN1Integer(byte[] bArr, boolean z) {
        if (!Properties.isOverrideSet("org.bouncycastle.asn1.allow_unsafe_integer") && isMalformed(bArr)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.bytes = z ? Arrays.clone(bArr) : bArr;
    }

    public static ASN1Integer getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Integer)) {
            return (ASN1Integer) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1Integer) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static ASN1Integer getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof ASN1Integer)) ? getInstance(object) : new ASN1Integer(ASN1OctetString.getInstance(object).getOctets());
    }

    static boolean isMalformed(byte[] bArr) {
        if (bArr.length > 1) {
            byte b = bArr[0];
            if (b == 0 && (bArr[1] & 128) == 0) {
                return true;
            }
            if (b == -1 && (bArr[1] & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Integer) {
            return Arrays.areEqual(this.bytes, ((ASN1Integer) aSN1Primitive).bytes);
        }
        return false;
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(2, this.bytes);
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength() {
        return StreamUtil.calculateBodyLength(this.bytes.length) + 1 + this.bytes.length;
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i == bArr.length) {
                return i2;
            }
            i2 ^= (bArr[i] & 255) << (i % 4);
            i++;
        }
    }

    @Override
        // org.bouncycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    public String toString() {
        return getValue().toString();
    }
}
