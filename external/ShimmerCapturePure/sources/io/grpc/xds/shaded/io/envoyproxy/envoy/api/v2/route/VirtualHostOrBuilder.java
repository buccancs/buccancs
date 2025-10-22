package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValueOption;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValueOptionOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.VirtualHost;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface VirtualHostOrBuilder extends MessageOrBuilder {
    @Deprecated
    boolean containsPerFilterConfig(String str);

    boolean containsTypedPerFilterConfig(String str);

    CorsPolicy getCors();

    CorsPolicyOrBuilder getCorsOrBuilder();

    String getDomains(int i);

    ByteString getDomainsBytes(int i);

    int getDomainsCount();

    /* renamed from: getDomainsList */
    List<String> mo19475getDomainsList();

    HedgePolicy getHedgePolicy();

    HedgePolicyOrBuilder getHedgePolicyOrBuilder();

    boolean getIncludeAttemptCountInResponse();

    boolean getIncludeRequestAttemptCount();

    String getName();

    ByteString getNameBytes();

    @Deprecated
    Map<String, Struct> getPerFilterConfig();

    @Deprecated
    int getPerFilterConfigCount();

    @Deprecated
    Map<String, Struct> getPerFilterConfigMap();

    @Deprecated
    Struct getPerFilterConfigOrDefault(String str, Struct struct);

    @Deprecated
    Struct getPerFilterConfigOrThrow(String str);

    UInt32Value getPerRequestBufferLimitBytes();

    UInt32ValueOrBuilder getPerRequestBufferLimitBytesOrBuilder();

    RateLimit getRateLimits(int i);

    int getRateLimitsCount();

    List<RateLimit> getRateLimitsList();

    RateLimitOrBuilder getRateLimitsOrBuilder(int i);

    List<? extends RateLimitOrBuilder> getRateLimitsOrBuilderList();

    HeaderValueOption getRequestHeadersToAdd(int i);

    int getRequestHeadersToAddCount();

    List<HeaderValueOption> getRequestHeadersToAddList();

    HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i);

    List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList();

    String getRequestHeadersToRemove(int i);

    ByteString getRequestHeadersToRemoveBytes(int i);

    int getRequestHeadersToRemoveCount();

    /* renamed from: getRequestHeadersToRemoveList */
    List<String> mo19476getRequestHeadersToRemoveList();

    VirtualHost.TlsRequirementType getRequireTls();

    int getRequireTlsValue();

    HeaderValueOption getResponseHeadersToAdd(int i);

    int getResponseHeadersToAddCount();

    List<HeaderValueOption> getResponseHeadersToAddList();

    HeaderValueOptionOrBuilder getResponseHeadersToAddOrBuilder(int i);

    List<? extends HeaderValueOptionOrBuilder> getResponseHeadersToAddOrBuilderList();

    String getResponseHeadersToRemove(int i);

    ByteString getResponseHeadersToRemoveBytes(int i);

    int getResponseHeadersToRemoveCount();

    /* renamed from: getResponseHeadersToRemoveList */
    List<String> mo19477getResponseHeadersToRemoveList();

    RetryPolicy getRetryPolicy();

    RetryPolicyOrBuilder getRetryPolicyOrBuilder();

    Any getRetryPolicyTypedConfig();

    AnyOrBuilder getRetryPolicyTypedConfigOrBuilder();

    Route getRoutes(int i);

    int getRoutesCount();

    List<Route> getRoutesList();

    RouteOrBuilder getRoutesOrBuilder(int i);

    List<? extends RouteOrBuilder> getRoutesOrBuilderList();

    @Deprecated
    Map<String, Any> getTypedPerFilterConfig();

    int getTypedPerFilterConfigCount();

    Map<String, Any> getTypedPerFilterConfigMap();

    Any getTypedPerFilterConfigOrDefault(String str, Any any);

    Any getTypedPerFilterConfigOrThrow(String str);

    VirtualCluster getVirtualClusters(int i);

    int getVirtualClustersCount();

    List<VirtualCluster> getVirtualClustersList();

    VirtualClusterOrBuilder getVirtualClustersOrBuilder(int i);

    List<? extends VirtualClusterOrBuilder> getVirtualClustersOrBuilderList();

    boolean hasCors();

    boolean hasHedgePolicy();

    boolean hasPerRequestBufferLimitBytes();

    boolean hasRetryPolicy();

    boolean hasRetryPolicyTypedConfig();
}
