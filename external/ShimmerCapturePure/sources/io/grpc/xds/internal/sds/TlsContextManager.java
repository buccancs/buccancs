package io.grpc.xds.internal.sds;

import io.grpc.xds.EnvoyServerProtoData;

/* loaded from: classes3.dex */
public interface TlsContextManager {
    SslContextProvider findOrCreateClientSslContextProvider(EnvoyServerProtoData.UpstreamTlsContext upstreamTlsContext);

    SslContextProvider findOrCreateServerSslContextProvider(EnvoyServerProtoData.DownstreamTlsContext downstreamTlsContext);

    SslContextProvider releaseClientSslContextProvider(SslContextProvider sslContextProvider);

    SslContextProvider releaseServerSslContextProvider(SslContextProvider sslContextProvider);
}
