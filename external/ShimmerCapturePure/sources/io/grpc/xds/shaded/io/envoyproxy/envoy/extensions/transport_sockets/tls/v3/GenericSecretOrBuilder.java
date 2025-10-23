package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSourceOrBuilder;

/* loaded from: classes4.dex */
public interface GenericSecretOrBuilder extends MessageOrBuilder {
    DataSource getSecret();

    DataSourceOrBuilder getSecretOrBuilder();

    boolean hasSecret();
}
