package io.grpc.xds.shaded.io.envoyproxy.envoy.service.load_stats.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.NodeOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStats;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStatsOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface LoadStatsRequestOrBuilder extends MessageOrBuilder {
    ClusterStats getClusterStats(int i);

    int getClusterStatsCount();

    List<ClusterStats> getClusterStatsList();

    ClusterStatsOrBuilder getClusterStatsOrBuilder(int i);

    List<? extends ClusterStatsOrBuilder> getClusterStatsOrBuilderList();

    Node getNode();

    NodeOrBuilder getNodeOrBuilder();

    boolean hasNode();
}
