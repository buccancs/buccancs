package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes5.dex */
public interface X509AttributeIdentifiers {
    public static final ASN1ObjectIdentifier id_aca;
    public static final ASN1ObjectIdentifier id_aca_accessIdentity;
    public static final ASN1ObjectIdentifier id_aca_authenticationInfo;
    public static final ASN1ObjectIdentifier id_aca_chargingIdentity;
    public static final ASN1ObjectIdentifier id_aca_encAttrs;
    public static final ASN1ObjectIdentifier id_aca_group;
    public static final ASN1ObjectIdentifier id_at_clearance;
    public static final ASN1ObjectIdentifier id_at_role;
    public static final ASN1ObjectIdentifier RoleSyntax = new ASN1ObjectIdentifier("2.5.4.72");
    public static final ASN1ObjectIdentifier id_pe_ac_auditIdentity = X509ObjectIdentifiers.id_pe.branch("4");
    public static final ASN1ObjectIdentifier id_pe_aaControls = X509ObjectIdentifiers.id_pe.branch("6");
    public static final ASN1ObjectIdentifier id_pe_ac_proxying = X509ObjectIdentifiers.id_pe.branch("10");
    public static final ASN1ObjectIdentifier id_ce_targetInformation = X509ObjectIdentifiers.id_ce.branch("55");

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = X509ObjectIdentifiers.id_pkix.branch("10");
        id_aca = aSN1ObjectIdentifierBranch;
        id_aca_authenticationInfo = aSN1ObjectIdentifierBranch.branch("1");
        id_aca_accessIdentity = aSN1ObjectIdentifierBranch.branch("2");
        id_aca_chargingIdentity = aSN1ObjectIdentifierBranch.branch("3");
        id_aca_group = aSN1ObjectIdentifierBranch.branch("4");
        id_aca_encAttrs = aSN1ObjectIdentifierBranch.branch("6");
        id_at_role = new ASN1ObjectIdentifier("2.5.4.72");
        id_at_clearance = new ASN1ObjectIdentifier("2.5.1.5.55");
    }
}
