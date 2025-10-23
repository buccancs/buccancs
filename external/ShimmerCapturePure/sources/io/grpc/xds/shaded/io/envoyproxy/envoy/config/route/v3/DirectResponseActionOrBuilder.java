package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.DataSourceOrBuilder;

/* loaded from: classes4.dex */
public interface DirectResponseActionOrBuilder extends MessageOrBuilder {
    DataSource getBody();

    DataSourceOrBuilder getBodyOrBuilder();

    int getStatus();

    boolean hasBody();
}
