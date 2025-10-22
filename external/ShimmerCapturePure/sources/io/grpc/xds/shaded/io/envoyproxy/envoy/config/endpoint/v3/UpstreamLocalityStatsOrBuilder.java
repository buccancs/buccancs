package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.LocalityOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface UpstreamLocalityStatsOrBuilder extends MessageOrBuilder {
    EndpointLoadMetricStats getLoadMetricStats(int i);

    int getLoadMetricStatsCount();

    List<EndpointLoadMetricStats> getLoadMetricStatsList();

    EndpointLoadMetricStatsOrBuilder getLoadMetricStatsOrBuilder(int i);

    List<? extends EndpointLoadMetricStatsOrBuilder> getLoadMetricStatsOrBuilderList();

    Locality getLocality();

    LocalityOrBuilder getLocalityOrBuilder();

    int getPriority();

    long getTotalErrorRequests();

    long getTotalIssuedRequests();

    long getTotalRequestsInProgress();

    long getTotalSuccessfulRequests();

    UpstreamEndpointStats getUpstreamEndpointStats(int i);

    int getUpstreamEndpointStatsCount();

    List<UpstreamEndpointStats> getUpstreamEndpointStatsList();

    UpstreamEndpointStatsOrBuilder getUpstreamEndpointStatsOrBuilder(int i);

    List<? extends UpstreamEndpointStatsOrBuilder> getUpstreamEndpointStatsOrBuilderList();

    boolean hasLocality();
}
