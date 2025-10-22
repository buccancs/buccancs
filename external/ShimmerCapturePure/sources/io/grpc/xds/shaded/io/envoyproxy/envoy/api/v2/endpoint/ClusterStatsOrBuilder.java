package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStats;

import java.util.List;

/* loaded from: classes3.dex */
public interface ClusterStatsOrBuilder extends MessageOrBuilder {
    String getClusterName();

    ByteString getClusterNameBytes();

    String getClusterServiceName();

    ByteString getClusterServiceNameBytes();

    ClusterStats.DroppedRequests getDroppedRequests(int i);

    int getDroppedRequestsCount();

    List<ClusterStats.DroppedRequests> getDroppedRequestsList();

    ClusterStats.DroppedRequestsOrBuilder getDroppedRequestsOrBuilder(int i);

    List<? extends ClusterStats.DroppedRequestsOrBuilder> getDroppedRequestsOrBuilderList();

    Duration getLoadReportInterval();

    DurationOrBuilder getLoadReportIntervalOrBuilder();

    long getTotalDroppedRequests();

    UpstreamLocalityStats getUpstreamLocalityStats(int i);

    int getUpstreamLocalityStatsCount();

    List<UpstreamLocalityStats> getUpstreamLocalityStatsList();

    UpstreamLocalityStatsOrBuilder getUpstreamLocalityStatsOrBuilder(int i);

    List<? extends UpstreamLocalityStatsOrBuilder> getUpstreamLocalityStatsOrBuilderList();

    boolean hasLoadReportInterval();
}
