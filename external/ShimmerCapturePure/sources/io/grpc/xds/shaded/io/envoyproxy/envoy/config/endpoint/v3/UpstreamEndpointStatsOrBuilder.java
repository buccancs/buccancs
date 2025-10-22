package io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AddressOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface UpstreamEndpointStatsOrBuilder extends MessageOrBuilder {
    Address getAddress();

    AddressOrBuilder getAddressOrBuilder();

    EndpointLoadMetricStats getLoadMetricStats(int i);

    int getLoadMetricStatsCount();

    List<EndpointLoadMetricStats> getLoadMetricStatsList();

    EndpointLoadMetricStatsOrBuilder getLoadMetricStatsOrBuilder(int i);

    List<? extends EndpointLoadMetricStatsOrBuilder> getLoadMetricStatsOrBuilderList();

    Struct getMetadata();

    StructOrBuilder getMetadataOrBuilder();

    long getTotalErrorRequests();

    long getTotalIssuedRequests();

    long getTotalRequestsInProgress();

    long getTotalSuccessfulRequests();

    boolean hasAddress();

    boolean hasMetadata();
}
