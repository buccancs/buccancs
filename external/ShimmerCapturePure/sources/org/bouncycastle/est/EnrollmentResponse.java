package org.bouncycastle.est;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;

/* loaded from: classes5.dex */
public class EnrollmentResponse {
    private final long notBefore;
    private final ESTRequest requestToRetry;
    private final Source source;
    private final Store<X509CertificateHolder> store;

    public EnrollmentResponse(Store<X509CertificateHolder> store, long j, ESTRequest eSTRequest, Source source) {
        this.store = store;
        this.notBefore = j;
        this.requestToRetry = eSTRequest;
        this.source = source;
    }

    public boolean canRetry() {
        return this.notBefore < System.currentTimeMillis();
    }

    public long getNotBefore() {
        return this.notBefore;
    }

    public ESTRequest getRequestToRetry() {
        return this.requestToRetry;
    }

    public Object getSession() {
        return this.source.getSession();
    }

    public Source getSource() {
        return this.source;
    }

    public Store<X509CertificateHolder> getStore() {
        return this.store;
    }

    public boolean isCompleted() {
        return this.requestToRetry == null;
    }
}
