package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface TlsSessionTicketKeysOrBuilder extends MessageOrBuilder {
    DataSource getKeys(int i);

    int getKeysCount();

    List<DataSource> getKeysList();

    DataSourceOrBuilder getKeysOrBuilder(int i);

    List<? extends DataSourceOrBuilder> getKeysOrBuilderList();
}
