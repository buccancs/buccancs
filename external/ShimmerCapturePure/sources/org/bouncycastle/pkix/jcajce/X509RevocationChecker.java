package org.bouncycastle.pkix.jcajce;

import androidx.core.os.EnvironmentCompat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CRL;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Iterable;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/* loaded from: classes5.dex */
public class X509RevocationChecker extends PKIXCertPathChecker {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", EnvironmentCompat.MEDIA_UNKNOWN, "removeFromCRL", "privilegeWithdrawn", "aACompromise"};
    private static final Map<GeneralName, WeakReference<X509CRL>> crlCache = Collections.synchronizedMap(new WeakHashMap());
    private static Logger LOG = Logger.getLogger(X509RevocationChecker.class.getName());
    private final boolean canSoftFail;
    private final List<CertStore> crlCertStores;
    private final List<Store<CRL>> crls;
    private final long failHardMaxTime;
    private final long failLogMaxTime;
    private final Map<X500Principal, Long> failures;
    private final PKIXJcaJceHelper helper;
    private final boolean isCheckEEOnly;
    private final Set<TrustAnchor> trustAnchors;
    private X509Certificate signingCert;
    private X500Principal workingIssuerName;
    private PublicKey workingPublicKey;

    private X509RevocationChecker(Builder builder) {
        PKIXJcaJceHelper pKIXNamedJcaJceHelper;
        this.failures = new HashMap();
        this.crls = new ArrayList(builder.crls);
        this.crlCertStores = new ArrayList(builder.crlCertStores);
        this.isCheckEEOnly = builder.isCheckEEOnly;
        this.trustAnchors = builder.trustAnchors;
        this.canSoftFail = builder.canSoftFail;
        this.failLogMaxTime = builder.failLogMaxTime;
        this.failHardMaxTime = builder.failHardMaxTime;
        if (builder.provider != null) {
            pKIXNamedJcaJceHelper = new PKIXProviderJcaJceHelper(builder.provider);
        } else {
            if (builder.providerName == null) {
                this.helper = new PKIXDefaultJcaJceHelper();
                return;
            }
            pKIXNamedJcaJceHelper = new PKIXNamedJcaJceHelper(builder.providerName);
        }
        this.helper = pKIXNamedJcaJceHelper;
    }

