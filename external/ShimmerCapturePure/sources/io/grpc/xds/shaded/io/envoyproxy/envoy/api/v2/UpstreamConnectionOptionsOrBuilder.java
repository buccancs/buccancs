package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TcpKeepalive;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.TcpKeepaliveOrBuilder;

/* loaded from: classes3.dex */
public interface UpstreamConnectionOptionsOrBuilder extends MessageOrBuilder {
    TcpKeepalive getTcpKeepalive();

    TcpKeepaliveOrBuilder getTcpKeepaliveOrBuilder();

    boolean hasTcpKeepalive();
}
