package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3.ZipkinConfig;

/* loaded from: classes4.dex */
public interface ZipkinConfigOrBuilder extends MessageOrBuilder {
    String getCollectorCluster();

    ByteString getCollectorClusterBytes();

    String getCollectorEndpoint();

    ByteString getCollectorEndpointBytes();

    ZipkinConfig.CollectorEndpointVersion getCollectorEndpointVersion();

    int getCollectorEndpointVersionValue();

    BoolValue getSharedSpanContext();

    BoolValueOrBuilder getSharedSpanContextOrBuilder();

    boolean getTraceId128Bit();

    boolean hasSharedSpanContext();
}
