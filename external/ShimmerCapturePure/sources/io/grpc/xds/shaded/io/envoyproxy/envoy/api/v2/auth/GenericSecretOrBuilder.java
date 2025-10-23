package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

/* loaded from: classes3.dex */
public interface GenericSecretOrBuilder extends MessageOrBuilder {
    DataSource getSecret();

    DataSourceOrBuilder getSecretOrBuilder();

    boolean hasSecret();
}
