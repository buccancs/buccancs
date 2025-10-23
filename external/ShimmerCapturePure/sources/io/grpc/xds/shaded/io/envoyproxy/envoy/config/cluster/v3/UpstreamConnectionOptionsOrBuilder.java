package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TcpKeepalive;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TcpKeepaliveOrBuilder;

/* loaded from: classes6.dex */
public interface UpstreamConnectionOptionsOrBuilder extends MessageOrBuilder {
    TcpKeepalive getTcpKeepalive();

    TcpKeepaliveOrBuilder getTcpKeepaliveOrBuilder();

    boolean hasTcpKeepalive();
}
