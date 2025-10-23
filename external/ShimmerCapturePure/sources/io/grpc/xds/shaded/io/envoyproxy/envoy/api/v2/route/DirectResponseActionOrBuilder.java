package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

/* loaded from: classes5.dex */
public interface DirectResponseActionOrBuilder extends MessageOrBuilder {
    DataSource getBody();

    DataSourceOrBuilder getBodyOrBuilder();

    int getStatus();

    boolean hasBody();
}
