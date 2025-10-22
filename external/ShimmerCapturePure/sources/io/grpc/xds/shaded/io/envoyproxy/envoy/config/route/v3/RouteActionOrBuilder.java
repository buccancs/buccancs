package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Metadata;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.MetadataOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.RoutingPriority;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteAction;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.RegexMatchAndSubstitute;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.RegexMatchAndSubstituteOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface RouteActionOrBuilder extends MessageOrBuilder {
    BoolValue getAutoHostRewrite();

    BoolValueOrBuilder getAutoHostRewriteOrBuilder();

    String getCluster();

    ByteString getClusterBytes();

    String getClusterHeader();

    ByteString getClusterHeaderBytes();

    RouteAction.ClusterNotFoundResponseCode getClusterNotFoundResponseCode();

    int getClusterNotFoundResponseCodeValue();

    RouteAction.ClusterSpecifierCase getClusterSpecifierCase();

    CorsPolicy getCors();

    CorsPolicyOrBuilder getCorsOrBuilder();

    Duration getGrpcTimeoutOffset();

    DurationOrBuilder getGrpcTimeoutOffsetOrBuilder();

    RouteAction.HashPolicy getHashPolicy(int i);

    int getHashPolicyCount();

    List<RouteAction.HashPolicy> getHashPolicyList();

    RouteAction.HashPolicyOrBuilder getHashPolicyOrBuilder(int i);

    List<? extends RouteAction.HashPolicyOrBuilder> getHashPolicyOrBuilderList();

    HedgePolicy getHedgePolicy();

    HedgePolicyOrBuilder getHedgePolicyOrBuilder();

    String getHostRewriteHeader();

    ByteString getHostRewriteHeaderBytes();

    String getHostRewriteLiteral();

    ByteString getHostRewriteLiteralBytes();

    RouteAction.HostRewriteSpecifierCase getHostRewriteSpecifierCase();

    Duration getIdleTimeout();

    DurationOrBuilder getIdleTimeoutOrBuilder();

    BoolValue getIncludeVhRateLimits();

    BoolValueOrBuilder getIncludeVhRateLimitsOrBuilder();

    @Deprecated
    RouteAction.InternalRedirectAction getInternalRedirectAction();

    @Deprecated
    int getInternalRedirectActionValue();

    InternalRedirectPolicy getInternalRedirectPolicy();

    InternalRedirectPolicyOrBuilder getInternalRedirectPolicyOrBuilder();

    Duration getMaxGrpcTimeout();

    DurationOrBuilder getMaxGrpcTimeoutOrBuilder();

    @Deprecated
    UInt32Value getMaxInternalRedirects();

    @Deprecated
    UInt32ValueOrBuilder getMaxInternalRedirectsOrBuilder();

    Metadata getMetadataMatch();

    MetadataOrBuilder getMetadataMatchOrBuilder();

    String getPrefixRewrite();

    ByteString getPrefixRewriteBytes();

    RoutingPriority getPriority();

    int getPriorityValue();

    RateLimit getRateLimits(int i);

    int getRateLimitsCount();

    List<RateLimit> getRateLimitsList();

    RateLimitOrBuilder getRateLimitsOrBuilder(int i);

    List<? extends RateLimitOrBuilder> getRateLimitsOrBuilderList();

    RegexMatchAndSubstitute getRegexRewrite();

    RegexMatchAndSubstituteOrBuilder getRegexRewriteOrBuilder();

    RouteAction.RequestMirrorPolicy getRequestMirrorPolicies(int i);

    int getRequestMirrorPoliciesCount();

    List<RouteAction.RequestMirrorPolicy> getRequestMirrorPoliciesList();

    RouteAction.RequestMirrorPolicyOrBuilder getRequestMirrorPoliciesOrBuilder(int i);

    List<? extends RouteAction.RequestMirrorPolicyOrBuilder> getRequestMirrorPoliciesOrBuilderList();

    RetryPolicy getRetryPolicy();

    RetryPolicyOrBuilder getRetryPolicyOrBuilder();

    Any getRetryPolicyTypedConfig();

    AnyOrBuilder getRetryPolicyTypedConfigOrBuilder();

    Duration getTimeout();

    DurationOrBuilder getTimeoutOrBuilder();

    RouteAction.UpgradeConfig getUpgradeConfigs(int i);

    int getUpgradeConfigsCount();

    List<RouteAction.UpgradeConfig> getUpgradeConfigsList();

    RouteAction.UpgradeConfigOrBuilder getUpgradeConfigsOrBuilder(int i);

    List<? extends RouteAction.UpgradeConfigOrBuilder> getUpgradeConfigsOrBuilderList();

    WeightedCluster getWeightedClusters();

    WeightedClusterOrBuilder getWeightedClustersOrBuilder();

    boolean hasAutoHostRewrite();

    boolean hasCors();

    boolean hasGrpcTimeoutOffset();

    boolean hasHedgePolicy();

    boolean hasIdleTimeout();

    boolean hasIncludeVhRateLimits();

    boolean hasInternalRedirectPolicy();

    boolean hasMaxGrpcTimeout();

    @Deprecated
    boolean hasMaxInternalRedirects();

    boolean hasMetadataMatch();

    boolean hasRegexRewrite();

    boolean hasRetryPolicy();

    boolean hasRetryPolicyTypedConfig();

    boolean hasTimeout();

    boolean hasWeightedClusters();
}
