package io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.cluster.v3.Cluster;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.AddressOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BindConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.BindConfigOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.ConfigSourceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Http1ProtocolOptions;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Http1ProtocolOptionsOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Http2ProtocolOptions;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Http2ProtocolOptionsOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HttpProtocolOptions;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HttpProtocolOptionsOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocket;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TransportSocketOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.TypedExtensionConfigOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptions;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.UpstreamHttpProtocolOptionsOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignmentOrBuilder;

import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public interface ClusterOrBuilder extends MessageOrBuilder {
    boolean containsTypedExtensionProtocolOptions(String str);

    String getAltStatName();

    ByteString getAltStatNameBytes();

    CircuitBreakers getCircuitBreakers();

    CircuitBreakersOrBuilder getCircuitBreakersOrBuilder();

    Duration getCleanupInterval();

    DurationOrBuilder getCleanupIntervalOrBuilder();

    boolean getCloseConnectionsOnHostHealthFailure();

    Cluster.ClusterDiscoveryTypeCase getClusterDiscoveryTypeCase();

    Cluster.CustomClusterType getClusterType();

    Cluster.CustomClusterTypeOrBuilder getClusterTypeOrBuilder();

    HttpProtocolOptions getCommonHttpProtocolOptions();

    HttpProtocolOptionsOrBuilder getCommonHttpProtocolOptionsOrBuilder();

    Cluster.CommonLbConfig getCommonLbConfig();

    Cluster.CommonLbConfigOrBuilder getCommonLbConfigOrBuilder();

    Duration getConnectTimeout();

    DurationOrBuilder getConnectTimeoutOrBuilder();

    Cluster.RefreshRate getDnsFailureRefreshRate();

    Cluster.RefreshRateOrBuilder getDnsFailureRefreshRateOrBuilder();

    Cluster.DnsLookupFamily getDnsLookupFamily();

    int getDnsLookupFamilyValue();

    Duration getDnsRefreshRate();

    DurationOrBuilder getDnsRefreshRateOrBuilder();

    Address getDnsResolvers(int i);

    int getDnsResolversCount();

    List<Address> getDnsResolversList();

    AddressOrBuilder getDnsResolversOrBuilder(int i);

    List<? extends AddressOrBuilder> getDnsResolversOrBuilderList();

    Cluster.EdsClusterConfig getEdsClusterConfig();

    Cluster.EdsClusterConfigOrBuilder getEdsClusterConfigOrBuilder();

    Filter getFilters(int i);

    int getFiltersCount();

    List<Filter> getFiltersList();

    FilterOrBuilder getFiltersOrBuilder(int i);

    List<? extends FilterOrBuilder> getFiltersOrBuilderList();

    HealthCheck getHealthChecks(int i);

    int getHealthChecksCount();

    List<HealthCheck> getHealthChecksList();

    HealthCheckOrBuilder getHealthChecksOrBuilder(int i);

    List<? extends HealthCheckOrBuilder> getHealthChecksOrBuilderList();

    Http2ProtocolOptions getHttp2ProtocolOptions();

    Http2ProtocolOptionsOrBuilder getHttp2ProtocolOptionsOrBuilder();

    Http1ProtocolOptions getHttpProtocolOptions();

    Http1ProtocolOptionsOrBuilder getHttpProtocolOptionsOrBuilder();

    boolean getIgnoreHealthOnHostRemoval();

    Cluster.LbConfigCase getLbConfigCase();

    Cluster.LbPolicy getLbPolicy();

    int getLbPolicyValue();

    Cluster.LbSubsetConfig getLbSubsetConfig();

    Cluster.LbSubsetConfigOrBuilder getLbSubsetConfigOrBuilder();

    Cluster.LeastRequestLbConfig getLeastRequestLbConfig();

    Cluster.LeastRequestLbConfigOrBuilder getLeastRequestLbConfigOrBuilder();

    ClusterLoadAssignment getLoadAssignment();

    ClusterLoadAssignmentOrBuilder getLoadAssignmentOrBuilder();

    LoadBalancingPolicy getLoadBalancingPolicy();

    LoadBalancingPolicyOrBuilder getLoadBalancingPolicyOrBuilder();

    ConfigSource getLrsServer();

    ConfigSourceOrBuilder getLrsServerOrBuilder();

    UInt32Value getMaxRequestsPerConnection();

    UInt32ValueOrBuilder getMaxRequestsPerConnectionOrBuilder();

    Metadata getMetadata();

    MetadataOrBuilder getMetadataOrBuilder();

    String getName();

    ByteString getNameBytes();

    Cluster.OriginalDstLbConfig getOriginalDstLbConfig();

    Cluster.OriginalDstLbConfigOrBuilder getOriginalDstLbConfigOrBuilder();

    OutlierDetection getOutlierDetection();

    OutlierDetectionOrBuilder getOutlierDetectionOrBuilder();

    UInt32Value getPerConnectionBufferLimitBytes();

    UInt32ValueOrBuilder getPerConnectionBufferLimitBytesOrBuilder();

    Cluster.ClusterProtocolSelection getProtocolSelection();

    int getProtocolSelectionValue();

    boolean getRespectDnsTtl();

    Cluster.RingHashLbConfig getRingHashLbConfig();

    Cluster.RingHashLbConfigOrBuilder getRingHashLbConfigOrBuilder();

    TrackClusterStats getTrackClusterStats();

    TrackClusterStatsOrBuilder getTrackClusterStatsOrBuilder();

    @Deprecated
    boolean getTrackTimeoutBudgets();

    TransportSocket getTransportSocket();

    Cluster.TransportSocketMatch getTransportSocketMatches(int i);

    int getTransportSocketMatchesCount();

    List<Cluster.TransportSocketMatch> getTransportSocketMatchesList();

    Cluster.TransportSocketMatchOrBuilder getTransportSocketMatchesOrBuilder(int i);

    List<? extends Cluster.TransportSocketMatchOrBuilder> getTransportSocketMatchesOrBuilderList();

    TransportSocketOrBuilder getTransportSocketOrBuilder();

    Cluster.DiscoveryType getType();

    int getTypeValue();

    @Deprecated
    Map<String, Any> getTypedExtensionProtocolOptions();

    int getTypedExtensionProtocolOptionsCount();

    Map<String, Any> getTypedExtensionProtocolOptionsMap();

    Any getTypedExtensionProtocolOptionsOrDefault(String str, Any any);

    Any getTypedExtensionProtocolOptionsOrThrow(String str);

    BindConfig getUpstreamBindConfig();

    BindConfigOrBuilder getUpstreamBindConfigOrBuilder();

    TypedExtensionConfig getUpstreamConfig();

    TypedExtensionConfigOrBuilder getUpstreamConfigOrBuilder();

    UpstreamConnectionOptions getUpstreamConnectionOptions();

    UpstreamConnectionOptionsOrBuilder getUpstreamConnectionOptionsOrBuilder();

    UpstreamHttpProtocolOptions getUpstreamHttpProtocolOptions();

    UpstreamHttpProtocolOptionsOrBuilder getUpstreamHttpProtocolOptionsOrBuilder();

    boolean getUseTcpForDnsLookups();

    boolean hasCircuitBreakers();

    boolean hasCleanupInterval();

    boolean hasClusterType();

    boolean hasCommonHttpProtocolOptions();

    boolean hasCommonLbConfig();

    boolean hasConnectTimeout();

    boolean hasDnsFailureRefreshRate();

    boolean hasDnsRefreshRate();

    boolean hasEdsClusterConfig();

    boolean hasHttp2ProtocolOptions();

    boolean hasHttpProtocolOptions();

    boolean hasLbSubsetConfig();

    boolean hasLeastRequestLbConfig();

    boolean hasLoadAssignment();

    boolean hasLoadBalancingPolicy();

    boolean hasLrsServer();

    boolean hasMaxRequestsPerConnection();

    boolean hasMetadata();

    boolean hasOriginalDstLbConfig();

    boolean hasOutlierDetection();

    boolean hasPerConnectionBufferLimitBytes();

    boolean hasRingHashLbConfig();

    boolean hasTrackClusterStats();

    boolean hasTransportSocket();

    boolean hasUpstreamBindConfig();

    boolean hasUpstreamConfig();

    boolean hasUpstreamConnectionOptions();

    boolean hasUpstreamHttpProtocolOptions();
}
