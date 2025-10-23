package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface DatadogConfigOrBuilder extends MessageOrBuilder {
    String getCollectorCluster();

    ByteString getCollectorClusterBytes();

    String getServiceName();

    ByteString getServiceNameBytes();
}
