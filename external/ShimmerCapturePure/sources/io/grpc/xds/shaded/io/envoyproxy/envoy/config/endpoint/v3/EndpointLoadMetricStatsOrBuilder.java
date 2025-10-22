package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes6.dex */
public interface EndpointLoadMetricStatsOrBuilder extends MessageOrBuilder {
    String getMetricName();

    ByteString getMetricNameBytes();

    long getNumRequestsFinishedWithMetric();

    double getTotalMetricValue();
}
