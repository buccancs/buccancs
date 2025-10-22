package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes5.dex */
public interface SSLSocketFactoryCreator {
    SSLSocketFactory createFactory() throws Exception;

    boolean isTrusted();
}