    static List<PKIXCRLStore> getAdditionalStoresFromCRLDistributionPoint(CRLDistPoint cRLDistPoint, Map<GeneralName, PKIXCRLStore> map) throws AnnotatedException {
        if (cRLDistPoint == null) {
            return Collections.EMPTY_LIST;
        }
        try {
            DistributionPoint[] distributionPoints = cRLDistPoint.getDistributionPoints();
            ArrayList arrayList = new ArrayList();
            for (DistributionPoint distributionPoint : distributionPoints) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2 != null && distributionPoint2.getType() == 0) {
                    for (GeneralName generalName : GeneralNames.getInstance(distributionPoint2.getName()).getNames()) {
                        PKIXCRLStore pKIXCRLStore = map.get(generalName);
                        if (pKIXCRLStore != null) {
                            arrayList.add(pKIXCRLStore);
                        }
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            throw new AnnotatedException("could not read distribution points could not be read", e);
        }
    }

    private void addIssuers(final List<X500Principal> list, CertStore certStore) throws CertStoreException {
        certStore.getCRLs(new X509CRLSelector() { // from class: org.bouncycastle.pkix.jcajce.X509RevocationChecker.1
            @Override // java.security.cert.X509CRLSelector, java.security.cert.CRLSelector
            public boolean match(CRL crl) {
                if (!(crl instanceof X509CRL)) {
                    return false;
                }
                list.add(((X509CRL) crl).getIssuerX500Principal());
                return false;
            }
        });
    }

    private void addIssuers(final List<X500Principal> list, Store<CRL> store) throws StoreException {
        store.getMatches(new Selector<CRL>() { // from class: org.bouncycastle.pkix.jcajce.X509RevocationChecker.2
            @Override // org.bouncycastle.util.Selector
            public Object clone() {
                return this;
            }

            @Override // org.bouncycastle.util.Selector
            public boolean match(CRL crl) {
                if (!(crl instanceof X509CRL)) {
                    return false;
                }
                list.add(((X509CRL) crl).getIssuerX500Principal());
                return false;
            }
        });
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    private CRL downloadCRLs(X500Principal x500Principal, Date date, ASN1Primitive aSN1Primitive, JcaJceHelper jcaJceHelper) throws IOException {
        URL url;
        X509CRL x509crl;
        Logger logger;
        Level level;
        StringBuilder sb;
        DistributionPoint[] distributionPoints = CRLDistPoint.getInstance(aSN1Primitive).getDistributionPoints();
        for (int i = 0; i != distributionPoints.length; i++) {
            DistributionPointName distributionPoint = distributionPoints[i].getDistributionPoint();
            if (distributionPoint.getType() == 0) {
                GeneralName[] names = GeneralNames.getInstance(distributionPoint.getName()).getNames();
                for (int i2 = 0; i2 != names.length; i2++) {
                    GeneralName generalName = names[i2];
                    if (generalName.getTagNo() == 6) {
                        Map<GeneralName, WeakReference<X509CRL>> map = crlCache;
                        WeakReference<X509CRL> weakReference = map.get(generalName);
                        if (weakReference != null) {
                            X509CRL x509crl2 = weakReference.get();
                            if (x509crl2 != null && !date.before(x509crl2.getThisUpdate()) && !date.after(x509crl2.getNextUpdate())) {
                                return x509crl2;
                            }
                            map.remove(generalName);
                        }
                        try {
                            url = new URL(generalName.getName().toString());
                            try {
                                CertificateFactory certificateFactoryCreateCertificateFactory = jcaJceHelper.createCertificateFactory("X.509");
                                InputStream inputStreamOpenStream = url.openStream();
                                x509crl = (X509CRL) certificateFactoryCreateCertificateFactory.generateCRL(new BufferedInputStream(inputStreamOpenStream));
                                inputStreamOpenStream.close();
                                logger = LOG;
                                level = Level.INFO;
                                sb = new StringBuilder();
                                sb.append("downloaded CRL from CrlDP ");
                                sb.append(url);
                                sb.append(" for issuer \"");
                            } catch (Exception e) {
                                e = e;
                            }
                            try {
                                sb.append(x500Principal);
                                sb.append("\"");
                                logger.log(level, sb.toString());
                                map.put(generalName, new WeakReference<>(x509crl));
                                return x509crl;
                            } catch (Exception e2) {
                                e = e2;
                                if (LOG.isLoggable(Level.FINE)) {
                                    LOG.log(Level.FINE, "CrlDP " + url + " ignored: " + e.getMessage(), (Throwable) e);
                                } else {
                                    LOG.log(Level.INFO, "CrlDP " + url + " ignored: " + e.getMessage());
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            url = null;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public void check(Certificate certificate, Collection<String> collection) throws IOException, CertPathValidatorException, StoreException {
        Logger logger;
        Level level;
        StringBuilder sb;
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (this.isCheckEEOnly && x509Certificate.getBasicConstraints() != -1) {
            this.workingIssuerName = x509Certificate.getSubjectX500Principal();
            this.workingPublicKey = x509Certificate.getPublicKey();
            this.signingCert = x509Certificate;
            return;
        }
        if (this.workingIssuerName == null) {
            this.workingIssuerName = x509Certificate.getIssuerX500Principal();
            TrustAnchor trustAnchor = null;
            for (TrustAnchor trustAnchor2 : this.trustAnchors) {
                if (this.workingIssuerName.equals(trustAnchor2.getCA()) || this.workingIssuerName.equals(trustAnchor2.getTrustedCert().getSubjectX500Principal())) {
                    trustAnchor = trustAnchor2;
                }
            }
            if (trustAnchor == null) {
                throw new CertPathValidatorException("no trust anchor found for " + this.workingIssuerName);
            }
            X509Certificate trustedCert = trustAnchor.getTrustedCert();
            this.signingCert = trustedCert;
            this.workingPublicKey = trustedCert.getPublicKey();
        }
        ArrayList arrayList = new ArrayList();
        try {
            PKIXParameters pKIXParameters = new PKIXParameters(this.trustAnchors);
            pKIXParameters.setRevocationEnabled(false);
            pKIXParameters.setDate(new Date());
            for (int i = 0; i != this.crlCertStores.size(); i++) {
                if (LOG.isLoggable(Level.INFO)) {
                    addIssuers(arrayList, this.crlCertStores.get(i));
                }
                pKIXParameters.addCertStore(this.crlCertStores.get(i));
            }
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXParameters);
            for (int i2 = 0; i2 != this.crls.size(); i2++) {
                if (LOG.isLoggable(Level.INFO)) {
                    addIssuers(arrayList, this.crls.get(i2));
                }
                builder.addCRLStore(new LocalCRLStore(this.crls.get(i2)));
            }
            if (arrayList.isEmpty()) {
                LOG.log(Level.INFO, "configured with 0 pre-loaded CRLs");
            } else if (LOG.isLoggable(Level.FINE)) {
                for (int i3 = 0; i3 != arrayList.size(); i3++) {
                    LOG.log(Level.FINE, "configuring with CRL for issuer \"" + arrayList.get(i3) + "\"");
                }
            } else {
                LOG.log(Level.INFO, "configured with " + arrayList.size() + " pre-loaded CRLs");
            }
            try {
                checkCRLs(builder.build(), x509Certificate, pKIXParameters.getDate(), this.signingCert, this.workingPublicKey, new ArrayList(), this.helper);
            } catch (AnnotatedException e) {
                throw new CertPathValidatorException(e.getMessage(), e.getCause());
            } catch (CRLNotFoundException e2) {
                if (x509Certificate.getExtensionValue(Extension.cRLDistributionPoints.getId()) == null) {
                    throw e2;
                }
                try {
                    CRL crlDownloadCRLs = downloadCRLs(x509Certificate.getIssuerX500Principal(), pKIXParameters.getDate(), RevocationUtilities.getExtensionValue(x509Certificate, Extension.cRLDistributionPoints), this.helper);
                    if (crlDownloadCRLs != null) {
                        try {
                            builder.addCRLStore(new LocalCRLStore(new CollectionStore(Collections.singleton(crlDownloadCRLs))));
                            checkCRLs(builder.build(), x509Certificate, new Date(), this.signingCert, this.workingPublicKey, new ArrayList(), this.helper);
                        } catch (AnnotatedException unused) {
                            throw new CertPathValidatorException(e2.getMessage(), e2.getCause());
                        }
                    } else {
                        if (!this.canSoftFail) {
                            throw e2;
                        }
                        X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
                        Long l = this.failures.get(issuerX500Principal);
                        if (l != null) {
                            long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
                            long j = this.failHardMaxTime;
                            if (j != -1 && j < jCurrentTimeMillis) {
                                throw e2;
                            }
                            if (jCurrentTimeMillis < this.failLogMaxTime) {
                                logger = LOG;
                                level = Level.WARNING;
                                sb = new StringBuilder("soft failing for issuer: \"");
                            } else {
                                logger = LOG;
                                level = Level.SEVERE;
                                sb = new StringBuilder("soft failing for issuer: \"");
                            }
                            sb.append(issuerX500Principal);
                            sb.append("\"");
                            logger.log(level, sb.toString());
                        } else {
                            this.failures.put(issuerX500Principal, Long.valueOf(System.currentTimeMillis()));
                        }
                    }
                } catch (AnnotatedException unused2) {
                    throw new CertPathValidatorException(e2.getMessage(), e2.getCause());
                }
            }
            this.signingCert = x509Certificate;
            this.workingPublicKey = x509Certificate.getPublicKey();
            this.workingIssuerName = x509Certificate.getSubjectX500Principal();
        } catch (GeneralSecurityException e3) {
            throw new RuntimeException("error setting up baseParams: " + e3.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void checkCRLs(org.bouncycastle.jcajce.PKIXExtendedParameters r21, java.security.cert.X509Certificate r22, java.util.Date r23, java.security.cert.X509Certificate r24, java.security.PublicKey r25, java.util.List r26, org.bouncycastle.pkix.jcajce.PKIXJcaJceHelper r27) throws org.bouncycastle.pkix.jcajce.AnnotatedException, java.security.cert.CertPathValidatorException {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.X509RevocationChecker.checkCRLs(org.bouncycastle.jcajce.PKIXExtendedParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.List, org.bouncycastle.pkix.jcajce.PKIXJcaJceHelper):void");
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public Object clone() {
        return this;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public void init(boolean z) throws CertPathValidatorException {
        if (z) {
            throw new IllegalArgumentException("forward processing not supported");
        }
        this.workingIssuerName = null;
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public boolean isForwardCheckingSupported() {
        return false;
    }

    public static class Builder {
        private boolean canSoftFail;
        private List<CertStore> crlCertStores;
        private List<Store<CRL>> crls;
        private long failHardMaxTime;
        private long failLogMaxTime;
        private boolean isCheckEEOnly;
        private Provider provider;
        private String providerName;
        private Set<TrustAnchor> trustAnchors;
        private int validityModel;

        public Builder(KeyStore keyStore) throws KeyStoreException {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.trustAnchors = new HashSet();
            Enumeration<String> enumerationAliases = keyStore.aliases();
            while (enumerationAliases.hasMoreElements()) {
                String strNextElement = enumerationAliases.nextElement();
                if (keyStore.isCertificateEntry(strNextElement)) {
                    this.trustAnchors.add(new TrustAnchor((X509Certificate) keyStore.getCertificate(strNextElement), null));
                }
            }
        }

        public Builder(TrustAnchor trustAnchor) {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.trustAnchors = Collections.singleton(trustAnchor);
        }

        public Builder(Set<TrustAnchor> set) {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.trustAnchors = new HashSet(set);
        }

        public Builder addCrls(CertStore certStore) {
            this.crlCertStores.add(certStore);
            return this;
        }

        public Builder addCrls(Store<CRL> store) {
            this.crls.add(store);
            return this;
        }

        public X509RevocationChecker build() {
            return new X509RevocationChecker(this);
        }

        public Builder setCheckEndEntityOnly(boolean z) {
            this.isCheckEEOnly = z;
            return this;
        }

        public Builder setSoftFail(boolean z, long j) {
            this.canSoftFail = z;
            this.failLogMaxTime = j;
            this.failHardMaxTime = -1L;
            return this;
        }

        public Builder setSoftFailHardLimit(boolean z, long j) {
            this.canSoftFail = z;
            this.failLogMaxTime = (3 * j) / 4;
            this.failHardMaxTime = j;
            return this;
        }

        public Builder usingProvider(String str) {
            this.providerName = str;
            return this;
        }

        public Builder usingProvider(Provider provider) {
            this.provider = provider;
            return this;
        }
    }

    private class LocalCRLStore<T extends CRL> implements PKIXCRLStore, Iterable<CRL> {
        private Collection<CRL> _local;

        public LocalCRLStore(Store<CRL> store) {
            this._local = new ArrayList(store.getMatches(null));
        }

        @Override // org.bouncycastle.jcajce.PKIXCRLStore, org.bouncycastle.util.Store
        public Collection getMatches(Selector selector) {
            if (selector == null) {
                return new ArrayList(this._local);
            }
            ArrayList arrayList = new ArrayList();
            for (CRL crl : this._local) {
                if (selector.match(crl)) {
                    arrayList.add(crl);
                }
            }
            return arrayList;
        }

        @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
        public Iterator<CRL> iterator() {
            return getMatches(null).iterator();
        }
    }
}
