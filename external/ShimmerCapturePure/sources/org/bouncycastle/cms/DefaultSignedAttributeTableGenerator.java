package org.bouncycastle.cms;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes5.dex */
public class DefaultSignedAttributeTableGenerator implements CMSAttributeTableGenerator {
    private final Hashtable table;

    public DefaultSignedAttributeTableGenerator() {
        this.table = new Hashtable();
    }

    public DefaultSignedAttributeTableGenerator(AttributeTable attributeTable) {
        this.table = attributeTable != null ? attributeTable.toHashtable() : new Hashtable();
    }

    private static Hashtable copyHashTable(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration enumerationKeys = hashtable.keys();
        while (enumerationKeys.hasMoreElements()) {
            Object objNextElement = enumerationKeys.nextElement();
            hashtable2.put(objNextElement, hashtable.get(objNextElement));
        }
        return hashtable2;
    }

    protected Hashtable createStandardAttributeTable(Map map) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        Hashtable hashtableCopyHashTable = copyHashTable(this.table);
        if (!hashtableCopyHashTable.containsKey(CMSAttributes.contentType) && (aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(map.get(CMSAttributeTableGenerator.CONTENT_TYPE))) != null) {
            Attribute attribute = new Attribute(CMSAttributes.contentType, new DERSet(aSN1ObjectIdentifier));
            hashtableCopyHashTable.put(attribute.getAttrType(), attribute);
        }
        if (!hashtableCopyHashTable.containsKey(CMSAttributes.signingTime)) {
            Attribute attribute2 = new Attribute(CMSAttributes.signingTime, new DERSet(new Time(new Date())));
            hashtableCopyHashTable.put(attribute2.getAttrType(), attribute2);
        }
        if (!hashtableCopyHashTable.containsKey(CMSAttributes.messageDigest)) {
            Attribute attribute3 = new Attribute(CMSAttributes.messageDigest, new DERSet(new DEROctetString((byte[]) map.get(CMSAttributeTableGenerator.DIGEST))));
            hashtableCopyHashTable.put(attribute3.getAttrType(), attribute3);
        }
        if (!hashtableCopyHashTable.contains(CMSAttributes.cmsAlgorithmProtect)) {
            Attribute attribute4 = new Attribute(CMSAttributes.cmsAlgorithmProtect, new DERSet(new CMSAlgorithmProtection((AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER), 1, (AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.SIGNATURE_ALGORITHM_IDENTIFIER))));
            hashtableCopyHashTable.put(attribute4.getAttrType(), attribute4);
        }
        return hashtableCopyHashTable;
    }

    @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return new AttributeTable(createStandardAttributeTable(map));
    }
}
