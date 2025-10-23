package io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.metadata.v3.MetadataKind;

/* loaded from: classes4.dex */
public interface MetadataKindOrBuilder extends MessageOrBuilder {
    MetadataKind.Cluster getCluster();

    MetadataKind.ClusterOrBuilder getClusterOrBuilder();

    MetadataKind.Host getHost();

    MetadataKind.HostOrBuilder getHostOrBuilder();

    MetadataKind.KindCase getKindCase();

    MetadataKind.Request getRequest();

    MetadataKind.RequestOrBuilder getRequestOrBuilder();

    MetadataKind.Route getRoute();

    MetadataKind.RouteOrBuilder getRouteOrBuilder();

    boolean hasCluster();

    boolean hasHost();

    boolean hasRequest();

    boolean hasRoute();
}
