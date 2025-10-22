package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HttpUri;

/* loaded from: classes6.dex */
public interface HttpUriOrBuilder extends MessageOrBuilder {
    String getCluster();

    ByteString getClusterBytes();

    HttpUri.HttpUpstreamTypeCase getHttpUpstreamTypeCase();

    Duration getTimeout();

    DurationOrBuilder getTimeoutOrBuilder();

    String getUri();

    ByteString getUriBytes();

    boolean hasTimeout();
}
