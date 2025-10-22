package io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.HeaderMatcherOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Principal;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.PathMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.PathMatcherOrBuilder;

/* loaded from: classes4.dex */
public interface PrincipalOrBuilder extends MessageOrBuilder {
    Principal.Set getAndIds();

    Principal.SetOrBuilder getAndIdsOrBuilder();

    boolean getAny();

    Principal.Authenticated getAuthenticated();

    Principal.AuthenticatedOrBuilder getAuthenticatedOrBuilder();

    CidrRange getDirectRemoteIp();

    CidrRangeOrBuilder getDirectRemoteIpOrBuilder();

    HeaderMatcher getHeader();

    HeaderMatcherOrBuilder getHeaderOrBuilder();

    Principal.IdentifierCase getIdentifierCase();

    MetadataMatcher getMetadata();

    MetadataMatcherOrBuilder getMetadataOrBuilder();

    Principal getNotId();

    PrincipalOrBuilder getNotIdOrBuilder();

    Principal.Set getOrIds();

    Principal.SetOrBuilder getOrIdsOrBuilder();

    CidrRange getRemoteIp();

    CidrRangeOrBuilder getRemoteIpOrBuilder();

    @Deprecated
    CidrRange getSourceIp();

    @Deprecated
    CidrRangeOrBuilder getSourceIpOrBuilder();

    PathMatcher getUrlPath();

    PathMatcherOrBuilder getUrlPathOrBuilder();

    boolean hasAndIds();

    boolean hasAuthenticated();

    boolean hasDirectRemoteIp();

    boolean hasHeader();

    boolean hasMetadata();

    boolean hasNotId();

    boolean hasOrIds();

    boolean hasRemoteIp();

    @Deprecated
    boolean hasSourceIp();

    boolean hasUrlPath();
}
