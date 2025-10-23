package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.AddressOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
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
