package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class DEROutputStream extends ASN1OutputStream {
    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
        // org.bouncycastle.asn1.ASN1OutputStream
    ASN1OutputStream getDERSubStream() {
        return this;
    }

    @Override
        // org.bouncycastle.asn1.ASN1OutputStream
    ASN1OutputStream getDLSubStream() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    public void writeObject(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable == null) {
            throw new IOException("null object detected");
        }
        aSN1Encodable.toASN1Primitive().toDERObject().encode(this);
    }
}
