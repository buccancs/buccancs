package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSourceOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface TlsSessionTicketKeysOrBuilder extends MessageOrBuilder {
    DataSource getKeys(int i);

    int getKeysCount();

    List<DataSource> getKeysList();

    DataSourceOrBuilder getKeysOrBuilder(int i);

    List<? extends DataSourceOrBuilder> getKeysOrBuilderList();
}
