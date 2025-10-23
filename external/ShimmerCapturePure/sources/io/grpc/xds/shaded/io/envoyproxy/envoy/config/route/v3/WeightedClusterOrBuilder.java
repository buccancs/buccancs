package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.WeightedCluster;

import java.util.List;

/* loaded from: classes4.dex */
public interface WeightedClusterOrBuilder extends MessageOrBuilder {
    WeightedCluster.ClusterWeight getClusters(int i);

    int getClustersCount();

    List<WeightedCluster.ClusterWeight> getClustersList();

    WeightedCluster.ClusterWeightOrBuilder getClustersOrBuilder(int i);

    List<? extends WeightedCluster.ClusterWeightOrBuilder> getClustersOrBuilderList();

    String getRuntimeKeyPrefix();

    ByteString getRuntimeKeyPrefixBytes();

    UInt32Value getTotalWeight();

    UInt32ValueOrBuilder getTotalWeightOrBuilder();

    boolean hasTotalWeight();
}
