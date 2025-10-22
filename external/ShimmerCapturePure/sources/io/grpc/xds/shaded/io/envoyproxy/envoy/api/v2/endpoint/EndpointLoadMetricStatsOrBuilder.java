package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes3.dex */
public interface EndpointLoadMetricStatsOrBuilder extends MessageOrBuilder {
    String getMetricName();

    ByteString getMetricNameBytes();

    long getNumRequestsFinishedWithMetric();

    double getTotalMetricValue();
}
