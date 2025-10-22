package io.grpc.xds.internal.sds;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import io.grpc.Status;
import io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolConfig;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import io.grpc.xds.EnvoyServerProtoData;
import io.grpc.xds.internal.sds.SslContextProvider;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CertificateValidationContext;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.CommonTlsContext;

import java.io.IOException;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class DynamicSslContextProvider extends SslContextProvider {
    protected final List<SslContextProvider.Callback> pendingCallbacks;
    @Nullable
    protected final CertificateValidationContext staticCertificateValidationContext;
    @Nullable
    protected SslContext sslContext;

    protected DynamicSslContextProvider(EnvoyServerProtoData.BaseTlsContext baseTlsContext, CertificateValidationContext certificateValidationContext) {
        super(baseTlsContext);
        this.pendingCallbacks = new ArrayList();
        this.staticCertificateValidationContext = certificateValidationContext;
    }

    protected abstract CertificateValidationContext generateCertificateValidationContext();

    @Nullable
    public SslContext getSslContext() {
        return this.sslContext;
    }

    protected abstract SslContextBuilder getSslContextBuilder(CertificateValidationContext certificateValidationContext) throws IOException, CertificateException, CertStoreException;

    protected final void updateSslContext() {
        SslContext sslContextBuild;
        List<SslContextProvider.Callback> listClonePendingCallbacksAndClear;
        try {
            SslContextBuilder sslContextBuilder = getSslContextBuilder(generateCertificateValidationContext());
            CommonTlsContext commonTlsContext = getCommonTlsContext();
            if (commonTlsContext != null && commonTlsContext.getAlpnProtocolsCount() > 0) {
                sslContextBuilder.applicationProtocolConfig(new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, (Iterable<String>) commonTlsContext.mo31646getAlpnProtocolsList()));
            }
            synchronized (this.pendingCallbacks) {
                sslContextBuild = sslContextBuilder.build();
                this.sslContext = sslContextBuild;
                listClonePendingCallbacksAndClear = clonePendingCallbacksAndClear();
            }
            makePendingCallbacks(sslContextBuild, listClonePendingCallbacksAndClear);
        } catch (Exception e) {
            onError(Status.fromThrowable(e));
            throw new RuntimeException(e);
        }
    }

    protected final void callPerformCallback(SslContextProvider.Callback callback, final SslContext sslContext) {
        performCallback(new SslContextProvider.SslContextGetter() { // from class: io.grpc.xds.internal.sds.DynamicSslContextProvider.1
            @Override // io.grpc.xds.internal.sds.SslContextProvider.SslContextGetter
            public SslContext get() {
                return sslContext;
            }
        }, callback);
    }

    @Override // io.grpc.xds.internal.sds.SslContextProvider
    public final void addCallback(SslContextProvider.Callback callback) {
        SslContext sslContext;
        Preconditions.checkNotNull(callback, "callback");
        synchronized (this.pendingCallbacks) {
            sslContext = this.sslContext;
            if (sslContext == null) {
                this.pendingCallbacks.add(callback);
                sslContext = null;
            }
        }
        if (sslContext != null) {
            callPerformCallback(callback, sslContext);
        }
    }

    private final void makePendingCallbacks(SslContext sslContext, List<SslContextProvider.Callback> list) {
        Iterator<SslContextProvider.Callback> it2 = list.iterator();
        while (it2.hasNext()) {
            callPerformCallback(it2.next(), sslContext);
        }
    }

    public final void onError(Status status) {
        Iterator<SslContextProvider.Callback> it2 = clonePendingCallbacksAndClear().iterator();
        while (it2.hasNext()) {
            it2.next().onException(status.asException());
        }
    }

    private List<SslContextProvider.Callback> clonePendingCallbacksAndClear() {
        ImmutableList immutableListCopyOf;
        synchronized (this.pendingCallbacks) {
            immutableListCopyOf = ImmutableList.copyOf((Collection) this.pendingCallbacks);
            this.pendingCallbacks.clear();
        }
        return immutableListCopyOf;
    }
}
