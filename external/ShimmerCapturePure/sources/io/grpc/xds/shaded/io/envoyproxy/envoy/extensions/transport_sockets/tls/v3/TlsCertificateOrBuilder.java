package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSourceOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface TlsCertificateOrBuilder extends MessageOrBuilder {
    DataSource getCertificateChain();

    DataSourceOrBuilder getCertificateChainOrBuilder();

    DataSource getOcspStaple();

    DataSourceOrBuilder getOcspStapleOrBuilder();

    DataSource getPassword();

    DataSourceOrBuilder getPasswordOrBuilder();

    DataSource getPrivateKey();

    DataSourceOrBuilder getPrivateKeyOrBuilder();

    PrivateKeyProvider getPrivateKeyProvider();

    PrivateKeyProviderOrBuilder getPrivateKeyProviderOrBuilder();

    DataSource getSignedCertificateTimestamp(int i);

    int getSignedCertificateTimestampCount();

    List<DataSource> getSignedCertificateTimestampList();

    DataSourceOrBuilder getSignedCertificateTimestampOrBuilder(int i);

    List<? extends DataSourceOrBuilder> getSignedCertificateTimestampOrBuilderList();

    boolean hasCertificateChain();

    boolean hasOcspStaple();

    boolean hasPassword();

    boolean hasPrivateKey();

    boolean hasPrivateKeyProvider();
}
